package br.com.workfinity.web.page.perfil;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ProfileSettingsShowPage extends Page {

	public ProfileSettingsShowPage(WebDriver driver) {
		super(driver);
		validateUrlContains("profileSettings/show");
	}

}
