package br.com.workfinity.web.page.recess;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class RecessShowPage extends Page {

	public RecessShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("recess/show");
	}

	public RecessCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new RecessCrudFormPage(driver);
	}

	public RecessListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new RecessListPage(driver);
	}

}
