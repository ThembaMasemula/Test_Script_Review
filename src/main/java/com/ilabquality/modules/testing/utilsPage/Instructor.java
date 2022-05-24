package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Instructor extends WebActions {
  protected WebDriver driver;

  public Instructor(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//bdi[text()[contains(.,'Instructor')]]")
  public WebElement btnInstructor;

  @FindBy(xpath = "//iframe[contains(@src,'/learning/admin/training/editComponentAuthorizedInstructors.do')]")
  public WebElement iframeInstructor;

  @FindBy(xpath = "(//a[normalize-space()='add one or more from list'])[1]")
  public WebElement lnkSearch;

  @FindBy(xpath = "(//input[@id='instructorIDs'])[1]")
  public WebElement checkBoxInstuctor;

  @FindBy(xpath = "(//input[@id='search'])[1]")
  public WebElement btnSearch;

  @FindBy(xpath = "(//input[@value='Add'])[1]")
  public WebElement btnAdd;
}
