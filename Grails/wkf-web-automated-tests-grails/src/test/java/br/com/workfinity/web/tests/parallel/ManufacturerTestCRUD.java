package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.manufacturer.ManufacturerCrudFormPage;
import br.com.workfinity.web.page.manufacturer.ManufacturerListPage;
import br.com.workfinity.web.page.manufacturer.ManufacturerShowPage;
import careman.html.TestBase;

public class ManufacturerTestCRUD extends TestBase {

	private static final String MANUFACTURER_NAME_CREATE = "MANUFACTURER_NAME_CREATE - " + randomString(5);
	private static final String MANUFACTURER_NAME_CREATE_2 = "MANUFACTURER_NAME_CREATE - " + randomString(5);
	private static final String MANUFACTURER_NAME_UPDATE = "MANUFACTURER_NAME_CREATE_UPDATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_MANUFACTURER_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_MANUFACTURER");

	private MainPage navBar;
	private ManufacturerCrudFormPage crudForm;
	private ManufacturerShowPage show;
	private ManufacturerListPage listPage;

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

		createManufacturer(MANUFACTURER_NAME_CREATE);
		createManufacturer(MANUFACTURER_NAME_CREATE_2);
	}

	private void createManufacturer(String nameManufacturer) {

		crudForm = navBar.visitManufacturer().buttonNew();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setName(nameManufacturer);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		show.buttonEdited();

		crudForm.setName(MANUFACTURER_NAME_UPDATE);
		crudForm.setStatus(Messages.ENABLED.toString());
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {

		listPage = show.buttonBackToSearch();
		listPage.setName("MANUFACTURER_NAME_CREATE");
		listPage.buttonSearch();

		listPage.validateTotalCount("2");
		listPage.filterExpand();
		listPage.setName(MANUFACTURER_NAME_CREATE);
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

		deleteManufacturer(MANUFACTURER_NAME_CREATE);
		deleteManufacturer(MANUFACTURER_NAME_UPDATE);
	}

	private void deleteManufacturer(String nameManufacturer) {

		listPage.filterExpand();
		listPage.setName(nameManufacturer);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
