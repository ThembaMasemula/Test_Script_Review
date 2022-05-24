package com.ilabquality.workbooks;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.ilabquality.modules.testing.utilsData.Data;

public class HRDataEntry {

	public static void main(String[] args) throws IOException {

		Data data = new Data();

		String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks";
		String fileName = "Data Entry Workbook.xlsx";
		String sheet = "EC";

		HRDataEntryWorkbook hrEmployeeDataWb = new HRDataEntryWorkbook(path, fileName, sheet);

		Map<String, Map<String, String>> identity = new HashMap<String, Map<String, String>>();
		identity = hrEmployeeDataWb.readSheet("EC", "Event Reason / Action (EC)");

		Map<String, Map<String, String>> personalInfo = new HashMap<String, Map<String, String>>();
		personalInfo = hrEmployeeDataWb.readSheet("EC", "Preferred Name");

		Map<String, Map<String, String>> globalUs = new HashMap<String, Map<String, String>>();
		globalUs = hrEmployeeDataWb.readSheet("EC", "Ethnic Group");

		Map<String, Map<String, String>> addressUs = new HashMap<String, Map<String, String>>();
		addressUs = hrEmployeeDataWb.readSheet("EC", "Address Line 1");

		Map<String, Map<String, String>> jobInfo = new HashMap<String, Map<String, String>>();
		jobInfo = hrEmployeeDataWb.readSheet("EC", "Position");

		Map<String, Map<String, String>> compensationInfo = new HashMap<String, Map<String, String>>();
		compensationInfo = hrEmployeeDataWb.readSheet("EC", "Compa Ratio");


		System.out.println(jobInfo.get("Country Transfer").get("Position"));

		iterate(identity);
		iterate(personalInfo);
		iterate(globalUs);
		iterate(addressUs);
		iterate(jobInfo);
		iterate(compensationInfo);

		System.out.println(dateFormat(identity.get("Rehire").get("Hire Date")));

		System.out.println("Finish");

	}

	private static String dateFormat(String date) {
		String[] chars = date.split("-");
		String day = chars[0];
		String month = chars[1];
		String year = chars[2];

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
			break;
		}

		String format = month  + day + "," + year;
		return format;
	}

	public static void iterate(Map<String, Map<String, String>> map) {
		for (Entry<String, Map<String, String>> entry : map.entrySet()) {
			String transaction = entry.getKey();
			Map<String, String> values = entry.getValue();
			for (Entry<String, String> entry2 : values.entrySet()) {
				String field = entry2.getKey();
				String value = entry2.getValue();
				if (!value.equals("") && !value.contains("IT0") && !transaction.contains("IT00") && !field.contains("Event Reason / Action")) {
					System.out.println("Transaction: " + transaction + "\n" +
										"Field: " + field + " = " + value + "\n");
				}
			}
		}
	}

}
