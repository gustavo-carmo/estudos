package br.com.workfinity.web.page.recess;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import web.utils.HtmlUtils;

public class RecessCrudFormPage extends Page {

	public RecessCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("recess/create", "recess/edit");
	}

	public RecessShowPage buttonCreate() {

		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new RecessShowPage(driver);
	}

	public RecessCrudFormPage setName(String name) {

		setFieldById("name", name);
		return this;
	}

	public RecessCrudFormPage setMonth(String month) {

		setSelectByIdAndVisibleText("month", month);
		waitAjaxEnd();
		return this;
	}

	public RecessCrudFormPage setDay(String day) {

		setSelectByIdAndVisibleText("day", day);
		return this;
	}

	public RecessCrudFormPage setRecessType(String type) {

		setSelectByIdAndVisibleText("recessType", type);
		waitAjaxEnd();
		return this;
	}

	public RecessCrudFormPage setCity(String city) {

		clickByCssSelector(".select2-selection.select2-selection--single");
		waitAjaxEnd();
		HtmlUtils.setSelect2Complete(driver, city, 2);
		waitAjaxEnd();
		return this;
	}
}
