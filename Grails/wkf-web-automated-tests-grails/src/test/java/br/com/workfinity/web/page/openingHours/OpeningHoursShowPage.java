
package br.com.workfinity.web.page.openingHours;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class OpeningHoursShowPage extends Page {

	public OpeningHoursShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("openingHours/show");
	}

	public OpeningHoursCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn-primary");
		return new OpeningHoursCrudFormPage(driver);
	}

	public OpeningHoursListPage buttonBackToSearch() {
		clickByCssSelector(".btn.btn.btn-default");
		return new OpeningHoursListPage(driver);
	}
}
