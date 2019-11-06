package br.com.workfinity.web.page.workflow;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class WorkflowListPage extends PageList {

	public WorkflowListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("workflow/index", "workflow/list");
	}

	public WorkflowCrudFormPage buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new WorkflowCrudFormPage(driver);
	}

	public WorkflowListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public WorkflowListPage setWorkflow(String type) {
		setSelectByIdAndVisibleText("workflowType", type);
		return this;
	}

	public WorkflowCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new WorkflowCrudFormPage(driver);
	}

	public WorkflowListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}

	public WorkflowShowPage showItemTable(int rowNumber) {
		showItemTable(rowNumber, 1);
		waitPageLoadEnds();	
		return new WorkflowShowPage(driver);
	}

	public int getRowNumber(List<String> textReturn) {
		return returnOneRowInTableFindByText(textReturn);
	}
}
