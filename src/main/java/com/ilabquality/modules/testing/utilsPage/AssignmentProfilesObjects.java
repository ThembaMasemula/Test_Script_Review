package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AssignmentProfilesObjects extends WebActions {
  protected WebDriver driver;

  public AssignmentProfilesObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = " //*[@id=\"ap.header.statusMessage-text\"]")
  public WebElement lblStatus;

  @FindBy(xpath = "//*[@id=\"ID\"]")
  public WebElement txtAssignmentProfilesId;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\"entityManagerID\"]")
  public WebElement lnkSearchResults;

  @FindBy(xpath = "//*[@id=\"__button0\"]")
  public WebElement btnActions;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/ul[1]/li[1]/div[2]")
  public WebElement lnkExecuteChanges;

  @FindBy(xpath = "//*[@id=\"emailNotificationEnabled\"]")
  public WebElement ChkNotifyEmail;

  @FindBy(xpath = "//input[@id='submitbutton']")
  public WebElement btnFinish;

  @FindBy(xpath = "//*[@id=\"submit\"]")
  public WebElement btnBackToAssignmentProfile;
}
