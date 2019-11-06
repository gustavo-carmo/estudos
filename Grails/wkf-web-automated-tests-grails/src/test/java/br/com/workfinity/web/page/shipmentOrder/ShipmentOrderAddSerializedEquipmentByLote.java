package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ShipmentOrderAddSerializedEquipmentByLote extends Page {

	public ShipmentOrderAddSerializedEquipmentByLote(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrder/addInBatch");
	}

	public ShipmentOrderAddSerializedEquipmentByLote setModel(String model) {

		setSelectByIdAndVisibleText("model", model);
		return this;
	}
	
	public ShipmentOrderAddSerializedEquipmentByLote setContractor(String contractor) {

		setSelectByIdAndVisibleText("contractor", contractor);
		return this;
	}

	public ShipmentOrderAddSerializedEquipmentByLote setSerialNumber(String... serialNumber) {

		waitOneTimeInPage(1500);
		setValuesInFieldByCssSelectorAndTapEnter("#serialNumbers", serialNumber);
		waitOneTimeInPage(1500);
		
		return this;
	}

	public ShipmentOrderAddSerializedEquipmentByLote buttonAddEquipments() {

		clickById("batch");
		waitOneTimeInPage(2000);
		return this;
	}

	public ShipmentOrderCrudForm backToEditShipmentOrder() {

		clickByCssSelector(".breadcrumb.text-2x li:nth-child(5) a");
		waitPageLoadEnds();
		
		return new ShipmentOrderCrudForm(driver);
	}
}
