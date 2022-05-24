package com.ilabquality.tests.pageObjects;

import com.google.gson.JsonObject;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.utilsWeb.WebActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class JobChangeObjects extends WebActions {
  protected WebDriver driver;
  protected BaseContext context = BaseContext.getInstance();
  protected JsonObject testParams = context.testParams;

  public JobChangeObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id='__button4-BDI-content']/parent::span/parent::span/parent::button")
  public WebElement btnAction;

  @FindBy(xpath = "//div[text()='Change Job and Compensation Info']")
  public WebElement lblChangeJobAndCompensationInfo;

  @FindBy(xpath = "/html/body/div[3]/div[3]/div/div/div/ul/li[1]/div/span/span[1]/bdi")
  public WebElement lblTakeAction;

  @FindBy(xpath = "//div[@title='Job Information']/div")
  public WebElement chkJobInformation;

  @FindBy(xpath = "//bdi[text()='When would you like your changes to take effect?']/parent::span/parent::label/parent::div/parent::div/parent::div//input")
  public WebElement txtDateWillChangeEffect;

  @FindBy(xpath = "//span[text()='Position Information']//ancestor::div[4]//descendant::div[5]//descendant::div[9]//descendant::input")
  public WebElement cmbPosition;

  @FindBy(xpath = "//*[@id=\"__box12-inner\"]")
  public WebElement cmbPayGrade;

  @FindBy(xpath = "//bdi[text()='Save']")
  public WebElement btnSave;

  @FindBy(xpath = "//a[text()='Show workflow participants']")
  public WebElement btnShowWorkflowParticipants;

  @FindBy(xpath = "//bdi[text()='Confirm']")
  public WebElement btnConfirm;

  @FindBy(xpath = "//span[text()='Approvers']/parent::div/parent::div/div/div/div/div/div/span/parent::div/parent::div/descendant::bdi")
  public WebElement lstApprovers;

  @FindBy(xpath = "//bdi[text()='Approve']")
  public WebElement btnApprove;

  @FindBy(xpath = "//*[@id=\"utilityLinksMenuId-inner\"]")
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

  @FindBy(xpath = "//span[text()='Position Information']/parent::div/button[2]")
  public WebElement btnHistoryPositionInformation;

  @FindBy(xpath = "//td[@data-help-id='Event Reason']/following-sibling::td//span")
  public WebElement lblEventReason;

}
