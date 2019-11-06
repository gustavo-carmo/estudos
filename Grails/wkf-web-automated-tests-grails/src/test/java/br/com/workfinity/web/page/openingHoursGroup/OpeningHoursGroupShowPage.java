package br.com.workfinity.web.page.openingHoursGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class OpeningHoursGroupShowPage extends Page {

	public OpeningHoursGroupShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("openingHoursGroup/show");
	}

	public OpeningHoursGroupCrudFormPage buttonEdit() {
		clickByName("_action_edit");
		return new OpeningHoursGroupCrudFormPage(driver);
	}

	public OpeningHoursGroupListPage buttonBackToSearch() {
		clickByName("_action_index");
		return new OpeningHoursGroupListPage(driver);
	}
}
