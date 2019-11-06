package br.com.workfinity.web.page.equipment;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class EquipmentListPage extends PageList {

	public EquipmentListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("equipment/index", "equipment/list");
	}

	public EquipmentCrudFormPage buttonNew() {
		
		clickByCssSelector("button.btn.btn.btn-info");
		waitPageLoadEnds();
		return new EquipmentCrudFormPage(driver);
	}

	public EquipmentCrudFormPage editItemTable(int rowNumber) {
		
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new EquipmentCrudFormPage(driver);
	}

}
