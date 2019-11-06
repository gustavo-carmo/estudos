package web.workfinity.isolatedTest.helpers;

import org.testng.Assert;

import careman.html.TestSuiteHelper;

public abstract class PageShow extends PageOld {

	public PageShow(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);
		Assert.assertTrue(getWebDriver().getCurrentUrl().contains("show"), "Page is not show");
	}
}
