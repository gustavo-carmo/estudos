package br.com.workfinity.web.page.productionPlanning;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ProductionPlanningModalGenerateRepairOrders extends Page {

	public ProductionPlanningModalGenerateRepairOrders(WebDriver driver) {
		super(driver);
	}

	public ProductionPlanningListPage buttonGenerateRepairOrders() {
		clickByCssSelector("div.modal-footer > button.btn.btn-primary");
		return new ProductionPlanningListPage(driver);
	}

	public ProductionPlanningModalGenerateRepairOrders setServiceGroup(String serviceGroup) {
		setSelectByIdAndVisibleText("serviceGroup", serviceGroup);
		waitAjaxEnd();
		return this;
	}

	public ProductionPlanningModalGenerateRepairOrders setService(String service) {
		setSelectByIdAndVisibleText("service", service);
		return this;
	}

	public ProductionPlanningModalGenerateRepairOrders setTechnician(String user) {
		setSelectByIdAndVisibleText("user", user);
		return this;
	}

}
