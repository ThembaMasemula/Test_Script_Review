package com.ilabquality.workbooks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;

import com.ilabquality.dataprovider.XlsxDataProvider;

public class HRDataEntryWorkbook {

	private String tab;
	private XlsxDataProvider xlsxFile;
	private Integer[] rowHeader;
	private Map<String, Integer> headerNumColumn;
	private Map<String, Integer> systemFieldIdNumRow;

	public HRDataEntryWorkbook(String path, String fileName, String sheet)
			throws EncryptedDocumentException, IOException {
		path.trim();
		if (!fileName.trim().contains(".xlsx")) {
			fileName.trim().concat(".xlsx");
		}
		fileName.trim();
		this.xlsxFile = new XlsxDataProvider(path, fileName, sheet);
		this.xlsxFile.generateWorkbookFactory();
	}

	public Map<String, Map<String, String>> readSheet(String sheet, String header) throws IOException {

		tab = sheet;

		xlsxFile.setSheetName(tab);
		// xlsxFile.generateWorkbookFactory();

		Map<String, String> mapOfHeaderValue = new HashMap<String, String>();

		Map<String, Map<String, String>> mapOfSystemFieldIdHeader = new HashMap<String, Map<String, String>>();

		// header
		String[] arrHeader = getHeaders(sheet, header);

		// system fields
		String[] arrSystemFields = getKey(sheet);

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
		int i = rowHeader[1] - 2;
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

	private String[] getKey(String tab) throws IOException {
		// system fields
		// xlsxFile = new XlsxDataProvider(path, fileName, tab);
		// xlsxFile.generateWorkbookFactory();

		ArrayList<String> systemFieldsId = new ArrayList<String>();
		systemFieldIdNumRow = new HashMap<String, Integer>();
		String[] arrSystemFields;
		int blanks = 0;
		int i = 0;
		ArrayList<String> fields = new ArrayList<>();
		fields.add("pos0");
		HashMap<String, Integer> duplicateCounter = new HashMap<>();
		while (blanks <= 0) {
			rowHeader = xlsxFile.getCellPositionNumber("Event Reason / Action (EC)");
			String systemField = xlsxFile.getCellValue(rowHeader[0] + i, rowHeader[1] - 1);
	//		System.out.println(i + ": " + systemField);
			boolean isDuplicate = false;

			if(duplicateCounter.get(systemField) == null) {
				duplicateCounter.put(systemField, 0);
			}


			for(int j=0;j<fields.size();j++) {
				if(systemField.equals(fields.get(j))) {
					duplicateCounter.put(systemField, duplicateCounter.get(systemField) + 1);
					systemField += "(" + duplicateCounter.get(systemField) + ")";
					System.out.println(systemField);
					isDuplicate = true;
				}
			}
			if(!isDuplicate) {
				fields.add(systemField);
			}
			/*
			 * try { systemFieldIdNumRow.get(systemField); systemField += "(1)";
			 * }catch(Exception e) {
			 *
			 * }
			 */

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
			arrSystemFields[j] = systemFieldsId.get(j).trim();
		}

		/*
		 * int duplicates = 0; for (int j = 0; j < arrSystemFields.length; j++) {
		 * duplicates = 0; for (int k = j+1; k < arrSystemFields.length; k++) { if (k!=j
		 * && arrSystemFields[k] == arrSystemFields[j]) { duplicates++;
		 * arrSystemFields[j] += "(" + duplicates + ")"; } } }
		 */

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
