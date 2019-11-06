package br.com.workfinity.web.page.serviceOrder;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.workfinity.web.page.Page;

public class ServiceOrderCrudFormConsumptionModel extends Page {

	public ServiceOrderCrudFormConsumptionModel(WebDriver driver) {
		super(driver);
	}

	public ServiceOrderCrudFormConsumptionModel goToTabParts() {
		clickByCssSelector("#consumption_modal_modal-body .nav.nav-tabs li a[href='#partTab']");
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel validateThereAreRowInTablePart(List<String> textsExpectedsFindInOneRow) {
		assertTrue(returnOneRowInTableFindByTable(textsExpectedsFindInOneRow, findByCssSelector("#partTab table")) > 0,
				"Don't find row in table of Consumpition, but expected find");
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel validateThereAreNotRowInTablePart(List<String> textsDontExpectedsFindInOneRow) {
		assertTrue(!(returnOneRowInTableFindByTable(textsDontExpectedsFindInOneRow, findByCssSelector("#partTab table")) > 0),
				"Find row in table of Consumpition, but expected don't find");
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel validateIfWithdrawnEquipmentIsDisable(String findRowByModel) {
		validateIfElementIsDisable(
				findByCssSelector(
						findTableReturnRowAndColumn(
								returnOneRowInTableFindByTable(Arrays.asList(findRowByModel),
										findByCssSelector("#consumption_parts_container table")), 4), "select"), "");
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel validateIfWithdrawnEquipmentIsEnabled(String findRowByModel) {
		WebElement table = findByCssSelector("#partTab table");

		WebElement columnAndRowOfTable = findTableReturnRowAndColumn(table,
				returnOneRowInTableFindByTable(Arrays.asList(findRowByModel), table), 4);

		validateIfElementIsEnabled(findByCssSelector(columnAndRowOfTable, "select"), "Era para o elemento [" + columnAndRowOfTable
				+ " - select ], estar habilitado, mas ele esta desabilitado");
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel validateIfFieldIsVisible(String idElement) {
		validateIfElementIsDisplayedFindById(idElement);
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel setPart(String part) {
		setSelectByIdAndVisibleText("consumption_part_id", part);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel setSerialNumber(String serialNumber) {
		String cssElement = "#consumption_part_serialNumber input";
		setFieldByCssSelector(cssElement, serialNumber);

		tab(cssElement);
		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudForm selectItemTable(int rowNumber) {
		clickByCssSelector("#partTab table tbody tr:nth-child(" + rowNumber + ") a i");
		waitAjaxEnd(3000);
		
		return new ServiceOrderCrudForm(driver);
	}

	public ServiceOrderCrudFormConsumptionModel selectItemTableFail(int rowNumber) {
		clickByCssSelector("#partTab table tbody tr:nth-child(" + rowNumber + ") a i");

		waitAjaxEnd();
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel setWithdrawnEquipment(String withdrawnEquipment, String newSerialNumber) {
		setSelectByCssSelectorAndVisibleText(".consumption-parts-model.form-control", withdrawnEquipment);
		waitAjaxEnd();

		// TODO O METODO SET_FIELD_BY_CSSSELECTOR NÃO ESTA SIMULANDO O TAB NESSE
		// ELEMENTO VERIFICAR O MOTIVO
		// setFieldByCssSelector("#consumption_parts_container table tbody tr td input[name='model_serial_number']",
		// newSerialNumber);

		setValue(newSerialNumber, findByCssSelector("#consumption_parts_container table tbody tr td input[name='model_serial_number']"));
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel validateIfDontHaveParts() {
		validateIfContainsTextInElement(findByCssSelector("div#partTab div.alert.alert-warning"),
				"não possúi estoque de Partes disponível para");
		return this;
	}

	public ServiceOrderCrudForm buttonCancel() {
		clickByCssSelector("div.modal-content a.btn.btn-default");
		return new ServiceOrderCrudForm(driver);
	}

	public ServiceOrderCrudFormConsumptionModel validateIfHaveParts() {
		validateIfElementIsDisplayedFindByElement(findByCssSelector("div#partTab div#consumption_parts_container"));
		return this;
	}

	public ServiceOrderCrudFormConsumptionModel validateMessageError(List<String> errorMessage) {
		String errorMessages = findByCssSelector("#consumption_modal_modal-body #main-flash-message .media-body").getText();

		for (String error : errorMessage) {
			assertTrue(errorMessages.contains(error), "Expected find [" + error + "] on " + errorMessages + ", but don't found");
		}

		return this;
	}
}
