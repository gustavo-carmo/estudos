package wkf3.web.gerenciar.ordemDeServico.regrasDeValidacao;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalEquipmentCreateAndEdit;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalEquipmentFind;
import careman.html.TestBase;

public class RegrasDeValidacaoNovoEquipamentoNumeroLogicoObrigatorioTest extends TestBase {

	private static final String USER_VR_NEW_EQUIP_LOGIC_NUMBER_REQUERID = "USER_VR_NEQ_LOGIC_REQ_" + randomString(5);
	private static final String VALIDATION_RULE_LOGIC_NUMBER_NEW_EQUIP_REQUIRED = "VR_LOGIC_NUMB_NEW_EQUIP_REQUIRED";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_EQUIPMENT_ORIGIN", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS", "ROLE_STOCK",
			"ROLE_STOCK_PLANNING_MANAGEMENT", "ROLE_STOCK_PLANNING_VIEW", "ROLE_VIEW_OTHER_STOCK", "ROLE_EQUIPMENT",
			"ROLE_EQUIPMENT_ADMIN");

	private ServiceOrderCrudForm serviceOrderCrudForm;
	private ServiceOrderModalEquipmentFind serviceOrderModalEquipmentFind;
	private ServiceOrderModalEquipmentCreateAndEdit serviceOrderModalEquipmentCreateAndEdit;

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
	public void loadDataRegrasDeValidacaoNovoEquipamentoNumeroLogicoObrigatorio() {

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_NEW_EQUIP_LOGIC_NUMBER_REQUERID, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoNovoEquipamentoNumeroLogicoObrigatorio" })
	public void deveraDarErroQuandoARegraDeValidacaoDeNumeroLogicoDoNovoEquipamentoObrigatorioNaoForAtendida() {

		// Adicionando Equipamento
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchEquipment();
		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Adicionando Novo Equipamento Sem Número Lógico
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchNewEquipment();
		serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_LOGIC_NUMBER_NEW_EQUIP_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm
				.validateErrorMessage("O Número Lógico do Novo Equipamento é requerido! (Validação do Próximo passo)");
	}

	@Test(dependsOnMethods = {
			"deveraDarErroQuandoARegraDeValidacaoDeNumeroLogicoDoNovoEquipamentoObrigatorioNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeNumeroLogicoDoNovoEquipamentoObrigatorioForAtendida() {

		serviceOrderCrudForm.buttonRemovedNewEquipment();

		// Gerando o Número Lógico
		String logicNumberNewEquipment = generateLengthNumber(8);

		// Adicionando Novo Equipamento Com Número Lógico
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchNewEquipment();
		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderModalEquipmentCreateAndEdit.setContractorExternalId(logicNumberNewEquipment);
		serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_LOGIC_NUMBER_NEW_EQUIP_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
}
