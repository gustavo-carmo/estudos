package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.status.StatusCrudFormPage;
import br.com.workfinity.web.page.status.StatusListPage;
import br.com.workfinity.web.page.status.StatusShowPage;
import careman.html.TestBase;

public class StatusTest extends TestBase {

	private MainPage navBar;
	private StatusCrudFormPage crudForm;
	private StatusShowPage show;
	private StatusListPage listPage;

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
		listPage = navBar.visitStatus();
		crudForm = listPage.buttonNew();

		crudForm.setName("Status Nova Crud");
		crudForm.setWorkFlow(Messages.SERVICE_ORDER.toString());
		crudForm.setType(Messages.START.toString());
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
		listPage = show.buttonBackToSearch();

		crudForm = listPage.buttonNew();

		crudForm.setName("Status Finalizado CRUD");
		crudForm.setWorkFlow(Messages.SERVICE_ORDER.toString());
		crudForm.setType("End with Success");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
		listPage = show.buttonBackToSearch();

		crudForm = listPage.buttonNew();

		crudForm.setName("Status Encaminhado CRUD");
		crudForm.setType(Messages.END_WITH_FAIL.toString());
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		crudForm = show.buttonEdit();

		crudForm.setType("");
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}
}
