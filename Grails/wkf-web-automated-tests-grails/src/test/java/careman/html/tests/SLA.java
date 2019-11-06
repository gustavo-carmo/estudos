package careman.html.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import careman.html.TestBase;

public class SLA extends TestBase {

	private StringBuffer verificationErrors = new StringBuffer();
	private ServiceOrderCrudForm serviceOrderCrudForm;
	private MainPage navBar;

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver());
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

	@Test(priority = 1)
	public void createUserAndDoLogin() throws Exception {
		createUserAndDoLogin(randomString(10), "123456", userRoles);
	}

	@Test(priority = 2)
	public void slaBanrisulInstalacaoTrocaTecnologia() throws Exception {
		navBar.visitServiceOrder();
		createServiceOrder("91.728.196/0001-90", "Instalação", "Instalação Venda", "08/04/2013 08:00")
				.validateIfContainsTextInDeadLine("23/04/2013 08:00").validateIfContainsTextInServiceProviderDeadLine("23/04/2013 08:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("44.333.547/0001-84", "Instalação", "Instalação Venda", "08/04/2013 10:00")
				.validateIfContainsTextInDeadLine("23/04/2013 10:00").validateIfContainsTextInServiceProviderDeadLine("23/04/2013 10:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("31.273.370/0001-26", "Troca de Tecnologia", "Troca de Tecnologia (geral)", "05/04/2013 20:00")
				.validateIfContainsTextInDeadLine("20/04/2013 20:00").validateIfContainsTextInServiceProviderDeadLine("20/04/2013 20:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("17.833.290/0001-57", "Instalação", "Instalação Venda", "11/03/2013 10:00")
				.validateIfContainsTextInDeadLine("26/03/2013 10:00").validateIfContainsTextInServiceProviderDeadLine("26/03/2013 10:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("61.799.637/0001-84", "Instalação", "Instalação Venda", "28/03/2013 10:00")
				.validateIfContainsTextInDeadLine("12/04/2013 10:00").validateIfContainsTextInServiceProviderDeadLine("10/04/2013 10:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("02.454.746/0001-09", "Instalação", "Instalação Venda", "09/04/2013 12:00")
				.validateIfContainsTextInDeadLine("24/04/2013 12:00").validateIfContainsTextInServiceProviderDeadLine("22/04/2013 12:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("50.696.552/0001-54", "Instalação", "Instalação Venda", "11/04/2013 10:00")
				.validateIfContainsTextInDeadLine("26/04/2013 10:00").validateIfContainsTextInServiceProviderDeadLine("24/04/2013 10:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("34.567.724/0001-33", "Troca de Tecnologia", "Troca de Tecnologia (geral)", "10/04/2013 20:00")
				.validateIfContainsTextInDeadLine("25/04/2013 20:00").validateIfContainsTextInServiceProviderDeadLine("23/04/2013 20:00")
				.validateMessageSuccessCreated();
	}

	private ServiceOrderCrudForm createServiceOrder(String cnpj, String group, String service, String openingDate) {
		serviceOrderCrudForm = navBar.visitServiceOrder().buttonNew();
		serviceOrderCrudForm.setDocument("CNPJ", cnpj).setServiceGroup(group).setService(service).setStatus("Nova")
				.setOpeningData(openingDate).buttonCreate();

		waitPageLoadEnds();

		return new ServiceOrderCrudForm(getDriver());
	}

	@Test(priority = 3)
	public void slaBanrisulDemaisServicos() throws Exception {

		createServiceOrder("91.728.196/0001-90", "Reconfiguração", "Reconfiguração", "08/04/2013 08:00")
				.validateIfContainsTextInDeadLine("08/04/2013 12:00").validateIfContainsTextInServiceProviderDeadLine("08/04/2013 12:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("31.273.370/0001-26", "Reconfiguração", "Reconfiguração", "08/04/2013 06:00")
				.validateIfContainsTextInDeadLine("08/04/2013 12:00").validateIfContainsTextInServiceProviderDeadLine("08/04/2013 12:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("17.833.290/0001-57", "Reconfiguração", "Reconfiguração", "11/03/2013 10:00")
				.validateIfContainsTextInDeadLine("11/03/2013 14:00").validateIfContainsTextInServiceProviderDeadLine("11/03/2013 14:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("17.833.290/0001-57", "Reconfiguração", "Reconfiguração", "12/03/2013 08:00")
				.validateIfContainsTextInDeadLine("13/03/2013 12:00").validateIfContainsTextInServiceProviderDeadLine("13/03/2013 12:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("34.567.724/0001-33", "Reconfiguração", "Reconfiguração", "10/04/2013 10:00")
				.validateIfContainsTextInDeadLine("12/04/2013 14:00").validateIfContainsTextInServiceProviderDeadLine("12/04/2013 10:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("61.799.637/0001-84", "Reconfiguração", "Reconfiguração", "11/04/2013 23:00")
				.validateIfContainsTextInDeadLine("15/04/2013 10:00").validateIfContainsTextInServiceProviderDeadLine("15/04/2013 08:00")
				.validateMessageSuccessCreated();

		// ===============================================================================================================================================

		createServiceOrder("63.766.854/0001-01", "Reconfiguração", "Reconfiguração", "04/04/2013 10:00")
				.validateIfContainsTextInDeadLine("08/04/2013 12:00").validateIfContainsTextInServiceProviderDeadLine("08/04/2013 10:00")
				.validateMessageSuccessCreated();
	}

}