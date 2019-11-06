package br.com.workfinity.web.page.reasonForCancellation;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ReasonForCancellationCrudFormPage extends Page {

	public ReasonForCancellationCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("reasonForCancellation/create", "reasonForCancellation/edit");
	}

	public ReasonForCancellationCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ReasonForCancellationCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ReasonForCancellationShowPage buttonCreate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new ReasonForCancellationShowPage(driver);
	}

	public ReasonForCancellationShowPage buttonUpdate() {
		clickByCssSelector("button.btn.btn-primary");
		waitPageLoadEnds();
		return new ReasonForCancellationShowPage(driver);	
	}

}