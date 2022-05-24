package com.ilabquality.modules.testing.utilsMobile;

public class MobileSpecific {

  private String UDID;
  private String PLATFORM_NAME;
  private String AUTOMATION_NAME;
  private String DEVICE_NAME;
  private String NO_RESET;
  private String NO_SIGN;
  private String BROWSER_NAME;
  private String PACKAGE;
  private String ACTIVITY;

  public String getUDID() {
    return UDID;
  }

  public void setUDID(String UDID) {
    this.UDID = UDID;
  }

  public String getNO_RESET() {
    return NO_RESET;
  }

  public void setNO_RESET(String NO_RESET) {
    this.NO_RESET = NO_RESET;
  }

  public String getNO_SIGN() {
    return NO_SIGN;
  }

  public void setNO_SIGN(String NO_SIGN) {
    this.NO_SIGN = NO_SIGN;
  }

  public String getPLATFORM_NAME() {
    return PLATFORM_NAME;
  }

  public void setPLATFORM_NAME(String PLATFORM_NAME) {
    this.PLATFORM_NAME = PLATFORM_NAME;
  }

  public String getAUTOMATION_NAME() {
    return AUTOMATION_NAME;
  }

  public void setAUTOMATION_NAME(String AUTOMATION_NAME) {
    this.AUTOMATION_NAME = AUTOMATION_NAME;
  }

  public String getDEVICE_NAME() {
    return DEVICE_NAME;
  }

  public void setDEVICE_NAME(String DEVICE_NAME) {
    this.DEVICE_NAME = DEVICE_NAME;
  }

  public String getBROWSER_NAME() {
    return BROWSER_NAME;
  }

  public void setBROWSER_NAME(String BROWSER_NAME) {
    this.BROWSER_NAME = BROWSER_NAME;
  }

  public String getAPP_PACKAGE() {
    return PACKAGE;
  }

  public void setAPP_PACKAGE(String PACKAGE) {
    this.PACKAGE = PACKAGE;
  }

  public String getAPP_ACTIVITY() {
    return ACTIVITY;
  }

  public void setAPP_ACTIVITY(String ACTIVITY) {
    this.ACTIVITY = ACTIVITY;
  }
}
