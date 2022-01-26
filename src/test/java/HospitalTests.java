import config.Project;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import pages.MainPage;
import pages.MedicationEditPage;
import pages.MedicationPage;

import static org.assertj.core.api.Assertions.assertThat;

public class HospitalTests extends BaseTest {
    private final String username = Project.config.username();
    private final String password = Project.config.password();

    @Test
    public void loginWithCorrectCredentials() {
        new LoginPage(getDriver()).loginToAccount(username, password);

        assertThat(new MainPage(getDriver()).getMainPageTitle()).isEqualTo("Patient Listing");
    }

    @Test
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginToAccount("hello", "world");

        assertThat(getDriver().getCurrentUrl()).isEqualTo("http://demo.hospitalrun.io/#/login");
        assertThat(loginPage.getTextAlertNotification()).isEqualTo("Username or password is incorrect.");
    }

    @Test
    public void logOutFromAccount() {
        loginWithCorrectCredentials();
        new MainPage(getDriver()).logOut();

        assertThat(new LoginPage(getDriver()).getFormTitle()).isEqualTo("PLEASE SIGN IN");
        assertThat(getDriver().getCurrentUrl()).isEqualTo("http://demo.hospitalrun.io/#/login");
    }

    @Test
    public void addNewMedication() {
        loginWithCorrectCredentials();
        MainPage mainPage = new MainPage(getDriver());
        mainPage.clickOnMedicationButton();

        assertThat(mainPage.getRequestsLinkElement().isDisplayed()).isTrue();
        assertThat(mainPage.getCompletedLinkElement().isDisplayed()).isTrue();
        assertThat(mainPage.getNewRequestLinkElement().isDisplayed()).isTrue();
        assertThat(mainPage.getReturnMedicationLinkElement().isDisplayed()).isTrue();

        new MedicationPage(getDriver()).clickOnNewRequestButton();
        MedicationEditPage medicationEditPage = new MedicationEditPage(getDriver());
//        medicationEditPage.fillPatientField();
        medicationEditPage.fillPrescriptionField();
        medicationEditPage.selectPrescriptionData();

        medicationEditPage.fillQuantityField();
        medicationEditPage.fillRefillsField();

        medicationEditPage.fillMedicationField();

        int i = 0;
    }

    @Test
    public void tst() {

    }
}
