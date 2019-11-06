package br.com.workfinity.web.tests.parallel.importGenericServiceOrder;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.importProcess.ImportProcessFormCrudPage;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import careman.html.TestBase;

public class ImportarOSGenericaBackgroundTest extends TestBase {

	private static final String USER_IMPORT_SERVICE_ORDER_GENERIC_WITHOUT_CONTRACTOR_ALIAS = "USER_ISOG_SEM_CONTR_ALIAS_" + randomString(5);
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

		createUserAndDoLogin(USER_IMPORT_SERVICE_ORDER_GENERIC_WITHOUT_CONTRACTOR_ALIAS, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	@Test(dependsOnMethods = { "background" })
	public void importacaoDeOSGenerica() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		ImportProcessFormCrudPage importProcessFormCrudPage = importProcessListPage .buttonNew();

		importProcessFormCrudPage.setType("Ordem de Serviço");
		importProcessFormCrudPage.setFile(getCurrentDirectory()
				+ "/src/test/resources/importGenericServiceOrder/IOSG_One_Test_File/Import_SO_Generic.csv");
		importProcessListPage = importProcessFormCrudPage.buttonCreate();

		importProcessListPage.validateMessageSuccessCreated();
		importProcessListPage.setFile("Import_SO_Generic.csv");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {

			importProcessListPage.validateType("Ordem de Serviço");
			importProcessListPage.validateRows("42");
			importProcessListPage.validateSuccess("14");
			importProcessListPage.validateError("28");
			importProcessListPage.validateNotPrecessed("0");
		}
	}
}
