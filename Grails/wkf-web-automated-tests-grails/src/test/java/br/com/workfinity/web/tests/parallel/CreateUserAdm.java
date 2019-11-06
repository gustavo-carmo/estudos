package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.user.UserCrudFormPage;
import br.com.workfinity.web.page.user.UserShowPage;
import careman.html.TestBase;

public class CreateUserAdm extends TestBase {
	private static final String USER = "USERNAME_" + randomString(5);
	private MainPage navBar;
	private UserShowPage show;
	private UserCrudFormPage crudForm;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, true);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void login() throws Exception {
		createUserAndDoLogin(USER, "123456", Arrays.asList("ROLE_USER", "ROLE_USER_PERMISSION", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS"));
	}

	@Test(dependsOnMethods = { "login" })
	public void createAdmUser() throws Exception {
		crudForm = navBar.visitUserManager().buttonNew();

		show = crudForm.setUsername("sidneyaraujo").setNewPassword("123456").setRepeatNewPassword("123456").setName("Sidney")
				.setLanguage("Portuguese, pt_BR (Português)").setProfile("Administrador").buttonCreate();

		show.validateMessageSuccessCreated();

		show.clickSelectAll();
		show.setPermission("ROLE_SHIPMENT_ORDER_EQUIPMENTS", false).clickSavePermission();
		show.validateMessageSuccess("Permissões atualizadas");
	}
}
