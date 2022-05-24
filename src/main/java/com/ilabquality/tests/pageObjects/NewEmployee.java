package com.ilabquality.tests.pageObjects;

import com.google.gson.JsonObject;

import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NewEmployee extends WebActions {
  protected WebDriver driver;
  protected BaseContext context = BaseContext.getInstance();
  protected JsonObject testParams = context.testParams;

  public NewEmployee(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div[2]/section/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[1]/div/div/div/div/div/div/div/div[1]/div/div[2]/div/div/input")
  public WebElement txtDateInput;

  @FindBy(xpath = "//span[@title='Company']/parent::div/parent::div/parent::div//input")
  public WebElement txtCompanyInput;

  @FindBy(xpath = "//span[@title='Event Reason']/parent::div/parent::div/parent::div//input")
  public WebElement txtEventReasonInput;

  @FindBy(xpath = "//bdi[text()='First Name']/parent::span/parent::span/parent::div/parent::div/descendant::input")
  public WebElement txtFirstNameInput;

  @FindBy(xpath = "//bdi[text()='Last Name']/parent::span/parent::span/parent::div/parent::div/descendant::input")
  public WebElement txtLastNameInput;

  @FindBy(xpath = "//bdi[text()='Date Of Birth']/parent::span/parent::span/parent::div/parent::div/descendant::input")
  public WebElement txtDateOfBirthInput;

  @FindBy(xpath = "//span[@title='Country Of Birth']/parent::div/parent::div/parent::div/descendant::input")
  public WebElement txtCountryOfBirthInput;

  @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div[2]/section/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[4]/div/div/div/div/div/div[2]/div/div[2]/div/div[2]/div/div/input")
  public WebElement txtEmpUserName;

  @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div[2]/section/div[2]/div[2]/div/div/div[1]/div/div/div[1]/div[6]/button")
  public WebElement btnContinueFirst;

  @FindBy(xpath = "//span[@title='Preferred Language']/parent::div/parent::div/parent::div/descendant::input")
  public WebElement txtPreferredLanguage;

  @FindBy(xpath = "//span[@title='Nationality']/parent::div/parent::div/parent::div/descendant::input")
  public WebElement txtNacionality;

  @FindBy(xpath = "//span[@title='Phone Number']/parent::div/parent::th/parent::tr/parent::thead/parent::table/descendant::tbody/descendant::tr/descendant::td[5]//input")
  public WebElement txtPhone;

  @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div[2]/section/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/div[3]/button")
  public WebElement btnAddEmail;

  @FindBy(xpath = "//*[@id=\"__box24-inner\"]")
  public WebElement txtEmailType;

  @FindBy(xpath = "//*[@id='__input13-inner']")
  public WebElement txtEmailAddress;

  @FindBy(xpath = "//*[@id='__box23-inner']")
  public WebElement txtPhoneType;

  @FindBy(xpath = "//*[@id='__input16-inner']")
  public WebElement txtPhoneNumber;


  @FindBy(xpath = "//*[@id='__box22-inner']")
  public WebElement txtIsEmmilPrimary;

  @FindBy(xpath = "//*[@id=\"__box27-inner\"]")
  public WebElement txtIsPhonePrimary;


  @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div[2]/section/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[2]/div/div[2]/div/div[3]/button")
  public WebElement btnAddPhone;

  @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div[2]/section/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[6]/button")
  public WebElement btnContinueSecond;

  @FindBy(xpath = "//span[@title='Position']/parent::div/parent::div/parent::div//input")
  public WebElement txtPosition;

  @FindBy(xpath = "//*[@id='__box25-arrow']")
  public WebElement drpPosition;

  @FindBy(xpath = "//*[@id=\"__box29-inner\"]")
  public WebElement OrgCompany;

  @FindBy(xpath = "//*[@id=\"__box30-inner\"]")
  public WebElement OrgBusinessUnit;

  @FindBy(xpath = "//*[@id=\"__box33-inner\"]")
  public WebElement OrgLocation;

  @FindBy(xpath = "//*[@id=\"__box34-inner\"]")
  public WebElement OrgTimeZone;

  @FindBy(xpath = "//*[@id=\"__box36-inner\"]")
  public WebElement OrgJobClassification;

  @FindBy(xpath = "//*[@id=\"__input23-inner\"]")
  public WebElement OrgHireDate;

  @FindBy(xpath = " //*[@id=\"__picker5-inner\"]")
  public WebElement OrgJobTittle;


  @FindBy(xpath = "//span[@title='Hire Date']/parent::div/parent::div/descendant::div[contains(@id,'__picker5')]//input")
  public WebElement txtHireDateEmploymentDetails;

  @FindBy(xpath = "//span[@title='First Date Worked']/parent::div/parent::div//input")
  public WebElement txtFirstDateWorked;

  @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div[2]/section/div[2]/div[2]/div/div/div[3]/div/div/div[1]/div[6]/button")
  public WebElement btnContinueThird;

  @FindBy(xpath = "//span[@title='Pay Group']/parent::div/parent::div/descendant::input")
  public WebElement txtPayGroup;

  @FindBy(xpath = "/html/body/div[4]/div/div/div/div/div/div[2]/section/div[2]/div[2]/div/div/div[4]/div/div/div[1]/div[3]/button")
  public WebElement btnContinueForth;

  @FindBy(xpath = "//span[text()='Warning']/parent::h2/parent::div/parent::div/parent::div/parent::header/parent::div/descendant::bdi[text()='Proceed']")
  public WebElement buttonProceed;

  @FindBy(xpath = "//span[text()='Warning']/parent::h2/parent::div/parent::div/parent::div/parent::header/parent::div/descendant::bdi[text()='Correct']")
  public WebElement buttonCorrect;

  @FindBy(xpath = "//span[text()='Next Steps']/parent::div/parent::div/parent::div/descendant::a")
  public WebElement viewProfileButton;

  @FindBy(xpath = "//bdi[@id='__label2-bdi']/parent::span/parent::div/parent::div//input")
  public WebElement btnEventReason;
}
