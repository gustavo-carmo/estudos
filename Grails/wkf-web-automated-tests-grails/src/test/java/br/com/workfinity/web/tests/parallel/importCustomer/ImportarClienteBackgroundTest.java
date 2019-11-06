package br.com.workfinity.web.tests.parallel.importCustomer;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.importProcess.ImportProcessFormCrudPage;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import careman.html.TestBase;

public class ImportarClienteBackgroundTest extends TestBase {

	private static final String USER_IMPORT_CUSTOMER = "USER_IC_" + randomString(5);

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

		createUserAndDoLogin(USER_IMPORT_CUSTOMER, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	@Test(dependsOnMethods = { "background" })
	public void importandoClientes() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		ImportProcessFormCrudPage importProcessFormCrudPage = importProcessListPage.buttonNew();

		importProcessFormCrudPage.setType("Cliente");
		importProcessFormCrudPage.setFile(
				getCurrentDirectory() + "/src/test/resources/importCustomer/IC_One_Test_File/Import_Customer.csv");
		importProcessListPage = importProcessFormCrudPage.buttonCreate();

		importProcessListPage.validateMessageSuccessCreated();
		importProcessListPage.setFile("Import_Customer.csv");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {

			importProcessListPage.validateType("Cliente");
			importProcessListPage.validateRows("22");
			importProcessListPage.validateSuccess("3");
			importProcessListPage.validateError("19");
			importProcessListPage.validateNotPrecessed("0");
		}
	}
}
