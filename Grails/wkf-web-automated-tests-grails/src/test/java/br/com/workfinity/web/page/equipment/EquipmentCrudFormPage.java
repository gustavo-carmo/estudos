package br.com.workfinity.web.page.equipment;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class EquipmentCrudFormPage extends Page {

	public EquipmentCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("equipment/create", "equipment/edit");
	}

	public EquipmentShowPage buttonCreate() {
		
		clickByXpath("//button[@type='submit']");
//		TODO - quando executa o waitAjaxEnd fala que o JQuery não esta definido verificar o motivo
//		waitAjaxEnd();
		waitOneTimeInPage(5000);
		waitPageLoadEnds();
		return new EquipmentShowPage(driver);
	}

	public EquipmentCrudFormPage setSituation(String situation) {
		
		setSelectByIdAndVisibleText("situation", situation);
		return this;
	}

	public EquipmentCrudFormPage setManufacturer(String manufacturer) {
		
		setSelectByIdAndVisibleText("manufacturer", manufacturer);
		waitAjaxEnd();
		return this;
	}

	public EquipmentCrudFormPage setModel(String model) {
		
		setSelectByIdAndVisibleText("model", model);
		waitAjaxEnd();
		return this;
	}

	public EquipmentCrudFormPage setServiceProvider(String provider) {
		
		setSelectByIdAndVisibleText("service_provider", provider);
		waitAjaxEnd();
		return this;
	}

	public EquipmentCrudFormPage setSerialNumber(String number) {
		
		setFieldById("serialNumber", number);
		return this;
	}

	public EquipmentShowPage buttonUpdateSuccess() {
		
		clickByCssSelector("button[type='submit']");	
//		TODO - quando executa o waitAjaxEnd fala que o JQuery não esta definido verificar o motivo
//		waitAjaxEnd();
		waitOneTimeInPage(5000);
		waitPageLoadEnds();
		return new EquipmentShowPage(driver);
	}

	public EquipmentCrudFormPage setStatus(String status) {
		
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public EquipmentCrudFormPage setContractorExternalId(String id) {
		
		setFieldById("contractorExternalId", id);
		return this;
	}

	public EquipmentCrudFormPage setPO(String po) {
		
		setFieldById("PO", po);
		return this;
	}

	public EquipmentCrudFormPage setSI(String si) {
		
		setFieldById("SI", si);
		return this;
	}

	public String getSerialNumber() {
		
		return getValueOfElement("serialNumber");
	}

	public String getContractorExternal() {
		
		return getTextOfElement("contractorExternalId");
	}

	public EquipmentCrudFormPage buttonUpdateFail() {
		
		clickByCssSelector("button[type='submit']");		
		waitAjaxEnd();
		return this;
	}

	public EquipmentCrudFormPage clickInventoried(boolean validate) {
		
		setCheckBoxById("inventoried", validate);
		return this;
	}

	public String getValue(String idElement) {
		
		return getValueOfElement(idElement);
	}

	public EquipmentCrudFormPage setContractor(String contractor) {
		
		setSelectByIdAndVisibleText("contractor", contractor);
		return this;
	}

	public EquipmentCrudFormPage setType(String type) {
		
		setSelectByIdAndVisibleText("modelType", type);
		return this;
	}

	public EquipmentCrudFormPage setTechnician(String technician) {
		
		setSelectByIdAndVisibleText("user", technician);
		return this;
	}

	public EquipmentCrudFormPage setCustomer(String customer) {

		setFieldAutoCompleteById("autocomplete_customer_id", customer);
		waitAjaxEnd();
		return this;
	}

	public EquipmentCrudFormPage setQuantity(String quantity) {

		setFieldById("quantity", quantity);
		return this;
	}

	public EquipmentCrudFormPage validateErrorMessage(String... error) {
		
		validateMessagesErrors("#status-error .alert.alert-danger", error);
		return this;
	}
}
