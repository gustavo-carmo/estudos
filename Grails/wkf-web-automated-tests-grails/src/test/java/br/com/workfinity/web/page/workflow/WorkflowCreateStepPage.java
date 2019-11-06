package br.com.workfinity.web.page.workflow;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class WorkflowCreateStepPage extends Page {

	public WorkflowCreateStepPage(WebDriver driver) {
		super(driver);
		validateUrlContains("step/create", "step/edit", "login/denied");
	}

	public void setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
	}

	public WorkflowCreateStepPage setFrom(String from) {
		setSelectByIdAndVisibleText("from.id", from);
		return this;
	}

	public WorkflowCreateStepPage setTo(String to) {
		setSelectByIdAndVisibleText("to.id", to);
		return this;
	}

	public WorkflowCreateStepPage setCheckBox(String id, boolean value) {
		setCheckBoxById(id, value);
		return this;
	}

	public void buttonCreateFail() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
	}

	public WorkflowShowPage buttonCreateSucess() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new WorkflowShowPage(driver);
	}

	public WorkflowCreateStepPage setCustomTriggers(String visibleText) {
		setSelectByIdAndVisibleText("stepRuleCustomTrigger", visibleText);
		waitAjaxEnd();
		return this;
	}

	public WorkflowCreateStepPage setCustomParam(String value) {
		setFieldById("stepRuleTypeCustomTriggerParam", value);
		return this;
	}

	public void buttonAdd() {
		clickById("addCustomTriggerButton");
	}

	public WorkflowShowPage buttonBackToDetail() {
		clickByName("_action_show");
		waitPageLoadEnds();
		return new WorkflowShowPage(driver);
	}

}
