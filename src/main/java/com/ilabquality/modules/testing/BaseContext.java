package com.ilabquality.modules.testing;

import com.google.gson.JsonObject;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;

public class BaseContext {
  private static BaseContext instance = null;

  public static BaseContext getInstance() {
    if (instance == null) {
      instance = new BaseContext();
    }

    return instance;
  }

  public static void setInstance(BaseContext instance) {
    if (instance != null) {
      BaseContext.instance = instance;
    }
  }

  public int testCount;
  public Boolean cloudDeployment;
  public List<String> systemArgs;
  public String systemPackage;
  public String suiteName;
  public Path clientDirectoryPath;
  public Path clientLogoPath;
  public Path pdfFileName;
  public Path pdfFilePath;
  public Path pdfFileDirectoryPath;
  public LinkedHashMap<String, Class<?>> classes;
  public List<String> classNames;
  public List<JsonObject> tests;
  public JsonObject testParams;
  public String scenario;
  public String currentTest;
  public Object[][] data;
}
