package com.ilabquality.pages;

import org.openqa.selenium.Keys;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class HireFormPage extends BasePage {

  public HireFormPage(Framework fw) {
    super(fw);
  }

  public void inputHireDate(String hireDate) throws Exception {
    fw.clearText("HireFormPage.txtHireDate");
    fw.sendText("HireFormPage.txtHireDate", hireDate);
    Thread.sleep(3000);
    fw.sendKeys("HireFormPage.txtHireDate", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtHireDate", Keys.ENTER);
  }

  public void inputCompany(String company) throws Exception {
    fw.clearText("HireFormPage.txtCompany");
    fw.sendText("HireFormPage.txtCompany", company);
    Thread.sleep(3000);
    fw.sendKeys("HireFormPage.txtCompany", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtCompany", Keys.ENTER);
  }

  public void inputEventReason(String eventReason) throws ElementNotFoundException, Exception {
    fw.sendText("HireFormPage.txtEventReason", eventReason);
    Thread.sleep(3000);
    fw.sendKeys("HireFormPage.txtEventReason", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtEventReason", Keys.ENTER);
  }

  public void inputEmailType(String emailType) throws ElementNotFoundException, Exception {
    fw.sendText("HireFormPage.txtEmailType", emailType);
    fw.sendKeys("HireFormPage.txtEmailType", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtEmailType", Keys.ENTER);
  }

  public void inputPhoneType(String phoneType) throws ElementNotFoundException, Exception {
    fw.sendText("HireFormPage.txtPhoneType", phoneType);
    Thread.sleep(3000);
    fw.sendKeys("HireFormPage.txtPhoneType", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtPhoneType", Keys.ENTER);
  }

  public void inputEmailAddress(String emailAddress) throws Exception {
    fw.clearText("HireFormPage.txtEmailAddress");
    fw.sendText("HireFormPage.txtEmailAddress", emailAddress);
  }

  public void inputIsEmailPrimary(String isEmailPrimary) throws ElementNotFoundException, Exception {
    fw.sendText("HireFormPage.txtIsEmmilPrimary", isEmailPrimary);
    fw.sendKeys("HireFormPage.txtIsEmmilPrimary", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtIsEmmilPrimary", Keys.ENTER);
  }

  public void inputIsPhonePrimary(String isPhonePrimary) throws ElementNotFoundException, Exception {
    fw.sendText("HireFormPage.txtIsPhonePrimary", isPhonePrimary);
    fw.sendKeys("HireFormPage.txtIsPhonePrimary", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtIsPhonePrimary", Keys.ENTER);
  }

  public void inputFirstName(String name) throws Exception {
    fw.clearText("HireFormPage.txtFirstName");
    fw.sendText("HireFormPage.txtFirstName", name);
  }

  public void inputLastName(String lastName) throws Exception {
    fw.clickElement("HireFormPage.txtLastName");
    Thread.sleep(3000);
    fw.sendText("HireFormPage.txtLastName", lastName);
  }

  public void inputDateOfBirth(String date) throws Exception {
    fw.clearText("HireFormPage.txtDateOfBirth");
    fw.sendText("HireFormPage.txtDateOfBirth", date);
  }

  public void inputEmpUsername(String username) throws Exception {
    fw.clearText("HireFormPage.txtEmpUserName");
    fw.sendText("HireFormPage.txtEmpUserName", username);
  }

  public void inputCountryOfBirth(String country) throws Exception {
    fw.clearText("HireFormPage.txtCountryOfBirth");
    fw.sendText("HireFormPage.txtCountryOfBirth", country);
    fw.sendKeys("HireFormPage.txtCountryOfBirth", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtCountryOfBirth", Keys.ENTER);
  }

  public void clickContinueFirst() throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("HireFormPage.btnContinueFirst");
    fw.clickElement("HireFormPage.btnContinueFirst");
  }

  public void inputPreferredLanguage(String language) throws Exception {
    fw.sendText("HireFormPage.txtPreferredLanguage", language);
    Thread.sleep(2000);
    fw.sendKeys("HireFormPage.txtPreferredLanguage", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtPreferredLanguage", Keys.ENTER);
  }

  public void inputNacionality(String nacionality) throws Exception {
    fw.sendText("HireFormPage.txtNacionality", nacionality);
    Thread.sleep(2000);
    fw.sendKeys("HireFormPage.txtNacionality", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtNacionality", Keys.ENTER);
  }

  public void inputPhone(String phone) throws Exception {
    fw.sendText("HireFormPage.txtPhone", phone);
  }


  public void inputPhoneNumber(String phoneNumber) throws Exception {
    fw.sendText("HireFormPage.txtPhoneNumber",  phoneNumber);
  }

  public void clickContinueSecond() throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("HireFormPage.btnContinueSecond");
    fw.clickElement("HireFormPage.btnContinueSecond");
  }

  public void clickAddEmail() throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("HireFormPage.btnAddEmail");
    fw.clickElement("HireFormPage.btnAddEmail");
  }

  public void clickAddPhone() throws ElementNotFoundException, Exception {
    fw.clickElement("HireFormPage.btnAddPhone");
  }

  public void selectPosition(String position) throws ElementNotFoundException, Exception {
    fw.clearText("HireFormPage.txtPosition");
    Thread.sleep(3000);
    fw.sendText("HireFormPage.txtPosition", position);
    Thread.sleep(10000);
    fw.sendKeys("HireFormPage.txtPosition", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtPosition", Keys.ENTER);
    Thread.sleep(5000);
  }

  public void choosePosition() throws ElementNotFoundException, Exception {
    fw.clickElement("HireFormPage.drpPosition");
    Thread.sleep(5000);
    fw.sendKeys("HireFormPage.drpPosition", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.drpPosition", Keys.ENTER);
  }

  public void inputHireDateEmploymentDetails(String hireDate) throws Exception {
    fw.clearText("HireFormPage.txtHireDateEmploymentDetails");
    fw.sendText("HireFormPage.txtHireDateEmploymentDetails", hireDate);
  }

  public void inputFirstDateWorked(String firstDateWorked) throws Exception {
    fw.clearText("HireFormPage.txtFirstDateWorked");
    fw.sendText("HireFormPage.txtFirstDateWorked", firstDateWorked);
  }

  public void clickButtonContinueThird() throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("HireFormPage.btnContinueThird");
    fw.clickElement("HireFormPage.btnContinueThird");
  }

  public void acceptWindow() throws ElementNotFoundException, Exception {
    fw.sendKeys("HireFormPage.txtPosition", Keys.ENTER);
    fw.sendKeys("HireFormPage.txtPosition", Keys.ENTER);
  }

  public void selectPayGroup(String payGroup) throws Exception {
    fw.clearText("HireFormPage.txtPayGroup");
    fw.sendText("HireFormPage.txtPayGroup", payGroup);
    Thread.sleep(5000);
    fw.sendKeys("HireFormPage.txtPayGroup", Keys.ARROW_DOWN);
    fw.sendKeys("HireFormPage.txtPayGroup", Keys.ENTER);
  }

  public void clickButtonContinueForth() throws ElementNotFoundException, Exception {
    fw.scrollUntilElementByXpath("HireFormPage.btnContinueForth");
    fw.clickElement("HireFormPage.btnContinueForth");
  }

  public void clickViewProfile() throws ElementNotFoundException, Exception {
    fw.clickElement("HireFormPage.viewProfileButton");
  }

  public void clickEventReason() throws ElementNotFoundException, Exception {
    String locator = "HireFormPage.btnEventReason";
    fw.clickElement(locator);
    // Thread.sleep(5000);
    fw.clearText(locator);
    fw.sendText(locator, "");
    Thread.sleep(2000);
    fw.sendKeys(locator, Keys.ARROW_DOWN);
  }
}
