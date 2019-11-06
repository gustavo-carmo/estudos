package br.com.workfinity.web.page.serviceOrder;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceOrderModalEquipmentFind extends Page {

	public ServiceOrderModalEquipmentFind(WebDriver driver) {
		super(driver);
	}

	public ServiceOrderModalEquipmentCreateAndEdit buttonNew() {
		clickByLinkText("Novo");
		waitAjaxEnd();
		return new ServiceOrderModalEquipmentCreateAndEdit(driver);
	}

	public ServiceOrderModalEquipmentFind setSerialNumber(String serialNumber) {
		setFieldById("equipment_modal_serialNumber", serialNumber);
		pressEnter("equipment_modal_serialNumber");
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderModalEquipmentCreateAndEdit clickSelectAndEdit(int rowNumber) {
		waitAjaxEnd();
		findByLinkText(findTableReturnRowAndColumn(findByCssSelector("div#equipment_modal_search_result_container"),rowNumber + 1, 1), "Selecionar e Editar").click();
		waitAjaxEnd();		
		return new ServiceOrderModalEquipmentCreateAndEdit(driver);
	}

	public ServiceOrderModalEquipmentFind setContractorExternalId(String id) {
		setFieldById("equipment_modal_contractorExternalId", id);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderModalEquipmentFind setModel(String model) {
		setFieldById("equipment_modal_model", model);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderModalEquipmentFind setManufacturer(String manufacturer) {
		setFieldById("equipment_modal_manufacturer", manufacturer);
		waitAjaxEnd();
		return this;
	}

	public int getRowNumber(List<String> textContains) {

		return returnOneRowInTableFindByText(textContains);
	}
}
