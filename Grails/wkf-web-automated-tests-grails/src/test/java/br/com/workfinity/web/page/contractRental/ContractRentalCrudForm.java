package br.com.workfinity.web.page.contractRental;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ContractRentalCrudForm extends Page {

	public ContractRentalCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("rentalAgreement/create", "rentalAgreement/edit");
	}

	public ContractRentalCrudForm buttonCreate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public ContractRentalCrudForm validateErrorMessage(String... errorMessage) {
		validateMessagesErrors(errorMessage);
		return this;
	}

	public ContractRentalCrudForm setServiceProvider(String serviceProvider) {
		setSelectByIdAndVisibleText("serviceProvider.id", serviceProvider);
		return this;
	}

	public ContractRentalCrudForm setClosingDay(String closingDay) {
		setSelectByIdAndVisibleText("closingDay", closingDay);
		return this;
		
	}

	public ContractRentalListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new ContractRentalListPage(driver);
	}

	public ContractRentalCrudForm buttonModels() {
		clickByCssSelector("ul.nav.nav-tabs li:nth-child(2)");
		waitAjaxEnd();
		return this;
	}

	public ContractRentalModels buttonNewModels() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ContractRentalModels(driver);
		
	}

	public ContractRentalModels editItemInTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ContractRentalModels(driver);
	}
}
