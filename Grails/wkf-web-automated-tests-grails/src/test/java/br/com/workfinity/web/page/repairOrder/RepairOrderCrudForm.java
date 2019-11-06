package br.com.workfinity.web.page.repairOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class RepairOrderCrudForm extends Page {

	public RepairOrderCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("repairOrder/edit");
	}

	public RepairOrderCrudForm setTechnician(String technician) {
		setSelectByIdAndVisibleText("user", technician);
		return this;
	}

	public RepairOrderCrudForm setRepairLevel(String repairLevel) {
		setSelectByIdAndVisibleText("repairLevel", repairLevel);
		return this;
	}

	public RepairOrderCrudForm buttonUpdate() {
		clickByCssSelector(".panel-footer.text-left .btn-primary[type='submit']");
		waitPageLoadEnds();
		return this;
	}

	public RepairOrderCrudForm setStatus(String status) {
		clickById("btnStatusSelected");
		clickByLinkText(status);
		return this;
	}

	public RepairOrderListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new RepairOrderListPage(driver);
	}

}
