package com.ilabquality.modules.testing.listeners;

import org.testng.IExecutionListener;

public class ExecutionListener implements IExecutionListener {

  @Override
  public void onExecutionStart() {
    System.out.println("\n\n[EXECUTION-LISTENER] [ON-START]");
  }

  @Override
  public void onExecutionFinish() {
    System.out.println("\n[EXECUTION-LISTENER] [ON-FINISH]");
  }
}
