package wkf3.web.gerenciar.ordemDeServico.regrasDeValidacao;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import careman.html.TestBase;

public class RegrasDeValidacaoCommonLoadDataTest extends TestBase {

	private static final String VALIDATION_RULE_NEW = "VALIDATION_RULE_NEW";
	private static final String VALIDATION_RULE_EQUIP_REQUIRED = "VR_EQUIP_REQUIRED";
	private static final String VALIDATION_RULE_EQUIP_INVENTORIED_REQUIRED = "VR_EQUIP_INVENT_REQUIRED";
	private static final String VALIDATION_RULE_EQUIP_LOGIC_NUMBER_REQUIRED = "VR_EQUIP_LOGIC_NUMB_REQUIRED";
	private static final String VALIDATION_RULE_EQUIP_SERIAL_NUMBER_REQUIRED = "VR_EQUIP_SERIAL_NUMB_REQUIRED";
	private static final String VALIDATION_RULE_NEW_EQUIP_FORBIDDEN = "VR_NEW_EQUIP_FORBIDDEN";
	private static final String VALIDATION_RULE_NEW_EQUIP_REQUIRED = "VR_NEW_EQUIP_REQUIRED";
	private static final String VALIDATION_RULE_NEW_EQUIP_INVENTORIED_REQUIRED = "VR_NEW_EQUIP_INVENT_REQUIRED";
	private static final String VALIDATION_RULE_IF_NEW_EQUIP_SERIAL_NUMBER_REQUIRED = "VR_IF_NEW_EQUIP_SERIAL_NUMB_REQUIRED";
	private static final String VALIDATION_RULE_SERIAL_NUMBER_NEW_EQUIP_REQUIRED = "VR_SERIAL_NUMB_NEW_EQUIP_REQUIRED";
	private static final String VALIDATION_RULE_LOGIC_NUMBER_NEW_EQUIP_REQUIRED = "VR_LOGIC_NUMB_NEW_EQUIP_REQUIRED";
	private static final String VALIDATION_RULE_SERVICE_PROVIDER_REQUIRED = "VR_SERVICE_PROVIDER_REQUIRED";
	private static final String VALIDATION_RULE_REASON_FOR_CANCELLATION_REQUIRED = "VR_REASON_CANCEL_REQUIRED";
	private static final String VALIDATION_RULE_EXTERNAL_CONTRACTOR_ID_REQUIRED = "VR_EXTERNAL_CONTRACTOR_ID_REQUIRED";
	private static final String VALIDATION_RULE_FIND_DEFECT_REQUIRED = "VR_FIND_DEFECT_REQUIRED";
	private static final String VALIDATION_RULE_SOLUTION_REQUIRED = "VR_SOLUTION_REQUIRED";
	private static final String VALIDATION_RULE_INFORMED_DEFECT_REQUIRED = "VR_INFORMED_DEFECT_REQUIRED";
	private static final String VALIDATION_RULE_TRACKING_NUMBER_REQUIRED = "VR_TRACKING_NUMBER_REQUIRED";
	private static final String VALIDATION_RULE_SERVICE_PROVIDER_TECHNICIAN_REQUIRED = "VR_SP_TECHNICIAN_REQUIRED";
	private static final String VALIDATION_RULE_ATTACHMENT_TRUE = "VR_ATTACHMENT_TRUE";
	private static final String WORKFLOW_VALIDATION_RULE = "WORKFLOW_VALIDATION_RULE";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";

//	private static final String VALIDATION_RULE_SERVICE_PROVIDER_AUTOMATION_ROUTING_TRUE = "VR_SP_AUTOMATION_ROUTING_TRUE";
//	private static final String VALIDATION_RULE_ATTACHMENT_CUSTOMER_SIGNATURE_TRUE = "VR_ATTACHMENT_CUSTOMER_SIGNATURE_TRUE";
//	private static final String VALIDATION_RULE_MOBILE_CHECK_IN_REQUIRED = "VR_MOBILE_CHECK_IN_REQUIRED";

	private static final List<String> STATUS_VALIDATION_RULE = Arrays.asList(VALIDATION_RULE_NEW, VALIDATION_RULE_EQUIP_REQUIRED,
			VALIDATION_RULE_EQUIP_INVENTORIED_REQUIRED, VALIDATION_RULE_EQUIP_LOGIC_NUMBER_REQUIRED, VALIDATION_RULE_EQUIP_SERIAL_NUMBER_REQUIRED,
			VALIDATION_RULE_NEW_EQUIP_FORBIDDEN, VALIDATION_RULE_NEW_EQUIP_REQUIRED, VALIDATION_RULE_NEW_EQUIP_INVENTORIED_REQUIRED, VALIDATION_RULE_SERIAL_NUMBER_NEW_EQUIP_REQUIRED,
			VALIDATION_RULE_IF_NEW_EQUIP_SERIAL_NUMBER_REQUIRED, VALIDATION_RULE_LOGIC_NUMBER_NEW_EQUIP_REQUIRED,
			VALIDATION_RULE_SERVICE_PROVIDER_REQUIRED, VALIDATION_RULE_REASON_FOR_CANCELLATION_REQUIRED,
			VALIDATION_RULE_ATTACHMENT_TRUE, VALIDATION_RULE_EXTERNAL_CONTRACTOR_ID_REQUIRED, 
			VALIDATION_RULE_FIND_DEFECT_REQUIRED, VALIDATION_RULE_SOLUTION_REQUIRED,
			VALIDATION_RULE_INFORMED_DEFECT_REQUIRED, VALIDATION_RULE_TRACKING_NUMBER_REQUIRED, VALIDATION_RULE_SERVICE_PROVIDER_TECHNICIAN_REQUIRED);
//			VALIDATION_RULE_SERVICE_PROVIDER_AUTOMATION_ROUTING_TRUE,VALIDATION_RULE_ATTACHMENT_CUSTOMER_SIGNATURE_TRUE,VALIDATION_RULE_MOBILE_CHECK_IN_REQUIRED);

	private static final List<String> FLAGS_SERVICE_VALIDATION_RULE = Arrays.asList(
			"service.allowsCreateEquipmentOnServiceOrder = true",
			"service.allowsCreateNewEquipmentOnServiceOrder = true",
			"service.onlyEquipmentFromCustomerAtServiceOrder = true",
			"service.onlyNewEquipmentFromCustomerAtServiceOrder = true");

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
	public void commonLoadDataRegraDeValidacaoScripts() {

		// Criando os Status para os Passos do Workflow
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_NEW, "START");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_EQUIP_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_EQUIP_INVENTORIED_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_EQUIP_LOGIC_NUMBER_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_EQUIP_SERIAL_NUMBER_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_NEW_EQUIP_FORBIDDEN, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_NEW_EQUIP_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_NEW_EQUIP_INVENTORIED_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_IF_NEW_EQUIP_SERIAL_NUMBER_REQUIRED,
				null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_SERIAL_NUMBER_NEW_EQUIP_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_LOGIC_NUMBER_NEW_EQUIP_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_SERVICE_PROVIDER_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_REASON_FOR_CANCELLATION_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_EXTERNAL_CONTRACTOR_ID_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_FIND_DEFECT_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_SOLUTION_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_INFORMED_DEFECT_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_TRACKING_NUMBER_REQUIRED, null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_SERVICE_PROVIDER_TECHNICIAN_REQUIRED,
				null);
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_ATTACHMENT_TRUE, null);
//		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_SERVICE_PROVIDER_AUTOMATION_ROUTING_TRUE,
//				null);
//		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_ATTACHMENT_CUSTOMER_SIGNATURE_TRUE,
//				null);
//		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), VALIDATION_RULE_MOBILE_CHECK_IN_REQUIRED, null);

		// Criando o Workflow de Regras de Validação
		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE);

		// Adicionando os Passos no Workflow de Regras de Validação
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_EQUIP_REQUIRED, Arrays.asList("rule_VALIDATION_equipment_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_EQUIP_INVENTORIED_REQUIRED,
				Arrays.asList("rule_VALIDATION_hasEquipmentInventoried_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_EQUIP_LOGIC_NUMBER_REQUIRED,
				Arrays.asList("rule_VALIDATION_hasEquipmentContractorExternalID_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_EQUIP_SERIAL_NUMBER_REQUIRED,
				Arrays.asList("rule_VALIDATION_hasEquipmentSerialNumber_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_NEW_EQUIP_FORBIDDEN, Arrays.asList("rule_VALIDATION_newEquipment_FORBIDDEN"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_NEW_EQUIP_REQUIRED, Arrays.asList("rule_VALIDATION_newEquipment_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_NEW_EQUIP_INVENTORIED_REQUIRED,
				Arrays.asList("rule_VALIDATION_hasNewEquipmentInventoried_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_IF_NEW_EQUIP_SERIAL_NUMBER_REQUIRED,
				Arrays.asList("rule_VALIDATION_hasNewEquipmentSerialNumberIfHasNewEquipment_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_SERIAL_NUMBER_NEW_EQUIP_REQUIRED,
				Arrays.asList("rule_VALIDATION_hasNewEquipmentSerialNumber_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_LOGIC_NUMBER_NEW_EQUIP_REQUIRED,
				Arrays.asList("rule_VALIDATION_hasNewEquipmentContractorExternalID_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_SERVICE_PROVIDER_REQUIRED, Arrays.asList("rule_VALIDATION_serviceProvider_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_REASON_FOR_CANCELLATION_REQUIRED,
				Arrays.asList("rule_VALIDATION_reasonForCancellation_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_EXTERNAL_CONTRACTOR_ID_REQUIRED,
				Arrays.asList("rule_VALIDATION_contractorExternalId_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_FIND_DEFECT_REQUIRED, Arrays.asList("rule_VALIDATION_defect_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_SOLUTION_REQUIRED, Arrays.asList("rule_VALIDATION_solution_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_INFORMED_DEFECT_REQUIRED, Arrays.asList("rule_VALIDATION_informedDefect_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_TRACKING_NUMBER_REQUIRED, Arrays.asList("rule_VALIDATION_trackingNumber_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_SERVICE_PROVIDER_TECHNICIAN_REQUIRED,
				Arrays.asList("rule_VALIDATION_serviceProviderTechnician_REQUIRED"));
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
				VALIDATION_RULE_ATTACHMENT_TRUE, Arrays.asList("rule_VALIDATION_hasAttachment_ASSERT_TRUE"));
//		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
//				VALIDATION_RULE_SERVICE_PROVIDER_AUTOMATION_ROUTING_TRUE,
//				Arrays.asList("rule_VALIDATION_hasRoutingCustomTrigger_ASSERT_TRUE"));
//		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
//				VALIDATION_RULE_ATTACHMENT_CUSTOMER_SIGNATURE_TRUE,
//				Arrays.asList("rule_VALIDATION_hasAttachmentTypeCustomerSignature_ASSERT_TRUE"));
//		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_VALIDATION_RULE, VALIDATION_RULE_NEW,
//				VALIDATION_RULE_MOBILE_CHECK_IN_REQUIRED, Arrays.asList("rule_VALIDATION_checkIn_REQUIRED"));

		// Adicionando os Status do Workflow no Perfil de Existente de
		// Administrador
		LoadDataHelper.addStatusToExistentProfile(getDriver(), getBaseUrl(), "ADMINISTRATOR", "SERVICE_ORDER", STATUS_VALIDATION_RULE);

		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_VALIDATION_RULE,
				CONTRACTOR_GENERIC, SERVICE_GROUP_GENERIC, WORKFLOW_VALIDATION_RULE,
				OPENING_HOURS_GROUP_GENERIC, FLAGS_SERVICE_VALIDATION_RULE, null);
	}
}
