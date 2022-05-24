package com.ilabquality.pages;

import org.openqa.selenium.Keys;

import com.ilabquality.core.Framework;
import com.ilabquality.dataprovider.JsonDataProvider;

public class AdminCenterPage extends BasePage {

	public AdminCenterPage(Framework fw) {
		super(fw);
	}

	public void setSearch(String searchFor) throws Exception {
		fw.timeout(1000);
		fw.clickAndSendText("AdminCenterPage.txtSerachFor", searchFor);

	}

	public void navigateToAddNewEmployee() throws Exception {
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ENTER);
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ARROW_DOWN);
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ARROW_DOWN);
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ENTER);
	}

	public void navigateToRehire() throws Exception {
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ENTER);
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ARROW_DOWN);
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ENTER);
	}

	public void navigateToEmployee() throws Exception {
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ENTER);
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ARROW_DOWN);
		fw.sendKeys("AdminCenterPage.txtSerachFor", Keys.ENTER);
	}


	public void sendKeyEnterInSearch() throws Exception {
		sendKeyEnter("AdminCenterPage.txtSerachFor");
	}


	public void sendKeyDownInSearch() throws Exception {
		sendKeyDown("AdminCenterPage.txtSerachFor");
	}

	public void clickHeaderDropdown() throws Exception {
		fw.clickElement("AdminCenterPage.btnHeaderDropdown");
	}

	public void clickHomeButton() throws Exception {
		String locator = "AdminCenterPage.btnHome";
		waitUntilElement(locator);
		fw.clickElement(locator);
	}

	public void clickOptionHeaderDropdown(String option) throws Exception {
		System.out.println("PRE DICT");
		JsonDataProvider dictionary = setDictionary();
		System.out.println("PRE SET");
		dictionary.setJsonArguments("web.AdminCenterPage.optHeaderDropdown.value", new String[] { option });
		System.out.println("POS SET");
		fw.clickElement("AdminCenterPage.optHeaderDropdown");
		System.out.println("TENTOU CLICK");
	}
}
