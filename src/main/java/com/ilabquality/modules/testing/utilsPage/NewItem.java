package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NewItem extends WebActions {
  protected WebDriver driver;

  public NewItem(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"item.classification-hiddenInput\"]")
  public WebElement cmbItemClassification;

  @FindBy(xpath = "//*[@id=\"item.locale-hiddenInput\"]")
  public WebElement cmbLocale;

  @FindBy(xpath = "//*[@id=\"item.componentTypeID_select-hiddenInput\"]")
  public WebElement cmbItemType;

  @FindBy(xpath = "//*[@id=\'item.add.online.completionStatus-label\']")
  public WebElement cmbCompletionStatus;

  @FindBy(xpath = "//*[@id=\'item.title-inner\']")
  public WebElement txtTitle;

  @FindBy(xpath = "//*[@id=\'item.totalLength-inner\']")
  public WebElement txtDuration;

  @FindBy(xpath = "//*[@id=\"item.deliveryMethodID_select-hiddenInput\"]")
  public WebElement cmbDeliveryMethod;

  @FindBy(xpath = "//*[@id=\"item.sourceID_select-hiddenInput\"]")
  public WebElement cmbSource;

  @FindBy(xpath = "//*[@id=\'item.description-inner\']")
  public WebElement txtDescription;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[3]/section/div/div/div/div/div[2]/div/div[2]/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/div[2]/div[4]/div[2]/div/div[1]/div/div[2]/div/div[1]/div/div[1]/div/span")
  public WebElement lnkSecurityDomain;

  @FindBy(xpath = "//*[@id=\"DomainID\"]")
  public WebElement txtSecurityDomainID;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[2]/div/header/div[1]/div[3]/div[2]/span/span[1]/bdi")
  public WebElement lblItemId;

  @FindBy(xpath = "//*[@id=\"item.domainID_input-inner\"]")
  public WebElement txtSecurityDomain;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\'item.active-inner\']")
  public WebElement rdbStatus;

  @FindBy(xpath = "//*[@id=\"item.requirementTypeID_select-hiddenInput\"]")
  public WebElement cmbAssignmentType;

  @FindBy(xpath = "//*[@id=\"item.customColumns_10_select-hiddenInput\"]")
  public WebElement cmbLearningArea;

  @FindBy(xpath = "//*[@id=\"item.customColumns_20_select-hiddenInput\"]")
  public WebElement cmbComplianceRegulations;

  @FindBy(xpath = "//*[@id=\"item.customColumns_30_select-hiddenInput\"]")
  public WebElement cmbSetaCategory;

  @FindBy(xpath = "//*[@id=\"item.customColumns_40_select-hiddenInput\"]")
  public WebElement cmbBEECategory;

  @FindBy(xpath = "//*[@id=\"item.customColumns_50_select-hiddenInput\"]")
  public WebElement cmbItemGrouping;

  @FindBy(xpath = "//*[@id=\'item.detail.footer.save-BDI-content\']")
  public WebElement btnSave;
}
