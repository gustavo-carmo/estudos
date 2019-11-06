package br.com.workfinity.web.page.reasonForCancellation;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ReasonForCancellationListPage extends PageList {

	public ReasonForCancellationListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("reasonForCancellation/index", "reasonForCancellation/list");
	}

	public ReasonForCancellationCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new ReasonForCancellationCrudFormPage(driver);	
	}

	public ReasonForCancellationListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ReasonForCancellationListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ReasonForCancellationCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ReasonForCancellationCrudFormPage(driver);
	}

	public ReasonForCancellationListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}
}
