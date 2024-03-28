package api;

import helpers.APIMethods;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.ConfigProvider;
import utils.JsonFileReader;
import java.io.IOException;

public class ReqResApi {
    public String baseUrl = ConfigProvider.REQRESBASEURL;

    public JSONObject getReqResJson(String path) throws IOException{
        JSONObject body = null;
        try {
            body = JsonFileReader.readJsonFromFile(path);
        } catch (IOException e) {
            return null;
        }
        return body;
    }

    public Response getUserDataResponse(JSONObject body) {
        return APIMethods.postApi(baseUrl, ConfigProvider.REQRESEDPOINTUSERS, body.toString());
    }
}