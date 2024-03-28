package helpers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import static io.restassured.RestAssured.given;
import utils.LocationValueModificationFilter;

public class APIMethods {
    public static Response getApi(String baseUri, Map<String, String> queryParams, int statusCode) {
        RequestSpecification requestSpecBuilder = given().baseUri(baseUri);
        if (queryParams != null && !queryParams.isEmpty()) {
            queryParams.forEach(requestSpecBuilder::queryParam);
        }
        return requestSpecBuilder
                .filter(new LocationValueModificationFilter())
                .get()
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .response();
    }

    public static Response postApi(String baseUri, String endpoint, String body) {
        return given()
                .header("Content-type", "Application/json")
                .header("charset", "utf-8")
                .baseUri(baseUri)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}