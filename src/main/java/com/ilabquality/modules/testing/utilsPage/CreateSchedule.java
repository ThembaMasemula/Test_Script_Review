package com.ilabquality.modules.testing.utilsPage;

import com.ilabquality.modules.testing.utilsWeb.WebActions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateSchedule extends WebActions {
    protected WebDriver driver;

    public CreateSchedule(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//bdi[text()[contains(.,'Actions')]])[1]")
    public WebElement btnAction;

    @FindBy(xpath = " (//*[contains(text(),'Schedule')] )[1]")
    public WebElement btnSchedule;

    @FindBy(xpath = "//iframe[contains(@src,'/learning/admin/training/createSOFromItem.do')]")
    public WebElement iframeSchedule;

    @FindBy(xpath = "(//textarea[@id='description'])[1]")
    public WebElement textDescription;

    @FindBy(xpath = "(//input[@id='startDate'])[1]")
    public WebElement textStartDate;

    @FindBy(xpath = "(//input[@id='startTime'])[1]")
    public WebElement textStartTime;

    @FindBy(xpath = "(//select[@id='timeZone'])[1]")
    public WebElement textTimeZone;

    @FindBy(xpath = "(//select[@id='facility_id'])[1]")
    public WebElement textFacility;

    @FindBy(xpath = "(//select[@id='locationID'])[1]")
    public WebElement textPrimaryLocation;

    @FindBy(xpath = "(//input[@id='instructorID'])[1]")
    public WebElement textPrimaryInstructor;

    @FindBy(xpath = "//*[@id=\'submitButton\']")
    public WebElement btnSave;

    @FindBy(xpath = "(//input[@id='submitbutton'])[1]")
    public WebElement btnYes;

    @FindBy(xpath = "//input[@id='scheduledOffering.contact-inner']")
    public WebElement textContactName;

    @FindBy(xpath = "(//input[@id='scheduledOffering.emailAddress-inner'])[1]")
    public WebElement textContactEmail;

    @FindBy(xpath = "//input[@id='customColumn_20_select-hiddenInput']")
    public WebElement textTraningCompanyName;

    @FindBy(xpath = "//*[name()='circle' and @id='scheduledOffering.email.confirmation.user.regEmailStudent.Yes-Button']")
    public WebElement rdEmailconfirmationUser;

    @FindBy(xpath = "//*[name()='circle' and @id='scheduledOffering.email.confirmation.user.regEmailSupervisor.Yes-Button']")
    public WebElement rdEmailconfirmationManager;

    @FindBy(xpath = "//*[name()='circle' and @id='scheduledOffering.email.confirmation.user.regEmailInstructor.Yes-Button']")
    public WebElement rdEmailconfirmationInstructor;

    @FindBy(xpath = "//*[name()='circle' and @id='scheduledOffering.email.confirmation.user.regEmailContacts.Yes-Button']")
    public WebElement rdEmailconfirmationContacts;

    @FindBy(xpath = "//*[name()='circle' and @id='scheduledOffering.selfEnrollment.Yes-Button']")
    public WebElement rdSelfEnrolment;

    @FindBy(xpath = "//div[@id='scheduledOffering.approvalRequired.Yes']//div[@class='sapMRbB sapMRbHoverable']//*[name()='svg']//*[name()='circle' and contains(@class,'sapMRbBInn')]")
    public WebElement rdAprovalRequired;

    @FindBy(xpath = "//div[@id='scheduledOffering.withdrawApprovalRequired.Yes']//div[@class='sapMRbB sapMRbHoverable']//*[name()='svg']//*[name()='circle' and contains(@class,'sapMRbBInn')]")
    public WebElement rdAprovalRequiredToWithdrow;

    @FindBy(xpath = "//bdi[@id='scheduledOffering.footer.save-BDI-content']")
    public WebElement btnSaveDetails;

    @FindBy(xpath = "(//span[@id='__title6-__block12-Collapsed--idEntityManagerForm-9-inner'])[1]")
    public WebElement lblComplition;
}
