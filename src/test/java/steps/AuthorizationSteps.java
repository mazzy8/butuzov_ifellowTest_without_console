package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.DashboardPage;
import pages.HeaderMenuPage;

public class AuthorizationSteps {

    private String username;
    private String password;
    private final DashboardPage jiraDashboardPage = new DashboardPage();
    private final HeaderMenuPage jiraHeaderMenuPage = new HeaderMenuPage();

    @Дано("^логин и пароль: (AT\\d{1,3}) и (\\w+)$")
    public void setLogPass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Когда("^вводим логин и пароль$")
    public void inputLogPass() {
        jiraDashboardPage.inputLoginAndPassword(username, password);
    }

    @И("^нажимаем кнопку авторизации$")
    public void clickLoginButton() {
        jiraDashboardPage.clickLoginButton();
    }

    @Тогда("^страница содержит логин (AT\\d{1,3})$")
    public void checkLogin(String username){
        Assertions.assertEquals(username, jiraHeaderMenuPage.getUserName());
    }
}