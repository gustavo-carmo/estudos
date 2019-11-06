package wkf3.web.logistica.ordemDeRemessa;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderAddSerializedEquipment;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderCrudForm;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderListPage;
import careman.html.TestBase;

public class ShipmentOrderEquipmentConditionTest extends TestBase {

	private static final String USER_SHIP = "USER_SHIP_EQUIP_CONDITION_" + randomString(5);

	// Contratante e Prestador de Serviço
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";

	// Workflow e Status
	private static final String STATUS_SHIP_BASIC_NEW = "STATUS_SHIP_BASIC_NEW";

	// Tipo da Remessa
	private static final String SHIP_TYPE_EQUIP_CONDITION = "SHIP_TYPE_EQUIP_CONDITION";

	// Equipamento
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";;

	// Status do Equipamento
	private static final String EQUIP_CONDITION = "EQUIP_CONDITION";

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
	private String serialNumberWithoutEquipCondition;
	private String serialNumberWithEquipCondition;
	private ShipmentOrderAddSerializedEquipment shipmentOrderAddEquipment;

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
	public void backgroundOrdemDeRemessaStatusDoEquipamento() {

		serialNumberWithoutEquipCondition = randomNumber(10);
		serialNumberWithEquipCondition = randomNumber(10);

		// Criando o Equipamento do Tipo Acessório
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "", "", MANUFACTORE_GENERIC,
				MODEL_GENERIC_PRODUCT, "GOOD", serialNumberWithoutEquipCondition, null,false, "", "", "", "");

		// Criando o Equipamento do Tipo Acessório
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "", "", MANUFACTORE_GENERIC,
				MODEL_GENERIC_PRODUCT, "GOOD", serialNumberWithEquipCondition, null, false,"", "", "", EQUIP_CONDITION);

		createUserAndDoLogin(USER_SHIP, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "backgroundOrdemDeRemessaStatusDoEquipamento" })
	public void criandoOrdemDeRemessaComSucesso() {

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom(CONTRACTOR_GENERIC);
		shipmentOrderCrudForm.setTo(SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_TYPE_EQUIP_CONDITION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_BASIC_NEW);
		shipmentOrderCrudForm.buttonCreate();
		shipmentOrderCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "criandoOrdemDeRemessaComSucesso" })
	public void falhaAoTentarAdicionarUmEquipamentoComStatusDoEquipamentoDiferenteDoSelecionadoNaRemessa() {

		// Adicionando equipamento na Ordem de Remessa
		shipmentOrderCrudForm.buttonAddEquipment();
		shipmentOrderAddEquipment = shipmentOrderCrudForm.buttonAddSerializedEquipByBarCode();

		shipmentOrderAddEquipment.setModel(MODEL_GENERIC_PRODUCT);
		shipmentOrderAddEquipment.setSerialNumber(serialNumberWithoutEquipCondition);

		shipmentOrderAddEquipment.validateMessageError("O Status do Equipamento [" + MODEL_GENERIC_PRODUCT + " - "
				+ serialNumberWithoutEquipCondition + "] não é permitido");

		shipmentOrderAddEquipment.validateIfContainsTheRowInTableByCssSelector(
				Arrays.asList(CONTRACTOR_GENERIC, MODEL_GENERIC_PRODUCT, serialNumberWithoutEquipCondition,
						"O Status do Equipamento [" + MODEL_GENERIC_PRODUCT + " - " + serialNumberWithoutEquipCondition
								+ "] não é permitido"),
				"#collapseThree .table.table-bordered.table-striped.table-condensed > tbody > tr",
				"Erro na validação da tabela de erro");
	}

	@Test(dependsOnMethods = { "falhaAoTentarAdicionarUmEquipamentoComStatusDoEquipamentoDiferenteDoSelecionadoNaRemessa" })
	public void sucessoAoAdicionarUmEquipamentoComStatusDoEquipamentoIgualAoSelecionadoNaRemessa() {

		shipmentOrderAddEquipment.setSerialNumber(serialNumberWithEquipCondition);

		shipmentOrderAddEquipment.validateMessageSuccess(
				"Sucesso: " + MODEL_GENERIC_PRODUCT + ", Número de Série: " + serialNumberWithEquipCondition,
				".media-body .alert-title");

		shipmentOrderAddEquipment.validateIfContainsTheRowInTableByCssSelector(
				Arrays.asList(CONTRACTOR_GENERIC, MODEL_GENERIC_PRODUCT, serialNumberWithEquipCondition),
				"#collapseOne .table.table-bordered.table-striped.table-condensed > tbody > tr",
				"Erro na validação da tabela de sucesso");
	}
}
