package br.com.workfinity.web.page.manufacturer;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ManufacturerCrudFormPage extends Page {

	public ManufacturerCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("manufacturer/edit", "manufacturer/create");
	}

	public ManufacturerCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ManufacturerCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ManufacturerShowPage buttonCreate() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ManufacturerShowPage(driver);
	}

	public ManufacturerShowPage buttonUpdate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new ManufacturerShowPage(driver);
	}

}
