package wkf3.web.gerenciar.gradeDeAgendamento;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.scheduledGrid.ScheduledGridAttendanceWindow;
import br.com.workfinity.web.page.scheduledGrid.ScheduledGridCrudForm;
import br.com.workfinity.web.page.scheduledGrid.ScheduledGridListPage;
import careman.html.TestBase;

public class GradeDeAgendamentoGerenciarJanelasDeAtendimento extends TestBase {

	private static final String PASSWORD = "123456";
	private static final String USERNAME = "GDA_" + randomString(5);
	private static final String JANELA_ATENDIMENTO = "JANELA_ATENDIMENTO_" + randomString(5);
	private static final String NOME_GRADE_AGENDAMENTO = "NOME_GRADE_AGENDAMENTO_" + randomString(5);
	private MainPage navBar;
	private ScheduledGridListPage scheduledGridListPage;
	private ScheduledGridCrudForm scheduledGridCrudForm;
	private ScheduledGridAttendanceWindow scheduledGridAttendanceWindow;

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

		createUserAndDoLogin(USERNAME, PASSWORD, Arrays.asList("ROLE_SCHEDULED_GRID"), "pt_BR");

		scheduledGridListPage = navBar.visitScheduledGrid();

		scheduledGridCrudForm = scheduledGridListPage.buttonNew();

		scheduledGridCrudForm.setName(NOME_GRADE_AGENDAMENTO);
		scheduledGridCrudForm.buttonCreate();
		scheduledGridCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "background" })
	public void deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeCriarUmaJanelaDeAtendimento() {

		scheduledGridCrudForm.buttonAttendanceWindow();
		scheduledGridAttendanceWindow = scheduledGridCrudForm.buttonNew();

		scheduledGridAttendanceWindow.buttonCreateFail();
		scheduledGridAttendanceWindow.validateErrorMessage("Campo \"Name\" é requerido", "Campo \"Início em\" é requerido", "Campo \"Finaliza em\" é requerido");

		scheduledGridAttendanceWindow.setName(randomString(4));
		scheduledGridAttendanceWindow.setStartAt("13:00");
		scheduledGridAttendanceWindow.setEndAt("13:30");
		scheduledGridAttendanceWindow.buttonCreateFail();
		scheduledGridAttendanceWindow.validateErrorMessage("[Name] é menor que o tamanho mínimo [5]");

		scheduledGridAttendanceWindow.setName(randomString(51));
		scheduledGridAttendanceWindow.setStartAt("13:00");
		scheduledGridAttendanceWindow.setEndAt("13:30");
		scheduledGridAttendanceWindow.buttonCreateFail();
		scheduledGridAttendanceWindow.validateErrorMessage("[Name] excede o tamanho máximo de [50]");

	}

	@Test(dependsOnMethods = { "deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeCriarUmaJanelaDeAtendimento" })
	public void deveraCriarComSucessoUmaJanelaDeAtendimentoQuandoInformoValoresValidos() {
		
		scheduledGridAttendanceWindow.setName(JANELA_ATENDIMENTO);
		scheduledGridAttendanceWindow.setStartAt("10:00");
		scheduledGridAttendanceWindow.setEndAt("13:00");
		scheduledGridCrudForm = scheduledGridAttendanceWindow.buttonCreateSuccess();
		scheduledGridCrudForm.validateMessageSuccessCreated();
		
	}
	
	@Test(dependsOnMethods = {"deveraCriarComSucessoUmaJanelaDeAtendimentoQuandoInformoValoresValidos" })
	public void deveraDarErroParaNomesDuplicadosNaMesmaJanelaDeAtendimento() {
		
		scheduledGridCrudForm.buttonAttendanceWindow();
		scheduledGridAttendanceWindow = scheduledGridCrudForm.buttonNew();

		scheduledGridAttendanceWindow.setName(JANELA_ATENDIMENTO);
		scheduledGridAttendanceWindow.setStartAt("13:00");
		scheduledGridAttendanceWindow.setEndAt("13:30");
		scheduledGridAttendanceWindow.buttonCreateFail();
		scheduledGridAttendanceWindow.validateErrorMessage("Name com valor [" + JANELA_ATENDIMENTO + "] deve ser único");
	}

	@Test(dependsOnMethods = { "deveraDarErroParaNomesDuplicadosNaMesmaJanelaDeAtendimento" })
	public void deveraDarErroParaOCampoFinalizaEmQuandoEsteNaoTemUmValorMaiorQueInicioEm() {

		scheduledGridAttendanceWindow.setName(randomString(10));
		scheduledGridAttendanceWindow.setStartAt("09:00");
		scheduledGridAttendanceWindow.setEndAt("08:59");
		scheduledGridAttendanceWindow.buttonCreateFail();
		scheduledGridAttendanceWindow.validateErrorMessage("[Finaliza em] deve ser depois de [Início em]");
	}

	@Test(dependsOnMethods = { "deveraDarErroParaOCampoFinalizaEmQuandoEsteNaoTemUmValorMaiorQueInicioEm" })
	public void editandoUmaJanelaDeAtendimentoDumaGradeDeAtendimentoComSucesso() {

		scheduledGridCrudForm = scheduledGridAttendanceWindow.buttonBackToScheduledGrid();
		scheduledGridCrudForm.buttonAttendanceWindow();

		scheduledGridAttendanceWindow = scheduledGridCrudForm.editItemTable(1);
		scheduledGridAttendanceWindow.setName(JANELA_ATENDIMENTO);
		scheduledGridCrudForm = scheduledGridAttendanceWindow.buttonCreateSuccess();
		scheduledGridCrudForm.validateMessageSuccessUpdate();

		scheduledGridCrudForm.buttonAttendanceWindow();
		scheduledGridCrudForm.validateIfContainsTheRowInTable(Arrays.asList(JANELA_ATENDIMENTO));

	}

	@Test(dependsOnMethods = { "editandoUmaJanelaDeAtendimentoDumaGradeDeAtendimentoComSucesso" })
	public void removendoUmaJanelaDeAtendimentoDumaGradeDeAtendimento() {

		scheduledGridCrudForm.deleteItemTableByPosition(1, 1);
		scheduledGridCrudForm.validateMessageSuccessDeleted();

		scheduledGridCrudForm.buttonAttendanceWindow();
		scheduledGridCrudForm.validateNotContainsRowInTable(Arrays.asList(JANELA_ATENDIMENTO), "");
	}
}
