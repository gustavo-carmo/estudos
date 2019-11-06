package br.com.workfinity.web.tests.legacy;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.equipment.EquipmentCrudFormPage;
import br.com.workfinity.web.page.equipment.EquipmentShowPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import careman.html.TestBase;

public class EquipmentTest extends TestBase {

	private EquipmentCrudFormPage crudForm;
	private MainPage navBar;
	private EquipmentShowPage show;

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
		crudForm = navBar.visitEquipment(getBaseUrl()).buttonNew();

		crudForm.setSituation("Good");
		crudForm.setManufacturer("CRUD Manufacturer - Edit");
		crudForm.setModel("CRUD Product");
		crudForm.setServiceProvider("CRUD Service Provider - New");
		crudForm.setSerialNumber(generateLengthNumber(10));
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {
		crudForm = show.buttonEdit();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setServiceProvider("Select one...");
		crudForm.buttonUpdateFail();

		validateMessagesErrors("Contractor or Service Provider or an User is required.");
		crudForm.setServiceProvider("CRUD Service Provider - New");
		crudForm.setStatus(Messages.ENABLED.toString());
		show = crudForm.buttonUpdateSuccess();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {
		show.buttonDelete();
		
		show.validateMessageSuccessDeleted();
	}
}
