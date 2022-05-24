package com.ilabquality.tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.ilabquality.modules.testing.BaseTest;
import org.apache.poi.EncryptedDocumentException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ilabquality.dataprovider.JsonDataProvider;
import com.ilabquality.pages.AdminCenterPage;
import com.ilabquality.pages.ChangeHistoryPage;
import com.ilabquality.pages.EmployeeProfilePage;
import com.ilabquality.pages.LoginPage;
import com.ilabquality.pages.PleaseConfirmYourRequestPage;
import com.ilabquality.pages.RehireFormPage;
import com.ilabquality.pages.RehireInactiveEmployeePage;
import com.ilabquality.pages.TerminationPage;

public class TerminationInVoluntary extends BaseTest {

	private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
	private JsonDataProvider global;
	private String url;
	private String username;
	private String password;
	private String employee;

	private String terminationDate;
	private String terminationReason;
	private String terminationReasonFull;
	private String terminationComment;


	private LoginPage loginPage;
	private AdminCenterPage homePage;
	private RehireInactiveEmployeePage rehireFirstPage;
	private RehireFormPage rehireFormPage;
	private EmployeeProfilePage employeePage;
	private PleaseConfirmYourRequestPage pleaseConfirmYourRequestPage;
	private ChangeHistoryPage changeHistoryPage;
	private AdminCenterPage adminCenterPage;
	private TerminationPage terminationPage;

	private JsonDataProvider address;
	private JsonDataProvider compensationInformation;
	private JsonDataProvider identity;
	private JsonDataProvider jobInformation;

	@Before
	public void setUp() throws EncryptedDocumentException, IOException {

		global = new JsonDataProvider(
				System.getProperty("user.dir") + File.separator + "resources" + File.separator + "global.json");



/*		loginPage = new LoginPage(fw);
		homePage = new AdminCenterPage(fw);
		rehireFirstPage = new RehireInactiveEmployeePage(fw);
		rehireFormPage = new RehireFormPage(fw);
		employeePage = new EmployeeProfilePage(fw);
		pleaseConfirmYourRequestPage = new PleaseConfirmYourRequestPage(fw);
		changeHistoryPage = new ChangeHistoryPage(fw);
		adminCenterPage = new AdminCenterPage(fw);
		terminationPage = new TerminationPage(fw);*/

    /*
		address= new JsonDataProvider(System.getProperty("user.dir") + File.separator + "dataTest"
				+ File.separator + "Inv RIFAddress.json");
		compensationInformation= new JsonDataProvider(System.getProperty("user.dir") + File.separator + "dataTest"
				+ File.separator + "Inv RIFCompensationInformation.json");
		identity= new JsonDataProvider(System.getProperty("user.dir") + File.separator + "data"
				+ File.separator + "Inv RIFIdentity.json");
		jobInformation= new JsonDataProvider(System.getProperty("user.dir") + File.separator + "dataTest"
				+ File.separator + "Inv RIFJobInformation.json");
				*/
    identity= new JsonDataProvider(System.getProperty("user.dir") + File.separator + "data"
      + File.separator + "terminationVoluntary.json");

	}

	@Test
	public void test() throws Exception {

		try {
			Thread.sleep(5000);
			url = global.getValue("Global.urlSystem");
			username = global.getValue("Global.login");
			password = global.getValue("Global.password");


			employee = identity.getValue("TerminationVoluntary.employee");
			terminationDate = identity.getValue("TerminationVoluntary.terminationDate");
			terminationReason = identity.getValue("TerminationVoluntary.terminationReason");
			terminationReasonFull = identity.getValue("TerminationVoluntary.terminationReasonFull");
			terminationComment = identity.getValue("TerminationVoluntary.terminationComment");

		} catch (Exception e) {
			System.out.println("Failed to get data");
			throw e;
		}

		try {
			loginPage.navigateTo(url);
			Thread.sleep(5000);
			loginPage.setUsername(username);
			loginPage.setPassword(password);
			loginPage.clickLogin();
		} catch (Exception e) {
			System.out.println("Failed to login");
			throw e;
		}

		try {
/*			Thread.sleep(7000);
			adminCenterPage.setSearch(employee);
			fw.timeout(3000);
			fw.getFullScreenshot();
			adminCenterPage.sendKeyEnterInSearch();
			adminCenterPage.sendKeyEnterInSearch();*/
		} catch (Exception e) {
			System.out.println("Failed to search");
			throw e;
		}

		try {
			Thread.sleep(5000);
			employeePage.clickActions();
			employeePage.clickTermination();
			terminationPage.sendTerminationDate(terminationDate);
			terminationPage.waitUntilLoadingDisappear();
			terminationPage.selectTerminationReason(terminationReason);
			terminationPage.selectOkToRehire();
			terminationPage.clickSaveTermination();

			pleaseConfirmYourRequestPage.setComment(terminationComment);
			pleaseConfirmYourRequestPage.clickShowWorkflowParticipants();
			String[] approvers = pleaseConfirmYourRequestPage.getApprovers();
			pleaseConfirmYourRequestPage.clickConfirm();
			//employeePage.waitChangesSavedSuccessfullyDisappear();
			for (int i = 0; i < approvers.length; i++) {
				if (approvers[i] != null) {
					employeePage.proxyNowName(approvers[i]);
					employeePage.approveFirst();
				}
			}

		} catch (Exception e) {
			System.out.println("Failed to terminate contract");
			throw e;
		}

		try {
			Thread.sleep(7000);
			adminCenterPage.setSearch(employee);
/*			fw.timeout(5000);
			fw.getFullScreenshot();*/
			adminCenterPage.sendKeyEnterInSearch();
			adminCenterPage.sendKeyEnterInSearch();
		} catch (Exception e) {
			System.out.println("Failed to search");
			throw e;
		}

		try {
			Thread.sleep(8000);
			employeePage.clickEmploymentLabel();
			employeePage.clickJobInformationHistory();
			changeHistoryPage.clickChangeHistoryReasonByDate(
					dateFormat.format(LocalDate.parse(terminationDate, dateFormat).plusDays(1)), terminationReason);
			changeHistoryPage
					.waitEffectiveAsOf(dateFormat.format(LocalDate.parse(terminationDate, dateFormat).plusDays(1)));
			changeHistoryPage.waitUntilLoadingDisappear();
			changeHistoryPage.verifyEventReason(terminationReasonFull);
/*			fw.getFullScreenshot();*/
			changeHistoryPage.closeWindowHistoryChange();
			Thread.sleep(3000);

		} catch (Exception e) {
			System.out.println("Failed to assert");
			throw e;
		}

		/*
		 * Rehire
		 *
		 */

	/*	try {
			homePage.setSearch(eventReason);
			fw.timeout(3000);
			homePage.sendKeyEnterInSearch();
			homePage.sendKeyDownInSearch();
			homePage.sendKeyEnterInSearch();
		} catch (Exception e) {
			System.out.println("Failed to search");
			throw e;
		}

		try {
			rehireFirstPage.sendEmployeeName(employee);
			rehireFirstPage.clickGo();
			rehireFirstPage.clickRehireEmployee();
		} catch (Exception e) {
			System.out.println("Failed to select Employee");
			throw e;
		}

		try {
			//Thread.sleep(3000);
			rehireFormPage.sendHireDate(hireDate);
			rehireFormPage.selectEventReason(eventReason);
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
			// Thread.sleep(8000);
			// rehireFormPage.selectPayScaleType(payScaleType);
			// Thread.sleep(5000);
			// rehireFormPage.selectPayScaleArea(payScaleArea);
			// Thread.sleep(5000);
			rehireFormPage.clickButtonContinueThird();
			// Thread.sleep(8000);
			rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.selectPayGroup(payGroup);
			// rehireFormPage.clickButtonAddCompensation();
			// rehireFormPage.selectCompensationPayComponent(compensationPayComponent);
			// rehireFormPage.inputCompensationAmount(compensationAmount);
			// rehireFormPage.selectCompensationCurrency(compensationCurrency);
			// rehireFormPage.selectCompensationFrequency(compensationFrequecy);
			// Thread.sleep(5000);
			// rehireFormPage.waitUntilLoadingDisappear();
			rehireFormPage.clickButtonContinueForth();
			rehireFormPage.waitUntilLoadingDisappear();

		} catch (Exception e) {
			System.out.println("Failed to fill form");
			throw e;
		}

		/*
		try {
			rehireFormPage.clickViewProfile();
			employeePage.clickEmploymentLabel();
			employeePage.clickJobInformationHistory();
			changeHistoryPage.clickChangeHistoryReasonByDate(
					dateFormat.format(LocalDate.parse(terminationDate, dateFormat).plusDays(1)), terminationReason);
			changeHistoryPage
					.waitEffectiveAsOf(dateFormat.format(LocalDate.parse(terminationDate, dateFormat).plusDays(1)));
			changeHistoryPage.waitUntilLoadingDisappear();
			changeHistoryPage.verifyEventReason(terminationReasonFull);

		} catch (Exception e) {
			System.out.println("Failed to assert");
			throw e;
		}
		*/

	}

	@After
	public void close(){
		try {
/*			fw.endTest();*/
		} catch (Exception e) {
			System.out.println("Failed to close test");
			throw e;

	}
	}

}
