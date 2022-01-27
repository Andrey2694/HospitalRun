package base;

import config.Project;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

// TODO BaseTest should be placed in the root folder of tests (tests)
// The folder structure might be like this:
// config
// pages
// tests
// utils
// helpers (if exists)
// models (if exists)
public abstract class BaseTest {
    private WebDriver driver;

    protected WebDriver getDriver() {
        return driver;
    }

    // TODO for time execution optimization we need to try to do as small amount of browser restarts as possible
    // TODO try to setup the tests so there is no browser restart between them
    @BeforeMethod
    public void setUp() {
        if (Project.config.browser().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (Project.config.browser().equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        // TODO browser window size should be taken from configs
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().deleteAllCookies();
        // TODO default timeouts should be taken from configs
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @AfterMethod
    public void setDown() {
        driver.quit();
    }
}
