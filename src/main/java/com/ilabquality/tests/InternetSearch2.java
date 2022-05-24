package com.ilabquality.tests;

import com.google.gson.JsonObject;

import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.testing.listeners.ReportListener;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.BaseTest;

import com.ilabquality.modules.testing.listeners.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class, ReportListener.class})
public class InternetSearch2 extends BaseTest {
  public static BaseContext context = BaseContext.getInstance();

  @Test(
    groups = {"002 SearchInternetForPage"}
  )
  public void BeforeTest() {
    JsonObject testParams = context.testParams;

    try {
      initialiseWeb();
      buildScenarioData(context.data, 2);

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
          driver.navigate().to(testClientUrl);
        }
      }

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, this.getClass(), test);
    }
  }

  @Test(
    priority = 1,
    groups = {"002 SearchInternetForPage"}
  )
  public void Navigate_to_Search_Page() throws Exception {
    try {
      WebDriver driver = getWebdriver();

      driver.manage().window().maximize();
      driver.get(TEST_DATA_MAP.get("SearchPage"));
      reportInstance.ExtentLogPass("Succesfully navigated to search page", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, this.getClass(), test);
    }
  }

  @Test(
    priority = 2,
    groups = {"002 SearchInternetForPage"})
  public void Search_for_Page() {
    String searchText = TEST_DATA_MAP.get("SearchString");

    try {
      WebDriver driver = getWebdriver();

      WebElement search_box = driver.findElement(By.xpath("//input[@name='q']"));
      search_box.sendKeys(searchText);
      search_box.submit();
      reportInstance.ExtentLogPass("Succesfully searched for page", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, this.getClass(), test);
    }
  }

  @Test(
    priority = 3,
    groups = {"002 SearchInternetForPage"}
  )
  public void Select_search_Result() {
    try {
      WebDriver driver = getWebdriver();

      WebElement resultElement = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div/div/div/div[1]/a/h3"));
      resultElement.click();
      reportInstance.ExtentLogPass("Succesfully selected search result", driver, test);

      Thread.sleep(3000);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, this.getClass(), test);
    }
  }

  @Test(
    priority = 4,
    groups = {"002 SearchInternetForPage"}
  )
  public void Check_Title() {
    String expectedTitle = TEST_DATA_MAP.get("ExpectedTitle");

    try {
      WebDriver driver = getWebdriver();

      String windowTitle = driver.getTitle();
      Assert.assertEquals(windowTitle, expectedTitle);
      reportInstance.ExtentLogPass("Search result title matched expected title", driver, test);

    } catch (Exception exception) {
      GlobalUtils.buildExtentReportFailure(reportInstance, exception, this.getClass(), test);
    }
  }
}
