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

public class RegrasDeValidacaoTecnicoDoPrestadorDeServicoObrigatorioTest extends TestBase {

	private static final String USER_VR_TECHNICIAN_SERVICE_PROVIDER_REQUIRED = "USER_VR_TECHNICIAN_SP_REQ_"
			+ randomString(5);
	private static final String TECHNICIAN_SERVICE_PROVIDER_VALIDATION_RULE = "TECHNICIAN_SP_VR_" + randomString(5);
	private static final String VALIDATION_RULE_SERVICE_PROVIDER_TECHNICIAN_REQUIRED = "VR_SP_TECHNICIAN_REQUIRED";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_SERVICE_PROVIDER",
			"ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER", "ROLE_SERVICE_ORDER_CONSUMPTION_REPORT",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_SERVICE_ORDER_HISTORY",
			"ROLE_USER_VIEW_ALL_SERVICE_ORDERS");

	private static final List<String> ROLES_TECHNICIAN = Arrays.asList("ROLE_SERVICE_ORDER_SERVICE_PROVIDER",
			"ROLE_SERVICE_ORDER");

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
	public void loadDataRegrasDeValidacaoTecnicoDoPrestadorDeServicoRequerido() {

		// Criando o Tecnico do Prestador de Serviço
		LoadDataHelper.createTechnician(getDriver(), getBaseUrl(), TECHNICIAN_SERVICE_PROVIDER_VALIDATION_RULE,
				"123456", ROLES_TECHNICIAN, SERVICE_PROVIDER_GENERIC_MATRIZ);

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_TECHNICIAN_SERVICE_PROVIDER_REQUIRED, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoTecnicoDoPrestadorDeServicoRequerido" })
	public void deveraDarErroQuandoARegraDeValidacaoDeTecnicoDoPrestadorDeServicoObrigatorioNaoForAtendida() {

		serviceOrderCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_SERVICE_PROVIDER_TECHNICIAN_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm
				.validateErrorMessage("Técnico do Prestador de Serviço é obrigatório! (Validação do Próximo Passo)");
	}

	@Test(dependsOnMethods = {
			"deveraDarErroQuandoARegraDeValidacaoDeTecnicoDoPrestadorDeServicoObrigatorioNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeTecnicoDoPrestadorDeServicoObrigatorioForAtendida() {

		serviceOrderCrudForm.setServiceProviderTechnician(TECHNICIAN_SERVICE_PROVIDER_VALIDATION_RULE);

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_SERVICE_PROVIDER_TECHNICIAN_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
}
