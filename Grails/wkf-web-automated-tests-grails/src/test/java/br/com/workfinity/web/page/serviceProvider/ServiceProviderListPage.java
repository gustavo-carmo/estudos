package br.com.workfinity.web.page.serviceProvider;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ServiceProviderListPage extends PageList {

	public ServiceProviderListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceProvider/index", "serviceProvider/list");
	}

	public ServiceProviderCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new ServiceProviderCrudFormPage(driver);
	}

	public ServiceProviderListPage setAlias(String alias) {
		setFieldById("alias", alias);
		return this;
	}

	public ServiceProviderListPage setDocument(String type, String number) {
		setSelectByIdAndVisibleText("documentTypeSearch", type);
		setFieldById("documentNumberSearch", number);
		return this;
	}

	public ServiceProviderListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ServiceProviderCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ServiceProviderCrudFormPage(driver);
	}

	public ServiceProviderListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}

	public ServiceProviderShowPage showItemTable(int rowNumber) {
		showItemTable(rowNumber, 1);
		waitPageLoadEnds();
		return new ServiceProviderShowPage(driver);
	}

}
