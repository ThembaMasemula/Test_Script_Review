package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddNewProgram extends WebActions {
  protected WebDriver driver;

  public AddNewProgram(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'9:_txtFld\']")
  public WebElement txtLocale;

  @FindBy(xpath = "//*[@id=\'10:_txtFld\']")
  public WebElement txtProgramID;

  @FindBy(xpath = "//*[@id=\'11:_txtFld\']")
  public WebElement txtTitle;

  @FindBy(xpath = "/html")
  public WebElement txtDescription;

  @FindBy(xpath = "//*[@id=\'19:_txtFld\']")
  public WebElement txtAssignemntType;

  @FindBy(xpath = "//*[@id=\'25:\']")
  public WebElement rdbOpenEndedType;

  @FindBy(xpath = "//*[@id=\'23:\']")
  public WebElement rdbScheduled;

  @FindBy(xpath = "//*[@id=\'24:\']")
  public WebElement rdbDurationBased;

  @FindBy(xpath = "//*[@id=\"21:\"]")
  public WebElement selCompletionStatus;

  @FindBy(xpath = "//*[@id=\'16:_txtFld\']")
  public WebElement txtSecurityDomainID;

  @FindBy(xpath = "//*[@id=\'dlgButton_39:\']")
  public WebElement btnCreateProgram;

  @FindBy(xpath = "//*[@id=\'6:\']")
  public WebElement wblCreatedID;
}

