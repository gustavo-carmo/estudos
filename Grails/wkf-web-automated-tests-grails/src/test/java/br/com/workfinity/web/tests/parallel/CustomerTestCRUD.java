package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.AddressBlock;
import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.customer.CustomerCrudFormPage;
import br.com.workfinity.web.page.customer.CustomerListPage;
import br.com.workfinity.web.page.helper.Messages;
import careman.html.TestBase;

public class CustomerTestCRUD extends TestBase {
	
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";

	private static final String CUSTOMER_ALIAS = "CUSTOMER ALIAS - " + randomString(5);
	private static final String CUSTOMER_ALIAS_2 = "CUSTOMER ALIAS - " + randomString(5);
	private static final String CUSTOMER_NAME = "CUSTOMER - " + randomString(5);
	private static final String CUSTOMER_NAME_2 = "CUSTOMER - " + randomString(5);

	private static final String CONTRACTOR_NAME_2 = "CONTRACTOR_CUSTOMER - " + randomString(5);
	
	private static final String USERNAME = "USERNAME_CUSTOMER_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_CUSTOMER");

	private MainPage navBar;
	private CustomerCrudFormPage crudForm;
	private CustomerListPage listPage;
	private AddressBlock address;

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

		LoadDataHelper.createContractor(getDriver(), getBaseUrl(), CONTRACTOR_NAME_2, "98.729.627/0001-82");
	}
	
	@Test(dependsOnMethods = { "doLoadData" })
	public void login() throws Exception {
		
		createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void criandoUmClienteComSucesso() throws InterruptedException {

		listPage = navBar.visitCustomer();

		createCustomer(CUSTOMER_NAME, CUSTOMER_ALIAS, "11.713.424/0001-10", Messages.ENABLED.toString());

		validaHistoricoDeAlteracaoDeEnderecoDoCliente(Arrays.asList("Avenida Paulista", "01311300", "Rio de Janeiro"));

		listPage = crudForm.buttonClose();

		createCustomer(CUSTOMER_NAME_2, CUSTOMER_ALIAS_2, "82.388.943/0001-06", Messages.DISABLE.toString());
	}

	private void validaHistoricoDeAlteracaoDeEnderecoDoCliente(List<String> valoresASeremValidados) {
		
		crudForm.entraNaAbaDeHistoricoDeAlteracao();
		crudForm.procuraALinhaQueContemOsValoresPassadosNaTabelaDeHistoricoDeAlteracao(valoresASeremValidados);
		crudForm.voltaParaPrimeiraAba();
	}

	private void createCustomer(String customerName, String customerAlias, String cnpj, String status) throws InterruptedException {

		crudForm = listPage.buttonNew();

		crudForm.setDocument("CNPJ", cnpj);
		crudForm.setName(customerName);
		crudForm.setAlias(customerAlias);
		crudForm.setContractor(CONTRACTOR_GENERIC);
		crudForm.setPhoneNumber("1154342345");
		crudForm.setStatus(status);

		address = new AddressBlock(getDriver());
		address.setAddress("Avenida Paulista");
		address.setZipCode("01311300");
		address.setCity("Rio de Janeiro", 6);
		
		waitAjaxEnd();

		crudForm.buttonCreate();
		crudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "criandoUmClienteComSucesso" })
	public void alterandoOEnderecoDeUmClienteComSucesso() {

		crudForm.setEmail("customerCRUD@email.com");
		address = new AddressBlock(getDriver());
		address.setZipCode("01311301");
		address.setAddress("Avenida Liberdade");
		address.setCity("Paraty", 6);
		
		crudForm.buttonUpdate();

		validaHistoricoDeAlteracaoDeEnderecoDoCliente(Arrays.asList("Avenida Paulista", "01311300", "Rio de Janeiro"));
		validaHistoricoDeAlteracaoDeEnderecoDoCliente(Arrays.asList("Avenida Liberdade", "01311301", "Paraty"));
		
		crudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "alterandoOEnderecoDeUmClienteComSucesso" })
	public void search() {
		
		listPage = crudForm.buttonClose();
		listPage.setAlias(CUSTOMER_ALIAS);
		listPage.setContractor(CONTRACTOR_GENERIC);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setAlias("");
		listPage.setDocument("CNPJ", "11.713.424/0001-10");
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setContractor(CONTRACTOR_NAME_2);
		listPage.buttonSearch();

		listPage.validateSearchMessageNoResult();
		listPage.filterExpand();
		listPage.setContractor(CONTRACTOR_GENERIC);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setContractor(Messages.ALL.toString());
		listPage.setDocument(Messages.ALL.toString(), "");
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ALL.toString());
		listPage.setDocument("CPF", "594.897.820-00");
		listPage.buttonSearch();
		listPage.validateSearchMessageNoResult();
	}

	@Test(dependsOnMethods = { "search" })
	public void delete() {
		
		deleteCustomer(CUSTOMER_NAME);
		deleteCustomer(CUSTOMER_NAME_2);

	}

	private void deleteCustomer(String customerName) {
		
		listPage.filterExpand();
		listPage.setDocument(Messages.ALL.toString(), "");
		listPage.setName(customerName);
		listPage.buttonSearch();

		crudForm = listPage.clickLink(customerName);

		crudForm.buttonDelete();

		crudForm.validateMessageSuccessDeleted();
		crudForm.buttonClose();
	}
	
	@Test(dependsOnMethods = { "delete" })
	public void undoLoadData() throws Exception {
		LoadDataHelper.deleteContractor(getDriver(), getBaseUrl(), "98.729.627/0001-82");
	}
}
