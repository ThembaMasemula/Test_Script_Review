package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Cohorts extends WebActions {
  protected WebDriver driver;

  public Cohorts(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'addNewLink\']")
  public WebElement btnAddNew;

  @FindBy(xpath = "//*[@id=\'learningClassID\']")
  public WebElement txtCohortID;

  @FindBy(xpath = "//iframe[contains(@src,'/learning/search/initSearch.do?searchType=0&selectorName=Class&stackID=search&adminShellMenuId=classes')]")
  public WebElement iframeCohorts;

  @FindBy(xpath = "//*[@id=\'description\']")
  public WebElement txtDescription;

  @FindBy(xpath = "//*[@id=\'domainID\']")
  public WebElement txtSecurityDomain;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnAdd;

  @FindBy(xpath = "//iframe[contains(@src,'/learning/search/addNew.do?selectorName=Class&stackID=search&showCriteria=Y&stackProperty=Cohorts')]")
  public WebElement iframeCohortAdd;

  @FindBy(xpath = "(//input[@value='Apply Changes'])[1]")
  public WebElement btnApplyChanges;

  @FindBy(xpath = "//iframe[contains(@src,'/learning/admin/scheduling/editLearningClassSummary.do?learningClassID')]")
  public WebElement iframeCohortSummary;

  @FindBy(xpath = "//*[@id=\'ID\']")
  public WebElement txtCohortID2;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "/html/body/div[1]/div[1]/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td[3]/a")
  public WebElement imgEdit;

  @FindBy(xpath = "//*[@id=\'Users\']")
  public WebElement tabUsers;

  @FindBy(xpath = "//*[@id=\'studentID\']")
  public WebElement txtUserID;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAddUserID;

  @FindBy(xpath = "//tbody/tr[3]/td[1]/input[1]")
  public WebElement btnApplyChanges2;

  @FindBy(xpath = "//*[@id=\'Classes\']")
  public WebElement tabClasses;

  @FindBy(xpath = "//*[@id=\'scheduleID\']")
  public WebElement txtClassID;

  @FindBy(xpath = "//*[@id=\'autoEnroll\']")
  public WebElement chkAutoRegister;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnAddClasses;

  @FindBy(xpath = "/html/body/table/tbody/tr/td/form[2]/table/tbody/tr/td/table/tbody/tr[5]/td/input[1]")
  public WebElement btnApplyChanges3;
}
