package br.com.workfinity.web.page.openingHoursGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class OpeningHoursGroupListPage extends PageList {

	public OpeningHoursGroupListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("openingHoursGroup/index", "openingHoursGroup/list");
	}

	public OpeningHoursGroupCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn-info");
		waitPageLoadEnds();
		return new OpeningHoursGroupCrudFormPage(driver);
	}

	public OpeningHoursGroupCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new OpeningHoursGroupCrudFormPage(driver);
	}

	public OpeningHoursGroupListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public void deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
	}
}
