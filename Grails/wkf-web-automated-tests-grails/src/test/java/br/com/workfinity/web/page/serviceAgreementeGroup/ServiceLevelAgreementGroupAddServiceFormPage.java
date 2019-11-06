package br.com.workfinity.web.page.serviceAgreementeGroup;

import org.openqa.selenium.WebDriver;

import br.com.workfinity.web.page.Page;

public class ServiceLevelAgreementGroupAddServiceFormPage extends Page {

	public ServiceLevelAgreementGroupAddServiceFormPage(WebDriver driver) {
		super(driver);
	}

	public ServiceLevelAgreementGroupShowPage buttonCreateSucess() {
		try {
			// PRECISA DESSA ESPERA POR QUE, ALGUNS CAMPOS SÃO GERADOS E EXCLUIDOS DO FORMULARIO, E NÃO SÃO FEITO EM REQUISIÇÕES AJAX
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		return new ServiceLevelAgreementGroupShowPage(driver);
	}

	public ServiceLevelAgreementGroupAddServiceFormPage buttonCreateFail() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setCriterion(String criterion) {
		setSelectByIdAndVisibleText("criterion", criterion);
		waitAjaxEnd();
		return this;
	}

	public ServiceLevelAgreementGroupShowPage buttonUpdate() {
		clickByCssSelector("button.btn.btn.btn-primary.noWarn");
		return new ServiceLevelAgreementGroupShowPage(driver);
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setPrazo(int numero) {
		setSelectByIdAndVisibleText("termValue", String.valueOf(numero));
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setPrazoTipoDia(String tipo) {
		setSelectByIdAndVisibleText("termTypeDetail", tipo);
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setPrestadorDeServico(int numero) {
		setSelectByIdAndVisibleText("serviceProviderTermValue", String.valueOf(numero));
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setHD(int numero) {
		setSelectByIdAndVisibleText("hdTermValue", String.valueOf(numero));
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setHDTempo(String tipo) {
		setSelectByIdAndVisibleText("hdTermType", tipo);
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setPrestadorDeServicoTipoDia(String tipo) {
		setSelectByIdAndVisibleText("serviceProviderTermTypeDetail", tipo);
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setEscolha(String escolha) {
		setSelectByIdAndVisibleText("distanceFromCapital.type", escolha);
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setDistancia(int km) {
		setSelectByIdAndVisibleText("distanceFromCapital.value", String.valueOf(km));
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setHDTipoDia(String tipo) {
		setSelectByIdAndVisibleText("hdTermTypeDetail", tipo);
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setPrazoTempo(String tipo) {
		setSelectByIdAndVisibleText("termType", tipo);
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setPrestadorDeServicoTempo(String tipo) {
		setSelectByIdAndVisibleText("serviceProviderTermType", tipo);
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setEstado(String estado) {
		setSelectByIdAndVisibleText("state_id", estado);
		waitAjaxEnd();
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setCity(String city) {
		findByCssSelector("div#cities_values input[type='text']").sendKeys(city);
		waitAjaxEnd();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		findByCssSelector("div.ms-res-ctn.dropdown-menu div").click();
		return this;
	}

	public ServiceLevelAgreementGroupAddServiceFormPage setRegiao(String region) {
		setSelectByIdAndVisibleText("region", region);
		return this;
	}
	
	
}
