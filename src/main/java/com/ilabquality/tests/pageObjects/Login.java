package com.ilabquality.tests.pageObjects;

import com.google.gson.JsonObject;

import com.ilabquality.modules.global.reference.SystemConstant;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Login extends WebActions {
  protected WebDriver driver;
  protected BaseContext context = BaseContext.getInstance();
  protected JsonObject testParams = context.testParams;

  public Login(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  public String username = testParams.get(SystemConstant.TEST_NAME).getAsJsonObject().get("login").getAsString();
  public String password = testParams.get(SystemConstant.TEST_NAME).getAsJsonObject().get("password").getAsString();

  @FindBy(xpath = "/html/body/div[5]/div/div[3]/div[2]/section/div/div[3]/form/div/div[1]/div/div[2]/div/div/input")
  public WebElement txtUsername;

  @FindBy(xpath = "/html/body/div[5]/div/div[3]/div[2]/section/div/div[3]/form/div/div[2]/div/div[2]/div/div/input")
  public WebElement txtPassword;

  @FindBy(xpath = "/html/body/div[5]/div/div[3]/div[2]/section/div/div[3]/form/button")
  public WebElement btnLogin;
}
