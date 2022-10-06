package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {
    @Override
    public WebDriver get() {
        return new ChromeDriver();
    }
}
