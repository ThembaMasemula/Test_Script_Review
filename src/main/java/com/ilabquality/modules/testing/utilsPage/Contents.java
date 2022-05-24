package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Contents extends WebActions {
  protected WebDriver driver;

  public Contents(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"curriculum.contents.manageContent\"]")
  public WebElement lnkManageContent;

  @FindBy(xpath = "//*[@id=\"auto_add_content_button\"]")
  public WebElement btnPlus;

  @FindBy(xpath = "/html/body/table[4]/tbody/tr[1]/td/div/div[1]")
  public WebElement lnkItems;
}
