package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddNewCurriculum extends WebActions {
  protected WebDriver driver;

  public AddNewCurriculum(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"crID\"]")
  public WebElement cmbLocale;

  @FindBy(xpath = "//*[@id=\"qualID\"]")
  public WebElement txtCurriculumID;

  @FindBy(xpath = "//*[@id=\"title\"]")
  public WebElement txtTitle;

  @FindBy(xpath = "//*[@id=\"desc\"]")
  public WebElement txtDescription;

  @FindBy(xpath = "//*[@id=\"domain\"]")
  public WebElement txtSecurityDomain;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[2]/tbody/tr[13]/td[3]/select")
  public WebElement cmbCurriculumType;

  @FindBy(xpath = "//*[@id=\"priority\"]")
  public WebElement cmbPriority;

  @FindBy(xpath = "//*[@id=\"submitbutton\"]")
  public WebElement btnAdd;
}
