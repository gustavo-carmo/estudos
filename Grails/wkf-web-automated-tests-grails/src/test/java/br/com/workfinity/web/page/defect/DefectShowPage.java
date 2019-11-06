package br.com.workfinity.web.page.defect;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class DefectShowPage extends Page {

	public DefectShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("defect/show");
	}
	
	public DefectShowPage(WebDriver driver, String baseUrl, String id) {
		super(driver);
		visitURL(baseUrl + "/defect/show/" + id);
		waitPageLoadEnds();
		validateUrlContains("defect/show");
	}

	public void buttonAdd() {
		clickById("form_add_solutions_button");
		waitAjaxEnd();
	}

	public void setSolutions(String solutions) {
		setSelectByIdAndVisibleText("solutions", solutions);
	}

	public DefectCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new DefectCrudFormPage(driver);
	}

	public DefectListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new DefectListPage(driver);
	}

}
