package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OnlineContentStructure extends WebActions {
  protected WebDriver driver;

  public OnlineContentStructure(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"onlineContentStructureLaunchModuleForm\"]/table/tbody/tr[1]/td/table[3]/tbody/tr[1]/td/table[2]/tbody/tr/td/table/tbody/tr/td[4]/table/tbody/tr/td/span/span/a")
  public WebElement lnkLaunch;

  @FindBy(xpath = "//*[@id=\"backLinkText\"]")
  public WebElement lnkBack;

  @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[2]/div/div[2]/div/span/button[2]")
  public WebElement btnNext1;

  @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[2]/div/div[2]/div/span/button[2]")
  public WebElement btnNext2;

  @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[2]/div/div[2]/div/span/button[2]")
  public WebElement btnNext3;

  @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[2]/div/div[2]/div/span/button[2]")
  public WebElement btnNext4;

  @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[2]/div/div[2]/div/span/button[2]")
  public WebElement btnNext5;

  @FindBy(xpath = "/html/body/div[1]/div/div[1]/div[2]/div/div[2]/div/span/button[2]")
  public WebElement btnNext6;

  @FindBy(xpath = "/html/body/div[1]/div/div[5]/span/div[2]/div/div/div/div[2]/div[2]/button")
  public WebElement btnYes;
}
