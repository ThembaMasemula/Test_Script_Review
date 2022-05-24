package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LearningActivitiesNavigation extends WebActions {
  protected WebDriver driver;

  public LearningActivitiesNavigation(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "(//*[text()[contains(.,'Learning Activities')]])")
  public WebElement lnkLearningActivities;

  @FindBy(xpath = "(//*[text()[contains(.,'Items')]])[2]")
  public WebElement lnkItems;

  @FindBy(xpath = "(//*[text()[contains(.,'Classes')]])[2]")
  public WebElement lnkClasses;

  @FindBy(xpath = "(//*[text()[contains(.,'Programs')]])[2]")
  public WebElement lnkPrograms;

  @FindBy(xpath = "(//*[text()[contains(.,'Curricula')]])")
  public WebElement lnkCurricula;

  @FindBy(xpath = "(//*[text()[contains(.,'Curriculum Requirements')]])")
  public WebElement lnkCurriculumRequirements;

  @FindBy(xpath = "(//*[text()[contains(.,'Collections')]])")
  public WebElement lnkCollections;

  @FindBy(xpath = "(//*[text()[contains(.,'External Links')]])")
  public WebElement lnkExternalLinks;

  @FindBy(xpath = "(//*[text()[contains(.,'Libraries')]])")
  public WebElement lnkLibraries;

  @FindBy(xpath = "(//*[text()[contains(.,'Competencies')]])")
  public WebElement lnkCompetencies;

  @FindBy(xpath = "(//*[text()[contains(.,'Class Groups')]])")
  public WebElement lnkClassGroups;
}
