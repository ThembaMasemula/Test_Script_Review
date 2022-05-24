package com.ilabquality.modules.testing.utilsMobile;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;

public class MobileDataCapabilities {

  public static DesiredCapabilities setSMSCaps(MobileSpecific mobilecaps) throws FileNotFoundException {

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.UDID, mobilecaps.getUDID());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilecaps.getPLATFORM_NAME());
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, mobilecaps.getAUTOMATION_NAME());
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobilecaps.getDEVICE_NAME());
    capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, mobilecaps.getAPP_PACKAGE());
    capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, mobilecaps.getAPP_ACTIVITY());
    capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, mobilecaps.getNO_SIGN());
    capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
    capabilities.acceptInsecureCerts();
    capabilities.setCapability(MobileCapabilityType.NO_RESET, mobilecaps.getNO_RESET());
    capabilities.setCapability("newCommandTimeout", 5000);
    return capabilities;
  }

  public static DesiredCapabilities setBrowserCaps(MobileSpecific mobilecaps) throws FileNotFoundException {

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.UDID, mobilecaps.getUDID());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilecaps.getPLATFORM_NAME());
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, mobilecaps.getAUTOMATION_NAME());
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobilecaps.getDEVICE_NAME());
    capabilities.setCapability(AndroidMobileCapabilityType.BROWSER_NAME, mobilecaps.getBROWSER_NAME());
    capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, mobilecaps.getNO_SIGN());
    capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
    capabilities.acceptInsecureCerts();
    capabilities.setCapability(MobileCapabilityType.NO_RESET, mobilecaps.getNO_RESET());
    capabilities.setCapability("newCommandTimeout", 5000);
    return capabilities;
  }

  public static DesiredCapabilities setDialerCapabilitiesAndroid(MobileSpecific mobilecaps) throws FileNotFoundException {

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.UDID, mobilecaps.getUDID());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilecaps.getPLATFORM_NAME());
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, mobilecaps.getAUTOMATION_NAME());
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobilecaps.getDEVICE_NAME());
    capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, mobilecaps.getAPP_PACKAGE());
    capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, mobilecaps.getAPP_ACTIVITY());
    capabilities.setCapability(AndroidMobileCapabilityType.NO_SIGN, mobilecaps.getNO_SIGN());
    capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
    capabilities.acceptInsecureCerts();
    capabilities.setCapability(MobileCapabilityType.NO_RESET, mobilecaps.getNO_RESET());
    capabilities.setCapability("newCommandTimeout", 2000);
    return capabilities;
  }

  public static DesiredCapabilities setVideoPlayCapabilitiesIOS(IOSSpecific mobilecaps) throws FileNotFoundException {

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.UDID, mobilecaps.getStrUDID());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, mobilecaps.getPLATFORM_NAME());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, mobilecaps.getPLATFORM_VERSION());
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, mobilecaps.getAUTOMATION_NAME());
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, mobilecaps.getDEVICE_NAME());
    capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, mobilecaps.getXCODE_ORG_ID());
    capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, mobilecaps.getXCODE_SIGNING_ID());
    capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, mobilecaps.getBUNDLE_ID());
    capabilities.acceptInsecureCerts();
    capabilities.setCapability("newCommandTimeout", 2000);
    return capabilities;
  }


}
