package careman.html.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import careman.html.TestBase;

public class Login extends TestBase {

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test(priority = 1)
	public void doLogin() throws Exception {
		loginOld(getDriver(), "admin", "Iso1981");
	}

	@Test(priority = 2)
	public void test01_example() throws Exception {
		Assert.assertEquals("Workfinity", this.getDriver().getTitle());
	}

	@Test(priority = 3)
	public void doLogout() throws Exception {
		logout(getDriver());
	}
}
