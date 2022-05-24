package com.ilabquality;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.ilabquality.modules.global.enums.PropertyType;
import com.ilabquality.modules.global.reference.SystemDefault;
import com.ilabquality.modules.storing.FileService;
import com.ilabquality.modules.testing.Runner;

import org.apache.commons.lang3.EnumUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

public class Executor {
  private final static Gson gson = new Gson();

  public static void main(String[] args) throws Exception {
    Runner testRunner = new Runner();
    FileService fileService = new FileService();

    if(args.length < 2) {
      System.out.println("\n[EXECUTOR] Missing arguments...");
      System.exit(1);
    }

    String eventsString  = args[0].trim();
    Type listType = new TypeToken<ArrayList<JsonObject>>() {}.getType();
    List<JsonObject> tests = gson.fromJson(eventsString, listType);

    JsonObject firstTest = tests.get(0).getAsJsonObject();
    String firstTestName= firstTest.get("name").getAsString();
    String suiteId = args[1].trim();

    if (EnumUtils.isValidEnum(PropertyType.class, firstTestName)) {
      String fileNameFieldName = firstTestName + "_FILE_NAME";
      SystemDefault defaults = SystemDefault.getInstance();

      Field fileNameField = defaults.getClass().getDeclaredField(fileNameFieldName);
      fileNameField.setAccessible(true);
      String fileName = fileNameField.get(defaults).toString();

      if (!fileName.isEmpty()) {
        System.out.println("[EXECUTOR] Parsing properties file: " + fileNameFieldName + ":" + fileName + "\n");
        fileService.writePropertyFile(fileName, gson.fromJson(eventsString, JsonObject.class));
      }

    } else {
      System.out.println("[EXECUTOR] Just a minute, we are searching for your test...\n");
      testRunner.runTest(tests, suiteId);
    }
  }
}

