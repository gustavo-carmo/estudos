package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.workflow.WorkflowCreateStepPage;
import br.com.workfinity.web.page.workflow.WorkflowCrudFormPage;
import br.com.workfinity.web.page.workflow.WorkflowListPage;
import br.com.workfinity.web.page.workflow.WorkflowShowPage;
import careman.html.TestBase;

public class WorkFlowTestCRUD extends TestBase {
	
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";

	private static final String PERMISSOES_VALIDAS = "PERMISSOES_VALIDAS_" + randomString(5);
	private static final String WORKFLOW_NOT_DELETE = "WORKFLOW_DELETE_" + randomString(5);
	private static final String WORKFLOW_UPDATE = "WORKFLOW_CREATE - " + randomString(5);
	private static final String WORKFLOW_CREATE = "WORKFLOW_CREATE - " + randomString(5);
	private static final String WORKFLOW_CREATE_2 = "WORKFLOW_CREATE - " + randomString(5);
	private static final String STATUS_FROM_WORKFLOW = "STATUS_FROM_WORKFLOW - " + randomString(5);
	private static final String STATUS_TO_WORKFLOW = "STATUS_TO_WORKFLOW - " + randomString(5);

	private static final String USERNAME = "USERNAME_WORK_FLOW_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_WORKFLOW", "ROLE_STEP");

	private MainPage navBar;
	private WorkflowCrudFormPage crudForm;
	private WorkflowShowPage show;
	private WorkflowListPage listPage;

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
	public void doLoadData() {

		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_FROM_WORKFLOW, "Start");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_TO_WORKFLOW, "End With Success");

		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_NOT_DELETE);
		
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), PERMISSOES_VALIDAS, CONTRACTOR_GENERIC,
				SERVICE_GROUP_GENERIC, WORKFLOW_NOT_DELETE, OPENING_HOURS_GROUP_GENERIC);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void login() throws Exception {

		createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void deveraExibeUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeCriarUmWorkflow() {

		listPage = navBar.visitWorkflow();
		crudForm = listPage.buttonNew();

		crudForm.buttonCreateFail();
		crudForm.validateErrorMessage("Campo \"Nome\" é requerido", "Campo \"Tipo de Workflow\" é requerido");
	}

	@Test(dependsOnMethods = { "deveraExibeUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeCriarUmWorkflow" })
	public void create() {

		createWorkflow(WORKFLOW_CREATE, Messages.ENABLED.toString());
		createWorkflow(WORKFLOW_CREATE_2, Messages.DISABLE.toString());
	}

	private void createWorkflow(String workflowName, String status) {

		crudForm = navBar.visitWorkflow().buttonNew();

		crudForm.setWorkflow(Messages.SERVICE_ORDER.toString());
		crudForm.setName(workflowName);
		crudForm.setStatus(status);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();

		if (status != Messages.DISABLE.toString()) {

			WorkflowCreateStepPage step = show.buttonAddStep();

			step.setStatus(Messages.ENABLED.toString());
			step.setFrom(STATUS_FROM_WORKFLOW);
			step.setTo(STATUS_FROM_WORKFLOW);
			step.setCheckBox("rule_VALIDATION_equipment_REQUIRED", true);
			step.buttonCreateFail();

			validateMessagesErrors("'DE' é igual a 'PARA'");

			step.setFrom(STATUS_TO_WORKFLOW);
			step.setTo(STATUS_FROM_WORKFLOW);
			step.buttonCreateFail();

			validateMessagesErrors("Pelo menos um passo 'de' deve ser do tipo 'INICIO' no Workflow");

			step.setFrom(STATUS_FROM_WORKFLOW);
			step.setTo(STATUS_TO_WORKFLOW);
			show = step.buttonCreateSucess();

			show.validateMessageSuccessCreated();
		}
	}
	
	@Test(dependsOnMethods = { "search" })
	public void edit() {
		
		listPage.filterExpand();
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		crudForm = listPage.editItemTable(listPage.returnOneRowInTableFindByText(Arrays.asList(WORKFLOW_CREATE_2)));

		crudForm.setName(WORKFLOW_UPDATE);
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();

		listPage = show.buttonBackToSearch();
	}

	@Test(dependsOnMethods = { "create" })
	public void search() {

		listPage = show.buttonBackToSearch();

		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ENABLED.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ALL.toString());
		listPage.setWorkflow(Messages.SERVICE_ORDER.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setWorkflow(Messages.REPAIR_ORDER.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setWorkflow(Messages.ALL.toString());
		listPage.buttonSearch();

		show = listPage.showItemTable(1);
		listPage = show.buttonBackToSearch();
	}


	@Test(dependsOnMethods = { "edit" })
	public void delete() {

		deleteWorkflow(Messages.ENABLED.toString(), Messages.SERVICE_ORDER.toString(), WORKFLOW_CREATE);
		deleteWorkflow(Messages.DISABLE.toString(), Messages.SERVICE_ORDER.toString(), WORKFLOW_UPDATE);
	}

	private void deleteWorkflow(String status, String type, String workflow) {

		listPage.filterExpand();
		listPage.setStatus(status);
		listPage.setWorkflow(type);
		listPage.buttonSearch();

		listPage.deleteItemTable(listPage.returnOneRowInTableFindByText(Arrays.asList(workflow)));
	}

	@Test(dependsOnMethods = { "delete" })
	public void naoDeveraExcluirORegistroQuandoClicarNoExcluirNoResultadoDaPesquisaPorqueEstaEmUso() {

		deleteWorkflow(Messages.ENABLED.toString(), Messages.SERVICE_ORDER.toString(), WORKFLOW_NOT_DELETE);

		listPage.assertTrue(listPage.returnOneRowInTableFindByText(Arrays.asList(WORKFLOW_NOT_DELETE)) > 0,
				"não foi encontrado um workflow que não pode ser excluido na tabela");
	}

	@Test(dependsOnMethods = { "naoDeveraExcluirORegistroQuandoClicarNoExcluirNoResultadoDaPesquisaPorqueEstaEmUso" })
	public void undoLoadData() {

		LoadDataHelper.deleteStatus(getDriver(), getBaseUrl(), STATUS_FROM_WORKFLOW);
		LoadDataHelper.deleteStatus(getDriver(), getBaseUrl(), STATUS_TO_WORKFLOW);
	}
}
