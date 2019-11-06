package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.manufacturer.ManufacturerCrudFormPage;
import br.com.workfinity.web.page.manufacturer.ManufacturerShowPage;
import careman.html.TestBase;

public class ManufacturerTest extends TestBase {

	private MainPage navBar;
	private ManufacturerCrudFormPage crudForm;
	private ManufacturerShowPage show;

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
	public void create() {

		crudForm = navBar.visitManufacturer().buttonNew();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setName("CRUD Manufacturer - New");
		show = crudForm.buttonCreate();
		
		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		show.buttonEdited();

		crudForm.setName("CRUD Manufacturer - Edit");
		crudForm.setStatus(Messages.ENABLED.toString());
		crudForm.buttonUpdate();
		crudForm.validateMessageSuccessUpdate();
	}
}
