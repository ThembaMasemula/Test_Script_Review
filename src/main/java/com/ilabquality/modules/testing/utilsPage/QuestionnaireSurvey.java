package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class QuestionnaireSurvey extends WebActions {
  protected WebDriver driver;

  public QuestionnaireSurvey(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
  }

  @FindBy(xpath = "//*[@id=\"Questions\"]")
  public WebElement btnQuestions;

  @FindBy(xpath = "//*[@id=\"addQuestion_1\"]/img")
  public WebElement btnAddQuestion;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[5]/tbody/tr[6]/td/div/div[1]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td/textarea")
  public WebElement txtQuestion;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[5]/tbody/tr[6]/td/div/div[1]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/select")
  public WebElement cmbQuestionType;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[5]/tbody/tr[6]/td/div/div[1]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[3]/td[2]/select")
  public WebElement cmbRatingScale;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[5]/tbody/tr[6]/td/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td/textarea")
  public WebElement txtQuestion2;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[5]/tbody/tr[6]/td/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/select")
  public WebElement cmbQuestionType2;

  @FindBy(xpath = "//*[@id=\"choiceLabels\"] ")
  public WebElement txtAnswerChoice;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[6]/tbody/tr/td[2]/ul/li/div/a")
  public WebElement btnPublish;

  @FindBy(xpath = "/html/body/form/table/tbody/tr/td/table[6]/tbody/tr/td/ul/li[1]/div/a")
  public WebElement btnPreview;

  @FindBy(xpath = "(//*[text()[contains(.,'Close')]])[2]")
  public WebElement btnCloseWindow;
}

