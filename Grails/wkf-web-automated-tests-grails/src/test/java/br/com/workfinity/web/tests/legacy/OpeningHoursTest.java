package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.openingHours.OpeningHoursCrudFormPage;
import br.com.workfinity.web.page.openingHours.OpeningHoursShowPage;
import careman.html.TestBase;

public class OpeningHoursTest extends TestBase {

	private MainPage navBar;
	private OpeningHoursCrudFormPage crudForm;
	private OpeningHoursShowPage show;

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
	public void create() throws Exception {

		crudForm = navBar.visitOpeningHours().buttonNew();

		crudForm.setStartAt("07:00");
		crudForm.setEndAt("12:00");
		show = crudForm.buttonCreateSuccess();
		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		crudForm = show.buttonEdit();
		crudForm.setDayOfWeek("Holiday");
		show = crudForm.buttonUpdateSuccess();

		show.validateMessageSuccessUpdate();
		crudForm = show.buttonEdit();

		crudForm.setEndAt("04:00");
		crudForm.buttonUpdateFail();

		validateMessagesErrors("End At must be after Start At");
		crudForm.setEndAt("07:00");
		crudForm.buttonUpdateFail();

		validateMessagesErrors("End At must be after Start At");
		crudForm.setStartAt("08:00");
		crudForm.setEndAt("12:00");
		show = crudForm.buttonUpdateSuccess();

		show.validateMessageSuccessUpdate();
	}
}
