package br.com.workfinity.web.page.user;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class UserChangeSecretPhrasePage extends Page {

	public UserChangeSecretPhrasePage(WebDriver driver) {
		super(driver);
		validateUrlContains("profile/changeSecretPhrase");
	}

	public UserChangeSecretPhrasePage setCurrentPassword(String password) {
		setFieldById("password", password);
		return this;
	}

	public UserChangeSecretPhrasePage setSecretQuestion(String secretQuestion) {
		setFieldById("secretQuestion", secretQuestion);
		return this;
	}

	public UserChangeSecretPhrasePage setSecretAnswer(String phrase) {
		setFieldById("secretAnswer", phrase);
		return this;
	}

	public ProfilePage buttonOk() {
		clickByCssSelector("button.btn.btn-primary");
		waitPageLoadEnds();
		return new ProfilePage(driver);
	}
}
