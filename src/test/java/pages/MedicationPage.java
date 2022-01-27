package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedicationPage extends BasePage {
    // todo make it public
    @FindBy(xpath = "//button[text() = '+ new request']")
    private WebElement newRequestButton;

    public MedicationPage(WebDriver driver) {
        super(driver);
    }

    // todo no need in such simple method
    @Step("Click on NewRequest button")
    public void clickOnNewRequestButton() {
        newRequestButton.click();
    }
}
