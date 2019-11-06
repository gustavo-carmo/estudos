package careman.html.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.helper.Messages;
import careman.html.TestBase;

public class Search extends TestBase {

	private StringBuffer verificationErrors = new StringBuffer();

	// private Selenium selenium;
	public String table_id;
	public String column;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	@Test(priority = 1)
	public void doLogin() throws Exception {
		loginOld(getDriver(), "sidneyaraujo", "123456");
	}

	@Test(priority = 2)
	public void searchStockControl() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 10);
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		getDriver().findElement(By.xpath("//ul[@id='mainnav-menu']/li[3]/a/span/strong")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Stock Control"))).click();
		new Select(getDriver().findElement(By.id("modelType"))).selectByVisibleText("Product");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		table_id = "resultList";
		column = "8";
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		new Select(getDriver().findElement(By.id("modelType"))).selectByVisibleText("Part");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		column = "8";
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		new Select(getDriver().findElement(By.id("modelType"))).selectByVisibleText("Component");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		column = "8";
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		new Select(getDriver().findElement(By.id("modelType"))).selectByVisibleText(Messages.ALL.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		column = "9";
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		new Select(getDriver().findElement(By.id("model"))).selectByVisibleText(Messages.ALL.toString());
		getDriver().findElement(By.id("serialNumber")).clear();
		getDriver().findElement(By.id("serialNumber")).sendKeys("2950032531022477");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		column = "10";
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		getDriver().findElement(By.id("serialNumber")).clear();
		getDriver().findElement(By.id("serialNumber")).sendKeys("");
		new Select(getDriver().findElement(By.id("situation"))).selectByVisibleText("Good");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		column = "7";
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		new Select(getDriver().findElement(By.id("situation"))).selectByVisibleText("Bad");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		column = "7";
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		new Select(getDriver().findElement(By.id("situation"))).selectByVisibleText(Messages.ALL.toString());
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.DISABLE.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td"), "No result"));
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		getDriver().findElement(By.id("PO")).clear();
		getDriver().findElement(By.id("PO")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td"), "No result"));
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		getDriver().findElement(By.id("PO")).clear();
		getDriver().findElement(By.id("PO")).sendKeys("");
		getDriver().findElement(By.id("SI")).clear();
		getDriver().findElement(By.id("SI")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td"), "No result"));
		/*
		 * (new WebDriverWait(getDriver(), 10)).
		 * until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters")));
		 * webDriverWait.until(ExpectedConditions.elementToBeClickable
		 * (By.linkText("Filters"))).click();
		 */
		getDriver().findElement(By.id("SI")).clear();
		getDriver().findElement(By.id("SI")).sendKeys("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		getDriver().findElement(By.xpath("//ul[@id='mainnav-menu']/li[3]/a/span/strong")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Stock Control"))).click();
		Assert.assertTrue(isElementPresent(getDriver(), By.xpath("/*//*[@id=\"resultList\"]")));
	}
}
