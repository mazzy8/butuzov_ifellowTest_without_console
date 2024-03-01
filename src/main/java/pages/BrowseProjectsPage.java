package pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class BrowseProjectsPage {

    private final SelenideElement testProjectLink = $x("//a[contains(text(),'Test')]");
    private final SelenideElement otherProjectLink = $x("//a[contains(text(),'otherProject')]");

    public void goToProject(String projectName) {
        switch (projectName) {
            case "Другой проект":
                otherProjectLink.shouldBe(Condition.enabled).click(ClickOptions.usingJavaScript());
                break;
            case "Test":
            default:
                testProjectLink.shouldBe(Condition.enabled).click(ClickOptions.usingJavaScript());
                break;
        }
        testProjectLink.shouldBe(Condition.visible).click();
    }

}
