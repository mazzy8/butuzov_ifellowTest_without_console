package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.builder.ResponseBuilder;
import org.json.JSONObject;

public class LocationValueModificationFilter implements Filter {
    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);
        if (response.statusCode() == 200) {
            String responseBody = response.getBody().asString();
            JSONObject jsonResponse = new JSONObject(responseBody);

            if (jsonResponse.has("id") && jsonResponse.getInt("id") == 825) {
                jsonResponse.getJSONObject("location").put("name", "Citadel of Ricks");

                return new ResponseBuilder().clone(response).setBody(jsonResponse.toString()).build();
            }
        }
        return response;
    }
}