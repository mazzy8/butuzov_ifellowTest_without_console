package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class CreateNewTaskPage {
    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();
    private final SelenideElement taskType = $x("//*[@id=\"issuetype-field\"]");
    private final SelenideElement taskSummary = $x("//*[@id=\"summary\"]");
    private final SelenideElement taskText = $x("//*[@id=\"tinymce\"]");
    private final SelenideElement createTaskButton = $x("//*[@id=\"create-issue-submit\"]");

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
