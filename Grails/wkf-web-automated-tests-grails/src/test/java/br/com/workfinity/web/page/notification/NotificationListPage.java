package br.com.workfinity.web.page.notification;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class NotificationListPage extends PageList {

	public NotificationListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("notification/index", "notification/list");
	}

	public NotificationCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();		
		return new NotificationCrudFormPage(driver);
	}

	public NotificationListPage setType(String type) {
		setSelectByIdAndVisibleText("type", type);
		return this;
	}

	public NotificationListPage setPeriodicity(String periodicity) {
		setSelectByIdAndVisibleText("periodicity", periodicity);
		return this;
	}

	public NotificationShowPage showItemTable(int rowNumber) {
		showItemTable(rowNumber, 1);
		return new NotificationShowPage(driver);
	}
	
}
