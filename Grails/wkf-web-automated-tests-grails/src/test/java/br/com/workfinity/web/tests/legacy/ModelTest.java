package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.model.ModelCrudFormPage;
import br.com.workfinity.web.page.model.ModelShowPage;
import careman.html.TestBase;

public class ModelTest extends TestBase {

	private MainPage navBar;
	private ModelCrudFormPage crudForm;
	private ModelShowPage show;

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
		crudForm = navBar.visitModel().buttonNew();

		crudForm.setName("CRUD Part");
		crudForm.setType("Part");
		show = crudForm.buttonCreateSuccess();
		
		show.validateMessageSuccessCreated();

	}
	@Test(dependsOnMethods = { "create" })
	public void edit() {
		crudForm = show.buttonEdit();

		crudForm.setName("CRUD Product");
		crudForm.setManufacturer("CRUD Manufacturer - Edit");
		crudForm.setType(Messages.TYPE_PRODUCT.toString());
		crudForm.setDescription("teste de descrição");
		show = crudForm.buttonUpdate();
		
		show.validateMessageSuccessUpdate();
	}
}
