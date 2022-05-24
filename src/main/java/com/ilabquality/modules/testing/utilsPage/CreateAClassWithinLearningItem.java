package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class CreateAClassWithinLearningItem extends WebActions {
  protected WebDriver driver;

  public CreateAClassWithinLearningItem(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"__button0-internalBtn-BDI-content\"]")
  public WebElement btnActions;

  @FindBy(xpath = "//div[@id='__item2-__component3---entitymanageredit--autoIDHeaderMenuAction-1-unifiedmenu-txt']")
  public WebElement lnkSchedule;

  @FindBy(xpath = "//*[@id=\"scheduledOffering.contact-inner\"]")
  public WebElement txtContactName;

  @FindBy(xpath = "//*[@id=\"scheduledOffering.emailAddress-inner\"]")
  public WebElement txtContactEmail;

  @FindBy(xpath = "//*[@id=\"customColumn_20_select-label\"]")
  public WebElement cmbCompanyNameArrow;

  @FindBy(xpath = "//*[@id=\"__list26\"]")
  public List<WebElement> lstCompanyName;

  @FindBy(xpath = "//*[@id=\"customColumn_20_select-arrow\"]")
  public WebElement cmbCompanyName; //span[@id='customColumn_20_select-label']

  @FindBy(xpath = "//*[@id=\"scheduledOffering.selfEnrollment.Yes-Button\"]")
  public WebElement rdoSelfRegister;

  @FindBy(xpath = "//*[@id=\"scheduledOffering.approvalRequired.Yes-Button\"]")
  public WebElement rdoApprovalRequest;

  @FindBy(xpath = "//*[@id=\"scheduledOffering.withdrawApprovalRequired.Yes-Button\"]")
  public WebElement rdoWithdrawalApprovalRequest;

  @FindBy(xpath = "//*[@id=\"scheduledOffering.footer.save-BDI-content\"]")
  public WebElement btnSave;

  @FindBy(xpath = "//*[@id=\"__component4---entitymanageredit--idObjectPageLayout-anchBar-__component4---entitymanageredit--idObjectPageLayout-4-anchor-BDI-content\"]")
  public WebElement lnkAgenda;
}
