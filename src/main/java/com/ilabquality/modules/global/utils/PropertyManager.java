package com.ilabquality.modules.global.utils;

import com.ilabquality.modules.global.enums.PropertyFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Locale;
import java.util.Properties;

public class PropertyManager {

  public static String getProperty(PropertyFolder fileFolder, String fileName, String key) {
    try {
      String newKey = key.substring(key.indexOf(".") + 1);
      Properties prop = new Properties();

      String filePath = System.getProperty("user.dir")
        + File.separator
        + fileFolder
        + File.separator
        + "properties"
        + File.separator
        + fileName.toLowerCase(Locale.ROOT) + ".properties";

      prop.load(new FileInputStream(filePath));

      return prop.getProperty(String.format("prop.%s", newKey));

    } catch (IOException e) {

      return null;
    }
  }

  public static String getPropertyByPath(String filePath, String fileName, String key) {
    try {
      String newKey = key.substring(key.indexOf(".") + 1);
      Properties prop = new Properties();

      filePath = System.getProperty("user.dir")
        + File.separator
        + filePath
        + File.separator
        + fileName.toLowerCase(Locale.ROOT) + ".properties";

      prop.load(new FileInputStream(filePath));

      return prop.getProperty(String.format("prop.%s", newKey));

    } catch (IOException e) {

      return null;
    }
  }
}
