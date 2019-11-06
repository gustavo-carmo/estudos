package web.workfinity.isolatedTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import web.workfinity.isolatedTest.helpers.PageForm;
import web.workfinity.isolatedTest.helpers.PageSearch;
import web.workfinity.isolatedTest.helpers.PageShow;
import careman.html.TestSuiteHelper;

public class OpeningHoursGroup {

	private TestSuiteHelper testSuiteHelper;

	final String MENU_LEVEL_ONE = "Management";
	final String MENU_LEVEL_TWO = "Opening Hours Groups";

	final String USERNAME = "openingHoursGroupUser" + TestSuiteHelper.randomString(3);
	final String PASSWORD = "123456";
	final List<String> ROLES = Arrays.asList("ROLE_OPENING_HOURS");

	final static String FIELD_NAME_CREATE = "OHG Create " + TestSuiteHelper.randomString(6);
	final static List<String> FIELD_DAYS_OF_WEEK_CREATE = Arrays.asList("Holiday", "Monday", "Tuesday", "Thursday", "Friday", "Saturday");
	final static String FIELD_NAME_UPDATE = "OHG Update" + TestSuiteHelper.randomString(6);
	final static List<String> FIELD_DAYS_OF_WEEK_UPDATE = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Saturday");

	final static String ERROR_NAME = "Field \"name\" is required";
	final static String ERROR_OPENING_HOURS = "Field \"openingHours\" is required";
	final static String ERROR_NAME_UNIQUE = "name with value [" + FIELD_NAME_UPDATE + "] must be unique";

	final static String HOUR_START = "00:01";
	final static String HOUR_END = "00:02";
	final static String HOUR_START_HOUR_END = "[" + HOUR_START + "-" + HOUR_END + "]";

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {

		this.testSuiteHelper = new TestSuiteHelper(baseURL, gridURL);
		this.testSuiteHelper.createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
		loadData(false);
	}

	void loadData(boolean run) {

		if (run) {

			String script = "import br.com.careman.domain.*\nimport br.com.careman.groovy.enums.OpeningHoursOptions\nimport groovy.time.TimeDuration\n\ndef startAt = new TimeDuration(0, 1, 0, 0)\ndef endAt = new TimeDuration(0, 2, 0, 0)\n\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.MONDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.TUESDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.WEDNESDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.THURSDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.FRIDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.SATURDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.SUNDAY, startAt: startAt, endAt: endAt).save()\nprintln new OpeningHours(dayOfWeek: OpeningHoursOptions.HOLIDAY, startAt: startAt, endAt: endAt).save()";

			this.testSuiteHelper.loadData(script);

			Assert.assertEquals(this.testSuiteHelper.getWebDriver().findElement(By.id("1")).getText(), "MONDAY [1 minutes - 2 minutes]");
			Assert.assertEquals(this.testSuiteHelper.getWebDriver().findElement(By.id("2")).getText(), "TUESDAY [1 minutes - 2 minutes]");
			Assert.assertEquals(this.testSuiteHelper.getWebDriver().findElement(By.id("3")).getText(), "WEDNESDAY [1 minutes - 2 minutes]");
			Assert.assertEquals(this.testSuiteHelper.getWebDriver().findElement(By.id("4")).getText(), "THURSDAY [1 minutes - 2 minutes]");
			Assert.assertEquals(this.testSuiteHelper.getWebDriver().findElement(By.id("5")).getText(), "FRIDAY [1 minutes - 2 minutes]");
			Assert.assertEquals(this.testSuiteHelper.getWebDriver().findElement(By.id("6")).getText(), "SATURDAY [1 minutes - 2 minutes]");
			Assert.assertEquals(this.testSuiteHelper.getWebDriver().findElement(By.id("7")).getText(), "SUNDAY [1 minutes - 2 minutes]");
			Assert.assertEquals(this.testSuiteHelper.getWebDriver().findElement(By.id("8")).getText(), "HOLIDAY [1 minutes - 2 minutes]");
		}
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

		OpeningHoursGroupPageForm pageCreate = new OpeningHoursGroupPageForm(this.testSuiteHelper);
		pageCreate.save();
		pageCreate.checkPresenceOfErrors(ERROR_NAME, ERROR_OPENING_HOURS);

		pageCreate.setName(FIELD_NAME_CREATE);
		pageCreate.save();
		pageCreate.checkPresenceOfErrors(ERROR_OPENING_HOURS);
		pageCreate.checkName(FIELD_NAME_CREATE);

		pageCreate.setName("");
		pageCreate.setDaysOfWeek(FIELD_DAYS_OF_WEEK_CREATE);
		pageCreate.save();
		pageCreate.checkPresenceOfErrors(ERROR_NAME);
		pageCreate.checkDaysOfWeek(FIELD_DAYS_OF_WEEK_CREATE);

		pageCreate.setName(FIELD_NAME_CREATE);
		pageCreate.setDaysOfWeek(FIELD_DAYS_OF_WEEK_CREATE);
		pageCreate.save();
		pageCreate.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void showAfterCreate() {

		OpeningHoursGroupPageShow pageShow = new OpeningHoursGroupPageShow(this.testSuiteHelper);
		pageShow.checkName(FIELD_NAME_CREATE);
		pageShow.checkDaysOfWeek(FIELD_DAYS_OF_WEEK_CREATE);
	}

	@Test(dependsOnMethods = { "showAfterCreate" })
	public void edit() {

		this.testSuiteHelper.linkEditFromShow();

		OpeningHoursGroupPageForm pageEdit = new OpeningHoursGroupPageForm(testSuiteHelper);

		pageEdit.setName("");
		pageEdit.deselectAllDaysOfWeek();
		pageEdit.save();
		pageEdit.checkPresenceOfErrors(ERROR_NAME, ERROR_OPENING_HOURS);

		pageEdit.setName(FIELD_NAME_UPDATE);
		pageEdit.setDaysOfWeek(FIELD_DAYS_OF_WEEK_UPDATE);
		pageEdit.save();
		pageEdit.validateMessageSuccessUpdated();
	}

	@Test(dependsOnMethods = { "edit" })
	public void showAfterUpdate() {

		OpeningHoursGroupPageShow pageShow = new OpeningHoursGroupPageShow(this.testSuiteHelper);
		pageShow.checkName(FIELD_NAME_UPDATE);
		pageShow.checkDaysOfWeek(FIELD_DAYS_OF_WEEK_UPDATE);
	}

	@Test(dependsOnMethods = { "showAfterUpdate" })
	public void search() {

		this.testSuiteHelper.linkSearchFromShow();

		OpeningHoursGroupPageSearch pageSearch = new OpeningHoursGroupPageSearch(this.testSuiteHelper);

		pageSearch.setName(OpeningHoursGroup.FIELD_NAME_UPDATE);
		pageSearch.execute();
		pageSearch.checkTotalCount("1");
		pageSearch.show(1);

		new OpeningHoursGroupPageShow(this.testSuiteHelper).checkName(OpeningHoursGroup.FIELD_NAME_UPDATE);

		this.testSuiteHelper.linkSearchFromShow();

		pageSearch.edit(1);

		new OpeningHoursGroupPageForm(this.testSuiteHelper).checkName(OpeningHoursGroup.FIELD_NAME_UPDATE);
	}

	@Test(dependsOnMethods = { "search" })
	public void tryCreateDuplicatedItem() {

		this.testSuiteHelper.linkCreateFromEdit();

		OpeningHoursGroupPageForm pageCreate = new OpeningHoursGroupPageForm(this.testSuiteHelper);

		pageCreate.setName(FIELD_NAME_UPDATE);
		pageCreate.setDaysOfWeek(FIELD_DAYS_OF_WEEK_UPDATE);
		pageCreate.save();
		pageCreate.checkName(FIELD_NAME_UPDATE);
		pageCreate.checkDaysOfWeek(FIELD_DAYS_OF_WEEK_UPDATE);
		pageCreate.checkPresenceOfErrors(ERROR_NAME_UNIQUE);
	}

	@Test(dependsOnMethods = { "tryCreateDuplicatedItem" })
	public void delete() {

		this.testSuiteHelper.linkSearchFromCreate();

		OpeningHoursGroupPageSearch pageSearch = new OpeningHoursGroupPageSearch(this.testSuiteHelper);

		pageSearch.delete(1);
		pageSearch.checkTotalCount("0");
	}
}

class OpeningHoursGroupPageForm extends PageForm {

	public OpeningHoursGroupPageForm(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);
	}

	public void setName(String value) {
		if (null != value) {
			WebElement name = getWebDriver().findElement(By.id("name"));
			name.clear();
			name.sendKeys(value);
		}
	}

	public void setDaysOfWeek(List<String> values) {
		if (null != values) {

			for (WebElement tr : getWebDriver().findElements(By.xpath("//table[@id='resultList']/tbody/tr"))) {

				WebElement checkbox = extractCheckbox(values, tr.findElements(By.tagName("td")));

				if (null != checkbox && !checkbox.isSelected()) {
					checkbox.click();
				}
			}
		}
	}

	private WebElement extractCheckbox(List<String> values, List<WebElement> tds) {

		if (values.contains(tds.get(1).getText()) && tds.get(2).getText().equals(OpeningHoursGroup.HOUR_START)
				&& tds.get(3).getText().equals(OpeningHoursGroup.HOUR_END)) {

			return tds.get(0).findElement(By.cssSelector("input[type='checkbox']"));
		}

		return null;
	}

	public void checkName(String name) {
		if (null != name) {
			Assert.assertEquals(getWebDriver().findElement(By.xpath("//input[@id='name']")).getAttribute("value"), name);
		}
	}

	public void checkDaysOfWeek(List<String> values) {
		if (null != values) {

			List<String> valuesNotFound = new ArrayList<String>();

			for (WebElement tr : getWebDriver().findElements(By.xpath("//table[@id='resultList']/tbody/tr"))) {

				WebElement checkbox = extractCheckbox(values, tr.findElements(By.tagName("td")));
				if (null != checkbox && !checkbox.isSelected()) {
					valuesNotFound.add(tr.getText());
				}
			}

			Assert.assertTrue(valuesNotFound.size() == 0, "Days not found " + valuesNotFound.toString());
		}
	}

	public void deselectAllDaysOfWeek() {
		for (WebElement checkbox : getWebDriver().findElements(By.xpath("//table[@id='resultList']/tbody/tr/td/input[@checked='checked']"))) {
			checkbox.click();
		}
	}
}

class OpeningHoursGroupPageShow extends PageShow {

	public OpeningHoursGroupPageShow(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);
	}

	public void checkName(String name) {
		getWebDriverWait().until(
				ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='page-content']/div/div/dl/dd[1]"), name));
	}

	public void checkDaysOfWeek(List<String> daysOfWeek) {

		List<WebElement> dd = getWebDriver().findElement(By.cssSelector("dl.dl-horizontal")).findElements(By.tagName("dd"));
		dd.remove(0);

		Assert.assertEquals(dd.size(), daysOfWeek.size());

		Iterator<String> daysOfWeekIterator = daysOfWeek.iterator();

		int count = 0;
		while (daysOfWeekIterator.hasNext()) {

			String ddValue = dd.get(count).getText();
			String dayOfWeek = daysOfWeekIterator.next() + " " + OpeningHoursGroup.HOUR_START_HOUR_END;

			Assert.assertEquals(ddValue, dayOfWeek);
			count++;
		}
	}
}

class OpeningHoursGroupPageSearch extends PageSearch {

	public OpeningHoursGroupPageSearch(TestSuiteHelper testSuiteHelper) {
		super(testSuiteHelper);
	}

	public void setName(String paramName) {

		WebElement name = getWebDriver().findElement(By.id("name"));
		name.clear();
		name.sendKeys(paramName);
	}
}