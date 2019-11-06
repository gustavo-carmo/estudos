package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.solution.SolutionCrudFormPage;
import br.com.workfinity.web.page.solution.SolutionShowPage;
import careman.html.TestBase;

public class SolutionTest extends TestBase {

	private MainPage navBar;
	private SolutionCrudFormPage crudForm;
	private SolutionShowPage show;

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
		crudForm = navBar.visitSolution().buttonNew();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setName("CRUD Solution - New");
		crudForm.checkBoxRCE(true);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		crudForm = show.buttonEdit();

		crudForm.setName("CRUD Solution - Edit");
		crudForm.setStatus(Messages.ENABLED.toString());
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}
}
