package wkf3.web.gerenciar.ordemDeServico;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderListPage;
import careman.html.TestBase;

public class OrdemDeServicoInformandoModelo extends TestBase {

	private static final String USER_SERVICE_ORDER_MODEL = "USER_SO_MODEL_" + randomString(5);
	private static final String SERVICE = "SERVICE_" + randomString(5);
	
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String WORKFLOW_GENERIC = "WORKFLOW_GENERIC";
	private static final String STATUS_GENERIC_NEW = "STATUS_GENERIC_NEW";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER", "ROLE_SERVICE_ORDER_CREATE",
			"ROLE_USER_VIEW_ALL_SERVICE_ORDERS");

	private MainPage navBar;

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

		// Criando o Serviço Específico para esse Teste
		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE, CONTRACTOR_GENERIC,
				SERVICE_GROUP_GENERIC, WORKFLOW_GENERIC, OPENING_HOURS_GROUP_GENERIC,
				Arrays.asList("service.allowsEquipmentWithOnlyModelOnServiceOrder = true"),
				Arrays.asList(MODEL_GENERIC_PRODUCT));

		createUserAndDoLogin(USER_SERVICE_ORDER_MODEL, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void criandoUmaOrdemDeServicoInformandoOModeloDoEquipamento() {

		ServiceOrderListPage serviceOrderListPage = navBar.visitServiceOrder();
		ServiceOrderCrudForm serviceOrderCrud = serviceOrderListPage.buttonNew();

		serviceOrderCrud.setDocument("CNPJ", CUSTOMER_DOCUMENT_NUMBER);
		serviceOrderCrud.setServiceGroup(SERVICE_GROUP_GENERIC);
		serviceOrderCrud.setService(SERVICE);
		serviceOrderCrud.setModel(MODEL_GENERIC_PRODUCT);
		serviceOrderCrud.setStatus(STATUS_GENERIC_NEW);
		serviceOrderCrud.buttonCreate();
		
		serviceOrderCrud.validateMessageSuccessCreated();
		
		serviceOrderCrud.validateEquipment(MODEL_GENERIC_PRODUCT);
	}
}