package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class PrimaryEmergencyContactPage extends BasePage {

	public PrimaryEmergencyContactPage(Framework fw) {
		super(fw);
	}

	public void waitTabOpen() throws InterruptedException, ElementNotFoundException, Exception {
		waitUntilElement("PrimaryEmergencyContactPage.tabEmergencyContact");
	}

	public void setName(String name) throws Exception {
		fw.clearText("PrimaryEmergencyContactPage.txtName");
		fw.clickAndSendText("PrimaryEmergencyContactPage.txtName", name);
		sendKeyEnter("PrimaryEmergencyContactPage.txtName");
	}

	public void clickSave() throws ElementNotFoundException, Exception {
		fw.clickElement("PrimaryEmergencyContactPage.btnSave");
	}

}
