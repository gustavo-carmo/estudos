package br.com.workfinity.web.page.serviceOrder;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.helper.Messages;

public class ServiceOrderCrudForm extends Page {

	public ServiceOrderCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceOrder/create", "serviceOrder/edit", "serviceOrder/update");
	}
	
	public ServiceOrderCrudForm(WebDriver driver, String baseUrl, String id) {
		super(driver);
		visitURL(baseUrl + "/serviceOrder/edit/" + id);
		waitPageLoadEnds();
		validateUrlContains("serviceOrder/edit");
	}

	public ServiceOrderCrudForm setDocument(String type, String number) {
		
		setSelectByIdAndVisibleText("customerDocumentType", type);
		setFieldWithDownArrowAutoCompleteByCssSelector("input#customerDocumentNumber", number, "ui-id-1");
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudForm setServiceGroup(String group) {
		
		waitAjaxEnd();
		setSelectByIdAndVisibleText("group", group);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudForm setService(String service) {
		
		waitAjaxEnd();
		setSelectByIdAndVisibleText("service", service);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudForm buttonCreate() {
		
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public ServiceOrderCrudForm setStatus(String status) {
		
		waitAjaxEnd();
		
		if (findByCssSelector(".breadcrumb.text-2x > li.active").getText().equals("Criar")) {
			setStatusCreate(status);
		} else {
			setStatusUpdate(status);
		}
		return this;
	}

	private void setStatusCreate(String status) {
		
		setSelectByIdAndVisibleText("status", status);
	}

	private void setStatusUpdate(String status) {
		
		clickById("btnStatusSelected");
		clickByLinkText(status);
	}

	public ServiceOrderModalEquipmentFind clickSearchEquipment() {
		
		waitAjaxEnd();
		clickByCssSelector(".fa.fa-search");
		waitAjaxEnd();
		return new ServiceOrderModalEquipmentFind(driver);
	}

	public ServiceOrderCrudForm buttonUpdate() {
		
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public ServiceOrderCrudForm setServiceProvider(String serviceProvider) {
		
		setSelectByIdAndVisibleText("serviceProvider", serviceProvider);
		return this;
	}

	public ServiceOrderCrudForm setContractoExternalId(String id) {
		
		setFieldById("contractorExternalId", id);
		return this;
	}

	public ServiceOrderModalEquipmentFind clickMergeEquipment() {
		
		waitAjaxEnd();
		clickByCssSelector(".btn.btn-default.btn-small.btn-warning");
		waitAjaxEnd();
		return new ServiceOrderModalEquipmentFind(driver);
	}

	public ServiceOrderCrudForm setDefect(String defect) {
		
		setSelectByIdAndVisibleText("defect", defect);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudForm setSolution(String solution) {
		
		setSelectByIdAndVisibleText("solution", solution);
		return this;
	}

	public ServiceOrderCrudForm setReasonForCancellation(String name) {
		
		setSelectByIdAndVisibleText("reasonForCancellation", name);
		return this;
	}

	public String getCode() {
		
		return getTextOfElement("tabServiceOrderId");
	}

	public ServiceOrderModalChangeService clickChangeService() {
		
		clickById("change_service");
		waitAjaxEnd();
		return new ServiceOrderModalChangeService(driver);
	}

	public ServiceOrderCrudForm setClosedDate(String date) {
		
		waitOneTimeInPage(3000);
		setFieldById("closedDate_value", date);
		return this;
	}

	public ServiceOrderCrudForm setOpeningData(String date) {
		
		setFieldById("openingDate_value", date);
		return this;
	}

	public ServiceOrderCrudForm validateIfContainsTextInDeadLine(String text) {
		
		validateIfContainsValueInElement(findById("deadLine_value"), text);
		return this;
	}

	public ServiceOrderCrudForm validateIfContainsTextInServiceProviderDeadLine(String text) {
		
		validateIfContainsValueInElement(findById("deadLineServiceProvider_value"), text);
		return this;
	}

	public ServiceOrderCrudForm validateElementDisable(String idElement) {
		
		validateIfElementIsDisable(idElement);
		return this;
	}

	public void validateContainsTextInEquipment(String textExpected) {
		
		validateIfContainsTextInElement(findById("equipment"), textExpected);
	}

	public ServiceOrderCrudForm campoObservacoesEstaHabilitado() {
		
		validateIfElementIsEnabled("notes", "ERROR - O campo observações esta desabilitado");
		return this;
	}

	public ServiceOrderCrudForm campoObservacoesEstaDesabilitado() {
		
		validateIfElementIsDisable("notes", "ERROR - O campo observações esta habilitado");
		return this;
	}

	public ServiceOrderCrudForm setNotes(String notes) {
		
		setFieldById("notes", notes);
		return this;
	}

	public void validaSeOcampoTemOTextoEsperado(String idElemento, String textoEsperado) {
		
		validateIfContainsTextInElement(findById(idElemento), textoEsperado);
	}

	public ServiceOrderCrudForm botaoAdicionaComentarioEstaDesabilitado() {
		
		clickById("showComments");
		waitAjaxEnd();
		
		validateIfElementIsDisable("ServiceOrder_addComment", "ERROR - O botão que adiciona comentarios está habilitado");

		return this;
	}

	public ServiceOrderCrudForm botaoAdicionaComentarioEstaHabilitado() {
		
		clickById("showComments");
		waitAjaxEnd();
		
		validateIfElementIsEnabled("ServiceOrder_addComment", "ERROR - O botão que adiciona comentarios está desabilitado");

		return this;
	}

	public ServiceOrderCrudFormCommentModel buttonAddComment() {
		
		clickById("showComments");
		waitAjaxEnd();
		
		clickById("ServiceOrder_addComment");
		waitAjaxEnd();

		return new ServiceOrderCrudFormCommentModel(driver);
	}

	public void verificaSeOComentarioFoiCriado(String comentario) {
		
		assertEquals(findTableReturnRowAndColumn(returnOneRowInTableFindByText(Arrays.asList(comentario)), 3).getText(), comentario, "");
	}

	public ServiceOrderCrudForm setModel(String model) {
		
		setFieldByCssSelector("div.input-group-btn.ui-combobox input.form-control.ui-autocomplete-input", model);
		
		waitAjaxEnd();
		
		clickByCssSelector(".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content.ui-corner-all li[role='presentation']:nth-child(2)");
		
		return this;
	}

	public ServiceOrderCrudForm validateEquipment(String textExpected) {
		
		validateIfContainsTextInElement(findByCssSelector("div#equipment a"), textExpected);
		return this;
	}

	public void validateCustomerNotification(String title, String message) {
		
		validateIfContainsTextInElement(findOneElementOfOneListElements("div#floating-top-right div div[role='alert']", title), message);
	}

	public ServiceOrderCrudForm buttonConsumption() {
		
		clickById("showConsumption");
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel buttonAddConsumption() {
		
		clickByCssSelector("#consumptionsTab .btn.btn-default.btn-small");
		waitAjaxEnd();
		return new ServiceOrderCrudFormConsumptionModel(driver);
	}

	public ServiceOrderCrudForm removeConsumption(List<String> textsExpectedInRow) {
		
		WebElement columnTable = findTableReturnRowAndColumn(findByCssSelector("div.tab-content div#consumptionsTab"),
				returnOneRowInTableFindByTable(textsExpectedInRow, findByCssSelector("div.tab-content div#consumptionsTab table")), 1);
		
		findByCssSelector(columnTable, "a").click();
		acceptMessageAlert();
		
		waitAjaxEnd();
		
		validateAlertTitleAndMessage(Messages.SUCCESS.toString());
		return this;
	}

	public ServiceOrderCrudForm buttonRemoved() {
		
		clickById("showRemoved");
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudForm validateIfRemovedPartsTableNotContains(List<String> textNotBeFindInRowTable) {
		
		assertTrue(returnOneRowInTableFindByTable(textNotBeFindInRowTable, findByCssSelector("#removedTab table")) < 1,"");
		return this;
	}

	public ServiceOrderCrudForm setServiceProviderTechnician(String technician) {
		
		setSelectByIdAndVisibleText("serviceProviderTechnician", technician);
		waitAjaxEnd();
		return this;
	}

	public String getId() {
		
		return getOneElementAtUrl();
	}

	public ServiceOrderCrudForm tabHistory() {
		
		clickById("showHistory");
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudForm validateErrorMessage(String... errorMessage) {
		
		validateMessagesErrors(errorMessage);
		waitPageLoadEnds();
		return this;
		
	}

	public ServiceOrderCrudForm buttonRemovedNewEquipment() {

		clickByCssSelector("#newEquipment .fa.fa-remove");
		acceptMessageAlert();
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudForm buttonRemovedEquipment() {
		
		clickByCssSelector("#equipment .fa.fa-remove");
		acceptMessageAlert();
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderModalEquipmentFind clickSearchNewEquipment() {
		
		waitAjaxEnd(5000);
		clickByCssSelector("#newEquipment .fa.fa-search");
		waitAjaxEnd();
		return new ServiceOrderModalEquipmentFind(driver);
	}

	public ServiceOrderCrudForm setTrackingNumber(String trackingNumber) {
		
		setFieldById("trackingNumber", trackingNumber);
		return this;
	}

	public ServiceOrderCrudForm tabAttachment() {

		clickById("showAttachments");
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderTabAttachmentsUpload buttonAttachmentUpload() {

		clickByCssSelector("#attachmentsTab .btn.btn-default");
		waitAjaxEnd();
		return new ServiceOrderTabAttachmentsUpload(driver);
	}

	public ServiceOrderCrudForm setInformedDefect(String informedDefect) {

		setSelectByIdAndVisibleText("informedDefect", informedDefect);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderModalEquipmentFind clickMergeNewEquipment() {
		
		waitAjaxEnd();
		clickByCssSelector("#newEquipment .btn.btn-default.btn-small.btn-warning");
		waitAjaxEnd();
		return new ServiceOrderModalEquipmentFind(driver);
	}

	public String clickMergeEquipmentFail() {
		
		waitAjaxEnd();
		clickByCssSelector("#equipment .btn.btn-default.btn-small.btn-warning");
		return getMessageAlert();
	}

	public String clickMergeNewEquipmentFail() {

		waitAjaxEnd();
		clickByCssSelector("#newEquipment .btn.btn-default.btn-small.btn-warning");
		return getMessageAlert();
	}

	public String getHistoryTotal() {

		return getElement(".badge.total-count");
	}

	public ServiceOrderCrudForm tabOS() {

		clickById("tabServiceOrderId");
		waitAjaxEnd();
		return this;
	}
}
