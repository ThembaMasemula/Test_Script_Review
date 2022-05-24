package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class AgendaTemplate extends WebActions {
  protected WebDriver driver;

  public AgendaTemplate(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id='item.segment.add-inner']")
  public WebElement btnAdd;

  @FindBy(xpath = "//*[@id='item.segment.add.day-inner']")
  public WebElement textDay;

  @FindBy(xpath = "//*[@id='item.segment.add.duration-inner']")
  public WebElement textDuration;

  @FindBy(xpath = "//*[@id='__button9-__dialog0-0-BDI-content']")
  public WebElement btnSave;

  @FindBy(xpath = "//*[@id='item.segment.edit-img']")
  public WebElement btnedit;

  @FindBy(xpath = "//bdi[@id='item.segment.footer.save-BDI-content']")
  public WebElement btnSaveEdit;

  @FindBy(xpath = "(//input[contains(@id,'item.segment.description.data')])")
  public List<WebElement> listDescription;
}
