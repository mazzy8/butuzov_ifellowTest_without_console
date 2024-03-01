package pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;


public class FilterPage {
    private final SelenideElement taskTypeArea = $x("//div/div/div/ul/li[2]/div");
    private final SelenideElement taskTypeCheck = $x("//*[@id=\"10100-1\"]/label");
    private final SelenideElement bugTypeCheck = $x("//li[@id='10102-6']/label");
    private final SelenideElement applyFilterButton = $x("(//button[@type='button'])[3]");
    private final SelenideElement openTaskLink = $x("//*[@id='key-val']");


    public void applyFilter(String taskType) {
        taskTypeArea.shouldBe(Condition.visible).click();
        switch (taskType) {
            case "Bug":
                bugTypeCheck.shouldBe(Condition.enabled).click(ClickOptions.usingJavaScript());
                break;
            case "Task":
            default:
                taskTypeCheck.shouldBe(Condition.enabled).click(ClickOptions.usingJavaScript());
                break;
        }
        applyFilterButton.shouldBe(Condition.visible).click();
    }

    public void openTaskFromFilter() {
        openTaskLink.shouldBe(Condition.visible).click(ClickOptions.usingJavaScript());
    }
}
