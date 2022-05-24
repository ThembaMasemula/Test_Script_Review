package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ReviseItem extends WebActions {
  protected WebDriver driver;

  public ReviseItem(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'revDate\']")
  public WebElement txtRevisionDate;

  @FindBy(xpath = "//*[@id=\'revTime\']")
  public WebElement txtRevisionTime;

  @FindBy(xpath = "//*[@id=\'timezone\']")
  public WebElement selTimeZone;

  @FindBy(xpath = "//*[@id=\'RevNo\']")
  public WebElement txtRevisionNumber;

  @FindBy(xpath = "//*[@id=\'yes\']")
  public WebElement chkCopySub;

  @FindBy(xpath = "//*[@id=\'schedule\']")
  public WebElement btnScheduleJobs;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnFinish;

  @FindBy(xpath = "/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/span")
  public WebElement lblStatus;

  @FindBy(xpath = "//*[@id=\'nextButton\']")
  public WebElement btnNext;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnNextSubmit;

  @FindBy(xpath = "//*[@id=\'createNew_N\']")
  public WebElement rdbNotProdReady;

  @FindBy(xpath = " //*[@id=\'createNew_Y\']")
  public WebElement rdbProdReady;

  @FindBy(xpath = "//*[@id=\'emailNotificationEnabled\']")
  public WebElement chkEmailNotification;

  @FindBy(xpath = "/html/body/form[1]/table/tbody/tr/td/table[2]/tbody/tr[15]/td[2]/input[2]")
  public WebElement btnFinish2;

  @FindBy(xpath = "/html/body/form/table/tbody/tr[2]/td/input")
  public WebElement btnReturnToNewRevision;
}

