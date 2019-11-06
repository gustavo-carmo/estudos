package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.AddressBlock;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.contractor.ContractorCrudFormPage;
import br.com.workfinity.web.page.contractor.ContractorListPage;
import br.com.workfinity.web.page.contractor.ContractorShowPage;
import br.com.workfinity.web.page.helper.Messages;
import careman.html.TestBase;

public class ContractorTestCRUD extends TestBase {

	private static final String CONTRACTOR_NAME_2 = "CONTRACTOR_CREATE - " + randomString(5);
	private static final String CONTRACTOR_NAME = "CONTRACTOR_CREATE - " + randomString(5);

	private static final String USERNAME = "USERNAME_CONTRACTOR_" + randomString(5);
	private static final String PASSWORD = "123456";
	private static final List<String> ROLES = Arrays.asList("ROLE_CONTRACTOR");

	private MainPage navBar;
	private ContractorCrudFormPage crudForm;
	private ContractorShowPage show;
	private ContractorListPage listPage;
	
	private String numberDocument1 = "34.450.950/0001-30";
	private String numberDocument1Edit = "21.682.422/0001-64";
	private String numberDocument2 = "01.560.128/0001-72";

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
	public void create() {

		createContractor(CONTRACTOR_NAME, "CNPJ", numberDocument1, Messages.ENABLED.toString());
		createContractor(CONTRACTOR_NAME_2, "CNPJ", numberDocument2, Messages.DISABLE.toString());
	}

	private void createContractor(String contractorName, String typeDocument, String numberDocument, String status) {

		crudForm = navBar.visitContractor().buttonNew();
		
		AddressBlock address = new AddressBlock(getDriver());
		
		address.setZipCode("39400215");
		address.setDistrict("Centro");
		address.setAddress("Avenida Deputado Esteves Rodrigues");
		address.setCountry("Brasil", 1);
		address.setState("Minas Gerais", 2);
		address.setCity("Montes Claros", 3);
		address.setNumber("543");

		crudForm.setContactName("CONTACT NAME");
		crudForm.setName(contractorName);
		crudForm.setAlias(contractorName);
		crudForm.setEmail("crudcontractor@new.com");
		crudForm.setDocument(typeDocument, numberDocument);
		crudForm.setPhoneNumber("(11) 4023-4355");
		crudForm.setStatus(status);

		show = crudForm.buttonCreate();
		
		show.validateMessageSuccessCreated();
	}

	@Test(dependsOnMethods = { "create" })
	public void edit() {

		listPage = show.buttonBackToSearch();
		listPage.setDocument("CNPJ", numberDocument1);
		listPage.buttonSearch();

		listPage.editItemTable(1);

		crudForm.setDocument("CNPJ", numberDocument1Edit);
		crudForm.buttonUpdated();

		show.validateMessageSuccessUpdate();
	}

	@Test(dependsOnMethods = { "edit" })
	public void search() {

		listPage = show.buttonBackToSearch();

		listPage.filterExpand();
		listPage.setAlias(CONTRACTOR_NAME);
		listPage.setDocument(Messages.ALL.toString(), "");
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setAlias("");
		listPage.setStatus(Messages.DISABLE.toString());
		listPage.buttonSearch();

		listPage.filterExpand();
		listPage.setDocument("CNPJ", "");
		listPage.setAlias("CONTRACTOR_CREATE");
		listPage.setStatus(Messages.ALL.toString());
		listPage.buttonSearch();

		listPage.validateTotalCount("2");
		listPage.filterExpand();
		listPage.setAlias("");
		listPage.setDocument("CPF", "111.111.111-11");
		listPage.buttonSearch();

		listPage.validateTotalCount("0");
		listPage.filterExpand();
		listPage.setAlias("");
		listPage.setStatus(Messages.ALL.toString());
		listPage.setDocument(Messages.ALL.toString(), "");
		listPage.buttonSearch();
	}

	@Test(dependsOnMethods = { "search" })
	public void delete() {

		deleteContractor("CNPJ", numberDocument1Edit);
		deleteContractor("CNPJ", numberDocument2);
	}

	private void deleteContractor(String typeDocument, String numberDocument) {

		listPage.filterExpand();
		listPage.setDocument(typeDocument, numberDocument);
		listPage.buttonSearch();

		listPage.deleteItemTable(1);
	}
}
