package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;


import pages.*;

public class CheckIssueSteps {
    private String issueName;
    private String issueType;
    private String issueStatus;
    private String issueFixVersion;

    private final IssuePage jiraIssuePage = new IssuePage();
    private final TestProjectPage jiraTestProjectPage = new TestProjectPage();


    @Дано("^название задачи, тип задачи, статус задачи, версия задачи: (.+), (.+), (.+), (.+)$")
    public void setup(String issueName, String issueType, String issueStatus, String issueFixVersion) {
        this.issueName = issueName;
        this.issueStatus = issueStatus;
        this.issueFixVersion = issueFixVersion;
        this.issueType = issueType;
    }

    @Когда("^открываем страницу задачи$")
    public void authorizationAndGoToIssue() {
        jiraTestProjectPage.goToIssue(issueName, issueType);
    }

    @Тогда("^страница задачи имеет опеределенные статус и версию$")
    public void checkIssue(){
        Assertions.assertEquals(issueStatus, jiraIssuePage.getIssueStatusValue());
        Assertions.assertEquals(issueFixVersion, jiraIssuePage.getIssueFixVersions());
    }
}