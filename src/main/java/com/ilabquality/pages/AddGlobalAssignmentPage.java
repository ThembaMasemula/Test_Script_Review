package com.ilabquality.pages;

import org.openqa.selenium.Keys;

import com.ilabquality.core.Framework;

public class AddGlobalAssignmentPage extends BasePage {

	public AddGlobalAssignmentPage(Framework fw) {
		super(fw);
	}

	public void setEventReason(String eventReason) throws Exception {
		String locator = "AddGlobalAssignment.txtEventReason";

		fw.clearText(locator);
		fw.sendText(locator, eventReason);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void setAssignmentType(String assignmentType) throws Exception {
		String locator = "AddGlobalAssignment.txtAssignmentType";

		fw.clearText(locator);
		fw.sendText(locator, assignmentType);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void setStartDate(String startDate) throws Exception {
		String locator = "AddGlobalAssignment.txtStartDate";

		fw.clearText(locator);
		fw.sendText(locator, startDate);
	}

	public void setEndDate(String endDate) throws Exception {
		String locator = "AddGlobalAssignment.txtEndDate";

		fw.clearText(locator);
		fw.sendText(locator, endDate);
	}

	public void setCompany(String company) throws Exception {
		String locator = "AddGlobalAssignment.txtCompany";

		fw.clearText(locator);
		fw.sendText(locator, company);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void setFirtsCompany() throws Exception {
		String locator = "AddGlobalAssignment.txtCompany";

		fw.clearText(locator);
		fw.sendText(locator, "");
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void clickBtnContinue() throws Exception{
		fw.clickElement("AddGlobalAssignment.btnContinue");
	}

	public void setPosition(String position) throws Exception {
		String locator = "AddGlobalAssignment.txtPosition";

		fw.clearText(locator);
		fw.sendText(locator, position);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void setFirstPosition() throws Exception {
		String locator = "AddGlobalAssignment.txtPosition";

		fw.clearText(locator);
		fw.sendText(locator, "");
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void setPayGroup(String payGroup) throws Exception {
		String locator = "AddGlobalAssignment.txtPayGroup";

		fw.clearText(locator);
		fw.sendText(locator, payGroup);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void setFirstPayGroup() throws Exception {
		String locator = "AddGlobalAssignment.txtPayGroup";

		fw.clearText(locator);
		fw.clickElement(locator);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void clickBtnSubmit() throws Exception{
		fw.clickElement("AddGlobalAssignment.btnSubmit");
	}

}
