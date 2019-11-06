package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.defect.DefectCrudFormPage;
import br.com.workfinity.web.page.defect.DefectListPage;
import br.com.workfinity.web.page.defect.DefectShowPage;
import br.com.workfinity.web.page.helper.Messages;
import careman.html.TestBase;

public class DefectTestCRUD extends TestBase {
	
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String SOLUTION_GENERIC = "SOLUTION_GENERIC";
	
	private static final String DEFECT_NAME_UPDATE = "DEFECT_UPDATE - " + randomString(5);
	private static final String DEFECT_NAME_CREATE = "DEFECT_CREATE - " + randomString(5);
	private static final String DEFECT_NAME_2 = "DEFECT_CREATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_DEFECT_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_DEFECT");

	private MainPage navBar;
	private DefectCrudFormPage crudForm;
	private DefectShowPage show;
	private DefectListPage listPage;

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
		
		createDefect(FAMILY_GENERIC, DEFECT_NAME_CREATE);
		createDefect(FAMILY_GENERIC, DEFECT_NAME_2);
	}

	private void createDefect(String familyName, String defectName) {
		
		crudForm = navBar.visitDefect().buttonNew();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setFamily(familyName);
		crudForm.setName(defectName);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		
		listPage = show.buttonBackToSearch();
		listPage.setName(DEFECT_NAME_CREATE);
		listPage.setFamily(FAMILY_GENERIC);
		listPage.buttonSearch();

		crudForm = listPage.editItemTable(1);

		crudForm.setStatus(Messages.ENABLED.toString());
		crudForm.setName(DEFECT_NAME_UPDATE);
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();

		show.setSolutions(SOLUTION_GENERIC);
		show.buttonAdd();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {
		
		listPage = show.buttonBackToSearch();
		listPage.filterExpand();
		listPage.setName("");
		listPage.setFamily(FAMILY_GENERIC);
		listPage.buttonSearch();

		listPage.validateTotalCount("2");
		listPage.filterExpand();
		listPage.setName(DEFECT_NAME_UPDATE);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setName("");
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setName("");
		listPage.setStatus(Messages.ENABLED.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ALL.toString());
		listPage.setFamily(FAMILY_GENERIC);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setFamily(Messages.ALL.toString());
		listPage.buttonSearch();

	}

	@Test(dependsOnMethods = { "search" })
	public void delete() {
		
		deleteDefect(DEFECT_NAME_UPDATE);
		deleteDefect(DEFECT_NAME_2);
	}

	private void deleteDefect(String defectName) {
		
		listPage.filterExpand();
		listPage.setName(defectName);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
