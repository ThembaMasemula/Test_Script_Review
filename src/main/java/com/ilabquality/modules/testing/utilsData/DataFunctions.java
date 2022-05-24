package com.ilabquality.modules.testing.utilsData;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import com.ilabquality.modules.global.enums.PropertyFolder;
import com.ilabquality.modules.global.reference.SystemConstant;
import com.ilabquality.modules.testing.BaseContext;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

import java.nio.file.Paths;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFunctions {

  public static BaseContext context = BaseContext.getInstance();
  public FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-ZA"), new RandomService());
  public Faker faker = new Faker(new Locale("en-ZA"));
  public Connection connect;
  public java.sql.Connection conn = null;
  public Sheet sheet;
  public Workbook workbook;
  public int fillonum = 1;
  public int count = 1;

  private String sWebURL;
  private String sPassword;
  private String sUserID;
  private String sAppLoc;
  private String sAppUsername;
  private String sAppPassword;
  private String sApi;
  private String sDataLocation;
  private String sDataType;
  private String sDBPassword;
  private String sDBUsername;
  private String sEmail;
  private String sEmailFrom;
  private String sEmailTo;
  private String sDBHost;
  private String sBrowser;
  private String sKobitonUsername;
  private String sKobitonPassword;
  private String sIOSUsername;
  private String sIOSPassword;
  private String sTestManagement;
  private String sTestMURL;
  private String sTestMUsername;
  private String sTestMPassword;
  private String sDomain;
  private String sProject;
  private String sReportName;
  private String sAppendReport;
  private String sAndroidUsername;
  private String sAndroidPassword;
  private String sbrowserDrivers;
  private String sReportHead;

  ArrayList<String> lines = new ArrayList<String>();

  public String getWebURL() {
    return sWebURL;
  }

  public String getWebPassword() {
    return sPassword;
  }

  public String getWebUserName() {
    return sUserID;
  }

  public String getWindowsAppLocation() {
    return sAppLoc;
  }

  public String getWindowsAppUsername() {
    return sAppUsername;
  }

  public String getWindowsAppPassword() {
    return sAppPassword;
  }

  public String getAPI() {
    return sApi;
  }

  public String getDataLocation() {
    return sDataLocation;
  }

  public String getDBHost() {
    return sDBHost;
  }

  public String getDBUsername() {
    return sDBUsername;
  }

  public String getDBPassword() {
    return sDBPassword;
  }

  public String getDataType() {
    return sDataType;
  }

  public String getSendEmail() {
    return sEmail;
  }

  public String getEmailFrom() {
    return sEmailFrom;
  }

  public String getEmailTo() {
    return sEmailTo;
  }

  public String getBrowser() {
    return sBrowser;
  }

  public String getKobitonPassword() {
    return sKobitonPassword;
  }

  public String getKobitonUsername() {
    return sKobitonUsername;
  }

  public String getIOSUsername() {
    return sIOSUsername;
  }

  public String getIOSPassword() {
    return sIOSPassword;
  }

  public String getTestManagement() {
    return sTestManagement;
  }

  public String getTestMURL() {
    return sTestMURL;
  }

  public String getTestMUsername() {
    return sTestMUsername;
  }

  public String getTestMPassword() {
    return sTestMPassword;
  }

  public String getDomain() {
    return sDomain;
  }

  public String getProject() {
    return sProject;
  }

  public String getReportName() {
    return sReportName;
  }

  public String getAppendReport() {
    return sAppendReport;
  }

  public String getAndroidUsername() {
    return sAndroidUsername;
  }

  public String getAndroidPassword() {
    return sAndroidPassword;
  }

  public String getBrowserDrivers() {
    return sbrowserDrivers;
  }

  public String getReportHead() {
    return sReportHead;
  }

  public void setEnvironmentVariables() {
    File dataFile;
    FileReader fileReader = null;
    JSONParser parser = new JSONParser();

    try {
      dataFile = new File(SystemConstant.ROOT_PATH
        + File.separator
        + PropertyFolder.DEFAULT
        + "/json/environment.json"
      );

      fileReader = new FileReader(dataFile);
      Object obj = parser.parse(fileReader);
      JSONObject jsonObject = (JSONObject) obj;

      sWebURL = (String) jsonObject.get("weburl");
      sUserID = (String) jsonObject.get("webusername");
      sPassword = (String) jsonObject.get("webpassword");
      sAppLoc = (String) jsonObject.get("windowsapplocation");
      sAppUsername = (String) jsonObject.get("windowsappusername");
      sAppPassword = (String) jsonObject.get("windowsapppassword");
      sApi = (String) jsonObject.get("API");
      sDataLocation = (String) jsonObject.get("datalocation");
      sDataType = (String) jsonObject.get("datatype");
      sDBHost = (String) jsonObject.get("dbhost");
      sDBPassword = (String) jsonObject.get("dbpass");
      sDBUsername = (String) jsonObject.get("dbusername");
      sEmail = (String) jsonObject.get("sendemail");
      sEmailFrom = (String) jsonObject.get("emailfrom");
      sEmailTo = (String) jsonObject.get("emailto");
      sBrowser = (String) jsonObject.get("browser");
      sKobitonUsername = (String) jsonObject.get("kobitonusername");
      sKobitonPassword = (String) jsonObject.get("kobitonpassword");
      sIOSUsername = (String) jsonObject.get("iosusername");
      sIOSPassword = (String) jsonObject.get("iospassword");
      sTestManagement = (String) jsonObject.get("testmanagement");
      sTestMURL = (String) jsonObject.get("testurl");
      sTestMUsername = (String) jsonObject.get("testmanagememntusername");
      sTestMPassword = (String) jsonObject.get("testmanagmentpassword");
      sDomain = (String) jsonObject.get("domain");
      sProject = (String) jsonObject.get("project");
      sReportName = (String) jsonObject.get("reportname");
      sAppendReport = (String) jsonObject.get("appendreport");
      sAndroidUsername = (String) jsonObject.get("androidusername");
      sAndroidPassword = (String) jsonObject.get("androidpassword");
      sbrowserDrivers = (String) jsonObject.get("browserDrivers");
      sReportHead = (String) jsonObject.get("reportHead");

    } catch (Exception exception) {
      exception.printStackTrace();

    } finally {
      try {
        fileReader.close();

      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }

  public String ReadTextFile(String filePath) throws IOException {
    BufferedReader input = null;
    FileReader file = new FileReader(filePath);
    input = new BufferedReader(file);
    String value = input.readLine();
    input.close();

    return value;
  }

  public void WriteTextFile(String filePath, String outputData) throws IOException {
    Writer output = null;
    File file = new File(filePath);
    output = new BufferedWriter(new FileWriter(file));

    output.write(outputData);
    output.close();
  }

  public void GetData(String Location, String sheetName) {
    String DefaultPath = System.getProperty("user.dir");
    sheet = ReadExcel(DefaultPath + Location, sheetName);
  }

  public Sheet ReadExcel(String FILE_NAME, String strSheetName) {
    try {
      FileInputStream excelFile = new FileInputStream(FILE_NAME);
      XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
      sheet = workbook.getSheet(strSheetName);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return sheet;
  }

  public void ReadExcelWorkbook(String FILE_NAME) throws IOException {
    FileInputStream excelFile = new FileInputStream(FILE_NAME);
    workbook = new XSSFWorkbook(excelFile);
  }

  public String getCellData(String strColumn, int iRow, Sheet sheet) {
    String Value = null;
    Row row = sheet.getRow(0);

    for (int i = 0; i < columnCount(sheet); i++) {
      if (row.getCell(i).getStringCellValue().trim().equals(strColumn)) {
        Row raw = sheet.getRow(iRow);
        Cell cell = raw.getCell(i);
        DataFormatter formatter = new DataFormatter();
        Value = formatter.formatCellValue(cell);
        break;
      }
    }

    return Value;
  }

  public String getCellData(String strColumn, int iRow, Sheet sheet, Recordset record, ResultSet resultset, String Type) {
    String sValue = null;

    try {
      switch (Type.toUpperCase()) {
        case "EXCEL":
          Row row = sheet.getRow(0);
          for (int i = 0; i < columnCount(sheet); i++) {

            if (row.getCell(i).getStringCellValue().trim().equals(strColumn)) {
              Row raw = sheet.getRow(iRow);
              Cell cell = raw.getCell(i);
              DataFormatter formatter = new DataFormatter();
              sValue = formatter.formatCellValue(cell);
              break;
            }
          }
          break;

        case "FILLO":
          if (iRow == fillonum) {

            if (record.next()) {
              fillonum = iRow + 1;
              sValue = record.getField(strColumn);
            }

          } else {
            sValue = record.getField(strColumn);
          }
          break;

        case "SQLSERVER":
          if (iRow == fillonum) {

            if (resultset.next()) {
              fillonum = iRow + 1;
              sValue = resultset.getString(strColumn);
            }

          } else {
            sValue = resultset.getString(strColumn);
          }
          break;
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return sValue;
  }

  public int rowCount(Sheet sheet, Recordset record, ResultSet resultset, String Type) {
    int count = 0;

    try {
      switch (Type.toUpperCase()) {
        case "EXCEL":
          count = sheet.getPhysicalNumberOfRows();
          break;

        case "FILLO":
          count = record.getCount();
          break;

        case "SQLSERVER":
          int i = 0;
          while (resultset.next()) {
            i++;
          }
          count = i;
      }
    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return count;
  }

  public int columnCount(Sheet sheet) {
    return sheet.getRow(0).getLastCellNum();
  }

  public Recordset ConnectFillo(String path, String Query) {
    Recordset record = null;

    try {
      Fillo fillo = new Fillo();
      connect = fillo.getConnection(path);
      record = connect.executeQuery(Query);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return record;
  }

  public ResultSet ConnectAndQuerySQLServer(String sDBURL, String sUserName, String sPassword, String sQuery) {
    ResultSet rs = null;

    try {
      String dbURL = sDBURL;
      String user = sUserName;
      String pass = sPassword;
      conn = DriverManager.getConnection(dbURL, user, pass);
      Statement stmt = conn.createStatement();
      rs = stmt.executeQuery(sQuery);

    } catch (SQLException ex) {
      ex.printStackTrace();

    }

    return rs;
  }

  public String GenerateSAIdNumber() {
    String saIdNumber = fakeValuesService.regexify(
      "(((\\d{2}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229))((\\d{4})(\\d{3})|(\\d{7}))");

    return saIdNumber;
  }

  public String GenerateSACellNumber(Boolean valid) {
    String saCellNumber = null;

    if (valid) {

      while (true) {
        saCellNumber = fakeValuesService.regexify("[+](27)[156789][0-9]{8}");
        Pattern pattern = Pattern.compile("[+](27)[156789][0-9]{8}");
        Matcher matcher = pattern.matcher(saCellNumber);

        if (matcher.matches()) {
          break;
        }
      }

      return saCellNumber;

    } else {

      while (true) {
        saCellNumber = fakeValuesService.bothify("+27#########");
        Pattern pattern = Pattern.compile("[+](27)[156789][0-9]{8}");
        Matcher matcher = pattern.matcher(saCellNumber);
        if (!matcher.matches()) {
          break;
        }
      }

      return saCellNumber;
    }
  }

  public String GenerateRandomBirthdayWithFormat(int minAge, int maxAge, String format) {
    Date date = faker.date().birthday(minAge, maxAge);

    return new SimpleDateFormat("dd/MM/yyyy").format(date);
  }

  public static Object[][] ReadTestData(String SheetName, String Location) {
    Object[][] Data = new Object[0][0];

    try {
      String file_location = String.valueOf(Paths.get(Location));
      FileInputStream fileInputStream = new FileInputStream(file_location);

      XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
      XSSFSheet worksheet = workbook.getSheet(SheetName);

      XSSFRow Row = worksheet.getRow(0);

      int RowNum = worksheet.getPhysicalNumberOfRows();
      int ColNum = Row.getLastCellNum();

      Data = new Object[RowNum][ColNum];

      for (int i = 0; i < RowNum; i++) {
        XSSFRow row = worksheet.getRow(i);

        for (int j = 0; j < ColNum; j++) {

          if (row == null) {
            Data[i][j] = "";

          } else {
            XSSFCell cell = row.getCell(j);

            if (cell == null) {
              Data[i][j] = "";

            } else {
              DataFormatter formatter = new DataFormatter();
              String value = formatter.formatCellValue(cell);
              Data[i][j] = value;
            }
          }
        }
      }

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    return Data;
  }
}
