package steps;

import java.util.Map;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class NewIssueSteps {

    private String projectName;
    private Map<String, String> newIssueParams;
    private Integer issueCounter;
    private String issueKey;

    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();
    private final BrowseProjectsPage jiraBrowseProjectsPage = new BrowseProjectsPage();
    private final TestProjectPage jiraTestProjectPage = new TestProjectPage();
    private final IssuePage jiraIssuePage = new IssuePage();


    @Дано("^имя проекта:$")
    public void setProjectName(Map<String, String> arg) {
        this.projectName = arg.get("projectName");
    }

    @И("^параметры новой задачи:$")
    public void setIssueParams(Map<String, String> arg) {
        this.newIssueParams = arg;
    }

    @Когда("^переходим в проект - проверяем счетчик задач$")
    public void authorizationAndCheckCounter() {
        jiraHeaderMenuPage.goToAllProjects();
        jiraBrowseProjectsPage.goToProject(projectName);
        jiraTestProjectPage.goToProjectIssueList();
        this.issueCounter =  jiraTestProjectPage.getIssueCounter();
    }

    @И("^заводим новую задачу$")
    public void newIssue() {
         this.issueKey = jiraTestProjectPage.createNewIssueFromMap(this.newIssueParams);
    }

    @Тогда("^счетчик задач должен увеличиться$")
    public void checkIssueCounter(){
        jiraHeaderMenuPage.goToAllProjects();
        jiraBrowseProjectsPage.goToProject(projectName);
        jiraTestProjectPage.goToProjectIssueList();
        Assertions.assertTrue(this.issueCounter < jiraTestProjectPage.getIssueCounter());
    }

    @Когда("^производим смену статуса задачи на (.+)$")
    public void changeIssueStatus(String issueStatus){
        jiraHeaderMenuPage.runSearch(this.issueKey);
        jiraIssuePage.changeIssueStatus(issueStatus);
    }

    @Тогда("^статус задачи меняется на (.+)$")
    public void checkChangeIssueStatus(String issueStatus){
        Assertions.assertEquals(issueStatus, jiraIssuePage.getIssueStatusValue());
    }
}