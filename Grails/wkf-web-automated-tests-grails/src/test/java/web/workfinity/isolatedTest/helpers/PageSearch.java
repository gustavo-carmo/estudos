package web.workfinity.isolatedTest.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import careman.html.TestSuiteHelper;

public abstract class PageSearch extends PageOld {

	public PageSearch(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);
	}

	public void checkTotalCount(String total) {
		getWebDriverWait().until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.badge.total-count"), total));
	}

	public void execute() {
		WebElement btSearch = getWebDriver().findElement(By.xpath("//button[@value='Search']"));

		if (!btSearch.isDisplayed()) {
			new WebDriverWait(this.getWebDriver(), 10).until(ExpectedConditions.elementToBeClickable(By.linkText("Filters"))).click();
		}

		btSearch.click();
	}

	public void edit(Integer rowNumber) {
		getWebDriver().findElement(By.xpath("//table[@id='resultList']/tbody/tr[" + rowNumber + "]/td/a[2]")).click();
	}

	public void show(Integer rowNumber) {
		getWebDriver().findElement(By.xpath("//table[@id='resultList']/tbody/tr[" + rowNumber + "]/td/a")).click();
	}

	public void delete(Integer rowNumber) {
		getWebDriver().findElement(By.xpath("//table[@id='resultList']/tbody/tr[" + rowNumber + "]/td/a[3]")).click();
		Assert.assertTrue(testSuiteHelper.closeAlertAndGetItsText(getWebDriver()).matches("^Are you sure[\\s\\S]$"));
		validateMessageSuccess("deleted");
	}

	public Map<String, Object> resultFindRow(List<String> values) {
		return this.searchInResult("resultList", values);
	}

	@SuppressWarnings("serial")
	public Map<String, Object> searchInResult(final String tableId, List<String> values) {

		if (null == values || null == tableId) {
			return null;
		}

		StringBuilder xpath = new StringBuilder("//table[@id='" + tableId + "']/tbody/tr[");

		String separator = "";
		for (String v : values) {
			xpath.append(separator);
			separator = " and ";
			xpath.append("td[text()='td-content']".replaceAll("td-content", v));
		}
		xpath.append("]");

		try {

			final WebElement rowFound = getWebDriver().findElement(By.xpath(xpath.toString()));

			return new HashMap<String, Object>() {
				{
					put("rowNumber", getWebDriver().findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr")).indexOf(rowFound) + 1);
					put("webElement", rowFound);
				}
			};

		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void resultValidateColumnValue(List<String> values) {
		this.resultValidateColumnValue("resultList", values);
	}

	public void resultValidateColumnValue(String tableId, List<String> values) {

		if (null != values) {

			WebElement table = getWebDriver().findElement(By.xpath("//table[@id='" + tableId + "']"));
			getWebDriverWait().until(ExpectedConditions.elementToBeClickable(table));

			List<WebElement> trs = table.findElements(By.xpath("//tbody/tr"));

			for (WebElement tr : trs) {

				List<WebElement> tds = tr.findElements(By.tagName("td"));

				for (int i = 0; i < values.size(); i++) {
					Assert.assertEquals(tds.get(i + 1).getText(), values.get(i));
				}
			}

		}
	}
}
