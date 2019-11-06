package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.reasonForCancellation.ReasonForCancellationCrudFormPage;
import br.com.workfinity.web.page.reasonForCancellation.ReasonForCancellationListPage;
import br.com.workfinity.web.page.reasonForCancellation.ReasonForCancellationShowPage;
import careman.html.TestBase;

public class ReasonForCancellationTestCRUD extends TestBase {

	private static final String REASON_FOR_CANCELATION_NAME_UPDATE = "REASON FOR CANCELLATION CREATE_UPDATE - "
			+ randomString(5);
	private static final String REASON_FOR_CANCELATION_NAME_CREATE = "REASON FOR CANCELLATION CREATE - "
			+ randomString(5);
	private static final String REASON_FOR_CANCELATION_NAME = "REASON FOR CANCELLATION CREATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_RFC_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_REASON_FOR_CANCELLATION");

	private MainPage navBar;
	private ReasonForCancellationCrudFormPage crudForm;
	private ReasonForCancellationShowPage show;
	private ReasonForCancellationListPage listPage;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, false);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test
	public void login() throws Exception {

		createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void create() {

		createReasonForCancellation(REASON_FOR_CANCELATION_NAME_CREATE);
		createReasonForCancellation(REASON_FOR_CANCELATION_NAME);
	}

	private void createReasonForCancellation(String reasonForCancelationName) {

		crudForm = navBar.visitReasonForCancellation().buttonNew();

		crudForm.setStatus(Messages.DISABLE.toString());
		crudForm.setName(reasonForCancelationName);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		listPage = show.buttonBackToSearch();
		listPage.setName(REASON_FOR_CANCELATION_NAME_CREATE);
		listPage.buttonSearch();

		listPage.editItemTable(1);

		crudForm.setStatus(Messages.ENABLED.toString());
		crudForm.setName(REASON_FOR_CANCELATION_NAME_UPDATE);
		show = crudForm.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {

		listPage = navBar.visitReasonForCancellation();
		listPage.filterExpand();
		listPage.setName("REASON FOR CANCELLATION CREATE");
		listPage.buttonSearch();

		listPage.validateTotalCount("2");
		listPage.filterExpand();
		listPage.setName(REASON_FOR_CANCELATION_NAME_UPDATE);
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setName("");
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ENABLED.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setStatus(Messages.ALL.toString());
		listPage.buttonSearch();
	}

	@Test(dependsOnMethods = { "search" })
	public void delete() {

		deleteReasonForCancellation(REASON_FOR_CANCELATION_NAME_UPDATE);
		deleteReasonForCancellation(REASON_FOR_CANCELATION_NAME);
	}

	private void deleteReasonForCancellation(String reasonForCancellationName) {

		listPage.filterExpand();
		listPage.setName(reasonForCancellationName);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
