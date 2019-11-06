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

public class MesclarEquipamentoNaOrdemDeServicoTest extends TestBase {

	private static final String USER_MERGE_EQUIP = "USER_MERGE_EQUIP_" + randomString(5);
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
	private String serialNumberEquipment;
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

		serialNumberEquipment = generateLengthNumber(8);

		// Criando o Equipamento
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "CNPJ", CUSTOMER_DOCUMENT_NUMBER, "",
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD", serialNumberEquipment, null, false);

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER, SERVICE_GENERIC);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_MERGE_EQUIP, "123456", ROLES, "pt_BR");

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
		serviceOrderModalEquipmentCreateAndEdit.setSerialNumber(generateLengthNumber(8));
		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Testando fazer o Merge Sem Prestador de Serviço
		String alert = serviceOrderCrudForm.clickMergeEquipmentFail();

		serviceOrderCrudForm.assertEquals(alert,
				"Para Mesclar o Equipamento, o Prestador de Serviços deve ser definido!", "Mensagem Diferente");

		serviceOrderCrudForm.acceptMessageAlert();
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoTentoMesclarUmEquipamentoNaOrdemDeServicoSemPrestadorDeServico" })
	public void deveraDarErroQuandoTentoMesclarUmEquipamentoNaOrdemDeServicoComNumeroDeSerie() {

		serviceOrderCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Testando fazer o Merge Com Número de Série
		String alert = serviceOrderCrudForm.clickMergeEquipmentFail();

		serviceOrderCrudForm.assertEquals(alert,
				"Apenas é permitido Mesclar Equipamentos não inventáriados e que não possuam Número de Série!",
				"Mensagem Diferente");

		serviceOrderCrudForm.acceptMessageAlert();
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoTentoMesclarUmEquipamentoNaOrdemDeServicoComNumeroDeSerie" })
	public void deveraMesclarUmEquipamentoComSucessoNaOrdemDeServicoQuandoOsRequisitosMinimosForemAtendidos() {

		serviceOrderCrudForm.buttonRemovedEquipment();

		// Criando um Equipamento
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchEquipment();
		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Fazendo a Mesclagem
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickMergeEquipment();
		serviceOrderModalEquipmentFind.setSerialNumber(serialNumberEquipment);

		serviceOrderModalEquipmentFind.validateIfContainsTheRowInTable(Arrays.asList(serialNumberEquipment));

		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.clickSelectAndEdit(1);
		serviceOrderCrudForm = serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Verificando o Estoque
		StockControlListPage stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(serialNumberEquipment);
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(serialNumberEquipment, CONTRACTOR_GENERIC,
				CUSTOMER_GENERIC + " - " + CUSTOMER_DOCUMENT_NUMBER));
	}
}
