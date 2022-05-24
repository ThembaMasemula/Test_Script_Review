package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ChecklistSettings extends WebActions {
  protected WebDriver driver;

  public ChecklistSettings(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]")
  public WebElement rdbEnableTaskChecklist;

  @FindBy(xpath = "//div[@id='task.detail.userCanRequestObservationStatus.Yes']//div[@class='sapMRbB sapMRbHoverable']//*[name()='svg']//*[name()='circle' and contains(@class,'sapMRbBInn')]")
  public WebElement rdbUserCanRequestObservation;

  @FindBy(xpath = "//div[@id='item.detail.authorizedObservers.Yes']//div[@class='sapMRbB sapMRbHoverable']//*[name()='svg']//*[name()='circle' and contains(@class,'sapMRbBInn')]")
  public WebElement rdbObserversMustBeAuthorizedInstructors;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]")
  public WebElement rdbEnableNotesField;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[1]/div[1]/div[1]")
  public WebElement rdbEnableTaskDuration;

  @FindBy(xpath = "//input[@id='task.detail.threshold-inner']")
  public WebElement txtThreshold;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[14]/div[1]/span[2]")
  public WebElement cmbCompletionStatus;

  @FindBy(xpath = "/html/body/div[1]/div[5]/div/div/div/div/div/ul/li[1]")
  public WebElement lnkCompetent;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[16]/div[1]/span[2]")
  public WebElement cmbFailureStatus;

  @FindBy(xpath = "/html/body/div[1]/div[6]/div/div/div/div/div/ul/li[3]")
  public WebElement lnkNotYetCompetent;

  @FindBy(xpath = "/html/body/div[1]/div[4]/footer/div/button[1]/span")
  public WebElement btnSave;
}
