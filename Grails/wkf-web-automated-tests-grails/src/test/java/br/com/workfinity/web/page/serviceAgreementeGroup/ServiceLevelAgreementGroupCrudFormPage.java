package br.com.workfinity.web.page.serviceAgreementeGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceLevelAgreementGroupCrudFormPage extends Page {

	public ServiceLevelAgreementGroupCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceLevelAgreementGroup/create", "serviceLevelAgreementGroup/edit");
	}

	public ServiceLevelAgreementGroupCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ServiceLevelAgreementGroupShowPage buttonCreate() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return new ServiceLevelAgreementGroupShowPage(driver);
	}

	public ServiceLevelAgreementGroupShowPage buttonUpdate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new ServiceLevelAgreementGroupShowPage(driver);
	}

}
