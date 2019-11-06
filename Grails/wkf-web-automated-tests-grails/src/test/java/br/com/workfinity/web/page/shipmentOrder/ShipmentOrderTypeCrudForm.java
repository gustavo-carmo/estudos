package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ShipmentOrderTypeCrudForm extends Page {
	
	public ShipmentOrderTypeCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrderType/create", "shipmentOrderType/edit", "shipmentOrderType/show");
	}
	
	public ShipmentOrderTypeCrudForm(WebDriver driver, String baseUrl, String id) {
		super(driver);
		visitURL(baseUrl + "/shipmentOrderType/edit/" + id);
		waitPageLoadEnds();
		validateUrlContains("shipmentOrderType/edit");
	}

	public ShipmentOrderTypeCrudForm setName(String shipmentOrderType) {
		setFieldById("name", shipmentOrderType);
		return this;
	}

	public ShipmentOrderTypeCrudForm setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ShipmentOrderTypeCrudForm setWorkflow(String workflowShipment) {
		setSelectByIdAndVisibleText("workflow.id", workflowShipment);
		return this;
	}

	public ShipmentOrderTypeCrudForm buttonCreate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public ShipmentOrderTypeCrudForm checkedBoxTrue(String id) {
		
		setCheckBoxById(id, true);
		return this;
	}

}
