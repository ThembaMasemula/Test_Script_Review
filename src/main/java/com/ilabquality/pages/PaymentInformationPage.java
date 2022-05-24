package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class PaymentInformationPage extends BasePage {

	public PaymentInformationPage(Framework fw) {
		super(fw);
	}

	public void waitTabOpen() throws InterruptedException, ElementNotFoundException, Exception {
		waitUntilElement("PaymentInformationPage.tabPaymentInformation");
	}

	public void clickSave() throws ElementNotFoundException, Exception {
		fw.clickElement("PaymentInformationPage.btnSave");
	}

	public void setAccountNumber(String bankAccountNumber) throws Exception {
		fw.clearText("PaymentInformationPage.txtAccountNumber");
		fw.clickAndSendText("PaymentInformationPage.txtAccountNumber", bankAccountNumber);
		sendKeyEnter("PaymentInformationPage.txtAccountNumber");
	}

}
