package br.com.workfinity.web.page.importProcess;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.PageList;

public class ImportProcessListPage extends PageList {

	public ImportProcessListPage(WebDriver driver) {
		super(driver);
		validateUrlContains("importProcess/list", "importProcess/index");
	}

	public ImportProcessListPage setFile(String fileName) {
		setFieldById("fileName", fileName);
		return this;
	}

	public boolean isProcessed() {
		List<String> expectedProcessStatus = Arrays.asList("IMPORTING", "NOT_PROCESSED", "PROCESSING", "PROCESSED");
		String status = findTableReturnRowAndColumn(1, 6).getText();
		Calendar inicioDaOperacao = Calendar.getInstance();
		Calendar tempoLimiteDaOperacao = Calendar.getInstance();
		tempoLimiteDaOperacao.add(Calendar.MINUTE, 10);

		boolean looping = true;
		
		while (!status.equals("PROCESSED") && looping) {
			clickByCssSelector("button.btn.btn-primary");
			waitPageLoadEnds();
			status = findTableReturnRowAndColumn(1, 6).getText();
			if (status != "" && !expectedProcessStatus.contains(status)) {
				assertEquals(status, "PROCESSED", "Field 'status1' with value '" + status
						+ "' is invalid! It should be: 'IMPORTING', 'NOT_PROCESSED', 'PROCESSING' or 'PROCESSED' --");
				return false;
			} 
			inicioDaOperacao = Calendar.getInstance();
			looping = inicioDaOperacao.getTimeInMillis() <= tempoLimiteDaOperacao.getTimeInMillis();
			if(!looping) {
				assertTrue(looping, "O codigo que valida o termino da importação foi executado durante 10 minutos, e não foi finalizada a importação");
				return false;
			}
		}
		return true;
	}
	
	public ImportProcessListPage validateType(String textExpected) {
		validateIfContainsTextInElement(findTableReturnRowAndColumn(1, 5), textExpected);
		return this;
	}

	public ImportProcessListPage validateRows(String textExpected) {
		validateIfContainsTextInElement(findTableReturnRowAndColumn(1, 8), textExpected);
		return this;
	}

	public ImportProcessListPage validateSuccess(String textExpected) {
		validateIfContainsTextInElement(findTableReturnRowAndColumn(1, 9), textExpected);
		return this;
	}

	public ImportProcessListPage validateError(String textExpected) {
		validateIfContainsTextInElement(findTableReturnRowAndColumn(1, 10), textExpected);
		return this;
	}

	public ImportProcessListPage validateNotPrecessed(String textExpected) {
		validateIfContainsTextInElement(findTableReturnRowAndColumn(1, 11), textExpected);
		return this;
	}

	public ImportProcessFormCrudPage buttonNew() {
		clickByCssSelector(".btn.btn.btn-info");
		waitPageLoadEnds();
		return new ImportProcessFormCrudPage(driver);
	}

	public ImportProcessShowPage showItemTable(int rowNumber) {
		showItemTable(rowNumber, 1);
		waitPageLoadEnds();
		return new ImportProcessShowPage(driver);
	}
}
