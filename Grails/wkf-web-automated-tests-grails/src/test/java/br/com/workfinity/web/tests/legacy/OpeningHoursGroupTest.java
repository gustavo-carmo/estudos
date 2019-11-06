package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.openingHoursGroup.OpeningHoursGroupCrudFormPage;
import br.com.workfinity.web.page.openingHoursGroup.OpeningHoursGroupShowPage;
import careman.html.TestBase;

public class OpeningHoursGroupTest extends TestBase {

	private MainPage navBar;
	private OpeningHoursGroupShowPage show;
	private OpeningHoursGroupCrudFormPage crudForm;

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

		crudForm = navBar.visitOpeningHoursGroup().buttonNew();

		crudForm.setName("Opening Hours CRUD");
		crudForm.clickCheckUncheckAll(true);
		show = crudForm.buttonCreate();
		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		crudForm = show.buttonEdit();

		crudForm.setName("Opening Hours CRUD EDIT");
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}
}