package br.com.workfinity.web.page.stock;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;
import br.com.workfinity.web.page.TableUtils;
import br.com.workfinity.web.page.equipment.EquipmentShowPage;

public class StockControlListPage extends PageList {

	public StockControlListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("stock/index", "stock/list");
	}

	public StockControlListPage setSerialNumber(String serialNumber) {

		setFieldById("serialNumber", serialNumber);
		return this;
	}

	public void findOneRowInTable(List<String> filter) {

		validateIfContainsTheRowInTable(filter);
	}

	public StockControlListPage clickCheckUnCheckAll() {

		clickById("checkUncheckAll");
		return this;
	}

	public StockControlModelAddToShipmentOrder buttonAddToShipmentOrder() {

		waitOneTimeInPage(2000);
		clickByCssSelector("div.panel-footer.text-left button:nth-child(2).btn.btn-primary");
		waitAjaxEnd();
		return new StockControlModelAddToShipmentOrder(driver);
	}

	public StockControlListPage buttonExportToCSV() {

		clickByCssSelector("[name='_action_export']");
		return this;
	}

	public StockControlListPage setFamily(String family) {

		String dropBoxItem = "#familyIds .ms-res-ctn.dropdown-menu .ms-res-item";

		setFieldByCssSelector("div#familyIds div.ms-sel-ctn input[type=\"text\"]", family);
		waitOneTimeInPage(1000);

		clickByCssSelector("#familyIds .ms-trigger .ms-trigger-ico");

		waitVisibilitOfElement(findByCssSelector(dropBoxItem));
		clickByCssSelector(dropBoxItem);
		waitOneTimeInPage(1000);

		return this;
	}

	public StockControlListPage setServiceProvider(String serviceProvider) {

		String dropBoxItem = "#serviceProviderIds .ms-res-ctn.dropdown-menu .ms-res-item";

		setFieldByCssSelector("div#serviceProviderIds div.ms-sel-ctn input[type=\"text\"]", serviceProvider);
		waitOneTimeInPage(1000);

		clickByCssSelector("#serviceProviderIds .ms-trigger .ms-trigger-ico");

		waitVisibilitOfElement(findByCssSelector(dropBoxItem));
		clickByCssSelector(dropBoxItem);
		waitOneTimeInPage(1000);

		return this;
	}

	public void validateRow(List<String> values, int rowNumber) {

		TableUtils.validateRow(findByCssSelector("table#resultList"), values, rowNumber);
	}

	public void validateNumberOfRowsOfTable(int rowsNumber) {

		TableUtils.validateRowsNumber(findByCssSelector("table#resultList"), rowsNumber);
	}

	public StockControlListPage clickCheckUnCheckByArguments(String... arguments) {

		findByCssSelector(findTableReturnRowAndColumn(returnOneRowInTableFindByText(Arrays.asList(arguments)), 1),
				"input[type='checkbox']").click();
		return this;
	}

	public EquipmentShowPage showItemTable(int rowNumber) {

		showItemTable(rowNumber, 2);
		return new EquipmentShowPage(driver);
	}

	public StockControlListPage setModelType(String type) {

		setSelectByIdAndVisibleText("modelType", type);
		return this;
	}

	public StockControlListPage setModel(String model) {

		String dropDownItem = "#modelIds .ms-res-ctn.dropdown-menu .ms-res-item";

		setFieldByCssSelector("#modelIds .ms-sel-ctn input[type=\"text\"]", model);
		waitOneTimeInPage(1000);

//		clickByCssSelector("#modelIds .ms-trigger .ms-trigger-ico");
//		waitOneTimeInPage(1000);

		waitVisibilitOfElement(findByCssSelector(dropDownItem));
		clickByCssSelector(dropDownItem);
		waitOneTimeInPage(1000);

		return this;
	}

	public StockControlListPage clearAllFilter() {

		clickById("clearFiltersValues");
		return this;
	}

	public StockControlListPage setContractor(String contractor) {

		setFieldAutoCompleteByCssSelector(
				".row:nth-child(5) .col-sm-3:nth-child(1) .input-group .form-control.ui-autocomplete-input",
				contractor);
		waitAjaxEnd();
		return this;
	}

	public int getRowNumber(List<String> textReturn) {
		return returnOneRowInTableFindByText(textReturn);
	}

	public StockControlListPage setCheckBoxTemporary(boolean seeTemporaryEquipments) {

		setCheckBoxById("seeTemporaryEquipments", true);
		return this;
	}

	public StockControlListPage setPO(String po) {

		setFieldById("PO", po);
		return this;
	}

	public StockControlListPage setSI(String si) {

		setFieldById("SI", si);
		return this;
	}
}
