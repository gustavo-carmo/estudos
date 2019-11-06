package br.com.workfinity.web.page.template;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class TemplateCrudPage extends Page {

	public TemplateCrudPage(WebDriver driver) {
		super(driver);
		validateUrlContains("template/create", "template/edit");
	}

	public TemplateCrudPage setStatus(String status) {
		setSelectByIdAndVisibleText("template.status", status);
		return this;
	}

	public TemplateCrudPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public TemplateCrudPage setMimeType(String visibleText) {
		setSelectByIdAndVisibleText("mimeType", visibleText);
		return this;
	}

	public TemplateShowPage buttonCreate() {
		clickByCssSelector(".panel-footer.text-left [type='submit']");
		waitPageLoadEnds();
		return new TemplateShowPage(driver);
	}

	public TemplateShowPage buttonUpdate() {
		clickByCssSelector(".panel-footer.text-left [type='submit']");
		waitPageLoadEnds();
		return new TemplateShowPage(driver);
	}
}
