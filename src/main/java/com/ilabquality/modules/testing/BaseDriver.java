package com.ilabquality.modules.testing;

import com.ilabquality.modules.global.reference.SystemConstant;
import com.ilabquality.modules.global.reference.SystemDefault;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BaseDriver extends SystemConstant {
  protected static ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<>();
  protected static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();
  protected static SystemDefault defaults = SystemDefault.getInstance();
  private static final BaseContext context = BaseContext.getInstance();
  public static String remote_url;

  public BaseDriver() {
    if (defaults.USE_SELENIUM_PROXY) {
      remote_url = defaults.SELENIUM_URI_PROXY;
    } else {
      remote_url = defaults.SELENIUM_URI;
    }
  }

  // REMOTE_DRIVER: The remote driver is the webdriver running on a Dockerized Selenium instance
  public ThreadLocal<RemoteWebDriver> configureRemoteDriver(String browserName) throws MalformedURLException {
    System.out.println("[EXECUTOR] [INFO] Browser " + browserName);
    System.out.println("[EXECUTOR] [INFO] Driver URL " + remote_url);
    System.out.println("[EXECUTOR] [INFO] Selenium is container based " + context.cloudDeployment);

    if (browserName.equalsIgnoreCase("chrome")) {

      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

      RemoteWebDriver webDriver = new RemoteWebDriver(new URL(remote_url), chromeOptions);
      webDriver.setLogLevel(Level.FINEST);
      remoteDriver.set(webDriver);

    } else if (browserName.equalsIgnoreCase("firefox")) {

      FirefoxOptions firefoxOptions = new FirefoxOptions();
      firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

      RemoteWebDriver webDriver = new RemoteWebDriver(new URL(remote_url), firefoxOptions);
      webDriver.setLogLevel(Level.FINEST);
      remoteDriver.set(webDriver);

    } else if (browserName.equalsIgnoreCase("edge")) {

      EdgeOptions firefoxOptions = new EdgeOptions();
      firefoxOptions.setPageLoadStrategy(String.valueOf(PageLoadStrategy.NONE));

      RemoteWebDriver webDriver = new RemoteWebDriver(new URL(remote_url), firefoxOptions);
      webDriver.setLogLevel(Level.FINEST);
      remoteDriver.set(webDriver);
    }

    return remoteDriver;
  }

  // WEB_DRIVER: The webdriver is a normal webdriver running on a local instance of Selenium
  public WebDriver getWebdriver() {
    if (context.cloudDeployment) {
      return remoteDriver.get();
    } else {
      return localDriver.get();
    }
  }

  public static ThreadLocal<WebDriver> configureLocalDriver(String browserName) {
    try {
      String operatingSystem = System.getProperty("os.name");

      switch (browserName.toUpperCase()) {
        case "CHROME":
          if (operatingSystem.contains("Win")) {
            System.setProperty("webdriver.chrome.silentOutput", "true");
          }

          ChromeOptions ChromeOption = new ChromeOptions();
          LoggingPreferences logs = new LoggingPreferences();

          ChromeOption.addArguments("--remote-debugging-port=9222");
          ChromeOption.addArguments("--disable-notifications");
          ChromeOption.setPageLoadStrategy(PageLoadStrategy.NONE);
          ChromeOption.setExperimentalOption("useAutomationExtension", false);
          ChromeOption.addArguments("Zoom 80%");
          ChromeOption.addArguments("user-data-dir=" + ROOT_PATH + "/chromeprofile");

          WebDriver webDriver = new ChromeDriver(ChromeOption);
          webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
          webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
          webDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
          webDriver.manage().window().maximize();

          localDriver.set(webDriver);
          break;

        case "MOBILECHROME":
          if (operatingSystem.contains("Win")) {
            System.setProperty("webdriver.chrome.driver", ROOT_PATH + "/drivers/chromedriver.exe");
          } else {
            System.setProperty("webdriver.chrome.driver", ROOT_PATH + "/drivers/chromedriver");
          }
          Map<String, String> mobileEmulation = new HashMap<>();
          mobileEmulation.put("deviceName", "Pixel 2");
          ChromeOptions chromeOptions = new ChromeOptions();
          chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
          WebDriver mobileWebDriver = new ChromeDriver(chromeOptions);

          localDriver.set(mobileWebDriver);
          break;
      }

    } catch (Exception e) {
      System.out.print(e.getMessage());
    }

    return localDriver;
  }

  public WebDriver configureDriver(String browser) throws MalformedURLException {
    if (context.cloudDeployment) {
      return configureRemoteDriver(browser).get();

    } else {
      return configureLocalDriver(browser).get();
    }
  }


  private boolean driverHasQuit() {
    WebDriver driver;

    if (context.cloudDeployment) {
      driver = remoteDriver.get();

    } else {
      driver = localDriver.get();
    }

    return driver == null;
  }

  public void quitDriver() {
    boolean quitted = driverHasQuit();

    if (!quitted) {
      if (context.cloudDeployment) {
        remoteDriver.get().quit();
        remoteDriver.remove();

      } else {
        localDriver.get().quit();
        localDriver.remove();
      }
    }
  }

  public String getTimeStampCustom() {
    Date now = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(now);
    calendar.add(Calendar.DATE, 1);
    now = calendar.getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

    return formatter.format(now);
  }
}
