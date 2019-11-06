package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupAddServiceFormPage;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupCrudFormPage;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupListPage;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupShowPage;
import careman.html.TestBase;

public class ServiceLevelAgreementGroupTest extends TestBase {

	private MainPage navBar;
	private ServiceLevelAgreementGroupCrudFormPage crudForm;
	private ServiceLevelAgreementGroupShowPage show;
	private ServiceLevelAgreementGroupAddServiceFormPage addService;
	private ServiceLevelAgreementGroupListPage listPage;

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
		String name = "CRUD SLA";
		crudForm = navBar.visitServiceLevelAgreementGroup().buttonNew();

		crudForm.setName(name);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
		addService = show.buttonAddServiceLevelAgreementRule();

		show = addService.buttonCreateSucess();
		show.validateMessageSuccessCreated();

		addService = show.buttonAddServiceLevelAgreementRule();
		addService.buttonCreateFail();

		validateMessagesErrors("The group " + name + " already have an SLA for criterion CAPITAL!");
		addService.setCriterion("Country Town");
		show = addService.buttonCreateSucess();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		addService = show.editItemTableServiceLevelAgreement(1);

		addService.setCriterion("Region");
		show = addService.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {
		listPage = show.buttonBackToSearch();

		listPage.buttonSearch();
		
		listPage.deleteItemTable(1);
	}
}
