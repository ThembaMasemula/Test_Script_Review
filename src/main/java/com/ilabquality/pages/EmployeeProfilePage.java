package com.ilabquality.pages;

import org.openqa.selenium.Keys;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class EmployeeProfilePage extends BasePage {


	public EmployeeProfilePage(Framework fw) {
		super(fw);
	}

	public void clickEmploymentLabel() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.employmentLabel");
		fw.clickElement("EmployeeProfilePage.employmentLabel");
	}

	public void clickJobInformationHistory() throws ElementNotFoundException, Exception {
		fw.clickElement("EmployeeProfilePage.jobInformationButtonHistory");
	}

	public void assertEventReason(String expected) throws Exception {
		Thread.sleep(4000);
		fw.assertText("ChangeHistoryPage.lblEventReason", expected);
		Thread.sleep(3000);
		System.out.println("The Reason is: => " + expected);
	}

	public String getManagerName() throws Exception {
		return fw.getText("EmployeeProfilePage.btnSupervisor");
	}

	public void clickActions() throws ElementNotFoundException, Exception {
		fw.waitForLoadPage();
		fw.timeout(2000);
		waitUntilElement("EmployeeProfilePage.btnActions");
		fw.timeout(1000);
		fw.clickElement("EmployeeProfilePage.btnActions");
	}

	public void clickActions2() throws ElementNotFoundException, Exception {
		fw.waitForLoadPage();
		fw.timeout(2000);
		waitUntilElement("//*[@id=\"__button4-inner\"]");
		fw.timeout(1000);
		fw.clickElement("//*[@id=\"__button4-inner\"]");
	}


	public void clickUserPhotoName() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.userPhotoName");
		fw.clickElement("EmployeeProfilePage.userPhotoName");
	}

	public void clickLogout() throws ElementNotFoundException, Exception {
		fw.timeout(1000);
		waitUntilElement("EmployeeProfilePage.menuLogout");
		fw.clickElement("EmployeeProfilePage.menuLogout");
	}

	public void clickChangeJobAndCompensationInfo() throws ElementNotFoundException, Exception {
		// wait element present
		int i = 0;
		while (!fw.isElementPresent("EmployeeProfilePage.optChangeJobAndCompensationInfo", 1)) {
			if (i < 10) {
				i++;
				clickActions();
				continue;
			} else {
				break;
			}
		}
		// then click
		fw.clickElement("EmployeeProfilePage.optChangeJobAndCompensationInfo");
	}

	public void clickHistoryPositionInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryPositionInformation");
		fw.clickElement("EmployeeProfilePage.btnHistoryPositionInformation");
	}

	public void clickHistoryCompensationInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryCompensationInformation");
		fw.clickElement("EmployeeProfilePage.btnHistoryCompensationInformation");
	}

	public void clickHistoryBiographicalInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryiographicalInformation");
		fw.clickElement("EmployeeProfilePage.btnHistoryiographicalInformation");
	}

	public void setFocusOrganizationalInformation() throws ElementNotFoundException, Exception {
		fw.setFocus("EmployeeProfilePage.lblOrganizationalInformation");
	}

	public void setFocusBiographicalInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.lblBiographicalInformation");
		fw.setFocus("EmployeeProfilePage.lblBiographicalInformation");
	}

	public void setFocusPersonalInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.lblPersonalInformation");
		fw.setFocus("EmployeeProfilePage.lblPersonalInformation");
	}

	public void closeHistory() throws ElementNotFoundException, Exception {
		fw.clickElement("EmployeeProfilePage.closeHistory");

	}

	public void clickCancel() throws ElementNotFoundException, Exception {
		fw.clickElement("EmployeeProfilePage.btnCancel");
	}

	public void clickTermination() throws ElementNotFoundException, Exception {

		// wait element present
		int i = 0;
		while (!fw.isElementPresent("EmployeeProfilePage.terminationButton", 2)) {
			if (i < 10) {
				i++;
				clickActions();
				continue;
			} else {
				break;
			}
		}
		// then click
		fw.clickElement("EmployeeProfilePage.terminationButton");

		/*
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		*/
	}


	//termination moved to TerminationPage


	public void clickHistoryPersonalInformation() throws ElementNotFoundException, Exception {
		//fw.clickElement("EmployeeProfilePage.lblContactInfo");
		waitUntilElement("EmployeeProfilePage.btnHistoryPersonalInformation");
		fw.clickElement("EmployeeProfilePage.btnHistoryPersonalInformation");
	}

	public void clickHistoryNationalID() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryNationalID");
		fw.clickElement("EmployeeProfilePage.btnHistoryNationalID");
	}

	public void clickHistoryWorkPermitInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryWorkPermitInfo");
		fw.clickElement("EmployeeProfilePage.btnHistoryWorkPermitInfo");
	}

	public void clickHistoryEmergencyContact() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryEmergencyContact");
		fw.clickElement("EmployeeProfilePage.btnHistoryEmergencyContact");
	}

	public void clickHistoryAddresses() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryAddresses");
		fw.clickElement("EmployeeProfilePage.btnHistoryAddresses");
	}

	public void clickHistoryJobInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryJobInfo");
		fw.clickElement("EmployeeProfilePage.btnHistoryJobInfo");
	}

	public void clickHistoryJobRelationships() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryJobRelationships");
		fw.clickElement("EmployeeProfilePage.btnHistoryJobRelationships");
	}

	public void clickHistoryGlobalAssignmentInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryGlobalAssignmentInfo");
		fw.clickElement("EmployeeProfilePage.btnHistoryGlobalAssignmentInfo");
	}

	public void clickHistoryContingentWorker() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryContingentWorker");
		fw.clickElement("EmployeeProfilePage.btnHistoryContingentWorker");
	}

	public void clickHistoryEmailInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryEmailInfo");
		fw.clickElement("EmployeeProfilePage.btnHistoryEmailInfo");
	}

	public void clickHistoryPhoneInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryPhoneInfo");
		fw.clickElement("EmployeeProfilePage.btnHistoryPhoneInfo");
	}

	public void clickEditPhoneInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditPhoneInfo");
		fw.clickElement("EmployeeProfilePage.btnEditPhoneInfo");
	}

	public void clickEditEmailInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditEmailInfo");
		fw.clickElement("EmployeeProfilePage.btnEditEmailInfo");
	}

	public void clickEditContingentWorker() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditContingentWorker");
		fw.clickElement("EmployeeProfilePage.btnEditContingentWorker");
	}

	public void clickEditGlobalAssignmentInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditGlobalAssignmentInfo");
		fw.clickElement("EmployeeProfilePage.btnEditGlobalAssignmentInfo");
	}

	public void clickEditJobRelationships() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditJobRelationships");
		fw.clickElement("EmployeeProfilePage.btnEditJobRelationships");
	}

	public void clickEditJobInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditJobInfo");
		fw.clickElement("EmployeeProfilePage.btnEditJobInfo");
	}

	public void clickEditPersonalInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditPersonalInformation");
		fw.clickElement("EmployeeProfilePage.btnEditPersonalInformation");
	}

	public void clickHistoryDependents() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryDependents");
		fw.clickElement("EmployeeProfilePage.btnHistoryDependents");
	}

	public void clickHistoryEmploymentInfoAtHire() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryEmploymentInfoAtHire");
		fw.clickElement("EmployeeProfilePage.btnHistoryEmploymentInfoAtHire");
	}

	public void clickEditEmploymentInfoAtHire() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditEmploymentInfoAtHire");
		fw.clickElement("EmployeeProfilePage.btnEditEmploymentInfoAtHire");
	}
	public void clickEditDependents() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditDependents");
		fw.clickElement("EmployeeProfilePage.btnEditDependents");
	}

	public void clickEditPositionInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditPositionInformation");
		fw.clickElement("EmployeeProfilePage.btnEditPositionInformation");
	}

	public void clickEditNationalId() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditNationalId");
		fw.clickElement("EmployeeProfilePage.btnEditNationalId");
	}

	public void clickEditWorkPermitInfo() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditWorkPermitInfo");
		fw.clickElement("EmployeeProfilePage.btnEditWorkPermitInfo");
	}

	public void clickEditEmergencyContact() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditEmergencyContact");
		fw.clickElement("EmployeeProfilePage.btnEditEmergencyContact");
	}

	public void clickEditAddresses() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditAddresses");
		fw.clickElement("EmployeeProfilePage.btnEditAddresses");
	}

	public void clickEditContactInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditContactInformation");
		fw.clickElement("EmployeeProfilePage.btnEditContactInformation");
	}

	public void clickMeBtn() throws Exception {
		Thread.sleep(6000);
		waitUntilElement("EmployeeProfilePage.btnMe");
		fw.setFocus("EmployeeProfilePage.btnMe");
		fw.clickElement("EmployeeProfilePage.btnMe");
		//fw.clickElement("EmployeeProfilePage.btnMe");
		//fw.sendKeys("EmployeeProfilePage.btnMe", Keys.ENTER);
		Thread.sleep(5000);
		fw.sendKeys("EmployeeProfilePage.btnMe", Keys.ARROW_DOWN);
		fw.sendKeys("EmployeeProfilePage.btnMe", Keys.ARROW_DOWN);
		fw.sendKeys("EmployeeProfilePage.btnMe", Keys.ARROW_DOWN);
		fw.sendKeys("EmployeeProfilePage.btnMe", Keys.ARROW_DOWN);
		fw.sendKeys("EmployeeProfilePage.btnMe", Keys.ENTER);
	}

	public void setFocusAddresses() throws ElementNotFoundException, Exception {
		Thread.sleep(8000);
		fw.waitForLoadPage();

		waitUntilElement("EmployeeProfilePage.lblAddresses");
		Thread.sleep(5000);
		fw.clickElement("EmployeeProfilePage.lblContactInfo");
		fw.clickElement("EmployeeProfilePage.lblAddresses");
		fw.setFocus("EmployeeProfilePage.lblAddresses");
	}

	public void setFocusAddresses2() throws ElementNotFoundException, Exception {
		Thread.sleep(8000);
		fw.waitForLoadPage();

		waitUntilElement("EmployeeProfilePage.lblContactInfo");
		Thread.sleep(5000);
		fw.clickElement("EmployeeProfilePage.lblContactInfo");
		Thread.sleep(3000);
		fw.clickElement("EmployeeProfilePage.lblAddresses");
		Thread.sleep(3000);
		fw.setFocus("EmployeeProfilePage.lblAddresses2");
	}

	public void setFocusJobRelationship() throws ElementNotFoundException, Exception {
		fw.setFocus("EmployeeProfilePage.lblJobRelationship");
	}

	public void setFocusContactInformation() throws ElementNotFoundException, Exception {
		fw.waitForLoadPage();
		waitUntilElement("EmployeeProfilePage.lblContactInformation");
		fw.setFocus("EmployeeProfilePage.lblContactInformation");
	}

	public void verifyPrimaryEmailChanges(String primaryEmailExpected)
			throws InterruptedException, ElementNotFoundException, Exception {
		fw.setFocus("EmployeeProfilePage.lblPrimaryEmail");
		fw.assertEquals("EmployeeProfilePage.lblPrimaryEmail", primaryEmailExpected);
	}

	public void verifyPhoneNumberChanges(String phoneNumberExpected)
			throws InterruptedException, ElementNotFoundException, Exception {
		fw.setFocus("EmployeeProfilePage.lblPrimaryPhoneNumber");
		fw.assertTrue(fw.getText("EmployeeProfilePage.lblPrimaryPhoneNumber").contains(phoneNumberExpected));
	}

	public void clickEditContactEmergency() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditContactEmergency");
		fw.clickElement("EmployeeProfilePage.btnEditContactEmergency");
	}

	public void clickEditCompensationInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditCompensationInformation");
		fw.clickElement("EmployeeProfilePage.btnEditCompensationInformation");
	}

	public void verifyContactEmergencyNameChanges(String nameEmergency)
			throws InterruptedException, ElementNotFoundException, Exception {
		fw.setFocus("EmployeeProfilePage.lblContactEmergencyName");
		fw.assertEquals("EmployeeProfilePage.lblContactEmergencyName", nameEmergency);
	}

	public void clickEditPaymentInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditPaymentInformation");
		fw.clickElement("EmployeeProfilePage.btnEditPaymentInformation");
	}

	public void clickEditPayComponent() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditPayComponent");
		fw.clickElement("EmployeeProfilePage.btnEditPayComponent");
	}

	public void clickHistoryPayComponent() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryPayComponent");
		fw.clickElement("EmployeeProfilePage.btnHistoryPayComponent");
	}

	public void clickHistoryPaymentInformation() throws ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnHistoryPaymentInformation");
		fw.clickElement("EmployeeProfilePage.btnHistoryPaymentInformation");
	}

	public void setFocusPaymentInformation() throws ElementNotFoundException, Exception {
		fw.waitForLoadPage();
		waitUntilElement("EmployeeProfilePage.lblPaymentInformation");
		fw.setFocus("EmployeeProfilePage.lblPaymentInformation");
	}

	public boolean getPositionInformationChangeNeedApproval()
			throws InterruptedException, ElementNotFoundException, Exception {
		setFocusJobRelationship();
		return fw.isElementPresent("EmployeeProfilePage.lblPositionInformationChangePendingApproval", 2);
	}

	public void clickPositonInformationChangePendingApproval() throws ElementNotFoundException, Exception {
		fw.clickElement("EmployeeProfilePage.lblPositionInformationChangePendingApproval");
	}

	public void clickPersonalInformationChangePendingApproval() throws ElementNotFoundException, Exception {
		fw.clickElement("EmployeeProfilePage.lblPersonalInformationChangePendingApproval");
	}

	public void clickAddressesChangePendingApproval() throws ElementNotFoundException, Exception {
		fw.clickElement("EmployeeProfilePage.lblAddressesChangePendingApproval");
	}

	public void clickCompensationLabel() throws InterruptedException, ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.compensationLabel");
		fw.clickElement("EmployeeProfilePage.compensationLabel");
	}

	public void clickViewWorkflow() throws ElementNotFoundException, Exception {
		fw.clickElement("WorkflowDetailsPage.viewWorkflowParticipants");
	}

	public String[] getApprovers() throws Exception {
		String[] approvers = new String[2];

		approvers[0] = fw.getText("WorkflowDetailsPage.approverOne");

		try {
			approvers[1] = fw.getText("WorkflowDetailsPage.approverTwo");
		} catch (Exception e) {
			approvers[1] = null;
		}

		return approvers;
	}

	public void closeWorkflow() throws ElementNotFoundException, Exception {
		fw.clickElement("WorkflowDetailsPage.buttonClose");
	}

	public void dropdownUser() throws ElementNotFoundException, Exception {
		fw.clickElement("AdminCenterPage.userArrowDown");
	}

	public void clickProxyNow() throws ElementNotFoundException, Exception {
		fw.clickElement("AdminCenterPage.optProxyNow");
	}

	public void selectUserProxy(String user) throws Exception {
		fw.sendText("ProxyPage.userProxy", user);
		Thread.sleep(1000);
		fw.sendKeys("ProxyPage.userProxy", Keys.ARROW_DOWN);
		fw.sendKeys("ProxyPage.userProxy", Keys.ARROW_DOWN);
		fw.sendKeys("ProxyPage.userProxy", Keys.ENTER);
	}

	public void targetUserOk() throws ElementNotFoundException, Exception {
		fw.clickElement("ProxyPage.buttonOk");
	}

	public void clickCardApprove() throws ElementNotFoundException, Exception {
		fw.clickElement("ProxyPage.approveRequests");
	}

	public void clickApprove() throws ElementNotFoundException, Exception {
		fw.clickElement("ProxyPage.buttonApprove");
	}

	public void clickAddGlobalAssignment() throws ElementNotFoundException, Exception {
		fw.clickElement("EmployeeProfilePage.optAddGlobalAssignment");
	}
	public void clickRbHomeAssignment() throws Exception {
		String locator = "EmployeeProfilePage.rbHomeAssignment";
		while (!fw.isElementPresent(locator)) {
			Thread.sleep(2000);
		}
		fw.clickElement(locator);
	}

	public void clickEditBiographicalInformation() throws InterruptedException, ElementNotFoundException, Exception {
		waitUntilElement("EmployeeProfilePage.btnEditBiographicalInformation");
		fw.clickElement("EmployeeProfilePage.btnEditBiographicalInformation");
	}
}

