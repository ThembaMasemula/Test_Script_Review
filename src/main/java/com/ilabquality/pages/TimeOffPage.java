package com.ilabquality.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.ilabquality.core.Framework;
import com.ilabquality.dataprovider.JsonDataProvider;
import com.ilabquality.exceptions.ElementNotFoundException;

public class TimeOffPage extends BasePage {

	Calendar calendar = Calendar.getInstance();

	public TimeOffPage(Framework fw) {
		super(fw);
	}

	private void selectYear(String year) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.TimeOffPage.selectYear.value", new String[] { year });
		System.out.println(year);
		waitUntilElement("TimeOffPage.btnFirstYear");
		Thread.sleep(1000);
		fw.clickElement("TimeOffPage.btnFirstYear");
		fw.clickElement("TimeOffPage.selectYear");
	}

	private void selectMonth(String month) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.TimeOffPage.selectMonth.value", new String[] { month });
		waitUntilElement("TimeOffPage.btnFirstMonth");
		fw.clickElement("TimeOffPage.btnFirstMonth");
		fw.clickElement("TimeOffPage.selectMonth");
	}

	private void selectDay(String day, String month) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.TimeOffPage.btnDay.value", new String[] { month, month, day });
		waitUntilElement("TimeOffPage.btnDay");
		Thread.sleep(2000);
		fw.clickElement("TimeOffPage.btnDay");
	}

	private void selectSecondYear(String year) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.TimeOffPage.selectSecondYear.value", new String[] { year });
		System.out.println(year);
		waitUntilElement("TimeOffPage.btnSecondYear");
		Thread.sleep(1000);
		fw.clickElement("TimeOffPage.btnSecondYear");
		fw.clickElement("TimeOffPage.selectSecondYear");
	}

	private void selectSecondMonth(String month) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.TimeOffPage.selectSecondMonth.value", new String[] { month });
		waitUntilElement("TimeOffPage.btnSecondMonth");
		fw.clickElement("TimeOffPage.btnSecondMonth");
		fw.clickElement("TimeOffPage.selectSecondMonth");
	}

	private void selectSecondDay(String day, String month) throws ElementNotFoundException, Exception {
		JsonDataProvider dictionary = setDictionary();
		dictionary.setJsonArguments("web.TimeOffPage.btnDay.value", new String[] { month, month, day });
		waitUntilElement("TimeOffPage.btnDay");
		Thread.sleep(2000);
		fw.clickElement("TimeOffPage.btnDay");
	}

	private void formatDate(String date) throws ParseException {
		calendar.setTime(new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).parse(date));
	}

	private Integer getYear(String date) {
		return calendar.get(Calendar.YEAR);
	}

	private String getMonth(String date) {
		String[] monthsName = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		return monthsName[calendar.get(Calendar.MONTH)];
	}

	private Integer getDay(String date) {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public void setDateStart(String date) throws Exception {
		fw.clearText("TimeOffPage.txtDateStart");
		fw.clickAndSendText("TimeOffPage.txtDateStart", date);
	}

	public void setDateFinish(String date) throws Exception {
		fw.clearText("TimeOffPage.txtDateFinish");
		fw.sendText("TimeOffPage.txtDateFinish", date);
	}

	public void setDate(String startDate, String finishDate) throws ElementNotFoundException, Exception {
		if (startDate.equals(finishDate)) {
			formatDate(startDate);
			String sYear = getYear(startDate).toString();
			String sMonth = getMonth(startDate).toString();
			String sDay = getDay(startDate).toString();

			selectYear(sYear);
			selectMonth(sMonth);
			selectDay(sDay, sMonth);

			formatDate(finishDate);
			String fYear = getYear(finishDate).toString();
			String fMonth = getMonth(finishDate).toString();
			String fDay = getDay(finishDate).toString();

			selectSecondYear(fYear);
			selectSecondMonth(fMonth);
			selectSecondDay(fDay, fMonth);
		}
		else {
			if (!"".equals(startDate)) {
				formatDate(startDate);
				String sYear = getYear(startDate).toString();
				String sMonth = getMonth(startDate).toString();
				String sDay = getDay(startDate).toString();

				selectYear(sYear);
				selectMonth(sMonth);
				selectDay(sDay, sMonth);
			}

			if (!"".equals(finishDate)) {
				formatDate(finishDate);
				String fYear = getYear(finishDate).toString();
				String fMonth = getMonth(finishDate).toString();
				String fDay = getDay(finishDate).toString();

				selectSecondYear(fYear);
				selectSecondMonth(fMonth);
				selectSecondDay(fDay, fMonth);
			}
		}
	}

	public void clickNewAbsence() throws ElementNotFoundException, Exception {
		fw.clickElement("TimeOffPage.btnNewAbsence");
	}

	public void setTimeType(String timeType) throws Exception {
		String locator = "TimeOffPage.comboTimeType";
		waitUntilElement(locator);
		Thread.sleep(2000);
		fw.clearText(locator);
		fw.sendText(locator, timeType);
		Thread.sleep(3000);
		sendKeyDown(locator);
		sendKeyEnter(locator);
	}

	public void clickSubmit() throws ElementNotFoundException, Exception {
		Thread.sleep(2000);
		fw.clickElement("TimeOffPage.btnSubmit");
	}

	public void verifyTimeTypeApproved(String date, String timeType)
			throws InterruptedException, ElementNotFoundException, Exception {
		// System.out.println("DATA: "+calendar.getTime().toString());
		formatDate(date);
		JsonDataProvider dictionary = setDictionary();
		System.out.println("AAA: " + getMonth(date).toString().substring(0, 3) + " " + getDay(date).toString());
		dictionary.setJsonArguments("web.TimeOffPage.validateTimeOff.value",
				new String[] { timeType, getMonth(date).toString().substring(0, 3) + " " + getDay(date).toString() });
		waitUntilElement("TimeOffPage.validateTimeOff");
		fw.assertTrue(fw.isElementPresent("TimeOffPage.validateTimeOff"));
		// return fw.isElementPresent("TimeOffPage.validateTimeOff");
	}

}
