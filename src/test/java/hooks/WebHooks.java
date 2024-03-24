package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.CustomAllureSelenide;
import io.qameta.allure.Allure;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import utils.ConfigProvider;
import helpers.AllureEnvironmentWriter;

public class WebHooks {

    public static void setupChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen"); // macOS
        options.addArguments("--start-maximized"); // Windows
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }

    public static void setupFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        Configuration.browserCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
    }

    @BeforeEach
    public void setup() {
        String browser = System.getProperty("browser", "chrome");
        Configuration.browser = browser;
        switch (browser) {
            case "firefox":
                setupFirefox();
                break;
            case "chrome":
            default:
                setupChrome();
                break;
        }
        Configuration.baseUrl = ConfigProvider.JIRAURL;
        Selenide.open("/");
        AllureEnvironmentWriter.writeEnvironmentProperties();
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide(Allure.getLifecycle()));
    }

    @AfterEach
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

}
