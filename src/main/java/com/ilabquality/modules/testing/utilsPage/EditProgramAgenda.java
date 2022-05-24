package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class EditProgramAgenda extends WebActions {
  protected WebDriver driver;

  public EditProgramAgenda(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "(.//*[normalize-space(text()) and normalize-space(.)='Agenda'])[1]/following::button[1]")
  public WebElement btnEdit;

  @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div[3]/div[2]/div/div[2]/div/ul/li/fieldset/legend/div/div/div[1]/div/input")
  public WebElement txtTitle;

  @FindBy(xpath = "(//*[text()[contains(.,'Done')]])[2]")
  public WebElement btnDone;

  @FindBy(xpath = "(//*[text()[contains(.,'Save')]])[2]")
  public WebElement btnSave;

  @FindBy(xpath = "(//div[contains(@class, 'agendaSummaryTitleContainer')])")
  public WebElement wblTitle;

  @FindBy(xpath = "/html/body/div[1]/div/div/div[1]/div[2]/div/div[2]/div/div[1]/div[1]/div[2]/div[3]/div[2]/div/div[3]/div[2]/input")
  public WebElement txtNumberOfActivities;

  @FindBy(xpath = "(//button[contains(., 'Add')])")
  public WebElement btnAdd;

  @FindBy(xpath = "(//input[contains(@value, 'No Title')])")
  public WebElement txtActivityName;

  @FindBy(xpath = "(//ul[contains(@class, 'agendaSimpleSectionList')])")
  public WebElement wblAgendaSection;

  @FindBy(xpath = "(//button[contains(@title, 'Text')])")
  public WebElement btnActivityTitle;

  @FindBy(xpath = "(//textarea[contains(., 'Enter Description')])")
  public WebElement txtActivityDescription;
}

