package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ClassesObjects extends WebActions {
  protected WebDriver driver;

  public ClassesObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"ScheduleID\"]")
  public WebElement txtClassID;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\"entityManagerID\"]")
  public WebElement lnkClassID;

  @FindBy(xpath = "//*[@id=\"learnerid\"]")
  public WebElement btnUserID;

  @FindBy(xpath = "//*[@id=\"learnerAddButton\"]")
  public WebElement btnAddUser;

  @FindBy(xpath = "//*[@id=\"btnNext\"]")
  public WebElement btnNext;

  @FindBy(xpath = " //*[@id=\"completionTimezone1383932650\"]")
  public WebElement cmbTimezone;

  @FindBy(xpath = "//*[@id=\"grade1383932650\"]")
  public WebElement txtGrade;

  @FindBy(xpath = "//*[@id=\"completionStatus1383932650\"]")
  public WebElement cmbCompletionStatus;

  @FindBy(xpath = "//*[@id=\"applyChangesButton3\"]")
  public WebElement btnApplyChanges;

  @FindBy(xpath = "//*[@id=\"btnNext\"]")
  public WebElement btnNext2;

  @FindBy(xpath = "//*[@id=\"finish\"]")
  public WebElement btnSubmit;
}
