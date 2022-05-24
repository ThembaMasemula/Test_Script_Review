package com.ilabquality.workbooks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;

import com.ilabquality.dataprovider.XlsxDataProvider;

public class HREmployeeDataWorkbook {

	private String tab;
	private XlsxDataProvider xlsxFile;
	private Integer[] rowHeader;
	private Map<String, Integer> headerNumColumn;
	private Map<String, Integer> systemFieldIdNumRow;

	public HREmployeeDataWorkbook(String path, String fileName) throws EncryptedDocumentException, IOException {
		path.trim();
		if (!fileName.trim().contains(".xlsx")) {
			fileName.trim().concat(".xlsx");
		}
		fileName.trim();
		this.xlsxFile = new XlsxDataProvider(path, fileName, "");
		System.out.println("Building data...");
		this.xlsxFile.generateWorkbookFactory();
	}

	public Map<String, Map<String, Map<String, String>>> readAllSheet() throws IOException {

		List<String> allSheets = xlsxFile.getAllSheetName();
		allSheets.remove("Cover Sheet");
		allSheets.remove("Table Of Contents");

		Map<String, Map<String, String>> mapOfSystemFieldIdHeader;
		Map<String, Map<String, Map<String, String>>> listOfMaps = new HashMap<String, Map<String, Map<String, String>>>();

		// System.out.println("Building data...");
		if (!allSheets.isEmpty()) {
			while (!allSheets.isEmpty()) {
				// listOfMaps = new HashMap<String, Map<String, Map<String, String>>>();
				mapOfSystemFieldIdHeader = new HashMap<String, Map<String, String>>();
				mapOfSystemFieldIdHeader = readSheet(allSheets.get(0));
				listOfMaps.put(allSheets.get(0), mapOfSystemFieldIdHeader);
				allSheets.remove(0);
			}
			System.out.println("Data built!");
		}
		return listOfMaps;

	}

	public Map<String, Map<String, String>> readSheet(String sheet) throws IOException {

		tab = sheet;

		xlsxFile.setSheetName(tab);
		// xlsxFile.generateWorkbookFactory();

		Map<String, String> mapOfHeaderValue = new HashMap<String, String>();

		Map<String, Map<String, String>> mapOfSystemFieldIdHeader = new HashMap<String, Map<String, String>>();

		// header
		String[] arrHeader = getHeaders(sheet, "System Field Id");

		// system fields
		String[] arrSystemFields = getSystemFieldsId(sheet);

		for (int k = 0; k < arrSystemFields.length; k++) {
			// System.out.println(arrSystemFields[k]);
			for (int j = 0; j < arrHeader.length; j++) {
				mapOfHeaderValue.put(arrHeader[j], getHeaderValue(arrSystemFields[k], arrHeader[j]));
			}
			mapOfSystemFieldIdHeader.put(arrSystemFields[k], mapOfHeaderValue);
			mapOfHeaderValue = new HashMap<String, String>();
		}

		return mapOfSystemFieldIdHeader;
	}

	public XlsxDataProvider getSheet() {
		return xlsxFile;
	}

	private String[] getHeaders(String tab, String firstColumnHeader) throws IOException {
		// firstColumnHeader = "System Field Id";
		// header
		// xlsxFile = new XlsxDataProvider(path, fileName, tab);
		// xlsxFile.generateWorkbookFactory();

		ArrayList<String> headers = new ArrayList<String>();
		String[] arrHeader;
		rowHeader = xlsxFile.getCellPositionNumber(firstColumnHeader);
		headerNumColumn = new HashMap<String, Integer>();

		int blanks = 0;
		int i = rowHeader[1] - 1;
		while (blanks <= 0) {
			i++;
			String header = xlsxFile.getCellValue(rowHeader[0] - 1, i);
			// System.out.println(header);
			if (!"".equals(header)) {
				headers.add(header.toString());
				headerNumColumn.put(header, i);
				blanks = 0;
			} else {
				blanks++;
			}
		}

		arrHeader = new String[headers.size()];
		for (int j = 0; j < headers.size(); j++) {
			arrHeader[j] = headers.get(j);
		}

		return arrHeader;
	}

	private String[] getSystemFieldsId(String tab) throws IOException {
		// system fields
		// xlsxFile = new XlsxDataProvider(path, fileName, tab);
		// xlsxFile.generateWorkbookFactory();

		ArrayList<String> systemFieldsId = new ArrayList<String>();
		systemFieldIdNumRow = new HashMap<String, Integer>();
		String[] arrSystemFields;
		int blanks = 0;
		int i = 0;

		while (blanks <= 0) {
			String systemField = xlsxFile.getCellValue(rowHeader[0] + i, rowHeader[1] - 1);
			// System.out.println(i + ": " + systemField);
			if (!"".equals(systemField)) {
				systemFieldsId.add(systemField.toString());
				systemFieldIdNumRow.put(systemField, rowHeader[0] + i);
				blanks = 0;
			} else {
				blanks++;
			}
			i++;
		}

		arrSystemFields = new String[systemFieldsId.size()];
		for (int j = 0; j < systemFieldsId.size(); j++) {
			arrSystemFields[j] = systemFieldsId.get(j);
		}

		return arrSystemFields;
	}

	private String getHeaderValue(String systemFieldId, String header) throws IOException {
		// XlsxDataProvider xlsxFile = new XlsxDataProvider(path, fileName, tab);
		// xlsxFile.generateWorkbookFactory();

		String headerValue = xlsxFile.getCellValue(systemFieldIdNumRow.get(systemFieldId), headerNumColumn.get(header));
		// System.out.println(systemFieldId + " - " + header + " - " + headerValue);
		return headerValue;
	}

}
