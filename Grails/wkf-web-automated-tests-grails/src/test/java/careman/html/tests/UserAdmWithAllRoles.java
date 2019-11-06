package careman.html.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import careman.html.TestBase;

public class UserAdmWithAllRoles extends TestBase {

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

	// test 2

	@Test(priority = 2)
	public void createAdmUser() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 10);
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		getDriver().findElement(By.cssSelector("i.fa.fa-users")).click();
		getDriver().findElement(By.name("_action_create")).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("sidneyaraujo");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		getDriver().findElement(By.id("name")).clear();
		getDriver().findElement(By.id("name")).sendKeys("Sidney");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("sidney.araujo@avatek.com.br");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Administrator");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		Thread.sleep(500);
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "created"));
	}

	@Test(priority = 3)
	public void addAllRolesToAdmUser() throws Exception {		
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 10);

		// Adicionando as Permissiões.
		getDriver().findElement(By.id("btnSelectAll")).click();
		Thread.sleep(5000);
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EQUIPMENTS")).click();
		Thread.sleep(10000);
		getDriver().findElement(By.id("btnSavePermission")).click();

		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "saved"));
		logout(getDriver());
	}
}
