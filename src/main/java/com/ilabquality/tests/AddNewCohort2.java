package com.ilabquality.tests;

import com.google.gson.JsonObject;
import com.ilabquality.modules.testing.BaseContext;
import com.ilabquality.modules.testing.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class AddNewCohort2 extends BaseTest {
  public static BaseContext context = BaseContext.getInstance();
  public JsonObject testParams = context.testParams;
  public String sSuccessFactorsURL;

  @Test(
    priority = 1,
    groups = {"002 Create a Cohort"},
    expectedExceptions = Exception.class
  )
  public void test_BeforeSetup(Object... testData) throws Exception {
    try {
      initialiseWeb();
      buildScenarioData(context.data, 2);

      if (Integer.parseInt(String.valueOf(TEST_DATA_MAP.size())) > 0) {
        TEST_NAME = this.getClass().getSimpleName();
        TEST_INSTANCE = Integer.parseInt(TEST_DATA_MAP.get("ALMInstanceID"));
        sSuccessFactorsURL = String.valueOf(testParams.get(TEST_NAME).getAsJsonObject().get("testUrl"));
        TestName(TEST_DATA_MAP.get("Scenario"), TEST_DATA_MAP.get("Scenario"));
        WebDriver driver = configureDriver(TEST_DATA_MAP.get("Browser"));

        driver.navigate().to(sSuccessFactorsURL);
      }

    } catch (Exception exception) {
      reportInstance.ExtentLogInfoFail("Failed to Complete " + this.getClass().getSimpleName() + " Test Exception : " + exception.getMessage(), test);
    }
  }

  @Test(
    priority = 2,
    groups = {"002 Create a Cohort"},
    expectedExceptions = Exception.class
  )
  public void test_AddNewCohort() throws Exception {
    webFunctions.LoginSuccessfactors(getWebdriver(), test);
    webFunctions.NavigateToLMS(getWebdriver(), test);
    webFunctions.NavigateToCohorts(getWebdriver(), test);
    webFunctions.AddNewCohort(getWebdriver(), test);
  }
}
