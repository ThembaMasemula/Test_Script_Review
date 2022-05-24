package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class WorkplaceAssessment extends WebActions {
  protected WebDriver driver;

  public WorkplaceAssessment(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[3]/section/div/div/div/div/div[3]/div/div[2]/div/section[1]/div/div[2]/div/button[7]")
  public WebElement tabTasks;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[3]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/div[1]/div[3]/div[1]/button")
  public WebElement btnPlus;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[3]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/div[1]/div[3]/div[3]/button/span[1]")
  public WebElement btnSettings;
}
