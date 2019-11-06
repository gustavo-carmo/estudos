package br.com.workfinity.web.page.manufacturer;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ManufacturerListPage extends PageList {

	public ManufacturerListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("manufacturer/list", "manufacturer/index");
	}

	public ManufacturerCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new ManufacturerCrudFormPage(driver);
	}

	public ManufacturerListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ManufacturerListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return new ManufacturerListPage(driver);
	}

	public ManufacturerCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ManufacturerCrudFormPage(driver);
	}

	public ManufacturerListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}

}
