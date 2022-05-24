package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ScheduleObjects extends WebActions {
  protected WebDriver driver;

  public ScheduleObjects(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"description\"]")
  public WebElement txtDescription;

  @FindBy(xpath = "//*[@id=\"facility_id\"]")
  public WebElement cmbFacility;

  @FindBy(xpath = "//*[@id=\"locationID\"]")
  public WebElement cmbPrimaryLocation;

  @FindBy(xpath = "//*[@id=\"startDate\"]")
  public WebElement txtStartDate;

  @FindBy(xpath = "//*[@id=\"startTime\"]")
  public WebElement txtStartTime;

  @FindBy(xpath = "//*[@id=\"timeZone\"]")
  public WebElement cmbTimeZone;

  @FindBy(xpath = "//*[@id=\"instructorID\"]")
  public WebElement txtPrimaryInstructor;

  @FindBy(xpath = "//*[@id=\"submitButton\"]")
  public WebElement btnSave;
}
