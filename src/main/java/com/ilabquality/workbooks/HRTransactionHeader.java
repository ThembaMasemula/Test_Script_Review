package com.ilabquality.workbooks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRTransactionHeader {

	private static final long MEGABYTE = 1024L * 1024L;

	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}

	public static void main(String[] args) throws IOException {

		String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "workbooks";
		String fileName = "EC HR Transactions Configuration Workbook.xlsx";
		Integer rowHeader;
		Integer columnKey;

		HRTransactionWorkbook hrTransaction = new HRTransactionWorkbook(path, fileName);

		// 1 event
		// HashMap<String, String[]> eventReasonMap = new HashMap<String, String[]>();
		// eventReasonMap = hrTransaction.readSheetEventReasons("Global Assignment",
		// "Event Reason");

		// array of events
		// String[] transactions = new String[] { "Global Assignment", "Hire", "Job
		// Change", "Return to Work", "Transfer" };
		// listOfTransactions = hrTransaction.readSheetEventReasons(transactions);
		// new
		Map<String, Object> listOfTransactions = new HashMap<String, Object>();
		rowHeader = 2;
		columnKey = 1;
		listOfTransactions = hrTransaction.readSheetEventReasons(rowHeader, columnKey);
		listOfTransactions.get("Transfer");

		// 1 role
		// HashMap<String, String[]> map = new HashMap<String, String[]>();
		// map = hrTransaction.readSheetWorkflowRoles("Short-term Assignment");

		// array of roles
		List<Map<String, String[]>> listOfRoles = new ArrayList<Map<String, String[]>>();
		// String[] roles = new String[] { "Short-term Assignment", "Long-term
		// Assignment", "End Global Assignment", "Away on Global Assignment" };
		rowHeader = 3;
		columnKey = 1;
		//listOfRoles = hrTransaction.readSheetWorkflowRoles(rowHeader, columnKey);

		// HashMap<String, String[]> map = new HashMap<String, String[]>();
		// map = hrTransaction.readSheetEventDerivationReq("Standard Employee Event");

		// array of DerivationReq
		List<Map<String, String[]>> listOfDerivationReq = new ArrayList<Map<String, String[]>>();
		//String[] derivationReqs = new String[] { "Functional Scenario", "Rule ID", "Standard Employee Event",
		//		"Event Reason" };
		// listOfDerivationReq =
		rowHeader = 2;
		//hrTransaction.readSheetEventDerivationReq(rowHeader);

		/*
		// Get the Java runtime
		Runtime runtime = Runtime.getRuntime();
		// Run the garbage collector
		runtime.gc();
		// Calculate the used memory
		long memory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory is bytes: " + memory);
		System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
		 */
		System.out.println("Finish");

	}

}
