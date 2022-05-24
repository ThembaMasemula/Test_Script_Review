package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ManageUserLearningNavigation extends WebActions {
  protected WebDriver driver;

  public ManageUserLearningNavigation(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"__item0-__xmlview2--menuTree-1-content\"]")
  public WebElement btnManageUserLearning;

  @FindBy(xpath = "//*[@id=\"__item0-__xmlview2--menuTree-2-content\"]")
  public WebElement btnAssignmentProfiles;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[1]/div/div/section/div/div[2]/div/div/section/div/ul/li[9]/div")
  public WebElement lnkAssignTaskObservers;

  @FindBy(xpath = " //*[@id=\"__item0-__xmlview2--menuTree-5-content\"]")
  public WebElement btnLearningHistoryMultipleCourse;
}
