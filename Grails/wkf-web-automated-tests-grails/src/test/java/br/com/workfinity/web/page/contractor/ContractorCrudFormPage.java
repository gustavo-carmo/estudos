package br.com.workfinity.web.page.contractor;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ContractorCrudFormPage extends Page {

	public ContractorCrudFormPage(WebDriver driver) {
		super(driver);
		validateUrlContains("contractor/create", "contractor/edit");
	}

	public ContractorCrudFormPage setContactName(String contactName) {
		setFieldById("contactName", contactName);
		return this;
	}

	public ContractorCrudFormPage setName(String name) {
		setFieldById("name", name);
		return this;
	}

	public ContractorCrudFormPage setEmail(String email) {
		setFieldById("email", email);
		return this;
	}

	public ContractorCrudFormPage setAlias(String alias) {
		setFieldById("alias", alias);
		return this;
	}

	public ContractorCrudFormPage setDocument(String type, String number) {
		setSelectByIdAndVisibleText("documentType", type);
		setFieldById("documentNumber", number);
		return this;
	}

	public ContractorShowPage buttonCreate() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		return new ContractorShowPage(driver);
	}

	public ContractorCrudFormPage setPhoneNumber(String number) {
		setFieldById("phoneNumber", number);
		return this;
	}

	public ContractorShowPage buttonUpdated() {
		clickByXpath("//button[@type='submit']");
		return new ContractorShowPage(driver);
	}

	public ContractorCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("status", status);
		return this;
	}

	public ContractorShowPage buttonBackToDetail() {
		clickByName("_action_show");
		waitPageLoadEnds();
		return new ContractorShowPage(driver);
	}	
}
