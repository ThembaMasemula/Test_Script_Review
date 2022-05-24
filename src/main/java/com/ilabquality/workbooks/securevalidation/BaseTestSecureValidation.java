package com.ilabquality.workbooks.securevalidation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ilabquality.dataprovider.JsonDataProvider;
import com.ilabquality.pages.AddressesPage;
import com.ilabquality.pages.AdminCenterPage;
import com.ilabquality.pages.ChangeHistoryPage;
import com.ilabquality.pages.ChangeJobAndCompensationInfoPage;
import com.ilabquality.pages.ContactInformationPage;
import com.ilabquality.pages.EmployeeProfilePage;
import com.ilabquality.pages.LoginPage;
import com.ilabquality.pages.PaymentInformationPage;
import com.ilabquality.pages.PersonalInformationPage;
import com.ilabquality.pages.PleaseConfirmYourRequestPage;
import com.ilabquality.pages.PrimaryEmergencyContactPage;
import com.ilabquality.utilities.BaseTest;
import com.ilabquality.workbooks.HREmployeeDataWorkbook;

public class BaseTestSecureValidation extends BaseTest {

	protected static Map<String, Map<String, Map<String, String>>> mapOfSheets = new HashMap<String, Map<String, Map<String, String>>>();

	protected JsonDataProvider global;
	protected JsonDataProvider testData;

	// Global Variables
	protected String url;
	protected String username;
	protected String password;

	// PageObjects
	protected AdminCenterPage adminCenterPage;
	protected AddressesPage addressesPage;
	protected ChangeJobAndCompensationInfoPage changeJobCompensationInfoPage;
	protected ChangeHistoryPage changeHistoryPage;
	protected ContactInformationPage contactInformationPage;
	protected EmployeeProfilePage employeeProfilePage;
	protected LoginPage loginPage;
	protected PaymentInformationPage paymentInformation;
	protected PersonalInformationPage personalInformationPage;
	protected PleaseConfirmYourRequestPage pleaseConfirmYourRequestPage;
	protected PrimaryEmergencyContactPage primaryEmergencyContact;

	protected static Map<String, Integer> profiles = new HashMap<String, Integer>();
	protected static List<List<String[]>> parameters = new ArrayList<List<String[]>>();
	protected static String sh;

	protected static boolean parametersOk = false;

	protected static List<String> errorsToLogin = new ArrayList<String>();
	protected static List<String> logSystemAdmin = new ArrayList<String>();
	protected static List<String> logEmpOnOthers = new ArrayList<String>();
	protected static List<String> logEmpOnThemselves = new ArrayList<String>();
	protected static List<String> logManager = new ArrayList<String>();
	protected static List<String> log2lvlManager = new ArrayList<String>();
	protected static List<String> logHrManager = new ArrayList<String>();
	protected static List<String> logHrAdmin = new ArrayList<String>();
	protected static List<String> logGlobalMobility = new ArrayList<String>();
	protected static List<String> logSecurityAdmin = new ArrayList<String>();

	protected static List<String> logSystemAdminOk = new ArrayList<String>();
	protected static List<String> logEmpOnOthersOk = new ArrayList<String>();
	protected static List<String> logEmpOnThemselvesOk = new ArrayList<String>();
	protected static List<String> logManagerOk = new ArrayList<String>();
	protected static List<String> log2lvlManagerOk = new ArrayList<String>();
	protected static List<String> logHrManagerOk = new ArrayList<String>();
	protected static List<String> logHrAdminOk = new ArrayList<String>();
	protected static List<String> logGlobalMobilityOk = new ArrayList<String>();
	protected static List<String> logSecurityAdminOk = new ArrayList<String>();

	// protected HREmployeeDataWorkbook hrEmployeeDataWb;

	protected List<String> viewCurrent = new ArrayList<String>();
	protected List<String> viewHistory = new ArrayList<String>();
	protected List<String> addData = new ArrayList<String>();
	protected List<String> hide = new ArrayList<String>();

	/*
	@BeforeClass
	public static void beforeEach() throws EncryptedDocumentException, IOException {
		//System.out.println("OK: " + parametersOk);
		if (!parametersOk) {
			parameters = data();
			parametersOk = true;
		}
		//System.out.println("OK: " + parametersOk);
	}
	*/

	@Before
	public void setUp() throws EncryptedDocumentException, IOException {
		// Global Data
		global = new JsonDataProvider(
				System.getProperty("user.dir") + File.separator + "resources" + File.separator + "global.json");
		url = global.getValue("Global.urlSystem");
		username = global.getValue("Global.login");
		password = global.getValue("Global.password");

		// PageObjects
		loginPage = new LoginPage(fw);
		adminCenterPage = new AdminCenterPage(fw);
		employeeProfilePage = new EmployeeProfilePage(fw);
		changeJobCompensationInfoPage = new ChangeJobAndCompensationInfoPage(fw);
		changeHistoryPage = new ChangeHistoryPage(fw);
		pleaseConfirmYourRequestPage = new PleaseConfirmYourRequestPage(fw);
		personalInformationPage = new PersonalInformationPage(fw);
		addressesPage = new AddressesPage(fw);
		contactInformationPage = new ContactInformationPage(fw);
		primaryEmergencyContact = new PrimaryEmergencyContactPage(fw);
		paymentInformation = new PaymentInformationPage(fw);


	}

	protected void separeteData(Integer profile) {
		for (int i = 0; i < parameters.get(profile).size(); i++) {
			switch (parameters.get(profile).get(i)[1]) {
			case "View Current & History":
				viewCurrent.add(parameters.get(profile).get(i)[0]);
				viewHistory.add(parameters.get(profile).get(i)[0]);
				break;
			case "View Current":
				viewCurrent.add(parameters.get(profile).get(i)[0]);
				break;
			case "Add Data":
				addData.add(parameters.get(profile).get(i)[0]);
				break;
			case "Hide":
				hide.add(parameters.get(profile).get(i)[0]);
				break;
			default:
				break;
			}
		}
	}

	@After
	public void close() throws InterruptedException {
		fw.timeout(2000);
		//fw.endTest();

	}

	@AfterClass
	public static void afterAll() {
		sendLog();
	}

	public static List<List<String[]>> data() throws EncryptedDocumentException, IOException {
		HREmployeeDataWorkbook hrEmployeeDataWb = new HREmployeeDataWorkbook(
				System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks",
				"EC Configuration Workbook Employee Data.xlsx");
		mapOfSheets = hrEmployeeDataWb.readAllSheet();
		List<String[]> employeeOnOthers = new ArrayList<String[]>();
		List<String[]> employeeOnThemselves = new ArrayList<String[]>();
		List<String[]> manager = new ArrayList<String[]>();
		List<String[]> secondLevelManager = new ArrayList<String[]>();
		List<String[]> hRManager = new ArrayList<String[]>();
		List<String[]> hRAdministrator = new ArrayList<String[]>();
		List<String[]> systemAdministrator = new ArrayList<String[]>();
		List<String[]> globalMobility = new ArrayList<String[]>();
		List<String[]> securityAdmin = new ArrayList<String[]>();

		profiles.put("Employee On Others", 0);
		profiles.put("Employee On Themselves", 1);
		profiles.put("Manager", 2);
		profiles.put("2nd Level Manager", 3);
		profiles.put("HR Manager", 4);
		profiles.put("HR Administrator", 5);
		profiles.put("System Administrator", 6);
		profiles.put("Global Mobility", 7);
		profiles.put("Security Admin", 8);

		parameters.add(new ArrayList<String[]>());
		parameters.add(new ArrayList<String[]>());
		parameters.add(new ArrayList<String[]>());
		parameters.add(new ArrayList<String[]>());
		parameters.add(new ArrayList<String[]>());
		parameters.add(new ArrayList<String[]>());
		parameters.add(new ArrayList<String[]>());
		parameters.add(new ArrayList<String[]>());
		parameters.add(new ArrayList<String[]>());

		// parameters.get(0).add("Employee On Others");

		for (String systemField : mapOfSheets.get(sh).keySet()) {
			if (!systemField.contains("custom")) {
				if (mapOfSheets.get(sh).get(systemField).containsKey("Employee On Others")) {
					employeeOnOthers.add(new String[] { systemField,
							mapOfSheets.get(sh).get(systemField).get("Employee On Others") });

				}
				if (mapOfSheets.get(sh).get(systemField).containsKey("Employee On Themselves")) {
					employeeOnThemselves.add(new String[] { systemField,
							mapOfSheets.get(sh).get(systemField).get("Employee On Themselves") });

				}
				if (mapOfSheets.get(sh).get(systemField).containsKey("Manager")) {
					manager.add(new String[] { systemField, mapOfSheets.get(sh).get(systemField).get("Manager") });

				}
				if (mapOfSheets.get(sh).get(systemField).containsKey("2nd Level Manager")) {
					secondLevelManager.add(new String[] { systemField,
							mapOfSheets.get(sh).get(systemField).get("2nd Level Manager") });

				}
				if (mapOfSheets.get(sh).get(systemField).containsKey("HR Manager")) {
					hRManager.add(new String[] { systemField, mapOfSheets.get(sh).get(systemField).get("HR Manager") });

				}
				if (mapOfSheets.get(sh).get(systemField).containsKey("HR Administrator")) {
					hRAdministrator.add(
							new String[] { systemField, mapOfSheets.get(sh).get(systemField).get("HR Administrator") });

				}
				if (mapOfSheets.get(sh).get(systemField).containsKey("System Administrator")) {
					systemAdministrator.add(new String[] { systemField,
							mapOfSheets.get(sh).get(systemField).get("System Administrator") });

				}
				if (mapOfSheets.get(sh).get(systemField).containsKey("Global Mobility")) {
					globalMobility.add(
							new String[] { systemField, mapOfSheets.get(sh).get(systemField).get("Global Mobility") });

				}
				if (mapOfSheets.get(sh).get(systemField).containsKey("Security Admin")) {
					securityAdmin.add(
							new String[] { systemField, mapOfSheets.get(sh).get(systemField).get("Security Admin") });

				}
			}
		}

		// 0: "Employee On Others"
		parameters.get(profiles.get("Employee On Others")).addAll(employeeOnOthers);
		// 1: Employee On Themselves
		parameters.get(profiles.get("Employee On Themselves")).addAll(employeeOnThemselves);
		// 2: "Manager"
		parameters.get(profiles.get("Manager")).addAll(manager);
		// 3: 2nd Level Manager
		parameters.get(profiles.get("2nd Level Manager")).addAll(secondLevelManager);
		// 4: HR Manager
		parameters.get(profiles.get("HR Manager")).addAll(hRManager);
		// 5: HR Administrator
		parameters.get(profiles.get("HR Administrator")).addAll(hRAdministrator);
		// 6: System Administrator
		parameters.get(profiles.get("System Administrator")).addAll(systemAdministrator);
		// 7: Global Mobility
		parameters.get(profiles.get("Global Mobility")).addAll(globalMobility);
		// 8: Security Admin
		parameters.get(profiles.get("Security Admin")).addAll(securityAdmin);

		return parameters;
	}

	private static void sendLog() {
		System.out.println("System Admin:");
		System.out.println("Not found:");
		for (String string : logSystemAdmin) {
			System.out.println(string);
		}
		System.out.println("Found:");
		for (String string : logSystemAdminOk) {
			System.out.println(string);
		}

		System.out.println("Employee on Others: ");
		System.out.println("Not found:");
		for (String string : logEmpOnOthers) {
			System.out.println(string);
		}
		System.out.println("Found:");
		for (String string : logEmpOnOthersOk) {
			System.out.println(string);
		}

		System.out.println("Employee on Themselves: ");
		System.out.println("Not found:");
		for (String string : logEmpOnThemselves) {
			System.out.println(string);
		}
		System.out.println("Found:");
		for (String string : logEmpOnThemselvesOk) {
			System.out.println(string);
		}

		System.out.println("Manager: ");
		System.out.println("Not found:");
		for (String string : logManager) {
			System.out.println(string);
		}
		System.out.println("Found:");
		for (String string : logManagerOk) {
			System.out.println(string);
		}

		System.out.println("2nd Level Manager: ");
		System.out.println("Not found:");
		for (String string : log2lvlManager) {
			System.out.println(string);
		}
		System.out.println("Found:");
		for (String string : log2lvlManagerOk) {
			System.out.println(string);
		}

		System.out.println("HR Manager: ");
		System.out.println("Not found:");
		for (String string : logHrManager) {
			System.out.println(string);
		}
		System.out.println("Found:");
		for (String string : logHrManagerOk) {
			System.out.println(string);
		}

		System.out.println("HR Administrator: ");
		System.out.println("Not found:");
		for (String string : logHrAdmin) {
			System.out.println(string);
		}
		System.out.println("Found:");
		for (String string : logHrAdminOk) {
			System.out.println(string);
		}

	}

	protected void viewCurrent(WebElement element) throws Exception {
		Boolean displayed = element.isDisplayed();
		if (!displayed) {
			// highlight cell
		}
		fw.assertTrue(displayed);
	}

	protected void viewHistory(WebElement element) throws Exception {
		Boolean displayed = element.isDisplayed();
		if (!displayed) {
			// highlight cell
		}
		fw.assertTrue(displayed);
		// close history
	}

	protected void hide(String systemFieldId) throws Exception {
		try {
			WebElement element = fw.getWebDriver().findElement(By.id(systemFieldId));
		} catch (Exception e) {
			fw.assertTrue(true);
		}
	}

	protected void edit(String systemFieldId) throws Exception {
		WebElement element = fw.getWebDriver().findElement(By.id(systemFieldId));
		Boolean enabled = element.isEnabled();
		if (!enabled) {
			// highlight cell
		}
		fw.assertTrue(enabled);
		// close edit

	}
	//protected static List<String> logGlobalMobility = new ArrayList<String>();
	//protected static List<String> logSecurityAdmin = new ArrayList<String>();
}
