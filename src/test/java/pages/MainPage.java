package pages;

import base.BasePage;
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

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getMainPageTitle() {
        return mainPageTitle.getText();
    }

    public void logOut() {
        settingsTriggerButton.click();
        logOutButton.click();
    }

}
