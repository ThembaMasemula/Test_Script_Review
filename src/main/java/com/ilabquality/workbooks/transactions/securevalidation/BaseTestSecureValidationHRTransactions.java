package com.ilabquality.workbooks.transactions.securevalidation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.junit.After;
import org.junit.Before;

import com.ilabquality.dataprovider.JsonDataProvider;
import com.ilabquality.pages.AddGlobalAssignmentPage;
import com.ilabquality.pages.AddressesPage;
import com.ilabquality.pages.AdminCenterPage;
import com.ilabquality.pages.ChangeHistoryPage;
import com.ilabquality.pages.ChangeJobAndCompensationInfoPage;
import com.ilabquality.pages.ContactInformationPage;
import com.ilabquality.pages.EmployeeProfilePage;
import com.ilabquality.pages.HomePage;
import com.ilabquality.pages.LoginPage;
import com.ilabquality.pages.PaymentInformationPage;
import com.ilabquality.pages.PendingRequestsPage;
import com.ilabquality.pages.PersonalInformationPage;
import com.ilabquality.pages.PleaseConfirmYourRequestPage;
import com.ilabquality.pages.PrimaryEmergencyContactPage;
import com.ilabquality.pages.RehireFormPage;
import com.ilabquality.pages.RehireInactiveEmployeePage;
import com.ilabquality.pages.TerminationPage;
import com.ilabquality.pages.TimeOffPage;
import com.ilabquality.pages.WorkflowDetailsPage;
import com.ilabquality.utilities.BaseTest;
import com.ilabquality.workbooks.HRTransactionWorkbook;

public class BaseTestSecureValidationHRTransactions extends BaseTest {

	protected static List<Map<String, String[]>> mapOfSheets = new ArrayList<Map<String, String[]>>();

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
	protected EmployeeProfilePage employeePage;
	protected TerminationPage terminationPage;
	protected RehireInactiveEmployeePage rehireFirstPage;
	protected RehireFormPage rehireFormPage;
	protected TimeOffPage timeOffPage;
	protected HomePage homePage;
	protected PendingRequestsPage pendingRequestsPage;
	protected WorkflowDetailsPage workflowDetailsPage;
	protected AddGlobalAssignmentPage addGlobalAssignment;

	protected static Map<String, Integer> profiles = new HashMap<String, Integer>();
	protected static List<String[]> parameters = new ArrayList<String[]>();
	protected static String sh;
	protected static String[] roles = new String[20];

	protected String idEmployee;
	protected String date;

	static String employeeId;
	static String initiatorRole;
	static String initiatorName;
	static String approverRole;
	static String approverName;
	static String approverGroup;
	static String rolesThatGetNotified;
	static String notifierGroup;
	static String notifierNames;

	static String[] empId;
	static String[] iniRole;
	static String[] iniName;
	static String[] appRole;
	static String[] appName;
	static String[] appGroup;
	static String[] rolesNotified;
	static String[] notifGroup;
	static String[] notiNames;

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
	 * @BeforeClass public static void beforeEach() throws
	 * EncryptedDocumentException, IOException { //System.out.println("OK: " +
	 * parametersOk); if (!parametersOk) { parameters = data(); parametersOk = true;
	 * } //System.out.println("OK: " + parametersOk); }
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
		employeePage = new EmployeeProfilePage(fw);
		employeeProfilePage = new EmployeeProfilePage(fw);
		changeJobCompensationInfoPage = new ChangeJobAndCompensationInfoPage(fw);
		changeHistoryPage = new ChangeHistoryPage(fw);
		pleaseConfirmYourRequestPage = new PleaseConfirmYourRequestPage(fw);
		personalInformationPage = new PersonalInformationPage(fw);
		addressesPage = new AddressesPage(fw);
		contactInformationPage = new ContactInformationPage(fw);
		primaryEmergencyContact = new PrimaryEmergencyContactPage(fw);
		paymentInformation = new PaymentInformationPage(fw);
		terminationPage = new TerminationPage(fw);
		rehireFirstPage = new RehireInactiveEmployeePage(fw);
		rehireFormPage = new RehireFormPage(fw);
		timeOffPage = new TimeOffPage(fw);
		pendingRequestsPage = new PendingRequestsPage(fw);
		workflowDetailsPage = new WorkflowDetailsPage(fw);
		homePage = new HomePage(fw);
		addGlobalAssignment = new AddGlobalAssignmentPage(fw);
	}

	protected void separeteData(Integer profile) {

	}

	@After
	public void close() throws InterruptedException {
		fw.timeout(2000);
		// fw.endTest();

	}

	public static List<String[]> data(String transaction) throws EncryptedDocumentException, IOException {
		HRTransactionWorkbook hrTransactionWorkbook = new HRTransactionWorkbook(
				System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks",
				"EC HR Transactions Configuration Workbook_Josh.xlsx");

		mapOfSheets = hrTransactionWorkbook.readSheetWorkflowRoles(3, 1);

		for (int i = 0; i < mapOfSheets.size(); i++) {
			if (mapOfSheets.get(i).containsKey(transaction)) {
				roles = mapOfSheets.get(i).get(transaction);
				employeeId = roles[0];
				initiatorRole = roles[1];
				initiatorName = roles[2];
				approverRole = roles[3];
				approverName = roles[4];
				approverGroup = roles[5];
				rolesThatGetNotified = roles[6];
				notifierGroup = roles[7];
				notifierNames = roles[8];
			}
		}

		empId = employeeId.split("\n");
		iniRole = initiatorRole.split("\n");
		iniName = initiatorName.split("\n");
		appRole = approverRole.split("\n");
		appName = approverName.split("\n");
		appGroup = approverGroup.split("\n");
		rolesNotified = rolesThatGetNotified.split("\n");
		notifGroup = notifierGroup.split("\n");
		notiNames = notifierNames.split("\n");

		//System.out.println();

		parameters.add(empId);
		parameters.add(iniRole);
		parameters.add(iniName);
		parameters.add(appRole);
		parameters.add(appName);
		parameters.add(appGroup);
		parameters.add(rolesNotified);
		parameters.add(notifGroup);
		parameters.add(notiNames);


		// for (String map : mapOfSheets) {
		// System.out.println(map);
		// }
		/*
		 * List<String[]> employeeOnOthers = new ArrayList<String[]>(); List<String[]>
		 * employeeOnThemselves = new ArrayList<String[]>(); List<String[]> manager =
		 * new ArrayList<String[]>(); List<String[]> secondLevelManager = new
		 * ArrayList<String[]>(); List<String[]> hRManager = new ArrayList<String[]>();
		 * List<String[]> hRAdministrator = new ArrayList<String[]>(); List<String[]>
		 * systemAdministrator = new ArrayList<String[]>(); List<String[]>
		 * globalMobility = new ArrayList<String[]>(); List<String[]> securityAdmin =
		 * new ArrayList<String[]>();
		 *
		 * profiles.put("Employee On Others", 0); profiles.put("Employee On Themselves",
		 * 1); profiles.put("Manager", 2); profiles.put("2nd Level Manager", 3);
		 * profiles.put("HR Manager", 4); profiles.put("HR Administrator", 5);
		 * profiles.put("System Administrator", 6); profiles.put("Global Mobility", 7);
		 * profiles.put("Security Admin", 8);
		 *
		 * parameters.add(new ArrayList<String[]>()); parameters.add(new
		 * ArrayList<String[]>()); parameters.add(new ArrayList<String[]>());
		 * parameters.add(new ArrayList<String[]>()); parameters.add(new
		 * ArrayList<String[]>()); parameters.add(new ArrayList<String[]>());
		 * parameters.add(new ArrayList<String[]>()); parameters.add(new
		 * ArrayList<String[]>()); parameters.add(new ArrayList<String[]>());
		 *
		 * // parameters.get(0).add("Employee On Others");
		 *
		 * for (String systemField : mapOfSheets.get(sh).keySet()) { if
		 * (!systemField.contains("custom")) { if
		 * (mapOfSheets.get(sh).get(systemField).containsKey("Employee On Others")) {
		 * employeeOnOthers.add(new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("Employee On Others") });
		 *
		 * } if
		 * (mapOfSheets.get(sh).get(systemField).containsKey("Employee On Themselves"))
		 * { employeeOnThemselves.add(new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("Employee On Themselves") });
		 *
		 * } if (mapOfSheets.get(sh).get(systemField).containsKey("Manager")) {
		 * manager.add(new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("Manager") });
		 *
		 * } if (mapOfSheets.get(sh).get(systemField).containsKey("2nd Level Manager"))
		 * { secondLevelManager.add(new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("2nd Level Manager") });
		 *
		 * } if (mapOfSheets.get(sh).get(systemField).containsKey("HR Manager")) {
		 * hRManager.add(new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("HR Manager") });
		 *
		 * } if (mapOfSheets.get(sh).get(systemField).containsKey("HR Administrator")) {
		 * hRAdministrator.add( new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("HR Administrator") });
		 *
		 * } if
		 * (mapOfSheets.get(sh).get(systemField).containsKey("System Administrator")) {
		 * systemAdministrator.add(new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("System Administrator") });
		 *
		 * } if (mapOfSheets.get(sh).get(systemField).containsKey("Global Mobility")) {
		 * globalMobility.add( new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("Global Mobility") });
		 *
		 * } if (mapOfSheets.get(sh).get(systemField).containsKey("Security Admin")) {
		 * securityAdmin.add( new String[] { systemField,
		 * mapOfSheets.get(sh).get(systemField).get("Security Admin") });
		 *
		 * } } }
		 *
		 * // 0: "Employee On Others"
		 * parameters.get(profiles.get("Employee On Others")).addAll(employeeOnOthers);
		 * // 1: Employee On Themselves
		 * parameters.get(profiles.get("Employee On Themselves")).addAll(
		 * employeeOnThemselves); // 2: "Manager"
		 * parameters.get(profiles.get("Manager")).addAll(manager); // 3: 2nd Level
		 * Manager
		 * parameters.get(profiles.get("2nd Level Manager")).addAll(secondLevelManager);
		 * // 4: HR Manager
		 * parameters.get(profiles.get("HR Manager")).addAll(hRManager); // 5: HR
		 * Administrator
		 * parameters.get(profiles.get("HR Administrator")).addAll(hRAdministrator); //
		 * 6: System Administrator
		 * parameters.get(profiles.get("System Administrator")).addAll(
		 * systemAdministrator); // 7: Global Mobility
		 * parameters.get(profiles.get("Global Mobility")).addAll(globalMobility); // 8:
		 * Security Admin
		 * parameters.get(profiles.get("Security Admin")).addAll(securityAdmin);
		 */
		return parameters;
	}

	public static boolean contains(final String[] approvers, final String string) {

        boolean result = false;

        for(String i : approvers){
            if(i.trim().equals(string.trim())){
                result = true;
                break;
            }
        }

        return result;
    }

	public void generateLog() {

	}

	public Boolean checkApproversTitle() throws Exception {
		String[] approversTitle = pleaseConfirmYourRequestPage.getApproversTitle();
		List<String> list = new ArrayList<String>();
		List<String> failures = new ArrayList<String>();

		for(String s : approversTitle) {
		   if(s != null && s.length() > 0) {
		      list.add(s);
		   }
		}
		approversTitle = list.toArray(new String[list.size()]);
		Boolean flag = true;
		for (int i = 0; i < parameters.get(3).length; i++) {
			if(!contains(approversTitle, parameters.get(3)[i])){
				flag = false;
			} else {
				failures.add(parameters.get(3)[i]);
			}
		}
		return flag;
	}



	public List<String> checkValuesTitle() throws Exception {
		String[] approvers = pleaseConfirmYourRequestPage.getApproversTitle();
		int index = 3;
		return checkValues(approvers, index);
	}

	public List<String> checkValuesName() throws Exception {
		String[] approvers = pleaseConfirmYourRequestPage.getApprovers();
		int index = 4;
		return checkValues(approvers, index);
	}

	public List<String> checkValuesCCTitle() throws Exception {
		String[] approvers = pleaseConfirmYourRequestPage.getCCTitle();
		int index = 6;
		return checkValues(approvers, index);
	}

	public List<String> checkValuesCCName() throws Exception {
		String[] approvers = pleaseConfirmYourRequestPage.getCCName();
		int index = 8;
		return checkValues(approvers, index);
	}

	private List<String> checkValues(String[] approvers, int index) {
		List<String> list = new ArrayList<String>();
		List<String> failures = new ArrayList<String>();

		for(String s : approvers) {
		   if(s != null && s.length() > 0) {
		      list.add(s.trim());
		   }
		}
		approvers = list.toArray(new String[list.size()]);
		for (int i = 0; i < approvers.length; i++) {
			if(!contains(parameters.get(index), approvers[i])){
				failures.add(approvers[i]);
			}
		}
		return failures;
	}
	public Boolean checkApprovers(String[] approvers, String[] approversTrue) throws Exception {
		List<String> list = new ArrayList<String>();

		for(String s : approvers) {
		   if(s != null && s.length() > 0) {
		      list.add(s);
		   }
		}
		approvers = list.toArray(new String[list.size()]);
		Boolean flag = true;
		for (int i = 0; i < approversTrue.length; i++) {
			if(!contains(approvers, approversTrue[i])){
				flag = false;
			}
		}
		return flag;
	}

	public Boolean checkApproversName() throws Exception {
		Boolean flag;
		String[] approversName = pleaseConfirmYourRequestPage.getApprovers();

		List<String> listName = new ArrayList<String>();

		for(String s : approversName) {
		   if(s != null && s.length() > 0) {
			   listName.add(s);
		   }
		}
		approversName = listName.toArray(new String[listName.size()]);
		flag = true;
		for (int i = 0; i < parameters.get(4).length; i++) {
			if(!contains(approversName, parameters.get(4)[i])){
				flag = false;
			}
		}
		return flag;
	}

	public Boolean checkNotifierTitle() throws Exception {
		Boolean flag;
		String[] ccTitle = pleaseConfirmYourRequestPage.getApproversTitle();
		List<String> listCCTitle = new ArrayList<String>();

		for(String s : ccTitle) {
		   if(s != null && s.length() > 0) {
			   listCCTitle.add(s);
		   }
		}
		ccTitle = listCCTitle.toArray(new String[listCCTitle.size()]);
		flag = true;
		for (int i = 0; i < parameters.get(6).length; i++) {
			if(!contains(ccTitle, parameters.get(6)[i])){
				flag = false;
			}
		}
		return flag;
	}
}
