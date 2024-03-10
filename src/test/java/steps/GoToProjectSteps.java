package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class GoToProjectSteps {

    private String projectName;

    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();
    private final BrowseProjectsPage jiraBrowseProjectsPage = new BrowseProjectsPage();
    private final TestProjectPage jiraTestProjectPage = new TestProjectPage();


    @Дано("^название проекта: (.+)$")
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Когда("^переходим на страницу проекта$")
    public void goToProject() {
        jiraHeaderMenuPage.goToAllProjects();
        jiraBrowseProjectsPage.goToProject(projectName);
        jiraTestProjectPage.goToProjectIssueList();
    }

    @Тогда("^страница проекта содержит название проекта$")
    public void checkProjectName(){
        Assertions.assertEquals(projectName, jiraTestProjectPage.getProjectName());
    }

    @Тогда("^на странице есть счетчик задач$")
    public void checkIssueCounter(){
        Assertions.assertNotNull(jiraTestProjectPage.getIssueCounter());
    }
}