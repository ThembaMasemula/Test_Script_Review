package com.ilabquality.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.BaseTest;
import com.ilabquality.modules.testing.listeners.ReportListener;
import com.ilabquality.modules.testing.listeners.TestListener;
import com.ilabquality.modules.testing.utilsWeb.WebActions;
import com.ilabquality.tests.pageObjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class, ReportListener.class})
public class DemotionWithoutPay extends BaseTest {
  public static BaseContext context = BaseContext.getInstance();
  private final static Gson gson = new Gson();

  @Test(
    groups = {"EC"}
  )
  public void BeforeTest() {
    try {
      JsonObject testParams = context.testParams;
      Type listType = new TypeToken<ArrayList<JsonObject>>() {
      }.getType();
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
     // GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
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
      //GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
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


      webAction.enterText(homePageObject.txtSearch, TEST_DATA_MAP.get("Employee ID"));
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      /*webAction.pressArrowDown(homePageObject.txtSearch, driver, test);*/
      webAction.presEnter(homePageObject.txtSearch, driver, test);

      reportInstance.ExtentLogPass("Successfully searched employee", driver, test);

    } catch (Exception exception) {
      //GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
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

      DemotionWithoutPayObject demoWithoutObj = new DemotionWithoutPayObject(driver);

      webAction.waitForElement(demoWithoutObj.actionBtn, 15, driver);
      webAction.clickObject(demoWithoutObj.actionBtn, driver, test);

      webAction.witForElement(demoWithoutObj.CompensationInfoItem, 20, driver);
      webAction.clickObject(demoWithoutObj.CompensationInfoItem, driver, test);

      webAction.waitForElement(demoWithoutObj.checkBoxJobInfo, 15, driver);
      webAction.clickObject(demoWithoutObj.checkBoxJobInfo, driver, test);
      webAction.enterText(demoWithoutObj.txtDateWillChangeEffect, TEST_DATA_MAP.get("EFFECTIVE DATE"));
      webAction.presEnter(demoWithoutObj.txtDateWillChangeEffect, driver, test);

      webAction.enterText(demoWithoutObj.demoPosition, TEST_DATA_MAP.get("Position"));
      Thread.sleep(3000);
      webAction.pressArrowDown(demoWithoutObj.demoPosition, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(demoWithoutObj.demoPosition, driver, test);

      Thread.sleep(15000);

      webAction.clickObject(demoWithoutObj.demoSaveBtn, driver, test);


    /*  webAction.clickObject(demoWithoutObj.confirmButton, driver, test);
      Thread.sleep(5000);*/

      reportInstance.ExtentLogPass("Job change and compensation information is captured successfully", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      //GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
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


      DemotionWithoutPayObject demoWithoutPay = new DemotionWithoutPayObject(driver);

      webAction.waitForElement(demoWithoutPay.btnShowWorkflowParticipants, 10, driver);
      webAction.clickObject(demoWithoutPay.btnShowWorkflowParticipants, driver, test);
      String[] approvers = getApprovers();

      webAction.waitForElement(demoWithoutPay.btnConfirm, 10, driver);
      webAction.clickObject(demoWithoutPay.btnConfirm, driver, test);
      //webAction.waitForElement(demoWithoutPay.btnUserIcon, 10,driver);
       Thread.sleep(5000);
      for (int i = 0; i < approvers.length; i++) {
        if (approvers[i] != null) {
         // webAction.waitForElement(demoWithoutPay.btnUserIcon, 10,driver);
          webAction.clickObject(demoWithoutPay.btnUserIcon, driver, test);

          webAction.waitForElement(demoWithoutPay.lnkProxyNow, 15,driver);
          webAction.clickObject(demoWithoutPay.lnkProxyNow, driver, test);
          webAction.enterText(demoWithoutPay.txtTargetUser, approvers[i]);
          Thread.sleep(3000);
          webAction.pressArrowDown(demoWithoutPay.txtTargetUser,driver, test);
          webAction.presEnter(demoWithoutPay.txtTargetUser, driver, test);
          webAction.clickObject(demoWithoutPay.btnOk, driver, test);


          webAction.waitForElement(demoWithoutPay.btnApproveRequest, 15, driver);
          webAction.clickObject(demoWithoutPay.btnApproveRequest, driver, test);

          webAction.waitForElement(demoWithoutPay.btnRequestApprove, 15, driver);
          webAction.clickObject(demoWithoutPay.btnRequestApprove, driver, test);

        }
      }

      reportInstance.ExtentLogPass("Job information changed successfully", driver, test);

      Thread.sleep(5000);

    } catch (Exception exception) {
      //GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
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

      Home homePageObject = new Home(driver);

      DemotionWithoutPayObject demoWithoutPay = new DemotionWithoutPayObject(driver);

      webAction.waitForElement(demoWithoutPay.btnUserIcon, 10, driver);
      webAction.clickObject(demoWithoutPay.btnUserIcon, driver, test);

      webAction.waitForElement(demoWithoutPay.lblAdminCenter, 15, driver);
      webAction.clickObject(demoWithoutPay.lblAdminCenter, driver, test);



      Search_Employee();
     /* webAction.enterText(homePageObject.txtSearch, TEST_DATA_MAP.get("Employee ID"));
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      webAction.pressArrowDown(homePageObject.txtSearch, driver, test);
      webAction.presEnter(homePageObject.txtSearch, driver, test);*/

      reportInstance.ExtentLogPass("Successfully searched employee", driver, test);

      webAction.waitForElement(demoWithoutPay.lblEmployment, 30, driver);
      webAction.clickObject(demoWithoutPay.lblEmployment, driver, test);

      webAction.waitForElement(demoWithoutPay.btnHistoryPositionInformation, 10, driver);
      webAction.clickObject(demoWithoutPay.btnHistoryPositionInformation, driver, test);

      webAction.waitForElement(demoWithoutPay.AsOfDate, 10, driver);
      webAction.clickObject(demoWithoutPay.AsOfDate, driver, test);



     // webAction.clickObject(demoWithoutPay.closeHistoryWindow, driver, test);
      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Employment Information clicked successfully", driver, test);

    } catch (Exception exception) {
      //GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
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
      DemotionWithoutPayObject demoWithoutPay = new DemotionWithoutPayObject(driver);

      String eventReason = webAction.getElementText(demoWithoutPay.lblEventReason);
      if (eventReason.contains(event))
        reportInstance.ExtentLogPass("Event Reason status is: " + eventReason, driver, test);
      else
        reportInstance.ExtentLogFail("Event Reason status does not match", driver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Job change and compensation information is changed successfully", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
     // GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);

    }
  }
}

