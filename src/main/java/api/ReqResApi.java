package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ReqResApi {
    public Response postApi(String baseUri, String postUri, String body, int statusCode) {
        return given()
                .header("Content-type", "Application/json")
                .header("charset", "utf-8")
                .baseUri(baseUri)
                .body(body)
                .when()
                .post(postUri)
                .then()
                .statusCode(statusCode)
                .log().ifError()
                .extract()
                .response();
    }
}