package br.com.workfinity.web.page.defect;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class DefectCrudFormPage extends Page {

	public DefectCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("defect/create", "defect/edit");
	}

	public DefectShowPage buttonCreate() {
		clickByCssSelector("button.btn.btn-primary");
		waitPageLoadEnds();
		return new DefectShowPage(driver);
	}

	public DefectCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public DefectCrudFormPage setFamily(String family) {
		setSelectByIdAndVisibleText("family", family);
		return this;
	}

	public DefectCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public DefectShowPage buttonUpdate() {
		clickByCssSelector("button.btn.btn-primary");
		waitPageLoadEnds();
		return new DefectShowPage(driver);			
	}
}
