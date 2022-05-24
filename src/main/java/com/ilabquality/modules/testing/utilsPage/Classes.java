package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class Classes extends WebActions {
    protected WebDriver driver;

    public Classes(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id=\'ScheduleID\']")
    public WebElement txtClassID;

    @FindBy(xpath = "//*[@id=\'search\']")
    public WebElement btnSearch;

    @FindBy(xpath = "//iframe[contains(@src,'/learning/search/initSearch.do?searchType=0&selectorName=ScheduleInstance&stackID=search&entityManagerEnabled')]")
    public WebElement iframeClass;


    @FindBy(xpath = "//*[@id=\'getPathBuffer\']")
    public WebElement iframeSearchResults;


    @FindBy(xpath = "//*[@id=\'entityManagerID\']")
    public WebElement lnkClass;
}
