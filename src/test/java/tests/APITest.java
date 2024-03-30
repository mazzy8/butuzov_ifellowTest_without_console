package tests;

import hooks.Hooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"classpath:steps", "classpath:hooks"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "html:target/cucumber-report.html"}
)
public class APITest extends Hooks {
}