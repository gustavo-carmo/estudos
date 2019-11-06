package br.com.workfinity.web.page.recess;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class RecessListPage extends PageList {

	public RecessListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("recess/index", "recess/list");
	}

	public RecessCrudFormPage buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new RecessCrudFormPage(driver);
	}

	public RecessListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public RecessListPage setMonth(String month) {
		setSelectByIdAndVisibleText("month", month);
		return this;
	}

	public RecessListPage setCity(String city) {
		
		waitAjaxEnd();
		setFieldAutoCompleteById("autocomplete_cityId", city);
		return this;
	}

	public RecessListPage setState(String state) {
		
		waitAjaxEnd();
		setFieldAutoCompleteById("autocomplete_stateId", state);
		return this;
	}

	public RecessListPage setCountry(String country) {
		
		waitAjaxEnd();
		setFieldAutoCompleteById("autocomplete_countryId", country);
		return this;
	}

	public RecessListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}
}
