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
import br.com.workfinity.web.page.stock.StockControlListPage;
import careman.html.TestBase;

public class ShipmentOrderCheckDestinyExistingSerializedEquipWithContractorTest extends TestBase {

	private static final String USER_SHIP = "USER_SHIP_PRODUCT_" + randomString(5);

	// Contratante e Prestador de Serviço
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";

	// Prestador de serviço para os testes
	private static final String SHIP_SERVICE_PROVIDER = "SHIP_SERVICE_PROVIDER";

	// Workflow e Status
	private static final String STATUS_SHIP_BASIC_NEW = "STATUS_SHIP_BASIC_NEW";
	private static final String STATUS_SHIP_BASIC_END = "STATUS_SHIP_BASIC_END";

	// Tipo de Remessa
	private static final String SHIP_TYPE_EXIST_PRODUCT = "SHIP_TYPE_EXIST_PRODUCT";

	// Equipamento
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";

	// Permissões do usuário
	private static final List<String> ROLES = Arrays.asList("ROLE_SHIPMENT_ORDER", "ROLE_SHIPMENT_ORDER_EDIT",
			"ROLE_SHIPMENT_ORDER_EQUIPMENTS", "ROLE_SHIPMENT_ORDER_PROMISE_OF_EQUIPMENT_VIEW",
			"ROLE_SHIPMENT_ORDER_PROMISE_OF_EQUIPMENT_MANAGER", "ROLE_SHIPMENT_ORDER_EDIT_INTEGRATION_STATUS",
			"ROLE_CREATE_DIVERGENCE_AT_SHIPMENT_ORDER","ROLE_STOCK", "ROLE_STOCK_ALLOWED_TO_SEE_TEMPORARY_EQUIPMENTS",
			"ROLE_EQUIPMENT", "ROLE_EQUIPMENT_ADMIN", "ROLE_EQUIPMENT_ORIGIN", "ROLE_EQUIPMENT_SITUATION",
			"ROLE_EQUIPMENT_CONDITION", "ROLE_TYPE_EQUIPMENT_AVAILABILITY", "ROLE_EQUIPMENT_TYPE",
			"ROLE_SHIPMENT_ORDER_TYPE");

	private MainPage navBar;
	private String serialNumberProduct;
	private ShipmentOrderAddSerializedEquipment shipmentOrderAddEquipment;
	private ShipmentOrderListPage shipmentOrderListPage;
	private ShipmentOrderCrudForm shipmentOrderCrudForm;

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
	public void backgroundOrdemDeRemessaEquipamentoSerializado() {

		serialNumberProduct = generateLengthNumber(10);

		// Criando o Equipamento do Tipo Produto
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD",
				serialNumberProduct, null, false);

		createUserAndDoLogin(USER_SHIP, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "backgroundOrdemDeRemessaEquipamentoSerializado" })
	public void criandoOrdemDeRemessaComSucesso() {

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom(SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setTo(SHIP_SERVICE_PROVIDER);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_TYPE_EXIST_PRODUCT);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_BASIC_NEW);
		shipmentOrderCrudForm.buttonCreate();
		shipmentOrderCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "criandoOrdemDeRemessaComSucesso" })
	public void sucessoAoAdicionarUmEquipamentoSerializadoNaOrdemDeRemessa() {

		shipmentOrderCrudForm.buttonAddEquipment();
		shipmentOrderAddEquipment = shipmentOrderCrudForm.buttonAddSerializedEquipByBarCode();
		
		shipmentOrderAddEquipment.setModel(MODEL_GENERIC_PRODUCT);
		shipmentOrderAddEquipment.setContractor(CONTRACTOR_GENERIC);
		shipmentOrderAddEquipment.setSerialNumber(serialNumberProduct);

		shipmentOrderAddEquipment.validateMessageSuccess(
				"Sucesso: " + MODEL_GENERIC_PRODUCT + ", Número de Série: " + serialNumberProduct,
				".media-body .alert-title");

		shipmentOrderAddEquipment.validateIfContainsTheRowInTableByCssSelector(
				Arrays.asList(CONTRACTOR_GENERIC, MODEL_GENERIC_PRODUCT, serialNumberProduct),
				"#collapseOne .table.table-bordered.table-striped.table-condensed > tbody > tr",
				"Erro na validação da tabela de sucesso");
	}

	@Test(dependsOnMethods = { "sucessoAoAdicionarUmEquipamentoSerializadoNaOrdemDeRemessa" })
	public void finalizandoAOrdemDeRemessa() {

		shipmentOrderCrudForm = shipmentOrderAddEquipment.backToShipmentOrderCrud();
		
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_BASIC_END);
		shipmentOrderCrudForm.buttonUpdate();
		
		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "finalizandoAOrdemDeRemessa" })
	public void verificandoSeOEquipamentoSerializadoFoiParaODestino() {

		StockControlListPage stockControlListPage = navBar.visitStockControl();
		
		stockControlListPage.setModelType("Produto");
		stockControlListPage.setSerialNumber(serialNumberProduct);
		stockControlListPage.buttonSearch();
		
		stockControlListPage.validateIfContainsTheRowInTable(
				Arrays.asList(CONTRACTOR_GENERIC, SHIP_SERVICE_PROVIDER, serialNumberProduct));
	}
}
