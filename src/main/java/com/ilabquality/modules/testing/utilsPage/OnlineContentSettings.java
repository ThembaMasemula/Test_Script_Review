package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OnlineContentSettings extends WebActions {
  protected WebDriver driver;

  public OnlineContentSettings(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"completionTab\"]")
  public WebElement lnkCompletion;

  @FindBy(xpath = "//*[@id=\"recordLearningEvent-switch\"]")
  public WebElement rdbAddToHistory;

  @FindBy(xpath = "//*[@id=\"completionStatus-hiddenInput\"]")
  public WebElement cmbCompletionStatus;

  @FindBy(xpath = "//*[@id=\"completionTableMarkObjectCompleteWhenLaunched-__clone16-CbBg\"]")
  public WebElement chkCompleteContentOnLaunch;

  @FindBy(xpath = "//*[@id=\"launchTab\"]")
  public WebElement lnkLaunch;

  @FindBy(xpath = "//*[@id=\"availableForLaunch-textoff\"]")
  public WebElement rdbContentIsAvailableForLaunch;

  @FindBy(xpath = "(//*[text()[contains(.,'OK')]])[3]")
  public WebElement btnOk;

  @FindBy(xpath = "//*[@id=\"item.onlinecontent.footer.save\"]")
  public WebElement btnSave;

  @FindBy(xpath = "/html/body/div[1]/div[4]/section/div/div/div/div[2]/div/div/div/div[2]/div/div/div[3]/div[1]/div[4]/div[1]/div/table/tbody/tr[2]/td[5]/div/div/div[1]/div")
  public WebElement chkAddToHistoryOnPass;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/div[3]/div[1]/div[4]/div[3]/div/div[2]/div/div/div/button[1]/span/span/bdi")
  public WebElement lnkAddContentObject;

  @FindBy(xpath = "//*[@id=\"contentObject-inner\"]")
  public WebElement txtContentObject;

  @FindBy(xpath = "//*[@id=\"objectTitle-inner\"]")
  public WebElement txtContentTitle;
}
