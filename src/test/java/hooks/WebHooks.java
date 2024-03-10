package hooks;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class WebHooks {

    public static void setupChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen"); // macOS
        options.addArguments("--start-maximized"); // Windows
        options.addArguments("--disable-notifications"); // Windows
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
    }

    public static void setupFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        Configuration.browserCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
    }

    @Before
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
        Configuration.baseUrl = "https://edujira.ifellow.ru/";
        Selenide.open("/");
    }

    @After
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }
}
