package br.com.workfinity.web.page.scheduledGrid;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ScheduledGridCrudForm extends Page {

	public ScheduledGridCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("scheduledGrid/create", "scheduledGrid/edit");
	}

	public ScheduledGridCrudForm setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ScheduledGridCrudForm buttonCreate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}


	public ScheduledGridCrudForm validateErrorMessage(String... messageError) {
		validateMessagesErrors(messageError);
		return this;
	}

	public ScheduledGridListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new ScheduledGridListPage(driver);
	}

	public ScheduledGridCrudForm buttonAttendanceWindow() {
		clickByCssSelector("ul.nav.nav-tabs li:nth-child(2)");
		waitAjaxEnd();
		return this;
	}

	public ScheduledGridAttendanceWindow buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ScheduledGridAttendanceWindow(driver);
	}

	public ScheduledGridAttendanceWindow editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ScheduledGridAttendanceWindow(driver);
	}
}
