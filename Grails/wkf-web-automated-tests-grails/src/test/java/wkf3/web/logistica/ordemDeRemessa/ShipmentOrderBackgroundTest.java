package wkf3.web.logistica.ordemDeRemessa;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderTypeCrudForm;
import careman.html.TestBase;

public class ShipmentOrderBackgroundTest extends TestBase {

	// Família
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";

	// Prestador de serviço para os testes
	private static final String SHIP_SERVICE_PROVIDER = "SHIP_SERVICE_PROVIDER";

	// Status simples para Ordem de Remessa
	private static final String WORKFLOW_SHIP_BASIC = "WORKFLOW_SHIP_BASIC";

	// Os tipos da Ordem de Remessa
	// Parte:
	private static final String SHIP_TYPE_NEW_PART = "SHIP_TYPE_NEW_PART";
	private static final String SHIP_TYPE_EXIST_PART = "SHIP_TYPE_EXIST_PART";

	// Produto:
	private static final String SHIP_TYPE_NEW_PRODUCT = "SHIP_TYPE_NEW_PRODUCT";
	private static final String SHIP_TYPE_EXIST_PRODUCT = "SHIP_TYPE_EXIST_PRODUCT";

	// Acessório:
	private static final String SHIP_TYPE_NEW_ACCESSORY = "SHIP_TYPE_NEW_ACCESSORY";
	private static final String SHIP_TYPE_EXIST_ACCESSORY = "SHIP_TYPE_EXIST_ACCESSORY";

	// Componente:
	private static final String SHIP_TYPE_NEW_COMPONENT = "SHIP_TYPE_NEW_COMPONENT";
	private static final String SHIP_TYPE_EXIST_COMPONENT = "SHIP_TYPE_EXIST_COMPONENT";

	// Insumo
	private static final String SHIP_TYPE_NEW_SUPPLY = "SHIP_TYPE_NEW_SUPPLY";
	private static final String SHIP_TYPE_EXIST_SUPPLY = "SHIP_TYPE_EXIST_SUPPLY";

	// Status do Equipamento
	private static final String EQUIP_CONDITION = "EQUIP_CONDITION";
	private static final String SHIP_TYPE_EQUIP_CONDITION = "SHIP_TYPE_EQUIP_CONDITION";

	// Tipo de Disponibilidade do Equipamento
	private static final String TYPE_EQUIP_AVAILABILITY = "TYPE_EQUIP_AVAILABILITY";
	private static final String SHIP_TYPE_TYPE_EQUIP_AVAILABILITY = "SHIP_TYPE_TYPE_EQUIP_AVAILABILITY";

	// Manter no De
	private static final String SHIP_TYPE_KEEP_EQUIP_FROM = "SHIP_TYPE_KEEP_EQUIP_FROM";

	// Promessa
	private static final String SHIP_TYPE_EQUIP_PROMISSE = "SHIP_TYPE_EQUIP_PROMISSE";

	// Criando o tipo do equipamento
	private static final String SHIP_EQUIP_TYPE = "SHIP_EQUIP_TYPE";

	// Usuário
	private static final String USER_BACK_SHIP = "USER_BACK_SHIP_" + randomString(5);
	private static final List<String> ROLES = Arrays.asList("ROLE_SHIPMENT_ORDER_TYPE");

	private String idShipOrderNewPart;
	private String idShipOrderExistPart;
	private String idShipOrderNewProduct;
	private String idShipOrderExistProduct;
	private String idShipOrderNewAccessory;
	private String idShipOrderExistAccessory;
	private String idShipOrderNewComponent;
	private String idShipOrderExistComponent;
	private String idShipOrderNewSupply;
	private String idShipOrderExistSupply;
	private String idShipOrderEquipAvailability;
	private String idShipOrderEquipCondition;
	private String idShipOrderKeepEquipFrom;
	private String idShipOrderEquipPromisse;

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
	public void backgroundShipmentOrderTypeScripts() {

		// Criando um outro prestador de serviço
		LoadDataHelper.createServiceProvider(getDriver(), getBaseUrl(), SHIP_SERVICE_PROVIDER, SHIP_SERVICE_PROVIDER,
				"CNPJ", "34.465.492/0001-02", SHIP_SERVICE_PROVIDER, "service_ship@email.com", "1187654326",
				"Rua Feliz", "209", "13308000", "Itu", "São Paulo");

		// Status do Equipamento
		LoadDataHelper.createEquipmentCondition(getDriver(), getBaseUrl(), EQUIP_CONDITION, "GOOD", randomNumber(5));

		// Tipo de Ordem de Remessa Status do Equipamento
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_EQUIP_CONDITION,
				WORKFLOW_SHIP_BASIC, "", "", EQUIP_CONDITION, false, false, false, false, "");

		idShipOrderEquipCondition = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Equipamento
		LoadDataHelper.createEquipmentType(getDriver(), getBaseUrl(), SHIP_EQUIP_TYPE, FAMILY_GENERIC, "1237654");

		// Tipo de Disponibilidade do Equipamento
		LoadDataHelper.createTypeOfEquipmentAvailability(getDriver(), getBaseUrl(), TYPE_EQUIP_AVAILABILITY,
				randomNumber(5));

		// Tipo de Ordem de Remessa Tipo de Disponibilidade do Equipamento
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_TYPE_EQUIP_AVAILABILITY,
				WORKFLOW_SHIP_BASIC, "", TYPE_EQUIP_AVAILABILITY, "", false, false, false, false, "");

		idShipOrderEquipAvailability = getDriver().findElement(By.tagName("body")).getText();

		// Manter equipamento no Dê
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_KEEP_EQUIP_FROM,
				WORKFLOW_SHIP_BASIC, "", "", "", true, false, false, false, "");

		idShipOrderKeepEquipFrom = getDriver().findElement(By.tagName("body")).getText();

		// Promessa de Equipamento
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_EQUIP_PROMISSE, WORKFLOW_SHIP_BASIC,
				"", "", "", false, true, true, true, "EQUIPMENT_TYPE");

		idShipOrderEquipPromisse = getDriver().findElement(By.tagName("body")).getText();

		// Tipos de Ordem de Remessa comuns:
		// Tipo de Ordem de Remessa com Nova Parte
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_NEW_PART, WORKFLOW_SHIP_BASIC, "");

		idShipOrderNewPart = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Parte Existente
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_EXIST_PART, WORKFLOW_SHIP_BASIC,
				"");

		idShipOrderExistPart = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Novo Produto
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_NEW_PRODUCT, WORKFLOW_SHIP_BASIC,
				"");

		idShipOrderNewProduct = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Produto Existente
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_EXIST_PRODUCT, WORKFLOW_SHIP_BASIC,
				"");

		idShipOrderExistProduct = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Novo Acessório
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_NEW_ACCESSORY, WORKFLOW_SHIP_BASIC,
				"");

		idShipOrderNewAccessory = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Acessório Existente
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_EXIST_ACCESSORY,
				WORKFLOW_SHIP_BASIC, "");

		idShipOrderExistAccessory = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Novo Componente
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_NEW_COMPONENT, WORKFLOW_SHIP_BASIC,
				"");

		idShipOrderNewComponent = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Componente Existente
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_EXIST_COMPONENT,
				WORKFLOW_SHIP_BASIC, "");

		idShipOrderExistComponent = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Novo Insumo
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_NEW_SUPPLY, WORKFLOW_SHIP_BASIC,
				"");

		idShipOrderNewSupply = getDriver().findElement(By.tagName("body")).getText();

		// Tipo de Ordem de Remessa com Insumo Existente
		LoadDataHelper.createShipmentOrderType(getDriver(), getBaseUrl(), SHIP_TYPE_EXIST_SUPPLY, WORKFLOW_SHIP_BASIC,
				"");

		idShipOrderExistSupply = getDriver().findElement(By.tagName("body")).getText();

		// Usuário
		createUserAndDoLogin(USER_BACK_SHIP, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "backgroundShipmentOrderTypeScripts" })
	public void backgroundAddShipmentOrderType() {

		// Tipo de Ordem de Remessa com Nova Parte
		ShipmentOrderTypeCrudForm shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(),
				idShipOrderNewPart);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewParts");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Parte Existente
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderExistPart);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingParts");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Novo Produto
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderNewProduct);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewProducts");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Produto Existente
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderExistProduct);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingProducts");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Novo Acessório
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderNewAccessory);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewAccessories");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Acessório Existente
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderExistAccessory);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingAccessories");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Novo Componente
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderNewComponent);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewComponents");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Componente Existente
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderExistComponent);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingComponents");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Novo Insumo
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderNewSupply);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowNewSupplies");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa com Insumo Existente
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderExistSupply);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingSupplies");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipos de Ordem de Remessa com particularidades:
		// Tipo de Ordem de Remessa Status do Equipamento
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderEquipCondition);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingAccessories");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingProducts");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa Tipo de Disponilibilidade do Equipamento
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(),
				idShipOrderEquipAvailability);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingAccessories");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingProducts");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa Manter Equipamento no De
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderKeepEquipFrom);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingProducts");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingAccessories");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();

		// Tipo de Ordem de Remessa Promessa de Equipamento
		shipmentOrderTypeCrudForm = new ShipmentOrderTypeCrudForm(getDriver(), getBaseUrl(), idShipOrderEquipPromisse);

		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingProducts");
		shipmentOrderTypeCrudForm.checkedBoxTrue("allowExistingAccessories");
		shipmentOrderTypeCrudForm.buttonCreate();

		shipmentOrderTypeCrudForm.validateMessageSuccessUpdate();
	}
}
