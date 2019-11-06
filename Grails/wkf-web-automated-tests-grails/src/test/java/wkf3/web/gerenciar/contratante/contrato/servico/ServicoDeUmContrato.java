package wkf3.web.gerenciar.contratante.contrato.servico;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.contractor.ContractorListPage;
import br.com.workfinity.web.page.contractor.ContractorShowPage;
import br.com.workfinity.web.page.contractor.contract.ContractCrudForm;
import br.com.workfinity.web.page.contractor.contract.service.ServiceContractCrudFormPage;
import careman.html.TestBase;

public class ServicoDeUmContrato extends TestBase {

	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String SERVICE_GROUP_GENERIC_LAB = "SERVICE_GROUP_GENERIC_LAB";
	private static final String WORKFLOW_GENERIC = "WORKFLOW_GENERIC";

	private static final String SERVICE = "SERVICE_" + randomString(5);

	private static final List<String> ROLES = Arrays.asList("ROLE_CONTRACTOR", "ROLE_GENERIC_CONTRACT",
			"ROLE_REPORT_REPAIR_CONTRACTOR_AVERAGE", "ROLE_CONTRACT", "ROLE_TEMPLATE");

	private MainPage navBar;
	private ContractCrudForm contractCrud;
	// private String contractId;
	private ServiceContractCrudFormPage serviceCrud;
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
	public void login() {

		createUserAndDoLogin(SERVICE, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void doLoadData() {

		ContractorListPage contractorListPage = navBar.visitContractor();

		contractorListPage.setAlias(CONTRACTOR_GENERIC);
		contractorListPage.setDocument("CNPJ", "84.412.721/0001-07");
		contractorListPage.buttonSearch();

		ContractorShowPage contractorShow = contractorListPage.showItemTable(1);

		contractCrud = contractorShow.editItemTable(1);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void validandoOsCamposDoTipoCampoNaCriacaoDeUmServico() {

		serviceCrud = contractCrud.buttonNewService();

		serviceCrud.validateIfElementsOfTypeFieldIsNotVisible();

		serviceCrud.setServiceGroup(SERVICE_GROUP_GENERIC);

		serviceCrud.validateIfElementsOfTypeFieldIsVisible();

		serviceCrud.setWorkflow(WORKFLOW_GENERIC);
		serviceCrud.setName(SERVICE);
		serviceCrud.setOpeningHoursGroup(OPENING_HOURS_GROUP_GENERIC);
		contractCrud = serviceCrud.buttonCreate();

		contractCrud.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "validandoOsCamposDoTipoCampoNaCriacaoDeUmServico" })
	public void validandoOsCamposDoTipoCampoNaEdicaoDeUmServico() {

		contractCrud.validateIfContainsTheRowInTable(Arrays.asList(SERVICE));
		rowNumberService = contractCrud.getRowNumber(Arrays.asList(SERVICE));

		serviceCrud = contractCrud.editService(rowNumberService);

		serviceCrud.setServiceGroup(SERVICE_GROUP_GENERIC_LAB);
		serviceCrud.validateIfElementsOfTypeFieldIsNotVisible();

		serviceCrud.setServiceGroup(SERVICE_GROUP_GENERIC);
		serviceCrud.validateIfElementsOfTypeFieldIsVisible();

		serviceCrud.buttonBackToDetail();
	}

	@Test(dependsOnMethods = { "validandoOsCamposDoTipoCampoNaEdicaoDeUmServico" })
	public void undoLoadData() {

		contractCrud.deleteService(rowNumberService);
	}
}
