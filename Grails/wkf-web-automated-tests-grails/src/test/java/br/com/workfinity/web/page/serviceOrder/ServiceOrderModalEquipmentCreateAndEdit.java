package br.com.workfinity.web.page.serviceOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceOrderModalEquipmentCreateAndEdit extends Page {

	public ServiceOrderModalEquipmentCreateAndEdit(WebDriver driver) {
		super(driver);
	}

	public ServiceOrderModalEquipmentCreateAndEdit setManufacturer(String manufacturer) {
		setSelectByIdAndVisibleText("equipment_modal_form_manufacturer", manufacturer);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderModalEquipmentCreateAndEdit setModel(String model) {
		setSelectByIdAndVisibleText("equipment_modal_form_model", model);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderModalEquipmentCreateAndEdit setContractorExternalId(String value) {
		setFieldById("equipment_modal_contractorExternalId", value);
		return this;
	}

	public ServiceOrderModalEquipmentCreateAndEdit setCustomField(String elementId, String codRede) {
		setFieldById(elementId, codRede);
		return this;
	}

	public ServiceOrderCrudForm buttonSave() {
		waitAjaxEnd();
		clickByLinkText("Salvar");
		waitAjaxEnd(5000);
		return new ServiceOrderCrudForm(driver);
	}

	public ServiceOrderModalEquipmentCreateAndEdit setSerialNumber(String serialNumber) {
		setFieldById("equipment_modal_serial_number", serialNumber);
		return this;
		
	}
}
