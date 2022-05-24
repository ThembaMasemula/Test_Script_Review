package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Curricula extends WebActions {
  protected WebDriver driver;

  public Curricula(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"search\"]")
  public WebElement btnSearch;

  @FindBy(xpath = "//*[@id=\"ID\"]")
  public WebElement txtCurriculumID;

  @FindBy(xpath = "(//*[text()[contains(.,'Contents')]])")
  public WebElement lnkContents;

  @FindBy(xpath = "//*[@id=\"ap.curricula.add\"]")
  public WebElement btnPlus;

  @FindBy(xpath = "//*[@id=\"FULL_TEXT_SEARCH\"]")
  public WebElement txtKeyword;

  @FindBy(xpath = "//*[@id=\"addQuals\"]")
  public WebElement chkAdd;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAdd;

  @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[3]/div/div[2]/div/section[1]/div/div[2]/div/button[5]")
  public WebElement tabLibraries;

  @FindBy(xpath = "//*[@id=\"__button0-internalBtn\"]")
  public WebElement btnActions;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[4]/ul[1]/li[2]/div[2]")
  public WebElement lnkCoverPage;

  @FindBy(xpath = "/html/body/div[1]/form/table/tbody/tr[1]/td/table/tbody/tr[4]/td/table/tbody/tr[3]/td[3]/div/input")
  public WebElement chkAddItem;
}
