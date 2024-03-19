package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class TestProjectPage {
    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();
    private final FilterPage jiraFilterPage = new FilterPage();
    private final CreateNewTaskPage jiraNewTaskPage = new CreateNewTaskPage();

    private final SelenideElement searchProjectName = $x("//a[contains(text(),'Test')]");
    private final SelenideElement searchProjectIssueCounter = $x("//div[2]/div/div/div/div/span");
    private final SelenideElement testProjectIssues = $x(
            "//*[@id='sidebar']/div/div[1]/nav/div/div/ul/li[5]/a/span[1]");

    public Boolean isTestProjectPage() {
        return searchProjectName.shouldBe(Condition.visible).isDisplayed();
    }

    public Integer getIssueCounter() {
        String issueCounter = searchProjectIssueCounter.shouldBe(Condition.visible).text();
        String[] parts = issueCounter.split(" ");
        return Integer.parseInt(parts[parts.length - 1]);
    }

    public void goToIssue(String issueName, String issueType) {
        jiraHeaderMenuPage.runSearch(issueName);
        jiraFilterPage.applyFilter(issueType);
        jiraFilterPage.openTaskFromFilter();
    }

    public void goToProjectIssueList() {
        testProjectIssues.shouldBe(Condition.visible).click();
    }

    public String createNewIssue(String[] newIssueParams) {
        jiraHeaderMenuPage.runNewTask();
        return jiraNewTaskPage.createNewTask(newIssueParams);
    }
}
