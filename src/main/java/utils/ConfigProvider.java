package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("test.conf");
    }

    String RAMBASEURL = config.getString("rick.and.morty.base.url");
    String RAMUSERNAME  = config.getString("rick.and.morty.find.user.name");
    String REQRESBASEURL = config.getString("reqres.base.url");
    String RAMEDPOINTCHARACTER = config.getString("rick.and.morty.endpoint.character");
    String REQRESEDPOINTUSERS = config.getString("reqres.endpoint.users");
    String REQRESJSONFILEPATH= config.getString("reqres.path.reqres.json");
    String REQRESUSERNAME= config.getString("reqres.user.name");
    String REQRESUSERJOB= config.getString("reqres.user.job");
    Integer REQRESEXPECTEDSTATUS = config.getInt("reqres.expected.status");
}