package careman.html.tests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.helper.Messages;
import careman.html.TestBase;

@SuppressWarnings("serial")
public class DevPart03 extends TestBase {

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
		loginOld(getDriver(), "sidneyaraujo", "123456");
	}

	@Test(priority = 51)
	public void createRecess() throws Exception {

		this.loadDataFromFileAndValidateResult("/loadData/DevPart03/createRecess.groovy", new ArrayList<String>() {
			{
				add("true");
				add("br.com.careman.domain.Recess : 2");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.Recess : 3");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.Recess : 4");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.Recess : 5");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.Recess : 6");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});

	}

	@Test(priority = 52)
	public void createActivity() throws Exception {

		this.loadDataFromFileAndValidateResult("/loadData/DevPart03/createActivity.groovy", new ArrayList<String>() {
			{
				add("true");
				add("Troca de Componente");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Troca de Parte");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Reparo");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});

	}

	@Test(priority = 53)
	public void importProcessBanrisulFromTo() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl() + "/importProcessFromTo");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("CONTRACTOR");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("1");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("2");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("2");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("1");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("3");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("3");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("4");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("2");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("6");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("7");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("7");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("4");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("8");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("4");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("9");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("1");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("STEP_STATUS");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("1");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("6");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("STEP_STATUS");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("2");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("7");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("STEP_STATUS");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("3");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("8");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("5");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("2");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("6");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("3");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("9");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("4");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("10");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("5");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("16");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("3");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("21");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("6");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("27");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("7");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("28");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("8");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("35");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("9");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("11");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("2");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("12");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("3");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("13");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("4");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("14");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("5");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("15");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("6");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("16");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("7");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("17");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("8");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("18");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("9");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("19");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("10");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("30");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("11");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("31");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("12");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("32");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("13");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("33");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("14");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("34");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("15");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("35");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("16");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("36");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("17");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("41");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("18");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("42");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("19");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		getDriver().findElement(By.id("fromId")).clear();
		getDriver().findElement(By.id("fromId")).sendKeys("99");
		getDriver().findElement(By.id("toId")).clear();
		getDriver().findElement(By.id("toId")).sendKeys("20");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
	}

	@Test(priority = 54)
	public void createQAEvaluation() throws Exception {

		this.loadDataFromFileAndValidateResult("/loadData/DevPart03/createQAEvaluation.groovy", new ArrayList<String>() {
			{
				add("true");
				add("br.com.careman.domain.QualityEvaluation : 1");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.QualityEvaluation : 2");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.QualityEvaluation : 3");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});

	}

	@Test(priority = 55)
	public void createProfileSettings() throws Exception {

		this.loadDataFromFileAndValidateResult("/loadData/DevPart03/createProfileSettings.groovy", new ArrayList<String>() {
			{
				add("true");
				add("br.com.careman.domain.ProfileSettings : 1");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 2");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 3");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 4");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 5");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 6");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 7");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 8");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 9");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 10");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("br.com.careman.domain.ProfileSettings : 11");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});

	}

	@Test(priority = 56)
	public void createCarrier() throws Exception {

		// Indo para tela que tem o menu....
		getDriver().get(this.getBaseUrl());

		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logistic"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Carrier"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Transportadora Braspress");
		getDriver().findElement(By.id("alias")).clear();
		getDriver().findElement(By.id("alias")).sendKeys("Braspress");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		getDriver().findElement(By.id("phoneNumber")).clear();
		getDriver().findElement(By.id("phoneNumber")).sendKeys("553166559944");
		getDriver().findElement(By.id("contactName")).clear();
		getDriver().findElement(By.id("contactName")).sendKeys("Alejandro");
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
	}

	@Test(priority = 57)
	public void createServiceAreas() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			getDriver().findElement(By.linkText("Service Areas"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Service Areas"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("São Paulo");
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).clear();
		getDriver().findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys("São Paul");
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		getDriver().findElement(By.id("form_add_state_button")).click();
		// Warning: verifyTextPresent may require manual changes
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[2]"), "São Paulo"));
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			getDriver().findElement(By.linkText("Service Areas"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Service Areas"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Rio Grande do Sul + Guarulhos");
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).clear();
		getDriver().findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys("Rio Grande do S");
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		getDriver().findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		getDriver().findElement(By.id("form_add_state_button")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[2]"), "Rio Grande do Sul"));
		new Select(getDriver().findElement(By.id("state_id"))).selectByVisibleText("São Paulo");
		getDriver().findElement(By.id("autocomplete_city")).clear();
		getDriver().findElement(By.id("autocomplete_city")).sendKeys("Guarulh");
		Thread.sleep(1500);
		getDriver().findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		getDriver().findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		getDriver().findElement(By.id("form_add_city_button")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div[3]/div[2]/form/div[3]/div/table/tbody/tr/td[2]"), "Guarulhos"));

	}

	@Test(priority = 58)
	public void createShipmentOrderType() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl() + "/shipmentOrderType/index");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Null -> Contratante (GOOD)");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples GOOD");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Null -> Service Provider (GOOD)");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples GOOD");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Service Provider -> Contratante (GOOD)");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples GOOD");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Null -> Contratante (BAD)");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples BAD");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Null -> Service Provider (BAD)");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples BAD");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Service Provider -> Contratante (BAD)");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples BAD");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Service Provider -> Service Provider");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Complexa");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Ingresso de Equipamentos - BAD");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Ingresso de Equipamentos - BAD");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Ordens de Entrega - GOOD");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Ordens de Entrega - GOOD");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowNewAccessories")).click();
		getDriver().findElement(By.id("allowExistingComponents")).click();
		getDriver().findElement(By.id("allowNewComponents")).click();
		getDriver().findElement(By.id("allowExistingParts")).click();
		getDriver().findElement(By.id("allowNewParts")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.id("allowNewProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Remessa Prestador para TEFTI");
		new Select(getDriver().findElement(By.id("workflow.id"))).selectByVisibleText("Remessa Prestador para TEFTI");
		getDriver().findElement(By.id("allowExistingAccessories")).click();
		getDriver().findElement(By.id("allowExistingProducts")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
	}

	@Test(priority = 59)
	public void adicionandoShipmentOrderTypeParaPrestadores() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			getDriver().findElement(By.linkText("Service Provider"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Service Provider"))).click();
		try {
			getDriver().findElement(By.id("alias")).clear();
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		}
		getDriver().findElement(By.id("alias")).clear();
		getDriver().findElement(By.id("alias")).sendKeys("Help Desk");
		getDriver().findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//table[@id='resultList']/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		getDriver().get(this.getBaseUrl() + "/serviceProvider/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		getDriver().findElement(By.id("alias")).clear();
		getDriver().findElement(By.id("alias")).sendKeys("TEFTI Barueri");
		getDriver().findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//table[@id='resultList']/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Null -> Contratante (BAD)");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Null -> Contratante (GOOD)");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Null -> Service Provider (BAD)");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Null -> Service Provider (GOOD)");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Contratante (BAD)");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Contratante (GOOD)");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Ingresso de Equipamentos - BAD");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Ordens de Entrega - GOOD");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Remessa Prestador para TEFTI");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		getDriver().get(this.getBaseUrl() + "/serviceProvider/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		getDriver().findElement(By.id("alias")).clear();
		getDriver().findElement(By.id("alias")).sendKeys("Vortex");
		getDriver().findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//table[@id='resultList']/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(1000);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Ordens de Entrega - GOOD");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		getDriver().get(this.getBaseUrl() + "/serviceProvider/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		getDriver().findElement(By.id("alias")).clear();
		getDriver().findElement(By.id("alias")).sendKeys("Smart");
		getDriver().findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//table[@id='resultList']/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
		getDriver().get(this.getBaseUrl() + "/serviceProvider/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		getDriver().findElement(By.id("alias")).clear();
		getDriver().findElement(By.id("alias")).sendKeys("Smartf1");
		getDriver().findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("//table[@id='resultList']/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(getDriver().findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		getDriver().findElement(By.id("add_shipment_order_types_button")).click();
	}

	@Test(priority = 60)
	public void test28createRepairLevel() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl());

		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			getDriver().findElement(By.linkText("Repair Level"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Repair Level"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("A");
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("B");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
	}

	@Test(priority = 61)
	public void createOtherUsersByAdm() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		// * ADMINISTRADOR
		// Administrador (OK)
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		getDriver().findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("dfmalafaia");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Diego Malafaia");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("diego.malafaia@avatek.com.br");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Administrator");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.linkText("Select All")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Supervisor HelpDesk (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("supervisor");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("supervisor");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("supervisor@helpdesk.com");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Supervisor");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Help Desk");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_TAB")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_CREATE")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_PRODUCTION_PLANNING")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_DELETE")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		getDriver().findElement(By.id("ROLE_USER")).click();
		getDriver().findElement(By.id("ROLE_USER_PERMISSION")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Supervisor TEFTI (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("sup_tefti");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Supervisor TEFTI");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("supervisor@tefti.com");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Supervisor");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("TEFTI Barueri");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_TAB")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_CREATE")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_PRODUCTION_PLANNING")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_DELETE")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		getDriver().findElement(By.id("ROLE_USER")).click();
		getDriver().findElement(By.id("ROLE_USER_PERMISSION")).click();

		// itens comentados, ver ->
		// https://avalon.atlassian.net/browse/OSTENANT-1743
		// getDriver().findElement(By.id("ROLE_IMPORT_PROCESS_BANRISUL")).click();
		// //
		// getDriver().findElement(By.id("ROLE_IMPORT_PROCESS")).click(); //
		// getDriver().findElement(By.id("ROLE_SERVICE_ORDER_ADMIN")).click();
		// //
		// getDriver().findElement(By.id("ROLE_EQUIPMENT_ADMIN")).click(); //
		// getDriver().findElement(By.id("ROLE_EXPORT_BANRISUL")).click(); //
		// getDriver().findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		// //
		// getDriver().findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		// //

		getDriver().findElement(By.id("ROLE_CUSTOMER")).click();
		getDriver().findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Service Provider Supervisor (Tenant) (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("sps_tefti");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("SP Supervisor TEFTI");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("sp_supervisor@tefti.com");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Service Provider Supervisor");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("TEFTI Barueri");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();

		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_TAB")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		getDriver().findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		getDriver().findElement(By.id("ROLE_USER")).click();
		getDriver().findElement(By.id("ROLE_USER_PERMISSION")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Service Provider Supervisor (Não Tenant) (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("sps_smart");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("sp_supervisor_smart");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("sp_supervisor@smart.com");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Service Provider Supervisor");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Smart");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_TAB")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		getDriver().findElement(By.id("ROLE_USER")).click();
		getDriver().findElement(By.id("ROLE_USER_PERMISSION")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		// Warning: verifyTextPresent may require manual changes
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Supervisor de Laboratorio (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("adm_lab");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Adm_Lab");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("adm_lab@vortex7.com.ar");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Laboratory Supervisor");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_CONTRACTOR")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		getDriver().findElement(By.id("ROLE_QUALITY_EVALUATION")).click();
		getDriver().findElement(By.id("ROLE_DELIVER_PIECES")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("ROLE_PRODUCTION_PLANNING")).click();
		getDriver().findElement(By.id("ROLE_REQUEST_PIECES")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_DELETE")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT_ADMIN")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		getDriver().findElement(By.id("ROLE_USER")).click();
		getDriver().findElement(By.id("ROLE_USER_PERMISSION")).click();
		getDriver().findElement(By.id("ROLE_CONSUMPTION_REPORT")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Contratante Banrisul (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("contratante");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Contratante Banrisul");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("contratante@banrisul.com.br");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Contractor");
		new Select(getDriver().findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes

		getDriver().findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_ONLY_VIEW")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		// Warning: verifyTextPresent may require manual changes

		// Logistic Techniccian Vortex (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("lt_vortex");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Logistic Technician Vortex");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("log_tech@vortex.com.br");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Logistic Technician");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes

		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("ROLE_REQUEST_PIECES")).click();
		getDriver().findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		getDriver().findElement(By.id("ROLE_LOCATION")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_DELIVER_PIECES")).click();
		getDriver().findElement(By.id("ROLE_IMPORT_PROCESS")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		// Warning: verifyTextPresent may require manual changes
		// Logistic Technician TEFTI (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("lt_tefti");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Logistic Technician TEFTI");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("log_tech@tefti.com.br");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Logistic Technician");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("TEFTI Barueri");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("ROLE_REQUEST_PIECES")).click();
		getDriver().findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		getDriver().findElement(By.id("ROLE_LOCATION")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_DELIVER_PIECES")).click();
		getDriver().findElement(By.id("ROLE_IMPORT_PROCESS")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Logistic Technician Smart (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("lt_smart");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Logistic Technician SMART");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("log_tech@smart.com.br");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Logistic Technician");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Smart");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("ROLE_REQUEST_PIECES")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		getDriver().findElement(By.id("ROLE_LOCATION")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_DELIVER_PIECES")).click();
		getDriver().findElement(By.id("ROLE_IMPORT_PROCESS")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		logout(getDriver());
		// ** SERVICE PROVIDER NAO TENANT (SMART)
		// Service Provider Attendant Smart (OK)
	}

	@Test(priority = 62)
	public void createOtherUsersBySpsSmart() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl() + "/logout/");
		loginOld(getDriver(), "sps_smart", "123456");
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		getDriver().findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("spa_smart");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Service Provider Attendant Smart");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("spa@smart.com.br");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Service Provider Attendant");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Service Provider Technician Smart (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("spt_smart");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Service Provider technician Smart");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("spt@smart.com.br");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Tecnico Mobile
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("tec_mob");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Técnico Mobile");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Tecnico Campo TEFTI 1 (OK)
		logout(getDriver());
		// *** SERVICE PROVIDER TENANT (TEFTI) (OK)
		// Tecnico Mobile
	}

	@Test(priority = 62)
	public void createOtherUsersBySpsTEFTI() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl() + "/logout/");
		loginOld(getDriver(), "sps_tefti", "123456");
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		getDriver().findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("tec_tefti1");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Técnico Campo Tefti 1");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		getDriver().findElement(By.id("btnSavePermission")).click();
		// Tecnico Campo TEFTI 2 (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("tec_tefti2");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Técnico Campo Tefti 2");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		logout(getDriver());
		// **** Help Desk
		// Atendente Help Desk (OK)
	}

	@Test(priority = 63)
	public void createOtherUsersBySupervisor() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl() + "/logout/");
		loginOld(getDriver(), "supervisor", "123456");
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		getDriver().findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("atendente");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Atendente Help Desk");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("atendente@helpdesk.com");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Attendant");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Help Desk");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_CUSTOMER")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_CREATE")).click();
		getDriver().findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		getDriver().findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		logout(getDriver());
	}

	@Test(priority = 64)
	public void createOtherUsersByLaboratorySupervisor() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		// Laboratory Technician 1 (OK)
		getDriver().get(this.getBaseUrl() + "/logout/");
		loginOld(getDriver(), "adm_lab", "123456");
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		getDriver().findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("tec_lab1");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Técnico de Laboratório 1");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("tec_lab@vortex7.com.ar");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Laboratory Technician");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_DELIVER_PIECES")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("ROLE_CONSUMPTION_REPORT")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("ROLE_REQUEST_PIECES")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Laboratory Technician 2 (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("tec_lab2");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Técnico de Laboratório 2");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("tec_lab@vortex8.com.ar");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Laboratory Technician");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_DELIVER_PIECES")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("ROLE_CONSUMPTION_REPORT")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("ROLE_REQUEST_PIECES")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_EQUIPMENT")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Quality Technician (OK)
		getDriver().findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("qa");
		getDriver().findElement(By.id("newPassword")).clear();
		getDriver().findElement(By.id("newPassword")).sendKeys("123456");
		getDriver().findElement(By.id("repeatNewPassword")).clear();
		getDriver().findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("QA");
		getDriver().findElement(By.id("email")).clear();
		getDriver().findElement(By.id("email")).sendKeys("qa@vortex7.com.ar");
		new Select(getDriver().findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(getDriver().findElement(By.id("profile"))).selectByVisibleText("Quality Technician");
		new Select(getDriver().findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		getDriver().findElement(By.id("ROLE_QUALITY_EVALUATION")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		getDriver().findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		getDriver().findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		getDriver().findElement(By.id("ROLE_CONSUMPTION_REPORT")).click();
		getDriver().findElement(By.id("ROLE_ATTACHMENT")).click();
		getDriver().findElement(By.id("ROLE_REPAIR_ORDER")).click();
		getDriver().findElement(By.id("ROLE_STOCK")).click();
		getDriver().findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		logout(getDriver());
	}

	@Test(priority = 65)
	public void createSecretQuestion() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		getDriver().get(this.getBaseUrl() + "/logout");
		getDriver().get(this.getBaseUrl() + "/login/auth");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgotten password"))).click();
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("Usuario");
		getDriver().findElement(By.xpath("//button[@type='submit']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div[3]/div/div/div/div/div/div/div[2]/h4"), "User not found"));
		getDriver().findElement(By.id("username")).clear();
		getDriver().findElement(By.id("username")).sendKeys("dfmalafaia");
		getDriver().findElement(By.xpath("//button[@type='submit']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div[3]/div/div/div/div/div/div/div[2]/h4"), "User do not have a secret question"));
		// /html/body/div[2]/div[3]/div/div/div/div/div/div/div[2]/h4
		getDriver().get(this.getBaseUrl() + "/login/auth");
		loginOld(getDriver(), "dfmalafaia", "123456");
		getDriver().get(this.getBaseUrl() + "/profile/changeSecretPhrase");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).clear();
		getDriver().findElement(By.id("password")).sendKeys("1234567");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("secretQuestion"))).clear();
		getDriver().findElement(By.id("secretQuestion")).sendKeys("Whats Your Name?");
		getDriver().findElement(By.id("secretAnswer")).clear();
		getDriver().findElement(By.id("secretAnswer")).sendKeys("Diego");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/form/div[1]/div[1]/div/div/div/div[2]/div/li"),
				"[Current Password] is invalid"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).clear();
		getDriver().findElement(By.id("password")).sendKeys("123456");
		getDriver().findElement(By.id("secretQuestion")).clear();
		getDriver().findElement(By.id("secretQuestion")).sendKeys("Whats Your Name?");
		getDriver().findElement(By.id("secretAnswer")).clear();
		getDriver().findElement(By.id("secretAnswer")).sendKeys("Diego");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		logout(getDriver());
	}
}
