package br.com.workfinity.web.page.openingHours;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class OpeningHoursListPage extends PageList {

	public OpeningHoursListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("openingHours/index", "openingHours/list");
	}

	public OpeningHoursCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn-info");
		waitPageLoadEnds();
		return new OpeningHoursCrudFormPage(driver);
	}

	public OpeningHoursListPage setDaysOfWeek(String dayOfWeek) {
		setSelectByIdAndVisibleText("dayOfWeek", dayOfWeek);
		return this;
	}

	public OpeningHoursCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new OpeningHoursCrudFormPage(driver);
	}

	public OpeningHoursListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}

	public OpeningHoursListPage findRowAndDeleteItemTable(String dayOfWeek, String startAt, String endAt) {
		deleteItemTableByPosition(returnOneRowInTableFindByText(Arrays.asList(dayOfWeek, startAt, endAt)), 1);
		return this;
	}

}
