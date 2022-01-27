import config.Project;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import pages.MainPage;
import pages.MedicationEditPage;
import pages.MedicationPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

// todo should be placed in the tests folder
public class HospitalTests extends BaseTest {
    // todo no need to create separate variables for it, just access directly
    private final String username = Project.config.username();
    private final String password = Project.config.password();

    @Test
    public void loginWithCorrectCredentials() {
        // todo this is not a good practice to access webdriver directly in the tests
        // for page object it is better to have one class that contains all of them and access them with help of an instance of that class
        // that class is Browser
        // here is how your test might look in case of using that strategy:
        // browser.loginPage().open();
        // browser.loginPage().submitLoginFormWithData(Project.config.username(), Project.config.password());
        // browser.mainPage().waitForPresent();
        new LoginPage(getDriver()).loginToAccount(username, password);

        step("Assert that User is logged in and Patient Listing page is displayed", () -> {
            assertThat(new MainPage(getDriver()).getMainPageTitle()).isEqualTo("Patient Listing");
        });
    }

    @Test
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        // todo we are not actually doing a login, but submitting the login form is invalid data, that's why loginToAccount is not a good name for this method
        // using instance of browser class, the test is going to look like this:
        // browser.loginPage().open();
        // browser.loginPage().submitLoginFormWithData("hello", "world");
        // browser.loginPage().waitForUsernameOrPasswordIsIncorrectErrorToBePresent();
        // browser.sleep(5000);
        // assertThat(browser.mainPage().isPresent()).isFalse();
        // assertThat(browser.loginPage().isPresent()).isTrue();

        loginPage.loginToAccount("hello", "world");
        step("Assert that User is stayed on Login Page and Error message is displayed", () -> {
            assertThat(getDriver().getCurrentUrl()).isEqualTo("http://demo.hospitalrun.io/#/login");
            assertThat(loginPage.getTextAlertNotification()).isEqualTo("Username or password is incorrect.");
        });
    }

    @Test
    public void logOutFromAccount() {
        loginWithCorrectCredentials();
        new MainPage(getDriver()).logOut();

        step("Assert that User is logged out and Login Page is displayed", () -> {
            assertThat(new LoginPage(getDriver()).getFormTitle()).isEqualTo("PLEASE SIGN IN");
            assertThat(getDriver().getCurrentUrl()).isEqualTo("http://demo.hospitalrun.io/#/login");
        });
    }

    @Test
    public void addNewMedication() {
        loginWithCorrectCredentials();
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickOnMedicationButton();

        step("Assert that Medication Section contains next 4 items:" +
                " Requests, Completed, New Request, Return Medication", () -> {
            assertThat(mainPage.getRequestsLinkElement().isDisplayed()).isTrue();
            assertThat(mainPage.getCompletedLinkElement().isDisplayed()).isTrue();
            assertThat(mainPage.getNewRequestLinkElement().isDisplayed()).isTrue();
            assertThat(mainPage.getReturnMedicationLinkElement().isDisplayed()).isTrue();
        });

        new MedicationPage(getDriver()).clickOnNewRequestButton();
        MedicationEditPage medicationEditPage = new MedicationEditPage(getDriver());

        // todo what to fill?
        medicationEditPage.fillPatientField();
        // todo what to fill?
        medicationEditPage.fillPrescriptionField();
        // todo what to select?
        medicationEditPage.selectPrescriptionData();
        // todo what to fill?
        medicationEditPage.fillQuantityField();
        // todo what to fill?
        medicationEditPage.fillRefillsField();
        // todo what to fill?
        medicationEditPage.fillMedicationField();
        medicationEditPage.selectVisitData(); // todo which one?
        medicationEditPage.clickOnAddRequestButton();

        step("Assert that Medication Request Saved popup is displayed and" +
                "contains next items: Ok button and Cross button", () -> {
            assertThat(medicationEditPage.getModalTitle()).isEqualTo("Medication Request Saved");
            assertThat(medicationEditPage.getCloseModalButton().isDisplayed()).isTrue();
        });

        medicationEditPage.clickOnSubmitModalButton();

        step("Assert that Medication Request Saved pop up isn`t displayed and" +
                "User stayed on New Medication Request Page", () -> {
            assertThat(medicationEditPage.isModalDisplayed()).isTrue();
        });
    }
}
