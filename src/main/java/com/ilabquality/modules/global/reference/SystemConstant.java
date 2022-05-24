package com.ilabquality.modules.global.reference;

import com.codoid.products.fillo.Recordset;

import com.ilabquality.modules.global.enums.PropertyFolder;
import com.ilabquality.modules.global.utils.PropertyManager;

import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.nio.file.Paths;

import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemConstant {

  //SYSTEM Constants
  public static String ROOT_PATH = System.getProperty("user.dir");
  public static String CLIENT_ROOT_PATH = ROOT_PATH + "/CLIENT";
  public static String PASSWORD;
  public static String USER_NAME;
/*  public static String QC_PATH = "[ALM] Resources\\SAP Automation\\LMSAutomation";
  public static final String STR_WEB_TEST_ENV_URL = "https://hcm2preview.sapsf.eu/login?bplte_logout=1&company=shopritechT1&_s.crb=fo0axC7YS19e3XLQ77ATDfHqt1D92EQQFeI7kQee0n0%253d#/login";
  public static final String STR_GMAIL = "https://mail.google.com/mail/u/0/?tab=km#inbox";
  public static final String STR_OUTLOOK = "https://outlook.office.com/mail/inbox";*/

  //TEST Constants
  public static boolean TEST_HAS_FAILED;
  public static int TEST_INSTANCE;
  public static String TEST_NAME;
  public static String TEST_PATH;

  //DATA Constants
  public static int DATA_COUNTER = 1;
  protected int DATA_ROW_COUNT;
  protected Sheet DATA_SHEET;
  protected Recordset DATA_RECORD_SET;
  protected String DATA_TYPE;
  public static HashMap<String, String> TEST_DATA_MAP = new HashMap<>();
  public static Map<String, List<String>> TEST_SCENARIO_MAP;
  public static final String CLIENT_DATA_PATH = ROOT_PATH + "/testData/webData/";

  //REPORTING Constants
  public static float PDF_FAILED_ELEMENT_HEIGHT = 50f;
  public static float PDF_PADDING = 50f;
  public static float PDF_ELEMENT2ELEMENT_MARGIN = 20f;
  public static float TABLE_ROW_HEIGHT = 20f;
  public static float TABLE_CELL_PADDING = 20f;
  public static float TABLE_PADDING = 5f;
  public static float STD_PADDING_SML = 5f;
  public static float STD_PADDING_MED = 10f;
  public static float STD_PADDING_LRG = 15f;
  public static float STD_PADDING_XL = 30f;
  public static float STD_PADDING_XXL = 50f;
  public static float TABLE_LINE_SPACING = 1.5f;
  public static float MAX_LOGO_HEIGHT = 80f;
  public static float MAX_LOGO_WIDTH = 107f;
  public static float MAX_TITLE_HEIGHT = 30f;
  public static PDType1Font STD_FONT = PDType1Font.HELVETICA;
  public static float STD_FONT_TITLE = 18f;
  public static float STD_FONT_SUB_TITLE = 14f;
  public static float STD_FONT_BASE = 12f;
  public static float STD_FONT_SUBSCRIPT = 10f;
  public static float STD_FONT_FOOTER = 8f;

  //REPORTING Constants (Legacy)
  public static String TEST_REPORT_PATH;
  public static HashMap<String, String> TEST_HEADER_MAP = new HashMap<>();
  public static Map<String, String> TEST_RESULTS_MAP;
  public static Map<String, String> TEST_REPORT_MAP;
  public static String TEST_RESULT;
  public static String TEST_FINAL_RESULT;
  protected ResultSet TEST_RESULT_SET;
  public static final String PASSED_STATE = "pass";

  //CYCLE Constants
  public static final String CYCLE_ID_FILE_PATH = String.valueOf(Paths.get(ROOT_PATH, "/JiraFiles/JIRACycle.txt"));
  public static String DEFAULT_CYCLE_ID;
  public static final String NEW_CYCLE_NAME = "Sprint7";//Sprint
  public static final String DEFAULT_CYCLE_ID_FILE_PATH = String.valueOf(Paths.get(ROOT_PATH, "/JiraFiles/DefaultCycleSprint.txt"));

  //JIRA Constants
  public static final String JIRA_URL = "http://jira.vodacom.co.za";
  public static final String JIRA_LOGIN_USER_NAME = "kokelat";
  public static final String JIRA_LOGIN_PASSWORD = "Lungile@00187";
  public static final String JIRA_PROJECT_KEY = "DLAP";
  public static final String JIRA_RELEASE_VERSION = "48156"; //Sprint
  public static boolean JIRA_TESTS_COPIED;

  //MICROFOCUS Constants
  public static final String MICROFOCUS_URL = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "url");
  public static final String MICROFOCUS_USERNAME = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "username");
  public static final String MICROFOCUS_KEY = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "key");
  public static final String MICROFOCUS_OWNER = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "owner");
  public static final String MICROFOCUS_DOMAIN = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "domain");
  public static final String MICROFOCUS_PROJECT = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "projectname");
  public static final String MICROFOCUS_ISSUE = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "issuename");
  public static final String MICROFOCUS_SEVERITY = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "severity");
  public static final String MICROFOCUS_DEFECT_PROJECT = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "defectproject");
  public static final String MICROFOCUS_DEFECT_SUBJECT = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "defectsubject");
  public static final String MICROFOCUS_UPDATE_ALM = PropertyManager.getProperty(PropertyFolder.DEFAULT, "microfocus", "updateALM");
}
