package br.com.workfinity.web.page.serviceOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceOrderTabAttachmentsUpload extends Page {

	public ServiceOrderTabAttachmentsUpload(WebDriver driver) {
		super(driver);
	}

	public ServiceOrderTabAttachmentsUpload setFile(String file) {

		findById("file").sendKeys(file);
		return this;
	}

	public ServiceOrderCrudForm buttonUploadFile() {

		clickByCssSelector("#attachmentModal .btn.btn-primary");
		waitPageLoadEnds();
		return new ServiceOrderCrudForm(driver);
	}
}
