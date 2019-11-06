package wkf3.web.gerenciar.ordemDeServico;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudFormCommentModel;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderListPage;
import careman.html.TestBase;

public class OrdemDeServicoComentarios extends TestBase {
	
	private static final String USER_SERVICE_ORDER_COMMENT = "USER_SO_COMMENT_" + randomString(5);
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String SERVICE_GENERIC = "SERVICE_GENERIC";
	private static final String STATUS_GENERIC_NEW = "STATUS_GENERIC_NEW";
	private static final String STATUS_GENERIC_END = "STATUS_GENERIC_END";
	
	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER", "ROLE_SERVICE_ORDER_CREATE",
				"ROLE_SERVICE_ORDER_NOTES_EDIT", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS", "ROLE_SERVICE_ORDER_ADMIN");

	private MainPage navBar;
	private ServiceOrderCrudForm serviceOrderCrud;
	
	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, true);

		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void doLoadData() {

		createUserAndDoLogin(USER_SERVICE_ORDER_COMMENT, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void adicionandoOsComentariosDeUmaOrdemDeServicosAberta() {
		
		ServiceOrderListPage serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderCrud = serviceOrderListPage.buttonNew();
		
		serviceOrderCrud.botaoAdicionaComentarioEstaDesabilitado();

		serviceOrderCrud.setDocument("CNPJ", CUSTOMER_DOCUMENT_NUMBER);
		serviceOrderCrud.setServiceGroup(SERVICE_GROUP_GENERIC);
		serviceOrderCrud.setService(SERVICE_GENERIC);
		serviceOrderCrud.setStatus(STATUS_GENERIC_NEW);
		serviceOrderCrud.buttonCreate();

		serviceOrderCrud.validateMessageSuccessCreated();
		
		serviceOrderCrud.botaoAdicionaComentarioEstaHabilitado();

		ServiceOrderCrudFormCommentModel comment = serviceOrderCrud.buttonAddComment();

		// Gerando um Comentário aleatório
		String comentario = randomString(10);
		
		serviceOrderCrud = comment.setDescription(comentario).buttonSave();
		
		serviceOrderCrud.verificaSeOComentarioFoiCriado(comentario);
	}

	@Test(dependsOnMethods = { "adicionandoOsComentariosDeUmaOrdemDeServicosAberta" })
	public void adicionandoOsComentáriosDeUmaOrdemDeServicosFinalizada() {

		serviceOrderCrud.setStatus(STATUS_GENERIC_END);
		serviceOrderCrud.setClosedDate(getCurrentDate());
		serviceOrderCrud.buttonUpdate();
		
		serviceOrderCrud.validateMessageSuccessUpdate();
		
		serviceOrderCrud.botaoAdicionaComentarioEstaHabilitado();
	}
}
