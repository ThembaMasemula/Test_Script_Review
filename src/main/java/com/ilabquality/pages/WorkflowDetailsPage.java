package com.ilabquality.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class WorkflowDetailsPage extends BasePage {

	public WorkflowDetailsPage(Framework fw) {
		super(fw);
	}

	public void clickViewWorkflow() throws ElementNotFoundException, Exception {
		fw.clickElement("WorkflowDetailsPage.viewWorkflowParticipants");
	}

	public String[] getApprovers() throws Exception {
		String[] approvers = new String[10];
		WebDriver driver = fw.getWebDriver();
		List<WebElement> elements = driver
				.findElements(By.xpath("//div[text()='Approvers']/parent::div/parent::div/div/div/div/div/div/div[text()]/parent::div/parent::div/descendant::span[1]"));
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
		String[] approvers = new String[10];
		WebDriver driver = fw.getWebDriver();
		List<WebElement> elements = driver
				.findElements(By.xpath("//div[text()='Approvers']/parent::div/parent::div/div/div/div/div/div/div[text()]/parent::div/parent::div/descendant::span[1]"));
		// List<WebElement> elements =
		// fw.findElementsByXpath("PleaseConfirmYourRequestPage.approvers");
		int j = 0;
		for (int i = 1; i < elements.size(); i += 2) {
			approvers[j] = elements.get(i).getText();
			j++;

		}
		return approvers;
	}

	public void closeWorkflow() throws ElementNotFoundException, Exception {
		fw.clickElement("WorkflowDetailsPage.buttonClose");
	}


}
