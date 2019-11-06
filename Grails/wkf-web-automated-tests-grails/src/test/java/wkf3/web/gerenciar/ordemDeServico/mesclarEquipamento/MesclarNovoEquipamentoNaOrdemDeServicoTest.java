package wkf3.web.gerenciar.ordemDeServico.mesclarEquipamento;

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

public class MesclarNovoEquipamentoNaOrdemDeServicoTest extends TestBase {

	private static final String USER_MERGE_NEW_EQUIP = "USER_MERGE_NEQ_" + randomString(5);
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String CUSTOMER_GENERIC = "CUSTOMER_GENERIC";
	private static final String SERVICE_GENERIC = "SERVICE_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_SERVICE_PROVIDER",
			"ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER", "ROLE_SERVICE_ORDER_CONSUMPTION_REPORT",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_SERVICE_ORDER_HISTORY",
			"ROLE_USER_VIEW_ALL_SERVICE_ORDERS", "ROLE_SERVICE_PROVIDER", "ROLE_STOCK",
			"ROLE_STOCK_PLANNING_MANAGEMENT", "ROLE_STOCK_PLANNING_VIEW", "ROLE_VIEW_OTHER_STOCK", "ROLE_EQUIPMENT",
			"ROLE_EQUIPMENT_ADMIN", "ROLE_EQUIPMENT_ORIGIN");

	private MainPage navBar;
	private String serialNumberNewEquipment;
	private ServiceOrderCrudForm serviceOrderCrudForm;
	private ServiceOrderModalEquipmentFind serviceOrderModalEquipmentFind;
	private ServiceOrderModalEquipmentCreateAndEdit serviceOrderModalEquipmentCreateAndEdit;

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
	public void loadDataMesclarEquipamentoNaOrdemDeServico() {

		serialNumberNewEquipment = generateLengthNumber(8);

		// Criando o Equipamento
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "CNPJ", CUSTOMER_DOCUMENT_NUMBER, "",
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD", serialNumberNewEquipment, null, false);

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER, SERVICE_GENERIC);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_MERGE_NEW_EQUIP, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataMesclarEquipamentoNaOrdemDeServico" })
	public void deveraDarErroQuandoTentoMesclarUmEquipamentoNaOrdemDeServicoSemPrestadorDeServico() {

		// Criando um Equipamento
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchEquipment();
		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Criando um Novo Equipamento
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchNewEquipment();
		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderModalEquipmentCreateAndEdit.setSerialNumber(generateLengthNumber(8));
		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Tentando pegar a mensagem do Alert para validar
		String alert = serviceOrderCrudForm.clickMergeNewEquipmentFail();

		serviceOrderCrudForm.assertEquals(alert,
				"Para Mesclar o Equipamento, o Prestador de Serviços deve ser definido!", "Mensagem Diferente");

		serviceOrderCrudForm.acceptMessageAlert();
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoTentoMesclarUmEquipamentoNaOrdemDeServicoSemPrestadorDeServico" })
	public void deveraDarErroQuandoTentoMesclarUmEquipamentoNaOrdemDeServicoComNumeroDeSerie() {

		serviceOrderCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Tentando pegar a mensagem do Alert para validar
		String alert = serviceOrderCrudForm.clickMergeNewEquipmentFail();

		serviceOrderCrudForm.assertEquals(alert,
				"Apenas é permitido Mesclar Equipamentos não inventáriados e que não possuam Número de Série!",
				"Mensagem Diferente");

		serviceOrderCrudForm.acceptMessageAlert();
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoTentoMesclarUmEquipamentoNaOrdemDeServicoComNumeroDeSerie" })
	public void deveraMesclarUmEquipamentoComSucessoNaOrdemDeServicoQuandoOsRequisitosMinimosForemAtendidos() {

		serviceOrderCrudForm.buttonRemovedNewEquipment();

		// Criando um Novo Equipamento
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchNewEquipment();
		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Fazendo a Mesclagem
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickMergeNewEquipment();
		serviceOrderModalEquipmentFind.setSerialNumber(serialNumberNewEquipment);

		serviceOrderModalEquipmentFind.validateIfContainsTheRowInTable(Arrays.asList(serialNumberNewEquipment));

		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.clickSelectAndEdit(1);
		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Verificando o Estoque
		StockControlListPage stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(serialNumberNewEquipment);
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(serialNumberNewEquipment, CONTRACTOR_GENERIC,
				CUSTOMER_GENERIC + " - " + CUSTOMER_DOCUMENT_NUMBER));
	}
}
