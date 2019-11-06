package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.solution.SolutionCrudFormPage;
import br.com.workfinity.web.page.solution.SolutionListPage;
import br.com.workfinity.web.page.solution.SolutionShowPage;
import careman.html.TestBase;

public class SolutionTestCRUD extends TestBase {

	private static final String SOLUTION_NAME_CREATE = "SOLUTION_CREATE - " + randomString(5);
	private static final String SOLUTION_NAME_CREATE_2 = "SOLUTION_CREATE - " + randomString(5);
	private static final String SOLUTION_NAME_UPDATE = "SOLUTION_CREATE_UPDATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_SOLUTION_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_SOLUTION");

	private MainPage navBar;
	private SolutionCrudFormPage crudForm;
	private SolutionShowPage show;
	private SolutionListPage listPage;

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

		createSolution(SOLUTION_NAME_CREATE);
		createSolution(SOLUTION_NAME_CREATE_2);
	}

	private void createSolution(String nameSolution) {

		crudForm = navBar.visitSolution().buttonNew();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setName(nameSolution);
		crudForm.checkBoxRCE(true);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		crudForm = show.buttonEdit();

		crudForm.setName(SOLUTION_NAME_UPDATE);
		crudForm.setStatus(Messages.ENABLED.toString());
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {

		listPage = show.buttonBackToSearch();
		listPage.setName("SOLUTION_CREATE");
		listPage.buttonSearch();
		listPage.validateTotalCount("2");

		listPage.filterExpand();
		listPage.setName(SOLUTION_NAME_CREATE);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setName("");
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ENABLED.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ALL.toString());
		listPage.buttonSearch();
	}

	@Test(dependsOnMethods = { "search" })
	public void delete() {

		deleteSolution(SOLUTION_NAME_CREATE);
		deleteSolution(SOLUTION_NAME_UPDATE);
	}

	private void deleteSolution(String nameSolution) {

		listPage.filterExpand();
		listPage.setName(nameSolution);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
