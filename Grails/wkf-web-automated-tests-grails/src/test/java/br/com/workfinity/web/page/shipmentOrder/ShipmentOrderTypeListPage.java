package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ShipmentOrderTypeListPage extends PageList {
	
	public ShipmentOrderTypeListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrderType/index", "shipmentOrderType/list");
	}

	public ShipmentOrderTypeCrudForm buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ShipmentOrderTypeCrudForm(driver);
	}

}
