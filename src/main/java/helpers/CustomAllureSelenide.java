package helpers;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.LogEvent;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import java.util.Optional;

public class CustomAllureSelenide extends AllureSelenide {

    private final AllureLifecycle lifecycle;
    private Optional<byte[]> screenshot;

    public CustomAllureSelenide(final AllureLifecycle lifecycle) {
        super(lifecycle);
        this.lifecycle = lifecycle;
    }

    @Override
    public void afterEvent(final LogEvent event) {
        if (event.getStatus().equals(LogEvent.EventStatus.FAIL)) {
            screenshot.ifPresent(bytes -> lifecycle.addAttachment("Перед ошибкой", "image/png", "png", bytes));
        } else {
            screenshot = getScreenshotBytes();
        }
        super.afterEvent(event);
    }

    private static Optional<byte[]> getScreenshotBytes() {
        try {
            return WebDriverRunner.hasWebDriverStarted()
                    ? Optional.of(((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES))
                    : Optional.empty();
        } catch (WebDriverException e) {
            return Optional.empty();
        }
    }
}