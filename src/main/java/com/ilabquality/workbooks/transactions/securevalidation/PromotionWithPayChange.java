package com.ilabquality.workbooks.transactions.securevalidation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.EncryptedDocumentException;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ilabquality.dataprovider.JsonDataProvider;


public class PromotionWithPayChange extends BaseTestSecureValidationHRTransactions {

	/*private Integer profileLoged = null;
	private String profileName = "";
	private String sysFieldId = "";
	private String login = "";
	private String password = "";
	private String employee = "";
	private Integer profile = -1;*/

	protected JsonDataProvider identity;
	protected JsonDataProvider compensation;
	protected JsonDataProvider jobInformation;

	protected String addressLine1;
	protected String amount;
	protected String bankAccountNumber;
	protected String contactEmergencyName;
	protected String eventReason;
	protected String eventReasonFull;
	protected String firstName;
    protected String fullName;
    protected String isFullTimeEmployee;
    protected String jobTitle;
    protected String lastName;
    protected String payGrade;
	protected String phoneNumber;
	protected String position;
	protected String timeProfile;
	protected String timeRecordingVariant;
	protected String timezone;
	protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);

	@BeforeClass
	public static void beforeEach() throws EncryptedDocumentException, IOException {
		//System.out.println("OK: " + parametersOk);
		if (!parametersOk) {
			parameters = data("Promotion - With Pay Change");
			parametersOk = true;
		}
		//System.out.println("OK: " + parametersOk);
	}

	@Test
	public void test() throws Exception {
		try {
			idEmployee = parameters.get(0)[0];
			LocalDateTime yesterday = LocalDateTime.now().minusDays(10);
			date = yesterday.format(dateFormat);
			//date = identity.getValue("Promotion - With Pay Change.EFFECTIVEDATE");
			//jobTitle = identity.getValue("Promotion - With Pay Change.jobTitle") + "" + new Random().nextInt(9);
			amount = "5000";

		} catch (Exception e) {
			System.out.println("Failed to get data");
			throw e;
		}

		try {
			loginPage.navigateTo(url);
			loginPage.setUsername(username);
			loginPage.setPassword(password);
			//fw.getFullScreenshot();
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
			//fw.getFullScreenshot();
			adminCenterPage.sendKeyEnterInSearch();
			adminCenterPage.sendKeyEnterInSearch();
		} catch (Exception e) {
			System.out.println("Failed to search");
			throw e;
		}

		try {
			// employeeFilePage.clickActions();
			employeeProfilePage.clickChangeJobAndCompensationInfo();
			//fw.getFullScreenshot();
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
			changeJobCompensationInfoPage.setFirstPosition();
			//changeJobConpensationInfoPage.setJobTitle(jobTitle);
			//changeJobConpensationInfoPage.setPayGrade(payGrade);
			fw.timeout(3000);
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			fw.timeout(1000);
			String codePayGrade = payGradeOld.substring(payGradeOld.indexOf("(") +1, payGradeOld.indexOf(")"));
			Integer code = Integer.parseInt(codePayGrade.substring(codePayGrade.indexOf("-") +1, codePayGrade.length())) + 1;
			//changeJobCompensationInfoPage.restoreLocation();
			changeJobCompensationInfoPage.setPayGrade(payGradeOld.substring(payGradeOld.indexOf("(") +1, payGradeOld.indexOf("-"))+"-" + "0"+code.toString());
			fw.timeout(1000);
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			changeJobCompensationInfoPage.setAmount(amount);
			changeJobCompensationInfoPage.waitUntilLoadingDisappear();
			//fw.getFullScreenshot();
			//changeJobCompensationInfoPage.setFocusRangePenetration();
			changeJobCompensationInfoPage.clickSave();
			try {
				changeJobCompensationInfoPage.waitUntilLoadingDisappear();
				changeJobCompensationInfoPage.clickProceed();
			} catch (Exception e) {
				System.out.println("Proceed did not appear");
			}
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

			// System.out.println(mensagem);
			fw.assertTrue(failuresTitle.isEmpty() && failuresName.isEmpty());

		}catch (Exception e) {
			System.out.println("Failed to change job information");
			throw e;
		}
	}
}
