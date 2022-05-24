package com.ilabquality.modules.reporting.utils;

import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.testing.BaseContext;

import lombok.NoArgsConstructor;

import org.testng.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

@NoArgsConstructor
public class ReportingUtils {

  public static float calculateCellContentWidth(float lineSpacing, float padding, float maxWidth) {
    return (float) Math.floor(maxWidth - (2 * lineSpacing) - (2 * padding));
  }

  public static float calculateCellWidth(float lineSpacing, float padding, float contentWidth) {
    return (float) Math.ceil(contentWidth + (2 * lineSpacing) + (2 * padding));
  }

  public static float calculateCellContentHeight(float lineSpacing, float padding, float maxHeight) {
    return (float) Math.floor(maxHeight - (2 * lineSpacing) - (2 * padding));
  }

  public static float calculateCellHeight(float lineSpacing, float padding, float contentHeight) {
    return (float) Math.ceil(contentHeight + (2 * lineSpacing) + (2 * padding));
  }

  public static float calculateEqualCellsContentWidthInRow(float lineSpacing, float padding, float maxWidth, int numberOfCells) {
    float totalLineSpacing = ((numberOfCells + 1) * 2) * lineSpacing;
    float totalPadding = numberOfCells * (2 * padding);
    float distributableWidth = maxWidth - totalLineSpacing - totalPadding;

    return ((distributableWidth / numberOfCells) / maxWidth) * 100;
  }

  public static float[] calculateUnequalCellsContentWidthInRow(float lineSpacing, float padding, float maxWidth, int[] widths) {
    int numberOfCells = widths.length;
    float totalLineSpacing = ((numberOfCells + 1) * 2) * lineSpacing;
    float totalPadding = numberOfCells * (2 * padding);
    float distributableWidth = maxWidth - totalLineSpacing - totalPadding;

    int count = 1;
    float remainder = 100;
    float[] response = new float[widths.length];
    for (int width: widths) {
      if (count == widths.length) {
        response[count - 1] = remainder;

      } else {
        float percentage = (width / distributableWidth) * 100;
        remainder -= percentage;
        response[count - 1] = percentage;
      }

      count++;
    }

    return response;
  }

  public static float calculateRowHeight(float lineSpacing, float padding, float contentHeight) {
    return (float) Math.ceil(ReportingUtils.calculateCellHeight(lineSpacing, padding, contentHeight) + (2 * lineSpacing));
  }

  public static HashMap<String, HashMap<String, String>> createSuiteResultMap(
    Map<String, ISuiteResult> suiteResultMap
  ) {
    Set<String> keySet = suiteResultMap.keySet();

    HashMap<String, HashMap<String, String>> resultMap = new HashMap<>();
    for (String key : keySet) {

      HashMap<String, String> testResultMap = new HashMap<>();
      resultMap.put(key, testResultMap);
      ITestContext testContext = suiteResultMap.get(key).getTestContext();

      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, dd/MM/yy hh:mm:ss");
      ZoneId zone = ZoneId.systemDefault();

      long startMillis = testContext.getStartDate().getTime();
      long endMillis = testContext.getEndDate().getTime();
      long[] durationUnits = GlobalUtils.splitDurationIntimeUnits(endMillis - startMillis);

      Instant startInstant = java.time.Instant.ofEpochMilli(startMillis);
      ZonedDateTime startZonedDate = java.time.ZonedDateTime.ofInstant(startInstant, zone);
      Instant endInstant = java.time.Instant.ofEpochMilli(endMillis);
      ZonedDateTime endZonedDate = java.time.ZonedDateTime.ofInstant(endInstant, zone);

      testResultMap.put("START-TIME", startZonedDate.format(dateFormatter));
      testResultMap.put("END-TIME", endZonedDate.format(dateFormatter));
      testResultMap.put("TOTAL-TIME",
        String.format("%s min, %s sec, %s ms", durationUnits[1], durationUnits[2], durationUnits[3])
      );

      IResultMap failedTest = testContext.getFailedTests();
      Collection<ITestNGMethod> failedMethods = failedTest.getAllMethods();

      List<String> failedMethodsNames = new ArrayList<>();
      for (ITestNGMethod method : failedMethods) {
        String methodName = method.getMethodName();
        if (!Objects.equals(methodName, "BeforeTest")) {
          failedMethodsNames.add(method.getMethodName());
        }
      }

      String failedMethodsString = GlobalUtils.convertListToCommaSeperatedStringList(failedMethodsNames);
      if (failedMethodsNames.isEmpty()) {
        failedMethodsString = "No steps failed";
      }

      IResultMap passedTest = testContext.getPassedTests();
      Collection<ITestNGMethod> passedMethods = passedTest.getAllMethods();

      List<String> passedMethodsNames = new ArrayList<>();
      for (ITestNGMethod method : passedMethods) {
        String methodName = method.getMethodName();
        if (!Objects.equals(methodName, "BeforeTest")) {
          passedMethodsNames.add(method.getMethodName());
        }
      }

      String passedMethodsString = GlobalUtils.convertListToCommaSeperatedStringList(passedMethodsNames);
      if (passedMethodsNames.isEmpty()) {
        passedMethodsString = "No steps passed";
      }

      testResultMap.put("FAILED STEPS", failedMethodsString);
      testResultMap.put("PASSED STEPS", passedMethodsString);
    }

    return resultMap;
  }

  public static HashMap<String, HashMap<String, String>> createTestResultMap(
    Map<String, List<ITestResult>> groupedTestResultMap,
    BaseContext context
  ) {
    List<String> groupedClassNames = new ArrayList<>(groupedTestResultMap.keySet());

    HashMap<String, HashMap<String, String>> suiteResultMap = new HashMap<>();
    for (String className : groupedClassNames) {

      List<ITestResult> testResults = groupedTestResultMap.get(className);
      testResults.sort(Comparator.comparing(ITestResult::getStartMillis));

      long startMillis = 0L;
      long endMillis = 0L;
      int count = 0;
      List<String> passedMethodsNames = new ArrayList<>();
      List<String> failedMethodsNames = new ArrayList<>();

      for (ITestResult result: testResults) {
        if (count == 0) {
          startMillis = result.getStartMillis();
        } else if (count == testResults.size() - 1) {
          endMillis = result.getEndMillis();
        }

        if (result.getStatus() == 1) {
          if (!Objects.equals(result.getName(), "BeforeTest")) {
            passedMethodsNames.add(result.getName());
          }

        } else {
          if (!Objects.equals(result.getName(), "BeforeTest")) {
            failedMethodsNames.add(result.getName());
          }
        }

        count++;
      }

      HashMap<String, String> testResultMap = new HashMap<>();
      String testName = GlobalUtils.extractClassName(className);

      testName = context.testParams.get(testName).getAsJsonObject().get("testName").getAsString();
      suiteResultMap.put(testName, testResultMap);

      DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, dd/MM/yy hh:mm:ss");
      ZoneId zone = ZoneId.systemDefault();

      long[] durationUnits = GlobalUtils.splitDurationIntimeUnits(endMillis - startMillis);

      Instant startInstant = java.time.Instant.ofEpochMilli(startMillis);
      ZonedDateTime startZonedDate = java.time.ZonedDateTime.ofInstant(startInstant, zone);
      Instant endInstant = java.time.Instant.ofEpochMilli(endMillis);
      ZonedDateTime endZonedDate = java.time.ZonedDateTime.ofInstant(endInstant, zone);

      testResultMap.put("START-TIME", startZonedDate.format(dateFormatter));
      testResultMap.put("END-TIME", endZonedDate.format(dateFormatter));
      testResultMap.put("TOTAL-TIME",
        String.format("%s min, %s sec, %s ms", durationUnits[1], durationUnits[2], durationUnits[3])
      );

      String failedMethodsString = GlobalUtils.convertListToCommaSeperatedStringList(failedMethodsNames);
      if (failedMethodsNames.isEmpty()) {
        failedMethodsString = "No steps failed";
      }

      String passedMethodsString = GlobalUtils.convertListToCommaSeperatedStringList(passedMethodsNames);
      if (passedMethodsNames.isEmpty()) {
        passedMethodsString = "No steps passed";
      }

      testResultMap.put("FAILED STEPS", failedMethodsString);
      testResultMap.put("PASSED STEPS", passedMethodsString);
    }

    return suiteResultMap;
  }
}
