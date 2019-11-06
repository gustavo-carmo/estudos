package wkf3.web.gerenciar.workPack;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.equipment.EquipmentCrudFormPage;
import br.com.workfinity.web.page.equipment.EquipmentShowPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.stock.StockControlListPage;
import br.com.workfinity.web.page.workPack.WorkPackCrudForm;
import br.com.workfinity.web.page.workPack.WorkPackListPage;
import careman.html.TestBase;

public class WorkPackEquipmentTest extends TestBase {

	private static final String WORKPACK_USER = "USER_WORKPACK_EQUIP_" + randomString(5);
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String SERVICE_PROVIDER_GENERIC_FILIAL = "SERVICE_PROVIDER_GENERIC_FILIAL";
	private static final String MANUFACTORE_GENERIC = "MANUFACTORE_GENERIC";
	private static final String MODEL_GENERIC_PRODUCT = "MODEL_GENERIC_PRODUCT";
	private static final String MODEL_GENERIC_ACCESSORY = "MODEL_GENERIC_ACCESSORY";
	private static final String MODEL_GENERIC_SUPLY = "MODEL_GENERIC_SUPLY";
	private static final String TECHNICIAN_MATRIZ_01 = "TECHNICIAN_MATRIZ_01";
	private static final String TECHNICIAN_MATRIZ_02 = "TECHNICIAN_MATRIZ_02";
	private static final String TECHNICIAN_FILIAL_01 = "TECHNICIAN_FILIAL_01";
	private static final String TECHNICIAN_FILIAL_02 = "TECHNICIAN_FILIAL_02";

	private static final List<String> ROLES = Arrays.asList("ROLE_USER_VIEW_ALL_SERVICE_ORDERS",
			"ROLE_SERVICE_PROVIDER", "ROLE_WORK_PACK", "ROLE_STOCK", "ROLE_STOCK_PLANNING_MANAGEMENT",
			"ROLE_STOCK_PLANNING_VIEW", "ROLE_VIEW_OTHER_STOCK", "ROLE_EQUIPMENT", "ROLE_EQUIPMENT_ADMIN",
			"ROLE_EQUIPMENT_ORIGIN");

	private MainPage navBar;
	private WorkPackListPage workPackListPage;
	private WorkPackCrudForm workPackCrudForm;
	private StockControlListPage stockControlListPage;
	private String codeWorkPackAddEquipment;

	public String codeWorkPackPSFilial;
	public String codeWorkPackProdutoParte;
	public String codeWorkPackAcessorioInsumo;
	public String serialNumberProduct;
	public String dataFechamento;

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
	public void backgroundScriptsWorkPackEquipment() {

		// Número de Série do Equipamento do Tipo Produto
		serialNumberProduct = generateLengthNumber(8);

		// Criando o Equipamento do Tipo Produto
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), CONTRACTOR_GENERIC, "", "", SERVICE_PROVIDER_GENERIC_MATRIZ,
				MANUFACTORE_GENERIC, MODEL_GENERIC_PRODUCT, "GOOD", serialNumberProduct, null, false);

		// Criando o Equipamento do Tipo Acessório
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "", "", SERVICE_PROVIDER_GENERIC_MATRIZ,
				MANUFACTORE_GENERIC, MODEL_GENERIC_ACCESSORY, "GOOD", "", 20, false);

		// Criando o Equipamento do Tipo Insumo
		LoadDataHelper.createEquipment(getDriver(), getBaseUrl(), "", "", "", SERVICE_PROVIDER_GENERIC_MATRIZ,
				MANUFACTORE_GENERIC, MODEL_GENERIC_SUPLY, "GOOD", "", 20, false);

		// Criando e Logando com o Usuário
		createUserAndDoLogin(WORKPACK_USER, "123456", ROLES, "pt_BR");
	}

	@Test(dependsOnMethods = { "backgroundScriptsWorkPackEquipment" })
	public void adicionandoComSucessoUmProdutoParteNumWorkPack() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();

		workPackCrudForm.validateMessageSuccessCreated();

		codeWorkPackAddEquipment = workPackCrudForm.getCodeWorkPack();

		workPackCrudForm.buttonModalProductOrPart();
		workPackCrudForm.setSerialNumber(serialNumberProduct);
		workPackCrudForm.buttonAddProductOrPart();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");

		workPackCrudForm.buttonCloseModalServiceOrderAndProductPart();
	}

	@Test(dependsOnMethods = { "adicionandoComSucessoUmProdutoParteNumWorkPack" })
	public void removendoComSucessoUmProdutoParteNumWorkPack() {

		workPackCrudForm.checkedCheckboxToDeleteItemInTable();
		workPackCrudForm.buttonDeleteEquipament();

		workPackCrudForm.validateNotContainsRowInTable(Arrays.asList(serialNumberProduct),
				"#equipments-containers .table.table-striped.table-bordered.dataTable.table-condensed",
				"Foi encontrado o equipamento Produto na tabela");
	}

	@Test(dependsOnMethods = { "removendoComSucessoUmProdutoParteNumWorkPack" })
	public void adicionandoComSucessoUmAcessorioInsumoNumWorkPack() {

		// Verificando se o Equipamento "Acessório" esta no Estoque
		stockControlListPage = navBar.visitStockControl();

		stockControlListPage.setModelType("Acessório");
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(MODEL_GENERIC_ACCESSORY, "20"));

		// Adicionando o Equipamento "Acessório" no WorkPack
		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPackAddEquipment);

		workPackCrudForm.buttonModalAcessoryOrSupply();
		workPackCrudForm.setModel(MODEL_GENERIC_ACCESSORY);
		workPackCrudForm.setAmount("10");
		workPackCrudForm.buttonAddAcessoryOrSupply();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");

		workPackCrudForm.buttonCloseModalAcessorySupply();

		// Verificando se o Equipamento "Acessorio" foi reduzido no Estoque
		stockControlListPage = navBar.visitStockControl();

		stockControlListPage.setModelType("Acessório");
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(MODEL_GENERIC_ACCESSORY, "10"));
	}

	@Test(dependsOnMethods = { "adicionandoComSucessoUmAcessorioInsumoNumWorkPack" })
	public void removendoComSucessoUmAcessorioInsumoNumWorkPack() {

		// Deletando o Equipamento "Acessorio" do WorkPack
		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPackAddEquipment);

		workPackCrudForm.checkedCheckboxToDeleteItemInTable();
		workPackCrudForm.buttonDeleteEquipament();

		workPackCrudForm.validateNotContainsRowInTable(Arrays.asList(MODEL_GENERIC_ACCESSORY),
				"#equipments-containers .table.table-striped.table-bordered.dataTable.table-condensed",
				"Foi encontrado o equipamento Acessorio na tabela");

		// Verificando se o Equipamento "Acessorio" voltou ao total no Estoque
		stockControlListPage = navBar.visitStockControl();

		stockControlListPage.setModelType("Acessório");
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(MODEL_GENERIC_ACCESSORY, "20"));
	}

	@Test(dependsOnMethods = { "removendoComSucessoUmAcessorioInsumoNumWorkPack" })
	public void adicionandoComSucessoUmEquipamentoQuandoOPrestadorDoWorkPackNaoTemEstoqueProprioEEUmaFilial() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_FILIAL);
		workPackCrudForm.buttonCreate();

		workPackCrudForm.validateMessageSuccessCreated();

		codeWorkPackPSFilial = workPackCrudForm.getCodeWorkPack();

		workPackCrudForm.buttonModalAcessoryOrSupply();
		workPackCrudForm.setModel(MODEL_GENERIC_SUPLY);
		workPackCrudForm.setAmount("10");
		workPackCrudForm.buttonAddAcessoryOrSupply();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");

		workPackCrudForm.buttonCloseModalAcessorySupply();

		// Verificando se o Equipamento "Insumo" foi reduzido no Estoque
		stockControlListPage = navBar.visitStockControl();

		stockControlListPage.setModelType("Insumo");
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(MODEL_GENERIC_SUPLY, "10"));
	}

	@Test(dependsOnMethods = {
			"adicionandoComSucessoUmEquipamentoQuandoOPrestadorDoWorkPackNaoTemEstoqueProprioEEUmaFilial" })
	public void removendoComSucessoUmAcessorioInsumoDumWorkPackQuandoOPrestadorDoWorkPackNaoTemEstoqueProprioEEmaFilial() {

		// Deletando o Equipamento "Insumo" do WorkPack
		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPackPSFilial);

		workPackCrudForm.checkedCheckboxToDeleteItemInTable();
		workPackCrudForm.buttonDeleteEquipament();

		workPackCrudForm.validateNotContainsRowInTable(Arrays.asList(MODEL_GENERIC_SUPLY),
				"#equipments-containers .table.table-striped.table-bordered.dataTable.table-condensed",
				"Foi encontrado um equipamento Acessorio na tabela");

		// Verificando se o Equipamento "Insumo" voltou ao total no Estoque
		stockControlListPage = navBar.visitStockControl();

		stockControlListPage.setModelType("Insumo");
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(MODEL_GENERIC_SUPLY, "20"));
	}

	@Test(dependsOnMethods = {
			"removendoComSucessoUmAcessorioInsumoDumWorkPackQuandoOPrestadorDoWorkPackNaoTemEstoqueProprioEEmaFilial" })
	public void deveraDarErroQuandoTentoMudarDoStatusAbertoParaEntregueNumWorkPackInvalido() {

		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPackPSFilial);

		workPackCrudForm.setStatus("Entregue");
		workPackCrudForm.buttonUpdate();
		workPackCrudForm.validateMessageError("Campo \"Técnico\" é requerido",
				"Para realizar a entrega é necessário ter Ordens de Serviço e/ou Equipamentos");
	}

	@Test(dependsOnMethods = { "deveraDarErroQuandoTentoMudarDoStatusAbertoParaEntregueNumWorkPackInvalido" })
	public void cancelandoUmWorkPackSemDados() {

		workPackCrudForm.setStatus("Cancelado");
		workPackCrudForm.buttonUpdateCancelled();

		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "cancelandoUmWorkPackSemDados" })
	public void cancelandoUmWorkPackComProdutoParte() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		workPackCrudForm.buttonModalProductOrPart();
		workPackCrudForm.setSerialNumber(serialNumberProduct);
		workPackCrudForm.buttonAddProductOrPart();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");

		workPackCrudForm.buttonCloseModalServiceOrderAndProductPart();

		workPackCrudForm.setStatus("Cancelado");
		workPackCrudForm.buttonUpdateCancelled();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "cancelandoUmWorkPackComProdutoParte" })
	public void cancelandoUmWorkPackComAcessorioInsumo() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		workPackCrudForm.buttonModalAcessoryOrSupply();
		workPackCrudForm.setModel(MODEL_GENERIC_ACCESSORY);
		workPackCrudForm.setAmount("10");
		workPackCrudForm.buttonAddAcessoryOrSupply();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");

		workPackCrudForm.buttonCloseModalAcessorySupply();

		workPackCrudForm.setStatus("Cancelado");
		workPackCrudForm.buttonUpdateCancelled();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");

		// Verificando se o Equipamento "Acessorio" voltou ao Estoque
		stockControlListPage = navBar.visitStockControl();

		stockControlListPage.setModelType("Acessório");
		stockControlListPage.buttonSearch();

		stockControlListPage.validateIfContainsTheRowInTable(Arrays.asList(MODEL_GENERIC_ACCESSORY, "20"));
	}

	@Test(dependsOnMethods = { "cancelandoUmWorkPackComAcessorioInsumo" })
	public void cancelandoUmWorkPackComTecnico() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_01);
		workPackCrudForm.setStatus("Cancelado");
		workPackCrudForm.buttonUpdateCancelled();

		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "cancelandoUmWorkPackComTecnico" })
	public void entregandoUmWorkPackComSucesso() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_FILIAL);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		workPackCrudForm.setTechnician(TECHNICIAN_FILIAL_01);
		workPackCrudForm.setStatus("Entregue");

		workPackCrudForm.buttonModalAcessoryOrSupply();
		workPackCrudForm.setModel(MODEL_GENERIC_SUPLY);
		workPackCrudForm.setAmount("10");
		workPackCrudForm.buttonAddAcessoryOrSupply();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");

		workPackCrudForm.buttonCloseModalAcessorySupply();

		workPackCrudForm.buttonUpdate();

		dataFechamento = getCurrentDate();

		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");

		workPackCrudForm.validateIfDeliveredDateIsComplete(dataFechamento);

		workPackCrudForm.validateIfDeliveredByIsComplete(WORKPACK_USER);
	}

	@Test(dependsOnMethods = { "entregandoUmWorkPackComSucesso" })
	public void entregandoUmWorkPackComProdutoParte() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		codeWorkPackProdutoParte = workPackCrudForm.getCodeWorkPack();

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_01);
		workPackCrudForm.setStatus("Entregue");

		workPackCrudForm.buttonModalProductOrPart();
		workPackCrudForm.setSerialNumber(serialNumberProduct);
		workPackCrudForm.buttonAddProductOrPart();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");
		workPackCrudForm.buttonCloseModalServiceOrderAndProductPart();

		workPackCrudForm.buttonUpdate();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "entregandoUmWorkPackComProdutoParte" })
	public void entregandoUmWorkPackComAcessorioInsumo() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_FILIAL);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		codeWorkPackAcessorioInsumo = workPackCrudForm.getCodeWorkPack();

		workPackCrudForm.setTechnician(TECHNICIAN_FILIAL_01);
		workPackCrudForm.setStatus("Entregue");

		workPackCrudForm.buttonModalAcessoryOrSupply();
		workPackCrudForm.setModel(MODEL_GENERIC_SUPLY);
		workPackCrudForm.setAmount("10");
		workPackCrudForm.buttonAddAcessoryOrSupply();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");
		workPackCrudForm.buttonCloseModalAcessorySupply();

		workPackCrudForm.buttonUpdate();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "entregandoUmWorkPackComAcessorioInsumo" })
	public void naoAlterandoComSucessoOTecnicoNumWorkPackEntregueQuandoOProdutoParteNaoEstaMaisComOTecnico() {

		// Mudando o produto de prestador
		stockControlListPage = navBar.visitStockControl();

		stockControlListPage.setModelType("Todos");
		stockControlListPage.setSerialNumber(serialNumberProduct);
		stockControlListPage.buttonSearch();

		EquipmentShowPage equipmentShowPage = stockControlListPage.showItemTable(1);
		EquipmentCrudFormPage equipmentCrudFormPage = equipmentShowPage.buttonEdit();

		equipmentCrudFormPage.setTechnician(TECHNICIAN_MATRIZ_02);
		equipmentCrudFormPage.buttonUpdateSuccess();

		equipmentCrudFormPage.validateMessageSuccessUpdate();

		// Mudando o tecnico com falha
		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPackProdutoParte);

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_02);
		workPackCrudForm.buttonUpdate();

		workPackCrudForm.validateMessageError(
				"Não é possível fazer a troca, o Técnico do Equipamento mudou ou a quantidade dispponível não é suficiente");
	}

	@Test(dependsOnMethods = {
			"naoAlterandoComSucessoOTecnicoNumWorkPackEntregueQuandoOProdutoParteNaoEstaMaisComOTecnico" })
	public void naoAlterandoComSucessoOTecnicoNumWorkPackEntregueQuandoOTecnicoNaoTemMaisAQuantidadeDeAcessorioInsumo() {

		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPackAcessorioInsumo);

		workPackCrudForm.setTechnician(TECHNICIAN_FILIAL_02);
		workPackCrudForm.buttonUpdate();

		workPackCrudForm.validateMessageError(
				"Não é possível fazer a troca, o Técnico do Equipamento mudou ou a quantidade dispponível não é suficiente");
	}
}
