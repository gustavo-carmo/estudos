package br.com.workfinity.web.page.contractor.contract.service;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.contractor.contract.ContractCrudForm;

public class ServiceContractCrudFormPage extends Page {

	public ServiceContractCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("service/create", "service/edit");
	}

	public ServiceContractCrudFormPage setServiceGroup(String value) {
		setSelectByIdAndVisibleText("service_group_id", value);
		waitAjaxEnd();
		return this;
	}

	public ServiceContractCrudFormPage setWorkflow(String value) {
		setSelectByIdAndVisibleText("workflow_id", value);
		return this;
	}

	public ServiceContractCrudFormPage setName(String service) {
		setFieldById("name", service);
		return this;
	}

	public ServiceContractCrudFormPage setTemplate(String template) {
		setSelectByIdAndVisibleText("templateServiceOrderPrint.id", template);
		return this;
	}

	public ServiceContractCrudFormPage validateAllTemplates(List<String> texts) {
		validateTextsInDropBox(texts, "templateServiceOrderPrint.id");
		return this;
	}

	public ContractCrudForm buttonCreate() {
		clickByCssSelector(".panel-footer.text-left [type='submit']");
		waitPageLoadEnds();
		return new ContractCrudForm(driver);
	}

	public ServiceContractCrudFormPage setOpeningHoursGroup(String name) {
		setSelectByIdAndVisibleText("openingHoursGroup.id", name);
		return this;
	}

	public ServiceContractCrudFormPage validateTemplate(String value) {
		validateIfContainsTextInElement(findById("templateServiceOrderPrint.id"), value);
		return this;
	}

	public ContractCrudForm buttonBackToDetail() {
		clickByName("_action_edit");
		waitPageLoadEnds();
		return new ContractCrudForm(driver);
	}

	public ContractCrudForm buttonUpdate() {
		clickByCssSelector(".panel-footer.text-left [type='submit']");
		waitPageLoadEnds();
		return new ContractCrudForm(driver);
	}

	public ServiceContractCrudFormPage validateIfElementsOfTypeFieldIsNotVisible() {
		validateIfElementIsNotDisplayed("allowsCreateEquipmentOnServiceOrder");
		validateIfElementIsNotDisplayed("allowsCreateNewEquipmentOnServiceOrder");
		validateIfElementIsNotDisplayed("onlyEquipmentFromServiceProviderStockOnServiceOrder");
		validateIfElementIsNotDisplayed("onlyNewEquipmentFromServiceProviderStockOnServiceOrder");
		validateIfElementIsNotDisplayed("onlyEquipmentFromCustomerAtServiceOrder");
		validateIfElementIsNotDisplayed("onlyNewEquipmentFromCustomerAtServiceOrder");
		validateIfElementIsNotDisplayed("allowsEquipmentWithOnlyModelOnServiceOrder");
		validateIfElementIsNotDisplayed("generateConsumptionInStock");
		validateIfElementIsNotDisplayed("templateServiceOrderPrint.id");
		validateIfElementIsNotDisplayed("migrateWarrantiesFromEquipmentToNewEquipmentKeepCustomer");
		validateIfElementIsNotDisplayed("controlRecurrence");
		
		return this;
	}

	public ServiceContractCrudFormPage validateIfElementsOfTypeFieldIsVisible() {
		validateIfElementIsDisplayedFindById("allowsCreateEquipmentOnServiceOrder");
		validateIfElementIsDisplayedFindById("allowsCreateNewEquipmentOnServiceOrder");
		validateIfElementIsDisplayedFindById("onlyEquipmentFromServiceProviderStockOnServiceOrder");
		validateIfElementIsDisplayedFindById("onlyNewEquipmentFromServiceProviderStockOnServiceOrder");
		validateIfElementIsDisplayedFindById("onlyEquipmentFromCustomerAtServiceOrder");
		validateIfElementIsDisplayedFindById("onlyNewEquipmentFromCustomerAtServiceOrder");
		validateIfElementIsDisplayedFindById("allowsEquipmentWithOnlyModelOnServiceOrder");
		validateIfElementIsDisplayedFindById("generateConsumptionInStock");
		validateIfElementIsDisplayedFindById("templateServiceOrderPrint.id");
		validateIfElementIsDisplayedFindById("migrateWarrantiesFromEquipmentToNewEquipmentKeepCustomer");
		validateIfElementIsDisplayedFindById("controlRecurrence");
		
		return this;
	}

	public ServiceContractCrudFormPage clickCheckBoxServiceOrderAllowCreateEquipment(boolean value) {

		setCheckBoxById("allowsCreateEquipmentOnServiceOrder", value);
		return this;
	}

	public ServiceContractCrudFormPage clickCheckBoxServiceOrderAllowCreateNewEquipment(boolean value) {

		setCheckBoxById("allowsCreateNewEquipmentOnServiceOrder", value);
		return this;
	}

	public ServiceContractCrudFormPage clickCheckBoxServiceOrderOnlyEquipmentFromCustomer(boolean value) {

		setCheckBoxById("onlyEquipmentFromCustomerAtServiceOrder", value);
		return this;
	}

	public ServiceContractCrudFormPage clickCheckBoxServiceOrderOnlyNewEquipmentFromCustomer(boolean value) {

		setCheckBoxById("onlyNewEquipmentFromCustomerAtServiceOrder", value);
		return this;
	}

	public ServiceContractCrudFormPage clickCheckBoxServiceOrderOnlyEquipmentFromServiceProvider(boolean value) {
		
		setCheckBoxById("onlyEquipmentFromServiceProviderStockOnServiceOrder", value);
		return this;
	}

	public ServiceContractCrudFormPage clickCheckBoxServiceOrderOnlyNewEquipmentFromServiceProvider(boolean value) {

		setCheckBoxById("onlyNewEquipmentFromServiceProviderStockOnServiceOrder", value);
		return this;
	}
}
