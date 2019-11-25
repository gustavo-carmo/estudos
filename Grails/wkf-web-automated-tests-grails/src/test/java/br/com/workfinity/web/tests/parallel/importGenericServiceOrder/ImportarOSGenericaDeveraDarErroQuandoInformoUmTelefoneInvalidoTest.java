package br.com.workfinity.web.tests.parallel.importGenericServiceOrder;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import br.com.workfinity.web.page.importProcess.ImportProcessShowPage;
import careman.html.TestBase;

public class ImportarOSGenericaDeveraDarErroQuandoInformoUmTelefoneInvalidoTest extends TestBase {

	private static final String USER_IMPORT_SERVICE_ORDER_GENERIC_CUSTOMER_PHONE_NUMBER_INVALID = "USER_ISOG_PHONE_INV_"
			+ randomString(5);

	private MainPage navBar;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, false);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void background() {

		createUserAndDoLogin(USER_IMPORT_SERVICE_ORDER_GENERIC_CUSTOMER_PHONE_NUMBER_INVALID, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	// TODO - ACEITO NÚMERO DE TELEFONE COM LETRA
	@Test(dependsOnMethods = { "background" })
	public void deveraDarErroQuandoNoCampoCustomerTelephoneNumberNaoForInformadoUmTelefoneValido() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Import_SO_Generic.csv");
		importProcessListPage.buttonSearch();

		ImportProcessShowPage importProcessShowPage = importProcessListPage.showItemTable(1);

		// Verificando o Status
		String status = importProcessShowPage.getStatus("status13");
		importProcessShowPage.assertEquals("Erro", status, "Diferentes!");

		// Verificando o detalhe do erro
		String details = importProcessShowPage.getDetails("val13");
		importProcessShowPage.assertEquals("", details, "Diferentes!");
	}
}