package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.AddressBlock;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.customer.CustomerCrudFormPage;
import br.com.workfinity.web.page.login.LoginPage;
import careman.html.TestBase;

public class CustomerTest extends TestBase {

	private MainPage navBar;
	private CustomerCrudFormPage crudForm;
	private AddressBlock address;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
//		super.setUp(baseURL, gridURL);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void login() throws Exception {

		getDriver().get(getBaseUrl());

		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();
	}

	@Test(dependsOnMethods = { "login" })
	public void create() {
		crudForm = navBar.visitCustomer().buttonNew();
		waitAjaxEnd();

		crudForm.setDocument("CPF", "727.836.438-80");
		crudForm.setName("CRUD Customer");
		crudForm.setContractor("CRUD Contractor - New");
		crudForm.setPhoneNumber("1154342345");

		address = new AddressBlock(getDriver());
		address.setAddress("Avenida Paulista");
		address.setZipCode("01311300");
		address.setCity("São Paulo", 6);
		crudForm.buttonCreate();
		crudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		crudForm.setEmail("customerCRUD@email.com");
		crudForm.buttonUpdate();
		
		crudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {
		crudForm.buttonDelete();
		
		crudForm.validateMessageSuccessDeleted();
	}
}
