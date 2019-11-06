package br.com.workfinity.web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.workfinity.web.page.contractRental.ContractRentalListPage;
import br.com.workfinity.web.page.contractor.ContractorListPage;
import br.com.workfinity.web.page.customer.CustomerListPage;
import br.com.workfinity.web.page.defect.DefectListPage;
import br.com.workfinity.web.page.equipment.EquipmentListPage;
import br.com.workfinity.web.page.export.ExportPage;
import br.com.workfinity.web.page.family.FamilyListPage;
import br.com.workfinity.web.page.genericContract.GenericContractListPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import br.com.workfinity.web.page.importProcess.ImportSolicitationsAndInstallations;
import br.com.workfinity.web.page.manufacturer.ManufacturerListPage;
import br.com.workfinity.web.page.model.ModelListPage;
import br.com.workfinity.web.page.notification.NotificationListPage;
import br.com.workfinity.web.page.openingHours.OpeningHoursListPage;
import br.com.workfinity.web.page.openingHoursGroup.OpeningHoursGroupListPage;
import br.com.workfinity.web.page.perfil.ProfileSettingsListPage;
import br.com.workfinity.web.page.productionPlanning.ProductionPlanningListPage;
import br.com.workfinity.web.page.reasonForCancellation.ReasonForCancellationListPage;
import br.com.workfinity.web.page.recess.RecessListPage;
import br.com.workfinity.web.page.rentalReport.RentalReportListPage;
import br.com.workfinity.web.page.repairOrder.RepairOrderListPage;
import br.com.workfinity.web.page.scheduledGrid.ScheduledGridListPage;
import br.com.workfinity.web.page.serviceAgreementeGroup.ServiceLevelAgreementGroupListPage;
import br.com.workfinity.web.page.serviceAreas.ServiceAreaListPage;
import br.com.workfinity.web.page.serviceGroup.ServiceGroupListPage;
import br.com.workfinity.web.page.serviceOrder.ServiceOrderListPage;
import br.com.workfinity.web.page.serviceProvider.ServiceProviderListPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderListPage;
import br.com.workfinity.web.page.shipmentOrder.ShipmentOrderTypeListPage;
import br.com.workfinity.web.page.solution.SolutionListPage;
import br.com.workfinity.web.page.status.StatusListPage;
import br.com.workfinity.web.page.stock.StockControlListPage;
import br.com.workfinity.web.page.template.TemplateListPage;
import br.com.workfinity.web.page.user.UserListPage;
import br.com.workfinity.web.page.warrantyProvider.WarrantyProviderListPage;
import br.com.workfinity.web.page.workPack.WorkPackListPage;
import br.com.workfinity.web.page.workflow.WorkflowListPage;

public class MainPage extends Page {

	public MainPage(WebDriver driver) {
		super(driver);
	}

	private void clickW() {
        waitPageLoadEnds();
        clickByCssSelector("img.wkf-brand");
	}

	public UserListPage visitUserManager() {
		clickW();
		clickByCssSelector("i.fa.fa-users");
		waitPageLoadEnds();
		return new UserListPage(driver);
	}

	private void openLeftMenuAndClick(String menuLevelOne, String menuLevelTwo) {
		clickW();
		waitElementToBeClickable(findByLinkText(menuLevelOne)).click();
		waitElementToBeClickable(findByLinkText(findByCssSelector("ul.collapse.in"), menuLevelTwo)).click();
	}
	
	private void openLeftMenuAndClick(Messages menuLevelOne, Messages menuLevelTwo) {
		openLeftMenuAndClick(menuLevelOne.toString(), menuLevelTwo.toString());
	}

	public OpeningHoursListPage visitOpeningHours() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.OPENING_HOURS);
		waitPageLoadEnds();
		return new OpeningHoursListPage(driver);
	}

	public OpeningHoursGroupListPage visitOpeningHoursGroup() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.OPENING_HOURS_GROUPS);
		waitPageLoadEnds();
		return new OpeningHoursGroupListPage(driver);
	}

	public ContractorListPage visitContractor() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.CONTRACTOR);
		waitPageLoadEnds();
		return new ContractorListPage(driver);
	}

	public CustomerListPage visitCustomer() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.CUSTOMER);
		waitPageLoadEnds();
		return new CustomerListPage(driver);
	}

	public ServiceProviderListPage visitServiceProvider() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.SERVICE_PROVIDER);
		waitPageLoadEnds();
		return new ServiceProviderListPage(driver);
	}

	public ManufacturerListPage visitManufacturer() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.MANUFACTURER);
		waitPageLoadEnds();
		return new ManufacturerListPage(driver);
	}

	public FamilyListPage visitFamily() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.FAMILY);
		waitPageLoadEnds();
		return new FamilyListPage(driver);
	}

	public ModelListPage visitModel() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.MODEL);
		waitPageLoadEnds();
		return new ModelListPage(driver);
	}

	public EquipmentListPage visitEquipment(String urlBase) {
		visitURL(urlBase + "/equipment/index");
		waitPageLoadEnds();
		return new EquipmentListPage(driver);
	}

	public SolutionListPage visitSolution() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.SOLUTION);
		waitPageLoadEnds();
		return new SolutionListPage(driver);
	}

	public DefectListPage visitDefect() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.DEFECT);
		waitPageLoadEnds();
		return new DefectListPage(driver);
	}

	public ReasonForCancellationListPage visitReasonForCancellation() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.REASON_FOR_CANCELLATION);
		waitPageLoadEnds();
		return new ReasonForCancellationListPage(driver);
	}

	public ServiceGroupListPage visitServiceGroup() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.SERVICE_GROUP);
		waitPageLoadEnds();
		return new ServiceGroupListPage(driver);

	}

	public StatusListPage visitStatus() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.STATUS);
		waitPageLoadEnds();
		return new StatusListPage(driver);
	}

	public WorkflowListPage visitWorkflow() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.WORKFLOW);
		waitPageLoadEnds();
		return new WorkflowListPage(driver);
	}

	public RecessListPage visitRecess() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.RECESS);
		waitPageLoadEnds();
		return new RecessListPage(driver);
	}

	public ServiceLevelAgreementGroupListPage visitServiceLevelAgreementGroup() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.SERVICE_LEVEL_AGREEMENT_GROUP);
		waitPageLoadEnds();
		return new ServiceLevelAgreementGroupListPage(driver);
	}

	public ServiceOrderListPage visitServiceOrder() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.SERVICE_ORDER);
		waitPageLoadEnds();
		return new ServiceOrderListPage(driver);
	}

	public StockControlListPage visitStockControl() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.STOCK_CONTROL);
		waitPageLoadEnds();
		return new StockControlListPage(driver);
	}

	public ProductionPlanningListPage visitProductionPlanning() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.PRODUCTION_PLANNING);
		waitPageLoadEnds();
		return new ProductionPlanningListPage(driver);
	}

	public RepairOrderListPage visitRepairOrder() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.REPAIR_ORDER);
		waitPageLoadEnds();
		return new RepairOrderListPage(driver);
	}

	public ShipmentOrderListPage visitShipmentOrder() {
		openLeftMenuAndClick(Messages.LOGISTIC, Messages.SHIPMENT_ORDER);
		waitPageLoadEnds();
		return new ShipmentOrderListPage(driver);
	}

	public void searchAnyoneMenu() {
		clickW();
		try {
			WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
			webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(("ul#mainnav-menu")))
					.findElement(By.cssSelector("li"))));
		} catch (Exception e) {
			// System.out.println("There aren't none menu");
		}
	}

	public ImportSolicitationsAndInstallations visitImportSolicitationsAndInstallations() {
		openLeftMenuAndClick(Messages.BANRISUL, Messages.IMPORT_SOLICITATIONS_E_INSTALLATIONS);
		waitPageLoadEnds();
		return new ImportSolicitationsAndInstallations(driver);
	}

	public ImportProcessListPage visitImportProcess() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.IMPORT_PROCESS);
		waitPageLoadEnds();
		return new ImportProcessListPage(driver);
	}

	public ExportPage visitExport() {
		openLeftMenuAndClick(Messages.BANRISUL, Messages.EXPORT);
		waitPageLoadEnds();
		return new ExportPage(driver);
	}

	public UtilConsolePage visitUtilConsole(String urlBase) {
		visitURLAndValidateLocation(urlBase + "/util/console");
		waitVisibilitOfElement(findById("command"));
		return new UtilConsolePage(driver);
	}

	public ProfileSettingsListPage visitPerfil(String baseUrl) {
		visitURLAndValidateLocation(baseUrl + "/profileSettings");
		waitPageLoadEnds();
		return new ProfileSettingsListPage(driver);
	}

	public TemplateListPage visitTemplate() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.TEMPLATE);
		waitPageLoadEnds();
		return new TemplateListPage(driver);
	}

	public GenericContractListPage visitGenercContract() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.GENERIC_CONTRACT);
		waitPageLoadEnds();
		return new GenericContractListPage(driver);
	}

	public NotificationListPage visitNotification() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.NOTIFICATION);
		return new NotificationListPage(driver);
	}

	public MainPage validateNumberOfNotifications(String notificationsNumber) {
		validateIfContainsTextInElement(findByCssSelector("li#notifications_container a.dropdown-toggle span"), notificationsNumber);
		return this;
	}

	public MainPage showNotification(String title) {
		clickByCssSelector("li#notifications_container a.dropdown-toggle");
		findByCssSelector(findOneElementOfOneListElements("div.notifications.dropdown-menu.dropdown-menu-md.dropdown-menu-right.with-arrow ul li", title), "a.media").click();
		waitAjaxEnd();
		return this;
	}

	public void acceptAlert() {
		acceptMessageAlert();
		waitPageLoadEnds();
	}

	public ScheduledGridListPage visitScheduledGrid() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.SCHEDULED_GRID);
		waitPageLoadEnds();
		return new ScheduledGridListPage(driver);
	}

	public ContractRentalListPage visitContractRental() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.CONTRACT_RENTAL);
		waitPageLoadEnds();
		return new ContractRentalListPage(driver);
	}

	public RentalReportListPage visitRentalReport() {
		openLeftMenuAndClick(Messages.REPORT, Messages.RENTAL_REPORT);
		waitPageLoadEnds();
		return new RentalReportListPage(driver);
	}

	public WorkPackListPage visitWorkPack() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.WORKPACK);
		waitPageLoadEnds();
		return new WorkPackListPage(driver);
	}

	public ShipmentOrderTypeListPage visitShipmentOrderType() {
		openLeftMenuAndClick(Messages.LOGISTIC, Messages.SHIPMENT_ORDER_TYPE);
		waitPageLoadEnds();
		return new ShipmentOrderTypeListPage(driver);
	}

	public ServiceAreaListPage visitServiceArea() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.SERVICE_AREA);
		waitPageLoadEnds();
		return new ServiceAreaListPage(driver);
	}

	public ProfileSettingsListPage visitProfileSettings() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.PROFILE_SETTINGS);
		waitPageLoadEnds();
		return new ProfileSettingsListPage(driver);
	}

	public WarrantyProviderListPage visitWarrantyProvider() {
		openLeftMenuAndClick(Messages.MANAGEMENT, Messages.WARRANTY_PROVIDER);
		waitPageLoadEnds();
		return new WarrantyProviderListPage(driver);
	}
}
