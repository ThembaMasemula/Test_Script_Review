package com.ilabquality.tests.pageObjects;

import com.google.gson.JsonObject;
import com.ilabquality.modules.testing.BaseContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DemotionWithoutPayObject {

  protected WebDriver driver;
  protected BaseContext context = BaseContext.getInstance();
  protected JsonObject testParams = context.testParams;

  public DemotionWithoutPayObject(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }


  @FindBy(xpath = "//*[@id='__button4-BDI-content']/parent::span/parent::span/parent::button")
  public WebElement actionBtn;

  @FindBy(xpath = "//span[text()='Change Job and Compensation Info']")
  public WebElement CompensationInfoItem;

  @FindBy(xpath = "//div[@title='Job Information']/div")
  public WebElement checkBoxJobInfo;

  @FindBy(xpath = "//*[@id=\"__box3-inner\"]")
  public WebElement demoPosition;

  @FindBy(xpath = "//bdi[text()='Save']")
  public WebElement demoSaveBtn;

  @FindBy(xpath = "//bdi[text()='When would you like your changes to take effect?']/parent::span/parent::label/parent::div/parent::div/parent::div//input")
  public WebElement txtDateWillChangeEffect;

  @FindBy(xpath = "//bdi[text()='Confirm']")
  public WebElement confirmButton;

  @FindBy(xpath = "//a[text()='Show workflow participants']")
  public WebElement btnShowWorkflowParticipants;

  @FindBy(xpath = "//bdi[text()='Confirm']")
  public WebElement btnConfirm;

  @FindBy(xpath = "//span[text()='Approvers']/parent::div/parent::div/div/div/div/div/div/span/parent::div/parent::div/descendant::bdi")
  public WebElement lstApprovers;

  @FindBy(xpath = "//bdi[text()='Approve']")
  public WebElement btnApprove;

  @FindBy(xpath = "//img[@class='surjUserPhotoImg']")
  public WebElement btnUserIcon;

  @FindBy(xpath = "//a[text()='Proxy Now']")
  public WebElement lnkProxyNow;

  @FindBy(xpath = "//span[text()='Select Target User']/parent::h2/parent::div/parent::div/parent::div/parent::header/parent::div/descendant::input")
  public WebElement txtTargetUser;

  @FindBy(xpath = "//bdi[text()='OK']")
  public WebElement btnOk;

  @FindBy(xpath = "//div[contains(text(),'Request')]")
  public WebElement btnApproveRequest;

  @FindBy(xpath = "//table/descendant::bdi[text()='Approve']")
  public WebElement btnRequestApprove;

  @FindBy(xpath = "//bdi[text()='Close']")
  public WebElement btnClose;

  @FindBy(xpath = "//bdi[text()='Employment']")
  public WebElement lblEmployment;


  @FindBy(xpath = "(//ul[@role='listbox']//a)[3]")
  public WebElement lblAdminCenter;

  @FindBy(xpath = "//*[@id=\"bizXSearchField-I\"]")
  public WebElement searchEmployee;


  @FindBy(xpath = "//span[text()='Position Information']/parent::div/button[2]")
  public WebElement btnHistoryPositionInformation;

  @FindBy(xpath = "//*[@id=\"__item51-__list1-0\"]/div[1]")
  public WebElement AsOfDate;

  @FindBy(xpath = "//*[@id=\"__button86-inner\"]")
  public WebElement closeHistoryWindow;


  @FindBy(xpath = "//td[@data-help-id='Event Reason']/following-sibling::td//span")
  public WebElement lblEventReason;




}




