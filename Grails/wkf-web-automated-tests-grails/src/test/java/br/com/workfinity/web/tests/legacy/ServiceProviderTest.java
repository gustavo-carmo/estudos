package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.AddressBlock;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.serviceProvider.ServiceProviderCrudFormPage;
import br.com.workfinity.web.page.serviceProvider.ServiceProviderShowPage;
import careman.html.TestBase;

public class ServiceProviderTest extends TestBase {

	private MainPage navBar;
	private ServiceProviderCrudFormPage crudForm;
	private AddressBlock address;
	private ServiceProviderShowPage show;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
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
		crudForm = new ServiceProviderCrudFormPage(getDriver());
		crudForm = navBar.visitServiceProvider().buttonNew();

		crudForm.setAlias("CRUD Service Provider - New");
		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setContactName("CRUD Service Provider - New");
		crudForm.setName("CRUD Service Provider - New");
		crudForm.setEmail("crudserviceprovider@new.com");
		crudForm.setPhoneNumber("553155664499");
		crudForm.setDocument("CNPJ", "72.108.727/0001-06");
		address = new AddressBlock(getDriver());
		address.setAddress("Avenida Bláh.");
		address.setNumber("123");
		address.setComplement("123");
		address.setDistrict("CRUD");
		address.setCountry("Brasil", 1);
		address.setState("Minas Gerais", 2);
		address.setCity("Montes Claro", 3);
		address.setZipCode("39400215");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}
	
	@Test(dependsOnMethods = { "create" })
	public void edit(){
		crudForm = show.buttonEdit();

		crudForm.setDocument("CNPJ", "36.803.325/0001-50");/* "895.666.242-80" */
		crudForm.setContactName("CRUD Service Provider - Edit");
		crudForm.setName("CRUD Service Provider - Edit");
		crudForm.setEmail("crudserviceprovider@edit.com");
		address.setZipCode("45689315");
		address.setState("São Paulo", 2);
		address.setCity("São Paulo", 3);
		crudForm.setStatus(Messages.ENABLED.toString());
		crudForm.setType(Messages.LABORATORY.toString());
		show = crudForm.buttonEdit();

		show.validateMessageSuccessUpdate();
	}
}
