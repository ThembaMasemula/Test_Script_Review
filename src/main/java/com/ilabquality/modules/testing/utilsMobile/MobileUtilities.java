package com.ilabquality.modules.testing.utilsMobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class MobileUtilities {

  protected static AndroidDriver mobileDriver;
  static AppiumDriverLocalService service;

  public void setMobileDriver(AndroidDriver AndroidTest) {
    this.mobileDriver = AndroidTest;
  }

  public static AndroidDriver getMobileDiver() {
    return mobileDriver;
  }

  public void closeMobileDriver() {
    mobileDriver.close();
  }

  public String appiumStartServer() throws Exception {
    String serviceUrl = null;
    try {

      AppiumServiceBuilder builder = new AppiumServiceBuilder().usingAnyFreePort();
      builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
      builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
      service = AppiumDriverLocalService.buildService(builder);

      if (service.isRunning()) {
        service.stop();
        Thread.sleep(2000);
        service.start();
        Thread.sleep(2000);

      } else {
        service.start();
      }

      serviceUrl = service.getUrl().toString();

    } catch (Exception exp) {
      System.out.println("Error Initialising Appium Server : " + exp.getMessage());
    }

    return serviceUrl;
  }

  public void appiumStoptServer() throws Exception {
    service.stop();
  }
}
