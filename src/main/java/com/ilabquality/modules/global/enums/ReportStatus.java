package com.ilabquality.modules.global.enums;

public enum ReportStatus  {
  CREATED, SUCCESS, FAILURE, SKIP, SUCCESS_PERCENTAGE_FAILURE, STARTED;

  public static ReportStatus getStatusString (String statusCode) {
    switch(statusCode) {
      case "-1":
        return CREATED;
      case "1":
        return SUCCESS;
      case "2":
        return FAILURE;
      case "3":
        return SKIP;
      case "4":
        return SUCCESS_PERCENTAGE_FAILURE;
      case "16":
        return STARTED;
      default:
        return null;
    }
  }
}
