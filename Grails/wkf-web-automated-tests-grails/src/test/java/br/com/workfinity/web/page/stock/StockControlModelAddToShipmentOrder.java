package br.com.workfinity.web.page.stock;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class StockControlModelAddToShipmentOrder extends Page {

	public StockControlModelAddToShipmentOrder(WebDriver driver) {
		super(driver);
	}

	public StockControlModelAddToShipmentOrder setShipmentOrder(String shipmentOrder) {
		setSelectByIdAndVisibleText("shipmentOrder", shipmentOrder);
		return this;
	}

	public StockControlModelAddToShipmentOrder buttonAdd() {
		clickByCssSelector("span.input-group-btn button.btn.btn-default.noWarn.pull-right");
		waitAjaxEnd();
		return this;
	}

	public StockControlListPage buttonClose() {
		clickByCssSelector("div.modal-dialog.modal-lg > div.modal-content > div.modal-footer > button.btn.btn-default");
		waitAjaxEnd();
		return new StockControlListPage(driver);
	}
	
	public String getSelectValue(String compareString){
		return getValueFromSelect(findById("shipmentOrder"), compareString);
	}
}
