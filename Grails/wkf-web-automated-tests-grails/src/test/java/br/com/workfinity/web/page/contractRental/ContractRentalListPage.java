package br.com.workfinity.web.page.contractRental;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;
import web.utils.HtmlUtils;

public class ContractRentalListPage extends PageList {
	
	public ContractRentalListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("rentalAgreement/index", "rentalAgreement/list");
	}

	public ContractRentalCrudForm buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ContractRentalCrudForm(driver);
	}

	public ContractRentalCrudForm editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		return new ContractRentalCrudForm(driver);
	}

	public ContractRentalListPage setCode(String codigo) {
		findByCssSelector("#ids_bootstrap_tagsinput input").sendKeys(codigo + ",");
		return this;
	}

	public ContractRentalListPage setServiceProvider(String serviceProvider) {
		HtmlUtils.autoCompleteMultiplePluginSelect2(driver, "serviceProviderIdList", serviceProvider);
		return this;
	}

	public ContractRentalListPage setClosingDays(String closingday) {
		findByCssSelector("#closingDays_bootstrap_tagsinput input").sendKeys(closingday + ",");
		return this;
	}

	public ContractRentalListPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ContractRentalListPage cleanFieldCode() {
		clickInElementsFindByCssSelector("#ids_bootstrap_tagsinput span[data-role='remove']");
		return this;
	}

	public ContractRentalListPage cleanFieldServiceProvider() {
		clickInElementsFindByCssSelector(".select2-selection__choice__remove");
		return this;
	}

	public ContractRentalListPage cleanFieldClosingDay() {
		clickInElementsFindByCssSelector("#closingDays_bootstrap_tagsinput span[data-role='remove']");
		return this;
	}

}
