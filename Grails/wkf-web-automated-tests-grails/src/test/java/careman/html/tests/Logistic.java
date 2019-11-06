package careman.html.tests;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.equipment.EquipmentCrudFormPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderCrudForm;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderListPage;
import br.com.workfinity.web.page.stock.StockControlListPage;
import br.com.workfinity.web.page.stock.StockControlModelAddToShipmentOrder;
import careman.html.TestBase;

public class Logistic extends TestBase {

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

	private EquipmentCrudFormPage equipmentCrudFormPage;

	private String getCurrentDate(boolean setTime) {
		if (setTime)
			return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
		else
			return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	}

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
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

	@Test(priority = 1)
	public void doLogin() throws Exception {
		getDriver().get(getBaseUrl());
		new LoginPage(getDriver(), "lt_tefti", "123456").buttonSignInSuccess();
	}

	@Test(priority = 2)
	public void shipmentOrderBadNullToSPCancelado() throws Exception {
		shipmentOrderCrudForm = navBar.visitShipmentOrder().buttonNew();

		shipmentOrderCrudForm.setTo("Service Provider: Vortex").setShipmentOrderType("Null -> Service Provider (BAD)")
				.setStatus("Nova").buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		shipmentOrderCrudForm.setStatus("Preparação").buttonUpdate();

		shipmentOrderCrudForm.validateMessageError("Invoice Date is required! (Next Step Validation)").validateMessageError(
				"Invoice Number is required! (Next Step Validation)");

		shipmentOrderCrudForm.setInvoiceNumber("00001").setStatus("Preparação").buttonUpdate();

		shipmentOrderCrudForm.validateMessageError("Invoice Date is required! (Next Step Validation)")
				.setInvoiceDate(getCurrentDate(false)).setInvoiceNumber("").setStatus("Preparação").buttonUpdate();

		shipmentOrderCrudForm.validateMessageError("Invoice Number is required! (Next Step Validation)")
				.setInvoiceNumber("00001").setStatus("Preparação").buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();

		shipmentOrderCrudForm.setStatus("Cancelado").buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 3)
	public void shipmentOrderBadNullToSPDespachado() throws Exception {
		shipmentOrderCrudForm = navBar.visitShipmentOrder().buttonNew();

		shipmentOrderCrudForm.setTo("Service Provider: Vortex").setShipmentOrderType("Null -> Service Provider (BAD)")
				.setStatus("Nova").buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		shipmentOrderCrudForm.setStatus("Preparação").buttonUpdate();

		shipmentOrderCrudForm.validateMessageError("Invoice Number is required! (Next Step Validation)")
				.validateMessageError("Invoice Date is required! (Next Step Validation)").setInvoiceNumber("00002")
				.setInvoiceDate(getCurrentDate(false)).buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		shipmentOrderCrudForm.setStatus("Preparação").buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		shipmentOrderCrudForm.setStatus("Despachado").buttonUpdate();

		shipmentOrderCrudForm.validateMessageError("Carrier is required! (Next Step Validation)")
				.validateMessageError("Outbound Date is required! (Next Step Validation)").setEquipmentModel("ACQUA")
				.setEquipmentSerialNumber(generateLengthNumber(10)).setEquipmentContractor("BANRISUL").buttonAddEquipment();

		sn1 = shipmentOrderCrudForm.getSerialNumber();
		shipmentOrderCrudForm.setEquipmentModel("ST3232ECTR").setEquipmentQuantity("5").buttonAddEquipment();

		shipmentOrderCrudForm.setOutBoundDate(getCurrentDate(true)).setCarrier("Braspress").setVolumesNumber("1")
				.setStatus("Despachado").buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 4)
	public void shipmentOrderGoodNullToSPDespachado() throws Exception {
		shipmentOrderCrudForm = navBar.visitShipmentOrder().buttonNew();

		shipmentOrderCrudForm.setTo("Service Provider: Help Desk").setShipmentOrderType("Null -> Service Provider (GOOD)")
				.setStatus("Nova").buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		shipmentOrderCrudForm.setStatus("Preparação").setInvoiceNumber("00003").setInvoiceDate(getCurrentDate(false))
				.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		shipmentOrderCrudForm.setEquipmentModel("ACQUA").setEquipmentSerialNumber(sn1).buttonAddEquipment();

		shipmentOrderCrudForm.setEquipmentModel("ACQUA").setEquipmentSerialNumber(generateLengthNumber(10))
				.setEquipmentContractor("BANRISUL").buttonAddEquipment();

		sn2 = shipmentOrderCrudForm.getSerialNumber();
		shipmentOrderCrudForm.setEquipmentModel("BANJO").setEquipmentSerialNumber(generateLengthNumber(10))
				.setEquipmentContractor("BANRISUL").buttonAddEquipment();

		sn3 = shipmentOrderCrudForm.getSerialNumber();
		shipmentOrderCrudForm.setEquipmentModel("BANJO").setEquipmentSerialNumber(generateLengthNumber(10))
				.setEquipmentContractor("BANRISUL").buttonAddEquipment();

		String serialNumber = shipmentOrderCrudForm.getSerialNumber();
		shipmentOrderCrudForm.validateIfContainsTheRowInTable(Arrays.asList("BANJO", "BANRISUL", serialNumber));
		shipmentOrderCrudForm.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		shipmentOrderCrudForm.deleteItemTableByPosition("", 3, 1, "Equipment removed");

		shipmentOrderCrudForm.setCarrier("Braspress").setOutBoundDate(getCurrentDate(true)).setStatus("Despachado")
				.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 5)
	public void shipmentOrderGoodSPToContractorDespachado() throws Exception {

		shipmentOrderCrudForm = navBar.visitShipmentOrder().buttonNew();

		shipmentOrderCrudForm.setFrom("Service Provider: Vortex").setTo("Contractor: BANRISUL")
				.setShipmentOrderType("Service Provider -> Contratante (GOOD)").setStatus("Nova").buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		sc1 = shipmentOrderCrudForm.getCode();
		shipmentOrderCrudForm.setStatus("Preparação").setInvoiceNumber("00004").setInvoiceDate(getCurrentDate(false))
				.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		stockControlListPage = shipmentOrderCrudForm.buttonStockControl();
		stockControlListPage.setSerialNumber(sn1).buttonSearch();

		addToShipmentOrder = stockControlListPage.clickCheckUnCheckAll().buttonAddToShipmentOrder();

		stockControlListPage = addToShipmentOrder.setShipmentOrder(sc1.trim() + ": Vortex -> BANRISUL (Eq: 0, NEq: 0)")
				.buttonAdd().buttonClose();

		stockControlListPage.setSerialNumber("").buttonSearch();

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.setCode(sc1).buttonSearch();

		shipmentOrderListPage.editItemTable(1);

		shipmentOrderCrudForm.setCarrier("Braspress").setVolumesNumber("1").setStatus("Despachado")
				.setOutBoundDate(getCurrentDate(true)).buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 6)
	public void shipmentOrderSPToSPRecusado() throws Exception {
		shipmentOrderListPage = navBar.visitShipmentOrder();

		shipmentOrderListPage.filterExpand();
		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Service Provider: Help Desk").setTo("Service Provider: Vortex")
				.setShipmentOrderType("Service Provider -> Service Provider").setStatus("Nova").buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		cdRemessa01test05 = shipmentOrderCrudForm.getCode();
		shipmentOrderCrudForm.setStatus("Preparação").setInvoiceNumber("00005").setInvoiceDate(getCurrentDate(false))
				.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		stockControlListPage = shipmentOrderCrudForm.buttonStockControl();

		stockControlListPage.setSerialNumber(sn2).buttonSearch();
		addToShipmentOrder = stockControlListPage.clickCheckUnCheckAll().buttonAddToShipmentOrder();

		stockControlListPage = addToShipmentOrder
				.setShipmentOrder(cdRemessa01test05.trim() + ": Help Desk -> Vortex (Eq: 0, NEq: 0)").buttonAdd().buttonClose();

		stockControlListPage.setSerialNumber(sn3).buttonSearch();
		addToShipmentOrder = stockControlListPage.clickCheckUnCheckAll().buttonAddToShipmentOrder();

		stockControlListPage = addToShipmentOrder
				.setShipmentOrder(cdRemessa01test05.trim() + ": Help Desk -> Vortex (Eq: 1, NEq: 0)").buttonAdd().buttonClose();

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.filterExpand();
		shipmentOrderListPage.setCode(cdRemessa01test05).buttonSearch();

		shipmentOrderListPage.editItemTable(1);

		shipmentOrderCrudForm = new ShipmentOrderCrudForm(getDriver());
		shipmentOrderCrudForm.setCarrier("Braspress").setVolumesNumber("2").setOutBoundDate(getCurrentDate(true))
				.setStatus("Em Transito").buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		logout(getDriver());

		new LoginPage(getDriver(), "lt_vortex", "123456").buttonSignInSuccess();

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.setCode(cdRemessa01test05).buttonSearch();

		shipmentOrderListPage.editItemTable(1);
		shipmentOrderCrudForm = new ShipmentOrderCrudForm(getDriver());

		shipmentOrderCrudForm.setStatus("Recusado no Destino").buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 7)
	public void shipmentOrderSPToSPRecebido() throws Exception {
		logout(getDriver());

		new LoginPage(getDriver(), "lt_tefti", "123456").buttonSignInSuccess();
		shipmentOrderListPage = navBar.visitShipmentOrder();

		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Service Provider: Help Desk").setTo("Service Provider: Vortex")
				.setShipmentOrderType("Service Provider -> Service Provider").setStatus("Nova").buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		String sc3 = shipmentOrderCrudForm.getCode();
		shipmentOrderCrudForm.setStatus("Preparação").setInvoiceNumber("00006").setInvoiceDate(getCurrentDate(false))
				.buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		stockControlListPage = shipmentOrderCrudForm.buttonStockControl();

		stockControlListPage.setSerialNumber(sn2).buttonSearch();

		addToShipmentOrder = stockControlListPage.clickCheckUnCheckAll().buttonAddToShipmentOrder();

		stockControlListPage = addToShipmentOrder.setShipmentOrder(sc3.trim() + ": Help Desk -> Vortex (Eq: 0, NEq: 0)")
				.buttonAdd().buttonClose();

		stockControlListPage.setSerialNumber(sn3).buttonSearch();

		addToShipmentOrder = stockControlListPage.clickCheckUnCheckAll().buttonAddToShipmentOrder();
		stockControlListPage = addToShipmentOrder.setShipmentOrder(sc3.trim() + ": Help Desk -> Vortex (Eq: 1, NEq: 0)")
				.buttonAdd().buttonClose();

		stockControlListPage.setSerialNumber("").buttonSearch();

		shipmentOrderListPage = navBar.visitShipmentOrder();
		shipmentOrderListPage.setCode(sc3.trim()).buttonSearch();

		shipmentOrderListPage.editItemTable(1);
		shipmentOrderCrudForm = new ShipmentOrderCrudForm(getDriver());

		shipmentOrderCrudForm.setCarrier("Braspress").setStatus("Em Transito").setOutBoundDate(getCurrentDate(true))
				.setVolumesNumber("1").buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
		logout(getDriver());

		new LoginPage(getDriver(), "lt_vortex", "123456").buttonSignInSuccess();

		shipmentOrderListPage = navBar.visitShipmentOrder();

		shipmentOrderListPage.setCode(sc3.trim()).buttonSearch();
		shipmentOrderListPage.editItemTable(1);

		shipmentOrderCrudForm = new ShipmentOrderCrudForm(getDriver());
		shipmentOrderCrudForm.setStatus("Recebido OK PS").buttonUpdate();

		shipmentOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 8)
	public void testEquipmentOfShipmentShowOtherPlace() throws Exception {
		logout(getDriver());
		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();

		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();

		equipmentCrudFormPage.setSituation("Bad").setManufacturer("Ingenico").setModel("I5100MMD012B").setContractor("TICKET")
				.setServiceProvider("Vortex").setSerialNumber(generateLengthNumber(10));
		sn4 = equipmentCrudFormPage.getSerialNumber();
		equipmentCrudFormPage.buttonCreate();

		equipmentCrudFormPage.validateMessageSuccessCreated();

		shipmentOrderListPage = navBar.visitShipmentOrder();

		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Service Provider: Vortex").setTo("Contractor: TICKET")
				.setShipmentOrderType("Service Provider -> Contratante (BAD)").setStatus("Nova").buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		String sc4 = shipmentOrderCrudForm.getCode(); 
		
		stockControlListPage = shipmentOrderCrudForm.buttonStockControl();
		stockControlListPage.setSerialNumber(sn4).buttonSearch();

		addToShipmentOrder = stockControlListPage.clickCheckUnCheckAll().buttonAddToShipmentOrder();

		stockControlListPage = addToShipmentOrder.setShipmentOrder(sc4.trim() + ": Vortex -> TICKET (Eq: 0, NEq: 0)").buttonAdd()
				.buttonClose();

		stockControlListPage.setSerialNumber("").buttonSearch();
		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();

		equipmentCrudFormPage.setSituation("Bad").setManufacturer("Ingenico").setModel("I5100MMD012B").setContractor("CABAL")
				.setServiceProvider("Vortex").setSerialNumber(generateLengthNumber(10));
		String sn5 = equipmentCrudFormPage.getSerialNumber();
		equipmentCrudFormPage.buttonCreate();

		equipmentCrudFormPage.validateMessageSuccessCreated();
		shipmentOrderListPage = navBar.visitShipmentOrder();

		shipmentOrderCrudForm = shipmentOrderListPage.buttonNew();

		shipmentOrderCrudForm.setFrom("Service Provider: Vortex").setTo("Contractor: CABAL")
				.setShipmentOrderType("Service Provider -> Contratante (BAD)").setStatus("Nova").buttonCreate();

		shipmentOrderCrudForm.validateMessageSuccessCreated();
		String sc5 = shipmentOrderCrudForm.getCode();

		stockControlListPage = shipmentOrderCrudForm.buttonStockControl();
		stockControlListPage.setSerialNumber(sn5).buttonSearch();

		addToShipmentOrder = stockControlListPage.clickCheckUnCheckAll().buttonAddToShipmentOrder();

		stockControlListPage = addToShipmentOrder.setShipmentOrder(sc5.trim() + ": Vortex -> CABAL (Eq: 0, NEq: 0)").buttonAdd()
				.buttonClose();

		stockControlListPage.setSerialNumber("").buttonSearch();
	}
}
