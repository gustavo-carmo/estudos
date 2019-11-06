package br.com.workfinity.web.page.scheduledGrid;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ScheduledGridListPage extends PageList {

	public ScheduledGridListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("scheduledGrid/index", "scheduledGrid/list");
	}

	public ScheduledGridCrudForm buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ScheduledGridCrudForm(driver);
	}

	public ScheduledGridListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ScheduledGridListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ScheduledGridCrudForm editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ScheduledGridCrudForm(driver);
	}
}
