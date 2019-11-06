package br.com.workfinity.web.page.customer;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class CustomerListPage extends PageList {

	public CustomerListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("customer/index", "customer/list");
	}

	public CustomerCrudFormPage buttonNew() {
		clickById("null");
		waitAjaxEnd();
		return new CustomerCrudFormPage(driver);
	}

	public CustomerListPage setAlias(String alias) {
		setFieldById("alias", alias);
		return this;
	}

	public CustomerListPage setDocument(String type, String number) {
		setSelectByIdAndVisibleText("documentTypeSearch", type);
		setFieldById("documentNumberSearch", number);
		return this;
	}

	public CustomerListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}
	
	public CustomerListPage setContractor(String contractor) {
		setFieldWithDownArrowAutoCompleteByCssSelector("div.input-group-btn.ui-combobox > input.form-control.ui-autocomplete-input", contractor, "ui-id-2");
		waitAjaxEnd();
		return this;
	}

	public CustomerCrudFormPage clickLink(String name) {
		clickByLinkText(name);
		waitAjaxEnd();
		return new CustomerCrudFormPage(driver);
	}

	public CustomerListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public CustomerListPage selectCustomerInTableForNotification(int rowNumber) {
		tableSetForNotification(rowNumber);
		return this;
	}
}
