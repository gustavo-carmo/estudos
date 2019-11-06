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

public class RegrasDeValidacaoMotivoDeCancelamentoObrigatorioTest extends TestBase {

	private static final String USER_VR_REASON_CANCELL_REQUIRED = "USER_VR_REASON_CAN_REQ_" + randomString(5);
	private static final String VALIDATION_RULE_REASON_CANCELLATION = "VR_REASON_CANCELLATION_"
			+ randomString(5);
	private static final String VALIDATION_RULE_REASON_FOR_CANCELLATION_REQUIRED = "VR_REASON_CANCEL_REQUIRED";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER",
			"ROLE_REASON_FOR_CANCELLATION", "ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_HISTORY",
			"ROLE_USER_VIEW_ALL_SERVICE_ORDERS");

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
	public void loadDataRegrasDeValidacaoMotivoDeCancelamentoRequerido() {
		
		// Criando o Motivo de Cancelamento
		LoadDataHelper.createReasonForCancellation(getDriver(), getBaseUrl(), VALIDATION_RULE_REASON_CANCELLATION);

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_REASON_CANCELL_REQUIRED, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoMotivoDeCancelamentoRequerido" })
	public void deveraDarErroQuandoARegraDeValidacaoDeMotivoDeCancelamentoObrigatorioNaoForAtendida() {

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_REASON_FOR_CANCELLATION_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm
				.validateErrorMessage("Motivo de Cancelamento é campo obrigatório! (Validação do Próximo Passo)");
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoARegraDeValidacaoDeMotivoDeCancelamentoObrigatorioNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeMotivoDeCancelamentoObrigatorioForAtendida() {

		// Adicionando o Motivo de Cancelamento
		serviceOrderCrudForm.setReasonForCancellation(VALIDATION_RULE_REASON_CANCELLATION);

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_REASON_FOR_CANCELLATION_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
}
