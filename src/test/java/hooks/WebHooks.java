package hooks;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInfo;


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
    public void setup(TestInfo testInfo) {
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
        Configuration.baseUrl = "https://edujira.ifellow.ru/";
        Selenide.open("/");
        System.out.println("Тест: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
