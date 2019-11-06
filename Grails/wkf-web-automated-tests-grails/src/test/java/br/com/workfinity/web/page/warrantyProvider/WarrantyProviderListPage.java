package br.com.workfinity.web.page.warrantyProvider;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class WarrantyProviderListPage extends Page {
	
	public WarrantyProviderListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("warrantyProvider/index", "warrantyProvider/list");
	}

	public WarrantyProviderCrudForm buttonNew() {
		clickByCssSelector(".fa.fa-file-o.fa-fw");
		waitPageLoadEnds();
		return new WarrantyProviderCrudForm(driver);
	}
}
