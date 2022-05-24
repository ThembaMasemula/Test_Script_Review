package com.ilabquality.modules.testing.utilsMobile;

import com.aventstack.extentreports.ExtentTest;

import com.ilabquality.modules.reporting.ExtentReport;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MobileActions {

  protected ExtentReport repo = new ExtentReport();

  public void clickObject(WebElement element) throws Exception {
    element.click();
  }

  public void jsClickAction(WebElement element, AndroidDriver driver) {
    if (element.isEnabled()) {
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", element);

    } else {
      System.out.println("Java script Unable to click on element");
    }
  }

  public boolean isAtCorrectPage(String title, AndroidDriver driver) {
    boolean isMatch = false;

    if (driver.getTitle().equalsIgnoreCase(title)) {
      isMatch = true;
      System.out.println("Title : " + title + " matches with the page");

    } else {
      System.out.println("Title : " + title + " does not matches with the page");
    }

    return isMatch;
  }

  public boolean elementExists(WebElement element) {
    try {
      element.isDisplayed();

      return true;

    } catch (Exception e) {
      return false;
    }
  }

  public boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();

    } catch (Exception e) {
      return false;
    }
  }

  public void scrollPage(AndroidDriver driver) {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy(0,180)", "");
  }

  public void scrollPageToElementView(WebElement element, WebDriver driver) throws InterruptedException {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("arguments[0].scrollIntoView();", element);
    Thread.sleep(1000);
  }

  public void scrollPageToElementView2(WebElement element, WebDriver driver) throws InterruptedException {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    Thread.sleep(1000);
  }

  public void scrollPageToElement(WebElement element, AndroidDriver driver) throws InterruptedException {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    Thread.sleep(1000);
  }

  public void highlightElement(WebElement element, AndroidDriver driver) {
    for (int i = 0; i < 10; i++) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
        "color: blue; border: 6px solid blue;");
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
    }
  }

  public boolean checkIfObjectIsDisplayed(
    WebElement elemet,
    int unit,
    String pMassage,
    String fMassage,
    AndroidDriver driver,
    ExtentTest test
  ) throws Exception {

    try {
      WebDriverWait wait = new WebDriverWait(driver, unit);
      wait.until(ExpectedConditions.visibilityOf(elemet));

    } catch (TimeoutException e) {
      System.out.println("Element " + elemet + " Not Displayed Error.");
      repo.ExtentLogFail(fMassage + "Element :" + elemet + " Not Displayed Error: " + e.getMessage(), driver, test);
      driver.quit();

      return false;
    }

    repo.ExtentLogPass(pMassage, driver, test);
    return true;
  }

  public boolean checkIfObjectIsDisplayed(WebElement elemet, int unit, AndroidDriver driver, ExtentTest test) throws Exception {
    try {
      WebDriverWait wait = new WebDriverWait(driver, unit);
      wait.until(ExpectedConditions.visibilityOf(elemet));

    } catch (TimeoutException e) {
      System.out.println("Element " + elemet + " Not Displayed Error.");
      repo.ExtentLogFail("Element :" + elemet + " Not Displayed Error: " + e.getMessage(), driver, test);
      driver.quit();

      return false;
    }

    return true;
  }

  public Boolean compareText(WebElement element, String TextOne, AndroidDriver driver, ExtentTest test) throws Exception {
    try {
      String text = element.getText();
      text.contains(TextOne);

      return true;

    } catch (Exception e) {
      repo.ExtentLogFail("Element :" + element + " Data Input Error: " + e.getMessage(), driver, test);
      driver.quit();

      return false;
    }
  }

  public boolean checkMobileElementExists(AndroidElement element, Integer timer, AndroidDriver mobileDriver) {
    Boolean isElementPresent = null;

    try {
      WebDriverWait wait = new WebDriverWait(mobileDriver, timer);
      isElementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();

      return isElementPresent;

    } catch (Exception e) {
      isElementPresent = false;
      System.out.println(e.getMessage());
      mobileDriver.quit();
      return isElementPresent;
    }
  }

  public boolean waitForElement(WebElement e, AndroidDriver driver) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, 5);
      wait.until(ExpectedConditions.visibilityOf(e));

      return true;

    } catch (TimeoutException exception) {
      exception.printStackTrace();

      return false;
    }
  }

  public void waitForElementTobeClickable(WebElement e, AndroidDriver driver) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, 20);
      wait.until(ExpectedConditions.elementToBeClickable(e));

    } catch (TimeoutException exception) {
      exception.printStackTrace();
    }
  }

  public void enterText(WebElement element, String text) throws Exception {
    if (!text.isEmpty()) {
      clearTextField(element);
      element.sendKeys(text);
    }
  }

  public boolean waitForObjectInvisibility(WebElement e, int timeout, WebDriver driver, ExtentTest test) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      wait.until(ExpectedConditions.invisibilityOf(e));

      return true;

    } catch (TimeoutException exception) {
      exception.printStackTrace();

      return false;
    }
  }

  public void waitForPageLoaded(WebDriver driver) {
    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
      }
    };

    try {
      Thread.sleep(1000);
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(expectation);

    } catch (Throwable error) {
      System.out.println("Timeout waiting for Page Load Request to complete.");
    }
  }

  public void clearTextField(WebElement element) throws Exception {
    element.clear();
  }

  public boolean waitForElement(WebElement e, int timeOut, AndroidDriver driver) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeOut);
      wait.until(ExpectedConditions.elementToBeClickable(e));

    } catch (TimeoutException exp) {
      exp.printStackTrace();

      return false;
    }

    return true;
  }

  public boolean verifyMessage(WebElement elmnt, String expValue) {
    boolean isMatch = false;

    try {
      String mesg = elmnt.getText().trim();
      System.out.print("The label text: " + mesg);

      if (mesg.equalsIgnoreCase(expValue)) {
        isMatch = true;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return isMatch;
  }

  public void presEnter(WebElement element, WebDriver driver, ExtentTest test) throws Exception {
    try {
      element.sendKeys(Keys.ENTER);
    } catch (Exception e) {
      System.out.println("Element " + element + " NOT found unable to press enter on Object.");
      repo.ExtentLogFail("Element :" + element + " Enter not Pressed Error: " + e.getMessage(), driver, test);
      driver.quit();

      return;
    }
  }

  public boolean verifyElementDisplayed(WebElement e) {
    boolean isDisplayed = false;

    if (e.isDisplayed()) {

      isDisplayed = true;
    }

    return isDisplayed;
  }

  public boolean verifyElementText(WebElement e, String text) {
    boolean isMatching = false;

    if ((e.getAttribute("value")).equalsIgnoreCase(text)) {
      isMatching = true;
    }

    return isMatching;
  }

  public boolean verifyElementTextIgnoreWhiteSpace(WebElement e, String text) {
    boolean isMatching = false;

    if ((e.getAttribute("value").replaceAll("\\s+", "")).equalsIgnoreCase(text.replaceAll("\\s+", ""))) {

      isMatching = true;
    }

    return isMatching;
  }

  public String getElementText(WebElement e) {
    return e.getText();
  }

  public void synchronizePage(AndroidDriver driver) throws Exception {
    waitforPagetoFullyLoad(driver);
  }

  private void waitforPagetoFullyLoad(AndroidDriver driver) throws Exception {
    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
      public Boolean apply(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
      }
    };

    try {
      Thread.sleep(1000);
      WebDriverWait wait = new WebDriverWait(driver, 30);
      wait.until(expectation);

    } catch (Throwable error) {
      System.out.println("Timeout waiting for Page Load Request to complete.");
    }
  }

  public void switchToTab(int tab, AndroidDriver driver) throws Exception {
    Thread.sleep(3000);
    ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
    driver.switchTo().window(allTabs.get(tab));
  }

  public void clickObject(WebElement element, String pMassage, String fMassage, AndroidDriver driver, ExtentTest test) throws Exception {
    try {
      element.click();

    } catch (Exception e) {
      System.out.println(element + " NOT found unable to click Object.");
      repo.ExtentLogFail(fMassage + " : " + element + " Not Not Clicked Error : " + e.getMessage(), driver, test);
    }

    repo.ExtentLogPass(pMassage, driver, test);
  }

  public void enterText(
    WebElement element,
    String text,
    String pMassage,
    String fMassage,
    AndroidDriver driver,
    ExtentTest test
  ) throws Exception {

    try {
      if (!text.isEmpty()) {
        clearTextField(element);
        element.sendKeys(text);
      }

    } catch (Exception e) {
      System.out.println("Element " + element + " Data Input Error.");
      repo.ExtentLogFail(fMassage + "Element :" + element + " Data Input Error: " + e.getMessage(), driver, test);
    }

    repo.ExtentLogPass(pMassage, driver, test);
  }

  public void clickObjectUsingAction(WebElement e, AndroidDriver driver) throws Exception {
    Actions action = new Actions(driver);
    action.moveToElement(e).click().build().perform();
  }

  public static void changeDriverContextToWeb(AndroidDriver mobileDriver) {
    Set<String> allContext = mobileDriver.getContextHandles();

    for (String context : allContext) {
      if (context.contains("WEBVIEW")) {
        mobileDriver.context(context);
        break;
      }

      if (context.contains("CHROMIUM")) {
        mobileDriver.context(context);
        break;
      }
    }
  }

  public static void changeDriverContextToWeb(IOSDriver mobileDriver) {
    Set<String> allContext = mobileDriver.getContextHandles();

    for (String context : allContext) {
      if (context.contains("WEBVIEW")) {
        mobileDriver.context(context);
        break;
      }

      if (context.contains("CHROMIUM")) {
        mobileDriver.context(context);
        break;
      }
    }
  }

  public static void changeDriverContextToApp(AndroidDriver mobileDriver) {
    Set<String> allContext = mobileDriver.getContextHandles();

    for (String context : allContext) {
      if (context.contains("NATIVE")) {
        mobileDriver.context(context);
        break;
      }
    }
  }

  public static void changeDriverContextToApp(IOSDriver mobileDriver) {
    Set<String> allContext = mobileDriver.getContextHandles();

    for (String context : allContext) {
      if (context.contains("NATIVE")) {
        mobileDriver.context(context);
        break;
      }
    }
  }

  public void scrollUpandDown(String strUporDown) {
    Actions action = new Actions(MobileUtilities.getMobileDiver());

    if (strUporDown.toUpperCase().equals("DOWN")) {
      action.sendKeys(Keys.PAGE_DOWN).build().perform();

    } else {
      action.sendKeys(Keys.PAGE_UP).build().perform();
    }
  }

  public boolean CheckMobileElementExists(AndroidDriver mobileDriver, Integer timer, String property, String Object) {
    Boolean isElementPresent = null;

    try {
      WebDriverWait wait = new WebDriverWait(mobileDriver, timer);
      switch (property.toUpperCase()) {
        case "XPATH":
          isElementPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Object))).isDisplayed();
          return isElementPresent;
        case "ID":
          isElementPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Object))).isDisplayed();
          return isElementPresent;
        case "CLASS":
          isElementPresent = wait.until(ExpectedConditions.presenceOfElementLocated(By.className(Object))).isDisplayed();
          return isElementPresent;
      }

    } catch (Exception e) {
      isElementPresent = false;
      System.out.println(e.getMessage());

      return isElementPresent;
    }
    return isElementPresent;
  }

  public boolean clickObjectTextFromList(
    List<WebElement> object,
    String objectToFind,
    String pMassage,
    String fMassage,
    AndroidDriver driver,
    ExtentTest test
  ) throws Exception {

    try {
      for (int x = 0; x < object.size(); x++) {
        System.out.println(object.get(x).getText().replaceAll("\\s+", ""));
        if (object.get(x).getText().replaceAll("\\s+", "").equalsIgnoreCase(objectToFind.replaceAll("\\s+", ""))) {
          System.out.println("Object Text Located: " + object.get(x).getText().replaceAll("\\s+", ""));
          object.get(x).click();
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("Element " + object + " NOT found.");
      repo.ExtentLogFail(fMassage + " " + "Element :" + object + " Not Displayed Displayed Error: " + e.getMessage(), driver, test);
      driver.quit();

      return false;
    }
    repo.ExtentLogPass(pMassage, driver, test);

    return true;
  }
}
