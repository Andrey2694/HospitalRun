package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    // TODO make all elements public
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

    // TODO no need in such simple methods, just make the field public and perform that action in the test directly
    public String getMainPageTitle() {
        return mainPageTitle.getText();
    }

    // TODO we can do logout not only on the main page, this feature is bound to navigation component which is accessible on all pages
    @Step("LogOut from account")
    public void logOut() {
        settingsTriggerButton.click();
        logOutButton.click();
    }

    // TODO no need in such simple methods, just make the field public and perform that action in the test directly
    public void clickOnMedicationButton() {
        medicationLink.click();
    }

    // TODO no need in such simple methods, just make the field public and access the field directly in the test
    public WebElement getCompletedLinkElement() {
        return completedLink;
    }

    // TODO no need in such simple methods, just make the field public and access the field directly in the test
    public WebElement getRequestsLinkElement() {
        return requestsLink;
    }

    // TODO no need in such simple methods, just make the field public and access the field directly in the test
    public WebElement getNewRequestLinkElement() {
        return newRequestLink;
    }

    // TODO no need in such simple methods, just make the field public and access the field directly in the test
    public WebElement getReturnMedicationLinkElement() {
        return returnMedicationLink;
    }

}
