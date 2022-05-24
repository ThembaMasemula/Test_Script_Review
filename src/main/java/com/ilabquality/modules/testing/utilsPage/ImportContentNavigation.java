package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ImportContentNavigation extends WebActions {
  protected WebDriver driver;

  public ImportContentNavigation(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "/html/body/table/tbody/tr/td/table[2]/tbody/tr[5]/td/span/label")
  public WebElement chkAddNewContent;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnNext;

  @FindBy(xpath = "//*[@id=\"browseFilesButton\"]")
  public WebElement btnBrowse;

  @FindBy(xpath = "//*[@id=\'__loader0_iframe\']")
  public WebElement ifrImportContent;
}

