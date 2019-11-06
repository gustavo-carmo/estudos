package br.com.workfinity.web.page.user;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class UserCrudFormPage extends Page {

	public UserCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("user/create", "user/edit");
	}

	public UserCrudFormPage setUsername(String username) {
		setFieldById("username", username);
		return this;
	}

	public UserCrudFormPage setNewPassword(String password) {
		setFieldById("newPassword", password);
		return this;
	}

	public UserCrudFormPage setRepeatNewPassword(String repeatPassword) {
		setFieldById("repeatNewPassword", repeatPassword);
		return this;
	}

	public UserCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public UserCrudFormPage setEmail(String email) {
		setFieldById("email", email);
		return this;
	}

	public UserCrudFormPage setEnabled(boolean value) {
		setCheckBoxById("enabled", value);
		return this;
	}

	public UserCrudFormPage setAddress(boolean value) {
		setCheckBoxById("address_enabled", value);
		return this;
	}

	public UserCrudFormPage setLanguage(String language) {
		setSelectByIdAndVisibleText("language.id", language);
		return this;
	}

	public UserCrudFormPage setProfile(String profile) {
		setSelectByIdAndVisibleText("profile", profile);
		return this;
	}

	public UserCrudFormPage setServiceProvider(String serviceProvider) {
		setSelectByIdAndVisibleText("serviceProvider.id", serviceProvider);
		return this;
	}

	public UserCrudFormPage setContractor(String contractor) {
		setSelectByIdAndVisibleText("contractor.id", contractor);
		return this;
	}

	public UserCrudFormPage setNotes(String notes) {
		setFieldById("notes", notes);
		return this;
	}

	public UserShowPage buttonCreate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new UserShowPage(driver);
	}

	public UserShowPage buttonUpdate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		return new UserShowPage(driver);
	}
}
