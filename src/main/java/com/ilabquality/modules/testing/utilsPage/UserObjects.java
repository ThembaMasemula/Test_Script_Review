package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class UserObjects extends WebActions {
  protected WebDriver driver;

  public UserObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"ID\"]")
  public WebElement txtuserID;

  @FindBy(xpath = "//*[@id=\"search0\"]")
  public WebElement btnSearchUser;

  @FindBy(xpath = "//*[@id=\"idfield_0\"]")
  public WebElement chkAdd;

  @FindBy(xpath = "//*[@id=\"addButton\"]")
  public WebElement btnAddUser;
}
