package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement loginInput = $x("//input[@id='login-form-username']")
            .as("Поле ввода Логин");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']")
            .as("Поле ввода Пароль");
    private final SelenideElement loginButton = $x("//input[@id='login']")
            .as("Кнопка авторизации");

    @Step("Авторизация пользователя \"{username}\"")
    public void signIn(String username, String password) {
        loginInput.shouldBe(Condition.visible).setValue(username);
        passwordInput.shouldBe(Condition.visible).setValue(password);
        loginButton.shouldBe(Condition.visible).click();
    }
}
