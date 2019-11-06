package wkf3.web.gerenciar.notificacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.LoadDataHelper;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.customer.CustomerListPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.login.LoginPage;
import br.com.workfinity.web.page.notification.AddNotficationPage;
import br.com.workfinity.web.page.notification.NotificationCrudFormPage;
import br.com.workfinity.web.page.notification.NotificationListPage;
import br.com.workfinity.web.page.notification.NotificationShowPage;
import br.com.workfinity.web.page.notification.RemoveNotificationPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderCrudForm;
import br.com.workfinity.web.page.user.UserListPage;
import careman.html.TestBase;

public class Notificacao extends TestBase {

	private static final String OPENING_HOURS_GROUP_GENERIC = "OPENING_HOURS_GROUP_GENERIC";
	private static final String CONTRACTOR_GENERIC = "CONTRACTOR_GENERIC";
	private static final String SERVICE_GROUP_GENERIC = "SERVICE_GROUP_GENERIC";
	private static final String CUSTOMER_GENERIC = "CUSTOMER_GENERIC";
	private static final String CUSTOMER_DOCUMENT_NUMBER = "71.632.433/0001-08";
	private static final String WORKFLOW_GENERIC = "WORKFLOW_GENERIC";

	private static final String USERNAME = "NOTIFICATION_" + randomString(5);
	private static final List<String> ROLES = Arrays.asList("ROLE_NOTIFICATION", "ROLE_USER", "ROLE_USER_PERMISSION",
			"ROLE_CUSTOMER");

	private static final String HABILITADO = Messages.ENABLED.toString();
	private static final String INFORMACAO = Messages.INFO.toString();
	private static final String PERMANENTE = Messages.PERMANENT.toString();
	private static final String PERIODO = Messages.PERIOD.toString();
	private static final String UMA_VEZ = Messages.ONCE.toString();
	private static final String AVISO = Messages.WARNING.toString();
	private static final String IMPORTANTE = Messages.IMPORTANT.toString();

	private static final String USER_ADD_NOT = "USER_ADD_NOT_" + randomString(5);
	private static final String SERVICE = "SERVICE_" + randomString(5);

	private static final String INFO_PERM_TI = randomString(5);
	private static final String INFO_PERM_ME = randomString(10);
	private static final String INFO_PERI_TI = randomString(5);
	private static final String INFO_PERI_ME = randomString(10);
	private static final String INFO_ONCE_TI = randomString(5);
	private static final String INFO_ONCE_ME = randomString(10);
	private static final String WAR_PERM_TI = randomString(5);
	private static final String WAR_PERM_ME = randomString(10);
	private static final String WAR_PERI_TI = randomString(5);
	private static final String WAR_PERI_ME = randomString(10);
	private static final String WAR_ONCE_TI = randomString(5);
	private static final String WAR_ONCE_ME = randomString(10);
	private static final String IMP_PERM_TI = randomString(5);
	private static final String IMP_PERM_ME = randomString(10);
	private static final String IMP_PERI_TI = randomString(5);
	private static final String IMP_PERI_ME = randomString(10);
	private static final String IMP_ONCE_TI = randomString(5);
	private static final String IMP_ONCE_ME = randomString(10);

	private MainPage mainPage;
	private NotificationCrudFormPage notificationCrud;
	private UserListPage userList;
	private NotificationShowPage notificationShow;
	private NotificationListPage notificationList;

	private String getCurrentDate(Integer daysAfterCurrenteDate) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		if (daysAfterCurrenteDate != null) {

			c.add(Calendar.DATE, daysAfterCurrenteDate);
		}

		return dateFormat.format(c.getTime());
	}

	private void criarNotificacao(String tipo, String periodicidade, String title, String message) {

		notificationCrud = mainPage.visitNotification().buttonNew();

		notificationCrud.setStatus(HABILITADO);
		notificationCrud.setType(tipo);
		notificationCrud.setPeriodicity(periodicidade);
		notificationCrud.setTitle(title);
		notificationCrud.setMessage(message);

		if (periodicidade.equals(PERIODO)) {

			notificationCrud.setStartAt(getCurrentDate(null));
			notificationCrud.setEndAt(getCurrentDate(10));
		}

		notificationShow = notificationCrud.buttonCreate();

		notificationShow.validateMessageSuccessCreated();
	}

	private void adicionarNotificacaoParaOsCamposSelecionados(String notificacao, Object classe) {

		AddNotficationPage addNotficationPage = userList.buttonAddNotification();

		addNotficationPage.setNotification(notificacao);
		addNotficationPage.buttonSave();

		((Page) classe).validateAlertTitleAndMessage(Messages.SUCCESS.toString());
	}

	private void validaANotificacao(String title, String message) {

		mainPage.showNotification(title);
		mainPage.validateAlertTitleAndMessage(title, message);
	}

	@BeforeClass
	@Parameters({ "baseURL", "gridURL" })
	public void setUp(String baseURL, String gridURL) throws Exception {

		super.setUp(baseURL, gridURL, false);

		mainPage = new MainPage(getDriver());
	}

	@AfterClass
	public void tearDown() throws Exception {

		getDriver().quit();
	}

	@Test
	public void doLoadData() {

		LoadDataHelper.createUser(getDriver(), getBaseUrl(), USER_ADD_NOT, "123456", userRoles, "pt_BR");

		LoadDataHelper.createServiceToContract(getDriver(), getBaseUrl(), SERVICE, CONTRACTOR_GENERIC,
				SERVICE_GROUP_GENERIC, WORKFLOW_GENERIC, OPENING_HOURS_GROUP_GENERIC);

		createUserAndDoLogin(USERNAME, "123456", ROLES);
	}

	@Test(dependsOnMethods = { "doLoadData" })
	public void cadastroDeNotificacao() {

		criarNotificacao(INFORMACAO, PERMANENTE, INFO_PERM_TI, INFO_PERM_ME);
		criarNotificacao(INFORMACAO, PERIODO, INFO_PERI_TI, INFO_PERI_ME);
		criarNotificacao(INFORMACAO, UMA_VEZ, INFO_ONCE_TI, INFO_ONCE_ME);
		criarNotificacao(AVISO, PERMANENTE, WAR_PERM_TI, WAR_PERM_ME);
		criarNotificacao(AVISO, PERIODO, WAR_PERI_TI, WAR_PERI_ME);
		criarNotificacao(AVISO, UMA_VEZ, WAR_ONCE_TI, WAR_ONCE_ME);
		criarNotificacao(IMPORTANTE, PERMANENTE, IMP_PERM_TI, IMP_PERM_ME);
		criarNotificacao(IMPORTANTE, PERIODO, IMP_PERI_TI, IMP_PERI_ME);
		criarNotificacao(IMPORTANTE, UMA_VEZ, IMP_ONCE_TI, IMP_ONCE_ME);
	}

	@Test(dependsOnMethods = { "cadastroDeNotificacao" })
	public void adicionarUsuariosComoAlvo() {

		userList = mainPage.visitUserManager();

		userList.setUsername(USER_ADD_NOT);
		userList.buttonSearch();

		userList.selectUserInTableForNotification(1);

		adicionarNotificacaoParaOsCamposSelecionados(INFO_PERM_TI, userList);
		adicionarNotificacaoParaOsCamposSelecionados(INFO_PERI_TI, userList);
		adicionarNotificacaoParaOsCamposSelecionados(INFO_ONCE_TI, userList);
		adicionarNotificacaoParaOsCamposSelecionados(WAR_PERM_TI, userList);
		adicionarNotificacaoParaOsCamposSelecionados(WAR_PERI_TI, userList);
		adicionarNotificacaoParaOsCamposSelecionados(WAR_ONCE_TI, userList);
		adicionarNotificacaoParaOsCamposSelecionados(IMP_PERM_TI, userList);
		adicionarNotificacaoParaOsCamposSelecionados(IMP_PERI_TI, userList);
		adicionarNotificacaoParaOsCamposSelecionados(IMP_ONCE_TI, userList);
	}

	@Test(dependsOnMethods = { "adicionarUsuariosComoAlvo" })
	public void adicionarClientesComoAlvo() {

		CustomerListPage customerList = mainPage.visitCustomer();

		customerList.setAlias(CUSTOMER_GENERIC);
		customerList.buttonSearch();

		customerList.selectCustomerInTableForNotification(1);

		adicionarNotificacaoParaOsCamposSelecionados(INFO_PERM_TI, customerList);
		adicionarNotificacaoParaOsCamposSelecionados(INFO_PERI_TI, customerList);
		adicionarNotificacaoParaOsCamposSelecionados(INFO_ONCE_TI, customerList);
		adicionarNotificacaoParaOsCamposSelecionados(WAR_PERM_TI, customerList);
		adicionarNotificacaoParaOsCamposSelecionados(WAR_PERI_TI, customerList);
		adicionarNotificacaoParaOsCamposSelecionados(WAR_ONCE_TI, customerList);
		adicionarNotificacaoParaOsCamposSelecionados(IMP_PERM_TI, customerList);
		adicionarNotificacaoParaOsCamposSelecionados(IMP_PERI_TI, customerList);
		adicionarNotificacaoParaOsCamposSelecionados(IMP_ONCE_TI, customerList);
	}

	@Test(dependsOnMethods = { "adicionarClientesComoAlvo" })
	public void visualizandoAsNotificacoesDeUsuario() {

		logout(getDriver());

		mainPage = new LoginPage(getDriver(), USER_ADD_NOT, "123456").buttonSignInSuccess();

		mainPage.validateNumberOfNotifications("9");

		validaANotificacao(INFO_PERM_TI, INFO_PERM_ME);
		validaANotificacao(INFO_PERI_TI, INFO_PERI_ME);
		validaANotificacao(INFO_ONCE_TI, INFO_ONCE_ME);
		validaANotificacao(WAR_PERM_TI, WAR_PERM_ME);
		validaANotificacao(WAR_PERI_TI, WAR_PERI_ME);
		validaANotificacao(WAR_ONCE_TI, WAR_ONCE_ME);
		validaANotificacao(IMP_PERM_TI, IMP_PERM_ME);
		validaANotificacao(IMP_PERI_TI, IMP_PERI_ME);
		validaANotificacao(IMP_ONCE_TI, IMP_ONCE_ME);
	}

	@Test(dependsOnMethods = { "visualizandoAsNotificacoesDeUsuario" })
	public void visualizandoAsNotificacoesDeClientes() {

		ServiceOrderCrudForm serviceOrderCrud = mainPage.visitServiceOrder().buttonNew();

		serviceOrderCrud.setDocument("CNPJ", CUSTOMER_DOCUMENT_NUMBER);
		serviceOrderCrud.setServiceGroup(SERVICE_GROUP_GENERIC);
		serviceOrderCrud.setService(SERVICE);

		serviceOrderCrud.validateCustomerNotification(INFO_PERM_TI, INFO_PERM_ME);
		serviceOrderCrud.validateCustomerNotification(INFO_PERI_TI, INFO_PERI_ME);
		serviceOrderCrud.validateCustomerNotification(INFO_ONCE_TI, INFO_ONCE_ME);
		serviceOrderCrud.validateCustomerNotification(WAR_PERM_TI, WAR_PERM_ME);
		serviceOrderCrud.validateCustomerNotification(WAR_PERI_TI, WAR_PERI_ME);
		serviceOrderCrud.validateCustomerNotification(WAR_ONCE_TI, WAR_ONCE_ME);
		serviceOrderCrud.validateCustomerNotification(IMP_PERM_TI, IMP_PERM_ME);
		serviceOrderCrud.validateCustomerNotification(IMP_PERI_TI, IMP_PERI_ME);
		serviceOrderCrud.validateCustomerNotification(IMP_ONCE_TI, IMP_ONCE_ME);
	}

	@Test(dependsOnMethods = { "visualizandoAsNotificacoesDeClientes" })
	public void excluindoAlvos() {

		getDriver().get(getBaseUrl());
		mainPage.acceptAlert();

		notificationList = mainPage.visitNotification();
		notificationList.buttonSearch();

		removerNotificacao(INFORMACAO, PERMANENTE);
		removerNotificacao(INFORMACAO, PERIODO);
		removerNotificacao(INFORMACAO, UMA_VEZ);
		removerNotificacao(AVISO, PERMANENTE);
		removerNotificacao(AVISO, PERIODO);
		removerNotificacao(AVISO, UMA_VEZ);
		removerNotificacao(IMPORTANTE, PERMANENTE);
		removerNotificacao(IMPORTANTE, PERIODO);
		removerNotificacao(IMPORTANTE, UMA_VEZ);
	}

	private void removerNotificacao(String tipo, String periodicidade) {

		notificationList.filterExpand();
		notificationList.setType(tipo);
		notificationList.setPeriodicity(periodicidade);
		notificationList.buttonSearch();

		notificationShow = notificationList.showItemTable(1);

		notificationShow.goToTargets();
		notificationShow.selectTargetsInTableForNotification(1);
		notificationShow.selectTargetsInTableForNotification(2);

		RemoveNotificationPage removeNotification = notificationShow.buttonRemoveNotification();

		notificationShow = removeNotification.buttonConfirm();

		notificationShow.validateAlertTitleAndMessage(Messages.SUCCESS.toString());

		notificationList = notificationShow.buttonBackToSearch();
	}
}