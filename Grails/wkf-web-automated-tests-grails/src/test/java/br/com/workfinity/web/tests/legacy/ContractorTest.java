package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.AddressBlock;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.contractor.ContractorCrudFormPage;
import br.com.workfinity.web.page.contractor.ContractorShowPage;
import br.com.workfinity.web.page.login.LoginPage;
import careman.html.TestBase;

public class ContractorTest extends TestBase {
	private MainPage navBar;
	private ContractorShowPage show;
	private ContractorCrudFormPage crudForm;
	private AddressBlock address;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
//		super.setUp(baseURL, gridURL);
//		navBar = new NavBar(getDriver());
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
		crudForm = navBar.visitContractor().buttonNew();

		crudForm.setContactName("CRUD Contractor - New");
		crudForm.setName("CRUD Contractor - New");
		crudForm.setEmail("crudcontractor@new.com");
		crudForm.setAlias("CRUD Contractor - New");
		crudForm.setDocument("CNPJ", "92.702.067/0001-96");
		crudForm.setPhoneNumber("(11) 4023-4355");

		address = new AddressBlock(getDriver());

		address.setAddress("Avenida Bláh");
		address.setZipCode("13308076");
		address.setNumber("451");
		address.setComplement("");
		address.setDistrict("CRUD");
		address.setCountry("Brasil", 1);
		address.setState("Minas Gerais", 2);
		address.setCity("Montes Claros", 3);

		show = crudForm.buttonCreate();
		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		show.buttonEdit();

		crudForm.setDocument("CPF", "92702067000");
		crudForm.buttonUpdated();

		show.validateMessageSuccessUpdate();
	}
}