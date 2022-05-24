package com.ilabquality.pages;

import org.openqa.selenium.Keys;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class RehireFormPage extends BasePage{

	public RehireFormPage(Framework fw) {
		super(fw);
	}

	public void selectEventReason(String eventReason) throws ElementNotFoundException, Exception {
		fw.sendText("RehireFormPage.EventReasonInput", eventReason);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.EventReasonInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.EventReasonInput", Keys.ENTER);
		fw.clickElement("RehireFormPage.EventReasonItem");
	}

	public void sendHireDate(String date) throws Exception {
		waitUntilElement("RehireFormPage.hireDateInput");fw.clearText("RehireFormPage.hireDateInput");
		fw.sendText("RehireFormPage.hireDateInput", date);
}
  public void inputCountryRegion(String text) throws Exception {
		fw.sendText("RehireFormPage.countryRegion", text);
	Thread.sleep(1500);
		fw.sendKeys("RehireFormPage.countryRegion", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.countryRegion", Keys.ENTER);
	  fw.clickElement("RehireFormPage.countryItem");
	}

	public void clickAddCountry() throws ElementNotFoundException, Exception{
		fw.clickElement("RehireFormPage.buttonAddCountry");
	}

	public void selectCountry(String text) throws Exception {
		fw.sendText("RehireFormPage.countryInput", text);
		Thread.sleep(1500);
		fw.sendKeys("RehireFormPage.countryInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.countryInput", Keys.ENTER);
	fw.clickElement("RehireFormPage.countryItem");
	}

	public void selectSocialSecurity() throws ElementNotFoundException, Exception {
		fw.clickElement("RehireFormPage.nationalIdCardTypeArrow");
	Thread.sleep(1500);
		fw.sendKeys("RehireFormPage.socialSecurityItem", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.socialSecurityItem", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.socialSecurityItem", Keys.ENTER);
	//	fw.clickElement("RehireFormPage.socialSecurityItem");
	}

	public void inputSocialSecurity(String text) throws Exception {
		fw.sendText("RehireFormPage.nationalIdInput", text);
	}

	public void selectIsPrimaryCountry() throws ElementNotFoundException, Exception {
		fw.clickElement("RehireFormPage.isPrimaryInput");
		Thread.sleep(1500);
		fw.sendKeys("RehireFormPage.IsPrimaryItem", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.IsPrimaryItem", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.IsPrimaryItem", Keys.ENTER);
	//	fw.clickElement("RehireFormPage.IsPrimaryItem");
	}

	public void clickContinueFirst() throws ElementNotFoundException, Exception {
		Thread.sleep(1500);
    fw.scrollUntilElementByXpath("RehireFormPage.buttonContinueFirst");
		fw.clickElement("RehireFormPage.buttonContinueFirst");
	}

	public void inputCountryCode(String code) throws Exception {
		waitUntilElement("RehireFormPage.countryCodeInput");
		fw.sendText("RehireFormPage.countryCodeInput", code);
	}

	public void inputCountryOfBirth(String text) throws Exception {
		fw.sendText("RehireFormPage.countryOfBirthInput", text);
		Thread.sleep(1500);
		fw.sendKeys("RehireFormPage.countryOfBirthInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.countryOfBirthInput", Keys.ENTER);
	}

	public void inputAreaCode(String code) throws Exception {
		fw.sendText("RehireFormPage.areaCodeInput", code);
	}

	public void inputPhone(String phone) throws Exception{
		fw.sendText("RehireFormPage.phoneInput", phone);
	}

	public void clickContinueSecond() throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("RehireFormPage.buttonContinueSecond");
		fw.clickElement("RehireFormPage.buttonContinueSecond");
	}

	public void selectPosition(String position) throws ElementNotFoundException, Exception {
		fw.sendText("RehireFormPage.positionInput", position);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.positionInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.positionInput", Keys.ENTER);
	}

	public void selectEmployeeClass(String employeeClass) throws ElementNotFoundException, Exception {
		fw.clearText("RehireFormPage.employeeClassInput");
		fw.sendText("RehireFormPage.employeeClassInput", employeeClass);
		Thread.sleep(5000);
		fw.sendKeys("RehireFormPage.employeeClassInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.employeeClassInput", Keys.ENTER);
		Thread.sleep(2500);
	}

	public void selectPayScaleType(String payScaleType) throws Exception {
		fw.sendText("RehireFormPage.payScaleTypeInput", payScaleType);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.payScaleTypeInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.payScaleTypeInput", Keys.ENTER);
	}

	public void selectPayScaleArea(String payScaleArea) throws Exception {
		fw.sendText("RehireFormPage.payScaleAreaInput", payScaleArea);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.payScaleAreaInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.payScaleAreaInput", Keys.ENTER);
	}

	public void clickButtonContinueThird() throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("RehireFormPage.buttonContinueThird");
		fw.clickElement("RehireFormPage.buttonContinueThird");
	}

	public void selectPayGroup(String payGroup) throws Exception {
		fw.clearText("RehireFormPage.payGroupInput");
		fw.sendText("RehireFormPage.payGroupInput", payGroup);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.payGroupInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.payGroupInput", Keys.ENTER);
	}

	public void clickButtonAddCompensation() throws ElementNotFoundException, Exception {
		fw.clickElement("RehireFormPage.buttonAddCompensation");
	}

	public void selectBusinessUnit(String businessUnit) throws ElementNotFoundException, Exception {
		Thread.sleep(3000);
		fw.sendText("RehireFormPage.txtBusinessUnit", businessUnit);
		Thread.sleep(3000);
		//fw.sendKeys("RehireFormPage.txtBusinessUnit", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.txtBusinessUnit", Keys.ENTER);
	}

	public void selectLocation(String location) throws ElementNotFoundException, Exception {
		fw.sendText("RehireFormPage.txtlocation", location);
		Thread.sleep(3000);
		//fw.sendKeys("RehireFormPage.txtlocation", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.txtlocation", Keys.ENTER);
	}

	public void selectJobClassification(String jobClassification) throws ElementNotFoundException, Exception {
		fw.sendText("RehireFormPage.txtJobClassification", jobClassification);
		Thread.sleep(3000);
		//fw.sendKeys("RehireFormPage.txtJobClassification", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.txtJobClassification", Keys.ENTER);
	}

	public void enterJobTitle(String jobTitle) throws ElementNotFoundException, Exception {
		fw.sendText("RehireFormPage.txtJobTitle", jobTitle);
		Thread.sleep(3000);

	}

	public void selectAddressType(String addressType) throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("RehireFormPage.txtAddressType");
		waitUntilElement("RehireFormPage.txtAddressType");
		//fw.clickElement("//*[@id='__title23-inner']");
		//fw.setFocus("RehireFormPage.txtAddressType");
		fw.clickElement("RehireFormPage.txtAddressType");
		fw.clearText("RehireFormPage.txtAddressType");
		fw.sendKeys("RehireFormPage.txtAddressType", Keys.TAB);
		fw.sendText("RehireFormPage.txtAddressType", addressType);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.txtAddressType", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.txtAddressType", Keys.ENTER);

	}

	public void selectTimezone(String timeZone) throws ElementNotFoundException, Exception {
		fw.sendText("RehireFormPage.txtTimezone", timeZone);
		Thread.sleep(3000);
		//fw.sendKeys("RehireFormPage.txtTimezone", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.txtTimezone", Keys.ENTER);
	}

	public void selectCompensationPayComponent(String payCompontent) throws Exception {
		fw.sendText("RehireFormPage.compensationPayComponentInput", payCompontent);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.compensationPayComponentInput", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.compensationPayComponentInput", Keys.ENTER);
	}

	public void inputCompensationAmount(String amount) throws Exception {
		fw.sendText("RehireFormPage.compensationAmount", amount);
	}

	public void selectCompensationCurrency(String currency) throws Exception {
		fw.sendText("RehireFormPage.compensationCurrency", currency);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.compensationCurrency", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.compensationCurrency", Keys.ENTER);
	}

	public void selectCompensationFrequency(String frequency) throws Exception {
		fw.sendText("RehireFormPage.compensationFrequency", frequency);
		Thread.sleep(3000);
		fw.sendKeys("RehireFormPage.compensationFrequency", Keys.ARROW_DOWN);
		fw.sendKeys("RehireFormPage.compensationFrequency", Keys.ENTER);
	}

	public void clickButtonContinueForth() throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("RehireFormPage.buttonContinueForth");
		fw.clickElement("RehireFormPage.buttonContinueForth");
	}

	public void clickViewProfile() throws ElementNotFoundException, Exception {
		waitUntilElement("RehireFormPage.viewProfileButton");
		fw.clickElement("RehireFormPage.viewProfileButton");
	}

	public void selectIsFulltimeEmployee(String isFulltimeEmployee) throws Exception {
		String locator = "RehireFormPage.isFulltimeEmployee";
		fw.sendText(locator, isFulltimeEmployee);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void setFirstDateWorked(String firstDateWorked) throws Exception {
		fw.setFocus("RehireFormPage.txtFirstDateWorked");
		Thread.sleep(1000);
		fw.clickElement("RehireFormPage.txtFirstDateWorked");
		fw.clearText("RehireFormPage.txtFirstDateWorked");
		fw.sendText("RehireFormPage.txtFirstDateWorked", firstDateWorked);
	}

	public void selectCompany(String company) throws Exception {
		String locator = "RehireFormPage.companyInput";
		fw.sendText(locator, company);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

}
