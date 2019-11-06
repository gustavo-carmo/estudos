package br.com.workfinity.web.tests.commonBackground;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.serviceAreas.ServiceAreaShowPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderTypeCrudForm;
import careman.html.TestBase;

public class CommonBackground extends TestBase {

	private static final String USER_GENERIC = "USER_GENERIC";

	// Horário de funcionamento
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";

	// Contratante
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";

	// Grupo de serviço e serviço genérico
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String SERVICE_GENERIC = "SERVICE_GENERIC";

	// Grupo de serviço e serviço de laboratório
	private static final String SERVICE_GROUP_GENERIC_LAB = "SERVICE_GROUP_GENERIC_LAB";
	private static final String SERVICE_GENERIC_LAB = "SERVICE_GENERIC_LAB";

	// Dados do cliente
	private static final String CUSTOMER_GENERIC = "CUSTOMER_GENERIC";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	// Workflow genérico simples
	private static final String WORKFLOW_GENERIC = "WORKFLOW_GENERIC";
	private static final String STATUS_GENERIC_NEW = "STATUS_GENERIC_NEW";
	private static final String STATUS_GENERIC_END = "STATUS_GENERIC_END";

	// Prestadores de serviço
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String SERVICE_PROVIDER_GENERIC_FILIAL = "SERVICE_PROVIDER_GENERIC_FILIAL";
	private static final String SERVICE_PROVIDER_GENERIC_TECHNICIAN = "SERVICE_PROVIDER_GENERIC_TECHNICIAN";
	private static final String SERVICE_PROVIDER_GENERIC_LAB = "SERVICE_PROVIDER_GENERIC_LAB";

	// Com relação aos equipamentos
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String MODEL_GENERIC_PART = "MODEL_GENERIC_PART";
	private static final String MODEL_GENERIC_ACCESSORY = "MODEL_GENERIC_ACCESSORY";
	private static final String MODEL_GENERIC_SUPLY = "MODEL_GENERIC_SUPLY";
	private static final String MODEL_GENERIC_COMPONENT = "MODEL_GENERIC_COMPONENT";

	// Solução genérica
	private static final String SOLUTION_GENERIC = "SOLUTION_GENERIC";

	// Área de serviço, transportadora genéricos
	private static final String SERVICE_AREAS_GENERIC = "SERVICE_AREAS_GENERIC";
	private static final String WARRANTY_PROVIDER_GENERIC = "WARRANTY_PROVIDER_GENERIC";
	private static final String WARRANTY_PROVIDER_GENERIC_SERVICE = "WARRANTY_GENERIC_SERVICE";
	private static final String CARRIER_GENERIC = "CARRIER_GENERIC";

	// Alguns status são usados em Consumo de OS e LogisticParallelTests (Ordem de Remessa)
	private static final String WORKFLOW_SHIP_ORDER_GENERIC = "WORKFLOW_SHIP_ORDER_GENERIC";
	private static final String SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION = "SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION";
	private static final String SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION = "SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION";
	private static final String STATUS_SHIP_ORDER_GENERIC_NEW = "STATUS_SHIP_ORDER_GENERIC_NEW";
	private static final String STATUS_SHIP_ORDER_GENERIC_MEIO_BAD = "STATUS_SHIP_ORDER_GENERIC_MEIO_BAD";
	private static final String STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD = "STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD";
	private static final String STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT = "STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT";
	private static final String STATUS_SHIP_ORDER_GENERIC_CANCELLED = "STATUS_SHIP_ORDER_GENERIC_CANCELLED";
	private static final String STATUS_SHIP_ORDER_GENERIC_END = "STATUS_SHIP_ORDER_GENERIC_END";
	
	// Workflow Basic Shipment Order
	private static final String WORKFLOW_SHIP_BASIC = "WORKFLOW_SHIP_BASIC";
	private static final String STATUS_SHIP_BASIC_NEW = "STATUS_SHIP_BASIC_NEW";
	private static final String STATUS_SHIP_BASIC_END = "STATUS_SHIP_BASIC_END";
	private static final String STATUS_SHIP_BASIC_CANCELLED = "STATUS_SHIP_BASIC_CANCELLED";

	// Para os testes de Laboratório (Ordem de Reparo)
	private static final String WORKFLOW_REPAIR_ORDER_GENERIC = "WORKFLOW_REPAIR_ORDER_GENERIC";
	private static final String STATUS_REPAIR_ORDER_GENERIC_NEW = "STATUS_REPAIR_ORDER_GENERIC_NEW";
	private static final String STATUS_REPAIR_ORDER_GENERIC_MEIO = "STATUS_REPAIR_ORDER_GENERIC_MEIO";
	private static final String REPAIR_LEVEL_GENERIC = "RLG";

	private static final List<String> STATUS_OS = Arrays.asList(STATUS_GENERIC_NEW, STATUS_GENERIC_END);

	private static final List<String> STATUS_SHIP = Arrays.asList(STATUS_SHIP_ORDER_GENERIC_NEW,
			STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD, STATUS_SHIP_ORDER_GENERIC_MEIO_BAD,
			STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT, STATUS_SHIP_ORDER_GENERIC_END,
			STATUS_SHIP_ORDER_GENERIC_CANCELLED, STATUS_SHIP_BASIC_NEW, STATUS_SHIP_BASIC_END,
			STATUS_SHIP_BASIC_CANCELLED);

	private static final List<String> STATUS_RO = Arrays.asList(STATUS_REPAIR_ORDER_GENERIC_NEW,
			STATUS_REPAIR_ORDER_GENERIC_MEIO);

	private static final List<String> FLAGS_SERVICE = Arrays.asList(
			"service.allowsCreateEquipmentOnServiceOrder = true",
			"service.allowsCreateNewEquipmentOnServiceOrder = true");

	private static final List<String> NEW_MEIO_GOOD = Arrays.asList("rule_VALIDATION_invoiceDate_REQUIRED",
			"rule_VALIDATION_invoiceNumber_REQUIRED");

	private static final List<String> NEW_MEIO_BAD = Arrays.asList("rule_VALIDATION_invoiceDate_REQUIRED",
			"rule_VALIDATION_invoiceNumber_REQUIRED");

	private static final List<String> MEIO_TRANS = Arrays.asList("rule_VALIDATION_invoiceDate_REQUIRED",
			"rule_VALIDATION_invoiceNumber_REQUIRED", "rule_VALIDATION_outboundDate_REQUIRED",
			"rule_VALIDATION_carrier_REQUIRED");

	private static final List<String> TRANS_END = Arrays.asList("rule_VALIDATION_invoiceDate_REQUIRED",
			"rule_VALIDATION_invoiceNumber_REQUIRED", "rule_VALIDATION_carrier_REQUIRED");

	private static final List<String> MEIO_GOOD_END = Arrays.asList("rule_VALIDATION_invoiceDate_REQUIRED",
			"rule_VALIDATION_invoiceNumber_REQUIRED", "rule_VALIDATION_outboundDate_REQUIRED",
			"rule_VALIDATION_allowOnlyGoodEquipments_REQUIRED", "rule_VALIDATION_carrier_REQUIRED");

	private static final List<String> MEIO_BAD_END = Arrays.asList("rule_VALIDATION_invoiceDate_REQUIRED",
			"rule_VALIDATION_invoiceNumber_REQUIRED", "rule_VALIDATION_outboundDate_REQUIRED",
			"rule_VALIDATION_allowOnlyBadEquipments_REQUIRED", "rule_VALIDATION_carrier_REQUIRED");

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_AREAS", "ROLE_SHIPMENT_ORDER_TYPE");

	private String idShipmentOrderTypeOne;
	private String idShipmentOrderTypeSome;
	private String idServiceAreas;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, true);
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void commonBackgroundGenericScripts() {

		// Criando o Horário de Atendimento e o Grupo de Horário de Atendimento
		// Genéricos
		LoadDataHelper.createOpeningHours(getDriver(), getBaseUrl(), "Friday", 7, 17);
		LoadDataHelper.createOpeningHoursGroup(getDriver(), getBaseUrl(), OPENING_HOURS_GROUP_GENERIC, 7, 17);

		// Criando a Família Genérica e o Fabricante Genérico
		LoadDataHelper.createFamily(getDriver(), getBaseUrl(), FAMILY_GENERIC);
		LoadDataHelper.createManufacturer(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC);

		// Criando a Solução Genérica
		LoadDataHelper.createSolution(getDriver(), getBaseUrl(), SOLUTION_GENERIC, true);

		// Criando os Modelos Genéricos
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC,
				MODEL_GENERIC_PRODUCT, "PRODUCT");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_GENERIC_PART,
				"PART");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC,
				MODEL_GENERIC_ACCESSORY, "ACCESSORY");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC, MODEL_GENERIC_SUPLY,
				"SUPPLY");
		LoadDataHelper.createModel(getDriver(), getBaseUrl(), MANUFACTORE_GENERIC, FAMILY_GENERIC,
				MODEL_GENERIC_COMPONENT, "COMPONENT");

		// Criando os Status Genéricos de Ordem de Serviço
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_GENERIC_NEW, "START");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_GENERIC_END, "END_WITH_SUCCESS");

		// Criando o Workflow Genérico de Ordem de Serviço
		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_GENERIC);

		// Criando o Status Genérico do Workflow de Ordem de Serviço
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_GENERIC, STATUS_GENERIC_NEW,
				STATUS_GENERIC_END);

		// Criando os Status Genéricos de Ordem de Remessa
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_ORDER_GENERIC_NEW, "START",
				"SHIPMENT_ORDER");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_ORDER_GENERIC_MEIO_BAD, null,
				"SHIPMENT_ORDER");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD, null,
				"SHIPMENT_ORDER");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT, null,
				"SHIPMENT_ORDER");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_ORDER_GENERIC_END, "END_WITH_SUCCESS",
				"SHIPMENT_ORDER");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_ORDER_GENERIC_CANCELLED, "END_WITH_FAIL",
				"SHIPMENT_ORDER");

		// Criando o Workflow Genérico de Ordem de Remessa
		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC, "SHIPMENT_ORDER");

		// Criando o Status Genérico do Workflow de Ordem de Remessa
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_NEW, STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD, NEW_MEIO_GOOD);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_NEW, STATUS_SHIP_ORDER_GENERIC_MEIO_BAD, NEW_MEIO_BAD);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_NEW, STATUS_SHIP_ORDER_GENERIC_END);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD, STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT, MEIO_TRANS);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT, STATUS_SHIP_ORDER_GENERIC_END, TRANS_END);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_MEIO_TRANSPORT, STATUS_SHIP_ORDER_GENERIC_CANCELLED);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD, STATUS_SHIP_ORDER_GENERIC_END, MEIO_GOOD_END);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_MEIO_GOOD, STATUS_SHIP_ORDER_GENERIC_CANCELLED);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_MEIO_BAD, STATUS_SHIP_ORDER_GENERIC_END, MEIO_BAD_END);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_ORDER_GENERIC,
				STATUS_SHIP_ORDER_GENERIC_MEIO_BAD, STATUS_SHIP_ORDER_GENERIC_CANCELLED);
		
		// Criando os Status Básico de Ordem de Remessa
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_BASIC_NEW, "START",
				"SHIPMENT_ORDER");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_BASIC_END, "END_WITH_SUCCESS",
				"SHIPMENT_ORDER");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_SHIP_BASIC_CANCELLED, "END_WITH_FAIL",
				"SHIPMENT_ORDER");
		
		// Criando o Workflow básico de Ordem de Remessa
		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_BASIC, "SHIPMENT_ORDER");
		
		// Criando o Status Básico do Workflow de Ordem de Remessa
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_BASIC,
				STATUS_SHIP_BASIC_NEW, STATUS_SHIP_BASIC_END);
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_SHIP_BASIC,
				STATUS_SHIP_BASIC_NEW, STATUS_SHIP_BASIC_CANCELLED);

		// Criando os Status Genéricos de Ordem de Reparo
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_REPAIR_ORDER_GENERIC_NEW, "START",
				"REPAIR_ORDER");
		LoadDataHelper.createStatus(getDriver(), getBaseUrl(), STATUS_REPAIR_ORDER_GENERIC_MEIO, null, "REPAIR_ORDER");

		// Criando o Workflow Genérico de Ordem de Reparo
		LoadDataHelper.createWorkflow(getDriver(), getBaseUrl(), WORKFLOW_REPAIR_ORDER_GENERIC, "REPAIR_ORDER");

		// Criando o Status Genérico do Workflow de Ordem de Reparo
		LoadDataHelper.addStepToWorkflow(getDriver(), getBaseUrl(), WORKFLOW_REPAIR_ORDER_GENERIC,
				STATUS_REPAIR_ORDER_GENERIC_NEW, STATUS_REPAIR_ORDER_GENERIC_MEIO);

		// Adicionando o Status à um Perfil Existente
		LoadDataHelper.addStatusToExistentProfile(getDriver(), getBaseUrl(), "ADMINISTRATOR", "SERVICE_ORDER",
				STATUS_OS);
		LoadDataHelper.addStatusToExistentProfile(getDriver(), getBaseUrl(), "ADMINISTRATOR", "SHIPMENT_ORDER",
				STATUS_SHIP);
		LoadDataHelper.addStatusToExistentProfile(getDriver(), getBaseUrl(), "ADMINISTRATOR", "REPAIR_ORDER",
				STATUS_RO);

		// Criando o Contratante Genérico
		LoadDataHelper.createContractor(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "84412721000107");

		// Criando o Grupo de Serviço e o Serviço Genéricos do Tipo Campo
		LoadDataHelper.createServiceGroup(getDriver(), getBaseUrl(), SERVICE_GROUP_GENERIC);

		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_GENERIC, CONTRACTOR_GENERIC,
				SERVICE_GROUP_GENERIC, WORKFLOW_GENERIC, OPENING_HOURS_GROUP_GENERIC, FLAGS_SERVICE, null);

		// Criando o Grupo de Serviço e o Serviço do Tipo Lab
		LoadDataHelper.createServiceGroup(getDriver(), getBaseUrl(), SERVICE_GROUP_GENERIC_LAB, "LABORATORY");

		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE_GENERIC_LAB, CONTRACTOR_GENERIC,
				SERVICE_GROUP_GENERIC_LAB, WORKFLOW_REPAIR_ORDER_GENERIC, OPENING_HOURS_GROUP_GENERIC);

		// Criando o Cliente Genérico
		LoadDataHelper.createCustomer(getDriver(), getBaseUrl(), CUSTOMER_GENERIC, CUSTOMER_DOCUMENT_NUMBER,
				CONTRACTOR_GENERIC);

		// TODO - Criando Area de Atendimento (MELHORAR ESSE CÓDIGO !)
		LoadDataHelper.createServiceAreas(getDriver(), getBaseUrl(), SERVICE_AREAS_GENERIC);
		// Pegando o Id da Área de Atendimento
		idServiceAreas = getDriver().findElement(By.tagName("body")).getText();

		// Criando o Prestador de Serviço Matriz Genérico
		LoadDataHelper.createServiceProvider(getDriver(), getBaseUrl(), SERVICE_PROVIDER_GENERIC_MATRIZ,
				SERVICE_PROVIDER_GENERIC_MATRIZ, "CNPJ", "57200705000170", SERVICE_PROVIDER_GENERIC_MATRIZ,
				"sp_matriz@email.com", "1197867564", "Avenida Blá", "123", "43654987", "Porto Alegre",
				"Rio Grande do Sul");

		// Adicionando o Contratante e o Grupo de Serviço no Prestador de
		// Serviço Matriz
		LoadDataHelper.createServiceProviderContractorAndServiceGroup(getDriver(), getBaseUrl(),
				SERVICE_PROVIDER_GENERIC_MATRIZ, CONTRACTOR_GENERIC, SERVICE_GROUP_GENERIC, SERVICE_AREAS_GENERIC);

		// Criando o Prestador de Serviço com Técnico Genérico
		LoadDataHelper.createServiceProvider(getDriver(), getBaseUrl(), SERVICE_PROVIDER_GENERIC_TECHNICIAN,
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, "CNPJ", "42137376000192", SERVICE_PROVIDER_GENERIC_TECHNICIAN,
				"sp_tecnico@email.com", "1197867564", "Avenida BleBle", "123", "43554987", "Porto Alegre",
				"Rio Grande do Sul", true);

		// Adicionando o Contratante e o Grupo de Serviço no Prestador de
		// Serviço Técnico
		LoadDataHelper.createServiceProviderContractorAndServiceGroup(getDriver(), getBaseUrl(),
				SERVICE_PROVIDER_GENERIC_TECHNICIAN, CONTRACTOR_GENERIC, SERVICE_GROUP_GENERIC, SERVICE_AREAS_GENERIC);

		// Criando o Prestador de Serviço Filial Genérico
		LoadDataHelper.createServiceProvider(getDriver(), getBaseUrl(), SERVICE_PROVIDER_GENERIC_FILIAL,
				SERVICE_PROVIDER_GENERIC_FILIAL, "CNPJ", "35702758000156", SERVICE_PROVIDER_GENERIC_FILIAL,
				"sp_fillial@email.com", "1186759403", "Avenida BlaBla", "321", "32435654", "Porto Alegre",
				"Rio Grande do Sul", "FIELD", false, false, SERVICE_PROVIDER_GENERIC_MATRIZ);

		// Criando o Prestador de Serviço do Tipo Lab
		LoadDataHelper.createServiceProvider(getDriver(), getBaseUrl(), SERVICE_PROVIDER_GENERIC_LAB,
				SERVICE_PROVIDER_GENERIC_LAB, "CNPJ", "35646835000106", SERVICE_PROVIDER_GENERIC_LAB, "sp_lab@test.com",
				"1186759475", "Avenida Feliz", "543", "64567890", "Porto Alegre", "Rio Grande do Sul", "LABORATORY",
				false, true, "");

		// Criando uma Localização
		LoadDataHelper.createLocation(getDriver(), getBaseUrl(), "Porto Alegre", SERVICE_PROVIDER_GENERIC_MATRIZ);
		LoadDataHelper.createLocation(getDriver(), getBaseUrl(), "Outro_Prestador", SERVICE_PROVIDER_GENERIC_FILIAL);

		// Criando uma Transportadora
		LoadDataHelper.createCarrier(getDriver(), getBaseUrl(), CARRIER_GENERIC, CARRIER_GENERIC);

		// Criando o Fornecedor de Garantia Genérico
		LoadDataHelper.createWarrantyProvider(getDriver(), getBaseUrl(), WARRANTY_PROVIDER_GENERIC, "MANUFACTURER",
				"30");
		LoadDataHelper.createWarrantyProvider(getDriver(), getBaseUrl(), WARRANTY_PROVIDER_GENERIC_SERVICE, "SERVICE",
				"30");

		// Criando o Nível de Reparo
		LoadDataHelper.createRepairLevel(getDriver(), getBaseUrl(), REPAIR_LEVEL_GENERIC);

		// TODO - Pedir ajuda ao Gustavo para terminar o código de tipos de
		// remessa
		// Com uma única permissão
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_ORDER_TYPE_GENERIC_ONE_TYPE_PERMISSION,
				WORKFLOW_SHIP_ORDER_GENERIC, "");

		// Pegando o Id pelo Console
		idShipmentOrderTypeOne = getDriver().findElement(By.tagName("body")).getText();

		// Com mais de uma permissão
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_ORDER_TYPE_GENERIC_SOME_TYPES_PERMISSION,
				WORKFLOW_SHIP_ORDER_GENERIC, "");

		// Pegando o Id pelo Console
		idShipmentOrderTypeSome = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_GENERIC, "123456", ROLES, "pt_BR");
	}

	@Test(dependsOnMethods = { "commonBackgroundGenericScripts" })
	public void criandoAAreaDeAtendimentoGenerica() {

		ServiceAreaShowPage serviceAreaShowPage = new ServiceAreaShowPage(getDriver(), getBaseUrl(), idServiceAreas);

		serviceAreaShowPage.setRegion("Sul");
		serviceAreaShowPage.buttonAdd();
	}

	@Test(dependsOnMethods = { "criandoAAreaDeAtendimentoGenerica" })
	public void criandoTipoDeRemessa() {

		// Acessando os Tipos de Ordem de Remessa com um único tipo
		ShipmentOrderTypeCrudForm shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(),
				idShipmentOrderTypeOne);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingParts");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Acessando os Tipos de Ordem de Remessa com muitos tipos
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipmentOrderTypeSome);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingAccessories");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewAccessories");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingComponents");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewComponents");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingParts");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewParts");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingProducts");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewProducts");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();
	}
}
