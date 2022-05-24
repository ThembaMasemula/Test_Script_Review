package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class AddressesPage extends BasePage {

	public AddressesPage(Framework fw) {
		super(fw);
	}

	public void waitTabOpen() throws InterruptedException, ElementNotFoundException, Exception {
		waitUntilElement("AddressesPage.tabAddresses");
	}

	public void setAddressLine1(String addressLine1) throws Exception {
		fw.clearText("AddressesPage.txtAddressLine1");
		fw.clickAndSendText("AddressesPage.txtAddressLine1", addressLine1);
	}

	public void setDate(String date) throws Exception {
		fw.clearText("AddressesPage.txtDate");
		fw.clickAndSendText("AddressesPage.txtDate", date);
		sendKeyEnter("AddressesPage.txtDate");
	}

	public void clickSave() throws ElementNotFoundException, Exception {
		fw.clickElement("AddressesPage.btnSave");
	}

	public void clickConfirm() throws ElementNotFoundException, Exception {
		fw.clickElement("AddressesPage.btnConfirm");
	}

	public void setComment(String comment) throws Exception {
		fw.clearText("AddressesPage.txtEnterYourCommentHere");
		fw.clickAndSendText("AddressesPage.txtEnterYourCommentHere", comment);
	}

}
