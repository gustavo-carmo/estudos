package br.com.workfinity.web.page.serviceAreas;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceAreaShowPage extends Page {
	
	public ServiceAreaShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("serviceAreas/show");
	}
	
	public ServiceAreaShowPage(WebDriver driver, String baseUrl, String id) {
		super(driver);
		visitURL(baseUrl + "/serviceAreas/show/" + id);
		waitPageLoadEnds();
		validateUrlContains("serviceAreas/show");
	}

	public ServiceAreaShowPage setRegion(String region) {
		setSelectByIdAndVisibleText("region", region);
		return this;
	}

	public ServiceAreaShowPage setAttend(String meet) {
		setSelectByIdAndVisibleText("exceptRegion", meet);
		return this;
	}
	
	public ServiceAreaShowPage buttonAdd() {
		clickById("form_add_regions_button");
		waitAjaxEnd();
		return this;
	}

}
