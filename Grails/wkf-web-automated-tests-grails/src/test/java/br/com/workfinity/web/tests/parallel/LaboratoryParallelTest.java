package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.productionPlanning.ProductionPlanningListPage;
import br.com.workfinity.web.page.productionPlanning.ProductionPlanningModalGenerateRepairOrders;
import br.com.workfinity.web.page.repairOrder.RepairOrderCrudForm;
import br.com.workfinity.web.page.repairOrder.RepairOrderListPage;
import br.com.workfinity.web.page.repairOrder.RepairOrderModelBatchChange;
import careman.html.TestBase;

public class LaboratoryParallelTest extends TestBase {

	private static final String USER_LAB = "USER_LAB_" + randomString(5);
	private static final String CONTRACTOR_1 = "CONTRACTOR_1_" + randomString(5);
	private static final String TECHNICIAN_1 = "TECHNICIAN_1_" + randomString(5);
	private static final String TECHNICIAN_2 = "TECHNICIAN_2_" + randomString(5);
	private static final String SERVICE_LAB = "SERVICE_LAB_" + randomString(5);
	
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String WORKFLOW_REPAIR_ORDER_GENERIC = "WORKFLOW_REPAIR_ORDER_GENERIC";
	private static final String STATUS_REPAIR_ORDER_GENERIC_NEW = "STATUS_REPAIR_ORDER_GENERIC_NEW";
	private static final String STATUS_REPAIR_ORDER_GENERIC_MEIO = "STATUS_REPAIR_ORDER_GENERIC_MEIO";
	private static final String SERVICE_GROUP_GENERIC_LAB = "SERVICE_GROUP_GENERIC_LAB";
	private static final String SERVICE_GENERIC_LAB = "SERVICE_GENERIC_LAB";
	private static final String SERVICE_PROVIDER_GENERIC_LAB = "SERVICE_PROVIDER_GENERIC_LAB";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String REPAIR_LEVEL_GENERIC = "RLG";
	
	private static final List<String> TECH_ROLES = Arrays.asList("ROLE_STOCK", "ROLE_DELIVER_PIECES", "ROLE_EQUIPMENT", "ROLE_REPAIR_ORDER",
			"ROLE_REPAIR_ORDER_EXPORT", "ROLE_REPAIR_ORDER_MASTER_REPORT", "ROLE_REQUEST_PIECES");
	
	private static final List<String> ROLES = Arrays.asList("ROLE_REPAIR_ORDER_CREATE", "ROLE_REPAIR_ORDER", 
			"ROLE_REPAIR_ORDER_CONSUMPTION_DELETE", "ROLE_REPAIR_ORDER_EDIT_INTEGRATION_STATUS", 
			"ROLE_REPAIR_ORDER_CHANGE_EQUIPMENT", "ROLE_REPAIR_ORDER_SEE_COST", "ROLE_REPAIR_ORDER_DELETE",
			"ROLE_REPAIR_ORDER_EXPORT", "ROLE_REPAIR_ORDER_MASTER_REPORT", "ROLE_PRODUCTION_PLANNING",
			"ROLE_REPAIR_ORDER_EDIT_NEW_SERIAL_NUMBER_FOR_EQUIPMENT", "ROLE_STOCK_PLANNING_MANAGEMENT",
			"ROLE_STOCK_PLANNING_VIEW", "ROLE_PROJECT", "ROLE_IMPORT_PROCESS", "ROLE_REPAIR_LEVEL",
			"ROLE_TEMPLATE", "ROLE_EQUIPMENT_ADMIN", "ROLE_DEFECT_LABORATORY", "ROLE_SOLUTION_LABORATORY",
			"ROLE_VIEW_OTHER_STOCK");

	private StringBuffer verificationErrors = new StringBuffer();

	private MainPage navBar;
	private ProductionPlanningListPage productionPlanningListPage;
	private ProductionPlanningModalGenerateRepairOrders generateRepairOrders;
	private RepairOrderListPage repairOrderListPage;
	private RepairOrderCrudForm repairOrderCrudForm;
	private RepairOrderModelBatchChange repairOrderModelBatchChange;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		
		super.setUp(baseURL, gridURL, true);
		
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

	@Test
	public void createEquipmentForLaboratory() throws Exception {
		
		LoadDataHelper.createContractor(getDriver(), getBaseUrl(), CONTRACTOR_1, "64413325000189");
		
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_LAB, CONTRACTOR_1, SERVICE_GROUP_GENERIC_LAB, WORKFLOW_REPAIR_ORDER_GENERIC, OPENING_HOURS_GROUP_GENERIC);
		
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECHNICIAN_1, "123456", TECH_ROLES, SERVICE_PROVIDER_GENERIC_LAB, "LABORATORY_TECHNICIAN");
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECHNICIAN_2, "123456", TECH_ROLES, SERVICE_PROVIDER_GENERIC_LAB, "LABORATORY_TECHNICIAN");

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "", SERVICE_PROVIDER_GENERIC_LAB,
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "BAD", generateLengthNumber(6), null, false);
		
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "", SERVICE_PROVIDER_GENERIC_LAB,
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "BAD", generateLengthNumber(6), null, false);
		
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_1, "", "", SERVICE_PROVIDER_GENERIC_LAB,
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "BAD", generateLengthNumber(6), null, false);
		
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_1, "", "", SERVICE_PROVIDER_GENERIC_LAB,
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "BAD", generateLengthNumber(6), null, false);

		createUserAndDoLogin(USER_LAB, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "createEquipmentForLaboratory" })
	public void createRepairOrder() throws Exception {

		productionPlanningListPage = navBar.visitProductionPlanning();
		productionPlanningListPage.setServiceProvider(SERVICE_PROVIDER_GENERIC_LAB);
		productionPlanningListPage.setContractor(CONTRACTOR_GENERIC);
		productionPlanningListPage.buttonSearch();

		productionPlanningListPage.clickCheckBoxInTable(1);
		productionPlanningListPage.clickCheckBoxInTable(2);
		generateRepairOrders = productionPlanningListPage.buttonGenerateRepairOrders();

		generateRepairOrders.setServiceGroup(SERVICE_GROUP_GENERIC_LAB);
		generateRepairOrders.setService(SERVICE_GENERIC_LAB);
		productionPlanningListPage = generateRepairOrders.buttonGenerateRepairOrders();

		productionPlanningListPage.validateMessageCreated();
		
		repairOrderListPage = navBar.visitRepairOrder();
		repairOrderListPage.setStatus(STATUS_REPAIR_ORDER_GENERIC_NEW);
		repairOrderListPage.buttonSearch();

		repairOrderCrudForm = repairOrderListPage.editItemTable(1);

		repairOrderCrudForm.setTechnician(TECHNICIAN_1);
		repairOrderCrudForm.setRepairLevel(REPAIR_LEVEL_GENERIC);
		repairOrderCrudForm.buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		
		repairOrderCrudForm.setStatus(STATUS_REPAIR_ORDER_GENERIC_MEIO);
		repairOrderCrudForm.buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		
		repairOrderListPage = repairOrderCrudForm.buttonBackToSearch();

		repairOrderListPage.editItemTable(1);

		repairOrderCrudForm.setTechnician(TECHNICIAN_1);
		repairOrderCrudForm.buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		
		repairOrderCrudForm.setStatus(STATUS_REPAIR_ORDER_GENERIC_MEIO);
		repairOrderCrudForm.buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		
		repairOrderListPage = repairOrderCrudForm.buttonBackToSearch();

		repairOrderListPage.filterExpand();
		repairOrderListPage.removeStatus(1);
		repairOrderListPage.setStatus(STATUS_REPAIR_ORDER_GENERIC_MEIO);
		repairOrderListPage.buttonSearch();

		productionPlanningListPage = navBar.visitProductionPlanning();

		productionPlanningListPage.setServiceProvider(SERVICE_PROVIDER_GENERIC_LAB);
		productionPlanningListPage.setContractor(CONTRACTOR_1);
		productionPlanningListPage.buttonSearch();

		productionPlanningListPage.clickCheckBoxInTable(1);
		productionPlanningListPage.clickCheckBoxInTable(2);
		generateRepairOrders = productionPlanningListPage.buttonGenerateRepairOrders();

		generateRepairOrders.setServiceGroup(SERVICE_GROUP_GENERIC_LAB);
		generateRepairOrders.setService(SERVICE_LAB);
		generateRepairOrders.setTechnician(TECHNICIAN_2);
		productionPlanningListPage = generateRepairOrders.buttonGenerateRepairOrders();

		productionPlanningListPage.validateMessageCreated();
		
		repairOrderListPage = navBar.visitRepairOrder();

		repairOrderListPage.filterExpand();
		repairOrderListPage.removeStatus(1);
		repairOrderListPage.setStatus(STATUS_REPAIR_ORDER_GENERIC_NEW);
		repairOrderListPage.buttonSearch();

		repairOrderListPage.clickCheckBoxInTable(1);
		repairOrderModelBatchChange = repairOrderListPage.buttonRepairOrdersBatchChange();

		repairOrderModelBatchChange.setServiceGroup(SERVICE_GROUP_GENERIC_LAB);
		repairOrderModelBatchChange.setService(SERVICE_LAB);
		repairOrderModelBatchChange.setTechnician(TECHNICIAN_2);
		repairOrderModelBatchChange.setStatus(STATUS_REPAIR_ORDER_GENERIC_MEIO);
		repairOrderListPage = repairOrderModelBatchChange.buttonRepairOrderBatchChange();

		repairOrderListPage.validateMessageSuccessUpdate();
		
		repairOrderListPage.editItemTable(1);

		repairOrderCrudForm.setStatus(STATUS_REPAIR_ORDER_GENERIC_MEIO);
		repairOrderCrudForm.buttonUpdate();

		repairOrderCrudForm.validateMessageSuccessUpdate();
		
		repairOrderListPage = repairOrderCrudForm.buttonBackToSearch();

		repairOrderListPage.filterExpand();
		repairOrderListPage.removeStatus(1);
		repairOrderListPage.setStatus(STATUS_REPAIR_ORDER_GENERIC_MEIO);
		repairOrderListPage.buttonSearch();
	}
}
