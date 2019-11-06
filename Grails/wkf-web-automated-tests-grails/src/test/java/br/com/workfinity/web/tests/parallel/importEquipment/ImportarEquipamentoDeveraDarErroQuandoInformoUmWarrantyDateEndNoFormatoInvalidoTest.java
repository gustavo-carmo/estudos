package br.com.workfinity.web.tests.parallel.importEquipment;

import java.util.Arrays;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import br.com.workfinity.web.page.MainPage;
import br.com.workfinity.web.page.importProcess.ImportProcessListPage;
import br.com.workfinity.web.page.importProcess.ImportProcessShowPage;
import careman.html.TestBase;

public class ImportarEquipamentoDeveraDarErroQuandoInformoUmWarrantyDateEndNoFormatoInvalidoTest extends TestBase {

	private static final String USER_IMPORT_EQUIP_WARRANTY_DATE_END_INVALID = "USER_IMPEQ_WARRANTY_END_INV_"
			+ randomString(5);
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

		createUserAndDoLogin(USER_IMPORT_EQUIP_WARRANTY_DATE_END_INVALID, "123456",
				Arrays.asList("ROLE_IMPORT_PROCESS", "ROLE_IMPORT_PROCESS_FROM_TO"), "pt_BR");
	}

	// TODO - A MENSAGEM DE ERRO É DIFÍCIL DE INTERPRETAR
	@Test(dependsOnMethods = { "background" })
	public void deveraDarErroQuandoInformoWarrantyDateEndNoFormatoInvalido() {

		ImportProcessListPage importProcessListPage = navBar.visitImportProcess();
		importProcessListPage.setFile("Import_Equipment.csv");
		importProcessListPage.buttonSearch();

		ImportProcessShowPage importProcessShowPage = importProcessListPage.showItemTable(1);

		// Verificando o Status
		String status = importProcessShowPage.getStatus("status17");
		importProcessShowPage.assertEquals("Erro", status, "Diferentes!");

		// Verificando o detalhe do erro
//		String details = importProcessShowPage.getDetails("val17");
//		importProcessShowPage.assertEquals("Warranty: grails.validation.ValidationErrors: 1 errors", details,
//				"Diferentes!");
//		importProcessShowPage.assertEquals(
//				"Field error in object 'br.com.careman.domain.Warranty' on field 'dateEnd': rejected value Wed Sep 06 00:00:00 BRT 24; codes br.com.careman.domain.Warranty.dateEnd.min.error.br.com.careman.domain.Warranty.dateEnd,br.com.careman.domain.Warranty.dateEnd.min.error.dateEnd,br.com.careman.domain.Warranty.dateEnd.min.error.java.util.Date,br.com.careman.domain.Warranty.dateEnd.min.error,warranty.dateEnd.min.error.br.com.careman.domain.Warranty.dateEnd,warranty.dateEnd.min.error.dateEnd,warranty.dateEnd.min.error.java.util.Date,warranty.dateEnd.min.error,br.com.careman.domain.Warranty.dateEnd.min.notmet.br.com.careman.domain.Warranty.dateEnd,br.com.careman.domain.Warranty.dateEnd.min.notmet.dateEnd,br.com.careman.domain.Warranty.dateEnd.min.notmet.java.util.Date,br.com.careman.domain.Warranty.dateEnd.min.notmet,warranty.dateEnd.min.notmet.br.com.careman.domain.Warranty.dateEnd,warranty.dateEnd.min.notmet.dateEnd,warranty.dateEnd.min.notmet.java.util.Date,warranty.dateEnd.min.notmet,min.notmet.br.com.careman.domain.Warranty.dateEnd,min.notmet.dateEnd,min.notmet.java.util.Date,min.notmet; arguments Date end,class br.com.careman.domain.Warranty,Wed Sep 06 00:00:00 BRT 24,Fri Jun 23 11:29:10 BRT 2017; default message {0} is less than minimum value: {3}.",
//				details, "Diferentes!");
	}
}
