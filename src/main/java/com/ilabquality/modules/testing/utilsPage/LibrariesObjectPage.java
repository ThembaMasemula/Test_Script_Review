package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LibrariesObjectPage extends WebActions {
  protected WebDriver driver;

  public LibrariesObjectPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"addNewLink\"]")
  public WebElement lnkAddNew;

  @FindBy(xpath = "//*[@id=\"ID\"]")
  public WebElement txtLibraryID2;

  @FindBy(xpath = "//*[@id=\"Active_r2\"]")
  public WebElement rdoActiveBoth;

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\"__component3---entitymanageredit--idObjectPageLayout-anchBar-__component3---entitymanageredit--idObjectPageLayout-1-anchor\"]")
  public WebElement lnkItem;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[2]/tbody/tr[3]/td/table/tbody/tr[2]/td/fieldset/table/tbody/tr/td[3]/span/input")
  public WebElement txtLibraryID3;

  @FindBy(xpath = "//*[@id=\"entityManagerID\"]")
  public WebElement lnkSearchResults;
}

