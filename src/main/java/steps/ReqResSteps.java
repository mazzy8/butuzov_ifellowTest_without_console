package steps;

import api.ReqResApi;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import utils.ConfigProvider;
import java.io.IOException;
import java.util.Map;

public class ReqResSteps {
    private final ReqResApi reqResApi = new ReqResApi();
    private JSONObject body;
    private String name;
    private String job;
    private Response response;

    @Дано("^имя, работа пользователя:$")
    public void SetNameAndJobAndStatus(Map<String, String> arg){
        this.name = arg.get("name");
        this.job = arg.get("job");
    }

    @Когда("^загружен JSON файл и изменены данные в нем$")
    public void modificationJSON() throws IOException {
        JSONObject ReqResJson = reqResApi.getReqResJson(ConfigProvider.REQRESJSONFILEPATH);
        ReqResJson.put("name", this.name);
        ReqResJson.put("job", this.job);
        this.body = ReqResJson;
    }

    @И("^отправлен POST запрос с body из модифицированного JSON$")
    public void getResponse(){
        this.response = reqResApi.getUserDataResponse(this.body);
    }

    @Тогда("^статус код ответа должен быть (\\d{3})$")
    public void checkStatusResponse(String expectedStatus){
        Assertions.assertEquals(Integer.parseInt(expectedStatus), this.response.getStatusCode());
    }

    @И("^имя и работа должны сосотвествовать данным в модифицированного JSON$")
    public void checkBodyResponse(){
        Assertions.assertEquals(this.body.get("name"), response.getBody().path("name"));
        Assertions.assertEquals(this.body.get("job"), response.getBody().path("job"));
    }
}