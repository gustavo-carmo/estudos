package br.com.workfinity.web.page.serviceAgreementeGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceLevelAgreementGroupShowPage extends Page {
	
	public ServiceLevelAgreementGroupShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceLevelAgreementGroup/show");
	}

	public ServiceLevelAgreementGroupAddServiceFormPage buttonAddServiceLevelAgreementRule() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ServiceLevelAgreementGroupAddServiceFormPage(driver);
	}

	public ServiceLevelAgreementGroupListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new ServiceLevelAgreementGroupListPage(driver);
	}
	
	public ServiceLevelAgreementGroupAddServiceFormPage editItemTableServiceLevelAgreement(int rowNumber){
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ServiceLevelAgreementGroupAddServiceFormPage(driver);
	}
}
