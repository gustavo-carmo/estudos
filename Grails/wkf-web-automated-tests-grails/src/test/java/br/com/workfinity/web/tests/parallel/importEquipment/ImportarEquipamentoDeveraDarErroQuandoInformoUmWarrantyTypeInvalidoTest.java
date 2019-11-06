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

public class ImportarEquipamentoDeveraDarErroQuandoInformoUmWarrantyTypeInvalidoTest extends TestBase {

	private static final String USER_IMPORT_EQUIP_WARRANTY_TYPE_INVALID = "USER_WARRANTY_TYPE_INV_" + randomString(5);
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

		createUserAndDoLogin(USER_IMPORT_EQUIP_WARRANTY_TYPE_INVALID, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	// TODO - VERIFICAR SE FARÁ A IMPORTAÇÃO CORRETAMENTE
	@Test(dependsOnMethods = { "background" })
	public void deveraDarErroQuandoInformoWarrantyTypeDeTipoInvalido() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Import_Equipment.csv");
		importProcessListPage.buttonSearch();

		ImportProcessShowPage importProcessShowPage = importProcessListPage.showItemTable(1);

		// Verificando o Status
		String status = importProcessShowPage.getStatus("status19");
		importProcessShowPage.assertEquals("Erro", status, "Diferentes!");

		// Verificando o detalhe do erro
//		String details = importProcessShowPage.getDetails("val19");
//		importProcessShowPage.assertEquals("Warranty: Tipo de Garantia inválida.", details, "Diferentes!");
	}
}
