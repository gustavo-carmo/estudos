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

public class RegrasDeValidacaoNovoEquipamentoProibidoTest extends TestBase {

	private static final String USER_VR_NEW_EQUIP_FORBIDDEN = "USER_VR_NEQ_FORB_" + randomString(5);
	private static final String VALIDATION_RULE_NEW_EQUIP_FORBIDDEN = "VR_NEW_EQUIP_FORBIDDEN";
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
	public void loadDataRegrasDeValidacaoNovoEquipamentoProibido() {

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_NEW_EQUIP_FORBIDDEN, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoNovoEquipamentoProibido" })
	public void deveraDarErroQuandoARegraDeValidacaoDeNovoEquipamentoProibidoNaoForAtendida() {

		// Adicionando um Equipamento
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchEquipment();
		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		// Adicionando Novo Equipamento
		serviceOrderModalEquipmentFind = serviceOrderCrudForm.clickSearchNewEquipment();
		serviceOrderModalEquipmentCreateAndEdit = serviceOrderModalEquipmentFind.buttonNew();

		serviceOrderModalEquipmentCreateAndEdit.setManufacturer(MANUFACTORE_GENERIC);
		serviceOrderModalEquipmentCreateAndEdit.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderModalEquipmentCreateAndEdit.buttonSave();

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_NEW_EQUIP_FORBIDDEN);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateErrorMessage("Novo Equipamento é campo proibido! (Validação do próximo passo)");
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoARegraDeValidacaoDeNovoEquipamentoProibidoNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeNovoEquipamentoProibidoForAtendida() {

		// Removendo o Novo Equipamento
		serviceOrderCrudForm.buttonRemovedNewEquipment();

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_NEW_EQUIP_FORBIDDEN);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
}
