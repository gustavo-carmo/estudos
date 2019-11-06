package br.com.workfinity.web.page.scheduledGrid;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ScheduledGridAttendanceWindow extends Page {

	public ScheduledGridAttendanceWindow(WebDriver driver) {
		super(driver);
		validateUrlContains("scheduledGridItem/create", "scheduledGridItem/edit");
	}

	public ScheduledGridAttendanceWindow buttonCreateFail() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}
	
	public ScheduledGridCrudForm buttonCreateSuccess() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ScheduledGridCrudForm(driver);
	}

	public ScheduledGridAttendanceWindow validateErrorMessage(String... messageError) {
		validateMessagesErrors(messageError);
		return this;
	}

	public ScheduledGridAttendanceWindow setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ScheduledGridAttendanceWindow setStartAt(String startAt) {
		setFieldById("startAt_timepicker", startAt);
		return this;
	}

	public ScheduledGridAttendanceWindow setEndAt(String endAt) {
		setFieldById("endAt_timepicker", endAt);
		return this;
	}

	public ScheduledGridCrudForm buttonBackToScheduledGrid() {
		clickByName("_action_edit");
		return new ScheduledGridCrudForm(driver);
	}
	
}
