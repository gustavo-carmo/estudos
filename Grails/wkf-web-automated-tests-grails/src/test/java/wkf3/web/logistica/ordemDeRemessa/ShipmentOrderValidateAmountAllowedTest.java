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

public class ShipmentOrderValidateAmountAllowedTest extends TestBase {

	private static final String USER_SHIP = "USER_SHIP_ACCESSORY_" + randomString(5);

	// Contratante e Prestador de Serviço
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";

	// Prestador de serviço para os testes
	private static final String SHIP_SERVICE_PROVIDER = "SHIP_SERVICE_PROVIDER";

	// Workflow e Status
	private static final String STATUS_SHIP_BASIC_NEW = "STATUS_SHIP_BASIC_NEW";

	// Tipo de Remessa
	private static final String SHIP_TYPE_EXIST_ACCESSORY = "SHIP_TYPE_EXIST_ACCESSORY";

	// Equipamento
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String MODEL_SHIP_ACCESSORY = "MODEL_SHIP_ACCESSORY_QTD_" + randomString(5);

	// Permissões do usuário
	private static final List<String> ROLES = Arrays.asList("ROLE_SHIPMENT_ORDER", "ROLE_SHIPMENT_ORDER_EDIT",
			"ROLE_SHIPMENT_ORDER_EQUIPMENTS", "ROLE_SHIPMENT_ORDER_PROMISE_OF_EQUIPMENT_VIEW",
			"ROLE_SHIPMENT_ORDER_PROMISE_OF_EQUIPMENT_MANAGER", "ROLE_SHIPMENT_ORDER_EDIT_INTEGRATION_STATUS",
			"ROLE_CREATE_DIVERGENCE_AT_SHIPMENT_ORDER", "ROLE_STOCK", "ROLE_STOCK_ALLOWED_TO_SEE_TEMPORARY_EQUIPMENTS",
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
	public void backgroundOrdemDeRemessaQuantidadeDeEquipamentoQuantitativoPermitido() {

		// Criando o Modelo
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_SHIP_ACCESSORY,
				"ACCESSORY");

		// Criando o Equipamento do Tipo Acessório
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "", SHIP_SERVICE_PROVIDER,
				MANUFACTORE_GENERIC, MODEL_SHIP_ACCESSORY, "GOOD", "", 300, false);

		createUserAndDoLogin(USER_SHIP, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "backgroundOrdemDeRemessaQuantidadeDeEquipamentoQuantitativoPermitido" })
	public void criandoOrdemDeRemessaComSucesso() {

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom(SHIP_SERVICE_PROVIDER);
		shipmentOrderCrudForm.setTo(SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_TYPE_EXIST_ACCESSORY);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_BASIC_NEW);
		shipmentOrderCrudForm.buttonCreate();
		shipmentOrderCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "criandoOrdemDeRemessaComSucesso" })
	public void falhaAoAdicionarUmaQuantidadeMaiorDoEquipamentoQuantitativoNaOrdemDeRemessa() {

		// Adicionando equipamento na Ordem de Remessa
		shipmentOrderCrudForm.buttonAddEquipment();
		addAccessoryComponentOrSupply = shipmentOrderCrudForm.buttonAddQuantityEquip();

		addAccessoryComponentOrSupply.setModel(MODEL_SHIP_ACCESSORY);
		addAccessoryComponentOrSupply.setContractor(CONTRACTOR_GENERIC);
		addAccessoryComponentOrSupply.setSituation("Good");
		addAccessoryComponentOrSupply.setQuantity("500");
		addAccessoryComponentOrSupply.buttonAddComponents();

		addAccessoryComponentOrSupply.validateMessageError("Equipamento: [300] " + MODEL_SHIP_ACCESSORY + " ("
				+ CONTRACTOR_GENERIC + "), quantidade informada de 500 é maior que a disponível.");

		addAccessoryComponentOrSupply.validateIfContainsTheRowInTableByCssSelector(
				Arrays.asList(MODEL_SHIP_ACCESSORY,
						"Equipamento: [300] " + MODEL_SHIP_ACCESSORY + " ("
								+ CONTRACTOR_GENERIC + "), quantidade informada de 500 é maior que a disponível."),
				"#collapseThree .table.table-bordered.table-striped.table-condensed > tbody > tr",
				"Erro na validação da tabela de erro");
	}
}
