package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ShipmentOrderConferedEquipment extends Page {

	public ShipmentOrderConferedEquipment(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrder/check");
	}

	public void setSerialNumber(String serialNumber) {

		setFieldByCssSelectorAndTapEnter("#serialNumberToCheck", serialNumber);
	}
	
	public ShipmentOrderCrudForm backToShipmentOrderCrud() {

		clickByCssSelector(".breadcrumb.text-2x li:nth-child(5) a");
		waitPageLoadEnds();
		
		return new ShipmentOrderCrudForm(driver);
	}
}
