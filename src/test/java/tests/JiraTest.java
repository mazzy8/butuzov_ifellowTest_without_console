package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pages.*;
import hooks.WebHooks;
import utils.ConfigProvider;
import java.util.List;

public class JiraTest extends WebHooks {
    public static Integer issueCounter;
    public static String issueKey;

    private final DashboardPage jiraDashboardPage = new DashboardPage();
    private final BrowseProjectsPage jiraBrowseProjectsPage = new BrowseProjectsPage();
    private final TestProjectPage jiraTestProjectPage = new TestProjectPage();
    private final IssuePage jiraIssuePage = new IssuePage();
    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();

    private void authorization() {
        jiraDashboardPage.signIn(ConfigProvider.LOGIN, ConfigProvider.PASSWORD);
    }
    private void openProjectIssueList() {
        jiraHeaderMenuPage.goToAllProjects();
        jiraBrowseProjectsPage.goToProject(ConfigProvider.PROJECTNAME);
        jiraTestProjectPage.goToProjectIssueList();
    }

    @Test
    @DisplayName("Авторизация")
    public void checkSignIn() {
        authorization();
        Assertions.assertTrue(jiraHeaderMenuPage.isUserSignedIn());
    }

    @Test
    @DisplayName("Переход в проект Test")
    public void checkGoToTestProject() {
        authorization();
        openProjectIssueList();
        Assertions.assertTrue(jiraTestProjectPage.isTestProjectPage());
    }

    @Test
    @DisplayName("Задачи на странице проекта Test")
    public void checkTestProjectIssues() {
        authorization();
        openProjectIssueList();
        issueCounter = jiraTestProjectPage.getIssueCounter();
        Assertions.assertNotNull(issueCounter);
    }

    @Test
    @DisplayName("Проверка параметров задачи TestSelenium")
    public void checkTestSeleniumTask() {
        authorization();
        openProjectIssueList();
        jiraTestProjectPage.goToIssue(ConfigProvider.ISSUENAME, ConfigProvider.ISSUETYPE);
        Assertions.assertEquals(ConfigProvider.ISSUENAME, jiraIssuePage.getIssueSummary(),
                "Саммери должно быть " + ConfigProvider.ISSUENAME);
        Assertions.assertEquals(ConfigProvider.ISSUESTATUS, jiraIssuePage.getIssueStatusValue(),
                "Статус должен быть " + ConfigProvider.ISSUESTATUS);
        Assertions.assertEquals(ConfigProvider.ISSUEFIXVERSION, jiraIssuePage.getIssueFixVersions(),
                "Версия должна быть " + ConfigProvider.ISSUEFIXVERSION);
    }

    @Test
    @DisplayName("Заводим багу")
    public void checkNewBugCreate() {
        authorization();
        openProjectIssueList();
        int beforeIssueCounter = jiraTestProjectPage.getIssueCounter();
        issueKey = jiraTestProjectPage.createNewIssue(ConfigProvider.NEWISSUEPARAMS);
        Assertions.assertNotNull(issueKey,"Должен вернуть номер новой задачи" + issueKey);

        openProjectIssueList();
        int afterIssueCounter = jiraTestProjectPage.getIssueCounter();
        Assertions.assertTrue(beforeIssueCounter < afterIssueCounter,
                "Счетчик запросов " + beforeIssueCounter + " должен увеличиться");

        jiraHeaderMenuPage.runSearch(issueKey);
        List<String> issueTypes = ConfigProvider.ISSUETYPES;
        for (String issueType : issueTypes) {
            jiraIssuePage.changeIssueStatus(issueType);
            Assertions.assertEquals(issueType, jiraIssuePage.getIssueStatusValue(),
                    "Тип баги должен быть " + issueType);
        }
    }
}
