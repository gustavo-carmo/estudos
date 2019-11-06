package br.com.workfinity.web.page.serviceGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceGroupShowPage extends Page {

	public ServiceGroupShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceGroup/show");
	}

	public ServiceGroupCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new ServiceGroupCrudFormPage(driver);
	}

	public ServiceGroupListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new ServiceGroupListPage(driver);
	}

}
