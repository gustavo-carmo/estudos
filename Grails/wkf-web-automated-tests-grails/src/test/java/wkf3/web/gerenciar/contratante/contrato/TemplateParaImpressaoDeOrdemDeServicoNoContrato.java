package wkf3.web.gerenciar.contratante.contrato;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.contractor.ContractorListPage;
import br.com.workfinity.web.page.contractor.ContractorShowPage;
import br.com.workfinity.web.page.contractor.contract.ContractCrudForm;
import careman.html.TestBase;

public class TemplateParaImpressaoDeOrdemDeServicoNoContrato extends TestBase {
	
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";

	private static final String TEXT = "TEXT";
	private static final String DISABLED = "DISABLED";
	private static final String ENABLED = "ENABLED";
	private static final String GSP = "GSP";
	
	private static final String CONTRACT = "CONTRACT_" + randomString(5);
	
	private static final String TEMPLATE_1 = "TEMPLATE_1_" + GSP + "_" + ENABLED + "_" + randomString(5);
	private static final String TEMPLATE_2 = "TEMPLATE_2_" + GSP + "_" + ENABLED + "_" + randomString(5);
	private static final String TEMPLATE_3 = "TEMPLATE_3_" + GSP + "_" + DISABLED + "_" + randomString(5);
	private static final String TEMPLATE_4 = "TEMPLATE_4_" + TEXT + "_" + ENABLED + "_" + randomString(5);
	
	private static final List<String> ROLES = Arrays.asList("ROLE_CONTRACTOR", "ROLE_GENERIC_CONTRACT",
			"ROLE_REPORT_REPAIR_CONTRACTOR_AVERAGE", "ROLE_CONTRACT", "ROLE_TEMPLATE");
	
	private MainPage navBar;
	private ContractorListPage contractorListPage;
	private ContractorShowPage contractorShowPage;
	private ContractCrudForm crudForm;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, true);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
    public void tearDown() throws Exception {
        getDriver().quit();
    }

	@Test
	public void doLoadData() {

		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_1, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_2, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), DISABLED, TEMPLATE_3, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_4, TEXT);

	}
	
	@Test(dependsOnMethods = { "doLoadData" })
	public void login() {
		
		createUserAndDoLogin(CONTRACT, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void definindoOTemplateParaImpressaoDeOrdemDeServicoParaUmNovoContratante() {
		
		contractorListPage = navBar.visitContractor();
		
		contractorListPage.setAlias(CONTRACTOR_GENERIC);
		contractorListPage.setDocument("CNPJ", "84.412.721/0001-07");
		contractorListPage.buttonSearch();

		contractorShowPage = contractorListPage.showItemTable(1);

		crudForm = contractorShowPage.editItemTable(1);

		crudForm.validateAllTemplates(Arrays.asList(TEMPLATE_1, TEMPLATE_2));

		crudForm.setTemplate(TEMPLATE_1);
		crudForm.buttonUpdate();

		crudForm.validateMessageSuccessUpdate();
		
		crudForm.validateTemplate(TEMPLATE_1);
		
		contractorShowPage = crudForm.buttonBackTo();
	}

	@Test(dependsOnMethods = { "definindoOTemplateParaImpressaoDeOrdemDeServicoParaUmNovoContratante" })
	public void definindoOTemplateParaImpressaoDeOrdemDeServicoParaUmContratanteExistente() {
		
		crudForm = contractorShowPage.editItemTable(1);

		crudForm.setTemplate(TEMPLATE_2).buttonUpdate();

		crudForm.validateMessageSuccessUpdate();
		crudForm.validateTemplate(TEMPLATE_2);
	}
	
//	TODO - VERIFICAR PORQUE ESTA FALHANDO A EXCLUSÃO
//	@Test(dependsOnMethods = { "definindoOTemplateParaImpressaoDeOrdemDeServicoParaUmContratanteExistente" })
//	public void undoLoadData() {
//
//		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_1);
//		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_2);
//		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_3);
//		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_4);
//	}
}
