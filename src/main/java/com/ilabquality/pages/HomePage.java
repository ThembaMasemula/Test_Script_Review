package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class HomePage extends BasePage {

	public HomePage(Framework fw) {
		super(fw);
	}

	public void clickManagePendingRequests() throws ElementNotFoundException, Exception {
		String locator = "HomePage.btnManagePendingRequests";
		waitUntilElement(locator);
		Thread.sleep(2000);
		fw.clickElement(locator);
	}

	public void clickTimeOff() throws InterruptedException, ElementNotFoundException, Exception {
		String locator = "HomePage.btnTimeOff";
		waitUntilElement(locator);
		Thread.sleep(2000);
		fw.clickElement(locator);
	}

}
