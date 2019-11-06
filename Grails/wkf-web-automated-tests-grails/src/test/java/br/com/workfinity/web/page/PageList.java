package br.com.workfinity.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public abstract class PageList extends Page {

	public PageList(WebDriver driver) {
		super(driver);
	}

	public void buttonSearch() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
	}

	public PageList filterExpand() {
		try {
			clickByCssSelector(".accordionFilters.collapsed");
			isElementPresent(".panel-collapse.collapse.in", "Filters is not expanded");
		} catch (Exception e) {
			Assert.fail("You tried to expand an already open field");
		}
		return this;
	}

	public void validateTotalCount(String total) {
		
		waitPageLoadEnds();

//		String totalFound = driver.findElement(By.id("resultListTotal")).getAttribute("value");
		String totalFound = driver.findElement(By.cssSelector(".badge.total-count")).getText();
		
		assertEquals(total, totalFound, "Os valores encontrados no Total Count são diferentes");
//		Assert.assertEquals(total, totalFound);
	}
}
