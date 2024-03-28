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
    private Integer expectedStatus;

    @Дано("^имя и работа пользователя:$")
    public void SetNameAndJob(Map<String, String> arg) {
        this.name = arg.get("name");
        this.job = arg.get("job");
    }

    @Когда("^считаем информацию из json$")
    public void getReqResJson() throws IOException {
        this.body = reqResApi.getReqResJson(ConfigProvider.REQRESJSONFILEPATH);
    }

    @И("^обновляем данные в полученном объекте$")
    public void updateJson(){
        this.body.put("name", this.name);
        this.body.put("job", this.job);
    }

    @Дано("^ожидаемый статус:$")
    public void setExpectedStatus(Map<String, String> arg) {
        this.expectedStatus = Integer.parseInt(arg.get("expectedStatus"));
    }

    @Когда("^получен ответ на POST запрос$")
    public void getResponse(){
        this.response = reqResApi.getUserDataResponse(this.body);
    }

    @Тогда("^проверяем параметры ответа$")
    public void checkBodyResponse(){
        Assertions.assertEquals(this.expectedStatus, response.getStatusCode());
        Assertions.assertEquals(this.name, response.getBody().path("name"));
        Assertions.assertEquals(this.job, response.getBody().path("job"));
    }
}
