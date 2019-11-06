package br.com.workfinity.web.tests.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.user.UserCrudFormPage;
import br.com.workfinity.web.page.user.UserListPage;
import br.com.workfinity.web.page.user.UserShowPage;
import careman.html.TestSuiteHelper;

public class CreateUserAdmWithAllRoles {

	private WebDriver driver;

	@BeforeClass
	@Parameters({ "baseURL" })
	public void setUp(String baseURL) throws Exception {

		driver = new FirefoxDriver();
		driver.get(baseURL);
	}

	@AfterClass
	public void tearDown() throws Exception {
//		driver.quit();
	}

	@Test
	public void test() {
		
		// new LoginPage(driver, "admin", "Iso1981").buttonSignIn();

		new MainPage(driver).visitUserManager();
		
		new UserListPage(driver).buttonNew();
		
		UserCrudFormPage userCrudFormPage = new UserCrudFormPage(driver);
		userCrudFormPage.setUsername("sidney" + TestSuiteHelper.randomString(3));
		userCrudFormPage.setNewPassword("123456");
		userCrudFormPage.setRepeatNewPassword("123456");
		userCrudFormPage.setName("Sidney");
		userCrudFormPage.setLanguage("English, en_US (English)");
		userCrudFormPage.setProfile("Administrator");
		userCrudFormPage.buttonCreate();
		
		UserShowPage userShowPage = new UserShowPage(driver);
		userShowPage.validateMessageSuccessCreated();
		userShowPage.clickSelectAll();
		userShowPage.setPermission("ROLE_SHIPMENT_ORDER_EQUIPMENTS", false);
		userShowPage.clickSavePermission();
		userShowPage.validateMessageSuccessSaved();
	}
}
