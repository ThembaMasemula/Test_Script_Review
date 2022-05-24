package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class ContactInformationPage extends BasePage {

	public ContactInformationPage(Framework fw) {
		super(fw);
	}

	public void waitTabOpen() throws InterruptedException, ElementNotFoundException, Exception {
		waitUntilElement("ContactInformationPage.tabPersonalInformation");
	}

	public void setPrimaryEmail(String email) throws Exception {
		fw.clearText("ContactInformationPage.txtPrimaryEmail");
		fw.clickAndSendText("ContactInformationPage.txtPrimaryEmail", email);
		sendKeyEnter("ContactInformationPage.txtPrimaryEmail");
	}

	public void setPrimaryPhoneNumber(String phoneNumber) throws Exception {
		fw.clearText("ContactInformationPage.txtPrimaryPhoneNumber");
		fw.clickAndSendText("ContactInformationPage.txtPrimaryPhoneNumber", phoneNumber);
		sendKeyEnter("ContactInformationPage.txtPrimaryPhoneNumber");
	}

	public void clickSave() throws ElementNotFoundException, Exception {
		fw.clickElement("ContactInformationPage.btnSave");
	}

}
