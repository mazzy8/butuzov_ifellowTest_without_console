package hooks;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;

public class Hooks {
    @BeforeEach
    public void configureRestAssured() {
        RestAssured.requestSpecification = given().port(443)
                .header("Language", "en");
        RestAssured.defaultParser = Parser.JSON;
    }
}
