package br.com.workfinity.web.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TableUtils {
	
	public static boolean isElementPresent(WebElement element, By by) {
		try {
			element.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public enum OPTIONS {
		
		EMPTY(""), SHOW("fa-eye"), EDIT("fa-edit"), DELETE("fa-remove"), CHECKBOX("");
		
		String clazz;
			

		OPTIONS(String value) {
			this.clazz = value;
		}
		
		public String getCssClass() {
			return clazz;
		}		
	}

	
	public static void validateHeader(WebElement tableElement, Object... args) {
		validateHeader(tableElement, "th", args);
	}

	public static void validateHeader(WebElement tableElement, String columnElement, Object... args) {
		
		List<WebElement> trs = tableElement.findElements(By.tagName("tr"));
		
		List<WebElement> columns = trs.get(0).findElements(By.tagName(columnElement));
		
		Assert.assertTrue(columns.size() != 0 && args.length != 0, "Columns and Args are not equals void");
		Assert.assertTrue(columns.size() == args.length, "Columns size are not equals the expected size");
		
		for (int i = 0; i < args.length; i++) {
			
			Object param = args[i];
			WebElement cell = columns.get(i);
			String columnValue = cell.getText().trim();
			
			if(param instanceof OPTIONS) {
				OPTIONS option = (OPTIONS) param;
				if(option == OPTIONS.EMPTY) {
					Assert.assertTrue(columnValue.isEmpty(), "Expected find value '', but found value '" + columnValue + "':");
				} else if(option == OPTIONS.CHECKBOX) {
					Assert.assertTrue(isElementPresent(cell, By.cssSelector("input[type='checkbox']")));
				}
			} else if (param instanceof String) {
				String columnValueExpected = (String) param;
				Assert.assertEquals(columnValue, columnValueExpected, "Expected find value [" + columnValueExpected + "], but found value [" + columnValue + "]:");
			} else {
				throw new UnsupportedOperationException("Can not work with a value different of OPTIONS or String");
			}
		}
	}
	
	public static void validateActions(WebElement tableElement, OPTIONS... args) {
		validateActions(tableElement, 0, args);
	}

	public static void validateActions(WebElement tableElement, Integer columnNumberWithActions, OPTIONS... args) {
		
		List<WebElement> trs = tableElement.findElement(By.cssSelector("tbody")).findElements(By.tagName("tr"));

		WebElement columnWithActions = trs.get(0).findElements(By.tagName("td")).get(columnNumberWithActions);				
		
		for (OPTIONS options : args) {			
			Assert.assertTrue(isElementPresent(columnWithActions, By.className(options.getCssClass())));			
		}
	}

	public static void validateResult(WebElement tableElement, String columnName, String columnValue) {
		
		List<WebElement> trs = tableElement.findElements(By.tagName("tr"));
		
		List<WebElement> columns = trs.get(0).findElements(By.tagName("th"));
		int i;
		for (i = 1; i < columns.size() + 1; i++) {
			String header = columns.get(i - 1).getText();
			if (header.equals(columnName)){
				break;
			}
		}
		
		for (int a = 1; a < trs.size(); a++){
			String columnText = trs.get(a).findElement(By.cssSelector("td:nth-child(" + i + ")")).getText();
			Assert.assertEquals(columnText, columnValue, "Expected find value [" + columnText + "], but found value [" + columnValue + "]");
		}
	}
	
	public static void validateRow(WebElement table, List<String> values, int rowNumber) {
		List<WebElement> trs = table.findElement(By.cssSelector("tbody")).findElements(By.tagName("tr"));
		
		String tr = trs.get(rowNumber).getText();
		
		for (int i = 0; i < values.size(); i++) {
			Assert.assertTrue(tr.contains(values.get(i)), "try find texts [" + StringUtils.myJoin(", ", values) + "] in row[" + rowNumber + "] of table");
		}
	}

	public static void validateRowsNumber(WebElement table, int rowsNumber) {
		List<WebElement> trs = table.findElement(By.cssSelector("tbody")).findElements(By.tagName("tr"));
		
		Assert.assertTrue(rowsNumber == trs.size(), "Expected find " + rowsNumber + " rows in a table, but found " + trs.size() + " rows");
	}
}
