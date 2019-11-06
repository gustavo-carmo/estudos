package br.com.workfinity.web.page.serviceOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ServiceOrderListPage extends PageList {

	public ServiceOrderListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceOrder/index", "serviceOrder/list");
	}

	public ServiceOrderListPage setCodeServiceOrder(String code) {
		setFieldById("code", code);
		return this;
	}

	public ServiceOrderCrudForm buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ServiceOrderCrudForm(driver);
	}

	public ServiceOrderListPage setDocument(String type, String number) {
		setSelectByIdAndVisibleText("customerDocumentType", type);
		setFieldAutoCompleteById("customerDocumentNumber", number);
		return this;
	}
}
