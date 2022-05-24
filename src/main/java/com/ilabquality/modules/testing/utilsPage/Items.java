package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Items extends WebActions {
  protected WebDriver driver;

  public Items(WebDriver driver) {
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

  @FindBy(xpath = "//*[@id=\'componentTitle\']")
  public WebElement txtItemTitle;

  @FindBy(xpath = "//*[@id=\'requirementType\']")
  public WebElement selAssignmentType;

  @FindBy(xpath = "//*[@id=\'domain1\']")
  public WebElement txtSecurityDomainID;

  @FindBy(xpath = "(//*[text()[contains(.,'Add to History on Completion of All Content')]])")
  public WebElement chkAddToHistory;

  @FindBy(xpath = "/html/body/div[1]/form/table/tbody/tr[1]/td/table[2]/tbody/tr[2]/td[4]/div/input")
  public WebElement chkAdd2;

  @FindBy(xpath = "//*[@id=\'onlineCompletionStatus\']")
  public WebElement selCompletionStatus;

  @FindBy(xpath = "//*[@id=\'finishButton1\']")
  public WebElement btnImportFiles;

  @FindBy(xpath = "//*[@id=\'search\']")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\'entityManagerID\']")
  public WebElement lnkItem2;

  @FindBy(xpath = "//*[@id=\'ComponentID\']")
  public WebElement txtItemID2;

  @FindBy(xpath = "//*[@id=\'addNewLink\']")
  public WebElement lnkAddNew;

  @FindBy(xpath = "//*[@id='ComponentID']")
  public WebElement txtItemID;

  @FindBy(xpath = "/html/body/div[1]/div[1]/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td/span/a")
  public WebElement lnkItem;

  @FindBy(xpath = "((.//*[normalize-space(text()) and normalize-space(.)='Items'])[2])")
  public WebElement tabItem;

  @FindBy(xpath = "//*[@id=\"catalog.items.add\"]")
  public WebElement btnPlus;

  @FindBy(xpath = "//span[@id='curriculum.catalogs.add-img']")
  public WebElement btnPlus2;

  @FindBy(xpath = "/html/body/div[1]/form/table/tbody/tr[1]/td/table[2]/tbody/tr[2]/td[3]/div/input")
  public WebElement chkAddItems;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/div[1]/div[2]/div[2]/button/span[1]")
  public WebElement btnEdit2;

  @FindBy(xpath = "(//input[@aria-roledescription='Date Input'])[2]")
  public WebElement txtDate2;

  @FindBy(xpath = "(//input[@aria-roledescription='Date Input'])[2]")
  public WebElement txtDate3;

  @FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/section[1]/div[1]/div[2]/div[1]/div[1]/section[1]/div[1]/div[2]/section[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/section[2]/section[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/span[2]")
  public WebElement btnHighlightAs2;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]")
  public WebElement lnkNew;

  @FindBy(xpath = "//*[@id=\"FULL_TEXT_SEARCH\"]")
  public WebElement txtKeyword;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAdd;

  @FindBy(xpath = "(//*[@id='addSchedule'])[1]")
  public WebElement chkAddClasses;

  @FindBy(xpath = "(//*[@id='addSku'])[1]")
  public WebElement chkAdd;

  @FindBy(xpath = "/html/body/div[2]/table/thead/tr/th[2]/div/a[2]/img")
  public WebElement btnClosePopup;

  @FindBy(xpath = "//*[@id=\"catalog.items.edit-img\"]")
  public WebElement btnEdit;

  @FindBy(xpath = " //*[@id=\"__block1-Collapsed--idEntityManagerGridTable-3-__catalog.items.catalogItemFlagID.data-__clone353\"]")
  public WebElement txtHighlightAs;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/table/tbody/tr[2]/td[2]/div/div[1]/div[3]/div/div")
  public WebElement txtHighlightAs2;

  @FindBy(xpath = "//*[@id=\"__block1-Collapsed--idEntityManagerGridTable-4-__catalog.items.catalogItemFlagUntilDate.data-__clone574-inner\"]")
  public WebElement txtDate;

  @FindBy(xpath = "//*[@id=\"catalog.items.footer.save-BDI-content\"]")
  public WebElement btnSave;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/section/div/div[2]/div[1]/button/span")
  public WebElement btnSave2;

  @FindBy(xpath = "(//span[@class='sapMSltArrow'])[1]")
  public WebElement btnHighlightAs;

  @FindBy(xpath = "/html/body/div[1]/div[4]/div/div/div/div/div/ul/li[3]")
  public WebElement lnkNew1;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div[2]/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/div[3]/div[1]/div[4]/div[1]/div/table/tbody/tr[1]/td[5]/div/div/div/div/div/div/span")
  public WebElement lnkDate;

  @FindBy(xpath = "/html/body/div[1]/div[5]/div/div/div/div[2]/div[1]/div[3]/div[4]/span")
  public WebElement btnDate;

  @FindBy(xpath = "/html/body/div[1]/form/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr[3]/td[3]/div/input")
  public WebElement chkAddItem;

  @FindBy(xpath = "/html/body/div[1]/form/table/tbody/tr/td/table/tbody/tr[5]/td/table/tbody/tr[3]/td[4]/div/input")
  public WebElement chkAdd3;

  @FindBy(xpath = "(//span[@id[contains(.,'__block1-Collapsed--idEntityManagerGridTable')]] )[4]")
  public WebElement btnHighlightAs3;

  @FindBy(xpath = "/html/body/form/table[2]/tbody/tr[2]/td/div/table/tbody/tr[4]/td[1]/input")
  public WebElement chkItemID;

  @FindBy(xpath = "/html/body/div[2]/form/table/tbody/tr[4]/td/table/tbody/tr/td[1]/button/img")
  public WebElement btnCriteria;

  @FindBy(xpath = "/html/body/form/table[2]/tbody/tr[3]/td/div/input")
  public WebElement btnSelect;

  @FindBy(xpath = "//*[@id=\"ComponentID\"]")
  public WebElement txtDescItemId;
}

