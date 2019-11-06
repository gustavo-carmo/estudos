package careman.html;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestSuiteHelper {

	private String baseUrl;
	private String gridURL;
	private WebDriver webDriver;
	private WebDriverWait webDriverWait;

	private boolean acceptNextAlert = true;

	public TestSuiteHelper(final String baseURL, final String gridURL) throws Exception {

		this.baseUrl = baseURL;
		this.gridURL = gridURL;

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.MAC);

		webDriver = new RemoteWebDriver(new URL(this.gridURL), capability);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().window().setSize(new Dimension(1366, 768));

		System.out.println("Start with resolution: " + webDriver.manage().window().getSize());
	}

	public WebDriver getWebDriver() {
		return this.webDriver;
	}

	public void login(WebDriver driver, String username, String password) {

		driver.get(this.getBaseUrl() + "/login/auth");

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).clear();

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), this.getBaseUrl() + "/", "Login fail...");
	}

	public void logout(WebDriver driver) {
		driver.get(this.getBaseUrl() + "/logout/index");

		/*
		 * driver.findElement(By.xpath("//li[@id='dropdown-user']/a/span/img")).
		 * click(); (new WebDriverWait(driver, 10)).
		 * until(ExpectedConditions.presenceOfElementLocated
		 * (By.partialLinkText("Logout"))).click();
		 */
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	}

	public String getBaseUrl() {
		return this.baseUrl;
	}

	public String getCurrentDirectory() {
		return System.getProperty("user.dir");
	}

	/*
	 * public DesiredCapabilities getFirefox() throws IOException { return
	 * DesiredCapabilities.firefox(); }
	 *
	 * public String getFromConfigPropertiesFile(String key) { return
	 * prop.getProperty(key); }
	 */

	public boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isAlertPresent(WebDriver driver) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public String closeAlertAndGetItsText(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	public String generateLengthNumber(int value) {
		String number = "";
		int aux = 0;
		for (int i = 0; i < value; i++) {
			aux = (int) Math.floor(Math.random() * 10);
			number += aux;
		}
		return number;
	}

	public String setEarlyDay(int value) {
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -value);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(today.getTime());
	}

	public Boolean findContentInTable(WebDriver driver, String tableXpath, int column, String value) {
		int count = 0;
		int i;
		int total = Integer.parseInt(driver.findElement(By.id("resultListTotal")).getAttribute("value"));

		for (i = 1; i <= total; i++) {
			if (value.equals(driver.findElement(By.xpath(tableXpath + "/tbody/tr[" + i + "]/td[" + column + "]")).getText())) {
				count++;
			}
		}
		return (count == total);
	}

	public Boolean verifyTable(WebDriver driver, String tableXpath, int line, int column, String value) {
		if (value.equals(driver.findElement(By.xpath(tableXpath + "/tbody/tr[" + line + "]/td[" + column + "]")).getText())) {
			return true;
		}
		return false;
	}

	public void validateMessagesErrors(WebDriverWait webDriverWait, String... messages) {
		for (int x = 0; x < messages.length; x++) {
			webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"list-error\"]/li[" + (x + 1) + "]"),
					messages[x]));
		}
	}

	public void validateMessagesErrors(String... messages) {
		for (int x = 0; x < messages.length; x++) {
			new WebDriverWait(getWebDriver(), 10).until(ExpectedConditions.textToBePresentInElementLocated(
					By.xpath("//*[@id='page-alert']/div/div/div/div[2]/div/li[" + (x + 1) + "]"), messages[x]));
		}
	}

	public void validateMessageSuccess(String message) {
		new WebDriverWait(getWebDriver(), 30).until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//div[@id='page-alert']/div/div/div/div[2]/h4"), message));
	}

	public void validateMessageUpdated(WebDriverWait webDriverWait, String message) {
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), message));
	}

	public static String randomString(Integer size) {
		return null != size ? RandomStringUtils.random(size, true, true) : "";
	}

	public void createUserAndDoLogin(String username, String password, List<String> roles) {

		// loginOld(this.webDriver, "admin", "Iso1981");

		this.webDriver.get(getBaseUrl() + "/util/console");
		this.webDriver.findElement(By.id("command")).clear();

		String script = "import br.com.careman.domain.*\nimport br.com.careman.groovy.enums.*\n\ndef username = \"--username--\"\ndef password = \"--password--\"\nSet roles = --roles--\n\ndef name = username\ndef email = username + \"@email.com\"\ndef language = Language.findByLocale(\"en_US\")\ndef profile = Profile.ADMINISTRATOR\n\ndef userInstance = new User(\nusername: username,\nenabled: true,\npassword: password,\nname: name,\nemail: email,\nlanguage: language,\nprofile: profile\n).save(flush: true)\n\nif(userInstance) {\nprintln userInstance\nroles.each {\ndef roleInstance = Role.findByAuthority(it)\nif(roleInstance) {\ndef userRoleInstance = UserRole.create(userInstance, roleInstance, true)\nif(userRoleInstance) {\nprintln userRoleInstance.user.username + \"-\" + userRoleInstance.role\n}\n}\n}\n} else {\nprintln \"user not created\"\n}";
		script = script.replace("--username--", username);
		script = script.replace("--password--", password);

		StringBuilder sb = new StringBuilder("[");
		String separator = "";
		for (String role : roles) {
			sb.append(separator);
			separator = ",";
			sb.append("'");
			sb.append(role);
			sb.append("'");
		}
		sb.append("]");

		script = script.replace("--roles--", sb.toString());

		WebElement textArea = this.webDriver.findElement(By.id("command"));
		textArea.clear();
		textArea.sendKeys(script);

		this.webDriver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		Assert.assertEquals(this.webDriver.findElement(By.id("1")).getText(), username);

		Iterator<String> iterator = roles.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(this.webDriver.findElement(By.id(String.valueOf(count + 2))).getText(), username + "-" + iterator.next());
			count++;
		}

		logout(this.webDriver);
		login(this.webDriver, username, password);
	}

	public void openLeftMenuAndClick(String menuLevelOne, String menuLevelTwop) {
		getWebDriver().get(getBaseUrl());
		getWebDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		(new WebDriverWait(getWebDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.linkText(menuLevelOne))).click();
		getWebDriver().findElement(By.linkText(menuLevelTwop)).click();

		/*
		 * new WebDriverWait(getWebDriver(), 10).until(new
		 * ExpectedCondition<Boolean>() {
		 *
		 * @Override public Boolean apply(WebDriver driver) { return (boolean)
		 * ((JavascriptExecutor)
		 * driver).executeScript("return jQuery.active == 0;"); } });
		 */
	}

	public void waitAjaxEnd() {
		new WebDriverWait(getWebDriver(), 10).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0;");
			}
		});
	}

	public void loadData(String script) {

		this.getWebDriver().get(this.getBaseUrl() + "/util/console");
		this.getWebDriver().findElement(By.id("command")).clear();
		this.getWebDriver().findElement(By.id("command")).sendKeys(script);

		this.getWebDriver().findElement(By.cssSelector("input[type=\"submit\"]")).click();
	}

	public void linkCreateFromSearch() {
		WebElement btCriar = getWebDriver().findElement(By.name("_action_create"));

		if (!btCriar.isDisplayed()) {
			new WebDriverWait(this.getWebDriver(), 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		}

		btCriar.click();
	}

	public void linkEditFromShow() {

		Assert.assertTrue(getWebDriver().getCurrentUrl().contains("show"), "Page is not show");
		getWebDriver().findElement(By.name("_action_edit")).click();
	}

	public void linkSearchFromShow() {

		Assert.assertTrue(getWebDriver().getCurrentUrl().contains("show"), "Page is not show");
		getWebDriver().findElement(By.name("_action_index")).click();
	}

	public void linkSearchFromCreate() {

		boolean error = false;
		String url = getWebDriver().getCurrentUrl();

		List<String> actions = Arrays.asList("create", "save", "edit", "update");
		for (String action : actions) {

			error = url.contains(action);
			if (error)
				break;
		}

		Assert.assertTrue(error, "Page is not create|save|edit|update, is " + url);

		getWebDriver().findElement(By.name("_action_index")).click();
	}

	public void linkCreateFromEdit() {

		Assert.assertTrue(getWebDriver().getCurrentUrl().contains("edit"), "Page is not edit");
		getWebDriver().findElement(By.name("_action_index")).click();
		linkCreateFromSearch();
	}

	public void createServiceOrder(String documentNumber, String serviceGroup, String service) {

		getBaseUrl();
		getWebDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		(new WebDriverWait(getWebDriver(), 10)).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='mainnav-menu']/li[2]/a/span/strong"))).click();

		(new WebDriverWait(getWebDriver(), 300)).until(ExpectedConditions.elementToBeClickable(By.id("customerDocumentNumber"))).clear();
		(new WebDriverWait(getWebDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.id("customerDocumentNumber"))).sendKeys(
				documentNumber);
		(new WebDriverWait(getWebDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ul/li/a"))).click();
		(new WebDriverWait(getWebDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.id("customerDocumentNumber"))).getText()
				.contains(documentNumber);

		(new WebDriverWait(getWebDriver(), 300)).until(ExpectedConditions.elementToBeClickable(By.id("group"))).isEnabled();
		(new WebDriverWait(getWebDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.id("group")));
		new Select(getWebDriver().findElement(By.id("group"))).selectByVisibleText(serviceGroup);
		(new WebDriverWait(getWebDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.id("group"))).isSelected();

		(new WebDriverWait(getWebDriver(), 300)).until(ExpectedConditions.elementToBeClickable(By.id("service"))).isEnabled();
		(new WebDriverWait(getWebDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.id("service")));
		new Select(getWebDriver().findElement(By.id("service"))).selectByVisibleText(service);
		(new WebDriverWait(getWebDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.id("service"))).isSelected();
	}



	public void loadDataFromFile(String fileName) throws IOException {

		// TODO Get from param
		StringBuilder redourceFromTestFolder = new StringBuilder(System.getProperty("user.dir"));
		redourceFromTestFolder.append("/src/test/resources");

		redourceFromTestFolder.append(fileName);

		File f = new File(redourceFromTestFolder.toString());

		if (!f.exists()) {

			// TODO - logger.....
			System.out.println("ERROR: " + f.getAbsolutePath() + " not found");
			throw new FileNotFoundException();
		}

		try {

			List<String> readLines = FileUtils.readLines(f);

			StringBuilder s = new StringBuilder();
			String concatChar = "";

			for (String line : readLines) {
				if (line.length() > 0) {
					s.append(concatChar);
					// s.append(line.replace("\"", "\""));
					s.append(line);
					concatChar = "\n";
				}
			}

			loadData(s.toString());

		} catch (IOException e) {

			// TODO - logger.....
			System.out.println("ERROR: " + e.getMessage());
			throw e;
		}
	}

	public void validateResultFromConsole(ArrayList<String> expectedResult) {

		if (null != expectedResult && !expectedResult.isEmpty()) {
			for (int i = 0; i < expectedResult.size(); i++) {
				Assert.assertEquals(getWebDriver().findElement(By.id(String.valueOf(i + 1))).getText(), expectedResult.get(i));
			}
		}
	}

	public void loadDataFromFileAndValidateResult(String fileName, ArrayList<String> expectedResult) {

		try {
			loadDataFromFile(fileName);
			validateResultFromConsole(expectedResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jqueryTriggerEventToElement(String jquerySelector, String event) {
		((JavascriptExecutor) getWebDriver()).executeScript("$('" + jquerySelector + "').trigger('" + event + "'); return true;");
	}

	public String OSGetCode() {
		String code = new WebDriverWait(getWebDriver(), 60)
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("tabServiceOrderId"))).getText();
		Assert.assertFalse(code.length() == 0, "code is empty...");
		return code;
	}
}
