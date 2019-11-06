package br.com.workfinity.web.page.workflow;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class WorkflowCrudFormPage extends Page {

	public WorkflowCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("workflow/create", "workflow/edit");
	}

	public WorkflowCrudFormPage setWorkflow(String type) {
		setSelectByIdAndVisibleText("workflowType", type);
		return this;
	}

	public WorkflowCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public WorkflowShowPage buttonCreate() {
		clickByCssSelector("button.btn.btn-primary");
		waitPageLoadEnds();
		return new WorkflowShowPage(driver);		
	}

	public WorkflowShowPage buttonBackToDetail() {
		clickByName("_action_show");
		waitPageLoadEnds();
		return new WorkflowShowPage(driver);
	}

	public WorkflowShowPage buttonUpdate() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new WorkflowShowPage(driver);
	}

	public WorkflowCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public WorkflowCrudFormPage buttonCreateFail() {
		clickByCssSelector("button.btn.btn-primary");
		waitPageLoadEnds();
		return this;
	}

	public WorkflowCrudFormPage validateErrorMessage(String... messageError) {
		validateMessagesErrors(messageError);
		return this;
	}

}
