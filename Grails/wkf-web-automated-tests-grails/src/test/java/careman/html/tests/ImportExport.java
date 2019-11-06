package careman.html.tests;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.export.ExportCrudPage;
import br.com.workfinity.web.page.export.ExportPage;
import br.com.workfinity.web.page.importProcess.ImportProcessFormCrudPage;
import br.com.workfinity.web.page.importProcess.ImportProcessGeneric;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import br.com.workfinity.web.page.importProcess.ImportProcessShowPage;
import br.com.workfinity.web.page.importProcess.ImportSolicitationsAndInstallations;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import careman.html.TestBase;

public class ImportExport extends TestBase {

	private StringBuffer verificationErrors = new StringBuffer();

	public String id;
	public String id2;
	public String id3;
	public List<String> expectedProcessStatuses = Arrays.asList("IMPORTING", "NOT_PROCESSED", "PROCESSING", "PROCESSED");

	String customFieldName;
	String customFieldName2;
	String customFieldName3;

	private ImportProcessListPage importProcessListPage;

	private MainPage navBar;

	private ImportSolicitationsAndInstallations importSolicitationsAndInstallations;

	private ImportProcessFormCrudPage importProcessCrudForm;

	private ServiceOrderCrudForm serviceOrderCrudForm;

	private ImportProcessShowPage importProcessShowPage;

	private ImportProcessGeneric importProcessGeneric;

	private String getBanrisulFile(final String fileName) {
		return this.getCurrentDirectory() + "/src/test/resources/INSTAL_POS.BJR02.D_" + fileName + ".txt";
	}

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	private void createCustomer(final String customerName) throws Exception {
		getDriver().get(getBaseUrl() + "/seed/execute?seed=createCustomer" + customerName + "SaoPaulo");
		String bodyText = getDriver().findElement(By.tagName("body")).getText();
		assert Objects.equals("OK", bodyText);
	}

	@Test(priority = 1)
	public void dologin() throws Exception {
		getDriver().get(getBaseUrl());
		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();
	}

	@Test(priority = 1)
	public void createUserAndDoLogin() throws Exception {
		createUserAndDoLogin(randomString(10), "123456", userRoles);
	}

	@Test(priority = 2)
	public void importAndTestBanrisulFileType1() throws Exception {
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();

		importProcessListPage = importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_1")).buttonImport();

		importProcessListPage.validateMessageSuccessCreated();
		importProcessListPage.setFile("Tipo_1");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("7").validateSuccess("7").validateError("0").validateNotPrecessed("0");
		}
	}

	@Test(priority = 3)
	public void importAndTestBanrisulFileType2() throws Exception {
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importProcessListPage = importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_2")).buttonImport();

		importProcessListPage.validateMessageSuccessCreated();
		importProcessListPage.setFile("Tipo_2");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("7").validateSuccess("6").validateError("1").validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so2");
		serviceOrderCrudForm.validateContainsTextInEquipment("EFT930G - 00001118");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2").buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so3");
		serviceOrderCrudForm.validateContainsTextInEquipment("EFT930G - 00001119");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2").buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so4");
		serviceOrderCrudForm.validateContainsTextInEquipment("EFT930G - 00001252");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2").buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so5");
		serviceOrderCrudForm.validateContainsTextInEquipment("EFT930G - 00001253");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2").buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_6");

		importProcessGeneric.setCity("SAO PAULO").buttonReprocess();

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2").buttonSearch();

		importProcessListPage.validateRows("7").validateSuccess("7").validateError("0").validateNotPrecessed("0");

	}

	@Test(priority = 4)
	public void importAndTestBanrisulFileType3() throws Exception {
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_3")).buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_3").buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("5").validateSuccess("4").validateError("1").validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so2");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_3").buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_4");
		importProcessGeneric.setCity("SAO PAULO").buttonReprocess().buttonBackToDetail();

		importProcessListPage = importProcessShowPage.validateContainsTextInElement("status4", "Reprocessado").buttonBackToSearch();

		importProcessListPage.setFile("Tipo_3").buttonSearch();

		importProcessListPage.validateError("0").validateRows("5").validateSuccess("5").validateNotPrecessed("0");

	}

	@Test(priority = 5)
	public void importAndTestBanrisulFileType4() throws Exception {
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_4")).buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_4.txt").buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("5").validateSuccess("4").validateError("1").validateNotPrecessed("0");
		}

		importProcessListPage.showItemTable(1);
		importProcessShowPage = new ImportProcessShowPage(getDriver());

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_4");
		importProcessGeneric.setCity("SAO PAULO").buttonReprocess().buttonBackToDetail();

		importProcessShowPage.validateContainsTextInElement("status4", "Reprocessado").buttonBackToSearch();

		importProcessListPage.validateRows("5").validateSuccess("5").validateError("0").validateNotPrecessed("0");

	}

	@Test(priority = 6)
	public void importAndTestBanrisulFileType6() throws Exception {
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_6_Dados")).buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_6_Dados.txt").buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateSuccess("5").validateError("0");
		}

		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_6")).buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_6.txt").buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("5").validateSuccess("4").validateError("1").validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_4");

		importProcessShowPage = importProcessGeneric.setDataEnvio("2012-10-08").buttonReprocess().buttonBackToDetail();

		importProcessShowPage.buttonBackToSearch();

		importProcessListPage.validateRows("5").validateSuccess("5").validateError("0").validateNotPrecessed("0");

	}

	@Test(priority = 7)
	public void importAndTestBanrisulFileType7() throws Exception {
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_7")).buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_7.txt").buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("6").validateSuccess("5").validateError("1").validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_5");

		importProcessGeneric.setCity("SAO PAULO").buttonReprocess().buttonBackToDetail();

		importProcessShowPage.buttonBackToSearch();
		importProcessListPage.validateRows("6").validateSuccess("6").validateError("0").validateNotPrecessed("0");

	}

	@Test(priority = 8)
	public void importAndTestBanrisulFileType8() throws Exception {
		this.createCustomer("Marcelo");
		this.createCustomer("Maria");
		getDriver().get(getBaseUrl());

		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_8")).buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_8.txt").buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("4").validateSuccess("4").validateError("0").validateNotPrecessed("0");
		}

		importProcessListPage.showItemTable(1);
		importProcessShowPage = new ImportProcessShowPage(getDriver());

		importProcessShowPage
				.validateContainsTextInElement("status1", "Sucesso")
				.validateContainsTextInElement("val1", "NOTHING_DONE")
				.validateContainsTextInElement("status2", "Sucesso")
				.validateContainsTextInElement("val2",
						"EQUIPMENT [ICT230 - 7711CT00001 - 77119335]: DISABLED!\nCUSTOMER [Marcelo De São Paulo - 94.572.765/0001-40]: DISABLED!")
				.validateContainsTextInElement("status3", "Sucesso")
				.validateContainsTextInElement("val3",
						"EQUIPMENT [ICT230 - 0000CT00002 - 00006197]: DISABLED!\nCUSTOMER [Maria De São Paulo - 10.261.148/0001-33]: DISABLED!");
	}

	@Test(priority = 9)
	public void genericImport() throws Exception {
		importProcessCrudForm = navBar.visitImportProcess().buttonNew();
		importProcessListPage = importProcessCrudForm.setFile(getCurrentDirectory() + "/src/test/resources/Generic_Import.csv")
				.buttonCreate();

		importProcessListPage.validateMessageSuccessCreated();
		importProcessListPage.validateUrlContains("importProcess/list");
		importProcessListPage.setFile("Generic_Import.csv").buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateError("2").validateSuccess("10").validateRows("12").validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.buttonEdit("tr:nth-child(10) [name='_action_row']");

		importProcessGeneric.setGenericField("textField_CUSTOMER_ADDRESS_CITY", "LAGES").buttonReprocess().buttonBackToDetail();
		importProcessGeneric = importProcessShowPage.buttonEdit("tr:nth-child(8) [name='_action_row']");

		importProcessShowPage = importProcessGeneric.setGenericField("textField_CUSTOMER_DOCUMENT_NUMBER", "03547252000123")
				.buttonReprocess().buttonBackToDetail();

		importProcessShowPage.buttonBackToSearch();

		importProcessListPage.validateError("0").validateSuccess("12").validateRows("12").validateNotPrecessed("0");
	}

	@Test(priority = 10)
	public void exportBanrisulFile() throws Exception {
		ExportPage exportPageList = navBar.visitExport();

		ExportCrudPage exportCrudPage = exportPageList.buttonNovaExportacao();
		exportPageList = exportCrudPage.buttonCreate().buttonExport().buttonBackToSearch();

		exportCrudPage = exportPageList.buttonNovaExportacao();

		exportPageList = exportCrudPage.buttonBackToSearch();
		exportPageList.buttonSearch();

		exportPageList.validateIfContainsTheRowInTable(Arrays.asList("DONE"));
		exportCrudPage = exportPageList.editItemTable(1);

		exportCrudPage.validateStatusMessage("SUCCESS");
	}

	@Test(priority = 11)
	public void importEquipment() throws Exception {
		importProcessCrudForm = navBar.visitImportProcess().buttonNew();
		importProcessCrudForm.setType("Equipamento").setFile(getCurrentDirectory() + "/src/test/resources/importProcess_equipment.csv")
				.buttonCreate();

		importProcessCrudForm.validateMessageSuccessCreated();
	}
}
