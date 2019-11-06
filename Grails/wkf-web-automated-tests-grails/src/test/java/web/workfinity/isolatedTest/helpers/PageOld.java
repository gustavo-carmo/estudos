package web.workfinity.isolatedTest.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import careman.html.TestSuiteHelper;

abstract class PageOld {

	TestSuiteHelper testSuiteHelper;

	public PageOld(TestSuiteHelper testSuiteHelper) {
		this.testSuiteHelper = testSuiteHelper;
	}

	public WebDriver getWebDriver() {
		return this.testSuiteHelper.getWebDriver();
	}

	public void validateMessageSuccess(String message) {
		testSuiteHelper.validateMessageSuccess(message);
	}

	public void validateMessageSuccessCreated() {
		testSuiteHelper.validateMessageSuccess("created");
	}

	public void validateMessageSuccessUpdated() {
		testSuiteHelper.validateMessageSuccess("updated");
	}

	public WebDriverWait getWebDriverWait() {
		return new WebDriverWait(getWebDriver(), 10);
	}

	public void checkPresenceOfErrors(String... errors) {
		testSuiteHelper.validateMessagesErrors(errors);
	}
}