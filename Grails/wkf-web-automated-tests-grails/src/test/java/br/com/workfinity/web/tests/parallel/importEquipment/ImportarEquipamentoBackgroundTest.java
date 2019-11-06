package br.com.workfinity.web.tests.parallel.importEquipment;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.importProcess.ImportProcessFormCrudPage;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import careman.html.TestBase;

public class ImportarEquipamentoBackgroundTest extends TestBase {

	private static final String USER_IMPORT_EQUIPAMENT = "USER_IE_" + randomString(5);

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

		createUserAndDoLogin(USER_IMPORT_EQUIPAMENT, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	@Test(dependsOnMethods = { "background" })
	public void importandoEquipamentos() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		ImportProcessFormCrudPage importProcessFormCrudPage = importProcessListPage.buttonNew();

		importProcessFormCrudPage.setType("Equipamento");
		importProcessFormCrudPage.setFile(
				getCurrentDirectory() + "/src/test/resources/importEquipment/IE_One_Test_File/Import_Equipment.csv");
		importProcessListPage = importProcessFormCrudPage.buttonCreate();

		importProcessListPage.validateMessageSuccessCreated();
		importProcessListPage.setFile("Import_Equipment.csv");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {

			importProcessListPage.validateType("Equipamento");
			importProcessListPage.validateRows("25");
			importProcessListPage.validateSuccess("4");
			importProcessListPage.validateError("21");
			importProcessListPage.validateNotPrecessed("0");
		}

	}
}
