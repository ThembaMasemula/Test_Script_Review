package com.ilabquality.modules.testing.listeners;

import com.ilabquality.modules.global.reference.SystemDefault;
import com.ilabquality.modules.global.utils.GlobalUtils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {
  private final SystemDefault defaults = SystemDefault.getInstance();

  @Override
  public void onStart(ISuite suite) {
    System.out.print("\n[SUITE-LISTENER] [ON-START]");
    System.out.printf("\n[EXECUTOR] [TEST] [INFO] Output directory: %s", suite.getOutputDirectory());
  }

  @Override
  public void onFinish(ISuite suite) {
    System.out.println("\n\n[SUITE-LISTENER] [ON-FINISH]");
    System.out.println(GlobalUtils.drawCharacterLine("=".charAt(0), defaults.CHAR_LINE_LENGTH));
  }
}
