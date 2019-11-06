package br.com.workfinity.web.page.contractRental;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ContractRentalModels extends Page {
	
	public ContractRentalModels(WebDriver driver) {
		super(driver);
		validateUrlContains("rentalAgreementItem/create", "rentalAgreementItem/edit");
	}

	public ContractRentalModels buttonCreateFail() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public ContractRentalModels validateErrorMessage(String... errorMessage) {
		validateMessagesErrors(errorMessage);
		return this;
	}

	public ContractRentalModels setModel(String nameModel) {
		setSelectByIdAndVisibleText("model.id", nameModel);
		return this;
	}

	public ContractRentalModels setValue(String value) {
		setFieldById("rentalCost", value);
		return this;
	}

	public ContractRentalCrudForm buttonCreateSuccess() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ContractRentalCrudForm(driver);
	}

	public ContractRentalCrudForm buttonUpdateSuccess() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ContractRentalCrudForm(driver);		
	}
	

}
