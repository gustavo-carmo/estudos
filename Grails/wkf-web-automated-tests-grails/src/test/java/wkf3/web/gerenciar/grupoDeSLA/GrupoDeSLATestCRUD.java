package wkf3.web.gerenciar.grupoDeSLA;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupAddServiceFormPage;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupCrudFormPage;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupListPage;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupShowPage;
import careman.html.TestBase;

public class GrupoDeSLATestCRUD extends TestBase {

	private static final String SERVICE_LEVEL_AGREEMENT_NAME = "SERVICE LEVEL AGREEMENT - " + randomString(5);

	private static final String USERNAME = "USERNAME_SLA_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_LEVEL_AGREEMENT",
			"ROLE_SERVICE_LEVEL_AGREEMENT_GROUP");

	private MainPage navBar;
	private ServiceLevelAgreementGroupCrudFormPage crudForm;
	private ServiceLevelAgreementGroupShowPage show;
	private ServiceLevelAgreementGroupAddServiceFormPage addService;

	private ServiceLevelAgreementGroupAddServiceFormPage addSLA;

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
	public void login() throws Exception {
		createUserAndDoLogin(USERNAME, PASSWORD, ROLES);
	}

	@Test(dependsOnMethods = { "login" })
	public void criandoGruposDeSLA() {

		crudForm = navBar.visitServiceLevelAgreementGroup().buttonNew();

		crudForm.setName(SERVICE_LEVEL_AGREEMENT_NAME);
		show = crudForm.buttonCreate();

		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "criandoGruposDeSLA" })
	public void adicionandoPrimeiraRegraDeSLAaUmGrupoDeSLAExistente() {

		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.validateUrlContains("sla/add");
		addSLA.setPrazo(2);
		addSLA.setPrazoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setPrestadorDeServico(1);
		addSLA.setHD(8);
		addSLA.setHDTempo(Messages.HOURS.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(Arrays.asList("2", Messages.CONSECUTIVE.toString().toLowerCase(),
				"1", Messages.USEFUL.toString().toLowerCase(), "8", Messages.HOURS.toString()));
	}

	@Test(dependsOnMethods = { "adicionandoPrimeiraRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoSegundaRegraDeSLAaUmGrupoDeSLAExistente() {

		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.COUNTRY_TOWN.toString());
		addSLA.setPrazo(10);
		addSLA.setPrazoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setPrestadorDeServico(5);
		addSLA.setPrestadorDeServicoTipoDia(Messages.CONSECUTIVE.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(Arrays.asList(Messages.COUNTRY_TOWN.toString(), "10",
				Messages.CONSECUTIVE.toString().toLowerCase(), "5", Messages.CONSECUTIVE.toString().toLowerCase()));
	}

	@Test(dependsOnMethods = { "adicionandoSegundaRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoTerceiraRegraDeSLAaUmGrupoDeSLAExistente() {

		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.DISTANCE_FROM_CAPITAL.toString());
		addSLA.setEscolha(Messages.UNTIL.toString());
		addSLA.setDistancia(10);
		addSLA.setPrazo(5);
		addSLA.setPrestadorDeServico(3);
		addSLA.setHD(1);
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(Arrays.asList(Messages.DISTANCE_FROM_CAPITAL.toString(),
				Messages.UNTIL.toString(), "10", "5", "3", "1", Messages.USEFUL.toString().toLowerCase()));
	}

	@Test(dependsOnMethods = { "adicionandoTerceiraRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoQuartaRegraDeSLAaUmGrupoDeSLAExistente() {

		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.DISTANCE_FROM_CAPITAL.toString());
		addSLA.setEscolha(Messages.GREATER_THAN.toString());
		addSLA.setDistancia(500);
		addSLA.setPrazo(10);
		addSLA.setPrazoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setPrestadorDeServico(5);
		addSLA.setPrestadorDeServicoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setHD(10);
		addSLA.setHDTempo(Messages.HOURS.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(
				Arrays.asList(Messages.DISTANCE_FROM_CAPITAL.toString(), Messages.GREATER_THAN.toString(), "500", "10",
						Messages.CONSECUTIVE.toString().toLowerCase(), "10", Messages.HOURS.toString()));
	}

	@Test(dependsOnMethods = { "adicionandoQuartaRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoQuintaRegraDeSLAaUmGrupoDeSLAExistente() {

		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.DISTANCE_FROM_CAPITAL.toString());
		addSLA.setEscolha(Messages.LESS_THAN.toString());
		addSLA.setDistancia(10);
		addSLA.setPrazo(10);
		addSLA.setPrazoTempo(Messages.HOURS.toString());
		addSLA.setPrestadorDeServico(5);
		addSLA.setPrestadorDeServicoTempo(Messages.HOURS.toString());
		addSLA.setHD(30);
		addSLA.setHDTempo(Messages.MINUTES.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(
				Arrays.asList(Messages.DISTANCE_FROM_CAPITAL.toString(), Messages.LESS_THAN.toString(), "10", "10",
						Messages.HOURS.toString(), "5", Messages.HOURS.toString(), "30", Messages.MINUTES.toString()));
	}

	@Test(dependsOnMethods = { "adicionandoQuintaRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoSextaRegraDeSLAaUmGrupoDeSLAExistente() {

		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.STATE_AND_OR_CITY.toString());
		addSLA.setEstado("Rio de Janeiro");
		addSLA.setPrazo(20);
		addSLA.setPrazoTempo(Messages.HOURS.toString());
		addSLA.setPrestadorDeServico(10);
		addSLA.setPrestadorDeServicoTempo(Messages.HOURS.toString());
		addSLA.setHD(3);
		addSLA.setHDTempo(Messages.HOURS.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(Arrays.asList("RJ", "20", Messages.HOURS.toString(), "10", "3"));
	}

	@Test(dependsOnMethods = { "adicionandoSextaRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoSetimaRegraDeSLAaUmGrupoDeSLAExistente() {
		
		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.STATE_AND_OR_CITY.toString());
		addSLA.setEstado("São Paulo");
		addSLA.setCity("São Carlos");
		addSLA.setCity("Poá");
		addSLA.setPrazo(5);
		addSLA.setPrazoTempo(Messages.HOURS.toString());
		addSLA.setPrestadorDeServico(3);
		addSLA.setPrestadorDeServicoTempo(Messages.HOURS.toString());
		addSLA.setHD(1);
		addSLA.setHDTempo(Messages.HOURS.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(Arrays.asList("SP", "5", Messages.HOURS.toString(), "3", "1"));
	}

	@Test(dependsOnMethods = { "adicionandoSetimaRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoOitavaRegraDeSLAaUmGrupoDeSLAExistente() {
		
		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.REGION.toString());
		addSLA.setEscolha(Messages.UNTIL.toString());
		addSLA.setDistancia(10);
		addSLA.setPrazo(10);
		addSLA.setPrazoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setPrestadorDeServico(5);
		addSLA.setPrestadorDeServicoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setHD(2);
		addSLA.setHDTempo(Messages.HOURS.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(
				Arrays.asList(Messages.REGION.toString(), Messages.NORTH.toString(), Messages.UNTIL.toString(), "10",
						"10", " 2", Messages.CONSECUTIVE.toString().toLowerCase(), Messages.HOURS.toString()));
	}

	@Test(dependsOnMethods = { "adicionandoOitavaRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoNonaRegraDeSLAaUmGrupoDeSLAExistente() {
		
		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.REGION.toString());
		addSLA.setRegiao(Messages.SOUTH.toString());
		addSLA.setEscolha(Messages.GREATER_THAN.toString());
		addSLA.setDistancia(500);
		addSLA.setPrazo(10);
		addSLA.setPrazoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setPrestadorDeServico(5);
		addSLA.setPrestadorDeServicoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setHD(2);
		addSLA.setHDTempo(Messages.HOURS.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(Arrays.asList(Messages.REGION.toString(),
				Messages.SOUTH.toString(), Messages.GREATER_THAN.toString(), "500", "10",
				Messages.CONSECUTIVE.toString().toLowerCase(), "5", "2", Messages.HOURS.toString()));
	}

	@Test(dependsOnMethods = { "adicionandoNonaRegraDeSLAaUmGrupoDeSLAExistente" })
	public void adicionandoDecimaRegraDeSLAaUmGrupoDeSLAExistente() {
		
		addSLA = show.buttonAddServiceLevelAgreementRule();

		addSLA.setCriterion(Messages.REGION.toString());
		addSLA.setRegiao(Messages.CENTRAL.toString());
		addSLA.setEscolha(Messages.LESS_THAN.toString());
		addSLA.setDistancia(100);
		addSLA.setPrazo(10);
		addSLA.setPrazoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setPrestadorDeServico(5);
		addSLA.setPrestadorDeServicoTipoDia(Messages.CONSECUTIVE.toString());
		addSLA.setHD(2);
		addSLA.setHDTempo(Messages.HOURS.toString());
		show = addSLA.buttonCreateSucess();

		validaCriacaoDaRegraESeElaEstaPresenteNaTabela(Arrays.asList(Messages.REGION.toString(),
				Messages.CENTRAL.toString(), Messages.LESS_THAN.toString(), "100", "10",
				Messages.CONSECUTIVE.toString().toLowerCase(), "5", "2", Messages.HOURS.toString()));
	}

	private void validaCriacaoDaRegraESeElaEstaPresenteNaTabela(List<String> dadosDaRegra) {

		show.validateMessageSuccessCreated();
		show.validateIfContainsTheRowInTable(dadosDaRegra);
	}

	@Test(dependsOnMethods = { "adicionandoDecimaRegraDeSLAaUmGrupoDeSLAExistente" })
	public void edit() {

		addService = show.editItemTableServiceLevelAgreement(1);

		addService.setCriterion(Messages.REGION.toString());
		show = addService.buttonUpdate();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void delete() {

		ServiceLevelAgreementGroupListPage listPage = show.buttonBackToSearch();

		listPage.setName(SERVICE_LEVEL_AGREEMENT_NAME);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
