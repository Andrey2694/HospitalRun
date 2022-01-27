import config.Project;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import pages.MainPage;
import pages.MedicationEditPage;
import pages.MedicationPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class HospitalTests extends BaseTest {
    private final String username = Project.config.username();
    private final String password = Project.config.password();

    @Test
    public void loginWithCorrectCredentials() {
        new LoginPage(getDriver()).loginToAccount(username, password);

        step("Assert that User is logged in and Patient Listing page is displayed", () -> {
            assertThat(new MainPage(getDriver()).getMainPageTitle()).isEqualTo("Patient Listing");
        });
    }

    @Test
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
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

        medicationEditPage.fillPatientField();
        medicationEditPage.fillPrescriptionField();
        medicationEditPage.selectPrescriptionData();
        medicationEditPage.fillQuantityField();
        medicationEditPage.fillRefillsField();
        medicationEditPage.fillMedicationField();
        medicationEditPage.selectVisitData();
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
