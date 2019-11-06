package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.family.FamilyCrudFormPage;
import br.com.workfinity.web.page.family.FamilyShowPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import careman.html.TestBase;

public class FamilyTest extends TestBase {
	private MainPage navBar;
	private FamilyCrudFormPage crudForm;
	private FamilyShowPage show;

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
		crudForm = navBar.visitFamily().buttonNew();

		crudForm.setName("CRUD Family - New");
		crudForm.setStatus(Messages.DISABLE.toString());
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}
	
	@Test(dependsOnMethods = { "create" })
	public void edit() {
		crudForm = show.buttonEdit();

		crudForm.setName("CRUD Family - Edit");
		crudForm.setStatus(Messages.ENABLED.toString());
	
		show = crudForm.buttonUpdate();
		show.validateMessageSuccessUpdate();
	}
}
