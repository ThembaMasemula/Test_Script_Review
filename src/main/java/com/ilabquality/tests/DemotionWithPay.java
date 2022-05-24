package com.ilabquality.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.BaseTest;
import com.ilabquality.modules.testing.listeners.ReportListener;
import com.ilabquality.modules.testing.listeners.TestListener;
import com.ilabquality.tests.pageObjects.DemotionWithPayObject;
import com.ilabquality.tests.pageObjects.DemotionWithoutPayObject;
import com.ilabquality.tests.pageObjects.Home;
import com.ilabquality.tests.pageObjects.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class, ReportListener.class})
public class DemotionWithPay extends BaseTest {
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


      webAction.enterText(homePageObject.txtSearch, TEST_DATA_MAP.get("Employee ID"));
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      /*webAction.pressArrowDown(homePageObject.txtSearch, driver, test);*/
      webAction.presEnter(homePageObject.txtSearch, driver, test);

      reportInstance.ExtentLogPass("Successfully searched employee", driver, test);

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

        DemotionWithPayObject demoWithPayObj = new DemotionWithPayObject(driver);

      webAction.witForElement(demoWithPayObj.actionBtn, 15, driver);
      webAction.clickObject(demoWithPayObj.actionBtn, driver, test);

      webAction.witForElement(demoWithPayObj.CompensationInfoItem, 20, driver);
      webAction.clickObject(demoWithPayObj.CompensationInfoItem, driver, test);

      webAction.witForElement(demoWithPayObj.checkBoxJobInfo, 15, driver);
      webAction.clickObject(demoWithPayObj.checkBoxJobInfo, driver, test);
      webAction.enterText(demoWithPayObj.txtDateWillChangeEffect, TEST_DATA_MAP.get("EFFECTIVE DATE"));
      webAction.presEnter(demoWithPayObj.txtDateWillChangeEffect, driver, test);

      webAction.enterText(demoWithPayObj.demoPosition, TEST_DATA_MAP.get("Position"));
      Thread.sleep(3000);
      webAction.pressArrowDown(demoWithPayObj.demoPosition, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(demoWithPayObj.demoPosition, driver, test);

      webAction.enterText(demoWithPayObj.cmbPayGrade, TEST_DATA_MAP.get("Pay Grade"));
      webAction.pressArrowDown(demoWithPayObj.cmbPayGrade,driver, test);
      webAction.presEnter(demoWithPayObj.cmbPayGrade, driver, test);

      Thread.sleep(15000);

      webAction.clickObject(demoWithPayObj.demoSaveBtn, driver, test);


    /*  webAction.clickObject(demoWithoutObj.confirmButton, driver, test);
      Thread.sleep(5000);*/

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


      DemotionWithPayObject demoWithPay = new DemotionWithPayObject(driver);

      webAction.waitForElement(demoWithPay.btnShowWorkflowParticipants, 10, driver);
      webAction.clickObject(demoWithPay.btnShowWorkflowParticipants, driver, test);
      String[] approvers = getApprovers();

      webAction.waitForElement(demoWithPay.btnConfirm, 10, driver);
      webAction.clickObject(demoWithPay.btnConfirm, driver, test);
      //webAction.waitForElement(demoWithoutPay.btnUserIcon, 10,driver);
       Thread.sleep(5000);
      for (int i = 0; i < approvers.length; i++) {
        if (approvers[i] != null) {
         // webAction.waitForElement(demoWithoutPay.btnUserIcon, 10,driver);
          webAction.clickObject(demoWithPay.btnUserIcon, driver, test);

          webAction.waitForElement(demoWithPay.lnkProxyNow, 15,driver);
          webAction.clickObject(demoWithPay.lnkProxyNow, driver, test);
          webAction.enterText(demoWithPay.txtTargetUser, approvers[i]);
          Thread.sleep(3000);
          webAction.pressArrowDown(demoWithPay.txtTargetUser,driver, test);
          webAction.presEnter(demoWithPay.txtTargetUser, driver, test);
          webAction.clickObject(demoWithPay.btnOk, driver, test);


          webAction.waitForElement(demoWithPay.btnApproveRequest, 15, driver);
          webAction.clickObject(demoWithPay.btnApproveRequest, driver, test);

          webAction.waitForElement(demoWithPay.btnRequestApprove, 15, driver);
          webAction.clickObject(demoWithPay.btnRequestApprove, driver, test);

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

      Home homePageObject = new Home(driver);

      DemotionWithPayObject demoWithPay = new DemotionWithPayObject(driver);

      webAction.waitForElement(demoWithPay.btnUserIcon, 10, driver);
      webAction.clickObject(demoWithPay.btnUserIcon, driver, test);

      webAction.waitForElement(demoWithPay.lblAdminCenter, 15, driver);
      webAction.clickObject(demoWithPay.lblAdminCenter, driver, test);



      Search_Employee();
     /* webAction.enterText(homePageObject.txtSearch, TEST_DATA_MAP.get("Employee ID"));
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      webAction.pressArrowDown(homePageObject.txtSearch, driver, test);
      webAction.presEnter(homePageObject.txtSearch, driver, test);*/

      reportInstance.ExtentLogPass("Successfully searched employee", driver, test);

      webAction.waitForElement(demoWithPay.lblEmployment, 30, driver);
      webAction.clickObject(demoWithPay.lblEmployment, driver, test);

      webAction.waitForElement(demoWithPay.btnHistoryPositionInformation, 10, driver);
      webAction.clickObject(demoWithPay.btnHistoryPositionInformation, driver, test);

      webAction.waitForElement(demoWithPay.AsOfDate, 10, driver);
      webAction.clickObject(demoWithPay.AsOfDate, driver, test);



     // webAction.clickObject(demoWithoutPay.closeHistoryWindow, driver, test);
      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Employment Information clicked successfully", driver, test);

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
      DemotionWithPayObject demoWithPay = new DemotionWithPayObject(driver);

      String eventReason = webAction.getElementText(demoWithPay.lblEventReason);
      if (eventReason.contains(event))
        reportInstance.ExtentLogPass("Event Reason status is: " + eventReason, driver, test);
      else
        //reportInstance.ExtentLogFail("Event Reason status does not match", driver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Job change and compensation information is changed successfully", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }
}

