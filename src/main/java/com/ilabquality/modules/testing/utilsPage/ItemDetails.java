package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ItemDetails extends WebActions {
  protected WebDriver driver;

  public ItemDetails(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'__button0-internalBtn-BDI-content\']")
  public WebElement lnkActions;

  @FindBy(xpath = "(//*[text()[contains(.,'Revise')]])")
  public WebElement lnkRevise;

  @FindBy(xpath = "((.//*[normalize-space(text()) and normalize-space(.)='Online Content'])[1])")
  public WebElement tbOnlineContent;

  @FindBy(xpath = "//*[@id=\'__button134-BDI-content\']")
  public WebElement btnAddContentObject;

  @FindBy(xpath = "//*[@id=\'contentObject-inner\']")
  public WebElement txtContentObject;

  @FindBy(xpath = "//*[@id=\'objectTitle-inner\']")
  public WebElement txtTitle;

  @FindBy(xpath = "//*[@id=\'addOnlineContentDialogSaveButton-BDI-content\']")
  public WebElement btnOK;

  @FindBy(xpath = "(//*[text()[contains(.,'Finalize Revision')]])")
  public WebElement lnkFinalizeRevision;

  @FindBy(xpath = "(//span[contains(@id, 'idObjectPageLayout-anchBar-arrowScrollRight-img')])")
  public WebElement lnkMoreOptions;

  @FindBy(xpath = "((.//*[normalize-space(text()) and normalize-space(.)='Substitutes'])[1])")
  public WebElement tbSubstitutes;

  @FindBy(xpath = "//*[@id=\"item.substitutes.add-img\"]")
  public WebElement btnAddSubstitutes;

  @FindBy(xpath = "//*[@id=\'RevisionNumber\']")
  public WebElement txtRevisionNumber;

  @FindBy(xpath = "//*[@id=\"Active_r1\"]")
  public WebElement rdbItemStatus;

  @FindBy(xpath = "//*[@id=\"add\"]")
  public WebElement chkAddSubstitute;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAddSubstitute;

  @FindBy(xpath = "//*[@id=\"status\"]")
  public WebElement selSabstituteStatus;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "(//*[text()[contains(.,'Substitutes (1)')]])")
  public WebElement addedSubsituteCount;

  @FindBy(xpath = "(//span[contains(@id, 'Collapsed--onlineContentOverflowButton-__')])[4]")
  public WebElement elpEditContentObject;

  @FindBy(xpath = "(//div[contains(@id, 'onlineContentMenu-0-txt')])")
  public WebElement btnEdit;

  @FindBy(xpath = "//*[@id=\'item.onlinecontent.footer.save-BDI-content\']")
  public WebElement btnSaveContent;

  @FindBy(xpath = "(//*[text()[contains(.,'OK')]])[3]")
  public WebElement btnOKContent;

  @FindBy(xpath = "//*[@id=\"item.title.edit\"]")
  public WebElement btnTranslate;

  @FindBy(xpath = "//*[@id=\"French\"]")
  public WebElement txtFrench;

  @FindBy(xpath = "//*[@id=\"Portuguese\"]")
  public WebElement txtPortuguese;

  @FindBy(xpath = "//*[@id=\"autoSaveButton\"]")
  public WebElement btnSaveAndClose;

  @FindBy(xpath = "//*[@id=\"__component3---entitymanageredit--idObjectPageLayout-anchBar-__component3---entitymanageredit--idObjectPageLayout-2-anchor-BDI-content\"]")
  public WebElement lnkOnlineContent;
}

