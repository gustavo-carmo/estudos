package br.com.workfinity.web.page.importProcess;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ImportProcessFormCrudPage extends Page {

	public ImportProcessFormCrudPage(WebDriver driver) {
		super(driver);
		validateUrlContains("importProcess/create");
	}

	public ImportProcessFormCrudPage setFile(String file) {
		findById("file").sendKeys(file);
		return this;
	}

	public ImportProcessListPage buttonCreate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ImportProcessListPage(driver);
	}

	public ImportProcessFormCrudPage setType(String type) {
		setSelectByIdAndVisibleText("type", type);
		return this;
	}

}
