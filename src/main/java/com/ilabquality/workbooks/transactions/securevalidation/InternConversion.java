package com.ilabquality.workbooks.transactions.securevalidation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.EncryptedDocumentException;
import org.junit.BeforeClass;
import org.junit.Test;

public class InternConversion extends BaseTestSecureValidationHRTransactions {

	protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
	protected String amount;
	protected String countryCode = "1";
	protected String areaCode = "317";
	protected String phone = "555-0174";
	protected String position = "90002022";
	protected String payScaleType = "Hourly";
	protected String payScaleArea = "Sales";
	protected String payGroup = "USA Bi-weekly";
	protected String firstDateWorked = "Apr 01, 2020";
	protected String isFulltimeEmployee = "No";
	protected String company = "BestRun";
	protected String eventReasonRehire = "New Hire";

	@BeforeClass
	public static void beforeEach() throws EncryptedDocumentException, IOException {
		// System.out.println("OK: " + parametersOk);
		if (!parametersOk) {
			parameters = data("Intern Conversion");
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
			adminCenterPage.setSearch("Rehire");
			fw.timeout(3000);
			adminCenterPage.sendKeyEnterInSearch();
			adminCenterPage.sendKeyDownInSearch();
			adminCenterPage.sendKeyEnterInSearch();
		} catch (Exception e) {
			System.out.println("Failed to search");
			throw e;
		}

		try {
			Thread.sleep(3000);
//			rehireFirstPage.clickRehireEmployee();
			rehireFirstPage.sendEmployeeName(idEmployee);
			rehireFirstPage.clickGo();
			Thread.sleep(3000);
			rehireFirstPage.clickRehireWithNewEmployment();
		} catch (Exception e) {
			System.out.println("Failed to select Employee");
			throw e;
		}

		try {
			// Thread.sleep(3000);
			rehireFormPage.sendHireDate(date);
			rehireFormPage.selectCompany(company);
			rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.selectEventReason(eventReasonRehire);
			// rehireFormPage.clickAddCountry();
			// rehireFormPage.selectCountry(country);
			// rehireFormPage.selectSocialSecurity();
			// rehireFormPage.inputSocialSecurity(socialSecurity);
			// rehireFormPage.selectIsPrimaryCountry();
			// Thread.sleep(2000);
			rehireFormPage.clickContinueFirst();
			// Thread.sleep(6500);
			rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.inputCountryCode(countryCode);
			rehireFormPage.inputAreaCode(areaCode);
			rehireFormPage.inputPhone(phone);
			// Thread.sleep(2000);
			rehireFormPage.clickContinueSecond();
			rehireFormPage.waitUntilLoadingDisappear();
			// Thread.sleep(15000);
			rehireFormPage.selectPosition(position);
			rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.selectIsFulltimeEmployee(isFulltimeEmployee);
			rehireFormPage.waitUntilLoadingDisappear();
			Thread.sleep(1000);
			rehireFormPage.selectPayScaleType(payScaleType);
			Thread.sleep(1000);
			rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.selectPayScaleArea(payScaleArea);
			// Thread.sleep(5000);
			Thread.sleep(1000);
			rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.setFirstDateWorked(firstDateWorked);
			rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.clickButtonContinueThird();
			// Thread.sleep(8000);
			rehireFormPage.waitUntilLoadingDisappear();

			rehireFormPage.selectPayGroup(payGroup);
			// rehireFormPage.clickButtonAddCompensation();
			// rehireFormPage.selectCompensationPayComponent(compensationPayComponent);
			// rehireFormPage.inputCompensationAmount(compensationAmount);
			// rehireFormPage.selectCompensationCurrency(compensationCurrency);
			// rehireFormPage.selectCompensationFrequency(compensationFrequecy);

			Thread.sleep(5000);
			// rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.clickButtonContinueForth();
			rehireFormPage.waitUntilLoadingDisappear();

		} catch (Exception e) {
			System.out.println("Failed to fill form");
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
