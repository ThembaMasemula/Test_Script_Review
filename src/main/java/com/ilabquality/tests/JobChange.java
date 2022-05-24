package com.ilabquality.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.BaseTest;
import com.ilabquality.modules.testing.listeners.ReportListener;
import com.ilabquality.modules.testing.listeners.TestListener;
import com.ilabquality.tests.pageObjects.Home;
import com.ilabquality.tests.pageObjects.JobChangeObjects;
import com.ilabquality.tests.pageObjects.Login;
import com.ilabquality.tests.pageObjects.NewEmployee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class, ReportListener.class})
public class JobChange extends BaseTest {
  public static BaseContext context = BaseContext.getInstance();
  private final static Gson gson = new Gson();

  @Test(
    groups = {"EC"}
  )
  public void BeforeTest() {
    try {
      JsonObject testParams = context.testParams;
      Type listType = new TypeToken<ArrayList<JsonObject>>() {}.getType();
      List<JsonObject> testArguments = gson.fromJson(context.systemArgs.get(0), listType);

      JsonObject testArgument = testArguments.get(context.testCount);
      int rowNum = testArgument.get("row").getAsInt();

      System.out.printf("\n[EXECUTOR] [INFO] [TEST_NUMBER:SHEET_ROW] %s:%s\n", context.testCount, rowNum);

      initialiseWeb();
      buildScenarioData(context.data, rowNum);

      if (Integer.parseInt(String.valueOf(TEST_DATA_MAP.size())) > 0) {
        String testName = getClass().getSimpleName();

        TEST_NAME = testName;
        TEST_INSTANCE = Integer.parseInt(TEST_DATA_MAP.get("InstanceID"));

        context.scenario = TEST_DATA_MAP.get("Scenario");
        TestName(testName, context.scenario);

        WebDriver driver = configureDriver(TEST_DATA_MAP.get("Browser"));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        String testClientUrl = String.valueOf(testParams.get(testName).getAsJsonObject().get("testClientUrl"));

        if (!testClientUrl.isEmpty()) {
          driver.get("https://salesdemo.successfactors.eu/login?company=SFPART060364");

          Thread.sleep(2000);
        }
      }

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 1,
    groups = {"EC"}
  )
  public void Log_into_Successfactors() {
    try {
      WebDriver driver = getWebdriver();
      driver.manage().window().maximize();

      Login loginPageObject = new Login(driver);

      webAction.enterText(loginPageObject.txtUsername, loginPageObject.username);
      webAction.enterText(loginPageObject.txtPassword, loginPageObject.password);
      webAction.clickAction(loginPageObject.btnLogin, driver, test);
      Thread.sleep(1000);

      reportInstance.ExtentLogPass("Successfully logged into Successfactors", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 2,
    groups = {"EC"})
  public void Search_Employee() {
    try {
      WebDriver driver = getWebdriver();

      Home homePageObject = new Home(driver);

      //Thread.sleep(2000);
      webAction.enterText(homePageObject.txtSearch, TEST_DATA_MAP.get("Employee ID"));
      webAction.presEnter(homePageObject.txtSearch, driver, test);

      webAction.pressArrowDown(homePageObject.txtSearch, driver, test);
      webAction.presEnter(homePageObject.txtSearch, driver, test);

      reportInstance.ExtentLogPass("Successfully searched employee", driver, test);

      Thread.sleep(2000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 3,
    groups = {"EC"}
  )
  public void Change_Job_And_Compensation_Info() {
    try {
      WebDriver driver = getWebdriver();


      JobChangeObjects jobChangeObject = new JobChangeObjects(driver);
      webAction.waitForElement(jobChangeObject.btnAction, 10, driver);
      webAction.clickObject(jobChangeObject.btnAction,driver, test);

    Thread.sleep(3000);
      webAction.clickObject(jobChangeObject.lblChangeJobAndCompensationInfo, driver, test);
      Thread.sleep(2000);

        webAction.clickObject(jobChangeObject.chkJobInformation, driver, test);
        webAction.enterText(jobChangeObject.txtDateWillChangeEffect, TEST_DATA_MAP.get("EFFECTIVE DATE"));
        webAction.presEnter(jobChangeObject.txtDateWillChangeEffect, driver, test);
        Thread.sleep(3000);

      //webAction.clearField(jobChangeObject.cmbPosition);
      webAction.enterText(jobChangeObject.cmbPosition, TEST_DATA_MAP.get("Position"));
      Thread.sleep(5000);
      webAction.pressArrowDown(jobChangeObject.cmbPosition,driver, test);
      webAction.presEnter(jobChangeObject.cmbPosition, driver, test);
      Thread.sleep(3000);
      //webAction.waitForLoad(driver);
      //webAction.clearField(jobChangeObject.cmbPayGrade);
      webAction.scrollPageToElement(jobChangeObject.cmbPayGrade, driver);
      webAction.clickObject(jobChangeObject.cmbPayGrade, driver, test);
      webAction.clearTextField(jobChangeObject.cmbPayGrade);
      Thread.sleep(2000);
      webAction.enterText(jobChangeObject.cmbPayGrade, TEST_DATA_MAP.get("Pay Grade"));
      webAction.pressArrowDown(jobChangeObject.cmbPayGrade,driver, test);
      webAction.presEnter(jobChangeObject.cmbPayGrade, driver, test);
      Thread.sleep(3000);

      webAction.clickObject(jobChangeObject.btnSave, driver, test);
      Thread.sleep(5000);



      reportInstance.ExtentLogPass("Job change and compensation information is captured successfully", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  public String[] getApprovers() throws Exception {
    WebDriver driver = getWebdriver();
    List<WebElement> elements = driver.findElements(By.xpath("//span[text()='Approvers']/parent::div/parent::div/div/div/div/div/div/span/parent::div/parent::div/descendant::bdi"));
    String[] approvers = new String[elements.size()/2];

    int j = 0;
    for (int i = 0; i < elements.size(); i += 2) {
      approvers[j] = elements.get(i).getText();
      j++;

    }
    return approvers;
  }

  @Test(
    priority = 4,
    groups = {"EC"}
  )
  public void Confirm_Request_Page()  {
    try {
      WebDriver driver = getWebdriver();

      JobChangeObjects jobChangeObject = new JobChangeObjects(driver);

      webAction.clickObject(jobChangeObject.btnShowWorkflowParticipants, driver, test);
      String[] approvers = getApprovers();

      webAction.clickObject(jobChangeObject.btnConfirm, driver, test);

      for (int i = 0; i < approvers.length; i++) {
        if (approvers[i] != null) {
          webAction.clickObject(jobChangeObject.btnUserIcon, driver, test);
          webAction.clickObject(jobChangeObject.lnkProxyNow, driver, test);
          webAction.enterText(jobChangeObject.txtTargetUser, approvers[i]);
          webAction.pressArrowDown(jobChangeObject.txtTargetUser,driver, test);
          webAction.presEnter(jobChangeObject.txtTargetUser, driver, test);
          webAction.clickObject(jobChangeObject.btnOk, driver, test);

          Thread.sleep(5000);

          webAction.clickObject(jobChangeObject.btnApproveRequest, driver, test);
          Thread.sleep(5000);
          webAction.clickObject(jobChangeObject.btnRequestApprove, driver, test);
        }
      }

      reportInstance.ExtentLogPass("Job information changed successfully", driver, test);

      Thread.sleep(5000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 5,
    groups = {"EC"}
  )
  public void Employment_Information() {
    try {
      WebDriver driver = getWebdriver();

      JobChangeObjects jobChangeObject = new JobChangeObjects(driver);

      Search_Employee();
      Thread.sleep(3000);

      webAction.clickObject(jobChangeObject.lblEmployment, driver, test);
      Thread.sleep(4000);
      webAction.clickObject(jobChangeObject.btnHistoryPositionInformation, driver, test);


      reportInstance.ExtentLogPass("Employment Information clicked successfully", driver, test);

      Thread.sleep(5000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 6,
    groups = {"EC"}
  )
  public void Verify_Event_Reason() {
    try {
      WebDriver driver = getWebdriver();

      String event = TEST_DATA_MAP.get("Event Reason / Action (EC)");
      JobChangeObjects jobChangeObject = new JobChangeObjects(driver);

      String eventReason = webAction.getElementText(jobChangeObject.lblEventReason);
      if (eventReason.contains(event))
        reportInstance.ExtentLogPass("Event Reason status is: " + eventReason, driver, test);
      else
        reportInstance.ExtentLogFail("Event Reason status does not match", driver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Job change and compensation information is changed successfully", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }
}
