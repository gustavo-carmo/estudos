package br.com.workfinity.web.page.importProcess;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ImportProcessGeneric extends Page {

	public ImportProcessGeneric(WebDriver driver) {
		super(driver);
	}

	public ImportProcessGeneric setCity(String city) {
		setFieldById("cidade", city);
		return this;
	}

	public ImportProcessGeneric buttonReprocess() {
		clickByCssSelector("[type='submit']");
		waitPageLoadEnds();
		return this;
	}

	public ImportProcessShowPage buttonBackToDetail() {
		clickByName("_action_show");
		waitPageLoadEnds();
		return new ImportProcessShowPage(driver);
	}

	public ImportProcessGeneric setDataEnvio(String date) {
		setFieldById("dataEnvio", date);
		waitPageLoadEnds();
		return this;
	}

	public ImportProcessGeneric setGenericField(String idElement, String value) {
		setFieldById(idElement, value);
		return this;
	}
	
	
	
}
