package br.com.workfinity.web.page.export;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ExportCrudPage extends Page{

	public ExportCrudPage(WebDriver driver) {
		super(driver);
		validateUrlContains("exportBanrisul/create", "exportBanrisul/edit");
	}

	public ExportCrudPage buttonCreate() {
		clickByCssSelector("div.panel-footer.text-left button.btn.btn-primary");
		waitPageLoadEnds();
		return this;
	}

	public ExportCrudPage buttonExport() {
		clickByCssSelector("div.panel-footer.text-left button.btn.btn-warning");
		return this;
	}

	public ExportPage buttonBackToSearch() {
		clickByCssSelector("div.panel-footer.text-left button.btn.btn.btn-default");
		waitPageLoadEnds();
		return new ExportPage(driver);
	}

	public ExportCrudPage validateStatusMessage(String message) {
		validateIfContainsTextInElement(findById("statusMessage"), message);
		return this;
	}
}
