package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class PersonalInformationPage extends BasePage {

	public PersonalInformationPage(Framework fw) {
		super(fw);
	}

	public void waitTabOpen() throws InterruptedException, ElementNotFoundException, Exception {
		waitUntilElement("PersonalInformationPage.tabPersonalInformation");
	}

	public void setFirstName(String firstName) throws Exception {
		fw.clearText("PersonalInformationPage.txtFirstName");
		fw.clickAndSendText("PersonalInformationPage.txtFirstName", firstName);
	}

	public void clickFirstName() throws Exception {
		fw.clickElement("PersonalInformationPage.txtFirstName");
	}
	public void setLastName(String lastName) throws Exception {
		fw.clearText("PersonalInformationPage.txtLastName");
		fw.clickAndSendText("PersonalInformationPage.txtLastName", lastName);
	}

	public void sendKeyEnterFirstName() throws Exception {
		sendKeyEnter("PersonalInformationPage.txtFirstName");
	}

	public void clickSave() throws ElementNotFoundException, Exception {
		fw.clickElement("PersonalInformationPage.btnSave");
	}




	public void clickShowMoreFields() throws ElementNotFoundException, Exception {
		fw.clickElement("PersonalInformationPage.btnShowMoreFields");
	}

	public void setDate(String date) throws Exception {
		fw.clearText("PersonalInformationPage.txtDate");
		fw.clickAndSendText("PersonalInformationPage.txtDate", date);
	}


}
