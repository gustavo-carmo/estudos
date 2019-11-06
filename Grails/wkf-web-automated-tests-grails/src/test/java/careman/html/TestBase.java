package careman.html;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import br.com.workfinity.util.SeleniumUtil;
import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.login.LoginPage;
import careman.util.FileLoader;

public abstract class TestBase {

	static final Logger LOG = LoggerFactory.getLogger(TestBase.class);
	public List<String> userRoles = Arrays.asList("ROLE_DASHBOARD", "ROLE_SERVICE_ORDER_CREATE", "ROLE_CONTRACTOR", "ROLE_CUSTOMER",
			"ROLE_SERVICE_PROVIDER", "ROLE_EQUIPMENT", "ROLE_FAMILY", "ROLE_DEFECT", "ROLE_SOLUTION", "ROLE_MANUFACTURER", "ROLE_MODEL",
			"ROLE_SERVICE_GROUP", "ROLE_REASON_FOR_CANCELLATION", "ROLE_WORKFLOW", "ROLE_STEP_STATUS", "ROLE_SERVICE_ORDER",
			"ROLE_REPAIR_ORDER", "ROLE_IMPORT_PROCESS", "ROLE_PRODUCTION_PLANNING", "ROLE_DEFECT_LABORATORY", "ROLE_SOLUTION_LABORATORY",
			"ROLE_ACTIVITY", "ROLE_REQUEST_PIECES", "ROLE_DELIVER_PIECES", "ROLE_QUALITY_EVALUATION", "ROLE_RECESS", "ROLE_OPENING_HOURS",
			"ROLE_PRIORITY_GROUP", "ROLE_STOCK", "ROLE_PROJECT", "ROLE_SERVICE_LEVEL_AGREEMENT_GROUP", "ROLE_PROFILE_SETTINGS",
			"ROLE_CUSTOM_FIELD", "ROLE_SHIFT_GROUP", "ROLE_REPAIR_LEVEL", "ROLE_LOCATION", "ROLE_SERVICE_AREAS", "ROLE_WARRANTY_PROVIDER",
			"ROLE_ROUTING_GROUP", "ROLE_NOTIFICATION", "ROLE_GENERIC_CONTRACT", "ROLE_STOCK_PLANNING_VIEW", "ROLE_TARGET", "ROLE_TEMPLATE",
			"ROLE_COMPOSITE_TEMPLATE_AND_TARGET", "ROLE_SCRIPT", "ROLE_CARRIER", "ROLE_SHIPMENT_ORDER", "ROLE_SHIPMENT_ORDER_TYPE",
			"ROLE_INVENTORY", "ROLE_IMPORT_PROCESS_BANRISUL", "ROLE_IMPORT_PROCESS_BANRISUL_PAYMENT", "ROLE_EXPORT_BANRISUL",
			"ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER", "ROLE_REPORT_REPAIR_CONTRACTOR_AVERAGE", "ROLE_REPORT_TOTAL_BAD_EQUIPMENT_BY_RANGE",
			"ROLE_CONSUMPTION_REPORT", "ROLE_SERVICE_ORDER_CONSUMPTION_REPORT", "ROLE_REPORT_LABORATORY_BACKLOG",
			"ROLE_REPORT_SUMMARY_REPAIR_ORDER", "ROLE_REPORT_SUMMARY_REPAIR_ORDER_X_SHIPMENT_ORDER_CLOSED", "ROLE_MESSAGE",
			"ROLE_APP_SETTINGS", "ROLE_APP_SETTINGS_REBUILD_REPORT_SHIPMENT_REPAIR", "ROLE_GROUP", "ROLE_USER", "ROLE_STEP",
			"ROLE_CONTRACT", "ROLE_SERVICE_LEVEL_AGREEMENT", "ROLE_IMPORT_PROCESS_FROM_TO", "ROLE_USER_PERMISSION", "ROLE_PROFILE_STATUS",
			"ROLE_EQUIPMENT_ORIGIN", "ROLE_EQUIPMENT_ADMIN", "ROLE_SERVICE_ORDER_SERVICE_PROVIDER", "ROLE_SERVICE_ORDER_HISTORY",
			"ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER_DEADLINE_HD_EDIT", "ROLE_REPAIR_ORDER_MASTER_REPORT",
			"ROLE_SHIPMENT_ORDER_EDIT", "ROLE_SHIPMENT_ORDER_EQUIPMENTS", "ROLE_VIEW_OTHER_STOCK", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS",
			"ROLE_BUCKET_OBJECT", "ROLE_ATTACHMENT", "ROLE_REPAIR_ORDER_DELETE", "ROLE_REPAIR_ORDER_EXPORT",
			"ROLE_REPAIR_ORDER_CONSUMPTION_DELETE", "ROLE_REPAIR_ORDER_SEE_COST", "ROLE_TAB", "ROLE_SEARCH",
			"ROLE_REPORT_SUMMARY_REPAIR_ORDER_SEE_COST", "ROLE_REPORT_SUMMARY_REPAIR_ORDER_X_SHIPMENT_ORDER_CLOSED_SEE_COST",
			"ROLE_STOCK_PLANNING_MANAGEMENT", "ROLE_SERVICE_ORDER_UPDATE_CLOSED_DATE_BIGGER_THEN_ALLOWED_IN_SERVICE",
			"ROLE_CUSTOM_FIELD_RESTRICTED_VIEW_EDIT", "ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_PLATFORM", "ROLE_SCHEDULED_TASK",
			"ROLE_WORK_PACK", "ROLE_RENTAL_AGREEMENT", "ROLE_SCHEDULED_GRID");

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;

	private boolean doBkp = false;

	public void setUp(String baseURL, String gridURL, boolean doBkp) throws Exception {

		this.doBkp = doBkp;
		config(baseURL, gridURL);
	}

	public void setUp(String baseURL, String gridURL) throws Exception {

		config(baseURL, gridURL);

		// LOG.info("Start with resolution: " +
		// driver.manage().window().getSize());
	}

	private void config(String baseURL, String gridURL) throws MalformedURLException {
		this.baseUrl = baseURL;

		getDriver(gridURL);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().setSize(new Dimension(1280, 5000));
	}

	public boolean isDoBkp() {
		return doBkp;
	}

	private void getDriver(String gridURL) throws MalformedURLException {

		if (gridURL.length() == 0) {
			driver = new FirefoxDriver();
		} else {

			DesiredCapabilities capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(Platform.LINUX);

			driver = new RemoteWebDriver(new URL(gridURL), capability);
			((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getBaseUrl() {
		return this.baseUrl;
	}

	public void loginOld(WebDriver driver, String username, String password) throws Exception {

		// logout(getDriver());
		driver.get(getBaseUrl() + "/login/auth");

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).clear();

		// Thread.sleep(5000);
		// driver.get(getBaseUrl() + "/login/auth");
		// (new WebDriverWait(driver,
		// 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).clear();

		new LoginPage(driver, username, password).buttonSignInSuccess();

		Assert.assertEquals(driver.getCurrentUrl(), this.getBaseUrl() + "/", "Login fail...");
	}

	public void validateMessageSuccess(String message) {
		waitPageLoadEnds();
		new WebDriverWait(getDriver(), 30).until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//div[@id='page-alert']/div/div/div/div[2]/h4"), message));
	}
	
	public String randomNumber(int quantityNumbers) {
		Random gerador = new Random();
		String retorno = "";

		for (int i = 0; i < quantityNumbers; i++) {
			retorno += gerador.nextInt(10);
		}

		return retorno;
	}

	public void waitAjaxEnd() {
		new WebDriverWait(getDriver(), 60).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0;");
			}
		});
	}

	public void loadDataFromFile(String fileName) throws IOException {

		// TODO Get from param
		try {
			FileLoader loader = new FileLoader();
			String loaded = loader.load(fileName);

			loadData(loaded);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw e;
		}
	}

	public void validateResultFromConsole(ArrayList<String> expectedResult) {

		if (null != expectedResult && !expectedResult.isEmpty()) {
			for (int i = 0; i < expectedResult.size(); i++) {

				try {
					Assert.assertEquals(getDriver().findElement(By.id(String.valueOf(i + 1))).getText(), expectedResult.get(i),
							"Expected row with value " + expectedResult.get(i) + " not found!!!");
				} catch (NoSuchElementException e) {
					Assert.fail("Element with id + " + (i + 1) + " not found (expected value to this webelement " + expectedResult.get(i)
							+ ")");
				}
			}
		}
	}

	public void loadDataFromFileAndValidateResult(String fileName, ArrayList<String> expectedResult) throws Exception {
		loadDataFromFile(fileName);
		validateResultFromConsole(expectedResult);
	}

	public void loadData(String script) {

		this.getDriver().get(this.getBaseUrl() + "/util/console");

		WebElement webElement = getDriver().findElement(By.id("command"));
		webElement.clear();
		webElement.sendKeys(script);

		this.getDriver().findElement(By.cssSelector("input[type=\"submit\"]")).click();
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

	public String OSGetCode() {
		String code = new WebDriverWait(getDriver(), 60).until(ExpectedConditions.visibilityOfElementLocated(By.id("tabServiceOrderId")))
				.getText();
		Assert.assertFalse(code.length() == 0, "code is empty...");
		return code;
	}

	public void scroolToElement(WebElement webElement) {
		((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
	}

	public void scroolDown(int totalPixels) {
		((JavascriptExecutor) this.driver).executeScript("window.scrollBy(0," + totalPixels + "); return true;", "");
	}

	public void scroolUp(int totalPixels) {
		((JavascriptExecutor) this.driver).executeScript("window.scrollBy(0," + (0 - totalPixels) + "); return true;", "");
	}

	public void OSSelectGroup(String groupValue, WebDriver driver) {

		WebElement selectGroup = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement el = driver.findElement(By.id("group"));
				return el.isEnabled() ? el : null;
			}
		});

		new Select(selectGroup).selectByVisibleText(groupValue);
		waitAjaxEnd();
		scroolUp(1000);
		((JavascriptExecutor) driver).executeScript("$('#group').trigger('change'); return true;");
	}

	public void OSSelectService(String serviceValue, WebDriver driver) {

		WebElement selectService = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement el = driver.findElement(By.id("service"));
				return el.isEnabled() ? el : null;
			}
		});

		scroolUp(1000);
		new Select(selectService).selectByVisibleText(serviceValue);
	}

	public void OSSelectDefect(String defectValue, WebDriver driver) {

		WebElement selectDefect = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement el = driver.findElement(By.id("defect"));
				return el.isEnabled() ? el : null;
			}
		});

		new Select(selectDefect).selectByVisibleText(defectValue);
		waitAjaxEnd();
		scroolUp(1000);
		((JavascriptExecutor) driver).executeScript("$('#defect').trigger('change'); return true;");
	}

	public void OSSelectSolution(String solutionValue, WebDriver driver) {

		WebElement selectSolution = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement el = driver.findElement(By.id("solution"));
				return el.isEnabled() ? el : null;
			}
		});

		scroolUp(1000);
		new Select(selectSolution).selectByVisibleText(solutionValue);
	}

	public void OSSetClosedDateValueToCurrentDate() {

		Calendar calendar = Calendar.getInstance();
		// calendar.add(Calendar.SECOND, -10);

		WebElement webElement = getDriver().findElement(By.id("closedDate_value"));
		webElement.clear();
		webElement.sendKeys(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(calendar.getTime()));

		((JavascriptExecutor) getDriver()).executeScript("$('#closedDate_value').trigger('change'); return true;");

		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("closedDate_value"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[3]/div[3]/button[1]"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[3]/div[3]/button[2]"))).click();
	}

	public void OSSetOpeningDateValue(String date) {

		WebElement webElement = getDriver().findElement(By.id("openingDate_value"));
		webElement.clear();
		webElement.sendKeys(date);

		((JavascriptExecutor) getDriver()).executeScript("$('#openingDate_value').trigger('change'); return true;");

		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("closedDate_value"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[3]/div[3]/button[1]"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[3]/div[3]/button[2]"))).click();
	}

	public void shipmentOrderSetInvoiceDateClosedDateValueToCurrentDate() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -3);

		WebElement webElement = getDriver().findElement(By.id("invoiceDate_value"));
		webElement.clear();
		webElement.sendKeys(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(calendar.getTime()));

		((JavascriptExecutor) getDriver()).executeScript("$('#invoiceDate_value').trigger('change'); return true;");

		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("closedDate_value"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[3]/div[3]/button[1]"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[3]/div[3]/button[2]"))).click();
	}

	public void shipmentOrderSetOutboundDateToCurrentDate() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -3);

		WebElement webElement = getDriver().findElement(By.id("outboundDate_value"));
		webElement.clear();
		webElement.sendKeys(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(calendar.getTime()));

		((JavascriptExecutor) getDriver()).executeScript("$('#outboundDate_value').trigger('change'); return true;");

		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("closedDate_value"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[3]/div[3]/button[1]"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[3]/div[3]/button[2]"))).click();
	}

	public void logout(WebDriver driver) {
		driver.get(this.getBaseUrl() + "/logout/index");

		/*
		 * driver.findElement(By.xpath("//li[@id='dropdown-user']/a/span/img")).
		 * click(); (new WebDriverWait(driver, 10)).
		 * until(ExpectedConditions.presenceOfElementLocated
		 * (By.partialLinkText("Logout"))).click();
		 */
		(new WebDriverWait(driver, 30)).until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
	}

	public void openLeftMenuAndClick(String menuLevelOne, String menuLevelTwop) {
		getDriver().get(getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		(new WebDriverWait(getDriver(), 10)).until(ExpectedConditions.elementToBeClickable(By.linkText(menuLevelOne))).click();
		getDriver().findElement(By.linkText(menuLevelTwop)).click();

		/*
		 * new WebDriverWait(getDriver(), 10).until(new
		 * ExpectedCondition<Boolean>() {
		 * 
		 * @Override public Boolean apply(WebDriver driver) { return (boolean)
		 * ((JavascriptExecutor)
		 * driver).executeScript("return jQuery.active == 0;"); } });
		 */
	}

	public Boolean verifyTable(WebDriver driver, String tableXpath, int line, int column, String value) {
		if (value.equals(driver.findElement(By.xpath(tableXpath + "/tbody/tr[" + line + "]/td[" + column + "]")).getText())) {
			return true;
		}
		return false;
	}

	public boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	protected String getCurrentDate() {

        Calendar calendar = Calendar.getInstance();
        // calendar.add(Calendar.MINUTE, -1);
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(calendar.getTime());
	}

	public String getCurrentDirectory() {
		return System.getProperty("user.dir");
	}

	public void validateMessagesErrors(String... errorsRequired) {

		waitPageLoadEnds();

		// TODO tem um erro aqui, verificar pq o texto não está disponível
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> webElementsErrors = driver.findElements(By.cssSelector("#main-flash-message .beanErrorsItem"));

		List<String> webElementsErrorsString = new ArrayList<String>();
		for (WebElement webElement : webElementsErrors) {
			webElementsErrorsString.add(webElement.getText());
		}

		Assert.assertTrue(errorsRequired.length == webElementsErrorsString.size(), "Expected error size [" + errorsRequired.length + "]( "
				+ errorsRequired + ") but foud [" + webElementsErrorsString.size() + "] ( " + webElementsErrorsString + ")");

		for (String errorRequired : errorsRequired) {
			Assert.assertTrue(webElementsErrorsString.contains(errorRequired), "Error message [" + errorRequired + "] not found in "
					+ webElementsErrorsString.toString());
		}
	}

	public void waitPageLoadEnds() {
		new WebDriverWait(driver, 300).until(ExpectedConditions.visibilityOfElementLocated(By.className("pace-done")));
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

	public static String randomString(Integer size) {
//		return "123456";
		return null != size ? RandomStringUtils.random(size, true, true) : "";
	}

	public void createUserAndDoLogin(String username, String password, List<String> roles, String language) {

		LoadDataHelper.createUser(getDriver(), getBaseUrl(), username, password, roles, language);

		logout(getDriver());

		new LoginPage(getDriver(), username, password).buttonSignInSuccess();
	}

	public void createUserAndDoLogin(String username, String password, List<String> roles) {
		createUserAndDoLogin(username, password, roles, "pt_BR");
	}

	public void createUserAndDoLogin(String username, String password) {
		LoadDataHelper.createUserNoPermission(getDriver(), getBaseUrl(), username, password, "pt_BR");

		logout(getDriver());

		new LoginPage(getDriver(), username, password).buttonSignInSuccess();
	}

	public void takeScrenshot(String fileName) {
		SeleniumUtil.takeScreenShot(fileName, getDriver());
	}
}
