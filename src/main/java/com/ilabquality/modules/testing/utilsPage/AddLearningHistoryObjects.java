package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddLearningHistoryObjects extends WebActions {
  protected WebDriver driver;

  public AddLearningHistoryObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"componentTypeIDs\"]")
  public WebElement cmbItemType;

  @FindBy(xpath = "//*[@id=\"componentIDs\"]")
  public WebElement txtItemId;

  @FindBy(xpath = "//*[@id=\"cpntAddButton\"]")
  public WebElement btnAddItem;

  @FindBy(xpath = "//*[@id=\"learnerid\"]")
  public WebElement btnUserID;

  @FindBy(xpath = "//*[@id=\"learnerAddButton\"]")
  public WebElement btnAddUser;

  @FindBy(xpath = "//*[@id=\"btnNext\"]")
  public WebElement btnNext;

  @FindBy(xpath = "/html/body/div[2]/fieldset/table/tbody/tr[5]/td/table/tbody/tr[2]/td[4]/select")
  public WebElement cmbTimezone;

  @FindBy(xpath = "/html/body/div[2]/fieldset/table/tbody/tr[5]/td/table/tbody/tr[2]/td[5]/input")
  public WebElement txtGrade;

  @FindBy(xpath = "/html/body/div[2]/fieldset/table/tbody/tr[5]/td/table/tbody/tr[2]/td[5]/select")
  public WebElement cmbCompletionStatus;

  @FindBy(xpath = "//*[@id=\"applyChangesButton3\"]")
  public WebElement btnApplyChanges;

  @FindBy(xpath = "//*[@id=\"btnNext\"]")
  public WebElement btnNext2;

  @FindBy(xpath = "//*[@id=\"finish\"]")
  public WebElement btnSubmit;
}
