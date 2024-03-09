package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.BrowseProjectsPage;
import pages.DashboardPage;
import pages.HeaderMenuPage;
import pages.TestProjectPage;

public class GoToProjectSteps {

    private String username;
    private String password;
    private String projectName;
    private final DashboardPage jiraDashboardPage = new DashboardPage();
    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();
    private final BrowseProjectsPage jiraBrowseProjectsPage = new BrowseProjectsPage();
    private final TestProjectPage jiraTestProjectPage = new TestProjectPage();

    @Дано("^логин, пароль, название проекта: (AT\\d{1,3}), (\\w+), (.+)$")
    public void setup(String username, String password, String projectName) {
        this.username = username;
        this.password = password;
        this.projectName = projectName;
    }

    @Когда("^авторизуемся$")
    public void authorization() {
        jiraDashboardPage.inputLoginAndPassword(username, password);
        jiraDashboardPage.clickLoginButton();
    }

    @И("^переходим на страницу проекта$")
    public void goToProject() {
        jiraHeaderMenuPage.goToAllProjects();
        jiraBrowseProjectsPage.goToProject(projectName);
        jiraTestProjectPage.goToProjectIssueList();
    }

    @Тогда("^страница проекта содержит название проекта (.+)$")
    public void checkProjectName(String projectName){
        Assertions.assertEquals(projectName, jiraTestProjectPage.getProjectName());
    }

    @Тогда("^на странице есть счетчик задач$")
    public void checkIssueCounter(){
        Assertions.assertNotNull(jiraTestProjectPage.getIssueCounter());
    }
}