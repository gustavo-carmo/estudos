package wkf3.web.gerenciar.ordemDeServico.trigger;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import careman.html.TestBase;

public class TriggerCommonLoadDataTest extends TestBase {

	private static final String WORKFLOW_COMMON_TRIGGER = "WORKFLOW_COMMON_TRIGGER";
	private static final String TRIGGER_NEW = "TRIGGER_NEW";
	private static final String TRIGGER_ROUTING_DATE = "TRIGGER_ROUTING_DATE";
	private static final String TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_SERVICE_PROVIDER = "TRIGGER_ASSOCIATE_NEW_EQUIP_TO_SP";
	private static final String TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_ASSOCIATE_TO_SERVICE_PROVIDER = "TRIGGER_DISASSO_CUSTOMER_GOOD_ASSOCI_TO_SP";
	private static final String TRIGGER_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER = "TRIGGER_ASSOCIATE_EQUIP_TO_SP";
	private static final String TRIGGER_CHANGE_EQUIPMENT_TO_BAD = "TRIGGER_CHANGE_EQUIP_TO_BAD";
	private static final String TRIGGER_CHANGE_EQUIPMENT_TO_GOOD = "TRIGGER_CHANGE_EQUIP_TO_GOOD";
	private static final String TRIGGER_CHANGE_NEW_EQUIPMENT_TO_BAD = "TRIGGER_CHANGE_NEW_EQUIP_TO_BAD";
	private static final String TRIGGER_CHANGE_NEW_EQUIPMENT_TO_GOOD = "TRIGGER_CHANGE_NEW_EQUIP_TO_GOOD";
	private static final String TRIGGER_ASSOCIATE_EQUIPMENT_TO_CUSTOMER = "TRIGGER_ASSOCIATE_EQUIP_TO_CUSTOMER";
	private static final String TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_CUSTOMER = "TRIGGER_ASSOCIATE_NEW_EQUIP_TO_CUSTOMER";
	private static final String TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER = "TRIGGER_DISASSOCIATE_EQUIP_OF_CUSTOMER";
	private static final String TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_CUSTOMER = "TRIGGER_DISASSOCIATE_NEW_EQUIP_OF_CUSTOMER";
	private static final String TRIGGER_TURN_EQUIPMENT_INVENTORIED = "TRIGGER_TURN_EQUIP_INVENTORIED";
	private static final String TRIGGER_TURN_NEW_EQUIPMENT_INVENTORIED = "TRIGGER_TURN_NEW_EQUIP_INVENTORIED";
	private static final String TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_DELETE_NEW_EQUIPMENT_IF_TEMPORARY = "TRIGGER_DIS_NEQ_CUTOMER_GOOD_DEL_NEQ_IF_TEMP";
	private static final String TRIGGER_IF_NEW_EQUIPMENT_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_DELETE_NEW_EQUIPMENT_IF_TEMPORARY = "TRIGGER_IF_NEQ_DIS_EQUIP_CL_GOOD_DEL_NEQ_IF_TEMP";
	private static final String TRIGGER_IF_NEW_EQUIPMENT_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_BAD_AND_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER = "TRIGGER_IF_NEQ_DIS_EQUIP_CL_BAD_ASS_EQUIP_TO_SP";
	private static final String SERVICE_COMMON_TRIGGER = "SERVICE_COMMON_TRIGGER";
	private static final String SERVICE_COMMON_TRIGGER_STOCK_SP = "SERVICE_COMMON_TRIGGER_STOCK_SP";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";

//	private static final String TRIGGER_DISASSOCIATE_EQUIPMENT_OF_SERVICE_PROVIDER = "TRIGGER_DISASSOCIATE_EQUIP_OF_SP";
//	private static final String TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_SERVICE_PROVIDER = "TRIGGER_DISASSOCIATE_NEW_EQUIP_OF_SP";

	  private static final List<String> STATUS_TRIGGER = Arrays.asList(TRIGGER_NEW, TRIGGER_ROUTING_DATE,
			  TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_SERVICE_PROVIDER,
			  TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_ASSOCIATE_TO_SERVICE_PROVIDER,
			  TRIGGER_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER, TRIGGER_CHANGE_EQUIPMENT_TO_BAD, TRIGGER_CHANGE_EQUIPMENT_TO_GOOD,
			  TRIGGER_CHANGE_NEW_EQUIPMENT_TO_BAD, TRIGGER_CHANGE_NEW_EQUIPMENT_TO_GOOD, 
			  TRIGGER_ASSOCIATE_EQUIPMENT_TO_CUSTOMER, TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_CUSTOMER, 
			  TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER, TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_CUSTOMER,
			  TRIGGER_TURN_EQUIPMENT_INVENTORIED, TRIGGER_TURN_NEW_EQUIPMENT_INVENTORIED,
			  TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_DELETE_NEW_EQUIPMENT_IF_TEMPORARY,
			  TRIGGER_IF_NEW_EQUIPMENT_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_DELETE_NEW_EQUIPMENT_IF_TEMPORARY,
			  TRIGGER_IF_NEW_EQUIPMENT_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_BAD_AND_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER);
//			  TRIGGER_DISASSOCIATE_EQUIPMENT_OF_SERVICE_PROVIDER, TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_SERVICE_PROVIDER);
	  
	  private static final List<String> FLAGS_SERVICE_COMMON_TRIGGER = Arrays.asList(
				"service.allowsCreateEquipmentOnServiceOrder = true",
				"service.allowsCreateNewEquipmentOnServiceOrder = true",
				"service.onlyEquipmentFromCustomerAtServiceOrder = true",
				"service.onlyNewEquipmentFromCustomerAtServiceOrder = true");

		private static final List<String> FLAGS_SERVICE_COMMON_TRIGGER_STOCK_SP = Arrays.asList(
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
	public void commonLoadDataTriggerScripts() {

		// Status para criar os passos das TRIGGERS
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_NEW, "START");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_ROUTING_DATE, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_SERVICE_PROVIDER,	null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(),
				TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_ASSOCIATE_TO_SERVICE_PROVIDER, null);
		
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_CHANGE_EQUIPMENT_TO_BAD, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_CHANGE_EQUIPMENT_TO_GOOD, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_CHANGE_NEW_EQUIPMENT_TO_BAD, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_CHANGE_NEW_EQUIPMENT_TO_GOOD, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_ASSOCIATE_EQUIPMENT_TO_CUSTOMER, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_CUSTOMER, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_CUSTOMER, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), 
				TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_DELETE_NEW_EQUIPMENT_IF_TEMPORARY, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_TURN_EQUIPMENT_INVENTORIED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_TURN_NEW_EQUIPMENT_INVENTORIED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), 
				TRIGGER_IF_NEW_EQUIPMENT_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_DELETE_NEW_EQUIPMENT_IF_TEMPORARY, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), 
				TRIGGER_IF_NEW_EQUIPMENT_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_BAD_AND_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER, null);
		
//		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_DISASSOCIATE_EQUIPMENT_OF_SERVICE_PROVIDER, null);
//		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_SERVICE_PROVIDER, null);

		// Workflow que conterá os passos das Triggers e das Regras de Validação
		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER);

		// Os passos das Triggers, porém sem ativar nenhuma Trigger em especial
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW,
				TRIGGER_ROUTING_DATE, Arrays.asList("rule_TRIGGER_defineRoutedDate"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW,
				TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_SERVICE_PROVIDER, Arrays.asList("rule_TRIGGER_associateNewEquipmentToServiceProvider"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_SERVICE_PROVIDER,
				TRIGGER_ASSOCIATE_NEW_EQUIPMENT_TO_CUSTOMER, Arrays.asList("rule_TRIGGER_associateNewEquipmentToCustomer"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW,
				TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_ASSOCIATE_TO_SERVICE_PROVIDER, Arrays.asList("rule_TRIGGER_disassociateEquipmentFromCustomerAndSetToGOOD"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER, Arrays.asList("rule_TRIGGER_associateEquipmentToServiceProvider"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER, TRIGGER_ASSOCIATE_EQUIPMENT_TO_CUSTOMER, Arrays.asList("rule_TRIGGER_associateEquipmentToCustomer"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_CHANGE_EQUIPMENT_TO_BAD, Arrays.asList("rule_TRIGGER_convertEquipmentToSituationBAD"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_CHANGE_EQUIPMENT_TO_BAD, TRIGGER_CHANGE_EQUIPMENT_TO_GOOD, Arrays.asList("rule_TRIGGER_convertEquipmentToSituationGOOD"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_CHANGE_NEW_EQUIPMENT_TO_BAD, Arrays.asList("rule_TRIGGER_convertNewEquipmentToSituationBAD"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_CHANGE_NEW_EQUIPMENT_TO_BAD, TRIGGER_CHANGE_NEW_EQUIPMENT_TO_GOOD, Arrays.asList("rule_TRIGGER_convertNewEquipmentToSituationGOOD"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER, Arrays.asList("rule_TRIGGER_disassociateEquipmentFromCustomer"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_CUSTOMER, Arrays.asList("rule_TRIGGER_disassociateNewEquipmentFromCustomer"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW,
				TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_DELETE_NEW_EQUIPMENT_IF_TEMPORARY, Arrays.asList("rule_TRIGGER_disassociateNewEquipmentFromCustomerAndSetToGOOD"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_TURN_EQUIPMENT_INVENTORIED, Arrays.asList("rule_TRIGGER_turnEquipmentAsInventoried"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_TURN_NEW_EQUIPMENT_INVENTORIED, Arrays.asList("rule_TRIGGER_turnNewEquipmentAsInventoried"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW,
				TRIGGER_IF_NEW_EQUIPMENT_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_GOOD_AND_DELETE_NEW_EQUIPMENT_IF_TEMPORARY, Arrays.asList("rule_TRIGGER_ifHasNewEquipmentDisassociateEquipmentFromCustomerAndSetToGOOD"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW,
				TRIGGER_IF_NEW_EQUIPMENT_DISASSOCIATE_EQUIPMENT_OF_CUSTOMER_KEEP_SITUATION_BAD_AND_ASSOCIATE_EQUIPMENT_TO_SERVICE_PROVIDER, Arrays.asList("rule_TRIGGER_ifHasNewEquipmentDisassociateEquipmentFromCustomerAndSetToBAD"));

//		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_DISASSOCIATE_EQUIPMENT_OF_SERVICE_PROVIDER, Arrays.asList("rule_TRIGGER_disassociateEquipmentFromServiceProvider"));
//		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_COMMON_TRIGGER, TRIGGER_NEW, TRIGGER_DISASSOCIATE_NEW_EQUIPMENT_OF_SERVICE_PROVIDER, Arrays.asList("rule_TRIGGER_disassociateNewEquipmentFromServiceProvider"));

		// O Perfil de Administrador Já Deve Estar Criado
		LoadDataHelper.addStatusToExistentProfile(getDriver(), getBaseUrl(), "ADMINISTRATOR", "SERVICE_ORDER", STATUS_TRIGGER);

		// Serviço do Contratante que usará o Estoque do Cliente
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_COMMON_TRIGGER,
				CONTRACTOR_GENERIC, SERVICE_GROUP_GENERIC, WORKFLOW_COMMON_TRIGGER,
				OPENING_HOURS_GROUP_GENERIC, FLAGS_SERVICE_COMMON_TRIGGER, null);
		
		// Serviço do contratante que usará o Estoque do Prestador
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_COMMON_TRIGGER_STOCK_SP,
				CONTRACTOR_GENERIC, SERVICE_GROUP_GENERIC, WORKFLOW_COMMON_TRIGGER, OPENING_HOURS_GROUP_GENERIC,
				FLAGS_SERVICE_COMMON_TRIGGER_STOCK_SP, null);
	}
}