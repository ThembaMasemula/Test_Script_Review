package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ContentNavigation extends WebActions {
  protected WebDriver driver;

  public ContentNavigation(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "(//*[text()[contains(.,'Import Content')]])")
  public WebElement lnkImportContent;

  @FindBy(xpath = "(//*[text()[contains(.,'Content Packages')]])")
  public WebElement lnkContetPackages;

  @FindBy(xpath = "(//*[text()[contains(.,'Content Objects')]])")
  public WebElement lnkContentObjects;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[1]/div/div/section/div/div[2]/div/div/section/div/ul/li[12]/div")
  public WebElement lnkTasks;

  @FindBy(xpath = "(//*[text()[contains(.,'Surveys')]])")
  public WebElement lnkSurveysObjects;

  @FindBy(xpath = "(//*[text()[contains(.,'Content')]])[2]")
  public WebElement lnkContent;
}

