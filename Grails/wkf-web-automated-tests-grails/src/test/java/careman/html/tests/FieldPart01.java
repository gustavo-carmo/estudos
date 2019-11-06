package careman.html.tests;

import java.util.Arrays;

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
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalEquipmentCreateAndEdit;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderModalEquipmentFind;
import br.com.workfinity.web.page.stock.StockControlListPage;
import careman.html.TestBase;

public class FieldPart01 extends TestBase {

	public String osinstalacao;
	public String osmanst;
	public String osmanct;
	public String externalidequipamento1;
	public String equipamento1;
	public String equipamento2;
	public String equipamento3;

	private StringBuffer verificationErrors = new StringBuffer();
	private MainPage navBar;
	private EquipmentCrudFormPage equipmentCrudFormPage;
	private EquipmentShowPage equipmentShowPage;
	private ServiceOrderCrudForm serviceOrderCrudForm;
	private ServiceOrderListPage serviceOrderListPage;
	private StockControlListPage stockControlListPage;
	private ServiceOrderModalEquipmentFind equipmentFind;
	private ServiceOrderModalEquipmentCreateAndEdit equipmentCrud;

	private void logoutByURL() {
		logout(getDriver());
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
	public void instalacaoCriacaoDoEquipamento() throws Exception {
		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();
		equipmentShowPage = equipmentCrudFormPage.setSituation("Good").setManufacturer("Ingenico").setModel("ICT220")
				.setServiceProvider("Smart").setSerialNumber(generateLengthNumber(5) + "CT" + generateLengthNumber(8))
				.setContractorExternalId(generateLengthNumber(8)).setPO(generateLengthNumber(7))
				.setSI(generateLengthNumber(7)).buttonCreate();

		equipmentShowPage.validateMessageSuccessCreated();
		equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		equipamento1 = equipmentCrudFormPage.clickInventoried(true).getValue("serialNumber");
		externalidequipamento1 = equipmentCrudFormPage.getValue("contractorExternalId");
		equipmentCrudFormPage.buttonUpdateSuccess();

		equipmentCrudFormPage.validateMessageSuccessUpdate();
	}

	@Test(priority = 3)
	public void instalacaoCriacaodaOS() throws Exception {
		logoutByURL();
		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Instalação")
				.setService("Instalação Venda").setStatus("Nova").buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		osinstalacao = serviceOrderCrudForm.getCode();
		equipmentFind = serviceOrderCrudForm.clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "87374404616")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "925365956835357").buttonSave();

//		serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setServiceProvider("Smart").setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 4)
	public void instalacaoEdicaodaOSEVerificacaoDoEstoque() throws Exception {
		logoutByURL();
		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(osinstalacao).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(equipamento1).buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(equipamento1, "Good", "Smart"));

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(osinstalacao).buttonSearch();

		equipmentFind = serviceOrderCrudForm.clickMergeEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento1).clickSelectAndEdit(1);

		equipmentCrud.buttonSave();
		// TODO conferir se o save foi feito com sucesso
	}

	@Test(priority = 5)
	public void instalacaoVerificacaoDoEstoqueAposCancelamento() throws Exception {
		logoutByURL();
		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();

		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(equipamento1).buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(
				Arrays.asList(equipamento1, "DILCEU JOSE COSTANTIN ME - 91.728.196/0001-90", "Good"));
		logoutByURL();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(osinstalacao).buttonSearch();

		serviceOrderCrudForm.setStatus("Cancelado").setReasonForCancellation("NOK - ENDERECO NAO LOCALIZADO")
				.setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(equipamento1).buttonSearch();
		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList("Smart", "Good", equipamento1));
		logoutByURL();

		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Instalação")
				.setService("Instalação Venda").setStatus("Nova").buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		osinstalacao = serviceOrderCrudForm.getCode();

		equipmentFind = serviceOrderCrudForm.setServiceProvider("Smart").clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "87374404616")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "925365956835357").buttonSave();

//		serviceOrderCrudForm.buttonUpdate();
//		serviceOrderCrudForm.validateMessageSuccessUpdate();

		serviceOrderCrudForm.setStatus("Encaminhado").buttonUpdate();
		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(osinstalacao).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickMergeEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento1).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.buttonSave();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Finalizado").setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();

		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(equipamento1).buttonSearch();
		stockControlListPage.validateIfContainsTheRowInTable(
				Arrays.asList(equipamento1, "Good", "DILCEU JOSE COSTANTIN ME - 91.728.196/0001-90"));
	}

	@Test(priority = 6)
	public void manutencaoReconfiguracaoSemTrocaConsumption() throws Exception {
		logoutByURL();
		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();

		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Manutenção")
				.setService("Manutenção").setStatus("Nova").setContractoExternalId(generateLengthNumber(8))
				.buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		osmanst = serviceOrderCrudForm.getCode();
		equipmentFind = serviceOrderCrudForm.clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "87374404616")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "925365956835357").buttonSave();

//		serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setServiceProvider("Smart").setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();
		serviceOrderListPage.setCodeServiceOrder(osmanst).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickMergeEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento1).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.buttonSave();

//		serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setDefect("POS: Fonte danificada").setSolution("Trocar Fonte").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Finalizado").setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 7)
	public void manutencaoTrocaDeEquipamentoVerificacao() throws Exception {
		logoutByURL();
		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();

		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();

		equipmentShowPage = equipmentCrudFormPage.setSituation("Good").setManufacturer("Ingenico").setModel("ICT220")
				.setServiceProvider("Smart").setSerialNumber(generateLengthNumber(5) + "CT" + generateLengthNumber(8))
				.setContractorExternalId(generateLengthNumber(8)).setPO(generateLengthNumber(7))
				.setSI(generateLengthNumber(7)).buttonCreate();

		equipmentShowPage.validateMessageSuccessCreated();
		equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		equipmentCrudFormPage.clickInventoried(true);
		equipamento2 = equipmentCrudFormPage.getValue("serialNumber");
		equipmentShowPage = equipmentCrudFormPage.buttonUpdateSuccess();

		equipmentShowPage.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Manutenção")
				.setService("Manutenção").setStatus("Nova").setContractoExternalId(generateLengthNumber(8))
				.buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		osmanct = serviceOrderCrudForm.getCode();
		equipmentFind = serviceOrderCrudForm.clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "56321458741")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "765653215458741").buttonSave();

//		serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setServiceProvider("Smart").setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();

		serviceOrderListPage.setCodeServiceOrder(osmanct).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickMergeEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento1).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.buttonSave();

//		serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickSearchEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento2).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.setCustomField("equipment.EQUIPMENT_custom_field_1", "56321458741")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "765653215458741").buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		 serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setDefect("POS: Terminal Bloqueado").setSolution("TROCA DE TERMINAL").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
//		 logoutByURL();

//		 new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

//		 serviceOrderListPage = navBar.visitServiceOrder();

//		 serviceOrderListPage.setCodeServiceOrder(osmanct).buttonSearch();

		serviceOrderCrudForm.setStatus("Cancelado").setReasonForCancellation("NOK - EQUIPAMENTO NAO ESTA NO LOCAL")
				.setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// TODO - Adicionando o pedaço de código que válida se o equipamento
		// voltou pro prestador após cancelar
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(equipamento2).buttonSearch();
		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList("Smart", "Good", equipamento2));

		logoutByURL();

		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Manutenção")
				.setService("Manutenção").setStatus("Nova").setContractoExternalId(generateLengthNumber(8))
				.buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		osmanct = serviceOrderCrudForm.getCode();
		equipmentFind = serviceOrderCrudForm.clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "56321458741")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "765653215458741").buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setServiceProvider("Smart").setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();

		serviceOrderListPage.setCodeServiceOrder(osmanct).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickMergeEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento1).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentCrud = serviceOrderCrudForm.clickSearchEquipment().setSerialNumber(equipamento2).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.setCustomField("equipment.EQUIPMENT_custom_field_1", "56321458741")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "765653215458741").buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setDefect("POS: Terminal Bloqueado").setSolution("TROCA DE TERMINAL").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Finalizado").setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(priority = 8)
	public void trocaDeTecnologiaMergeEquipment() throws Exception {
		logoutByURL();
		new LoginPage(getDriver(), "sidneyaraujo", "123456").buttonSignInSuccess();

		equipmentCrudFormPage = navBar.visitEquipment(getBaseUrl()).buttonNew();
		equipmentShowPage = equipmentCrudFormPage.setSituation("Good").setManufacturer("Ingenico").setModel("ICT220")
				.setServiceProvider("Smart").setSerialNumber(generateLengthNumber(5) + "CT" + generateLengthNumber(8))
				.setContractorExternalId(generateLengthNumber(8)).setPO(generateLengthNumber(7))
				.setSI(generateLengthNumber(7)).buttonCreate();

		equipmentShowPage.validateMessageSuccessCreated();
		equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		equipamento3 = equipmentCrudFormPage.clickInventoried(true).getValue("serialNumber");
		equipmentCrudFormPage.buttonUpdateSuccess();

		equipmentCrudFormPage.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Troca de Tecnologia")
				.setService("Troca de Tecnologia (geral)").setStatus("Nova");

		serviceOrderCrudForm.buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		String ostroca = serviceOrderCrudForm.getCode();

		equipmentFind = serviceOrderCrudForm.setServiceProvider("Smart").clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "73900264276")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "397449022267740").buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();
		serviceOrderListPage = navBar.visitServiceOrder();

		serviceOrderListPage.setCodeServiceOrder(ostroca).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickMergeEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento2).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickSearchEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento3).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.setCustomField("equipment.EQUIPMENT_custom_field_1", "92366177938")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "187933152830225").buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		 serviceOrderCrudForm.validateMessageSuccessUpdate();
//		 logoutByURL();

//		 new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

//		 serviceOrderListPage = navBar.visitServiceOrder();
//		 serviceOrderListPage.setCodeServiceOrder(ostroca).buttonSearch();

		serviceOrderCrudForm.setStatus("Cancelado (Pendente Autorização)")
				.setReasonForCancellation("NOK - INSCRICAO ESTADUAL INVALIDA").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Cancelado").setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// TODO - Adicionando o pedaço de código que válida se o equipamento
		// voltou pro prestador após cancelar
		stockControlListPage = navBar.visitStockControl();
		stockControlListPage.setSerialNumber(equipamento3).buttonSearch();
		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList("Smart", "Good", equipamento3));

		logoutByURL();

		new LoginPage(getDriver(), "atendente", "123456").buttonSignInSuccess();

		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();

		serviceOrderCrudForm.setDocument("CNPJ", "91.728.196/0001-90").setServiceGroup("Troca de Tecnologia")
				.setService("Troca de Tecnologia (geral)").setStatus("Nova").buttonCreate();

		serviceOrderCrudForm.validateMessageSuccessCreated();
		ostroca = serviceOrderCrudForm.getCode();

		equipmentFind = serviceOrderCrudForm.setServiceProvider("Smart").clickSearchEquipment();

		equipmentCrud = equipmentFind.buttonNew();

		serviceOrderCrudForm = equipmentCrud.setManufacturer("Ingenico").setModel("ICT220")
				.setContractorExternalId(generateLengthNumber(8))
				.setCustomField("equipment.EQUIPMENT_custom_field_1", "73900264276")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "397449022267740").buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Encaminhado").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		logoutByURL();

		new LoginPage(getDriver(), "sps_smart", "123456").buttonSignInSuccess();

		serviceOrderListPage = navBar.visitServiceOrder();

		serviceOrderListPage.setCodeServiceOrder(ostroca).buttonSearch();

		serviceOrderCrudForm.setStatus("Em Campo").buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickMergeEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento2).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		equipmentFind = serviceOrderCrudForm.clickSearchEquipment();

		equipmentCrud = equipmentFind.setSerialNumber(equipamento3).clickSelectAndEdit(1);

		serviceOrderCrudForm = equipmentCrud.setCustomField("equipment.EQUIPMENT_custom_field_1", "92366177938")
				.setCustomField("equipment.EQUIPMENT_custom_field_2", "187933152830225").buttonSave();

//		 serviceOrderCrudForm.buttonUpdate();

//		serviceOrderCrudForm.validateMessageSuccessUpdate();
		serviceOrderCrudForm.setStatus("Finalizado").setClosedDate(getCurrentDate()).buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}

}
