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
import careman.html.TestBase;

public class RegrasDeValidacaoNumeroDeRastreamentoObrigatorioTest extends TestBase {

	private static final String USER_VR_TRACKING_NUMBER = "USER_VR_TRACKING_NUMBER_" + randomString(5);
	private static final String VALIDATION_RULE_TRACKING_NUMBER_REQUIRED = "VR_TRACKING_NUMBER_REQUIRED";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_EQUIPMENT_ORIGIN", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS", "ROLE_STOCK",
			"ROLE_STOCK_PLANNING_MANAGEMENT", "ROLE_STOCK_PLANNING_VIEW", "ROLE_VIEW_OTHER_STOCK", "ROLE_EQUIPMENT",
			"ROLE_EQUIPMENT_ADMIN");

	private ServiceOrderCrudForm serviceOrderCrudForm;

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
	public void loadDataRegrasDeValidacaoNumeroDeRastreamentoObrigatorio() {

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_TRACKING_NUMBER, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoNumeroDeRastreamentoObrigatorio" })
	public void deveraDarErroQuandoARegraDeValidacaoDeNumeroDeRastreamentoObrigatorioNaoForAtendida() {

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_TRACKING_NUMBER_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateErrorMessage("Número de Rastreamento é obrigatório! (Validação do próximo passo)");
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoARegraDeValidacaoDeNumeroDeRastreamentoObrigatorioNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeNumeroDeRastreamentoObrigatorioForAtendida() {

		// Gerando um Número de Rastreamento
		String trackingNumber = generateLengthNumber(5);

		serviceOrderCrudForm.setTrackingNumber(trackingNumber);

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_TRACKING_NUMBER_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
}
