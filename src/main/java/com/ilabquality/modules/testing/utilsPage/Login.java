package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Login extends WebActions {
  protected WebDriver driver;

  public Login(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'__input1-inner\']")
  public WebElement txtusername;

  @FindBy(xpath = "//*[@id=\'__input2-inner\']")
  public WebElement txtpassword;

  @FindBy(xpath = " //*[@id=\'__button2-inner\']")
  public WebElement btnLogin;
}
