package helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import tests.BaseTest;

import java.nio.charset.StandardCharsets;

public class AllureAttachments extends BaseTest {
    @Attachment(value = "{attachName}", type = "image/png")
    public byte[] addScreenshotAs(String attachName) {
        return ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public byte[] addPageSource() {
        return browser.getDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }
}
