package com.ilabquality.modules.testing;

import com.google.gson.JsonObject;
import com.ilabquality.modules.global.enums.PropertyFolder;
import com.ilabquality.modules.global.reference.SystemDefault;
import com.ilabquality.modules.global.utils.GlobalUtils;
import com.ilabquality.modules.global.utils.PropertyManager;
import com.ilabquality.modules.testing.listeners.SuiteListener;
import com.ilabquality.modules.testing.listeners.TestListener;
import com.ilabquality.modules.global.utils.ClassFinder;
import com.ilabquality.modules.testing.listeners.ReportListener;

import org.testng.TestNG;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Runner {
  private final SystemDefault defaults = SystemDefault.getInstance();
  private final BaseContext context = BaseContext.getInstance();
  private final ClassFinder classFinder = new ClassFinder();
  private final Path reportPath = Paths.get(defaults.TEST_REPORT_PATH);

  public void runTest(List<JsonObject> tests, String suiteId) {
    try {
      List<String> testNames = tests.stream()
        .map((item) -> item.getAsJsonObject().get("name").getAsString())
        .collect(Collectors.toList());

      List<Class<?>> classes = classFinder.findClasses(testNames);
      TestNG testSuite = new TestNG();

      if (classes.isEmpty()) {
        System.out.println("[EXECUTOR] Sorry but we could not locate your test(s)...\n");
        System.exit(1);
      }

      String suiteName = GlobalUtils.buildSuiteName(suiteId);
      context.suiteName = suiteName;
      context.systemArgs = new ArrayList<>();
      context.systemArgs.add(tests.toString());
      context.systemArgs.add(suiteId);
      context.testParams = GlobalUtils.readTestParamsJson("test");

      Class<?>[] testsToRun = new Class[classes.size()];
      context.classes = GlobalUtils.buildTestClassMap(classes, testsToRun);
      context.classNames = testNames;
      context.systemPackage = testsToRun[0].getPackage().getName();
      context.cloudDeployment = Boolean.parseBoolean(PropertyManager.getProperty(PropertyFolder.CLIENT, "client", "cloudDeployment"));

      context.testCount = 0;
      BaseContext.setInstance(context);

      String eventsString = GlobalUtils.convertListToCommaSeperatedStringList(testNames);
      System.out.printf("[EXECUTOR] [ARGS] Jenkins arguments passed in: %s, %s\n", tests, suiteId);
      System.out.printf("[EXECUTOR] [PACKAGE] Test package where tests are located: %s\n", context.systemPackage);
      System.out.printf("[EXECUTOR] [TEST] Tests that will be attempted: %s", eventsString);

      testSuite.setVerbose(0);
      testSuite.setTestClasses(testsToRun);
      testSuite.addListener(new TestListener());
      testSuite.addListener(new SuiteListener());
      testSuite.addListener(new ReportListener());
      testSuite.setDefaultSuiteName(suiteName);
      testSuite.setOutputDirectory(String.valueOf(reportPath));

      testSuite.run();

    } catch (Exception exception) {
      System.out.println("[FAILURE] " + exception.getCause() + ":" + exception.getMessage());

      exception.printStackTrace();
      System.exit(1);
    }
  }

}
