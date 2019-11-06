package br.com.workfinity.web.page.notification;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class NotificationCrudFormPage extends Page {

	public NotificationCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("notification/create", "notification/edit");
	}

	public NotificationCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public NotificationCrudFormPage setType(String type) {
		setSelectByIdAndVisibleText("type", type);
		return this;
	}

	public NotificationCrudFormPage setPeriodicity(String periodicity) {
		setSelectByIdAndVisibleText("periodicity", periodicity);
		return this;
	}

	public NotificationCrudFormPage setTitle(String title) {
		setFieldById("title", title);
		return this;
	}

	public NotificationCrudFormPage setMessage(String message) {
		setFieldById("message", message);
		return this;
	}

	public NotificationShowPage buttonCreate() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new NotificationShowPage(driver);
	}

	public NotificationCrudFormPage setStartAt(String date) {
		setFieldById("startAt_value", date);
		return this;
	}

	public NotificationCrudFormPage setEndAt(String date) {
		setFieldById("endAt_value", date);
		return this;
	}

}
