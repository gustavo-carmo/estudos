package br.com.workfinity.web.page.importProcess;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;

public class ImportProcessShowPage extends Page {

	public ImportProcessShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("importProcess/show");
	}

	public ServiceOrderCrudForm clickLinkServiceOrder(String idLink) {
		clickById(idLink);
		waitPageLoadEnds();
		return new ServiceOrderCrudForm(driver);
	}

	public ImportProcessGeneric clickLinkEdit(String idElement) {
		clickById(idElement);
		waitPageLoadEnds();
		return new ImportProcessGeneric(driver);
	}

	public ImportProcessShowPage buttonReprocessRow() {
		clickByCssSelector("button.btn.btn-danger[type='submit']");
		waitPageLoadEnds();
		return this;
	}

	public void buttonShowContent() {
		clickById("show_content");
		waitPageLoadEnds();
	}

	public ImportProcessShowPage validateContainsTextInElement(String idElement, String textExpected) {
		validateIfContainsTextInElement(findById(idElement), textExpected);
		return this;
	}

	public ImportProcessListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new ImportProcessListPage(driver);
	}

	public ImportProcessGeneric buttonEdit(String cssSelector) {
		clickByCssSelector(cssSelector);
		waitPageLoadEnds();
		return new ImportProcessGeneric(driver);
	}

	public ImportProcessShowPage setGenericField(String idElement, String value) {
		setFieldById(idElement, value);
		return this;
	}

	public ImportProcessShowPage buttonReprocess() {
		clickByCssSelector("[type='submit']");
		waitPageLoadEnds();
		return this;
	}

	public void clickLink(String link) {
		clickByLinkText(link);
		waitPageLoadEnds();
	}

	public String getStatus(String id) {
		
		return getTextOfElement(id);
	}

	public String getDetails(String id) {
		
		return getTextOfElement(id);
	}

	public ImportProcessShowPage buttonNextPage() {
		
		clickByCssSelector(".text-center .next .step");
		waitAjaxEnd();
		return this;
	}

}
