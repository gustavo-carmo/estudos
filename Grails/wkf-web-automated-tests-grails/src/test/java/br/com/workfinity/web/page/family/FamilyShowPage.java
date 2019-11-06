package br.com.workfinity.web.page.family;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class FamilyShowPage extends Page {

	public FamilyShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("family/show");
	}

	public FamilyCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new FamilyCrudFormPage(driver);
	}

	public FamilyListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new FamilyListPage(driver);
	}
}
