package wkf3.web.gerenciar.controleDeEstoque;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.TableUtils.OPTIONS;
import br.com.workfinity.web.page.stock.StockControlListPage;
import careman.html.TestBase;

public class PesquisandoEquipamento extends TestBase {

	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String MODEL_GENERIC_PART = "MODEL_GENERIC_PART";
	private static final String MODEL_GENERIC_ACCESSORY = "MODEL_GENERIC_ACCESSORY";
	private static final String MODEL_GENERIC_SUPLY = "MODEL_GENERIC_SUPLY";
	private static final String MODEL_GENERIC_COMPONENT = "MODEL_GENERIC_COMPONENT";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";

	private static final String USERNAME = "PESQUISA_EQUIPAMENTO_" + randomString(5);

	private MainPage navBar;
	private StockControlListPage listPage;
	private String serialNumberOne;
	private String serialNumberTwo;

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

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD",
				generateLengthNumber(8), null, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_GENERIC_PART, "GOOD",
				generateLengthNumber(8), null, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_GENERIC_COMPONENT, "GOOD", "", 10, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_GENERIC_ACCESSORY, "GOOD", "", 5, false);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_GENERIC_SUPLY, "GOOD", "", 7, false);

		// Gerando o número de série
		serialNumberOne = generateLengthNumber(8);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD", serialNumberOne,
				null, false);

		// Gerando o número de série
		serialNumberTwo = generateLengthNumber(8);

		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "",
				SERVICE_PROVIDER_GENERIC_MATRIZ, MANUFACTORE_GENERIC, MODEL_GENERIC_PART, "GOOD", serialNumberTwo, null,
				false);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void login() throws Exception {

		createUserAndDoLogin(USERNAME, "123456", Arrays.asList("ROLE_STOCK"), "pt_BR");
	}

	@Test(dependsOnMethods = { "login" })
	public void exibindoOsEquipamentosValidosNoResultadoDaTelaDeEstoque() {

		listPage = navBar.visitStockControl();
		listPage.setContractor(CONTRACTOR_GENERIC);
		listPage.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		listPage.buttonSearch();
		
		// Validando se contém a linha na tabela com esses dados:
		listPage.validateIfContainsTheRowInTable(Arrays.asList(CONTRACTOR_GENERIC, SERVICE_PROVIDER_GENERIC_MATRIZ, "Good", "Componente",
				MODEL_GENERIC_COMPONENT, "10", "Habilitado"));
		
		listPage.validateIfContainsTheRowInTable(Arrays.asList(CONTRACTOR_GENERIC, SERVICE_PROVIDER_GENERIC_MATRIZ, "Good", "Acessório",
				MODEL_GENERIC_ACCESSORY, "5", "Habilitado"));
		
		listPage.validateIfContainsTheRowInTable(Arrays.asList(CONTRACTOR_GENERIC, SERVICE_PROVIDER_GENERIC_MATRIZ, "Good", "Insumo",
				MODEL_GENERIC_SUPLY, "7", "Habilitado"));
		
		listPage.validateIfContainsTheRowInTable(Arrays.asList(CONTRACTOR_GENERIC, SERVICE_PROVIDER_GENERIC_MATRIZ, "Good", "Produto",
				MODEL_GENERIC_PRODUCT, serialNumberOne, "Habilitado"));
		
		listPage.validateIfContainsTheRowInTable(Arrays.asList(CONTRACTOR_GENERIC, SERVICE_PROVIDER_GENERIC_MATRIZ, "Good", "Parte",
				MODEL_GENERIC_PART, serialNumberTwo, "Habilitado"));
	}

	@Test(dependsOnMethods = { "exibindoOsEquipamentosValidosNoResultadoDaTelaDeEstoque" })
	public void visualizandoOResultadoDaPesquisaComDados() {

		listPage.clearAllFilter();
		listPage.setSerialNumber(serialNumberTwo);
		listPage.buttonSearch();

		listPage.validateResultHeader(OPTIONS.CHECKBOX, OPTIONS.EMPTY, "Contratante", "Prestador de Serviço", "Cliente",
				"Usuário", "Situação", "Evento que alterou a situação", "Data do evento que alterou a situação", "Tipo",
				"Modelo", "Número de Série", "Quantidade", "PO", "SI", "Localização", "Código da Ordem de Remessa",
				"Status", "Tipo de Remessa", "Ordem de Reparo", "Em garantia");

		listPage.validateResultAction(1, OPTIONS.SHOW);
	}
}
