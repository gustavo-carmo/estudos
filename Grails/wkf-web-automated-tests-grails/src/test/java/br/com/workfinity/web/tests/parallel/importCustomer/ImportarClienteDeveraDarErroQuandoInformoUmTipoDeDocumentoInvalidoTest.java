package br.com.workfinity.web.tests.parallel.importCustomer;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import br.com.workfinity.web.page.importProcess.ImportProcessShowPage;
import careman.html.TestBase;

public class ImportarClienteDeveraDarErroQuandoInformoUmTipoDeDocumentoInvalidoTest extends TestBase {

	private static final String USER_IMPORT_CUSTOMER_DOCUMENT_TYPE_INVALID = "USER_IC_DOC_TYPE_INV_" + randomString(5);
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

		createUserAndDoLogin(USER_IMPORT_CUSTOMER_DOCUMENT_TYPE_INVALID, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	@Test(dependsOnMethods = { "background" })
	public void deveraDarErroQuandoInformoUmTipoDeDocumentoInvalido() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Import_Customer.csv");
		importProcessListPage.buttonSearch();

		ImportProcessShowPage importProcessShowPage = importProcessListPage.showItemTable(1);

		// Verificando o Status
		String status = importProcessShowPage.getStatus("status8");
		importProcessShowPage.assertEquals("Erro", status, "Diferentes!");

		// Verificando o detalhe do erro
		String details = importProcessShowPage.getDetails("val8");
		importProcessShowPage.assertEquals("{0} é um Tipo de Documento Inválido!", details, "Diferentes!");
	}
}
