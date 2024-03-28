package hooks;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;

public class Hooks {
    @BeforeEach
    public void configureRestAssured() {
        RestAssured.requestSpecification = given().port(443)
                .header("Language", "en");
        RestAssured.defaultParser = Parser.JSON;
    }
}
