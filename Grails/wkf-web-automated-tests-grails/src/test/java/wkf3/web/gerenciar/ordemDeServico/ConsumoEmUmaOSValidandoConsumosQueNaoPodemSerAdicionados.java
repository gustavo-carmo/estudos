package wkf3.web.gerenciar.ordemDeServico;

import java.util.Arrays;
import java.util.List;

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

public class ConsumoEmUmaOSValidandoConsumosQueNaoPodemSerAdicionados extends TestBase {

	private static final String USERNAME = "CONSUMO_OS_" + randomString(5);
	private static final String TECNICO = "TECNICO_OS_" + randomString(5);
	private static final String SERVICE = "SERVICE_CONSUMO_VAL_" + randomString(5);

	private static final String MODEL_PRODUCT = "PRODUTO_MODELO";
	private static final String MODEL_PART_1 = "PRODUTO_PARTE_1";
	private static final String MODEL_PART_2 = "PRODUTO_PARTE_2";
	private static final String MODEL_PART_3 = "PRODUTO_PARTE_3";

	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String SERVICE_PROVIDER_GENERIC_TECHNICIAN = "SERVICE_PROVIDER_GENERIC_TECHNICIAN";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String WORKFLOW_GENERIC = "WORKFLOW_GENERIC";
	private static final String STATUS_GENERIC_NEW = "STATUS_GENERIC_NEW";
	private static final String STATUS_GENERIC_END = "STATUS_GENERIC_END";
	private static final String SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION = "SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION";
	private static final String SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION = "SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION";
	private static final String STATUS_SHIP_ORDER_GENERIC_NEW = "STATUS_SHIP_ORDER_GENERIC_NEW";
	private static final String STATUS_SHIP_ORDER_GENERIC_END = "STATUS_SHIP_ORDER_GENERIC_END";

	private static final List<String> ROLES_TECHNICIAN = Arrays.asList("ROLE_ATTACHMENT",
			"ROLE_CUSTOM_FIELD_RESTRICTED_VIEW_EDIT", "ROLE_SERVICE_ORDER", "ROLE_SERVICE_ORDER_HISTORY",
			"ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_SERVICE_ORDER_SERVICE_PROVIDER");

	private MainPage navBar;
	private ServiceOrderListPage serviceOrderListPage;
	private ServiceOrderCrudForm serviceOrderCrud;
	private ServiceOrderCrudFormConsumptionModel consumptionModel;
//	private String idEquipment1 = "95464987";
	private String idEquipment2 = "65321496";
	private String idEquipment3 = "54797987";
	private String idEquipment4 = "65764987";

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
	public void doLoadDataScripts() {

		// Criando os Modelos Específicos
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PART_1,
				"PART");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PART_2,
				"PART");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PART_3,
				"PART");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_PRODUCT,
				"PRODUCT", "", Arrays.asList(MODEL_PART_1, MODEL_PART_2, MODEL_PART_3), false);

		// Criando o Serviço Específico
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE, CONTRACTOR_GENERIC,
				SERVICE_GROUP_GENERIC, WORKFLOW_GENERIC, OPENING_HOURS_GROUP_GENERIC,
				Arrays.asList("service.allowsEquipmentWithOnlyModelOnServiceOrder = true"), null);

		// Criando o Técnico
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECNICO, "123456", ROLES_TECHNICIAN,
				SERVICE_PROVIDER_GENERIC_TECHNICIAN);

		// Criando os Equipamentos Por Scripts
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, MANUFACTORE_GENERIC, MODEL_PRODUCT, "GOOD", "49749795", null,
				false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, MANUFACTORE_GENERIC, MODEL_PART_1, "GOOD", "95464987", null,
				false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, MANUFACTORE_GENERIC, MODEL_PART_1, "GOOD", "65321496", null,
				false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, MANUFACTORE_GENERIC, MODEL_PART_2, "GOOD", "54797987", null,
				false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, MANUFACTORE_GENERIC, MODEL_PART_2, "GOOD", "10616659", null,
				false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, MANUFACTORE_GENERIC, MODEL_PART_3, "GOOD", "15648799", null,
				false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, MANUFACTORE_GENERIC, MODEL_PART_3, "GOOD", "65764987", null,
				false);

		createUserAndDoLogin(USERNAME, "123456", userRoles);
	}

	@Test(dependsOnMethods = { "doLoadDataScripts" })
	public void doLoadDataServiceOrder() {

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

		serviceOrderCrud.buttonConsumption();
		consumptionModel = serviceOrderCrud.buttonAddConsumption();

		consumptionModel.goToTabParts();
		consumptionModel.setPart(MODEL_PART_1);
		consumptionModel.setWithdrawnEquipment(MODEL_PART_1, "167496423");

		serviceOrderCrud = consumptionModel.selectItemTable(1);

		serviceOrderCrud.setStatus(STATUS_GENERIC_END);
		serviceOrderCrud.setClosedDate(getCurrentDate());
		serviceOrderCrud.buttonUpdate();

		serviceOrderCrud.validateMessageSuccessUpdate();

		// ADICIONANDO UM CONSUMO A UMA ORDEM DE SERVIÇO ABERTA
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

		serviceOrderCrud.buttonConsumption();
		consumptionModel = serviceOrderCrud.buttonAddConsumption();

		consumptionModel.goToTabParts();
		consumptionModel.setPart(MODEL_PART_1);
		consumptionModel.setWithdrawnEquipment(MODEL_PART_1, "6432164697");

		serviceOrderCrud = consumptionModel.selectItemTable(1);
	}

	@Test(dependsOnMethods = { "doLoadDataServiceOrder" })
	public void doLoadDataShipmentOrder() {

		// ADICIONANDO UM CONSUMO A UMA ORDEM DE REMESSA
		String ps = "Prestador de Serviço: ";

		ShipmentOrderListPage shipmentOrderList = navBar.visitShipmentOrder();
		ShipmentOrderCrudForm shipmentOrderCrud = shipmentOrderList.buttonNew();

		shipmentOrderCrud.setFrom(ps + SERVICE_PROVIDER_GENERIC_TECHNICIAN);
		shipmentOrderCrud.setTo(ps + SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrud.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrud.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrud.setInvoiceNumber(randomNumber(5));
		shipmentOrderCrud.buttonCreate();

		shipmentOrderCrud.validateMessageSuccessCreated();

		// pegando o código
		String codeShipmentOrder = shipmentOrderCrud.getCode();

		StockControlListPage stockControl = navBar.visitStockControl();
		stockControl.setModel(MODEL_PART_2);
		stockControl.buttonSearch();
		stockControl.clearAllFilter();

		stockControl.clickCheckUnCheckByArguments(MODEL_PART_2, idEquipment3);
		StockControlModelAddToShipmentOrder stockControlShipmentOrderModel = stockControl.buttonAddToShipmentOrder();

		stockControlShipmentOrderModel.setShipmentOrder(codeShipmentOrder + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN
				+ " -> " + SERVICE_PROVIDER_GENERIC_MATRIZ + " (Eq: 0, NEq: 0)");
		stockControlShipmentOrderModel.buttonAdd();

		stockControlShipmentOrderModel.validateMessageSuccess("Equipamentos: [" + MODEL_PART_2 + " - " + idEquipment3
				+ "] adicionados em " + codeShipmentOrder + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN + " -> "
				+ SERVICE_PROVIDER_GENERIC_MATRIZ + " (Eq: 1, NEq: 0)");

		stockControlShipmentOrderModel.buttonClose();

		// ADICIONANDO UM CONSUMO A UM TECNICO
		shipmentOrderList = navBar.visitShipmentOrder();
		shipmentOrderCrud = shipmentOrderList.buttonNew();

		shipmentOrderCrud.setFrom(ps + SERVICE_PROVIDER_GENERIC_TECHNICIAN);
		shipmentOrderCrud.setTo("Usuário: " + TECNICO);
		shipmentOrderCrud.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION);
		shipmentOrderCrud.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrud.setInvoiceNumber(randomNumber(5));
		shipmentOrderCrud.buttonCreate();

		shipmentOrderCrud.validateMessageSuccessCreated();

		// pegando o código
		String shipmentCode = shipmentOrderCrud.getCode();

		stockControl = navBar.visitStockControl();
		stockControl.setModel(MODEL_PART_3);
		stockControl.buttonSearch();
		stockControl.clearAllFilter();

		stockControl.clickCheckUnCheckByArguments(MODEL_PART_3, idEquipment4);
		stockControlShipmentOrderModel = stockControl.buttonAddToShipmentOrder();

		stockControlShipmentOrderModel.setShipmentOrder(
				shipmentCode + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN + " -> " + TECNICO + " (Eq: 0, NEq: 0)");
		stockControlShipmentOrderModel.buttonAdd();

		stockControlShipmentOrderModel.validateMessageSuccess(
				"Equipamentos: [" + MODEL_PART_3 + " - " + idEquipment4 + "] adicionados em " + shipmentCode + ": "
						+ SERVICE_PROVIDER_GENERIC_TECHNICIAN + " -> " + TECNICO + " (Eq: 1, NEq: 0)");

		stockControlShipmentOrderModel.buttonClose();

		// Voltando para Ordem de Remessa
		shipmentOrderList = navBar.visitShipmentOrder();

		shipmentOrderList.setCode(shipmentCode);
		shipmentOrderList.buttonSearch();

		shipmentOrderCrud = shipmentOrderList.editItemTable(1);

		shipmentOrderCrud.setStatus(STATUS_SHIP_ORDER_GENERIC_END);
		shipmentOrderCrud.buttonUpdate();

		shipmentOrderCrud.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "doLoadDataShipmentOrder" })
	public void adicionandoConsumosDeTrocaDePartesEmUmaOrdemDeServicoInformandoUmaParteRetiradaQueNaoPodeSerUtiliazada() {

		// Ordem de Serviço Três
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

		serviceOrderCrud.buttonConsumption();
		consumptionModel = serviceOrderCrud.buttonAddConsumption();

		// TODO - Verificar porque não esta dando erro.
//		consumptionModel.goToTabParts();
//		consumptionModel.setWithdrawnEquipment(MODEL_PART_1, idEquipment1);
//
//		consumptionModel.selectItemTableFail(1);
//
//		consumptionModel.validateMessageError(Arrays.asList("Consumo",
//				"Parte \"PRODUTO_PARTE_1 - 95464987\" não pode ser utilizado: Está sendo usado por: Cliente:"));

		consumptionModel.goToTabParts();
		consumptionModel.setWithdrawnEquipment(MODEL_PART_1, idEquipment2);

		consumptionModel.selectItemTableFail(1);

		consumptionModel.validateMessageError(Arrays.asList("Consumo",
				"Parte \"PRODUTO_PARTE_1 - 65321496\" não pode ser utilizado: Está sendo usado por: Ordem de Serviço:"));

		consumptionModel.goToTabParts();
		consumptionModel.setWithdrawnEquipment(MODEL_PART_2, idEquipment3);

		consumptionModel.selectItemTableFail(1);

		consumptionModel.validateMessageError(Arrays.asList("Consumo",
				"Parte \"PRODUTO_PARTE_2 - 54797987\" não pode ser utilizado: Está sendo usado por: Ordem de Remessa:",
				"Parte \"PRODUTO_PARTE_2 - 54797987\" não pode ser utilizado: Está sendo usado por: Prestador de Serviço:"));

		consumptionModel.goToTabParts();
		consumptionModel.setWithdrawnEquipment(MODEL_PART_3, "15648799");

		consumptionModel.selectItemTableFail(1);

		consumptionModel.validateMessageError(Arrays.asList("Consumo",
				"Parte \"PRODUTO_PARTE_3 - 15648799\" não pode ser utilizado: Está sendo usado por: Prestador de Serviço:"));

		consumptionModel.goToTabParts();
		consumptionModel.setWithdrawnEquipment(MODEL_PART_3, idEquipment4);

		consumptionModel.selectItemTableFail(1);

		consumptionModel.validateMessageError(Arrays.asList("Consumo",
				"Parte \"PRODUTO_PARTE_3 - 65764987\" não pode ser utilizado: Está sendo usado por: Técnico:"));
	}
}
