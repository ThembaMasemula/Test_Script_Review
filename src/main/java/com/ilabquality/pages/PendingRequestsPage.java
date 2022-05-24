package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class PendingRequestsPage extends BasePage {

	public PendingRequestsPage(Framework fw) {
		super(fw);
	}

	public void clickMyFirstRequestWaitingForApproval() throws ElementNotFoundException, Exception {
		fw.clickElement("PendingRequestsPage.linkFisrtMyRequestsWaitingForApproval");
	}

	public Boolean isMyFirstRequestWaitingForApprovalExists() throws ElementNotFoundException, Exception {
		return !("There are no workflow requests".equals(fw.getText("PendingRequestsPage.txtFisrtMyRequestsWaitingForApproval").trim()));

	}

}
