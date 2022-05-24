package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddContentObjectToLearningItem extends WebActions {
  protected WebDriver driver;

  public AddContentObjectToLearningItem(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'__tile5\']")
  public WebElement btnLearningAdmin;

  @FindBy(xpath = "//*[@id=\'__item0-__xmlview2--menuTree-2-content\']")
  public WebElement lnkLearningActivities;

  @FindBy(xpath = "//*[@id=\'__item0-__xmlview2--menuTree-3-content\']")
  public WebElement btnItems;
}
