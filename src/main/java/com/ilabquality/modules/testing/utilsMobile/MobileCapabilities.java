package com.ilabquality.modules.testing.utilsMobile;

import com.ilabquality.modules.testing.BaseTest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MobileCapabilities extends BaseTest {

  public void setSMSCaps(MobileSpecific mobSpec, String DeviceName) throws FileNotFoundException {

    JSONParser parser = new JSONParser();
    FileReader fr;

    if (DeviceName.equalsIgnoreCase("SUMSUNG")) {
      fr = new FileReader(new File(ROOT_PATH + "/conf/SumsungDevice_SMS_Package.json"));

    } else {
      fr = new FileReader(new File(ROOT_PATH + "/conf/HuaweiDevice_SMS_Package.json"));
    }

    try {
      Object obj = parser.parse(fr);
      JSONObject jsonObject = (JSONObject) obj;

      mobSpec.setUDID((String) jsonObject.get("UDID"));
      mobSpec.setPLATFORM_NAME((String) jsonObject.get("PLATFORM_NAME"));
      mobSpec.setAPP_PACKAGE((String) jsonObject.get("APP_PACKAGE"));
      mobSpec.setAUTOMATION_NAME((String) jsonObject.get("AUTOMATION_NAME"));
      mobSpec.setDEVICE_NAME((String) jsonObject.get("DEVICE_NAME"));
      mobSpec.setAPP_ACTIVITY((String) jsonObject.get("APP_ACTIVITY"));
      mobSpec.setNO_SIGN((String) jsonObject.get("NO_SIGN"));
      mobSpec.setNO_RESET((String) jsonObject.get("NO_RESET"));

    } catch (Exception e) {
      System.out.println("Error Initialising SMS Capabilities : " + e.getMessage());
    }
  }

  public void setBrowserCaps(MobileSpecific mobSpec, String DeviceName) throws FileNotFoundException {

    JSONParser parser = new JSONParser();
    FileReader fr;

    if (DeviceName.equalsIgnoreCase("SUMSUNG")) {
      fr = new FileReader(new File(ROOT_PATH + "/conf/SumsungDevice_GoogleChrome_Package.json"));

    } else {
      fr = new FileReader(new File(ROOT_PATH + "/conf/Huawei_GoogleChrome_Package.json"));
    }

    try {
      Object obj = parser.parse(fr);
      JSONObject jsonObject = (JSONObject) obj;

      mobSpec.setUDID((String) jsonObject.get("UDID"));
      mobSpec.setPLATFORM_NAME((String) jsonObject.get("PLATFORM_NAME"));
      mobSpec.setAUTOMATION_NAME((String) jsonObject.get("AUTOMATION_NAME"));
      mobSpec.setDEVICE_NAME((String) jsonObject.get("DEVICE_NAME"));
      mobSpec.setBROWSER_NAME((String) jsonObject.get("BROWSER_NAME"));
      mobSpec.setNO_SIGN((String) jsonObject.get("NO_SIGN"));
      mobSpec.setNO_RESET((String) jsonObject.get("NO_RESET"));

    } catch (Exception e) {
      System.out.println("Error Initialising Android Browser Capabilities : " + e.getMessage());
    }
  }

  public void setDailer(MobileSpecific mobSpec, String DeviceName) throws FileNotFoundException {
    JSONParser parser = new JSONParser();
    FileReader fr;

    if (DeviceName.equalsIgnoreCase("SUMSUNG")) {
      fr = new FileReader(new File(ROOT_PATH + "/conf/SumsungDevice_Dialer_Package.json"));

    } else {
      fr = new FileReader(new File(ROOT_PATH + "/conf/Huawei_GoogleChrome_Package.json"));
    }

    try {
      Object obj = parser.parse(fr);
      JSONObject jsonObject = (JSONObject) obj;
      mobSpec.setUDID((String) jsonObject.get("UDID"));
      mobSpec.setPLATFORM_NAME((String) jsonObject.get("PLATFORM_NAME"));
      mobSpec.setAPP_PACKAGE((String) jsonObject.get("APP_PACKAGE"));
      mobSpec.setAUTOMATION_NAME((String) jsonObject.get("AUTOMATION_NAME"));
      mobSpec.setDEVICE_NAME((String) jsonObject.get("DEVICE_NAME"));
      mobSpec.setAPP_ACTIVITY((String) jsonObject.get("APP_ACTIVITY"));
      mobSpec.setNO_SIGN((String) jsonObject.get("NO_SIGN"));
      mobSpec.setNO_RESET((String) jsonObject.get("NO_RESET"));

    } catch (Exception e) {
      System.out.println("Before Test Error : " + e.getMessage());
    }
  }

  public void setCapabilitiesIOS(IOSSpecific mobSpec) throws FileNotFoundException {

    JSONParser parser = new JSONParser();
    FileReader fr = new FileReader(new File(ROOT_PATH + "/conf/iOSDevice_VideoPlay_Package.json"));

    try {
      Object obj = parser.parse(fr);
      JSONObject jsonObject = (JSONObject) obj;

      mobSpec.setStrUDID((String) jsonObject.get("UDID"));
      mobSpec.setPLATFORM_NAME((String) jsonObject.get("PLATFORM_NAME"));
      mobSpec.setPLATFORM_VERSION((String) jsonObject.get("PLATFORM_VERSION"));
      mobSpec.setAUTOMATION_NAME((String) jsonObject.get("AUTOMATION_NAME"));
      mobSpec.setDEVICE_NAME((String) jsonObject.get("DEVICE_NAME"));
      mobSpec.setXCODE_ORG_ID((String) jsonObject.get("XCODE_ORG_ID"));
      mobSpec.setXCODE_SIGNING_ID((String) jsonObject.get("XCODE_SIGNING_ID"));
      mobSpec.setBUNDLE_ID((String) jsonObject.get("BUNDLE_ID"));

    } catch (Exception e) {
      System.out.println("Before Test Error : " + e.getMessage());
    }
  }

}
