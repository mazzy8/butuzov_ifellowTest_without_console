package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class CreateNewTaskPage {
    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();
    private final SelenideElement taskType = $x("//*[@id='issuetype-field']")
            .as("Поле ввода Тип задачи");
    private final SelenideElement taskSummary = $x("//*[@id='summary']")
            .as("Поле ввода Название задачи");
    private final SelenideElement taskText = $x("//*[@id='tinymce']")
            .as("Поле ввода Описание задачи");
    private final SelenideElement createTaskButton = $x("//*[@id='create-issue-submit']")
            .as("Кнопка подтверждения");

    @Step("Создание новой задачи")
    public String createNewTask(String[] newIssueParams) {
        taskType.shouldBe(Condition.visible).setValue(newIssueParams[0]);
        taskSummary.shouldBe(Condition.visible).setValue(newIssueParams[1]);
        switchTo().frame("mce_7_ifr");
        taskText.shouldBe(Condition.visible).setValue(newIssueParams[2]);
        switchTo().defaultContent();
        createTaskButton.shouldBe(Condition.visible).click();
        String popUpText = jiraHeaderMenuPage.getTextFromPopUp();
        return popUpText.split(" ")[1];
    }
}
