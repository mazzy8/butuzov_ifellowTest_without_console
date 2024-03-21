package helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class AllureEnvironmentWriter {

    public static void writeEnvironmentProperties() {
        Properties properties = new Properties();
        // Информация о системе
        properties.setProperty("OS.Name", System.getProperty("os.name"));
        properties.setProperty("OS.Arch", System.getProperty("os.arch"));
        properties.setProperty("Java.Version", System.getProperty("java.version"));
        // Информация о браузере
        String browserInfo = BrowserInfo.getBrowserInfo();
        properties.setProperty("Browser.Info", browserInfo);

        String filePath = "target/allure-results/environment.properties";
        File file = new File(filePath);

        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        try (OutputStream out = new FileOutputStream(file)) {
            properties.store(out, "Environment Properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}