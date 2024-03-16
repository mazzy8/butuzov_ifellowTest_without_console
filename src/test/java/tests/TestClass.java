package tests;

import hooks.Hooks;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import api.*;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class TestClass extends Hooks {
    private final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    private final ReqResApi reqResApi = new ReqResApi();

    @Test
    public void rickAndMortyTest() {

        Map<String, String> queryParams = Map.of("name", "Morty Smith");
        Response findMortyResponse = rickAndMortyApi.getApi(
                "https://rickandmortyapi.com/api/character", queryParams,200);
        List<String> allEpisodes = findMortyResponse.jsonPath().get("results[0].episode");
        String lastEpisodeUrl = allEpisodes.get(allEpisodes.size() - 1);

        Response getEpisodeResponse = rickAndMortyApi.getApi(lastEpisodeUrl,null,200);
        List<String> allCharacters = getEpisodeResponse.jsonPath().get("characters");
        String lastCharacterUrl = allCharacters.get(allCharacters.size() - 1);

        Response lastCharacterResponse = rickAndMortyApi.getApi(lastCharacterUrl,null,200);

        Assertions.assertEquals(findMortyResponse.jsonPath().getString("results[0].species"),
                lastCharacterResponse.jsonPath().getString("species"), "Расса должна совпадать");
        Assertions.assertEquals(findMortyResponse.jsonPath().getString("results[0].location.name"),
                lastCharacterResponse.jsonPath().getString("location.name"),
                "Местоположение должно совпадать");
    }

    @Test
    public void reqResTest() throws IOException {
        try {
            JSONObject body = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/json/reqres.json"))));
            body.put("name", "Tomato");
            body.put("job", "Eat maket");

            Response response = reqResApi.postApi("https://reqres.in/", "/api/users", body.toString(), 201);
            Assertions.assertEquals("Tomato", response.body().path("name"), "Имя должно совпадать");
            Assertions.assertEquals("Eat maket", response.body().path("job"),
                    "Место работы должно совпадать");
        } catch (IOException e) {
            Assertions.fail("Ошибка при чтении файла");
        }
    }
}
