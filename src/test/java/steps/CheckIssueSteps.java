package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class CheckIssueSteps {
    private String username;
    private String password;
    private String issueName;
    private String issueType;
    private String issueStatus;
    private String issueFixVersion;
    private final DashboardPage jiraDashboardPage = new DashboardPage();
    private final IssuePage jiraIssuePage = new IssuePage();
    private final TestProjectPage jiraTestProjectPage = new TestProjectPage();

    @Дано("^логин, пароль, название задачи, статус задачи, версия задачи: (AT\\d{1,3}), (\\w+), (.+), (.+), (.+), (.+)$")
    public void setup(String username, String password, String issueName, String issueType,
                      String issueStatus, String issueFixVersion) {
        this.username = username;
        this.password = password;
        this.issueName = issueName;
        this.issueStatus = issueStatus;
        this.issueFixVersion = issueFixVersion;
        this.issueType = issueType;
    }

    @Когда("авторизуемся - открываем страницу задачи <issueName>$")
    public void authorizationAndGoToIssue() {
        jiraDashboardPage.inputLoginAndPassword(username, password);
        jiraDashboardPage.clickLoginButton();
        jiraTestProjectPage.goToIssue(issueName, issueType);
    }

    @Тогда("^страница задачи имеет опеределенные <issueStatus> и <issueFixVersion>$")
    public void checkIssue(){
        Assertions.assertEquals(issueStatus, jiraIssuePage.getIssueStatusValue());
        Assertions.assertEquals(issueFixVersion, jiraIssuePage.getIssueFixVersions());
    }
}