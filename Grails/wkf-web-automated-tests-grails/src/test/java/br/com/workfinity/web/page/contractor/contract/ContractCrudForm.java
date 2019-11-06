package br.com.workfinity.web.page.contractor.contract;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.contractor.ContractorShowPage;
import br.com.workfinity.web.page.contractor.contract.service.ServiceContractCrudFormPage;

public class ContractCrudForm extends Page {

	public ContractCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("contract/edit");
	}

	public ContractCrudForm setOpeningHoursGroup(int rowNumber) {
		findById(findTableReturnRowAndColumn("openingHoursDataTable", rowNumber, 1), "openingHoursGroup").click();
		return this;
	}

	public ContractCrudForm buttonUpdate() {
		clickByCssSelector("button[type='submit']");
		waitPageLoadEnds();
		return this;
	}

	public ContractCrudForm validateAllTemplates(List<String> texts) {
		validateTextsInDropBox(texts, "templateServiceOrderPrint.id");
		return this;
	}

	public ContractCrudForm setTemplate(String template) {
		setSelectByIdAndVisibleText("templateServiceOrderPrint.id", template);
		return this;
	}

	public ContractorShowPage buttonBackTo() {
		clickByName("_action_show");
		waitPageLoadEnds();
		return new ContractorShowPage(driver);
	}

	public ContractCrudForm validateTemplate(String template) {
		validateIfContainsTextInElement(findById("templateServiceOrderPrint.id"), template);
		return this;
	}

	public ServiceContractCrudFormPage buttonNewService() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ServiceContractCrudFormPage(driver);
	}

	public ServiceContractCrudFormPage editService(int rowNumber) {
		editItemTableByPosition("servicesDataTable", rowNumber, 1);
		waitPageLoadEnds();
		return new ServiceContractCrudFormPage(driver);
	}

	public ContractCrudForm deleteService(int rowNumber) {
		deleteItemTableByPosition("servicesDataTable", rowNumber, 1);
		return this;
	}

	public int getRowNumber(List<String> textReturn) {
		return returnOneRowInTableFindByText(textReturn, "servicesDataTable");
	}
}
