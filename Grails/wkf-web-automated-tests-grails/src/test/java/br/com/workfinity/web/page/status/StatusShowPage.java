package br.com.workfinity.web.page.status;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class StatusShowPage extends Page{

	public StatusShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("stepStatus/show");
	}

	public StatusListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new StatusListPage(driver);
	}

	public StatusCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new StatusCrudFormPage(driver);
	}
	

}
