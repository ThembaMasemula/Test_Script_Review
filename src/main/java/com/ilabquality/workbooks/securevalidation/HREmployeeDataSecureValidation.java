package com.ilabquality.workbooks.securevalidation;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ilabquality.workbooks.HREmployeeDataWorkbook;

	@RunWith(Suite.class)
	@Suite.SuiteClasses({
		BiographicalInfo.class
		})
	public class HREmployeeDataSecureValidation extends BaseTestSecureValidation {
		@BeforeClass
		public static void setUpClass() throws EncryptedDocumentException, IOException {
			HREmployeeDataWorkbook hrEmployeeDataWb = new HREmployeeDataWorkbook(System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks", "EC Configuration Workbook Employee Data.xlsx");
			mapOfSheets = hrEmployeeDataWb.readAllSheet();

		}
	}

