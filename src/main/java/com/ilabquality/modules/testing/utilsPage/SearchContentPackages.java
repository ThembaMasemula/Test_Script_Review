package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchContentPackages extends WebActions {
  protected WebDriver driver;

  public SearchContentPackages(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'ID\']")
  public WebElement txtSearchContentID;

  @FindBy(xpath = "//*[@id=\'search\']")
  public WebElement btnSearch;

  @FindBy(xpath = "/html/body/div[1]/div[1]/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td[3]/a/img")
  public WebElement edtContent;

  @FindBy(xpath = "/html/body/table/tbody/tr/td/table[3]/tbody/tr/td[6]/div")
  public WebElement tbContent;

  @FindBy(xpath = "//*[@id=\"auto_ContentObjects_-921009976\"]/span")
  public WebElement artContent;

  @FindBy(xpath = "/html/body/table/tbody/tr/td/form/table[2]/tbody/tr[6]/td[2]/span")
  public WebElement lblObjectID;
}

