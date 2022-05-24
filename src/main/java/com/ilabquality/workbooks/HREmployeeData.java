package com.ilabquality.workbooks;

import java.util.HashMap;
import java.util.Map;

public class HREmployeeData {

	public static void main(String[] args) throws Exception {

		//Date dataInicio = new Date();
		String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks";
		//String fileName = "EC Configuration Workbook Employee Data.xlsx";
		String fileName = "EC Configuration Workbook Employee Data.xlsb";

		System.out.println("Reading file " + path + "\\" + fileName);
		HREmployeeDataWorkbook hrEmployeeDataWb = new HREmployeeDataWorkbook(path, fileName);

		//Map<String, Map<String, String>> personalInfo = new HashMap<String, Map<String, String>>();
		//personalInfo = hrEmployeeDataWb.readSheet("Personal Info");

		Map<String, Map<String, Map<String, String>>> listOfSheets = new HashMap<String, Map<String, Map<String, String>>>();
		listOfSheets = hrEmployeeDataWb.readAllSheet();

		if(!listOfSheets.isEmpty()) {
			listOfSheets.get("Biographical Info").values();
			listOfSheets.get("Biographical Info").size();
			listOfSheets.get("Biographical Info").get("country-of-birth").get("Manager");
		}
		//Date dataFim = new Date();
		//System.out.println(dataInicio + " - " + dataFim);
		System.out.println();

		for (String sheet : listOfSheets.keySet()) {
		    System.out.println("Sheet - " + sheet);
		    System.out.println();
		    for(String systemField : listOfSheets.get(sheet).keySet()) {
		    	System.out.println("System Field Id - " + systemField);
		    	for(String header : listOfSheets.get(sheet).get(systemField).keySet()) {
		    		System.out.println(header + " - " + listOfSheets.get(sheet).get(systemField).get(header));
		    	}
		    	System.out.println();
		    }
		    System.out.println();
		}

		/*
		System.out.println();
		System.out.println("System Fields Id from Biographical Info: ");
		for (String key : listOfSheets.get("Biographical Info").keySet()) {
		    System.out.println(key);
		}

		System.out.println();
		System.out.println("Place of birth from Biographical Info: ");
		for (String key : listOfSheets.get("Biographical Info").get("place-of-birth").keySet()) {
		    System.out.println(key);
		}
		*/
		System.out.println("Finish");

	}

}
