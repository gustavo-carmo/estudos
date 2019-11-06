package br.com.workfinity.web.tests.parallel;

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
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import careman.html.TestBase;

public class ImportExportParallelTest extends TestBase {
	
	private static final String USER_IMPORT_BANRISUL = "USER_IMPORT_BANRISUL_" + randomString(5);

	private StringBuffer verificationErrors = new StringBuffer();

	public String id;
	public String id2;
	public String id3;
	public List<String> expectedProcessStatuses = Arrays.asList("IMPORTING", "NOT_PROCESSED", "PROCESSING", "PROCESSED");

	String customFieldName;
	String customFieldName2;
	String customFieldName3;

	private MainPage navBar;
	private ImportProcessListPage importProcessListPage;
	private ImportSolicitationsAndInstallations importSolicitationsAndInstallations;
	private ImportProcessShowPage importProcessShowPage;
	private ServiceOrderCrudForm serviceOrderCrudForm;
	private ImportProcessFormCrudPage importProcessCrudForm;
	private ImportProcessGeneric importProcessGeneric;

	private String getBanrisulFile(final String fileName) {
		
		return this.getCurrentDirectory() + "/src/test/resources/INSTAL_POS.BJR02.D_" + fileName + ".txt";
	}

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		
		super.setUp(baseURL, gridURL, false);
		
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

	@Test
	public void createUserAndDoLogin() throws Exception {
		
		createUserAndDoLogin(USER_IMPORT_BANRISUL, "123456", userRoles);
	}

	@Test(dependsOnMethods = { "createUserAndDoLogin" })
	public void importAndTestBanrisulFileType1() throws Exception {
		
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();

		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_1"));
		importProcessListPage = importSolicitationsAndInstallations.buttonImport();

		importProcessListPage.validateMessageSuccessCreated();
		
		importProcessListPage.setFile("Tipo_1");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("7");
			importProcessListPage.validateSuccess("7");
			importProcessListPage.validateError("0");
			importProcessListPage.validateNotPrecessed("0");
		}
	}

	@Test(dependsOnMethods = { "importAndTestBanrisulFileType1" })
	public void importAndTestBanrisulFileType2() throws Exception {
		
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_2"));
		importProcessListPage = importSolicitationsAndInstallations.buttonImport();

		importProcessListPage.validateMessageSuccessCreated();
		
		importProcessListPage.setFile("Tipo_2");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			importProcessListPage.validateRows("7");
			importProcessListPage.validateSuccess("6");
			importProcessListPage.validateError("1");
			importProcessListPage.validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so2");
		serviceOrderCrudForm.validateContainsTextInEquipment("EFT930G - 00001118");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2");
		importProcessListPage.buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so3");
		serviceOrderCrudForm.validateContainsTextInEquipment("EFT930G - 00001119");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2");
		importProcessListPage.buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so4");
		serviceOrderCrudForm.validateContainsTextInEquipment("EFT930G - 00001252");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2");
		importProcessListPage.buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so5");
		serviceOrderCrudForm.validateContainsTextInEquipment("EFT930G - 00001253");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2");
		importProcessListPage.buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_6");

		importProcessGeneric.setCity("SAO PAULO");
		importProcessGeneric.buttonReprocess();

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_2");
		importProcessListPage.buttonSearch();

		importProcessListPage.validateRows("7");
		importProcessListPage.validateSuccess("7");
		importProcessListPage.validateError("0");
		importProcessListPage.validateNotPrecessed("0");
	}

	@Test(dependsOnMethods = { "importAndTestBanrisulFileType2" })
	public void importAndTestBanrisulFileType3() throws Exception {
		
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_3"));
		importSolicitationsAndInstallations.buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_3");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			
			importProcessListPage.validateRows("5");
			importProcessListPage.validateSuccess("4");
			importProcessListPage.validateError("1");
			importProcessListPage.validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		serviceOrderCrudForm = importProcessShowPage.clickLinkServiceOrder("so2");

		importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Tipo_3");
		importProcessListPage.buttonSearch();

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_4");
		importProcessGeneric.setCity("SAO PAULO");
		importProcessGeneric.buttonReprocess();
		importProcessGeneric.buttonBackToDetail();

		importProcessShowPage.validateContainsTextInElement("status4", "Reprocessado");
		importProcessListPage = importProcessShowPage.buttonBackToSearch();

		importProcessListPage.setFile("Tipo_3");
		importProcessListPage.buttonSearch();

		importProcessListPage.validateError("0");
		importProcessListPage.validateRows("5");
		importProcessListPage.validateSuccess("5");
		importProcessListPage.validateNotPrecessed("0");
	}

	@Test(dependsOnMethods = { "importAndTestBanrisulFileType3" })
	public void importAndTestBanrisulFileType4() throws Exception {
		
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_4"));
		importSolicitationsAndInstallations.buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_4.txt");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			
			importProcessListPage.validateRows("5");
			importProcessListPage.validateSuccess("4");
			importProcessListPage.validateError("1");
			importProcessListPage.validateNotPrecessed("0");
		}

		importProcessListPage.showItemTable(1);
		importProcessShowPage = new ImportProcessShowPage(getDriver());

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_4");
		importProcessGeneric.setCity("SAO PAULO");
		importProcessGeneric.buttonReprocess();
		importProcessGeneric.buttonBackToDetail();

		importProcessShowPage.validateContainsTextInElement("status4", "Reprocessado");
		importProcessShowPage.buttonBackToSearch();

		importProcessListPage.validateRows("5");
		importProcessListPage.validateSuccess("5");
		importProcessListPage.validateError("0");
		importProcessListPage.validateNotPrecessed("0");
	}

	@Test(dependsOnMethods = { "importAndTestBanrisulFileType4" })
	public void importAndTestBanrisulFileType6() throws Exception {
		
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_6_Dados"));
		importSolicitationsAndInstallations.buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_6_Dados.txt");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			
			importProcessListPage.validateSuccess("5");
			importProcessListPage.validateError("0");
		}

		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_6"));
		importSolicitationsAndInstallations.buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_6.txt");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			
			importProcessListPage.validateRows("5");
			importProcessListPage.validateSuccess("4");
			importProcessListPage.validateError("1");
			importProcessListPage.validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_4");

		importProcessGeneric.setDataEnvio("2012-10-08");
		importProcessGeneric.buttonReprocess();
		importProcessShowPage = importProcessGeneric.buttonBackToDetail();

		importProcessShowPage.buttonBackToSearch();

		importProcessListPage.validateRows("5");
		importProcessListPage.validateSuccess("5");
		importProcessListPage.validateError("0");
		importProcessListPage.validateNotPrecessed("0");
	}

	@Test(dependsOnMethods = { "importAndTestBanrisulFileType6" })
	public void importAndTestBanrisulFileType7() throws Exception {
		
		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_7"));
		importSolicitationsAndInstallations.buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_7.txt");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			
			importProcessListPage.validateRows("6");
			importProcessListPage.validateSuccess("5");
			importProcessListPage.validateError("1");
			importProcessListPage.validateNotPrecessed("0");
		}

		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.clickLinkEdit("edit_5");

		importProcessGeneric.setCity("SAO PAULO");
		importProcessGeneric.buttonReprocess();
		importProcessGeneric.buttonBackToDetail();

		importProcessShowPage.buttonBackToSearch();
		
		importProcessListPage.validateRows("6");
		importProcessListPage.validateSuccess("6");
		importProcessListPage.validateError("0");
		importProcessListPage.validateNotPrecessed("0");
	}

	@Test(dependsOnMethods = { "importAndTestBanrisulFileType7" })
	public void importAndTestBanrisulFileType8() throws Exception {
		
		this.createCustomer("Marcelo");
		this.createCustomer("Maria");
		getDriver().get(getBaseUrl());

		importSolicitationsAndInstallations = navBar.visitImportSolicitationsAndInstallations();
		importSolicitationsAndInstallations.setFile(this.getBanrisulFile("Tipo_8"));
		importSolicitationsAndInstallations.buttonImport();

		importSolicitationsAndInstallations.validateMessageSuccessCreated();

		importProcessListPage = new ImportProcessListPage(getDriver());
		importProcessListPage.setFile("Tipo_8.txt");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			
			importProcessListPage.validateRows("4");
			importProcessListPage.validateSuccess("4");
			importProcessListPage.validateError("0");
			importProcessListPage.validateNotPrecessed("0");
		}

		importProcessListPage.showItemTable(1);
		importProcessShowPage = new ImportProcessShowPage(getDriver());

		importProcessShowPage.validateContainsTextInElement("status1", "Sucesso");
		
		importProcessShowPage.validateContainsTextInElement("val1", "NOTHING_DONE");
		
		importProcessShowPage.validateContainsTextInElement("status2", "Sucesso");
		
		importProcessShowPage.validateContainsTextInElement("val2",
						"EQUIPMENT [ICT230 - 7711CT00001 - 77119335]: DISABLED!\nCUSTOMER [Marcelo De São Paulo - 94.572.765/0001-40]: DISABLED!");
		
		importProcessShowPage.validateContainsTextInElement("status3", "Sucesso");
		
		importProcessShowPage.validateContainsTextInElement("val3",
						"EQUIPMENT [ICT230 - 0000CT00002 - 00006197]: DISABLED!\nCUSTOMER [Maria De São Paulo - 10.261.148/0001-33]: DISABLED!");
	}

	@Test(dependsOnMethods = { "importAndTestBanrisulFileType8" })
	public void genericImport() throws Exception {
		
		importProcessCrudForm = navBar.visitImportProcess().buttonNew();
		importProcessCrudForm.setFile(getCurrentDirectory() + "/src/test/resources/Generic_Import.csv");
		importProcessListPage = importProcessCrudForm.buttonCreate();

		importProcessListPage.validateMessageSuccessCreated();
		
		importProcessListPage.validateUrlContains("importProcess/list");
		importProcessListPage.setFile("Generic_Import.csv");
		importProcessListPage.buttonSearch();

		if (importProcessListPage.isProcessed()) {
			
			importProcessListPage.validateRows("5");
			importProcessListPage.validateSuccess("5");
			importProcessListPage.validateError("0");
			importProcessListPage.validateNotPrecessed("0");
		}
/*
		importProcessShowPage = importProcessListPage.showItemTable(1);

		importProcessGeneric = importProcessShowPage.buttonEdit("tr:nth-child(10) [name='_action_row']");

		importProcessGeneric.setGenericField("textField_CUSTOMER_ADDRESS_CITY", "LAGES").buttonReprocess().buttonBackToDetail();
		importProcessGeneric = importProcessShowPage.buttonEdit("tr:nth-child(8) [name='_action_row']");

		importProcessShowPage = importProcessGeneric.setGenericField("textField_CUSTOMER_DOCUMENT_NUMBER", "03547252000123")
				.buttonReprocess().buttonBackToDetail();

		importProcessShowPage.buttonBackToSearch();

		importProcessListPage.validateError("0").validateSuccess("5").validateRows("5").validateNotPrecessed("0");
		*/
	}

	@Test(dependsOnMethods = { "genericImport" })
	public void exportBanrisulFile() throws Exception {
		
		ExportPage exportPageList = navBar.visitExport();

		ExportCrudPage exportCrudPage = exportPageList.buttonNovaExportacao();
		exportCrudPage.buttonCreate();
		exportCrudPage.buttonExport();
		exportPageList = exportCrudPage.buttonBackToSearch();

		exportCrudPage = exportPageList.buttonNovaExportacao();

		exportPageList = exportCrudPage.buttonBackToSearch();
		exportPageList.buttonSearch();

		exportPageList.validateIfContainsTheRowInTable(Arrays.asList("DONE"));
		
		exportCrudPage = exportPageList.editItemTable(1);

		exportCrudPage.validateStatusMessage("SUCCESS");
	}

/*	@Test(priority = 11)
	public void importEquipment() throws Exception {
		importProcessCrudForm = navBar.visitImportProcess().buttonNew();
		importProcessCrudForm.setType("Equipamento").setFile(getCurrentDirectory() + "/src/test/resources/importProcess_equipment.csv")
				.buttonCreate();

		importProcessCrudForm.validateMessageSuccessCreated();
	}*/
}
