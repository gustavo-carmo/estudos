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

public class RegrasDeValidacaoIdentificadorExternoDoContratanteObrigatorioTest extends TestBase {

	private static final String USER_VR_CONTRACTOR_EXTERNAL_ID_REQUIRED = "USER_VR_CE_ID_REQ_" + randomString(5);
	private static final String VALIDATION_RULE_EXTERNAL_CONTRACTOR_ID_REQUIRED = "VR_EXTERNAL_CONTRACTOR_ID_REQUIRED";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS");

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
	public void loadDataRegrasDeValidacaoIdentificadorExternoDoContratanteRequerido() {

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_CONTRACTOR_EXTERNAL_ID_REQUIRED, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoIdentificadorExternoDoContratanteRequerido" })
	public void deveraDarErroQuandoARegraDeValidacaoDeIdentificadorExternoDoContratanteObrigatorioNaoForAtendida() {

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_EXTERNAL_CONTRACTOR_ID_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateErrorMessage(
				"Identificador Externo do Contratante é obrigatório ! (Validação do próximo passo)");
	}

	@Test(dependsOnMethods = {
			"deveraDarErroQuandoARegraDeValidacaoDeIdentificadorExternoDoContratanteObrigatorioNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeIdentificadorExternoDoContratanteObrigatorioForAtendida() {

		// Criando o Identificador Externo do Contratante
		String contractorExternalId = generateLengthNumber(8);

		// Adicionando o Identificador Externo do Contratante
		serviceOrderCrudForm.setContractoExternalId(contractorExternalId);

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_EXTERNAL_CONTRACTOR_ID_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
}
