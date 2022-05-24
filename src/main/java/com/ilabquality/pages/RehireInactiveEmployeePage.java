package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class RehireInactiveEmployeePage extends BasePage{

	public RehireInactiveEmployeePage(Framework fw) {
		super(fw);
	}

	public void clickRehireEmployee() throws Exception {
		waitUntilElement("RehireInactiveEmployeePage.firstNameInGrid");
		fw.mouseOver("RehireInactiveEmployeePage.firstNameInGrid");
		fw.clickElement("RehireInactiveEmployeePage.buttonRehireEmployee");
	}

	public void clickRehireWithNewEmployment() throws Exception {
		waitUntilElement("RehireInactiveEmployeePage.firstNameInGrid");
		fw.mouseOver("RehireInactiveEmployeePage.firstNameInGrid");
		fw.clickElement("RehireInactiveEmployeePage.buttonRehireWithNewEmployment");
	}

	public void sendEmployeeName(String employee) throws Exception {
		waitUntilElement("RehireInactiveEmployeePage.searchByInput");
		fw.sendText("RehireInactiveEmployeePage.searchByInput", employee);
	}

	public void clickGo() throws ElementNotFoundException, Exception {
		fw.clickElement("RehireInactiveEmployeePage.buttonGo");
	}



}
