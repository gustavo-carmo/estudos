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

public class RegrasDeValidacaoDefeitoInformadoObrigatorioTest extends TestBase {

	private static final String USER_VR_INFORMED_DEFECT_REQUIRED = "USER_VR_INFO_DEFECT_REQ_" + randomString(5);
	private static final String INFORMED_DEFECT_VALIDATION_RULE = "VR_INFORMED_DEFECT_" + randomString(5);
	private static final String VALIDATION_RULE_INFORMED_DEFECT_REQUIRED = "VR_INFORMED_DEFECT_REQUIRED";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER",
			"ROLE_DEFECT", "ROLE_SOLUTION", "ROLE_SERVICE_ORDER_CREATE", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS",
			"ROLE_MANUFACTURER", "ROLE_MODEL");

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
	public void loadDataRegrasDeValidacaoDefeitoInformadoRequerido() {
		
		// Criando o Defeito
		LoadDataHelper.createDefect(getDriver(), getBaseUrl(), INFORMED_DEFECT_VALIDATION_RULE, FAMILY_GENERIC);

		// Criando a Ordem de Serviço
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_INFORMED_DEFECT_REQUIRED, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoDefeitoInformadoRequerido" })
	public void deveraDarErroQuandoARegraDeValidacaoDeDefeitoInformadoObrigatorioNaoForAtendida() {

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_INFORMED_DEFECT_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateErrorMessage("Defeito informado é obrigatório! (Validação do próximo passo)");
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoARegraDeValidacaoDeDefeitoInformadoObrigatorioNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeDefeitoInformadoObrigatorioForAtendida() {

		// Adicionando o Defeito Informado
		serviceOrderCrudForm.setInformedDefect(FAMILY_GENERIC + ": " + INFORMED_DEFECT_VALIDATION_RULE);

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_INFORMED_DEFECT_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
};