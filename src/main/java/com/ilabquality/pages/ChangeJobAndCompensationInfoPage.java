package com.ilabquality.pages;

import org.openqa.selenium.Keys;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class ChangeJobAndCompensationInfoPage extends BasePage {

	public ChangeJobAndCompensationInfoPage(Framework fw) {
		super(fw);
	}

	public void clickJobInformation() throws ElementNotFoundException, Exception {
		fw.clickElement("ChangeJobAndCompensationInfoPage.chkJobInformation");
	}

	public void setDateWillChangeEffects(String date) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtDateWillChangeEffect";
		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, date);
		sendKeyEnter(locator);
	}

	public void setJobTitle(String jobTitle) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtJobTitle";
		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, jobTitle);
		sendKeyEnter(locator);
	}

	public void clickSave() throws ElementNotFoundException, Exception {
		fw.clickElement("ChangeJobAndCompensationInfoPage.btnSave");
	}

	public void clickClose() throws ElementNotFoundException, Exception {
		fw.clickElement("ChangeJobAndCompensationInfoPage.btnClose");
	}

	public void setTimeProfile(String timeProfile) throws InterruptedException, ElementNotFoundException, Exception {

		String locator = "ChangeJobAndCompensationInfoPage.txtTimeProfile";
		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, timeProfile);
		Thread.sleep(3000);
		fw.clickElement(locator);
		Thread.sleep(5000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setTimeRecordingVariant(String timeRecordingVariant)
			throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtTimeRecordingVariant";

		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, timeRecordingVariant);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);

		Thread.sleep(5000);
	}

	public void setLocation(String location) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtLocation";

		fw.clearText(locator);
		fw.sendText(locator, location);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public String getLocation() throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtLocation";

		waitUntilElement(locator);
		return fw.getElementAttribute(locator, "value");
	}

	public void setPayGrade(String payGrade) throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtPayGrade";

		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, payGrade);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public String getPayGrade() throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtPayGrade";

		waitUntilElement(locator);
		return fw.getElementAttribute(locator, "value");
	}

	public void setAmount(String amount) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAmount";

		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, amount);
		sendKeyEnter(locator);
	}

	public void clickCompensationInformation() throws ElementNotFoundException, Exception {
		fw.clickElement("ChangeJobAndCompensationInfoPage.chkCompensationInformation");
	}

	public void setPosition(String position) throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.comboPosition";

		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, position);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setFirstPosition() throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.comboPosition";
		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, "");
		Thread.sleep(3000);
		sendKeyDown(locator);
		Thread.sleep(1000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setFirstJobClassification() throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtJobClassification";
		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, "");
		Thread.sleep(3000);
		sendKeyDown(locator);
		Thread.sleep(1000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setFirstLocation() throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtLocation";
		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, "");
		Thread.sleep(3000);
		sendKeyDown(locator);
		Thread.sleep(1000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setDifferentLocation() throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtLocation";
		waitUntilElement(locator);
		fw.clearText(locator);
		String actualLocation = fw.getText(locator);
		fw.sendText(locator, "");
		Thread.sleep(3000);
		sendKeyDown(locator);
		Thread.sleep(1000);
		sendKeyDown(locator);
		sendKeyEnter(locator);

		try {
			waitUntilLoadingDisappear(2);
			if(actualLocation.equals(fw.getText("ChangeJobAndCompensationInfoPage.txtLocationOld"))) {
				fw.sendText(locator, "");
				Thread.sleep(3000);
				sendKeyDown(locator);
				Thread.sleep(1000);
				sendKeyDown(locator);
				sendKeyDown(locator);
				sendKeyEnter(locator);
			}
		} catch (Exception e) {

		}


	}
	public void setDifferentTimeProfile() throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtTimeProfile";
		waitUntilElement(locator);
		fw.clearText(locator);
		String actualTimeProfile = fw.getText(locator);
		fw.sendText(locator, "");
		Thread.sleep(3000);
		sendKeyDown(locator);
		Thread.sleep(1000);
		sendKeyDown(locator);
		sendKeyEnter(locator);

		try {
			waitUntilLoadingDisappear(2);
			if(actualTimeProfile.equals(fw.getText("ChangeJobAndCompensationInfoPage.txtTimeProfileOld"))) {
				fw.sendText(locator, "");
				Thread.sleep(3000);
				sendKeyDown(locator);
				Thread.sleep(1000);
				sendKeyDown(locator);
				sendKeyDown(locator);
				sendKeyEnter(locator);
			}
		} catch (Exception e) {

		}


	}
	public void setPositionForLocation(String location) throws InterruptedException, ElementNotFoundException, Exception {
		setPosition(location);
	}

	public void setFocusRangePenetration() throws Exception {
		fw.setFocus("ChangeJobAndCompensationInfoPage.lblRangePenetration");
	}

	public void clickProceed() throws ElementNotFoundException, Exception {
		fw.clickElement("ChangeJobAndCompensationInfoPage.btnProceed");
	}

	public void clickAddCompensation() throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.btnAddCompensation";

		scrollToAddCompensation();
		fw.clickElement(locator);
		Thread.sleep(3000);
	}

	public void scrollToAddCompensation() throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.btnAddCompensation";

		fw.scrollUntilElementByXpath(locator);
	}

	public void addSetPayComponentCompensation(String payComponent) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAddPayComponentCompensation";

		fw.clearText(locator);
		fw.sendText(locator, payComponent);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void addSetAmountCompensation(String amount) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAddAmountCompensation";

		fw.clearText(locator);
		fw.sendText(locator, amount);
	}

	public void addSetCurrencyCompensation(String currency) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAddCurrencyCompensation";

		fw.clearText(locator);
		fw.sendText(locator, currency);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void addSetFrequencyCompensation(String frequency) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAddFrequencyCompensation";

		fw.clearText(locator);
		fw.sendText(locator, frequency);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
		Thread.sleep(5000);
	}

	// Recurring Payments and Deductions

	public void setPayGroup(String payGroup) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtPayGroup";

		fw.clearText(locator);
		fw.sendText(locator, payGroup);
		Thread.sleep(3000);
		fw.sendKeys(locator, Keys.ARROW_DOWN);
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void clickAddRecurring() throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.btnAddRecurring";

		fw.scrollUntilElementByXpath(locator);
		fw.clickElement(locator);
		Thread.sleep(3000);
	}

	public void addSetPayComponentRecurring(String payComponent) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAddPayComponentRecurring";

		fw.clearText(locator);
		fw.sendText(locator, payComponent);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void addSetAmountRecurring(String amount) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAddAmountRecurring";

		fw.clearText(locator);
		fw.sendText(locator, amount);
	}

	public void addSetCurrencyRecurring(String currency) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAddCurrencyRecurring";

		fw.clearText(locator);
		fw.sendText(locator, currency);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void addSetFrequencyRecurring(String frequency) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtAddFrequencyRecurring";

		fw.clearText(locator);
		fw.sendText(locator, frequency);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
		Thread.sleep(5000);
		fw.isElementPresent(locator);
	}

	public void restorePayGrade() throws Exception {
		String payGrade = getPayGradeOld();
		setPayGrade(payGrade.substring(payGrade.indexOf("(") + 1, payGrade.indexOf(")") - 1));
	}

	private String getPayGradeOld() throws Exception {
		return fw.getText("ChangeJobAndCompensationInfoPage.txtPayGradeOld");
	}

	public void restoreDivision() throws Exception {
		String divisionOld = getDivisionOld();
		setDivision(divisionOld.substring(0, divisionOld.indexOf("(")).trim());
	}

	private String getDivisionOld() throws Exception {
		return fw.getText("ChangeJobAndCompensationInfoPage.txtDivisionOld");
	}

	public void setDivision(String division) throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtDivision";

		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, division);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void restoreDepartment() throws Exception {
		String departmentOld = getDepartmentOld();
		setDepartment(departmentOld.substring(0, departmentOld.indexOf("(")).trim());
	}

	private String getDepartmentOld() throws Exception {
		return fw.getText("ChangeJobAndCompensationInfoPage.txtDepartmentOld");
	}

	public void setDepartment(String department) throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.txtDepartment";

		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, department);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setTimezone(String timezone) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.comboTimeZone";

		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, timezone);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setIsFulltimeEmployee(String isFulltimeEmployee) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.comboIsFulltimeEmployee";

		waitUntilElement(locator);
		fw.clearText(locator);
		fw.sendText(locator, isFulltimeEmployee);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setEmployeeClass(String employeeClass) throws Exception {
		String locator = "ChangeJobAndCompensationInfoPage.comboEmployeeClass";

		waitUntilElement(locator);
		fw.clickElement(locator);
		//fw.clearText(locator);
		fw.sendText(locator, employeeClass);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void setEmploymentType(String employmentType) throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.comboEmploymentType";

		waitUntilElement(locator);
		fw.clickElement(locator);
		//fw.clearText(locator);
		fw.sendText(locator, employmentType);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void clickViewOrgChart() throws ElementNotFoundException, Exception {
		fw.clickElement("ChangeJobAndCompensationInfoPage.btnViewOrgChart");
	}

	public void clickUpOneLevel() throws ElementNotFoundException, Exception {
		fw.clickElement("ChangeJobAndCompensationInfoPage.btnViewOrgChartUpOneLevel");
	}

	public String getLevelAbove() throws Exception {
		return fw.getText("ChangeJobAndCompensationInfoPage.lblPositionAbove");
	}

	public void restoreLocation() throws Exception {
		String location = fw.getText("ChangeJobAndCompensationInfoPage.lblOldLocation").trim();
		String[] loc = location.split(" ");
		setLocation(loc[0] + " " + loc[1]);
	}

}
