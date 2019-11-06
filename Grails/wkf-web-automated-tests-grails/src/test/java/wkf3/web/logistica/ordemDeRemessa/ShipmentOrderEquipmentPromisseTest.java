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
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderEquipmentPromisse;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderListPage;
import careman.html.TestBase;

public class ShipmentOrderEquipmentPromisseTest extends TestBase {

	private static final String USER_SHIP = "USER_SHIP_ACCESSORY_" + randomString(5);

	// Contratante e Prestador de Serviço
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";

	// Prestador de serviço para os testes
	private static final String SHIP_SERVICE_PROVIDER = "SHIP_SERVICE_PROVIDER";

	// Workflow e Status
	private static final String STATUS_SHIP_BASIC_NEW = "STATUS_SHIP_BASIC_NEW";

	// Tipo de Remessa
	private static final String SHIP_TYPE_EQUIP_PROMISSE = "SHIP_TYPE_EQUIP_PROMISSE";

	// Tipo do Equipamento
	private static final String SHIP_EQUIP_TYPE = "SHIP_EQUIP_TYPE";

	// Equipamento
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String MODEL_SHIP_ACCESSORY_EQUIP_TYPE = "MODEL_SHIP_ACCESSORY_EQUIP_TYPE_" + randomString(5);
	private static final String MODEL_SHIP_ACCESSORY = "SHIP_ACCESSORY_SIMPLE_" + randomString(5);

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
	public void backgroundOrdemDeRemessaPromessaDeEquipamento() {

		// Criando o Modelo com Tipo de Equipamento
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC,
				MODEL_SHIP_ACCESSORY_EQUIP_TYPE, "ACCESSORY", SHIP_EQUIP_TYPE, null, false);

		// Criando o Equipamento do Tipo Acessório com Tipo de Equipamento
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_SHIP_ACCESSORY_EQUIP_TYPE, "GOOD", "", 50,
				false);
		
		// Criando o Modelo sem Tipo de Equipamento
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_SHIP_ACCESSORY,
				"ACCESSORY", "", null, false);

		// Criando o Equipamento do Tipo Acessório sem Tipo de Equipamento
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_SHIP_ACCESSORY, "GOOD", "", 50, false);

		// Usuário
		createUserAndDoLogin(USER_SHIP, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "backgroundOrdemDeRemessaPromessaDeEquipamento" })
	public void criandoOrdemDeRemessaComSucesso() {

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom(SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setTo(SHIP_SERVICE_PROVIDER);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_TYPE_EQUIP_PROMISSE);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_BASIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "criandoOrdemDeRemessaComSucesso" })
	public void falhaAoAdicionarUmEquipamentoSemConfigurarPromessaDaRemessa() {

		// Adicionando equipamento na Ordem de Remessa
		shipmentOrderCrudForm.buttonAddEquipment();
		addAccessoryComponentOrSupply = shipmentOrderCrudForm.buttonAddQuantityEquip();

		addAccessoryComponentOrSupply.setModel(MODEL_SHIP_ACCESSORY);
		addAccessoryComponentOrSupply.setContractor(CONTRACTOR_GENERIC);
		addAccessoryComponentOrSupply.setSituation("Good");
		addAccessoryComponentOrSupply.setQuantity("25");

		addAccessoryComponentOrSupply.buttonAddComponents();

		addAccessoryComponentOrSupply.validateMessageError(
				"O Tipo de Equipamento [[50] " + MODEL_SHIP_ACCESSORY + " (" + CONTRACTOR_GENERIC
						+ ") - null] não é permitido",
				"Não é permitido adicionar equipamento, quando a Promessa de Equipamento não estiver configurada");
	}

	@Test(dependsOnMethods = { "falhaAoAdicionarUmEquipamentoSemConfigurarPromessaDaRemessa" })
	public void adicionandoAPromessaNaOrdemDeRemessa() throws InterruptedException {

		// Voltando para a Edição da Ordem de Remessa
		addAccessoryComponentOrSupply.backToShipmentOrderCrud();

		// Adicionando promessa de equipamento
		ShipmentOrderEquipmentPromisse equipmentPromisseAba = shipmentOrderCrudForm.equipmentPromisseAba();
		equipmentPromisseAba.buttonAddPromisse();

		Thread.sleep(10000);
		
		equipmentPromisseAba.setQuantity("25");
//		equipmentPromisseAba.setEquipmentType(SHIP_EQUIP_TYPE);
		equipmentPromisseAba.setSelectFirstEquipmentType();
		equipmentPromisseAba.buttonSave();

		equipmentPromisseAba.validateMessageSuccess("Sucesso",
				".el-notification__group.is-with-icon .el-notification__title");

		shipmentOrderCrudForm = equipmentPromisseAba.backToShipmentOrder();
	}

	@Test(dependsOnMethods = { "adicionandoAPromessaNaOrdemDeRemessa" })
	public void falhaAoAdicionarUmEquipamentoSemTipoDeEquipamentoNaRemessa() {

		// Adicionando equipamento na Ordem de Remessa
		shipmentOrderCrudForm.buttonAddEquipment();
		addAccessoryComponentOrSupply = shipmentOrderCrudForm.buttonAddQuantityEquip();

		addAccessoryComponentOrSupply.setModel(MODEL_SHIP_ACCESSORY);
		addAccessoryComponentOrSupply.setContractor(CONTRACTOR_GENERIC);
		addAccessoryComponentOrSupply.setSituation("Good");
		addAccessoryComponentOrSupply.setQuantity("25");
		addAccessoryComponentOrSupply.buttonAddComponents();

		addAccessoryComponentOrSupply.validateMessageError("O Tipo de Equipamento [[50] " + MODEL_SHIP_ACCESSORY + " ("
				+ CONTRACTOR_GENERIC + ") - null] não é permitido");
	}

	@Test(dependsOnMethods = { "falhaAoAdicionarUmEquipamentoSemTipoDeEquipamentoNaRemessa" })
	public void adicionandoEquipamentoComSucessoNaOrdemDeRemessa() {

		// Adicionando equipamento na Ordem de Remessa
		shipmentOrderCrudForm.buttonAddEquipment();
		addAccessoryComponentOrSupply = shipmentOrderCrudForm.buttonAddQuantityEquip();

		addAccessoryComponentOrSupply.setModel(MODEL_SHIP_ACCESSORY_EQUIP_TYPE);
		addAccessoryComponentOrSupply.setContractor(CONTRACTOR_GENERIC);
		addAccessoryComponentOrSupply.setSituation("Good");
		addAccessoryComponentOrSupply.setQuantity("25");
		addAccessoryComponentOrSupply.buttonAddComponents();

		addAccessoryComponentOrSupply.validateIfContainsTheRowInTableByCssSelector(
				Arrays.asList(MODEL_SHIP_ACCESSORY_EQUIP_TYPE, "25"),
				"#collapseOne .table.table-bordered.table-striped.table-condensed > tbody > tr",
				"Erro na validação da tabela de sucesso");
	}
}
