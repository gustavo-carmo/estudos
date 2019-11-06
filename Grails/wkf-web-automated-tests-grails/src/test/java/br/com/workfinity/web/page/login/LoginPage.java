package br.com.workfinity.web.page.login;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.Page;

public class LoginPage extends Page {

	static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);

	public LoginPage(final WebDriver driver, final String username, final String password) {
		super(driver);
		Assert.assertTrue("You are not on the login page " + driver.getCurrentUrl(),
				driver.getTitle().contains("Workfinity - Login"));
		setFieldById("username", username);
		setFieldById("password", password);
	}

	public MainPage buttonSignInSuccess() {
		clickByXpath("//button[@type='submit']");
		waitPageLoadEnds();
		NoContainsTextInUrl("login/auth?login_error=", "Login Error");
		LOG.info("Login Success");
		return new MainPage(driver);
	}

	public ForgottenPage clickForgottenPassword() {
		clickByCssSelector("a.btn-link.mar-rgt");
		waitPageLoadEnds();
		return new ForgottenPage(driver);
	}
}
