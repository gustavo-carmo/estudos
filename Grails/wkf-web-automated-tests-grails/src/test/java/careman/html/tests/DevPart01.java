package careman.html.tests;

import java.util.ArrayList;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import careman.html.TestBase;

@SuppressWarnings("serial")
public class DevPart01 extends TestBase {

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {
		super.setUp(baseURL, gridURL);
	}

	@AfterClass
	public void tearDown() throws Exception {
		getDriver().quit();
	}

	@Test(priority = 1)
	public void doLogin() throws Exception {
		loginOld(getDriver(), "sidneyaraujo", "123456");
	}

	@Test(priority = 2)
	public void createDocumentType() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createDocumentType.txt", new ArrayList<String>() {
			{
				add("true");
				add("br.com.careman.domain.AppConfig : 13");
				add("true");
				add("br.com.careman.domain.AppConfig : 14");
				add("true");
				add("br.com.careman.domain.AppConfig : 15");
			}
		});
	}

	@Test(priority = 2)
	public void createOpeningHours() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createOpeningHours.txt", new ArrayList<String>() {
			{
				add("MONDAY [8 hours - 18 hours]");
				add("TUESDAY [8 hours - 18 hours]");
				add("WEDNESDAY [8 hours - 18 hours]");
				add("THURSDAY [8 hours - 18 hours]");
				add("FRIDAY [8 hours - 18 hours]");
			}
		});
	}

	@Test(priority = 3)
	public void createOpeningHoursGroup() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createOpeningHoursGroup.txt", new ArrayList<String>() {
			{
				add("true");
				add("MON-FRI 08h/18h");
				add("FRIDAY [8 hours - 18 hours], MONDAY [8 hours - 18 hours], THURSDAY [8 hours - 18 hours], TUESDAY [8 hours - 18 hours], WEDNESDAY [8 hours - 18 hours]");
			}
		});
	}

	@Test(priority = 4)
	public void createServiceGroup() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createServiceGroup.txt", new ArrayList<String>() {
			{
				add("true");
				add("Instalação");
				add("FIELD");
				add("true");
				add("Manutenção");
				add("FIELD");
				add("true");
				add("Desinstalação");
				add("FIELD");
				add("true");
				add("Reconfiguração");
				add("FIELD");
				add("true");
				add("Cancelamento");
				add("FIELD");
				add("true");
				add("Troca de Tecnologia");
				add("FIELD");
				add("true");
				add("Reincidência");
				add("FIELD");
				add("true");
				add("Carga de Software");
				add("FIELD");
				add("true");
				add("Rollout");
				add("FIELD");
				add("true");
				add("Reparacion en Laboratorio");
				add("LABORATORY");
			}
		});
	}

	@Test(priority = 5)
	public void createCustomFieldCodRed() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomFieldCodRed.txt", new ArrayList<String>() {
			{
				add("true");
				add("Cód. Rede");
				add("EQUIPMENT");
				add("REGEX");
				add("\\d{11}");
			}
		});
	}

	@Test(priority = 6)
	public void createCustomFieldCodEstabelecimento() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomFieldCodEstabelecimento.txt", new ArrayList<String>() {
			{
				add("true");
				add("Cód. Estabelecimento");
				add("EQUIPMENT");
				add("REGEX");
				add("\\d{15}");
			}
		});
	}

	@Test(priority = 7)
	public void createCustomFieldNumeroAtendimento() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomFieldNumeroAtendimento.txt", new ArrayList<String>() {
			{
				add("true");
				add("Número Atendimento");
				add("SERVICE_ORDER");
				add("REGEX");
				add("\\d{5}");
				add("[Reincidência]");
			}
		});
	}

	@Test(priority = 8)
	public void createCustomFieldNumeroDaReincidencia() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomFieldNumeroDaReincidencia.txt", new ArrayList<String>() {
			{
				add("true");
				add("Número da Reincidência");
				add("SERVICE_ORDER");
				add("LIST");
				add("[Reincidência]");
				add("[0, 1, 2, 3]");
			}
		});
	}

	@Test(priority = 9)
	public void createCustomFieldTipoDeOS() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomFieldTipoDeOS.txt", new ArrayList<String>() {
			{
				add("true");
				add("Tipo de Ordem de Serviço");
				add("SERVICE_ORDER");
				add("LIST");
				add("[Manutenção]");
				add("[Field, Help Desk, Laboratory]");
			}
		});
	}

	@Test(priority = 10)
	public void createCustomFieldNumeroDoChamadoPai() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomFieldNumeroDoChamadoPai.txt", new ArrayList<String>() {
			{
				add("true");
				add("Numero do chamado Pai");
				add("SERVICE_ORDER");
				add("REGEX");
				add("\\d{5,10}");
				add("[Manutenção]");
			}
		});
	}

	@Test(priority = 11)
	public void createCustomFieldNumeroDoRg() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomFieldNumeroDoRg.txt", new ArrayList<String>() {
			{
				add("true");
				add("Número do RG");
				add("SERVICE_ORDER");
				add("REGEX");
				add("^\\s*([^\\s]\\s*){8,12}$");
				add("[Manutenção]");
			}
		});
	}

	@Test(priority = 12)
	public void createCustomFieldNumeroDoCPF() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomFieldNumeroDoCPF.txt", new ArrayList<String>() {
			{
				add("true");
				add("Número do CPF");
				add("SERVICE_ORDER");
				add("REGEX");
				add("\\d{11}");
				add("[Manutenção]");
			}
		});
	}

	@Test(priority = 13)
	public void createContractorBanrisul() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createContractorBanrisul.txt", new ArrayList<String>() {
			{
				add("true");
				add("Rua Capitão Montanha");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("BANRISUL");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("[EQUIPMENT] Cód. Rede -> \\d{11} (REQUIRED)");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("[EQUIPMENT] Cód. Estabelecimento -> \\d{15} (REQUIRED)");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("grails.validation.ValidationErrors: 0 errors");
				add("BANRISUL");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 14)
	public void createContractorPOSNET() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createContractorPOSNET.txt", new ArrayList<String>() {
			{
				add("true");
				add("Peru");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("POSNET");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("grails.validation.ValidationErrors: 0 errors");
				add("POSNET");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 15)
	public void createContractorCABAL() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createContractorCABAL.txt", new ArrayList<String>() {
			{
				add("true");
				add("Lavalle");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("CABAL");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("grails.validation.ValidationErrors: 0 errors");
				add("CABAL");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 16)
	public void createContractorTICKET() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createContractorTICKET.txt", new ArrayList<String>() {
			{
				add("true");
				add("Rua Bela Cintra");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("TICKET");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("grails.validation.ValidationErrors: 0 errors");
				add("TICKET");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 17)
	public void createContractorBanrisulFilial() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createContractorBanrisulFilial.txt", new ArrayList<String>() {
			{
				add("true");
				add("Avenida Paulista");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("BANRISUL Filial SP");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 18)
	public void createServiceProviderHelpDesk() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createServiceProviderHelpDesk.txt", new ArrayList<String>() {
			{
				add("true");
				add("Avenida Ibirapuera");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Help Desk");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Help Desk");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 19)
	public void createServiceProviderTEFTIBarueri() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createServiceProviderTEFTIBarueri.txt", new ArrayList<String>() {
			{
				add("true");
				add("Alameda Juari");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("TEFTI Barueri");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("TEFTI Barueri");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 20)
	public void createServiceProviderVortex() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createServiceProviderVortex.txt", new ArrayList<String>() {
			{
				add("true");
				add("Manuel Ugarte");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Vortex");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Vortex");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 21)
	public void createServiceProviderSmart() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createServiceProviderSmart.txt", new ArrayList<String>() {
			{
				add("true");
				add("Avenida Corrientes");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Smart");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Smart");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 22)
	public void createServiceProviderSmartF1() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createServiceProviderSmartF1.txt", new ArrayList<String>() {
			{
				add("true");
				add("Avenida Corrientes");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Smartf1");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Smartf1");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 23)
	public void createCustomerDILCEU() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerDILCEU.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA PEDRO SOUZA");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("DILCEU JOSE COSTANTIN ME - 91.728.196/0001-90");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerMariaCeilandia() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerMariaCeilandia.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 2");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Maria Ceilândia - 12.282.122/0001-05");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerArnaldoPlanaltina() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerArnaldoPlanaltina.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 3");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Arnaldo Planaltina - 56.052.444/0001-25");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerAntoniaPontalina() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerAntoniaPontalina.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 5");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Antônia Pontalina - 29.788.521/0001-37");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerMauraBonito() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerMauraBonito.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 6");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Maura Bonito - 28.454.484/0001-68");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerPaulo() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerPaulo.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 7");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Paulo - 77.766.829/0001-24");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerRonaldoRecife() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerRonaldoRecife.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 8");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Ronaldo Recife - 95.826.713/0001-15");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerFabioFloripa() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerFabioFloripa.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 9");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Fábio Floripa - 31.273.370/0001-26");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerRicardoCuritiba() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerRicardoCuritiba.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 10");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Ricardo Curitiba - 91.628.557/0001-27");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerLucasGravatai() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerLucasGravatai.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 11");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Lucas Gravataí - 61.799.637/0001-84");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerGustavoBentoGoncalves() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerGustavoBentoGoncalves.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 12");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Gustavo Bento Gonçalves - 02.454.746/0001-09");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerLucasJoinvile() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerLucasJoinvile.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 13");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Lucas Joiville - 50.696.552/0001-54");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerOctavioLondrina() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerOctavioLondrina.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 14");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Octavio Londrina - 34.567.724/0001-33");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerGabrielAlvorada() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerGabrielAlvorada.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 15");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Gabriel Alvorada - 85.176.535/0001-70");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerGustavoNovoHamburgo() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerGustavoNovoHamburgo.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 16");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Gustavo Novo Hamburgo - 63.766.854/0001-01");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerLucasGuarulhos() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerLucasGuarulhos.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 17");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Lucas Guarulhos - 86.888.825/0001-00");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerAndreTaubate() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerAndreTaubate.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 18");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Andre Taubaté - 42.370.218/0001-88");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerGrabrielSaoSimao() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerGrabrielSaoSimao.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 19");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Gabriel São Simão - 66.838.669/0001-65");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerGuilhermeSaltoGrande() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerGuilhermeSaltoGrande.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 20");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Guilherme Salto Grande - 64.226.831/0001-69");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerGiovandersonSaoPaulo() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerGiovandersonSaoPaulo.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 21");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Giovanderson São Paulo - 44.333.547/0001-84");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 24)
	public void createCustomerJosefRecife() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createCustomerJosefRecife.txt", new ArrayList<String>() {
			{
				add("true");
				add("RUA 22");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Josef Recife - 17.833.290/0001-57");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 25)
	public void createManufacturer() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createManufacturer.txt", new ArrayList<String>() {
			{
				add("true");
				add("Ingenico");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Verifone");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Sin datos");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 26)
	public void createFamilyPOS() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createFamilyPOS.txt", new ArrayList<String>() {
			{
				add("true");
				add("POS");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 26)
	public void createFamilyDisplay() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createFamilyDisplay.txt", new ArrayList<String>() {
			{
				add("true");
				add("Display");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 26)
	public void createFamilyPOSComponentes() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createFamilyPOSComponentes.txt", new ArrayList<String>() {
			{
				add("true");
				add("POS - Componentes");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 26)
	public void createFamilyPOSImpressora() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createFamilyPOSImpressora.txt", new ArrayList<String>() {
			{
				add("true");
				add("POS - Impressora");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 26)
	public void createFamilyFonteDeAlimentacao() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createFamilyFonteDeAlimentacao.txt", new ArrayList<String>() {
			{
				add("true");
				add("Fonte de Alimentação");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 27)
	public void createModelForProducts() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createModelForProducts.txt", new ArrayList<String>() {
			{
				add("true");
				add("BANJO");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("I5100");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("I8200");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("I7910");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("ACQUA");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("EFT930G");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("EFT930S");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("ICT220");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("I5100MMD012B");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("I5100MMD047A");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("I5100PPT033B");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("NURIT8320S");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("IWL250");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("ICT250");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 28)
	public void createModelForComponents() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createModelForComponents.txt", new ArrayList<String>() {
			{
				add("true");
				add("ST763A");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("ST3232ECTR");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("CAP 100UF6V3(SMD)");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 29)
	public void createModelForParts() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createModelForParts.txt", new ArrayList<String>() {
			{
				add("true");
				add("POS_DISPLAY");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("POS_IMPRESORA");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Fonte ICT220");
				add("grails.validation.ValidationErrors: 0 errors");
				add("ACQUA");
				add("[ST763A]");
				add("ICT220");
				add("[Fonte ICT220]");
			}
		});
	}

	@Test(priority = 30)
	public void createEquipment() throws Exception {

		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createEquipment.txt", new ArrayList<String>() {
			{
				add("true");
				add("BANJO - 9729012741 - 00000674");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("BANJO - 9729012742");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("BANJO - 9729012743");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("BANJO - 9729012744 - 00000675");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("[100] ST3232ECTR");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("[100] ST3232ECTR (POSNET)");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("[5] ST763A (CABAL)");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("[30] CAP 100UF6V3(SMD) (POSNET)");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("[30] CAP 100UF6V3(SMD)");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("[20] Fonte ICT220");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("[50] CAP 100UF6V3(SMD)");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("ACQUA - 9729012745 - 00000340");
				add("grails.validation.ValidationErrors: 0 errors");				

				add("true");
				add("[20] CAP 100UF6V3(SMD)");
				add("grails.validation.ValidationErrors: 0 errors");
				
				add("true");
				add("ACQUA - 9729012746 - 00000380");
				add("grails.validation.ValidationErrors: 0 errors");

				add("true");
				add("[100] Fonte ICT220");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 31)
	public void createSolution() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createSolution.txt", new ArrayList<String>() {
			{
				add("true");
				add("TROCA DE TERMINAL");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("CONFIGURACAO DO TERMINAL");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Trocar Fonte");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}

	@Test(priority = 32)
	public void createSolutionLaboratory() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart01/createSolutionLaboratory.txt", new ArrayList<String>() {
			{
				add("true");
				add("Repara Carcaza");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Cambia Display");
				add("grails.validation.ValidationErrors: 0 errors");
				add("true");
				add("Cambia Impresora");
				add("grails.validation.ValidationErrors: 0 errors");
			}
		});
	}
}