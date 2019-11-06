package br.com.workfinity.web.page.perfil;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ProfileSettingsCrudForm extends Page {

	public ProfileSettingsCrudForm(WebDriver driver) {
		super(driver);
		validateUrlContains("profileSettings/create", "profileSettings/edit");
	}

	public ProfileSettingsCrudForm editItemTable(int rowNumber) {
		editItemTableByPosition("resultList", rowNumber, 1, "i.fa.fa-edit");
		waitPageLoadEnds();
		return this;
	}

	public ProfileSettingsShowPage buttonCreate() {
		clickByCssSelector("button[type='submit']");
		waitPageLoadEnds();
		return new ProfileSettingsShowPage(driver);
	}

	public int getRowNumber(List<String> textReturn) {
		return returnOneRowInTableFindByText(textReturn, "resultList");
	}
}
