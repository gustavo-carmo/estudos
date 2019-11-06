package br.com.workfinity.web.page.perfil;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ProfileSettingsListPage extends PageList {

	public ProfileSettingsListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("profileSettings/index", "profileSettings/list");
	}

	public ProfileSettingsCrudForm buttonNew() {
		clickByName("_action_create");
		waitPageLoadEnds();
		return new ProfileSettingsCrudForm(driver);
	}

	public ProfileSettingsListPage setProfile(String profile) {
		setSelectByIdAndVisibleText("profile", profile);
		return this;
	}

	public ProfileSettingsCrudForm editItemTable(int rowNumber) {
		editItemTableByPosition(rowNumber, 1);
		waitPageLoadEnds();
		return new ProfileSettingsCrudForm(driver);
	}
	
}
