package br.com.workfinity.web.page.model;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ModelListPage extends PageList {

	public ModelListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("model/index", "model/list");
	}

	public ModelCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new ModelCrudFormPage(driver);
	}

	public ModelListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ModelListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ModelListPage setManufacturer(String manufacturer) {
		setFieldWithDownArrowAutoCompleteByCssSelector("div.input-group-btn.ui-combobox > input.form-control.ui-autocomplete-input", manufacturer, "ui-id-1");
		return this;
	}

	public ModelCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ModelCrudFormPage(driver);
	}

	public ModelListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}
}
