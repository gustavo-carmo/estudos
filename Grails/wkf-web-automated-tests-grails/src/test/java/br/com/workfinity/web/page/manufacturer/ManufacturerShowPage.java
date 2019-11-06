package br.com.workfinity.web.page.manufacturer;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ManufacturerShowPage extends Page {

	public ManufacturerShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("manufacturer/show");
	}

	public void buttonEdited() {
		clickByName("_action_edit");
	}

	public ManufacturerListPage buttonBackToSearch() {
		clickByName("_action_index");
		return new ManufacturerListPage(driver);
	}

}
