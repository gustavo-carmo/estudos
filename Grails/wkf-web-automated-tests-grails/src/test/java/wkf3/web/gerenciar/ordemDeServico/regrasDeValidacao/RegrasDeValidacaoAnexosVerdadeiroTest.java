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
import br.com.workfinity.web.page.serviceOrder.ServiceOrderTabAttachmentsUpload;
import careman.html.TestBase;

public class RegrasDeValidacaoAnexosVerdadeiroTest extends TestBase {

	private static final String USER_VR_ATTACHMENT_TRUE = "USER_VR_ATTACHMENT_TRUE_" + randomString(5);
	private static final String VALIDATION_RULE_ATTACHMENT_TRUE = "VR_ATTACHMENT_TRUE";
	private static final String SERVICE_VALIDATION_RULE = "SERVICE_VALIDATION_RULE";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER",
			"ROLE_ATTACHMENT", "ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_HISTORY",
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
	public void loadDataRegrasDeValidacaoAnexosVerdadeiro() {

		// Criando a Ordem de Serviço
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_VALIDATION_RULE);

		// Pegando o Id da Ordem de Serviço
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_VR_ATTACHMENT_TRUE, "123456", ROLES, "pt_BR");

		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataRegrasDeValidacaoAnexosVerdadeiro" })
	public void deveraDarErroQuandoARegraDeValidacaoDeAnexosVerdadeiroNaoForAtendida() {

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_ATTACHMENT_TRUE);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateErrorMessage("Por favor anexe pelo menos um arquivo");
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoARegraDeValidacaoDeAnexosVerdadeiroNaoForAtendida" })
	public void deveraMudarComSucessoOStatusDeUmaOrdemDeServicoQuandoARegraDeValidacaoDeAnexosVerdadeiroForAtendida() {

		// Anexando um Arquivo
		serviceOrderCrudForm.tabAttachment();
		ServiceOrderTabAttachmentsUpload serviceOrderTabAttachmentsUpload = serviceOrderCrudForm
				.buttonAttachmentUpload();

		serviceOrderTabAttachmentsUpload
				.setFile(getCurrentDirectory() + "/src/test/resources/regra-validacao-anexo-verdadeiro.png");
		serviceOrderCrudForm = serviceOrderTabAttachmentsUpload.buttonUploadFile();

		serviceOrderCrudForm.validateMessageSuccess("Arquivo anexado");

		serviceOrderCrudForm.setStatus(VALIDATION_RULE_ATTACHMENT_TRUE);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}
}
