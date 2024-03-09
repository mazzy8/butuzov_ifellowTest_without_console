package tests;

import hooks.WebHooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"classpath:steps", "classpath:hooks"},
        plugin = {"pretty", "html:target/cucumber"}
)
public class TestClass extends WebHooks {
}