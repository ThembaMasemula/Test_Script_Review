package com.ilabquality.pages;

import com.ilabquality.core.Framework;
import com.ilabquality.exceptions.ElementNotFoundException;

public class LoginPage extends BasePage {

	public LoginPage(Framework fw) {
		super(fw);
	}

	public void setUsername(String login) throws Exception {
		fw.sendText("LoginPage.txtUsername", login);
	}

	public void setPassword(String password) throws Exception {
		fw.sendText("LoginPage.txtPassword", password);
	}

	public void clickLogin() throws ElementNotFoundException, Exception {
		fw.clickElement("LoginPage.btnLogin");
	}

}
