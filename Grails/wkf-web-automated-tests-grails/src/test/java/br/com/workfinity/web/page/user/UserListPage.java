package br.com.workfinity.web.page.user;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;
import br.com.workfinity.web.page.notification.AddNotficationPage;

public class UserListPage extends PageList {

	public UserListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("user/index", "user/list");
	}

	public void setIdSearch(String id) {
		setFieldById("id", id);
	}

	public UserListPage setUsername(String username) {
		setFieldById("username", username);
		return this;
	}

	public UserListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public UserListPage setEmail(String email) {
		setFieldById("email", email);
		return this;
	}

	public UserListPage setEnabled(String visibleText) {
		setSelectByIdAndVisibleText("enabled", visibleText);
		return this;
	}

	public void setProfile(String profile) {
		setSelectByIdAndVisibleText("profile", profile);
	}

	public void setServiceProfvider(String serviceProvider) {
		setSelectByIdAndVisibleText("serviceProvider", serviceProvider);
	}

	public UserCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn-info");
		waitPageLoadEnds();
		return new UserCrudFormPage(driver);
	}

	public UserCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 2);
		waitPageLoadEnds();
		return new UserCrudFormPage(driver);
	}

	public UserListPage selectUserInTableForNotification(int rowNumber) {
		tableSetForNotification(rowNumber);
		return this;
	}

	public AddNotficationPage buttonAddNotification() {
		clickById("notification_target_add");
		waitAjaxEnd();
		return new AddNotficationPage(driver);
	}
}