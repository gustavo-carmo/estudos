package br.com.workfinity.web.page.status;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class StatusCrudFormPage extends Page {

	public StatusCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("stepStatus/create", "stepStatus/edit");
	}

	public StatusShowPage buttonCreate() {
		clickByCssSelector("button[type='submit']");
		waitPageLoadEnds();
		return new StatusShowPage(driver);
	}

	public void setName(String name) {
		setFieldById("name", name);
	}

	public void setWorkFlow(String workFlow) {
		setSelectByIdAndVisibleText("workflowType", workFlow);
	}

	public void setType(String type) {
		setSelectByIdAndVisibleText("type", type);
	}

	public StatusShowPage buttonUpdate() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new StatusShowPage(driver);
	}
}
