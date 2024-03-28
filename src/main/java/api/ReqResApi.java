package api;

import helpers.APIMethods;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.ConfigProvider;
import utils.JsonFileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ReqResApi {
    public String baseUrl = ConfigProvider.REQRESBASEURL;

    public JSONObject getAndUpdateJsonBody() throws IOException {
        JSONObject file = getReqResJson(ConfigProvider.REQRESJSONFILEPATH);
        Map<String, String> params = createParamsMap();
        return updateJsonObject(file, params);
    }

    public Map<String, String> createParamsMap(){
        Map<String, String> body = new HashMap<>();
        body.put("name", ConfigProvider.REQRESUSERNAME);
        body.put("job", ConfigProvider.REQRESUSERJOB);
        return body;
    }

    public JSONObject getReqResJson(String path) throws IOException{
        JSONObject body = null;
        try {
            body = JsonFileReader.readJsonFromFile(path);
        } catch (IOException e) {
            return null;
        }
        return body;
    }

    public JSONObject updateJsonObject(JSONObject body, Map<String, String> params){
        for (Map.Entry<String, String> entry : params.entrySet()) {
            body.put(entry.getKey(), entry.getValue());
        }
        return body;
    }

    public Response getUserDataResponse(JSONObject body) {
        return APIMethods.postApi(baseUrl, ConfigProvider.REQRESEDPOINTUSERS, body.toString());
    }
}
