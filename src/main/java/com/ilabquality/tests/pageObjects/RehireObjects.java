package com.ilabquality.tests.pageObjects;

import com.google.gson.JsonObject;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.utilsWeb.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RehireObjects extends WebActions {
  protected WebDriver driver;
  protected BaseContext context = BaseContext.getInstance();
  protected JsonObject testParams = context.testParams;

  public RehireObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }


  @FindBy(xpath = "//*[@id=\"5__write\"]")
  public WebElement txtSearchEmployee;

  @FindBy(xpath = "//*[@id=\"dlgButton_8_\"]")
  public WebElement btnSearchInactiveEmployee;

  @FindBy(xpath = "//a[@class='SFContextualMenuLabel link']")
  public WebElement lblEmployeeName;

  @FindBy(xpath = "(//a[@class='link'])[2]")
  public WebElement rehireEmployee;


  @FindBy(xpath = "//bdi[contains(text(),'Event Rea')]/parent::span/parent::span/parent::div/parent::div//input")
  public WebElement eventReason;

  @FindBy(xpath = "//bdi[text()='Continue']")
  public WebElement firstBtnContinue ;

  @FindBy(xpath = "//span[@title='Phone Number']/parent::div/parent::th/parent::tr/parent::thead/parent::table/descendant::tbody/descendant::tr/descendant::td[5]//input")
  public WebElement phoneNumber;

  @FindBy(xpath = "(//bdi[text()='Continue'])[2]")
  public WebElement secondBtnContinue;

  @FindBy(xpath = "//bdi[contains(text(),'Posi')]/parent::span/parent::span/parent::div/parent::div/descendant::div[5]//")
  public WebElement position;

  @FindBy(xpath = "(//bdi[text()='Continue'])[3]")
  public WebElement thirdBtnContinue;

  @FindBy(xpath = "//span[@title='Pay Group']/parent::div/parent::div/descendant::input")
  public WebElement payGroup;

  @FindBy(xpath = "//bdi[text()='Add Payment Information']/parent::span/parent::span/parent::button/parent::div/parent::div/parent::div/parent::div/parent::div/following-sibling::div//bdi")
  public WebElement forthBtnContinue;

  @FindBy(xpath = "//span[text()='Next Steps']/parent::div/parent::div/parent::div/descendant::a")
  public WebElement viewProfile;

  @FindBy(xpath = "//bdi[text()='Employment']")
  public WebElement lblEmployment;

  @FindBy(xpath = "//span[text()='Job Information']/parent::div/parent::span/parent::div/descendant::button[2]")
  public WebElement jobHistoryInfo;

  @FindBy(xpath = "//td[@data-help-id='Event Reason']/following-sibling::td//span")
  public WebElement lblEventReason;






  //div[contains(@class,'sapMInputBaseContentWrapper sapMInputBaseContentWrapperState')]//input[1]

  /*@FindBy(xpath = "//*[@id=\"__picker0-inner\"]")
  public WebElement txtHireDateEmploymentDetails;

  @FindBy(xpath = "//*[@id=\"__box0-inner\"]")
  public WebElement txtCompany;

  @FindBy(xpath = "//*[@id=\"__box1-inner\"]")
  public WebElement txtEmpUserName;

  @FindBy(xpath = "//*[@id=\"__box1-inner\"]")
  public WebElement txtRehireEventReason;

  @FindBy(xpath = "//*[@id=\"__input0-inner\"]")
  public WebElement txtRehireFirstName;

  @FindBy(xpath = "//span[@title='Nationality']/parent::div/parent::div/parent::div/descendant::input")
  public WebElement txtNationality;

  @FindBy(xpath = "//*[@id=\"__input1-inner\"]")
  public WebElement txtRehireLastName;

  @FindBy(xpath = "//*[@id=\"__input2-inner\"]")
  public WebElement txtMiddleName;

  @FindBy(xpath = "//*[@id=\"__box2-inner\"]")
  public WebElement txtSalutation;

  @FindBy(xpath = "//*[@id=\"__box3-inner\"]")
  public WebElement txtSuffix;

  @FindBy(xpath = "//*[@id=\"__picker1-inner\"]")
  public WebElement txtDateOfBirth;

  @FindBy(xpath = "//*[@id=\"__box4-inner\"]")
  public WebElement txtCountryOfBirth;

  @FindBy(xpath = "//*[@id=\"__input6-inner\"]")
  public WebElement txtPersonId;

  @FindBy(xpath = "//*[@id=\"__input7-inner\"]")
  public WebElement txtUsername;

  @FindBy(xpath = "//*[@id=\"__button19\"]")
  public WebElement btnAddNationalIdInformation;

  @FindBy(xpath = "//*[@id=\"__box8-inner\"]")
  public WebElement textCountry;

  @FindBy(xpath = "//*[@id=\"__box9-inner\"]")
  public WebElement txtNationalIdCardType;

  @FindBy(xpath = "//*[@id=\"__input9-inner\"]")
  public WebElement textAddNationalID;

  @FindBy(xpath = "//*[@id=\"__box10-inner\"]")
  public WebElement textIsPrimary;

  @FindBy(xpath = "//span[@title='First Date Worked']/parent::div/parent::div//input")
  public WebElement buttont;

  @FindBy(xpath = "//*[@id=\"__box8-inner\"]")
  public WebElement textGender;

  @FindBy(xpath = "//*[@id=\"__box9-inner\"]")
  public WebElement txtMaritalStatus;

  @FindBy(xpath = "//*[@id=\"__box10-inner\"]")
  public WebElement txtNationalityInfo;

  @FindBy(xpath = "//*[@id=\"__box11-inner\"]")
  public WebElement prefferedLanguage;

  @FindBy(xpath = "//*[@id=\"__box12-inner\"]")
  public WebElement txtCountryRegion;

  @FindBy(xpath = "//*[@id=\"__box13-inner\"]")
  public WebElement txtEthnicGroup;

  @FindBy(xpath = "//*[@id=\"__box14-inner\"]")
  public WebElement txtVeteran;

  @FindBy(xpath = "//*[@id=\"__box15-inner\"]")
  public WebElement txtChallengedVeteran;

  @FindBy(xpath = "//*[@id=\"__box16-inner\"]")
  public WebElement txtSpecialChallengedVeteran;

  @FindBy(xpath = "//*[@id=\"__box17-inner\"]")
  public WebElement txtVisaType;

  @FindBy(xpath = "//*[@id=\"__box18-inner\"]")
  public WebElement txtChallengeGroup;

  @FindBy(xpath = "//*[@id=\"__box19-inner\"]")
  public WebElement txtTypeOfChallenge;

  @FindBy(xpath = "//*[@id=\"__box20-inner\"]")
  public WebElement txtChallenge;

  @FindBy(xpath = "//*[@id=\"__picker2-inner\"]")
  public WebElement txtDateOfDetermination;

  @FindBy(xpath = "//*[@id=\"__picker3-inner\"]")
  public WebElement txtDateOfSeparation;

  @FindBy(xpath = "//*[@id=\"__panel16-expandButton-inner\"]")
  public WebElement btnExpandGlobalInformation;

  @FindBy(xpath = "//*[@id=\"__box24-inner\"]")
  public WebElement txtCountryOrigin;

  @FindBy(xpath = "//*[@id=\"__button44-inner\"]")
  public WebElement btnAddPhoneInformation;

  @FindBy(xpath = "//*[@id=\"__box27-inner\"]")
  public WebElement txtPhoneType;

  @FindBy(xpath = "//*[@id=\"__input15-inner\"]")
  public WebElement txtCountryCode;

  @FindBy(xpath = "//*[@id=\"__input16-inner\"]")
  public WebElement txtAreaCode;

  @FindBy(xpath = "//*[@id=\"__input18-inner\"]")
  public WebElement txtExtension;

  @FindBy(xpath = "//*[@id=\"__input15-inner\"]")
  public WebElement txtCountryCode;

  @FindBy(xpath = "//*[@id=\"__input15-inner\"]")
  public WebElement txtCountryCode;

  @FindBy(xpath = "//*[@id=\"__input15-inner\"]")
  public WebElement txtCountryCode;

  @FindBy(xpath = "//*[@id=\"__input15-inner\"]")
  public WebElement txtCountryCode;

  @FindBy(xpath = "//*[@id=\"__input15-inner\"]")
  public WebElement txtCountryCode;*/



}
