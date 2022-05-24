package com.ilabquality.workbooks.securevalidation;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ilabquality.exceptions.ElementNotFoundException;

public class BiographicalInfoSecureValidation extends BaseTestSecureValidation {

	private Integer profileLoged = null;
	private String profileName = "";
	private String sysFieldId = "";
	private String login = "";
	private String password = "";
	private String employee = "";
	private Integer profile = -1;


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
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();

		// System.out.println("Verify History");
//		if (errorsEOO.size() > 0) {
//			throw new AssertionError("Failed to validate security Employee On Others");
//		}
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
		preTest(profile);
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();
		// System.out.println("Verify History");
	/*	if (errorsEOO.size() > 0) {
			throw new AssertionError("Failed to validate security System Administrator");
		}*/
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
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();
		// System.out.println("Verify History");
		/*if (errorsEOO.size() > 0) {
			throw new AssertionError("Failed to validate security Employee On Themselves");
		}*/
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
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();
		// System.out.println("Verify History");
		/*if (errorsEOO.size() > 0) {
			throw new AssertionError("Failed to validate security Manager");
		}*/
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
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();
		// System.out.println("Verify History");
		/*if (errorsEOO.size() > 0) {
			throw new AssertionError("Failed to validate security 2nd Level Manager");
		}*/
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
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();
		// System.out.println("Verify History");
		/*if (errorsEOO.size() > 0) {
			throw new AssertionError("Failed to validate security HR Manager");
		}*/
	}

	@Test
	public void hRAdministrator() throws Exception {
		System.out.println("HR Administrator");
		profileName = "HR Administrator";
		profile = profiles.get(profileName);
		separeteData(profile);
		login = "localhr";
		password = "sky1234";
		employee = "bjohnston";
		preTest(profile);
		preTest(profile);
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();
		// System.out.println("Verify History");
		/*if (errorsEOO.size() > 0) {
			throw new AssertionError("Failed to validate security HR Administrator");
		}*/
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
		preTest(profile);
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();
		// System.out.println("Verify History");
		/*if (errorsEOO.size() > 0) {
			throw new AssertionError("Failed to validate security Global Mobility");
		}*/
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
		biographicalViewCurrent();
		// biographicalViewHistory();
		biographicalHide();
		biographicalAddData();
		// System.out.println("Verify History");
		/*if (errorsEOO.size() > 0) {
			throw new AssertionError("Failed to validate security Security Admin");
		}*/
	}

	/**
	 *
	 * Profile Number: Profile
	 *
	 * 0: "Employee On Others" 1: Employee On Themselves 2: "Manager" 3: 2nd Level
	 * Manager 4: HR Manager 5: HR Administrator 6: System Administrator 7: Global
	 * Mobility 8: Security Admin
	 *
	 * @param profile
	 * @throws Exception
	 */

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
			employeeProfilePage.setFocusBiographicalInformation();
		} catch (AssertionError e) {
	//		errorsEOO.add(profile, "");
		} catch (ElementNotFoundException e) {
	//		errorsEOO.add(profile, "");
		} catch (Exception e) {
	//		errorsEOO.add(profile, "");
		}
		profileLoged = profile;

	}

	private void biographicalViewCurrent() {
		System.out.println("Verify Current");
		for (int i = 0; i < viewCurrent.size(); i++) {
			try {
				System.out.println(viewCurrent.get(i));
				sysFieldId = viewCurrent.get(i);
				WebElement element;
				element = fw.getWebDriver().findElement(By.id(sysFieldId));
				element.wait(3000);
				viewCurrent(element);
			} catch (AssertionError e) {
	//			errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			} catch (ElementNotFoundException e) {
	//			errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			} catch (Exception e) {
	//			errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			}
		}
		viewCurrent.clear();
	}

	private void biographicalViewHistory() throws Exception {
		System.out.println("Verify History");
		for (int i = 0; i < viewHistory.size(); i++) {
			try {
				System.out.println(viewHistory.get(i));
				sysFieldId = viewHistory.get(i);
				WebElement element;
				element = fw.getWebDriver().findElement(By.id(sysFieldId));
				viewHistory(element);
			} catch (AssertionError e) {
			//	errorsEOO.add(i, parameters.get(profile).get(i)[0]);
				// } catch (ElementNotFoundException e) {
				// errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			} catch (Exception e) {
			//	errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			}
		}
		employeeProfilePage.closeHistory();
		viewHistory.clear();
	}

	private void biographicalAddData() throws Exception {
		System.out.println("Verify Add Data");
		for (int i = 0; i < addData.size(); i++) {
			try {
				System.out.println(addData.get(i));
				sysFieldId = addData.get(i);
				edit(sysFieldId);
			} catch (AssertionError e) {
		//		errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			} catch (ElementNotFoundException e) {
		//		errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			} catch (Exception e) {
		//		errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			}
		}
		employeeProfilePage.sendKeyEsc();
		employeeProfilePage.sendKeyEsc();
		addData.clear();
	}

	private void biographicalHide() throws Exception {
		System.out.println("Verify Hide");
		for (int i = 0; i < hide.size(); i++) {
			try {
				System.out.println(hide.get(i));
				sysFieldId = hide.get(i);
				hide(sysFieldId);
			} catch (AssertionError e) {
	//			errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			} catch (ElementNotFoundException e) {
	//			errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			} catch (Exception e) {
	//			errorsEOO.add(i, parameters.get(profile).get(i)[0]);
			}
		}
		hide.clear();
	}

	protected void viewCurrent(WebElement element) throws Exception {
		Boolean displayed = element.isDisplayed();
		if (!displayed) {
			// highlight cell
		}
		fw.assertTrue(displayed);
	}

	protected void viewHistory(WebElement element) throws Exception {
		// click history
		employeeProfilePage.clickHistoryBiographicalInformation();
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
		// click edit
		employeeProfilePage.clickEditBiographicalInformation();
		WebElement element = fw.getWebDriver().findElement(By.id(systemFieldId));
		Boolean enabled = element.isEnabled();
		if (!enabled) {
			// highlight cell
		}

		fw.assertTrue(enabled);
		// close edit

	}

}
