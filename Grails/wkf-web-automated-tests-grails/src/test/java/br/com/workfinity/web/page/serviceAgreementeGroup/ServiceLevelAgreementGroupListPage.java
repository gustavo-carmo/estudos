package br.com.workfinity.web.page.serviceAgreementeGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ServiceLevelAgreementGroupListPage extends PageList {

	public ServiceLevelAgreementGroupListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceLevelAgreementGroup/index", "serviceLevelAgreementGroup/list");
	}

	public ServiceLevelAgreementGroupCrudFormPage buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ServiceLevelAgreementGroupCrudFormPage(driver);
	}
	
	public void deleteItemTable(int rowNumber){
		deleteItemTableByPosition(rowNumber, 1);
	}

	public ServiceLevelAgreementGroupListPage setName(String name) {
		setFieldById("name",name);
		return this;
	}

}
