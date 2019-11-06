package br.com.workfinity.web.page.solution;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class SolutionListPage extends PageList {

	public SolutionListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("solution/index", "solution/list");
	}

	public SolutionCrudFormPage buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new SolutionCrudFormPage(driver);
	}

	public SolutionListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public SolutionListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public SolutionCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new SolutionCrudFormPage(driver);
	}

	public SolutionListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}

}
