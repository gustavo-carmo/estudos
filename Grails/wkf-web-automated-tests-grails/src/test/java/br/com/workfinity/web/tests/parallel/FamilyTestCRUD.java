package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.family.FamilyCrudFormPage;
import br.com.workfinity.web.page.family.FamilyListPage;
import br.com.workfinity.web.page.family.FamilyShowPage;
import br.com.workfinity.web.page.helper.Messages;
import careman.html.TestBase;

public class FamilyTestCRUD extends TestBase {

	private static final String FAMILY_NAME_CREATE = "FAMILY_CREATE - " + randomString(5);
	private static final String FAMILY_NAME_2 = "FAMILY_CREATE - " + randomString(5);
	private static final String FAMILY_NAME_UPDATE = "FAMILY_CREATE_UPDATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_FAMILY_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_FAMILY");

	private MainPage navBar;
	private FamilyCrudFormPage crudForm;
	private FamilyShowPage show;
	private FamilyListPage listPage;

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

		createFamily(FAMILY_NAME_CREATE, Messages.DISABLE.toString());
		createFamily(FAMILY_NAME_2, Messages.DISABLE.toString());
	}

	private void createFamily(String familyName, String status) {

		crudForm = navBar.visitFamily().buttonNew();

		crudForm.setName(familyName);
		crudForm.setStatus(status);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		listPage = show.buttonBackToSearch();
		listPage.setName(FAMILY_NAME_CREATE);
		listPage.buttonSearch();

		listPage.editItemTable(1);

		crudForm.setName(FAMILY_NAME_UPDATE);
		crudForm.setStatus(Messages.ENABLED.toString());

		show = crudForm.buttonUpdate();
		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {

		listPage = navBar.visitFamily();
		listPage.filterExpand();
		listPage.setName("FAMILY_CREATE");
		listPage.buttonSearch();

		listPage.validateTotalCount("2");
		listPage.filterExpand();
		listPage.setName(FAMILY_NAME_UPDATE);
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

		deleteFamily(FAMILY_NAME_UPDATE);
		deleteFamily(FAMILY_NAME_2);
	}

	private void deleteFamily(String familyName) {

		listPage.filterExpand();
		listPage.setName(familyName);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
