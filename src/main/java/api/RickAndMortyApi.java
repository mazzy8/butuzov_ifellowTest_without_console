package api;

import helpers.APIMethods;
import utils.ConfigProvider;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RickAndMortyApi {
    private String baseUrl = ConfigProvider.RAMBASEURL;

    public Map<String, String> createParamsMap(){
        Map<String, String> body = new HashMap<>();
        body.put("name", ConfigProvider.RAMUSERNAME);
        return body;
    }

    public Response findUser(Map<String, String> queryParams) {
        String url = baseUrl + ConfigProvider.RAMEDPOINTCHARACTER;
        return APIMethods.getApi(url, queryParams, 200);
    }

    public String getLastEpisodeUrl() {
        Response response = findUser(createParamsMap());
        List<String> allEpisodes = response.jsonPath().get("results[0].episode");
        return allEpisodes.get(allEpisodes.size() - 1);
    }

    public String getLastCharacterUrl() {
        String lastEpisodeUrl = getLastEpisodeUrl();
        Response getEpisodesResponse = APIMethods.getApi(lastEpisodeUrl,null,200);
        List<String> allCharacters = getEpisodesResponse.jsonPath().get("characters");
        return allCharacters.get(allCharacters.size() - 1);
    }

    public Response getUserDataResponse() {
        String lastCharacterUrl = getLastCharacterUrl();
        return APIMethods.getApi(lastCharacterUrl, null, 200);
    }
}