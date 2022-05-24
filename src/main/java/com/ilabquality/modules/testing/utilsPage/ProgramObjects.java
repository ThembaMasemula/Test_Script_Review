package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ProgramObjects extends WebActions {
  protected WebDriver driver;

  public ProgramObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "((.//*[normalize-space(text()) and normalize-space(.)='Programs'])[1])")
  public WebElement lnkProgramTab;

  @FindBy(xpath = "//*[@id=\"ap.programs.add-img\"]")
  public WebElement btnAddProgram;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\"ID\"]")
  public WebElement txtProgramID;

  @FindBy(xpath = "//*[@id=\"__component7---entitymanageredit--idObjectPageLayout-anchBar-__component7---entitymanageredit--idObjectPageLayout-1-anchor\"]")
  public WebElement lnkContents;

  @FindBy(xpath = "//*[@id=\"FULL_TEXT_SEARCH\"]")
  public WebElement txtKeyword;

  @FindBy(xpath = "//*[@id=\"addQuals\"]")
  public WebElement chkAdd;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAdd;

  @FindBy(xpath = "//*[@id=\"add\"]")
  public WebElement chkAdd2;
}
