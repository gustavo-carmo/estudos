package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.equipment.EquipmentCrudFormPage;
import br.com.workfinity.web.page.equipment.EquipmentListPage;
import br.com.workfinity.web.page.equipment.EquipmentShowPage;
import br.com.workfinity.web.page.helper.Messages;
import careman.html.TestBase;

public class EquipmentTestCRUD extends TestBase {
	
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";

	private static final String USERNAME = "USERNAME_EQUIPMENT_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_EQUIPMENT", "ROLE_EQUIPMENT_ADMIN");

	private MainPage navBar;
	private EquipmentCrudFormPage crudForm;
	private EquipmentShowPage show;
	private EquipmentListPage listPage;

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
		
		listPage = navBar.visitEquipment(getBaseUrl());
		crudForm = listPage.buttonNew();

		crudForm.setManufacturer(MANUFACTORE_GENERIC);
		crudForm.setModel(MODEL_GENERIC_PRODUCT);
		crudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		crudForm.setSerialNumber(generateLengthNumber(10));
		crudForm.setSituation("Good");
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		
		crudForm = show.buttonEdit();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setServiceProvider("Selecione um …");
		crudForm.buttonUpdateFail();

//		crudForm.validateErrorMessage("Contratante ou Prestador de Serviço ou Usuário é requerido.");
		crudForm.validateErrorMessage("Contractor or Service Provider or an User is required.");
		
		crudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		crudForm.setStatus(Messages.ENABLED.toString());
		show = crudForm.buttonUpdateSuccess();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {
		
		listPage = show.buttonDelete();

		listPage.validateMessageSuccessDeleted();
	}
}
