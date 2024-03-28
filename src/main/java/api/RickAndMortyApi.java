package api;

import helpers.APIMethods;
import utils.ConfigProvider;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RickAndMortyApi {
    private String baseUrl = ConfigProvider.RAMBASEURL;

    public Map<String, String> createParamsMap(String name){
        Map<String, String> body = new HashMap<>();
        body.put("name", name);
        return body;
    }

    public Response findUser(Map<String, String> queryParams) {
        String url = this.baseUrl + ConfigProvider.RAMEDPOINTCHARACTER;
        return APIMethods.getApi(url, queryParams);
    }

    public Response executeGetRequest(String url) {
        return APIMethods.getApi(url, null);
    }

    public String getLastEpisodeUrl(Response response) {
        List<String> allEpisodes = response.jsonPath().get("results[0].episode");
        return allEpisodes.get(allEpisodes.size() - 1);
    }

    public String getLastCharacterUrl(Response response) {
        List<String> allCharacters = response.jsonPath().get("characters");
        return allCharacters.get(allCharacters.size() - 1);
    }
}