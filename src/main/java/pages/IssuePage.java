package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class IssuePage {
    private final SelenideElement issueSummary = $x("//*[@id='summary-val']")
            .as("Название задачи");
    private final SelenideElement issueStatusValue = $x("//*[@id='status-val']/span")
            .as("Статус задачи");
    private final SelenideElement issueFixVersions = $x("//*[@id='fixVersions-field']/a")
            .as("FixVersion задачи");
    private final SelenideElement issueKey = $x("//*[@id='key-val']")
            .as("Ключ задачи");
    private final SelenideElement issueStatusToDoButton = $x("//*[@id='action_id_11']/span")
            .as("Кнопка Нужно сделать");
    private final SelenideElement issueStatusInProgressButton = $x("//*[@id='action_id_21']/span")
            .as("Кнопка В работе");
    private final SelenideElement issueStatusBusinessProcessButton =
            $x("//*[@id='opsbar-transitions_more']/span").as("Кнопка Бизнесс-процесс");
    private final SelenideElement issueStatusDoneButton = $x("//*[@id='action_id_31']")
            .as("Кнопка Выполнено");
    private final SelenideElement issueStatusResolvedButton = $x("//*[@id='action_id_51']")
            .as("Кнопка Решено");
    private final SelenideElement editIssueModalFormSubmitButton =
            $x("//*[@id='issue-workflow-transition-submit']").as("Кнопка подтверждения мод. формы");
    private final SelenideElement issueStatusReopenedSubmitButton = $x("//*[@id='action_id_61']")
            .as("Кнопка Переоткрыть");

    @Step("Получение названия задачи")
    public String getIssueSummary() {
        return issueSummary.shouldBe(Condition.visible).shouldBe(Condition.visible).text();
    }

    @Step("Получение статуса задачи")
    public String getIssueStatusValue() {
        return issueStatusValue.shouldBe(Condition.visible).text();
    }

    @Step("Получение FixVersion задачи")
    public String getIssueFixVersions() {
        return issueFixVersions.shouldBe(Condition.visible).text();
    }

    @Step("Смена статуса задачи на \"{statusName}\"")
    public void changeIssueStatus(String statusName) {
        switch (statusName) {
            case "В РАБОТЕ":
                issueStatusInProgressButton.shouldBe(Condition.visible).click();
                break;
            case "ГОТОВО":
                issueStatusBusinessProcessButton.shouldBe(Condition.visible).click();
                issueStatusDoneButton.shouldBe(Condition.visible).click();
                break;
            case "РЕШЕННЫЕ":
                issueStatusBusinessProcessButton.shouldBe(Condition.visible).click();
                issueStatusResolvedButton.shouldBe(Condition.visible).click();
                editIssueModalFormSubmitButton.shouldBe(Condition.visible).click();
                break;
            case "ПЕРЕОТКРЫТ":
                issueStatusBusinessProcessButton.shouldBe(Condition.visible).click();
                issueStatusReopenedSubmitButton.shouldBe(Condition.visible).click();
                editIssueModalFormSubmitButton.shouldBe(Condition.visible).click();
                break;
            case "СДЕЛАТЬ":
            default:
                issueStatusToDoButton.shouldBe(Condition.visible).click();
                break;
            }
        issueStatusValue.shouldBe(Condition.visible).shouldHave(Condition.text(statusName));
        }
    }
