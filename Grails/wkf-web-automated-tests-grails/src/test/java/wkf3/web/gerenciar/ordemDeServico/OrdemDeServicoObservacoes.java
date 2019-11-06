package wkf3.web.gerenciar.ordemDeServico;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderListPage;
import careman.html.TestBase;

public class OrdemDeServicoObservacoes extends TestBase {

	private static final String USER_WITH_PERMISSION = "USER_PERMISSION_OBS_" + randomString(5);
	private static final String USER_NO_PERMISSION = "USER_NO_PERMISSION_OBS_" + randomString(5);
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";
	private static final String SERVICE_GENERIC = "SERVICE_GENERIC";
	private static final String STATUS_GENERIC_END = "STATUS_GENERIC_END";

	private static final List<String> ROLES_NO_PERMISSION = Arrays.asList("ROLE_SERVICE_ORDER",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS");

	private static final List<String> ROLES_PERMISSION = Arrays.asList("ROLE_SERVICE_ORDER",
			"ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS",
			"ROLE_SERVICE_ORDER_ADMIN");

	private MainPage navBar;
	private String notes = randomString(100);
	private ServiceOrderCrudForm serviceOrderCrud;
	private String idServiceOrderObservation;

	private void loginNew(String user) {
		logout(getDriver());
		new LoginPage(getDriver(), user, "123456").buttonSignInSuccess();
	}

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, false);

		navBar = new MainPage(getDriver());
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
		idServiceOrderObservation = getDriver().findElement(By.tagName("body")).getText();

		LoadDataHelper.createUser(getDriver(), getBaseUrl(), USER_NO_PERMISSION, "123456", ROLES_NO_PERMISSION,
				"pt_BR");

		createUserAndDoLogin(USER_WITH_PERMISSION, "123456", ROLES_PERMISSION);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void visualizandoOCampoObservacaoDeUmaOrdemDeServico() {

		// Usuário com Permissão de Editar Observações
		ServiceOrderListPage serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderCrud = serviceOrderListPage.buttonNew();

		serviceOrderCrud.campoObservacoesEstaHabilitado();

		// Logando com Usuário Sem Permissão de Editar Observações
		loginNew(USER_NO_PERMISSION);

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderCrud = serviceOrderListPage.buttonNew();

		serviceOrderCrud.campoObservacoesEstaDesabilitado();
	}

	@Test(dependsOnMethods = { "visualizandoOCampoObservacaoDeUmaOrdemDeServico" })
	public void editandoOCampoObservacaoDeUmaOrdemDeServicosAberta() {

		// Verificando com Usuário Sem Permissão de Editar Observações
		serviceOrderCrud = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrderObservation);

		serviceOrderCrud.campoObservacoesEstaDesabilitado();

		// Logando com Usuário Com Permissão de Editar Observações
		loginNew(USER_WITH_PERMISSION);

		serviceOrderCrud = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrderObservation);

		serviceOrderCrud.campoObservacoesEstaHabilitado();
	}

	@Test(dependsOnMethods = { "editandoOCampoObservacaoDeUmaOrdemDeServicosAberta" })
	public void editandoOCampoObservacaoDeUmaOrdemDeServicosFinalizada() {

		// Editando as Observações
		serviceOrderCrud.setNotes(notes);
		serviceOrderCrud.setStatus(STATUS_GENERIC_END);
		serviceOrderCrud.setClosedDate(getCurrentDate());
		serviceOrderCrud.buttonUpdate();

		serviceOrderCrud.validateMessageSuccessUpdate();

		serviceOrderCrud.campoObservacoesEstaHabilitado();

		serviceOrderCrud.validaSeOcampoTemOTextoEsperado("notes", notes);
	}
}
