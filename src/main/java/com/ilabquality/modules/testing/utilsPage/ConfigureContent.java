package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ConfigureContent extends WebActions {
  protected WebDriver driver;

  public ConfigureContent(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'contentDeploymentLocationID\']")
  public WebElement selServerLocation;

  @FindBy(xpath = "//*[@id=\'cpdomain\']")
  public WebElement txtSecurityDomain;

  @FindBy(xpath = "//*[@id=\'contentPackageID\']")
  public WebElement txtContentPackage;

  @FindBy(xpath = "//*[@id=\'addNewItem\']")
  public WebElement chkItems;

  @FindBy(xpath = "//*[@id=\'componentTypeID\']")
  public WebElement selItemType;

  @FindBy(xpath = "//*[@id=\'autoGenCompID\']")
  public WebElement chkAutoGenerateItemID;

  @FindBy(xpath = "//*[@id=\'componentID\']")
  public WebElement txtItemID;

  @FindBy(xpath = "//*[@id=\'componentTitle\']")
  public WebElement txtItemTitle;

  @FindBy(xpath = "//*[@id=\'requirementType\']")
  public WebElement selAssignmentType;

  @FindBy(xpath = "//*[@id=\'domain1\']")
  public WebElement txtSecurityDomainID;

  @FindBy(xpath = "(//*[text()[contains(.,'Add to History on Completion of All Content')]])")
  public WebElement chkAddToHistory;

  @FindBy(xpath = "//*[@id=\'onlineCompletionStatus\']")
  public WebElement selCompletionStatus;

  @FindBy(xpath = "//*[@id=\'finishButton1\']")
  public WebElement btnImportFiles;
}

