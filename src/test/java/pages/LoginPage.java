package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "identification")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "btn")
    private WebElement button;

    @FindBy(className = "form-signin-heading")
    private WebElement formTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("login to Account")
    public void loginToAccount(String loginValue, String passwordValue) {
        getDriver().get("http://demo.hospitalrun.io/");
        login.sendKeys(loginValue);
        password.sendKeys(passwordValue);
        button.click();
    }

    public String getTextAlertNotification() {
        return getDriver().findElement(By.className("alert")).getText();
    }

    public String getFormTitle() {
        return formTitle.getText();
    }
}
