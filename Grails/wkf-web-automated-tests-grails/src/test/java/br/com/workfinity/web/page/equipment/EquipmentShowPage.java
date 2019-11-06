package br.com.workfinity.web.page.equipment;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class EquipmentShowPage extends Page {

	public EquipmentShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("equipment/show");
	}

	public EquipmentCrudFormPage buttonEdit() {
		
		clickByName("_action_edit");
		waitPageLoadEnds();
		return new EquipmentCrudFormPage(driver);
	}

	public EquipmentListPage buttonDelete() {
		
		clickByName("_action_delete");
		acceptMessageAlert();
		waitPageLoadEnds();
		return new EquipmentListPage(driver);
	}

	public String getCode() {
		
		return getTextOfElement("Nta6S8twBgH");
	}

	public String getInventoriedText() {
		
		return getElement("#equipment_form .dl-horizontal:nth-child(1) dd:nth-child(2)");
	}
	
	public String getTemporaryText() {
		
		return getElement("#equipment_form .dl-horizontal:nth-child(1) dd:nth-child(36)");
	}
}
