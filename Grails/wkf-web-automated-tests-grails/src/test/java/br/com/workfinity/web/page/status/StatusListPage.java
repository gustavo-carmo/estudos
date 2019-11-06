package br.com.workfinity.web.page.status;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class StatusListPage extends PageList {

	public StatusListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("stepStatus/index", "stepStatus/list");
	}

	public StatusCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new StatusCrudFormPage(driver);
	}

	public StatusListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public StatusListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public StatusListPage setWorkflowType(String workflow) {
		setSelectByIdAndVisibleText("workflowType", workflow);
		return this;
	}

	public StatusListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}

	public StatusCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new StatusCrudFormPage(driver);
	}
}
