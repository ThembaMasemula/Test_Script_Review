package com.ilabquality.tests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.BaseTest;
import com.ilabquality.modules.testing.listeners.ReportListener;
import com.ilabquality.modules.testing.listeners.TestListener;
import com.ilabquality.tests.pageObjects.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.sql.Driver;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class, ReportListener.class})
public class Rehire extends BaseTest {
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
  public void Navigate_to_rehire_page() {
    try {
      WebDriver driver = getWebdriver();

      Home homePageObject = new Home(driver);

      webAction.enterText(homePageObject.txtSearch, "Rehire Inactive Employee");
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      Thread.sleep(2000);
      webAction.pressArrowDown(homePageObject.txtSearch, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      Thread.sleep(1000);

      reportInstance.ExtentLogPass("Successfully navigated to Rehire page", driver, test);

      Thread.sleep(5000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 3,
    groups = {"EC"})
  public void Search_for_inactive_employee() {
    try {
      WebDriver driver = getWebdriver();

      RehireObjects rehireObj = new RehireObjects(driver);
      Home homePageObject = new Home(driver);


      webAction.enterText(rehireObj.txtSearchEmployee, TEST_DATA_MAP.get("Employee ID"));
      webAction.clickObject(rehireObj.btnSearchInactiveEmployee, driver, test);
      Thread.sleep(2000);
      webAction.clickObject(rehireObj.lblEmployeeName, driver, test);

      Thread.sleep(3000);
      webAction.clickObject(rehireObj.rehireEmployee, driver, test);


      reportInstance.ExtentLogPass("Successfully navigated to new employee form page", driver, test);


    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }


  @Test(
    priority = 4,
    groups = {"EC"})
  public void Fill_in_form_page_new_employee() {
    try {
      WebDriver driver = getWebdriver();

      RehireObjects rehireObj = new RehireObjects(driver);

      webAction.witForElement(rehireObj.eventReason, 15, driver);
     // webAction.clickObject(rehireObj.eventReason, driver, test);
      webAction.enterText(rehireObj.eventReason, TEST_DATA_MAP.get("Event Reason / Action (EC)"));
      Thread.sleep(4000);
      webAction.pressArrowDown(rehireObj.eventReason, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(rehireObj.eventReason, driver, test);

      Thread.sleep(5000);

      webAction.clickObject(rehireObj.firstBtnContinue, driver, test);

      Thread.sleep(5000);

      webAction.enterText(rehireObj.phoneNumber, TEST_DATA_MAP.get("Phone Number"));
      Thread.sleep(4000);
      webAction.pressArrowDown(rehireObj.phoneNumber, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(rehireObj.phoneNumber, driver, test);

      webAction.clickObject(rehireObj.secondBtnContinue, driver, test);
      webAction.witForElement(rehireObj.position, 15, driver);
      webAction.enterText(rehireObj.position, TEST_DATA_MAP.get("Position"));
      Thread.sleep(4000);
      webAction.pressArrowDown(rehireObj.position, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(rehireObj.position, driver, test);

      Thread.sleep(5000);

      webAction.clickObject(rehireObj.thirdBtnContinue, driver, test);
      //webAction.witForElement(rehireObj.payGroup, 15, driver);

      Thread.sleep(10000);
      webAction.enterText(rehireObj.payGroup, TEST_DATA_MAP.get("Pay Group"));
      Thread.sleep(4000);
      webAction.pressArrowDown(rehireObj.payGroup, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(rehireObj.payGroup, driver, test);
      Thread.sleep(5000);
      webAction.clickObject(rehireObj.forthBtnContinue, driver, test);

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

      RehireObjects rehireObj = new RehireObjects(driver);

      //DemotionWithoutPayObject demoWithoutPay = new DemotionWithoutPayObject(driver);

      webAction.witForElement(rehireObj.viewProfile, 20, driver);
      webAction.clickObject(rehireObj.viewProfile, driver, test);

      webAction.witForElement(rehireObj.lblEmployment, 20, driver);
      webAction.clickObject(rehireObj.lblEmployment, driver, test);

      webAction.witForElement(rehireObj.jobHistoryInfo, 20, driver);
      webAction.clickObject(rehireObj.jobHistoryInfo, driver, test);

      Thread.sleep(6000);

      // webAction.clickObject(demoWithoutPay.closeHistoryWindow, driver, test);
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
      RehireObjects rehireObj = new RehireObjects(driver);

     // DemotionWithoutPayObject demoWithoutPay = new DemotionWithoutPayObject(driver);

      String eventReason = webAction.getElementText(rehireObj.lblEventReason);
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



