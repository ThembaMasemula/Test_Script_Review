package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CoverPage extends WebActions {
  protected WebDriver driver;

  public CoverPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "(//bdi[text()[contains(.,'Action')]])[1]")
  public WebElement btnAction;

  @FindBy(xpath = " (//*[contains(text(),'Cover Page')] )[1]")
  public WebElement btnCoverPage;

  @FindBy(xpath = "(//button[@title='Save'])[1]")
  public WebElement btnSave;

  @FindBy(xpath = "(//button[@title='Activate'])[1]")
  public WebElement btnAvtivate;

  @FindBy(xpath = "(//button[@title='Save and Close'])[1]")
  public WebElement btnSaveAndClose;
}
