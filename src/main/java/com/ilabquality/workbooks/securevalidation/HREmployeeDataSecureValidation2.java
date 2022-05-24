package com.ilabquality.workbooks.securevalidation;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ilabquality.workbooks.HREmployeeDataWorkbook;

@RunWith(value = Parameterized.class)
public class HREmployeeDataSecureValidation2 extends BaseTestSecureValidation {

	private String sheet;
	private String systemFieldId;
	private String employee;
	private String securityLevel;

	// Inject via constructor
	// for {8, 2, 10}, numberA = 8, numberB = 2, expected = 10
	public HREmployeeDataSecureValidation2(String s1, String s2, String s3, String s4)
			throws EncryptedDocumentException, IOException {

		this.sheet = s1;
		this.systemFieldId = s2;
		this.employee = s3;
		this.securityLevel = s4;
	}

	// name attribute is optional, provide an unique name for test
	// multiple parameters, uses Collection<Object[]>
	@Parameters(name = "{index}: {0} - {1} - {2} => {3}")
	public static Collection<Object[]> data2() throws EncryptedDocumentException, IOException {
		HREmployeeDataWorkbook hrEmployeeDataWb = new HREmployeeDataWorkbook(
				System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks",
				"EC Configuration Workbook Employee Data.xlsx");
		mapOfSheets = hrEmployeeDataWb.readAllSheet();

		return Arrays.asList(new Object[][] {
			{"Biographical Info","person-id-external", "Employee On Themselves", mapOfSheets.get("Biographical Info").get("person-id-external").get("Employee On Themselves") },
			{"Biographical Info","person-id-external", "Employee On Others", mapOfSheets.get("Biographical Info").get("person-id-external").get("Employee On Others") } });
	}




	@Test
	public void employeeOnOthers() {
		switch (sheet) {
		case "Biographical Info":
			System.out.println(sheet);
			System.out.println(systemFieldId);
			System.out.println(employee);
			System.out.println(securityLevel);
			break;

		default:
			break;
		}
	}
}
