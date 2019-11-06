package br.com.workfinity.web.page;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import br.com.workfinity.web.page.TableUtils.OPTIONS;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.user.UserChangeSecretPhrasePage;
import careman.html.TestListener;
import web.utils.HtmlUtils;

public abstract class Page {
	
	static final Logger LOG = LoggerFactory.getLogger(TestListener.class);
	protected WebDriver driver;
	private WebDriverWait wait;

	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 120);
	}

	// METODOS QUE PROCURAM O ELEMENTO NA PAGINA E RETORNAM O ELEMENTO
	// ENCONTRADO
	protected WebElement findByClassName(String className) {
		return waitVisibilitOfElement(driver.findElement(By.className(className)));
	}

	protected WebElement findByClassName(WebElement webElement, String className) {
		LOG.debug("Find by className " + className);
		return waitVisibilitOfElement(webElement.findElement(By.className(className)));
	}

	protected WebElement findByName(String name) {
		return waitVisibilitOfElement(driver.findElement(By.name(name)));
	}

	protected WebElement findByName(WebElement webElement, String name) {
		LOG.debug("Find by name " + name);
		return waitVisibilitOfElement(webElement.findElement(By.name(name)));
	}

	protected WebElement findByLinkText(String linkText) {
		return waitVisibilitOfElement(driver.findElement(By.linkText(linkText)));
	}

	protected WebElement findByLinkText(WebElement webElement, String linkText) {
		LOG.debug("Find by linkText " + linkText);
		return waitVisibilitOfElement(webElement.findElement(By.linkText(linkText)));
	}

	protected WebElement findByCssSelector(String cssSelector) {
		return waitVisibilitOfElement(driver.findElement(By.cssSelector(cssSelector)));
	}

	protected WebElement findByCssSelector(WebElement webElement, String cssSelector) {
		LOG.debug("Find by cssSelector " + cssSelector);
		return waitVisibilitOfElement(webElement.findElement(By.cssSelector(cssSelector)));
	}

	protected WebElement findById(String id) {
		return waitVisibilitOfElement(driver.findElement(By.id(id)));
	}

	protected WebElement findById(WebElement webElement, String id) {
		LOG.debug("Find by id " + id);
		return waitVisibilitOfElement(webElement.findElement(By.id(id)));
	}

	protected WebElement findByXpath(String xpath) {
		return waitVisibilitOfElement(driver.findElement(By.xpath(xpath)));
	}

	protected WebElement findByXpath(WebElement webElement, String xpath) {
		LOG.debug("Find by xpath " + xpath);
		return waitVisibilitOfElement(webElement.findElement(By.xpath(xpath)));
	}
	
	protected WebElement findOneElementOfOneListElements(String cssOfListElements, String textInsideElement) {
		List<WebElement> elements = driver.findElements(By.cssSelector(cssOfListElements));

		for(int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getAttribute("textContent").contains(textInsideElement)) {
				return elements.get(i);
			}
		}
		
		return null;
	}

	// METODOS QUE CLICAM NE UM ELEMENTO
	protected void clickByName(String name) {
		LOG.debug("Clicou no elemento com o nome " + name);
		waitElementToBeClickable(findByName(name)).click();
	}

	protected void clickByXpath(String xpath) {
		LOG.debug("Clicou no elemento com o xpath " + xpath);
		waitElementToBeClickable(findByXpath(xpath)).click();
	}

	protected void clickByLinkText(String link) {
		LOG.debug("Clicou no elemento com o link " + link);
		waitElementToBeClickable(findByLinkText(link)).click();
	}

	protected void clickById(String id) {
		LOG.debug("Clicou no elemento com o id " + id);
		waitElementToBeClickable(findById(id)).click();
	}

	protected void clickByCssSelector(String cssSelector) {
		LOG.debug("Clicou no elemento com o cssSelector " + cssSelector);
		waitElementToBeClickable(findByCssSelector(cssSelector)).click();
	}
	
	protected void clickInElementsFindByCssSelector(String cssSelector) {
		List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));
		
		for (WebElement element : elements) {
			element.click();
		}
		
	}

	public void logout() {
		clickImage();
		clickByCssSelector("div.dropdown-menu.dropdown-menu-md.dropdown-menu-right.with-arrow.panel-default a.btn.btn-primary");
		waitPageLoadEnds();
	}

	public UserChangeSecretPhrasePage clickChangeSecretPhrase() {
		clickImage();
		clickByCssSelector("div.dropdown-menu.dropdown-menu-md.dropdown-menu-right.with-arrow.panel-default ul li:nth-child(3) i.fa.fa-refresh");
		waitPageLoadEnds();
		return new UserChangeSecretPhrasePage(driver);
	}

	private void clickImage() {
		clickByCssSelector("div.navbar-content.clearfix img.img-circle.img-user.media-object");
	}

	// METODOS QUE SETAM VALOR NE UM ELEMENTO
	protected void setValue(String value, WebElement webElement) {
		waitVisibilitOfElement(webElement).clear();

		if (value.equals("")) {
			webElement.sendKeys(Keys.BACK_SPACE);
			webElement.sendKeys(Keys.TAB);
		} else {
			webElement.sendKeys(value);
		}
	}

	protected void setFieldById(String id, String value) {
		if (null == value || null == id) {
			throw new IllegalArgumentException("Id and Value are required fields!");
		} else {
			WebElement webElement = findById(id);
			setValue(value, webElement);

			((JavascriptExecutor) driver).executeScript("$('#" + id + "').trigger('change'); return true;");
			validateFieldFill(id, value, findById(id));
		}
	}

	protected void setFieldByCssSelector(String cssSelector, String value) {
		if (null == value || null == cssSelector) {
			throw new IllegalArgumentException("cssSelector and Value are required fields!");
		} else {
			WebElement webElement = findByCssSelector(cssSelector);
			setValue(value, webElement);

			((JavascriptExecutor) driver).executeScript("$('" + cssSelector + "').trigger('change'); return true;");
			validateFieldFill(cssSelector, value, findByCssSelector(cssSelector));
		}
	}

	protected void setCheckBoxById(String id, boolean value) {
		waitElementToBeClickable(findById(id));
		WebElement checkBox = findById(id);
		if (!checkBox.isSelected() && value) {
			checkBox.click();
		} else if (checkBox.isSelected() && !value) {
			checkBox.click();
		}
	}

	protected void setElementCheckBox(WebElement elementCheckBox, boolean value) {
		waitElementToBeClickable(elementCheckBox);
		if (!elementCheckBox.isSelected() && value) {
			elementCheckBox.click();
		} else if (elementCheckBox.isSelected() && !value) {
			elementCheckBox.click();
		}
	}
	
	protected void setSelectByIdAndVisibleText(final String id, final String visibleText) {

		LOG.debug("Selecionou o texto no selector do id: " + id + " O texto visível é: " + visibleText);
		
		new Select(findById(id)).selectByVisibleText(visibleText);
		// TODO - voltar quando corrigir o bug de reparo
		// ((JavascriptExecutor) driver).executeScript("$('#" + id +
		// "').trigger('change'); return true;");

		try {
			wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					Select select = new Select(findById(id));
					return (boolean) (select.getFirstSelectedOption().getText()).equals(visibleText);
				}
			});
		} catch (TimeoutException e) {
			Select select = new Select(findById(id));
			Assert.assertEquals(select.getFirstSelectedOption(), visibleText, "Fail in attempt to change [" + id + "] the field text ");
		}
	}

	protected void setSelectByCssSelectorAndVisibleText(final String cssSelector, final String visibleText) {
		
		new Select(findByCssSelector(cssSelector)).selectByVisibleText(visibleText);
		
		// TODO - voltar quando corrigir o bug de reparo
		// ((JavascriptExecutor) driver).executeScript("$('#" + id + "').trigger('change'); return true;");
		
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					Select select = new Select(findByCssSelector(cssSelector));
					return (boolean) (select.getFirstSelectedOption().getText()).equals(visibleText);
				}
			});
		} catch (TimeoutException e) {
			Select select = new Select(findByCssSelector(cssSelector));
			Assert.assertEquals(select.getFirstSelectedOption(), visibleText, "Fail in attempt to change [" + cssSelector + "] the field text ");
		}
	}
	
	protected void setFieldWithDownArrowAutoCompleteByCssSelector(String cssSelector, String value, String idUlElement) {
		if (value != null && value != "") {
			setFieldWithDownArrowAutoCompleteByCssSelector(cssSelector, value, 1, idUlElement);
		} else {
			setFieldById(cssSelector, "");
			waitAjaxEnd();
		}
	}

	private void setFieldWithDownArrowAutoCompleteByCssSelector(String cssSelector, String value, int elementPosition, String idUlElement) {
		setFieldByCssSelector(cssSelector, value);
		waitAjaxEnd();

		findById(idUlElement);

		WebElement autoComplete = findByCssSelector(cssSelector);

		for (int i = 0; i < elementPosition; i++) {
			autoComplete.sendKeys(Keys.ARROW_DOWN);
		}

		autoComplete.sendKeys(Keys.ENTER);
	}

	protected void setFieldAutoCompleteById(String id, String value) {
		if (value != null && value != "") {
			setFieldAutoCompleteById(id, value, 1);
		} else {
			setFieldById(id, "");
			waitAjaxEnd();
		}
	}

	private void setFieldAutoCompleteById(String id, String value, int elementPosition) {
		setFieldById(id, value);
		waitAjaxEnd();

		findByCssSelector("div#container_" + id + " ul.ui-autocomplete");

		WebElement autoComplete = findById(id);

		for (int i = 0; i < elementPosition; i++) {
			autoComplete.sendKeys(Keys.ARROW_DOWN);
		}

		autoComplete.sendKeys(Keys.ENTER);
	}
	
	protected void setFieldAutoCompleteByCssSelector(String cssSelector, String value) {
		if (value != null && value != "") {
			setFieldAutoCompleteByCssSelector(cssSelector, value, 1);
		} else {
			setFieldById(cssSelector, "");
			waitAjaxEnd();
		}
	}
	
	private void setFieldAutoCompleteByCssSelector(String cssSelector, String value, int elementPosition) {
		setFieldByCssSelector(cssSelector, value);
		waitAjaxEnd();
		
		findByCssSelector(cssSelector);
		
		WebElement autoComplete = findByCssSelector(cssSelector);
		
		for (int i = 0; i < elementPosition; i++) {
			autoComplete.sendKeys(Keys.ARROW_DOWN);
		}
		
		autoComplete.sendKeys(Keys.ENTER);
	}
	

	protected void setFieldByCssSelectorAndTapEnter(String cssSelector, String value) {

		if (null == value || null == cssSelector) {
			throw new IllegalArgumentException("cssSelector and Value are required fields!");
		} else {
			
			WebElement webElement = findByCssSelector(cssSelector);
			setValue(value, webElement);
			
			webElement.sendKeys(Keys.ENTER);
		}
	}
	
	protected void setValuesInFieldByCssSelectorAndTapEnter(String cssSelector, String... values) {
		
		if (null == values || null == cssSelector) {
			throw new IllegalArgumentException("cssSelector and Value are required fields!");
		} else {
			
			WebElement webElement = findByCssSelector(cssSelector);
			
			waitVisibilitOfElement(webElement);
			
			for (String value : values) {
				
				webElement.sendKeys(value);
				
				webElement.sendKeys(Keys.ENTER);
			}

			
		}
	}

	// METODOS QUE VALIDAM ALGO NA PAGINA
	private void validateFieldFill(String identify, final String value, final WebElement webElement) {
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return (boolean) (webElement.getAttribute("value").equals(value));
				}
			});
		} catch (TimeoutException e) {
			Assert.assertEquals(webElement.getAttribute("value"), value, "Fail in attempt to change [" + identify + "] the field text ");
		}
	}

	public void validateIfElementIsDisable(String idElement) {
		validateIfElementIsDisable(idElement, "");
	}

	public void validateIfElementIsDisable(String idElement, String messageReturned) {
		validateIfElementIsDisable(findById(idElement), messageReturned);
	}
	
	public void validateIfElementIsDisable(WebElement element, String messageReturned) {
		LOG.debug("Validando se o elemento " + element.toString() + " esta desabilitado");
		Assert.assertFalse(element.isEnabled(), messageReturned);
	}

	public void validateIfElementIsEnabled(String idElement) {
		validateIfElementIsEnabled(idElement, "");
	}

	public void validateIfElementIsEnabled(String idElement, String messageReturned) {
		LOG.debug("Validando se o elemento " + idElement + " esta habilitado");
		validateIfElementIsEnabled(findById(idElement), messageReturned);
	}
	
	public void validateIfElementIsEnabled(WebElement element, String messageReturned) {
		LOG.debug("Validando se o elemento " + element + " esta habilitado");
		Assert.assertTrue(element.isEnabled(), messageReturned);
	}

	public void validateMessageSuccess(String message, String cssSelector) {
		LOG.debug("Validando a menssagem de sucesso" + message);
		waitPageLoadEnds();
		waitTextToBePresentInElementLocated(By.cssSelector(cssSelector), message);
	}
	
	public void validateMessageSuccess(String message) {
		validateMessageSuccess(message, "#main-flash-message .media-body .alert-title");
	}

	public void validateMessageSuccess(Messages message) {
		validateMessageSuccess(message.toString());
	}

	public void validateMessageSuccessCreated() {
		LOG.debug("Criado com sucesso");
		validateMessageSuccess(Messages.CREATED);
	}

	public void validateMessageSuccessUpdate() {
		LOG.debug("Atualizado com sucesso");
		validateMessageSuccess(Messages.UPDATED);
	}

	public void validateMessageSuccessSaved() {
		LOG.debug("Salvo com sucesso");
		validateMessageSuccess(Messages.SAVED);
	}

	public void validateMessageSuccessDeleted() {
		LOG.debug("Excluído com sucesso");
		validateMessageSuccess(Messages.DELETED);
	}
	
	public void validateAlertTitleAndMessage(String titleExpected) {
		validateAlertTitleAndMessage("div.media.hide", titleExpected, null);
	}
	
	public void validateAlertTitleAndMessage(String titleExpected, String messageExpected) {
		validateAlertTitleAndMessage("div.media.hide", titleExpected, messageExpected);
	}
	
	public void validateAlertTitleAndMessage(String cssOfElementHasTheAlert, String titleExpected, String messageExpeceted) {
		Assert.assertTrue(driver.findElement(By.cssSelector(cssOfElementHasTheAlert + " h4.alert-title")).getAttribute("textContent").equals(titleExpected));
		if (messageExpeceted != null) {
			Assert.assertTrue(driver.findElement(By.cssSelector("div.media.hide p.alert-message")).getAttribute("textContent").equals(messageExpeceted));
		}
	}

	public void validateAccessDenied() {
		// VALIDA A URL
		validateUrlContains("login/denied");
		// VALIDA A MENSAGEM DE ACESSO NEGADO
		validateIfContainsTextInElement(findByCssSelector("[id='content-container']  h4.text-center"),
				"Ops, você não tem acesso a esta página");
	}

	public void validateSearchMessageNoResult() {
		validateIfContainsTextInElement(findByXpath("/html/body/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div/table/tbody/tr/td"),
				"Nenhum resultado");
	}

	public void validateIfContainsTheRowInTable(List<String> filter) {
		validateIfContainsTheRowInTable(filter, null);
	}
	
	public void validateIfContainsTheRowInTable(List<String> filter, String idTable) {
		validateIfContainsTheRowInTable(filter, idTable, "The row sought there aren't in table - sought one row with the values : " + filter);
	}
	
	public void validateIfContainsTheRowInTable(List<String> filter, String idTable, String messageReturn) {
		Assert.assertTrue(returnOneRowInTableFindByText(filter, idTable) > 0,
				messageReturn);
	}
	
	public void validateIfContainsTheRowInTableByCssSelector(List<String> filter, String cssSelector, String messageReturn) {
		Assert.assertTrue(returnOneRowInTableFindByCssSelectorText(filter, cssSelector) > 0,
				messageReturn);
	}
	
	public void validateNotContainsRowInTable(List<String> filter, String findTableByCssSelector, String messageReturn) {
		assertTrue(returnOneRowInTableFindByTable(filter, findByCssSelector(findTableByCssSelector)) < 1, messageReturn);
	}
	
	public void validateNotContainsRowInTable(List<String> filter, String messageReturn) {
		validateNotContainsRowInTable(filter, "table#resultList", messageReturn);
	}
	
	public void validateIfContainsTextInElement(WebElement webElement, String text) {
		Assert.assertTrue(webElement.getText().contains(text), "VALIDATION ERROR - Expected to find the [" + text
				+ "] value, but found the value [" + webElement.getText() + "] -----");
	}

	public void validateIfContainsValueInElement(WebElement webElement, String text) {
		Assert.assertTrue(webElement.getAttribute("value").contains(text),
				"Esperava encontrar o valor [" + text + "] mas encontrou o valor [" + webElement.getAttribute("value") + "] ");
	}

	public void assertEquals(String text1, String text2, String message) {
		Assert.assertEquals(text1, text2, message);
	}

	public void assertNull(String element) {
		Assert.assertNull(driver.findElement(By.cssSelector(element)), "The element should be null");
	}
	
	public void assertTrue(Boolean validation, String messageReturn) {
		Assert.assertTrue(validation, messageReturn);
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}

	public void isElementPresent(String cssSelector, String message) {
		Assert.assertTrue(isElementPresent(By.cssSelector(cssSelector)), message);
	}

	public void validateResultHeader(Object... headers) {
		TableUtils.validateHeader(findById("resultList"), headers);
	}

	public void validateResultAction(OPTIONS... args) {
		validateResultAction(0, args);
	}

	public void validateResultAction(int columnNumber, OPTIONS... args) {
		TableUtils.validateActions(findById("resultList"), columnNumber, args);
	}

	public void validateTextsInDropBox(List<String> mustContainText, String idElement) {
		String textoDoElemento = findById(idElement).getText();

		// TODO Tornar os dois asserts false, em assertFalse genericos não fixos
		Assert.assertFalse(textoDoElemento.contains(Messages.DISABLE.toString()));
		Assert.assertFalse(textoDoElemento.contains(Messages.TEXT.toString()));

		for (String text : mustContainText) {
			Assert.assertTrue(textoDoElemento.contains(text), "Era esperado encontrar o texto [" + text + "], no elemento com o id: "
					+ idElement);
		}
	}

	public void NoContainsTextInUrl(String text, String messageReturnIfContains) {
		Assert.assertFalse(driver.getCurrentUrl().contains(text), messageReturnIfContains);
	}

	public void validateUrlContains(String... urls) {
		String currentUrl = driver.getCurrentUrl();
		Boolean validacao = null;
		for (int i = 0; i < urls.length; i++) {
			validacao = comparador(validacao, currentUrl.contains(urls[i]));
		}

		Assert.assertTrue(validacao, "Esperado estar em uma URL que contém ["+ StringUtils.myJoin(" - ", urls) + "], mas o sistema esta na url [" + currentUrl + "]");
	}

	protected void validateIfElementIsDisplayedFindById(String idElement) {
		validateIfElementIsDisplayedFindByElement(findById(idElement));
	}
	
	protected void validateIfElementIsDisplayedFindByElement(WebElement element) {
		Assert.assertTrue(element.isDisplayed(), "The element " + element.getText() + ", is not Displayed");
	}
	
	protected void validateIfElementIsNotDisplayed(String idElement) {
		Assert.assertFalse(driver.findElement(By.id(idElement)).isDisplayed(), "The element with id: " + idElement + ", is Displayed");
	}
	
	protected void validateMessagesErrors(String cssSelector, String... errorsRequired) {

		waitPageLoadEnds();

		// TODO tem um erro aqui, verificar pq o texto não está disponível
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> webElementsErrors = driver.findElements(By.cssSelector(cssSelector));

		List<String> webElementsErrorsString = new ArrayList<String>();
		for (WebElement webElement : webElementsErrors) {
			webElementsErrorsString.add(webElement.getText());
		}

		Assert.assertTrue(errorsRequired.length == webElementsErrorsString.size(), "Expected error size [" + errorsRequired.length + "]( "
				+ errorsRequired + ") but foud [" + webElementsErrorsString.size() + "] ( " + webElementsErrorsString + ")");

		for (String errorRequired : errorsRequired) {
			Assert.assertTrue(webElementsErrorsString.contains(errorRequired), "Error message [" + errorRequired + "] not found in "
					+ webElementsErrorsString.toString());
		}
	}
	
	protected void validateMessagesErrors(String... errorsRequired) {
		
		validateMessagesErrors("#main-flash-message .beanErrorsItem", errorsRequired);
		
	}

	// METODOS QUE MANIPULAM UMA TABELA
	public void deleteItemTableByPosition(int rowNumber, int columnNumber) {
		deleteItemTableByPosition("", rowNumber, columnNumber);
	}

	public void deleteItemTableByPosition(String id, int rowNumber, int columnNumber) {
		deleteItemTableByPosition(id, rowNumber, columnNumber, "");
	}

	public void deleteItemTableByPosition(String id, int rowNumber, int columnNumber, String messageExpected) {
		deleteItemTableByPosition(id, rowNumber, columnNumber, "", "a.fa-remove");
	}
	
	public void deleteItemTableByPosition(String id, int rowNumber, int columnNumber, String messageExpected, String cssSelector) {
		waitPageLoadEnds();
		findTableReturnRowAndColumn(id, rowNumber, columnNumber).findElement(By.cssSelector(cssSelector)).click();
		acceptMessageAlert();
		if (messageExpected.equals("")) {
			validateMessageSuccessDeleted();
		} else {
			validateMessageSuccess(messageExpected);
		}
	}
	
	protected void deleteOneRowInTableByPosition(int rowNumber, String cssSelector) {

		waitPageLoadEnds();
		findTableReturnRowAndColumn(rowNumber, 1).findElement(By.cssSelector(cssSelector)).click();
		acceptMessageAlert();
	}

	protected void showItemTable(int rowNumber, int columnNumber) {
		findTableReturnRowAndColumn(rowNumber, columnNumber).findElement(By.cssSelector("a.fa-eye")).click();
		waitPageLoadEnds();
	}

	protected void editItemTableByPosition(int rowNumber, int columnNumber) {
		editItemTableByPosition("", rowNumber, columnNumber);
	}

	protected void editItemTableByPosition(String idTable, int rowNumber, int columnNumber) {
		editItemTableByPosition(idTable, rowNumber, columnNumber, "a.fa-edit");
		waitPageLoadEnds();
	}
	
	protected void editItemTableByPosition(String idTable, int rowNumber, int columnNumber, String cssSelector) {
		findTableReturnRowAndColumn(idTable, rowNumber, columnNumber).findElement(By.cssSelector(cssSelector)).click();
		waitPageLoadEnds();
	}

	public int returnOneRowInTableFindByText(List<String> textsInTable) {
		return returnOneRowInTableFindByText(textsInTable, null);
	}
	
	public int returnOneRowInTableFindByText(List<String> textsInTable, String idTable) {
		List<String> list = new ArrayList<String>();

		for (String text : textsInTable) {
			list.add(text);
		}
		List<WebElement> row;
		if (idTable == null) {
			row = driver.findElements(By.cssSelector("table > tbody > tr"));
		} else {
			row = driver.findElements(By.cssSelector("table#" + idTable + " > tbody > tr"));
		}

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(row.get(0)));

		int validacao = 0, a = 1;
		for (WebElement webElement : row) {
			for (int i = 0; i < list.size(); i++) {
				if (webElement.getText().contains(list.get(i))) {
					validacao++;
					if (validacao == list.size()) {
						return a;
					}
				}
			}
			validacao = 0;
			a++;
		}

		return 0;
	}
	
	public int returnOneRowInTableFindByCssSelectorText(List<String> textsInTable, String cssSelector) {
		List<String> list = new ArrayList<String>();
		
		for (String text : textsInTable) {
			list.add(text);
		}
		List<WebElement> row;
		
		row = driver.findElements(By.cssSelector(cssSelector));
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(row.get(0)));
		
		int validacao = 0, a = 1;
		for (WebElement webElement : row) {
			for (int i = 0; i < list.size(); i++) {
				if (webElement.getText().contains(list.get(i))) {
					validacao++;
					if (validacao == list.size()) {
						return a;
					}
				}
			}
			validacao = 0;
			a++;
		}
		
		return 0;
	}
	
	public int returnOneRowInTableFindByTable(List<String> textsInTable, WebElement table) {
		
		List<String> list = new ArrayList<String>();
		
		for (String text : textsInTable) {
			list.add(text);
		}
		
		List<WebElement> row;
				
		row = table.findElements(By.cssSelector("tbody > tr"));
		
		if(row.isEmpty()){
			return 0;
		}
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(row.get(0)));
		
		int validacao = 0, a = 1;
		for (WebElement webElement : row) {
			for (int i = 0; i < list.size(); i++) {
				if (webElement.getText().contains(list.get(i))) {
					validacao++;
					if (validacao == list.size()) {
						return a;
					}
				}
			}
			validacao = 0;
			a++;
		}
		
		return 0;
	}
	
	protected int returnOneRowInTableFindByTableSpecificColumn(String textInTable, String idTable){
		
		List<WebElement> column;
		if (idTable == null) {
			column = driver.findElements(By.cssSelector("table > tbody > tr > td:nth-child(2)"));
		} else {
			column = driver.findElements(By.cssSelector("table#" + idTable + " > tbody > tr > td:nth-child(2)"));
		}

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(column.get(0)));

		int a = 1;
		for (WebElement webElement : column) {
				if (webElement.getText().equals(textInTable)) {
						return a;
				}
			a++;
		}

		return 0;
	}

	protected WebElement findTableReturnRowAndColumn(int rowNumber, int columnNumber) {
		return findTableReturnRowAndColumn("", rowNumber, columnNumber);
	}

	protected WebElement findTableReturnRowAndColumn(String idOfElement, int rowNumber, int columnNumber) {
		if (idOfElement.equals("")) {
			return findByCssSelector("table > tbody > tr:nth-child(" + rowNumber + ") > td:nth-child(" + columnNumber + ")");
		} else {
			return findByCssSelector(findById(idOfElement), "tbody > tr:nth-child(" + rowNumber + ") > td:nth-child(" + columnNumber + ")");
		}
	}

	protected void tableSetForNotification(int rowNumber) {
		findByCssSelector(findTableReturnRowAndColumn(rowNumber, 1), "input[type='checkbox']").click();
	}
	
	protected WebElement findTableReturnRowAndColumn(WebElement webElement, int rowNumber, int columnNumber) {
		return findByCssSelector(webElement, "table > tbody > tr:nth-child(" + rowNumber + ") > td:nth-child(" + columnNumber + ")");
	}

	// METODOS QUE PEGAM ALGUM VALOR NA PAGINA
	protected String getValueOfElement(String idElement) {
		return findById(idElement).getAttribute("value");
	}
	
	protected String getElement(String cssSelector) {
		return findByCssSelector(cssSelector).getText();
	}

	protected String getTextOfElement(String idElement) {
		return findById(idElement).getText();
	}
	
	public String getValueFromSelect(WebElement element, String compareText) {
        List<WebElement> options = new Select(element).getAllSelectedOptions(); 
        
        for (WebElement option : options){
        	if (option.getText().contains(compareText)){
        		return option.getText();
        	}
        }
    	return null;
	}
	
	// TODO - Tentando pegar o elemento Hidden ou pela URL
	protected String getOneElementAtUrl() {
		String urlCurrent = driver.getCurrentUrl();
						
		return urlCurrent.substring(urlCurrent.indexOf("edit/") + 5);
		
//		WebElement hiddenDiv = findById("serviceOrder_id");
//		String script = "return $('#hidden_div').text();";
//		return (String) ((JavascriptExecutor) driver).executeScript(script, hiddenDiv);
		
	}

	// METODOS QUE ENVIAM KEYS DO TECLADO PARA PAGINA
	protected void pressEnter(String idElement) {
		findById(idElement).sendKeys(Keys.ENTER);
	}
	
	protected void pressEnter(WebElement element) {
		element.sendKeys(Keys.ENTER);
	}

	protected void pressTab(String cssSelectorElement) {
		findByCssSelector(cssSelectorElement).sendKeys(Keys.TAB);
	}
	
	// METODOS QUE AGUARDAM UMA AÇÃO NA PAGINA
	protected void waitAjaxEnd(long milliseconds) {
		waitAjaxEnd();
		waitOneTimeInPage(milliseconds);
	}
	
	protected void waitAjaxEnd() {
		LOG.debug("Esperando o ajax terminar de carregar");
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0;");
			}
		});
	}

	protected void waitPageLoadEnds() {
		LOG.debug("Esperando a página terminar de carregar");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pace-done")));
	}

	protected WebElement waitElementToBeClickable(WebElement webElement) {
		LOG.debug("Esperando o elemento " + webElement + " ser clicavel");
		return wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	protected WebElement waitVisibilitOfElement(WebElement webElement) {
		LOG.debug("Espera o elemento " + webElement + " estar visível");
		return wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	private void waitTextToBePresentInElementLocated(By by, String textExpected) {
		LOG.debug("Espera o texto " + textExpected + " estar presente no elemento " + by);
		wait.until(ExpectedConditions.textToBePresentInElementLocated(by, textExpected));
	}

	protected void waitOneTimeInPage(long milliseconds) {
		HtmlUtils.waitOneTimeInPage(driver, milliseconds);
	}

	// OUTROS METODOS
	public void visitURL(String url) {
		LOG.debug("Indo para a url " + url);
		driver.get(url);
	}
	
	public void visitURLAndValidateLocation(String url) {
		visitURL(url);
		validateUrlContains(url);
	}
	
	public void logoutWithAcceptAlert() {
		clickImage();
		clickByCssSelector("div.dropdown-menu.dropdown-menu-md.dropdown-menu-right.with-arrow.panel-default a.btn.btn-primary");
		acceptMessageAlert();
		waitPageLoadEnds();
	}

	private static Boolean comparador(Boolean comp1, Boolean comp2) {
		if (comp1 == null)
			return comp2;
		if (comp2 == null)
			return comp1;
		if (!comp1 && !comp2)
			return false;
		else
			return true;
	}
	
	protected void tab(String cssElement) {
		 ((JavascriptExecutor) driver).executeScript("$('" + cssElement + "').trigger('change'); return true;");
	}
	
	public void acceptMessageAlert() {
		driver.switchTo().alert().accept();
	}
	
	public String getMessageAlert() {
		return driver.switchTo().alert().getText();
	}
}
