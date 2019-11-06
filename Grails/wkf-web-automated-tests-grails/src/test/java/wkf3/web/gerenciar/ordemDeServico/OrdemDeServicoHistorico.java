package wkf3.web.gerenciar.ordemDeServico;

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

public class OrdemDeServicoHistorico extends TestBase {
	
	private static final String USER_OS_HISTORY = "USER_OS_HISTORY_" + randomString(5);
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";
	private static final String SERVICE_GENERIC = "SERVICE_GENERIC";
	private static final String STATUS_GENERIC_NEW = "STATUS_GENERIC_NEW";
	private static final String STATUS_GENERIC_END = "STATUS_GENERIC_END";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS",
			"ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER_HISTORY");

	private ServiceOrderCrudForm serviceOrderCrud;
	private String idServiceOrderHistory;
	private String historyTotal;

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
	public void doLoadData() {

		// Criando a Ordem de Serviço
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER, SERVICE_GENERIC);

		// Pegando o Id da Ordem de Serviço pelo Console
		idServiceOrderHistory = getDriver().findElement(By.tagName("body")).getText();

		createUserAndDoLogin(USER_OS_HISTORY, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void visualizandoOCampoHistoricoDaOSAposCriacao() {

		// Verificando com Usuário Sem Permissão de Editar Observações
		serviceOrderCrud = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrderHistory);
		
		// Indo para a tab de histórico
		serviceOrderCrud.tabHistory();
		
		historyTotal = serviceOrderCrud.getHistoryTotal();
		
		// Verificando se os valores batem
		serviceOrderCrud.assertEquals("1", historyTotal, "Diferentes!");
		
		// Verificando se registrou no histórico a criação da OS
		serviceOrderCrud.validateIfContainsTheRowInTable(Arrays.asList(
				"Alterado o Status para", STATUS_GENERIC_NEW, "Sistema"), "resultList");
	}
	
	// TODO - DESCOMENTAR ESSE BLOCO QUANDO RESOLVER O PROBLEMA DA DATA DE FECHAMENTO, POIS O CAMPO MOSTRA COMO readyonly
	/*@Test(dependsOnMethods = { "visualizandoOCampoHistoricoDaOSAposCriacao" })
	public void visualizandoOCampoHistoricoDaOSAposEncerrar() {
		
		// Voltando para a aba de detalhes da OS
		serviceOrderCrud.tabOS();
		
		serviceOrderCrud.setStatus(STATUS_GENERIC_END);
		serviceOrderCrud.setClosedDate(getCurrentDate());
		
		serviceOrderCrud.buttonUpdate();
		
		// Indo para a tab de histórico
		serviceOrderCrud.tabHistory();
		
		// Pegando a quantidade total de itens
		historyTotal = serviceOrderCrud.getHistoryTotal();
		
		// Verificando se os valores batem
		serviceOrderCrud.assertEquals("2", historyTotal, "Diferentes!");
		
		// Verificando se registrou a mudança no histórico
		serviceOrderCrud.validateIfContainsTheRowInTable(Arrays.asList(
				USER_OS_HISTORY, "Alterado o Status para", STATUS_GENERIC_END, "Web"), "resultList");
	}*/
}
