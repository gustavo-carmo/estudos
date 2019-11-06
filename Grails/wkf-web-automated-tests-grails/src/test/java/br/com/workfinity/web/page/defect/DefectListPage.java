package br.com.workfinity.web.page.defect;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class DefectListPage extends PageList {

	public DefectListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("defect/index", "defect/list");
	}
	
	public DefectListPage setName(String name){
		setFieldById("name", name);
		return this;
	}

	public DefectCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new DefectCrudFormPage(driver);
	}

	public DefectListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public DefectListPage setFamily(String name) {
		setFieldWithDownArrowAutoCompleteByCssSelector("div.input-group-btn.ui-combobox > input.form-control.ui-autocomplete-input", name, "ui-id-1");
		return this;
	}

	public DefectCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new DefectCrudFormPage(driver);
	}

	public DefectListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}
}