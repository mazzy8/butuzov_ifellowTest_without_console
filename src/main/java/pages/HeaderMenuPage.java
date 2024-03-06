package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderMenuPage {
    private final SelenideElement searchInput = $x("//input[@id='quickSearchInput']");
    private final SelenideElement newTaskButton = $x("//*[@id='create_link']");
    private final SelenideElement userAvatar= $x("//a[@id='header-details-user-fullname']/span/span/img");
    private final SelenideElement projectsButton = $x("//a[contains(text(),'Проекты')]");
    private final SelenideElement projectMenu = $x("//a[contains(text(),'Просмотр всех проектов')]");
    private final SelenideElement messageFromPopUp= $x("//*[@id='aui-flag-container']/div/div");

    public Boolean isUserSignedIn() {
        return userAvatar.shouldBe(Condition.visible).isDisplayed();
    }

    public void runSearch(String searchValue) {
        searchInput.shouldBe(Condition.visible).setValue(searchValue).pressEnter();
    }

    public void runNewTask() {
        newTaskButton.shouldBe(Condition.visible).click();
    }

    public void goToAllProjects() {
        projectsButton.shouldBe(Condition.visible).click();
        projectMenu.shouldBe(Condition.visible).click();
    }

    public String getTextFromPopUp () {
        return messageFromPopUp.shouldBe(Condition.visible).getText();
    }
}
