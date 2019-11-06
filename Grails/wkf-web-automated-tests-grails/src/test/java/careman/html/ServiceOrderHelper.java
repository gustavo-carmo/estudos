package careman.html;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ServiceOrderHelper {

	public static void createServiceOrder(WebDriver driver, String baseURL, String documentNumber, String serviceGroup, String service) {

		driver.findElement(By.cssSelector("img.wkf-brand")).click();
		(new WebDriverWait(driver, 60)).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='mainnav-menu']/li[2]/a/span/strong"))).click();

		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("customerDocumentNumber"))).clear();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("customerDocumentNumber"))).sendKeys(
				documentNumber);
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ul/li/a"))).click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("customerDocumentNumber"))).getText()
				.contains(documentNumber);

		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("group"))).isEnabled();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("group")));
		new Select(driver.findElement(By.id("group"))).selectByVisibleText(serviceGroup);
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("group"))).isSelected();

		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("service"))).isEnabled();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("service")));
		new Select(driver.findElement(By.id("service"))).selectByVisibleText(service);
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(By.id("service"))).isSelected();
	}

}
