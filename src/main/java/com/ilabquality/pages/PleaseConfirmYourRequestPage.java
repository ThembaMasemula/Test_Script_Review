package com.ilabquality.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class PleaseConfirmYourRequestPage extends BasePage {

	public PleaseConfirmYourRequestPage(Framework fw) {
		super(fw);
	}

	public void clickShowWorkflowParticipants() throws Exception {
		fw.clickElement("PleaseConfirmYourRequestPage.btnShowWorkflowParticipants");
	}

	public Boolean isShowWorkflowParticipantsAppears() throws Exception {
		return fw.isElementPresent("PleaseConfirmYourRequestPage.btnShowWorkflowParticipants", 3);
	}

	public void setComment(String comment) throws Exception {
		fw.clearText("PleaseConfirmYourRequestPage.txtEnterYourCommentHere");
		fw.clickAndSendText("PleaseConfirmYourRequestPage.txtEnterYourCommentHere", comment);
	}

	public void clickConfirm() throws ElementNotFoundException, Exception {
		fw.clickElement("PleaseConfirmYourRequestPage.btnConfirm");
	}

	public void clickApprove() throws ElementNotFoundException, Exception {
		fw.clickElement("PleaseConfirmYourRequestPage.btnApprove");
	}

	public String[] getApprovers() throws Exception {

		WebDriver driver = fw.getWebDriver();
		List<WebElement> elements = driver
				.findElements(By.xpath("//span[text()='Approvers']/parent::div/parent::div/div/div/div/div/div/span/parent::div/parent::div/descendant::bdi"));
		String[] approvers = new String[elements.size()/2];
		// List<WebElement> elements =
		// fw.findElementsByXpath("PleaseConfirmYourRequestPage.approvers");
		int j = 0;
		for (int i = 0; i < elements.size(); i += 2) {
			approvers[j] = elements.get(i).getText();
			j++;

		}
		return approvers;
	}

	public String[] getApproversTitle() throws Exception {
		WebDriver driver = fw.getWebDriver();
		List<WebElement> elements = driver
				.findElements(By.xpath("//span[text()='Approvers']/parent::div/parent::div/div/div/div/div/div/span/parent::div/parent::div/descendant::bdi"));
		String[] approvers = new String[elements.size()/2];
		// List<WebElement> elements =
		// fw.findElementsByXpath("PleaseConfirmYourRequestPage.approvers");
		int j = 0;
		for (int i = 1; i < elements.size(); i += 2) {
			approvers[j] = elements.get(i).getText();
			j++;

		}
		return approvers;
	}

	public String[] getCCTitle() throws Exception {
		WebDriver driver = fw.getWebDriver();
		List<WebElement> elements = driver
				.findElements(By.xpath("//span[text()='CC']/parent::div/parent::div/div/div/div/div/div/descendant::bdi"));
		String[] ccTitle = new String[elements.size()/2];
		// List<WebElement> elements =
		// fw.findElementsByXpath("PleaseConfirmYourRequestPage.approvers");
		int j = 0;
		for (int i = 1; i < elements.size(); i += 2) {
			ccTitle[j] = elements.get(i).getText();
			j++;

		}
		return ccTitle;
	}

	public String[] getCCName() throws Exception {
		WebDriver driver = fw.getWebDriver();
		List<WebElement> elements = driver
				.findElements(By.xpath("//span[text()='CC']/parent::div/parent::div/div/div/div/div/div/descendant::bdi"));
		String[] ccTitle = new String[elements.size()/2];
		// List<WebElement> elements =
		// fw.findElementsByXpath("PleaseConfirmYourRequestPage.approvers");
		int j = 0;
		for (int i = 0; i < elements.size(); i += 2) {
			ccTitle[j] = elements.get(i).getText();
			j++;

		}
		return ccTitle;
	}


	public void clickOk() throws ElementNotFoundException, Exception {
		fw.clickElement("PleaseConfirmYourRequestPage.btnOk");
	}
}
