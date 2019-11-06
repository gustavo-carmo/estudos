package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.serviceGroup.ServiceGroupCrudFormPage;
import br.com.workfinity.web.page.serviceGroup.ServiceGroupListPage;
import br.com.workfinity.web.page.serviceGroup.ServiceGroupShowPage;
import careman.html.TestBase;

public class ServiceGroupTestCRUD extends TestBase {

	private static final String SERVICE_GROUP_CREATE = "SERVICE_GROUP_CREATE - " + randomString(5);
	private static final String SERVICE_GROUP_CREATE_2 = "SERVICE_GROUP_CREATE - " + randomString(5);
	private static final String SERVICE_GROUP_UPDATE = "SERVICE_GROUP_CREATE_UPDATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_SERVICE_GROUP_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_GROUP");

	private MainPage navBar;
	private ServiceGroupCrudFormPage crudForm;
	private ServiceGroupShowPage show;
	private ServiceGroupListPage listPage;

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

		createServiceGroup(SERVICE_GROUP_CREATE);
		createServiceGroup(SERVICE_GROUP_CREATE_2);
	}

	private void createServiceGroup(String nameServiceGroup) {

		crudForm = navBar.visitServiceGroup().buttonNew();

		crudForm.setType(Messages.LABORATORY.toString());
		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setName(nameServiceGroup);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		crudForm = show.buttonEdit();

		crudForm.setName(SERVICE_GROUP_UPDATE);
		crudForm.setType(Messages.FIELD.toString());
		crudForm.setStatus(Messages.ENABLED.toString());
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {

		listPage = show.buttonBackToSearch();
		listPage.setName("SERVICE_GROUP_CREATE");
		listPage.buttonSearch();

		listPage.validateTotalCount("2");
		listPage.filterExpand();
		listPage.setName(SERVICE_GROUP_CREATE);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setName("");
		listPage.setType(Messages.LABORATORY.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setType(Messages.ALL.toString());
		listPage.setStatus(Messages.ENABLED.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ALL.toString());
		listPage.buttonSearch();
	}

	@Test(dependsOnMethods = { "search" })
	public void delete() {

		deleteServiceGroup(SERVICE_GROUP_CREATE);
		deleteServiceGroup(SERVICE_GROUP_UPDATE);
	}

	private void deleteServiceGroup(String nameServiceGroup) {

		listPage.filterExpand();
		listPage.setName(nameServiceGroup);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
