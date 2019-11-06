package careman.html.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.login.ForgottenPage;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.user.UserChangeSecretPhrasePage;
import br.com.workfinity.web.page.user.UserCrudFormPage;
import br.com.workfinity.web.page.user.UserShowPage;
import careman.html.TestBase;

public class Permission extends TestBase {

	private static final String ANSWER = "ANSWER";
	private static final String SEM_PERMISAO = "SEM_PERMISAO";
	private StringBuffer verificationErrors = new StringBuffer();
	private MainPage navBar;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL, false);
		navBar = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	@Test
	public void createUserAndLogin() {
		createUser();
		new LoginPage(getDriver(), SEM_PERMISAO, "123456").buttonSignInSuccess();
	}

	private void createUser() {
		getDriver().get(getBaseUrl());
		new LoginPage(getDriver(), "admin", "Iso1981").buttonSignInSuccess();

		UserCrudFormPage crudForm = navBar.visitUserManager().buttonNew();

		crudForm.setUsername(SEM_PERMISAO);
		crudForm.setNewPassword("123456");
		crudForm.setRepeatNewPassword("123456");
		crudForm.setName("Sem Permisao");
		crudForm.setLanguage("English, en_US (English)");
		crudForm.setProfile("Administrator");
		UserShowPage show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();

		show.clickSavePermission();
		show.validateMessageSuccessSaved();
		show.logout();
	}

	@Test(dependsOnMethods = { "createUserAndLogin" })
	public void userNoPermission() throws Exception {
		UserChangeSecretPhrasePage userChangePhrase = navBar.clickChangeSecretPhrase();
		userChangePhrase.setCurrentPassword("123456").setSecretQuestion("AaAa").setSecretAnswer(ANSWER).buttonOk();

		navBar.searchAnyoneMenu();
		
		
		Page page = new Page(getDriver()) {
		};
		
		getDriver().get(getBaseUrl() + "/appConfig/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/activity/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/contractor/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/customer/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/defect/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/equipment/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/family/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/manufacturer/createAjax");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/manufacturer/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/message/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/model/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/openingHoursGroup/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/reasonForCancellation/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/recess/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/repairOrder/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/requestPieces/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/service/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/serviceGroup/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/serviceLevelAgreement/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/serviceOrder/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/serviceProvider/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/solution/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/step/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/stepStatus/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/user/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/workflow/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/dashboard/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/customField/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/defectLaboratory/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/deliverPieces/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/genericContract/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/importProcess/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/location/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/notification/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/openingHours/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/platform/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/priorityGroup/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/productionPlanning/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/profileSettings/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/project/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/qualityEvaluation/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/repairLevel/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/routingGroup/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/serviceAreas/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/serviceLevelAgreementGroup/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/shiftGroup/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/solutionLaboratory/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/stock/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/stockPlanning/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/target/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/compositeTemplatesAndTarget/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/template/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/trackingLog/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/warrantyProvider/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/carrier/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/inventory/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/shipmentOrder/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/shipmentOrderType/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/exportBanrisul/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/importProcessBanrisul/payment");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/importProcessBanrisul/upload");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/consumptionReport/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/serviceOrderConsumptionReport/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/reportLaboratoryBacklog/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/reportRepairContractorAverage	/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/reportSummaryRepairOrder/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/reportSummaryRepairOrderXShipmentOrderClosed/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/reportTotalBadEquipmentByRange/index");
		page.validateAccessDenied();
		
		getDriver().get(getBaseUrl() + "/shipmentClosedAndRepairOrder/index");
		page.validateAccessDenied();
		
		page.logout();
	}

	@Test(dependsOnMethods = { "userNoPermission" })
	public void forgottenPassword() throws Exception {
		LoginPage loginPage = new LoginPage(getDriver(), SEM_PERMISAO, "123456");
		
		ForgottenPage forgottenPage = loginPage.clickForgottenPassword();
		
		forgottenPage.setUsername(SEM_PERMISAO).buttonSearch();
		
		forgottenPage.setAnswer("batatinha").setNewPassword("987654").setRepeatNewPassword("987654").buttonOk();
		
		forgottenPage.validateMessage("Secret answer wrong");
		forgottenPage.setAnswer(ANSWER).buttonOk();

		loginPage = new LoginPage(getDriver(), SEM_PERMISAO, "987654");
		loginPage.validateMessageSuccess("Password changed successfully");
		loginPage.buttonSignInSuccess();
	}
}
