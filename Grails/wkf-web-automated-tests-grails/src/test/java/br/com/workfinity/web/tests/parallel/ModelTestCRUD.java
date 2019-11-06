
package br.com.workfinity.web.tests.parallel;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.model.ModelCrudFormPage;
import br.com.workfinity.web.page.model.ModelListPage;
import br.com.workfinity.web.page.model.ModelShowPage;
import careman.html.TestBase;

public class ModelTestCRUD extends TestBase {
	
	private static final String FAMILY_GENERIC = "FAMILY_GENERIC";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";

	private static final String MODEL_NAME_UPDATE = "MODEL_CREATE_UPDATE - " + randomString(5);
	private static final String MODEL_NAME_CREATE = "MODEL_CREATE - " + randomString(5);
	private static final String MODEL_NAME_2 = "MODEL_CREATE - " + randomString(5);;

	private static final String USERNAME = "USERNAME_MODEL_" + randomString(5);
	private static final String PASSWORD = "123456";
	
	@SuppressWarnings("serial")
	private static final List<String> ROLES = new ArrayList<String>() {
		{
			add("ROLE_MODEL");
		}
	};

	private MainPage navBar;
	private ModelCrudFormPage crudForm;
	private ModelShowPage show;
	private ModelListPage listPage;

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
		
		createModel(MODEL_NAME_CREATE, Messages.ENABLED.toString());
		createModel(MODEL_NAME_2, Messages.DISABLE.toString());
	}

	private void createModel(String modelName, String status) {
		
		crudForm = navBar.visitModel().buttonNew();

		crudForm.buttonCreateFail();

		validateMessagesErrors("Campo \"Nome\" é requerido");
		
		crudForm.setName(modelName);
		crudForm.setStatus(status);
		crudForm.setType("Parte");
		crudForm.setFamily(FAMILY_GENERIC);
		crudForm.setManufacturer(MANUFACTORE_GENERIC);
		show = crudForm.buttonCreateSuccess();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		
		listPage = show.buttonBackToSearch();
		listPage.setName(MODEL_NAME_CREATE);
		listPage.buttonSearch();

		listPage.editItemTable(1);

		crudForm.setName(MODEL_NAME_UPDATE);
		crudForm.setType("Produto");
		crudForm.setDescription("teste de descrição");
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {
		
		listPage = show.buttonBackToSearch();
		listPage.filterExpand();
		listPage.setName("MODEL_CREATE");
		listPage.buttonSearch();

		listPage.validateTotalCount("2");
		listPage.filterExpand();
		listPage.setName(MODEL_NAME_UPDATE);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setName("");
		listPage.setStatus(Messages.ENABLED.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ENABLED.toString());
		listPage.setManufacturer(MANUFACTORE_GENERIC);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setName(MODEL_NAME_UPDATE);
		listPage.setManufacturer(Messages.ALL.toString());
		listPage.setStatus(Messages.ALL.toString());
		listPage.buttonSearch();
	}

	@Test(dependsOnMethods = { "search" })
	public void delete() {
		
		deleteModel(MODEL_NAME_UPDATE);
		deleteModel(MODEL_NAME_2);
	}

	private void deleteModel(String modelName) {
		
		listPage.filterExpand();
		listPage.setName(modelName);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
