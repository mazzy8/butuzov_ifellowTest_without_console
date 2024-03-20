package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class HeaderMenuPage {
    private final SelenideElement searchInput = $x("//input[@id='quickSearchInput']")
            .as("Поле ввода для поиска");
    private final SelenideElement newTaskButton = $x("//*[@id='create_link']")
            .as("Кнопка создания задачи");
    private final SelenideElement userAvatar= $x("//a[@id='header-details-user-fullname']/span/span/img")
            .as("Аватар пользователя");
    private final SelenideElement projectsButton = $x("//a[contains(text(),'Проекты')]")
            .as("Кнопка Проекты");
    private final SelenideElement projectMenu = $x("//a[contains(text(),'Просмотр всех проектов')]")
            .as("Выпадающий список кнопки проектов");
    private final SelenideElement messageFromPopUp= $x("//*[@id='aui-flag-container']/div/div")
            .as("Информационный поп-ап");

    @Step("Авторизован ли пользователь")
    public Boolean isUserSignedIn() {
        return userAvatar.shouldBe(Condition.visible).isDisplayed();
    }

    @Step("Запуск поиска с текстом \"{searchValue}\"")
    public void runSearch(String searchValue) {
        searchInput.shouldBe(Condition.visible).setValue(searchValue).pressEnter();
    }

    @Step("Нажатие кнопки новой задачи")
    public void runNewTask() {
        newTaskButton.shouldBe(Condition.visible).click();
    }

    @Step("Переход ко всем проектам")
    public void goToAllProjects() {
        projectsButton.shouldBe(Condition.visible).click();
        projectMenu.shouldBe(Condition.visible).click();
    }

    @Step("Получиение текста из информационного пап-апа")
    public String getTextFromPopUp () {
        return messageFromPopUp.shouldBe(Condition.visible).getText();
    }
}
