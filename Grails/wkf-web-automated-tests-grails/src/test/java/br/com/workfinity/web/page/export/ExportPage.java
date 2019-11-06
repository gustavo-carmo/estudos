package br.com.workfinity.web.page.export;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ExportPage extends PageList {

	public ExportPage(WebDriver driver) {
		super(driver);
		validateUrlContains("exportBanrisul/list", "exportBanrisul/index");
	}

	public ExportCrudPage buttonNovaExportacao() {
		clickByCssSelector("button[type='submit'].btn.btn-default");
		waitPageLoadEnds();
		return new ExportCrudPage(driver);
	}

	public ExportCrudPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ExportCrudPage(driver);
	}
}
