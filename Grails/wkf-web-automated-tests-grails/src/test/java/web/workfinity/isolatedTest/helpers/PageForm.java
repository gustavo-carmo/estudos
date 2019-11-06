package web.workfinity.isolatedTest.helpers;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import careman.html.TestSuiteHelper;

public abstract class PageForm extends PageOld {

	public PageForm(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);

		boolean error = false;
		String url = getWebDriver().getCurrentUrl();

		List<String> actions = Arrays.asList("create", "save", "edit", "update");
		for (String action : actions) {

			error = url.contains(action);
			if (error)
				break;
		}

		Assert.assertTrue(error, "Page is not create|save|edit|update, is " + url);
	}

	public void save() {
		getWebDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
	}
}
