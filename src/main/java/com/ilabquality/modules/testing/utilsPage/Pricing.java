package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class Pricing extends WebActions {
  protected WebDriver driver;

  public Pricing(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//bdi[text()[contains(.,'Pricing')]]")
  public WebElement btnPricing;

  @FindBy(xpath = "//iframe[contains(@src,'/learning/admin/training/editComponentPricing.do')]")
  public WebElement iframePricing;

  @FindBy(xpath = "(//input[@id='defaultPublishedCalculatedPrice'])[1]")
  public WebElement textPricing;

  @FindBy(xpath = "(//input[@id='submitbutton'])[2]")
  public WebElement btnApplChanges;

  @FindBy(xpath = "(//select[@name='defaultPublishedPriceType'])[1]")
  public List<WebElement> selectType;
}
