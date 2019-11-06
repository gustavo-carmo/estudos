package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ShipmentOrderAddSerializedEquipment extends Page {

	public ShipmentOrderAddSerializedEquipment(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrder/add");
	}

	public ShipmentOrderAddSerializedEquipment setSerialNumber(String serialNumber) {

		waitOneTimeInPage(1500);
		setFieldByCssSelectorAndTapEnter("#serialNumber", serialNumber);
		waitOneTimeInPage(1500);
		return this;
	}

	public ShipmentOrderAddSerializedEquipment setModel(String model) {

		setSelectByIdAndVisibleText("model", model);
		return this;
	}

	public ShipmentOrderAddSerializedEquipment validateMessageError(String... messageError) {

		validateMessagesErrors(".media-body .list-unstyled .beanErrorsItem", messageError);
		waitOneTimeInPage(2000);
		return this;
	}
	
	public ShipmentOrderAddSerializedEquipment validateMessageErrorModal(String... messageError) {
		
		validateMessagesErrors("#shipment_order_new_equipment_form_container .media-body .list-unstyled .beanErrorsItem", messageError);
		waitOneTimeInPage(2000);
		return this;
	}

	public ShipmentOrderAddSerializedEquipment buttonCancelModelModal() {

		clickByCssSelector(".modal-footer .btn.btn-default");
		return this;
	}

	public ShipmentOrderCrudForm backToShipmentOrderCrud() {

		clickByCssSelector(".breadcrumb.text-2x li:nth-child(5) a");
		waitPageLoadEnds();
		
		return new ShipmentOrderCrudForm(driver);
	}

	public ShipmentOrderAddSerializedEquipment setContractor(String contractor) {

		setSelectByIdAndVisibleText("contractor", contractor);
		return this;
	}

	public ShipmentOrderAddSerializedEquipment removeItemInTheRow(int rowNumber) {

		deleteOneRowInTableByPosition(rowNumber, "i.fa-remove");
		waitOneTimeInPage(1500);
		return this;
	}

	public ShipmentOrderAddSerializedEquipment setModalModel(String model) {

		setSelectByCssSelectorAndVisibleText("#shipment_order_new_equipment_form #model", model);
		return this;
	}

	public ShipmentOrderAddSerializedEquipment buttonSaveModal() {

		clickById("equipment_modal_save");
		waitOneTimeInPage(5000);
		return this;
	}

	public String getTotalEquipmentCount() {
		
		return getValueOfElement("total");
	}

	public ShipmentOrderAddSerializedEquipment validateMessageErrorSimple(String... error) {

		validateMessagesErrors(".media-body .alert-title", error);
		waitOneTimeInPage(2000);
		return this;
	}
}
