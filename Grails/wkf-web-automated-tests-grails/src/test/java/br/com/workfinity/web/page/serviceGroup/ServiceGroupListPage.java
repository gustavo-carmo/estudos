package br.com.workfinity.web.page.serviceGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ServiceGroupListPage extends PageList {

	public ServiceGroupListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceGroup/list", "serviceGroup/index");
	}

	public ServiceGroupCrudFormPage buttonNew() {
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new ServiceGroupCrudFormPage(driver);
	}

	public ServiceGroupListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ServiceGroupListPage setType(String type) {
		setSelectByIdAndVisibleText("type", type);
		return this;
	}

	public ServiceGroupListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ServiceGroupCrudFormPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ServiceGroupCrudFormPage(driver);
	}

	public ServiceGroupListPage deleteItemTable(int rowNumber) {
		deleteItemTableByPosition(rowNumber, 1);
		return this;
	}

}
