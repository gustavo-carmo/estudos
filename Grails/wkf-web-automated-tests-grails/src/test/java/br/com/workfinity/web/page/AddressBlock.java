package br.com.workfinity.web.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddressBlock extends Page {
	
	String idZipCode = "zipCodeTextField";

	public AddressBlock(WebDriver driver) {
		super(driver);
	}

	public AddressBlock setZipCode(String zipCode) {
		
		setFieldById(idZipCode, zipCode);
		WebElement webElement = findById(idZipCode);
		webElement.sendKeys(Keys.TAB);
		waitAjaxEnd(2000);
		return this;
	}

	public AddressBlock setAddress(String address) {
		
		setFieldById("address.address", address);
		return this;
	}

	public AddressBlock setNumber(String number) {
		
		setFieldById("address.number", number);
		return this;
	}

	public AddressBlock setComplement(String complement) {
		
		setFieldById("address.complement", complement);
		return this;
	}

	public AddressBlock setDistrict(String district) {
		
		setFieldById("address.district", district);
		return this;
	}

	public AddressBlock setCountry(String countryFullName, int positionId) {
		
		waitAjaxEnd();
		setFieldAutoCompleteById("autocomplete_countryId", countryFullName);
		return this;
	}

	public AddressBlock setState(String stateFullName, int positionId) {
		
		waitAjaxEnd();
		setFieldAutoCompleteById("autocomplete_stateId", stateFullName);
		return this;
	}

	public AddressBlock setCity(String cityFullName, int positionId) {
		
		waitAjaxEnd();
		setFieldAutoCompleteById("autocomplete_cityId", cityFullName);
		return this;
	}

	public AddressBlock clearAllBoxs() {
		
		setZipCode("");
		setAddress("");
		setNumber("");
		setComplement("");
		setDistrict("");
		setCity("", 3);
		setState("", 2);
		setCountry("", 1);
		return this;
	}		
}
