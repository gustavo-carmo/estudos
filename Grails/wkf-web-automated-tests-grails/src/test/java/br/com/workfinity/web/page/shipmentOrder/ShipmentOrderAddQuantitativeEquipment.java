package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ShipmentOrderAddQuantitativeEquipment extends Page {

	public ShipmentOrderAddQuantitativeEquipment(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrder/addComponents");
	}

	public ShipmentOrderAddQuantitativeEquipment setModel(String model) {

		setSelectByIdAndVisibleText("model", model);
		return this;
	}

	public ShipmentOrderAddQuantitativeEquipment setSituation(String situation) {

		setSelectByIdAndVisibleText("situation2", situation);
		return this;
	}

	public ShipmentOrderAddQuantitativeEquipment setQuantity(String quantity) {

		setFieldById("quantity", quantity);
		return this;
	}

	public ShipmentOrderAddQuantitativeEquipment buttonAddComponents() {

		clickById("add_component");
		waitOneTimeInPage(2000);
		return this;
	}

	public ShipmentOrderAddQuantitativeEquipment validateMessageError(String... messageError) {

		validateMessagesErrors(".media-body .list-unstyled .beanErrorsItem", messageError);
		waitOneTimeInPage(2000);
		return this;
	}

	public ShipmentOrderAddQuantitativeEquipment setContractor(String contractor) {

		setSelectByIdAndVisibleText("contractor", contractor);
		return this;
	}
	
	public ShipmentOrderCrudForm backToShipmentOrderCrud() {

		clickByCssSelector(".breadcrumb.text-2x li:nth-child(5) a");
		waitPageLoadEnds();
		
		return new ShipmentOrderCrudForm(driver);
	}

	public String getTotalEquipmentCount() {

		return getValueOfElement("total");
	}

	public ShipmentOrderAddQuantitativeEquipment removeItemInTheRow(int rowNumber) {

		deleteOneRowInTableByPosition(rowNumber, "i.fa-remove");
		waitOneTimeInPage(1500);
		return this;
	}

	public ShipmentOrderAddQuantitativeEquipment setPO(String po) {

		setFieldById("po", po);
		return this;
	}

	public ShipmentOrderAddQuantitativeEquipment setSI(String si) {

		setFieldById("si", si);
		return this;
	}

	public ShipmentOrderAddQuantitativeEquipment validateMessageErrorSimple(String... error) {

		validateMessagesErrors(".media-body .alert-title", error);
		waitOneTimeInPage(2000);
		return this;
	}
}
