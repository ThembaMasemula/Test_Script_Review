package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CreateAssignmentProfile extends WebActions {
  protected WebDriver driver;

  public CreateAssignmentProfile(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"__label0-bdi\"]")
  public WebElement lblAssignmentProfileID;

  @FindBy(xpath = "//*[@id=\"addNewLink\"]")
  public WebElement lnkAddNew;

  @FindBy(xpath = "//*[@id=\"assignmentProfileId\"]")
  public WebElement txtAssignmentProfileID;

  @FindBy(xpath = "//*[@id=\"description\"]")
  public WebElement txtDescription;

  @FindBy(xpath = "//*[@id=\"domain\"]")
  public WebElement txtSecurityDomain;

  @FindBy(xpath = "//*[@id=\"email\"]")
  public WebElement txtContactEmail;

  @FindBy(xpath = "//*[@id=\"createdFor\"]")
  public WebElement txtCreatedFor;

  @FindBy(xpath = "//*[@id=\"notes\"]")
  public WebElement txtNotes;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAdd;

  @FindBy(xpath = "//*[@id=\"assignmentProfileDTO.active-inner\"]")
  public WebElement btnStatus;

  @FindBy(xpath = "((.//*[normalize-space(text()) and normalize-space(.)='Rules'])[1])")
  public WebElement lnkRulesTab;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div[1]/div[3]/div/div/div[2]/span")
  public WebElement btnAddDomain;

  @FindBy(xpath = "//*[@id=\"FULL_TEXT_SEARCH\"]")
  public WebElement txtSecurityDomainKeyword;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\"sub_0\"]")
  public WebElement chkDomain;

  @FindBy(xpath = "//*[@id=\"AdminSearchContainer\"]/table/tbody/tr/td/table/tbody/tr[6]/td/input")
  public WebElement btnAddSecurityDomain;

  @FindBy(xpath = "(//*[text()[contains(.,'Create Group')]])")
  public WebElement lnkCreateGroup;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div[2]/div[2]/div/div/header/div/div[1]/div/input")
  public WebElement txtGroupName;

  @FindBy(xpath = "//*[@id=\"__block1-Collapsed--attributeDropdown-__block1-Collapsed--ruleList-__block1-Collapsed--groups-0-0-inner\"]")
  public WebElement txtAttribute;

  @FindBy(xpath = " //*[@id=\"__block1-Collapsed--attributeDropdown-__block1-Collapsed--ruleList-__block1-Collapsed--groups-0-0-arrow\"]")
  public WebElement btnAttribute;

  @FindBy(xpath = "//*[@id=\"__block1-Collapsed--operatorDropdown-__block1-Collapsed--ruleList-__block1-Collapsed--groups-0-0-inner\"]")
  public WebElement txtOperator;

  @FindBy(xpath = "//*[@id=\"__block1-Collapsed--operatorDropdown-__block1-Collapsed--ruleList-__block1-Collapsed--groups-0-0-arrow\"]")
  public WebElement btnOperator;

  @FindBy(xpath = " //*[@id=\"ap.managerules.footer.preview-BDI-content\"]")
  public WebElement btnDownloadPreview;

  @FindBy(xpath = "//*[@id=\"ap.managerules.footer.save-BDI-content\"]")
  public WebElement btnSave;

  @FindBy(xpath = "//*[@id=\"__block1-Collapsed--ruleValueMultiInput-__block1-Collapsed--ruleList-__block1-Collapsed--groups-0-0-vhi\"]")
  public WebElement btnUsers;

  @FindBy(xpath = "//*[@id=\"ap.header.statusMessage-text\"]")
  public WebElement lblStatus;
}

