package br.com.workfinity.web.page.customer;

import java.util.List;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;
import br.com.workfinity.web.page.StringUtils;

public class CustomerCrudFormPage extends Page {

	public CustomerCrudFormPage(WebDriver driver) {
		super(driver);
	}

	public CustomerCrudFormPage setDocument(String type, String number) {
		setSelectByIdAndVisibleText("documentType", type);
		setFieldById("documentNumber", number);
		return this;
	}

	public CustomerCrudFormPage setName(String name) {
		setFieldById("customer_name", name);
		return this;
	}

	public CustomerCrudFormPage setContractor(String contractor) {
		setSelectByIdAndVisibleText("customer_contractor", contractor);
		waitAjaxEnd();
		return this;
	}

	public CustomerCrudFormPage setPhoneNumber(String number) {
		setFieldById("phoneNumber", number);
		return this;
	}

	public CustomerCrudFormPage setEmail(String email) {
		setFieldById("customer_email", email);
		return this;
	}

	public void buttonCreate() {
		clickByCssSelector("button.btn.btn-primary.noWarn");
		waitAjaxEnd();
	}

	public void buttonUpdate() {
		clickByCssSelector("button.btn.btn-primary.noWarn");
		waitAjaxEnd();
	}

	public void buttonDelete() {
		clickByCssSelector("button.btn.btn-danger.noWarn");
		driver.switchTo().alert().accept();
	}

	public CustomerListPage buttonClose() {
		clickByCssSelector(".close");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		return new CustomerListPage(driver);
	}

	public CustomerCrudFormPage setAlias(String alias) {
		setFieldById("customer_alias", alias);
		return this;
	}

	public CustomerCrudFormPage setStatus(String status) {
		setSelectByIdAndVisibleText("customer_status", status);
		return this;
	}

	public CustomerCrudFormPage entraNaAbaDeHistoricoDeAlteracao() {
		clickById("showHistoryTabAddress");
		waitAjaxEnd();

		return this;
	}

	public CustomerCrudFormPage procuraALinhaQueContemOsValoresPassadosNaTabelaDeHistoricoDeAlteracao(List<String> valoresASeremValidados) {
		validateIfContainsTheRowInTable(valoresASeremValidados, "addressHistories",
				"Não foi encontrado a linha com os valores [" + StringUtils.myJoin(", ", valoresASeremValidados)
						+ "], na tabela de Historico de alteração de endereço de clientes");
		return this;
	}

	public CustomerCrudFormPage voltaParaPrimeiraAba() {
		clickByCssSelector("ul.nav.nav-tabs li a[href='#mainTab']");
		waitAjaxEnd();
		
		return this;
	}
}
