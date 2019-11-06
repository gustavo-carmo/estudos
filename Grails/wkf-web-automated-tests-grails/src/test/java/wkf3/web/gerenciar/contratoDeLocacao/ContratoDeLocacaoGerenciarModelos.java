package wkf3.web.gerenciar.contratoDeLocacao;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.contractRental.ContractRentalCrudForm;
import br.com.workfinity.web.page.contractRental.ContractRentalListPage;
import br.com.workfinity.web.page.contractRental.ContractRentalModels;
import careman.html.TestBase;

public class ContratoDeLocacaoGerenciarModelos extends TestBase {
	
	private static final String SERVICE_PROVIDER_GENERIC_TECHNICIAN = "SERVICE_PROVIDER_GENERIC_TECHNICIAN";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";

	private static final String CONTRACT_RENTAL_MODELS = "CR_MODELS_" + randomString(5);
	private static final String PASSWORD = "123456";

	private MainPage navBar;
	private ContractRentalListPage contractRentalListPage;
	private ContractRentalCrudForm contractRentalCrudForm;
	private ContractRentalModels contractRentalModels;

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
	public void background() {

		createUserAndDoLogin(CONTRACT_RENTAL_MODELS, PASSWORD, Arrays.asList("ROLE_RENTAL_AGREEMENT"), "pt_BR");

		contractRentalListPage = navBar.visitContractRental();

		contractRentalCrudForm = contractRentalListPage.buttonNew();
		contractRentalCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_TECHNICIAN);
		contractRentalCrudForm.setClosingDay("20");
		contractRentalCrudForm.buttonCreate();
		contractRentalCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "background" })
	public void deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeAdicionarUmMModeloNumContratoDeLocacao() {
		
		contractRentalCrudForm.buttonModels();
		contractRentalModels = contractRentalCrudForm.buttonNewModels();

		contractRentalModels.buttonCreateFail();
		contractRentalModels.validateErrorMessage("Campo \"Modelo\" é requerido", "Campo \"Valor\" é requerido");

		contractRentalModels.setModel(MODEL_GENERIC_PRODUCT);
		contractRentalModels.setValue("-1");
		contractRentalModels.buttonCreateFail();
		contractRentalModels.validateErrorMessage("Valor é menor que o valor mínimo de: 1");

		contractRentalModels.setModel(MODEL_GENERIC_PRODUCT);
		contractRentalModels.setValue("1000");
		contractRentalModels.buttonCreateFail();
		contractRentalModels.validateErrorMessage("Valor excede o valor máximo de [999,99]");
	}

	@Test(dependsOnMethods = {
			"deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeAdicionarUmMModeloNumContratoDeLocacao" })
	public void adicionandoUmModeloNumContratoDeLocacaoComSucesso() {
		
		contractRentalModels.setModel(MODEL_GENERIC_PRODUCT);
		contractRentalModels.setValue("500");
		contractRentalCrudForm = contractRentalModels.buttonCreateSuccess();
		contractRentalCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "adicionandoUmModeloNumContratoDeLocacaoComSucesso" })
	public void editandoUmModeloDumContratoDeLocacaoComSucesso() {
		
		contractRentalModels = contractRentalCrudForm.editItemInTable(1);

		contractRentalModels.setValue("750");
		contractRentalCrudForm = contractRentalModels.buttonUpdateSuccess();
		contractRentalCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "editandoUmModeloDumContratoDeLocacaoComSucesso" })
	public void removendoUmModeloDumContratoDeLocacao() {
		
		contractRentalCrudForm.deleteItemTableByPosition(1, 1);

		contractRentalCrudForm.validateMessageSuccessDeleted();
	}
}
