package wkf3.web.logistica.ordemDeRemessa;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderAddSerializedEquipment;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderCrudForm;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderListPage;
import br.com.workfinity.web.page.stock.StockControlListPage;
import careman.html.TestBase;

public class ShipmentOrderCanceledEquipmentSerializedTest extends TestBase {

	private static final String USER_SHIP = "USER_SHIP_PRODUCT_" + randomString(5);

	// Contratante e Prestador de Serviço
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";

	// Workflow e Status
	private static final String STATUS_SHIP_BASIC_NEW = "STATUS_SHIP_BASIC_NEW";
	private static final String STATUS_SHIP_BASIC_CANCELLED = "STATUS_SHIP_BASIC_CANCELLED";

	// Tipo de Remessa
	private static final String SHIP_TYPE_NEW_PRODUCT = "SHIP_TYPE_NEW_PRODUCT";

	// Equipamento
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";

	// Permissões do usuário
	private static final List<String> ROLES = Arrays.asList("ROLE_SHIPMENT_ORDER", "ROLE_SHIPMENT_ORDER_EDIT",
			"ROLE_SHIPMENT_ORDER_EQUIPMENTS", "ROLE_SHIPMENT_ORDER_PROMISE_OF_EQUIPMENT_VIEW",
			"ROLE_SHIPMENT_ORDER_PROMISE_OF_EQUIPMENT_MANAGER", "ROLE_SHIPMENT_ORDER_EDIT_INTEGRATION_STATUS",
			"ROLE_CREATE_DIVERGENCE_AT_SHIPMENT_ORDER", "ROLE_STOCK", "ROLE_STOCK_ALLOWED_TO_SEE_TEMPORARY_EQUIPMENTS",
			"ROLE_EQUIPMENT", "ROLE_EQUIPMENT_ADMIN", "ROLE_EQUIPMENT_ORIGIN", "ROLE_EQUIPMENT_SITUATION",
			"ROLE_EQUIPMENT_CONDITION", "ROLE_TYPE_EQUIPMENT_AVAILABILITY", "ROLE_EQUIPMENT_TYPE",
			"ROLE_SHIPMENT_ORDER_TYPE");

	private MainPage navBar;
	private String serialNumberProduct;
	private ShipmentOrderAddSerializedEquipment shipmentOrderAddEquipment;
	private ShipmentOrderListPage shipmentOrderListPage;
	private ShipmentOrderCrudForm shipmentOrderCrudForm;
	private String codeShipmentOrder;
	private StockControlListPage visitStockControl;

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
	public void backgroundCancelandoOrdemDeRemessaComEquipamentoTemporario() {

		createUserAndDoLogin(USER_SHIP, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "backgroundCancelandoOrdemDeRemessaComEquipamentoTemporario" })
	public void criandoOrdemDeRemessaComSucesso() {

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom(CONTRACTOR_GENERIC);
		shipmentOrderCrudForm.setTo(SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_TYPE_NEW_PRODUCT);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_BASIC_NEW);
		shipmentOrderCrudForm.buttonCreate();
		shipmentOrderCrudForm.validateMessageSuccessCreated();

		codeShipmentOrder = shipmentOrderCrudForm.getCode();
	}

	@Test(dependsOnMethods = { "criandoOrdemDeRemessaComSucesso" })
	public void sucessoAoAdicionarUmEquipamentoTemporarioNaOrdemDeRemessa() {

		shipmentOrderCrudForm.buttonAddEquipment();
		shipmentOrderAddEquipment = shipmentOrderCrudForm.buttonAddSerializedEquipByBarCode();

		// Gerando um número randômico
		serialNumberProduct = randomNumber(10);

		shipmentOrderAddEquipment.setModel(MODEL_GENERIC_PRODUCT);
		shipmentOrderAddEquipment.setSerialNumber(serialNumberProduct);

		shipmentOrderAddEquipment.validateMessageSuccess(
				"Sucesso: " + MODEL_GENERIC_PRODUCT + ", Número de Série: " + serialNumberProduct,
				".media-body .alert-title");

		shipmentOrderAddEquipment.validateIfContainsTheRowInTableByCssSelector(
				Arrays.asList(CONTRACTOR_GENERIC, MODEL_GENERIC_PRODUCT, serialNumberProduct),
				"#collapseOne .table.table-bordered.table-striped.table-condensed > tbody > tr",
				"Erro na validação da tabela de sucesso");
	}

	@Test(dependsOnMethods = { "sucessoAoAdicionarUmEquipamentoTemporarioNaOrdemDeRemessa" })
	public void verificandoSeOEquipamentoTemporarioEstaNoEstoque() {

		visitStockControl = navBar.visitStockControl();
		visitStockControl.setSerialNumber(serialNumberProduct);
		visitStockControl.setCheckBoxTemporary(true);
		visitStockControl.buttonSearch();

		visitStockControl.validateIfContainsTheRowInTable(Arrays.asList(CONTRACTOR_GENERIC, serialNumberProduct));
	}

	@Test(dependsOnMethods = { "verificandoSeOEquipamentoTemporarioEstaNoEstoque" })
	public void finalizandoAOrdemDeRemessa() {

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.setCode(codeShipmentOrder);
		shipmentOrderListPage.buttonSearch();

		shipmentOrderCrudForm = shipmentOrderListPage.editItemTable(1);

		shipmentOrderCrudForm.setStatus(STATUS_SHIP_BASIC_CANCELLED);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "finalizandoAOrdemDeRemessa" })
	public void verificandoSeOEquipamentoFoiExcluido() {

		visitStockControl = navBar.visitStockControl();
		visitStockControl.setSerialNumber(serialNumberProduct);
		visitStockControl.buttonSearch();

		visitStockControl.validateNotContainsRowInTable(
				Arrays.asList(CONTRACTOR_GENERIC, SERVICE_PROVIDER_GENERIC_MATRIZ, serialNumberProduct),
				"Encontrou a linha na tabela");
	}
}
