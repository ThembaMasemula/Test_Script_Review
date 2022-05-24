package com.ilabquality.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ilabquality.core.Framework;
import com.ilabquality.dataprovider.JsonDataProvider;
import com.ilabquality.exceptions.ElementNotFoundException;
import com.ilabquality.utilities.PropertiesManager;

public class BasePage {

	Framework fw = null;
	Robot robot;
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
	Calendar calendar = Calendar.getInstance();

	public static JsonDataProvider dictionary;

	public BasePage(Framework fw) {
		this.fw = fw;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAllActionList(String locator) throws Exception {
		while(fw.getWebDriver().findElements(By.xpath(locator)).get(0).getText().contains("Loading")) {
			sendALotOfArrowDown(20);
			fw.timeout(3000);
		}
		return fw.getWebDriver().findElements(By.xpath(locator)).get(0).getText();
	}

	public String[] getArrayFromJsonString(String jsonString) {
		return jsonString.substring(1, jsonString.length() - 1).replaceAll("\"", "").split(",");
	}

	public void navigateTo(String url) throws Exception {
		fw.navigateTo(url);
	}

	public void sendKeyEnter(String locator) throws Exception {
		fw.sendKeys(locator, Keys.ENTER);
	}

	public void sendKeyDown(String locator) throws Exception {
		fw.sendKeys(locator, Keys.ARROW_DOWN);
	}

	public void sendKeyEsc(String locator) throws Exception {
		fw.sendKeys(locator, Keys.ESCAPE);
	}

	public void sendKeyEsc() throws Exception {
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	public void sendALotOfArrowDown(int quantity) throws Exception {
		for (int i = 0; i < quantity; i++) {
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
		}

	}

	public void waitUntilElement(String locator) throws InterruptedException, ElementNotFoundException, Exception {
		// wait element present
		int i = 0;
		while (!fw.isElementPresent(locator, 1)) {
			if (i < 10) {
				i++;
				fw.timeout(1000);
				continue;
			} else {
				break;
			}
		}
	}

	public void waitUntilLoadingDisappear() throws InterruptedException, ElementNotFoundException, Exception {
		waitUntilLoadingDisappear(5);

	}

	public void waitUntilLoadingDisappear(int time) throws InterruptedException, ElementNotFoundException, Exception {
		int i = 0;
		// fw.waitForLoadPage();
		fw.timeout(1000);
		try {
			while ("visible".equals(fw.getCssValue("BasePage.loading", "visibility", time))) {
				if (i < 10) {
					i++;
					fw.timeout(2000);
					continue;
				} else {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Loading not found!");
		}

	}

	public void waitUntilElementDisappear(String element)
			throws InterruptedException, ElementNotFoundException, Exception {
		int i = 0;
		// fw.waitForLoadPage();
		// fw.timeout(500);
		while (fw.isElementPresent(element, 1)) {
			if (i < 10) {
				i++;
				fw.timeout(1000);
				continue;
			} else {
				break;
			}
		}

	}

	public void waitChangesSavedSuccessfully() throws ElementNotFoundException, Exception {
		int i = 0;
		while (!fw.isTextPresent("Your changes were successfully saved.")) {
			if (i < 10) {
				i++;
				continue;
			} else {
				break;
			}
		}
	}

	public void waitChangesSavedSuccessfullyDisappear() throws ElementNotFoundException, Exception {
		int i = 0;
		while (fw.isTextPresent("Your changes were successfully saved.")) {
			if (i < 10) {
				i++;
				fw.timeout(500);
				continue;
			} else {
				break;
			}
		}
	}

	public JsonDataProvider setDictionary() {
		if (PropertiesManager.project != null) {
			dictionary = new JsonDataProvider(
					System.getProperty("user.dir") + "/resources/" + PropertiesManager.project + "/dictionary.json");
		} else {
			dictionary = new JsonDataProvider(System.getProperty("user.dir") + "/resources/dictionary.json");
		}

		return dictionary;
	}

	/**
	 * Just for demo system
	 */

	public void approveFirst() throws Exception, ElementNotFoundException, InterruptedException {
		WebDriver driver;
		driver = fw.getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);

		try {
			Thread.sleep(7000);
			//fw.timeout(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Request')]"))).click();

		} catch (Exception e) {
			try {
				fw.clickElement(By.xpath("//div[contains(text(),'Request')]"));
			} catch (Exception e2) {

			}

		}

		 //fw.waitForLoadPage();
		// fw.timeout(4000);
		// fw.clickElement(By.xpath("//*[@id='__box3-__xmlview1--todosTable-0-CbBg']"));
		// fw.timeout(6000);
		wait = new WebDriverWait(driver, 20);
		Thread.sleep(7000);
		fw.timeout(2500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/descendant::bdi[text()='Approve']"))).click();
		// driver.findElement(By.xpath("//tr[1]/td/div/div/button")).click();
		// fw.clickElement(By.xpath("//table/tbody/tr[1]/td/div/div/button/span/span/bdi"));
		fw.timeout(2000);
		if(fw.isElementPresent("ApproveRequestsPage.btnClose", 1)) {
			fw.clickElement("ApproveRequestsPage.btnClose");
		}

	}


	public void approveRequest() throws Exception, ElementNotFoundException, InterruptedException {
		WebDriver driver;
		driver = fw.getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);

		try {
			Thread.sleep(7000);
			//fw.timeout(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Request')]"))).click();

		} catch (Exception e) {
			try {
				fw.clickElement(By.xpath("//div[contains(text(),'Request')]"));
			} catch (Exception e2) {

			}

		}

		 //fw.waitForLoadPage();
		// fw.timeout(4000);
		// fw.clickElement(By.xpath("//*[@id='__box3-__xmlview1--todosTable-0-CbBg']"));
		// fw.timeout(6000);
		wait = new WebDriverWait(driver, 20);
		Thread.sleep(7000);
		fw.timeout(2500);
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__button4-__xmlview1--todosTable-0-BDI-content\"]"))).click();
		// driver.findElement(By.xpath("//tr[1]/td/div/div/button")).click();
		// fw.clickElement(By.xpath("//table/tbody/tr[1]/td/div/div/button/span/span/bdi"));
		fw.timeout(2000);
		if(fw.isElementPresent("ApproveRequestsPage.btnClose", 1)) {
			fw.clickElement("ApproveRequestsPage.btnClose");
		}

	}

	public void proxyNow(String id) throws Exception, ElementNotFoundException, InterruptedException {
		WebDriver driver;
		driver = fw.getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, 600);
		fw.timeout(4000);
		fw.waitForLoadPage();
		String url = driver.getCurrentUrl();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("utilityLinksMenuId-inner"))).click();
		fw.timeout(500);
		fw.clickElement(By.xpath("//a[text()='Proxy Now']"));

		driver.findElement(
				By.xpath("//label[@aria-label='Please enter target user name:']/following::div/div/input"))
				.sendKeys(id);
		fw.timeout(3000);
		try {
			fw.clickElement(By.xpath("//span[contains(text(),'(" + id + ")')]"));
		} catch (Exception e) {
			try {
				fw.clickElement(By.xpath("//div[contains(text(),'" + id + "')]"));
			} catch (Exception e2) {
				fw.clickElement(By.xpath("//em[contains(text(),'" + id + "')]"));
			}
		} finally {
			fw.timeout(500);
			fw.clickElement(By.xpath("//bdi[text()='OK']"));
			// adminCenterPage.waitUntilLoadingDisappear();
			fw.timeout(8000);
			int i = 0;
			while (url.equals(driver.getCurrentUrl()) && i < 3) {
				i++;
				fw.timeout(2000);
			}
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='customHeaderModulePickerBtn']")));
			// fw.timeout(8000);
		}

	}

	public void proxyNowName(String employeeName) throws Exception, ElementNotFoundException, InterruptedException {
		WebDriver driver;
		driver = fw.getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, 600);
		fw.timeout(4000);
		fw.waitForLoadPage();
		String url = driver.getCurrentUrl();
		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("utilityLinksMenuId-inner"))).click();
		fw.timeout(500);
		fw.clickElement(By.xpath("//a[text()='Proxy Now']"));

		driver.findElement(
				By.xpath("//span[text()='Select Target User']/parent::h2/parent::div/parent::div/parent::div/parent::header/parent::div/descendant::input"))
				.sendKeys(employeeName);
		fw.timeout(3000);
		try {
			fw.clickElement(By.xpath("//div[contains(text(),'" + employeeName + "')]"));
		} catch (Exception e) {
			fw.clickElement(By.xpath("//em[contains(text(),'" + employeeName + "')]"));
		}
		fw.timeout(500);
		fw.clickElement(By.xpath("//bdi[text()='OK']"));
		// adminCenterPage.waitUntilLoadingDisappear();
		int i = 0;
		while (url.equals(driver.getCurrentUrl()) && i < 10) {
			fw.timeout(500);
			i++;
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='customHeaderModulePickerBtn']")));
		fw.timeout(2000);
	}

	public void proxyBecomeSelf() throws Exception, ElementNotFoundException, InterruptedException {
		WebDriver driver;
		driver = fw.getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, 600);
		fw.timeout(4000);
		fw.waitForLoadPage();
		String url = driver.getCurrentUrl();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("utilityLinksMenuId-inner"))).click();
		fw.timeout(500);
		fw.clickElement(By.xpath("//a[text()='Become Self']"));
		fw.timeout(8000);
		while (url.equals(driver.getCurrentUrl())) {
			fw.timeout(2000);
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='customHeaderModulePickerBtn']")));
		fw.timeout(2000);
	}

	public boolean alertChangeSuccefulySaved() throws ElementNotFoundException, Exception {
		String locator = "ChangeJobAndCompensationInfoPage.alertChangeSuccefulySaved";
		String textSuccess = "Your changes were successfully saved";
		int i = 0;

		while (!fw.isElementPresent(locator, 1)) {
			if (i < 10) {
				fw.timeout(1000);
				i++;
				continue;
			} else {
				break;
			}
		}
		try {
			return fw.isTextPresent(textSuccess);
		} catch (Exception e) {
			System.out.println("Alert not found!");
			throw e;
		}

	}

	public String[] treatedVariable(String variable) {

		variable.substring(1, variable.length() - 1).trim();
		return variable.split(",");
	}

	public void verifyEventReasonList(String list, String listOfAction) throws Exception {
		String[] eventList = list.split("\n");
		String[] listOfActions = listOfAction.substring(1, listOfAction.length() - 1).split(",");
		Arrays.sort(eventList);
		List<String> failures = new ArrayList<String>();
		Boolean isEqual = false;

		for (int i = 0; i < listOfActions.length; i++) {
			isEqual = false;
			for (int j = 0; j < eventList.length; j++) {
				if (listOfActions[i].equals(eventList[j])) {
					isEqual = true;
					j = eventList.length;
				}
			}
			if (!isEqual) {
				failures.add(listOfActions[i]);
			}
		}
		System.out.println(failures);

		// Arrayverso
		List<String> failuresReverse = new ArrayList<String>();
		isEqual = false;
		for (int j = 0; j < eventList.length; j++) {
			isEqual = false;
			for (int i = 0; i < listOfActions.length; i++) {
				if (listOfActions[i].equals(eventList[j])) {
					isEqual = true;
					i = eventList.length;
				}
			}
			if (!isEqual) {
				failuresReverse.add(listOfActions[j]);
			}
		}

		System.out.println(failuresReverse);

		if (failures.isEmpty() && failuresReverse.isEmpty()) {
			if (fw.getWebDriver() instanceof JavascriptExecutor) {
				((JavascriptExecutor) fw.getWebDriver()).executeScript("alert(arguments[0] +  ' passed');",
						"Action list");
			}
		} else {

			if (fw.getWebDriver() instanceof JavascriptExecutor) {
				((JavascriptExecutor) fw.getWebDriver()).executeScript("alert('Action list failed \\nNot found: \\n ' + arguments[0] + '\\nWrong items: \\n ' + arguments[1]);", failures, failuresReverse);
			}
		}

		Thread.sleep(3000);

		// TODO: put in fw pattern
		//BufferedImage image = new Robot()
		//		.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		//ImageIO.write(image, "png", new File(System.getProperty("user.dir") + "\\borraaaaaas.png"));
		fw.getWindowScreenshot();

		fw.getWebDriver().switchTo().alert().accept();

		fw.assertTrue(failures.isEmpty() && failuresReverse.isEmpty());
	}

	public void verifyEventReasonList(String[] eventList, String[] listOfActions) throws Exception {
		Arrays.sort(eventList);
		List<String> failures = new ArrayList<String>();
		Boolean isEqual = false;

		for (int i = 0; i < listOfActions.length; i++) {
			isEqual = false;
			for (int j = 0; j < eventList.length; j++) {
				if (listOfActions[i].equals(eventList[j])) {
					isEqual = true;
					j = eventList.length;
				}
			}
			if (!isEqual) {
				failures.add(listOfActions[i]);
			}
		}
		System.out.println(failures);

		// Arrayverso
		List<String> failuresReverse = new ArrayList<String>();
		isEqual = false;
		for (int j = 0; j < eventList.length; j++) {
			isEqual = false;
			for (int i = 0; i < listOfActions.length; i++) {
				if (listOfActions[i].equals(eventList[j])) {
					isEqual = true;
					i = eventList.length;
				}
			}
			if (!isEqual) {
				failuresReverse.add(listOfActions[j]);
			}
		}

		System.out.println(failuresReverse);

		if (failures.isEmpty() && failuresReverse.isEmpty()) {
			if (fw.getWebDriver() instanceof JavascriptExecutor) {
				((JavascriptExecutor) fw.getWebDriver()).executeScript("alert(arguments[0] +  ' passed');",
						"Action List");
			}
		} else {

			if (fw.getWebDriver() instanceof JavascriptExecutor) {
				((JavascriptExecutor) fw.getWebDriver()).executeScript("alert('Action List failed \\nNot found: \\n ' + arguments[0] + '\\nWrong items: \\n ' + arguments[1]);", failures, failuresReverse);
			}
		}

		Thread.sleep(3000);

		fw.getWindowScreenshot();

		fw.getWebDriver().switchTo().alert().accept();

		fw.assertTrue(failures.isEmpty() && failuresReverse.isEmpty());
	}

	public String getAllListEventReason() throws Exception {
		String locator = "//header/parent::div/child::div//ul//li/parent::ul[@role='listbox']";
		while (fw.getWebDriver().findElements(By.xpath(locator)).get(0).getText().contains("Loading")) {
			sendALotOfArrowDown(20);
			fw.timeout(3000);
		}
		return fw.getWebDriver().findElements(By.xpath(locator)).get(0).getText();
	}
}
