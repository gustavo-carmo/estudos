package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.user.UserCrudFormPage;
import br.com.workfinity.web.page.user.UserShowPage;
import careman.html.TestBase;

public class UserAdmWithAllRolesTest extends TestBase {

	private MainPage navBar;
	private UserShowPage show;
	private UserCrudFormPage crudForm;

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

		new LoginPage(getDriver(), "admin", "Iso1981").buttonSignInSuccess();
	}

	@Test(dependsOnMethods = { "login" })
	public void createAdmUser() throws Exception {
		crudForm = navBar.visitUserManager().buttonNew();

		crudForm.setUsername("sidneyaraujo");
		crudForm.setNewPassword("123456");
		crudForm.setRepeatNewPassword("123456");
		crudForm.setName("Sidney");
		crudForm.setLanguage("English, en_US (English)");
		crudForm.setProfile("Administrator");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();

		show.clickSelectAll();
		show.setPermission("ROLE_SHIPMENT_ORDER_EQUIPMENTS", false);
		show.clickSavePermission();
		show.validateMessageSuccessSaved();
	}
}
