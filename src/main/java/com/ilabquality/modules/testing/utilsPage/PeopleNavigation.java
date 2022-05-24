package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PeopleNavigation extends WebActions {
  protected WebDriver driver;

  public PeopleNavigation(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "(//*[text()[contains(.,'People')]])")
  public WebElement lnkPeople;

  @FindBy(xpath = "(//*[text()[contains(.,'Cohorts')]])")
  public WebElement lnkCohorts;

  @FindBy(xpath = "(//*[text()[contains(.,'Users')]])")
  public WebElement lnkUsers;

  @FindBy(xpath = "(//*[text()[contains(.,'Instructors')]])")
  public WebElement lnkInstructors;

  @FindBy(xpath = "(//*[text()[contains(.,'Organizations')]])")
  public WebElement lnkOrganizations;

  @FindBy(xpath = "(//*[text()[contains(.,'User Groups')]])")
  public WebElement lnkUserGroups;
}
