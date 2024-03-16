package hooks;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;


public class Hooks {
    @BeforeMethod
    public void configureRestAssured() {
        RestAssured.requestSpecification = given().port(80)
                .header("Language", "en");
        RestAssured.defaultParser = Parser.JSON;
    }
}
