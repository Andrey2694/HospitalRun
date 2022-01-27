package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    // TODO fix name, not clear what exactly is it - login form, login button, login field?
    // TODO (tip: you can add Fld for fields, Btn for buttons, Form for forms and so on to the end of the element's variable name)
    @FindBy(id = "identification")
    private WebElement login;

    // TODO fix name, not clear what exactly is it
    @FindBy(id = "password")
    private WebElement password;

    // TODO fix name, not clear what exactly is it
    @FindBy(className = "btn")
    private WebElement button;

    @FindBy(className = "form-signin-heading")
    private WebElement formTitle;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // TODO page objects should not contain methods that describe business logic of the app
    // page objects should contain all methods and elements that aimed for interaction with it
    // for example (for this page object):
    // properties:
    // URL
    // elements:
    // loginFormTitle
    // usernameFld
    // passwordFld
    // signInBtn
    // methods:
    // open()
    // submitLoginForWithData(String username, String password)
    @Step("login to Account")
    public void loginToAccount(String loginValue, String passwordValue) {
        // TODO in most cases, if page object describes a page, that page has URL which can be described as part of its properties (should be stored in URL field of the page object)
        // TODO the navigation to specific page object should be described as Page.open() method
        getDriver().get("http://demo.hospitalrun.io/");
        login.sendKeys(loginValue);
        password.sendKeys(passwordValue);
        button.click();
    }

    // TODO no need to create methods for so simple actions, make the elements public
    public String getTextAlertNotification() {
        // TODO we should avoid usage of selectors in the methods
        return getDriver().findElement(By.className("alert")).getText();
    }

    // TODO no need to create methods for so simple actions, make the elements public
    public String getFormTitle() {
        return formTitle.getText();
    }
}
