package br.com.workfinity.web.page.workPack;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import web.utils.HtmlUtils;

public class WorkPackCrudForm extends Page {
	
	public WorkPackCrudForm(WebDriver driver) {
		
		super(driver);
		validateUrlContains("workPack/create", "workPack/edit");
	}
	
	public WorkPackCrudForm(WebDriver driver, String baseUrl, String id) {
		
		super(driver);
		visitURL(baseUrl + "/workPack/edit/" + id);
		waitPageLoadEnds();
		validateUrlContains("workPack/edit");
	}

	public WorkPackCrudForm buttonCreate() {
		
		clickById("btn-submit");
		waitPageLoadEnds();
		return this;
	}
	
	public WorkPackCrudForm buttonCreateFail() {
		
		clickById("btn-submit");
		waitAjaxEnd();
		return this;
	}

	public WorkPackCrudForm validateMessageError(String... messageError) {
		
		validateMessagesErrors(".media-body .alert-title", messageError);
		waitOneTimeInPage(2000);
		return this;
	}

	public WorkPackCrudForm setServiceProvider(String serviceProvider) {
		
		// TODO - VOLTAR AQUI E REFATORAR
		clickByCssSelector(".select2-selection.select2-selection--single");
		waitOneTimeInPage(2000);
		HtmlUtils.setSelect2Complete(driver, serviceProvider);
		return this;
	}

	public WorkPackCrudForm buttonModalServiceOrder() {
		
		waitOneTimeInPage(2000);
		clickById("btn-open-modal-to-add-service-order");
		return this;
	}

	public WorkPackCrudForm setCode(String code) {
		
		setFieldById("service-order-code", code);
		return this;
	}

	public WorkPackCrudForm buttonAddServiceOrder() {
		
		waitAjaxEnd(2000);
		clickById("btn-add-service-order");
		waitAjaxEnd();
		return this;
	}

	public WorkPackCrudForm buttonModalProductOrPart() {
		
		waitOneTimeInPage(2000);
		clickById("btn-open-modal-to-add-product-part");
		return this;
	}

	public WorkPackCrudForm setSerialNumber(String serialNumber) {
		
		setFieldById("product-part-serial-number", serialNumber);
		return this;
	}

	public WorkPackCrudForm buttonAddProductOrPart() {
		
		waitAjaxEnd(2000);
		clickById("btn-add-product-part");
		waitAjaxEnd();
		return this;
	}

	public WorkPackCrudForm buttonModalAcessoryOrSupply() {
		
		waitOneTimeInPage(2000);
		clickById("btn-open-modal-to-add-accessory-supply");
		return this;
	}

	public WorkPackCrudForm setContractor(String contractor) {
		
		clickById("select2-accessory-supply-contractor-id-container");
		waitAjaxEnd(2000);
		HtmlUtils.setSelect2Complete(driver, contractor);
		return this;
	}

	public WorkPackCrudForm setModel(String modelName) {
		
		clickById("select2-accessory-supply-model-id-container");
		waitAjaxEnd(2000);
		HtmlUtils.setSelect2Complete(driver, modelName);
		return this;
	}

	public WorkPackCrudForm setAmount(String amount) {
		
		setFieldById("accessory-supply-quantity", amount);
		return this;
	}

	public WorkPackCrudForm buttonAddAcessoryOrSupply() {
		
		waitAjaxEnd(2000);
		clickById("btn-add-accessory-supply");
		waitAjaxEnd();
		return this;
	}

	public WorkPackCrudForm checkedCheckboxToDeleteItemInTable() {
		
		setCheckBoxById("equipment-all-checkbox", true);
		return this;
	}

	public WorkPackCrudForm buttonDeleteEquipament() {
		
		waitAjaxEnd();
		clickById("btn-remove-equipment");
		acceptMessageAlert();
		waitAjaxEnd();
		return this;
	}

	public WorkPackCrudForm buttonCloseModalServiceOrderAndProductPart() {
		
		waitOneTimeInPage(2000);
		clickByCssSelector(".modal-dialog.modal-md:nth-child(2) .btn.btn-default");
		waitAjaxEnd(2000);
		return this;
	}

	public WorkPackCrudForm buttonCloseModalAcessorySupply() {
		
		waitOneTimeInPage(2000);
		clickByCssSelector(".modal-dialog.modal-lg:nth-child(2) .btn.btn-default");
		waitAjaxEnd(2000);
		return this;
	}
	
	public WorkPackCrudForm buttonUpdate() {
		
		waitOneTimeInPage(2000);
		clickById("btn-submit");
		waitAjaxEnd();
		return this;
	}
	
	public WorkPackCrudForm buttonUpdateCancelled() {
		
		waitOneTimeInPage(2000);
		clickById("btn-submit");
		acceptMessageAlert();
		waitAjaxEnd();
		return this;
	}

	public WorkPackCrudForm setStatus(String status) {
		
		waitAjaxEnd();
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public WorkPackCrudForm setTechnician(String technician) {
		
		waitAjaxEnd();
		clickByCssSelector(".select2-selection.select2-selection--single");
		waitAjaxEnd(2000);
		HtmlUtils.setSelect2Complete(driver, technician);
		return this;
	}

	public WorkPackCrudForm validateIfDeliveredDateIsComplete(String deliveredDate) {
		
		validateIfContainsValueInElement(findById("delivered-date"), deliveredDate);		
		return this;
	}

	public WorkPackCrudForm validateIfDeliveredByIsComplete(String deliveredBy) {
		
		validateIfContainsValueInElement(findById("delivered-by"), deliveredBy);
		return this;
	}

	public WorkPackCrudForm validateMessageUpdateSucess(String title, String message) {
		
		validateIfContainsTextInElement(findOneElementOfOneListElements("div#floating-top-right div div[role='alert']", title), message);
		waitOneTimeInPage(2000);
		return this;
	}

	public String getCodeWorkPack() {
		
		return findByCssSelector(".nav.nav-tabs .active a").getText();
	}
}
