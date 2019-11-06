package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.AddressBlock;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.recess.RecessCrudFormPage;
import br.com.workfinity.web.page.recess.RecessListPage;
import br.com.workfinity.web.page.recess.RecessShowPage;
import careman.html.TestBase;

public class RecessTest extends TestBase {
	private MainPage navBar;
	private RecessCrudFormPage crudForm;
	private RecessShowPage show;
	private RecessListPage listPage;

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
		crudForm = navBar.visitRecess().buttonNew();

		crudForm.setName("CRUD Recess - New");
		crudForm.setMonth("May");
		crudForm.setDay("1");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		crudForm = show.buttonEdit();

		crudForm.setName("CRUD Recess - Edit");
		crudForm.setMonth("June");
		crudForm.setDay("13");

		AddressBlock address = new AddressBlock(getDriver());
		address.setCity("Belo Horizont", 3);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {
		listPage = show.buttonBackToSearch();

		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}