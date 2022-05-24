package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Survey extends WebActions {
  protected WebDriver driver;

  public Survey(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"ID\"]")
  public WebElement txtSurveyID;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "/html/body/div[1]/div[1]/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td[3]/a/img")
  public WebElement btnEdit;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnAdd;
}

