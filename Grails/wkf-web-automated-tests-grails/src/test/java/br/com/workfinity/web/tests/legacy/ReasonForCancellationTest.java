package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.reasonForCancellation.ReasonForCancellationCrudFormPage;
import br.com.workfinity.web.page.reasonForCancellation.ReasonForCancellationListPage;
import br.com.workfinity.web.page.reasonForCancellation.ReasonForCancellationShowPage;
import careman.html.TestBase;

public class ReasonForCancellationTest extends TestBase {

	private MainPage navBar;
	private ReasonForCancellationCrudFormPage crudForm;
	private ReasonForCancellationShowPage show;
	private ReasonForCancellationListPage listPage;

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
		crudForm = navBar.visitReasonForCancellation().buttonNew();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setName("CRUD Reason For Cancellation - New");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		crudForm = show.buttonEdit();

		crudForm.setStatus(Messages.ENABLED.toString());
		crudForm.setName("CRUD Reason For Cancellation - Edit");
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {
		listPage = navBar.visitReasonForCancellation();

		listPage.setName("crud reason").buttonSearch();
		listPage.deleteItemTable(1);
	}
}
