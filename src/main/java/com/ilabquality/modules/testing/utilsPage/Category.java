package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Category extends WebActions {
  protected WebDriver driver;

  public Category(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//bdi[text()[contains(.,'Categories')]]")
  public WebElement btnCategory;

  @FindBy(xpath = "(//span[@id='item.subjectAreas.add-img'])[1]")
  public WebElement btnAdd;

  @FindBy(xpath = "//iframe[contains(@src,'/learning/search/initSearch.do?searchType=0&selectorName=SubjectArea&stackID=list&moduleName=Component&nukeStack=1&paramString=componentTypeID')]")
  public WebElement iframeCategory;

  @FindBy(xpath = "(//input[@id='subjectAreaIDs'])[1]")
  public WebElement selectCategory;

  @FindBy(xpath = "(//input[@id='submitbutton'])[2]")
  public WebElement btnAddCategory;
}
