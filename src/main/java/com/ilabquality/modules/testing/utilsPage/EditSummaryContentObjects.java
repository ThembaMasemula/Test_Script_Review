package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class EditSummaryContentObjects extends WebActions {

  protected WebDriver driver;

  public EditSummaryContentObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'Launch Method\']")
  public WebElement tbLaunchMethod;

  @FindBy(xpath = "//*[@id=\'lt2\']")
  public WebElement rdbAICC;

  @FindBy(xpath = "//*[@id=\'useAICCWrapper\']")
  public WebElement chkAICCWrapper;

  @FindBy(xpath = "//*[@id=\'fname\']")
  public WebElement txtContentURL;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnApplyChanges;
}

