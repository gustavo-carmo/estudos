package careman.html.tests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.helper.Messages;
import careman.html.TestBase;

@SuppressWarnings("serial")
public class DevPart02 extends TestBase {

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

	@Test(priority = 33)
	public void createDefect() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createDefect.txt", new ArrayList<String>() {
			{
				add("POS: Terminal Bloqueado (grails.validation.ValidationErrors: 0 errors)");
				add("CONFIGURACAO DO TERMINAL (grails.validation.ValidationErrors: 0 errors)");
				add("TROCA DE TERMINAL (grails.validation.ValidationErrors: 0 errors)");
				add("POS: Fonte danificada (grails.validation.ValidationErrors: 0 errors)");
				add("Trocar Fonte (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 34)
	public void createDefectLaboratory() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createDefectLaboratory.txt", new ArrayList<String>() {
			{
				add("Carcaza Rota (grails.validation.ValidationErrors: 0 errors)");
				add("Repara Carcaza (grails.validation.ValidationErrors: 0 errors)");
				add("Display Defectuoso (grails.validation.ValidationErrors: 0 errors)");
				add("Cambia Display (grails.validation.ValidationErrors: 0 errors)");
				add("Impresora Imprime Mal (grails.validation.ValidationErrors: 0 errors)");
				add("Cambia Impresora (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 35)
	public void createSLAGBanrisulInstalacaoTrocaDeTecnologia() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createSLAGBanrisulInstalacaoTrocaDeTecnologia.txt",
				new ArrayList<String>() {
					{
						add("SLA Banrisul (Instalação e Troca de Tecnologia) (grails.validation.ValidationErrors: 0 errors)");
						add("CAPITAL: 15 CONSECUTIVE_DAYS, SP: 15 CONSECUTIVE_DAYS (grails.validation.ValidationErrors: 0 errors)");
						add("COUNTRY_TOWN: 15 CONSECUTIVE_DAYS, SP: 13 CONSECUTIVE_DAYS (grails.validation.ValidationErrors: 0 errors)");
						add("SLA Banrisul (Demais Serviços) (grails.validation.ValidationErrors: 0 errors)");
						add("CAPITAL: 4 HOUR, SP: 4 HOUR (grails.validation.ValidationErrors: 0 errors)");
						add("COUNTRY_TOWN: 24 HOUR, SP: 20 HOUR (grails.validation.ValidationErrors: 0 errors)");
						add("STATE_AND_OR_CITY (RS, Alvorada, ...): 12 HOUR, SP: 10 HOUR (grails.validation.ValidationErrors: 0 errors)");
					}
				});
	}

	@Test(priority = 36)
	public void createSLATicketLocacaoInstalacao() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createSLATicketLocacaoInstalacao.txt", new ArrayList<String>() {
			{
				add("SLA Ticket (Locação e Instalação) (grails.validation.ValidationErrors: 0 errors)");
				add("REGION MIDWEST (GREATER_THAN 300 km): 16 WORKING_DAY, SP: 14 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION MIDWEST (UNTIL 300 km): 13 WORKING_DAY, SP: 11 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION NORTH (GREATER_THAN 300 km): 17 WORKING_DAY, SP: 15 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION NORTH (UNTIL 150 km): 12 WORKING_DAY, SP: 10 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION NORTH (UNTIL 300 km): 14 WORKING_DAY, SP: 12 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION NORTH (UNTIL 50 km): 10 WORKING_DAY, SP: 8 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION NORTHEAST (GREATER_THAN 300 km): 16 WORKING_DAY, SP: 14 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION NORTHEAST (UNTIL 150 km): 11 WORKING_DAY, SP: 9 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION NORTHEAST (UNTIL 300 km): 13 WORKING_DAY, SP: 11 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION NORTHEAST (UNTIL 50 km): 9 WORKING_DAY, SP: 7 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION SOUTH (GREATER_THAN 300 km): 16 WORKING_DAY, SP: 14 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION SOUTH (UNTIL 150 km): 11 WORKING_DAY, SP: 9 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION SOUTH (UNTIL 300 km): 13 WORKING_DAY, SP: 11 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION SOUTH (UNTIL 50 km): 9 WORKING_DAY, SP: 7 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION SOUTHEAST (GREATER_THAN 300 km): 15 WORKING_DAY, SP: 13 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION SOUTHEAST (UNTIL 150 km): 10 WORKING_DAY, SP: 8 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION SOUTHEAST (UNTIL 300 km): 12 WORKING_DAY, SP: 10 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("REGION SOUTHEAST (UNTIL 50 km): 8 WORKING_DAY, SP: 6 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 37)
	public void createSLATicketManutencao() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createSLATicketManutencao.txt", new ArrayList<String>() {
			{
				add("SLA Ticket (Manutenção) (grails.validation.ValidationErrors: 0 errors)");
				add("CAPITAL: 12 WORKING_DAY, SP: 10 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("DISTANCE_FROM_CAPITAL (GREATER_THAN 300 km): 60 WORKING_DAY, SP: 50 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("DISTANCE_FROM_CAPITAL (UNTIL 150 km): 24 WORKING_DAY, SP: 20 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("DISTANCE_FROM_CAPITAL (UNTIL 300 km): 36 WORKING_DAY, SP: 30 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
				add("DISTANCE_FROM_CAPITAL (UNTIL 50 km): 12 WORKING_DAY, SP: 10 WORKING_DAY (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 38)
	public void createStatus() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createStatus.txt", new ArrayList<String>() {
			{
				add("SERVICE_ORDER: Nova [START] (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Cancelado [END_WITH_FAIL] (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Finalizado [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Em Campo [WITH_TECHNICIAN] (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Encaminhado (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Pre-Finalizado [PRE_END] (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Para Aprovação (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Para Encerramento (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Cancelado (Pendente Autorização) (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Reencaminhado (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Aprovado (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Conferência no Destino (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Encaminhado Manut (grails.validation.ValidationErrors: 0 errors)");
				add("SERVICE_ORDER: Finalizado (Sem Troca) [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: RR Abierta [START] (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: Asignada (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: En Reparo (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: Reparacion OK (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: Irreparable (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: QA_Lab [QA] (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: QA Aprobado (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: QA Rechazado (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: Bodega Egreso (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: Entregado OK [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: Entregado NOK [END_WITH_FAIL] (grails.validation.ValidationErrors: 0 errors)");
				add("REPAIR_ORDER: Bodega Baja (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Nova [START] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Preparação (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Despachado [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Cancelado [END_WITH_FAIL] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Em Transito (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Recebido OK PS [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Recusado no Destino [END_WITH_FAIL] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: PRÉ-DESPACHO (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: DESPACHADO GOOD [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: DESPACHADO BAD [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: INGRESSO GOOD [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: INGRESSO BAD [END_WITH_SUCCESS] (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Ingresso de Equiptos (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Conferência no Destino (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Gerar Nota Fiscal (grails.validation.ValidationErrors: 0 errors)");
				add("SHIPMENT_ORDER: Disponivel para Coleta (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 39)
	public void createWarrantyProvider() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createWarrantyProvider.txt", new ArrayList<String>() {
			{
				add("Repair - Laboratorio Vortex (grails.validation.ValidationErrors: 0 errors)");
				add("Manufacturer - Ingenico (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 40)
	public void createRoutingGroups() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createRoutingGroups.txt", new ArrayList<String>() {
			{
				add("Grupo de Roteirização A1 (grails.validation.ValidationErrors: 0 errors)");
				add("Grupo de Roteirização B1 (grails.validation.ValidationErrors: 0 errors)");
				add("Grupo de Roteirização C1 (grails.validation.ValidationErrors: 0 errors)");
				add("Grupo de Roteirização D1 (grails.validation.ValidationErrors: 0 errors)");
				add("Grupo de Roteirização E1 (grails.validation.ValidationErrors: 0 errors)");
				add("Grupo de Roteirização F1 (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 41)
	public void createWorkflowStepsForField() throws Exception {
		
		getDriver().get(this.getBaseUrl());
		
		WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
		
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		try {
			getDriver().findElement(By.linkText("Workflow"));
		} catch (NoSuchElementException e) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		}
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		getDriver().findElement(By.linkText("Workflow")).click();
		// Workflow de Campo
		// Instalação
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Instalação");
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Para Aprovação");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Para Aprovação");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Para Aprovação");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Aprovado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Aprovado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Inventoried_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		// Desinstalação
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		getDriver().findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Desinstalação");
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		// Manutenção
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		getDriver().findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Manutenção");
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		getDriver().findElement(By.id("rule_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Defect")).click();
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Solution")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		getDriver().findElement(By.id("rule_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver()
				.findElement(
						By.id("rule_Disassociate_New_Equipment_from_Customer_setting_Situation_to_GOOD,_and_delete_New_Equipment_if_it_is_temporary"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Defect")).click();
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Solution")).click();
		getDriver().findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Pre-Start");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Nova");
		getDriver().findElement(By.id("rule_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		// Troca de Tecnologia
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		getDriver().findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Troca de Tecnologia");
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_New_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Inventoried_New_Equipment")).click();
		getDriver().findElement(By.id("rule_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado (Sem Troca)");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_New_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Inventoried_New_Equipment")).click();
		getDriver().findElement(By.id("rule_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Disassociate_Equipment_from_Customer_setting_Situation_to_GOOD_and_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado (Pendente Autorização)");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Cancelado (Pendente Autorização)");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver()
				.findElement(
						By.id("rule_Disassociate_New_Equipment_from_Customer_setting_Situation_to_GOOD,_and_delete_New_Equipment_if_it_is_temporary"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Cancelado (Pendente Autorização)");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Reencaminhado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Reencaminhado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		// Reconfiguração
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		getDriver().findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Reconfiguração");
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Defect")).click();
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Solution")).click();
		getDriver().findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver()
				.findElement(
						By.id("rule_Disassociate_New_Equipment_from_Customer_setting_Situation_to_GOOD,_and_delete_New_Equipment_if_it_is_temporary"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Defect")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Solution")).click();
		getDriver().findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		// Atualização de Software
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		getDriver().findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Atualização de Software");
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Service_Provider_through_automatic_routing")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver()
				.findElement(
						By.id("rule_Disassociate_New_Equipment_from_Customer_setting_Situation_to_GOOD,_and_delete_New_Equipment_if_it_is_temporary"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Defect")).click();
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_If_exists_New_Equipment_Serial_Number")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Solution")).click();
		getDriver().findElement(
				By.id("rule_If_Has_New_Equipment,_disassociate_Equipment_from_Customer_setting_Situation_to_BAD_and_Service_Provider"))
				.click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		// Cancelamento
		getDriver().get(this.getBaseUrl());
		getDriver().findElement(By.cssSelector("img.wkf-brand")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText("Management"))).click();
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Workflow")));
		getDriver().findElement(By.linkText("Workflow")).click();
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("status"))).selectByVisibleText(Messages.ENABLED.toString());
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).clear();
		getDriver().findElement(By.id("name")).sendKeys("Cancelamento");
		new Select(getDriver().findElement(By.id("workflowType"))).selectByVisibleText(Messages.SERVICE_ORDER.toString());
		getDriver().findElement(By.cssSelector("button.btn.btn-primary")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Encaminhado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.id("rule_Define_Routed_Date")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Nova");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Encaminhado");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Em Campo");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Cancelado");
		getDriver().findElement(By.id("rule_Reason_For_Cancellation")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
		webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("_action_create"))).click();
		new Select(getDriver().findElement(By.id("from.id"))).selectByVisibleText("Em Campo");
		new Select(getDriver().findElement(By.id("to.id"))).selectByVisibleText("Finalizado");
		getDriver().findElement(By.id("rule_Equipment")).click();
		getDriver().findElement(By.id("rule_Equipment_Contractor_External_ID")).click();
		getDriver().findElement(By.id("rule_Equipment_Serial_Number")).click();
		getDriver().findElement(By.xpath("(//input[@id='rule_New_Equipment'])[2]")).click();
		getDriver().findElement(By.id("rule_Service_Provider")).click();
		getDriver().findElement(By.xpath("//button[@value='Create']")).click();
		validateMessageSuccess("created");
	}

	@Test(priority = 42)
	public void createWorkflowStepsForLaboratory() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createWorkflowStepsForLaboratory.txt", new ArrayList<String>() {
			{
				add("Workflow Laboratório (grails.validation.ValidationErrors: 0 errors)");
				add("Asignada -> En Reparo (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Technician (grails.validation.ValidationErrors: 0 errors)");
				add("Bodega Baja -> Entregado NOK (grails.validation.ValidationErrors: 0 errors)");
				add("Bodega Egreso -> Entregado OK (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Convert_Equipment_to_Situation_GOOD (grails.validation.ValidationErrors: 0 errors)");
				add("En Reparo -> Irreparable (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Convert_Equipment_to_Situation_IRREPARABLE (grails.validation.ValidationErrors: 0 errors)");
				add("En Reparo -> Reparacion OK (grails.validation.ValidationErrors: 0 errors)");
				add("Irreparable -> Bodega Baja (grails.validation.ValidationErrors: 0 errors)");
				add("QA Aprobado -> Bodega Egreso (grails.validation.ValidationErrors: 0 errors)");
				add("QA Rechazado -> En Reparo (grails.validation.ValidationErrors: 0 errors)");
				add("QA_Lab -> QA Aprobado (grails.validation.ValidationErrors: 0 errors)");
				add("rule_All_Quality_Evaluation_must_be_Approved (grails.validation.ValidationErrors: 0 errors)");
				add("QA_Lab -> QA Rechazado (grails.validation.ValidationErrors: 0 errors)");
				add("rule_At_least_one_Quality_Evaluation_must_be_Reproved (grails.validation.ValidationErrors: 0 errors)");
				add("RR Abierta -> Asignada (grails.validation.ValidationErrors: 0 errors)");
				add("Reparacion OK -> QA_Lab (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 43)
	public void createWorkflowStepsForLogistic() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createWorkflowStepsForLogistic.txt", new ArrayList<String>() {
			{
				add("Workflow Logística - Remessa Simples BAD (grails.validation.ValidationErrors: 0 errors)");
				add("Nova -> Preparação (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("Preparação -> Cancelado (grails.validation.ValidationErrors: 0 errors)");
				add("Preparação -> Despachado (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Only_BAD_Equipments (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");

				add("Workflow Logística - Remessa Simples GOOD (grails.validation.ValidationErrors: 0 errors)");
				add("Nova -> Preparação (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("Preparação -> Cancelado (grails.validation.ValidationErrors: 0 errors)");
				add("Preparação -> Despachado (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Only_GOOD_Equipments (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");

				add("Workflow Logística - Remessa Complexa (grails.validation.ValidationErrors: 0 errors)");
				add("Em Transito -> Recebido OK PS (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("Em Transito -> Recusado no Destino (grails.validation.ValidationErrors: 0 errors)");
				add("Nova -> Preparação (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("Preparação -> Em Transito (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");

				add("Ingresso de Equipamentos - GOOD (grails.validation.ValidationErrors: 0 errors)");
				add("Ingresso de Equiptos -> Cancelado (grails.validation.ValidationErrors: 0 errors)");
				add("Ingresso de Equiptos -> Recebido OK PS (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Set_Equipments_situation_to_GOOD (grails.validation.ValidationErrors: 0 errors)");
				add("Nova -> Ingresso de Equiptos (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");

				add("Ingresso de Equipamentos - BAD (grails.validation.ValidationErrors: 0 errors)");
				add("Ingresso de Equiptos -> Cancelado (grails.validation.ValidationErrors: 0 errors)");
				add("Ingresso de Equiptos -> Recebido OK PS (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Set_Equipments_situation_to_BAD (grails.validation.ValidationErrors: 0 errors)");
				add("Nova -> Ingresso de Equiptos (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");

				add("Ordens de Entrega - GOOD (grails.validation.ValidationErrors: 0 errors)");
				add("Nova -> Preparação (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Only_GOOD_Equipments (grails.validation.ValidationErrors: 0 errors)");
				add("PRÉ-DESPACHO -> Cancelado (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Only_GOOD_Equipments (grails.validation.ValidationErrors: 0 errors)");
				add("PRÉ-DESPACHO -> DESPACHADO GOOD (grails.validation.ValidationErrors: 0 errors)");
				add("addWarranty (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Weight (grails.validation.ValidationErrors: 0 errors)");
				add("Preparação -> PRÉ-DESPACHO (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Only_GOOD_Equipments (grails.validation.ValidationErrors: 0 errors)");

				add("Ordens de Entrega - BAD (grails.validation.ValidationErrors: 0 errors)");
				add("Nova -> Preparação (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Only_BAD_Equipments (grails.validation.ValidationErrors: 0 errors)");
				add("PRÉ-DESPACHO -> Cancelado (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Only_BAD_Equipments (grails.validation.ValidationErrors: 0 errors)");
				add("PRÉ-DESPACHO -> DESPACHADO GOOD (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Weight (grails.validation.ValidationErrors: 0 errors)");
				add("Preparação -> PRÉ-DESPACHO (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Only_BAD_Equipments (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 44)
	public void createWorkflowStepsPrestadorParaTEFTI() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createWorkflowStepsPrestadorParaTEFTI.txt", new ArrayList<String>() {
			{
				add("Remessa Prestador para TEFTI (grails.validation.ValidationErrors: 0 errors)");
				add("Conferência no Destino -> Recebido OK PS (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Weight (grails.validation.ValidationErrors: 0 errors)");
				add("Conferência no Destino -> Recusado no Destino (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Weight (grails.validation.ValidationErrors: 0 errors)");
				add("Em Transito -> Cancelado (grails.validation.ValidationErrors: 0 errors)");
				add("Em Transito -> Conferência no Destino (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Weight (grails.validation.ValidationErrors: 0 errors)");
				add("Gerar Nota Fiscal -> Cancelado (grails.validation.ValidationErrors: 0 errors)");
				add("Gerar Nota Fiscal -> Em Transito (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Carrier (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Invoice_Number (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Outbound_Date (grails.validation.ValidationErrors: 0 errors)");
				add("rule_Weight (grails.validation.ValidationErrors: 0 errors)");
				add("Ingresso de Equiptos -> Gerar Nota Fiscal (grails.validation.ValidationErrors: 0 errors)");
				add("Nova -> Ingresso de Equiptos (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 45)
	public void createServiceToBanrisul() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createServiceToBanrisul.txt", new ArrayList<String>() {
			{
				add("Instalação Venda (grails.validation.ValidationErrors: 0 errors)");
				add("Desinstalação (grails.validation.ValidationErrors: 0 errors)");
				add("Reconfiguração (grails.validation.ValidationErrors: 0 errors)");
				add("Troca de Tecnologia (geral) (grails.validation.ValidationErrors: 0 errors)");
				add("Manutenção (grails.validation.ValidationErrors: 0 errors)");
				add("Atualização de Software (grails.validation.ValidationErrors: 0 errors)");
				add("Cancelamento (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 46)
	public void createServiceToPOSNET() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createServiceToPOSNET.txt", new ArrayList<String>() {
			{
				add("Reparacion en Laboratorio (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 47)
	public void createServiceToCABAL() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createServiceToCABAL.txt", new ArrayList<String>() {
			{
				add("Reparacion en Laboratorio (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 48)
	public void createServiceToTicket() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createServiceToTicket.txt", new ArrayList<String>() {
			{
				add("Instalação Geral (grails.validation.ValidationErrors: 0 errors)");
				add("Manutenção (grails.validation.ValidationErrors: 0 errors)");
				add("Desinstalação (grails.validation.ValidationErrors: 0 errors)");
				add("Troca de Tecnologia (Geral) (grails.validation.ValidationErrors: 0 errors)");
				add("Reincidência (grails.validation.ValidationErrors: 0 errors)");
				add("Carga de Software (grails.validation.ValidationErrors: 0 errors)");
				add("Reconfiguração (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 49)
	public void createGenericContract() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createGenericContract.txt", new ArrayList<String>() {
			{
				add("Contrato Generico (grails.validation.ValidationErrors: 0 errors)");
				add("StockLevel (grails.validation.ValidationErrors: 0 errors)");
				add("Instalação Venda (grails.validation.ValidationErrors: 0 errors)");
				add("Desinstalação (grails.validation.ValidationErrors: 0 errors)");
				add("Reconfiguração (grails.validation.ValidationErrors: 0 errors)");
				add("Troca de Tecnologia (Geral) (grails.validation.ValidationErrors: 0 errors)");
				add("Manutenção (grails.validation.ValidationErrors: 0 errors)");
				add("Atualização de Software (grails.validation.ValidationErrors: 0 errors)");
				add("Cancelamento (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}

	@Test(priority = 50)
	public void createReasonForCancellation() throws Exception {
		this.loadDataFromFileAndValidateResult("/loadData/DevPart02/createReasonForCancellation.txt", new ArrayList<String>() {
			{
				add("NOK - ENDERECO NAO LOCALIZADO (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - SEM COMUNICACAO (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - ESTABELECIMENTO FECHADO (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - NAO AUTORIZADO PELO CLIENTE (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - FALTA ASSINATURA DO CONTRATO (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - INSCRICAO ESTADUAL INVALIDA (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - CEP INVALIDO (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - RAZAO SOCIAL INVALIDA (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - EQUIPAMENTO NAO ESTA NO LOCAL (grails.validation.ValidationErrors: 0 errors)");
				add("NOK Contato EC, endereço divergente (grails.validation.ValidationErrors: 0 errors)");
				add("NOK Contato EC não autorizou instal (grails.validation.ValidationErrors: 0 errors)");
				add("NOK Contato cliente EC sem infraest (grails.validation.ValidationErrors: 0 errors)");
				add("NOK Contato EC Banco solicitou canc (grails.validation.ValidationErrors: 0 errors)");
				add("NOK Contato EC, POS de outro fornec (grails.validation.ValidationErrors: 0 errors)");
				add("NOK Chamado duplicado (grails.validation.ValidationErrors: 0 errors)");
				add("NOK CNPJ baixado no SINTEGRA (grails.validation.ValidationErrors: 0 errors)");
				add("PENDENTE NO ESTABELECIMENTO (grails.validation.ValidationErrors: 0 errors)");
				add("PENDENTE NA AGENCIA (grails.validation.ValidationErrors: 0 errors)");
				add("NOK - OUTRO (grails.validation.ValidationErrors: 0 errors)");
			}
		});
	}
}
