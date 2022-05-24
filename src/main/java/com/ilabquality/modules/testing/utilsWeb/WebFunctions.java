package com.ilabquality.modules.testing.utilsWeb;

import com.aventstack.extentreports.ExtentTest;

import com.ilabquality.modules.testing.BaseTest;
import com.ilabquality.modules.testing.utils.AngularWaits;
import com.ilabquality.modules.testing.utilsPage.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class WebFunctions extends BaseTest {

  public void LoginSuccessfactors(WebDriver webDriver, ExtentTest test) throws Exception {
    Login loginObject = new Login(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webAction.enterText(loginObject.txtusername, USER_NAME);
      webAction.enterText(loginObject.txtpassword, PASSWORD);

      Thread.sleep(3000);

      webAction.clickAction(loginObject.btnLogin, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass(" Login Successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to Login : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToLMS(WebDriver webDriver, ExtentTest test) throws Exception {
    NavigateToTheLMSPage NavigateToLMSObject = new NavigateToTheLMSPage(webDriver);
    ContentNavigation NavigationObject = new ContentNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webAction.clickObject(NavigateToLMSObject.lnkLMS, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Navigation To Learning Successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Learning : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddAssignmentProfileToLibrary(WebDriver webDriver, ExtentTest test) throws Exception {
    CreateLibrary libraryObject = new CreateLibrary(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();

      Thread.sleep(3000);

      webAction.clickObject(libraryObject.lnkAssignmentProfilesTab, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Assignment profile tab clicked Successfully : ", webDriver, test);

      webAction.clickObject(libraryObject.lnkAddProfile, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Plus button clicked Successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(2000);

      webAction.clickObject(libraryObject.btnCriteria, webDriver, test);

      Thread.sleep(3000);

      WebElement iframeElement1 = webDriver.findElement(By.xpath("//iframe[contains(@src,'/learning/search/body_criteria_chooser.jsp?stackID=list')]"));
      webDriver.switchTo().frame(iframeElement1);

      Thread.sleep(3000);

      if (!libraryObject.chkAssignmentProfileID.isSelected()) {

        webAction.checkBox(libraryObject.chkAssignmentProfileID, webDriver, test);

        Thread.sleep(2000);
      }
      webAction.clickObject(libraryObject.btnSelect, webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(2000);

      webAction.enterText(libraryObject.txtDescKeywordID, TEST_DATA_MAP.get("Assignment Profile ID"));
      webAction.clickObject(libraryObject.btnSearchKeyword, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Search assignment profile button clicked Successfully : ", webDriver, test);

      webAction.checkBox(libraryObject.chkAdd, webDriver, test);
      webAction.clickObject(libraryObject.btnAddProfile, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Add assignment profile button clicked Successfully : ", webDriver, test);

      webDriver.switchTo().defaultContent();
      Thread.sleep(5000);

      webAction.clickAction(libraryObject.lnkDescription, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Assignment profile description clicked Successfully : ", webDriver, test);

      String assignmentStatus = webAction.getElementText(libraryObject.lblStatus2);

      if (assignmentStatus.contains("Execute")) {
        reportInstance.ExtentLogPass("Assignment Profile status is : " + assignmentStatus, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Assignment Profile status does not match", webDriver, test);
      }

      Thread.sleep(1000);

      webAction.clickAction(libraryObject.lnkRulesTab, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Rules tab clicked Successfully : ", webDriver, test);
      webAction.clickObject(libraryObject.btnDownloadPreview, webDriver, test);

      Thread.sleep(3000);

      webAction.isFileDownloaded(" Assignment Profile_Preview Users", System.getProperty("user.home") + "/Downloads");

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Assignment Profile file downloaded Successfully : ", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(libraryObject.btnActions, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(libraryObject.lnkExecuteChanges, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Execute Changes tab clicked Successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeExChanges = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeExChanges);
      webAction.waitForPageLoaded(webDriver);

      Thread.sleep(3000);

      webAction.clickObject(libraryObject.ChkNotifyEmail, webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(libraryObject.btnFinish, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Finish button clicked Successfully : ", webDriver, test);

      Thread.sleep(4000);

      webAction.clickObject(libraryObject.btnBackToAssignmentProfile, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Back To Assignment Profile button clicked Successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      String assignmentStatus2 = webAction.getElementText(libraryObject.lblStatus2);

      if (assignmentStatus2.contains("Valid")) {
        reportInstance.ExtentLogPass("Assignment Profile status is : " + assignmentStatus2, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Assignment Profile status does not match", webDriver, test);
      }

      Thread.sleep(1000);

      reportInstance.ExtentLogPass(" Assignment Profile added to a library successfully : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add an assignment profile to a library : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToMyLearning(WebDriver webDriver, ExtentTest test) throws Exception {
    NavigateToTheLMSPage NavigateToLMSObject = new NavigateToTheLMSPage(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webAction.clickObject(NavigateToLMSObject.lnkLearning, webDriver, test);

      Thread.sleep(8000);

      reportInstance.ExtentLogPass("Navigation To Learning Successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Learning : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToCohorts(WebDriver webDriver, ExtentTest test) throws Exception {
    PeopleNavigation PeopleObject = new PeopleNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(6000);

      webAction.clickObject(PeopleObject.lnkPeople, webDriver, test);
      webAction.clickObject(PeopleObject.lnkCohorts, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Navigation To Cohorts Successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Navigation to Cohorts not Successful: " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToImport(WebDriver webDriver, ExtentTest test) throws Exception {
    ImportContentNavigation ImportObject = new ImportContentNavigation(webDriver);
    ContentNavigation NavigationObject = new ContentNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(6000);

      webAction.clickObject(NavigationObject.lnkContent, webDriver, test);
      webAction.clickObject(NavigationObject.lnkImportContent, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Navigation To Import content Page Successful : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(6000);

      webAction.clickObject(ImportObject.btnNext, webDriver, test);
      reportInstance.ExtentLogPass("Navigation To Import content Successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Navigation to Import Content  not Successful: " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToTasks(WebDriver webDriver, ExtentTest test) throws Exception {
    try {

      ContentNavigation NavigationObject = new ContentNavigation(webDriver);
      webAction.clickObject(NavigationObject.lnkContent, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Content link successfully clicked: ", webDriver, test);
      webAction.clickObject(NavigationObject.lnkTasks, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Navigation to tasks Successful: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Navigation to tasks Unsuccessful: " + e.getMessage(), webDriver, test);
    }

  }

  public void SelectFiles(WebDriver webDriver, ExtentTest test) throws Exception {
    SelectFiles SelectFilesObject = new SelectFiles(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(5000);

      webAction.waitForElement(SelectFilesObject.btnBrowse, 10, webDriver);
      webAction.clickObject(SelectFilesObject.btnBrowse, webDriver, test);
      webAction.uploadImage(System.getProperty("user.dir") + "/testData/testingDocuments/" + TEST_DATA_MAP.get("UploadDocumentName"));

      Thread.sleep(4000);

      webAction.clickObject(SelectFilesObject.btnNextImport, webDriver, test);
      reportInstance.ExtentLogPass("Select Files to import Content Successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Select Files to import Content Unsuccessful: ", webDriver, test);
    }
  }

  public String ImportContent(WebDriver webDriver, ExtentTest test) throws Exception {
    ConfigureContent ConfigureObject = new ConfigureContent(webDriver);
    webAction.waitForPageLoaded(webDriver);
    String ContentPakageID = null;

    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);
      webAction.waitForElement(ConfigureObject.selServerLocation, 10, webDriver);
     /* webAction.SelectByVisibleText(ConfigureObject.selServerLocation, TEST_DATA_MAP.get("ServerLocation"), webDriver, test); */
      webAction.enterText(ConfigureObject.txtSecurityDomain, TEST_DATA_MAP.get("SecurityDomainID"));

      if (TEST_DATA_MAP.get("ContentPackageID").equalsIgnoreCase("AUTO")) {
        ContentPakageID = TEST_DATA_MAP.get("ContentPackageID") + webAction.generateRandomString(5);
      } else {
        ContentPakageID = TEST_DATA_MAP.get("ContentPackageID");
      }

      webAction.enterText(ConfigureObject.txtContentPackage, ContentPakageID);
      webAction.scrollPageToElement(ConfigureObject.chkItems, webDriver);
      webAction.clickObject(ConfigureObject.chkItems, webDriver, test);
      webAction.SelectByVisibleText(ConfigureObject.selItemType, TEST_DATA_MAP.get("ItemType"), webDriver, test);
      webAction.clickObject(ConfigureObject.chkAutoGenerateItemID, webDriver, test);

      if (TEST_DATA_MAP.get("ItemID").equalsIgnoreCase("AUTO")) {
        webAction.enterText(ConfigureObject.txtItemID, TEST_DATA_MAP.get("ItemID") + webAction.generateRandomString(5));
      } else {
        webAction.enterText(ConfigureObject.txtItemID, TEST_DATA_MAP.get("ItemID"));
      }

      webAction.scrollPageToElement(ConfigureObject.txtItemTitle, webDriver);

      if (TEST_DATA_MAP.get("ItemTitle").equalsIgnoreCase("AUTO")) {
        webAction.enterText(ConfigureObject.txtItemTitle, TEST_DATA_MAP.get("ItemTitle") + webAction.generateRandomString(5));
      } else {
        webAction.enterText(ConfigureObject.txtItemTitle, TEST_DATA_MAP.get("ItemTitle"));
      }

      webAction.SelectByVisibleText(ConfigureObject.selAssignmentType, TEST_DATA_MAP.get("AssignmentType"), webDriver, test);
      webAction.enterText(ConfigureObject.txtSecurityDomainID, TEST_DATA_MAP.get("SecurityDomainID"));
      webAction.clickObject(ConfigureObject.chkAddToHistory, webDriver, test);
      webAction.scrollPageToElement(ConfigureObject.btnImportFiles, webDriver);
      webAction.SelectByVisibleText(ConfigureObject.selCompletionStatus, TEST_DATA_MAP.get("CompletionStatus"), webDriver, test);
      AngularWaits.setDriver(webDriver);
      reportInstance.ExtentLogPass("import Content page Successfully Populated : ", webDriver, test);
      webAction.clickObject(ConfigureObject.btnImportFiles, webDriver, test);
      reportInstance.ExtentLogPass("import Content Successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("import Content Unsuccessful: ", webDriver, test);
    }

    return ContentPakageID;
  }

  public String ImportSummary(WebDriver webDriver, ExtentTest test) throws Exception {
    String sStatus = null;
    ImportSummary ImportSummaryObject = new ImportSummary(webDriver);
    webAction.waitForPageLoaded(webDriver);
    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);

      int i;
      boolean boolStatus = false;
      for (i = 0; i < 30; i++) {
        Thread.sleep(5000);
        webAction.waitForElement(ImportSummaryObject.brProgressiveBar, 20, webDriver);
        boolStatus = webAction.checkIfObjectIsDisplayed(ImportSummaryObject.brProgressiveBar, 10, webDriver);

        if (!boolStatus) {
          boolStatus = false;
          break;
        }

        Thread.sleep(5000);
      }

      if (!boolStatus) {
        String sText = webAction.getElementText(ImportSummaryObject.lblStatus);

        if (!sText.equalsIgnoreCase("Fail")) {
          webAction.clickObject(ImportSummaryObject.btnScheduleJobs, webDriver, test);
          webAction.waitForElement(ImportSummaryObject.txtContactEmailAddress, 10, webDriver);
          webAction.enterText(ImportSummaryObject.txtContactEmailAddress, TEST_DATA_MAP.get("ContactEmailAddress"));
          webAction.clickObject(ImportSummaryObject.btnFinish, webDriver, test);

          for (i = 0; i < 30; i++) {
            webAction.waitForElement(ImportSummaryObject.lblStatus2, 20, webDriver);
            sStatus = webAction.getElementText(ImportSummaryObject.lblStatus2);

            if (sStatus.equalsIgnoreCase("Succeeded")) {
              break;
            } else {
              Thread.sleep(10000);
            }
          }
          if (sStatus.equalsIgnoreCase("Succeeded")) {
            reportInstance.ExtentLogPass("Background Job Scheduled Successful : ", webDriver, test);
          } else {
            reportInstance.ExtentLogFail("Background Job Scheduled Unsuccessful: ", webDriver, test);
          }
        }
      }

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Background Job Scheduled uSuccessful: ", webDriver, test);
    }

    return sStatus;
  }

  public void SearchContentObject(WebDriver webDriver, ExtentTest test, String ContentID) throws Exception {
    SearchContentObjects SearchObject = new SearchContentObjects(webDriver);
    ContentNavigation NavigationObject = new ContentNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(5000);

      webAction.clickObject(NavigationObject.lnkContent, webDriver, test);
      webAction.clickObject(NavigationObject.lnkContentObjects, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Successfully Navigated to Content Objects", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);
      webAction.enterText(SearchObject.txtSearchContentID, ContentID);
      webAction.clickObject(SearchObject.btnSearch, webDriver, test);
      webAction.checkIfObjectIsDisplayed(SearchObject.tblresults, 5, webDriver);
      reportInstance.ExtentLogPass("Names of content objects displayed Successfully", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Names of content objects not displayed Successfully", webDriver, test);
    }
  }

  public String SearchContentPackages(WebDriver webDriver, ExtentTest test, String ContentID) throws Exception {
    String sObjectShortID = null;
    ContentPackages SearchObject = new ContentPackages(webDriver);
    ContentNavigation NavigationObject = new ContentNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(5000);

      webAction.clickObject(NavigationObject.lnkContent, webDriver, test);
      webAction.clickObject(NavigationObject.lnkContetPackages, webDriver, test);
      reportInstance.ExtentLogPass("Successfully Navigated to Content Packages", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);
      webAction.enterText(SearchObject.txtSearchContentID, ContentID);
      webAction.clickObject(SearchObject.btnSearch, webDriver, test);
      webAction.waitForElement(SearchObject.edtContent, 10, webDriver);
      reportInstance.ExtentLogPass("Successfully Searched to Content Packages", webDriver, test);
      webAction.clickObject(SearchObject.edtContent, webDriver, test);
      webAction.waitForElement(SearchObject.tbContent, 10, webDriver);
      webAction.clickObject(SearchObject.tbContent, webDriver, test);
      webAction.checkIfObjectIsDisplayed(SearchObject.artContent, 5, webDriver);
      webAction.clickObject(SearchObject.artContent, webDriver, test);
      String sObjectlongID = null;
      String parent = webDriver.getWindowHandle();
      Set<String> s = webDriver.getWindowHandles();

      for (String child_window : s) {

        if (!parent.equals(child_window)) {
          webDriver.switchTo().window(child_window);
          sObjectlongID = webAction.getElementText(SearchObject.lblObjectID);
          webDriver.close();
        }
      }

      webDriver.switchTo().window(parent);

      assert sObjectlongID != null;
      if (!sObjectlongID.isEmpty()) {
        String[] aObjectShortID = sObjectlongID.split(" ");
        sObjectShortID = aObjectShortID[0];
      }

      reportInstance.ExtentLogPass("Names of content packages displayed Successfully", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Names of content packages not displayed Successfully", webDriver, test);
    }

    return sObjectShortID;
  }

  public void NavigateToContentObjects(WebDriver webDriver, ExtentTest test) throws Exception {
    try {
      ContentNavigation NavigationObject = new ContentNavigation(webDriver);
      webAction.clickObject(NavigationObject.lnkContent, webDriver, test);
      webAction.clickObject(NavigationObject.lnkContentObjects, webDriver, test);
      AngularWaits.setDriver(webDriver);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Navigation to Content Objects Successful: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Navigation to Content Object Unsuccessful: " + e.getMessage(), webDriver, test);
    }

  }

  public void AddContentObject(WebDriver webDriver, ExtentTest test) throws Exception {
    ContentObjects addObjects = new ContentObjects(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);
      webAction.waitForElement(addObjects.lnkAddNew, 10, webDriver);
      webAction.clickObject(addObjects.lnkAddNew, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Navigation to Add New Content Objects  Successful: ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement2 = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      String sContentObjectID;

      if (TEST_DATA_MAP.get("ContentObjectID").equalsIgnoreCase("AUTO")) {
        sContentObjectID = TEST_DATA_MAP.get("ContentObjectID") + webAction.generateRandomString(5);
      } else {
        sContentObjectID = TEST_DATA_MAP.get("ContentObjectID");
      }

      webAction.enterText(addObjects.txtContentObjectID, sContentObjectID);
      webAction.enterText(addObjects.txtTitle, TEST_DATA_MAP.get("Title") + webAction.generateRandomString(5));
      webAction.enterText(addObjects.txtSecurityDomainID, TEST_DATA_MAP.get("SecurityDomainID"));
      webAction.clickObject(addObjects.btnAdd, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("All Captured information saved, Content ID is: " + sContentObjectID, webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Add a Content Object not Successful", webDriver, test);
    }
  }

  public void AddAICCInformation(WebDriver webDriver, ExtentTest test) throws Exception {
    EditSummaryContentObjects EditObject = new EditSummaryContentObjects(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.waitForElement(EditObject.tbLaunchMethod, 10, webDriver);
      webAction.clickObject(EditObject.tbLaunchMethod, webDriver, test);

      Thread.sleep(3000);

      webAction.waitForElement(EditObject.rdbAICC, 10, webDriver);
      webAction.clickObject(EditObject.rdbAICC, webDriver, test);
      webAction.clickObject(EditObject.chkAICCWrapper, webDriver, test);
      webAction.enterText(EditObject.txtContentURL, System.getProperty("user.dir") + "/testData/testingDocuments/" + TEST_DATA_MAP.get("DocumentName"));
      webAction.clickObject(EditObject.btnApplyChanges, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("AICC Updates successfully saved", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("AICC Updates successfully not saved", webDriver, test);
    }
  }

  public void NavigateToItems(WebDriver webDriver, ExtentTest test) throws Exception {
    LearningActivitiesNavigation LearningActivtitesObject = new LearningActivitiesNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);
    webAction.waitForElement(LearningActivtitesObject.lnkLearningActivities, 5000, webDriver);

    try {
      Thread.sleep(3000);

      webAction.clickObject(LearningActivtitesObject.lnkLearningActivities, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully navigated to Learning Activities tab : ", webDriver, test);
      webAction.clickObject(LearningActivtitesObject.lnkItems, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully navigated to Items : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Items : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToCurricula(WebDriver webDriver, ExtentTest test) throws Exception {
    LearningActivitiesNavigation LearningActivitiesObject = new LearningActivitiesNavigation(webDriver);
    webAction.waitForElement(LearningActivitiesObject.lnkLearningActivities, 5000, webDriver);

    try {
      Thread.sleep(3000);

      webAction.clickObject(LearningActivitiesObject.lnkLearningActivities, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully navigated to Learning Activities tab : ", webDriver, test);
      webAction.clickObject(LearningActivitiesObject.lnkCurricula, webDriver, test);
      webAction.waitForPageLoaded(webDriver);

      Thread.sleep(6000);

      reportInstance.ExtentLogPass("Successfully navigated to Curricula : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Curricula : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToLibrary(WebDriver webDriver, ExtentTest test) throws Exception {
    NavigateToLibrary navigateToLibraryObject = new NavigateToLibrary(webDriver);
    LearningActivitiesNavigation LearningActivtitesObject = new LearningActivitiesNavigation(webDriver);
    webAction.waitForElement(LearningActivtitesObject.lnkLearningActivities, 10, webDriver);

    try {
      webAction.clickObject(LearningActivtitesObject.lnkLearningActivities, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Successfully navigated to Learning Activities tab : ", webDriver, test);
      webAction.clickObject(navigateToLibraryObject.lnkLibraries, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Successfully navigated to Library tab : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Library : " + e.getMessage(), webDriver, test);
    }
  }

  public void CreateAssignmentProfile(WebDriver webDriver, ExtentTest test) throws Exception {
    CreateAssignmentProfile assignmentProfile = new CreateAssignmentProfile(webDriver);
    UserObjects user = new UserObjects(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.clickObject(assignmentProfile.lnkAddNew, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass(" Add new assignment profile link clicked Successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementAddNew = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementAddNew);

      Thread.sleep(5000);

      webAction.enterText(assignmentProfile.txtAssignmentProfileID, TEST_DATA_MAP.get("Assignment Profile ID") + webAction.generateRandomValue(5)); //+ webAction.generateRandomValue(3));
      webAction.enterText(assignmentProfile.txtDescription, TEST_DATA_MAP.get("Description"));
      webAction.clearTextField(assignmentProfile.txtSecurityDomain);
      webAction.enterText(assignmentProfile.txtSecurityDomain, TEST_DATA_MAP.get("Security Domain"));
      webAction.clickObject(assignmentProfile.btnAdd, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Assignment profile added Successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();

      Thread.sleep(3000);

      String sCreatedID = webAction.getElementText(assignmentProfile.lblAssignmentProfileID);

      if (sCreatedID.contains("AUTO")) {
        reportInstance.ExtentLogPass("Assignment Profile Created, ID : " + sCreatedID, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Assignment Profile not Created", webDriver, test);
      }

      Thread.sleep(1000);

      webAction.clickObject(assignmentProfile.lnkRulesTab, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Rules tab clicked Successfully : ", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(assignmentProfile.btnAddDomain, webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementAddSecurityDomain = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementAddSecurityDomain);

      Thread.sleep(5000);

      webAction.enterText(assignmentProfile.txtSecurityDomainKeyword, TEST_DATA_MAP.get("Security Domain"));
      webAction.clickObject(assignmentProfile.btnSearch, webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementAddDomain = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementAddDomain);

      Thread.sleep(5000);

      webAction.clickObject(assignmentProfile.chkDomain, webDriver, test);
      webAction.clickObject(assignmentProfile.btnAddSecurityDomain, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Security Domain is successfully added : ", webDriver, test);
      webDriver.switchTo().defaultContent();

      Thread.sleep(5000);

      webAction.clickObject(assignmentProfile.lnkCreateGroup, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Create Group link is clicked successfully : ", webDriver, test);
      webAction.enterText(assignmentProfile.txtGroupName, TEST_DATA_MAP.get("Group Name"));

      Thread.sleep(3000);

      webAction.clickObject(assignmentProfile.btnAttribute, webDriver, test);

      Thread.sleep(3000);

      webAction.enterText(assignmentProfile.txtAttribute, TEST_DATA_MAP.get("Attribute"));
      webAction.presEnter(assignmentProfile.txtAttribute, webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(assignmentProfile.btnOperator, webDriver, test);

      Thread.sleep(3000);

      webAction.enterText(assignmentProfile.txtOperator, TEST_DATA_MAP.get("Operator"));
      webAction.presEnter(assignmentProfile.txtOperator, webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(assignmentProfile.btnUsers, webDriver, test);
      reportInstance.ExtentLogPass("Adding users is clicked successfully : ", webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElementAddUser = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementAddUser);

      Thread.sleep(5000);

      webAction.enterText(user.txtuserID, TEST_DATA_MAP.get("User ID"));
      webAction.clickObject(user.btnSearchUser, webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(user.chkAdd, webDriver, test);
      webAction.clickObject(user.btnAddUser, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("User is added successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.clickObject(assignmentProfile.btnSave, webDriver, test);
      Thread.sleep(3000);

      webAction.clickObject(assignmentProfile.btnDownloadPreview, webDriver, test);

      Thread.sleep(3000);

      webAction.isFileDownloaded("Assignment Profile_Preview Users", System.getProperty("user.home") + "/Downloads");

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Assignment Profile file downloaded Successfully : ", webDriver, test);
      webAction.checkIfObjectIsDisplayed(assignmentProfile.lblStatus, 10, webDriver);
      String status = webAction.getElementText(assignmentProfile.lblStatus);

      if (status.contains("Execute")) {
        reportInstance.ExtentLogPass("Assignment Profile status is: " + status, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Assignment Profile status does not match", webDriver, test);
      }

      Thread.sleep(1000);

      reportInstance.ExtentLogPass(" Assignment profile created Successfully. " + sCreatedID, webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to create assignment profile : " + e.getMessage(), webDriver, test);
    }
  }

  public void EnrollInAProgramAndTakeOnlineLearning(WebDriver webDriver, ExtentTest test) throws Exception {
    MyLearningAssignments MyLearningAssignmentsObject = new MyLearningAssignments(webDriver);
    OnlineContentStructure OnlineContentStructureObject = new OnlineContentStructure(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(10000);

      webAction.waitForElement(MyLearningAssignmentsObject.lnkProgram, 10, webDriver);
      webAction.clickObject(MyLearningAssignmentsObject.lnkProgram, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Program selected: ", webDriver, test);

      Thread.sleep(3000);

      webAction.waitForElement(MyLearningAssignmentsObject.lnkProgram, 10, webDriver);
      webAction.clickObject(MyLearningAssignmentsObject.btnStartCourse, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Start Course clicked: ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementKeyword = webDriver.findElement(By.xpath("/html/body/div[5]/iframe[1]"));
      webDriver.switchTo().frame(iframeElementKeyword);

      Thread.sleep(3000);

      String parentWindowHandle = webDriver.getWindowHandle();
      webAction.clickObject(OnlineContentStructureObject.lnkLaunch, webDriver, test);

      Thread.sleep(5000);

      Set<String> onlineHandles = webDriver.getWindowHandles();

      for (String x : onlineHandles) {

        if (!x.equals(parentWindowHandle)) {
          webDriver.switchTo().window(x);
          Thread.sleep(2000);
          reportInstance.ExtentLogPass("Launch link clicked: ", webDriver, test);
          break;
        }
      }

      Thread.sleep(2000);
      webDriver.close();

      for (String x : onlineHandles) {

        if (x.equals(parentWindowHandle)) {
          webDriver.switchTo().window(x);
          break;
        }

        System.out.println(x);
      }

      webDriver.switchTo().defaultContent();
      WebElement iframeOnlineContent = webDriver.findElement(By.xpath("/html/body/div[5]/iframe[1]"));
      webDriver.switchTo().frame(iframeOnlineContent);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Course Window closed successfully: ", webDriver, test);
      webAction.clickObject(OnlineContentStructureObject.lnkBack, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Back button clicked successfully: ", webDriver, test);
      webAction.clickObject(OnlineContentStructureObject.lnkBack, webDriver, test);

      Thread.sleep(6000);

      reportInstance.ExtentLogPass("Back button clicked successfully: ", webDriver, test);
      webAction.waitForElement(MyLearningAssignmentsObject.lnkProgram, 5, webDriver);
      reportInstance.ExtentLogPass("Enroll in a program and take Online Learning successful: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Enroll in a program and take Online Learning unsuccessful : " + e.getMessage(), webDriver, test);
    }
  }

  public void CreateAnObservationChecklist(WebDriver webDriver, ExtentTest test) throws Exception {
    AddNewTask AddNewTaskObject = new AddNewTask(webDriver);
    Tasks TasksObject = new Tasks(webDriver);
    LearningActivitiesNavigation LearningActivtitesObject = new LearningActivitiesNavigation(webDriver);
    ChecklistSettings ChecklistSettingsObject = new ChecklistSettings(webDriver);
    WorkplaceAssessment WorkplaceAssessmentObject = new WorkplaceAssessment(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[2]/div/section/iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(5000);

      webAction.clickObject(TasksObject.lnkAddNew, webDriver, test);

      Thread.sleep(3500);

      reportInstance.ExtentLogPass("Add new link clicked successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeAdd = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeAdd);

      Thread.sleep(5000);

      webAction.enterText(AddNewTaskObject.txtTaskID, TEST_DATA_MAP.get("Task ID"));

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Task ID entered successfully : ", webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Description entered successfully : ", webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Add Task fields entered successfully : ", webDriver, test);
      webAction.clickObject(AddNewTaskObject.btnAdd, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Add button clicked successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.clickObject(LearningActivtitesObject.lnkLearningActivities, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Learning Activities clicked successfully : ", webDriver, test);
      webAction.clickObject(LearningActivtitesObject.lnkItems, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Items clicked successfully : ", webDriver, test);
      SearchandSelectItemByID(webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.waitForElement(WorkplaceAssessmentObject.tabTasks, 5, webDriver);
      webAction.clickObject(WorkplaceAssessmentObject.tabTasks, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Tasks tab clicked successfully : ", webDriver, test);
      webAction.waitForElement(WorkplaceAssessmentObject.btnPlus, 5, webDriver);
      webAction.clickObject(WorkplaceAssessmentObject.btnPlus, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Add button clicked successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeTasks = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeTasks);

      Thread.sleep(5000);

      webAction.enterText(TasksObject.txtKeyword, TEST_DATA_MAP.get("Task ID"));

      Thread.sleep(4000);

      webAction.clickObject(TasksObject.btnSearch, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Search button clicked successfully : ", webDriver, test);
      webAction.checkBox(TasksObject.chkAdd, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Add checkbox successfully selected : ", webDriver, test);
      webAction.clickObject(TasksObject.btnAdd, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Add button clicked successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.waitForElement(WorkplaceAssessmentObject.btnSettings, 5, webDriver);
      webAction.clickObject(WorkplaceAssessmentObject.btnSettings, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Settings button clicked successfully : ", webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Settings selected successfully : ", webDriver, test);
      webAction.clickObject(ChecklistSettingsObject.btnSave, webDriver, test);

      Thread.sleep(7000);

      reportInstance.ExtentLogPass("Create an observation checklist successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Create an observation checklist unsuccessful : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddProgramToAssignmentProfile(WebDriver webDriver, ExtentTest test) throws Exception {
    AssignmentProfilesObjects profileObjects = new AssignmentProfilesObjects(webDriver);
    ProgramObjects programObjects = new ProgramObjects(webDriver);
    CreateLibrary libraryObjects = new CreateLibrary(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      ManageUserLearningNavigation(webDriver, test);

      Thread.sleep(3000);

      SearchAndSelectAssignmentByID(webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      webAction.waitForPageLoaded(webDriver);
      webAction.clickObject(programObjects.lnkProgramTab, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass(" Programs tab clicked successfully : ", webDriver, test);
      webAction.clickObject(programObjects.btnAddProgram, webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementProgram = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementProgram);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass(" Program plus sign clicked successfully: ", webDriver, test);
      webAction.clickObject(programObjects.btnSearch, webDriver, test);

      Thread.sleep(4500);

      webAction.clickObject(programObjects.chkAdd2, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(programObjects.btnAdd, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass(" Program added successfully: ", webDriver, test);
      webDriver.switchTo().defaultContent();

      Thread.sleep(4500);

      webAction.clickObject(libraryObjects.btnActions, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(libraryObjects.lnkExecuteChanges, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Execute Changes tab clicked Successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeExChanges = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeExChanges);
      webAction.waitForPageLoaded(webDriver);

      Thread.sleep(5000);

      webAction.clickObject(profileObjects.ChkNotifyEmail, webDriver, test);

      Thread.sleep(5000);

      webAction.clickObject(profileObjects.btnFinish, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Finish button clicked Successfully : ", webDriver, test);
      webAction.clickObject(profileObjects.btnBackToAssignmentProfile, webDriver, test);

      Thread.sleep(6000);

      reportInstance.ExtentLogPass("Back To Assignment Profile button clicked Successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();

      Thread.sleep(2000);

      webAction.checkIfObjectIsDisplayed(libraryObjects.lblStatus2, 10, webDriver);
      String assignmentStatus2 = webAction.getElementText(libraryObjects.lblStatus2);

      if (assignmentStatus2.contains("Valid")) {
        reportInstance.ExtentLogPass("Assignment Profile status is : " + assignmentStatus2, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Assignment Profile status does not match", webDriver, test);
      }

      Thread.sleep(3000);

      reportInstance.ExtentLogPass(" Program added successfully to assignment profile : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add program to assignment profile: " + e.getMessage(), webDriver, test);
    }
  }

  public void ManageUserLearningNavigation(WebDriver webDriver, ExtentTest test) throws Exception {
    ManageUserLearningNavigation ManageUserLearningNavigationObject = new ManageUserLearningNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    Thread.sleep(3000);

    webAction.waitForElement(ManageUserLearningNavigationObject.btnManageUserLearning, 5000, webDriver);

    try {

      webAction.clickObject(ManageUserLearningNavigationObject.btnManageUserLearning, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully navigated to Manage User Learning tab : ", webDriver, test);
      webAction.clickObject(ManageUserLearningNavigationObject.btnAssignmentProfiles, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully navigated to Assignment Profiles tab : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Manage User Learning tab : " + e.getMessage(), webDriver, test);
    }
  }

  public void AssignTaskObserversNavigation(WebDriver webDriver, ExtentTest test) throws Exception {
    ManageUserLearningNavigation ManageUserLearningNavigationObject = new ManageUserLearningNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    Thread.sleep(3000);

    webAction.waitForElement(ManageUserLearningNavigationObject.btnManageUserLearning, 5, webDriver);

    try {
      Thread.sleep(3000);

      webAction.clickObject(ManageUserLearningNavigationObject.btnManageUserLearning, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Manager User Learning button clicked successfully  : ", webDriver, test);
      webAction.clickObject(ManageUserLearningNavigationObject.lnkAssignTaskObservers, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Successfully navigated to Assign Task Observers : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Assign Task Observers : " + e.getMessage(), webDriver, test);
    }
  }

  public void AssignTaskObservers(WebDriver webDriver, ExtentTest test) throws Exception {
    AssignTaskObservers AssignTaskObserversObject = new AssignTaskObservers(webDriver);
    webAction.waitForPageLoaded(webDriver);
    webDriver.switchTo().defaultContent();
    WebElement iframeElement = webDriver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div/div/div[2]/div/section/div/div[2]/div/div/section/div/div[2]/section/div/div/div/div/div[2]/div/section/iframe"));
    webDriver.switchTo().frame(iframeElement);

    Thread.sleep(3000);

    try {
      Thread.sleep(3000);

      webAction.enterText(AssignTaskObserversObject.txtItemID, TEST_DATA_MAP.get("Item ID"));

      Thread.sleep(4000);

      webAction.clickObject(AssignTaskObserversObject.lnkItem, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Item ID entered successfully  : ", webDriver, test);
      webAction.clickObject(AssignTaskObserversObject.btnStep2, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Step 2 button clicked successfully  : ", webDriver, test);
      webAction.enterText(AssignTaskObserversObject.txtObservers, TEST_DATA_MAP.get("Observation"));

      Thread.sleep(4000);

      webAction.clickObject(AssignTaskObserversObject.lnkObservers, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Observer entered successfully  : ", webDriver, test);
      webAction.checkBox(AssignTaskObserversObject.chkObservers, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Observer selected successfully  : ", webDriver, test);
      webAction.clickObject(AssignTaskObserversObject.btnStep3, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Step 3 button clicked successfully  : ", webDriver, test);
      webAction.enterText(AssignTaskObserversObject.txtUsers, TEST_DATA_MAP.get("Users"));

      Thread.sleep(4000);

      webAction.clickObject(AssignTaskObserversObject.lnkUsers, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("User entered successfully  : ", webDriver, test);
      webAction.checkBox(AssignTaskObserversObject.chkUsers, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("User selected successfully  : ", webDriver, test);
      webAction.clickObject(AssignTaskObserversObject.btnReview, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Review button clicked successfully  : ", webDriver, test);
      webAction.clickObject(AssignTaskObserversObject.btnSendObservationRequest, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Successfully Assigned Task Observers : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Assigning Task Observers unsuccessful : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToPrograms(WebDriver webDriver, ExtentTest test) throws Exception {
    LearningActivitiesNavigation LearningActivtitesObject = new LearningActivitiesNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);
    webAction.waitForElement(LearningActivtitesObject.lnkLearningActivities, 5000, webDriver);

    try {
      webAction.clickObject(LearningActivtitesObject.lnkLearningActivities, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully navigated to Learning Activities tab : ", webDriver, test);
      webAction.clickObject(LearningActivtitesObject.lnkPrograms, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully navigated to Programs tab : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Programs tab : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToClasses(WebDriver webDriver, ExtentTest test) throws Exception {
    LearningActivitiesNavigation LearningActivtitesObject = new LearningActivitiesNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);
    webAction.waitForElement(LearningActivtitesObject.lnkLearningActivities, 5000, webDriver);

    try {
      Thread.sleep(3000);

      webAction.clickObject(LearningActivtitesObject.lnkLearningActivities, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully navigated to Learning Activities tab : ", webDriver, test);
      webAction.clickObject(LearningActivtitesObject.lnkClasses, webDriver, test);
      webAction.AngularWaits(webDriver);
      reportInstance.ExtentLogPass("Successfully navigated to Classes tab : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Classes tab : " + e.getMessage(), webDriver, test);
    }

  }

  public void SearchandSelectItemByID(WebDriver webDriver, ExtentTest test) throws Exception {
    Items ItemsObject = new Items(webDriver);
    webDriver.switchTo().defaultContent();
    WebElement iframeElement = webDriver.findElement(By.xpath("//iframe[contains(@src,'/learning/search/initSearch.do?searchType=0&selectorName=Component&stackID=search&entityManagerEnabled=Y')]"));
    webDriver.switchTo().frame(iframeElement);

    Thread.sleep(5000);

    try {
      Thread.sleep(3000);

      webAction.enterText(ItemsObject.txtItemID, TEST_DATA_MAP.get("Item ID"));

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Item ID entered successfully : ", webDriver, test);
      webAction.clickObject(ItemsObject.btnSearch, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Search button clicked successfully : ", webDriver, test);
      List<WebElement> iframe1 = webDriver.findElements(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframe1.get(0));
      List<WebElement> Resultrows = webDriver.findElements(By.xpath("//*[@id=\"entityManagerID\"]"));

      int tblrows = Resultrows.size();
      if (tblrows > 0) {
        System.out.println("Number of rows are: " + tblrows);

        WebElement tblRow = webDriver.findElement(
          By.xpath("/html/body/div[1]/div[1]/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td/span/a"));

        Thread.sleep(3000);

        tblRow.click();

        Thread.sleep(5000);

      } else {
        System.out.println("Record not found in table");
      }

      reportInstance.ExtentLogPass("Successfully searched and selected an Item: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The Item ID entered returned no information : " + e.getMessage(), webDriver, test);
    }
  }

  public void SearchAndSelectCurriculumByID(WebDriver webDriver, ExtentTest test) throws Exception {
    AddNewCurriculum AddNewCurriculumObject = new AddNewCurriculum(webDriver);
    Curricula CurriculaObject = new Curricula(webDriver);

    Thread.sleep(5000);

    webDriver.switchTo().defaultContent();
    WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
    webDriver.switchTo().frame(iframeElement);
    webAction.waitForElement(CurriculaObject.btnSearch, 5, webDriver);

    try {
      Thread.sleep(3000);

      webAction.enterText(CurriculaObject.txtCurriculumID, TEST_DATA_MAP.get("Curriculum ID"));

      Thread.sleep(3000);

      webAction.clickObject(CurriculaObject.btnSearch, webDriver, test);

      Thread.sleep(3000);

      List<WebElement> iframe1 = webDriver.findElements(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframe1.get(0));
      List<WebElement> Resultrows = webDriver.findElements(By.xpath("//*[@id=\"entityManagerID\"]"));

      int tblrows = Resultrows.size();
      if (tblrows > 0) {
        System.out.println("Number of rows are: " + tblrows);

        WebElement tblRow = webDriver.findElement(
          By.xpath("/html/body/div[1]/div[1]/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td/span/a"));

        Thread.sleep(3000);

        tblRow.click();

        Thread.sleep(5000);

      } else {
        System.out.println("Record not found in table");
      }

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully searched and selected a Curriculum: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The Curriculum ID entered returned no information : " + e.getMessage(), webDriver, test);
    }
  }

  public void SearchAndSelectAssignmentByID(WebDriver webDriver, ExtentTest test) throws Exception {
    AssignmentProfiles AssignmentProfilesObject = new AssignmentProfiles(webDriver);
    webDriver.switchTo().defaultContent();
    WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
    webDriver.switchTo().frame(iframeElement);
    webAction.waitForElement(AssignmentProfilesObject.btnSearch, 5, webDriver);

    try {
      Thread.sleep(3000);

      webAction.enterText(AssignmentProfilesObject.txtAssignmentProfilesId, TEST_DATA_MAP.get("Assignment Profile ID"));

      Thread.sleep(3000);

      webAction.clickObject(AssignmentProfilesObject.btnSearch, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Assignment ID searched successfully: ", webDriver, test);
      List<WebElement> iframe1 = webDriver.findElements(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframe1.get(0));
      List<WebElement> Resultrows = webDriver.findElements(By.xpath("//*[@id=\"entityManagerID\"]"));

      int tblrows = Resultrows.size();
      if (tblrows > 0) {
        System.out.println("Number of rows are: " + tblrows);

        WebElement tblRow = webDriver.findElement(
          By.xpath("/html/body/div[1]/div[1]/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td/span/a"));

        Thread.sleep(3000);

        tblRow.click();

        Thread.sleep(5000);

      } else {
        System.out.println("Record not found in table");
      }

      reportInstance.ExtentLogPass("Successfully searched and selected an Assignment: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The Assignment ID entered returned no information : " + e.getMessage(), webDriver, test);
    }
  }

  public void SearchLibrary(WebDriver webDriver, ExtentTest test) throws Exception {
    LibrariesObjectPage librariesPage = new LibrariesObjectPage(webDriver);
    webDriver.switchTo().defaultContent();
    WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
    webDriver.switchTo().frame(iframeElement);
    webAction.waitForElement(librariesPage.txtLibraryID2, 10, webDriver);

    try {
      Thread.sleep(2000);

      webAction.enterText(librariesPage.txtLibraryID2, TEST_DATA_MAP.get("Library ID"));

      Thread.sleep(2000);

      webAction.clickObject(librariesPage.rdoActiveBoth, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(librariesPage.btnSearch, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass(" Library search successful : ", webDriver, test);
      List<WebElement> iframe1 = webDriver.findElements(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframe1.get(0));
      List<WebElement> Resultrows = webDriver.findElements(By.xpath("//*[@id=\"entityManagerID\"]"));

      int tblrows = Resultrows.size();
      if (tblrows > 0) {
        System.out.println("Number of rows are: " + tblrows);
        WebElement tblRow = webDriver.findElement(
          By.xpath("/html/body/div[1]/div[1]/form/table[2]/tbody/tr/td[2]/table/tbody/tr/td/span/a"));

        Thread.sleep(3000);

        tblRow.click();

        Thread.sleep(5000);

      } else {
        System.out.println("Record not found in table");
      }

      reportInstance.ExtentLogPass(" Library ID found successfully: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to get library ID  : " + e.getMessage(), webDriver, test);
    }
  }

  public void CreateALibrary(WebDriver webDriver, ExtentTest test) throws Exception {
    CreateLibrary libraryObject = new CreateLibrary(webDriver);
    LibrariesObjectPage librariesPage = new LibrariesObjectPage(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.clickObject(librariesPage.lnkAddNew, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass(" Add new library link iS clicked successfully. ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementAddNew = webDriver.findElement(By.className("plateauPopupContentFrame"));
      webDriver.switchTo().frame(iframeElementAddNew);

      Thread.sleep(5000);

      webAction.enterText(libraryObject.txtLibraryID, TEST_DATA_MAP.get("Library ID") + webAction.generateRandomValue(5));
      webAction.enterText(libraryObject.txtLibraryDesc, TEST_DATA_MAP.get("Description"));
      webAction.clearTextField(libraryObject.txtSecurityDomain);
      webAction.enterText(libraryObject.txtSecurityDomain, TEST_DATA_MAP.get("Security Domain"));

      Thread.sleep(2000);

      webAction.clickObject(libraryObject.btnAdd, webDriver, test);

      Thread.sleep(2000);

      webDriver.switchTo().defaultContent();

      Thread.sleep(2000);

      webAction.checkIfObjectIsDisplayed(libraryObject.lblLibraryID, 10, webDriver);
      String sCreatedID = webAction.getElementText(libraryObject.lblLibraryID);

      if (sCreatedID.contains("AUTO")) {
        reportInstance.ExtentLogPass("Library Created, ID : " + sCreatedID, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Library not Created", webDriver, test);
      }

      Thread.sleep(1000);

      reportInstance.ExtentLogPass(" Library created Successfully. The library Id created is: " + libraryObject.lblLibraryID.getText(), webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to create a library : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddAssignmentProfile(WebDriver webDriver, ExtentTest test) throws Exception {
    CreateLibrary libraryObject = new CreateLibrary(webDriver);
    LibrariesObjectPage librariesPage = new LibrariesObjectPage(webDriver);
    LearningActivitiesNavigation learningActivites = new LearningActivitiesNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      NavigateToLibrary(webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.enterText(librariesPage.txtLibraryID2, TEST_DATA_MAP.get("Library ID"));
      webAction.clickObject(librariesPage.rdoActiveBoth, webDriver, test);
      webAction.clickObject(librariesPage.btnSearch, webDriver, test);

      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElementSearchResults = webDriver.findElement(By.xpath("/html/body/form/table/tbody/tr/td/div[1]/iframe"));
      webDriver.switchTo().frame(iframeElementSearchResults);

      Thread.sleep(3000);

      webAction.clickObject(librariesPage.lnkSearchResults, webDriver, test);

      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElementAssignProfiles = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementAssignProfiles);

      Thread.sleep(3000);

      webAction.enterText(libraryObject.txtDescKeyword, TEST_DATA_MAP.get("Assignment Profile ID"));
      webAction.clickObject(libraryObject.btnSearchKeyword, webDriver, test);
      webAction.checkBox(libraryObject.chkAdd, webDriver, test);
      webAction.clickObject(libraryObject.btnAddProfile, webDriver, test);
      webDriver.switchTo().defaultContent();

      Thread.sleep(5000);

      webAction.clickObject(libraryObject.lnkDescription, webDriver, test);
      webAction.clickObject(libraryObject.lnkRulesTab, webDriver, test);
      webAction.clickObject(learningActivites.lnkLearningActivities, webDriver, test);
      webAction.clickObject(learningActivites.lnkItems, webDriver, test);
      reportInstance.ExtentLogPass(" Assignment Profile added Successfully : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add an assignment profile : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddItemToLibrary(WebDriver webDriver, ExtentTest test) throws Exception {
    Items itemsObject = new Items(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();

      Thread.sleep(5000);

      webAction.clickObject(itemsObject.tabItem, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Items tab clicked successfully : ", webDriver, test);
      webAction.clickObject(itemsObject.btnPlus, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Items plus sign clicked successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeItem = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeItem);

      Thread.sleep(3000);

      webAction.enterText(itemsObject.txtKeyword, TEST_DATA_MAP.get("Item ID"));
      webAction.clickObject(itemsObject.btnSearch, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Item ID search successful : ", webDriver, test);

      Thread.sleep(5000);

      webAction.checkBox(itemsObject.chkAddClasses, webDriver, test);
      webAction.checkBox(itemsObject.chkAdd3, webDriver, test);

      Thread.sleep(2000);
      webAction.clickObject(itemsObject.btnAdd, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Item ID added successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();

      Thread.sleep(5000);

      webAction.clickObject(itemsObject.btnEdit, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Items edit sign clicked successfully : ", webDriver, test);
      webAction.waitForElement(itemsObject.btnHighlightAs3, 5, webDriver);
      webAction.clickObject(itemsObject.btnHighlightAs3, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(itemsObject.lnkNew, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(itemsObject.txtDate3, webDriver, test);
      LocalDateTime ldt = LocalDateTime.now();
      String sDate = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH).format(ldt);
      webAction.enterText(itemsObject.txtDate3, sDate);

      Thread.sleep(2000);

      webAction.clickObject(itemsObject.btnSave, webDriver, test);

      Thread.sleep(4000);


      reportInstance.ExtentLogPass(" Item added successfully to a library : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add an item to library : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddALibraryToACurriculum(WebDriver webDriver, ExtentTest test) throws Exception {
    Curricula CurriculaObject = new Curricula(webDriver);
    Items itemsObject = new Items(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      webAction.clickObject(CurriculaObject.tabLibraries, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Libraries tab selected successfully: ", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(itemsObject.btnPlus2, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Plus button clicked successfully: ", webDriver, test);
      webDriver.switchTo().defaultContent();
      int size = webDriver.findElements(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe")).size();
      WebElement iframeLibrary = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeLibrary);

      Thread.sleep(3000);

      webAction.enterText(itemsObject.txtKeyword, TEST_DATA_MAP.get("Library ID"));

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Library ID entered successfully: ", webDriver, test);
      webAction.clickObject(itemsObject.btnSearch, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Search button clicked successfully: ", webDriver, test);
      webAction.checkBox(itemsObject.chkAddItems, webDriver, test);

      Thread.sleep(2000);

      webAction.checkBox(itemsObject.chkAdd2, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Add Checkboxes selected successfully: ", webDriver, test);
      webAction.clickObject(itemsObject.btnAdd, webDriver, test);
      webDriver.switchTo().defaultContent();

      Thread.sleep(5000);

      webAction.clickObject(itemsObject.btnEdit2, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Edit button clicked successfully: ", webDriver, test);
      webAction.waitForElement(itemsObject.btnHighlightAs2, 5, webDriver);
      webAction.clickObject(itemsObject.btnHighlightAs2, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(itemsObject.lnkNew1, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(itemsObject.txtDate3, webDriver, test);
      LocalDateTime ldt = LocalDateTime.now();
      String sDate = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH).format(ldt);
      webAction.enterText(itemsObject.txtDate3, sDate);

      Thread.sleep(4000);

      webAction.clickObject(itemsObject.btnSave, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Save button clicked successfully: ", webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Adding a Library to a Curriculum successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Adding a Library to a Curriculum unsuccessful  : " + e.getMessage(), webDriver, test);
    }
  }

  public void NewlyActivatedLibrary(WebDriver webDriver, ExtentTest test) throws Exception {
    CreateLibrary libraryObject = new CreateLibrary(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();

      Thread.sleep(5000);

      webAction.scrollPageToElement(libraryObject.btnStatus, webDriver);
      webAction.clickObject(libraryObject.btnStatus, webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(libraryObject.btnStatus, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(libraryObject.btnSave, webDriver, test);

      Thread.sleep(4000);

      webAction.scrollPageToElement(libraryObject.lblStatus, webDriver);
      webAction.checkIfObjectIsDisplayed(libraryObject.lblStatus, 10, webDriver);
      String status = webAction.getElementText(libraryObject.lblStatus);

      if (status.contains("Active")) {
        reportInstance.ExtentLogPass("Newly activated library status is: " + status, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Newly activated library status is not updated", webDriver, test);
      }


      Thread.sleep(1000);

      reportInstance.ExtentLogPass(" Newly created library status successfully updated : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to activate newly created library : " + e.getMessage(), webDriver, test);
    }
  }


  public void AddCurriculumToAssignmentProfile(WebDriver webDriver, ExtentTest test) throws Exception {
    LearningActivitiesNavigation LearningActivtitesObject = new LearningActivitiesNavigation(webDriver);
    AssignmentProfiles AssignmentProfilesObject = new AssignmentProfiles(webDriver);
    Curricula CurriculaObject = new Curricula(webDriver);
    webAction.waitForElement(LearningActivtitesObject.lnkLearningActivities, 5, webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();

      Thread.sleep(3000);

      webAction.clickObject(AssignmentProfilesObject.lnkCurricula, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Curricula link click successfully : ", webDriver, test);
      webAction.clickObject(CurriculaObject.btnPlus, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Add button successfully clicked: ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.enterText(CurriculaObject.txtKeyword, TEST_DATA_MAP.get("Curriculum ID"));

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Keyword successfully entered: ", webDriver, test);
      webAction.clickObject(CurriculaObject.btnSearch, webDriver, test);

      Thread.sleep(4500);

      reportInstance.ExtentLogPass("Search button clicked successfully : ", webDriver, test);
      webAction.checkBox(CurriculaObject.chkAdd, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Curricula selected successfully : ", webDriver, test);
      webAction.clickObject(CurriculaObject.btnAdd, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Add button clicked successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();

      Thread.sleep(4500);

      webAction.clickObject(AssignmentProfilesObject.btnActions, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(AssignmentProfilesObject.lnkExecuteChanges, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Execute Changes clicked successfully : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeExChanges = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeExChanges);

      Thread.sleep(4000);

      webAction.checkBox(AssignmentProfilesObject.ChkNotifyEmail, webDriver, test);

      Thread.sleep(8000);

      reportInstance.ExtentLogPass("Schedule details captured successfully : ", webDriver, test);
      webAction.clickObject(AssignmentProfilesObject.btnFinish, webDriver, test);

      Thread.sleep(10000);

      reportInstance.ExtentLogPass("Finish button clicked successfully : ", webDriver, test);
      webAction.clickObject(AssignmentProfilesObject.btnBackToAssignmentProfile, webDriver, test);

      Thread.sleep(5000);

      webAction.waitForPageLoaded(webDriver);
      reportInstance.ExtentLogPass("Successfully added curriculum to assignment profile : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add curriculum to assignment profile : " + e.getMessage(), webDriver, test);
    }
  }

  public void CreateACurriculum(WebDriver webDriver, ExtentTest test) throws Exception {
    LearningActivitiesNavigation LearningActivitiesObject = new LearningActivitiesNavigation(webDriver);
    Items ItemsObject = new Items(webDriver);
    AddNewCurriculum CurriculaObject = new AddNewCurriculum(webDriver);
    webAction.waitForElement(LearningActivitiesObject.lnkLearningActivities, 5000, webDriver);

    try {
      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.clickObject(ItemsObject.lnkAddNew, webDriver, test);

      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      WebElement iframeCurricula = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeCurricula);

      Thread.sleep(5000);

      webAction.waitForElement(CurriculaObject.btnAdd, 5000, webDriver);
      webAction.SelectByValue(CurriculaObject.cmbLocale, TEST_DATA_MAP.get("Locale"), webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CurriculaObject.txtCurriculumID, TEST_DATA_MAP.get("Curriculum ID") + webAction.generateRandomValue(5));

      Thread.sleep(2000);

      webAction.enterText(CurriculaObject.txtTitle, TEST_DATA_MAP.get("Title"));

      Thread.sleep(2000);

      webAction.enterText(CurriculaObject.txtDescription, TEST_DATA_MAP.get("Description"));

      Thread.sleep(2000);

      webAction.enterText(CurriculaObject.txtSecurityDomain, TEST_DATA_MAP.get("Security Domain"));

      Thread.sleep(2000);

      webAction.SelectByVisibleText(CurriculaObject.cmbCurriculumType, TEST_DATA_MAP.get("Curriculum Type"), webDriver, test);

      Thread.sleep(2000);

      webAction.SelectByValue(CurriculaObject.cmbPriority, TEST_DATA_MAP.get("Priority"), webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Curriculum details entered successfully : ", webDriver, test);
      webAction.clickObject(CurriculaObject.btnAdd, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Curriculum successfully created : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to create a curriculum : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddOnlineItemToLibrary(WebDriver webDriver, ExtentTest test) throws Exception {
    Items ItemsObject = new Items(webDriver);
    LibrariesObjectPage librariesPageObject = new LibrariesObjectPage(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      webAction.waitForElement(librariesPageObject.lnkItem, 5000, webDriver);
      webAction.clickObject(librariesPageObject.lnkItem, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Item clicked successfully : ", webDriver, test);
      webAction.clickObject(ItemsObject.btnPlus, webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementKeyword = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementKeyword);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Add button clicked successfully : ", webDriver, test);
      webAction.enterText(ItemsObject.txtKeyword, TEST_DATA_MAP.get("Item ID"));
      reportInstance.ExtentLogPass("Keyword captured successfully : ", webDriver, test);
      webAction.clickObject(ItemsObject.btnSearch, webDriver, test);

      Thread.sleep(7000);

      webAction.checkBox(ItemsObject.chkAddClasses, webDriver, test);

      Thread.sleep(2000);

      webAction.checkBox(ItemsObject.chkAdd3, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Add checkboxes selected successfully : ", webDriver, test);
      webAction.clickObject(ItemsObject.btnAdd, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully added an online item to library : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add an online item to library : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddAssessmentQuiztoLearningItem(WebDriver webDriver, ExtentTest test) throws Exception {
    Items ItemsObject = new Items(webDriver);
    ItemDetails ItemDetailsObject = new ItemDetails(webDriver);
    OnlineContent OnlineContentObject = new OnlineContent(webDriver);
    LearningActivitiesNavigation LearningActivitiesObject = new LearningActivitiesNavigation(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      webAction.checkIfObjectIsDisplayed(ItemDetailsObject.lnkOnlineContent, 10, "Online Content displayed", "Online Content not displayed", webDriver, test);
      webAction.clickObject(ItemDetailsObject.lnkOnlineContent, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Online Contents clicked successfully : ", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(OnlineContentObject.cmbAddAssessment, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Add Assessment clicked successfully : ", webDriver, test);
      webAction.clickObject(OnlineContentObject.lnkAddAssessment, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Add Assessment selected successfully : ", webDriver, test);
      webAction.enterText(OnlineContentObject.txtAssessmentID, TEST_DATA_MAP.get("Assessment ID"));

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Add Assessment details captured successfully : ", webDriver, test);
      webAction.enterText(OnlineContentObject.txtAssessmentTitle, TEST_DATA_MAP.get("Assessment Title"));

      Thread.sleep(2000);

      webAction.clickObject(OnlineContentObject.btnAssessmentOk, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Ok button clicked successfully : ", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(OnlineContentObject.btnSave, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Assessment quiz added to learning item successfully : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Adding assessment quiz to learning item failed : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddAssessmentExamToLearningItem(WebDriver webDriver, ExtentTest test) throws Exception {
    ItemDetails ItemDetailsObject = new ItemDetails(webDriver);
    OnlineContent OnlineContentObject = new OnlineContent(webDriver);
    OnlineContentSettings OnlineContentSettingsObject = new OnlineContentSettings(webDriver);
    SearchAssessments SearchAssessmentsObject = new SearchAssessments(webDriver);

    try {
      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      webAction.checkIfObjectIsDisplayed(ItemDetailsObject.lnkOnlineContent, 10, "Online Content displayed", "Online Content not displayed", webDriver, test);
      webAction.clickObject(ItemDetailsObject.lnkOnlineContent, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Online Contents clicked successfully : ", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(OnlineContentObject.cmbAddAssessment, webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(OnlineContentObject.lnkAddAssessment, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Add Assessment selected successfully : ", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(OnlineContentObject.btnSearchOptions, webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementKeyword = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementKeyword);

      Thread.sleep(3000);

      webAction.enterText(SearchAssessmentsObject.txtAssessmentID, TEST_DATA_MAP.get("Assessment ID"));

      Thread.sleep(2000);

      webAction.clickObject(SearchAssessmentsObject.btnSearch, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Search button clicked successfully : ", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(SearchAssessmentsObject.lnkSelectAssessmentResults, webDriver, test);

      Thread.sleep(2000);

      webAction.waitForElement(OnlineContentObject.btnAssessmentOk, 5, webDriver);
      webDriver.switchTo().defaultContent();

      Thread.sleep(2000);

      webAction.enterText(OnlineContentObject.txtAssessmentTitle, TEST_DATA_MAP.get("Assessment Title"));

      Thread.sleep(2000);

      webAction.clickObject(OnlineContentObject.btnOk, webDriver, test);
      webAction.waitForElement(OnlineContentObject.btnSettings, 5, webDriver);
      webAction.clickObject(OnlineContentObject.btnSettings, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Settings button clicked successfully : ", webDriver, test);
      webAction.waitForElement(OnlineContentSettingsObject.lnkCompletion, 5, webDriver);
      webAction.clickObject(OnlineContentSettingsObject.lnkCompletion, webDriver, test);
      webAction.clickObject(OnlineContentSettingsObject.rdbAddToHistory, webDriver, test);

      Thread.sleep(3000);

      webAction.enterText(OnlineContentSettingsObject.cmbCompletionStatus, TEST_DATA_MAP.get("Completion Status"));
      webAction.presEnter(OnlineContentSettingsObject.cmbCompletionStatus, webDriver, test);

      Thread.sleep(3000);

      webAction.checkBox(OnlineContentSettingsObject.chkAddToHistoryOnPass, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Completion settings selected successfully : ", webDriver, test);
      webAction.clickObject(OnlineContentObject.btnOk, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Ok button clicked successfully : ", webDriver, test);
      webAction.waitForElement(OnlineContentObject.btnSettings, 5, webDriver);
      webAction.clickObject(OnlineContentObject.btnSettings, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Settings button clicked successfully : ", webDriver, test);
      webAction.clickObject(OnlineContentSettingsObject.lnkLaunch, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Launch link clicked successfully : ", webDriver, test);
      webAction.clickObject(OnlineContentSettingsObject.rdbContentIsAvailableForLaunch, webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(OnlineContentSettingsObject.lnkLaunch, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Launch settings selected successfully : ", webDriver, test);
      webAction.clickObject(OnlineContentSettingsObject.btnOk, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Ok button clicked successfully : ", webDriver, test);
      webAction.waitForElement(OnlineContentObject.btnSave, 5, webDriver);
      webAction.clickObject(OnlineContentObject.btnSave, webDriver, test);

      Thread.sleep(4000);

      reportInstance.ExtentLogPass("Adding assessment exam to learning item Successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add assessment exam to learning item : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddAnItemToTheCurriculum(WebDriver webDriver, ExtentTest test) throws Exception {
    Curricula CurriculaObject = new Curricula(webDriver);
    Contents ContentsObject = new Contents(webDriver);
    Items ItemsObject = new Items(webDriver);

    try {
      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();

      Thread.sleep(2000);

      webAction.clickObject(CurriculaObject.lnkContents, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Contents link clicked successfully : ", webDriver, test);

      Thread.sleep(5000);

      webAction.clickObject(ContentsObject.lnkManageContent, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Manage Content button clicked successfully  : ", webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElementKeyword = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementKeyword);

      Thread.sleep(3000);

      webAction.waitForElement(ContentsObject.btnPlus, 5, webDriver);
      webAction.clickObject(ContentsObject.btnPlus, webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(ContentsObject.lnkItems, webDriver, test);

      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElementAddItems = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElementAddItems);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Add button clicked successfully  : ", webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeItem = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeItem);

      Thread.sleep(3000);

      webAction.clickObject(ItemsObject.btnCriteria, webDriver, test);

      Thread.sleep(3000);

      WebElement iframeElement1 = webDriver.findElement(By.xpath("//iframe[contains(@src,'/learning/search/body_criteria_chooser.jsp?stackID=list')]"));
      webDriver.switchTo().frame(iframeElement1);

      Thread.sleep(3000);

      if (!ItemsObject.chkItemID.isSelected()) {
        webAction.checkBox(ItemsObject.chkItemID, webDriver, test);

        Thread.sleep(2000);
      }
      webAction.clickObject(ItemsObject.btnSelect, webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      webDriver.switchTo().frame(iframeItem);

      Thread.sleep(2000);

      webAction.enterText(ItemsObject.txtDescItemId, TEST_DATA_MAP.get("Item ID"));

      Thread.sleep(2000);

      webAction.clickObject(ItemsObject.btnSearch, webDriver, test);
      reportInstance.ExtentLogPass("Search button clicked successfully  : ", webDriver, test);

      Thread.sleep(5000);

      webAction.clickObject(CurriculaObject.chkAddItem, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Item selected successfully  : ", webDriver, test);
      webAction.clickObject(ItemsObject.btnAdd, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(ItemsObject.btnClosePopup, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Successfully added an item to the curriculum : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add an item to the curriculum : " + e.getMessage(), webDriver, test);
    }
  }

  public void TransferItemTitleAndDescription(WebDriver webDriver, ExtentTest test) throws Exception {
    ItemDetails CourseObject = new ItemDetails(webDriver);
    LearningActivitiesNavigation LearningActivitiesObject = new LearningActivitiesNavigation(webDriver);
    webAction.waitForElement(LearningActivitiesObject.lnkLearningActivities, 5000, webDriver);

    try {
      NavigateToItems(webDriver, test);

      Thread.sleep(3000);

      SearchandSelectItemByID(webDriver, test);

      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      webAction.clickObject(CourseObject.btnTranslate, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Translate button clicked successfully : ", webDriver, test);
      webAction.waitForElement(CourseObject.btnSaveAndClose, 5000, webDriver);
      webAction.enterText(CourseObject.txtFrench, TEST_DATA_MAP.get("Translation(French)"));
      webAction.enterText(CourseObject.txtPortuguese, TEST_DATA_MAP.get("Translation(Portuguese)"));

      Thread.sleep(3500);

      reportInstance.ExtentLogPass("Translations captured successfully : ", webDriver, test);
      webAction.clickObject(CourseObject.btnSaveAndClose, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Translating item title and description successful : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Translating item title and description unsuccessful : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddContentObjectToLearningItem(WebDriver webDriver, ExtentTest test) throws Exception {
    OnlineContent OnlineContentObject = new OnlineContent(webDriver);
    OnlineContentSettings OnlineContentSettingsObject = new OnlineContentSettings(webDriver);
    ItemDetails ItemDetailsObject = new ItemDetails(webDriver);

    try {
      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      webAction.checkIfObjectIsDisplayed(ItemDetailsObject.lnkOnlineContent, 10, "Online Content displayed", "Online Content not displayed", webDriver, test);
      webAction.clickObject(ItemDetailsObject.lnkOnlineContent, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Online content clicked successfully : ", webDriver, test);
      webAction.checkIfObjectIsDisplayed(OnlineContentObject.lnkAddContentObject, 10, "Add Content displayed", "Add Content not displayed", webDriver, test);
      webAction.clickObject(OnlineContentObject.lnkAddContentObject, webDriver, test);

      Thread.sleep(3000);

      webAction.enterText(OnlineContentObject.txtContentObject, TEST_DATA_MAP.get("Content object"));

      Thread.sleep(2000);

      webAction.enterText(OnlineContentObject.txtContentTitle, TEST_DATA_MAP.get("Object Title"));

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Content details captured successfully : ", webDriver, test);
      webAction.clickObject(OnlineContentObject.btnOk, webDriver, test);

      Thread.sleep(3000);

      webAction.checkIfObjectIsDisplayed(OnlineContentObject.btnSettings, 10, "Settings button displayed", "Settings button not displayed", webDriver, test);
      webAction.clickObject(OnlineContentObject.btnSettings, webDriver, test);

      Thread.sleep(3000);

      webAction.checkIfObjectIsDisplayed(OnlineContentSettingsObject.lnkCompletion, 10, "Completion link displayed", "Completion link not displayed", webDriver, test);
      webAction.clickObject(OnlineContentSettingsObject.lnkCompletion, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Completion link clicked successfully : ", webDriver, test);
      if (!OnlineContentSettingsObject.rdbAddToHistory.isSelected()) {
        webAction.clickObject(OnlineContentSettingsObject.rdbAddToHistory, webDriver, test);
      }

      webAction.enterText(OnlineContentSettingsObject.cmbCompletionStatus, TEST_DATA_MAP.get("Completion Status"));
      webAction.presEnter(OnlineContentSettingsObject.cmbCompletionStatus, webDriver, test);
      webAction.checkBox(OnlineContentSettingsObject.chkCompleteContentOnLaunch, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Completion settings selected successfully : ", webDriver, test);
      webAction.clickObject(OnlineContentSettingsObject.lnkLaunch, webDriver, test);
      webAction.checkIfObjectIsDisplayed(OnlineContentSettingsObject.rdbContentIsAvailableForLaunch, 10, "Content is available for launch radio button displayed", "Content is available for launch radio button not displayed", webDriver, test);

      if (!OnlineContentSettingsObject.rdbContentIsAvailableForLaunch.isSelected()) {
        webAction.clickObject(OnlineContentSettingsObject.rdbContentIsAvailableForLaunch, webDriver, test);
      }

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Launch settings selected successfully : ", webDriver, test);
      webAction.clickObject(OnlineContentSettingsObject.btnOk, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Ok button clicked successfully : ", webDriver, test);

      Thread.sleep(5000);

      webAction.clickObject(OnlineContentSettingsObject.btnSave, webDriver, test);
      boolean okExists = webAction.checkIfObjectIsDisplayed(ItemDetailsObject.btnOKContent, 4, webDriver);

      if (okExists) {
        webAction.clickObject(ItemDetailsObject.btnOKContent, webDriver, test);
      }

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Content object successfully added to a learning item : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Adding content to learning item unsuccessful : " + e.getMessage(), webDriver, test);
    }
  }

  public void CreateOnlineItem(WebDriver webDriver, ExtentTest test) throws Exception {
    NewItem CreateAnOnlineItemObject = new NewItem(webDriver);
    LearningActivitiesNavigation LearningActivitiesObject = new LearningActivitiesNavigation(webDriver);
    Items ItemsObject = new Items(webDriver);
    webAction.waitForElement(LearningActivitiesObject.lnkLearningActivities, 5, webDriver);

    try {
      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      int size = webDriver.findElements(By.id("__iframe1__iframe")).size();
      WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.clickObject(ItemsObject.lnkAddNew, webDriver, test);

      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();
      reportInstance.ExtentLogPass("Add new button clicked successfully : ", webDriver, test);
      webAction.enterText(CreateAnOnlineItemObject.cmbItemClassification, TEST_DATA_MAP.get("Item Classification"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbItemClassification, webDriver, test);

      Thread.sleep(3000);

      webAction.enterText(CreateAnOnlineItemObject.cmbLocale, TEST_DATA_MAP.get("Locale"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbLocale, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbItemType, TEST_DATA_MAP.get("Item Type"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbItemType, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.txtTitle, TEST_DATA_MAP.get("Title"));

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.txtDuration, TEST_DATA_MAP.get("Duration (hours)"));

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbDeliveryMethod, TEST_DATA_MAP.get("Delivery Method"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbDeliveryMethod, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbSource, TEST_DATA_MAP.get("Source"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbSource, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.txtDescription, TEST_DATA_MAP.get("Description"));

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.txtSecurityDomain, TEST_DATA_MAP.get("Security Domain"));

      Thread.sleep(5000);

      webAction.enterText(CreateAnOnlineItemObject.cmbAssignmentType, TEST_DATA_MAP.get("Assignment Type"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbAssignmentType, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbLearningArea, TEST_DATA_MAP.get("Learning Area"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbLearningArea, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbComplianceRegulations, TEST_DATA_MAP.get("Compliance Regulations"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbComplianceRegulations, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbSetaCategory, TEST_DATA_MAP.get("SETA Category"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbSetaCategory, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbBEECategory, TEST_DATA_MAP.get("BEE Category"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbBEECategory, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbItemGrouping, TEST_DATA_MAP.get("BEE Category"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbItemGrouping, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Online content details captured successfully : ", webDriver, test);
      webAction.clickAction(CreateAnOnlineItemObject.btnSave, webDriver, test);

      Thread.sleep(4000);

      String sCreatedID = webAction.getElementText(CreateAnOnlineItemObject.lblItemId);

      if (sCreatedID.contains("Item")) {
        reportInstance.ExtentLogPass("Online Item Created, ID : " + sCreatedID, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Online Item not Created", webDriver, test);
      }

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add Online Item : " + e.getMessage(), webDriver, test);
    }
  }

  public void CreateAnOtherItem(WebDriver webDriver, ExtentTest test) throws Exception {
    NewItem CreateAnOnlineItemObject = new NewItem(webDriver);
    LearningActivitiesNavigation LearningActivitiesObject = new LearningActivitiesNavigation(webDriver);
    Items ItemsObject = new Items(webDriver);
    webAction.checkIfObjectIsDisplayed(LearningActivitiesObject.lnkLearningActivities, 10, "Learning Activities found ", "Learning Activities not found", webDriver, test);

    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(2000);

      webAction.clickObject(ItemsObject.lnkAddNew, webDriver, test);

      Thread.sleep(2000);

      webDriver.switchTo().defaultContent();
      reportInstance.ExtentLogPass("Add new button clicked successfully : ", webDriver, test);
      webAction.enterText(CreateAnOnlineItemObject.cmbItemClassification, TEST_DATA_MAP.get("Item Classification"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbItemClassification, webDriver, test);

      Thread.sleep(3000);

      webAction.enterText(CreateAnOnlineItemObject.cmbLocale, TEST_DATA_MAP.get("Locale"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbLocale, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbItemType, TEST_DATA_MAP.get("Item Type"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbItemType, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.txtTitle, TEST_DATA_MAP.get("Title"));

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.txtDuration, TEST_DATA_MAP.get("Duration (hours)"));

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbDeliveryMethod, TEST_DATA_MAP.get("Delivery Method"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbDeliveryMethod, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbSource, TEST_DATA_MAP.get("Source"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbSource, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.txtDescription, TEST_DATA_MAP.get("Description"));

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.txtSecurityDomain, TEST_DATA_MAP.get("Security Domain"));

      Thread.sleep(5000);

      webAction.enterText(CreateAnOnlineItemObject.cmbAssignmentType, TEST_DATA_MAP.get("Assignment Type"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbAssignmentType, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbLearningArea, TEST_DATA_MAP.get("Learning Area"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbLearningArea, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbComplianceRegulations, TEST_DATA_MAP.get("Compliance Regulations"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbComplianceRegulations, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbSetaCategory, TEST_DATA_MAP.get("SETA Category"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbSetaCategory, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbBEECategory, TEST_DATA_MAP.get("BEE Category"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbBEECategory, webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(CreateAnOnlineItemObject.cmbItemGrouping, TEST_DATA_MAP.get("Item Grouping"));
      webAction.presEnter(CreateAnOnlineItemObject.cmbItemGrouping, webDriver, test);

      Thread.sleep(6000);

      reportInstance.ExtentLogPass("Other details captured successfully : ", webDriver, test);
      webAction.clickAction(CreateAnOnlineItemObject.btnSave, webDriver, test);

      Thread.sleep(8000);

      String sCreatedID = webAction.getElementText(CreateAnOnlineItemObject.lblItemId);

      if (sCreatedID.contains("Item")) {
        reportInstance.ExtentLogPass("Other Item successfully added. The item number is: " + sCreatedID, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Other Item not Created", webDriver, test);
      }

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add Other Item : " + e.getMessage(), webDriver, test);
    }
  }

  public void SearchandSelectItemByID2(WebDriver webDriver, ExtentTest test) throws Exception {
    Items ItemsObject = new Items(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__iframe1__iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.waitForElement(ItemsObject.txtItemID2, 10, webDriver);
      webAction.enterText(ItemsObject.txtItemID2, TEST_DATA_MAP.get("ItemID"));
      webAction.clickObject(ItemsObject.btnSearch, webDriver, test);
      WebElement iframeElement2 = webDriver.findElement(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      webAction.waitForElement(ItemsObject.lnkItem2, 10, webDriver);
      webAction.clickObject(ItemsObject.lnkItem2, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Successfully searched and selected an Item: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The Item ID entered returned no information : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToReviseItem(WebDriver webDriver, ExtentTest test) throws Exception {
    ItemDetails ItemsObject = new ItemDetails(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      webAction.waitForElement(ItemsObject.lnkActions, 10, webDriver);
      webAction.clickObject(ItemsObject.lnkActions, webDriver, test);
      webAction.clickObject(ItemsObject.lnkRevise, webDriver, test);
      reportInstance.ExtentLogPass("Successfully Navigated to revise Item: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The navigation to revise item was not successful : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToFinalizeRevision(WebDriver webDriver, ExtentTest test) throws Exception {
    ItemDetails ItemsObject = new ItemDetails(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webAction.clickObject(ItemsObject.lnkActions, webDriver, test);
      webAction.clickObject(ItemsObject.lnkFinalizeRevision, webDriver, test);
      reportInstance.ExtentLogPass("Successfully Navigated to revise Item: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The navigation to revise item was not successful : " + e.getMessage(), webDriver, test);
    }
  }

  public void FinalizeRevisionandScheduleJob(WebDriver webDriver, ExtentTest test) throws Exception {
    ReviseItem ReviseObject = new ReviseItem(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.clickObject(ReviseObject.btnNextSubmit, webDriver, test);

      Thread.sleep(3000);

      if (webAction.elementExists(ReviseObject.btnNextSubmit)) {
        webAction.clickObject(ReviseObject.btnNextSubmit, webDriver, test);
      }

      webAction.waitForElement(ReviseObject.btnScheduleJobs, 10, webDriver);
      webAction.clickObject(ReviseObject.btnScheduleJobs, webDriver, test);
      webAction.waitForElement(ReviseObject.chkEmailNotification, 10, webDriver);
      webAction.clickObject(ReviseObject.chkEmailNotification, webDriver, test);
      webAction.waitForElement(ReviseObject.btnFinish, 10, webDriver);
      webAction.clickObject(ReviseObject.btnFinish, webDriver, test);

      int i;
      String sStatus = null;
      for (i = 0; i < 30; i++) {
        Thread.sleep(5000);
        sStatus = webAction.getElementText(ReviseObject.lblStatus);

        if (sStatus.equalsIgnoreCase("Succeeded")) {
          break;
        } else {
          Thread.sleep(5000);
        }
      }

      if (sStatus.equalsIgnoreCase("Succeeded")) {
        reportInstance.ExtentLogPass("Background Job Scheduled Successful : ", webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Background Job Scheduled uSuccessful: ", webDriver, test);
      }

      webAction.clickObject(ReviseObject.btnReturnToNewRevision, webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The navigation to revise item was not successful : " + e.getMessage(), webDriver, test);
    }
  }

  public String ReviseItem(WebDriver webDriver, ExtentTest test, String Type) throws Exception {
    ReviseItem ReviseObject = new ReviseItem(webDriver);
    String currentRevision = null;
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      String sDate = null;
      String sTime = null;

      if (TEST_DATA_MAP.get("NewRevisionDate").equalsIgnoreCase("Today")) {
        LocalDateTime ldt = LocalDateTime.now();
        sDate = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH).format(ldt);
      }

      if (TEST_DATA_MAP.get("NewRevisionTime").equalsIgnoreCase("Now")) {
        LocalDateTime ldt = LocalDateTime.now();
        sTime = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH).format(ldt);
      }

      assert sDate != null;
      webAction.enterText(ReviseObject.txtRevisionDate, sDate);

      assert sTime != null;
      webAction.enterText(ReviseObject.txtRevisionTime, sTime);

      webAction.SelectByVisibleText(ReviseObject.selTimeZone, TEST_DATA_MAP.get("TimeZone"), webDriver, test);
      currentRevision = webAction.getElementValue(ReviseObject.txtRevisionNumber);
      webAction.enterText(ReviseObject.txtRevisionNumber, TEST_DATA_MAP.get("RevisionNumber") + "/" + webAction.generateRandomValue(5));

      Thread.sleep(10000);

      webAction.clickObject(ReviseObject.btnNext, webDriver, test);
      webAction.waitForElement(ReviseObject.chkCopySub, 10, webDriver);
      webAction.clickObject(ReviseObject.chkCopySub, webDriver, test);
      webAction.clickObject(ReviseObject.btnNextSubmit, webDriver, test);

      if (!Type.equalsIgnoreCase("Production Ready")) {
        webAction.waitForElement(ReviseObject.rdbNotProdReady, 10, webDriver);
        webAction.clickObject(ReviseObject.rdbNotProdReady, webDriver, test);
        webAction.clickObject(ReviseObject.btnNextSubmit, webDriver, test);
        webAction.waitForElement(ReviseObject.btnFinish2, 10, webDriver);
        webAction.clickObject(ReviseObject.btnFinish2, webDriver, test);

      } else {
        webAction.waitForElement(ReviseObject.rdbProdReady, 10, webDriver);
        webAction.clickObject(ReviseObject.rdbProdReady, webDriver, test);
        webAction.clickObject(ReviseObject.btnNextSubmit, webDriver, test);
        FinalizeRevisionandScheduleJob(webDriver, test);
      }

      reportInstance.ExtentLogPass("Information captured and saved correctly on ReviseItem: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Information not captured and saved correctly on ReviseItem : " + e.getMessage(), webDriver, test);
    }

    return currentRevision;
  }

  public void ItemDetailsEditContent(WebDriver webDriver, ExtentTest test) throws Exception {
    ItemDetails ItemObject = new ItemDetails(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();

      Thread.sleep(10000);

      webAction.waitForElement(ItemObject.tbOnlineContent, 10, webDriver);
      webAction.clickObject(ItemObject.tbOnlineContent, webDriver, test);
      webAction.waitForElement(ItemObject.elpEditContentObject, 10, webDriver);
      webAction.clickObject(ItemObject.elpEditContentObject, webDriver, test);

      Thread.sleep(1000);

      webAction.clickObject(ItemObject.btnEdit, webDriver, test);
      webAction.waitForElement(ItemObject.txtTitle, 10, webDriver);

      if (TEST_DATA_MAP.get("ObjectTitle").equalsIgnoreCase("AUTO")) {
        webAction.enterText(ItemObject.txtTitle, TEST_DATA_MAP.get("ObjectTitle") + webAction.generateRandomString(5));
      } else {
        webAction.enterText(ItemObject.txtTitle, TEST_DATA_MAP.get("ObjectTitle"));
      }

      webAction.clickObject(ItemObject.btnOK, webDriver, test);
      webAction.waitForElement(ItemObject.btnSaveContent, 10, webDriver);
      webAction.clickObject(ItemObject.btnSaveContent, webDriver, test);
      boolean okExists = webAction.checkIfObjectIsDisplayed(ItemObject.btnOKContent, 4, webDriver);

      if (okExists) {
        webAction.clickObject(ItemObject.btnOKContent, webDriver, test);
      }

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Content Edited Successfully : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Content Edited Unsuccessfully : " + e.getMessage(), webDriver, test);
    }
  }

  public void RecordClassroomAttendance(WebDriver webDriver, ExtentTest test) throws Exception {
    Course CourseObject = new Course(webDriver);
    MarkAttendance MarkObject = new MarkAttendance(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      webAction.AngularWaits(webDriver);
      webAction.clickObject(CourseObject.tbRegistration, webDriver, test);
      webAction.AngularWaits(webDriver);
      boolean sStatus = webAction.checkIfObjectIsDisplayed(CourseObject.wblRegistrationStatus, 10, webDriver);

      if (sStatus) {
        reportInstance.ExtentLogPass("Status is : Active", webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Active status not found : ", webDriver, test);
      }

      webAction.clickObject(CourseObject.tbAgenda, webDriver, test);
      webAction.AngularWaits(webDriver);
      webAction.clickObject(CourseObject.btnMore, webDriver, test);

      Thread.sleep(1000);

      webAction.clickObject(CourseObject.lnkMarkAttendance, webDriver, test);
      webAction.AngularWaits(webDriver);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(MarkObject.iframeMarkAttendance, webDriver);
      webAction.enterText(MarkObject.txtComments, TEST_DATA_MAP.get("Comments"));
      webAction.clickObject(MarkObject.btnApplyChanges, webDriver, test);
      reportInstance.ExtentLogPass("Mark Attendance information displayed Successfully : " + sStatus, webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.clickObject(MarkObject.btnClose, webDriver, test);
      webAction.AngularWaits(webDriver);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Record Classroom Attendance Completed Successfully : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Record Classroom Attendance did not Complete : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddNewCohort(WebDriver webDriver, ExtentTest test) throws Exception {
    Cohorts CohortObject = new Cohorts(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      webAction.AngularWaits(webDriver);
      webAction.switchToFrame(CohortObject.iframeCohorts, webDriver);
      webAction.AngularWaits(webDriver);
      webAction.clickObject(CohortObject.btnAddNew, webDriver, test);
      reportInstance.ExtentLogPass("Navigation to add to Cohorts successful", webDriver, test);
      webAction.switchToFrame(CohortObject.iframeCohortAdd, webDriver);
      webAction.enterText(CohortObject.txtCohortID, TEST_DATA_MAP.get("CohortID") + webAction.generateRandomValue(5));
      webAction.enterText(CohortObject.txtDescription, TEST_DATA_MAP.get("Description"));
      webAction.clearTextField(CohortObject.txtSecurityDomain);
      webAction.enterText(CohortObject.txtSecurityDomain, TEST_DATA_MAP.get("SecurityDomain"));

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Cohorts Page Successfully Populated", webDriver, test);
      webAction.clickObject(CohortObject.btnAdd, webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement3 = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(5000);

      webAction.clickObject(CohortObject.btnApplyChanges, webDriver, test);
      reportInstance.ExtentLogPass("Cohort Addition Completed Successfully : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Cohort Addition did not Complete : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddUserToCohort(WebDriver webDriver, ExtentTest test) throws Exception {
    Cohorts CohortObject = new Cohorts(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      webAction.AngularWaits(webDriver);
      webAction.switchToFrame(CohortObject.iframeCohorts, webDriver);
      webAction.AngularWaits(webDriver);

      Thread.sleep(3000);

      webAction.enterText(CohortObject.txtCohortID2, TEST_DATA_MAP.get("CohortID"));
      webAction.clickObject(CohortObject.btnSearch, webDriver, test);
      webAction.AngularWaits(webDriver);

      WebElement iframeElement2 = webDriver.findElement(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Search for available Cohorts successful", webDriver, test);
      webAction.clickObject(CohortObject.imgEdit, webDriver, test);
      webAction.AngularWaits(webDriver);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement3 = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(5000);

      webAction.clickObject(CohortObject.tabUsers, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Navigation to add user to Cohorts successful", webDriver, test);
      webAction.enterText(CohortObject.txtUserID, TEST_DATA_MAP.get("UserName"));
      webAction.clickObject(CohortObject.btnAdd, webDriver, test);
      reportInstance.ExtentLogPass("added user successfully", webDriver, test);
      webAction.clickObject(CohortObject.btnApplyChanges2, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Adding a user to Cohort  Completed Successfully : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Cohort User Addition did not Complete : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddClassesToCohort(WebDriver webDriver, ExtentTest test) throws Exception {
    Cohorts CohortObject = new Cohorts(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      webAction.AngularWaits(webDriver);
      webAction.switchToFrame(CohortObject.iframeCohorts, webDriver);
      webAction.AngularWaits(webDriver);

      Thread.sleep(3000);

      webAction.enterText(CohortObject.txtCohortID2, TEST_DATA_MAP.get("CohortID"));
      webAction.clickObject(CohortObject.btnSearch, webDriver, test);
      webAction.AngularWaits(webDriver);
      WebElement iframeElement2 = webDriver.findElement(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Search for available Cohorts successful", webDriver, test);
      webAction.clickObject(CohortObject.imgEdit, webDriver, test);
      webAction.AngularWaits(webDriver);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement3 = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(5000);

      webAction.clickObject(CohortObject.tabClasses, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Navigation to add classes to Cohorts successful", webDriver, test);
      webAction.enterText(CohortObject.txtClassID, TEST_DATA_MAP.get("ClassID"));
      webAction.clickObject(CohortObject.chkAutoRegister, webDriver, test);
      webAction.clickObject(CohortObject.btnAdd, webDriver, test);
      reportInstance.ExtentLogPass("Class added successfully", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(CohortObject.btnApplyChanges3, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Adding a class to Cohort  Completed Successfully : ", webDriver, test);
    } catch (Exception e) {
      reportInstance.ExtentLogFail("Cohort class Addition did not Complete : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddSubstitute(WebDriver webDriver, ExtentTest test, String RevisionNumber) throws Exception {
    ItemDetails ItemObject = new ItemDetails(webDriver);
    Items ItemsObject = new Items(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(10000);

      webDriver.switchTo().defaultContent();
      webAction.waitForElement(ItemObject.lnkMoreOptions, 10, webDriver);
      webAction.clickObject(ItemObject.lnkMoreOptions, webDriver, test);
      webAction.waitForElement(ItemObject.tbSubstitutes, 10, webDriver);
      webAction.clickObject(ItemObject.tbSubstitutes, webDriver, test);

      Thread.sleep(5000);

      webAction.waitForElement(ItemObject.btnAddSubstitutes, 10, webDriver);
      webAction.clickObject(ItemObject.btnAddSubstitutes, webDriver, test);
      WebElement iframeElement = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.waitForElement(ItemsObject.txtItemID2, 10, webDriver);
      webAction.enterText(ItemsObject.txtItemID2, TEST_DATA_MAP.get("ItemID"));
      webAction.waitForElement(ItemObject.txtRevisionNumber, 10, webDriver);
      webAction.enterText(ItemObject.txtRevisionNumber, RevisionNumber);
      webAction.clickObject(ItemObject.rdbItemStatus, webDriver, test);
      webAction.clickObject(ItemObject.btnSearch, webDriver, test);
      webAction.waitForElement(ItemObject.selSabstituteStatus, 10, webDriver);
      webAction.SelectByVisibleText(ItemObject.selSabstituteStatus, TEST_DATA_MAP.get("SubstituteStatus"), webDriver, test);
      webAction.clickObject(ItemObject.chkAddSubstitute, webDriver, test);
      webAction.clickObject(ItemObject.btnAddSubstitute, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Substitute has been added for the old Item Successfully : ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Substitute has not been added for the old Item Successfully : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddNewProgram(WebDriver webDriver, ExtentTest test) throws Exception {
    Programs ProgramObject = new Programs(webDriver);
    AddNewProgram NewObject = new AddNewProgram(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);
      webAction.waitForElement(ProgramObject.lnkAddNew, 10, webDriver);
      webAction.clickObject(ProgramObject.lnkAddNew, webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement2 = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      webAction.enterText(NewObject.txtLocale, TEST_DATA_MAP.get("Locale"));
      String sProgramID;

      if (TEST_DATA_MAP.get("ProgramID").equalsIgnoreCase("AUTO")) {
        sProgramID = TEST_DATA_MAP.get("ProgramID") + webAction.generateRandomString(5);
      } else {
        sProgramID = TEST_DATA_MAP.get("ProgramID");
      }


      webAction.enterText(NewObject.txtProgramID, sProgramID);
      webAction.enterText(NewObject.txtTitle, TEST_DATA_MAP.get("Title") + webAction.generateRandomString(5));
      webAction.enterText(NewObject.txtSecurityDomainID, TEST_DATA_MAP.get("SecurityDomainID"));
      webAction.enterText(NewObject.txtAssignemntType, TEST_DATA_MAP.get("AssignmentType"));

      switch (TEST_DATA_MAP.get("Type").toUpperCase()) {
        case "OPEN-ENDED":
          webAction.clickObject(NewObject.rdbOpenEndedType, webDriver, test);
          break;

        case "DURATION-BASED":
          webAction.clickObject(NewObject.rdbDurationBased, webDriver, test);
          break;

        case "SCHEDULED":
          webAction.clickObject(NewObject.rdbScheduled, webDriver, test);
          break;
      }

      webAction.SelectByVisibleText(NewObject.selCompletionStatus, TEST_DATA_MAP.get("Completion Status"), webDriver, test);
      webAction.clickObject(NewObject.btnCreateProgram, webDriver, test);
      webDriver.switchTo().defaultContent();
      WebElement iframeElement3 = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(2000);

      webAction.checkIfObjectIsDisplayed(NewObject.wblCreatedID, 10, webDriver);
      String sCreatedID = webAction.getElementText(NewObject.wblCreatedID);

      if (sProgramID.equalsIgnoreCase(sCreatedID)) {
        reportInstance.ExtentLogPass("Program Created, ID : " + sProgramID, webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Program not Created", webDriver, test);
      }

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Program not Created :" + e.getMessage(), webDriver, test);
    }
  }

  public void SearchandSelectClassByID(WebDriver webDriver, ExtentTest test) throws Exception {
    Classes ClassesObject = new Classes(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webAction.AngularWaits(webDriver);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(ClassesObject.iframeClass, webDriver);
      webAction.AngularWaits(webDriver);
      webAction.enterText(ClassesObject.txtClassID, TEST_DATA_MAP.get("ClassID"));
      webAction.clickObject(ClassesObject.btnSearch, webDriver, test);
      webAction.AngularWaits(webDriver);

      Thread.sleep(5000);

      WebElement iframeElement2 = webDriver.findElement(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframeElement2);
      webAction.clickObject(ClassesObject.lnkClass, webDriver, test);
      reportInstance.ExtentLogPass("Successfully searched and selected a Class: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The Class ID entered returned no information : " + e.getMessage(), webDriver, test);
    }
  }

  public void SearchandSelectProgramByID(WebDriver webDriver, ExtentTest test) throws Exception {
    Programs ProgramObject = new Programs(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.enterText(ProgramObject.txtProgramID, TEST_DATA_MAP.get("ProgramID"));
      webAction.clickObject(ProgramObject.btnSearch, webDriver, test);
      WebElement iframeElement2 = webDriver.findElement(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      webAction.waitForElement(ProgramObject.lnkProgram, 10, webDriver);
      webAction.clickObject(ProgramObject.lnkProgram, webDriver, test);
      reportInstance.ExtentLogPass("Successfully searched and selected a Program: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("The Program ID entered returned no information : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddNewProgramAgenda(WebDriver webDriver, ExtentTest test) throws Exception {
    EditProgramAgenda AgendaObject = new EditProgramAgenda(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement3 = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(2000);

      webAction.waitForElement(AgendaObject.btnEdit, 10, webDriver);
      webAction.clickObject(AgendaObject.btnEdit, webDriver, test);
      webAction.waitForElement(AgendaObject.txtTitle, 10, webDriver);
      String sTitle = TEST_DATA_MAP.get("Title") + webAction.generateRandomString(5);
      webAction.enterText(AgendaObject.txtTitle, sTitle);
      webAction.clickObject(AgendaObject.btnSave, webDriver, test);
      webAction.clickObject(AgendaObject.btnDone, webDriver, test);
      webAction.waitForElement(AgendaObject.wblTitle, 10, webDriver);
      String sCreatedAgenda = webAction.getElementText(AgendaObject.wblTitle);

      if (sTitle.equalsIgnoreCase(sCreatedAgenda))
        reportInstance.ExtentLogPass("Agenda Saved, Title : " + sCreatedAgenda, webDriver, test);
      else
        reportInstance.ExtentLogFail("Agenda not Saved", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Agenda not Saved :" + e.getMessage(), webDriver, test);
    }
  }

  public void AddSectionsToAgenda(WebDriver webDriver, ExtentTest test) throws Exception {
    EditProgramAgenda AgendaObject = new EditProgramAgenda(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement3 = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(2000);

      webAction.waitForElement(AgendaObject.btnEdit, 10, webDriver);
      webAction.clickObject(AgendaObject.btnEdit, webDriver, test);
      webAction.waitForElement(AgendaObject.txtNumberOfActivities, 10, webDriver);
      webAction.clickObject(AgendaObject.txtNumberOfActivities, webDriver, test);
      webAction.clearField(AgendaObject.txtNumberOfActivities);
      webAction.enterText(AgendaObject.txtNumberOfActivities, TEST_DATA_MAP.get("NumberOfActivities"));
      webAction.clickObject(AgendaObject.btnAdd, webDriver, test);
      boolean activityName = webAction.checkIfObjectIsDisplayed(AgendaObject.txtActivityName, 3, webDriver);
      String sTitle = null;

      while (activityName) {
        sTitle = TEST_DATA_MAP.get("Title") + webAction.generateRandomString(5);
        webAction.enterText(AgendaObject.txtActivityName, sTitle);
        webAction.clickObject(AgendaObject.btnSave, webDriver, test);

        Thread.sleep(4000);

        activityName = webAction.checkIfObjectIsDisplayed(AgendaObject.txtActivityName, 3, webDriver);
      }

      webAction.clickObject(AgendaObject.btnDone, webDriver, test);
      webAction.waitForElement(AgendaObject.wblAgendaSection, 10, webDriver);
      String sCreatedAgenda = webAction.getElementText(AgendaObject.wblAgendaSection);

      if (sCreatedAgenda.contains(sTitle)) {
        reportInstance.ExtentLogPass("Sections Created", webDriver, test);
      } else {
        reportInstance.ExtentLogFail("Sections not Created", webDriver, test);
      }

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Sections not Created" + e.getMessage(), webDriver, test);
    }
  }

  public void AddActivitiesToAgenda(WebDriver webDriver, ExtentTest test) throws Exception {
    EditProgramAgenda AgendaObject = new EditProgramAgenda(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement3 = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(2000);

      webAction.waitForElement(AgendaObject.btnEdit, 10, webDriver);
      webAction.clickObject(AgendaObject.btnEdit, webDriver, test);
      boolean activityName = webAction.checkIfObjectIsDisplayed(AgendaObject.txtActivityName, 3, webDriver);

      if (activityName) {
        String sTitle = TEST_DATA_MAP.get("Title") + webAction.generateRandomString(5);
        webAction.enterText(AgendaObject.txtActivityName, sTitle);
        webAction.clickObject(AgendaObject.btnSave, webDriver, test);
      }

      Thread.sleep(2000);

      String sTitleActivity = null;

      if ("TEXT".equals(TEST_DATA_MAP.get("Activity").toUpperCase())) {
        webAction.waitForElement(AgendaObject.btnActivityTitle, 10, webDriver);
        webAction.clickObject(AgendaObject.btnActivityTitle, webDriver, test);
        webAction.waitForElement(AgendaObject.txtActivityName, 10, webDriver);
        webAction.clickObject(AgendaObject.txtActivityName, webDriver, test);
        webAction.clearField(AgendaObject.txtActivityName);
        sTitleActivity = TEST_DATA_MAP.get("Title") + webAction.generateRandomString(5);
        webAction.enterText(AgendaObject.txtActivityName, sTitleActivity);

        Thread.sleep(1000);

        webAction.clearField(AgendaObject.txtActivityDescription);
        webAction.enterText(AgendaObject.txtActivityDescription, TEST_DATA_MAP.get("Title"));
      }

      Thread.sleep(1000);

      webAction.clickObject(AgendaObject.btnSave, webDriver, test);

      Thread.sleep(4000);

      webAction.clickObject(AgendaObject.btnDone, webDriver, test);
      reportInstance.ExtentLogPass("Activity Added", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Activity not Added" + e.getMessage(), webDriver, test);
    }
  }

  public void AgendaTemplate(WebDriver webDriver, ExtentTest test) throws Exception {
    AgendaTemplate AgenderTemplateObject = new AgendaTemplate(webDriver);

    try {
      webAction.clickObject(AgenderTemplateObject.btnAdd, webDriver, test);
      webAction.enterText(AgenderTemplateObject.textDay, TEST_DATA_MAP.get("Days"));
      webAction.enterText(AgenderTemplateObject.textDuration, TEST_DATA_MAP.get("Duration"));
      webAction.clickObject(AgenderTemplateObject.btnSave, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(AgenderTemplateObject.btnedit, webDriver, test);

      int size = AgenderTemplateObject.listDescription.size();
      for (int i = 0; i < size; i++) {
        webAction.enterText(AgenderTemplateObject.listDescription.get(i), TEST_DATA_MAP.get("Description"));
      }

      webAction.clickObject(AgenderTemplateObject.btnSaveEdit, webDriver, test);
      reportInstance.ExtentLogPass("Agenda Template successfully added ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add AgendaTemplate : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddCategory(WebDriver webDriver, ExtentTest test) throws Exception {
    Category AddCategoryObject = new Category(webDriver);

    try {
      webAction.clickObject(AddCategoryObject.btnCategory, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(AddCategoryObject.btnAdd, webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(AddCategoryObject.iframeCategory, webDriver);
      webAction.checkBox(AddCategoryObject.selectCategory, webDriver, test);
      webAction.clickObject(AddCategoryObject.btnAddCategory, webDriver, test);
      webDriver.switchTo().defaultContent();
      reportInstance.ExtentLogPass("Category successfully added ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add Category : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddInstructor(WebDriver webDriver, ExtentTest test) throws Exception {
    Instructor AddInstructorObject = new Instructor(webDriver);

    try {
      webAction.clickObject(AddInstructorObject.btnInstructor, webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(AddInstructorObject.iframeInstructor, webDriver);
      webAction.clickObject(AddInstructorObject.lnkSearch, webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(AddInstructorObject.btnSearch, webDriver, test);

      Thread.sleep(2000);

      webAction.checkBox(AddInstructorObject.checkBoxInstuctor, webDriver, test);
      webAction.checkBox(AddInstructorObject.btnAdd, webDriver, test);

      webDriver.switchTo().defaultContent();
      reportInstance.ExtentLogPass("Instructor successfully added ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add Instructor : " + e.getMessage(), webDriver, test);
    }
  }

  public void EditCoverPage(WebDriver webDriver, ExtentTest test) throws Exception {
    CoverPage EditCoverPageObject = new CoverPage(webDriver);

    try {
      webAction.clickObject(EditCoverPageObject.btnAction, webDriver, test);
      webAction.clickObject(EditCoverPageObject.btnCoverPage, webDriver, test);
      webAction.switchToTab(1, webDriver);
      webAction.clickObject(EditCoverPageObject.btnSave, webDriver, test);
      webAction.clickObject(EditCoverPageObject.btnAvtivate, webDriver, test);
      reportInstance.ExtentLogPass("Cover Page successfully Activated ", webDriver, test);
      webAction.clickObject(EditCoverPageObject.btnSaveAndClose, webDriver, test);
      webAction.switchToTab(0, webDriver);
      webDriver.switchTo().defaultContent();
      reportInstance.ExtentLogPass("Cover Page successfully added ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to Edit Cover Page : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddPricingToItem(WebDriver webDriver, ExtentTest test) throws Exception {
    Pricing AddPricingToItemObject = new Pricing(webDriver);

    try {
      webAction.clickObject(AddPricingToItemObject.btnPricing, webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(AddPricingToItemObject.iframePricing, webDriver);
      webAction.clickObjectTextFromList(AddPricingToItemObject.selectType, TEST_DATA_MAP.get("PricingType"), webDriver, test);
      webAction.enterText(AddPricingToItemObject.textPricing, TEST_DATA_MAP.get("Pricing"));
      webAction.clickObject(AddPricingToItemObject.btnApplChanges, webDriver, test);
      webDriver.switchTo().defaultContent();
      reportInstance.ExtentLogPass("Pricing Item successfully added ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add Pricing To Item : " + e.getMessage(), webDriver, test);
    }
  }

  public void SearchClasses(WebDriver webDriver, ExtentTest test) throws Exception {
    ClassesObjects classesObjects = new ClassesObjects(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.xpath("//iframe[contains(@src,'search')]"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.enterText(classesObjects.txtClassID, TEST_DATA_MAP.get("Class ID"));

      Thread.sleep(3000);

      webAction.clickObject(classesObjects.btnSearch, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Class ID searched successfully: ", webDriver, test);

      Thread.sleep(3000);

      WebElement iframeElement2 = webDriver.findElement(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      webAction.waitForElement(classesObjects.lnkClassID, 10, webDriver);
      webAction.clickObject(classesObjects.lnkClassID, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Course clicked successfully: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to search for classes : " + e.getMessage(), webDriver, test);
    }
  }


  public void NavigateToLearningHistoryForMultipleCourses(WebDriver webDriver, ExtentTest test) throws Exception {
    ManageUserLearningNavigation ManageUserLearningNavigationObject = new ManageUserLearningNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);
    webAction.waitForElement(ManageUserLearningNavigationObject.btnManageUserLearning, 5000, webDriver);

    try {
      Thread.sleep(3000);

      webAction.clickObject(ManageUserLearningNavigationObject.btnManageUserLearning, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Navigation to Manage User Learning tab Successful : ", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(ManageUserLearningNavigationObject.btnLearningHistoryMultipleCourse, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Navigation to Add to Learning History for Multiple Courses tab Successful ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to navigate to Manage User : " + e.getMessage(), webDriver, test);
    }
  }

  public void AddToLearningHistory(WebDriver webDriver, ExtentTest test) throws Exception {
    AddLearningHistoryObjects learningHistoryObject = new AddLearningHistoryObjects(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.id("__loader0_iframe"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(2000);

      webAction.SelectByVisibleText(learningHistoryObject.cmbItemType, TEST_DATA_MAP.get("Item Type"), webDriver, test);
      webAction.enterText(learningHistoryObject.txtItemId, TEST_DATA_MAP.get("Item ID"));

      Thread.sleep(3000);

      webAction.clickObject(learningHistoryObject.btnAddItem, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Item added to items list successfully", webDriver, test);

      Thread.sleep(3000);

      webAction.enterText(learningHistoryObject.btnUserID, TEST_DATA_MAP.get("User ID"));

      Thread.sleep(5000);

      webAction.clickObject(learningHistoryObject.btnAddUser, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("User added to users list successfully", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(learningHistoryObject.btnNext, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Next button clicked successfully", webDriver, test);
      webAction.waitForElement(learningHistoryObject.cmbTimezone, 5000, webDriver);
      webAction.SelectByVisibleText(learningHistoryObject.cmbTimezone, TEST_DATA_MAP.get("Time Zone"), webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(learningHistoryObject.txtGrade, TEST_DATA_MAP.get("Grade"));

      Thread.sleep(2000);

      webAction.SelectByVisibleText(learningHistoryObject.cmbCompletionStatus, TEST_DATA_MAP.get("Completion status"), webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Edit details successfully captured", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(learningHistoryObject.btnApplyChanges, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Edit details applied successfully", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(learningHistoryObject.btnNext2, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Next button clicked successfully", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(learningHistoryObject.btnSubmit, webDriver, test);

      Thread.sleep(8000);

      reportInstance.ExtentLogPass("The History Records were added successfully.", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add History Records" + e.getMessage(), webDriver, test);
    }
  }

  public void SearchSurvey(WebDriver webDriver, ExtentTest test) throws Exception {
    Survey surveyObject = new Survey(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.xpath("//iframe[contains(@src,'search')]"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(3000);

      webAction.enterText(surveyObject.txtSurveyID, TEST_DATA_MAP.get("Survey ID"));

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Survey ID captured: ", webDriver, test);
      webAction.clickObject(surveyObject.btnSearch, webDriver, test);

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Search button clicked successfully: ", webDriver, test);

      Thread.sleep(3000);

      int size2 = webDriver.findElements(By.id("getPathBuffer")).size();
      WebElement iframeElement2 = webDriver.findElement(By.id("getPathBuffer"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      webAction.waitForElement(surveyObject.btnEdit, 10, webDriver);
      webAction.clickObject(surveyObject.btnEdit, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Edit button clicked successfully: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to search for the survey : " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToSurvey(WebDriver webDriver, ExtentTest test) throws Exception {
    ContentNavigation NavigationObject = new ContentNavigation(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {

      webAction.clickObject(NavigationObject.lnkContent, webDriver, test);
      reportInstance.ExtentLogPass("Content tab clicked Successfully: ", webDriver, test);

      Thread.sleep(8000);

      webAction.clickObject(NavigationObject.lnkSurveysObjects, webDriver, test);

      Thread.sleep(8000);

      reportInstance.ExtentLogPass("Navigation to Surveys Successful: ", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Navigation to Surveys Unsuccessful: " + e.getMessage(), webDriver, test);
    }
  }

  public void AddItemToTheSurvey(WebDriver webDriver, ExtentTest test) throws Exception {
    EditSurvey editSurveyObject = new EditSurvey(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      int siz3 = webDriver.findElements(By.xpath("//iframe[contains(@src,'survey')]")).size();
      WebElement iframeElement3 = webDriver.findElement(By.xpath("//iframe[contains(@src,'survey')]"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(2000);

      webAction.clickObject(editSurveyObject.btnItemUsage, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Item usage button clicked successfully", webDriver, test);

      Thread.sleep(5000);

      webAction.SelectByVisibleText(editSurveyObject.cmbItemType, TEST_DATA_MAP.get("Item Type"), webDriver, test);
      webAction.enterText(editSurveyObject.txtItemID, TEST_DATA_MAP.get("Item ID"));

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Item type and Item Id are successfully added", webDriver, test);

      Thread.sleep(3000);

      webAction.clickObject(editSurveyObject.btnAdd, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("Item Added", webDriver, test);

      Thread.sleep(3000);

      String parentWindowHandle = webDriver.getWindowHandle();
      webAction.clickObject(editSurveyObject.btnRunReport, webDriver, test);

      Thread.sleep(20000);

      Set<String> onlineHandles = webDriver.getWindowHandles();

      for (String x : onlineHandles) {
        if (!x.equals(parentWindowHandle)) {
          webDriver.switchTo().window(x);

          Thread.sleep(2000);

          reportInstance.ExtentLogPass("Run Report clicked: ", webDriver, test);
          break;
        }
      }

      Thread.sleep(2000);

      webDriver.close();

      for (String x : onlineHandles) {
        if (x.equals(parentWindowHandle)) {
          webDriver.switchTo().window(x);
          break;
        }

        System.out.println(x);
      }

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Item added to the survey successfully", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add an item to the survey" + e.getMessage(), webDriver, test);
    }
  }

  public void CreateQuestionnaireSurveyAndAddQuestions(WebDriver webDriver, ExtentTest test) throws Exception {
    QuestionnaireSurvey questionnaireSurveyObjects = new QuestionnaireSurvey(webDriver);
    AddNewSurvey addNewSurveyObjects = new AddNewSurvey(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      WebElement iframeElement = webDriver.findElement(By.xpath("//iframe[contains(@src,'search')]"));
      webDriver.switchTo().frame(iframeElement);

      Thread.sleep(2000);

      webAction.clickObject(addNewSurveyObjects.lnkAddNew, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Add new link clicked successfully", webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();
      WebElement iframeElement2 = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/iframe"));
      webDriver.switchTo().frame(iframeElement2);

      Thread.sleep(3000);

      webAction.enterText(addNewSurveyObjects.txtSurveyID, TEST_DATA_MAP.get("Survey ID"), webDriver, test);

      Thread.sleep(1000);

      webAction.enterText(addNewSurveyObjects.txtSurveyName, TEST_DATA_MAP.get("Survey Name"), webDriver, test);

      Thread.sleep(1000);

      webAction.SelectByVisibleText(addNewSurveyObjects.cmbSurveyType, TEST_DATA_MAP.get("Survey Type"), webDriver, test);

      Thread.sleep(2000);

      webAction.clearTextField(addNewSurveyObjects.txtSecurityDomain);
      webAction.enterText(addNewSurveyObjects.txtSecurityDomain, TEST_DATA_MAP.get("Security Domain"), webDriver, test);

      Thread.sleep(1000);

      webAction.clickObject(addNewSurveyObjects.btnAdd, webDriver, test);

      Thread.sleep(5000);

      reportInstance.ExtentLogPass("New survey created successfully", webDriver, test);
      Thread.sleep(2000);

      webDriver.switchTo().defaultContent();
      int size3 = webDriver.findElements(By.id("__loader1_iframe")).size();
      WebElement iframeElement3 = webDriver.findElement(By.id("__loader1_iframe"));
      webDriver.switchTo().frame(iframeElement3);

      Thread.sleep(2000);

      webAction.waitForElement(questionnaireSurveyObjects.btnQuestions, 5, webDriver);
      webAction.clickObject(questionnaireSurveyObjects.btnQuestions, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Questions tab clicked successfully ", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(questionnaireSurveyObjects.btnAddQuestion, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Add question icon clicked successfully ", webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(questionnaireSurveyObjects.txtQuestion, TEST_DATA_MAP.get("Question 1"), webDriver, test);

      Thread.sleep(3000);

      webAction.SelectByVisibleText(questionnaireSurveyObjects.cmbQuestionType, TEST_DATA_MAP.get("Question Type"), webDriver, test);

      Thread.sleep(2000);

      webAction.SelectByVisibleText(questionnaireSurveyObjects.cmbRatingScale, TEST_DATA_MAP.get("Rating Scale"), webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("First question added successfully", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(questionnaireSurveyObjects.btnAddQuestion, webDriver, test);

      Thread.sleep(2000);

      webAction.scrollPageToElement(questionnaireSurveyObjects.txtQuestion2, webDriver);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Add question icon clicked successfully ", webDriver, test);

      Thread.sleep(2000);

      webAction.enterText(questionnaireSurveyObjects.txtQuestion2, TEST_DATA_MAP.get("Question 2"), webDriver, test);

      Thread.sleep(3000);

      webAction.SelectByVisibleText(questionnaireSurveyObjects.cmbQuestionType2, TEST_DATA_MAP.get("Question 2 Type"), webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Second question added successfully", webDriver, test);

      Thread.sleep(2000);

      webAction.clickObject(questionnaireSurveyObjects.btnPublish, webDriver, test);

      Thread.sleep(6000);

      reportInstance.ExtentLogPass("Questions published successfully", webDriver, test);

      Thread.sleep(2000);

      String parentWindowHandle = webDriver.getWindowHandle();
      webAction.clickObject(questionnaireSurveyObjects.btnPreview, webDriver, test);

      Thread.sleep(5000);

      webDriver.switchTo().defaultContent();

      Thread.sleep(2000);

      Set<String> onlineHandles = webDriver.getWindowHandles();

      for (String x : onlineHandles) {
        if (!x.equals(parentWindowHandle)) {
          webDriver.switchTo().window(x);

          Thread.sleep(4000);

          reportInstance.ExtentLogPass("Survey questions added successfully.", webDriver, test);
          webAction.clickObject(questionnaireSurveyObjects.btnCloseWindow, webDriver, test);

          Thread.sleep(5000);
          break;
        }
      }

      for (String x : onlineHandles) {
        if (x.equals(parentWindowHandle)) {
          webDriver.switchTo().window(x);
          break;
        }

        System.out.println(x);
      }

      reportInstance.ExtentLogPass("Survey Questionnaire window closed successfully.", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to create questionnaire to survey" + e.getMessage(), webDriver, test);
    }
  }

  public void CreateAClassFromWithinLearningItem(WebDriver webDriver, ExtentTest test) throws Exception {
    CreateAClassWithinLearningItem classWithinLearningItem = new CreateAClassWithinLearningItem(webDriver);
    ScheduleObjects scheduleObjects = new ScheduleObjects(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      Thread.sleep(3000);

      webAction.clickObject(classWithinLearningItem.lnkAgenda, webDriver, test);

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Class from within learning item is created successfully.", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to create a class from within learning item " + e.getMessage(), webDriver, test);
    }
  }

  public void MyLearning(WebDriver webDriver, ExtentTest test) throws Exception {
    MyLearning MyLearningObject = new MyLearning(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webAction.clickObject(MyLearningObject.linkBrowsAllCourses, webDriver, test);
      webAction.checkIfObjectIsDisplayed(MyLearningObject.lblLibrary, 10, "Navigate To Library Page Successful", "Navigate To Library Page Unsuccessful", webDriver, test);
      webAction.AngularWaits(webDriver);
      webAction.enterText(MyLearningObject.textSearch, TEST_DATA_MAP.get("Search"));
      webAction.clickObject(MyLearningObject.iconSearch, webDriver, test);
      webAction.AngularWaits(webDriver);
      String itemName = TEST_DATA_MAP.get("Search");
      WebElement item = webDriver.findElement(By.xpath("(//a[normalize-space()='" + itemName + "'])[1]"));
      webAction.checkIfObjectTextExist(item, itemName, "Course displayed ", "Course Not displayed", webDriver, test);
      webAction.clickObject(MyLearningObject.linkStartCourse, webDriver, test);
      webAction.AngularWaits(webDriver);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(MyLearningObject.iframeCourse, webDriver);
      webAction.AngularWaits(webDriver);
      webAction.checkIfObjectIsDisplayed(MyLearningObject.btnBack, 10, "Course Page Loaded successfully", "Course Page Load Unsuccessful", webDriver, test);
      webAction.clickObject(MyLearningObject.btnBack, webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.clickObject(MyLearningObject.linkMyLearning, webDriver, test);
      webAction.checkIfObjectIsDisplayed(MyLearningObject.lblLearning, 15, "Navigation To Learning Successful ", "Learning screen not displayed", webDriver, test);
      webAction.AngularWaits(webDriver);
      webAction.checkIfObjectIsDisplayed(MyLearningObject.lblLearning, 10, "Navigation To Learning Successful ", "Learning screen not displayed", webDriver, test);
      WebElement item2 = webDriver.findElement(By.xpath("(//a[normalize-space()='" + itemName + "'])[1]"));
      webAction.checkIfObjectTextExist(item2, itemName, "Course displayed in self assigned", "Course Not displayed in self assigned", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to Navigation to Assessment Page " + e.getMessage(), webDriver, test);
    }
  }

  public void NavigateToAssessment(WebDriver webDriver, ExtentTest test) throws Exception {
    NavigateToAssessment NavigateToAssessmentObject = new NavigateToAssessment(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webAction.clickObject(NavigateToAssessmentObject.lnkContent, webDriver, test);
      webAction.clickObject(NavigateToAssessmentObject.lnkAssessments, webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(NavigateToAssessmentObject.iframeAssessment, webDriver);

      if (webAction.checkIfObjectIsDisplayed(NavigateToAssessmentObject.lblAssessment, 10, webDriver)) {
        reportInstance.ExtentLogPass("Navigation to Assessment Page Successfully", webDriver, test);
      }

      webDriver.switchTo().defaultContent();

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to Navigation to Assessment Page " + e.getMessage(), webDriver, test);
    }
  }

  public void AddNewAssessmentQuiz(WebDriver webDriver, ExtentTest test) throws Exception {
    AddNewQuiz AddNewQuizObject = new AddNewQuiz(webDriver);
    webAction.waitForPageLoaded(webDriver);

    try {
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(AddNewQuizObject.iframeAssessment, webDriver);
      webAction.clickObject(AddNewQuizObject.lnkAddNew, webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(AddNewQuizObject.iframeAddAssessment, webDriver);

      if (webAction.checkIfObjectIsDisplayed(AddNewQuizObject.lblAddNewAssessment, 10, "Add New assessment screen displayed", "Add New assessment screen not displayed", webDriver, test)) {
        webAction.clickObject(AddNewQuizObject.btnAddNewQuiz, webDriver, test);
      }

      if (webAction.checkIfObjectIsDisplayed(AddNewQuizObject.lblAddNewQuiz, 10, "Add New quiz screen displayed", "Add New quiz screen not displayed", webDriver, test)) {
        webAction.enterText(AddNewQuizObject.textQuizID, webAction.generateRandomValue(4));
        webAction.enterText(AddNewQuizObject.textQuizName, TEST_DATA_MAP.get("QuizName") + "-" + webAction.generateRandomValue(3));
        webAction.enterText(AddNewQuizObject.textDescription, TEST_DATA_MAP.get("Description"));
        webAction.clickObject(AddNewQuizObject.btnCreate, webDriver, test);
      }

      if (webAction.checkIfObjectIsDisplayed(AddNewQuizObject.lblOverview, 10, "Overview screen displayed", "Overview screen not displayed", webDriver, test)) {
        webAction.clickObject(AddNewQuizObject.btnMenu, webDriver, test);
        webAction.clickObject(AddNewQuizObject.btnSettings, webDriver, test);
      }

      if (webAction.checkIfObjectIsDisplayed(AddNewQuizObject.lblSettings, 10, "Settings screen displayed", "Settings screen not displayed", webDriver, test)) {
        webAction.enterText(AddNewQuizObject.textPassingPercentage, TEST_DATA_MAP.get("PassingPercentage"));
        webAction.clickObject(AddNewQuizObject.btnSave, webDriver, test);
        webAction.clickObject(AddNewQuizObject.btnMenu, webDriver, test);
        webAction.clickObject(AddNewQuizObject.btnQuestions, webDriver, test);
      }

      if (webAction.checkIfObjectIsDisplayed(AddNewQuizObject.lblQuestions, 10, "Questions screen displayed", "Questions screen not displayed", webDriver, test)) {
        webAction.clickObject(AddNewQuizObject.btnAddQuestion, webDriver, test);

        Thread.sleep(2000);

        webAction.enterText(AddNewQuizObject.textQuestion, TEST_DATA_MAP.get("Question"));
        webAction.enterText(AddNewQuizObject.textAnswerOne, TEST_DATA_MAP.get("AnswerOne"));
        webAction.enterText(AddNewQuizObject.textAnswerTwo, TEST_DATA_MAP.get("AnswerTwo"));
        webAction.enterText(AddNewQuizObject.textAnswerThree, TEST_DATA_MAP.get("AnswerThree"));
        webAction.enterText(AddNewQuizObject.textAnswerFour, TEST_DATA_MAP.get("AnswerFour"));
        webAction.clickObject(AddNewQuizObject.btnQuestionSave, webDriver, test);
        reportInstance.ExtentLogPass(" Questions Details saved Successfully", webDriver, test);
        webAction.clickObject(AddNewQuizObject.btnMenu, webDriver, test);
        webAction.clickObject(AddNewQuizObject.btnQuizOverview, webDriver, test);
      }

      if (webAction.checkIfObjectIsDisplayed(AddNewQuizObject.lblOverview, 10, "Overview screen displayed", "Overview screen not displayed", webDriver, test)) {
        webAction.clickObject(AddNewQuizObject.btnPublish, webDriver, test);

        if (webAction.checkIfObjectIsDisplayed(AddNewQuizObject.btnConfirm, 5, webDriver)) {
          webAction.clickObject(AddNewQuizObject.btnConfirm, webDriver, test);
        }

        Thread.sleep(2000);

        webAction.clickObject(AddNewQuizObject.btnPreview, webDriver, test);

        Thread.sleep(2000);
      }

      webDriver.switchTo().defaultContent();
      webAction.switchToNewTab(webDriver);

      if (webAction.checkIfObjectIsDisplayed(AddNewQuizObject.btnStart, 10, webDriver)) {
        Thread.sleep(2000);

        reportInstance.ExtentLogPass(" Assessment Quiz Added Successfully", webDriver, test);
      }

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to Add Assessment Quiz :" + e.getMessage(), webDriver, test);
    }
  }

  public void CreateSchedule(WebDriver webDriver, ExtentTest test) throws Exception {
    CreateSchedule CreateScheduleObject = new CreateSchedule(webDriver);
    webAction.waitForPageLoaded(webDriver);
    webDriver.switchTo().defaultContent();

    try {
      if (webAction.checkIfObjectIsDisplayed(CreateScheduleObject.btnAction, 10, webDriver)) {
        webAction.clickObject(CreateScheduleObject.btnAction, webDriver, test);
        webAction.clickObject(CreateScheduleObject.btnSchedule, webDriver, test);
      }

      Thread.sleep(2000);

      reportInstance.ExtentLogPass("Schedule clicked successfully", webDriver, test);
      webDriver.switchTo().defaultContent();
      webAction.switchToFrame(CreateScheduleObject.iframeSchedule, webDriver);

      if (webAction.checkIfObjectIsDisplayed(CreateScheduleObject.textDescription, 10, webDriver)) {
        webAction.enterText(CreateScheduleObject.textDescription, TEST_DATA_MAP.get("Description"), webDriver, test);
        webAction.enterText(CreateScheduleObject.textStartDate, getTimeStampCustom(), webDriver, test);
        webAction.enterText(CreateScheduleObject.textStartTime, TEST_DATA_MAP.get("StartTime"), webDriver, test);
        webAction.clickObject(CreateScheduleObject.textTimeZone, webDriver, test);
        String timeZone = TEST_DATA_MAP.get("TimeZone");
        webDriver.findElement(By.xpath("//option[@title='" + timeZone + "']")).click();

        Thread.sleep(3000);

        webAction.clickObject(CreateScheduleObject.textFacility, webDriver, test);
        String Facility = TEST_DATA_MAP.get("Facility");
        webDriver.findElement(By.xpath("//option[@title='" + Facility + "']")).click();

        Thread.sleep(3000);

        webAction.clickObject(CreateScheduleObject.textPrimaryLocation, webDriver, test);
        String PrimaryLocation = TEST_DATA_MAP.get("PrimaryLocation");
        webDriver.findElement(By.xpath("//option[@title='" + PrimaryLocation + "']")).click();

        Thread.sleep(3000);

        webAction.enterText(CreateScheduleObject.textPrimaryInstructor, TEST_DATA_MAP.get("PrimaryInstructor"), webDriver, test);

        Thread.sleep(3000);

        reportInstance.ExtentLogPass("Schedule data captured successfully", webDriver, test);

        Thread.sleep(3000);

        webAction.clickObject(CreateScheduleObject.btnSave, webDriver, test);
      }

      if (webAction.checkObject(CreateScheduleObject.btnYes, 5, webDriver)) {
        webAction.clickObject(CreateScheduleObject.btnYes, webDriver, test);
      }

      if (webAction.checkObject(CreateScheduleObject.btnSave, 5, webDriver)) {
        webAction.clickObject(CreateScheduleObject.btnSave, webDriver, test);
      }

      if (webAction.checkObject(CreateScheduleObject.btnYes, 5, webDriver)) {
        webAction.clickObject(CreateScheduleObject.btnYes, webDriver, test);
      }

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Save button clicked successfully", webDriver, test);

      Thread.sleep(3000);

      webDriver.switchTo().defaultContent();

      if (webAction.checkIfObjectIsDisplayed(CreateScheduleObject.textContactName, 20, webDriver)) {
        webAction.enterText(CreateScheduleObject.textContactName, TEST_DATA_MAP.get("ContactName"), webDriver, test);
        webAction.enterText(CreateScheduleObject.textContactEmail, TEST_DATA_MAP.get("ContactEmail"), webDriver, test);
        webAction.enterText(CreateScheduleObject.textTraningCompanyName, TEST_DATA_MAP.get("TraningCompanyName"), webDriver, test);
        CreateScheduleObject.rdEmailconfirmationUser.isSelected();
        CreateScheduleObject.rdEmailconfirmationContacts.isSelected();
        CreateScheduleObject.rdEmailconfirmationInstructor.isSelected();
        CreateScheduleObject.rdEmailconfirmationManager.isSelected();
        webAction.clickObjectActionSVG(CreateScheduleObject.rdSelfEnrolment, webDriver, test);
        webAction.scrollPageToElement(CreateScheduleObject.lblComplition, webDriver);
        webAction.clickObjectActionSVG(CreateScheduleObject.rdAprovalRequired, webDriver, test);
        webAction.clickObjectActionSVG(CreateScheduleObject.rdAprovalRequiredToWithdrow, webDriver, test);
        webAction.clickObject(CreateScheduleObject.btnSaveDetails, webDriver, test);
      }

      Thread.sleep(3000);

      reportInstance.ExtentLogPass("Schedule Added successfully", webDriver, test);

    } catch (Exception e) {
      reportInstance.ExtentLogFail("Failed to add Schedule" + e.getMessage(), webDriver, test);
    }
  }
}
