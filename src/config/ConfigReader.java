package config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Reads config from config.json file
 */

public class ConfigReader {

    public static GameConfig getGameConfig() {
        GameConfig gameConfig;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String configFile = "src/config/config.json";
            File file = new File(configFile);
            gameConfig = objectMapper.readValue(file, GameConfig.class);
        } catch (IOException exception) {
            throw new RuntimeException("Failed reading config file: " + exception);
        }
        return gameConfig;
    }
}
