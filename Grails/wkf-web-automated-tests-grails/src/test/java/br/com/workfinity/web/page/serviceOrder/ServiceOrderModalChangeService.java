package br.com.workfinity.web.page.serviceOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceOrderModalChangeService extends Page {

	public ServiceOrderModalChangeService(WebDriver driver) {
		super(driver);
	}

	public ServiceOrderModalChangeService setService(String service) {
		setSelectByIdAndVisibleText("change_service_modal_service", service);
		return this;
	}

	public ServiceOrderModalChangeService setStatus(String service) {
		setSelectByIdAndVisibleText("change_service_modal_status", service);
		return this;
	}

	public ServiceOrderCrudForm buttonSave() {
		clickByCssSelector("#change_service_modal_modal-body a.btn-primary");
		waitAjaxEnd();
		return new ServiceOrderCrudForm(driver);
	}

}
