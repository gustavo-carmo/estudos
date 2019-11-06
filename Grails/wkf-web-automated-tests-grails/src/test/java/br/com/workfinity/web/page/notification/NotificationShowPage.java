package br.com.workfinity.web.page.notification;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class NotificationShowPage extends Page {

	public NotificationShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("notification/show");
	}

	public NotificationShowPage goToTargets() {
		clickById("showTargets");
		waitAjaxEnd();
		return this;
	}

	public NotificationShowPage selectTargetsInTableForNotification(int rowNumber) {
		setElementCheckBox(findById(findTableReturnRowAndColumn(rowNumber, 1), "targetIds"), true);
		return this;
	}

	public RemoveNotificationPage buttonRemoveNotification() {
		clickById("notification_target_list_remove");
		waitAjaxEnd();
		return new RemoveNotificationPage(driver);
	}

	public NotificationListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new NotificationListPage(driver);
	}

}
