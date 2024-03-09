//package steps;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions;
//
//
//import hooks.WebHooks;
//
//
//public class Steps extends WebHooks {
//    public static String issueCounter = "Не проверялось";
//    public static String issueKey;
//
//
//    private final DashboardPage jiraDashboardPage = new DashboardPage();
//    private final BrowseProjectsPage jiraBrowseProjectsPage = new BrowseProjectsPage();
//    private final TestProjectPage jiraTestProjectPage = new TestProjectPage();
//    private final IssuePage jiraIssuePage = new IssuePage();
//    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();
//
//    private final String username = "AT4";
//    private final String password = "Qwerty123";
//    private final String issueNameForSearch = "TestSelenium";
//    private final String issueStatus = "СДЕЛАТЬ";
//    private final String issueFixVersions = "Version 2.0";
//    private final String[] newIssueParams = {"Ошибка", "CreateNewBugAT4", "BigBigBigBuuuuuuuuuug!!!!!"};
//    private final String[] issueTypes = {"СДЕЛАТЬ", "В РАБОТЕ", "РЕШЕННЫЕ", "ПЕРЕОТКРЫТ", "ГОТОВО"};
//
//
//    private void openProjectIssueList() {
//        jiraHeaderMenuPage.goToAllProjects();
//        jiraBrowseProjectsPage.goToProject("Test");
//        jiraTestProjectPage.goToProjectIssueList();
//    }
//
//    @Test
//    @DisplayName("Авторизация")
//    public void checkSignIn() {
//        jiraDashboardPage.signIn(username, password);
//        Assertions.assertTrue(jiraHeaderMenuPage.isUserSignedIn());
//    }
//
//    @Test
//    @DisplayName("Переход в проект Test")
//    public void checkGoToTestProject() {
//        jiraDashboardPage.signIn(username, password);
//        openProjectIssueList();
//        Assertions.assertTrue(jiraTestProjectPage.isTestProjectPage());
//    }
//
//    @Test
//    @DisplayName("Задачи на странице проекта Test")
//    public void checkTestProjectIssues() {
//        jiraDashboardPage.signIn(username, password);
//        openProjectIssueList();
//        issueCounter = jiraTestProjectPage.getIssueCounter();
//        Assertions.assertNotNull(issueCounter);
//    }
//
//    @Test
//    @DisplayName("Проверка параметров задачи TestSelenium")
//    public void checkTestSeleniumTask() {
//        jiraDashboardPage.signIn(username, password);
//        openProjectIssueList();
//        jiraTestProjectPage.goToIssue(issueNameForSearch, "Task");
//        Assertions.assertEquals(issueNameForSearch, jiraIssuePage.getIssueSummary(),
//                "Саммери должно быть " + issueNameForSearch);
//        Assertions.assertEquals(issueStatus, jiraIssuePage.getIssueStatusValue(),
//                "Статус должен быть " + issueStatus);
//        Assertions.assertEquals(issueFixVersions, jiraIssuePage.getIssueFixVersions(),
//                "Версия должна быть " + issueFixVersions);
//    }
//
//    @Test
//    @DisplayName("Заводим багу")
//    public void checkNewBugCreate() {
//        jiraDashboardPage.signIn(username, password);
//        openProjectIssueList();
//        int beforeIssueCounter = Integer.parseInt(jiraTestProjectPage.getIssueCounter());
//        issueKey = jiraTestProjectPage.createNewIssue(newIssueParams);
//        Assertions.assertNotNull(issueKey,"Должен вернуть номер новой задачи" + issueKey);
//
//        openProjectIssueList();
//        int afterIssueCounter = Integer.parseInt(jiraTestProjectPage.getIssueCounter());
//        Assertions.assertTrue(beforeIssueCounter < afterIssueCounter,
//                "Счетчик запросов " + beforeIssueCounter + " должен увеличиться");
//
//        jiraHeaderMenuPage.runSearch(issueKey);
//        for (int i = 0; i < issueTypes.length; i++) {
//            jiraIssuePage.changeIssueStatus(issueTypes[i]);
//            Assertions.assertEquals(issueTypes[i], jiraIssuePage.getIssueStatusValue(),
//                    "Тип баги должен быть " + issueTypes[i]);
//        }
//
//    }
//}
