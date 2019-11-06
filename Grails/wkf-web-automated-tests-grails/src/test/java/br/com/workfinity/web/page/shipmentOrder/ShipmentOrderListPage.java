package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ShipmentOrderListPage extends PageList {

	public ShipmentOrderListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrder/index", "shipmentOrder/list");
	}

	public ShipmentOrderCrudForm buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ShipmentOrderCrudForm(driver);
	}

	public ShipmentOrderListPage setCode(String code) {
		setFieldById("code", code);
		return this;
	}

	public ShipmentOrderCrudForm editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 2);
		waitPageLoadEnds();
		return new ShipmentOrderCrudForm(driver);
	}
}
