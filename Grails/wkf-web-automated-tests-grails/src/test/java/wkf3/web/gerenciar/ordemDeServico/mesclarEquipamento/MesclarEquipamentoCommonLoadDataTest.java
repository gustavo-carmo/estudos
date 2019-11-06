package wkf3.web.gerenciar.ordemDeServico.mesclarEquipamento;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import careman.html.TestBase;

public class MesclarEquipamentoCommonLoadDataTest extends TestBase {

	private static final String WORKFLOW_MERGE_EQUIP = "WORKFLOW_MERGE_EQUIP";
	private static final String MERGE_EQUIP_NEW = "MERGE_EQUIP_NEW";
	private static final String MERGE_EQUIP_END = "MERGE_EQUIP_END";
	private static final String SERVICE_MERGE_EQUIP_STOCK_CUSTOMER = "SERVICE_MERGE_EQUIP_STOCK_CL";
	private static final String SERVICE_MERGE_EQUIP_STOCK_SERVICE_PROVIDER = "SERVICE_MERGE_EQUIP_STOCK_SP";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";

	private static final List<String> STATUS_STEP = Arrays.asList(MERGE_EQUIP_NEW, MERGE_EQUIP_END);

	private static final List<String> FLAGS_SERVICE_MERGE_EQUIP_STOCK_CUSTOMER = Arrays.asList(
			"service.allowsCreateEquipmentOnServiceOrder = true",
			"service.allowsCreateNewEquipmentOnServiceOrder = true",
			"service.onlyEquipmentFromCustomerAtServiceOrder = true",
			"service.onlyNewEquipmentFromCustomerAtServiceOrder = true");

	private static final List<String> FLAGS_SERVICE_MERGE_EQUIP_STOCK_SERVICE_PROVIDER = Arrays.asList(
			"service.allowsCreateEquipmentOnServiceOrder = true",
			"service.allowsCreateNewEquipmentOnServiceOrder = true",
			"service.onlyEquipmentFromServiceProviderStockOnServiceOrder = true",
			"service.onlyNewEquipmentFromServiceProviderStockOnServiceOrder = true");

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
	public void commonLoadDataMesclarEquipamentoScripts() {

		// Criando os Status
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), MERGE_EQUIP_NEW, "START");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), MERGE_EQUIP_END, "END_WITH_SUCCESS");

		// Criando o Workflow
		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_MERGE_EQUIP);

		// Adicionando o Passo no Workflow
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_MERGE_EQUIP, MERGE_EQUIP_NEW,
				MERGE_EQUIP_END);

		// Adicionando no Perfil de Administrador
		LoadDataHelper.addStatusToExistentProfile(getDriver(), getBaseUrl(), "ADMINISTRATOR", "SERVICE_ORDER", STATUS_STEP);

		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_MERGE_EQUIP_STOCK_CUSTOMER,
				CONTRACTOR_GENERIC, SERVICE_GROUP_GENERIC, WORKFLOW_MERGE_EQUIP, OPENING_HOURS_GROUP_GENERIC,
				FLAGS_SERVICE_MERGE_EQUIP_STOCK_CUSTOMER, null);
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_MERGE_EQUIP_STOCK_SERVICE_PROVIDER,
				CONTRACTOR_GENERIC, SERVICE_GROUP_GENERIC, WORKFLOW_MERGE_EQUIP, OPENING_HOURS_GROUP_GENERIC,
				FLAGS_SERVICE_MERGE_EQUIP_STOCK_SERVICE_PROVIDER, null);
	}
}
