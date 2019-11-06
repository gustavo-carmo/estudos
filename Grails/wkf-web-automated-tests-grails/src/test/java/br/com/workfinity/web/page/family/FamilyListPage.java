package br.com.workfinity.web.page.family;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class FamilyListPage extends PageList {

	public FamilyListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("family/index", "family/list");
	}

	public FamilyCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new FamilyCrudFormPage(driver);
	}

	public FamilyListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public FamilyListPage setStatus(String staus) {
		setSelectByIdAndVisibleText("status", staus);
		return this;
	}

	public FamilyCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new FamilyCrudFormPage(driver);
	}

	public FamilyListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}
}
