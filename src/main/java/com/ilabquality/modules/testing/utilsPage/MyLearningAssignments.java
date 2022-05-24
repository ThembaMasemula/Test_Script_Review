package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MyLearningAssignments extends WebActions {
  protected WebDriver driver;

  public MyLearningAssignments(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "(//*[text()[contains(.,'Autotest')]])")
  public WebElement lnkProgram;

  @FindBy(xpath = "/html/body/div[2]/div[2]/div/div[4]/div[3]/div/div/div/div/div[2]/div/div/div/div[3]/div/div[1]/button")
  public WebElement btnStartCourse;
}
