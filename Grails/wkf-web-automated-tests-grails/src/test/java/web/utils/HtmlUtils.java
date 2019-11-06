package web.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlUtils {
	
	static final Logger LOG = LoggerFactory.getLogger(HtmlUtils.class);

	public static void autoCompleteMultiplePluginSelect2(WebDriver driver, String id, String value) {
		
		WebElement myElement = driver.findElement(By.id(id));
		WebElement parent = myElement.findElement(By.xpath(".."));
		
		WebElement findByClassName = findByClassName(driver, parent, "select2-search__field");
		findByClassName.sendKeys(value);
		findByClassName.sendKeys(Keys.ENTER);
	}
	
	public static void setSelect2Complete(WebDriver driver, String value){
		
		setSelect2Complete(driver, value, 1);
	}
	
	public static void setSelect2Complete(WebDriver driver, String value, int elementPosition){
		
		WebElement element = findByClassName(driver, "select2-search__field");
		element.sendKeys(value);

		for (int i = 0; i < elementPosition; i++) {
			element.sendKeys(Keys.ARROW_DOWN);
		}

		element.sendKeys(Keys.ENTER);
	}	
	
	public static WebElement waitVisibilitOfElement(WebDriver driver, WebElement webElement) {
		
		LOG.debug("Espera o elemento " + webElement + " estar visível");
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		return wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public static WebElement findByClassName(WebDriver driver, String className) {
		
		return waitVisibilitOfElement(driver, driver.findElement(By.className(className)));
	}
	
	public static WebElement findByClassName(WebDriver driver, WebElement element, String className) {
		
		return waitVisibilitOfElement(driver, element.findElement(By.className(className)));
	}
	
	public static void waitOneTimeInPage(WebDriver driver, long milliseconds) {
		
		LOG.debug("Aguardando um tempo de " + milliseconds + " milissegundos na página");
		
		try {
			Thread.sleep(milliseconds);
			
			LOG.debug("Fim da espera de " + milliseconds + " milissegundos na página");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
