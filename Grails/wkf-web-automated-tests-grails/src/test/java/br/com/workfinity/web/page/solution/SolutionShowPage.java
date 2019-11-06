package br.com.workfinity.web.page.solution;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class SolutionShowPage extends Page {

	public SolutionShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("solution/show");
	}

	public SolutionCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new SolutionCrudFormPage(driver);
	}

	public SolutionListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new SolutionListPage(driver);
	}
}
