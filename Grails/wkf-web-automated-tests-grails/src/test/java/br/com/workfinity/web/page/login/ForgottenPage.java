package br.com.workfinity.web.page.login;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.user.ProfilePage;

public class ForgottenPage extends Page {

	public ForgottenPage(WebDriver driver) {
		super(driver);
		validateUrlContains("forgottenPassword/index", "forgottenPassword/step");
	}

	public ForgottenPage setUsername(String username) {
		setFieldById("username", username);
		return this;
	}

	public ForgottenPage buttonSearch() {
		clickByCssSelector("button.btn.btn-primary.btn-lg.btn-block");
		waitPageLoadEnds();
		return this;
	}

	public ForgottenPage setAnswer(String answer) {
		setFieldById("secretAnswer", answer);
		return this;
	}

	public ForgottenPage setNewPassword(String newPassword) {
		setFieldById("newPassword", newPassword);
		return this;
	}

	public ForgottenPage setRepeatNewPassword(String repeatNewPassord) {
		setFieldById("repeatNewPassword", repeatNewPassord);
		return this;
	}

	public ProfilePage buttonOk() {
		clickByCssSelector("button.btn.btn-primary.btn-lg.btn-block");
		waitPageLoadEnds();
		return new ProfilePage(driver);
	}

	public void validateMessage(String... message) {
		validateMessagesErrors(message);
	}

	public ForgottenPage buttonOkFail() {
		clickByCssSelector("button.btn.btn-primary.btn-lg.btn-block");
		waitPageLoadEnds();
		return this;
	}
}
