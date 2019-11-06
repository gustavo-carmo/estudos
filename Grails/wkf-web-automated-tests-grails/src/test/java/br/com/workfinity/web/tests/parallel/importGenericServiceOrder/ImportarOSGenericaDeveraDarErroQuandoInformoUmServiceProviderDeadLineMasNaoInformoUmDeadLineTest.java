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

public class ImportarOSGenericaDeveraDarErroQuandoInformoUmServiceProviderDeadLineMasNaoInformoUmDeadLineTest
		extends TestBase {

	private static final String USER_IMPORT_SERVICE_ORDER_GENERIC_SERVICE_PROVIDER_DEAD_LINE_WITHOUT_DEAD_LINE = "USER_ISOG_SP_SEM_DL_"
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

		createUserAndDoLogin(USER_IMPORT_SERVICE_ORDER_GENERIC_SERVICE_PROVIDER_DEAD_LINE_WITHOUT_DEAD_LINE, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	@Test(dependsOnMethods = { "background" })
	public void deveraDarErroQuandoNoCampoServiceProviderDeadLineInformadoUmServiceProviderDeadLineMasNaoInformoUmDeadLine() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Import_SO_Generic.csv");
		importProcessListPage.buttonSearch();

		ImportProcessShowPage importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessShowPage.buttonNextPage();

		// Verificando o Status
		String status = importProcessShowPage.getStatus("status28");
		importProcessShowPage.assertEquals("Erro", status, "Diferentes!");

		// Verificando o detalhe do erro
		String details = importProcessShowPage.getDetails("val28");
		importProcessShowPage.assertEquals(
				"O campo [Data Limite] e o campo [Data Limite Prestador] são opcionais mas quando um é informado, ambos passam a ser requeridos",
				details, "Diferentes!");
	}
}
