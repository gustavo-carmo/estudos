package wkf3.web.gerenciar.contratoDeLocacao;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.contractRental.ContractRentalCrudForm;
import br.com.workfinity.web.page.contractRental.ContractRentalListPage;
import careman.html.TestBase;

public class ContratoDeLocacao extends TestBase {
	
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";

	private static final String CONTRACT_RENTAL = "CONTRATO_LOCACAO_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final String CLOSINGDAY = "12";
	private MainPage navBar;
	private ContractRentalListPage contractRentalListPage;
	private ContractRentalCrudForm contractRentalCrudForm;

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
		
		createUserAndDoLogin(CONTRACT_RENTAL, PASSWORD, Arrays.asList("ROLE_RENTAL_AGREEMENT"), "pt_BR");

	}

	@Test(dependsOnMethods = { "background" })
	public void deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeCriarUmContratoDeLocacao() {
		
		contractRentalListPage = navBar.visitContractRental();

		contractRentalCrudForm = contractRentalListPage.buttonNew();

		contractRentalCrudForm.buttonCreate();
		contractRentalCrudForm.validateErrorMessage("Campo \"Prestador de Serviço\" é requerido",
				"Campo \"Dia de fechamento\" é requerido");
	}

	@Test(dependsOnMethods = {
			"deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeCriarUmContratoDeLocacao" })
	public void deveraCriarComSucessoUmContratoDeLocacaoParaUmPrestadorDeServicoQuandoInformoValoresValidos() {
		
		contractRentalCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		contractRentalCrudForm.setClosingDay(CLOSINGDAY);
		contractRentalCrudForm.buttonCreate();
		contractRentalCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = {
			"deveraCriarComSucessoUmContratoDeLocacaoParaUmPrestadorDeServicoQuandoInformoValoresValidos" })
	public void deveraDarErroParaOCampoPrestadorDeServicoQuandoOValorSelecionadoJaTemUmContratoDeLocacao() {
		
		contractRentalListPage = navBar.visitContractRental();

		contractRentalCrudForm = contractRentalListPage.buttonNew();

		contractRentalCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		contractRentalCrudForm.setClosingDay("20");
		contractRentalCrudForm.buttonCreate();
		contractRentalCrudForm
				.validateErrorMessage("Já existe um(a) [Contrato de locação] com o [Prestador de Serviço] informado ["
						+ SERVICE_PROVIDER_GENERIC_MATRIZ + "]");
	}

	@Test(dependsOnMethods = {
			"deveraDarErroParaOCampoPrestadorDeServicoQuandoOValorSelecionadoJaTemUmContratoDeLocacao" })
	public void validandoQueOFiltroDePesquisaRetornaSomenteOsDadosDoFiltroInformado() {
		
		contractRentalListPage = contractRentalCrudForm.buttonBackToSearch();
		
		contractRentalListPage.setCode("1");
		contractRentalListPage.buttonSearch();
		contractRentalListPage.validateIfContainsTheRowInTable(Arrays.asList("1"));

		contractRentalListPage.filterExpand();
		contractRentalListPage.cleanFieldCode();
		contractRentalListPage.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		contractRentalListPage.buttonSearch();
		contractRentalListPage.validateIfContainsTheRowInTable(Arrays.asList(SERVICE_PROVIDER_GENERIC_MATRIZ));

		contractRentalListPage.filterExpand();
		contractRentalListPage.cleanFieldServiceProvider();
		contractRentalListPage.setClosingDays(CLOSINGDAY);
		contractRentalListPage.buttonSearch();
		contractRentalListPage.validateIfContainsTheRowInTable(Arrays.asList(CLOSINGDAY));

		contractRentalListPage.filterExpand();
		contractRentalListPage.cleanFieldClosingDay();
		contractRentalListPage.setStatus("Habilitado");
		contractRentalListPage.buttonSearch();
		contractRentalListPage.validateIfContainsTheRowInTable(Arrays.asList("Habilitado"));
	}

	@Test(dependsOnMethods = { "validandoQueOFiltroDePesquisaRetornaSomenteOsDadosDoFiltroInformado" })
	public void visitandoAPaginaDeEdicaoDumContratoDeLocacao() {
		
		contractRentalCrudForm = contractRentalListPage.editItemTable(1);
	}
}
