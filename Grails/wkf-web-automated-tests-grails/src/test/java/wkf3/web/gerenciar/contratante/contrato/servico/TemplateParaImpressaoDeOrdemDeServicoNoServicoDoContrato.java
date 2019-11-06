package wkf3.web.gerenciar.contratante.contrato.servico;

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
import br.com.workfinity.web.page.contractor.contract.service.ServiceContractCrudFormPage;
import careman.html.TestBase;

public class TemplateParaImpressaoDeOrdemDeServicoNoServicoDoContrato extends TestBase {
	
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String WORKFLOW_GENERIC = "WORKFLOW_GENERIC";

	private static final String DISABLED = "DISABLED";
	private static final String ENABLED = "ENABLED";
	private static final String TEXT = "text";
	private static final String GSP = "GSP";
	private static final String SERVICE = "SERVICE_" + randomString(5);
	private static final String TEMPLATE_1 = "TEMPLATE_1_" + GSP + "_" + ENABLED + "_" + randomString(5);
	private static final String TEMPLATE_2 = "TEMPLATE_2_" + GSP + "_" + ENABLED + "_" + randomString(5);
	private static final String TEMPLATE_3 = "TEMPLATE_3_" + GSP + "_" + DISABLED + "_" + randomString(5);
	private static final String TEMPLATE_4 = "TEMPLATE_4_" + TEXT + "_" + ENABLED + "_" + randomString(5);

	private static final List<String> ROLES = Arrays.asList("ROLE_CONTRACTOR", "ROLE_GENERIC_CONTRACT",
			"ROLE_REPORT_REPAIR_CONTRACTOR_AVERAGE", "ROLE_CONTRACT", "ROLE_TEMPLATE");

	private MainPage navBar;
	private ContractCrudForm contractCrud;
	private ServiceContractCrudFormPage crudForm;
	private int rowNumberService;

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
	public void doLoadDataScripts() {
		
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_1, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_2, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), DISABLED, TEMPLATE_3, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_4, TEXT);
	}
	
	@Test(dependsOnMethods = { "doLoadDataScripts" })
	public void doLoadData() {
		
		createUserAndDoLogin(SERVICE, "123456", ROLES);
		
		ContractorListPage contractorListPage = navBar.visitContractor();
		
		contractorListPage.setAlias(CONTRACTOR_GENERIC);
		contractorListPage.setDocument("CNPJ", "84.412.721/0001-07");
		contractorListPage.buttonSearch();
		
		ContractorShowPage contractorShow = contractorListPage.showItemTable(1);
		
		contractCrud = contractorShow.editItemTable(1);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void definindoOTemplateParaImpressaoDeOrdemDeServicoParaUmNovoServico() {

		crudForm = contractCrud.buttonNewService();

		crudForm.setServiceGroup(SERVICE_GROUP_GENERIC);
		crudForm.setWorkflow(WORKFLOW_GENERIC);
		crudForm.setName(SERVICE);
		crudForm.setTemplate(TEMPLATE_1);
		crudForm.setOpeningHoursGroup(OPENING_HOURS_GROUP_GENERIC);

		crudForm.validateAllTemplates(Arrays.asList(TEMPLATE_1, TEMPLATE_2));
		
		contractCrud = crudForm.buttonCreate();

		contractCrud.validateMessageSuccessCreated();
		
		contractCrud.validateIfContainsTheRowInTable(Arrays.asList(SERVICE));
		rowNumberService = contractCrud.getRowNumber(Arrays.asList(SERVICE));
		
		crudForm = contractCrud.editService(rowNumberService);

		contractCrud = crudForm.validateTemplate(TEMPLATE_1).buttonBackToDetail();
	}

	@Test(dependsOnMethods = { "definindoOTemplateParaImpressaoDeOrdemDeServicoParaUmNovoServico" })
	public void definindoOTemplateParaImpressaoDeOrdemDeServicoParaUmServicoExistente() {
		
		crudForm = contractCrud.editService(rowNumberService);

		contractCrud = crudForm.setTemplate(TEMPLATE_2).buttonUpdate();

		contractCrud.validateMessageSuccessUpdate();
		
		crudForm = contractCrud.editService(rowNumberService);

		crudForm.validateAllTemplates(Arrays.asList(TEMPLATE_1, TEMPLATE_2));
		
		crudForm.validateTemplate(TEMPLATE_2);
		contractCrud = crudForm.buttonBackToDetail();
	}

	@Test(dependsOnMethods = { "definindoOTemplateParaImpressaoDeOrdemDeServicoParaUmServicoExistente" })
	public void undoLoadData() {

		contractCrud.deleteService(rowNumberService);

		// TODO - Voltar quando arrumar o problema de não deletar
//		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_1);
//		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_2);
//		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_3);
//		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_4);
	}
}
