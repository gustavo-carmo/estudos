package br.com.workfinity.web.page.contractor;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ContractorListPage extends PageList {

	public ContractorListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("contractor/index", "contractor/list");
	}

	public ContractorCrudFormPage buttonNew() {
		clickByCssSelector(".btn.btn.btn-info");
		waitPageLoadEnds();
		return new ContractorCrudFormPage(driver);
	}

	public ContractorListPage setAlias(String alias) {
		setFieldById("alias", alias);
		return this;
	}

	public ContractorListPage setDocument(String type, String number) {
		setSelectByIdAndVisibleText("documentType", type);
		setFieldById("documentNumber", number);
		return this;
	}

	public ContractorListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ContractorCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ContractorCrudFormPage(driver);
	}

	public ContractorListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}

	public ContractorShowPage showItemTable(int rowNumber) {
		showItemTable(rowNumber, 1);
		waitPageLoadEnds();
		return new ContractorShowPage(driver);
	}

}
