package web.workfinity.isolatedTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import web.workfinity.isolatedTest.helpers.PageForm;
import web.workfinity.isolatedTest.helpers.PageSearch;
import web.workfinity.isolatedTest.helpers.PageShow;
import careman.html.TestSuiteHelper;

public class OpeningHours {

	private TestSuiteHelper testSuiteHelper;

	final String MENU_LEVEL_ONE = "Management";
	final String MENU_LEVEL_TWO = "Opening Hours";

	static final String FIELD_START_AT_CREATE = "04:01";
	static final String FIELD_END_AT_CREATE = "04:02";
	static final String FIELD_END_AT_CREATE_LESS_THAN_START_AT = "04:00";
	static final String FIELD_END_AT_CREATE_EQUALS_START_AT = "04:01";

	static final String FIELD_START_AT_UPDATE = "04:10";
	static final String FIELD_END_AT_UPDATE = "04:11";

	static final String FIELD_DAY_OF_WEEK_CREATE = "Tuesday";
	static final String FIELD_DAY_OF_WEEK_UPDATE = "Wednesday";

	static final String ERROR_START_AT_REQUIRED = "Field \"startAt\" is required";
	static final String ERROR_END_AT_REQUIRED = "Field \"endAt\" is required";
	static final String ERROR_END_AT_MUST_BE_AFTER_START_AT = "End At must be after Start At";
	static final String ERROR_DUPLICATE = "Opening Hour duplicated!";
	static final String ERROR_SAME_HOUR_TO_BOTH_FIELDS = "End At must be after Start At";

	final String USERNAME = "openingHoursUser" + TestSuiteHelper.randomString(3);
	final String PASSWORD = "123456";
	final List<String> ROLES = Arrays.asList("ROLE_OPENING_HOURS");

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		this.testSuiteHelper = new TestSuiteHelper(baseURL, gridURL);
		this.testSuiteHelper.createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
	}

	@AfterClass
	public void tearDown() throws Exception {
		this.testSuiteHelper.getWebDriver().quit();
	}

	@Test
	public void start() {
		this.testSuiteHelper.openLeftMenuAndClick(MENU_LEVEL_ONE, MENU_LEVEL_TWO);
	}

	@Test(dependsOnMethods = { "start" })
	public void create() {

		this.testSuiteHelper.linkCreateFromSearch();

		OpeningHoursPageForm pageCreate = new OpeningHoursPageForm(this.testSuiteHelper);
		pageCreate.selectDayOfTheWeak(FIELD_DAY_OF_WEEK_CREATE);
		pageCreate.save();
		pageCreate.checkFieldDayOfWeak(FIELD_DAY_OF_WEEK_CREATE.toUpperCase());
		pageCreate.checkPresenceOfErrors(ERROR_START_AT_REQUIRED, ERROR_END_AT_REQUIRED);

		pageCreate.setStartAt(FIELD_START_AT_CREATE);
		pageCreate.save();
		pageCreate.checkPresenceOfErrors(ERROR_END_AT_REQUIRED);
		pageCreate.checkFieldStartAt(FIELD_START_AT_CREATE);

		pageCreate.setEndAt(FIELD_END_AT_CREATE_LESS_THAN_START_AT);
		pageCreate.save();
		pageCreate.checkPresenceOfErrors(ERROR_END_AT_MUST_BE_AFTER_START_AT);

		pageCreate.setEndAt(FIELD_END_AT_CREATE_EQUALS_START_AT);
		pageCreate.save();
		pageCreate.checkPresenceOfErrors(ERROR_SAME_HOUR_TO_BOTH_FIELDS);

		pageCreate.setEndAt(FIELD_END_AT_CREATE);
		pageCreate.save();
		pageCreate.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void showAfterCreate() {

		OpeningHoursPageShow pageShow = new OpeningHoursPageShow(this.testSuiteHelper);

		pageShow.checkOpeningHoursDay(FIELD_DAY_OF_WEEK_CREATE);
		pageShow.checkStartAt(FIELD_START_AT_CREATE);
		pageShow.checkEndA(FIELD_END_AT_CREATE);
	}

	@Test(dependsOnMethods = { "showAfterCreate" })
	public void edit() {
		this.testSuiteHelper.linkEditFromShow();

		OpeningHoursPageForm pageEdit = new OpeningHoursPageForm(testSuiteHelper);

		pageEdit.selectDayOfTheWeak(FIELD_DAY_OF_WEEK_UPDATE);
		pageEdit.setStartAt(FIELD_START_AT_UPDATE);
		pageEdit.setEndAt(FIELD_END_AT_UPDATE);
		pageEdit.save();
		pageEdit.validateMessageSuccessUpdated();

	}

	@Test(dependsOnMethods = { "edit" })
	public void showAfterUpdate() {

		OpeningHoursPageShow pageShow = new OpeningHoursPageShow(this.testSuiteHelper);

		pageShow.checkOpeningHoursDay(FIELD_DAY_OF_WEEK_UPDATE);
		pageShow.checkStartAt(FIELD_START_AT_UPDATE);
		pageShow.checkEndA(FIELD_END_AT_UPDATE);
	}

	@Test(dependsOnMethods = { "showAfterUpdate" })
	public void search() {
		
		this.testSuiteHelper.linkSearchFromShow();

		OpeningHoursPageSearch pageSearch = new OpeningHoursPageSearch(this.testSuiteHelper);
		pageSearch.selectDayOfTheWeak(FIELD_DAY_OF_WEEK_UPDATE);
		pageSearch.execute();
		
		pageSearch.resultValidateColumnValue(Arrays.asList(FIELD_DAY_OF_WEEK_UPDATE));

		Map<String, Object> searchInResult = pageSearch.resultFindRow(Arrays.asList(FIELD_DAY_OF_WEEK_UPDATE, FIELD_START_AT_UPDATE,
				FIELD_END_AT_UPDATE));
		Assert.assertNotNull(searchInResult);

		Integer rowNumber = (Integer) searchInResult.get("rowNumber");

		pageSearch.show(rowNumber);

		OpeningHoursPageShow pageShow = new OpeningHoursPageShow(this.testSuiteHelper);
		pageShow.checkOpeningHoursDay(OpeningHours.FIELD_DAY_OF_WEEK_UPDATE);
		pageShow.checkStartAt(OpeningHours.FIELD_START_AT_UPDATE);
		pageShow.checkEndA(OpeningHours.FIELD_END_AT_UPDATE);

		this.testSuiteHelper.linkSearchFromShow();

		pageSearch.edit(rowNumber);

		OpeningHoursPageForm pageEdit = new OpeningHoursPageForm(this.testSuiteHelper);
		pageEdit.checkFieldDayOfWeak(OpeningHours.FIELD_DAY_OF_WEEK_UPDATE.toUpperCase());
		pageEdit.checkFieldStartAt(OpeningHours.FIELD_START_AT_UPDATE);
		pageEdit.checkFieldEndAt(OpeningHours.FIELD_END_AT_UPDATE);
	}

	@Test(dependsOnMethods = { "search" })
	public void duplicatedOpeningHours() {

		this.testSuiteHelper.linkCreateFromEdit();

		OpeningHoursPageForm pageCreate = new OpeningHoursPageForm(this.testSuiteHelper);
		pageCreate.setStartAt(FIELD_START_AT_UPDATE);
		pageCreate.setEndAt(FIELD_END_AT_UPDATE);
		pageCreate.selectDayOfTheWeak(FIELD_DAY_OF_WEEK_UPDATE);
		pageCreate.save();
		pageCreate.checkPresenceOfErrors(ERROR_DUPLICATE);
	}

	@Test(dependsOnMethods = { "duplicatedOpeningHours" })
	public void delete() {

		this.testSuiteHelper.linkSearchFromCreate();

		OpeningHoursPageSearch pageSearch = new OpeningHoursPageSearch(this.testSuiteHelper);

		Map<String, Object> searchInResult = pageSearch.resultFindRow(Arrays.asList(FIELD_DAY_OF_WEEK_UPDATE, FIELD_START_AT_UPDATE,
				FIELD_END_AT_UPDATE));
		Integer rowNumber = (Integer) searchInResult.get("rowNumber");

		pageSearch.delete(rowNumber);

		Assert.assertNull(pageSearch.resultFindRow(Arrays.asList(FIELD_DAY_OF_WEEK_UPDATE, FIELD_START_AT_UPDATE, FIELD_END_AT_UPDATE)));
	}
}

class OpeningHoursPageForm extends PageForm {

	public OpeningHoursPageForm(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);
	}

	public void setStartAt(String value) {
		if (null != value) {
			WebElement startAt = getWebDriver().findElement(By.id("startAt_timepicker"));
			// startAt.clear();
			startAt.sendKeys(value);
		}
	}

	public void setEndAt(String value) {
		if (null != value) {
			WebElement endAt = getWebDriver().findElement(By.id("endAt_timepicker"));
			// endAt.clear();
			endAt.sendKeys(value);
		}
	}

	public void selectDayOfTheWeak(String dayOfTheWeak) {
		new Select(getWebDriver().findElement(By.id("dayOfWeek"))).selectByVisibleText(dayOfTheWeak);
	}

	public void checkFieldStartAt(String startAt) {
		if (null != startAt) {
			Assert.assertEquals(getWebDriver().findElement(By.id("startAt_timepicker")).getAttribute("value"), startAt);
		}
	}

	public void checkFieldEndAt(String endAt) {
		if (null != endAt) {
			Assert.assertEquals(getWebDriver().findElement(By.id("endAt_timepicker")).getAttribute("value"), endAt);
		}
	}

	public void checkFieldDayOfWeak(String dayOfWeak) {
		if (null != dayOfWeak) {
			Assert.assertEquals(getWebDriver().findElement(By.id("dayOfWeek")).getAttribute("value"), dayOfWeak);
		}
	}

}

class OpeningHoursPageShow extends PageShow {

	public OpeningHoursPageShow(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);
	}

	public void checkOpeningHoursDay(String dayOfWeak) {
		getWebDriverWait().until(
				ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='page-content']/div/div/dl/dd[1]"), dayOfWeak));
	}

	public void checkStartAt(String startAt) {
		getWebDriverWait().until(
				ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='page-content']/div/div/dl/dd[2]"), startAt));

	}

	public void checkEndA(String endAt) {
		getWebDriverWait().until(
				ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='page-content']/div/div/dl/dd[3]"), endAt));
	}

}

class OpeningHoursPageSearch extends PageSearch {

	public OpeningHoursPageSearch(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);
	}	

	public void setName(String paramName) {

		WebElement name = getWebDriver().findElement(By.id("name"));
		name.clear();
		name.sendKeys(paramName);
	}

	public void selectDayOfTheWeak(String dayOfTheWeak) {
		new Select(getWebDriver().findElement(By.id("dayOfWeek"))).selectByVisibleText(dayOfTheWeak);
	}
}