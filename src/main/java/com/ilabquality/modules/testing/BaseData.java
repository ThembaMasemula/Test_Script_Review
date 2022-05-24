package com.ilabquality.modules.testing;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.codoid.products.fillo.Recordset;

import com.google.gson.JsonObject;

import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.reporting.ExtentReport;
import com.ilabquality.modules.testing.utilsData.DataFunctions;
import com.ilabquality.modules.testing.utilsWeb.WebFunctions;

import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;

import java.nio.file.Paths;

import java.sql.ResultSet;

import java.util.HashMap;

public class BaseData extends BaseDriver {
  protected WebFunctions webFunctions;
  protected DataFunctions dataInstance = new DataFunctions();
  protected ExtentReport reportInstance = new ExtentReport();

  public static ExtentTest test;
  public static ExtentTest parent;
  public static BaseContext context = BaseContext.getInstance();

  public BaseData() {
    dataInstance.setEnvironmentVariables();
  }

  public int getDataRowCount(String testName) {
    int rowCount = 0;

    try {
      JsonObject testParams = context.testParams.get(testName).getAsJsonObject();
      String dataLocation = String.valueOf(Paths.get(ROOT_PATH + File.separator + testParams.get("testDataUrl").getAsString()));
      String sheetName = testParams.get("sheetName").getAsString();
      String dataType = dataInstance.getDataType();

      switch (dataType.toUpperCase()) {
        case "EXCEL":
          Sheet sheet =  dataInstance.ReadExcel(dataLocation, sheetName);
          rowCount = dataInstance.rowCount(sheet, null, null, dataType) - 1;
          break;

        case "FILLO":
          Recordset record  = dataInstance.ConnectFillo(dataLocation, "Select * from Sheet1");
          rowCount = dataInstance.rowCount(null, record, null, dataType);
          break;

        case "SQLSERVER":
          ResultSet result = dataInstance.ConnectAndQuerySQLServer(
            dataInstance.getDBHost(),
            dataInstance.getDBUsername(),
            dataInstance.getDBPassword(),
            "Select * from  [BookFlights].[dbo].[BookFlights]"
          );

          rowCount = dataInstance.rowCount(null, null, result, dataType);
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return rowCount;
  }

  public Object[][] getData(String testName) {
    Object[][] parsedData = new Object[0][0];

    try {
      JsonObject testParams = context.testParams.get(testName).getAsJsonObject();
      String dataLocation = String.valueOf(Paths.get(ROOT_PATH + File.separator + testParams.get("testDataUrl").getAsString()));
      String sheetName = testParams.get("sheetName").getAsString();

      DATA_TYPE = dataInstance.getDataType();
      PASSWORD = dataInstance.getWebPassword();
      USER_NAME = dataInstance.getWebUserName();

      switch (dataInstance.getDataType().toUpperCase()) {
        case "EXCEL":
          DATA_SHEET = dataInstance.ReadExcel(dataLocation, sheetName);
          DATA_ROW_COUNT = dataInstance.rowCount(DATA_SHEET, DATA_RECORD_SET, TEST_RESULT_SET, DATA_TYPE) - 1;
          break;

        case "FILLO":
          DATA_RECORD_SET = dataInstance.ConnectFillo(dataLocation, "Select * from Sheet1");
          DATA_ROW_COUNT = dataInstance.rowCount(DATA_SHEET, DATA_RECORD_SET, TEST_RESULT_SET, DATA_TYPE);
          break;

        case "SQLSERVER":
          TEST_RESULT_SET = dataInstance.ConnectAndQuerySQLServer(
            dataInstance.getDBHost(),
            dataInstance.getDBUsername(),
            dataInstance.getDBPassword(),
            "Select * from  [BookFlights].[dbo].[BookFlights]"
          );

          DATA_ROW_COUNT = dataInstance.rowCount(DATA_SHEET, DATA_RECORD_SET, TEST_RESULT_SET, DATA_TYPE);
          TEST_RESULT_SET = dataInstance.ConnectAndQuerySQLServer(
            dataInstance.getDBHost(),
            dataInstance.getDBUsername(),
            dataInstance.getDBPassword(),
            "Select * from  [BookFlights].[dbo].[BookFlights]"
          );
      }

      parsedData = DataFunctions.ReadTestData(sheetName, dataLocation);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return parsedData;
  }

  public void setupReport(String reportName) {
    ExtentReports extendReport = reportInstance.initializeExtentReports(reportName, dataInstance.getAppendReport());
    reportInstance.setExtent(extendReport);
  }

  protected void initialiseWeb() {
    webFunctions = new WebFunctions();
  }

  public void addTestToMap(String testName) {
    TEST_SCENARIO_MAP.put(testName, null);
  }

  protected void TestName(String testName, String childName) {
    setupReport(TEST_NAME);
    addTestToMap(TEST_NAME);

    parent = reportInstance.setParent(testName);
    test = reportInstance.setChildNode(childName, parent);
    test.assignCategory(dataInstance.getReportHead());
  }

  public HashMap<String, String> buildTestData(Object[] data, Boolean buildHeader) {
    if (buildHeader) {
      return GlobalUtils.buildTestDataHeader(data);
    } else {
      return GlobalUtils.buildScenarioTestData(TEST_HEADER_MAP, data);
    }
  }

  public void buildScenarioData(Object[][] testData, Integer rowNum) {
    TEST_HEADER_MAP = buildTestData(testData[0], true);
    TEST_DATA_MAP = buildTestData(testData[rowNum - 1], false);
  }
}
