package api;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import utils.LocationValueModificationFilter;

import java.util.Map;

public class RickAndMortyApi {

    public Response getApi(String baseUri, Map<String, ?> queryParams, int statusCode) {
        var requestSpecBuilder = given().baseUri(baseUri);
        if (queryParams != null && !queryParams.isEmpty()) {
            queryParams.forEach((key, value) -> {
                requestSpecBuilder.queryParam(key, value);
            });
        }
        return requestSpecBuilder
                .filters(new LocationValueModificationFilter())
                .get()
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(statusCode)
                .extract()
                .response();
    }
}
