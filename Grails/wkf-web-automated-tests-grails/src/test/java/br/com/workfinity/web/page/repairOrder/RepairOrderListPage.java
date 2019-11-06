package br.com.workfinity.web.page.repairOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class RepairOrderListPage extends PageList {

	public RepairOrderListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("repairOrder/index", "repairOrder/list");
	}

	public RepairOrderListPage setStatus(String status) {
		setFieldByCssSelector("#statusId > div.ms-sel-ctn > input[type=\"text\"]", status);
		clickByCssSelector("#statusId .ms-trigger .ms-trigger-ico");
		clickByCssSelector(".ms-res-item.ms-res-item-active");
		return this;
	}

	public RepairOrderListPage clickCheckBoxInTable(int rowNumber) {
		findByName(findTableReturnRowAndColumn( rowNumber, 1), "repairOrders").click();
		return this;
	}

	public RepairOrderModelBatchChange buttonRepairOrdersBatchChange() {
		clickByCssSelector(".panel-body #batch_change_button");
		waitAjaxEnd();
		return new RepairOrderModelBatchChange(driver);
	}

	public RepairOrderListPage removeStatus(int numberStatus) {
		clickByCssSelector("div.ms-sel-ctn div:nth-child(" + numberStatus + ") span.ms-close-btn");
		return this;
	}

	public RepairOrderCrudForm editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 2);
		waitPageLoadEnds();
		return new RepairOrderCrudForm(driver);
	}
}
