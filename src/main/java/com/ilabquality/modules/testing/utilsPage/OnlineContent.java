package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OnlineContent extends WebActions {
  protected WebDriver driver;

  public OnlineContent(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/div[3]/div[1]/div[4]/div[3]/div/div[2]/div/div/div/button[1]/span/span/bdi")
  public WebElement lnkAddContentObject;

  @FindBy(xpath = "//*[@id=\"contentObject-inner\"]")
  public WebElement txtContentObject;

  @FindBy(xpath = "//*[@id=\"objectTitle-inner\"]")
  public WebElement txtContentTitle;

  @FindBy(xpath = "(//*[text()[contains(.,'OK')]])[3]")
  public WebElement btnOk;

  @FindBy(xpath = "//*[@id=\"__block1-Collapsed--settingsButton\"]")
  public WebElement btnSettings;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div/div/section[2]/section/div[2]/div/div[1]/div/div/div/div/div/div[3]/div[1]/div[4]/div[3]/div/div[2]/div/div/div/button[2]/span[1]")
  public WebElement cmbAddAssessment;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/ul[1]/li[3]/div[2]")
  public WebElement lnkAddAssessment;

  @FindBy(xpath = "//*[@id=\"assessment-inner\"]")
  public WebElement txtAssessmentID;

  @FindBy(xpath = "//*[@id=\"assessmentTitle-inner\"]")
  public WebElement txtAssessmentTitle;

  @FindBy(xpath = "//*[@id=\"addOnlineContentDialogSaveButton\"]")
  public WebElement btnAssessmentOk;

  @FindBy(xpath = "//*[@id=\"item.onlinecontent.footer.save\"]")
  public WebElement btnSave;

  @FindBy(xpath = "//span[@id='assessment-vhi']")
  public WebElement btnSearchOptions;
}
