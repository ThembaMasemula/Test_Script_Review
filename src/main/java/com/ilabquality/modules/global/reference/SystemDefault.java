package com.ilabquality.modules.global.reference;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SystemDefault {
  private static SystemDefault instance = null;

  public static SystemDefault getInstance() {
    if (instance == null) {
      instance = new SystemDefault();
    }

    return instance;
  }

  public static void setInstance(SystemDefault instance) {
    if (instance != null) {
      SystemDefault.instance = instance;
    }
  }

  public Integer CHAR_LINE_LENGTH = 150;
  public String TEST_PACKAGE = "com/ilabquality/tests";
  public String TEST_REPORT_PATH = "TEST/report";
  public String PROP_PATH_CLIENT = "CLIENT/properties";
  public String PROP_PATH_DEFAULT = "DEFAULT/properties";
  public String SYSTEM_FILE_NAME = "config.properties";
  public String JIRA_FILE_NAME = "jira.properties";
  public String MICROFOCUS_FILE_NAME = "microfocus.properties";
  public String SELENIUM_URI = "http://54.85.200.63:4444/wd/hub";
  public String SELENIUM_URI_PROXY = "http://localhost:4444/wd/hub";
  public Boolean USE_SELENIUM_PROXY = true;
}
