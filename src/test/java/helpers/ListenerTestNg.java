package helpers;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTestNg implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        new AllureAttachments().addScreenshotAs("Failed test screenshot");
    }
}
