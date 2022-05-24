package com.ilabquality.tests.pageObjects;

import com.google.gson.JsonObject;
import com.ilabquality.modules.testing.BaseContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddGlobalAssignmentObject {

  protected WebDriver driver;
  protected BaseContext context = BaseContext.getInstance();
  protected JsonObject testParams = context.testParams;

  public AddGlobalAssignmentObject(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }


  @FindBy(xpath = "//*[@id='__button4-BDI-content']/parent::span/parent::span/parent::button")
  public WebElement actionBtn;

  @FindBy(xpath = "//div[text()='Add: Global Assignment Details']")
  public WebElement AddGlobalItem;

  @FindBy(xpath = "//span[text()='Event']/parent::div/parent::span/parent::div/parent::div/descendant::tr[4]/descendant::td[2]/descendant::span")
  public WebElement EventReason;

  @FindBy(xpath = "//bdi[text()='Assignment Type']/parent::span/parent::span/parent::div/parent::div//input")
  public WebElement AssignmentType;

  @FindBy(xpath = "//bdi[text()='Start Date']/parent::span/parent::span/parent::div/parent::div//input")
  public WebElement startDate;

  @FindBy(xpath = "//*[@id='__picker1-content']//input")
  public WebElement plannedEndDate;

  @FindBy(xpath = "//bdi[text()='Company']/parent::span/parent::div/parent::div/descendant::input")
  public WebElement company;

  @FindBy(xpath = "//bdi[text()='Continue']")
  public WebElement btnContinue;

  @FindBy(xpath = "//bdi[contains(text(),'Posi')]/parent::span/parent::span/parent::div/parent::div/descendant::div[5]//")
  public WebElement Position;

  @FindBy(xpath = "//bdi[text()='Pay Grade']/parent::span/parent::div/following-sibling::div/span/span")
  public WebElement PayGrade;

  @FindBy(xpath = " //bdi[text()='Submit']")
  public WebElement btnSubmit;

  @FindBy(xpath = "//span[text()='Approvers']/parent::div/parent::div/div/div/div/div/div/span/parent::div/parent::div/descendant::bdi")
  public WebElement lstApprovers;

  @FindBy(xpath = "//bdi[text()='Approve']")
  public WebElement btnApprove;

  @FindBy(xpath = "//div[@class='surjUserPhotoName']")
  public WebElement btnUserIcon;

  @FindBy(xpath = "//a[text()='Proxy Now']")
  public WebElement lnkProxyNow;

  @FindBy(xpath = "//span[text()='Select Target User']/parent::h2/parent::div/parent::div/parent::div/parent::header/parent::div/descendant::input")
  public WebElement txtTargetUser;

  @FindBy(xpath = "//bdi[text()='OK']")
  public WebElement btnOk;
  @FindBy(xpath = "//a[text()='Show workflow participants']")
  public WebElement btnShowWorkflowParticipants;

  @FindBy(xpath = "//bdi[text()='Confirm']")
  public WebElement btnConfirm;

  @FindBy(xpath = "//div[contains(text(),'Request')]")
  public WebElement btnApproveRequest;

  @FindBy(xpath = "//table/descendant::bdi[text()='Approve']")
  public WebElement btnRequestApprove;

  @FindBy(xpath = "//bdi[text()='Close']")
  public WebElement btnClose;

  @FindBy(xpath = "//bdi[text()='Employment']")
  public WebElement lblEmployment;


  @FindBy(xpath = "//*[@id=\"__item11-__list10-3\"]")
  public WebElement lblAdminCenter;

  @FindBy(xpath = "//*[@id=\"bizXSearchField-I\"]")
  public WebElement searchEmployee;


  @FindBy(xpath = "//span[text()='Position Information']/parent::div/button[2]")
  public WebElement btnHistoryPositionInformation;

  @FindBy(xpath = " //*[@id=\"__item95-__list2-0\"]/div[1]")
  public WebElement AsOfDate;

  @FindBy(xpath = " //span[text()='Change History']/ancestor::div/descendant::header/descendant::button")
  public WebElement closeHistoryWindow;


  @FindBy(xpath = "//td[@data-help-id='Event Reason']/following-sibling::td//span")
  public WebElement lblEventReason;




}




