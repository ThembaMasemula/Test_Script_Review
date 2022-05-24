package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class NavigateToTheLMSPage extends WebActions {
  protected WebDriver driver;

  public NavigateToTheLMSPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "(//div[@aria-label='Learning Administration'])[1]")
  public WebElement lnkLMS;

  @FindBy(xpath = "(//div[@aria-label='Learning'])[1]")
  public WebElement lnkLearning;
}

