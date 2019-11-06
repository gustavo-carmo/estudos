package br.com.workfinity.web.page.workPack;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;
import web.utils.HtmlUtils;

public class WorkPackListPage extends PageList {
	
	public WorkPackListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("workPack/index", "workPack/list");
	}

	public WorkPackCrudForm buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new WorkPackCrudForm(driver);
	}

	public WorkPackListPage setServiceProvider(String serviceProvider) {
		HtmlUtils.autoCompleteMultiplePluginSelect2(driver, "serviceProviderIdList", serviceProvider);
		return this;		
	}

	public WorkPackCrudForm editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new WorkPackCrudForm(driver);		
	}

	public WorkPackListPage setStatus(String status) {
		HtmlUtils.autoCompleteMultiplePluginSelect2(driver, "statusList", status);
		return this;
	}

	public WorkPackListPage setTechnician(String technician) {
		HtmlUtils.autoCompleteMultiplePluginSelect2(driver, "technicianIdList", technician);
		return this;
	}

	public int getRowNumberOfColumn(String codeReturn) {
		return returnOneRowInTableFindByTableSpecificColumn(codeReturn, "resultList");
	}
	
	public int getRowNumberWithText(List<String> textReturn) {
		return returnOneRowInTableFindByText(textReturn, "resultList");
	}

	public WorkPackListPage cleanFieldFilter() {
		clickInElementsFindByCssSelector(".select2-selection__choice__remove");
		return this;
	}

}
