package br.com.workfinity.web.page.openingHours;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class OpeningHoursCrudFormPage extends Page {

	public OpeningHoursCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("openingHours/create", "openingHours/edit");
	}

	public void setStartAt(String startAt) {
		setFieldById("startAt_timepicker", startAt);
	}
	
	public void setEndAt(String endAt) {
		setFieldById("endAt_timepicker", endAt);
	}

	public OpeningHoursShowPage buttonCreateSuccess() {
		clickByXpath("//button[@type='submit']");		
		waitPageLoadEnds();
		return new OpeningHoursShowPage(driver); 
	}

	public void setDayOfWeek(String dayOfWeek) {
		setSelectByIdAndVisibleText("dayOfWeek", dayOfWeek);
	}

	public OpeningHoursShowPage buttonUpdateSuccess() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new OpeningHoursShowPage(driver);
	}
	
	public void buttonUpdateFail() {
		clickByXpath("//button[@type='submit']");		
		waitPageLoadEnds();
	}

	public void buttonDeleteEndAt() {
		clickById("endAt_timepicker_clear");
	}	
}
