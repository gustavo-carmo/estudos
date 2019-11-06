package br.com.workfinity.web.page.model;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ModelShowPage extends Page {

	public ModelShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("model/show");
	}

	public ModelCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		return new ModelCrudFormPage(driver);
	}

	public ModelListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new ModelListPage(driver);
	}

}
