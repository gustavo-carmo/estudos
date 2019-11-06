package br.com.workfinity.web.page.repairOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class RepairOrderModelBatchChange extends Page {

	public RepairOrderModelBatchChange(WebDriver driver) {
		super(driver);
	}

	public RepairOrderModelBatchChange setServiceGroup(String serviceGroup) {
		setSelectByIdAndVisibleText("batchChangeModalServiceGroup", serviceGroup);
		waitAjaxEnd();
		return this;
	}

	public RepairOrderModelBatchChange setService(String service) {
		setSelectByIdAndVisibleText("batchChangeModalService", service);
		return this;
	}

	public RepairOrderModelBatchChange setTechnician(String user) {
		setSelectByIdAndVisibleText("batchChangeModalTechnician", user);
		return this;
	}

	public RepairOrderModelBatchChange setStatus(String status) {
		setSelectByIdAndVisibleText("batchChangeModalStatus", status);
		return this;
	}

	public RepairOrderListPage buttonRepairOrderBatchChange() {
		clickByCssSelector("div.modal-footer > button.btn.btn-primary");
		waitAjaxEnd();
		return new RepairOrderListPage(driver);
	}
}
