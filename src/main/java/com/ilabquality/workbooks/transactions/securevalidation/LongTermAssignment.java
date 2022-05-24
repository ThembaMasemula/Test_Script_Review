package com.ilabquality.workbooks.transactions.securevalidation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.EncryptedDocumentException;
import org.junit.BeforeClass;
import org.junit.Test;

public class LongTermAssignment extends BaseTestSecureValidationHRTransactions {

	protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
	protected String amount;
	protected String eventReason;
	protected String assignmentType;
	protected String endDate;

	@BeforeClass
	public static void beforeEach() throws EncryptedDocumentException, IOException {
		// System.out.println("OK: " + parametersOk);
		if (!parametersOk) {
			parameters = data("Long-term Assignment");
			parametersOk = true;
		}

		// System.out.println("OK: " + parametersOk);
	}

	@Test
	public void test() throws Exception {
		try {
			idEmployee = parameters.get(0)[0];
			LocalDateTime yesterday = LocalDateTime.now().minusDays(10);
			LocalDateTime finalDate = LocalDateTime.now().minusDays(1);
			date = yesterday.format(dateFormat);
			endDate = finalDate.format(dateFormat);
			eventReason = "Add Global";
			assignmentType = "Long-term";
		} catch (Exception e) {
			System.out.println("Failed to get data");
			throw e;
		}

		try {
			loginPage.navigateTo(url);
			loginPage.setUsername(username);
			loginPage.setPassword(password);
			// fw.getFullScreenshot();
			loginPage.clickLogin();
			loginPage.proxyNowName(parameters.get(2)[0]);
			loginPage.waitUntilLoadingDisappear();
			fw.timeout(3000);
		} catch (Exception e) {
			System.out.println("Failed to login");
			throw e;
		}

		try {
			adminCenterPage.setSearch(idEmployee);
			fw.timeout(3000);
			// fw.getFullScreenshot();
			adminCenterPage.sendKeyEnterInSearch();
			adminCenterPage.sendKeyEnterInSearch();
		} catch (Exception e) {
			System.out.println("Failed to search");
			throw e;
		}

		try {
			employeeProfilePage.clickActions();
			employeeProfilePage.clickAddGlobalAssignment();
			// fw.getFullScreenshot();
		} catch (Exception e) {
			System.out.println("Failed to select action menu");
			throw e;
		}

		try {
			addGlobalAssignment.setEventReason(eventReason);
			addGlobalAssignment.setAssignmentType(assignmentType);
			addGlobalAssignment.setStartDate(date);
			addGlobalAssignment.setEndDate(endDate);
			addGlobalAssignment.setFirtsCompany();
			addGlobalAssignment.clickBtnContinue();

		} catch (Exception e) {
			System.out.println("Failed to change job information");
			throw e;
		}

		try {
			addGlobalAssignment.setFirstPosition();
			addGlobalAssignment.waitUntilLoadingDisappear();
			addGlobalAssignment.setFirstPayGroup();
			addGlobalAssignment.clickBtnSubmit();
			// fw.getFullScreenshot();
			addGlobalAssignment.waitUntilLoadingDisappear();
			pleaseConfirmYourRequestPage.clickShowWorkflowParticipants();

			// ASSERT APPROVERS TITLE
			List<String> failuresTitle = checkValuesTitle();
			if (!failuresTitle.isEmpty()) {
				mensagem += "Titles not found in HR Document:";
			}
			for (int i = 0; i < failuresTitle.size(); i++) {
				mensagem += System.lineSeparator() + failuresTitle.get(i);
			}

			// ASSERT APPROVERS NAME
			List<String> failuresName = checkValuesName();
			if (!failuresName.isEmpty()) {
				mensagem += "Names not found in HR Document:";
			}
			for (int i = 0; i < failuresName.size(); i++) {
				mensagem += System.lineSeparator() + failuresName.get(i);
			}

			// ASSERT CC TITLE
			List<String> ccTitle = checkValuesCCTitle();
			if (!ccTitle.isEmpty()) {
				mensagem += "Titles not found in HR Document:";
			}
			for (int i = 0; i < ccTitle.size(); i++) {
				mensagem += System.lineSeparator() + ccTitle.get(i);
			}

			// ASSERT CC NAME
			List<String> ccName = checkValuesCCName();
			if (!ccName.isEmpty()) {
				mensagem += "Names not found in HR Document:";
			}
			for (int i = 0; i < ccName.size(); i++) {
				mensagem += System.lineSeparator() + ccName.get(i);
			}

			// System.out.println(mensagem);
			fw.assertTrue(failuresTitle.isEmpty() && failuresName.isEmpty() && ccName.isEmpty() && ccTitle.isEmpty());

		} catch (Exception e) {
			System.out.println("Failed to add Global Assignment Details");
			throw e;
		}
	}
}
