package wkf3.web.logistica.ordemDeRemessa;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderAddQuantitativeEquipment;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderCrudForm;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderListPage;
import careman.html.TestBase;

public class ShipmentOrderRemoveNewQuantitativeEquipTest extends TestBase {

	private static final String USER_SHIP = "USER_SHIP_DELETE_EQUIP_" + randomString(5);

	// Contratante e Prestador de Serviço
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";

	// Prestador de serviço para os testes
	private static final String SHIP_SERVICE_PROVIDER = "SHIP_SERVICE_PROVIDER";

	// Workflow e Status
	private static final String STATUS_SHIP_BASIC_NEW = "STATUS_SHIP_BASIC_NEW";

	// Tipo de Remessa
	private static final String SHIP_TYPE_NEW_ACCESSORY = "SHIP_TYPE_NEW_ACCESSORY";

	// Equipamento
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String MODEL_SHIP_ACCESSORY = "MODEL_SHIP_ACCESSORY_DELL_" + randomString(5);

	// Permissões do usuário
	private static final List<String> ROLES = Arrays.asList("ROLE_SHIPMENT_ORDER", "ROLE_SHIPMENT_ORDER_EDIT",
			"ROLE_SHIPMENT_ORDER_EQUIPMENTS", "ROLE_SHIPMENT_ORDER_PROMISE_OF_EQUIPMENT_VIEW",
			"ROLE_SHIPMENT_ORDER_PROMISE_OF_EQUIPMENT_MANAGER", "ROLE_SHIPMENT_ORDER_EDIT_INTEGRATION_STATUS",
			"ROLE_CREATE_DIVERGENCE_AT_SHIPMENT_ORDER","ROLE_STOCK", "ROLE_STOCK_ALLOWED_TO_SEE_TEMPORARY_EQUIPMENTS",
			"ROLE_EQUIPMENT", "ROLE_EQUIPMENT_ADMIN", "ROLE_EQUIPMENT_ORIGIN", "ROLE_EQUIPMENT_SITUATION",
			"ROLE_EQUIPMENT_CONDITION", "ROLE_TYPE_EQUIPMENT_AVAILABILITY", "ROLE_EQUIPMENT_TYPE",
			"ROLE_SHIPMENT_ORDER_TYPE");

	private MainPage navBar;
	private ShipmentOrderListPage shipmentOrderListPage;
	private ShipmentOrderCrudForm shipmentOrderCrudForm;
	private ShipmentOrderAddQuantitativeEquipment addAccessoryComponentOrSupply;

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
	public void backgroundOrdemDeRemessaExcluindoUmEquipamentoQuantitativoNovo() {

		// Criando o Modelo
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_SHIP_ACCESSORY,
				"ACCESSORY");

		// Usuário
		createUserAndDoLogin(USER_SHIP, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "backgroundOrdemDeRemessaExcluindoUmEquipamentoQuantitativoNovo" })
	public void criandoOrdemDeRemessaComSucesso() {

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom(CONTRACTOR_GENERIC);
		shipmentOrderCrudForm.setTo(SHIP_SERVICE_PROVIDER);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_TYPE_NEW_ACCESSORY);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_BASIC_NEW);
		shipmentOrderCrudForm.buttonCreate();
		shipmentOrderCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "criandoOrdemDeRemessaComSucesso" })
	public void adicionandoUmEquipamentoNaOrdemDeRemessa() {

		shipmentOrderCrudForm.buttonAddEquipment();
		addAccessoryComponentOrSupply = shipmentOrderCrudForm.buttonAddQuantityEquip();
		
		addAccessoryComponentOrSupply.setModel(MODEL_SHIP_ACCESSORY);
		addAccessoryComponentOrSupply.setSituation("Good");
		addAccessoryComponentOrSupply.setQuantity("25");
		addAccessoryComponentOrSupply.buttonAddComponents();

		addAccessoryComponentOrSupply.validateIfContainsTheRowInTableByCssSelector(
				Arrays.asList(MODEL_SHIP_ACCESSORY, "25"),
				"#collapseOne .table.table-bordered.table-striped.table-condensed > tbody > tr",
				"Erro na validação da tabela de sucesso");
	}

	@Test(dependsOnMethods = { "adicionandoUmEquipamentoNaOrdemDeRemessa" })
	public void excluindoUmEquipamentoDaOrdemDeRemessa() {

		shipmentOrderCrudForm = addAccessoryComponentOrSupply.backToShipmentOrderCrud();

		shipmentOrderCrudForm.setAllEquipmentCheckBoxToDelete(true);
		shipmentOrderCrudForm.clickDeleteEquipments();
		
		shipmentOrderCrudForm.validateMessageSuccess("Equipamento(s) removido(s) (1).", ".media-body .alert-title");
	}
}
