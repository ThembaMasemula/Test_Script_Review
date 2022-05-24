package com.ilabquality.modules.testing.listeners;

import com.ilabquality.modules.reporting.PdfBoxReport;
import com.ilabquality.modules.testing.BaseTest;

import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseTest implements ITestListener {
  private final PdfBoxReport pdfInstance = PdfBoxReport.getInstance();

  @Override
  public void onTestStart(ITestResult result) {
    System.out.printf("\n[TEST-LISTENER] [ON-START] %s", getTestMethodName(result));
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    System.out.printf("\n[TEST-LISTENER] [ON-SUCCESS] %s\n", getTestMethodName(result));
    int priority = result.getMethod().getPriority();

    if (priority > 0) {
      WebDriver driver = getWebdriver();
      pdfInstance.screenCapture(driver, result, "onTestSuccess");
    }
  }

  @Override
  public void onTestFailure(ITestResult result) {
    System.out.printf("\n[TEST-LISTENER] [ON-FAIL] %s\n", getTestMethodName(result));
    int priority = result.getMethod().getPriority();

    if (priority > 0) {
      WebDriver driver = getWebdriver();
      pdfInstance.screenCapture(driver, result, "onTestFailure");
    }
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    System.out.printf("\n[TEST-LISTENER] [ON-SKIP] %s", getTestMethodName(result));
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    System.out.printf("[TEST-LISTENER] [ON-FAIL-WITHIN-SUCCESS-PERC] %s\n", getTestMethodName(result));
  }

  @Override
  public void onStart(ITestContext context) {
    System.out.printf("\n[TEST-CONTEXT] [ON-START] Test name: %s\n", getTestName(context));
  }

  @Override
  public void onFinish(ITestContext context) {
    System.out.printf("\n[TEST-CONTEXT] [ON-FINISH] Test name: %s", getTestName(context));
  }

  private static String getTestName(ITestContext context) {
    String[] bits = context.getSuite().getName().split("_");
    return bits[bits.length - 1];
  }

  private static String getTestMethodName(ITestResult result) {
    return result.getMethod().getConstructorOrMethod().getName();
  }
}
