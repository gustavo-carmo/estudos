package wkf3.web.gerenciar.ordemDeServico.trigger;

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

public class TriggerDefineDataDeEncaminhamentoTest extends TestBase {

	private static final String USER_TRIGGER_ROUTING_DATE = "USER_TR_ROUTING_DATE_" + randomString(5);
	private static final String TRIGGER_ROUTING_DATE = "TRIGGER_ROUTING_DATE";
	private static final String SERVICE_COMMON_TRIGGER = "SERVICE_COMMON_TRIGGER";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER",
			"ROLE_SERVICE_ORDER_CONSUMPTION_REPORT", "ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_NOTES_EDIT",
			"ROLE_SERVICE_ORDER_HISTORY", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS");

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
	public void loadDataTriggerDefineDataDeEncaminhamento() {

		// Criando a Ordem de Serviço no Console
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_COMMON_TRIGGER);

		// Pegando o Id da Ordem de Serviço no Console
		String idServiceOrder = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_TRIGGER_ROUTING_DATE, "123456", ROLES, "pt_BR");

		// Acessando a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrder);
	}

	@Test(dependsOnMethods = { "loadDataTriggerDefineDataDeEncaminhamento" })
	public void deveraAcionarOEventoDaTriggerQuandoATriggerDeDefineDatadeEncaminhamentoEstaHabilitada() {

		serviceOrderCrudForm.setStatus(TRIGGER_ROUTING_DATE);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		serviceOrderCrudForm.tabHistory();
		serviceOrderCrudForm
				.validateIfContainsTheRowInTable(Arrays.asList("Trigger disparado", "Define Data de Encaminhamento"));
	}
}