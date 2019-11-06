package br.com.workfinity.web.page.serviceProvider;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceProviderShowPage extends Page{

	public ServiceProviderShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceProvider/show");
	}

	public ServiceProviderCrudFormPage buttonEdit() {
		clickByCssSelector("button.btn.btn.btn-primary");
		waitPageLoadEnds();
		return new ServiceProviderCrudFormPage(driver);
	}

	public ServiceProviderListPage buttonBackToSearch() {
		clickByName("_action_index");
		waitPageLoadEnds();
		return new ServiceProviderListPage(driver);
	}

	public ServiceProviderShowPage setServiceAreas(String serviceArea) {
		setSelectByIdAndVisibleText("serviceArea", serviceArea);
		waitAjaxEnd();
		return this;
	}

	public ServiceProviderShowPage buttonAddServiceAreas() {
		clickById("form_add_serviceAreas_button");
		waitAjaxEnd();
		return this;
	}

	public ServiceProviderShowPage setContractor(String contractor) {
		setSelectByIdAndVisibleText("contractor", contractor);
		waitAjaxEnd();
		return this;
	}

	public ServiceProviderShowPage buttonAddContractor() {
		clickById("form_add_contractors_button");
		waitAjaxEnd();
		return this;
	}

	public ServiceProviderShowPage setServiceGroup(String serviceGroup) {
		setSelectByIdAndVisibleText("serviceGroup", serviceGroup);
		waitAjaxEnd();
		return this;
	}

	public ServiceProviderShowPage buttonAddServiceGroup() {
		clickById("form_add_serviceGroup_button");
		waitAjaxEnd();
		return this;
	}

}
