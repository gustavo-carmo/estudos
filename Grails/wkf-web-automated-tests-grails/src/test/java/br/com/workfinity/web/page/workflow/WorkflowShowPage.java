package br.com.workfinity.web.page.workflow;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class WorkflowShowPage extends Page {

	public WorkflowShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("workflow/show");
	}

	public WorkflowCrudFormPage buttonEdit() {
		clickByName("_action_edit");
		waitPageLoadEnds();
		return new WorkflowCrudFormPage(driver);
	}

	public WorkflowCreateStepPage buttonAddStep() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new WorkflowCreateStepPage(driver);
	}

	public WorkflowListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new WorkflowListPage(driver);
	}

	public WorkflowCreateStepPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new WorkflowCreateStepPage(driver);
	}

	public int getRowNumber(List<String> textReturn) {
		
		return returnOneRowInTableFindByText(textReturn);
	}
}
