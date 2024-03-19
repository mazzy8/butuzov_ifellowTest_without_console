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

    String JIRAURL = readConfig().getString("jiraUrl");
    String LOGIN = readConfig().getString("userParams.user.login");
    String PASSWORD = readConfig().getString("userParams.user.password");
    String PROJECTNAME = readConfig().getString("jiraProjectName");
    String ISSUETYPE = readConfig().getString("checkIssueParams.issueType");
    String ISSUENAME = readConfig().getString("checkIssueParams.issueName");
    String ISSUESTATUS = readConfig().getString("checkIssueParams.issueStatus");
    String ISSUEFIXVERSION = readConfig().getString("checkIssueParams.issueFixVersions");
    String[] NEWISSUEPARAMS = readConfig().getStringList("newIssueParams").toArray(new String[0]);
    List<String> ISSUETYPES = config.getStringList("issueTypes");
}
