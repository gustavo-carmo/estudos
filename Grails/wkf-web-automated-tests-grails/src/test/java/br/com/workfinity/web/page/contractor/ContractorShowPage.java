package br.com.workfinity.web.page.contractor;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.contractor.contract.ContractCrudForm;

public class ContractorShowPage extends Page {

	public ContractorShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("contractor/show");
	}

	public ContractorCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new ContractorCrudFormPage(driver);
	}

	public ContractorListPage buttonBackToSearch() {
		clickByCssSelector(".btn.btn.btn-default");
		waitPageLoadEnds();
		return new ContractorListPage(driver);
	}

	public ContractCrudForm editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ContractCrudForm(driver);
	}

	public ContractCrudForm createContract() {
		clickByName("_action_addContract");
		waitPageLoadEnds();
		return new ContractCrudForm(driver);
	}

	public String getContractId() {
		return findTableReturnRowAndColumn(1, 2).getText();
	}
}
