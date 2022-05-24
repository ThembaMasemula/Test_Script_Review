package com.ilabquality.workbooks.transactions.securevalidation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.EncryptedDocumentException;
import org.junit.BeforeClass;
import org.junit.Test;

public class DemotionWithPayChange extends BaseTestSecureValidationHRTransactions {

	protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
	protected String amount;

	@BeforeClass
	public static void beforeEach() throws EncryptedDocumentException, IOException {
		// System.out.println("OK: " + parametersOk);
		if (!parametersOk) {
			parameters = data("Demotion - With Pay Change");
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
			amount = "1";
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
			// employeeFilePage.clickActions();
			employeeProfilePage.clickChangeJobAndCompensationInfo();
			// fw.getFullScreenshot();
		} catch (Exception e) {
			System.out.println("Failed to select action menu");
			throw e;
		}

		try {
			changeJobCompensationInfoPage.clickJobInformation();
			changeJobCompensationInfoPage.clickCompensationInformation();
			changeJobCompensationInfoPage.setDateWillChangeEffects(date);
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			String payGradeOld = changeJobCompensationInfoPage.getPayGrade();
			String location = changeJobCompensationInfoPage.getLocation();
			changeJobCompensationInfoPage.setPositionForLocation(location.substring(0, location.indexOf("(")).trim());
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			changeJobCompensationInfoPage.restoreDivision();
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			fw.timeout(1000);
			changeJobCompensationInfoPage.restoreDepartment();
			fw.timeout(3000);
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			fw.timeout(1000);
			String codePayGrade = payGradeOld.substring(payGradeOld.indexOf("(") + 1, payGradeOld.indexOf(")"));
			Integer code = Integer
					.parseInt(codePayGrade.substring(codePayGrade.indexOf("-") + 1, codePayGrade.length())) - 1;
			changeJobCompensationInfoPage
					.setPayGrade(payGradeOld.substring(payGradeOld.indexOf("(") + 1, payGradeOld.indexOf("-")) + "-"
							+ "0" + code.toString());
			fw.timeout(1000);
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			changeJobCompensationInfoPage.setAmount(amount);
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			changeJobCompensationInfoPage.clickSave();
			/*
			 * //OPTIONAL try { changeJobCompensationInfoPage.waitUntilLoadingDisappear(1);
			 * changeJobCompensationInfoPage.clickProceed(); } catch (Exception e) {
			 * System.out.println("Proceed did not appear"); }
			 */
			pleaseConfirmYourRequestPage.clickShowWorkflowParticipants();

			// ASSERT APPROVERS TITLE
			List<String> failuresTitle = checkValuesTitle();
			if (!failuresTitle.isEmpty()) {
				mensagem += System.lineSeparator() + "Titles not found in HR Document:";
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
				mensagem += System.lineSeparator() + "Titles not found in HR Document:";
			}
			for (int i = 0; i < ccTitle.size(); i++) {
				mensagem += System.lineSeparator() + ccTitle.get(i);
			}

			// ASSERT CC NAME
			List<String> ccName = checkValuesCCName();
			if (!ccName.isEmpty()) {
				mensagem += System.lineSeparator() + "Names not found in HR Document:";
			}
			for (int i = 0; i < ccName.size(); i++) {
				mensagem += System.lineSeparator() + ccName.get(i) + System.lineSeparator();
			}

			// System.out.println(mensagem);
			fw.assertTrue(failuresTitle.isEmpty() && failuresName.isEmpty() && ccName.isEmpty() && ccTitle.isEmpty());

		} catch (Exception e) {
			System.out.println("Failed to change job information");
			throw e;
		}
	}
}
