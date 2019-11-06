package br.com.workfinity.web.page.model;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ModelCrudFormPage extends Page {

	public ModelCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("model/create", "model/edit");
	}

	public ModelCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ModelCrudFormPage setType(String type) {
		setSelectByIdAndVisibleText("type", type);
		return this;
	}

	public ModelCrudFormPage setManufacturer(String manufacturerName) {
		setSelectByIdAndVisibleText("manufacturerId", manufacturerName);
		return this;
	}

	public ModelCrudFormPage setDescription(String description) {
		setFieldById("description", description);
		return this;
	}

	public ModelCrudFormPage setFamily(String familyName) {
		setSelectByIdAndVisibleText("family", familyName);
		return this;
	}

	public ModelShowPage buttonCreateSuccess() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ModelShowPage(driver);
	}

	public ModelCrudFormPage buttonCreateFail() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public ModelCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ModelShowPage buttonUpdate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ModelShowPage(driver);	
	}
}
