package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// TODO BaseTest should be placed in the root folder of page objects
// The folder structure might be like this:
// config
// pages
// tests
// utils
// helpers (if exists)
// models (if exists)
public abstract class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait() {
        if (wait == null) {
            // TODO default webdriverwait timeout should be placed in configs
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }

    // TODO constructors should go after class fields and before class methods
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(getDriver(), this);
    }
}
