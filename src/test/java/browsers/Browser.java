package browsers;

import config.Project;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HospitalRun;

import java.time.Duration;

public class Browser {
    private WebDriver driver;
    private HospitalRun hospitalRun;

    public HospitalRun hospitalRun() {
        if (this.hospitalRun == null) {
            this.hospitalRun = new HospitalRun(this.driver);
        }
        return this.hospitalRun;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void open(String browser) {
        if (browser.equals("chrome") || Project.config.browser().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox") || Project.config.browser().equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().setSize(new Dimension(Project.config.browserWidth(), Project.config.browserHeight()));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Project.config.pageLoadTimeOut()));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Project.config.implicitlyWait()));
    }

    public void deleteCookies() {
        driver.manage().deleteAllCookies();
    }
}