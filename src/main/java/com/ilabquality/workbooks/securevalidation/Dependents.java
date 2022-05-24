package com.ilabquality.workbooks.securevalidation;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ilabquality.exceptions.ElementNotFoundException;

public class Dependents extends BaseTestSecureValidation {

	private Integer profileLoged = null;
	private String profileName = "";
	private String sysFieldId = "";
	private String login = "";
	private String password = "";
	private String employee = "";
	private Integer profile = -1;

	@BeforeClass
	public static void beforeEach() throws EncryptedDocumentException, IOException {
		//System.out.println("OK: " + parametersOk);
		sh = "Dependents";
		if (!parametersOk) {
			parameters = data();
			parametersOk = true;
		}
		//System.out.println("OK: " + parametersOk);
	}

	@Test
	public void employeeOnOthers() throws Exception {
		System.out.println("Employee On Others");
		profileName = "Employee On Others";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "bjohnston";
		password = "sky1234";
		employee = "eakers";
		preTest(profile);
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0 || logEmpOnOthers.size() > 0) {
			throw new AssertionError("Failed to validate security Employee On Others");
		}
	}

	@Test
	public void systemAdministrator() throws Exception {
		profileName = "System Administrator";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "sfadmin";
		password = "partTokyoDC2";
		employee = "bjohnston";
		preTest(profile);
		System.out.println("System Administrator");
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0 || logSystemAdmin.size() > 0) {
			throw new AssertionError("Failed to validate security System Administrator");
		}
	}

	@Test
	public void employeeOnThemselves() throws Exception {
		System.out.println("Employee On Themselves");
		profileName = "Employee On Themselves";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "bjohnston";
		password = "sky1234";
		employee = "bjohnston";
		preTest(profile);
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0 || logEmpOnThemselves.size() > 0) {
			throw new AssertionError("Failed to validate security Employee On Themselves");
		}
	}

	@Test
	public void manager() throws Exception {
		System.out.println("Manager");
		profileName = "Manager";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "alopez";
		password = "sky1234";
		employee = "bjohnston";
		preTest(profile);
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0 || logManager.size() > 0) {
			throw new AssertionError("Failed to validate security Manager");
		}
	}

	@Test
	public void secondLevelManager() throws Exception {
		System.out.println("2nd Level Manager");
		profileName = "2nd Level Manager";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "mcooper";
		password = "sky1234";
		employee = "bjohnston";
		preTest(profile);
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0 || log2lvlManager.size() > 0) {
			throw new AssertionError("Failed to validate security 2nd Level Manager");
		}
	}

	@Test
	public void hRManager() throws Exception {
		System.out.println("HR Manager");
		profileName = "HR Manager";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "wmuller";
		password = "sky1234";
		employee = "bjohnston";
		preTest(profile);
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0 || logHrManager.size() > 0) {
			throw new AssertionError("Failed to validate security HR Manager");
		}
	}

	@Test
	public void hRAdministrator() throws Exception {
		System.out.println("HR Administrator");
		profileName = "HR Administrator";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "lhradmin";
		password = "sky1234";
		employee = "bjohnston";
		preTest(profile);
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0 || logHrAdmin.size() > 0) {
			throw new AssertionError("Failed to validate security HR Administrator");
		}
	}

	// @Test
	public void globalMobility() throws Exception {
		System.out.println("Global Mobility");
		profileName = "Global Mobility";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "awegner";
		password = "sky1234";
		employee = "awegner";
		preTest(profile);
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0  || logGlobalMobility.size() > 0) {
			throw new AssertionError("Failed to validate security Global Mobility");
		}
	}

	// @Test
	public void securityAdmin() throws Exception {
		System.out.println("Security Admin");
		profileName = "Security Admin";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "awegner";
		password = "sky1234";
		employee = "awegner";
		preTest(profile);
		preTest(profile);
		securityViewCurrent();
		securityViewHistory();
		securityHide();
		securityAddData();
		// System.out.println("Verify History");
		if (errorsToLogin.size() > 0 || logSecurityAdmin.size() > 0) {
			throw new AssertionError("Failed to validate security Security Admin");
		}
	}

	private void preTest(Integer profile) throws Exception {

		if (profileLoged == null) {
			loginPage.navigateTo(url);
			loginPage.setUsername(login);
			loginPage.setPassword(password);
			loginPage.clickLogin();
		} else if (profileLoged != profile) {
			employeeProfilePage.clickUserPhotoName();
			employeeProfilePage.clickLogout();
			loginPage.navigateTo(url);
			loginPage.setUsername(login);
			loginPage.setPassword(password);
			loginPage.clickLogin();
		}
		if ((!fw.isTextPresent(employee) || employee == login) && (employee != null || employee != "")) {
			adminCenterPage.setSearch(employee);
			fw.timeout(3000);
			adminCenterPage.sendKeyEnterInSearch();
			adminCenterPage.sendKeyEnterInSearch();
		}
		try {
			employeeProfilePage.setFocusPersonalInformation();
		} catch (AssertionError e) {
			errorsToLogin.add(0, "");
		} catch (ElementNotFoundException e) {
			errorsToLogin.add(0, "");
		} catch (Exception e) {
			errorsToLogin.add(0, "");
		}
		profileLoged = profile;

	}

	private void securityViewCurrent() {
		System.out.println("Verify Current");
		fw.waitForLoadPage();
		for (int i = 0; i < viewCurrent.size(); i++) {
			try {
				System.out.println(viewCurrent.get(i));
				sysFieldId = viewCurrent.get(i);
				WebElement element;
				element = fw.getWebDriver().findElement(By.id(sysFieldId));
				element.wait(3000);
				viewCurrent(element);
				handlingOk(i, profile);
			} catch (AssertionError e) {
				handlingErrors(i, profile);
			} catch (ElementNotFoundException e) {
				handlingErrors(i, profile);
			} catch (Exception e) {
				handlingErrors(i, profile);
			}
		}

		viewCurrent.clear();
	}



	private void securityViewHistory() throws Exception {
		System.out.println("Verify History");
		fw.waitForLoadPage();
		employeeProfilePage.clickHistoryDependents();
		for (int i = 0; i < viewHistory.size(); i++) {
			try {
				System.out.println(viewHistory.get(i));
				sysFieldId = viewHistory.get(i);
				WebElement element;
				element = fw.getWebDriver().findElement(By.id(sysFieldId));
				viewHistory(element);
			} catch (AssertionError e) {
				handlingErrors(i, profile);
				// } catch (ElementNotFoundException e) {
				// errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			} catch (Exception e) {
				handlingErrors(i, profile);
			}
		}
		employeeProfilePage.closeHistory();
		viewHistory.clear();
	}

	private void securityAddData() throws Exception {
		System.out.println("Verify Add Data");
		fw.waitForLoadPage();
		employeeProfilePage.clickEditDependents();
		for (int i = 0; i < addData.size(); i++) {
			try {
				System.out.println(addData.get(i));
				sysFieldId = addData.get(i);
				edit(sysFieldId);
			} catch (AssertionError e) {
				handlingErrors(i, profile);
			} catch (ElementNotFoundException e) {
				handlingErrors(i, profile);
			} catch (Exception e) {
				handlingErrors(i, profile);
			}
		}
		employeeProfilePage.clickCancel();
		addData.clear();
	}

	private void securityHide() throws Exception {
		System.out.println("Verify Hide");
		for (int i = 0; i < hide.size(); i++) {
			try {
				System.out.println(hide.get(i));
				sysFieldId = hide.get(i);
				hide(sysFieldId);
			} catch (AssertionError e) {
				handlingErrors(i, profile);
			} catch (ElementNotFoundException e) {
				handlingErrors(i, profile);
			} catch (Exception e) {
				handlingErrors(i, profile);
			}
		}
		hide.clear();
	}



	private void handlingErrors(Integer position, Integer profile) {
		switch (profile) {
		case 0:
			logEmpOnOthers.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 1:
			logEmpOnThemselves.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 2:
			logManager.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 3:
			log2lvlManager.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 4:
			logHrManager.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 5:
			logHrAdmin.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 6:
			logSystemAdmin.add(position, parameters.get(profile).get(position)[0]);
			break;

		default:
			break;
		}
	}

	private void handlingOk(int position, Integer profile2) {
		switch (profile) {
		case 0:
			logEmpOnOthersOk.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 1:
			logEmpOnThemselvesOk.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 2:
			logManagerOk.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 3:
			log2lvlManagerOk.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 4:
			logHrManagerOk.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 5:
			logHrAdminOk.add(position, parameters.get(profile).get(position)[0]);
			break;
		case 6:
			logSystemAdminOk.add(position, parameters.get(profile).get(position)[0]);
			break;

		default:
			break;
		}
	}
}
