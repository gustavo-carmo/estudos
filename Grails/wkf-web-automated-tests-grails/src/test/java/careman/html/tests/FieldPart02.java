package careman.html.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.equipment.EquipmentCrudFormPage;
import br.com.workfinity.web.page.equipment.EquipmentShowPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderListPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalChangeService;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalEquipmentCreateAndEdit;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalEquipmentFind;
import careman.html.TestBase;

public class FieldPart02 extends TestBase {

	private StringBuffer verificationErrors = new StringBuffer();
	public String oschangingservice;
	public String codigocontractor;
	private MainPage navBar;
	private ServiceOrderCrudForm serviceOrderCrudForm;
	private ServiceOrderModalEquipmentFind equipmentFind;
	private ServiceOrderModalEquipmentCreateAndEdit equipmentCrud;
	private EquipmentCrudFormPage equipmentCrudFormPage;
	private EquipmentShowPage equipmentShowPage;
	private ServiceOrderListPage serviceOrderListPage;

	private void logoutNoAlert() {
		navBar.logout();
	}
	
	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	@Test(priority = 1)
	public void doLogin() throws Exception {
		getDriver().get(getBaseUrl());
		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();
	}

	@Test(priority = 2)
	public void ChangingService() throws Exception {
		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Troca de Tecnologia")
				.setService("Troca de Tecnologia (geral)").setStatus("Nova").buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		oschangingservice = serviceOrderCrudForm.getCode();
		equipmentFind = serviceOrderCrudForm.setServiceProvider("Smart").clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "53900264276")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "787449022267740").buttonSave();

		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutNoAlert();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(oschangingservice.trim()).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutNoAlert();

		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(oschangingservice).buttonSearch();

		ServiceOrderModalChangeService serviceOrderModalChangeService = serviceOrderCrudForm.clickChangeService();

		serviceOrderCrudForm = serviceOrderModalChangeService.setService("Manutenção").setStatus("Nova").buttonSave();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(oschangingservice).buttonSearch();

		serviceOrderCrudForm.setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.setStatus("Encaminhado").setContractoExternalId("213543251151").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.setStatus("Cancelado").setReasonForCancellation("NOK - EQUIPAMENTO NAO ESTA NO LOCAL")
				.setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 3)
	public void EditingOSAtEndStatus() throws Exception {
		logoutNoAlert();
		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();

		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();
		equipmentShowPage = equipmentCrudFormPage.setSituation("Good").setManufacturer("Ingenico").setModel("ICT220")
				.setServiceProvider("Smart").setSerialNumber(generateLengthNumber(5) + "CT" + generateLengthNumber(8))
				.setContractorExternalId(generateLengthNumber(8)).setPO(generateLengthNumber(7)).setSI(generateLengthNumber(7))
				.buttonCreate();

		equipmentShowPage.validateMessageSuccessCreated();
		equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		equipmentCrudFormPage.clickInventoried(true);
		String equiptestedit = equipmentCrudFormPage.getSerialNumber();
		equipmentShowPage = equipmentCrudFormPage.buttonUpdateSuccess();

		equipmentShowPage.validateMessageSuccessUpdate();
		logoutNoAlert();

		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Instalação")
				.setService("Instalação Venda").setStatus("Nova").buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		String osendstatus = serviceOrderCrudForm.getCode();

		equipmentFind = serviceOrderCrudForm.setServiceProvider("Smart").clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "58374404616")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "861365956835357").buttonSave();

		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setServiceProvider("Smart").setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutNoAlert();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(osendstatus).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickMergeEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equiptestedit).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.buttonSave();

		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Finalizado").setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.validateElementDisable("customerDocumentNumber").validateElementDisable("status.id")
				.validateElementDisable("notes").validateElementDisable("reasonForCancellation").validateElementDisable("group")
				.validateElementDisable("defect").validateElementDisable("solution").validateElementDisable("serviceProvider")
				.validateElementDisable("serviceProviderTechnician").validateElementDisable("stockEntryForkedEquipment_model")
				.validateElementDisable("stockEntryForkedNewEquipment_model").validateElementDisable("openingDate")
				.validateElementDisable("closedDate").validateElementDisable("deadLineServiceProvider")
				.validateElementDisable("registeredClosedDate");
	}

	@Test(priority = 4)
	public void serviceOrderContractor() throws Exception {
		logoutNoAlert();
		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Instalação")
				.setService("Instalação Venda").setStatus("Nova").buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		codigocontractor = serviceOrderCrudForm.getCode();
		equipmentFind = serviceOrderCrudForm.clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", generateLengthNumber(11))
				.setCustomField("equipment.EQUIPMENT_custom_field_2", generateLengthNumber(15)).buttonSave();

		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setServiceProvider("Smart").setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutNoAlert();

		new LoginPage(getDriver(), "contratante", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(codigocontractor).buttonSearch();

		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver());
		serviceOrderCrudForm.validateElementDisable("notes")
				.validateElementDisable("closedDate");
	}
}
