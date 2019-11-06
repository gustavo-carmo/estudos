package br.com.workfinity.web.page.serviceProvider;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceProviderCrudFormPage extends Page {

	public ServiceProviderCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceProvider/create", "serviceProvider/edit");
	}

	public ServiceProviderShowPage buttonCreate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new ServiceProviderShowPage(driver);		
	}

	public ServiceProviderCrudFormPage setAlias(String alias) {
		setFieldById("alias", alias);
		return this;
	}

	public ServiceProviderCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ServiceProviderCrudFormPage setContactName(String name) {
		setFieldById("contactName", name);	
		return this;
	}

	public ServiceProviderCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ServiceProviderCrudFormPage setEmail(String email) {
		setFieldById("email", email);
		return this;
	}

	public ServiceProviderCrudFormPage setPhoneNumber(String number) {
		setFieldById("phoneNumber", number);		
		return this;
	}

	public ServiceProviderCrudFormPage setDocument(String type, String number) {
		setSelectByIdAndVisibleText("documentType", type);
		setFieldById("documentNumber", number);
		return this;
	}

	public ServiceProviderCrudFormPage setType(String type) {
		setSelectByIdAndVisibleText("type", type);
		return this;
	}

	public ServiceProviderShowPage buttonEdit() {
		clickByXpath("//button[@type='submit']");
		return new ServiceProviderShowPage(driver);
	}

	public ServiceProviderCrudFormPage setSelectMatriz(String stockMatriz) {
		setSelectByIdAndVisibleText("headquarters.id", stockMatriz);
		return this;
	}

	public ServiceProviderCrudFormPage setRadioMatriz(boolean isMatriz) {
		if(isMatriz){
			clickByCssSelector(".panel-body .row:nth-child(1) .radio label:nth-child(1)");
		} else {
			clickByCssSelector(".panel-body .row:nth-child(1) .radio label:nth-child(2) ");
		}
		return this;
	}
		
		
}
