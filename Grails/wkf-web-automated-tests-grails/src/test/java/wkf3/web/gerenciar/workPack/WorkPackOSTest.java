package wkf3.web.gerenciar.workPack;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.workPack.WorkPackCrudForm;
import br.com.workfinity.web.page.workPack.WorkPackListPage;
import careman.html.TestBase;

public class WorkPackOSTest extends TestBase {

	private static final String WORKPACK_USER = "USER_WORKPACK_OS_" + randomString(5);
	private static final String SERVICE_PROVIDER_GENERIC_MATRIZ = "SERVICE_PROVIDER_GENERIC_MATRIZ";
	private static final String SERVICE_GENERIC = "SERVICE_GENERIC";
	private static final String SERVICE_WITH_TECHNICIAN = "SERVICE_WITH_TECHNICIAN";
	private static final String STATUS_GENERIC_END = "STATUS_GENERIC_END";
	private static final String TECHNICIAN_MATRIZ_01 = "TECHNICIAN_MATRIZ_01";
	private static final String TECHNICIAN_MATRIZ_02 = "TECHNICIAN_MATRIZ_02";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";

	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_ORDER_SERVICE_PROVIDER",
			"ROLE_SERVICE_ORDER_ADMIN", "ROLE_SERVICE_ORDER", "ROLE_WORK_PACK",
			"ROLE_SERVICE_ORDER_UPDATE_CLOSED_DATE_BIGGER_THEN_ALLOWED_IN_SERVICE",
			"ROLE_SERVICE_ORDER_CONSUMPTION_REPORT", "ROLE_SERVICE_ORDER_CREATE", "ROLE_SERVICE_ORDER_NOTES_EDIT",
			"ROLE_SERVICE_ORDER_HISTORY", "ROLE_USER_VIEW_ALL_SERVICE_ORDERS", "ROLE_SERVICE_PROVIDER");

	private MainPage navBar;
	private WorkPackListPage workPackListPage;
	private WorkPackCrudForm workPackCrudForm;
	private ServiceOrderCrudForm serviceOrderCrudForm;
	private String idServiceOrderWithoutStatusTechnician;
	private String idServiceOrderWithStatusTechnician;
	private String idServiceOrderDeliverySuccess;

	public String codeServiceOrderWithoutStatusTechnician;
	public String codeServiceOrderWithStatusTechnician;
	public String codeOSAlterandoComSucesso;
	public String codeWorkPack;
	public String codeWorkPackOSComStatusTecnico;
	public String codeWorkPackOSSemStatusTecnico;

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
	public void backgroundScriptsWorkPackOS() {

		// Criando a Ordem de Serviço Sem Alterar o Seu Status // 1
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER, SERVICE_GENERIC);

		// Pegando o Id da Ordem de Serviço no Console
		idServiceOrderWithoutStatusTechnician = getDriver().findElement(By.tagName("body")).getText();
		
		// Criando a Ordem de Serviço que Altera o Status // 2
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER,
				SERVICE_WITH_TECHNICIAN);

		// Pegando o Id da Ordem de Serviço no Console
		idServiceOrderWithStatusTechnician = getDriver().findElement(By.tagName("body")).getText();

		// Criacao da Ordem de Servico que será entregue com sucesso // 3
		LoadDataHelper.createServiceOrder(getDriver(), getBaseUrl(), "CNPJ", CUSTOMER_DOCUMENT_NUMBER, SERVICE_GENERIC);

		// Pegando o Id da Ordem de Serviço no Console
		idServiceOrderDeliverySuccess = getDriver().findElement(By.tagName("body")).getText();
		
		// Criando e Logando com o Usuário
		createUserAndDoLogin(WORKPACK_USER, "123456", ROLES, "pt_BR");
	}

	@Test(dependsOnMethods = { "backgroundScriptsWorkPackOS" })
	public void backgroundAdicionandoPrestadorDeServicoNasOrdensDeServico() {

		// Acessando a Ordem de Serviço Sem Alterar o Seu Status // 1
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(),
				idServiceOrderWithoutStatusTechnician);

		codeServiceOrderWithoutStatusTechnician = serviceOrderCrudForm.getCode();

		serviceOrderCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		serviceOrderCrudForm.buttonUpdate();
		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Acessando a Ordem de Serviço que Altera o Status // 2
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrderWithStatusTechnician);

		codeServiceOrderWithStatusTechnician = serviceOrderCrudForm.getCode();

		serviceOrderCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		serviceOrderCrudForm.buttonUpdate();
		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Acessando a Ordem de Serviço que será entregue com sucesso // 3
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrderDeliverySuccess);

		codeOSAlterandoComSucesso = serviceOrderCrudForm.getCode();

		serviceOrderCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		serviceOrderCrudForm.buttonUpdate();
		serviceOrderCrudForm.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "backgroundAdicionandoPrestadorDeServicoNasOrdensDeServico" })
	public void deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentativaDeCriarUmaWorkPack() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.buttonCreateFail();
		workPackCrudForm.validateMessageError("Campo \"Prestador de Serviço\" é requerido");
	}

	@Test(dependsOnMethods = {
			"deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentativaDeCriarUmaWorkPack" })
	public void deveraCriarComSucessoUmaWorkPackQuandoInformoValoresValidos() {

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();

		workPackCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "deveraCriarComSucessoUmaWorkPackQuandoInformoValoresValidos" })
	public void adicionandoComSucessoUmaOrdemDeServicoNumWorkPack() {

		workPackCrudForm.buttonModalServiceOrder();
		workPackCrudForm.setCode(codeServiceOrderWithoutStatusTechnician);
		workPackCrudForm.buttonAddServiceOrder();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");

		workPackCrudForm.buttonCloseModalServiceOrderAndProductPart();
	}

	@Test(dependsOnMethods = { "adicionandoComSucessoUmaOrdemDeServicoNumWorkPack" })
	public void cancelandoUmWorkPackComOrdemDeServico() {

		workPackCrudForm.setStatus("Cancelado");
		workPackCrudForm.buttonUpdateCancelled();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "cancelandoUmWorkPackComOrdemDeServico" })
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
	public void entregandoUmWorkPackComOrdemDeServicoEAlterandoSeuStatus() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		codeWorkPackOSComStatusTecnico = workPackCrudForm.getCodeWorkPack();

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_01);
		workPackCrudForm.setStatus("Entregue");

		workPackCrudForm.buttonModalServiceOrder();
		workPackCrudForm.setCode(codeServiceOrderWithStatusTechnician);
		workPackCrudForm.buttonAddServiceOrder();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");
		workPackCrudForm.buttonCloseModalServiceOrderAndProductPart();

		workPackCrudForm.buttonUpdate();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "entregandoUmWorkPackComOrdemDeServicoEAlterandoSeuStatus" })
	public void entregandoUmWorkPackComOrdemDeServicoSemAlterarSeuStatus() {

		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		codeWorkPackOSSemStatusTecnico = workPackCrudForm.getCodeWorkPack();

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_01);
		workPackCrudForm.setStatus("Entregue");

		workPackCrudForm.buttonModalServiceOrder();
		workPackCrudForm.setCode(codeServiceOrderWithoutStatusTechnician);
		workPackCrudForm.buttonAddServiceOrder();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");
		workPackCrudForm.buttonCloseModalServiceOrderAndProductPart();

		workPackCrudForm.buttonUpdate();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "entregandoUmWorkPackComOrdemDeServicoSemAlterarSeuStatus" })
	public void alterandoComSucessoOTecnicoNumWorkPackEntregue() {

		// Indo para o WorkPack para trocar com sucesso o Tecnico
		workPackListPage = navBar.visitWorkPack();
		workPackCrudForm = workPackListPage.buttonNew();

		workPackCrudForm.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackCrudForm.buttonCreate();
		workPackCrudForm.validateMessageSuccessCreated();

		codeWorkPack = workPackCrudForm.getCodeWorkPack();

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_01);
		workPackCrudForm.setStatus("Entregue");

		workPackCrudForm.buttonModalServiceOrder();
		workPackCrudForm.setCode(codeOSAlterandoComSucesso);
		workPackCrudForm.buttonAddServiceOrder();

		workPackCrudForm.validateMessageSuccess(Messages.SUCCESS.toString(), ".modal-body .alert.alert-success");
		workPackCrudForm.buttonCloseModalServiceOrderAndProductPart();

		workPackCrudForm.buttonUpdate();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_02);
		workPackCrudForm.buttonUpdate();
		workPackCrudForm.validateMessageUpdateSucess(Messages.SUCCESS.toString(), "WorkPack atualizado");
	}

	@Test(dependsOnMethods = { "alterandoComSucessoOTecnicoNumWorkPackEntregue" })
	public void naoAlterandoComSucessoOTecnicoNumWorkPackEntregueHaMaisDe15minutos() {

		LoadDataHelper.workPackUpdate(getDriver(), getBaseUrl(), codeWorkPack);

		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPack);

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_01);
		workPackCrudForm.buttonUpdate();
		workPackCrudForm.validateMessageError("Só é permitida a edição dum WorkPack até 15 minutos de sua entrega");
	}

	@Test(dependsOnMethods = { "naoAlterandoComSucessoOTecnicoNumWorkPackEntregueHaMaisDe15minutos" })
	public void naoAlterandoComSucessoOTecnicoNumWorkPackEntregueQuandoAOrdemDeServicoJaMudouDeTecnico() {

		// Indo para a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(), idServiceOrderWithStatusTechnician);

		serviceOrderCrudForm.setServiceProviderTechnician(TECHNICIAN_MATRIZ_02);
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Voltando para o WorkPack
		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPackOSComStatusTecnico);

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_02);
		workPackCrudForm.buttonUpdate();

		workPackCrudForm.validateMessageError(
				"Não é possível fazer a troca, a Ordem de Serviço [Code: " + codeServiceOrderWithStatusTechnician
						+ ", ID: " + idServiceOrderWithStatusTechnician + "] mudou de Técnico ou não está aberta");
	}

	@Test(dependsOnMethods = {
			"naoAlterandoComSucessoOTecnicoNumWorkPackEntregueQuandoAOrdemDeServicoJaMudouDeTecnico" })
	public void naoAlterandoComSucessoOTecnicoNumWorkPackEntregueQuandoAOrdemDeServicoEstaFechada() {

		// Indo para a Ordem de Serviço
		serviceOrderCrudForm = new ServiceOrderCrudForm(getDriver(), getBaseUrl(),
				idServiceOrderWithoutStatusTechnician);

		serviceOrderCrudForm.setStatus(STATUS_GENERIC_END);
		serviceOrderCrudForm.setClosedDate(getCurrentDate());
		serviceOrderCrudForm.buttonUpdate();

		serviceOrderCrudForm.validateMessageSuccessUpdate();

		// Voltando para o WorkPack
		workPackCrudForm = new WorkPackCrudForm(getDriver(), getBaseUrl(), codeWorkPackOSSemStatusTecnico);

		workPackCrudForm.setTechnician(TECHNICIAN_MATRIZ_02);
		workPackCrudForm.buttonUpdate();

		workPackCrudForm.validateMessageError(
				"Não é possível fazer a troca, a Ordem de Serviço [Code: " + codeServiceOrderWithoutStatusTechnician
						+ ", ID: " + idServiceOrderWithoutStatusTechnician + "] mudou de Técnico ou não está aberta");
	}

	@Test(dependsOnMethods = { "naoAlterandoComSucessoOTecnicoNumWorkPackEntregueQuandoAOrdemDeServicoEstaFechada" })
	public void validandoQueOFiltroDePesquisaRetornaSomenteOsDadosDoFiltroInformado() {

		workPackListPage = navBar.visitWorkPack();

		workPackListPage.setServiceProvider(SERVICE_PROVIDER_GENERIC_MATRIZ);
		workPackListPage.buttonSearch();

		workPackListPage.validateIfContainsTheRowInTable(Arrays.asList(SERVICE_PROVIDER_GENERIC_MATRIZ));

		workPackListPage.filterExpand();
		workPackListPage.cleanFieldFilter();

		workPackListPage.setStatus("Cancelado");
		workPackListPage.buttonSearch();

		workPackListPage.validateIfContainsTheRowInTable(Arrays.asList("Cancelado"));

		workPackListPage.filterExpand();
		workPackListPage.cleanFieldFilter();

		workPackListPage.setTechnician(TECHNICIAN_MATRIZ_01);
		workPackListPage.buttonSearch();

		workPackListPage.validateIfContainsTheRowInTable(Arrays.asList(TECHNICIAN_MATRIZ_01));
	}
}
