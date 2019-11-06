package br.com.workfinity.web.page.serviceGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceGroupCrudFormPage extends Page {

	public ServiceGroupCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceGroup/create", "serviceGroup/edit");
	}

	public void setType(String type) {
		setSelectByIdAndVisibleText("type", type);
	}

	public void setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
	}

	public void setName(String name) {
		setFieldById("name", name);
	}

	public ServiceGroupShowPage buttonCreate() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ServiceGroupShowPage(driver);
	}

	public ServiceGroupShowPage buttonUpdate() {
		clickByCssSelector("button.btn.btn-primary");
		waitPageLoadEnds();
		return new ServiceGroupShowPage(driver);
		
		
	}
}
