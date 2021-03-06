package wkf3.web.gerenciar.ordemDeServico.trigger;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalEquipmentCreateAndEdit;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalEquipmentFind;
import br.com.workfinity.web.page.stock.StockControlListPage;
import careman.html.TestBase;

public class TriggerDesassociaEquipamentoDoClienteMantemSituacaoGoodAssociaEquipamentoAoPrestadorDeServicoTest
		extends TestBase {

	private static final String USER_TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_ASSOCIATE_TO_SERVICE_PROVIDER_ = "USER_TR_DIS_CL_GOOD_ASS_PS_"
			+ randomString(4);
	private static final String TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_ASSOCIATE_TO_SERVICE_PROVIDER = "TRIGGER_DISASSO_CUSTOMER_GOOD_ASSOCI_TO_SP";
	private static final String SERVICE_COMMON_TRIGGER = "SERVICE_COMMON_TRIGGER";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String CUSTOMER_GENERIC = "CUSTOMER_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_SERVICE_PROVIDER",
			"ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER", "ROLE_SERVICE_ORDER_CONSUMPTION_REPORT",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_SERVICE_ORDER_HISTORY",
			"ROLE_USER_VIEW_ALL_SERVICE_ORDERS", "ROLE_STOCK", "ROLE_STOCK_PLANNING_MANAGEMENT",
			"ROLE_STOCK_PLANNING_VIEW", "ROLE_VIEW_OTHER_STOCK", "ROLE_EQUIPMENT", "ROLE_EQUIPMENT_ADMIN",
			"ROLE_EQUIPMENT_ORIGIN");

	private MainPage navBar;
	private String serialNumberEquipment;
	private ServiceOrderCrudForm serviceOrderCrudForm;
	private String idServiceOrder;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, false);

		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void loadDataTriggerDesassociaEquipamentoDoClienteMantemGoodEAssociaAoPrestadorDeServico() {

		serialNumberEquipment = generateLengthNumber(8);

		// Criando o Equipamento
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "CNPJ", CUSTOMER_DOCUMENT_NUMBER, "",
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD", serialNumberEquipment, null, false);

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_COMMON_TRIGGER);

		// Pegando o Id da Ordem de Serviço no Console
		idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(
				USER_TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_ASSOCIATE_TO_SERVICE_PROVIDER_,
				"123456", ROLES, "pt_BR");
	}

	@Test(dependsOnMethods = { "loadDataTriggerDesassociaEquipamentoDoClienteMantemGoodEAssociaAoPrestadorDeServico" })
	public void deveraAcionarOEventoDaTriggerQuandoATriggerDeDesassociaOEquipamentoDoClienteMantemASituacaoComoGOODEAssociaOEquipamentoAoPrestadorDeServicosEstaHabilitada() {

		// Visitando o Estoque para ver se o Equipamento esta com o Cliente
		StockControlListPage stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(serialNumberEquipment);
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(
				Arrays.asList(serialNumberEquipment, CUSTOMER_GENERIC + " - " + CUSTOMER_DOCUMENT_NUMBER));

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);

		serviceOrderCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Adicionando o Equipamento na Ordem de Serviço
		ServiceOrderModalEquipmentFind serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchEquipment();
		serviceOrderModalEquipmentFind.setSerialNumber(serialNumberEquipment);

		serviceOrderModalEquipmentFind.validateIfContainsTheRowInTable(Arrays.asList(serialNumberEquipment));

		ServiceOrderModalEquipmentCreateAndEdit serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind
				.clickSelectAndEdit(1);

		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		serviceOrderCrudForm.setStatus(
				TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_ASSOCIATE_TO_SERVICE_PROVIDER);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Verificando se o Equipamento esta com o Prestador
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(serialNumberEquipment);
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(
				Arrays.asList(serialNumberEquipment, SERVICE_PROVIDER_GENERIC_MATRIZ, "Good"));
	}
}