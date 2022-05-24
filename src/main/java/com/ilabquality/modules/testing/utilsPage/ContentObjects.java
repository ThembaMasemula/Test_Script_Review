package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ContentObjects extends WebActions {
  protected WebDriver driver;

  public ContentObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'addNewLink\']")
  public WebElement lnkAddNew;

  @FindBy(xpath = "//*[@id=\'objId\']")
  public WebElement txtContentObjectID;

  @FindBy(xpath = "//*[@id=\'objTitle\']")
  public WebElement txtTitle;

  @FindBy(xpath = "//*[@id=\'domain\']")
  public WebElement txtSecurityDomainID;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnAdd;

  @FindBy(xpath = "//*[@id=\'ID\']")
  public WebElement txtSearchContentID;

  @FindBy(xpath = "//*[@id=\'search\']")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\"resultsTable\"]/tbody/tr/td[2]")
  public WebElement tblresults;

  @FindBy(xpath = "/html/body/div[2]/table/tbody/tr/td[2]/iframe")
  public WebElement ifrAddContentObject;
}

