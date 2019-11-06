package br.com.workfinity.web.page.rentalReport;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class RentalReportListPage extends PageList {
	
	public RentalReportListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("rentalReport/index", "rentalReport/list");
	}

}
