package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.recess.RecessCrudFormPage;
import br.com.workfinity.web.page.recess.RecessListPage;
import br.com.workfinity.web.page.recess.RecessShowPage;
import careman.html.TestBase;

public class RecessTestCRUD extends TestBase {

	private static final String RECESS_NAME_UPDATE = "RECESS_CREATE_UPDATE - " + randomString(5);
	private static final String RECESS_NAME_CREATE = "RECESS_CREATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_RECESS_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_RECESS");

	private MainPage navBar;
	private RecessCrudFormPage crudForm;
	private RecessShowPage show;
	private RecessListPage listPage;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, false);

		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void login() throws Exception {
		createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void create() {

		listPage = navBar.visitRecess();
		crudForm = listPage.buttonNew();

		crudForm.setName(RECESS_NAME_CREATE);
		crudForm.setMonth("Maio");
		crudForm.setDay("1");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() throws InterruptedException {

		crudForm = show.buttonEdit();

		crudForm.setRecessType("Municipal");
		crudForm.setCity("Belo Horizonte");
		crudForm.setName(RECESS_NAME_UPDATE);
		crudForm.setMonth("Junho");
		crudForm.setDay("13");
		
		show = crudForm.buttonCreate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() throws InterruptedException {

		listPage = show.buttonBackToSearch();

		listPage.setName(RECESS_NAME_UPDATE);
		listPage.buttonSearch();

		listPage.validateTotalCount("1");
		
		listPage.filterExpand();
		listPage.setName("");
		listPage.setMonth("Maio");
		listPage.buttonSearch();
		// listPage.validateResult("Month", "May");

		listPage.filterExpand();
		listPage.setMonth(Messages.ALL.toString());
		listPage.setCity("Recife");
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setCity("");
		listPage.setState("Rio de Janeiro");

		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setState("");
		listPage.setCountry("Brasil");
		listPage.buttonSearch();
	}

//	TODO  Não esta rodando no servidor. Verificar o motivo de não rodar no servidor
//	@Test(dependsOnMethods = { "search" })
//	public void delete() {
//		
//		listPage.filterExpand();
//		listPage.setCountry("");
//		listPage.setName(RECESS_NAME_UPDATE);
//		listPage.buttonSearch();
//
//		listPage.validateTotalCount("1");
//		listPage.deleteItemTableByPosition(1, 1);
//	}
}
