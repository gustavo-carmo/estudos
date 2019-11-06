package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.workflow.WorkflowCreateStepPage;
import br.com.workfinity.web.page.workflow.WorkflowCrudFormPage;
import br.com.workfinity.web.page.workflow.WorkflowShowPage;
import careman.html.TestBase;

public class WorkflowTest extends TestBase {

	private MainPage navBar;
	private WorkflowCrudFormPage crudForm;
	private WorkflowShowPage show;
	private WorkflowCreateStepPage step;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void login() throws Exception {

		getDriver().get(getBaseUrl());

		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();
	}

	@Test(dependsOnMethods = { "login" })
	public void create() {
		crudForm = navBar.visitWorkflow().buttonNew();

		crudForm.setWorkflow(Messages.SERVICE_ORDER.toString());
		crudForm.setName("CRUD Workflow - New");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
		crudForm = show.buttonEdit();

		show = crudForm.buttonBackToDetail();
		step = show.buttonAddStep();
		step.setStatus(Messages.ENABLED.toString());
		step.setFrom("Status Nova Crud");
		step.setTo("Status Nova Crud");
		step.setCheckBox("rule_VALIDATION_equipment_REQUIRED", true);
		step.buttonCreateFail();

		validateMessagesErrors("'From' is equal 'To'");
		step.setFrom("Status Encaminhado CRUD");
		step.setTo("Status Finalizado CRUD");
		step.buttonCreateFail();

		validateMessagesErrors("At least one 'from' step must be of type 'START' on Workflow");
		step.setFrom("Status Nova Crud");
		show = step.buttonCreateSucess();

		show.validateMessageSuccessCreated();
	}
	@Test(dependsOnMethods = { "create" })
	public void edit() {
		crudForm = show.buttonEdit();
		
		crudForm.setName("CRUD Workflow - Edit");
		show = crudForm.buttonUpdate();
		
		show.validateMessageSuccessUpdate();
	}
}
