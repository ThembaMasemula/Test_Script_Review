package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Course extends WebActions {
  protected WebDriver driver;

  public Course(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "((.//*[normalize-space(text()) and normalize-space(.)='Registrations'])[1])")
  public WebElement tbRegistration;

  @FindBy(xpath = "(//*[contains(text(),'Active Enrollment')])[1]")
  public WebElement wblRegistrationStatus;

  @FindBy(xpath = "((.//*[normalize-space(text()) and normalize-space(.)='Agenda'])[1])")
  public WebElement tbAgenda;

  @FindBy(xpath = "(//span[@title='More'])[2]")
  public WebElement btnMore;

  @FindBy(xpath = "(//*[text()[contains(.,'Mark Attendance')]])[1]")
  public WebElement lnkMarkAttendance;
}
