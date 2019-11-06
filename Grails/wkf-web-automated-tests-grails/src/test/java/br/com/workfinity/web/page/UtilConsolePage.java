package br.com.workfinity.web.page;

import org.openqa.selenium.WebDriver;

public class UtilConsolePage extends Page {
	
	public UtilConsolePage(WebDriver driver) {
		super(driver);
		validateUrlContains("util/console");
	}

	private void setCommand(String command) {	
		
		findById("command").sendKeys(command);
	}

	private void buttonSubmit() {
		
		clickByCssSelector("[type='submit']");
	}

	public static synchronized void execute(WebDriver driver, String baseUrl, String command) {
		
		new MainPage(driver).visitUtilConsole(baseUrl);
		
		UtilConsolePage utilConsolePage = new UtilConsolePage(driver);		
		utilConsolePage.setCommand(command);
		utilConsolePage.buttonSubmit();	

		utilConsolePage.isElementPresent("span", "Failure to execute the scripit, expected find the element [span]");
	}
}
