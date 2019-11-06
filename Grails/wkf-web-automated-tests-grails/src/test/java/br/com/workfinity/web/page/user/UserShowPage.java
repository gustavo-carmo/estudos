package br.com.workfinity.web.page.user;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class UserShowPage extends Page {

	public UserShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("user/show");
	}

	public UserShowPage setPermission(String id, boolean value) {
		setCheckBoxById(id, value);
		return this;
	}

	public UserShowPage clickSavePermission() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return this;
	}

	public UserShowPage deselectAllClick() {
		clickById("btnUnselectAll");
		return this;
	}

	public UserShowPage clickSelectAll() {
		clickById("btnSelectAll");
		return this;
	}
}
