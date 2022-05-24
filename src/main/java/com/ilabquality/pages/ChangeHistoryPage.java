package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.dataprovider.JsonDataProvider;
import com.ilabquality.exceptions.ElementNotFoundException;

public class ChangeHistoryPage extends BasePage {

	public ChangeHistoryPage(Framework fw) {
		super(fw);
	}

	public void clickChangeHistoryByDate(String date, String change) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.ChangeHistoryPage.lblChangeHistory.value", new String[] { date, change });
		int i = 0;
		while (!fw.isElementPresent("ChangeHistoryPage.lblChangeHistory", 2) && i < 5) {
			i++;
			clickLoadMore();
		}


		fw.clickElement("ChangeHistoryPage.lblChangeHistory");
		fw.clickElement("ChangeHistoryPage.lblChangeHistory");
	}

	public void clickChangeHistoryReasonByDate(String date, String change) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.ChangeHistoryPage.lblChangeHistoryReason.value",
				new String[] { date, change });
		int i = 0;
		while (!fw.isElementPresent("ChangeHistoryPage.lblChangeHistoryReason", 5) && i < 10) {
			i++;
			try {
				clickLoadMore();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		fw.clickElement("ChangeHistoryPage.lblChangeHistoryReason");
		fw.clickElement("ChangeHistoryPage.lblChangeHistoryReason");
	}

	public void clickLoadMore() throws Exception, ElementNotFoundException {
		fw.clickElement("ChangeHistoryPage.btnLoadMore");
	}

	public String getNewJobTitle(String jobTitle) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.ChangeHistoryPage.lblNewJobTitle.value", new String[] { jobTitle });
		while (!fw.isElementPresent("ChangeHistoryPage.lblNewJobTitle")) {
			fw.timeout(300);
		}
		String newJobTitle = fw.getText("ChangeHistoryPage.lblNewJobTitle");
		return newJobTitle;
	}

	public String getNewFirstName() throws ElementNotFoundException, Exception {
		return fw.getText("ChangeHistoryPage.lblNewFirstName");
	}

	public String getNewLastName() throws ElementNotFoundException, Exception {
		return fw.getText("ChangeHistoryPage.lblNewLastName");
	}

	public void verifyFirstNameChanges(String firstNameExpected) throws Exception {
		fw.assertEquals("ChangeHistoryPage.lblNewFirstName", firstNameExpected);
	}

	public void verifyAddressLine1Changes(String address1Expected) throws Exception {
		Thread.sleep(3000);
		fw.assertEquals("ChangeHistoryPage.lblNewAddressLineB", address1Expected);
		Thread.sleep(3000);
		System.out.println("The address is : =======>>>>>>> " + address1Expected);
		Thread.sleep(3000);
	}

	public void verifyBankNumberAccountChanges(String numberAccountExpected) throws Exception {
		fw.assertEquals("ChangeHistoryPage.lblNewNumberAccountExpected", numberAccountExpected);
	}

	public void verifyAddressesChanges(String address) throws Exception {
		fw.assertEquals("ChangeHistoryPage.lblNewFirstName", address);
	}

	public void verifyLastNameChanges(String lastNameExpected)
			throws InterruptedException, ElementNotFoundException, Exception {
		fw.assertEquals("ChangeHistoryPage.lblNewLastName", lastNameExpected);
	}

	public void verifyJobTitleChanges(String jobTitleExpected) throws Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.ChangeHistoryPage.lblNewJobTitle.value", new String[] { jobTitleExpected });
		while (!fw.isElementPresent("ChangeHistoryPage.lblNewJobTitle")) {
			fw.timeout(300);
		}
		fw.assertEquals("ChangeHistoryPage.lblNewJobTitle", jobTitleExpected);
	}

	public void verifyEventReason(String eventReason) throws Exception {
		waitUntilElement("ChangeHistoryPage.lblEventReason");
		fw.assertEquals("ChangeHistoryPage.lblEventReason", eventReason);
	}

	public void verifyEventReasonFinal(String eventReasonFinal) throws Exception {
		waitUntilElement("ChangeHistoryPage.lblEventReasonFinal");
		fw.assertEquals("ChangeHistoryPage.lblEventReasonFinal", eventReasonFinal);
	}

	public void verifyCompensation(String payComponent, String amount, String position) throws Exception {
		String locatorPayComponent = "ChangeHistoryPage.spanPayComponentCompensation";
		String locatorAmount = "ChangeHistoryPage.spanAmountCompensation";

		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.ChangeHistoryPage.spanPayComponentCompensation.value", new String[] { position });
		dictionary.setJsonArguments("web.ChangeHistoryPage.spanAmountCompensation.value", new String[] { position });

		if (payComponent.toUpperCase().contains("MEAL")) {
			payComponent = "Meal Allowance USA (MEAL_US)";
		} else if (payComponent.toUpperCase().contains("BASESAL_US")) {
			payComponent = "Base Salary USA (BASESAL_US)";
		} else {
			payComponent = "Hourly Rate (1000)";
		}

		waitUntilElement(locatorPayComponent);
		waitUntilElement(locatorAmount);
		fw.assertEquals(locatorPayComponent, payComponent);
		fw.assertEquals(locatorAmount, amount);
	}

	public void verifyRecurring(String payComponent, String amount, String position) throws Exception {
		String locatorPayComponent = "ChangeHistoryPage.spanPayComponentRecurring";
		String locatorAmount = "ChangeHistoryPage.spanAmountRecurring";

		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.ChangeHistoryPage.spanPayComponentRecurring.value", new String[] { position });
		dictionary.setJsonArguments("web.ChangeHistoryPage.spanAmountRecurring.value", new String[] { position });

		if (payComponent.toUpperCase().contains("CHARITY")) {
			payComponent = "Charity Contribution (CHARITY)";

		} else if (payComponent.toUpperCase().contains("PRODUES")) {
			payComponent = "Professional Dues (PRODUES)";

		} else if (payComponent.toUpperCase().contains("TOOL")) {
			payComponent = "Tool Deduction (TOOL)";

		} else if (payComponent.toUpperCase().contains("UNION")) {
			payComponent = "Union Dues (UNION)";

		} else {
			payComponent = "United Way (UNITED_WAY)";
		}

		waitUntilElement(locatorPayComponent);
		waitUntilElement(locatorAmount);
		fw.assertEquals(locatorPayComponent, payComponent);
		Thread.sleep(3000);
		fw.assertEquals(locatorAmount, amount);

	}

	public void waitEffectiveAsOf(String date) throws InterruptedException, ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.ChangeHistoryPage.lblEffectiveAsOf.value", new String[] { date });
		int i = 0;
		while (!fw.isElementPresent("ChangeHistoryPage.lblEffectiveAsOf", 5) && i < 10) {
			i++;
			fw.timeout(500);
		}
		fw.timeout(1000);
	}

	public void closeWindowPersonalHistory() throws Exception {
		sendKeyEsc("ChangeHistoryPage.lblNewFirstName");
	}

	public void closeWindowHistoryChange() throws Exception {
		fw.clickElement("ChangeHistoryPage.btnClose");
		waitUntilElementDisappear("ChangeHistoryPage.btnClose");
	}

	public void verifyEvent(String event) throws Exception {
		waitUntilElement("ChangeHistoryPage.lblEvent");
		fw.assertEquals("ChangeHistoryPage.lblEvent", event);
	}

	public String getEvent() throws Exception {
		return fw.getText("ChangeHistoryPage.lblEvent");
	}

	public void setEvent(String event) throws Exception {
		fw.clearText("ChangeHistoryPage.lblEvent");
		fw.sendText("ChangeHistoryPage.lblEvent", event);
	}

	public void clickEdit() throws ElementNotFoundException, Exception {
		fw.clickElement("ChangeHistoryPage.btnEdit");

	}

	public void clickChangeHistoryByEventName(String startDate, String eventName) throws ElementNotFoundException, Exception {
		String locator = "ChangeHistoryPage.spanGlobalAssignment";
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.ChangeHistoryPage.spanGlobalAssignment.value", new String[] { startDate, eventName });
		int i = 0;
		while (!fw.isElementPresent(locator, 5) && i < 10) {
			i++;
			Thread.sleep(2000);
		}
		fw.clickElement(locator);
	}
}
