package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MarkAttendance extends WebActions {
  protected WebDriver driver;

  public MarkAttendance(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'comments\']")
  public WebElement txtComments;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnApplyChanges;

  @FindBy(xpath = "//iframe[contains(@src,'/learning/admin/scheduling/scheduleSegmentAttendance.do?scheduleSegmentNo=1&scheduleID=418&schedSegmenNumberUI=1&segmentDesc')]")
  public WebElement iframeMarkAttendance;

  @FindBy(xpath = "(//*[@id='auto_closePopup']/img)[1]")
  public WebElement btnClose;
}
