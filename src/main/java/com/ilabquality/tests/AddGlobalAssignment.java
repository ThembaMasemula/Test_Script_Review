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
public class AddGlobalAssignment extends BaseTest {
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


      reportInstance.ExtentLogPass("Successfully logged into Successfactors", driver, test);

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
  public void Add_Global_Assignment_Details() {
    try {
      WebDriver driver = getWebdriver();

      AddGlobalAssignmentObject addGlobalObject = new AddGlobalAssignmentObject(driver);

      webAction.witForElement(addGlobalObject.actionBtn, 20, driver);
      webAction.clickObject(addGlobalObject.actionBtn, driver, test);

      webAction.waitForElement(addGlobalObject.AddGlobalItem, 20, driver);
      webAction.clickObject(addGlobalObject.AddGlobalItem, driver, test);

      webAction.clickObject(addGlobalObject.EventReason, driver, test);
      Thread.sleep(3000);
      webAction.enterText(addGlobalObject.EventReason, TEST_DATA_MAP.get("Event Code"));
      Thread.sleep(3000);
      webAction.pressArrowDown(addGlobalObject.EventReason, driver, test);
      webAction.presEnter(addGlobalObject.EventReason, driver, test);

      webAction.clickObject(addGlobalObject.AssignmentType, driver, test);
      Thread.sleep(3000);
      webAction.enterText(addGlobalObject.AssignmentType, TEST_DATA_MAP.get("Event"));
      Thread.sleep(3000);
      webAction.pressArrowDown(addGlobalObject.AssignmentType, driver, test);
      webAction.presEnter(addGlobalObject.AssignmentType, driver, test);
      Thread.sleep(3000);
      webAction.clickObject(addGlobalObject.startDate, driver, test);
      webAction.enterText(addGlobalObject.startDate, TEST_DATA_MAP.get("Original Start Date"));
      Thread.sleep(3000);
      webAction.presEnter(addGlobalObject.startDate, driver, test);

      webAction.clickObject(addGlobalObject.plannedEndDate, driver, test);
      webAction.enterText(addGlobalObject.plannedEndDate, TEST_DATA_MAP.get("Probabtionary Period End Date"));
      webAction.presEnter(addGlobalObject.plannedEndDate, driver, test);

      webAction.clickObject(addGlobalObject.company, driver, test);
      Thread.sleep(3000);
      webAction.enterText(addGlobalObject.company, TEST_DATA_MAP.get("Company Code"));
      Thread.sleep(3000);
      webAction.pressArrowDown(addGlobalObject.company, driver, test);
      webAction.presEnter(addGlobalObject.company, driver, test);

      webAction.clickObject(addGlobalObject.btnContinue, driver, test);

      //webAction.waitForElement(addGlobalObject.Position, 15, driver);
      Thread.sleep(10000);
      webAction.enterText(addGlobalObject.Position, TEST_DATA_MAP.get("Position"));
      Thread.sleep(4000);
      webAction.pressArrowDown(addGlobalObject.Position, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(addGlobalObject.Position, driver, test);

      Thread.sleep(5000);

      webAction.scrollPageToElement(addGlobalObject.PayGrade, driver);
      webAction.clickObject(addGlobalObject.PayGrade, driver, test);
      webAction.clearTextField(addGlobalObject.PayGrade);

      Thread.sleep(3000);
      webAction.enterText(addGlobalObject.PayGrade, TEST_DATA_MAP.get("Pay Grade"));
      Thread.sleep(5000);
      webAction.pressArrowDown(addGlobalObject.PayGrade,driver, test);
      webAction.presEnter(addGlobalObject.PayGrade, driver, test);
      Thread.sleep(3000);


      webAction.clickObject(addGlobalObject.btnSubmit, driver, test);

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


      AddGlobalAssignmentObject addGlobalObject = new AddGlobalAssignmentObject(driver);
     // DemotionWithPayObject demoWithPay = new DemotionWithPayObject(driver);

      webAction.waitForElement(addGlobalObject.btnShowWorkflowParticipants, 10, driver);
      webAction.clickObject(addGlobalObject.btnShowWorkflowParticipants, driver, test);
      String[] approvers = getApprovers();

      webAction.waitForElement(addGlobalObject.btnConfirm, 10, driver);
      webAction.clickObject(addGlobalObject.btnConfirm, driver, test);
      webAction.witForElement(addGlobalObject.btnOk, 10,driver);
      webAction.clickObject(addGlobalObject.btnOk, driver, test);

       Thread.sleep(5000);
      for (int i = 0; i < approvers.length; i++) {
        if (approvers[i] != null) {
          webAction.waitForElement(addGlobalObject.btnUserIcon, 10,driver);
          webAction.clickObject(addGlobalObject.btnUserIcon, driver, test);

          webAction.waitForElement(addGlobalObject.lnkProxyNow, 15,driver);
          webAction.clickObject(addGlobalObject.lnkProxyNow, driver, test);
          webAction.enterText(addGlobalObject.txtTargetUser, approvers[i]);
          Thread.sleep(5000);
          webAction.pressArrowDown(addGlobalObject.txtTargetUser,driver, test);
          webAction.presEnter(addGlobalObject.txtTargetUser, driver, test);
          webAction.witForElement(addGlobalObject.btnOk, 15, driver);
          webAction.clickObject(addGlobalObject.btnOk, driver, test);


          webAction.waitForElement(addGlobalObject.btnApproveRequest, 15, driver);
          webAction.clickObject(addGlobalObject.btnApproveRequest, driver, test);

          webAction.waitForElement(addGlobalObject.btnRequestApprove, 15, driver);
          webAction.clickObject(addGlobalObject.btnRequestApprove, driver, test);

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
      AddGlobalAssignmentObject addGlobalAssignmentObj = new AddGlobalAssignmentObject(driver);

      webAction.waitForElement(addGlobalAssignmentObj.btnUserIcon, 10, driver);
      webAction.clickObject(addGlobalAssignmentObj.btnUserIcon, driver, test);

      webAction.waitForElement(addGlobalAssignmentObj.lblAdminCenter, 15, driver);
      webAction.clickObject(addGlobalAssignmentObj.lblAdminCenter, driver, test);
      Thread.sleep(5000);

      webAction.enterText(homePageObject.txtSearch, TEST_DATA_MAP.get("Employee ID"));
      webAction.presEnter(homePageObject.txtSearch, driver, test);
//    webAction.pressArrowDown(homePageObject.txtSearch, driver, test);
      webAction.presEnter(homePageObject.txtSearch, driver, test);

      reportInstance.ExtentLogPass("Successfully searched employee", driver, test);

      webAction.waitForElement(addGlobalAssignmentObj.lblEmployment, 10, driver);
      webAction.clickObject(addGlobalAssignmentObj.lblEmployment, driver, test);

      webAction.waitForElement(addGlobalAssignmentObj.btnHistoryPositionInformation, 10, driver);
      webAction.clickObject(addGlobalAssignmentObj.btnHistoryPositionInformation, driver, test);

      webAction.waitForElement(addGlobalAssignmentObj.AsOfDate, 10, driver);
      webAction.clickObject(addGlobalAssignmentObj.AsOfDate, driver, test);


      webAction.clickObject(addGlobalAssignmentObj.closeHistoryWindow, driver, test);
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
      //DemotionWithPayObject demoWithPay = new DemotionWithPayObject(driver);
      AddGlobalAssignmentObject addGlobalAssignmentObj = new AddGlobalAssignmentObject(driver);


      String eventReason = webAction.getElementText(addGlobalAssignmentObj.lblEventReason);
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

