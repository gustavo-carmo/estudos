package br.com.workfinity.web.page.genericContract;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class GenericContractListPage extends Page {

	public GenericContractListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("genericContract/index", "genericContract/list");
	}

	public GenericContractCrudFormPage buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new GenericContractCrudFormPage(driver);
	}
}
