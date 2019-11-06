package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.openingHoursGroup.OpeningHoursGroupCrudFormPage;
import br.com.workfinity.web.page.openingHoursGroup.OpeningHoursGroupListPage;
import br.com.workfinity.web.page.openingHoursGroup.OpeningHoursGroupShowPage;
import careman.html.TestBase;

public class OpeningHoursGroupTestCRUD extends TestBase {

	private static final String OHG_NAME_CREATE = "OHG_CREATE - " + randomString(5);
	private static final String OHG_NAME_UPDATE = "OHG_CREATE_UPDATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_OHG_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_OPENING_HOURS");

	private static final int START_AT = 8;
	private static final int END_AT = 16;

	private MainPage navBar;
	private OpeningHoursGroupCrudFormPage crudForm;
	private OpeningHoursGroupShowPage show;

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
	public void doLoadData() throws Exception {

		createOpeningHours("Holiday");
		createOpeningHours("Sunday");
		createOpeningHours("Monday");
		createOpeningHours("Tuesday");
		createOpeningHours("Wednesday");
		createOpeningHours("Thursday");
		createOpeningHours("Friday");
		createOpeningHours("Saturday");
	}

	void createOpeningHours(String dayOfWeek) {

		LoadDataHelper.createOpeningHours(getDriver(), getBaseUrl(), dayOfWeek, START_AT, END_AT);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void login() throws Exception {

		createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void create() throws Exception {

		crudForm = navBar.visitOpeningHoursGroup().buttonNew();

		crudForm.setName(OHG_NAME_CREATE);
		crudForm.clickCheckOneDay("Feriado", START_AT, END_AT);
		crudForm.clickCheckOneDay("Domingo", START_AT, END_AT);
		crudForm.clickCheckOneDay("Segunda-feira", START_AT, END_AT);
		crudForm.clickCheckOneDay("Terça-feira", START_AT, END_AT);
		crudForm.clickCheckOneDay("Quarta-feira", START_AT, END_AT);
		crudForm.clickCheckOneDay("Quinta-feira", START_AT, END_AT);
		crudForm.clickCheckOneDay("Sexta-feira", START_AT, END_AT);
		crudForm.clickCheckOneDay("Sábado", START_AT, END_AT);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		crudForm = show.buttonEdit();

		crudForm.setName(OHG_NAME_UPDATE);
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {

		OpeningHoursGroupListPage listPage = show.buttonBackToSearch();

		listPage.setName(OHG_NAME_UPDATE);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}

	@Test(dependsOnMethods = { "delete" })
	public void undoLoadData() {

		deleteOH("Holiday");
		deleteOH("Sunday");
		deleteOH("Monday");
		deleteOH("Tuesday");
		deleteOH("Wednesday");
		deleteOH("Thursday");
		deleteOH("Friday");
		deleteOH("Saturday");
	}

	private void deleteOH(String dayOfWeek) {

		LoadDataHelper.deleteOpeningHours(getDriver(), getBaseUrl(), dayOfWeek, START_AT, END_AT);
	}
}
