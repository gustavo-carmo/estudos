package br.com.workfinity.web.page.productionPlanning;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ProductionPlanningListPage extends PageList {

	public ProductionPlanningListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("productionPlanning/index", "productionPlanning/list");
	}

	public ProductionPlanningListPage setServiceProvider(String serviceProvider) {
		setFieldWithDownArrowAutoCompleteByCssSelector("#filters_form div div:nth-child(1) div div div input", serviceProvider, "ui-id-1");
		return this;
	}

	public ProductionPlanningListPage setContractor(String contractor) {
		setFieldWithDownArrowAutoCompleteByCssSelector("#filters_form div div:nth-child(2) div div div input", contractor, "ui-id-2");
		return this;
	}

	public ProductionPlanningListPage clickCheckBoxInTable(int rowNumber) {
		findByName(findTableReturnRowAndColumn(rowNumber, 1), "equipments").click();
		return this;
	}

	public ProductionPlanningModalGenerateRepairOrders buttonGenerateRepairOrders() {
		clickByLinkText("Gerar Ordens de Reparo");
		return new ProductionPlanningModalGenerateRepairOrders(driver);
	}

	public void validateMessageCreated() {
		validateMessageSuccess("Ordens de Reparos Criadas");
	}

}
