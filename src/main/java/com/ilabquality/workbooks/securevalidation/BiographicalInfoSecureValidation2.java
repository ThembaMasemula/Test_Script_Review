package com.ilabquality.workbooks.securevalidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ilabquality.workbooks.HREmployeeDataWorkbook;

@RunWith(value = Parameterized.class)
public class BiographicalInfoSecureValidation2 extends BaseTestSecureValidation {

	private List<String> systemFieldId;
	private List<String> employee;
	private List<String> securityLevel;

	public BiographicalInfoSecureValidation2(List<String> s1, List<String> s2, List<String> s3)
			throws EncryptedDocumentException, IOException {

		this.systemFieldId = s1;
		this.employee = s2;
		this.securityLevel = s3;
	}

	@Parameters(name = "{index}: {0} - {1} - {2}")
	public static List<List<Object>> data2() throws EncryptedDocumentException, IOException {
		HREmployeeDataWorkbook hrEmployeeDataWb = new HREmployeeDataWorkbook(
				System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks",
				"EC Configuration Workbook Employee Data.xlsx");
		mapOfSheets = hrEmployeeDataWb.readAllSheet();
		List<List<Object>> parameters = new ArrayList<List<Object>>();
		List<Object[]> employeeOnOthers = new ArrayList<Object[]>();


		String sh = "Biographical Info";
		parameters.add(new ArrayList<Object>());
		parameters.add(new ArrayList<Object>());
		parameters.add(new ArrayList<Object>());
		parameters.add(new ArrayList<Object>());
		parameters.add(new ArrayList<Object>());
		parameters.add(new ArrayList<Object>());
		parameters.add(new ArrayList<Object>());
		parameters.add(new ArrayList<Object>());
		parameters.add(new ArrayList<Object>());

		//parameters.get(0).add("Employee On Others");

		for (String systemField : mapOfSheets.get(sh).keySet()) {
			if (!systemField.contains("custom")) {
				if (mapOfSheets.get(sh).get(systemField).containsKey("Employee On Others")) {
					employeeOnOthers.add(new Object[] {systemField, mapOfSheets.get(sh).get(systemField).get("Employee On Others")});

				}
				/*
				parameters.get(1).add("Employee On Themselves");
				parameters.get(1).add(systemField);
				parameters.get(1).add(mapOfSheets.get(sh).get(systemField).get("Employee On Themselves"));

				parameters.get(2).add(systemField);
				parameters.get(2).add("Manager");
				parameters.get(2).add(mapOfSheets.get(sh).get(systemField).get("Manager"));

				parameters.get(3).add("2nd Level Manager");
				parameters.get(3).add(systemField);
				parameters.get(3).add(mapOfSheets.get(sh).get(systemField).get("2nd Level Manager"));

				parameters.get(4).add("HR Manager");
				parameters.get(4).add(systemField);
				parameters.get(4).add(mapOfSheets.get(sh).get(systemField).get("HR Manager"));

				parameters.get(5).add("HR Administrator");
				parameters.get(5).add(systemField);
				parameters.get(5).add(mapOfSheets.get(sh).get(systemField).get("HR Administrator"));

				parameters.get(6).add("System Administrator");
				parameters.get(6).add(systemField);
				parameters.get(6).add(mapOfSheets.get(sh).get(systemField).get("System Administrator"));

				parameters.get(7).add("Global Mobility");
				parameters.get(7).add(systemField);
				parameters.get(7).add(mapOfSheets.get(sh).get(systemField).get("Global Mobility"));

				parameters.get(8).add("Security Admin");
				parameters.get(8).add(systemField);
				parameters.get(8).add(mapOfSheets.get(sh).get(systemField).get("Security Admin"));
				*/
			}
		}


		//0: "Employee On Others"
		parameters.get(0).addAll(employeeOnOthers);

		/*
		List<Object[]> list = new ArrayList<Object[]>();
		for (int j = 0; j < parameters.size(); j++) {
			list.add(parameters.get(j).toArray());
		}
		 */
		return parameters;
		//return list;
	}

	@Test
	public void teste() throws Exception {
		for (int i = 0; i < employee.size(); i++) {
			employeeSecure(i);
		}

	}

	private void employeeSecure(Integer pos) throws Exception {
		switch (employee.get(pos)) {
		case "System Administrator":
			preTest(pos);
			trySecurityLevel(pos);

		default:
			fw.assertTrue(false);
			break;
		}
	}

	private void preTest(Integer pos) throws Exception {

		// navigate to system url
		loginPage.navigateTo(url);

		// login with correct profile
		switch (employee.get(pos)) {
		case "System Administrator":
			loginPage.setUsername("sfadmin");
			loginPage.setPassword("partTokyoDC2");
			loginPage.clickLogin();
			adminCenterPage.setSearch("eakers");
			fw.timeout(3000);
			fw.getFullScreenshot();
			adminCenterPage.sendKeyEnterInSearch();
			adminCenterPage.sendKeyEnterInSearch();

			break;

		default:
			fw.assertTrue(false);
			break;
		}

		// navigate to page to made the test
		employeeProfilePage.setFocusBiographicalInformation();
	}

	private void trySecurityLevel(Integer pos) throws IOException {
		switch (securityLevel.get(pos)) {
		case "View Current & History":
			viewCurrent(fw.getWebDriver().findElement(By.id(systemFieldId.get(pos))));
			viewHistory(fw.getWebDriver().findElement(By.id(systemFieldId.get(pos))));

			break;
		case "Hide":
			hide();
			break;
		default:
			fw.assertTrue(false);
			break;
		}
	}

	protected void viewCurrent(WebElement element) throws IOException {
		Boolean displayed = element.isDisplayed();
		if (!displayed) {
			// highlight cell
		}
		fw.assertTrue(displayed);
	}

	protected void viewHistory(WebElement element) throws IOException {
		Boolean displayed = element.isDisplayed();
		if (!displayed) {
			// highlight cell
		}
		fw.assertTrue(displayed);
	}

	private void hide() {

	}

}
