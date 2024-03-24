package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.util.List;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("jira.conf");
    }

    String JIRAURL = config.getString("jira.url");
    String LOGIN = config.getString("login");
    String PASSWORD = config.getString("password");
    String PROJECTNAME = config.getString("jira.project.name");
    String ISSUETYPE = config.getString("issue.type");
    String ISSUENAME = config.getString("issue.name");
    String ISSUESTATUS = config.getString("issue.status");
    String ISSUEFIXVERSION = config.getString("issue.fix.versions");
    String[] NEWISSUEPARAMS = config.getStringList("new.issue.params").toArray(new String[0]);
    List<String> ISSUETYPES = config.getStringList("issue.types");
}
