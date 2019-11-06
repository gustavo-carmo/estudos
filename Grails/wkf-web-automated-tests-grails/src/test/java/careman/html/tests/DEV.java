package careman.html.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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
import careman.html.TestSuiteHelper;

public class DEV extends TestBase {

	private TestSuiteHelper testSuiteHelper;
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	// private Selenium selenium;
	public String table_id;
	public String column;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		this.testSuiteHelper = new TestSuiteHelper(baseURL, gridURL);
		this.driver = testSuiteHelper.getWebDriver();
		// driver.manage().window().setSize(new Dimension(1920, 1080));
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	@Test(priority = 1)
	public void doLogin() throws Exception {
		testSuiteHelper.login(driver, "sidneyaraujo", "123456");
	}

    @Test(priority = 2)
    public void createDocumentType() throws Exception {
        System.out.println("DEV createDocumentType iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        driver.findElement(By.cssSelector("i.fa.fa-wrench")).click();
        driver.findElement(By.id("edit_13")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("form_documentTypes_button")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CPF");
        driver.findElement(By.id("form_documentTypes_button")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CUIT");
        driver.findElement(By.id("form_documentTypes_button")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
        testSuiteHelper.validateMessageSuccess("updated");
        driver.findElement(By.id("edit_14")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("form_documentTypes_button")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CPF");
        driver.findElement(By.id("form_documentTypes_button")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CUIT");
        driver.findElement(By.id("form_documentTypes_button")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
        testSuiteHelper.validateMessageSuccess("updated");
        driver.findElement(By.id("edit_15")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("form_documentTypes_button")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CPF");
        driver.findElement(By.id("form_documentTypes_button")).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CUIT");
        driver.findElement(By.id("form_documentTypes_button")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
        testSuiteHelper.validateMessageSuccess("updated");
        System.out.println("DEV createDocumentType finalizado");
    }

    @Test(priority = 2)
    public void createOpeningHours() throws Exception {
        System.out.println("DEV OpeningHours iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Opening Hours"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys("08:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys("18:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("dayOfWeek")));
        new Select(driver.findElement(By.id("dayOfWeek"))).selectByVisibleText("Monday");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
        testSuiteHelper.validateMessageSuccess("created");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_index"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("startAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys("08:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys("18:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("dayOfWeek")));
        new Select(driver.findElement(By.id("dayOfWeek"))).selectByVisibleText("Tuesday");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
        testSuiteHelper.validateMessageSuccess("created");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_index"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("startAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys("08:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys("18:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("dayOfWeek")));
        new Select(driver.findElement(By.id("dayOfWeek"))).selectByVisibleText("Wednesday");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
        testSuiteHelper.validateMessageSuccess("created");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_index"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("startAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys("08:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys("18:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("dayOfWeek")));
        new Select(driver.findElement(By.id("dayOfWeek"))).selectByVisibleText("Thursday");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_index"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("startAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys("08:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("startAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys("18:00");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("endAt_timepicker"))).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("dayOfWeek")));
        new Select(driver.findElement(By.id("dayOfWeek"))).selectByVisibleText("Friday");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV OpeningHours finalizado");
    }

    @Test(priority = 3)
    public void createOpeningHoursGroup() throws Exception {
        System.out.println("DEV OpeningHoursGroup iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        // OpeningHoursGroup
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Opening Hours Groups")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Opening Hours Groups"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("MON-FRI 08h/18h");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("checkUncheckAll"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV OpeningHoursGroup finalizado");
    }

    @Test(priority = 4)
    public void createServiceGroup() throws Exception {
        System.out.println("DEV ServiceGroup iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Group")));
        driver.findElement(By.linkText("Service Group")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Instalação");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Manutenção");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Desinstalação");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Reconfiguração");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Cancelamento");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Troca de Tecnologia");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Reincidência");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Carga de Software");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Reparacion en Laboratorio");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.LABORATORY.toString());
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Rollout");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.FIELD.toString());
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV ServiceGroup finalizado");

    }

    @Test(priority = 5)
    public void createCustomFieldCodRed() throws Exception {
        System.out.println("DEV CustomFieldCodRed iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Custom Fields"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.id("custom_field_name")).clear();
        driver.findElement(By.id("custom_field_name")).sendKeys("Cód. Rede");
        new Select(driver.findElement(By.id("custom_field_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("custom_field_type"))).selectByVisibleText("Equipment");
        new Select(driver.findElement(By.id("custom_field_kind"))).selectByVisibleText("Regex");
        driver.findElement(By.id("custom_field_pattern")).clear();
        driver.findElement(By.id("custom_field_pattern")).sendKeys("\\d{11}");
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV CustomFieldCodRed finalizado");
    }

    @Test(priority = 6)
    public void createCustomFieldCodEstabelecimento() throws Exception {
        System.out.println("DEV CustomFieldCodEstabelecimento iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Custom Fields"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.id("custom_field_name")).clear();
        driver.findElement(By.id("custom_field_name")).sendKeys("Cód. Estabelecimento");
        new Select(driver.findElement(By.id("custom_field_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("custom_field_type"))).selectByVisibleText("Equipment");
        new Select(driver.findElement(By.id("custom_field_kind"))).selectByVisibleText("Regex");
        driver.findElement(By.id("custom_field_pattern")).clear();
        driver.findElement(By.id("custom_field_pattern")).sendKeys("\\d{15}");
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV CustomFieldCodEstabelecimento finalizado");
    }

    @Test(priority = 7)
    public void createCustomFieldNumeroAtendimento() throws Exception {
        System.out.println("DEV CustomFieldNumeroAtendimento iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Custom Fields"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.id("custom_field_name")).clear();
        driver.findElement(By.id("custom_field_name")).sendKeys("Número Atendimento");
        new Select(driver.findElement(By.id("custom_field_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("custom_field_type"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys("Reinci");
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//html/body/div[2]/div/div/div[1]/div/form/div[1]/div/div[5]/div/div/div[3]/div"))).click();
        new Select(driver.findElement(By.id("custom_field_kind"))).selectByVisibleText("Regex");
        driver.findElement(By.id("custom_field_pattern")).clear();
        driver.findElement(By.id("custom_field_pattern")).sendKeys("\\d{5}");
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV CustomFieldNumeroAtendimento finalizado");
    }

    @Test(priority = 8)
    public void createCustomFieldNumeroDaReincidencia() throws Exception {
        System.out.println("DEV CustomFieldNumeroDaReincidencia iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Custom Fields"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.id("custom_field_name")).clear();
        driver.findElement(By.id("custom_field_name")).sendKeys("Número da Reincidência");
        new Select(driver.findElement(By.id("custom_field_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("custom_field_type"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys("Reinci");
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//html/body/div[2]/div/div/div[1]/div/form/div[1]/div/div[5]/div/div/div[3]/div"))).click();
        new Select(driver.findElement(By.id("custom_field_kind"))).selectByVisibleText("List");
        driver.findElement(By.id("custom_field_item_name")).clear();
        driver.findElement(By.id("custom_field_item_name")).sendKeys("0");
        driver.findElement(By.id("custom_field_items_add")).click();
        driver.findElement(By.id("custom_field_item_name")).clear();
        driver.findElement(By.id("custom_field_item_name")).sendKeys("1");
        driver.findElement(By.id("custom_field_items_add")).click();
        driver.findElement(By.id("custom_field_item_name")).clear();
        driver.findElement(By.id("custom_field_item_name")).sendKeys("2");
        driver.findElement(By.id("custom_field_items_add")).click();
        driver.findElement(By.id("custom_field_item_name")).clear();
        driver.findElement(By.id("custom_field_item_name")).sendKeys("3");
        driver.findElement(By.id("custom_field_items_add")).click();
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV CustomFieldNumeroDaReincidencia finalizado");

    }

    @Test(priority = 9)
    public void createCustomFieldTipoDeOS() throws Exception {
        // Criando custom fields para o teste de validação de campos
        // customizados do tipo ordem de serviço
        System.out.println("DEV CustomFieldTipoDeOS iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Custom Fields"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.id("custom_field_name")).clear();
        driver.findElement(By.id("custom_field_name")).sendKeys("Tipo de Ordem de Serviço");
        new Select(driver.findElement(By.id("custom_field_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("custom_field_type"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys("Manuten");
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//html/body/div[2]/div/div/div[1]/div/form/div[1]/div/div[5]/div/div/div[3]/div"))).click();
        new Select(driver.findElement(By.id("custom_field_kind"))).selectByVisibleText("List");
        driver.findElement(By.id("custom_field_item_name")).clear();
        driver.findElement(By.id("custom_field_item_name")).sendKeys(Messages.LABORATORY.toString());
        driver.findElement(By.id("custom_field_items_add")).click();
        driver.findElement(By.id("custom_field_item_name")).clear();
        driver.findElement(By.id("custom_field_item_name")).sendKeys(Messages.FIELD.toString());
        driver.findElement(By.id("custom_field_items_add")).click();
        driver.findElement(By.id("custom_field_item_name")).clear();
        driver.findElement(By.id("custom_field_item_name")).sendKeys("Help Desk");
        driver.findElement(By.id("custom_field_items_add")).click();
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV CustomFieldTipoDeOS finalizado");
    }

    @Test(priority = 10)
    public void createCustomFieldNumeroDoChamadoPai() throws Exception {
        System.out.println("DEV CustomFieldNumeroDoChamadoPai iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Custom Fields"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.id("custom_field_name")).clear();
        driver.findElement(By.id("custom_field_name")).sendKeys("Numero do chamado Pai");
        new Select(driver.findElement(By.id("custom_field_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("custom_field_type"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys("Manuten");
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//html/body/div[2]/div/div/div[1]/div/form/div[1]/div/div[5]/div/div/div[3]/div"))).click();
        new Select(driver.findElement(By.id("custom_field_kind"))).selectByVisibleText("Regex");
        driver.findElement(By.id("custom_field_pattern")).clear();
        driver.findElement(By.id("custom_field_pattern")).sendKeys("\\d{5,10}");
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV CustomFieldNumeroDoChamadoPai finalizado");
    }

    @Test(priority = 11)
    public void createCustomFieldNumeroDoRg() throws Exception {
        System.out.println("DEV CustomFieldNumeroDoRg iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Custom Fields"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.id("custom_field_name")).clear();
        driver.findElement(By.id("custom_field_name")).sendKeys("Número do RG");
        new Select(driver.findElement(By.id("custom_field_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("custom_field_type"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys("Manuten");
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//html/body/div[2]/div/div/div[1]/div/form/div[1]/div/div[5]/div/div/div[3]/div"))).click();
        new Select(driver.findElement(By.id("custom_field_kind"))).selectByVisibleText("Regex");
        driver.findElement(By.id("custom_field_pattern")).clear();
        driver.findElement(By.id("custom_field_pattern")).sendKeys("^\\s*([^\\s]\\s*){8,12}$");
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV CustomFieldNumeroDoRg finalizado");
    }

    @Test(priority = 12)
    public void createCustomFieldNumeroDoCPF() throws Exception {
        System.out.println("DEV CustomFieldNumeroDoCPF iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Custom Fields"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.id("custom_field_name")).clear();
        driver.findElement(By.id("custom_field_name")).sendKeys("Número do CPF");
        new Select(driver.findElement(By.id("custom_field_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("custom_field_type"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys("Manuten");
        driver.findElement(By.cssSelector("div.ms-sel-ctn > input[type=\"text\"]")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//html/body/div[2]/div/div/div[1]/div/form/div[1]/div/div[5]/div/div/div[3]/div"))).click();
        new Select(driver.findElement(By.id("custom_field_kind"))).selectByVisibleText("Regex");
        driver.findElement(By.id("custom_field_pattern")).clear();
        driver.findElement(By.id("custom_field_pattern")).sendKeys("\\d{11}");
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV CustomFieldNumeroDoCPF finalizado");
    }

    @Test(priority = 13)
    public void createContractorBanrisul() throws Exception {
        System.out.println("DEV ContractorBanrisul iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contractor"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("92702067000196");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("BANRISUL");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("TEFTI");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Banco do Estado do Rio Grande do Sul S/A");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("tefti@tefti.com.br");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("5153130000");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Rua Capitão Montanha");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("77");
        driver.findElement(By.id("address.complement")).clear();
        driver.findElement(By.id("address.complement")).sendKeys("");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("Centro");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("90010-040");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Rio Grande do Su");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Porto");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.id("add_custom_field")).click();
        Thread.sleep(2000);
        new Select(driver.findElement(By.id("custom_field_specification_customField")))
                .selectByVisibleText("[EQUIPMENT] Cód. Rede -> \\d{11}");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("custom_field_specification_required"))).click();
        driver.findElement(By.id("custom_field_specification_modal_btn_save")).click();
        // Warning: verifyTextPresent may require manual changes
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("add_custom_field"))).click();
        Thread.sleep(3000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("custom_field_specification_customField")));
        new Select(driver.findElement(By.id("custom_field_specification_customField")))
                .selectByVisibleText("[EQUIPMENT] Cód. Estabelecimento -> \\d{15}");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("custom_field_specification_required"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("custom_field_specification_modal_btn_save")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("_action_addContract")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("openingHoursGroup"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("updated");
        System.out.println("DEV ContractorBanrisul finalizado");

    }

    @Test(priority = 14)
    public void createContractorPOSNET() throws Exception {
        System.out.println("DEV ContractorPOSNET iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contractor"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CUIT");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("30620177497");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("POSNET");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Sindatospos");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("POSNET SRL");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("sd@posnet.com");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("541156278900");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Peru");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("143");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("CABA");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("C1006ACG");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Argentin");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Buenos");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("La Plat");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_addContract")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("openingHoursGroup"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("updated");
        System.out.println("DEV ContractorPOSNET finalizado");
    }

    @Test(priority = 15)
    public void createContractorCABAL() throws Exception {
        System.out.println("DEV ContractorCABAL iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contractor"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CUIT");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("30-65436422-9");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("CABAL");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Cabal");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("CABAL C.L.");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("sd@cabal.com");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("541156278900");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Lavalle");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("341");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("CABA");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("C1006ACG");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Argen");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Buenos");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("La Plat");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();

        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_addContract")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("openingHoursGroup"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("updated");
        System.out.println("DEV ContractorCABAL finalizado");
    }

    @Test(priority = 16)
    public void createContractorTICKET() throws Exception {
        System.out.println("DEV ContractorTICKET iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contractor"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("47.866.934/0001-74");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("TICKET");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Marco");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("TICKET SERVIÇOS SA");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("preencher@tefti.com.br");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("1155555555");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Rua Bela Cintra");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("1149");
        driver.findElement(By.id("address.complement")).clear();
        driver.findElement(By.id("address.complement")).sendKeys("5º Andar");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("Consolação");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("01415-001");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("São");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("São Pa");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_addContract")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("openingHoursGroup"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("updated");
        System.out.println("DEV ContractorTICKET finalizado");

    }

    @Test(priority = 17)
    public void createContractorBanrisulFilial() throws Exception {
        System.out.println("DEV ContractorBanrisulFilial iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contractor"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("isHeadquarters"))).selectByVisibleText("Affiliate");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("headquarter")));
        new Select(driver.findElement(By.id("headquarter"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("00398022000232");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("BANRISUL Filial SP");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Banrisul Paulista");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Banco do Estado do Rio Grande do Sul S/A");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("barisulsp@barisulsp.com.br");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("511121881515");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Avenida Paulista");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("2381");
        driver.findElement(By.id("address.complement")).clear();
        driver.findElement(By.id("address.complement")).sendKeys("");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("Consolação");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("01311300");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("São Pau");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("São Pau");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV ContractorBanrisulFilial finalizado");

    }

    @Test(priority = 18)
    public void createServiceProviderHelpDesk() throws Exception {
        System.out.println("DEV ProviderHelpDesk iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Provider")));
        driver.findElement(By.linkText("Service Provider")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("58454463000103");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("Help Desk");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Silvio");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Novtis do Brasil");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("silvio.alexandre@novtis.com.br");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("113588-9090");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Help Desk");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Avenida Ibirapuera");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("2332");
        driver.findElement(By.id("address.complement")).clear();
        driver.findElement(By.id("address.complement")).sendKeys("");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("Moema");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("04028-002");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("São");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("São Pa");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("serviceGroup")));
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Cancelamento");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Carga de Software");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Desinstalação");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Instalação");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Manutenção");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Reconfiguração");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Reincidência");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Troca de Tecnologia");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("BANRISUL");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("POSNET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("CABAL");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("TICKET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        System.out.println("DEV ProviderHelpDesk finalizado");
    }

    @Test(priority = 19)
    public void createServiceProviderTEFTIBarueri() throws Exception {
        System.out.println("DEV ServiceProviderTEFTIBarueri iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Provider")));
        driver.findElement(By.linkText("Service Provider")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("07.697.245/0001-69");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("TEFTI Barueri");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Sidney Araujo");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("TEFTI Barueri");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("sidney.araujo@tefti.com.br");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("114133-4099");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.FIELD.toString());
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Alameda Juari");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("485");
        driver.findElement(By.id("address.complement")).clear();
        driver.findElement(By.id("address.complement")).sendKeys("Centro Empresarial Tamboré");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("Tamboré");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("06460-090");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("São");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Baru");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By
                        .xpath("/html/body/div[2]/div/div/div[1]/div/form/div[1]/div[2]/div[3]/div/div/label[1]"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Cancelamento");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Carga de Software");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Desinstalação");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Instalação");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Manutenção");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Reconfiguração");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Reincidência");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Troca de Tecnologia");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("BANRISUL");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("POSNET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("CABAL");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(800);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("TICKET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        System.out.println("DEV ServiceProviderTEFTIBarueri finalizado");
    }

    @Test(priority = 20)
    public void createServiceProviderVortex() throws Exception {
        System.out.println("DEV ServiceProviderVortex iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Provider")));
        driver.findElement(By.linkText("Service Provider")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CUIT");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("30709395382");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("Vortex");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Vortex");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Vortex7 S.A.");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("info@vortex7.com.ar");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.LABORATORY.toString());
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("541147817700");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Manuel Ugarte");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("1650");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("Centro");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("C1428BRD");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Argen");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Buenos");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Buenos");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(By
                        .xpath("/html/body/div[2]/div/div/div[1]/div/form/div[1]/div[2]/div[3]/div/div/label[1]"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Reparacion en Laboratorio");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("POSNET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("CABAL");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("TICKET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        System.out.println("DEV ServiceProviderVortex finalizado");
    }

    @Test(priority = 21)
    public void createServiceProviderSmart() throws Exception {
        System.out.println("DEV ServiceProviderSmart iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Provider")));
        driver.findElement(By.linkText("Service Provider")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("83488184000108");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("Smart");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Smart");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Smart");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Smart");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("info@smart.com.ar");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.FIELD.toString());
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("541132636670");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Avenida Corrientes");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("2150");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("Centro");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("C1043ABN");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Argen");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Buenos");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Buenos");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Instalação");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Manutenção");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Desinstalação");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Rollout");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Troca de Tecnologia");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Reconfiguração");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Cancelamento");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("POSNET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("CABAL");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("TICKET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(500);
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("BANRISUL");
        driver.findElement(By.id("form_add_contractors_button")).click();
        System.out.println("DEV ServiceProviderSmart finalizado");
    }

    @Test(priority = 22)
    public void createServiceProviderSmartF1() throws Exception {
        System.out.println("DEV ServiceProviderSmartF1 iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Provider")));
        driver.findElement(By.linkText("Service Provider")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        driver.findElement(By.xpath("//div[@id='page-content']/div/form/div/div/div/div/div/label[2]")).click();
        new Select(driver.findElement(By.id("headquarters.id"))).selectByVisibleText("Smart");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.xpath("//div[@id='page-content']/div/form/div/div[2]/div[4]/div/div/label")).click();
        // driver.findElement(By.id("hasOwnStock")).click();
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("54583675000130");
        driver.findElement(By.id("alias")).clear();
        driver.findElement(By.id("alias")).sendKeys("Smartf1");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Smartf1");
        driver.findElement(By.id("contactName")).clear();
        driver.findElement(By.id("contactName")).sendKeys("Smartf1");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("info@smartf1.com.ar");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.FIELD.toString());
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("541156335568");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("Avenida Corrientes");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("1020");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("Centro");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("C1253ACD");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Argen");
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[1]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Buenos");
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/ul[2]/li/a"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Buenos");
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//html/body/ul[3]/li/a"))).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        new Select(driver.findElement(By.id("serviceGroup"))).selectByVisibleText("Reparacion en Laboratorio");
        driver.findElement(By.id("form_add_serviceGroup_button")).click();
        Thread.sleep(750);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("contractor")));
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("POSNET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(750);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("contractor")));
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("CABAL");
        driver.findElement(By.id("form_add_contractors_button")).click();
        Thread.sleep(750);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("contractor")));
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("TICKET");
        driver.findElement(By.id("form_add_contractors_button")).click();
        System.out.println("DEV ServiceProviderSmartF1 finalizado");
    }

    @Test(priority = 23)
    public void createCustomerDILCEU() throws Exception {
        System.out.println("DEV Customer iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys(Keys.chord("91728196000190"));
        driver.findElement(By.id("customer_contactName")).clear();
        driver.findElement(By.id("customer_contactName")).sendKeys("DILCEU");
        driver.findElement(By.id("customer_alias")).clear();
        driver.findElement(By.id("customer_alias")).sendKeys("DILCEU JOSE COSTANTIN ME");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("DILCEU JOSE COSTANTIN ME");
        driver.findElement(By.id("customer_email")).clear();
        driver.findElement(By.id("customer_email")).sendKeys("teste@tefti.com.br");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("5133158567");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA PEDRO SOUZA");
        driver.findElement(By.id("address.number")).clear();
        driver.findElement(By.id("address.number")).sendKeys("248");
        driver.findElement(By.id("address.district")).clear();
        driver.findElement(By.id("address.district")).sendKeys("JOAO PESSOA");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91520-130");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();
        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Brasi");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Rio Gra");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Porto");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();
        System.out.println("DEV Customer finalizado");
    }

    @Test(priority = 24)
    public void createCustomerMariaCeilandia() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("12.282.122/0001-05");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Maria Ceilândia");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("5133158561");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 2");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91520-131");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Distri");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Cei");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerArnaldoPlanaltina() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("56.052.444/0001-25");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Arnaldo Planaltina");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("12341234");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 3");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91525-555");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Goiás");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Pla");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();
    }

    @Test(priority = 24)
    public void createCustomerAntoniaPontalina() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("29.788.521/0001-37");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Antônia Pontalina");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("5133158563");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 5");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Goi");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Pont");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerMauraBonito() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("28.454.484/0001-68");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Maura Bonito");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("43214321");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 6");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Mato Grosso do S");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Bon");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerPaulo() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("77.766.829/0001-24");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Paulo");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959534");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 7");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("São");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("São Pau");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerRonaldoRecife() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("95.826.713/0001-15");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Ronaldo Recife");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959534");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 8");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Pernam");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Recif");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerFabioFloripa() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("31.273.370/0001-26");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Fábio Floripa");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 9");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Santa Catarina");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Flori");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerRicardoCuritiba() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("91.628.557/0001-27");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Ricardo Curitiba");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 10");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Paran");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Curitib");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerLucasGravatai() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("61.799.637/0001-84");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Lucas Gravataí");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 11");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Rio Grande do S");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Grava");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerGustavoBentoGoncalves() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("02.454.746/0001-09");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Gustavo Bento Gonçalves");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 12");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Rio Grande do S");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Bento Gon");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerLucasJoinvile() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("50.696.552/0001-54");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Lucas Joiville");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 13");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Santa Cat");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Join");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerOctavioLondrina() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("34.567.724/0001-33");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Octavio Londrina");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 14");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Paran");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Londri");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerGabrielAlvorada() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("85.176.535/0001-70");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Gabriel Alvorada");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 15");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Rio Grande do S");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Alvo");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerGustavoNovoHamburgo() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("63.766.854/0001-01");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Gustavo Novo Hamburgo");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 16");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Rio Grande do S");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Novo Ham");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerLucasGuarulhos() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("86.888.825/0001-00");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Lucas Guarulhos");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 17");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("são pau");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Guarul");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(100);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerAndreTaubate() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("42.370.218/0001-88");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Andre Taubaté");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 18");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("sao pau");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Tauba");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerGrabrielSaoSimao() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("66.838.669/0001-65");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Gabriel São Simão");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 19");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("sao pau");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("sao sim");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerGuilhermeSaltoGrande() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("TICKET");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("64.226.831/0001-69");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Guilherme Salto Grande");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34959530");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 20");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91523-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("sao pau");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Salto G");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerGiovandersonSaoPaulo() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("44.333.547/0001-84");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Giovanderson São Paulo");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("32212011");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 21");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("08021-170");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("São");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("São Pau");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 24)
    public void createCustomerJosefRecife() throws Exception {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Customer"))).click();
        driver.findElement(By.id("null")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_email"))).clear();
        new Select(driver.findElement(By.id("customer_contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("customer_status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("CNPJ");
        driver.findElement(By.id("documentNumber")).clear();
        driver.findElement(By.id("documentNumber")).sendKeys("17.833.290/0001-57");
        driver.findElement(By.id("customer_name")).clear();
        driver.findElement(By.id("customer_name")).sendKeys("Josef Recife");
        driver.findElement(By.id("phoneNumber")).clear();
        driver.findElement(By.id("phoneNumber")).sendKeys("34651128");
        driver.findElement(By.id("address.address")).clear();
        driver.findElement(By.id("address.address")).sendKeys("RUA 22");
        driver.findElement(By.id("zipCodeTextField")).clear();
        driver.findElement(By.id("zipCodeTextField")).sendKeys("91000-133");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete_countryId"))).clear();

        driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bras");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_stateId"))).clear();
        driver.findElement(By.id("autocomplete_stateId")).sendKeys("Pernam");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).clear();
        driver.findElement(By.id("autocomplete_cityId")).sendKeys("Recif");
        Thread.sleep(700);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("div.modal-footer > button.btn.btn-primary")).click();

    }

    @Test(priority = 25)
    public void createManufacturer() throws Exception {
        System.out.println("DEV Manufacturer iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Manufacturer")));
        driver.findElement(By.linkText("Manufacturer")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Ingenico");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Verifone");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Sin datos");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV Manufacturer finalizado");

    }

    @Test(priority = 26)
    public void createFamilyPOS() throws Exception {
        System.out.println("DEV FamilyPOS iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Family"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("POS");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV FamilyPOS finalizado");
    }

    @Test(priority = 26)
    public void createFamilyDisplay() throws Exception {
        System.out.println("DEV FamilyDisplay iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Family"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Display");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV FamilyDisplay finalizado");
    }

    @Test(priority = 26)
    public void createFamilyPOSComponentes() throws Exception {
        System.out.println("DEV FamilyPOSComponentes iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Family"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("POS - Componentes");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV FamilyPOSComponentes finalizado");
    }

    @Test(priority = 26)
    public void createFamilyPOSImpressora() throws Exception {
        System.out.println("DEV FamilyPOSImpressora iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Family"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("POS - Impressora");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV FamilyPOSImpressora finalizado");
    }

    @Test(priority = 26)
    public void createFamilyFonteDeAlimentacao() throws Exception {
        System.out.println("DEV FamilyFonteDeAlimentacao iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Family"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Fonte de Alimentação");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV FamilyFonteDeAlimentacao finalizado");

    }

    @Test(priority = 27)
    public void createModelForProducts() throws Exception {
        System.out.println("DEV ModelForProducts iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Model"))).click();
        ;
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("BANJO");
        driver.findElement(By.id("mask")).clear();
        driver.findElement(By.id("mask")).sendKeys("9999999999");
        driver.findElement(By.id("pattern")).clear();
        driver.findElement(By.id("pattern")).sendKeys("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("I5100");
        driver.findElement(By.id("mask")).clear();
        driver.findElement(By.id("mask")).sendKeys("9999999999");
        driver.findElement(By.id("pattern")).clear();
        driver.findElement(By.id("pattern")).sendKeys("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("I8200");
        driver.findElement(By.id("mask")).clear();
        driver.findElement(By.id("mask")).sendKeys("9999999999");
        driver.findElement(By.id("pattern")).clear();
        driver.findElement(By.id("pattern")).sendKeys("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("I7910");
        driver.findElement(By.id("mask")).clear();
        driver.findElement(By.id("mask")).sendKeys("9999999999");
        driver.findElement(By.id("pattern")).clear();
        driver.findElement(By.id("pattern")).sendKeys("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("ACQUA");
        driver.findElement(By.id("mask")).clear();
        driver.findElement(By.id("mask")).sendKeys("9999999999");
        driver.findElement(By.id("pattern")).clear();
        driver.findElement(By.id("pattern")).sendKeys("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        // ERROR: Caught exception [ERROR: Unsupported command [typeKeys |
        // id=family | POS]]
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("EFT930G");
        driver.findElement(By.id("mask")).clear();
        driver.findElement(By.id("mask")).sendKeys("99999PT99999999");
        driver.findElement(By.id("pattern")).clear();
        driver.findElement(By.id("pattern")).sendKeys("[0-9][0-9][0-9][0-9][0-9][P][T][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("EFT930S");
        driver.findElement(By.id("mask")).clear();
        driver.findElement(By.id("mask")).sendKeys("99999PS99999999");
        driver.findElement(By.id("pattern")).clear();
        driver.findElement(By.id("pattern")).sendKeys("[0-9][0-9][0-9][0-9][0-9][P][S][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("ICT220");
        driver.findElement(By.id("mask")).clear();
        driver.findElement(By.id("mask")).sendKeys("99999CT99999999");
        driver.findElement(By.id("pattern")).clear();
        driver.findElement(By.id("pattern")).sendKeys("[0-9][0-9][0-9][0-9][0-9][C][T][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        // Equip. laboratório
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("I5100MMD012B");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("POS I5100");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("I5100MMD047A");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("POS I5100");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("I5100PPT033B");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("POS I5100");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Verifone");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("NURIT8320S");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("POS NURIT 8320");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV ModelForProducts finalizado");
    }

    @Test(priority = 28)
    public void createModelForComponents() throws Exception {
        System.out.println("DEV ModelForComponents iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Model"))).click();
        ;
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Sin datos");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS - Componentes");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("ST763A");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("3.3V STEP DWN CURR MODE PWM DC-DC CONV");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Sin datos");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS - Componentes");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("ST3232ECTR");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("RS-232 DRIVERS AND RECEIVERS");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Sin datos");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS - Componentes");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("CAP 100UF6V3(SMD)");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("CAPACITOR 100uF 6V3 (SMD)");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV ModelForComponents finalizado");
    }

    @Test(priority = 29)
    public void createModelForParts() throws Exception {
        System.out.println("DEV ModelForParts iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Model"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Part");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("Display");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("POS_DISPLAY");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("DISPLAY I5100/PPT/I7910/I7310 (AFF0058)");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Part");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS - Impressora");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("POS_IMPRESORA");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("IMPRESORA I5100/PPT/I7910/I7310 (DIV1109)");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        // Create Accessory
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("manufacturerId"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("type"))).selectByVisibleText("Accessory");
        new Select(driver.findElement(By.id("family"))).selectByVisibleText("Fonte de Alimentação");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Fonte ICT220");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        // Add Component/Accessory
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("ACQUA");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        webDriverWait.until(
                ExpectedConditions.elementToBeClickable(By
                        .xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]/a[1]"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[1]/div/ul/li[2]/a"))).click();
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("components"))).selectByVisibleText("ST763A");
        driver.findElement(By.id("form_add_models_button")).click();

        // adicionando a fonte ao ICT220
        driver.get(this.testSuiteHelper.getBaseUrl() + "/model/list");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Filters")));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("ICT220");
        driver.findElement(By.xpath("//button[@value='Search']")).click();
        driver.findElement(By.xpath("//html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[2]/td[1]/a[1]")).click();
        driver.findElement(By.xpath("//html/body/div[2]/div/div/div[1]/div/ul/li[2]/a")).click();
        new Select(driver.findElement(By.id("components"))).selectByVisibleText("Fonte ICT220");
        driver.findElement(By.id("form_add_models_button")).click();
        System.out.println("DEV ModelForParts finalizado");

    }

    @Test(priority = 30)
    public void createEquipment() throws Exception {
        System.out.println("DEV Equipment iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl() + "/equipment/index");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        Thread.sleep(500);
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("BANJO");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("serialNumber"))).clear();
        driver.findElement(By.id("serialNumber")).sendKeys(testSuiteHelper.generateLengthNumber(10));
        driver.findElement(By.id("contractorExternalId")).clear();
        driver.findElement(By.id("contractorExternalId")).sendKeys("00000674");
        driver.findElement(By.id("EQUIPMENT_custom_field_1")).clear();
        driver.findElement(By.id("EQUIPMENT_custom_field_1")).sendKeys("00414002300");
        driver.findElement(By.id("EQUIPMENT_custom_field_2")).clear();
        driver.findElement(By.id("EQUIPMENT_custom_field_2")).sendKeys("000000000000506");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Product");
        Thread.sleep(500);
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("BANJO");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("serialNumber")));
        driver.findElement(By.id("serialNumber")).clear();
        driver.findElement(By.id("serialNumber")).sendKeys(testSuiteHelper.generateLengthNumber(10));
        driver.findElement(By.id("EQUIPMENT_custom_field_1")).clear();
        driver.findElement(By.id("EQUIPMENT_custom_field_1")).sendKeys("00414002200");
        driver.findElement(By.id("EQUIPMENT_custom_field_2")).clear();
        driver.findElement(By.id("EQUIPMENT_custom_field_2")).sendKeys("000000000000873");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("BANJO");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("serialNumber"))).clear();
        driver.findElement(By.id("serialNumber")).sendKeys(testSuiteHelper.generateLengthNumber(10));
        driver.findElement(By.id("EQUIPMENT_custom_field_1")).clear();
        driver.findElement(By.id("EQUIPMENT_custom_field_1")).sendKeys("00414002300");
        driver.findElement(By.id("EQUIPMENT_custom_field_2")).clear();
        driver.findElement(By.id("EQUIPMENT_custom_field_2")).sendKeys("000000000000249");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");

        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("BANRISUL");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("BANJO");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("serialNumber"))).clear();
        driver.findElement(By.id("serialNumber")).sendKeys(testSuiteHelper.generateLengthNumber(10));
        driver.findElement(By.id("contractorExternalId")).sendKeys(testSuiteHelper.generateLengthNumber(8));
        driver.findElement(By.id("EQUIPMENT_custom_field_1")).clear();
        driver.findElement(By.id("EQUIPMENT_custom_field_1")).sendKeys("00414004000");
        driver.findElement(By.id("EQUIPMENT_custom_field_2")).clear();
        driver.findElement(By.id("EQUIPMENT_custom_field_2")).sendKeys("000000000000010");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Sin datos");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("TEFTI Barueri");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("ST3232ECTR");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("100");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        // Equip. Laboratório
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("POSNET");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Sin datos");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Vortex");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("ST3232ECTR");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("100");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("CABAL");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Sin datos");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Vortex");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("ST763A");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("5");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("contractor"))).selectByVisibleText("POSNET");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Sin datos");
        Thread.sleep(300);
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("CAP 100UF6V3(SMD)");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Vortex");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("30");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Sin datos");
        Thread.sleep(300);
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("CAP 100UF6V3(SMD)");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Vortex");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("30");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Accessory");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("Fonte ICT220");
        // ERROR: Caught exception [ERROR: Unsupported command [selectWindow |
        // null | ]]
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("TEFTI Barueri");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("20");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        // Criando Equipamentos para Smart e filial
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Sin datos");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("CAP 100UF6V3(SMD)");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Smart");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("50");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("ACQUA");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Smart");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("serialNumber"))).clear();
        driver.findElement(By.id("serialNumber")).sendKeys(testSuiteHelper.generateLengthNumber(10));
        driver.findElement(By.id("contractorExternalId")).clear();
        driver.findElement(By.id("contractorExternalId")).sendKeys("00000340");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Component");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Sin datos");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("CAP 100UF6V3(SMD)");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Smartf1");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("20");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Product");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("ACQUA");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Smartf1");
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("serialNumber"))).clear();
        driver.findElement(By.id("serialNumber")).sendKeys(testSuiteHelper.generateLengthNumber(10));
        driver.findElement(By.id("contractorExternalId")).clear();
        driver.findElement(By.id("contractorExternalId")).sendKeys("00000380");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("situation"))).selectByVisibleText("Good");
        new Select(driver.findElement(By.id("modelType"))).selectByVisibleText("Accessory");
        new Select(driver.findElement(By.id("manufacturer"))).selectByVisibleText("Ingenico");
        new Select(driver.findElement(By.id("model"))).selectByVisibleText("Fonte ICT220");
        new Select(driver.findElement(By.id("service_provider"))).selectByVisibleText("Smart");
        driver.findElement(By.id("quantity")).clear();
        driver.findElement(By.id("quantity")).sendKeys("100");
        driver.findElement(By.id("PO")).sendKeys(testSuiteHelper.generateLengthNumber(7));
        driver.findElement(By.id("SI")).sendKeys(testSuiteHelper.generateLengthNumber(7));
        driver.findElement(By.xpath("//button[@value='Create']")).click();
        System.out.println("DEV Equipment finalizado");
    }

    @Test(priority = 31)
    public void createSolution() throws Exception {
        System.out.println("DEV Solution iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Solution")));
        driver.findElement(By.linkText("Solution")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("TROCA DE TERMINAL");
        driver.findElement(By.id("changeEquipment")).click();
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        // Warning: verifyTextPresent may require manual changes
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("CONFIGURACAO DO TERMINAL");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Trocar Fonte");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV Solution finalizado");
    }

    @Test(priority = 32)
    public void createSolutionLaboratory() throws Exception {
        System.out.println("DEV SolutionLaboratory iniciado");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
        driver.get(this.testSuiteHelper.getBaseUrl());
        driver.findElement(By.cssSelector("img.wkf-brand")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Solution Laboratory")));
        driver.findElement(By.linkText("Solution Laboratory")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Repara Carcaza");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Cambia Display");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        driver.findElement(By.name("_action_index")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
        new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
        driver.findElement(By.id("name")).sendKeys("Cambia Impresora");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        testSuiteHelper.validateMessageSuccess("created");
        System.out.println("DEV SolutionLaboratory finalizado");

    }

	@Test(priority = 33)
	public void createDefect() {
		System.out.println("DEV Defect iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/defect/index");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Terminal Bloqueado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		new Select(driver.findElement(By.id("solutions"))).selectByVisibleText("TROCA DE TERMINAL");
		driver.findElement(By.id("form_add_solutions_button")).click();

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add_solutions_container")));
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("solutions")));
		new Select(driver.findElement(By.id("solutions"))).selectByVisibleText("CONFIGURACAO DO TERMINAL");
		driver.findElement(By.id("form_add_solutions_button")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add_solutions_container")));

		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Fonte danificada");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		new Select(driver.findElement(By.id("solutions"))).selectByVisibleText("Trocar Fonte");
		driver.findElement(By.id("form_add_solutions_button")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("add_solutions_container")));

		System.out.println("DEV Defect finalizado");
	}

	@Test(priority = 34)
	public void createDefectLaboratory() throws Exception {
		System.out.println("DEV DefectLaboratory iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/defectLaboratory/index");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Carcaza Rota");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		new Select(driver.findElement(By.id("solutions"))).selectByVisibleText("Repara Carcaza");
		driver.findElement(By.id("form_add_solutions_button")).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("_action_index")));
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Display Defectuoso");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		new Select(driver.findElement(By.id("solutions"))).selectByVisibleText("Cambia Display");
		driver.findElement(By.id("form_add_solutions_button")).click();

		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("family"))).selectByVisibleText("POS");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Impresora Imprime Mal");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		new Select(driver.findElement(By.id("solutions"))).selectByVisibleText("Cambia Impresora");
		driver.findElement(By.id("form_add_solutions_button")).click();

		System.out.println("DEV DefectLaboratory finalizado");

	}

	@Test(priority = 35)
	public void createSLAGBanrisulInstalacaoTrocaDeTecnologia() throws Exception {
		System.out.println("DEV SLAGBanrisulInstalacaoTrocaDeTecnologia iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Level Agreement Group")));
		driver.findElement(By.linkText("Service Level Agreement Group")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("SLA Banrisul (Instalação e Troca de Tecnologia)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Capital");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("15");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("termTypeDetail"))).selectByVisibleText("Consecutive Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("13");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Country Town");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("15");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("termTypeDetail"))).selectByVisibleText("Consecutive Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("13");
		new Select(driver.findElement(By.id("serviceProviderTermType"))).selectByVisibleText("Days");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("SLA Banrisul (Demais Serviços)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Capital");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("4");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Hour");
		driver.findElement(By.cssSelector("option[value=\"HOUR\"]")).click();
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("4");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Country Town");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("24");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("20");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("State and City");
		new Select(driver.findElement(By.id("state_id"))).selectByVisibleText("Rio Grande do Sul");
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("alvora");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("cano");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("caxi");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("grava");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("novo ha");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("passo fu");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("pelo");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("rio gr");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("santa cruz");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("Santa Ma");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("santo");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("sao leo");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("viam");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(800);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("addCity"))).click();
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("12");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("10");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV SLAGBanrisulInstalacaoTrocaDeTecnologia finalizado");
	}

	@Test(priority = 36)
	public void createSLATicketLocacaoInstalacao() throws Exception {
		System.out.println("DEV SLATicketLocacaoInstalacao iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Level Agreement Group")));
		driver.findElement(By.linkText("Service Level Agreement Group")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("SLA Ticket (Locação e Instalação)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Midwest");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("50");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("9");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("7");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Midwest");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("150");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("11");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("9");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Midwest");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("13");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("11");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Midwest");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Greater Than");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("16");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("14");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Northeast");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("50");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("9");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("7");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Northeast");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("150");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("11");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("9");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Northeast");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("13");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("11");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Northeast");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Greater Than");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("16");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("14");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("North");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("50");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("10");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("8");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("North");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("150");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("12");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("10");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("North");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("14");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("12");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("North");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Greater Than");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("17");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("15");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Southeast");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("50");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("8");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("6");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Southeast");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("150");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("10");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("8");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Southeast");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("12");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("10");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("Southeast");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Greater Than");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("15");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("13");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("South");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("50");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("9");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("7");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("South");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("150");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("11");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("9");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("South");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("13");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("11");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Region");
		new Select(driver.findElement(By.id("region"))).selectByVisibleText("South");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Greater Than");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("16");
		new Select(driver.findElement(By.id("termType"))).selectByVisibleText("Days");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("14");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV SLATicketLocacaoInstalacao finalizado");
	}

	@Test(priority = 37)
	public void createSLATicketManutencao() throws Exception {
		System.out.println("DEV SLATicketManutencao iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Service Level Agreement Group")));
		driver.findElement(By.linkText("Service Level Agreement Group")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("SLA Ticket (Manutenção)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Distance From Capital");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("50");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("12");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("10");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Distance From Capital");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("150");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("24");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("20");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Distance From Capital");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Until");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("36");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("30");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Distance From Capital");
		new Select(driver.findElement(By.id("distanceFromCapital.type"))).selectByVisibleText("Greater Than");
		new Select(driver.findElement(By.id("distanceFromCapital.value"))).selectByVisibleText("300");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("60");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("50");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("criterion"))).selectByVisibleText("Capital");
		new Select(driver.findElement(By.id("termValue"))).selectByVisibleText("12");
		new Select(driver.findElement(By.id("serviceProviderTermValue"))).selectByVisibleText("10");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV SLATicketManutencao finalizado");
	}

	@Test(priority = 38)
	public void createStatus() throws Exception {
		System.out.println("DEV Status iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		// Status de Campo
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Status")));
		driver.findElement(By.linkText("Status")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.START.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Nova");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.END_WITH_FAIL.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Cancelado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Finalizado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("With Technician");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Em Campo");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Encaminhado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("Pre End");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Pre-Finalizado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Para Aprovação");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Para Encerramento");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Cancelado (Pendente Autorização)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reencaminhado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Aprovado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Conferência no Destino");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Encaminhado Manut");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Finalizado (Sem Troca)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Status de Laboratório
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.START.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("RR Abierta");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Asignada");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("En Reparo");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reparacion OK");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Irreparable");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("In QA Evaluation");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("QA_Lab");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("QA Aprobado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("QA Rechazado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Bodega Egreso");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Entregado OK");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.END_WITH_FAIL.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Entregado NOK");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Bodega Baja");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Status Remessa
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.START.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Nova");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Preparação");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Despachado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.END_WITH_FAIL.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Cancelado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Em Transito");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Recebido OK PS");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText(Messages.END_WITH_FAIL.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Recusado no Destino");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("PRÉ-DESPACHO");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("DESPACHADO GOOD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("DESPACHADO BAD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("INGRESSO GOOD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("End with Success");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("INGRESSO BAD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Ingresso de Equiptos");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Conferência no Destino");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Gerar Nota Fiscal");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		new Select(driver.findElement(By.id("type"))).selectByVisibleText("");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Disponivel para Coleta");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV Status finalizado");
	}

	@Test(priority = 39)
	public void createWarrantyProvider() throws Exception {
		System.out.println("DEV WarrantyProvider iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Warranty Provider")));
		driver.findElement(By.linkText("Warranty Provider")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Laboratorio Vortex");
		new Select(driver.findElement(By.id("warrantyType"))).selectByVisibleText("Repair");
		driver.findElement(By.id("days")).clear();
		driver.findElement(By.id("days")).sendKeys("30");
		driver.findElement(By.id("allowChangeDaysInSO")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Ingenico");
		new Select(driver.findElement(By.id("warrantyType"))).selectByVisibleText("Manufacturer");
		driver.findElement(By.id("days")).clear();
		driver.findElement(By.id("days")).sendKeys("360");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		System.out.println("DEV WarrantyProvider finalizado");
	}

	@Test(priority = 40)
	public void createRoutingGroups() throws Exception {
		System.out.println("DEV RoutingGroups iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Routing Group")));
		driver.findElement(By.linkText("Routing Group")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Grupo de Roteirização A1");
		driver.findElement(By.xpath("(//input[@id='rules'])[6]")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Routing Group Grupo de Roteirização A1 created"));
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Filters")));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Grupo de Roteirização B1");
		driver.findElement(By.xpath("(//input[@id='rules'])[5]")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Routing Group Grupo de Roteirização B1 created"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Grupo de Roteirização C1");
		driver.findElement(By.xpath("(//input[@id='rules'])[4]")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Routing Group Grupo de Roteirização C1 created"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Grupo de Roteirização D1");
		driver.findElement(By.xpath("(//input[@id='rules'])[3]")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Routing Group Grupo de Roteirização D1 created"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Grupo de Roteirização E1");
		driver.findElement(By.xpath("(//input[@id='rules'])[2]")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Routing Group Grupo de Roteirização E1 created"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Grupo de Roteirização F1");
		driver.findElement(By.xpath("(//input[@id='rules'])[1]")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Routing Group Grupo de Roteirização F1 created"));
		System.out.println("DEV RoutingGroups finalizado");

	}

	@Test(priority = 41)
	public void createWorkflowStepsForField() throws Exception {
		System.out.println("DEV WorkflowStepsForField iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Workflow"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		// Workflow de Campo
		// Instalação
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Instalação");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Para Aprovação");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Para Aprovação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Para Aprovação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Aprovado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Aprovado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Inventoried_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Desinstalação
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Desinstalação");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Manutenção
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Manutenção");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		driver.findElement(By.id("rule_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Defect")).click();
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Solution")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		driver.findElement(By.id("rule_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(
				By.id("rule_Disassociate_New_Equipment_from_Customer_setting_Situation_to_GOOD,_and_delete_New_Equipment_if_it_is_temporary"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Defect")).click();
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Solution")).click();
		driver.findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Pre-Start");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Nova");
		driver.findElement(By.id("rule_Contractor_External_ID")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Troca de Tecnologia
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Troca de Tecnologia");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_New_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Inventoried_New_Equipment")).click();
		driver.findElement(By.id("rule_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado (Sem Troca)");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_New_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Inventoried_New_Equipment")).click();
		driver.findElement(By.id("rule_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado (Pendente Autorização)");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Cancelado (Pendente Autorização)");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(
				By.id("rule_Disassociate_New_Equipment_from_Customer_setting_Situation_to_GOOD,_and_delete_New_Equipment_if_it_is_temporary"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Cancelado (Pendente Autorização)");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Reencaminhado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Reencaminhado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Reconfiguração
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reconfiguração");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Defect")).click();
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Solution")).click();
		driver.findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(
				By.id("rule_Disassociate_New_Equipment_from_Customer_setting_Situation_to_GOOD,_and_delete_New_Equipment_if_it_is_temporary"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Defect")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Solution")).click();
		driver.findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Atualização de Software
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Atualização de Software");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(
				By.id("rule_Disassociate_New_Equipment_from_Customer_setting_Situation_to_GOOD,_and_delete_New_Equipment_if_it_is_temporary"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Defect")).click();
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Solution")).click();
		driver.findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Cancelamento
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Cancelamento");
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.id("rule_Define_Routed_Date")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Reason_For_Cancellation")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		driver.findElement(By.id("rule_Equipment")).click();
		driver.findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		driver.findElement(By.id("rule_Equipment_Serial_Number")).click();
		driver.findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		driver.findElement(By.id("rule_Service_Provider")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV WorkflowStepsForField finalizado");
	}

	@Test(priority = 42)
	public void createWorkflowStepsForLaboratory() throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		System.out.println("DEV WorkflowStepsForLaboratory iniciado");
		// Workflow de Laboratório
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Workflow"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText(Messages.REPAIR_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Workflow Laboratório");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("RR Abierta");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Asignada");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Asignada");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("En Reparo");
		driver.findElement(By.id("rule_Technician")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("En Reparo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Reparacion OK");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("En Reparo");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Irreparable");
		driver.findElement(By.id("rule_Convert_Equipment_to_Situation_IRREPARABLE")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Irreparable");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Bodega Baja");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Reparacion OK");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("QA_Lab");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("QA_Lab");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("QA Aprobado");
		driver.findElement(By.id("rule_All_Quality_Evaluation_must_be_Approved")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("QA_Lab");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("QA Rechazado");
		driver.findElement(By.id("rule_At_least_one_Quality_Evaluation_must_be_Reproved")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("QA Aprobado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Bodega Egreso");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("QA Rechazado");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("En Reparo");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Bodega Egreso");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Entregado OK");
		driver.findElement(By.id("rule_Convert_Equipment_to_Situation_GOOD")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Bodega Baja");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Entregado NOK");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV WorkflowStepsForLaboratory finalizado");

	}

	@Test(priority = 43)
	public void createWorkflowStepsForLogistic() throws Exception {
		System.out.println("DEV WorkflowStepsForLogistic iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		// Workflow Logística
		// Remessa Simples BAD
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Workflow"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		driver.findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Workflow Logística - Remessa Simples BAD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Preparação");
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Preparação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Despachado");
		driver.findElement(By.id("rule_Only_BAD_Equipments")).click();
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Preparação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Remessa Simples GOOD
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Workflow Logística - Remessa Simples GOOD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Preparação");
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Preparação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Despachado");
		driver.findElement(By.id("rule_Only_GOOD_Equipments")).click();
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Preparação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Remessa Complexa
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Workflow Logística - Remessa Complexa");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Preparação");
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Preparação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Transito");
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Transito");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Recebido OK PS");
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Transito");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Recusado no Destino");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Ingresso de Equipamentos - GOOD
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Ingresso de Equipamentos - GOOD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Ingresso de Equiptos");
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Ingresso de Equiptos");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Recebido OK PS");
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.id("rule_Set_Equipments_situation_to_GOOD")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Ingresso de Equiptos");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Ingresso de Equipamentos - BAD
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Ingresso de Equipamentos - BAD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Ingresso de Equiptos");
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Ingresso de Equiptos");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Recebido OK PS");
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.id("rule_Set_Equipments_situation_to_BAD")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Ingresso de Equiptos");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Ordens de Entrega - GOOD
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Ordens de Entrega - GOOD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Preparação");
		driver.findElement(By.id("rule_Only_GOOD_Equipments")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Preparação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("PRÉ-DESPACHO");
		driver.findElement(By.id("rule_Only_GOOD_Equipments")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("PRÉ-DESPACHO");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("DESPACHADO GOOD");
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.id("rule_Weight")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("PRÉ-DESPACHO");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Only_GOOD_Equipments")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Ordens de Entrega - BAD
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Ordens de Entrega - BAD");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Preparação");
		driver.findElement(By.id("rule_Only_BAD_Equipments")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Preparação");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("PRÉ-DESPACHO");
		driver.findElement(By.id("rule_Only_BAD_Equipments")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("PRÉ-DESPACHO");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("DESPACHADO GOOD");
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.id("rule_Weight")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("PRÉ-DESPACHO");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.id("rule_Only_BAD_Equipments")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		// Adicionando a trigger de Garantia de reparo.
		driver.findElement(By.name("_action_index")).click();
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[6]/td[1]/a[1]")));
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[6]/td[1]/a[1]")).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By
				.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/table/tbody/tr[3]/td[1]/a[2]")));
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/table/tbody/tr[3]/td[1]/a[2]")).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("stepRuleCustomTrigger")));
		new Select(driver.findElement(By.id("stepRuleCustomTrigger"))).selectByVisibleText("Add warranty to equipments");
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("addCustomTriggerButton")));
		driver.findElement(By.id("addCustomTriggerButton")).click();
		System.out.println("DEV WorkflowStepsForLogistic finalizado");
	}

	@Test(priority = 44)
	public void createWorkflowStepsPrestadorParaTEFTI() throws Exception {
		System.out.println("DEV WorkflowStepsPrestadorParaTEFTI iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/workflow/list");
		// Remessa Prestador para TEFTI
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("workflowType")));
		new Select(driver.findElement(By.id("workflowType"))).selectByVisibleText("Shipment Order");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Remessa Prestador para TEFTI");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Ingresso de Equiptos");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Ingresso de Equiptos");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Gerar Nota Fiscal");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Gerar Nota Fiscal");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Gerar Nota Fiscal");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Em Transito");
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.id("rule_Weight")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Transito");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Em Transito");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Conferência no Destino");
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.id("rule_Weight")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Conferência no Destino");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Recusado no Destino");
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.id("rule_Weight")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("from.id"))).selectByVisibleText("Conferência no Destino");
		new Select(driver.findElement(By.id("to.id"))).selectByVisibleText("Recebido OK PS");
		driver.findElement(By.id("rule_Carrier")).click();
		driver.findElement(By.id("rule_Invoice_Date")).click();
		driver.findElement(By.id("rule_Invoice_Number")).click();
		driver.findElement(By.id("rule_Outbound_Date")).click();
		driver.findElement(By.id("rule_Weight")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		System.out.println("DEV WorkflowStepsPrestadorParaTEFTI finalizado");
	}

	@Test(priority = 45)
	public void createServiceToBanrisul() throws Exception {
		System.out.println("DEV ServiceToBanrisul iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contractor"))).click();
		// BANRISUL
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("Banrisul");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Instalação");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Instalação");
		Thread.sleep(1000);
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyEquipmentFromServiceProviderStockOnServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id")))
				.selectByVisibleText("SLA Banrisul (Instalação e Troca de Tecnologia)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Instalação Venda");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");// retirar
																			// apos
																			// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();// retirar
																																	// apos
																																	// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();// retirar
																															// apos
																															// correcao
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Desinstalação");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Desinstalação");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id")))
				.selectByVisibleText("SLA Banrisul (Instalação e Troca de Tecnologia)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Desinstalação");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");// retirar
																			// apos
																			// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();// retirar
																																	// apos
																																	// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();// retirar
																															// apos
																															// correcao
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Reconfiguração");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Reconfiguração");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyNewEquipmentFromServiceProviderStockOnServiceOrder")).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Banrisul (Demais Serviços)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reconfiguração");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");// retirar
																			// apos
																			// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();// retirar
																																	// apos
																																	// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();// retirar
																															// apos
																															// correcao
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Troca de Tecnologia");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Troca de Tecnologia");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("allowsCreateNewEquipmentOnServiceOrder")).click();
		driver.findElement(By.id("onlyNewEquipmentFromServiceProviderStockOnServiceOrder")).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id")))
				.selectByVisibleText("SLA Banrisul (Instalação e Troca de Tecnologia)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Troca de Tecnologia (geral)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");// retirar
																			// apos
																			// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();// retirar
																																	// apos
																																	// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();// retirar
																															// apos
																															// correcao
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Manutenção");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Manutenção");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyNewEquipmentFromServiceProviderStockOnServiceOrder")).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Banrisul (Demais Serviços)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Manutenção");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");// retirar
																			// apos
																			// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();// retirar
																																	// apos
																																	// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();// retirar
																															// apos
																															// correcao
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Rollout");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Atualização de Software");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyNewEquipmentFromServiceProviderStockOnServiceOrder")).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");

		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Locação e Instalação)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Atualização de Software");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");// retirar
																			// apos
																			// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();// retirar
																																	// apos
																																	// correcao
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();// retirar
																															// apos
																															// correcao
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Cancelamento");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Cancelamento");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Locação e Instalação)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Cancelamento");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV ServiceToBanrisul finalizado");
	}

	@Test(priority = 46)
	public void createServiceToPOSNET() throws Exception {
		System.out.println("DEV createServiceToPOSNET iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		// driver.get(this.testSuiteHelper.getBaseUrl());
		// driver.findElement(By.cssSelector("img.wkf-brand")).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		// Thread.sleep(500);
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contractor"))).click();
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("POSNET");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Reparacion en Laboratorio");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Workflow Laboratório");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reparacion en Laboratorio");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV createServiceToPOSNET finalizado");
	}

	@Test(priority = 47)
	public void createServiceToCABAL() throws Exception {
		System.out.println("DEV ServiceToCABAL iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		// driver.get(this.testSuiteHelper.getBaseUrl());
		// driver.findElement(By.cssSelector("img.wkf-brand")).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		// Thread.sleep(500);
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contractor"))).click();
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("CABAL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Reparacion en Laboratorio");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Workflow Laboratório");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reparacion en Laboratorio");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV ServiceToCABAL finalizado");

	}

	@Test(priority = 48)
	public void createServiceToTicket() throws Exception {
		System.out.println("DEV ServiceToTicket iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.get(this.testSuiteHelper.getBaseUrl() + "/contractor/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("Ticket");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[1]/a[1]"))).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/table/tbody/tr/td[1]/a"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Instalação");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Instalação");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyEquipmentFromServiceProviderStockOnServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id")))
				.selectByVisibleText("SLA Banrisul (Instalação e Troca de Tecnologia)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Instalação Geral");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Manutenção");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Manutenção");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("allowsCreateNewEquipmentOnServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Manutenção)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Manutenção");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Desinstalação");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Desinstalação");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("allowsCreateNewEquipmentOnServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Locação e Instalação)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Desinstalação");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Troca de Tecnologia");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Troca de Tecnologia");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("allowsCreateNewEquipmentOnServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Locação e Instalação)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Troca de Tecnologia(Geral)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Reincidência");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Manutenção");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("allowsCreateNewEquipmentOnServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Manutenção)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reincidência");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Carga de Software");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Manutenção");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("allowsCreateNewEquipmentOnServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Manutenção)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Carga de Software");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Reconfiguração");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Manutenção");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("allowsCreateNewEquipmentOnServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Manutenção)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reconfiguração");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV ServiceToTicket finalizado");
	}

	@Test(priority = 49)
	public void createGenericContract() throws Exception {
		System.out.println("DEV GenericContract iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/genericContract");
		// driver.findElement(By.cssSelector("img.wkf-brand")).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Generic Contract"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Contrato Generico");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("openingHoursGroup"))).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Instalação");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Instalação");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyEquipmentFromServiceProviderStockOnServiceOrder")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Instalação Venda");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id")))
				.selectByVisibleText("SLA Banrisul (Instalação e Troca de Tecnologia)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Desinstalação");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Desinstalação");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id")))
				.selectByVisibleText("SLA Banrisul (Instalação e Troca de Tecnologia)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Desinstalação");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Reconfiguração");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Reconfiguração");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyNewEquipmentFromServiceProviderStockOnServiceOrder")).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Banrisul (Demais Serviços)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reconfiguração");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Troca de Tecnologia");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Troca de Tecnologia");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("allowsCreateNewEquipmentOnServiceOrder")).click();
		driver.findElement(By.id("onlyNewEquipmentFromServiceProviderStockOnServiceOrder")).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id")))
				.selectByVisibleText("SLA Banrisul (Instalação e Troca de Tecnologia)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Troca de Tecnologia (geral)");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Manutenção");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Manutenção");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyNewEquipmentFromServiceProviderStockOnServiceOrder")).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Banrisul (Demais Serviços)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Manutenção");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Rollout");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Atualização de Software");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		driver.findElement(By.id("onlyNewEquipmentFromServiceProviderStockOnServiceOrder")).click();
		driver.findElement(By.id("onlyEquipmentFromCustomerAtServiceOrder")).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Locação e Instalação)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Atualização de Software");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("service_group_id")));
		new Select(driver.findElement(By.id("service_group_id"))).selectByVisibleText("Cancelamento");
		new Select(driver.findElement(By.id("workflow_id"))).selectByVisibleText("Cancelamento");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("allowsCreateEquipmentOnServiceOrder"))).click();
		new Select(driver.findElement(By.id("openingHoursGroup.id"))).selectByVisibleText("MON-FRI 08h/18h");
		new Select(driver.findElement(By.id("serviceLevelAgreementGroup.id"))).selectByVisibleText("SLA Ticket (Locação e Instalação)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Cancelamento");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV GenericContract finalizado");

	}

	@Test(priority = 50)
	public void createReasonForCancellation() throws Exception {
		System.out.println("DEV ReasonForCancellation iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Contractor"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Reason For Cancellation"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - ENDERECO NAO LOCALIZADO");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - SEM COMUNICACAO");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - ESTABELECIMENTO FECHADO");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - NAO AUTORIZADO PELO CLIENTE");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - FALTA ASSINATURA DO CONTRATO");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - INSCRICAO ESTADUAL INVALIDA");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - CEP INVALIDO");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - RAZAO SOCIAL INVALIDA");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - EQUIPAMENTO NAO ESTA NO LOCAL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK Contato EC, endereço divergente");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK Contato EC não autorizou instal");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK Contato cliente EC sem infraest");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK Contato EC Banco solicitou canc");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK Contato EC, POS de outro fornec");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK Chamado duplicado");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK CNPJ baixado no SINTEGRA");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("PENDENTE NO ESTABELECIMENTO");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("PENDENTE NA AGENCIA");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("NOK - OUTRO");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV ReasonForCancellation finalizado");
	}

	@Test(priority = 51)
	public void createRecess() throws Exception {
		System.out.println("DEV Recess iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Contractor"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Recess"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Confraternização Universal");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("January");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Paixão de Cristo");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("March");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("2013");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("29");
		driver.findElement(By.id("autocomplete_countryId")).sendKeys("Bra");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.id("autocomplete_countryId")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Revolução Constitucionalista de 1932");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("July");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("9");

		driver.findElement(By.id("autocomplete_stateId")).sendKeys("São Paul");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.id("autocomplete_stateId")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_index"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("Aniversário da Cidade de Recife");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("March");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("12");

		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).sendKeys("Recife");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_index"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Aniversário da Cidade de Novo Hamburgo");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("April");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("5");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete_cityId"))).sendKeys("Novo Ham");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.id("autocomplete_cityId")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV Recess finalizado");

	}

	@Test(priority = 52)
	public void createActivity() throws Exception {
		System.out.println("DEV Activity iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Contractor"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Activity"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Troca de Componente");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Troca de Parte");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Reparo");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV Activity finalizado");
	}

	@Test(priority = 53)
	public void importProcessBanrisulFromTo() throws Exception {
		System.out.println("DEV importProcessBanrisulFromTo iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/importProcessFromTo");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("CONTRACTOR");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("1");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("2");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("2");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("1");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("3");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("3");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("4");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("2");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("6");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("7");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("7");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("4");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("8");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("4");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("SERVICE");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("9");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("1");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("STEP_STATUS");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("1");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("6");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("STEP_STATUS");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("2");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("7");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("STEP_STATUS");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("3");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("8");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("5");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("2");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("6");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("3");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("9");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("4");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("10");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("5");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("16");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("3");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("21");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("6");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("27");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("7");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("28");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("8");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("MODEL");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("35");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("9");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("11");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("2");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("12");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("3");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("13");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("4");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("14");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("5");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("15");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("6");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("16");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("7");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("17");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("8");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("18");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("9");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("19");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("10");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("30");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("11");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("31");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("12");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("32");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("13");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("33");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("14");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("34");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("15");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("35");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("16");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("36");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("17");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("41");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("18");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("42");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("19");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(driver.findElement(By.id("entityType"))).selectByVisibleText("REASON_FOR_CANCELLATION");
		driver.findElement(By.id("fromId")).clear();
		driver.findElement(By.id("fromId")).sendKeys("99");
		driver.findElement(By.id("toId")).clear();
		driver.findElement(By.id("toId")).sendKeys("20");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV importProcessBanrisulFromTo finalizado");
	}

	@Test(priority = 54)
	public void createQAEvaluation() throws Exception {
		System.out.println("DEV QAEvaluation iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Contractor"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quality Evaluation"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("QA - Estético");
		new Select(driver.findElement(By.id("family.id"))).selectByVisibleText("POS");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("QA - Funcional");
		new Select(driver.findElement(By.id("family.id"))).selectByVisibleText("POS");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("QA - Sistema");
		new Select(driver.findElement(By.id("family.id"))).selectByVisibleText("POS");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV QAEvaluation finalizado");
	}

	@Test(priority = 55)
	public void createProfileSettings() throws Exception {
		System.out.println("DEV ProfileSettings iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Contractor"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Profile Settings"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Administrator");
		driver.findElement(By.id("badge_edit_6")).click();
		driver.findElement(By.id("badge_edit_7")).click();
		driver.findElement(By.id("badge_edit_8")).click();
		driver.findElement(By.id("badge_edit_9")).click();
		driver.findElement(By.id("badge_edit_10")).click();
		driver.findElement(By.id("badge_edit_11")).click();
		driver.findElement(By.id("badge_edit_12")).click();
		driver.findElement(By.id("badge_edit_13")).click();
		driver.findElement(By.id("badge_edit_14")).click();
		driver.findElement(By.id("badge_edit_15")).click();
		driver.findElement(By.id("badge_edit_16")).click();
		driver.findElement(By.id("badge_edit_17")).click();
		driver.findElement(By.id("badge_edit_18")).click();
		driver.findElement(By.id("badge_edit_19")).click();
		driver.findElement(By.id("badge_edit_20")).click();
		driver.findElement(By.id("badge_edit_21")).click();
		driver.findElement(By.id("badge_edit_22")).click();
		driver.findElement(By.id("badge_edit_23")).click();
		driver.findElement(By.id("badge_edit_24")).click();
		driver.findElement(By.id("badge_edit_25")).click();
		driver.findElement(By.id("badge_edit_26")).click();
		driver.findElement(By.id("badge_edit_27")).click();
		driver.findElement(By.id("badge_edit_28")).click();
		driver.findElement(By.id("badge_edit_29")).click();
		driver.findElement(By.id("badge_edit_30")).click();
		driver.findElement(By.id("badge_edit_31")).click();
		driver.findElement(By.id("badge_edit_32")).click();
		driver.findElement(By.id("badge_edit_33")).click();
		driver.findElement(By.id("badge_edit_34")).click();
		driver.findElement(By.id("badge_edit_38")).click();
		driver.findElement(By.id("badge_edit_37")).click();
		driver.findElement(By.id("badge_edit_40")).click();
		driver.findElement(By.id("badge_edit_39")).click();
		driver.findElement(By.id("badge_edit_41")).click();
		driver.findElement(By.id("badge_edit_36")).click();
		driver.findElement(By.id("badge_edit_35")).click();
		driver.findElement(By.id("badge_edit_42")).click();
		driver.findElement(By.id("badge_edit_43")).click();
		driver.findElement(By.id("badge_edit_44")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Attendant");
		driver.findElement(By.id("badge_edit_6")).click();
		driver.findElement(By.id("badge_edit_7")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Laboratory Supervisor");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("badge_edit_18"))).click();
		driver.findElement(By.id("badge_edit_25")).click();
		driver.findElement(By.id("badge_edit_28")).click();
		driver.findElement(By.id("badge_edit_19")).click();
		driver.findElement(By.id("badge_edit_27")).click();
		driver.findElement(By.id("badge_edit_26")).click();
		driver.findElement(By.id("badge_edit_21")).click();
		driver.findElement(By.id("badge_edit_22")).click();
		driver.findElement(By.id("badge_edit_23")).click();
		driver.findElement(By.id("badge_edit_24")).click();
		driver.findElement(By.id("badge_edit_22")).click();
		driver.findElement(By.id("badge_edit_17")).click();
		driver.findElement(By.id("badge_edit_20")).click();
		driver.findElement(By.id("badge_edit_23")).click();
		driver.findElement(By.id("badge_edit_24")).click();
		driver.findElement(By.id("badge_edit_25")).click();
		driver.findElement(By.id("badge_edit_26")).click();
		driver.findElement(By.id("badge_edit_27")).click();
		driver.findElement(By.id("badge_edit_28")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Laboratory Technician");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("badge_edit_17"))).click();
		driver.findElement(By.id("badge_edit_18")).click();
		driver.findElement(By.id("badge_edit_19")).click();
		driver.findElement(By.id("badge_edit_20")).click();
		driver.findElement(By.id("badge_edit_21")).click();
		driver.findElement(By.id("badge_edit_22")).click();
		driver.findElement(By.id("badge_edit_23")).click();
		driver.findElement(By.id("badge_edit_24")).click();
		driver.findElement(By.id("badge_edit_25")).click();
		driver.findElement(By.id("badge_edit_26")).click();
		driver.findElement(By.id("badge_edit_27")).click();
		driver.findElement(By.id("badge_edit_28")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Quality Technician");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("badge_edit_22"))).click();
		driver.findElement(By.id("badge_edit_23")).click();
		driver.findElement(By.id("badge_edit_24")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Attendant");
		driver.findElement(By.id("badge_edit_6")).click();
		driver.findElement(By.id("badge_edit_7")).click();
		driver.findElement(By.id("badge_edit_8")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Supervisor");
		driver.findElement(By.id("badge_edit_6")).click();
		driver.findElement(By.id("badge_edit_7")).click();
		driver.findElement(By.id("badge_edit_8")).click();
		driver.findElement(By.id("badge_edit_11")).click();
		driver.findElement(By.id("badge_edit_13")).click();
		driver.findElement(By.id("badge_edit_12")).click();
		driver.findElement(By.id("badge_edit_9")).click();
		driver.findElement(By.id("badge_edit_16")).click();
		driver.findElement(By.id("badge_edit_15")).click();
		driver.findElement(By.id("badge_edit_14")).click();
		driver.findElement(By.id("badge_edit_10")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("badge_edit_6"))).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Logistic Technician");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("badge_edit_32"))).click();
		driver.findElement(By.id("badge_edit_38")).click();
		driver.findElement(By.id("badge_edit_37")).click();
		driver.findElement(By.id("badge_edit_31")).click();
		driver.findElement(By.id("badge_edit_33")).click();
		driver.findElement(By.id("badge_edit_40")).click();
		driver.findElement(By.id("badge_edit_39")).click();
		driver.findElement(By.id("badge_edit_41")).click();
		driver.findElement(By.id("badge_edit_36")).click();
		driver.findElement(By.id("badge_edit_29")).click();
		driver.findElement(By.id("badge_edit_30")).click();
		driver.findElement(By.id("badge_edit_34")).click();
		driver.findElement(By.id("badge_edit_35")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_index"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Contractor");
		driver.findElement(By.id("badge_edit_6")).click();
		driver.findElement(By.id("badge_edit_7")).click();
		driver.findElement(By.id("badge_edit_10")).click();
		driver.findElement(By.id("badge_edit_11")).click();
		driver.findElement(By.id("badge_edit_12")).click();
		driver.findElement(By.id("badge_edit_16")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Supervisor");
		driver.findElement(By.id("badge_edit_6")).click();
		driver.findElement(By.id("badge_edit_7")).click();
		driver.findElement(By.id("badge_edit_8")).click();
		driver.findElement(By.id("badge_edit_9")).click();
		driver.findElement(By.id("badge_edit_10")).click();
		driver.findElement(By.id("badge_edit_11")).click();
		driver.findElement(By.id("badge_edit_12")).click();
		driver.findElement(By.id("badge_edit_15")).click();
		driver.findElement(By.id("badge_edit_16")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV ProfileSettings finalizado");

	}

	@Test(priority = 56)
	public void createCarrier() throws Exception {
		System.out.println("DEV Carrier iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logistic"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Carrier"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Transportadora Braspress");
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("Braspress");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		driver.findElement(By.id("phoneNumber")).clear();
		driver.findElement(By.id("phoneNumber")).sendKeys("553166559944");
		driver.findElement(By.id("contactName")).clear();
		driver.findElement(By.id("contactName")).sendKeys("Alejandro");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV Carrier fechado");
	}

	@Test(priority = 57)
	public void createServiceAreas() throws Exception {
		System.out.println("DEV ServiceAreas iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Service Areas"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Service Areas"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("São Paulo");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).clear();
		driver.findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys("São Paul");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("form_add_state_button")).click();
		// Warning: verifyTextPresent may require manual changes
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[2]"), "São Paulo"));
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Service Areas"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Service Areas"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Rio Grande do Sul + Guarulhos");
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).clear();
		driver.findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys("Rio Grande do S");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.form-control.ui-autocomplete-input")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("form_add_state_button")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div[2]/div[2]/div/table/tbody/tr/td[2]"), "Rio Grande do Sul"));
		new Select(driver.findElement(By.id("state_id"))).selectByVisibleText("São Paulo");
		driver.findElement(By.id("autocomplete_city")).clear();
		driver.findElement(By.id("autocomplete_city")).sendKeys("Guarulh");
		Thread.sleep(1500);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(1000);
		driver.findElement(By.id("autocomplete_city")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("form_add_city_button")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/div/div[3]/div[2]/form/div[3]/div/table/tbody/tr/td[2]"), "Guarulhos"));
		System.out.println("DEV ServiceAreas finalizado");

	}

	@Test(priority = 58)
	public void createShipmentOrderType() throws Exception {
		System.out.println("DEV ShipmentOrderType iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/shipmentOrderType/index");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Null -> Contratante (GOOD)");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples GOOD");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Null -> Service Provider (GOOD)");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples GOOD");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Service Provider -> Contratante (GOOD)");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples GOOD");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Null -> Contratante (BAD)");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples BAD");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Null -> Service Provider (BAD)");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples BAD");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Service Provider -> Contratante (BAD)");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Simples BAD");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Service Provider -> Service Provider");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Workflow Logística - Remessa Complexa");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Ingresso de Equipamentos - BAD");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Ingresso de Equipamentos - BAD");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Ordens de Entrega - GOOD");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Ordens de Entrega - GOOD");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowNewAccessories")).click();
		driver.findElement(By.id("allowExistingComponents")).click();
		driver.findElement(By.id("allowNewComponents")).click();
		driver.findElement(By.id("allowExistingParts")).click();
		driver.findElement(By.id("allowNewParts")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.id("allowNewProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Remessa Prestador para TEFTI");
		new Select(driver.findElement(By.id("workflow.id"))).selectByVisibleText("Remessa Prestador para TEFTI");
		driver.findElement(By.id("allowExistingAccessories")).click();
		driver.findElement(By.id("allowExistingProducts")).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV ShipmentOrderType finalizado");

	}

	@Test(priority = 59)
	public void adicionandoShipmentOrderTypeParaPrestadores() throws Exception {
		System.out.println("DEV adicionandoShipmentOrderTypeParaPrestadores iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Service Provider"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Service Provider"))).click();
		try {
			driver.findElement(By.id("alias")).clear();
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		}
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("Help Desk");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		driver.get(this.testSuiteHelper.getBaseUrl() + "/serviceProvider/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("TEFTI Barueri");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Null -> Contratante (BAD)");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Null -> Contratante (GOOD)");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Null -> Service Provider (BAD)");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Null -> Service Provider (GOOD)");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Contratante (BAD)");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Contratante (GOOD)");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Ingresso de Equipamentos - BAD");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Ordens de Entrega - GOOD");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(500);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Remessa Prestador para TEFTI");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		driver.get(this.testSuiteHelper.getBaseUrl() + "/serviceProvider/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("Vortex");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Ordens de Entrega - GOOD");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		driver.get(this.testSuiteHelper.getBaseUrl() + "/serviceProvider/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("Smart");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		driver.get(this.testSuiteHelper.getBaseUrl() + "/serviceProvider/list");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("Smartf1");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		webDriverWait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]/a[1]"))).click();
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("shipmentOrderType"))).selectByVisibleText("Service Provider -> Service Provider");
		driver.findElement(By.id("add_shipment_order_types_button")).click();
		System.out.println("DEV adicionandoShipmentOrderTypeParaPrestadores finalizado");

	}

	@Test(priority = 60)
	public void test28createRepairLevel() throws Exception {
		System.out.println("DEV test28createRepairLevel iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl());

		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			driver.findElement(By.linkText("Repair Level"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Repair Level"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("A");
		new Select(driver.findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		driver.findElement(By.xpath("//button[@value='Create']")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("B");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		System.out.println("DEV test28createRepairLevel finalizado");
	}

	@Test(priority = 61)
	public void createOtherUsersByAdm() throws Exception {
		System.out.println("DEV OtherUsersByAdm iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		// * ADMINISTRADOR
		// Administrador (OK)
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		driver.findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("dfmalafaia");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Diego Malafaia");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("diego.malafaia@avatek.com.br");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Administrator");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.linkText("Select All")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Supervisor HelpDesk (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("supervisor");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("supervisor");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("supervisor@helpdesk.com");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Supervisor");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Help Desk");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_TAB")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_CREATE")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_PRODUCTION_PLANNING")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_DELETE")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		driver.findElement(By.id("ROLE_USER")).click();
		driver.findElement(By.id("ROLE_USER_PERMISSION")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Supervisor TEFTI (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("sup_tefti");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Supervisor TEFTI");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("supervisor@tefti.com");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Supervisor");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("TEFTI Barueri");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_TAB")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_CREATE")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_PRODUCTION_PLANNING")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_DELETE")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		driver.findElement(By.id("ROLE_USER")).click();
		driver.findElement(By.id("ROLE_USER_PERMISSION")).click();
		driver.findElement(By.id("ROLE_IMPORT_PROCESS_BANRISUL")).click();
		driver.findElement(By.id("ROLE_IMPORT_PROCESS")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_ADMIN")).click();
		driver.findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT_ADMIN")).click();
		driver.findElement(By.id("ROLE_EXPORT_BANRISUL")).click();
		driver.findElement(By.id("ROLE_CUSTOMER")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Service Provider Supervisor (Tenant) (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("sps_tefti");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("SP Supervisor TEFTI");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("sp_supervisor@tefti.com");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Supervisor");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("TEFTI Barueri");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_TAB")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		driver.findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		driver.findElement(By.id("ROLE_USER")).click();
		driver.findElement(By.id("ROLE_USER_PERMISSION")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Service Provider Supervisor (Não Tenant) (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("sps_smart");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("sp_supervisor_smart");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("sp_supervisor@smart.com");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Supervisor");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Smart");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_TAB")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		driver.findElement(By.id("ROLE_USER")).click();
		driver.findElement(By.id("ROLE_USER_PERMISSION")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		// Warning: verifyTextPresent may require manual changes
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Supervisor de Laboratorio (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("adm_lab");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Adm_Lab");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("adm_lab@vortex7.com.ar");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Laboratory Supervisor");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_CONTRACTOR")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		driver.findElement(By.id("ROLE_QUALITY_EVALUATION")).click();
		driver.findElement(By.id("ROLE_DELIVER_PIECES")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER")).click();
		driver.findElement(By.id("ROLE_PRODUCTION_PLANNING")).click();
		driver.findElement(By.id("ROLE_REQUEST_PIECES")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_DELETE")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT_ADMIN")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		driver.findElement(By.id("ROLE_USER")).click();
		driver.findElement(By.id("ROLE_USER_PERMISSION")).click();
		driver.findElement(By.id("ROLE_CONSUMPTION_REPORT")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Contratante Banrisul (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("contratante");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Contratante Banrisul");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("contratante@banrisul.com.br");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Contractor");
		new Select(driver.findElement(By.id("contractor.id"))).selectByVisibleText("BANRISUL");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes

		driver.findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_ONLY_VIEW")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		// Warning: verifyTextPresent may require manual changes

		// Logistic Techniccian Vortex (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("lt_vortex");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Logistic Technician Vortex");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("log_tech@vortex.com.br");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Logistic Technician");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes

		driver.findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER")).click();
		driver.findElement(By.id("ROLE_REQUEST_PIECES")).click();
		driver.findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		driver.findElement(By.id("ROLE_LOCATION")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_DELIVER_PIECES")).click();
		driver.findElement(By.id("ROLE_IMPORT_PROCESS")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		// Warning: verifyTextPresent may require manual changes
		// Logistic Technician TEFTI (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("lt_tefti");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Logistic Technician TEFTI");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("log_tech@tefti.com.br");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Logistic Technician");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("TEFTI Barueri");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		// Warning: verifyTextPresent may require manual changes
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER")).click();
		driver.findElement(By.id("ROLE_REQUEST_PIECES")).click();
		driver.findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		driver.findElement(By.id("ROLE_LOCATION")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_DELIVER_PIECES")).click();
		driver.findElement(By.id("ROLE_IMPORT_PROCESS")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Logistic Technician Smart (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("lt_smart");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Logistic Technician SMART");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("log_tech@smart.com.br");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Logistic Technician");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Smart");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER_EDIT")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT_ORIGIN")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER")).click();
		driver.findElement(By.id("ROLE_REQUEST_PIECES")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		driver.findElement(By.id("ROLE_LOCATION")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_ORDER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_DELIVER_PIECES")).click();
		driver.findElement(By.id("ROLE_IMPORT_PROCESS")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		testSuiteHelper.logout(driver);
		// ** SERVICE PROVIDER NAO TENANT (SMART)
		// Service Provider Attendant Smart (OK)
		System.out.println("DEV OtherUsersByAdm finalizado");

	}

	@Test(priority = 62)
	public void createOtherUsersBySpsSmart() throws Exception {
		System.out.println("DEV OtherUsersBySpsSmart iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/logout/");
		testSuiteHelper.login(driver, "sps_smart", "123456");
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		driver.findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("spa_smart");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Service Provider Attendant Smart");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("spa@smart.com.br");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Attendant");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Service Provider Technician Smart (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("spt_smart");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Service Provider technician Smart");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("spt@smart.com.br");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Tecnico Mobile
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("tec_mob");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Técnico Mobile");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Tecnico Campo TEFTI 1 (OK)
		testSuiteHelper.logout(driver);
		// *** SERVICE PROVIDER TENANT (TEFTI) (OK)
		// Tecnico Mobile
		System.out.println("DEV OtherUsersBySpsSmart finalizado");
	}

	@Test(priority = 62)
	public void createOtherUsersBySpsTEFTI() throws Exception {
		System.out.println("DEV OtherUsersBySpsTEFTI iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/logout/");
		testSuiteHelper.login(driver, "sps_tefti", "123456");
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		driver.findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("tec_tefti1");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Técnico Campo Tefti 1");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		driver.findElement(By.id("btnSavePermission")).click();
		// Tecnico Campo TEFTI 2 (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("tec_tefti2");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Técnico Campo Tefti 2");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Service Provider Technician");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		testSuiteHelper.logout(driver);
		// **** Help Desk
		// Atendente Help Desk (OK)
		System.out.println("DEV OtherUsersBySpsTEFTI finalizado");
	}

	@Test(priority = 63)
	public void createOtherUsersBySupervisor() throws Exception {
		System.out.println("DEV OtherUsersBySupervisor iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/logout/");
		testSuiteHelper.login(driver, "supervisor", "123456");
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		driver.findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("atendente");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Atendente Help Desk");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("atendente@helpdesk.com");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Attendant");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Help Desk");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_CUSTOMER")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_CREATE")).click();
		driver.findElement(By.id("ROLE_USER_VIEW_ALL_SERVICE_ORDERS")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_SERVICE_PROVIDER")).click();
		driver.findElement(By.id("ROLE_SERVICE_ORDER_HISTORY")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		testSuiteHelper.logout(driver);
		System.out.println("DEV OtherUsersBySupervisor finalizado");
	}

	@Test(priority = 64)
	public void createOtherUsersByLaboratorySupervisor() throws Exception {
		System.out.println("DEV OtherUsersByLaboratorySupervisor iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		// Laboratory Technician 1 (OK)
		driver.get(this.testSuiteHelper.getBaseUrl() + "/logout/");
		testSuiteHelper.login(driver, "adm_lab", "123456");
		driver.get(this.testSuiteHelper.getBaseUrl());
		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		driver.findElement(By.cssSelector("i.fa.fa-users")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("tec_lab1");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Técnico de Laboratório 1");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("tec_lab@vortex7.com.ar");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Laboratory Technician");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_DELIVER_PIECES")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("ROLE_CONSUMPTION_REPORT")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER")).click();
		driver.findElement(By.id("ROLE_REQUEST_PIECES")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Laboratory Technician 2 (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("tec_lab2");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("Técnico de Laboratório 2");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("tec_lab@vortex8.com.ar");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Laboratory Technician");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_DELIVER_PIECES")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("ROLE_CONSUMPTION_REPORT")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER")).click();
		driver.findElement(By.id("ROLE_REQUEST_PIECES")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_EQUIPMENT")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		// Quality Technician (OK)
		driver.findElement(By.name("_action_index")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("qa");
		driver.findElement(By.id("newPassword")).clear();
		driver.findElement(By.id("newPassword")).sendKeys("123456");
		driver.findElement(By.id("repeatNewPassword")).clear();
		driver.findElement(By.id("repeatNewPassword")).sendKeys("123456");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		driver.findElement(By.id("name")).sendKeys("QA");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("qa@vortex7.com.ar");
		new Select(driver.findElement(By.id("language.id"))).selectByVisibleText("English, en_US (English)");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("profile")));
		new Select(driver.findElement(By.id("profile"))).selectByVisibleText("Quality Technician");
		new Select(driver.findElement(By.id("serviceProvider.id"))).selectByVisibleText("Vortex");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.validateMessageSuccess("created");
		driver.findElement(By.id("ROLE_QUALITY_EVALUATION")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_MASTER_REPORT")).click();
		driver.findElement(By.id("ROLE_VIEW_OTHER_STOCK")).click();
		driver.findElement(By.id("ROLE_SHIPMENT_CLOSED_AND_REPAIR_ORDER")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER_EXPORT")).click();
		driver.findElement(By.id("ROLE_CONSUMPTION_REPORT")).click();
		driver.findElement(By.id("ROLE_ATTACHMENT")).click();
		driver.findElement(By.id("ROLE_REPAIR_ORDER")).click();
		driver.findElement(By.id("ROLE_STOCK")).click();
		driver.findElement(By.id("btnSavePermission")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/div/div/div[2]/h4"), "Permission saved"));
		testSuiteHelper.logout(driver);
		System.out.println("DEV OtherUsersByLaboratorySupervisor finalizado");
	}

	@Test(priority = 65)
	public void createSecretQuestion() throws Exception {
		System.out.println("DEV SecretQuestion iniciado");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 20);
		driver.get(this.testSuiteHelper.getBaseUrl() + "/logout");
		driver.get(this.testSuiteHelper.getBaseUrl() + "/login/auth");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Forgotten password"))).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("Usuario");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div[3]/div/div/div/div/div/div/div[2]/h4"), "User not found"));
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("dfmalafaia");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div[3]/div/div/div/div/div/div/div[2]/h4"), "User do not have a secret question"));
		// /html/body/div[2]/div[3]/div/div/div/div/div/div/div[2]/h4
		driver.get(this.testSuiteHelper.getBaseUrl() + "/login/auth");
		testSuiteHelper.login(driver, "dfmalafaia", "123456");
		driver.get(this.testSuiteHelper.getBaseUrl() + "/profile/changeSecretPhrase");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).clear();
		driver.findElement(By.id("password")).sendKeys("1234567");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("secretQuestion"))).clear();
		driver.findElement(By.id("secretQuestion")).sendKeys("Whats Your Name?");
		driver.findElement(By.id("secretAnswer")).clear();
		driver.findElement(By.id("secretAnswer")).sendKeys("Diego");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("/html/body/div[2]/div/div/div[1]/div/form/div[1]/div[1]/div/div/div/div[2]/div/li"),
				"[Current Password] is invalid"));
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).clear();
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("secretQuestion")).clear();
		driver.findElement(By.id("secretQuestion")).sendKeys("Whats Your Name?");
		driver.findElement(By.id("secretAnswer")).clear();
		driver.findElement(By.id("secretAnswer")).sendKeys("Diego");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		testSuiteHelper.logout(driver);
		System.out.println("DEV SecretQuestion finalizado");
	}
}
