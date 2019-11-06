package br.com.workfinity.web.page.serviceOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceOrderCrudFormCommentModel extends Page {

	public ServiceOrderCrudFormCommentModel(WebDriver driver) {
		super(driver);
	}

	public ServiceOrderCrudFormCommentModel setDescription(String description) {
		setFieldByCssSelector(".modal-content #description", description);
		return this;
	}

	public ServiceOrderCrudForm buttonSave() {
		clickByCssSelector(".modal-content .btn.btn-primary");
		waitAjaxEnd();
		return new ServiceOrderCrudForm(driver);
	}

	
}
