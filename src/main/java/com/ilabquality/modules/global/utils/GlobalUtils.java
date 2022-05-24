package com.ilabquality.modules.global.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.ilabquality.modules.global.enums.PropertyFolder;
import com.ilabquality.modules.global.reference.SystemDefault;
import com.ilabquality.modules.reporting.ExtentReport;
import com.ilabquality.modules.storing.FileService;
import com.ilabquality.modules.testing.BaseContext;

import lombok.NoArgsConstructor;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import org.testng.ITestNGMethod;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
public class GlobalUtils {
  private static final Gson gson = new Gson();
  private static FileService fileService = null;
  private static final SystemDefault defaults = SystemDefault.getInstance();

  static {
    try {
      fileService = new FileService();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static HashMap<String, String> buildTestDataHeader(Object data) {
    HashMap<String, String> dataMap = new HashMap<>();
    List<?> dataArray = convertObjectToList(data);

    for (int i = 0; i < dataArray.size(); i++) {
      dataMap.put(String.valueOf(dataArray.get(i)), String.valueOf(i));
    }

    return dataMap;
  }

  public static HashMap<String, String> buildScenarioTestData(HashMap<String, String> scenarioHeadersMap, Object[] data) {
    HashMap<String, String> dataMap = new HashMap<>();
    List<?> dataArray = convertObjectToList(data);

    for (String key : scenarioHeadersMap.keySet()) {
      int keyIndexData = Integer.parseInt(scenarioHeadersMap.get(key));
      dataMap.put(key, StringUtils.normalizeSpace(String.valueOf(dataArray.get(keyIndexData))));
    }

    return dataMap;
  }

  public static List<?> convertObjectToList(Object obj) {
    List<?> list = new ArrayList<>();
    if (obj.getClass().isArray()) {
      list = Arrays.asList((Object[]) obj);

    } else if (obj instanceof Collection) {
      list = new ArrayList<>((Collection<?>) obj);
    }

    return list;
  }

  public static <T> T getFieldValueFromObject(Object obj, String fieldName) throws IllegalAccessException {
    T value = null;

    for (Field field : obj.getClass().getDeclaredFields()) {
      field.setAccessible(true);

      if (field.getName().equalsIgnoreCase(fieldName)) {
        value = (T) field.get(obj);
      }
    }

    return value;
  }

  public static String buildSuiteName(String suiteId) {
    LocalDateTime dateObj = LocalDateTime.now();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yy_HH-mm-ss");

   return String.format("suite_%s_%s", dateObj.format(dateFormat), suiteId);
  }

  public static LinkedHashMap<String, Class<?>> buildTestClassMap(List<Class<?>> classes, Class<?>[] testsToRun) {
    LinkedHashMap<String, Class<?>> classMap = new LinkedHashMap<>();
    int classCounter = 0;

    for (Class<?> clazz : classes) {
      testsToRun[classCounter] = clazz;
      classMap.put(clazz.getSimpleName(), clazz);
      classCounter++;
    }

    return classMap;
  }

  public static String convertListToCommaSeperatedStringList(List<?> list) {
    return list.toString()
      .replace("[", "")
      .replace("]", "")
      .replace("_", " ");
  }

  public static String drawCharacterLine(char character, Integer length) {
    StringBuilder line = new StringBuilder();
    for (Integer num: new Integer[length]) {
      line.append(character);
    }
    return line.toString();
  }

  public static JsonObject readTestParamsJson(String fileNamePrefix) {
    String fileDirectory = String.format("%s/json", PropertyFolder.TEST);
    String fileName = String.format("%s.json", fileNamePrefix);
    String jsonString = fileService.readTextFile(fileDirectory, fileName);

    return gson.fromJson(jsonString, JsonObject.class);
  }

  public static void prettyPrintObject(Object object, String context) {
    if (object != null) {
      String objectString = ToStringBuilder.reflectionToString(object);
      System.out.printf("\nPrettyPrint: %s: %s: \n", context, objectString);
    } else {
      System.out.printf("\nPrettyPrint: %s: Object is null\n\n", context);
    }
  }

  public static Path getFileDirectoryWithoutFile(Path path) {
    Path fileName = path.getFileName();
    List<String> fileParts = List.of(fileName.toString().split("\\."));
    Path filePath;

    if (fileParts.size() > 1) {
      String filePathString = path.toString();
      int indexOfFileName = filePathString.indexOf(String.valueOf(fileName));
      filePath = Paths.get(filePathString.substring(0,indexOfFileName));
    } else {
      filePath = path;
    }

    return Paths.get(String.valueOf(filePath));
  }

  public static String getSubDirectoryNameByPositionFromRight(Path path, Integer positionFromRight) {
    Path filePathWithoutFileName = GlobalUtils.getFileDirectoryWithoutFile(path);

    List<String> pathParts = new ArrayList<>(List.of(filePathWithoutFileName.toString().split(File.separator)));
    Collections.reverse(pathParts);

    return pathParts.get(positionFromRight - 1);
  }

  public static Path getPathDroppingPathsByPositionFromRight(Path path, Integer positionFromRight) {
    String pathFromRight = GlobalUtils.getSubDirectoryNameByPositionFromRight(path, positionFromRight);
    int indexOfPathFromRight = path.toString().indexOf(pathFromRight);

    return Paths.get(path.toString().substring(0,indexOfPathFromRight));
  }

  public static Map<String, Object> convertObjectFieldsToHashMap(Object obj) {
    Map<String, Object> map = new HashMap<>();

    for (Field field : obj.getClass().getDeclaredFields()) {
      field.setAccessible(true);

      try {
        map.put(field.getName(), field.get(obj));
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }

    return map;
  }

  public static long[] splitDurationIntimeUnits(long duration) {
    long[] response = new long[4];
    response[0] = duration;
    long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
    response[1] = minutes;
    long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(minutes);
    response[2] = seconds;
    long millis = TimeUnit.MILLISECONDS.toMillis(duration) - TimeUnit.MINUTES.toMillis(minutes) - TimeUnit.SECONDS.toMillis(seconds);
    response[3] = millis;

    return response;
  }

  public static String extractPdfLinkPath(BaseContext context) {
    String pdfFilePath = context.pdfFilePath.toString();
    int indexOfTestPath = pdfFilePath.indexOf(defaults.TEST_REPORT_PATH);
    pdfFilePath = pdfFilePath.substring(indexOfTestPath);

    // ###-NB-### Do not remove this print line because it's used to get the suite name for the pdf link in the Poller Application
    // http://............./TEST/report/suite_09-03-22_10-37-28_3/suite_09-03-22_10-37-28_3.pdf
    System.out.printf("\n[TEST-LISTENER] [PDF-LINK][START]%s[END]", pdfFilePath);

    return pdfFilePath;
  }

  public static void buildExtentReportFailure(ExtentReport extentInstance, Exception error, Class<?> clazz, ExtentTest test) {
    if (test.getStatus().equals(Status.FAIL)) {
      extentInstance.ExtentLogInfoFail("Failed to Complete " + clazz.getSimpleName() + " Test Exception : " + error.getMessage(), test);
    }

    if (test.getStatus().equals(Status.ERROR)) {
      error.printStackTrace();
    }
  }

  public static void buildExtentReportFailure(ExtentReport extentInstance, Exception error, Class<?> clazz, ExtentTest test, Boolean printStackTrace) {
    if (test.getStatus().equals(Status.FAIL)) {
      extentInstance.ExtentLogInfoFail("Failed to Complete " + clazz.getSimpleName() + " Test Exception : " + error.getMessage(), test);
    }

    if (printStackTrace || test.getStatus().equals(Status.ERROR)) {
      error.printStackTrace();
    }
  }

  public static  String buildTestDetailString(ITestNGMethod method) {
    String response = null;
    String methodName = method.getMethodName();
    String className = method.getInstance().getClass().getSimpleName();
    String methodPriority =  String.valueOf(method.getPriority());
    String methodDate = new Date(method.getDate()).toString();

    if (!Objects.equals(methodName, "BeforeTest")) {
      response = String.format("[EXECUTOR] [TEST] [%s] %s : %s : %s", className, methodName, methodPriority, methodDate);
    }

    return response;
  }

  public static String extractClassName(String fullClassName) {
    return fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
  }

  public static void prettyPrintHashMap(HashMap<?,?> map, String context) {
    System.out.printf("\n%s", context);

    for (Object o : map.keySet()) {
      System.out.printf("\nKey: %s:%s", o.toString(), map.get(o).toString());
    }

    System.out.println("\n");
  }

  public static <T> void prettyPrintTwoDimArray(T[][] array, String context) {
    System.out.printf("\n%s", context);
    Map<Object, Object> map = ArrayUtils.toMap(array);

    for (Object o : map.keySet()) {
      System.out.printf("\nKey: %s:%s", o.toString(), map.get(o).toString());
    }

    System.out.println("\n");
  }

  public static LocalDate randomLocalDateBetween(LocalDate startInclusive, LocalDate endExclusive) {
    long startEpochDay = startInclusive.toEpochDay();
    long endEpochDay = endExclusive.toEpochDay();
    long randomDay = ThreadLocalRandom
      .current()
      .nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomDay);
  }
}
