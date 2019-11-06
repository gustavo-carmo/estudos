package wkf3.web.gerenciar.ordemDeServico.regrasDeValidacao;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.defect.DefectShowPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import careman.html.TestBase;

public class RegrasDeValidacaoSolucaoObrigatorioTest extends TestBase {

	private static final String USER_VR_SOLUTION_REQUIRED = "USER_VR_SOLUTION_REQ_" + randomString(5);
	private static final String DEFECT_VALIDATION_RULE = "VR_DEFECT_" + randomString(5);
	private static final String SOLUTION_VALIDATION_RULE = "VR_SOLUTION_" + randomString(5);
	private static final String VALIDATION_RULE_SOLUTION_REQUIRED = "VR_SOLUTION_REQUIRED";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_SERVICE_PROVIDER",
			"ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER", "ROLE_DEFECT", "ROLE_SOLUTION",
			"ROLE_SERVICE_ORDER_CONSUMPTION_REPORT", "ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_HISTORY",
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
	public void loadDataRegrasDeValidacaoSolucaoRequerido() {
		
		// Criando o Defeito
		LoadDataHelper.createDefect(getDriver(), getBaseUrl(), DEFECT_VALIDATION_RULE, FAMILY_GENERIC);

		// Pegando o Id do Defeito criado
		String idDefect = getDriver().findElement(By.tagName("body")).getText();
		
		// Criando a Solução
		LoadDataHelper.createSolution(getDriver(), getBaseUrl(), SOLUTION_VALIDATION_RULE, false);

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_SOLUTION_REQUIRED, "123456", ROLES, "pt_BR");
		
		// Adicionando Solução ao Defeito
		DefectShowPage defectShowPage = new DefectShowPage(getDriver(), getBaseUrl(), idDefect);
		
		defectShowPage.setSolutions(SOLUTION_VALIDATION_RULE);
		defectShowPage.buttonAdd();

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoSolucaoRequerido" })
	public void deveraDarErroQuandoARegraDeValidacaoDeSolucaoObrigatorioNaoForAtendida() {

		serviceOrderCrudForm.setDefect(FAMILY_GENERIC + ": " + DEFECT_VALIDATION_RULE);

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_SOLUTION_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateErrorMessage("Solução é campo obrigatório! (Validação do Próximo passo)");
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoARegraDeValidacaoDeSolucaoObrigatorioNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeSolucaoObrigatorioForAtendida() {

		// Adicionando a Solução
		serviceOrderCrudForm.setSolution(SOLUTION_VALIDATION_RULE);

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_SOLUTION_REQUIRED);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
}
