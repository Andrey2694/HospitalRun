package tests;

import browsers.Browser;
import helpers.ListenerTestNg;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(ListenerTestNg.class)
public abstract class BaseTest {
    protected Browser browser = new Browser();

    @BeforeSuite
    public void openBrowser() {
        browser.open("chrome");
    }

    @AfterSuite
    public void setDown() {
        browser.getDriver().quit();
    }

//    @AfterMethod
//    public void addAttachments() {
//        AllureAttachments.addScreenshotAs("Last screenshot", browser.getDriver());
//        AllureAttachments.addPageSource(browser.getDriver());
//    }
}
