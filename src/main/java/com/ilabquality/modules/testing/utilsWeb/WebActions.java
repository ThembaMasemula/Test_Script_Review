package com.ilabquality.modules.testing.utilsWeb;



import com.aventstack.extentreports.ExtentTest;
import com.ilabquality.modules.reporting.ExtentReport;

import com.ilabquality.modules.testing.utils.AngularWaits;
import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import java.io.File;

import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WebActions {

  protected ExtentReport repo = new ExtentReport();

  public void clickObject(WebElement element, WebDriver driver, ExtentTest test) {
    AngularWaits(driver);

    try {
      if (element.isDisplayed()) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
          .withTimeout(Duration.ofSeconds(5))
          .pollingEvery(Duration.ofMillis(1000))
          .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
      }

    } catch (Exception e) {
      System.out.println(element + " NOT found.");
      driver.quit();
    }
  }

  public void checkBox(WebElement element, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      if (element.isDisplayed()) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
          .withTimeout(Duration.ofSeconds(5))
          .pollingEvery(Duration.ofMillis(1000))
          .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        boolean isSelected = element.isSelected();
        if (!isSelected) {
          element.click();
        }
      }

    } catch (Exception e) {
      System.out.println(element + " NOT found.");
      repo.ExtentLogFail("Element : " + element + " Not Found Error : " + e.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void SelectByValue(WebElement element, String text, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      if (element.isDisplayed()) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
          .withTimeout(Duration.ofSeconds(5))
          .pollingEvery(Duration.ofMillis(1000))
          .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Select sValue = new Select(element);
        sValue.selectByValue(text);

      }

    } catch (Exception e) {
      System.out.println(element + " NOT found.");
      repo.ExtentLogFail("Element : " + element + " Not Found Error : " + e.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void SelectByIndex(WebElement element, int index, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      if (element.isDisplayed()) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
          .withTimeout(Duration.ofSeconds(5))
          .pollingEvery(Duration.ofMillis(1000))
          .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Select sValue = new Select(element);
        sValue.selectByIndex(index);
      }

    } catch (Exception e) {
      System.out.println(element + " NOT found.");
      repo.ExtentLogFail("Element : " + element + " Not Found Error : " + e.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void SelectByVisibleText(WebElement element, String text, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      if (element.isDisplayed()) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
          .withTimeout(Duration.ofSeconds(5))
          .pollingEvery(Duration.ofMillis(1000))
          .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Select sValue = new Select(element);
        sValue.selectByVisibleText(text);

      }

    } catch (Exception e) {
      System.out.println(element + " NOT found.");
      repo.ExtentLogFail("Element : " + element + " Not Found Error : " + e.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void selectObject(WebElement selectElement, WebDriver driver,String selectValue)
  {
    try
    {
      if (selectElement.isDisplayed())
      {
        Wait<WebDriver> wait = new FluentWait<>(driver)
          .withTimeout(Duration.ofSeconds(5))
          .pollingEvery(Duration.ofMillis(1000))
          .ignoring(WebDriverException.class);


        Select selc = new Select(selectElement);
        selc.selectByValue(selectValue);
      }
    } catch (Exception e){
      System.out.println(selectElement + "Not found");
    }
  }


  public void clickObject(WebElement element, String pMessage, String fMessage, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      if (element.isDisplayed()) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
          .withTimeout(Duration.ofSeconds(5))
          .pollingEvery(Duration.ofMillis(4000))
          .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
      }

    } catch (Exception e) {
      System.out.println(element + " NOT found.");
      repo.ExtentLogFail(fMessage + ": Element : " + element + " Not Found Error : " + e.getMessage(), driver, test);
      driver.quit();

      return;
    }

    repo.ExtentLogPass(pMessage, driver, test);
  }


  public void clickAction(WebElement element, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      if (element.isDisplayed()) {
        element.click();
      }

    } catch (Exception e) {
      System.out.println(element + " NOT found.");
      repo.ExtentLogFail("Element : " + element + " Not Found Error : " + e.getMessage(), driver, test);
      driver.quit();
    }
  }



  public void clickObjectUsingAction(WebElement e, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(5))
        .pollingEvery(Duration.ofMillis(1000))
        .ignoring(WebDriverException.class);
      wait.until(ExpectedConditions.elementToBeClickable(e));
      Actions action = new Actions(driver);
      action.moveToElement(e).click().build().perform();

    } catch (Exception ex) {
      System.out.println("Element " + e + " Data Input Error.");
      repo.ExtentLogFail("Element :" + e + " Data Input Error: " + ex.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void clickObjectActionSVG(WebElement e, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(5))
        .pollingEvery(Duration.ofMillis(1000))
        .ignoring(WebDriverException.class);
      wait.until(ExpectedConditions.elementToBeClickable(e));
      Actions action = new Actions(driver);
      action.click(e).build().perform();

    } catch (Exception ex) {
      System.out.println("Element " + e + " Data Input Error.");
      repo.ExtentLogFail("Element :" + e + " Data Input Error: " + ex.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void jsClickAction(WebElement element, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      if (element.isEnabled()) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

      } else {
        System.out.println("Java script Unable to click on element : " + element);
      }

    } catch (Exception e) {
      repo.ExtentLogFail("Element : " + element + " Not Found Error : " + e.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void enterText(WebElement element, String text) {
    if (!text.isEmpty()) {
      clearTextField(element);
      element.sendKeys(text);
    }
  }

  public void enterText(WebElement element, String text, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      if (!text.isEmpty()) {
        clearTextField(element);
        element.sendKeys(text);
      }

    } catch (Exception e) {
      System.out.println("Element " + element + " Data Input Error.");
      repo.ExtentLogFail("Element :" + element + " Data Input Error: " + e.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void clearTextField(WebElement element) {
    element.clear();
  }

  public void clearField(WebElement element) {
    element.sendKeys(Keys.CONTROL + "a");
    element.sendKeys(Keys.DELETE);
  }

  public void switchToFrame(WebElement element, WebDriver driver) throws InterruptedException {
    AngularWaits(driver);
    driver.switchTo().defaultContent();
    WebElement iframeElement = element;
    driver.switchTo().frame(iframeElement);
    Thread.sleep(2000);
  }

  public boolean witForElement(WebElement e, int timeOut, WebDriver driver) {
    AngularWaits(driver);

    try {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(timeOut))
        .pollingEvery(Duration.ofMillis(5000))
        .ignoring(WebDriverException.class);
      wait.until(ExpectedConditions.visibilityOf(e));

    } catch (TimeoutException exp) {
      exp.printStackTrace();

      return false;
    }

    return true;
  }

  public boolean checkIfObjectIsDisplayed(WebElement elemet, int unit, String pMassage, String fMassage, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

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

  public boolean checkIfObjectIsDisplayed(WebElement elemet, int unit, WebDriver driver) throws Exception {
    AngularWaits(driver);

    try {
      WebDriverWait wait = new WebDriverWait(driver, unit);
      wait.until(ExpectedConditions.visibilityOf(elemet));

    } catch (TimeoutException e) {
      driver.quit();

      return false;
    }

    return true;
  }

  public boolean checkObject(WebElement elemet, int unit, WebDriver driver) throws Exception {
    AngularWaits(driver);

    try {
      WebDriverWait wait = new WebDriverWait(driver, unit);
      wait.until(ExpectedConditions.visibilityOf(elemet));

    } catch (TimeoutException e) {
      return false;
    }

    return true;
  }

  public void presEnter(WebElement element, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      element.sendKeys(Keys.ENTER);

    } catch (
      Exception e) {
      System.out.println("Element " + element + " NOT found unable to click Object.");
      repo.ExtentLogFail("Element :" + element + " Enter not Pressed Error: " + e.getMessage(), driver, test);
      driver.quit();
    }
  }

  public void pressArrowDown(WebElement element, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      element.sendKeys(Keys.ARROW_DOWN);

    } catch (
      Exception e) {
      System.out.println("Element " + element + " NOT found unable to click Object.");
      repo.ExtentLogFail("Element :" + element + " Enter not Pressed Error: " + e.getMessage(), driver, test);
      driver.quit();
    }
  }

  public boolean elementExists(WebElement element) {
    try {
      element.isDisplayed();

    } catch (Exception e) {
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

  public boolean verifyMessageContains(WebElement elmnt, String expValue, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);
    boolean isMatch = false;

    try {
      String mesg = elmnt.getText().trim();
      System.out.print("The label text: " + mesg);

      if (mesg.contains(expValue)) {
        isMatch = true;
      }

    } catch (Exception e) {
      repo.ExtentLogFail("Validation For : " + expValue + "Failed " + " : " + e.getMessage(), driver, test);
      driver.quit();

      return isMatch;
    }

    repo.ExtentLogPass("Validation For : " + expValue + "Completed Successfully", driver, test);
    return isMatch;
  }

  public boolean verifyElementTextIgnoreWhiteSpace(WebElement e, String text) {
    boolean isMatching = false;

    if ((e.getAttribute("value").replaceAll("\\s+", "")).equalsIgnoreCase(text.replaceAll("\\s+", ""))) {
      isMatching = true;
    }

    return isMatching;
  }

  public boolean verifyElementContainsTextIgnoreWhiteSpace(WebElement e, String text) {
    boolean isMatching = false;
    System.out.println("Element Text : " + e.getAttribute("value"));

    if ((e.getAttribute("value").replaceAll("\\s+", "")).contains(text.replaceAll("\\s+", ""))) {
      isMatching = true;
    }

    return isMatching;
  }

  public String getElementText(WebElement e) {
    return e.getText();
  }

  public String getElementValue(WebElement e) {
    return e.getAttribute("value");
  }

  public void checkIfObjectTextExist(WebElement element, String strExpected, String strPass, String strFail, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);
    String strText = null;

    try {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      element = wait.until(ExpectedConditions.elementToBeClickable(element));
      strText = element.getText();

    } catch (Exception e) {
      System.out.println("Element " + element + " NOT Displayed.");
      repo.ExtentLogFail("Element : " + element + " Not Displayed Error: " + e.getMessage(), driver, test);
      driver.quit();

      return;
    }

    if (strText.contains(strExpected)) {
      repo.ExtentLogPass(strPass, driver, test);

    } else {
      repo.ExtentLogFail(strFail, driver, test);
      driver.quit();
    }

  }

  public boolean checkObjectTextFromList(List<WebElement> object, String objectToFind, String pMassage, String fMassage, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      for (WebElement webElement : object) {
        System.out.println(webElement.getText().replaceAll("\\s+", ""));
        if (webElement.getText().replaceAll("\\s+", "").equalsIgnoreCase(objectToFind.replaceAll("\\s+", ""))) {
          System.out.println("Object Text Located: " + webElement.getText().replaceAll("\\s+", ""));
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("Element " + object + " NOT found.");
      repo.ExtentLogFail(fMassage + " " + "Element :" + object + " Not Displayed Displayed Error: " + e.getMessage(), driver, test);

      return false;
    }

    repo.ExtentLogPass(pMassage, driver, test);
    return true;
  }

  public boolean clickObjectTextFromList(List<WebElement> object, String objectToFind, String pMassage, String fMassage, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      for (WebElement webElement : object) {
        System.out.println(webElement.getText().replaceAll("\\s+", ""));
        if (webElement.getText().replaceAll("\\s+", "").equalsIgnoreCase(objectToFind.replaceAll("\\s+", ""))) {
          System.out.println("Object Text Located: " + webElement.getText().replaceAll("\\s+", ""));
          webElement.click();
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

  public boolean clickObjectTextFromList(List<WebElement> object, String objectToFind, WebDriver driver, ExtentTest test) throws Exception {
    AngularWaits(driver);

    try {
      for (WebElement webElement : object) {
        System.out.println(webElement.getText().replaceAll("\\s+", ""));
        if (webElement.getText().replaceAll("\\s+", "").equalsIgnoreCase(objectToFind.replaceAll("\\s+", ""))) {
          System.out.println("Object Text Located: " + webElement.getText().replaceAll("\\s+", ""));
          webElement.click();
          break;
        }
      }

    } catch (Exception e) {
      System.out.println("Element " + object + " NOT found.");
      repo.ExtentLogFail("Element :" + object + " Not Displayed Displayed Error: " + e.getMessage(), driver, test);
      driver.quit();

      return false;
    }

    return true;
  }

  public boolean waitForElement(WebElement e, int timeOut, WebDriver driver) {
    AngularWaits(driver);

    try {
      Wait<WebDriver> wait = new WebDriverWait(driver, 30)
        .withTimeout(Duration.ofSeconds(timeOut))
        .pollingEvery(Duration.ofMillis(3000))
        .ignoring(WebDriverException.class);

      wait.until(ExpectedConditions.visibilityOf(e));

    } catch (TimeoutException exp) {
      exp.printStackTrace();

      return false;
    }

    return true;
  }

  public void waitForPageLoaded(WebDriver driver) {
    AngularWaits(driver);

    Wait<WebDriver> wait = new WebDriverWait(driver, 30);

    wait.until(new Function<WebDriver, Boolean>() {
      public Boolean apply(WebDriver driver) {
        System.out.println("Current Window State       : "
          + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));

        return String
          .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
          .equals("complete");
      }
    });
  }

  public void waitForLoad(WebDriver driver) {
    AngularWaits(driver);

    ExpectedCondition<Boolean> pageLoadCondition = driver1
      -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");

    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(pageLoadCondition);
  }

  public void switchToTab(int tab, WebDriver driver) throws Exception {
    AngularWaits(driver);
    Thread.sleep(2000);
    ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(allTabs.get(tab));
  }

  public boolean checkPrevioseElement(WebElement e, WebDriver driver) {
    AngularWaits(driver);

    try {
      WebDriverWait wait = new WebDriverWait(driver, 10);
      wait.until(ExpectedConditions.stalenessOf(e));

      return true;

    } catch (TimeoutException exception) {
      exception.printStackTrace();

      return false;
    }
  }

  public boolean waitForObjectInvisibility(WebElement e, int timeout, WebDriver driver) throws Exception {
    AngularWaits(driver);

    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      wait.until(ExpectedConditions.invisibilityOf(e));

      return true;

    } catch (TimeoutException exception) {
      exception.printStackTrace();

      return false;
    }
  }

  public void highlightElement(WebElement element, WebDriver driver) {
    AngularWaits(driver);

    for (int i = 0; i < 10; i++) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
        "color: blue; border: 6px solid blue;");
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
    }
  }

  public void scrollPageToElement(WebElement element, WebDriver driver) {
    AngularWaits(driver);
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("arguments[0].scrollIntoView();", element);
  }

  public boolean isAtCorrectPage(String title, WebDriver driver) {
    AngularWaits(driver);
    boolean isMatch = false;

    if (driver.getTitle().equalsIgnoreCase(title)) {
      isMatch = true;
      System.out.println("Title : " + title + " matches with the page");

    } else {
      System.out.println("Title : " + title + " does not matches with the page");
    }

    return isMatch;
  }

  public void scrollUpandDown(String strUporDown, WebDriver driver) {
    Actions action = new Actions(driver);

    if (strUporDown.toUpperCase().equals("DOWN")) {
      action.sendKeys(Keys.PAGE_DOWN).build().perform();

    } else {
      action.sendKeys(Keys.PAGE_UP).build().perform();
    }
  }

  public void openLinkInNewTab(String link, WebDriver driver) {
    AngularWaits(driver);
    String currentHandle = driver.getWindowHandle();
    ((JavascriptExecutor) driver).executeScript("window.open()");
    Set<String> handles = driver.getWindowHandles();

    for (String actual : handles) {
      if (!actual.equalsIgnoreCase(currentHandle)) {
        driver.switchTo().window(actual);
        driver.get(link);
      }
    }
  }

  public void switchToNewTab(WebDriver driver) {
    AngularWaits(driver);
    String currentHandle = driver.getWindowHandle();
    Set<String> handles = driver.getWindowHandles();

    for (String actual : handles) {
      if (!actual.equalsIgnoreCase(currentHandle)) {
        driver.switchTo().window(actual);
      }
    }
  }

  public String SAidentityNumberGenerator() {
    String StrID = "";
    String StrIDNumber = "";

    try {

      Random rand = new Random();
      int count = 0;
      int total = 0;
      int flag = 0;

      while (flag != 2) {
        String strDays = String.valueOf(10 + rand.nextInt(20));
        String strMonth = String.valueOf(10 + rand.nextInt(2));
        String strYear = String.valueOf(60 + rand.nextInt(40));
        String strGender = String.valueOf(4999 + rand.nextInt(4999));

        StrID = strYear + strMonth + strDays + strGender + "08";

        for (int i = 0; i < StrID.length(); i++) {
          int multiple = (count % 2) + 1;
          count++;
          int temp = multiple * Integer.parseInt(String.valueOf(StrID.charAt(i)));
          temp = (int) Math.floor(temp / 10) + (temp % 10);
          total += temp;
        }

        total = (total * 9) % 10;

        if (total == 1 || total == 2) {
          flag = 2;

          StrIDNumber = StrID + total;

        }
      }

    } catch (Exception e) {
      System.out.print(e.getMessage());
    }

    return StrIDNumber;
  }

  public String CheckGenderFromIdNumber(String strIdNumber) {
    try {
      String strgenderCode = strIdNumber.substring(6, 10);

      return Integer.parseInt(strgenderCode) < 5000 ? "Female" : "Male";

    } catch (NoAlertPresentException ex) {
      return "Invalid ID Number";
    }

  }

  public String generateRandomString(int length) {
    String generatedString = RandomStringUtils.random(length, true, false);
    System.out.println(generatedString);

    return generatedString;
  }

  public String generateRandomValue(int length) {
    String generatedValue = RandomStringUtils.random(length, false, true);
    System.out.println(generatedValue);

    return generatedValue;
  }

  public void UploadDocuments(WebElement upload, String FilePath, WebDriver webDriver, ExtentTest test) throws Exception {
    AngularWaits(webDriver);

    try {
      Thread.sleep(1000);
      upload.sendKeys(System.getProperty("user.dir") + "/testData/testingDocuments/" + FilePath);
      Thread.sleep(2000);

    } catch (Exception e) {
      repo.ExtentLogFail("Failed to Complete Upload Document Test Exception : " + e.getMessage(), webDriver, test);
    }
  }

  public void UploadDocuments2(String FilePath, WebDriver webDriver, ExtentTest test) throws Exception {
    try {
      StringSelection s = new StringSelection(System.getProperty("user.dir") + "/testData/testingDocuments/" + FilePath);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
      Robot robot = new Robot();
      Thread.sleep(2000);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.delay(200);
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.delay(200);
      robot.keyPress(KeyEvent.VK_META);
      robot.delay(200);
      robot.keyPress(KeyEvent.VK_SHIFT);
      robot.delay(200);
      robot.keyPress(KeyEvent.VK_G);
      robot.delay(200);
      robot.keyRelease(KeyEvent.VK_META);
      robot.delay(200);
      robot.keyRelease(KeyEvent.VK_SHIFT);
      robot.delay(200);
      robot.keyRelease(KeyEvent.VK_G);
      robot.delay(200);
      robot.keyPress(KeyEvent.VK_META);
      robot.delay(200);
      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_META);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_ENTER);
      Thread.sleep(1000);
      robot.keyPress(KeyEvent.VK_ENTER);

    } catch (Exception e) {
      repo.ExtentLogFail("Failed to Complete Upload Document Test Exception : " + e.getMessage(), webDriver, test);
    }

  }

  public void setClipboardData(String string) {
    StringSelection stringSelection = new StringSelection(string);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
  }

  public void uploadFile(String fileLocation, String OSName, Robot r) {
    try {
      setClipboardData("C:\\Users\\Bongs.Lushaba\\IdeaProjects\\lmsautomation\\testData\\testingDocuments\\40532243_V10_072021_ML_01.zip");
      Thread.sleep(3000);

      if (OSName.contains("Mac OS X")) {
        r.keyPress(KeyEvent.VK_META);
        r.delay(2000);
        r.keyPress(KeyEvent.VK_V);
        r.delay(2000);
        r.keyRelease(KeyEvent.VK_V);
        r.delay(2000);
        r.keyRelease(KeyEvent.VK_META);
        r.delay(2000);
        PressEnter(2);

      } else {
        try {
          Runtime.getRuntime().exec(System.getProperty("user.dir")
            + "\\autoit\\fileUpload.exe" + " "
            + "C:\\Users\\Bongs.Lushaba\\IdeaProjects\\lmsautomation\\testData\\testingDocuments\\40534013_V10_072021_ML_01.zip");

        } catch (Exception e) {
          System.out.println("Error - Upload Image:\n" + e.getMessage());
        }
      }

    } catch (Exception exp) {
      exp.printStackTrace();
    }
  }

  public void PressEnter(int iteration) throws InterruptedException, AWTException {
    int i = 1;

    while (i <= iteration) {
      Thread.sleep(1000);
      Robot robot = new Robot();
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.delay(2000);
      robot.keyRelease(KeyEvent.VK_ENTER);
      i++;
    }
  }

  public void uploadImage(WebDriver driver) {
    AngularWaits(driver);

    try {
      Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\autoit\\fileUpload.exe");

    } catch (Exception e) {
      System.out.println("Error - Upload Image:\n" + e.getMessage());
    }
  }

  public void uploadImage(String filepath) {
    try {
      String OSName = System.getProperty("os.name");
      Thread.sleep(5000);
      Robot robot = new Robot();

      if (OSName.contains("Mac OS X")) {
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_META);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_G);
        robot.delay(2000);
        robot.keyRelease(KeyEvent.VK_G);
        robot.delay(2000);
        robot.keyRelease(KeyEvent.VK_META);
        robot.delay(2000);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.delay(2000);
      }

      uploadFile(filepath, OSName, robot);

    } catch (Exception e) {
      System.out.println("Error - LogoutUser:\n" + e.getMessage());
    }
  }

  public Boolean isFileDownloaded(String fileName, String directory) {
    boolean flag = false;
    String dirPath = directory;
    File dir = new File(dirPath);
    File[] files = dir.listFiles();

    if (files.length == 0 || files == null) {
      System.out.println("The directory is empty");
      flag = false;

    } else {
      for (File listFile : files) {
        if (listFile.getName().contains(fileName)) {
          System.out.println(fileName + " is present");
          break;
        }
        flag = true;
      }
    }

    return flag;
  }

  public void AngularWaits(WebDriver driver) {
    AngularWaits allwaits = new AngularWaits();
    AngularWaits.setDriver(driver);
    allwaits.waitAllRequest();
  }

  public void switchToFrameNest(WebElement element, WebDriver driver) throws InterruptedException {
    AngularWaits(driver);
    WebElement iframeElement = element;
    driver.switchTo().frame(iframeElement);
    Thread.sleep(2000);
  }

  public void moveToElements(WebElement element, WebDriver driver) throws InterruptedException {
    AngularWaits(driver);
    if (element.isDisplayed()) {
      Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(5))
        .pollingEvery(Duration.ofMillis(1000))
        .ignoring(WebDriverException.class);
      wait.until(ExpectedConditions.elementToBeClickable(element));
      Actions action = new Actions(driver);
      action.moveToElement(element).click().perform();
    }
  }
}
