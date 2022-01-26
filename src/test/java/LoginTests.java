import config.Project;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import pages.MainPage;

import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests extends BaseTest {
    private final String username = Project.config.username();
    private final String password = Project.config.password();

    @Test
    public void loginWithCorrectCredentials() {
        new LoginPage(getDriver()).loginToAccount(username, password);
//        assertThat(new MainPage(getDriver()).getMainPageTitle()).isEqualTo("Patient Listing");
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
        MainPage mainPage = new MainPage(getDriver());
        mainPage.logOut();
        int i = 0;
    }
}
