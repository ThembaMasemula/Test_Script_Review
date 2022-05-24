package com.ilabquality.modules.testing.listeners;

import com.ilabquality.modules.global.reference.SystemDefault;
import com.ilabquality.modules.global.utils.GlobalUtils;

import com.ilabquality.modules.reporting.PdfBoxReport;
import com.ilabquality.modules.testing.BaseContext;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.*;

public class ReportListener implements IReporter {
  private final SystemDefault defaults = SystemDefault.getInstance();
  private final PdfBoxReport pdfInstance = PdfBoxReport.getInstance();
  private final BaseContext context = BaseContext.getInstance();

  @Override
  public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

    if (context.cloudDeployment) {
      pdfInstance.reportCloudSummary(suites);
    } else {
      pdfInstance.reportXmlSummary(suites);
    }

    for (ISuite suite : suites) {
      try {
        Map<String, ISuiteResult> resultSuiteMap = suite.getResults();
        Set<String> keySet = resultSuiteMap.keySet();

        for (String key : keySet) {
          ITestContext context = resultSuiteMap.get(key).getTestContext();
          IResultMap failedTest = context.getFailedTests();
          Collection<ITestNGMethod> failedMethods = failedTest.getAllMethods();

          System.out.printf("\n[EXECUTOR] [TEST] [%s] ## FAILED Results ##\n", key);

          if (failedMethods.isEmpty()) {
            System.out.printf("[EXECUTOR] [TEST] [%s] Nothing - No tests failed\n", key);
          }

          for (ITestNGMethod method : failedMethods) {
            String methodString = GlobalUtils.buildTestDetailString(method);
            if (methodString != null) {
              System.out.println(methodString);
            }
          }

          IResultMap passedTest = context.getPassedTests();
          Collection<ITestNGMethod> passedMethods = passedTest.getAllMethods();

          System.out.printf("\n[EXECUTOR] [TEST] [%s] ## PASSED Results ##\n", context.getName());

          if (passedMethods.isEmpty()) {
            System.out.printf("[EXECUTOR] [TEST] [%s] Nothing - No tests passed\n", context.getName());
          }

          for (ITestNGMethod method : passedMethods) {
            String methodString = GlobalUtils.buildTestDetailString(method);
            if (methodString != null) {
              System.out.println(methodString);
            }
          }
        }

        System.out.println(GlobalUtils.drawCharacterLine("=".charAt(0), defaults.CHAR_LINE_LENGTH));

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
