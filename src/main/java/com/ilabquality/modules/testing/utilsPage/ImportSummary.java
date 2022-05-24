package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ImportSummary extends WebActions {
  protected WebDriver driver;

  public ImportSummary(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\'selectedFileList\']/table/tr/td[3]/div/div")
  public WebElement brProgressiveBar;

  @FindBy(xpath = "//*[@id=\'resultsTable\']/tbody/tr[2]/td[1]")
  public WebElement lblStatus;

  @FindBy(xpath = "//*[@id=\'schedButton\']")
  public WebElement btnScheduleJobs;

  @FindBy(xpath = "//*[@id=\'resultsTable\']/tbody/tr[2]/td[5]/ul")
  public WebElement lblReason;

  @FindBy(xpath = "//*[@id=\'emailAddress\']")
  public WebElement txtContactEmailAddress;

  @FindBy(xpath = "//*[@id=\'submitbutton\']")
  public WebElement btnFinish;

  @FindBy(xpath = "/html/body/form/table/tbody/tr[1]/td/table/tbody/tr/td/table[2]/tbody/tr[7]/td[2]/span")
  public WebElement lblStatus2;
}

