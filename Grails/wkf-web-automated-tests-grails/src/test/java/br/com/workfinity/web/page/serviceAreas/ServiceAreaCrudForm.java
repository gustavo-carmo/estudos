package br.com.workfinity.web.page.serviceAreas;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceAreaCrudForm extends Page {
	
	public ServiceAreaCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceAreas/create", "serviceAreas/list");
	}

	public ServiceAreaCrudForm setName(String serviceArea) {
		setFieldById("name", serviceArea);
		return this;
	}

	public ServiceAreaShowPage buttonCreate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ServiceAreaShowPage(driver);
	}

}
