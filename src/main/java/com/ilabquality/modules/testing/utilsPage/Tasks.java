package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Tasks extends WebActions {
  protected WebDriver driver;

  public Tasks(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[1]/tbody/tr[1]/td[2]/div/span[2]/a")
  public WebElement lnkAddNew;

  @FindBy(xpath = "/html/body/div[2]/form/table/tbody/tr[4]/td/table/tbody/tr/td[2]/input[1]")
  public WebElement btnSearch;

  @FindBy(xpath = "/html/body/div[2]/form/table/tbody/tr[1]/td/table[2]/tbody/tr/td/fieldset/table/tbody/tr/td[3]/span/input")
  public WebElement txtKeyword;

  @FindBy(xpath = "//td[@class='TableBodyBackground2']//input[@id='add']")
  public WebElement chkAdd;

  @FindBy(xpath = "/html/body/div[1]/form/table/tbody/tr/td/table/tbody/tr[3]/td/input")
  public WebElement btnAdd;
}
