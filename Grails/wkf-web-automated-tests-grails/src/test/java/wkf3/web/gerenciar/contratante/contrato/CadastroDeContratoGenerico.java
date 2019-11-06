package wkf3.web.gerenciar.contratante.contrato;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.genericContract.GenericContractCrudFormPage;
import br.com.workfinity.web.page.genericContract.GenericContractShowPage;
import careman.html.TestBase;

public class CadastroDeContratoGenerico extends TestBase {
	
	private static final String CONTRACT = "CONTRACT_GENERIC" + randomString(5);
	private static final List<String> ROLES = Arrays.asList("ROLE_CONTRACTOR", "ROLE_GENERIC_CONTRACT",
			"ROLE_REPORT_REPAIR_CONTRACTOR_AVERAGE", "ROLE_CONTRACT", "ROLE_TEMPLATE");
	
	private static final String CONTRATO = "CONTRATO_GENERICO_" + randomString(5);
	private static final String CONTRATO_UPDATE = "CONTRATO_GENERICO_" + randomString(5);
	
	private static final String DISABLED = "DISABLED";
	private static final String ENABLED = "ENABLED";
	private static final String TEXT = "text";
	private static final String GSP = "GSP";
	
	private static final String TEMPLATE_1 = "TEMPLATE_1_" + GSP + "_" + ENABLED + "_" + randomString(5);
	private static final String TEMPLATE_2 = "TEMPLATE_2_" + GSP + "_" + ENABLED + "_" + randomString(5);
	private static final String TEMPLATE_3 = "TEMPLATE_3_" + GSP + "_" + DISABLED + "_" + randomString(5);
	private static final String TEMPLATE_4 = "TEMPLATE_4_" + TEXT + "_" + ENABLED + "_" + randomString(5);


	private MainPage navBar;
	private GenericContractCrudFormPage crudForm;
	private GenericContractShowPage showPage;
	private String code;
	
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
	public void doLoadData() {
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_1, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_2, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), DISABLED, TEMPLATE_3, GSP);
		LoadDataHelper.createTemplate(getDriver(), getBaseUrl(), ENABLED, TEMPLATE_4, TEXT);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void login() {
		
		createUserAndDoLogin(CONTRACT, "123456", ROLES);
	}
	
	@Test(dependsOnMethods = { "login" })
	public void CadastrandoUmNovoContratoGenerico() {
		crudForm = navBar.visitGenercContract().buttonNew();
		
		crudForm.validateAllTemplates(Arrays.asList(TEMPLATE_1, TEMPLATE_1));
		showPage = crudForm.setName(CONTRATO).setTemplate(TEMPLATE_1).buttonCreate();
		
		showPage.validateMessageSuccessCreated();
		showPage.validateName(CONTRATO).validateTemplate(TEMPLATE_1);
		
		code = showPage.getContractCode(getDriver().getCurrentUrl());
	}
	
	@Test(dependsOnMethods = { "CadastrandoUmNovoContratoGenerico" })
	public void EditandoUmContratoGenericoExistente() {
		crudForm = showPage.buttonEdit();
		
		showPage = crudForm.setTemplate(TEMPLATE_2).setName(CONTRATO_UPDATE).buttonUpdate();
		
		showPage.validateMessageSuccessUpdate();
		showPage.validateName(CONTRATO_UPDATE).validateTemplate(TEMPLATE_2);
	}
	
	@Test(dependsOnMethods = { "CadastrandoUmNovoContratoGenerico" })
	public void undoLoadData() {
		LoadDataHelper.deleteContract(getDriver(), getBaseUrl(), code);
		
		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_1);
		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_2);
		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_3);
		LoadDataHelper.deleteTemplate(getDriver(), getBaseUrl(), TEMPLATE_4);
	}
}
