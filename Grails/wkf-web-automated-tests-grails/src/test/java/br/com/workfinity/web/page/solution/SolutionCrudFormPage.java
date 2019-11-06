package br.com.workfinity.web.page.solution;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class SolutionCrudFormPage extends Page {

	public SolutionCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("solution/create", "solution/edit");
	}

	public SolutionCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public SolutionCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public SolutionCrudFormPage checkBoxRCE(boolean clickTrueOrFalse) {
		setCheckBoxById("changeEquipment", clickTrueOrFalse);
		return this;
	}

	public SolutionShowPage buttonCreate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new SolutionShowPage(driver);
	}

	public SolutionShowPage buttonUpdate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new SolutionShowPage(driver);
	}

}
