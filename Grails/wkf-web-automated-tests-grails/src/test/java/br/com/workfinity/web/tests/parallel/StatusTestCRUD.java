package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.status.StatusCrudFormPage;
import br.com.workfinity.web.page.status.StatusListPage;
import br.com.workfinity.web.page.status.StatusShowPage;
import careman.html.TestBase;

public class StatusTestCRUD extends TestBase {

	private static final String STATUS_NAME = "STATUS_NAME - " + randomString(5);
	private static final String STATUS_NAME_2 = "STATUS_NAME - " + randomString(5);
	private static final String STATUS_NAME_3 = "STATUS_NAME - " + randomString(5);
	private static final String STATUS_NAME_4 = "STATUS_NAME - " + randomString(5);

	private static final String USERNAME = "USERNAME_STATUS_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_STEP_STATUS");

	private MainPage navBar;
	private StatusListPage listPage;
	private StatusCrudFormPage crudForm;
	private StatusShowPage show;

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
	public void login() throws Exception {

		createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void create() {

		listPage = navBar.visitStatus();

		createStatus(STATUS_NAME, Messages.SERVICE_ORDER.toString(), Messages.START.toString());
		listPage = show.buttonBackToSearch();

		createStatus(STATUS_NAME_2, Messages.SERVICE_ORDER.toString(), Messages.END_WITH_SUCCESS.toString());
		listPage = show.buttonBackToSearch();

		createStatus(STATUS_NAME_3, Messages.SERVICE_ORDER.toString(), Messages.END_WITH_FAIL.toString());
		listPage = show.buttonBackToSearch();

		createStatus(STATUS_NAME_4, Messages.REPAIR_ORDER.toString(), Messages.END_WITH_FAIL.toString());
	}

	private void createStatus(String name, String workflow, String typeStatus) {

		crudForm = listPage.buttonNew();

		crudForm.setName(name);
		crudForm.setWorkFlow(workflow);
		crudForm.setType(typeStatus);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		crudForm = show.buttonEdit();

		crudForm.setType("");
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {

		listPage = show.buttonBackToSearch();
		listPage.setName(STATUS_NAME);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setName("");
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ALL.toString());
		listPage.setWorkflowType(Messages.SERVICE_ORDER.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setWorkflowType(Messages.REPAIR_ORDER.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setWorkflowType(Messages.ALL.toString());
		listPage.buttonSearch();
	}

	@Test(dependsOnMethods = { "search" })
	public void delete() {

		deleteStatus(STATUS_NAME);
		deleteStatus(STATUS_NAME_2);
		deleteStatus(STATUS_NAME_3);
		deleteStatus(STATUS_NAME_4);
	}

	private void deleteStatus(String nameStatus) {

		listPage.filterExpand();
		listPage.setName(nameStatus);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.deleteItemTable(1);
	}
}
