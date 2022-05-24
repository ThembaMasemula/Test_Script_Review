package com.ilabquality.modules.testing.utils;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AngularWaits {

  private static WebDriver jsWaitDriver;
  private static WebDriverWait jsWait;
  private static JavascriptExecutor jsExec;
  private static WebDriver driver;

  //Get the Android  driver
  public static void setDriver(AndroidDriver mobileDriver) {
    jsWaitDriver = mobileDriver;
    jsWait = new WebDriverWait(jsWaitDriver, 15);
    jsExec = (JavascriptExecutor) jsWaitDriver;
  }

  // Get the Web driver
  public static void setDriver(WebDriver webDriver) {
    driver = webDriver;
    jsWaitDriver = webDriver;
    jsWait = new WebDriverWait(jsWaitDriver, 15);
    jsExec = (JavascriptExecutor) jsWaitDriver;
  }

  private void ajaxComplete() {
    jsExec.executeScript("var callback = arguments[arguments.length - 1];"
      + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
      + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
      + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
  }

  private void waitForJQueryLoad() {
    try {
      ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) AngularWaits.driver)
        .executeScript("return jQuery.active") == 0);

      boolean jqueryReady = (Boolean) jsExec.executeScript("return jQuery.active==0");

      if (!jqueryReady) {
        jsWait.until(jQueryLoad);
      }
    } catch (WebDriverException ignored) {
      System.out.println();
    }
  }

  private void waitForAngularLoad() {
    String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
    angularLoads(angularReadyScript);
  }

  private void waitUntilJSReady() {
    try {
      ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) AngularWaits.driver)
        .executeScript("return document.readyState")
        .toString().equals("complete");
      boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");

      if (!jsReady) {
        jsWait.until(jsLoad);
      }
    } catch (WebDriverException ignored) {
    }
  }

  private void waitUntilJQueryReady() {
    Boolean jQueryDefined = (Boolean) jsExec.executeScript("return typeof jQuery != 'undefined'");
    if (jQueryDefined) {
      poll(20);

      waitForJQueryLoad();

      poll(20);
    }
  }

  public void waitUntilAngularReady() {
    try {
      Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");

      if (!angularUnDefined) {
        Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");

        if (!angularInjectorUnDefined) {
          poll(20);

          waitForAngularLoad();

          poll(20);
        }
      }

    } catch (WebDriverException ignored) {
      System.out.println();
    }
  }

  public void waitUntilAngular5Ready() {
    try {
      Object angular5Check = jsExec.executeScript("return getAllAngularRootElements()[0].attributes['ng-version']");

      if (angular5Check != null) {
        Boolean angularPageLoaded = (Boolean) jsExec.executeScript("return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");

        if (!angularPageLoaded) {
          poll(20);

          waitForAngular5Load();

          poll(20);
        }
      }

    } catch (WebDriverException ignored) {
      System.out.println();
    }
  }

  private void waitForAngular5Load() {
    String angularReadyScript = "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1";
    angularLoads(angularReadyScript);
  }

  private void angularLoads(String angularReadyScript) {
    try {
      ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
        .executeScript(angularReadyScript).toString());

      boolean angularReady = Boolean.parseBoolean(jsExec.executeScript(angularReadyScript).toString());

      if (!angularReady) {
        jsWait.until(angularLoad);
      }
    } catch (WebDriverException ignored) {
      System.out.println();
    }
  }

  public void waitAllRequest() {
    waitUntilJSReady();
    ajaxComplete();
    waitUntilJQueryReady();
    waitUntilAngularReady();
    waitUntilAngular5Ready();
  }

  public void waitForElementAreComplete(By by, int expected) {
    ExpectedCondition<Boolean> angularLoad = driver -> {
      int loadingElements = AngularWaits.driver.findElements(by).size();
      return loadingElements >= expected;
    };

    jsWait.until(angularLoad);
  }

  public void waitForAnimationToComplete(String css) {
    ExpectedCondition<Boolean> angularLoad = driver -> {
      int loadingElements = AngularWaits.driver.findElements(By.cssSelector(css)).size();
      return loadingElements == 0;
    };

    jsWait.until(angularLoad);
  }

  private void poll(long milis) {
    try {
      Thread.sleep(milis);
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }
  }

}
