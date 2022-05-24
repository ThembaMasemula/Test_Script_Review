package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CreateLibrary extends WebActions {
  protected WebDriver driver;

  public CreateLibrary(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "(//a[normalize-space()='Library Search'])[1]")
  public WebElement lblLibrarySearch;

  @FindBy(xpath = "//iframe[@id='getPathBuffer']")
  public WebElement iframeSelect;

  @FindBy(xpath = "//iframe[contains(@src,'learning/search/initSearch.do')]")
  public WebElement iframeLibrary;

  @FindBy(xpath = "(//input[@id='Active_r2'])[1]")
  public WebElement rdbBoth;

  @FindBy(xpath = "(//input[@id='search'])[1]")
  public WebElement btnSearch;

  @FindBy(xpath = "(//a[@id='entityManagerID'])[1]")
  public WebElement lnkSelect;

  @FindBy(xpath = "//*[@id='catalogId']")
  public WebElement txtLibraryID;

  @FindBy(xpath = "//*[@id='description']")
  public WebElement txtLibraryDesc;

  @FindBy(xpath = "//*[@id='domain']")
  public WebElement txtSecurityDomain;

  @FindBy(xpath = "//*[@id='submitbutton']")
  public WebElement btnAdd;

  @FindBy(xpath = "//bdi[@id='__label0-bdi']")
  public WebElement lblLibraryID;

  @FindBy(xpath = "//*[@id='__component3---entitymanageredit--idObjectPageLayout-anchBar-__component3---entitymanageredit--idObjectPageLayout-6-anchor-BDI-content']")
  public WebElement lnkAssignmentProfilesTab; //.//*[normalize-space(text()) and normalize-space(.)='Assignment Profiles'])[2])

  @FindBy(xpath = "//*[@id='catalog.assignment.add-img']")
  public WebElement lnkAddProfile;

  @FindBy(xpath = "(//input[@id='FULL_TEXT_SEARCH'])[1]")
  public WebElement txtDescKeyword;

  @FindBy(xpath = "//*[@id='search']")
  public WebElement btnSearchKeyword;

  @FindBy(xpath = "(//input[@id='remove'])[1]")
  public WebElement chkAdd;

  @FindBy(xpath = "(//*[@id='submitbutton'])[1]")
  public WebElement btnAddProfile;

  @FindBy(xpath = "(//td[@id[contains(.,'__item')]])[2]")
  public WebElement lnkDescription;

  @FindBy(xpath = " //*[@id='__component4---entitymanageredit--idObjectPageLayout-anchBar-__component4---entitymanageredit--idObjectPageLayout-1-anchor-BDI-content']")
  public WebElement lnkRulesTab;

  @FindBy(xpath = " //*[@id='ap.managerules.footer.preview-BDI-content']")
  public WebElement btnDownloadPreview;

  @FindBy(xpath = "(//bdi[text()[contains(.,'Actions')]])[1]") //*[@id="__button5-internalBtn-inner"] //*[@id="__button0"]
  public WebElement btnActions;

  @FindBy(xpath = "//*[text()='Execute Changes']")
  public WebElement lnkExecuteChanges; //*[@id="__title6-mainNavigationArea"]

  @FindBy(xpath = "//*[@id='emailNotificationEnabled']")
  public WebElement ChkNotifyEmail;

  @FindBy(xpath = "//input[@id='submitbutton']")
  public WebElement btnFinish;

  @FindBy(xpath = "//*[@id='submit']")
  public WebElement btnBackToAssignmentProfile;

  @FindBy(xpath = "//*[@id='ap.header.statusMessage-text']")
  public WebElement lblStatus2;

  @FindBy(xpath = "//*[@id='catalog.summary.active-handle']")
  public WebElement btnStatus;

  @FindBy(xpath = "//*[@id='catalog.detail.footer.save-BDI-content']")
  public WebElement btnSave;

  @FindBy(xpath = "//*[@id='catalog.header.status-text']")
  public WebElement lblStatus;

  @FindBy(xpath = "//div[@id='__title5-expand-wrapper']\n")
  public WebElement lblStatus3;

  @FindBy(xpath = "/html/body/div[1]/form/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr[10]/td[3]/div/input")
  public WebElement chkAdd2;

  @FindBy(xpath = "//input[@id='ID']")
  public WebElement txtDescKeywordID;

  @FindBy(xpath = "/html/body/div[2]/form/table/tbody/tr[4]/td/table/tbody/tr/td[1]/button/img")
  public WebElement btnCriteria;

  @FindBy(xpath = "/html/body/form/table[2]/tbody/tr[2]/td/div/table/tbody/tr[2]/td[1]/input")
  public WebElement chkAssignmentProfileID;

  @FindBy(xpath = "/html/body/form/table[2]/tbody/tr[3]/td/div/input")
  public WebElement btnSelect;
}

