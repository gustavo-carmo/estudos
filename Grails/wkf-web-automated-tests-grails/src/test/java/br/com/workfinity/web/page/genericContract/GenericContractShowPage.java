package br.com.workfinity.web.page.genericContract;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class GenericContractShowPage extends Page {

	public GenericContractShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("genericContract/show");
	}

	public GenericContractCrudFormPage buttonEdit() {
		clickByName("_action_edit");
		waitPageLoadEnds();
		return new GenericContractCrudFormPage(driver);
	}

	public GenericContractShowPage validateName(String name) {
		assertEquals(findByCssSelector(".panel-body dd:nth-child(4)").getText(), name, "The value in elemente isn't equals, ");
		return this;
	}

	public GenericContractShowPage validateTemplate(String template) {
		assertEquals(findByCssSelector(".panel-body dd:nth-child(7)").getText(), template, "The text in elemente isn't equals, ");
		return this;
	}

	public String getContractCode(String currentUrl) {
		return currentUrl.substring(currentUrl.length() -1);
	}
}
