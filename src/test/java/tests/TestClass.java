package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;
import api.*;
import hooks.Hooks;
import utils.ConfigProvider;
import java.io.IOException;

@DisplayName("APITests")
public class TestClass extends Hooks {
    private final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    private final ReqResApi reqResApi = new ReqResApi();

    @Test
    @DisplayName("Rick and Morty")
    public void rickAndMortyTest() {
        Response findMortyResponse = rickAndMortyApi.findUser(rickAndMortyApi.createParamsMap());
        Response lastCharacterResponse = rickAndMortyApi.getUserDataResponse();

        Assertions.assertEquals(findMortyResponse.jsonPath().getString("results[0].species"),
                lastCharacterResponse.jsonPath().getString("species"), "Расса должна совпадать");
        Assertions.assertEquals(findMortyResponse.jsonPath().getString("results[0].location.name"),
                lastCharacterResponse.jsonPath().getString("location.name"),
                "Местоположение должно совпадать");
    }

    @Test
    @DisplayName("ReqRes")
    public void reqResTest() throws IOException {
        JSONObject body = reqResApi.getAndUpdateJsonBody();
        Response response = reqResApi.getUserDataResponse(body);

        Assertions.assertEquals(ConfigProvider.REQRESEXPECTEDSTATUS, response.getStatusCode());
        Assertions.assertEquals(ConfigProvider.REQRESUSERNAME, response.getBody().path("name"));
        Assertions.assertEquals(ConfigProvider.REQRESUSERJOB, response.getBody().path("job"));
    }
}