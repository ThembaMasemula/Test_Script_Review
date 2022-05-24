package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Programs extends WebActions {
  protected WebDriver driver;

  public Programs(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'addNewLink\']")
  public WebElement lnkAddNew;

  @FindBy(xpath = "//*[@id=\'ID\']")
  public WebElement txtProgramID;

  @FindBy(xpath = "//*[@id=\'search\']")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\'GotoProgramDashboard\']")
  public WebElement lnkProgram;
}

