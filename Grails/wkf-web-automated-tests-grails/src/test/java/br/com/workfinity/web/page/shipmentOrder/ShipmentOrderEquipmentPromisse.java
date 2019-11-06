package br.com.workfinity.web.page.shipmentOrder;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ShipmentOrderEquipmentPromisse extends Page {

	public ShipmentOrderEquipmentPromisse(WebDriver driver) {
		super(driver);
		validateUrlContains("shipmentOrder/create", "shipmentOrder/edit");
	}

	public ShipmentOrderEquipmentPromisse buttonAddPromisse() {

		clickByCssSelector(".fa.fa-file-o.fa-fw");
		waitOneTimeInPage(5000);
		return this;
	}

	public ShipmentOrderEquipmentPromisse setEquipmentType(String equipmentType) {

		setSelectByCssSelectorAndVisibleText(".el-input__inner:nth-child(2)", equipmentType);
		
		return this;
	}

	public ShipmentOrderEquipmentPromisse setQuantity(String quantity) {

		setFieldByCssSelector(".el-form-item__content .el-input .el-input__inner:nth-child(1)", quantity);
		
		return this;
	}

	public ShipmentOrderEquipmentPromisse buttonSave() {

		clickByCssSelector(".el-button.el-button--primary.el-button--small .fa.fa-check.fa-fw");
		waitOneTimeInPage(1000);
		return this;
	}

	public ShipmentOrderCrudForm backToShipmentOrder() {

		clickByCssSelector(".nav.nav-tabs li:nth-child(1)");
		waitOneTimeInPage(1000);
		return new ShipmentOrderCrudForm(driver);
	}

	public ShipmentOrderEquipmentPromisse setSelectFirstEquipmentType() {

		clickByCssSelector(".el-scrollbar__view.el-select-dropdown__list .el-select-dropdown__item:nth-child(1)");
		return this;
	}
}
