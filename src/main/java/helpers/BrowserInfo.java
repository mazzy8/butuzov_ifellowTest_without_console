package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserInfo {

    public static String getBrowserInfo() {
        try {
            RemoteWebDriver driver = (RemoteWebDriver) WebDriverRunner.getWebDriver();
            Capabilities capabilities = driver.getCapabilities();
            String browserName = capabilities.getBrowserName();
            String browserVersion = capabilities.getBrowserVersion();
            return browserName + " " + browserVersion;
        } catch (IllegalStateException e) {
            return "Browser information is not available";
        }
    }
}