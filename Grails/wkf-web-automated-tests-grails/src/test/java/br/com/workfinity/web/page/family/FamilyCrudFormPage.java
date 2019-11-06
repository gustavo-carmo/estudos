package br.com.workfinity.web.page.family;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class FamilyCrudFormPage extends Page {

	public FamilyCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("family/create", "family/edit");
	}

	public FamilyCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public FamilyShowPage buttonCreate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new FamilyShowPage(driver);
	}

	public FamilyCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public FamilyShowPage buttonUpdate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		return new FamilyShowPage(driver);
	}
}
