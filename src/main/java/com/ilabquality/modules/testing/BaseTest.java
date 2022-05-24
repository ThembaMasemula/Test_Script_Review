package com.ilabquality.modules.testing;

import com.aventstack.extentreports.ExtentReports;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import com.ilabquality.modules.global.dtos.TestArgumentsDto;
import com.ilabquality.modules.global.enums.PropertyFolder;
import com.ilabquality.modules.global.reference.SystemConstant;
import com.ilabquality.modules.global.reference.SystemDefault;
import com.ilabquality.modules.global.utils.ClassFinder;
import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.global.utils.PropertyManager;
import com.ilabquality.modules.reporting.PdfBoxReport;
import com.ilabquality.modules.testing.clients.ALMRESTClient;
import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;

import java.io.File;

import java.time.Instant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BaseTest extends BaseData {
  protected WebActions webAction = new WebActions();
  private final PdfBoxReport pdfInstance = PdfBoxReport.getInstance();
  private final ClassFinder classFinder = new ClassFinder();
  private final Gson gson = new Gson();
  private long startTime = 0L;

  @BeforeSuite
  public void BeforeSuite(ITestContext testContext) {
    System.out.println("\n[BASE-TEST] [ON-BEFORE-SUITE]");
    String suiteId = testContext.getSuite().getName();

    if (context.suiteName == null) {
      context.suiteName = GlobalUtils.buildSuiteName(suiteId);
      context.systemPackage = getClass().getPackage().getName();

      List<ITestNGMethod> suiteMethods = testContext.getSuite().getAllMethods();
      context.classNames = suiteMethods.stream()
        .map((method) -> method.getInstance().getClass().getSimpleName())
        .distinct()
        .collect(Collectors.toList());

      List<Class<?>> classes = classFinder.findClasses(context.classNames);
      Class<?>[] testsToRun = new Class[classes.size()];
      context.classes = GlobalUtils.buildTestClassMap(classes, testsToRun);

      context.systemArgs = new ArrayList<>();
      context.systemArgs.add(context.classNames.toString());
      context.systemArgs.add(suiteId);

      context.testParams = GlobalUtils.readTestParamsJson("test");
      context.cloudDeployment = Boolean.parseBoolean(PropertyManager.getProperty(PropertyFolder.CLIENT, "client", "cloudDeployment"));

      BaseContext.setInstance(context);
      SystemDefault.setInstance(defaults);
    }

    startTime = testContext.getStartDate().toInstant().toEpochMilli();

    System.out.println(GlobalUtils.drawCharacterLine("=".charAt(0), defaults.CHAR_LINE_LENGTH));
    System.out.println("[EXECUTOR] [TEST] "
      + "[START]["
      + startTime + "] "
      + String.format("Execution was started on %s tests", gson.fromJson(context.systemArgs.get(0), List.class).size())
    );
    System.out.println(GlobalUtils.drawCharacterLine("=".charAt(0), defaults.CHAR_LINE_LENGTH));

    String eventsString = GlobalUtils.convertListToCommaSeperatedStringList(context.classNames);
    System.out.printf("[EXECUTOR] [PACKAGE] Test package where tests are located: %s\n", context.systemPackage);
    System.out.printf("[EXECUTOR] [TEST] Tests that will be attempted: %s", eventsString);
  }

  @BeforeTest
  public void BeforeTest() {
    System.out.println("\n[BASE-TEST] [ON-BEFORE-TEST]");
  }

  @BeforeClass
  public void BeforeClass(ITestContext testContext) {
    System.out.println("\n[BASE-TEST] [ON-BEFORE-CLASS]");
    String testName = getClass().getSimpleName();
    String eventId = testContext.getCurrentXmlTest().getParameter("eventId");
    String rowNum = testContext.getCurrentXmlTest().getParameter("rowNum");

    JsonArray systemArguments = gson.fromJson(context.systemArgs.get(0), JsonArray.class);
    if (systemArguments.get(context.testCount).getAsString().equals(testName)) {

      TestArgumentsDto testArguments = new TestArgumentsDto(testName, eventId, rowNum);
      systemArguments.set(context.testCount, testArguments.toJsonElement());
      context.systemArgs.set(0, systemArguments.toString());
    }

    try {
      context.currentTest = testName;
      context.data = getData(testName);

      TEST_SCENARIO_MAP = new HashMap<>();
      TEST_RESULTS_MAP = new HashMap<>();
      TEST_REPORT_MAP = new HashMap<>();

      TEST_HEADER_MAP.clear();
      TEST_DATA_MAP.clear();

      pdfInstance.setupPaths();
      pdfInstance.builPdf();

    } catch (Exception exception) {
      System.out.println("Before Class Error setUpSelenium : " + exception.getMessage());
    }
  }

  @BeforeMethod
  public void BeforeMethod(ITestContext context) {
    System.out.println("[BASE-TEST] [ON-BEFORE-METHOD]");
  }

  @AfterMethod
  public void AfterMethod() {
    System.out.println("[BASE-TEST] [ON-AFTER-METHOD]");
  }

  @AfterClass
  public void AfterClass() {
    System.out.println("[BASE-TEST] [ON-AFTER-CLASS]");
    context.testCount ++;
  }

  @AfterTest
  public void AfterTest() {
    System.out.println("[BASE-TEST] [ON-AFTER-TEST]");
  }

  @AfterSuite
  public void AfterSuite() {
    System.out.println("\n[BASE-TEST] [ON-AFTER-SUITE]");
    ExtentReports report = reportInstance.getExtent();

    try {
      if (reportInstance.getExtent() != null) {

        reportInstance.getExtent().flush();
        File myfile = new File(TEST_PATH);

        assert SystemConstant.MICROFOCUS_UPDATE_ALM != null;
        if (SystemConstant.MICROFOCUS_UPDATE_ALM.toUpperCase().contains("TRUE")) {

          if (TEST_HAS_FAILED) {
            ALMRESTClient.updateTestStatus(TEST_INSTANCE, "Failed", myfile);

          } else {
            ALMRESTClient.updateTestStatus(TEST_INSTANCE, "Passed", myfile);
          }
        }
      }

      quitDriver();
    } catch (Exception exception) {
      exception.printStackTrace();
      quitDriver();
    }

    long endTime = Instant.now().toEpochMilli();
    long duration = endTime - startTime;

    System.out.println(GlobalUtils.drawCharacterLine("=".charAt(0), defaults.CHAR_LINE_LENGTH));
    System.out.println("[EXECUTOR] [TEST] "
      + "[END][" + endTime + "] "
      + String.format("Execution was ended on %s tests", gson.fromJson(context.systemArgs.get(0), List.class).size())
    );

    long[] durationUnits = GlobalUtils.splitDurationIntimeUnits(duration);

    System.out.printf("[EXECUTOR] [TEST] [DURATION][%s] Total time taken: %s min, %s sec, %s ms",
      durationUnits[0],
      durationUnits[1],
      durationUnits[2],
      durationUnits[3]
    );
    GlobalUtils.extractPdfLinkPath(context);
    System.out.printf("\n[EXECUTOR] [TEST] [INFO] %s\n", context.suiteName);
    System.out.printf("%s", GlobalUtils.drawCharacterLine("=".charAt(0), defaults.CHAR_LINE_LENGTH));
  }

  @BeforeGroups
  public void BeforeGroups() {
    System.out.println("[BASE-TEST] [ON-BEFORE-GROUPS]");
  }

  @AfterGroups
  public void AfterGroups() {
    System.out.println("[BASE-TEST] [ON-AFTER-GROUPS]");
  }
}
