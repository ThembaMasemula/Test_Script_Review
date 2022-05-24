package com.ilabquality.tests.pageObjects;

import com.google.gson.JsonObject;

import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Home extends WebActions {
  protected WebDriver driver;
  protected BaseContext context = BaseContext.getInstance();
  protected JsonObject testParams = context.testParams;

  public Home(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "/html/body/div[3]/div/div/div/div/section/div/div[2]/section/div[2]/div/div/div[1]/div/div/div[1]/div[3]/div[1]/form/input")
  public WebElement txtSearch;
}
