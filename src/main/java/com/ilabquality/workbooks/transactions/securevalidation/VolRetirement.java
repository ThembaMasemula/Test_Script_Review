package com.ilabquality.workbooks.transactions.securevalidation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.EncryptedDocumentException;
import org.junit.BeforeClass;
import org.junit.Test;

public class VolRetirement extends BaseTestSecureValidationHRTransactions {

	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
	private String terminationReason;

	@BeforeClass
	public static void beforeEach() throws EncryptedDocumentException, IOException {
		// System.out.println("OK: " + parametersOk);
		if (!parametersOk) {
			parameters = data("Vol Retirement");
			parametersOk = true;
		}
		// System.out.println("OK: " + parametersOk);
	}

	@Test
	public void test() throws Exception {
		try {
			idEmployee = parameters.get(0)[0];
			LocalDateTime yesterday = LocalDateTime.now().minusDays(10);
			date = yesterday.format(dateFormat);
			terminationReason = "Vol Termination - Retirement";
			// date = identity.getValue("Promotion - With Pay Change.EFFECTIVEDATE");
			// jobTitle = identity.getValue("Promotion - With Pay Change.jobTitle") + "" +
			// new Random().nextInt(9);

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
			employeePage.clickActions();
			employeePage.clickTermination();
		} catch (Exception e) {
			System.out.println("Failed to select action menu");
			throw e;
		}

		try {
			terminationPage.sendTerminationDate(date);
			terminationPage.waitUntilLoadingDisappear();
			terminationPage.selectTerminationReason(terminationReason);
			terminationPage.selectOkToRehire();
			Thread.sleep(3000);
			terminationPage.clickSaveTermination();
			/*
			 * //OPTIONAL try { changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			 * changeJobCompensationInfoPage.clickProceed(); } catch (Exception e) {
			 * System.out.println("Proceed did not appear"); }
			 */
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
			System.out.println("Failed to change job information");
			throw e;
		}
	}

}
