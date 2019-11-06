package br.com.workfinity.web.page.template;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class TemplateShowPage extends Page {

	public TemplateShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("template/show");
	}
}
