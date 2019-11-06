package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.stock.StockControlListPage;

public class ShipmentOrderCrudForm extends Page {

	public ShipmentOrderCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrder/create", "shipmentOrder/edit");
	}

	public ShipmentOrderCrudForm setTo(String to) {
		setFieldWithDownArrowAutoCompleteByCssSelector("#shipmentTo_label", to, "ui-id-2");
		waitAjaxEnd();
		return this;
	}
	
	public ShipmentOrderCrudForm setFrom(String from) {
		setFieldWithDownArrowAutoCompleteByCssSelector("#shipmentFrom_label", from, "ui-id-1");
		waitAjaxEnd();
		return this;
	}

	public ShipmentOrderCrudForm setShipmentOrderType(String type) {
		setSelectByIdAndVisibleText("shipmentOrderType", type);
		waitAjaxEnd();
		return this;
	}

	public ShipmentOrderCrudForm setStatus(String status) {
		if (findByCssSelector(".breadcrumb.text-2x > li.active").getText().equals("Criar")) {
			return setStatusCreate(status);
		} else {
			return setStatusUpdate(status);
		}
	}

	private ShipmentOrderCrudForm setStatusUpdate(String status) {
		clickById("btnStatusSelected");
		clickByLinkText(status);
		waitAjaxEnd();
		return this;
	}

	private ShipmentOrderCrudForm setStatusCreate(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ShipmentOrderCrudForm buttonCreate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public ShipmentOrderCrudForm buttonUpdate() {
		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public ShipmentOrderCrudForm validateGenericMessageError(String... errorsMessage) {
		validateMessagesErrors(errorsMessage);
		return this;

	}

	public ShipmentOrderCrudForm setInvoiceNumber(String invoiceNumber) {
		setFieldById("invoiceNumber", invoiceNumber);
		return this;
	}

	public ShipmentOrderCrudForm setInvoiceDate(String date) {
		setFieldById("invoiceDate_value", date);
		return this;
	}

	public ShipmentOrderCrudForm setEquipmentModel(String model) {
		setSelectByIdAndVisibleText("newEquipmentModel", model);
		waitAjaxEnd();
		return this;
	}

	public ShipmentOrderCrudForm setEquipmentSerialNumber(String serialNumber) {
		setFieldById("equipment_serial_number", serialNumber);
		return this;
	}

	public ShipmentOrderCrudForm setEquipmentContractor(String contractor) {
		setSelectByIdAndVisibleText("contractor", contractor);
		return this;
	}

	public ShipmentOrderCrudForm buttonAddEquipment() {
		clickByCssSelector(".btn.btn-default .fa.fa-plus-circle");
//		clickById("addNewEquipment");
		waitAjaxEnd();
		return this;
	}

	public String getSerialNumber() {
		return getValueOfElement("equipment_serial_number");
	}

	public ShipmentOrderCrudForm setEquipmentQuantity(String quantity) {
		setFieldById("equipment_quantity", quantity);
		return this;
	}

	public ShipmentOrderCrudForm setCarrier(String carrier) {
		setSelectByIdAndVisibleText("carrier.id", carrier);
		return this;
	}

	public ShipmentOrderCrudForm setVolumesNumber(String volumesNumber) {
		setFieldById("volumesNumber", volumesNumber);
		return this;
	}

	public ShipmentOrderCrudForm setOutBoundDate(String date) {
		setFieldById("outboundDate_value", date);
		return this;
	}

	public String getCode() {
		return findByCssSelector("li.active a[aria-expanded='true']").getText();
	}

	public StockControlListPage buttonStockControl() {
		clickByLinkText("Controle de Estoque");
		return new StockControlListPage(driver);
	}

	public ShipmentOrderAddSerializedEquipment buttonAddSerializedEquipByBarCode() {

		clickByCssSelector("#equipment_modal_form_container .btn.btn.btn-default:nth-child(3)");
		waitPageLoadEnds();
		return new ShipmentOrderAddSerializedEquipment(driver);
	}

	public ShipmentOrderAddQuantitativeEquipment buttonAddQuantityEquip() {

		clickByCssSelector(".btn.btn.btn-default .fa.fa-wrench");
		waitPageLoadEnds();
		return new ShipmentOrderAddQuantitativeEquipment(driver);
	}

	public ShipmentOrderCrudForm setAllEquipmentCheckBoxToDelete(boolean value) {

		setCheckBoxById("stockEntryIdSelectAll", value);
		return this;
	}

	public ShipmentOrderCrudForm clickDeleteEquipments() {

		clickById("equipmentRemove");
		acceptMessageAlert();
		waitPageLoadEnds();
		return this;
	}

	public ShipmentOrderCrudForm validateMessageError(String... errorsRequired) {

		validateMessagesErrors("#shipmentOrderTab .media-body .alert-title", errorsRequired);
		return this;
	}

	public ShipmentOrderConferedEquipment buttonConferedEquipment() {

		clickByCssSelector(".btn.btn.btn-default .fa.fa-check-circle");
		waitPageLoadEnds();
		return new ShipmentOrderConferedEquipment(driver);
	}

	public ShipmentOrderEquipmentPromisse equipmentPromisseAba() {
		
		clickById("showPromiseOfEquipmentTab");
		waitOneTimeInPage(1000);
		return new ShipmentOrderEquipmentPromisse(driver);
	}

	public ShipmentOrderAddSerializedEquipmentByLote buttonAddSerializedEquipByLot() {
		
		clickByCssSelector("#equipment_modal_form_container .btn.btn.btn-default:nth-child(5)");
		waitPageLoadEnds();
		
		return new ShipmentOrderAddSerializedEquipmentByLote(driver);
	}
}
