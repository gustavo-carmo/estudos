package wkf3.web.gerenciar.workPack;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import careman.html.TestBase;

public class WorkPackBackgroundTest extends TestBase{
	
	private static final String PASSWORD = "123456";
	private static final String SERVICE_WITH_TECHNICIAN = "SERVICE_WITH_TECHNICIAN";
	private static final String WORKFLOW_WITH_TECHNICIAN = "WORKFLOW_WITH_TECHNICIAN";
	private static final String STATUS_WITH_TECHNICIAN_NEW = "STATUS_WITH_TEC_NEW";
	private static final String STATUS_WITH_TECHNICIAN_MEIO = "STATUS_WITH_TEC_MEIO";
	private static final String STATUS_WITH_TECHNICIAN_AC = "STATUS_WITH_TEC_AC";
	private static final String STATUS_WITH_TECHNICIAN_AB = "STATUS_WITH_TEC_AB";
	private static final String STATUS_WITH_TECHNICIAN_END = "STATUS_WITH_TEC_END";
	private static final String TECHNICIAN_MATRIZ_01 = "TECHNICIAN_MATRIZ_01";
	private static final String TECHNICIAN_MATRIZ_02 = "TECHNICIAN_MATRIZ_02";
	private static final String TECHNICIAN_FILIAL_01 = "TECHNICIAN_FILIAL_01";
	private static final String TECHNICIAN_FILIAL_02 = "TECHNICIAN_FILIAL_02";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String SERVICE_PROVIDER_GENERIC_FILIAL = "SERVICE_PROVIDER_GENERIC_FILIAL";

	private static final List<String> ROLES_TECHNICIAN = Arrays.asList("ROLE_WORK_PACK", "ROLE_EQUIPMENT",
			"ROLE_SERVICE_ORDER", "ROLE_SERVICE_ORDER_SERVICE_PROVIDER", "ROLE_STOCK");

	private static final List<String> STEP_STATUS = Arrays.asList(STATUS_WITH_TECHNICIAN_NEW,
			STATUS_WITH_TECHNICIAN_MEIO, STATUS_WITH_TECHNICIAN_AC, STATUS_WITH_TECHNICIAN_AB,
			STATUS_WITH_TECHNICIAN_END);


	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, false);
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void backgroundScripts() {
		
//		  new Page(getDriver()) { }.visitURL(getBaseUrl() + "/login/auth"); 
//		  new LoginPage(getDriver(), "WORKPACK_USER_InugA", PASSWORD).buttonSignInSuccess();

		// Criando os Status para a Ordem de Serviço com Técnico
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_WITH_TECHNICIAN_NEW, "START");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_WITH_TECHNICIAN_MEIO, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_WITH_TECHNICIAN_AC, "WITH_TECHNICIAN");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_WITH_TECHNICIAN_AB, "WITH_TECHNICIAN");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_WITH_TECHNICIAN_END, "END_WITH_SUCCESS");

		// Criando os Workflows
		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_WITH_TECHNICIAN);

		// Adicionando os Status no Workflow com Técnico
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_WITH_TECHNICIAN,
				STATUS_WITH_TECHNICIAN_NEW, STATUS_WITH_TECHNICIAN_MEIO);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_WITH_TECHNICIAN,
				STATUS_WITH_TECHNICIAN_NEW, STATUS_WITH_TECHNICIAN_AB);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_WITH_TECHNICIAN,
				STATUS_WITH_TECHNICIAN_NEW, STATUS_WITH_TECHNICIAN_AC);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_WITH_TECHNICIAN, STATUS_WITH_TECHNICIAN_AB,
				STATUS_WITH_TECHNICIAN_END);

		// Adicionando os Status no Perfil de Administrador
		LoadDataHelper.addStatusToExistentProfile(getDriver(), getBaseUrl(), "ADMINISTRATOR", "SERVICE_ORDER", STEP_STATUS);

		// Criando o Serviço do Contratante
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_WITH_TECHNICIAN, CONTRACTOR_GENERIC,
				SERVICE_GROUP_GENERIC, WORKFLOW_WITH_TECHNICIAN, OPENING_HOURS_GROUP_GENERIC);

		// Criando os Técnicos do Prestador de Serviço Matriz e Filial
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECHNICIAN_MATRIZ_01, PASSWORD, ROLES_TECHNICIAN,
				SERVICE_PROVIDER_GENERIC_MATRIZ);
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECHNICIAN_MATRIZ_02, PASSWORD, ROLES_TECHNICIAN,
				SERVICE_PROVIDER_GENERIC_MATRIZ);
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECHNICIAN_FILIAL_01, PASSWORD, ROLES_TECHNICIAN,
				SERVICE_PROVIDER_GENERIC_FILIAL);
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECHNICIAN_FILIAL_02, PASSWORD, ROLES_TECHNICIAN,
				SERVICE_PROVIDER_GENERIC_FILIAL);
	}
}
