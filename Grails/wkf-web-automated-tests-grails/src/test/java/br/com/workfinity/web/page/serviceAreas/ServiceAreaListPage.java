package br.com.workfinity.web.page.serviceAreas;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ServiceAreaListPage extends PageList {
	
	public ServiceAreaListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceAreas/index", "serviceAreas/list");
	}

	public ServiceAreaCrudForm buttonNew() {
		clickByName("_action_create");
		return new ServiceAreaCrudForm(driver);
	}

}
