package pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class FilterPage {
    private final SelenideElement taskTypeArea = $x("//div/div/div/ul/li[2]/div")
            .as("Поле ввода для поиска");
    private final SelenideElement taskTypeCheck = $x("//*[@id='10100-1']/label")
            .as("Чек-бокс Задача");
    private final SelenideElement bugTypeCheck = $x("//li[@id='10102-6']/label")
            .as("Чек-бокс Баг");
    private final SelenideElement applyFilterButton = $x("(//button[@type='button'])[3]")
            .as("Кнопка Поиск");
    private final SelenideElement openTaskLink = $x("//*[@id='key-val']")
            .as("чек-бокс Задача");

    @Step("Выбор типа задачи(\"{taskType}\") на странице параметров фильтра")
    public void applyFilter(String taskType) {
        taskTypeArea.shouldBe(Condition.visible).click();
        switch (taskType) {
            case "Bug":
                bugTypeCheck.shouldBe(Condition.enabled).click(ClickOptions.usingJavaScript());
                break;
            case "Task":
            default:
                taskTypeCheck.shouldBe(Condition.enabled).click(ClickOptions.usingJavaScript());
                break;
        }
        applyFilterButton.shouldBe(Condition.visible).click();
    }

    @Step("Открытие отфильтрованной задачи")
    public void openTaskFromFilter() {
        openTaskLink.shouldBe(Condition.visible).click(ClickOptions.usingJavaScript());
    }
}
