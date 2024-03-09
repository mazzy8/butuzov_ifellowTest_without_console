package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {

    private final SelenideElement loginInput = $x("//input[@id='login-form-username']");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']");
    private final SelenideElement loginButton = $x("//input[@id='login']");

    public void inputLoginAndPassword(String username, String password) {
        loginInput.shouldBe(Condition.visible).setValue(username);
        passwordInput.shouldBe(Condition.visible).setValue(password);
    }

    public void clickLoginButton() {
        loginButton.shouldBe(Condition.visible).click();
    }
}
