package careman.demo;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.gargoylesoftware.htmlunit.BrowserVersion;

import br.com.workfinity.web.page.AddressBlock;
import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.StringUtils;
import br.com.workfinity.web.page.TableUtils;
import br.com.workfinity.web.page.TableUtils.OPTIONS;
import br.com.workfinity.web.page.contractRental.ContractRentalListPage;
import br.com.workfinity.web.page.contractor.ContractorListPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.importProcess.ImportProcessFormCrudPage;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.openingHours.OpeningHoursListPage;
import br.com.workfinity.web.page.perfil.ProfileSettingsCrudForm;
import br.com.workfinity.web.page.serviceAreas.ServiceAreaCrudForm;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.serviceProvider.ServiceProviderCrudFormPage;
import br.com.workfinity.web.page.serviceProvider.ServiceProviderListPage;
import br.com.workfinity.web.page.status.StatusListPage;
import br.com.workfinity.web.page.stock.StockControlListPage;
import br.com.workfinity.web.page.user.UserCrudFormPage;
import br.com.workfinity.web.page.user.UserListPage;
import br.com.workfinity.web.page.user.UserShowPage;
import br.com.workfinity.web.page.workPack.WorkPackCrudForm;
import br.com.workfinity.web.page.workPack.WorkPackListPage;
import br.com.workfinity.web.page.workflow.WorkflowListPage;
import br.com.workfinity.web.page.workflow.WorkflowShowPage;
import careman.html.TestBase;
import careman.html.TestSuiteHelper;
import careman.util.FileLoader;
import web.utils.HtmlUtils;

/**
 * @author gustavo
 * 
 */
public class Example01 extends TestBase {

	private static AddressBlock addressBlock;
	
	public static void main(String[] args) throws Exception {

		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);

		
		// checkboxInTable();
		// checkDDValues();
		// createActivity();
		// checkItemsTableIfCheckboxIsUnchecked();
		// testeFilter();
		// testeConsoleUser();
		// tableFindRow();
		// tableTableResultList();
		// takeScreenShot();
		// test();
		// readFileFromLoadDataAndTransformToString();
		// testFromGustavo("Repeat New Password is required","New Password is
		// required");
		// testFromGustavo("Repeat New Password is required","New Password is
		// required");
		// testeDeLoginViaLoginPage();
		// testeUserCrudPage();
		// testeUserShowPermissionsPage();
		// testeUserShowPermissionsPageDiselectAllSelectAll();
		// testeUserListPage();
		// testeFindAndGetTextInElement();
		// testeInserirValorVazioEmUmCampo();
		// deletarItensDeUmaTabela(1);
		// testeDeInsercaoDeValorEmUmCampo();
		// encontraUmaLinhaNaTabela();
		// testeDeExclusaoDeOpeningHours();
		// testeDoMetodoFindOneRowInTable();
		// separadorDeString();
		// testeViaSeleniumGrid();
		// testeClearSpecialString();
		// testeTime();
		// testeConcatenarString();
		// testeLerArquivoDeTexto();
//		 testeAlteraTodosParametros();
		// tableUtils();
		// randomNumber();
		// arrayListTeste(Arrays.asList("passarinho", "Abobrinha",
		// "TimeTurner"));
		// testeCampoFamiliStockControl();
//		 testeData();
//		testeImportProcess();
//		testandoAutoCompleteMultiplePluginSelect2();
//		testandoSplitEIndexOfDeURL();
//		testandoRadioBoxMatriz();
//		testeAdicionarPermissaoNoProfileSettings();
//		testeDataSubtraindoSegundos();
//		testeAutoCompleteSelector2ComClick();
//		testePegarCodigoDoPrestadorDeServicoEEditaOPrestadorComOCodigo();
//		testandoOWaitOneTimeInPage();
		testandoPegarIdDeUmaOrdemDeServico();
		 
	}
	
	private static void testandoPegarIdDeUmaOrdemDeServico() {
		
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/bianca/Applications/Firefox/firefox-40.0/firefox")),
				new FirefoxProfile());
		
		driver.get("http://localhost:8080/osmanager-tenant/login/auth");	
		
		new LoginPage(driver, "bianca", "123456").buttonSignInSuccess();
		
		driver.get("http://localhost:8080/osmanager-tenant/serviceOrder/edit/2");
		
		ServiceOrderCrudForm serviceOrderCrudForm = new ServiceOrderCrudForm(driver);
		
		String id = serviceOrderCrudForm.getId();
		
		System.out.println("esse é o id: " + id);
	}
	
	private static void testandoOWaitOneTimeInPage() {
		
		WebDriver driver = Example01.login(
				"http://localhost:8080/osmanager-tenant", 
				"bianca", 
				"123456", 
				false, 
				"http://localhost:8080/osmanager-tenant/workPack/"
				);		
		
		System.out.println(new Date());
		HtmlUtils.waitOneTimeInPage(driver, 5000);		
		System.out.println(new Date());		
	}
	
	private static void testePegarCodigoDoPrestadorDeServicoEEditaOPrestadorComOCodigo() {
		
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/bianca/Applications/Firefox/firefox-40.0/firefox")),
				new FirefoxProfile());
		
		driver.get("http://localhost:8080/osmanager-tenant/workPack/create");
		
		new LoginPage(driver, "WORKPACK_USER_123456", "123456").buttonSignInSuccess();
		
		driver.get("http://localhost:8080/osmanager-tenant/workPack/create");
		
		WorkPackCrudForm workPackCrudForm = new WorkPackCrudForm(driver);

		workPackCrudForm.setServiceProvider("SERVICE_PROVIDER_MATRIZ_123456");
		workPackCrudForm.buttonCreate();
		
		workPackCrudForm.validateMessageSuccessCreated();
		String codigo = workPackCrudForm.getCodeWorkPack();
		
		System.out.println(codigo);
		
		driver.get("http://localhost:8080/osmanager-tenant/workPack/index");
		
		WorkPackListPage workPackListPage = new WorkPackListPage(driver);
		
		workPackListPage.setServiceProvider("SERVICE_PROVIDER_MATRIZ_123456");
		workPackListPage.setStatus("Aberto");
		workPackListPage.buttonSearch();

		workPackListPage.validateIfContainsTheRowInTable(Arrays.asList(codigo, "SERVICE_PROVIDER_MATRIZ_123456", "Aberto"));
		int workPackTableRowNumber = workPackListPage.getRowNumberOfColumn(codigo);

		workPackCrudForm = workPackListPage.editItemTable(workPackTableRowNumber);
	}
	
		
	private static void testeAutoCompleteSelector2ComClick(){
		
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/bianca/Applications/Firefox/firefox-40.0/firefox")),
				new FirefoxProfile());
		
		driver.get("http://localhost:8080/osmanager-tenant/workPack/create");
		
		new LoginPage(driver, "bianca", "123456").buttonSignInSuccess();
		
		driver.get("http://localhost:8080/osmanager-tenant/workPack/create");
		
		WorkPackCrudForm workPackCrudForm = new WorkPackCrudForm(driver);
		
		workPackCrudForm.setServiceProvider("PRESTADOR DE SERVIÇO GENERICO");
		
		
	}
	
	private static void testeDataSubtraindoSegundos() {
		
		Calendar dataAtual = Calendar.getInstance();
		Calendar dataAtualDiminuida = Calendar.getInstance();
		
		dataAtualDiminuida.add(Calendar.SECOND, -60);
		
		System.out.println(dataAtual.getTime());
		System.out.println(dataAtualDiminuida.getTime());
	}
	
	private static void testeAdicionarPermissaoNoProfileSettings() {
		
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/bianca/Applications/Firefox/firefox-40.0/firefox")),
				new FirefoxProfile());
		
		driver.get("http://localhost:8080/osmanager-tenant/profileSettings/edit/1");
		
		new LoginPage(driver, "bianca", "123456").buttonSignInSuccess();
		
		driver.get("http://localhost:8080/osmanager-tenant/profileSettings/edit/1");
		
		
		ProfileSettingsCrudForm profileCrudForm = new ProfileSettingsCrudForm(driver);
		
		profileCrudForm.validateIfContainsTheRowInTable(Arrays.asList("Cancelado"));
		int linha = profileCrudForm.getRowNumber(Arrays.asList("Cancelado"));
		
		profileCrudForm.editItemTable(linha);
		
		profileCrudForm.validateIfContainsTheRowInTable(Arrays.asList("Em Campo"));
		linha = profileCrudForm.getRowNumber(Arrays.asList("Em Campo"));
		
		profileCrudForm.editItemTable(linha);
		
		
	}
	
	private static void testandoRadioBoxMatriz() {
		
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/bianca/Applications/Firefox/firefox-40.0/firefox")),
				new FirefoxProfile());
		
		driver.get("http://localhost:8080/osmanager-tenant/serviceProvider/create");
		
		new LoginPage(driver, "bianca", "123456").buttonSignInSuccess();
		
		driver.get("http://localhost:8080/osmanager-tenant/serviceProvider/create");
		
		ServiceProviderCrudFormPage serviceProviderCrudFormPage = new ServiceProviderCrudFormPage(driver);
		
		serviceProviderCrudFormPage.setRadioMatriz(false);
	}
	
	private static void testandoSplitEIndexOfDeURL() {
		
		String a = "http://localhost:8080/osmanager-tenant/serviceProvider/show/1";
		
//		String parts[] = a.split("[\\W]");
//		
//		for (String i:parts){
//		System.out.println(i);
//		}
		
		String subString = a.substring(a.indexOf("show/") + 5);
		
		System.out.println(subString);
	}

	private static void testandoAutoCompleteMultiplePluginSelect2() {
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/bianca/Applications/Firefox/firefox-40.0/firefox")),
				new FirefoxProfile());
		
		driver.get("http://localhost:8080/osmanager-tenant/rentalAgreement/index");
		
		new LoginPage(driver, "bianca", "123456").buttonSignInSuccess();
		
		driver.get("http://localhost:8080/osmanager-tenant/rentalAgreement/index");
		
		HtmlUtils.autoCompleteMultiplePluginSelect2(driver, "serviceProviderIdList", "PS2");
		
		ContractRentalListPage contractRentalListPage = new ContractRentalListPage(driver);
		
		contractRentalListPage.buttonSearch();
		
	}
		
		
		
	private static void testeImportProcess() {
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/gustavo/Applications/Firefox/firefox-40.0/firefox")),
				new FirefoxProfile());

		driver.get("http://localhost:8080/osmanager-tenant/importProcess/create");

		new LoginPage(driver, "gustavo", "123456").buttonSignInSuccess();
		
		ImportProcessFormCrudPage crudPage = new ImportProcessFormCrudPage(driver);
		ImportProcessListPage importProcessList = crudPage.setType("Equipamento")
				.setFile(System.getProperty("user.dir") + "//src/test/resources/ConsumoEmUmaOSPrestadorDeServicoMatriz.csv").buttonCreate();
		
		if (!importProcessList.isProcessed()) {
			System.out.println("Sucesso!!!");
		} else {
		System.out.println("Não entrou no looping");
		}
	}

	private static void testeData() {

		Calendar inicioDaOperacao = Calendar.getInstance();
		Calendar horaLimiteDaOperacao = Calendar.getInstance();
		horaLimiteDaOperacao.add(Calendar.SECOND, 30);

		boolean looping = true;
		while (looping) {
			System.out.println(inicioDaOperacao.getTime());
			System.out.println(inicioDaOperacao.getTimeInMillis());
			System.out.println(horaLimiteDaOperacao.getTime());
			System.out.println(horaLimiteDaOperacao.getTimeInMillis());

			System.out.println(inicioDaOperacao.getTimeInMillis() < horaLimiteDaOperacao.getTimeInMillis());
			System.out.println(inicioDaOperacao.compareTo(horaLimiteDaOperacao));

			inicioDaOperacao.add(Calendar.SECOND, 2);

			looping = inicioDaOperacao.getTimeInMillis() <= horaLimiteDaOperacao.getTimeInMillis();
			if (!looping) {
				System.out.println(inicioDaOperacao.getTime());
				System.out.println(inicioDaOperacao.getTimeInMillis());
				System.out.println(horaLimiteDaOperacao.getTime());
				System.out.println(horaLimiteDaOperacao.getTimeInMillis());

				System.out.println(inicioDaOperacao.getTimeInMillis() < horaLimiteDaOperacao.getTimeInMillis());
				System.out.println(inicioDaOperacao.compareTo(horaLimiteDaOperacao));
			}
		}

	}

	private static void testeCampoFamiliStockControl() throws MalformedURLException {
		/*
		 * DesiredCapabilities capability = DesiredCapabilities.firefox();
		 * capability.setBrowserName("firefox");
		 * capability.setPlatform(Platform.LINUX);
		 * 
		 * RemoteWebDriver driver = new RemoteWebDriver(new
		 * URL("http://localhost:4441/wd/hub"), capability);
		 */
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/gustavo/Applications/Firefox/firefox-40.0/firefox")),
				new FirefoxProfile());

		driver.get("http://localhost:8080/osmanager-tenant/stock/index");

		new LoginPage(driver, "gustavo", "123456").buttonSignInSuccess();

		StockControlListPage stockControlListPage = new StockControlListPage(driver);

		stockControlListPage.setFamily("FAMILIA");

		stockControlListPage.setServiceProvider("SERVICE_PROVIDER");

		stockControlListPage.buttonSearch();

		driver.close();

	}

	private static void arrayListTeste(List<String> arguments) {
		System.out.println(arguments.toString() + "\n\n");
		System.out.println(arguments);
	}

	private static void randomNumber() {
		Random gerador = new Random();
		String retorno = "";

		for (int a = 0; a < 10; a++) {
			for (int i = 0; i < 5; i++) {
				retorno += gerador.nextInt(10);
			}

			System.out.println(retorno);
			retorno = "";
		}
	}

	private static void tableUtils() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.LINUX);

		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), capability);

		driver.get("http://localhost:8080/osmanager-tenant/serviceOrder/list");

		new LoginPage(driver, "sidneyaraujo", "123456").buttonSignInSuccess();

		ServiceProviderListPage listPage = new ServiceProviderListPage(driver);

		listPage.buttonSearch();

		new WebDriverWait(driver, 300).until(ExpectedConditions.visibilityOfElementLocated(By.className("pace-done")));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement elTable = driver.findElement(By.id("resultList"));

		TableUtils.validateHeader(elTable, TableUtils.OPTIONS.CHECKBOX, OPTIONS.EMPTY, "Code", "Contractor Ext. ID", "Status", "Document",
				"Contractor", "City", "State", "Customer", "Service", "Opening Date", "DeadLine", "Routed at", "Closed Date",
				"Registered Closed Date", "Service Provider", "Technician");
	}

	private static void testeAlteraTodosParametros() throws IOException {
		String a = new FileLoader().load("/scriptsGroovy/workflowCreate");
		String b = StringUtils.replaceAll(a, Arrays.asList("%name%", "%workflowType%"), Arrays.asList("", "SERVICE_ORDER"));
		System.out.println(b);
	}

	private static void testeLerArquivoDeTexto() throws IOException {
		String a = new FileLoader().load("/scriptsGroovy/contractorCreate");
		System.out.println(a);

		System.out.println("\n\n====== ALTERANDO O AS STRINGS ==========\n\n");
		String b = a.replace("'ENABLED'", "Avião vermelho pilotador por um elefante");

		System.out.println(b);
	}

	private static void testeConcatenarString() {
		List<String> stringArray = Arrays.asList("Batatinha quando", " nasce se esparrama pelo chão", " o amor que tu me tinhas");

		String concatenar = "";
		for (String s : stringArray) {
			concatenar += s;
		}

		// System.out.println(stringArray.);
	}

	private static void testeTime() {
		String END_AT = "16:00";

		System.out.println(END_AT);

		int a = Integer.parseInt(END_AT.replace("0", "").replaceAll("\\W", ""));

		System.out.println(a);
	}

	private static void testeClearSpecialString() {
		String a = "aso.234.dks/";
		System.out.println(a);

	}

	private static void testeViaSeleniumGrid() throws Exception {

		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.LINUX);

		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), capability);

		driver.get("http://localhost:8080/osmanager-tenant/user/index");
	}

	private static void separadorDeString() {
		String teste = "12345CT67890123";
		String testeJuncao = "";
		String testeDivisao[] = teste.split("CT");
		String testeDivisao2 = teste.substring(0, 5);
		String testeDivisao3 = teste.substring(7);
		for (int i = 0; i < testeDivisao.length; i++) {
			System.out.println("stringDividida[" + i + "]=" + testeDivisao[i]);
			testeJuncao += testeDivisao[i];
		}

		System.out.println("_______________________________________\n" + testeJuncao);
		System.out.println("\nO teste 2 retornou = " + testeDivisao2);
		System.out.println("\nO teste 3 retornou = " + testeDivisao3);
		// System.out.println(teste.indexOf("CT"));
		// System.out.println("\n" + teste.lastIndexOf("CT"));

	}

	private static void testeDoMetodoFindOneRowInTable() {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://127.0.0.1:8080/osmanager-tenant/stepStatus/list");
		new LoginPage(driver, "sidneyaraujo", "123456").buttonSignInSuccess();

		StatusListPage statusListPage = new StatusListPage(driver);

		statusListPage.editItemTable(2);
	}

	private static void testeDeExclusaoDeOpeningHours() {
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/user/index", "sidneyaraujo", "123456", false,
				"http://localhost:8080/osmanager-tenant/openingHours/list");

		List<WebElement> elements = driver.findElements(By.cssSelector("table > tbody > tr"));

		for (WebElement webElement : elements) {
			System.out.println(webElement.getText());
		}

		OpeningHoursListPage oh = new OpeningHoursListPage(driver);

		oh.deleteItemTable(oh.returnOneRowInTableFindByText(Arrays.asList("Friday", "08:00", "18:00")));

		System.out.println("\n____________________________________________________________________________________________________\n");

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("table > tbody > tr"))));

		for (WebElement webElement : elements) {
			System.out.println(webElement.getText());
		}
	}

	private static void encontraUmaLinhaNaTabela() {
		boolean executa = false;
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/user/index", "gustavo", "123456", false,
				"http://localhost:8080/osmanager-tenant/user/list");

		List<String> list = new ArrayList<String>();
		list.add("dfmalafaia");
		list.add("Diego Malafaia");
		list.add("Administrator");
		list.add("6");
		list.add("diego.malafaia@avatek.com.br");

		WebElement text = driver.findElement(By.cssSelector("table > tbody")).findElement(By.xpath("//tr[" + 3 + "]/td[" + 4 + "]"));
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(text));

		List<WebElement> texts = driver.findElements(By.cssSelector("table > tbody > tr"));

		// for (WebElement webElement : texts) {
		// if (webElement.getText().contains("dfmalafaia")) {
		//
		// if (webElement.getText().contains("Diego Malafaia")) {
		//
		// if (webElement.getText().contains("Administrator")) {
		// System.out.println(webElement.getText());
		// }
		// }
		// }
		// }
		int validacao = 0, a = 1;
		for (WebElement webElement : texts) {
			for (int i = 0; i < list.size(); i++) {
				if (webElement.getText().contains(list.get(i))) {
					validacao++;
					if (validacao == list.size()) {
						System.out.println(webElement.getText());
						System.out.println("\n" + a);
						break;
					}
				}
			}
			validacao = 0;
			a++;
		}

		// for (WebElement webElement : texts) {
		// if (webElement.getText().contains("dfmalafaia") &&
		// webElement.getText().contains("Diego Malafaia") &&
		// webElement.getText().contains("Administrator")) {
		// System.out.println(webElement.getText());
		// }
		// }

		// .findElements(By.xpath("//tr[" + 3 + "]/td[" + 4 + "]"));

		if (executa) {
			WebDriver driverFirefox = new FirefoxDriver();
			driverFirefox.get("http://www.w3schools.com/html/html_tables.asp");

			WebElement table;

			table = driverFirefox.findElement(By.cssSelector("table > tbody")).findElement(By.xpath("//tr[" + 2 + "]/td[" + 3 + "]"));
			System.out.println(table.getText() + "\n");
			table = driverFirefox.findElement(By.cssSelector("table > tbody")).findElement(By.xpath("//tr[" + 2 + "]/td[" + 4 + "]"));
			System.out.println(table.getText() + "\n");
			table = driverFirefox.findElement(By.cssSelector("table > tbody")).findElement(By.xpath("//tr[" + 4 + "]/td[" + 2 + "]"));
			System.out.println(table.getText() + "\n");
			table = driverFirefox.findElement(By.cssSelector("table > tbody")).findElement(By.xpath("//tr[" + 3 + "]/td[" + 3 + "]"));
			System.out.println(table.getText() + "\n");

		}
		// driver.findElement(By.id("resultList")).findElement(by)
		// System.out.println(driver.findElement(By.cssSelector("table >
		// tbody")).findElement(By.xpath("//tr["
		// + 2 + "]/td[" + 4 + "]")).isDisplayed());

		// WebElement[] element = new WebElement[5];

		// CODIGO PARA PEGAR O CSS DO ELEMENTO NA PAGINA
		/*
		 * WebElement element =
		 * driver.findElement(By.id("autocomplete_countryId"));
		 * 
		 * System.out.println(element.getCssValue("display").toString());
		 */

	}

	private static void testeDeInsercaoDeValorEmUmCampo() {
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/user/index", "USERNAME_CONTRACTOR_B0JWc", "123456", false,
				"http://localhost:8080/osmanager-tenant/contractor/index");

		ContractorListPage contractorListPage = new ContractorListPage(driver);

		contractorListPage.setDocument("CPF", "175.515.833-51").buttonSearch();

		contractorListPage.filterExpand();
		WebElement fieldSelect = driver.findElement(By.id("documentType"));
		Select select = new Select(fieldSelect);

		select.selectByVisibleText(Messages.ALL.toString());
		WebElement field = driver.findElement(By.id("documentNumber"));
		field.clear();
		field.sendKeys("");
		Assert.assertEquals(field.getAttribute("value"), "", "Falhou ao tentar alterar o campo texto");
	}

	private static void deletarItensDeUmaTabela(int numeroDaLinha) {
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/user/index", "sidneyaraujo", "123456", false,
				"http://localhost:8080/osmanager-tenant/serviceLevelAgreementGroup/list");

		achaTabelaELinhaDesejada(numeroDaLinha, driver).findElement(By.cssSelector("a.fa-edit")).click();

		// driver.findElement(By.xpath("//table[@id='resultList']/tbody/tr[" +
		// numeroDaLinha + "]/td[1]"))
		// .findElement(By.cssSelector("a.fa-remove")).click();
		// driver.switchTo().alert().accept();
	}

	private static WebElement achaTabelaELinhaDesejada(int numeroDaLinha, WebDriver driver) {
		return driver.findElement(By.cssSelector("table > tbody")).findElement(By.xpath("//tr[" + numeroDaLinha + "]/td[1]"));
	}

	private static void testeInserirValorVazioEmUmCampo() {
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/user/index", "sidneyaraujo", "123456", false,
				"http://localhost:8080/osmanager-tenant/contractor/index");

		ContractorListPage contractorListPage = new ContractorListPage(driver);
		contractorListPage.setDocument("CNPJ", "40.103.734/0001-10").buttonSearch();

		contractorListPage.filterExpand();
		contractorListPage.setDocument(Messages.ALL.toString(), "");

		System.out.println("Sucesso");
	}

	private static void testeFindAndGetTextInElement() throws InterruptedException {
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/user/index", "sidneyaraujo", "123456", false,
				"http://localhost:8080/osmanager-tenant/user/index");

		WebElement elementInPage = driver.findElement(By.id("profile"));

		WebElement firstSelectedOption = new Select(elementInPage).getFirstSelectedOption();

		System.out.println(firstSelectedOption.getText());
	}

	private static void testeUserListPage() throws Exception {
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "admin", "Iso1981", false,
				"http://localhost:8080/osmanager-tenant/user");

		UserListPage userListPage = new UserListPage(driver);

		userListPage.setIdSearch("4");
		userListPage.buttonSearch();

		userListPage.filterExpand();
		userListPage.setIdSearch("");

		userListPage.setUsername("gustavo");
		userListPage.buttonSearch();

		userListPage.filterExpand();
		userListPage.setUsername("");

		userListPage.setName("gustavo");
		userListPage.buttonSearch();

		userListPage.filterExpand();
		userListPage.setName("");

		userListPage.setEmail("bla");
		userListPage.buttonSearch();

		userListPage.filterExpand();
		userListPage.setEmail("");

		userListPage.setEnabled("Yes");
		userListPage.buttonSearch();

		userListPage.filterExpand();
		userListPage.setEnabled(Messages.ALL.toString());

		userListPage.setProfile("Attendant");
		userListPage.buttonSearch();

		userListPage.filterExpand();
		userListPage.setProfile(Messages.ALL.toString());

		userListPage.setServiceProfvider("ads");
		userListPage.buttonSearch();

		userListPage.filterExpand();
		userListPage.setServiceProfvider(Messages.ALL.toString());

	}

	private static void testeUserShowPermissionsPageDiselectAllSelectAll() {
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "admin", "Iso1981", false,
				"http://localhost:8080/osmanager-tenant/user/show/5");

		UserShowPage userShowPermissionsPage = new UserShowPage(driver);

		userShowPermissionsPage.deselectAllClick();
		userShowPermissionsPage.clickSelectAll();

		userShowPermissionsPage.clickSavePermission();
	}

	private static void testeUserShowPermissionsPage() {
		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "admin", "Iso1981", false, null);

		driver.get("http://localhost:8080/osmanager-tenant/user/show/5");

		UserShowPage userShowPermissionsPage = new UserShowPage(driver);

		userShowPermissionsPage.setPermission("ROLE_ATTACHMENT", true);
		userShowPermissionsPage.setPermission("ROLE_SOLUTION_LABORATORY", true);
		userShowPermissionsPage.setPermission("ROLE_MODEL", true);
		userShowPermissionsPage.setPermission("ROLE_GROUP", true);
		userShowPermissionsPage.setPermission("ROLE_LOCATION", true);
		userShowPermissionsPage.setPermission("ROLE_USER_VIEW_ALL_SERVICE_ORDERS", true);

		userShowPermissionsPage.clickSavePermission();
	}

	private static void testeUserCrudPage() {

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "admin", "Iso1981", false, null);

		driver.get("http://localhost:8080/osmanager-tenant/user/create");

		UserCrudFormPage userCrudFormPage = new UserCrudFormPage(driver);

		userCrudFormPage.setEnabled(false);
		userCrudFormPage.setUsername("gustavo");
		userCrudFormPage.setNewPassword("123456");
		userCrudFormPage.setRepeatNewPassword("123456");
		userCrudFormPage.setName("Gustavo da Silva do Carmo");
		userCrudFormPage.setEmail("gustavo@hotmail.com");
		userCrudFormPage.setLanguage("English, en_US (English)");
		userCrudFormPage.setProfile("Administrator");
		userCrudFormPage.setServiceProvider("ads");
		userCrudFormPage.setContractor("dfsds");
		userCrudFormPage.setNotes("bla bla bla");

		userCrudFormPage.setAddress(true);

		AddressBlock addressBlock = new AddressBlock(driver);
		addressBlock.setZipCode("13308077");
		addressBlock.setAddress("Rua Blabla");
		addressBlock.setNumber("123");
		addressBlock.setComplement("");
		addressBlock.setDistrict("Blumenau");
		addressBlock.setCountry("Brasil", 1);
		addressBlock.setState("Rio Grande do Sul", 2);
		addressBlock.setCity("Porto Alegre", 3);

		userCrudFormPage.buttonCreate();
	}

	private static void testeDeLoginViaLoginPage() {

		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/osmanager-tenant/login/auth");

		new LoginPage(driver, "admin", "Iso1981").buttonSignInSuccess();
	}

	private static void testFromGustavo(String... errorMensage) throws Exception {

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "admin", "Iso1981", false, null);
		driver.get("http://localhost:8080/osmanager-tenant/user/create");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		validateMessagesErrors(driver, errorMensage);

		driver.close();
	}

	private static void validateMessagesErrors(WebDriver driver, String... errorsRequired) {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("pace-done")));

		List<WebElement> webElementsErrors = driver.findElements(By.xpath("//*[@id='page-alert']/div/div/div/div[2]/div/li"));

		Assert.assertTrue(errorsRequired.length == webElementsErrors.size(),
				"Expected error size [" + errorsRequired.length + "] but foud [" + webElementsErrors.size() + "]");

		List<String> webElementsErrorsString = new ArrayList<String>();
		for (WebElement webElement : webElementsErrors) {
			webElementsErrorsString.add(webElement.getText());
		}

		for (String errorRequired : errorsRequired) {
			Assert.assertTrue(webElementsErrorsString.contains(errorRequired),
					"Error message [" + errorRequired + "] not found in " + webElementsErrorsString.toString());
		}
	}

	private static void readFileFromLoadDataAndTransformToString() {

		String fileName = "/loadData/DevPart01/createDocumentType.txt";

		// Get from tst redorces
		StringBuilder redourceFromTestFolder = new StringBuilder(System.getProperty("user.dir"));
		redourceFromTestFolder.append("/src/test/resources");

		redourceFromTestFolder.append(fileName);

		File f = new File(redourceFromTestFolder.toString());

		if (!f.exists()) {

			System.out.println(f.getAbsolutePath() + " not found");

		} else {

			try {

				List<String> readLines = FileUtils.readLines(f);

				StringBuilder s = new StringBuilder();
				String concatChar = "";

				for (String line : readLines) {
					if (line.length() > 0) {
						s.append(concatChar);
						s.append(line);
						concatChar = "\\n";
					}
				}

				System.out.println(s.toString());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// System.out.println(System.getProperty("user.dir"));
		// System.out.println(System.getProperty("."));

		// TODO Auto-generated method stub

	}

	private static void test() {

		RemoteWebDriver driver = loginWithGrid("http://186.230.33.70:8086/workfinity-qa/login/auth", "aaquino", "abc210281",
				"http://192.168.0.14:4441/wd/hub");

		printCurrentUrlAndPageTile(driver);

		try {

			driver.get("http://186.230.33.70:8086/workfinity-qa/serviceOrder/edit/240988");
			printCurrentUrlAndPageTile(driver);

			WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("change_service"))).click();

			/*
			 * new WebDriverWait(driver, 10).until(new
			 * ExpectedCondition<Boolean>() {
			 * 
			 * @Override public Boolean apply(WebDriver driver) {
			 * System.out.println("Wait for modal ready.....");
			 * System.out.println(((JavascriptExecutor) driver).executeScript(
			 * "return $('#change_service_modal').is(':animated');"));
			 * System.out.println(((JavascriptExecutor) driver).executeScript(
			 * "return $('#change_service_modal div.modal-dialog').is(':animated');"
			 * )); System.out.println(((JavascriptExecutor)
			 * driver).executeScript (
			 * "return $('#change_service_modal_service').is(':animated');"));
			 * System.out.println(((JavascriptExecutor) driver).executeScript(
			 * "return $('#change_service_modal div.modal-dialog').is(':animated');"
			 * )); System.out.println(((JavascriptExecutor)
			 * driver).executeScript(
			 * "return $('#change_service_modal div.modal-dialog').is(':animated') != true;"
			 * )); return (boolean) ((JavascriptExecutor) driver).executeScript(
			 * "return $('#change_service_modal div.modal-dialog').is(':animated');"
			 * ); } });
			 */

			// getscreenshot(driver, "depois 2");
			WebElement s = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("change_service_modal_service")));
			System.out.println(s.isDisplayed());
			System.out.println(s.isEnabled());
			new Select(s).selectByVisibleText("Manutenção Global");

			// driver.close();
			System.out.println("Sucesso....");

		} catch (Exception e) {

			// System.out.println(driver.getPageSource().contains("Change
			// Service To "));
			getscreenshot(driver, new Object() {
			}.getClass().getEnclosingMethod().getName());
			e.printStackTrace();
		}
	}

	private static void takeScreenShot1(WebDriver driver) {
		try {
			System.out.println(driver.findElement(By.id("yschsp1")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			getscreenshot(driver, new Object() {
			}.getClass().getEnclosingMethod().getName());
		}
	}

	private static void getscreenshot(WebDriver driver, String fileNameWithpath) {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File file = new File("target/" + fileNameWithpath + new Date() + ".png");
			FileUtils.copyFile(scrFile, file);
			System.out.println("----------------------------------------------------------------");
			System.out.println("------------------------------------------------");
			System.out.println("File save in: " + file.getAbsolutePath());
			System.out.println("------------------------------------------------");
			System.out.println("----------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void takeScreenShot2(WebDriver driver) {
		try {
			System.out.println(driver.findElement(By.id("yschsp2")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			getscreenshot(driver, new Object() {
			}.getClass().getEnclosingMethod().getName());
		}
	}

	private static void takeScreenShot3(WebDriver driver) {
		try {
			System.out.println(driver.findElement(By.id("yschsp3")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			getscreenshot(driver, new Object() {
			}.getClass().getEnclosingMethod().getName());
		}
	}

	private static void takeScreenShot() {

		WebDriver driver = getWebDriver("http://br.search.yahoo.com/", false);
		printCurrentUrlAndPageTile(driver);

		takeScreenShot1(driver);
		takeScreenShot2(driver);
		takeScreenShot3(driver);

		System.out.println(driver.findElement(By.id("yschsp")));
		driver.close();
	}

	private static void tableTableResultList() throws InterruptedException {

		WebDriver driver = login("http://localhost:8008/osmanager-tenant/login/auth", "openingHoursUserMmw", "123456", false, null);
		printCurrentUrlAndPageTile(driver);

		driver.get("http://localhost:8008/osmanager-tenant/openingHours/index");
		printCurrentUrlAndPageTile(driver);

		searchExecute(driver);

		WebElement table = driver.findElement(By.xpath("//table[@id='resultList']"));
		System.out.println(table.isDisplayed());
		System.out.println(table.isEnabled());

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(table));
		System.out.println(table.isDisplayed());
		System.out.println(table.isEnabled());

		List<WebElement> trs = table.findElements(By.xpath("//tbody/tr"));

		System.out.println("trs");
		System.out.println(trs);

		// Thread.sleep(2000);

		for (WebElement tr : trs) {

			System.out.println(table.isDisplayed());
			System.out.println(table.isEnabled());

			List<WebElement> tds = tr.findElements(By.tagName("td"));

			for (WebElement webElement : tds) {
				// System.out.println("webElement");
				// System.out.println(webElement);
				// System.out.println("webElement.getText()");
				System.out.print(webElement.getText());
				// System.out.println("webElement.getAttribute(\"value\")");
				// System.out.println(webElement.getAttribute("value"));
			}
			System.out.println("---");
		}
	}

	private static void tableFindRow() {
		WebDriver driver = login("http://localhost:8008/osmanager-tenant/login/auth", "openingHoursUserMmw", "123456", false, null);
		printCurrentUrlAndPageTile(driver);

		driver.get("http://localhost:8008/osmanager-tenant/openingHours/index");
		printCurrentUrlAndPageTile(driver);

		searchExecute(driver);

		/*
		 * List<WebElement> trs =
		 * driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr"));
		 * 
		 * System.out.println(trs);
		 * 
		 * for (WebElement tr : trs) { System.out.println(tr); }
		 */

		/*
		 * List<WebElement> w = driver.findElements(By.xpath(
		 * "//table[@id='resultList']/tbody/tr/td[2][text()='Wednesday']"));
		 * System.out.println(w); System.out.println(w.get(0).getText());
		 */

		// List<WebElement> w2 =
		// driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr/td[contains,'Wednesday']
		// and td[contains,'00:02']"));
		// List<WebElement> w2 =
		// driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr[td[contains(text(),'Wednesday')]
		// and td[contains(text(),'00:02')]]"));
		// // OK
		// List<WebElement> w2 =
		// driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr[td[text()='Wednesday']
		// and td[text()='00:02']]"));
		// // ok
		// List<WebElement> w2 =
		// driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr[td[text()='Wednesday']
		// and td[text()='00:02'] and td[text()='00:07'] ]"));
		// // ok

		List<String> data = Arrays.asList("Wednesday", "00:02", "00:07");
		String id = "resultList";

		StringBuilder stringBuilder = new StringBuilder("//table[@id='" + id + "']/tbody/tr[");

		String separator = "";
		for (String d : data) {
			stringBuilder.append(separator);
			separator = " and ";
			stringBuilder.append("td[text()='td-content']".replaceAll("td-content", d));
		}
		stringBuilder.append("]");
		System.out.println(stringBuilder);

		// td[text()='Wednesday'] and td[text()='00:02'] and td[text()='00:07']
		// stringBuilder.append("]");*/

		List<WebElement> trs = driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr"));

		System.out.println("trs");
		System.out.println(trs);

		// WebElement row =
		// driver.findElement(By.xpath("//table[@id='resultList']/tbody/tr[td[text()='Wednesday']
		// and td[text()='00:02'] and td[text()='00:07']]"));
		// // ok
		// WebElement row =
		// driver.findElement(By.xpath(stringBuilder.toString())); // ok
		WebElement row = driver.findElement(By.xpath(stringBuilder.toString())); // ok

		System.out.println(row);
		// System.out.println(row.getAttribute("data-row-index"));
		// System.out.println(w2.get(0).get);

		System.out.println(trs.indexOf(row));

		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();

		// (new WebDriverWait(driver,
		// 10)).until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();

		// driver.findElement(By.id("name")).clear();
		// driver.findElement(By.id("name")).sendKeys("OpeningHoursGroup Create
		// PSJllx");
	}

	private static void testeConsole() {

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "aaquino2", "abc210281", false, null);

		driver.get("http://localhost:8080/osmanager-tenant/util/console");

		driver.findElement(By.id("command")).clear();
		driver.findElement(By.id("command")).sendKeys(
				"import br.com.careman.domain.*\nimport br.com.careman.groovy.enums.OpeningHoursOptions\nimport groovy.time.TimeDuration\n\ndef startAt = new TimeDuration(0, 1, 0, 0)\ndef endAt = new TimeDuration(0, 2, 0, 0)\n\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.MONDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.TUESDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.WEDNESDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.THURSDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.FRIDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.SATURDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.SUNDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.HOLIDAY, startAt: startAt, endAt: endAt).save()");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		Assert.assertEquals(driver.findElement(By.id("1")).getText(), "MONDAY [1 minutes - 2 minutes]");
		Assert.assertEquals(driver.findElement(By.id("2")).getText(), "TUESDAY [1 minutes - 2 minutes]");
		Assert.assertEquals(driver.findElement(By.id("3")).getText(), "WEDNESDAY [1 minutes - 2 minutes]");
		Assert.assertEquals(driver.findElement(By.id("4")).getText(), "THURSDAY [1 minutes - 2 minutes]");
		Assert.assertEquals(driver.findElement(By.id("5")).getText(), "FRIDAY [1 minutes - 2 minutes]");
		Assert.assertEquals(driver.findElement(By.id("6")).getText(), "SATURDAY [1 minutes - 2 minutes]");
		Assert.assertEquals(driver.findElement(By.id("7")).getText(), "SUNDAY [1 minutes - 2 minutes]");
		Assert.assertEquals(driver.findElement(By.id("8")).getText(), "HOLIDAY [1 minutes - 2 minutes]");
	}

	private static void testeConsoleUser() {

		String username = "aaquino66";
		String password = "123456";
		Set<String> roles = new LinkedHashSet<String>() {
			{
				add("ROLE_OPENING_HOURS");
				add("ROLE_ACTIVITY");
			}
		};

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "admin", "Iso1981", false, null);

		driver.get("http://localhost:8080/osmanager-tenant/util/console");
		driver.findElement(By.id("command")).clear();

		String script = "import br.com.careman.domain.*\nimport br.com.careman.groovy.enums.*\n\ndef username = \"--username--\"\ndef password = \"--password--\"\nSet roles = --roles--\n\ndef name = username\ndef email = username + \"@email.com\"\ndef language = Language.findByLocale(\"en_US\")\ndef profile = Profile.ADMINISTRATOR\n\ndef userInstance = new User(\n	username: username,\n	enabled: true,\n	password: password,\n	name: name,\n	email: email,\n	language: language,\n	profile: profile\n).save(flush: true)\n\nif(userInstance) {\n    println userInstance\n	roles.each {\n    	def roleInstance = Role.findByAuthority(it)\n    	if(roleInstance) {\n        	def userRoleInstance = UserRole.create(userInstance, roleInstance, true)\n        	if(userRoleInstance) {\n            	println userRoleInstance.user.username + \"-\" + userRoleInstance.role\n        	}\n    	}\n	}\n} else {\n    println \"user not created\"\n}";
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

		driver.findElement(By.id("command")).sendKeys(script);
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();

		Assert.assertEquals(driver.findElement(By.id("1")).getText(), username);

		Iterator<String> iterator = roles.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			Assert.assertEquals(driver.findElement(By.id(String.valueOf(count + 2))).getText(), username + "-" + iterator.next());
			count++;
		}
	}

	public static void testeFilter() {

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "aaquino", "123456", false, null);
		printCurrentUrlAndPageTile(driver);

		driver.get("http://localhost:8080/osmanager-tenant/openingHoursGroup/index");
		printCurrentUrlAndPageTile(driver);

		// webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();

		// (new WebDriverWait(driver,
		// 10)).until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();

		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("OpeningHoursGroup Create PSJllx");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		System.out.println(driver.findElement(By.name("_action_create")).isDisplayed());
		System.out.println(driver.findElement(By.name("_action_create")).isEnabled());
		printCurrentUrlAndPageTile(driver);

		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("OpeningHoursGroup Create PSJllx");
		driver.findElement(By.xpath("//button[@value='Search']")).click();
		System.out.println(driver.findElement(By.name("_action_create")).isDisplayed());
		System.out.println(driver.findElement(By.name("_action_create")).isEnabled());

		System.out.println("***********");
		driver.findElement(By.name("_action_create")).click();
		System.out.println("***********");
		printCurrentUrlAndPageTile(driver);
		System.out.println("***********");
	}

	public static void checkItemsTableIfCheckboxIsUnchecked() {

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "aaquino2", "abc210281", false, null);

		driver.get("http://localhost:8080/osmanager-tenant/openingHoursGroup/edit/6");

		for (WebElement checkbox : driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr/td/input[@checked='checked']"))) {
			checkbox.click();
			// System.out.println(checkbox.getAttribute("checked"));
			// System.out.println(checkbox.isSelected());
			// System.out.println("**************");
			/*
			 * if(checkbox.isSelected()) { checkbox.click(); }
			 */
		}

		System.out.println(driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr/td/input[@checked='checked']")));
		System.out.println(driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr/td/input[@checked='checked']")).size());
	}

	public static void checkboxInTable() {

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "aaquino2", "abc210281", false, null);

		driver.get("http://localhost:8080/osmanager-tenant/openingHoursGroup/edit/1");

		final ArrayList<String> days = new ArrayList<String>() {
			{
				add("Segunda-feira");
				add("Terça-feira");
				add("Quinta-feira");
				add("Sexta-feira");
				add("Sábado");
				add("Feriado");
			}
		};

		for (WebElement tr : driver.findElements(By.xpath("//table[@id='resultList']/tbody/tr"))) {

			List<WebElement> td = tr.findElements(By.tagName("td"));

			if (days.contains(td.get(1).getText()) && td.get(2).getText().equals("00:01") && td.get(3).getText().equals("00:02")) {
				tr.findElement(By.cssSelector("input[type='checkbox']")).click();
			}
		}

		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		printCurrentUrlAndPageTile(driver);

		driver.quit();
	}

	public static void checkDDValues() {

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "aaquino2", "abc210281", false, null);

		driver.get("http://localhost:8080/osmanager-tenant/openingHoursGroup/show/1");

		printCurrentUrlAndPageTile(driver);

		System.out.println(driver.findElement(By.xpath("//div[@id='page-content']/div/div/dl/dd[1]")).getText());
		System.out.println(driver.findElement(By.xpath("//div[@id='page-content']/div/div/dl/dd[2]")).getText());

		System.out.println("****");

		final String HOUR_START = "00:01";
		final String HOUR_END = "00:02";
		final String HOUR_START_HOUR_END = "[" + HOUR_START + "-" + HOUR_END + "]";
		final Set<String> DAYS_OF_WEEK_UPDATE = new LinkedHashSet<String>() {
			{
				add("Domingo");
				add("Segunda-feira");
				add("Terça-feira");
				add("Quarta-feira");
				add("Sábado");
			}
		};

		List<WebElement> dd = driver.findElement(By.cssSelector("dl.dl-horizontal")).findElements(By.tagName("dd"));
		dd.remove(0);

		Assert.assertEquals(dd.size(), DAYS_OF_WEEK_UPDATE.size());

		Iterator<String> daysOfWeekIterator = DAYS_OF_WEEK_UPDATE.iterator();

		int count = 0;
		while (daysOfWeekIterator.hasNext()) {
			Assert.assertEquals(dd.get(count).getText(), daysOfWeekIterator.next() + " " + HOUR_START_HOUR_END);
			count++;
		}

		printCurrentUrlAndPageTile(driver);

		driver.quit();
	}

	public static void createActivity() {

		WebDriver driver = login("http://localhost:8080/osmanager-tenant/login/auth", "aaquino2", "abc210281", false, null);
		driver.get("http://localhost:8080/osmanager-tenant/activity/create");
		printCurrentUrlAndPageTile(driver);

		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Um nome" + TestSuiteHelper.randomString(2));
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		printCurrentUrlAndPageTile(driver);

		(new WebDriverWait(driver, 10)).until(
				ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='page-alert']/div/div/div/div[2]/h4"), "created"));

		System.out.println(driver.findElement(By.xpath("//div[@id='page-alert']/div/div/div/div[2]/h4")).getText());
	}

	public static WebDriver getWebDriver(String url, Boolean headless) {

		if (headless) {
			// HtmlUnitDriver driver = new
			// HtmlUnitDriver(BrowserVersion.FIREFOX_24);
			// driver.setJavascriptEnabled(true);
			// driver.get(url);
			// return driver;
			return null;
		} else {
			WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("/home/bianca/Applications/Firefox/firefox-40.0/firefox")),
					new FirefoxProfile());
			driver.get(url);
			return driver;
		}
	}

	public static RemoteWebDriver getWebDriverRemote(String hubURL) {

		try {

			DesiredCapabilities capability = DesiredCapabilities.firefox();
			RemoteWebDriver webDriver = new RemoteWebDriver(new URL(hubURL), capability);
			webDriver.manage().window().setSize(new Dimension(1920, 1080));
			return webDriver;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static WebDriver login(String url, String username, String passsword, Boolean headless, String urlGoToAfterLogin) {

		WebDriver driver = getWebDriver(url, headless);

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).clear();

		new LoginPage(driver, username, passsword).buttonSignInSuccess();

		printCurrentUrlAndPageTile(driver);

		if (null != urlGoToAfterLogin) {
			driver.get(urlGoToAfterLogin);
		}

		return driver;
	}

	public static RemoteWebDriver loginWithGrid(String url, String username, String password, String hubURL) {

		RemoteWebDriver driver = getWebDriverRemote(hubURL);
		driver.get(url);
		printCurrentUrlAndPageTile(driver);

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).clear();

		new LoginPage(driver, username, password).buttonSignInSuccess();

		printCurrentUrlAndPageTile(driver);

		return driver;
	}

	public static void printCurrentUrlAndPageTile(WebDriver driver) {
		System.out.println("Page title is: " + driver.getCurrentUrl());
		System.out.println("Page title is: " + driver.getTitle());
	}

	public static void searchExecute(WebDriver webDriver) {

		WebElement bt = webDriver.findElement(By.xpath("//button[@value='Search']"));

		if (!bt.isDisplayed()) {
			new WebDriverWait(webDriver, 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		}

		bt.click();
	}
}