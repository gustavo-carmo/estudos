package careman.html.tests;

import java.util.Objects;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import careman.html.TestBase;

public class LoadTestData extends TestBase {

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, true);
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test(priority = 1)
	public void doLoadAllTestData() throws Exception {

		getDriver().get(getBaseUrl() + "/seed/all");

		String bodyText = getDriver().findElement(By.tagName("body")).getText();
		assert Objects.equals("OK", bodyText);

	}


}