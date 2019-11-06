package br.com.workfinity.web.page.notification;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class AddNotficationPage extends Page {

	public AddNotficationPage(WebDriver driver) {
		super(driver);
	}

	public AddNotficationPage setNotification(String notification) {
		setSelectByIdAndVisibleText("notification.id", notification);
		return this;
	}

	public void buttonSave() {
		clickByCssSelector("div#notification_target_add_modal_modal-body a.btn.btn-primary");
		waitAjaxEnd();
	}
}
