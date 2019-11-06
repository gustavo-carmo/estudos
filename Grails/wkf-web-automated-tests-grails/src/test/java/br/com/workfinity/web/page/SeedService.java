package br.com.workfinity.web.page;

import org.openqa.selenium.WebDriver;

public class SeedService extends Page {
	final String baseUrl = "http://localhost:8080/osmanager-tenant/seed/"; 

	public SeedService(WebDriver driver) {
		super(driver);
	}

	private void createManufacturer(String name, String status) {
		driver.get(baseUrl + "execute?seed=createGenericManufacturer&name=" + name + "&status=" + status);
	}
}
