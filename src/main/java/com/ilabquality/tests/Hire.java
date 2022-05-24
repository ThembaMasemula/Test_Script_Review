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
import com.ilabquality.tests.pageObjects.Login;
import com.ilabquality.tests.pageObjects.NewEmployee;

import org.openqa.selenium.WebDriver;

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
public class Hire extends BaseTest {
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
  public void Navigate_to_new_Employee_page() {
    try {
      WebDriver driver = getWebdriver();

      Home homePageObject = new Home(driver);

      webAction.enterText(homePageObject.txtSearch, "Add New Employee");
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      Thread.sleep(2000);
      webAction.pressArrowDown(homePageObject.txtSearch, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      Thread.sleep(1000);

      reportInstance.ExtentLogPass("Successfully navigated to new employee page", driver, test);

      Thread.sleep(5000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 3,
    groups = {"EC"}
  )
  public void Fill_in_form_page_one() {
    try {
      WebDriver driver = getWebdriver();

      NewEmployee newEmployeePageObject = new NewEmployee(driver);

      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
      LocalDate startDateStart = LocalDate.of(2022, Month.APRIL, 1);
      LocalDate startDateEnd = LocalDate.of(2022, Month.APRIL, 30);
      String startDate = dateFormatter.format(GlobalUtils.randomLocalDateBetween(startDateStart, startDateEnd));

      LocalDate dateOfBirthStart = LocalDate.of(1975, Month.APRIL, 1);
      LocalDate dateOfBirthEnd = LocalDate.of(1975, Month.APRIL, 30);
      String dateOfBirth = dateFormatter.format(GlobalUtils.randomLocalDateBetween(dateOfBirthStart, dateOfBirthEnd));

      webAction.clearField(newEmployeePageObject.txtDateInput);
      Thread.sleep(800);
      webAction.enterText(newEmployeePageObject.txtDateInput, startDate);
      Thread.sleep(1000);
      webAction.enterText(newEmployeePageObject.txtCompanyInput, TEST_DATA_MAP.get("Company Code"));
      Thread.sleep(2000);
      webAction.pressArrowDown(newEmployeePageObject.txtCompanyInput, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtCompanyInput, driver, test);
      Thread.sleep(3000);
      webAction.enterText(newEmployeePageObject.txtEventReasonInput, TEST_DATA_MAP.get("Event Code"));
      Thread.sleep(2000);
      webAction.pressArrowDown(newEmployeePageObject.txtEventReasonInput, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtEventReasonInput, driver, test);
      Thread.sleep(5000);
      webAction.enterText(newEmployeePageObject.txtFirstNameInput, TEST_DATA_MAP.get("First name"));
      Thread.sleep(1000);
      webAction.presEnter(newEmployeePageObject.txtFirstNameInput, driver, test);
      Thread.sleep(1000);
      webAction.enterText(newEmployeePageObject.txtLastNameInput, TEST_DATA_MAP.get("Last name"));
      Thread.sleep(800);
      webAction.clearField(newEmployeePageObject.txtDateOfBirthInput);
      Thread.sleep(800);
      webAction.enterText(newEmployeePageObject.txtDateOfBirthInput, dateOfBirth);
      Thread.sleep(800);
      webAction.enterText(newEmployeePageObject.txtCountryOfBirthInput, TEST_DATA_MAP.get("Country of Birth"));
      Thread.sleep(2000);
      webAction.pressArrowDown(newEmployeePageObject.txtCountryOfBirthInput, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtCountryOfBirthInput, driver, test);
      Thread.sleep(1000);
      webAction.enterText(newEmployeePageObject.txtEmpUserName, TEST_DATA_MAP.get("First name") + "@" + TEST_DATA_MAP.get("Last name"));
      Thread.sleep(800);
      webAction.clickAction(newEmployeePageObject.btnContinueFirst, driver, test);
      Thread.sleep(800);

      reportInstance.ExtentLogPass("Successfully filled page one new hire form", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 4,
    groups = {"EC"}
  )
  public void Fill_in_form_page_two() {
    try {
      WebDriver driver = getWebdriver();

      NewEmployee newEmployeePageObject = new NewEmployee(driver);

      webAction.scrollPageToElement(newEmployeePageObject.btnAddEmail, driver);
      Thread.sleep(800);
      webAction.clickAction(newEmployeePageObject.btnAddEmail, driver, test);
      Thread.sleep(800);
      webAction.enterText(newEmployeePageObject.txtEmailType, TEST_DATA_MAP.get("Email type"));
      Thread.sleep(2000);
      webAction.pressArrowDown(newEmployeePageObject.txtEmailType, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtEmailType, driver, test);
      Thread.sleep(1000);
      webAction.enterText(newEmployeePageObject.txtEmailAddress, TEST_DATA_MAP.get("Email Address"));
      Thread.sleep(800);
      webAction.enterText(newEmployeePageObject.txtIsEmmilPrimary, TEST_DATA_MAP.get("Is Primary Email"));
      Thread.sleep(2000);
      webAction.pressArrowDown(newEmployeePageObject.txtIsEmmilPrimary, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtIsEmmilPrimary, driver, test);
      Thread.sleep(1000);

      webAction.clickAction(newEmployeePageObject.btnAddPhone, driver, test);
      Thread.sleep(800);
      webAction.enterText(newEmployeePageObject.txtPhoneType, TEST_DATA_MAP.get("Phone Type"));
      Thread.sleep(2000);
      webAction.pressArrowDown(newEmployeePageObject.txtPhoneType, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtPhoneType, driver, test);
      Thread.sleep(1000);
      webAction.enterText(newEmployeePageObject.txtPhoneNumber, TEST_DATA_MAP.get("Phone Number"));
      Thread.sleep(800);
      webAction.enterText(newEmployeePageObject.txtIsPhonePrimary, TEST_DATA_MAP.get("Is Primary Phone"));
      Thread.sleep(2000);
      webAction.pressArrowDown(newEmployeePageObject.txtIsPhonePrimary, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtIsPhonePrimary, driver, test);
      Thread.sleep(1000);
      webAction.clickAction(newEmployeePageObject.btnContinueSecond, driver, test);
      Thread.sleep(800);

      reportInstance.ExtentLogPass("Successfully filled page two new hire form", driver, test);

      Thread.sleep(5000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 5,
    groups = {"EC"}
  )
  public void Fill_in_form_page_three() {
    try {
      WebDriver driver = getWebdriver();

      NewEmployee newEmployeePageObject = new NewEmployee(driver);

      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
      LocalDate firstWorkedStart = LocalDate.of(2022, Month.APRIL, 1);
      LocalDate firstWorkedEnd = LocalDate.of(2022, Month.APRIL, 30);
      String firstWorkedDate = dateFormatter.format(GlobalUtils.randomLocalDateBetween(firstWorkedStart, firstWorkedEnd));

      webAction.enterText(newEmployeePageObject.txtPosition, TEST_DATA_MAP.get("Position"));
      Thread.sleep(5000);
      webAction.pressArrowDown(newEmployeePageObject.txtPosition, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtPosition, driver, test);
      Thread.sleep(2000);
      webAction.enterText(newEmployeePageObject.txtFirstDateWorked, firstWorkedDate);
      Thread.sleep(800);
      webAction.clickAction(newEmployeePageObject.btnContinueThird, driver, test);
      Thread.sleep(800);

      reportInstance.ExtentLogPass("Successfully filled page three new hire form", driver, test);

      Thread.sleep(5000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 6,
    groups = {"EC"}
  )
  public void Fill_in_form_page_four() {
    try {
      WebDriver driver = getWebdriver();

      NewEmployee newEmployeePageObject = new NewEmployee(driver);

      webAction.enterText(newEmployeePageObject.txtPayGroup, TEST_DATA_MAP.get("Pay Group"));
      Thread.sleep(2000);
      webAction.pressArrowDown(newEmployeePageObject.txtPayGroup, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(newEmployeePageObject.txtPayGroup, driver, test);
      Thread.sleep(1000);
      webAction.clickAction(newEmployeePageObject.btnContinueForth, driver, test);
      Thread.sleep(800);

      reportInstance.ExtentLogPass("Successfully filled page three new hire form", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }

  @Test(
    priority = 6,
    groups = {"EC"}
  )
  public void Search_for_newly_added_employee() {
    try {
      WebDriver driver = getWebdriver();

      Home homePageObject = new Home(driver);

      webAction.enterText(homePageObject.txtSearch,
        TEST_DATA_MAP.get("First name") + " "
          + TEST_DATA_MAP.get("Last name"));
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      Thread.sleep(2000);
      webAction.pressArrowDown(homePageObject.txtSearch, driver, test);
      Thread.sleep(2000);
      webAction.presEnter(homePageObject.txtSearch, driver, test);
      Thread.sleep(1000);

      reportInstance.ExtentLogPass("Successfully filled page three new hire form", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, getClass(), test, true);
    }
  }
}
