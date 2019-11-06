package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.defect.DefectCrudFormPage;
import br.com.workfinity.web.page.defect.DefectListPage;
import br.com.workfinity.web.page.defect.DefectShowPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import careman.html.TestBase;

public class DefectTest extends TestBase {

	private MainPage navBar;
	private DefectCrudFormPage crudForm;
	private DefectShowPage show;
	private DefectListPage listPage;

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
		crudForm = navBar.visitDefect().buttonNew();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setFamily("CRUD Family - Edit");
		crudForm.setName("CRUD Defect - New");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		show.setSolutions("CRUD Solution - Edit");
		show.buttonAdd();
		crudForm = show.buttonEdit();

		crudForm.setStatus(Messages.ENABLED.toString());
		crudForm.setName("CRUD Defect - Edit");
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {
		listPage = show.buttonBackToSearch();
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
