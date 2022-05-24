package com.ilabquality.workbooks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;

import com.ilabquality.dataprovider.XlsxDataProvider;

public class HRTransactionWorkbook {

	private String path;
	private String fileName;
	private String tab;
	private XlsxDataProvider xlsxFile;

	public HRTransactionWorkbook(String path, String fileName) throws EncryptedDocumentException, IOException {
		this.path = path.trim();
		if (!fileName.trim().contains(".xlsx")) {
			fileName.trim().concat(".xlsx");
		}
		this.fileName = fileName.trim();
		this.xlsxFile = new XlsxDataProvider(path, fileName, "");
		System.out.println("Building data...");
		this.xlsxFile.generateWorkbookFactory();
	}

	public HashMap<String, String[]> readSheetEventReasons(String transaction, String configuration)
			throws IOException {
		tab = "1 - Event Reasons";
		//XlsxDataProvider xlsxFile = new XlsxDataProvider(path, fileName, tab);
		xlsxFile.setSheetName(tab);
		Integer[] rowNumGlobal = xlsxFile.getCellPositionNumber(transaction);
		Integer[] rowNumEventReason = xlsxFile.getCellPositionNumber(configuration);
		Integer rowNextWithValue = xlsxFile.getNextRowWithValue(rowNumGlobal[0], rowNumGlobal[1]);
		//System.out.println(configuration);
		String[] eventReason = new String[rowNextWithValue - 1 - (rowNumGlobal[0] - 1)];
		HashMap<String, String[]> map = new HashMap<String, String[]>();

		for (int i = rowNumGlobal[0] - 1; i < rowNextWithValue - 1; i++) {
			String eventReasonValue = xlsxFile.getValue(i, rowNumEventReason[1]).toString();
			//System.out.println(eventReasonValue);
			if (eventReasonValue != null) {
				eventReason[i - rowNumGlobal[0] + 1] = eventReasonValue;
			}
			map.put(configuration, eventReason);
		}
		//map.put(configuration, eventReason);
		return map;
	}

	public List<Map<String, String[]>> readSheetEventReasons(String transaction) throws IOException {
		List<Map<String, String[]>> listOfMaps = new ArrayList<Map<String, String[]>>();

		String[] configuration = new String[] { "Event Reason Code", "Event Reason", "Event",
				"Resulting Employee Status", "Will be Derived" };

		for (int i = 0; i < configuration.length; i++) {
			listOfMaps.add(readSheetEventReasons(transaction, configuration[i]));
		}
		return listOfMaps;
	}

	public Map<String, Object> readSheetEventReasons(String[] transactions) throws IOException {

		List<Map<String, String[]>> listOfHeaders = new ArrayList<Map<String, String[]>>();
		Map<String, Object> mapOfTransactions = new HashMap<String, Object>();
		String[] configuration = new String[] { "Event Reason Code", "Event Reason", "Event",
				"Resulting Employee Status", "Will be Derived" };
		for (int i = 0; i < transactions.length; i++) {
			//System.out.println(transactions[i]);
			for (int j = 0; j < configuration.length; j++) {
				listOfHeaders.add(readSheetEventReasons(transactions[i], configuration[j]));
			}
			mapOfTransactions.put(transactions[i], listOfHeaders);
		}
		return mapOfTransactions;
	}

	public Map<String, Object> readSheetEventReasons(Integer rowHeader, Integer columnKey) throws IOException {
		tab = "1 - Event Reasons";

		List<Map<String, String[]>> listOfHeaders = null;
		Map<String, Object> mapOfTransactions = new HashMap<String, Object>();
		XlsxDataProvider xlsxFile = new XlsxDataProvider(path, fileName, tab);

		// header
		ArrayList<String> headers = new ArrayList<String>();
		String[] arrHeader;
		int blanks = 0;
		int i = 0;
		while (blanks <= 30) {
			i++;
			String header = xlsxFile.getValue(rowHeader - 1, i).toString();
			if (!"".equals(header)) {
				headers.add(header.toString());
				blanks = 0;
			} else {
				blanks++;
			}
		}

		arrHeader = new String[headers.size()];
		for (int j = 0; j < headers.size(); j++) {
			arrHeader[j] = headers.get(j);
		}

		// transactions
		ArrayList<String> transactions = new ArrayList<String>();
		String[] arrTransactions;
		blanks = 0;
		i = 0;
		while (blanks <= 50) {
			i++;
			String transaction = xlsxFile.getValue(i, columnKey).toString();
			if (!"".equals(transaction)) {
				transactions.add(transaction.toString());
				blanks = 0;
			} else {
				blanks++;
			}
		}

		arrTransactions = new String[transactions.size()];
		for (int j = 0; j < transactions.size(); j++) {
			arrTransactions[j] = transactions.get(j);
		}

		for (int k = 0; k < arrTransactions.length; k++) {
			System.out.println(arrTransactions[k]);
			listOfHeaders = new ArrayList<Map<String, String[]>>();
			for (int j = 0; j < arrHeader.length; j++) {
				listOfHeaders.add(readSheetEventReasons(arrTransactions[k], arrHeader[j]));
			}
			mapOfTransactions.put(arrTransactions[k], listOfHeaders);
		}

		return mapOfTransactions;
	}

	public HashMap<String, String[]> readSheetEventDerivationReq(String header) throws IOException {
		tab = "2 - Event Derivation Req.";

		XlsxDataProvider xlsxFile = new XlsxDataProvider(path, fileName, tab);

		Integer[] rowNumHeader = xlsxFile.getCellPositionNumber(header);
		Integer rowNextWithValue = xlsxFile.getNextRowWithValue(rowNumHeader[0], rowNumHeader[1]);
		HashMap<String, String[]> map = new HashMap<String, String[]>();

		if (rowNextWithValue == null) {
			String[] rowValue = new String[] { "" };
			map.put(header, rowValue);
			return map;
		}
		Integer rowNextWithoutValue = xlsxFile.getNextRowWithoutValue(rowNextWithValue, rowNumHeader[1]);

		String[] rowValue = new String[rowNextWithoutValue - rowNextWithValue + 1];

		for (int i = rowNextWithValue - 1; i < rowNextWithoutValue; i++) {
			String value = xlsxFile.getValue(i, rowNumHeader[1]).toString();
			System.out.println(value);
			rowValue[i - rowNextWithValue + 1] = value;
		}
		map.put(header, rowValue);
		return map;
	}

	public List<Map<String, String[]>> readSheetEventDerivationReq(String[] derivationReqs) throws IOException {
		List<Map<String, String[]>> listOfMaps = new ArrayList<Map<String, String[]>>();
		for (int i = 0; i < derivationReqs.length; i++) {
			listOfMaps.add(readSheetEventDerivationReq(derivationReqs[i]));
		}
		return listOfMaps;
	}

	public List<Map<String, String[]>> readSheetEventDerivationReq(Integer rowHeader) throws IOException {
		String[] derivationReqs = new String[] { "Functional Scenario", "Rule ID", "Standard Employee Event",
		"Event Reason" };
		List<Map<String, String[]>> listOfMaps = new ArrayList<Map<String, String[]>>();
		for (int i = 0; i < derivationReqs.length; i++) {
			listOfMaps.add(readSheetEventDerivationReq(derivationReqs[i]));
		}
		return listOfMaps;
	}

	public HashMap<String, String[]> readSheetWorkflowRoles(String transaction) throws IOException {
		tab = "4 - Workflow Roles";
		XlsxDataProvider xlsxFile = new XlsxDataProvider(path, fileName, tab);

		Integer[] rowNumlastHeader = xlsxFile.getCellPositionNumber("Notifier Names");
		Integer[] rowNumTransaction = xlsxFile.getCellPositionNumber(transaction);
		//System.out.println(transaction);
		String[] workflowRoles = new String[rowNumlastHeader[1] - 1];
		HashMap<String, String[]> map = new HashMap<String, String[]>();

		for (int i = rowNumTransaction[1] + 1; i < rowNumlastHeader[1] + 1; i++) {
			String eventReasonValue = xlsxFile.getValue(rowNumTransaction[0] - 1, i).toString();
			System.out.println(eventReasonValue);
			workflowRoles[i - rowNumTransaction[1] - 1] = eventReasonValue;
		}
		map.put(transaction, workflowRoles);
		return map;

	}

	public HashMap<String, String[]> readSheetWorkflowRoles(String transaction, String[] header) throws IOException {
		tab = "4 - Workflow Roles";
		XlsxDataProvider xlsxFile = new XlsxDataProvider(path, fileName, tab);

		//Integer[] rowNumHeader = xlsxFile.getCellPositionNumber(header);
		Integer[] rowNumTransaction = xlsxFile.getCellPositionNumber(transaction);
		//System.out.println(transaction);
		String[] workflowRoles = new String[header.length - 1];
		HashMap<String, String[]> map = new HashMap<String, String[]>();

		for (int i = rowNumTransaction[1] + 1; i < header.length + 1; i++) {
			String eventReasonValue = xlsxFile.getValue(rowNumTransaction[0] - 1, i).toString();
			//System.out.println(eventReasonValue);
			workflowRoles[i - rowNumTransaction[1] - 1] = eventReasonValue;
		}
		map.put(transaction, workflowRoles);
		return map;

	}

	public List<Map<String, String[]>> readSheetWorkflowRoles(Integer rowHeader, Integer columnKey) throws IOException {
		tab = "4 - Workflow Roles";
		XlsxDataProvider xlsxFile = new XlsxDataProvider(path, fileName, tab);
		List<Map<String, String[]>> listOfHeaders = new ArrayList<Map<String, String[]>>();
		Map<String, Object> mapOfTransactions = new HashMap<String, Object>();

		// header
		ArrayList<String> headers = new ArrayList<String>();
		String[] arrHeader;
		int blanks = 0;
		int i = 0;
		while (blanks <= 30) {
			i++;
			String header = xlsxFile.getValue(rowHeader - 1, i).toString();
			if (!"".equals(header)) {
				headers.add(header.toString());
				blanks = 0;
			} else {
				blanks++;
			}
		}

		arrHeader = new String[headers.size()];
		for (int j = 0; j < headers.size(); j++) {
			arrHeader[j] = headers.get(j);
		}

		// transactions
		ArrayList<String> transactions = new ArrayList<String>();
		String[] arrTransactions;
		blanks = 0;
		i = 0;
		while (blanks <= 50) {
			i++;
			String transaction = xlsxFile.getValue(i, columnKey).toString();
			if (!"".equals(transaction)) {
				transactions.add(transaction.toString());
				blanks = 0;
			} else {
				blanks++;
			}
		}

		arrTransactions = new String[transactions.size()];
		for (int j = 0; j < transactions.size(); j++) {
			arrTransactions[j] = transactions.get(j);
		}


		for (int k = 1; k < arrTransactions.length; k++) {
			//System.out.println(arrTransactions[k]);
			//listOfHeaders = new ArrayList<Map<String, String[]>>();
			//for (int j = 0; j < arrHeader.length; j++) {
				listOfHeaders.add(readSheetWorkflowRoles(arrTransactions[k], arrHeader));
			//}
			//mapOfTransactions.put(arrTransactions[k], listOfHeaders);
		}

		return listOfHeaders;


	}

	public List<Map<String, String[]>> readSheetWorkflowRoles(String[] transactions) throws IOException {
		List<Map<String, String[]>> listOfRoles = new ArrayList<Map<String, String[]>>();
		HashMap<String, String[]> map = new HashMap<String, String[]>();

		for (int i = 0; i < transactions.length; i++) {
			map = readSheetWorkflowRoles(transactions[i]);
			listOfRoles.add(map);

		}
		return listOfRoles;
	}

}
