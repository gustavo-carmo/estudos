package br.com.workfinity.web.page.genericContract;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class GenericContractCrudFormPage extends Page {

	public GenericContractCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("genericContract/create", "genericContract/edit");
	}

	public GenericContractCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public GenericContractCrudFormPage setTemplate(String template) {
		setSelectByIdAndVisibleText("templateServiceOrderPrint.id", template);
		return this;
	}

	public GenericContractShowPage buttonCreate() {
		clickByCssSelector(".panel-footer.text-left [type='submit']");
		waitPageLoadEnds();
		return new GenericContractShowPage(driver);
	}

	public GenericContractShowPage buttonUpdate() {
		clickByCssSelector(".panel-footer.text-left [type='submit']");
		waitPageLoadEnds();
		return new GenericContractShowPage(driver);
	}

	public void validateAllTemplates(List<String> texts) {
		validateTextsInDropBox(texts, "templateServiceOrderPrint.id");
	}
}
