package br.com.workfinity.web.tests.parallel.importEquipment;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import br.com.workfinity.web.page.importProcess.ImportProcessShowPage;
import careman.html.TestBase;

public class ImportarEquipamentoDeveraDarErroQuandoNumeroDeDocumentoForDiferenteDoTipoDeDocumentoTest extends TestBase {

	private static final String USER_IMPORT_EQUIP_CUSTOMER_DOCUMENT_NUMBER_DIFFERENT_DOCUMENT_TYPE = "USER_DOCNUM_DIF_DOCTYPE_"
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

		createUserAndDoLogin(USER_IMPORT_EQUIP_CUSTOMER_DOCUMENT_NUMBER_DIFFERENT_DOCUMENT_TYPE, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	@Test(dependsOnMethods = { "background" })
	public void deveraDarErroQuandoNumeroDeDocumentoForDiferenteDoTipoDeDocumento() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Import_Equipment.csv");
		importProcessListPage.buttonSearch();

		ImportProcessShowPage importProcessShowPage = importProcessListPage.showItemTable(1);

		// Verificando o Status
		String status = importProcessShowPage.getStatus("status5");
		importProcessShowPage.assertEquals("Erro", status, "Diferentes!");

		// Verificando o detalhe do erro
		String details = importProcessShowPage.getDetails("val5");
		importProcessShowPage.assertEquals(
				"Customer: documentType: CNPJ, documentNumber: 88000123 not found, or the customer does not belong to CONTRACTOR_GENERIC.",
				details, "Diferentes!");
	}
}
