package wkf3.web.gerenciar.gradeDeAgendamento;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.scheduledGrid.ScheduledGridCrudForm;
import br.com.workfinity.web.page.scheduledGrid.ScheduledGridListPage;
import careman.html.TestBase;

public class GradeDeAgendamento extends TestBase {

	private static final String GRADE_AGENDAMENTO = "GRADE_AGENDAMENTO_" + randomString(5);
	private static final String USERNAME = "GDA_" + randomString(5);
	private ScheduledGridCrudForm scheduledGridCrudForm;
	private ScheduledGridListPage scheduledGridListPage;
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
	}

	@Test
	public void background() {

		createUserAndDoLogin(USERNAME, "123456", Arrays.asList("ROLE_SCHEDULED_GRID"), "pt_BR");
	}

	@Test(dependsOnMethods = { "background" })
	public void deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeCriarUmaGradeDeAgendamento() {

		scheduledGridListPage = navBar.visitScheduledGrid();
		scheduledGridCrudForm = scheduledGridListPage.buttonNew();

		scheduledGridCrudForm.buttonCreate();
		scheduledGridCrudForm.validateErrorMessage("Campo \"Nome\" é requerido");

		scheduledGridCrudForm.setName(randomString(4));
		scheduledGridCrudForm.buttonCreate();
		scheduledGridCrudForm.validateErrorMessage("[Nome] é menor que o tamanho mínimo [5]");

		scheduledGridCrudForm.setName(randomString(51));
		scheduledGridCrudForm.buttonCreate();
		scheduledGridCrudForm.validateErrorMessage("[Nome] excede o tamanho máximo de [50]");
	}

	@Test(dependsOnMethods = {
			"deveraExibirUmaMensagemDeErroQuandoInformoUmValorInvalidoNaTentivaDeCriarUmaGradeDeAgendamento" })
	public void deveraCriarComSucessoUmaGradeDeAgendamentoQuandoInformoValoresValidos() {

		scheduledGridCrudForm.setName(GRADE_AGENDAMENTO);
		scheduledGridCrudForm.buttonCreate();
		scheduledGridCrudForm.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "deveraCriarComSucessoUmaGradeDeAgendamentoQuandoInformoValoresValidos" })
	public void deveraDarErroParaOCampoNomeQuandoJaExisteUmaGradeDeAgendamentoComOMesmoNome() {

		scheduledGridListPage = navBar.visitScheduledGrid();
		scheduledGridCrudForm = scheduledGridListPage.buttonNew();

		scheduledGridCrudForm.setName(GRADE_AGENDAMENTO);
		scheduledGridCrudForm.buttonCreate();
		scheduledGridCrudForm.validateErrorMessage("Nome com valor [" + GRADE_AGENDAMENTO + "] deve ser único");
	}

	@Test(dependsOnMethods = { "deveraDarErroParaOCampoNomeQuandoJaExisteUmaGradeDeAgendamentoComOMesmoNome" })
	public void validandoQueOFiltroDePesquisaRetornaSomenteOsDadosDoFiltroInformado() {

		scheduledGridListPage = scheduledGridCrudForm.buttonBackToSearch();

		scheduledGridListPage.setName(GRADE_AGENDAMENTO);
		scheduledGridListPage.buttonSearch();
		scheduledGridListPage.validateIfContainsTheRowInTable(Arrays.asList(GRADE_AGENDAMENTO));

		scheduledGridListPage.filterExpand();
		
		scheduledGridListPage.setName("");
		scheduledGridListPage.setStatus("Habilitado");
		scheduledGridListPage.buttonSearch();
		scheduledGridListPage.validateIfContainsTheRowInTable(Arrays.asList("Habilitado"));
	}

	@Test(dependsOnMethods = { "validandoQueOFiltroDePesquisaRetornaSomenteOsDadosDoFiltroInformado" })
	public void visitandoAPaginaDeEdicaoDumaGradeDeAgendamento() {

		scheduledGridCrudForm = scheduledGridListPage.editItemTable(1);

		scheduledGridCrudForm.validateUrlContains("scheduledGrid/edit");
	}
}
