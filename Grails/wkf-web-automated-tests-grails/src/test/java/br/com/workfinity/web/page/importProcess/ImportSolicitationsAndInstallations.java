package br.com.workfinity.web.page.importProcess;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ImportSolicitationsAndInstallations extends Page {

	public ImportSolicitationsAndInstallations(WebDriver driver) {
		super(driver);
		validateUrlContains("importProcessBanrisul/upload");
	}

	public ImportSolicitationsAndInstallations setFile(String file) {
		findById("file").sendKeys(file);
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e);
		}
		return this;
	}

	public ImportProcessListPage buttonImport() {
		clickByName("_action_uploadSave");
		waitPageLoadEnds();
		return new ImportProcessListPage(driver);
	}
	
	
}
