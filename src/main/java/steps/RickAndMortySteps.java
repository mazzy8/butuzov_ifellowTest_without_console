package steps;

import api.RickAndMortyApi;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class RickAndMortySteps {
    private final RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    private String characterName;
    private Response characterNameResponse;
    private Response temporaryResponse;
    private String lastEpisodeUrl;
    private String lastCharacterUrl;

    @Дано("^имя персонажа для поиска: (.+)$")
    public void getCharacterName(String characterName) {
        this.characterName = characterName;
    }

    @Когда("^отправляем запрос на поиск пользователя и ожидаем статус: (\\d{3})$")
    public void getUserData(String statusCode) {
        Response findUserResponse = rickAndMortyApi.findUser(rickAndMortyApi.createParamsMap(this.characterName));
        Assertions.assertEquals(Integer.parseInt(statusCode), findUserResponse.statusCode());
        this.characterNameResponse = findUserResponse;
    }

    @Тогда("^получаем url последней серии из списка серий персонажа$")
    public void getLastEpisode() {
        String lastEpisodeUrl = rickAndMortyApi.getLastEpisodeUrl(this.characterNameResponse);
        Assertions.assertNotNull(lastEpisodeUrl);
        this.lastEpisodeUrl = lastEpisodeUrl;
    }

    @Когда("^отправляем запрос на получение списка персонажей из последней серии и ожидаем статус: (\\d{3})$")
    public void getCharacterList(String statusCode) {
        Response getCharacterList = rickAndMortyApi.executeGetRequest(this.lastEpisodeUrl);
        Assertions.assertEquals(Integer.parseInt(statusCode), getCharacterList.statusCode());
        this.temporaryResponse = getCharacterList;
    }

    @Тогда("^получаем url последнего персонажа из ответа$")
    public void getLastCharacterUrl() {
        String lastCharacterUrl = rickAndMortyApi.getLastCharacterUrl(this.temporaryResponse);
        Assertions.assertNotNull(lastCharacterUrl);
        this.lastCharacterUrl = lastCharacterUrl;
    }

    @Когда("^оправляем запрос на получение данных персонажа и ожидаем статус: (\\d{3})$")
    public void getLastCharacterData(String statusCode) {
        Response lastCharacterDataResponse = rickAndMortyApi.executeGetRequest(this.lastCharacterUrl);
        Assertions.assertEquals(Integer.parseInt(statusCode), lastCharacterDataResponse.statusCode());
        this.temporaryResponse = lastCharacterDataResponse;
    }
    @Тогда("^сверяем рассу и местонахождение искомого и полученного персонажей$")
    public void compareData() {
        Assertions.assertEquals(this.characterNameResponse.jsonPath().getString("results[0].species"),
                this.temporaryResponse.jsonPath().getString("species"), "Расса должна совпадать");
        Assertions.assertEquals(this.characterNameResponse.jsonPath().getString("results[0].location.name"),
                this.temporaryResponse.jsonPath().getString("location.name"),
                "Местоположение должно совпадать");
    }
}