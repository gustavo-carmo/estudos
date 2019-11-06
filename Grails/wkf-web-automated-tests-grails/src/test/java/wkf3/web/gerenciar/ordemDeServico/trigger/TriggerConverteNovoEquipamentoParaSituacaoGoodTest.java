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

public class TriggerConverteNovoEquipamentoParaSituacaoGoodTest extends TestBase {

	private static final String USER_TRIGGER_CHANGE_NEW_EQUIPMENT_TO_GOOD = "USER_TR_CHANGE_NEQ_GOOD_"
			+ randomString(5);
	private static final String TRIGGER_CHANGE_NEW_EQUIPMENT_TO_BAD = "TRIGGER_CHANGE_NEW_EQUIP_TO_BAD";
	private static final String TRIGGER_CHANGE_NEW_EQUIPMENT_TO_GOOD = "TRIGGER_CHANGE_NEW_EQUIP_TO_GOOD";
	private static final String SERVICE_COMMON_TRIGGER = "SERVICE_COMMON_TRIGGER";
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
	private String serialNumberNewEquipment;
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
	public void loadDataTriggerConventerNovoEquipamentoParaSituacaoGood() {

		serialNumberNewEquipment = generateLengthNumber(8);

		// Criando o Equipamento
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "CNPJ", CUSTOMER_DOCUMENT_NUMBER, "",
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD", serialNumberNewEquipment, null, false);

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_COMMON_TRIGGER);

		// Pegando o Id da Ordem de Serviço no Console
		idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_TRIGGER_CHANGE_NEW_EQUIPMENT_TO_GOOD, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataTriggerConventerNovoEquipamentoParaSituacaoGood" })
	public void deveraAcionarOEventoDaTriggerQuandoATriggerDeConverteONovoEquipamentoParaSituacaoGOODstaHabilitada() {

		// Adicionando o Equipamento
		ServiceOrderModalEquipmentFind serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchEquipment();
		ServiceOrderModalEquipmentCreateAndEdit serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind
				.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Adicionando o Novo Equipamento na Ordem de Serviço
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchNewEquipment();
		serviceOrderModalEquipmentFind.setSerialNumber(serialNumberNewEquipment);

		serviceOrderModalEquipmentFind.validateIfContainsTheRowInTable(Arrays.asList(serialNumberNewEquipment));

		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.clickSelectAndEdit(1);

		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		serviceOrderCrudForm.setStatus(TRIGGER_CHANGE_NEW_EQUIPMENT_TO_BAD);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Verificando no Estoque se o Novo Equipamento esta com Situação Bad
		StockControlListPage stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(serialNumberNewEquipment);
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(serialNumberNewEquipment, "Bad"));

		// Voltando para a Ordem de Serviço para Mudar a Situação do Novo
		// Equipamento para Good
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);

		serviceOrderCrudForm.setStatus(TRIGGER_CHANGE_NEW_EQUIPMENT_TO_GOOD);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Verificando no Novo Estoque se o Equipamento esta com Situação Good
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(serialNumberNewEquipment);
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(serialNumberNewEquipment, "Good"));
	}
}
