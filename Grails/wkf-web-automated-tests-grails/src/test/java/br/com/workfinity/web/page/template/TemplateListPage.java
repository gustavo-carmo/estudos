package br.com.workfinity.web.page.template;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class TemplateListPage extends PageList {

	public TemplateListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("template/index", "template/list");
	}

	public TemplateCrudPage buttonNew() {
		clickByName("_action_create");
		waitAjaxEnd();
		return new TemplateCrudPage(driver);
	}

	public TemplateListPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public TemplateCrudPage editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		return new TemplateCrudPage(driver);
	}
}
