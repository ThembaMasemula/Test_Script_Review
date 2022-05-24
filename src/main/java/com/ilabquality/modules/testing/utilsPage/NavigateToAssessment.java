package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigateToAssessment extends WebActions {
  protected WebDriver driver;

  public NavigateToAssessment(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//iframe[contains(@src,'learning/search/initSearch.do')]")
  public WebElement iframeAssessment;

  @FindBy(xpath = "//div[@id='__item0-__xmlview2--menuTree-3-content']")
  public WebElement lnkContent;

  @FindBy(xpath = "//div[@id='__item0-__xmlview2--menuTree-8-content']")
  public WebElement lnkAssessments;

  @FindBy(xpath = "//span[@class='PageTitle']")
  public WebElement lblAssessment;
}
