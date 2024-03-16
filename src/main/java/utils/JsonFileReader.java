package utils;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class JsonFileReader {
    public static JSONObject readJsonFromFile(String filePath) throws IOException {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(filePath));
            String jsonString = new String(jsonData);
            return new JSONObject(jsonString);
        } catch (NoSuchFileException e) {
            throw new IOException("Файл не найден: " + e.getMessage());
        }
    }
}