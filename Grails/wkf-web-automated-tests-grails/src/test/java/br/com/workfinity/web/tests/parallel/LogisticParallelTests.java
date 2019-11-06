package br.com.workfinity.web.tests.parallel;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderCrudForm;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderListPage;
import br.com.workfinity.web.page.stock.StockControlListPage;
import br.com.workfinity.web.page.stock.StockControlModelAddToShipmentOrder;
import careman.html.TestBase;

public class LogisticParallelTests extends TestBase {

	private static final String USER_LOGISTIC = "USER_LOGISTIC_" + randomString(5);
	private static final String MODEL_GENERIC_PRODUCT_1 = "MODEL_GENERIC_PRODUCT_1_" + randomString(5);
	private static final String CONTRACTOR_1 = "CONTRACTOR_1_" + randomString(5);
	private static final String CONTRACTOR_2 = "CONTRACTOR_2_" + randomString(5);
	
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String SERVICE_PROVIDER_GENERIC_TECHNICIAN = "SERVICE_PROVIDER_GENERIC_TECHNICIAN";
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String MODEL_GENERIC_ACCESSORY = "MODEL_GENERIC_ACCESSORY";
	private static final String SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION = "SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION";
	private static final String STATUS_SHIP_ORDER_GENERIC_NEW = "STATUS_SHIP_ORDER_GENERIC_NEW";
	private static final String STATUS_SHIP_ORDER_GENERIC_MEIO_BAD = "STATUS_SHIP_ORDER_GENERIC_MEIO_BAD";
	private static final String STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD = "STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD";
	private static final String STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT = "STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT";
	private static final String STATUS_SHIP_ORDER_GENERIC_CANCELLED = "STATUS_SHIP_ORDER_GENERIC_CANCELLED";
	private static final String STATUS_SHIP_ORDER_GENERIC_END = "STATUS_SHIP_ORDER_GENERIC_END";
	private static final String CARRIER_GENERIC = "CARRIER_GENERIC";
	
	private StringBuffer verificationErrors = new StringBuffer();

	public String sn1;
	public String sn2;
	public String sn3;
	public String sn4;
	public String sn5;
	public String sc1;
	public String sc2;
	public String cdRemessa01test05;
	public String Or01;

	private MainPage navBar;
	private ShipmentOrderCrudForm shipmentOrderCrudForm;
	private StockControlListPage stockControlListPage;
	private StockControlModelAddToShipmentOrder addToShipmentOrder;
	private ShipmentOrderListPage shipmentOrderListPage;

	private String getCurrentDate(boolean setTime) {
		if (setTime)
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
		else
			return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, false);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	@Test
	public void doLoadDataScripts() {
		
		// Criando um Modelo
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_GENERIC_PRODUCT_1, "PRODUCT");
		
		// Criando dois Contratantes
		LoadDataHelper.createContractor(getDriver(), getBaseUrl(), CONTRACTOR_1, "35354538000189");
		LoadDataHelper.createContractor(getDriver(), getBaseUrl(), CONTRACTOR_2, "12635772000189");

		sn4 = generateLengthNumber(10);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "", "", SERVICE_PROVIDER_GENERIC_MATRIZ,
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "BAD", sn4, null, false);

		sn5 = generateLengthNumber(10);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "", "", SERVICE_PROVIDER_GENERIC_MATRIZ,
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "BAD", sn5, null, false);
	}

	@Test(dependsOnMethods = { "doLoadDataScripts" })
	public void doLogin() throws Exception {
		
		createUserAndDoLogin(USER_LOGISTIC, "123456", userRoles);
	}

	@Test(dependsOnMethods = { "doLogin" })
	public void shipmentOrderBadNullToSPCancelado() throws Exception {

		// Criando Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setTo("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();

		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_BAD);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateGenericMessageError("Data da Nota Fiscal é obrigatório ! (Validação do próximo passo)",
				"Número da Nota Fiscal é obrigatório ! (Validação do próximo passo)");

		shipmentOrderCrudForm.setInvoiceNumber("00001");
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_BAD);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateGenericMessageError("Data da Nota Fiscal é obrigatório ! (Validação do próximo passo)");
		
		shipmentOrderCrudForm.setInvoiceDate(getCurrentDate(false));
		shipmentOrderCrudForm.setInvoiceNumber("");
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_BAD);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm
				.validateGenericMessageError("Número da Nota Fiscal é obrigatório ! (Validação do próximo passo)");
		
		shipmentOrderCrudForm.setInvoiceNumber("00001");
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_BAD);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();

		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_CANCELLED);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "shipmentOrderBadNullToSPCancelado" })
	public void shipmentOrderBadNullToSPDespachado() throws Exception {

		// Criando Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setTo("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_BAD);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateGenericMessageError("Número da Nota Fiscal é obrigatório ! (Validação do próximo passo)",
				"Data da Nota Fiscal é obrigatório ! (Validação do próximo passo)");
		
		shipmentOrderCrudForm.setInvoiceNumber("00002");
		shipmentOrderCrudForm.setInvoiceDate(getCurrentDate(false));
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_BAD);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_END);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateGenericMessageError("Transportadora é obrigatório ! (Validação do próximo passo)",
				"Data de Saída é obrigatório ! (Validação do próximo passo)");

		// Gerando o Número de Série
		sn1 = generateLengthNumber(10);
		
		// TODO - Não há o elemento que ele tenta achar;
		// Tem que clicar no botão "adicionar equipamentos"
		// e nele há algumas opções para inserir acessório/componente
		// mas só acessei essa tela pelo QA
		// TODO - Verificar como fazer
		shipmentOrderCrudForm.setEquipmentModel(MODEL_GENERIC_PRODUCT);
		shipmentOrderCrudForm.setEquipmentSerialNumber(sn1);
		shipmentOrderCrudForm.setEquipmentContractor(CONTRACTOR_GENERIC);
		shipmentOrderCrudForm.buttonAddEquipment();

		shipmentOrderCrudForm.setEquipmentModel(MODEL_GENERIC_ACCESSORY);
		shipmentOrderCrudForm.setEquipmentQuantity("5");
		shipmentOrderCrudForm.buttonAddEquipment();

		shipmentOrderCrudForm.setOutBoundDate(getCurrentDate(true));
		shipmentOrderCrudForm.setCarrier(CARRIER_GENERIC);
		shipmentOrderCrudForm.setVolumesNumber("1");
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_END);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "shipmentOrderBadNullToSPDespachado" })
	public void shipmentOrderGoodNullToSPDespachado() throws Exception {

		// Criando Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setTo("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_TECHNICIAN);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();

		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD);
		shipmentOrderCrudForm.setInvoiceNumber("00003");
		shipmentOrderCrudForm.setInvoiceDate(getCurrentDate(false));
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();

		shipmentOrderCrudForm.setEquipmentModel(MODEL_GENERIC_PRODUCT);
		shipmentOrderCrudForm.setEquipmentSerialNumber(sn1);
		shipmentOrderCrudForm.buttonAddEquipment();
		
		// Gerando o Número de Série
		sn2 = generateLengthNumber(10);
		
		shipmentOrderCrudForm.setEquipmentModel(MODEL_GENERIC_PRODUCT);
		shipmentOrderCrudForm.setEquipmentSerialNumber(sn2);
		shipmentOrderCrudForm.setEquipmentContractor(CONTRACTOR_GENERIC);
		shipmentOrderCrudForm.buttonAddEquipment();
		
		// Gerando o Número de Série
		sn3 = generateLengthNumber(10);

		shipmentOrderCrudForm.setEquipmentModel(MODEL_GENERIC_PRODUCT_1);
		shipmentOrderCrudForm.setEquipmentSerialNumber(sn3);
		shipmentOrderCrudForm.setEquipmentContractor(CONTRACTOR_GENERIC);
		shipmentOrderCrudForm.buttonAddEquipment();

		// Gerando o Número de Série
		String serialNumber = generateLengthNumber(10);
		
		shipmentOrderCrudForm.setEquipmentModel(MODEL_GENERIC_PRODUCT_1);
		shipmentOrderCrudForm.setEquipmentSerialNumber(serialNumber);
		shipmentOrderCrudForm.setEquipmentContractor(CONTRACTOR_GENERIC);
		shipmentOrderCrudForm.buttonAddEquipment();

		shipmentOrderCrudForm
				.validateIfContainsTheRowInTable(Arrays.asList(MODEL_GENERIC_PRODUCT_1, CONTRACTOR_GENERIC, serialNumber));

		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();

		shipmentOrderCrudForm.deleteItemTableByPosition("", 3, 1, "Equipamento removido");

		shipmentOrderCrudForm.setCarrier(CARRIER_GENERIC);
		shipmentOrderCrudForm.setOutBoundDate(getCurrentDate(true));
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_END);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "shipmentOrderGoodNullToSPDespachado" })
	public void shipmentOrderGoodSPToContractorDespachado() throws Exception {

		// Criando Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setTo("Contratante: " + CONTRACTOR_GENERIC);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();

		sc1 = shipmentOrderCrudForm.getCode();

		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD);
		shipmentOrderCrudForm.setInvoiceNumber("00004");
		shipmentOrderCrudForm.setInvoiceDate(getCurrentDate(false));
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();

		// Estoque
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(sn1);
		stockControlListPage.buttonSearch();

		stockControlListPage.clickCheckUnCheckAll();
		addToShipmentOrder = stockControlListPage.buttonAddToShipmentOrder();

		addToShipmentOrder.setShipmentOrder(
				sc1.trim() + ": " + SERVICE_PROVIDER_GENERIC_MATRIZ + " -> " + CONTRACTOR_GENERIC + " (Eq: 0, NEq: 0)");
		addToShipmentOrder.buttonAdd();
		
		stockControlListPage = addToShipmentOrder.buttonClose();

		stockControlListPage.setSerialNumber("");
		stockControlListPage.buttonSearch();

		// Voltando para a Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.setCode(sc1);
		shipmentOrderListPage.buttonSearch();

		shipmentOrderListPage.editItemTable(1);

		shipmentOrderCrudForm.setCarrier(CARRIER_GENERIC);
		shipmentOrderCrudForm.setVolumesNumber("1");
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_END);
		shipmentOrderCrudForm.setOutBoundDate(getCurrentDate(true));
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "shipmentOrderGoodSPToContractorDespachado" })
	public void shipmentOrderSPToSPRecusado() throws Exception {

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.filterExpand();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_TECHNICIAN);
		shipmentOrderCrudForm.setTo("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();

		cdRemessa01test05 = shipmentOrderCrudForm.getCode();

		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD);
		shipmentOrderCrudForm.setInvoiceNumber("00005");
		shipmentOrderCrudForm.setInvoiceDate(getCurrentDate(false));
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();

		// Estoque
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(sn2);
		stockControlListPage.buttonSearch();

		stockControlListPage.clickCheckUnCheckAll();
		addToShipmentOrder = stockControlListPage.buttonAddToShipmentOrder();

		addToShipmentOrder.setShipmentOrder(cdRemessa01test05.trim() + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN
				+ " -> " + SERVICE_PROVIDER_GENERIC_MATRIZ + " (Eq: 0, NEq: 0)");
		addToShipmentOrder.buttonAdd();
		
		stockControlListPage = addToShipmentOrder.buttonClose();

		stockControlListPage.setSerialNumber(sn3);
		stockControlListPage.buttonSearch();

		stockControlListPage.clickCheckUnCheckAll();
		addToShipmentOrder = stockControlListPage.buttonAddToShipmentOrder();

		addToShipmentOrder.setShipmentOrder(cdRemessa01test05.trim() + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN
				+ " -> " + SERVICE_PROVIDER_GENERIC_MATRIZ + " (Eq: 1, NEq: 0)");
		addToShipmentOrder.buttonAdd();
		
		stockControlListPage = addToShipmentOrder.buttonClose();

		// Voltando para Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.filterExpand();
		shipmentOrderListPage.setCode(cdRemessa01test05);
		shipmentOrderListPage.buttonSearch();

		shipmentOrderListPage.editItemTable(1);

		shipmentOrderCrudForm.setCarrier(CARRIER_GENERIC);
		shipmentOrderCrudForm.setVolumesNumber("2");
		shipmentOrderCrudForm.setOutBoundDate(getCurrentDate(true));
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();

		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_CANCELLED);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "shipmentOrderSPToSPRecusado" })
	public void shipmentOrderSPToSPRecebido() throws Exception {

		// Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.filterExpand();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_TECHNICIAN);
		shipmentOrderCrudForm.setTo("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();

		String sc3 = shipmentOrderCrudForm.getCode();

		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD);
		shipmentOrderCrudForm.setInvoiceNumber("00006");
		shipmentOrderCrudForm.setInvoiceDate(getCurrentDate(false));
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();

		// Estoque
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(sn2);
		stockControlListPage.buttonSearch();

		stockControlListPage.clickCheckUnCheckAll();

		addToShipmentOrder = stockControlListPage.buttonAddToShipmentOrder();

		addToShipmentOrder.setShipmentOrder(sc3.trim() + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN + " -> "
				+ SERVICE_PROVIDER_GENERIC_MATRIZ + " (Eq: 0, NEq: 0)");
		addToShipmentOrder.buttonAdd();
		
		stockControlListPage = addToShipmentOrder.buttonClose();

		stockControlListPage.setSerialNumber(sn3);
		stockControlListPage.buttonSearch();

		stockControlListPage.clickCheckUnCheckAll();

		addToShipmentOrder = stockControlListPage.buttonAddToShipmentOrder();

		addToShipmentOrder.setShipmentOrder(sc3.trim() + ": " + SERVICE_PROVIDER_GENERIC_TECHNICIAN + " -> "
				+ SERVICE_PROVIDER_GENERIC_MATRIZ + " (Eq: 1, NEq: 0)");
		addToShipmentOrder.buttonAdd();
		
		stockControlListPage = addToShipmentOrder.buttonClose();

		stockControlListPage.setSerialNumber("");
		stockControlListPage.buttonSearch();

		// Voltando para a Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.filterExpand();
		shipmentOrderListPage.setCode(sc3.trim());
		shipmentOrderListPage.buttonSearch();

		shipmentOrderListPage.editItemTable(1);

		shipmentOrderCrudForm.setCarrier(CARRIER_GENERIC);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT);
		shipmentOrderCrudForm.setOutBoundDate(getCurrentDate(true));
		shipmentOrderCrudForm.setVolumesNumber("1");
		shipmentOrderCrudForm.buttonUpdate();
		
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_END);
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "shipmentOrderSPToSPRecebido" })
	public void testEquipmentOfShipmentShowOtherPlace() throws Exception {

		// Criando uma Nova Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.filterExpand();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setTo("Contratante: " + CONTRACTOR_1);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();

		String sc4 = shipmentOrderCrudForm.getCode();

		// Estoque
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(sn4);
		stockControlListPage.buttonSearch();

		stockControlListPage.clickCheckUnCheckAll();
		addToShipmentOrder = stockControlListPage.buttonAddToShipmentOrder();

		addToShipmentOrder
				.setShipmentOrder(sc4.trim() + ": " + SERVICE_PROVIDER_GENERIC_MATRIZ + " -> " + CONTRACTOR_1 + " (Eq: 0, NEq: 0)");
		addToShipmentOrder.buttonAdd();
		
		stockControlListPage = addToShipmentOrder.buttonClose();

		stockControlListPage.setSerialNumber("");
		stockControlListPage.buttonSearch();

		// Criando uma Nova Ordem de Remessa
		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.filterExpand();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Prestador de Serviço: " + SERVICE_PROVIDER_GENERIC_MATRIZ);
		shipmentOrderCrudForm.setTo("Contratante: " + CONTRACTOR_2);
		shipmentOrderCrudForm.setShipmentOrderType(SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION);
		shipmentOrderCrudForm.setStatus(STATUS_SHIP_ORDER_GENERIC_NEW);
		shipmentOrderCrudForm.buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();

		String sc5 = shipmentOrderCrudForm.getCode();

		// Estoque
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(sn5);
		stockControlListPage.buttonSearch();

		stockControlListPage.clickCheckUnCheckAll();
		addToShipmentOrder = stockControlListPage.buttonAddToShipmentOrder();

		addToShipmentOrder
				.setShipmentOrder(sc5.trim() + ": " + SERVICE_PROVIDER_GENERIC_MATRIZ + " -> " + CONTRACTOR_2 + " (Eq: 0, NEq: 0)");
		addToShipmentOrder.buttonAdd();
		
		stockControlListPage = addToShipmentOrder.buttonClose();

		stockControlListPage.setSerialNumber("");
		stockControlListPage.buttonSearch();
	}
}
