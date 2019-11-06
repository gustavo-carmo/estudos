package br.com.workfinity.web.page.reasonForCancellation;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ReasonForCancellationShowPage extends Page {

	public ReasonForCancellationShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("reasonForCancellation/show");
	}

	public ReasonForCancellationCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new ReasonForCancellationCrudFormPage(driver);
	}

	public ReasonForCancellationListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new ReasonForCancellationListPage(driver);
	}
}
