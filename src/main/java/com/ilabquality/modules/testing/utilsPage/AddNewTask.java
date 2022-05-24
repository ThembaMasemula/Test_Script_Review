package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddNewTask extends WebActions {
  protected WebDriver driver;

  public AddNewTask(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"taskId\"]")
  public WebElement txtTaskID;

  @FindBy(xpath = "//*[@id=\"description\"]")
  public WebElement txtDescription;

  @FindBy(xpath = "//*[@id=\"domain\"]")
  public WebElement txtSecurityDomain;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAdd;
}
