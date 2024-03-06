package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class IssuePage {
    private final SelenideElement issueSummary = $x("//*[@id='summary-val']");
    private final SelenideElement issueStatusValue = $x("//*[@id='status-val']/span");
    private final SelenideElement issueFixVersions = $x("//*[@id='fixVersions-field']/a");
    private final SelenideElement issueKey = $x("//*[@id='key-val']");
    private final SelenideElement issueStatusToDoButton = $x("//*[@id='action_id_11']/span");
    private final SelenideElement issueStatusInProgressButton = $x("//*[@id='action_id_21']/span");
    private final SelenideElement issueStatusBusinessProcessButton = $x("//*[@id='opsbar-transitions_more']/span");
    private final SelenideElement issueStatusDoneButton = $x("//*[@id='action_id_31']");
    private final SelenideElement issueStatusResolvedButton = $x("//*[@id='action_id_51']");
    private final SelenideElement editIssueModalFormSubmitButton = $x("//*[@id='issue-workflow-transition-submit']");
    private final SelenideElement issueStatusReopenedSubmitButton = $x("//*[@id='action_id_61']");

    public String getIssueSummary() {
        return issueSummary.shouldBe(Condition.visible).shouldBe(Condition.visible).text();
    }

    public String getIssueStatusValue() {
        return issueStatusValue.shouldBe(Condition.visible).text();
    }

    public String getIssueFixVersions() {
        return issueFixVersions.shouldBe(Condition.visible).text();
    }

    public String getIssueKey() {
        return issueKey.shouldBe(Condition.visible).text();
    }

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
