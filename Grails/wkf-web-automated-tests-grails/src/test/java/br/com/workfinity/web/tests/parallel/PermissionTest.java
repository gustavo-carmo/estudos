package br.com.workfinity.web.tests.parallel;

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
import careman.html.TestBase;

public class PermissionTest extends TestBase {

	private static final String ANSWER = "ANSWER";
	private static final String SEM_PERMISAO = "SEM_PERMISAO";
	private StringBuffer verificationErrors = new StringBuffer();
	private MainPage navBar;
	private Page page;

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
		createUserAndDoLogin(SEM_PERMISAO, "123456");
	}

	@Test(dependsOnMethods = { "createUserAndLogin" })
	public void userNoPermission() throws Exception {
		UserChangeSecretPhrasePage userChangePhrase = navBar.clickChangeSecretPhrase();
		userChangePhrase.setCurrentPassword("123456").setSecretQuestion("AaAa").setSecretAnswer(ANSWER).buttonOk();
			
		navBar.searchAnyoneMenu();

		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/appConfig/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/activity/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/contractor/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/customer/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/defect/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/equipment/index");
		                                                    
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/family/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/manufacturer/createAjax");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/manufacturer/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/message/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/model/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/openingHoursGroup/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/reasonForCancellation/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/recess/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/repairOrder/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/requestPieces/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/service/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/serviceGroup/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/serviceLevelAgreement/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/serviceOrder/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/serviceProvider/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/solution/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/step/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/stepStatus/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/user/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/workflow/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/dashboard/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/customField/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/defectLaboratory/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/deliverPieces/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/genericContract/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/importProcess/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/location/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/notification/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/openingHours/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/platform/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/priorityGroup/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/productionPlanning/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/profileSettings/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/project/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/qualityEvaluation/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/repairLevel/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/routingGroup/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/serviceAreas/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/serviceLevelAgreementGroup/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/shiftGroup/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/solutionLaboratory/index");
                                                            
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/stock/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/stockPlanning/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/target/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/compositeTemplatesAndTarget/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/template/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/trackingLog/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/warrantyProvider/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/carrier/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/inventory/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/shipmentOrder/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/shipmentOrderType/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/exportBanrisul/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/importProcessBanrisul/payment");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/importProcessBanrisul/upload");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/consumptionReport/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/serviceOrderConsumptionReport/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/reportLaboratoryBacklog/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/reportRepairContractorAverage	/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/reportSummaryRepairOrder/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/reportSummaryRepairOrderXShipmentOrderClosed/index");
		                                                     
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/reportTotalBadEquipmentByRange/index");
                                                             
		visitaUrlEValidaMensagemAcessoNegado(getBaseUrl() + "/shipmentClosedAndRepairOrder/index");

		page.logout();
	}

	private void visitaUrlEValidaMensagemAcessoNegado(String url) {
		page.visitURL(url);
		waitPageLoadEnds();
		page.validateAccessDenied();
	}

	@Test(dependsOnMethods = { "userNoPermission" })
	public void forgottenPassword() throws Exception {
		LoginPage loginPage = new LoginPage(getDriver(), SEM_PERMISAO, "123456");

		ForgottenPage forgottenPage = loginPage.clickForgottenPassword();

		forgottenPage.setUsername(SEM_PERMISAO).buttonSearch();

		forgottenPage.setAnswer("batatinha").setNewPassword("987654").setRepeatNewPassword("987654").buttonOkFail();

		forgottenPage.validateMessage("Secret answer wrong");
		forgottenPage.setAnswer(ANSWER).buttonOk();

		loginPage = new LoginPage(getDriver(), SEM_PERMISAO, "987654");
		loginPage.validateMessageSuccess("Password changed successfully");
		loginPage.buttonSignInSuccess();
	}
}
