package com.ilabquality.modules.testing.utilsData;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;

import com.ilabquality.dataprovider.JsonDataProvider;
import com.ilabquality.workbooks.HRDataEntryWorkbook;

public class Data {

	String path = System.getProperty("user.dir") + File.separator + "workbooks";
	String fileName = "Data Entry Workbook.xlsx";
	String sheet = "EC";
	Map<String, Map<String, String>> identity;
	Map<String, Map<String, String>> personalInfo;
	Map<String, Map<String, String>> globalUs;
	Map<String, Map<String, String>> addressUs;
	Map<String, Map<String, String>> jobInfo;
	Map<String, Map<String, String>> compensationInfo;
	HRDataEntryWorkbook hrEmployeeDataWb;
	JsonDataProvider json;
	ArrayList<String> isPresent = new ArrayList<>();

	public static boolean isDone = false;

	public Data() throws EncryptedDocumentException, IOException {

		this.hrEmployeeDataWb = new HRDataEntryWorkbook(path, fileName, sheet);
		this.json = new JsonDataProvider();

		this.identity = new HashMap<String, Map<String, String>>();
		this.personalInfo = new HashMap<String, Map<String, String>>();
		this.globalUs = new HashMap<String, Map<String, String>>();
		this.addressUs = new HashMap<String, Map<String, String>>();
		this.jobInfo = new HashMap<String, Map<String, String>>();
		this.compensationInfo = new HashMap<String, Map<String, String>>();

		this.identity = hrEmployeeDataWb.readSheet("EC", "Event Reason / Action (EC)");
		this.personalInfo = hrEmployeeDataWb.readSheet("EC", "Preferred Name");
		this.globalUs = hrEmployeeDataWb.readSheet("EC", "Ethnic Group");
		this.addressUs = hrEmployeeDataWb.readSheet("EC", "Address Line 1");
		this.jobInfo = hrEmployeeDataWb.readSheet("EC", "Position");
		this.compensationInfo = hrEmployeeDataWb.readSheet("EC", "Compa Ratio");



		iterate(identity, "Identity");
		iterate(personalInfo, "PersonalInformation");
		iterate(globalUs, "GlobalUs");
		iterate(addressUs, "Address");
		iterate(jobInfo, "JobInformation");
		iterate(compensationInfo, "CompensationInformation");

	}

	public void iterate(Map<String, Map<String, String>> map, String name) {
		HashMap<String, String> keyValue = new HashMap<>();

		for (Entry<String, Map<String, String>> entry : map.entrySet()) {
			String transaction = entry.getKey();
			boolean willWrite = false;
			Map<String, String> values = entry.getValue();

			for (Entry<String, String> entry2 : values.entrySet()) {
				String field = entry2.getKey();
				String value = entry2.getValue();

				if (!value.equals("") && !value.contains("IT0") && !transaction.contains("IT00")
						&& !field.contains("Event Reason / Action")) {

					if (value.contains("-")) {
						value = dateFormat(value);
					}

					if (value.contains(".") && field.equals("Person ID")) {
						value = value.substring(0, value.indexOf("."));
					}

					if (value.contains(".") && field.equals("Company")) {
						value = value.substring(0, value.indexOf("."));
					}

					if (value.contains(".") && value.contains("E")) {
						value = value.replace(".", "");
						value = value.substring(0, value.indexOf("E"));
					}

					field = field.replace(" ", "");

					keyValue.put(field.trim(), value);
					willWrite = true;
				}
			}

			if (willWrite) {
				String filePath = System.getProperty("user.dir") + File.separator + "dataTest"
						+ File.separator + transaction + name.trim();

				json.writeFile(transaction, keyValue, filePath);
			}
		}

	}

	private String dateFormat(String date) {
		String[] chars;
		String day = "null";
		String month = "null";
		String year = "null";

		if (date.contains("-")) {
			chars = date.split("-");

			if (chars.length == 3) {
				day = chars[0];
				month = chars[1];
				year = chars[2];
			}
		}

		switch (month) {
		case "jan":
			month = "Jan";
			break;
		case "fev":
			month = "Feb";
			break;
		case "mar":
			month = "Mar";
			break;
		case "abr":
			month = "Apr";
			break;
		case "mai":
			month = "May";
			break;
		case "jun":
			month = "Jun";
			break;
		case "jul":
			month = "Jul";
			break;
		case "ago":
			month = "Aug";
			break;
		case "set":
			month = "Sep";
			break;
		case "out":
			month = "Oct";
			break;
		case "nov":
			month = "Nov";
			break;
		case "dez":
			month = "Dec";
			break;
		default:
			return date;
		}

    return month + " " + day + ", " + year;
	}
}
