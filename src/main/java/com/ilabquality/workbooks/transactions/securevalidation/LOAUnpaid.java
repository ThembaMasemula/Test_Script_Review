package com.ilabquality.workbooks.transactions.securevalidation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.EncryptedDocumentException;
import org.junit.BeforeClass;
import org.junit.Test;

public class LOAUnpaid extends BaseTestSecureValidationHRTransactions {

	protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
	private String[] approvers;
	private String[] approversTitle;

	@BeforeClass
	public static void beforeEach() throws EncryptedDocumentException, IOException {
		// System.out.println("OK: " + parametersOk);
		if (!parametersOk) {
			parameters = data("LOA Unpaid");
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
			adminCenterPage.setSearch("vacation");
			fw.timeout(3000);
			adminCenterPage.sendKeyEnterInSearch();
			adminCenterPage.sendKeyEnterInSearch();
		} catch (Exception e) {
			System.out.println("Failed to search");
			throw e;
		}

		try {
			timeOffPage.setDate(date, "");
			timeOffPage.clickNewAbsence();
			timeOffPage.setTimeType("Vacation");
			timeOffPage.clickSubmit();
			timeOffPage.waitUntilLoadingDisappear();
		} catch (Exception e) {
			System.out.println("Failed to create an new absence");
			throw e;
		}

		try {
			adminCenterPage.clickHomeButton();
			homePage.clickManagePendingRequests();
			if (pendingRequestsPage.isMyFirstRequestWaitingForApprovalExists()) {
				pendingRequestsPage.clickMyFirstRequestWaitingForApproval();
				workflowDetailsPage.clickViewWorkflow();
				approvers = workflowDetailsPage.getApprovers();
				approversTitle = workflowDetailsPage.getApproversTitle();
			}
		} catch (Exception e) {
			System.out.println("Failed to manage pending requests");
			throw e;
		}

		try {

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

			// System.out.println(mensagem);
			fw.assertTrue(failuresTitle.isEmpty() && failuresName.isEmpty());

		} catch (Exception e) {
			System.out.println("Failed to change job information");
			throw e;
		}
	}
}
