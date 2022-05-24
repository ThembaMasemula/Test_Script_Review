package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewQuiz extends WebActions {

  protected WebDriver driver;

  public AddNewQuiz(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//iframe[contains(@src,'learning/search/initSearch.do')]")
  public WebElement iframeAssessment;

  @FindBy(xpath = "//iframe[contains(@src,'learning/search/addNew.do')]")
  public WebElement iframeAddAssessment;

  @FindBy(xpath = "(//a[normalize-space()='Add New'])[1]")
  public WebElement lnkAddNew;

  @FindBy(xpath = "(//span[@id='__xmlview1--addAssessmentSelectTypeDialog--addAssessmentSelectTypeDialog-title-inner'])[1]")
  public WebElement lblAddNewAssessment;

  @FindBy(xpath = "(//span[@id='__xmlview1--manageAssessmentFragment--manageAssessmentDialog-title-inner'])[1]")
  public WebElement lblAddNewQuiz;

  @FindBy(xpath = "(//span[@id='__title0-inner'])[1]")
  public WebElement lblOverview;

  @FindBy(xpath = "(//div[@class='sapMCustomTileContent'])[1]")
  public WebElement btnAddNewQuiz;

  @FindBy(xpath = "(//input[@id='__xmlview1--manageAssessmentFragment--assessmentID-inner'])[1]")
  public WebElement textQuizID;

  @FindBy(xpath = "(//input[@id='__xmlview1--manageAssessmentFragment--assessmentName-inner'])[1]")
  public WebElement textQuizName;

  @FindBy(xpath = "(//textarea[@id='__xmlview1--manageAssessmentFragment--description-inner'])[1]")
  public WebElement textDescription;

  @FindBy(xpath = "(//input[@id='__xmlview1--manageAssessmentFragment--domainSearchPicker_input-inner'])[1]")
  public WebElement textSecurityDomain;

  @FindBy(xpath = "(//bdi[@id='__button1-BDI-content'])[1]")
  public WebElement btnCreate;

  @FindBy(xpath = "(//bdi[text()[contains(.,'Menu')]])[1]")
  public WebElement btnMenu;

  @FindBy(xpath = "(//*[contains(text(),'Settings')])[1]")
  public WebElement btnSettings;

  @FindBy(xpath = "(//*[contains(text(),'Questions')])[1]")
  public WebElement btnQuestions;

  @FindBy(xpath = "(//span[@id='__xmlview4--idQuestionEditText-inner'])[1]")
  public WebElement lblQuestions;

  @FindBy(xpath = "(//span[@id='__title2-inner'])[1]")
  public WebElement lblSettings;

  @FindBy(xpath = "(//input[@id='__xmlview3--passingPercentage_Field-inner'])[1]")
  public WebElement textPassingPercentage;

  @FindBy(xpath = "(//bdi[@id='__button8-BDI-content'])[1]")
  public WebElement btnSave;

  @FindBy(xpath = "(//*[text()[contains(.,'Single Answer')]])[1]")
  public WebElement btnAddQuestion;

  @FindBy(xpath = "(//div[@id='__editor0-textarea'])[1]")
  public WebElement textQuestion;

  @FindBy(xpath = "(//div[@id='__editor1-__xmlview7--idAnswerChoices-0-textarea'])[1]")
  public WebElement textAnswerOne;

  @FindBy(xpath = "(//div[@id='__editor1-__xmlview7--idAnswerChoices-1-textarea'])[1]")
  public WebElement textAnswerTwo;

  @FindBy(xpath = "(//div[@id='__editor1-__xmlview7--idAnswerChoices-2-textarea'])[1]")
  public WebElement textAnswerThree;

  @FindBy(xpath = "(//div[@id='__editor1-__xmlview7--idAnswerChoices-3-textarea'])[1]")
  public WebElement textAnswerFour;

  @FindBy(xpath = "(//bdi[@id='__xmlview7--idSaveButton-BDI-content'])[1]")
  public WebElement btnQuestionSave;

  @FindBy(xpath = "(//*[contains(text(),'Quiz Overview')])[1]")
  public WebElement btnQuizOverview;

  @FindBy(xpath = "(//div[@id='__xmlview2--publishSwitch-__xmlview2--idAssessmentDetailsTable-0-switch'])[1]")
  public WebElement btnPublish;

  @FindBy(xpath = "(//span[@id='__xmlview2--assessmentPreviewID-__xmlview2--idAssessmentDetailsTable-0-img'])[1]")
  public WebElement btnPreview;

  @FindBy(xpath = "(//span[@id='__button2-inner'])[1]")
  public WebElement btnStart;

  @FindBy(xpath = " (//bdi[contains(text(),'Yes')] )[1]")
  public WebElement btnConfirm;
}
