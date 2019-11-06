package careman.html.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.equipment.EquipmentCrudFormPage;
import br.com.workfinity.web.page.equipment.EquipmentShowPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.productionPlanning.ProductionPlanningListPage;
import br.com.workfinity.web.page.productionPlanning.ProductionPlanningModalGenerateRepairOrders;
import br.com.workfinity.web.page.repairOrder.RepairOrderCrudForm;
import br.com.workfinity.web.page.repairOrder.RepairOrderListPage;
import br.com.workfinity.web.page.repairOrder.RepairOrderModelBatchChange;
import careman.html.TestBase;

public class Laboratory extends TestBase {

	private StringBuffer verificationErrors = new StringBuffer();

	private MainPage navBar;
	private EquipmentCrudFormPage equipmentCrudFormPage;
	private EquipmentShowPage equipmentShowPage;

	private ProductionPlanningListPage productionPlanningListPage;

	private ProductionPlanningModalGenerateRepairOrders generateRepairOrders;

	private RepairOrderListPage repairOrderListPage;

	private RepairOrderCrudForm repairOrderCrudForm;

	private RepairOrderModelBatchChange repairOrderModelBatchChange;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
		getDriver().get(getBaseUrl());
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
	public void CreateEquipmentForLaboratory() throws Exception {
		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();

		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();

		equipmentShowPage = equipmentCrudFormPage.setContractor("POSNET").setManufacturer("Ingenico").setType(Messages.TYPE_PRODUCT.toString())
				.setServiceProvider("Vortex").setModel("I5100MMD012B").setSituation("Bad").setStatus(Messages.ENABLED.toString())
				.setSerialNumber(generateLengthNumber(6)).setPO(generateLengthNumber(6)).setSI(generateLengthNumber(6))
				.buttonCreate();

		equipmentShowPage.validateMessageSuccessCreated();
		equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();

		equipmentShowPage = equipmentCrudFormPage.setContractor("POSNET").setManufacturer("Ingenico").setType(Messages.TYPE_PRODUCT.toString())
				.setServiceProvider("Vortex").setModel("I5100MMD047A").setSituation("Bad").setStatus(Messages.ENABLED.toString())
				.setSerialNumber(generateLengthNumber(6)).setPO(generateLengthNumber(6)).setSI(generateLengthNumber(6))
				.buttonCreate();

		equipmentShowPage.validateMessageSuccessCreated();
		equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();

		equipmentShowPage = equipmentCrudFormPage.setContractor("CABAL").setManufacturer("Ingenico").setType(Messages.TYPE_PRODUCT.toString())
				.setServiceProvider("Vortex").setModel("I5100MMD012B").setSituation("Bad").setStatus(Messages.ENABLED.toString())
				.setSerialNumber(generateLengthNumber(6)).setPO(generateLengthNumber(6)).setSI(generateLengthNumber(6))
				.buttonCreate();

		equipmentShowPage.validateMessageSuccessCreated();
		equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();

		equipmentShowPage = equipmentCrudFormPage.setContractor("CABAL").setManufacturer("Ingenico").setType(Messages.TYPE_PRODUCT.toString())
				.setServiceProvider("Vortex").setModel("I5100MMD047A").setSituation("Bad").setStatus(Messages.ENABLED.toString())
				.setSerialNumber(generateLengthNumber(6)).setPO(generateLengthNumber(6)).setSI(generateLengthNumber(6))
				.buttonCreate();

		equipmentShowPage.validateMessageSuccessCreated();
		equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		logout(getDriver());
	}

	@Test(priority = 2)
	public void CreateRepairOrder() throws Exception {
		new LoginPage(getDriver(), "adm_lab", "123456").buttonSignInSuccess();

		productionPlanningListPage = navBar.visitProductionPlanning();
		productionPlanningListPage.setServiceProvider("Vortex").setContractor("POSNET").buttonSearch();

		generateRepairOrders = productionPlanningListPage.clickCheckBoxInTable(1).clickCheckBoxInTable(2)
				.buttonGenerateRepairOrders();

		productionPlanningListPage = generateRepairOrders.setServiceGroup("Reparacion en Laboratorio")
				.setService("Reparacion en Laboratorio").buttonGenerateRepairOrders();

		productionPlanningListPage.validateMessageSuccessCreated();
		repairOrderListPage = navBar.visitRepairOrder();

		repairOrderListPage.setStatus("RR Abierta").buttonSearch();

		repairOrderListPage.editItemTable(1);

		repairOrderCrudForm = new RepairOrderCrudForm(getDriver());
		repairOrderCrudForm.setTechnician("Técnico de Laboratório 1").setRepairLevel("A").buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		repairOrderCrudForm.setStatus("Asignada").buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		repairOrderListPage = repairOrderCrudForm.buttonBackToSearch();

		repairOrderListPage.editItemTable(1);

		repairOrderCrudForm.setTechnician("Técnico de Laboratório 1").buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		repairOrderCrudForm.setStatus("Asignada").buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		repairOrderListPage = repairOrderCrudForm.buttonBackToSearch();

		repairOrderListPage.filterExpand();
		repairOrderListPage.removeStatus(1).setStatus("Asignada").buttonSearch();

		productionPlanningListPage = navBar.visitProductionPlanning();

		productionPlanningListPage.setServiceProvider("Vortex").setContractor("CABAL").buttonSearch();

		generateRepairOrders = productionPlanningListPage.clickCheckBoxInTable(1).clickCheckBoxInTable(2)
				.buttonGenerateRepairOrders();

		productionPlanningListPage = generateRepairOrders.setServiceGroup("Reparacion en Laboratorio")
				.setService("Reparacion en Laboratorio").setTechnician("Técnico de Laboratório 2").buttonGenerateRepairOrders();

		productionPlanningListPage.validateMessageSuccessCreated();
		repairOrderListPage = navBar.visitRepairOrder();

		repairOrderListPage.filterExpand();
		repairOrderListPage.removeStatus(1).setStatus("RR Abierta").buttonSearch();

		repairOrderModelBatchChange = repairOrderListPage.clickCheckBoxInTable(1).buttonRepairOrdersBatchChange();

		repairOrderListPage = repairOrderModelBatchChange.setServiceGroup("Reparacion en Laboratorio")
				.setService("Reparacion en Laboratorio").setTechnician("Técnico de Laboratório 2").setStatus("Asignada")
				.buttonRepairOrderBatchChange();

		repairOrderListPage.validateMessageSuccessUpdate();
		repairOrderListPage.editItemTable(1);

		repairOrderCrudForm.setStatus("Asignada").buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		repairOrderListPage = repairOrderCrudForm.buttonBackToSearch();

		repairOrderListPage.filterExpand();
		repairOrderListPage.removeStatus(1).setStatus("Asignada").buttonSearch();

		logout(getDriver());
	}
}
