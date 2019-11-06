package br.com.workfinity.web.page.user;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ProfilePage extends Page {

	public ProfilePage(WebDriver driver) {
		super(driver);
		validateUrlContains("profile/index", "login");
	}

}
