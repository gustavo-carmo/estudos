package br.com.workfinity.web.page.warrantyProvider;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class WarrantyProviderCrudForm extends Page {
	
	public WarrantyProviderCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("warrantyProvider/create", "warrantyProvider/edit", "warrantyProvider/show");
	}

	public WarrantyProviderCrudForm setName(String warrantyProviderName) {
		
		setFieldById("name", warrantyProviderName);
		return this;
	}

	public WarrantyProviderCrudForm setWarrantyType(String warrantyType) {

		setSelectByIdAndVisibleText("warrantyType", warrantyType);
		return this;
	}

	public WarrantyProviderCrudForm setWarrantyDays(String days) {

		setFieldById("days", days);
		return this;
	}

	public WarrantyProviderCrudForm buttonCreate() {

		clickByCssSelector(".btn.btn.btn-primary.noWarn");
		waitPageLoadEnds();
		return this;
	}

	public WarrantyProviderListPage buttonBackToSearch() {

		clickByCssSelector(".fa.fa-table.fa-fw");
		waitPageLoadEnds();
		return new WarrantyProviderListPage(driver);
	}
}
