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

public class TriggerDesassociaEquipamentoDoClienteTest extends TestBase {

	private static final String USER_TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER = "USER_TR_DIS_EQ_CL_"
			+ randomString(5);
	private static final String TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER = "TRIGGER_DISASSOCIATE_EQUIP_OF_CUSTOMER";
	private static final String SERVICE_COMMON_TRIGGER = "SERVICE_COMMON_TRIGGER";
	private static final String CUSTOMER_GENERIC = "CUSTOMER_GENERIC";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
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
	public void loadDataTriggerDesassociaEquipamentoDoCliente() {

		serialNumberEquipment = generateLengthNumber(8);

		// Criando o Equipamento
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "CNPJ", CUSTOMER_DOCUMENT_NUMBER, "",
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD", serialNumberEquipment, null, false);

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_COMMON_TRIGGER);

		// Pegando o Id da Ordem de Serviço no Console
		idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER, "123456", ROLES, "pt_BR");
	}

	@Test(dependsOnMethods = { "loadDataTriggerDesassociaEquipamentoDoCliente" })
	public void deveraAcionarOEventoDaTriggerQuandoATriggerDeDesassociaOEquipamentoDoClienteEstaHabilitada() {

		// Verificando se o Equipamento esta com o Cliente
		StockControlListPage stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(serialNumberEquipment);
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(
				Arrays.asList(serialNumberEquipment, CUSTOMER_GENERIC + " - " + CUSTOMER_DOCUMENT_NUMBER));

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);

		// Adicionando o Equipamento na Ordem de Serviço
		ServiceOrderModalEquipmentFind serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchEquipment();
		serviceOrderModalEquipmentFind.setSerialNumber(serialNumberEquipment);

		serviceOrderModalEquipmentFind.validateIfContainsTheRowInTable(Arrays.asList(serialNumberEquipment));

		ServiceOrderModalEquipmentCreateAndEdit serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind
				.clickSelectAndEdit(1);

		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		serviceOrderCrudForm.setStatus(TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Verificando no Estoque se o Equipamento não esta com o Cliente
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(serialNumberEquipment);
		stockControlListPage.buttonSearch();

		stockControlListPage.validateNotContainsRowInTable(
				Arrays.asList(CUSTOMER_GENERIC + " - " + CUSTOMER_DOCUMENT_NUMBER), "Esta com o Cliente");
	}
}
