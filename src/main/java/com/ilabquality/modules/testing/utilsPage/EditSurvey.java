package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class EditSurvey extends WebActions {
  protected WebDriver driver;

  public EditSurvey(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"Item Usage\"]")
  public WebElement btnItemUsage;

  @FindBy(xpath = "//*[@id=\"cpntTypeID\"]")
  public WebElement cmbItemType;

  @FindBy(xpath = "//*[@id=\"cpntID\"]")
  public WebElement txtItemID;

  @FindBy(xpath = "//*[@id=\"add_item_button\"]")
  public WebElement btnAdd;

  @FindBy(xpath = "/html/body/table/tbody/tr/td/form[2]/table/tbody/tr[9]/td/ul/li[1]/div/a")
  public WebElement btnRunReport;
}

