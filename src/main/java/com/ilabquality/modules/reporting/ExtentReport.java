package com.ilabquality.modules.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.ilabquality.modules.global.enums.PropertyFolder;
import com.ilabquality.modules.global.reference.SystemConstant;
import com.ilabquality.modules.global.reference.SystemDefault;
import com.ilabquality.modules.testing.BaseContext;

import com.microsoft.playwright.Page;

import io.appium.java_client.android.AndroidDriver;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Base64;

public class ExtentReport {
  private final SystemDefault defaults = SystemDefault.getInstance();
  private final BaseContext context = BaseContext.getInstance();

  public static ExtentReports extent;
  public static ExtentSparkReporter spark;
  public static ExtentTest extentTest;
  public static ExtentTest parentTest;
  public static ExtentTest childTest;
  public static ExtentTest childNodeTest;

  public ExtentReports initializeExtentReports(String sReportName, String sAppend) {

    if (sAppend.equalsIgnoreCase("TRUE")) {
      SystemConstant.TEST_PATH = SystemConstant.ROOT_PATH
        + File.separator
        +defaults.TEST_REPORT_PATH
        + File.separator
        + context.suiteName
        + File.separator
        + sReportName
        + ".html";

    } else {
      SystemConstant.TEST_PATH = SystemConstant.ROOT_PATH
        + File.separator
        + PropertyFolder.TEST
        + "/report/"
        + context.suiteName
        + File.separator
        + sReportName
        + getCurrentTimeStamp()
        + ".html";
    }



    spark = new ExtentSparkReporter(SystemConstant.TEST_PATH);
    spark.config().setReportName("<img src=" + SystemConstant.CLIENT_ROOT_PATH +  "/logo/logo.jpg");
    spark.config().setCSS(".report-name { padding-right: 10px; } .report-name > img { height: 80%;margin-left: 0px;margin-bottom: -10px;width: auto; }");

    extent = new ExtentReports();
    extent.setSystemInfo("HTML Report", "GUI and Mobile Regression");
    extent.setSystemInfo("Automation Engineer Lead", "Trevor Kokela");
    extent.attachReporter(spark);

    return extent;
  }

  public String getCurrentTimeStamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    return sdf.format(timestamp);

  }

  public void setExtent(ExtentReports extentTest) {
    ExtentReport.extent = extentTest;
  }

  public void setExtentTest(ExtentTest extentTest) {
    ExtentReport.extentTest = extentTest;
  }

  public ExtentTest setParent(String parentName) {
    parentTest = extent.createTest(parentName);

    return parentTest;
  }

  public ExtentTest setChild(String childName, ExtentTest parentName) {
    childTest = parentName.createNode(childName);

    return childTest;
  }

  public ExtentTest setChildNode(String childNodeName, ExtentTest parentName) {
    childNodeTest = parentName.createNode(childNodeName);

    return childNodeTest;
  }

  public ExtentReports getExtent() {
    return extent;
  }

  public ExtentTest getExtentTest() {
    return extentTest;
  }

  public void childLogPass(
    String sStepName,
    String sExpected,
    String sActual,
    WebDriver driver,
    ExtentTest logger
  ) throws Exception {

    logger.pass("<b>Step Name : </b><br/>"
      + sStepName + "</b><br/><b>Expected : </b><br/>"
      + sExpected + "<br/><b>Actual Result :</b><br/>"
      + sActual, MediaEntityBuilder.createScreenCaptureFromBase64String(CaptureScreenWin(driver)).build());

    if (!SystemConstant.TEST_HAS_FAILED) {
      SystemConstant.TEST_RESULT = logger.getStatus().toString();
    }
  }

  public void childLogFail(
    String sStepName,
    String sExpected,
    String sActual,
    WebDriver driver,
    ExtentTest logger
  ) throws Exception {

    logger.fail("<b>Step Name : </b><br/>"
      + sStepName + "</b><br/><b>Expected : </b><br/>"
      + sExpected + "<br/><b>Actual Result :</b><br/>"
      + sActual, MediaEntityBuilder.createScreenCaptureFromBase64String(CaptureScreenWin(driver)).build());

    SystemConstant.TEST_RESULT = logger.getStatus().toString();
    SystemConstant.TEST_HAS_FAILED = true;
  }

  public void childLogPlayWrightPass(
    String sStepName,
    String sExpected,
    String sActual,
    Page page,
    ExtentTest logger
  ) throws Exception {

    logger.pass("<b>Step Name : </b><br/>"
      + sStepName + "</b><br/><b>Expected : </b><br/>"
      + sExpected + "<br/><b>Actual Result :</b><br/>"
      + sActual, MediaEntityBuilder.createScreenCaptureFromBase64String(PlayWrightScreen(page)).build());

    if (!SystemConstant.TEST_HAS_FAILED) {
      SystemConstant.TEST_RESULT = logger.getStatus().toString();
    }
  }

  public void childLogPlayWrightFail(
    String sStepName,
    String sExpected,
    String sActual,
    Page page,
    ExtentTest logger
  ) throws Exception {

    logger.fail("<b>Step Name : </b><br/>"
      + sStepName + "</b><br/><b>Expected : </b><br/>"
      + sExpected + "<br/><b>Actual Result :</b><br/>"
      + sActual, MediaEntityBuilder.createScreenCaptureFromBase64String(PlayWrightScreen(page)).build());

    SystemConstant.TEST_RESULTS_MAP.put(SystemConstant.TEST_NAME, logger.getStatus().toString());
    SystemConstant.TEST_HAS_FAILED = true;
  }

  public void ExtentLogPlayWrightPass(String sMessageFail, Page page, ExtentTest test) throws Exception {
    String fileName = PlayWrightScreen(page);
    test.pass(sMessageFail, MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());

    if (!SystemConstant.TEST_HAS_FAILED) {
      SystemConstant.TEST_RESULT = test.getStatus().toString();
    }
  }

  public void ExtentLogPlayWrightFail(String sMessageFail, Page page, ExtentTest test) throws Exception {
    String fileName = PlayWrightScreen(page);
    test.fail(sMessageFail, MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
    SystemConstant.TEST_RESULT = test.getStatus().toString();
    SystemConstant.TEST_HAS_FAILED = true;
  }

  public void ExtentLogPlayrightInfo(String sMessageInfo, ExtentTest test) throws Exception {
    test.info(sMessageInfo);
  }

  public void ExtentLogPass(String sMessagePass, WebDriver driver, ExtentTest test) throws Exception {
    String fileName = CaptureScreenWin(driver);
    test.pass(sMessagePass, MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());

    if (!SystemConstant.TEST_HAS_FAILED) {
      SystemConstant.TEST_RESULT = test.getStatus().toString();
    }
  }

  public void ExtentLogFail(String sMessageFail, WebDriver driver, ExtentTest test) throws Exception {
    String fileName = CaptureScreenWin(driver);
    test.fail(sMessageFail, MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());

    SystemConstant.TEST_RESULT = test.getStatus().toString();
    SystemConstant.TEST_HAS_FAILED = true;

    driver.quit();
    Assert.fail();
  }

  public void ExtentLogInfo(String sMessageInfo, ExtentTest test) throws Exception {
    test.info(sMessageInfo);

    SystemConstant.TEST_RESULT = test.getStatus().toString();
    SystemConstant.TEST_HAS_FAILED = true;
  }

  public void ExtentLogInfoFail(String sMessageInfo, ExtentTest test) {
    try {
      test.fail(sMessageInfo, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenShotTaskBar()).build());

      SystemConstant.TEST_RESULT = test.getStatus().toString();
      SystemConstant.TEST_HAS_FAILED = true;
      Assert.fail();
    } catch (Exception exception) {
      exception.printStackTrace();
    }

  }

  public void ExtentLogMobilePass(String sMessagePass, AndroidDriver driver, ExtentTest test) throws Exception {
    String fileName = CaptureScreenMob(driver);
    test.pass(sMessagePass, MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());

    if (!SystemConstant.TEST_HAS_FAILED) {
      SystemConstant.TEST_RESULT = test.getStatus().toString();
    }
  }

  public void ExtentLogMobileFail(String sMessageFail, AndroidDriver driver, ExtentTest test) throws Exception {
    String fileName = CaptureScreenMob(driver);
    test.fail(sMessageFail, MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());

    SystemConstant.TEST_RESULT = test.getStatus().toString();
    SystemConstant.TEST_HAS_FAILED = true;
  }

  public void ExtentLogMobileInfo(String sMessageInfo, ExtentTest test) throws Exception {
    test.info(sMessageInfo);
  }

  public String CaptureScreenWin(WebDriver driver) throws IOException {

    File scrImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    byte[] fileContent = FileUtils.readFileToByteArray(new File(String.valueOf(scrImage)));

    return Base64.getEncoder().encodeToString(fileContent);
  }

  public String CaptureScreenMob(AndroidDriver driver) throws IOException {
    File scrImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    byte[] fileContent = FileUtils.readFileToByteArray(new File(String.valueOf(scrImage)));

    return Base64.getEncoder().encodeToString(fileContent);
  }

  public String PlayWrightScreen(Page page) throws IOException {
    byte[] buffer = page.screenshot(new Page.ScreenshotOptions());

    return Base64.getEncoder().encodeToString(buffer);
  }

  public static String takeScreenShotTaskBar() throws Exception {
    final ByteArrayOutputStream os = new ByteArrayOutputStream();

    try {
      Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
      BufferedImage screenFullImage = new Robot().createScreenCapture(screenRect);
      ImageIO.write(screenFullImage, "png", os);

      return Base64.getEncoder().encodeToString(os.toByteArray());

    } catch (final IOException ioe) {
      throw new UncheckedIOException(ioe);
    }
  }
}

