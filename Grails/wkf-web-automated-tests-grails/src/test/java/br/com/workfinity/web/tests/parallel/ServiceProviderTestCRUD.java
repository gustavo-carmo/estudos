package br.com.workfinity.web.tests.parallel;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.AddressBlock;
import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.helper.Messages;
import br.com.workfinity.web.page.serviceProvider.ServiceProviderCrudFormPage;
import br.com.workfinity.web.page.serviceProvider.ServiceProviderListPage;
import br.com.workfinity.web.page.serviceProvider.ServiceProviderShowPage;
import careman.html.TestBase;

public class ServiceProviderTestCRUD extends TestBase {
	
    private static final String SERVICE_PROVIDER_NAME_CREATE = "SERVICE PROVIDER CREATE" + randomString(5);
    private static final String SERVICE_PROVIDER_NAME_2 = "SERVICE PROVIDER CREATE" + randomString(5);
    private static final String SERVICE_PROVIDER_NAME_UPDATE = "SERVICE PROVIDER CREATE_UPDATE" + randomString(5);

    private static final String USERNAME = "USERNAME_SERVICE_PROVIDER_" + randomString(5);
    private static final String PASSWORD = "123456";
    private static final List<String> ROLES = Arrays.asList("ROLE_SERVICE_PROVIDER");

    private MainPage navBar;
    private ServiceProviderCrudFormPage crudForm;
    private ServiceProviderShowPage show;
    private AddressBlock address;
    private ServiceProviderListPage listPage;
    
    private String documentNumberServiceProvider1 = "72.108.727/0001-06";
    private String documentNumberServiceProvider2 = "776.136.171-14";

    @BeforeClass
    @Parameters({"baseURL", "gridURL"})
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

    @Test(dependsOnMethods = {"login"})
    public void create() {

        createServiceProvider(SERVICE_PROVIDER_NAME_CREATE, "CNPJ", documentNumberServiceProvider1);
        createServiceProvider(SERVICE_PROVIDER_NAME_2, "CPF", documentNumberServiceProvider2);
    }

    private void createServiceProvider(String serviceProviderName, String documentType, String documentNumber) {
    	
        crudForm = navBar.visitServiceProvider().buttonNew();
        
        address = new AddressBlock(getDriver());
        
        address.setZipCode("39400215");
        address.setDistrict("Centro");
		address.setAddress("Avenida Deputado Esteves Rodrigues");
		address.setCountry("Brasil", 1);
		address.setState("Minas Gerais", 2);
		address.setCity("Montes Claros", 3);
		address.setNumber("123");

        crudForm.setAlias(serviceProviderName);
        crudForm.setStatus(Messages.DISABLE.toString());
        crudForm.setContactName("CONTACT NAME");
        crudForm.setName(serviceProviderName);
        crudForm.setEmail("crudserviceprovider@new.com");
        crudForm.setPhoneNumber("553155664499");
        crudForm.setDocument(documentType, documentNumber);
        
        show = crudForm.buttonCreate();

        show.validateMessageSuccessCreated();
    }

    @Test(dependsOnMethods = {"create"})
    public void edit() throws InterruptedException {
    	
        listPage = show.buttonBackToSearch();
        listPage.setDocument("CNPJ", documentNumberServiceProvider1);
        listPage.setAlias("SERVICE PROVIDER CREATE");
        listPage.buttonSearch();

        listPage.editItemTable(1);

        address.setZipCode("22041080");
        address.setDistrict("Copacabana");
		address.setAddress("Rua Anita Garibaldi");
		address.setCountry("Brasil", 1);
		address.setState("Rio de Janeiro", 2);
		address.setCity("Rio de Janeiro", 3);
		address.setNumber("321");
        
        documentNumberServiceProvider1 = "36.803.325/0001-50";
        crudForm.setDocument("CNPJ", documentNumberServiceProvider1);
        crudForm.setAlias(SERVICE_PROVIDER_NAME_UPDATE);
        crudForm.setName(SERVICE_PROVIDER_NAME_UPDATE);
        crudForm.setEmail("crudserviceprovider@edit.com");
        crudForm.setStatus(Messages.ENABLED.toString());
        crudForm.setType(Messages.LABORATORY.toString());

        show = crudForm.buttonEdit();

        show.validateMessageSuccessUpdate();
    }

    @Test(dependsOnMethods = {"edit"})
    public void search() {
    	
//        listPage = show.buttonBackToSearch();
    	
    	listPage = navBar.visitServiceProvider();
        listPage.filterExpand();
        listPage.setDocument(Messages.ALL.toString(), "");
        listPage.setAlias(SERVICE_PROVIDER_NAME_UPDATE);
        listPage.buttonSearch();

        listPage.filterExpand();
        listPage.setAlias(SERVICE_PROVIDER_NAME_2);
        listPage.setStatus(Messages.DISABLE.toString());
        listPage.buttonSearch();

        listPage.filterExpand();
        listPage.setStatus(Messages.ALL.toString());
        listPage.setAlias("");
        listPage.setDocument("CNPJ", documentNumberServiceProvider1);
        listPage.buttonSearch();

        listPage.filterExpand();
        listPage.setDocument("CPF", "");
        listPage.buttonSearch();

        listPage.filterExpand();
        listPage.setAlias("");
        listPage.setDocument(Messages.ALL.toString(), "");
        listPage.setStatus(Messages.ALL.toString());
        listPage.buttonSearch();
    }

    @Test(dependsOnMethods = {"search"})
    public void delete() {
    	
        deleteServiceProvider("CNPJ", documentNumberServiceProvider1);
        deleteServiceProvider("CPF", documentNumberServiceProvider2);
    }

    private void deleteServiceProvider(String documentType, String documentNumber) {
    	
        listPage.filterExpand();

        listPage.setAlias("SERVICE PROVIDER CREATE");
        listPage.setDocument(documentType, documentNumber);
        listPage.buttonSearch();

        listPage.deleteItemTable(1);
    }
}
