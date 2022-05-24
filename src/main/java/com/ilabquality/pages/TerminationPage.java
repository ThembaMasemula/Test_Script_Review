package com.ilabquality.pages;

import org.openqa.selenium.Keys;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class TerminationPage extends BasePage {

	public TerminationPage(Framework fw) {
		super(fw);
	}

	public String getAllListTermination() throws Exception {
		String locator = "//div[contains(@id, 'popup-list-before')]/following-sibling::ul[@role='listbox']";
		return getAllActionList(locator);
	}

	public void clickTerminationReason() throws ElementNotFoundException, Exception {
		//fw.clickElement("TerminationPage.arrowTerminationReason");
		fw.clickElement("TerminationPage.terminationReason");
		// Thread.sleep(5000);
		fw.clearText("TerminationPage.terminationReason");
		fw.sendText("TerminationPage.terminationReason", "");
		Thread.sleep(2000);
		fw.sendKeys("TerminationPage.terminationReason", Keys.ARROW_DOWN);
	}

	public void sendTerminationDate(String date) throws ElementNotFoundException, Exception {
		fw.clearText("TerminationPage.terminationDate");
		fw.sendText("TerminationPage.terminationDate", date);
		sendKeyEnter("TerminationPage.terminationDate");
	}

	public void selectTerminationReason(String reason) throws Exception {
		fw.clickElement("TerminationPage.terminationReason");
		// Thread.sleep(5000);
		fw.clearText("TerminationPage.terminationReason");
		fw.sendText("TerminationPage.terminationReason", reason);
		Thread.sleep(2000);
		fw.sendKeys("TerminationPage.terminationReason", Keys.ARROW_DOWN);
		fw.sendKeys("TerminationPage.terminationReason", Keys.ENTER);
	}

	public void selectOkToRehire() throws Exception {
		fw.clearText("TerminationPage.okayToRehire");
		fw.sendText("TerminationPage.okayToRehire", "Yes");
		Thread.sleep(1000);
		fw.sendKeys("TerminationPage.okayToRehire", Keys.ARROW_DOWN);
		fw.sendKeys("TerminationPage.okayToRehire", Keys.ENTER);
	}

	public void clickSaveTermination() throws ElementNotFoundException, Exception {
		fw.clickElement("TerminationPage.terminationButtonSave");
	}

	public void sendTerminationComment(String comment) throws Exception {
		fw.sendText("TerminationPage.terminationComment", comment);
	}

	public void clickConfirmTermination() throws ElementNotFoundException, Exception {
		fw.clickElement("TerminationPage.terminationConfirm");
	}

	public void clickTerminationPending() throws ElementNotFoundException, Exception {
		fw.clickElement("TerminationPage.terminationLink");
	}


}
