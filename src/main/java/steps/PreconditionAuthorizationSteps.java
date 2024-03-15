package steps;

import io.cucumber.java.ru.Дано;
import java.util.Map;

import pages.*;

public class PreconditionAuthorizationSteps {

    private final DashboardPage jiraDashboardPage = new DashboardPage();

    @Дано("^пользователь авторизован с данными$")
    public void logIn(Map<String, String> arg) {
        jiraDashboardPage.inputLoginAndPassword(arg.get("username"), arg.get("password"));
        jiraDashboardPage.clickLoginButton();
    }
}
