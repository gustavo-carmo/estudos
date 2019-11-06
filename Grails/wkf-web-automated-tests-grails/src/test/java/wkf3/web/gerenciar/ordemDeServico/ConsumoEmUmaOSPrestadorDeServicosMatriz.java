package wkf3.web.gerenciar.ordemDeServico;

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
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudFormConsumptionModel;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderListPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderCrudForm;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderListPage;
import br.com.workfinity.web.page.stock.StockControlListPage;
import br.com.workfinity.web.page.stock.StockControlModelAddToShipmentOrder;
import careman.html.TestBase;

public class ConsumoEmUmaOSPrestadorDeServicosMatriz extends TestBase {

	private static final String USERNAME = "CONSUMO_OS_PS_MATRIZ_" + randomString(5);
	private static final String SERVICE = "SERVICO_CONSUMO_PSM_" + randomString(5);
	private static final String TECNICO = "TECNICO_" + randomString(5);

	private static final String MODEL_PRODUCT = "PRODUCT_MODEL";
	private static final String MODEL_PRODUCT_2 = "PRODUCT_MODEL_2";
	private static final String MODEL_PART_1 = "PART_1";
	private static final String MODEL_PART_2 = "PART_2";
	private static final String MODEL_PART_3 = "PART_3";
	private static final String MODEL_PART_4 = "PART_4";
	private static final String EQUIPMENT_SN_MODEL_2 = "87945498";

	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String SERVICE_PROVIDER_GENERIC_TECHNICIAN = "SERVICE_PROVIDER_GENERIC_TECHNICIAN";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String CUSTOMER_GENERIC = "CUSTOMER_GENERIC";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String WORKFLOW_GENERIC = "WORKFLOW_GENERIC";
	private static final String STATUS_GENERIC_NEW = "STATUS_GENERIC_NEW";
	private static final String SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION = "SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION";
	private static final String STATUS_SHIP_ORDER_GENERIC_NEW = "STATUS_SHIP_ORDER_GENERIC_NEW";
	private static final String STATUS_SHIP_ORDER_GENERIC_END = "STATUS_SHIP_ORDER_GENERIC_END";

	private static final List<String> ROLES_TECHNICIAN = Arrays.asList("ROLE_ATTACHMENT",
			"ROLE_CUSTOM_FIELD_RESTRICTED_VIEW_EDIT", "ROLE_SERVICE_ORDER", "ROLE_SERVICE_ORDER_HISTORY",
			"ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_SERVICE_ORDER_SERVICE_PROVIDER");

	private MainPage navBar;
	private ServiceOrderCrudForm serviceOrderCrud;
	private ServiceOrderCrudFormConsumptionModel consumptionModel;
	private StockControlListPage stockControl;
	private String idFirstServiceOrder;
	private ServiceOrderListPage serviceOrderListPage;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, true);

		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void doLoadDataScripts() {

		// Criando os Modelos Específicos do Teste
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PART_1,
				"PART");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PART_2, "PART", "",
				null, true);
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PART_3,
				"PART");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PRODUCT,
				"PRODUCT", "", Arrays.asList(MODEL_PART_1, MODEL_PART_2, MODEL_PART_3), false);

		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PART_4,
				"PART");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PRODUCT_2,
				"PRODUCT", "", Arrays.asList(MODEL_PART_4), false);

		// Criando o Serviço Específico para esse Teste
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE, CONTRACTOR_GENERIC,
				SERVICE_GROUP_GENERIC, WORKFLOW_GENERIC, OPENING_HOURS_GROUP_GENERIC,
				Arrays.asList("service.allowsEquipmentWithOnlyModelOnServiceOrder = true"), null);

		// Criando o Técnico
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECNICO, "123456", ROLES_TECHNICIAN,
				SERVICE_PROVIDER_GENERIC_TECHNICIAN);

		// Criando os Equipamentos Por Scripts
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_PRODUCT, "GOOD", "74984654", null, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_PART_1, "GOOD", "15468798", null, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_PART_2, "GOOD", "87945498", null, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_PART_3, "BAD", "65465413", null, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_PRODUCT_2, "GOOD", "65497984", null, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, MANUFACTORE_GENERIC, MODEL_PART_1, "GOOD", "96467942", null,
				false);

		// Logando com o Usuário
		createUserAndDoLogin(USERNAME, "123456", userRoles);
	}

	@Test(dependsOnMethods = { "doLoadDataScripts" })
	public void doLoadDataOrdemDeRemessa() {

		// Adicionando Consumo a um Técnico
		ShipmentOrderListPage shipmentOrderListPage = navBar.visitShipmentOrder();
		ShipmentOrderCrudForm shipmentOrderCrud = shipmentOrderListPage.buttonNew();

		shipmentOrderCrud.setFrom("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_TECHNICIAN);
		shipmentOrderCrud.setTo("Usuário: " + TECNICO);
		shipmentOrderCrud.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION);
		shipmentOrderCrud.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrud.setInvoiceNumber(randomNumber(5));
		shipmentOrderCrud.buttonCreate();

		shipmentOrderCrud.validateMessageSuccessCreated();

		String shipmentCode = shipmentOrderCrud.getCode();

		// Visitando o Estoque
		stockControl = navBar.visitStockControl();
		stockControl.setFamily(FAMILY_GENERIC);
		stockControl.buttonSearch();

		stockControl.clickCheckUnCheckByArguments(MODEL_PART_1, "96467942");
		StockControlModelAddToShipmentOrder stockControlModel = stockControl.buttonAddToShipmentOrder();

		stockControlModel.setShipmentOrder(
				shipmentCode + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN + " -> " + TECNICO + " (Eq: 0, NEq: 0)");
		stockControlModel.buttonAdd();

		stockControlModel.validateMessageSuccess("Equipamentos: [" + MODEL_PART_1 + " - 96467942] adicionados em "
				+ shipmentCode + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN + " -> " + TECNICO + " (Eq: 1, NEq: 0)");

		stockControlModel.buttonClose();

		// Voltando para a Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();

		shipmentOrderListPage.setCode(shipmentCode);
		shipmentOrderListPage.buttonSearch();

		shipmentOrderCrud = shipmentOrderListPage.editItemTable(1);

		shipmentOrderCrud.setStatus(STATUS_SHIP_ORDER_GENERIC_END);
		shipmentOrderCrud.buttonUpdate();

		shipmentOrderCrud.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "doLoadDataOrdemDeRemessa" })
	public void doLoadDataOrdemDeServico() {

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderCrud = serviceOrderListPage.buttonNew();

		serviceOrderCrud.setDocument("CNPJ", CUSTOMER_DOCUMENT_NUMBER);
		serviceOrderCrud.setServiceGroup(SERVICE_GROUP_GENERIC);
		serviceOrderCrud.setService(SERVICE);
		serviceOrderCrud.setModel(MODEL_PRODUCT);
		serviceOrderCrud.setStatus(STATUS_GENERIC_NEW);
		serviceOrderCrud.buttonCreate();

		serviceOrderCrud.validateMessageSuccessCreated();

		idFirstServiceOrder = serviceOrderCrud.getId();

		serviceOrderCrud.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		serviceOrderCrud.buttonUpdate();

		serviceOrderCrud.validateMessageSuccessUpdate();

		// Acessando a Segunda Ordem de Serviço que foi Criada
		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderCrud = serviceOrderListPage.buttonNew();

		serviceOrderCrud.setDocument("CNPJ", CUSTOMER_DOCUMENT_NUMBER);
		serviceOrderCrud.setServiceGroup(SERVICE_GROUP_GENERIC);
		serviceOrderCrud.setService(SERVICE);
		serviceOrderCrud.setModel(MODEL_PRODUCT_2);
		serviceOrderCrud.setStatus(STATUS_GENERIC_NEW);
		serviceOrderCrud.buttonCreate();

		serviceOrderCrud.validateMessageSuccessCreated();

		serviceOrderCrud.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		serviceOrderCrud.buttonUpdate();

		serviceOrderCrud.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "doLoadDataOrdemDeServico" })
	public void visualizandoOsConsumosDePartesRealizadosEmUmaOrdemDeServico() {

		serviceOrderCrud.buttonConsumption();
		consumptionModel = serviceOrderCrud.buttonAddConsumption();

		consumptionModel.goToTabParts();
		consumptionModel.validateIfDontHaveParts();
		consumptionModel.buttonCancel();

		// Acessando a Primeira Ordem de Serviço que foi Criada
		serviceOrderCrud = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idFirstServiceOrder);

		serviceOrderCrud.buttonConsumption();
		consumptionModel = serviceOrderCrud.buttonAddConsumption();

		consumptionModel.goToTabParts();
		consumptionModel.validateIfHaveParts();
	}

	@Test(dependsOnMethods = { "visualizandoOsConsumosDePartesRealizadosEmUmaOrdemDeServico" })
	public void visualizandoAsPartesDisponiveisParaOConsumoEmUmaOrdemDeServicoDoEstoqueDeUmPrestadorDeServicosMatriz() {

		consumptionModel.validateThereAreRowInTablePart(Arrays.asList(MODEL_PART_1, "15468798"));
		consumptionModel.validateThereAreRowInTablePart(Arrays.asList(MODEL_PART_2, "87945498"));
	}

	@Test(dependsOnMethods = {
			"visualizandoAsPartesDisponiveisParaOConsumoEmUmaOrdemDeServicoDoEstoqueDeUmPrestadorDeServicosMatriz" })
	public void visualizandoAsPartesParaConsumoDeUmModeloQueSejaConsumivelEmUmaOrdemDeServico() {

		consumptionModel.validateIfWithdrawnEquipmentIsDisable("87945498");
	}

	@Test(dependsOnMethods = { "visualizandoAsPartesParaConsumoDeUmModeloQueSejaConsumivelEmUmaOrdemDeServico" })
	public void visualizandoAsPartesParaConsumoDeUmModeloQueNaoSejaConsumivelEmUmaOrdemDeServico() {

		consumptionModel.validateIfWithdrawnEquipmentIsEnabled(MODEL_PART_1);
	}

	@Test(dependsOnMethods = { "visualizandoAsPartesParaConsumoDeUmModeloQueNaoSejaConsumivelEmUmaOrdemDeServico" })
	public void pesquisandoPorPartesASeremConsumidasEmUmaOrdemDeServico() {

		consumptionModel.validateIfFieldIsVisible("consumption_part_id");
		consumptionModel.validateIfFieldIsVisible("consumption_part_serialNumber");
		consumptionModel.setPart(MODEL_PART_1);

		consumptionModel.validateThereAreRowInTablePart(Arrays.asList(MODEL_PART_1));

		consumptionModel.setPart(MODEL_PART_2);
		consumptionModel.setSerialNumber(EQUIPMENT_SN_MODEL_2);

		consumptionModel.validateThereAreRowInTablePart(Arrays.asList(MODEL_PART_2));
	}

	@Test(dependsOnMethods = { "pesquisandoPorPartesASeremConsumidasEmUmaOrdemDeServico" })
	public void adicionandoConsumosDeInstalacaoDePartesEmUmaOrdemDeServico() {

		consumptionModel.selectItemTable(1);

		if (getDriver().findElement(By.cssSelector("#mainModalDiv")).isDisplayed()) {

			// TODO POR ALGUM MOTIVO O CLICK ANTERIOR NÃO FUNCIONOU - VERIFICAR ESSE MOTIVO
			getDriver().findElement(By.cssSelector("#partTab table tbody tr:nth-child(" + 1 + ") a i")).click();
			waitAjaxEnd();
		}

		// Visitando o Estoque
		stockControl = navBar.visitStockControl();

		stockControl.setSerialNumber(EQUIPMENT_SN_MODEL_2);
		stockControl.buttonSearch();

		stockControl.validateIfContainsTheRowInTable(
				Arrays.asList(EQUIPMENT_SN_MODEL_2, MODEL_PART_2, CUSTOMER_DOCUMENT_NUMBER));
	}

	@Test(dependsOnMethods = { "adicionandoConsumosDeInstalacaoDePartesEmUmaOrdemDeServico" })
	public void adicionandoConsumosDeTrocaDePartesEmUmaOrdemDeServico() {

		// Acessando a Primeira Ordem de Serviço que foi Criada
		serviceOrderCrud = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idFirstServiceOrder);

		serviceOrderCrud.buttonConsumption();
		consumptionModel = serviceOrderCrud.buttonAddConsumption();

		consumptionModel.goToTabParts();

		consumptionModel.setPart(MODEL_PART_1);
		consumptionModel.setWithdrawnEquipment(MODEL_PART_3, "15487965");

		consumptionModel.selectItemTable(1);

		// Visitando o Estoque
		stockControl = navBar.visitStockControl();

		stockControl.setSerialNumber("");
		stockControl.buttonSearch();

		stockControl.validateIfContainsTheRowInTable(Arrays.asList(CONTRACTOR_GENERIC, MODEL_PART_1, "Good",
				CUSTOMER_GENERIC + " - " + CUSTOMER_DOCUMENT_NUMBER));

		stockControl.validateIfContainsTheRowInTable(
				Arrays.asList(CONTRACTOR_GENERIC, MODEL_PART_3, "Bad", SERVICE_PROVIDER_GENERIC_MATRIZ));
	}

	@Test(dependsOnMethods = { "adicionandoConsumosDeTrocaDePartesEmUmaOrdemDeServico" })
	public void removendoConsumosDeInstalacaoDePartesDeUmaOrdemDeServico() {

		// Acessando a Primeira Ordem de Serviço que foi Criada
		serviceOrderCrud = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idFirstServiceOrder);

		serviceOrderCrud.buttonConsumption();
		serviceOrderCrud.removeConsumption(Arrays.asList("Instalação", MODEL_PART_2));

		// Visitando o Estoque
		stockControl = navBar.visitStockControl();

		stockControl.validateIfContainsTheRowInTable(
				Arrays.asList(CONTRACTOR_GENERIC, MODEL_PART_2, "Good", SERVICE_PROVIDER_GENERIC_MATRIZ));
	}

	@Test(dependsOnMethods = { "removendoConsumosDeInstalacaoDePartesDeUmaOrdemDeServico" })
	public void removendoConsumosDeTrocaDePartesDeUmaOrdemDeServicoTirandoAParteRetiradaDoEstoqueDoClienteEExcluindoAParteRetiradaDoSistema() {

		// Acessando a Primeira Ordem de Serviço que foi Criada
		serviceOrderCrud = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idFirstServiceOrder);

		serviceOrderCrud.buttonConsumption();

		serviceOrderCrud.removeConsumption(Arrays.asList("Troca", MODEL_PART_1));

		serviceOrderCrud.buttonRemoved();

		serviceOrderCrud.validateIfRemovedPartsTableNotContains(Arrays.asList(MODEL_PART_3, "15487965"));
	}

	@Test(dependsOnMethods = {
			"removendoConsumosDeTrocaDePartesDeUmaOrdemDeServicoTirandoAParteRetiradaDoEstoqueDoClienteEExcluindoAParteRetiradaDoSistema" })
	public void removendoConsumosDeTrocaDePartesDeUmaOrdemDeServicoTirandoAParteRetiradaDoEstoqueDoClienteMasNaoExcluindoAParteRetiradaDoSistema() {

		// Visitando o Estoque
		stockControl = navBar.visitStockControl();

		// stockControl.validateNotContainsRowInTable(Arrays.asList(MODEL_PART_3,
		// "15487965"), "");

		stockControl.validateIfContainsTheRowInTable(
				Arrays.asList(CONTRACTOR_GENERIC, MODEL_PART_1, "Good", SERVICE_PROVIDER_GENERIC_MATRIZ));
	}

	@Test(dependsOnMethods = {
			"removendoConsumosDeTrocaDePartesDeUmaOrdemDeServicoTirandoAParteRetiradaDoEstoqueDoClienteMasNaoExcluindoAParteRetiradaDoSistema" })
	public void visualizandoAsPartesDisponiveisParaOConsumoEmUmaOrdemDeServicoDoEstoqueDeUmTecnicoDeUmPrestadorDeServicos() {

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderCrud = serviceOrderListPage.buttonNew();

		serviceOrderCrud.setDocument("CNPJ", CUSTOMER_DOCUMENT_NUMBER);
		serviceOrderCrud.setServiceGroup(SERVICE_GROUP_GENERIC);
		serviceOrderCrud.setService(SERVICE);
		serviceOrderCrud.setModel(MODEL_PRODUCT);
		serviceOrderCrud.setStatus(STATUS_GENERIC_NEW);
		serviceOrderCrud.buttonCreate();

		serviceOrderCrud.validateMessageSuccessCreated();

		serviceOrderCrud.setServiceProvider(SERVICE_PROVIDER_GENERIC_TECHNICIAN);
		serviceOrderCrud.buttonUpdate();

		serviceOrderCrud.validateMessageSuccessUpdate();

		serviceOrderCrud.setServiceProviderTechnician(TECNICO);
		serviceOrderCrud.buttonUpdate();

		serviceOrderCrud.validateMessageSuccessUpdate();

		serviceOrderCrud.buttonConsumption();
		consumptionModel = serviceOrderCrud.buttonAddConsumption();

		consumptionModel.goToTabParts();
		consumptionModel.validateThereAreRowInTablePart(Arrays.asList(MODEL_PART_1, "96467942"));
	}
}