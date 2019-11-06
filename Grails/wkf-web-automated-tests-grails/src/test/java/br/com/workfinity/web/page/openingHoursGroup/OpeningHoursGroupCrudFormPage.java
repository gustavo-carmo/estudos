package br.com.workfinity.web.page.openingHoursGroup;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class OpeningHoursGroupCrudFormPage extends Page {

	public OpeningHoursGroupCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("openingHoursGroup/create", "openingHoursGroup/edit");
	}

	public void setName(String name) {
		setFieldById("name", name);
	}

	public void clickCheckUncheckAll(boolean value) {
		setCheckBoxById("checkUncheckAll", value);
	}

	public OpeningHoursGroupShowPage buttonCreate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new OpeningHoursGroupShowPage(driver);
	}

	public OpeningHoursGroupShowPage buttonUpdate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new OpeningHoursGroupShowPage(driver);
	}

	public OpeningHoursGroupCrudFormPage clickCheckOneDay(String day, int startAt, int endAt) {
		findByName(
				findTableReturnRowAndColumn(returnOneRowInTableFindByText(Arrays.asList(day, String.valueOf(startAt), String.valueOf(endAt))), 1),
				"openingHours").click();
		return this;
	}
}
