package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyLearning extends WebActions {
  protected WebDriver driver;

  public MyLearning(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "(//a[normalize-space()='Browse all courses'])[1]")
  public WebElement linkBrowsAllCourses;

  @FindBy(xpath = "(//a[normalize-space()='Library'])[1]")
  public WebElement lblLibrary;

  @FindBy(xpath = "(//input[@id='__xmlview1--catalogSearchField-I'])[1]")
  public WebElement textSearch;

  @FindBy(xpath = "(//div[@id='__xmlview1--catalogSearchField-search'])[1]")
  public WebElement iconSearch;

  @FindBy(xpath = "(//a[normalize-space()='Assign to Me'])[1]")
  public WebElement linkAssignToMe;

  @FindBy(xpath = "(//a[normalize-space()='My Learning'])[1]")
  public WebElement linkMyLearning;

  @FindBy(xpath = "(//span[normalize-space()='Find Learning'])[1]")
  public WebElement lblLearning;

  @FindBy(xpath = "(//a[normalize-space()='Start Course'])[1]")
  public WebElement linkStartCourse;

  @FindBy(xpath = "//iframe[contains(@src,'/learning/user/images/blank.html?')]")
  public WebElement iframeCourse;

  @FindBy(xpath = "(//a[@id='backLinkText'])[1]")
  public WebElement btnBack;
}
