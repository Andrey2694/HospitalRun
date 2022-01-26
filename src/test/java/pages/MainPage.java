package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(className = "view-current-title")
    private WebElement mainPageTitle;

    @FindBy(className = "settings-trigger")
    private WebElement settingsTriggerButton;

    @FindBy(className = "logout")
    private WebElement logOutButton;

    @FindBy(xpath = "//a[text() = 'Medication']")
    private WebElement medicationLink;

    @FindBy(xpath = "//a[text() = 'Requests']")
    private WebElement requestsLink;

    @FindBy(xpath = "//a[text() = 'Completed']")
    private WebElement completedLink;

    @FindBy(xpath = "//a[text() = 'New Request']")
    private WebElement newRequestLink;

    @FindBy(xpath = "//a[text() = 'Return Medication']")
    private WebElement returnMedicationLink;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getMainPageTitle() {
        return mainPageTitle.getText();
    }

    @Step("LogOut from account")
    public void logOut() {
        settingsTriggerButton.click();
        logOutButton.click();
    }

    public void clickOnMedicationButton() {
        medicationLink.click();
    }

    public WebElement getCompletedLinkElement() {
        return completedLink;
    }

    public WebElement getRequestsLinkElement() {
        return requestsLink;
    }

    public WebElement getNewRequestLinkElement() {
        return newRequestLink;
    }

    public WebElement getReturnMedicationLinkElement() {
        return returnMedicationLink;
    }

}
