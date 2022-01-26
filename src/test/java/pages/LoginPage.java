package pages;

import base.BasePage;
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

    public LoginPage(WebDriver driver) {
        super(driver);
        getDriver().get("http://demo.hospitalrun.io/");
    }

    public void loginToAccount(String loginValue, String passwordValue) {
        login.sendKeys(loginValue);
        password.sendKeys(passwordValue);
        button.click();
    }

    public String getTextAlertNotification() {
        return getDriver().findElement(By.className("alert")).getText();
    }
}
