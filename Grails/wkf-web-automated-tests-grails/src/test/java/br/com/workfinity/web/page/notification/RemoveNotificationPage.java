package br.com.workfinity.web.page.notification;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class RemoveNotificationPage extends Page {

	public RemoveNotificationPage(WebDriver driver) {
		super(driver);
	}

	public NotificationShowPage buttonConfirm() {
		clickByCssSelector(".modal-dialog.modal-md div#notification_target_remove_modal_modal-body .btn.btn.btn-danger");
		waitAjaxEnd();
		return new NotificationShowPage(driver);
	}
	
	

}
