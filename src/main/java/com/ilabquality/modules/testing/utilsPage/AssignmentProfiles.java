package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AssignmentProfiles extends WebActions {
  protected WebDriver driver;

  public AssignmentProfiles(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"__component3---entitymanageredit--idObjectPageLayout-anchBar-__component3---entitymanageredit--idObjectPageLayout-4-anchor-inner\"]")
  public WebElement lnkCurricula;

  @FindBy(xpath = "//*[@id=\"ID\"]")
  public WebElement txtAssignmentProfilesId;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\"entityManagerID\"]")
  public WebElement lnkSearchResults;

  @FindBy(xpath = "(//bdi[text()[contains(.,'Actions')]])[1]") //*[@id="__button5-internalBtn-inner"] //*[@id="__button0"]
  public WebElement btnActions;

  @FindBy(xpath = "//*[text()='Execute Changes']")
  public WebElement lnkExecuteChanges; //*[@id="__title6-mainNavigationArea"]

  @FindBy(xpath = "//*[@id=\"emailNotificationEnabled\"]")
  public WebElement ChkNotifyEmail;

  @FindBy(xpath = "//input[@id='submitbutton']")
  public WebElement btnFinish;

  @FindBy(xpath = "//*[@id=\"jobStatus-64e16ffc-6196-4fd9-a261-cb33977d3d54\"]")
  public WebElement lblStatus;

  @FindBy(xpath = "//*[@id=\"submit\"]")
  public WebElement btnBackToAssignmentProfile;
}
