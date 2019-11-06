package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.openingHours.OpeningHoursCrudFormPage;
import br.com.workfinity.web.page.openingHours.OpeningHoursListPage;
import br.com.workfinity.web.page.openingHours.OpeningHoursShowPage;
import careman.html.TestBase;

public class OpeningHoursTestCRUD extends TestBase {

	private static final String DAY_CREATE = "Feriado";
	private static final String DAY_UPDATE = "Terça-feira";

	private static final String USERNAME = "USERNAME_OPENING_HOURS_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_OPENING_HOURS");

	private MainPage navBar;
	private OpeningHoursCrudFormPage crudForm;
	private OpeningHoursShowPage show;

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
	public void create() throws Exception {

		crudForm = navBar.visitOpeningHours().buttonNew();

		crudForm.setStartAt("07:00");
		crudForm.setEndAt("12:00");
		crudForm.setDayOfWeek(DAY_CREATE);
		show = crudForm.buttonCreateSuccess();
		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		crudForm = show.buttonEdit();
		crudForm.setDayOfWeek(DAY_UPDATE);
		show = crudForm.buttonUpdateSuccess();

		show.validateMessageSuccessUpdate();
		crudForm = show.buttonEdit();

		crudForm.setEndAt("04:00");
		crudForm.buttonUpdateFail();

		validateMessagesErrors("Finaliza Em deve ser depois de Início Em");

		crudForm.setEndAt("07:00");
		crudForm.buttonUpdateFail();

		validateMessagesErrors("Finaliza Em deve ser depois de Início Em");

		crudForm.setStartAt("08:00");
		crudForm.setEndAt("12:00");
		show = crudForm.buttonUpdateSuccess();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {

		OpeningHoursListPage listPage = show.buttonBackToSearch();

		listPage.setDaysOfWeek(DAY_UPDATE).buttonSearch();

		listPage.findRowAndDeleteItemTable(DAY_UPDATE, "8:00", "12:00");
	}
}
