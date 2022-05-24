package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AssignTaskObservers extends WebActions {
  protected WebDriver driver;

  public AssignTaskObservers(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"__xmlview1--itemTypeahead-inner\"]")
  public WebElement txtItemID;

  @FindBy(xpath = "/html/body/div[2]/div/div[1]/div/div[3]/div/div/div/div/div/div[2]/div/section/div/section/div[1]/button/span/span/bdi")
  public WebElement btnStep2;

  @FindBy(xpath = "/html/body/div[1]/div/div/div/div/ul/li/div/div/div")
  public WebElement lnkItem;

  @FindBy(xpath = "//*[@id=\"__xmlview1--observerTypeahead-inner\"]")
  public WebElement txtObservers;

  @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/ul/li/div/div/div")
  public WebElement lnkObservers;

  @FindBy(xpath = "/html/body/div[2]/div/div[1]/div/div[3]/div/div/div/div/div/div[2]/div/section/div/section/div[2]/div/div[2]/div/ul/li/div[1]/div")
  public WebElement chkObservers;

  @FindBy(xpath = "/html/body/div[2]/div/div[1]/div/div[3]/div/div/div/div/div/div[2]/div/section/div/section/div[3]/div/div[2]/div/ul/li/div[1]/div")
  public WebElement chkUsers;

  @FindBy(xpath = "/html/body/div[2]/div/div[1]/div/div[3]/div/div/div/div/div/div[2]/div/section/div/section/div[2]/button/span/span/bdi")
  public WebElement btnStep3;

  @FindBy(xpath = "//*[@id=\"__xmlview1--userTypeahead-inner\"]")
  public WebElement txtUsers;

  @FindBy(xpath = "/html/body/div[1]/div[3]/div/div/div/ul/li/div/div/div")
  public WebElement lnkUsers;

  @FindBy(xpath = "/html/body/div[2]/div/div[1]/div/div[3]/div/div/div/div/div/div[2]/div/section/div/section/div[3]/button/span/span/bdi")
  public WebElement btnReview;

  @FindBy(xpath = "/html/body/div[2]/div/div[1]/div/div[3]/div/div/div/div/div/div[3]/div/footer/div/button[2]/span[1]/span/bdi")
  public WebElement btnSendObservationRequest;
}
