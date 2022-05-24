package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddNewSurvey extends WebActions {

  protected WebDriver driver;

  public AddNewSurvey(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"addNewLink\"]")
  public WebElement lnkAddNew;

  @FindBy(xpath = "//*[@id=\"surveyID\"]")
  public WebElement txtSurveyID;

  @FindBy(xpath = "//*[@id=\"name\"]")
  public WebElement txtSurveyName;

  @FindBy(xpath = "//*[@id=\"surveyLevel\"]")
  public WebElement cmbSurveyType;

  @FindBy(xpath = "//*[@id=\"domainID\"]")
  public WebElement txtSecurityDomain;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAdd;
}
