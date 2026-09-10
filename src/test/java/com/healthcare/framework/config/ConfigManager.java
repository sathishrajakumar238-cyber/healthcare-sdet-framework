package com.healthcare.framework.config;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConfigManager {

    private static ConfigManager instance;
    private final Properties properties = new Properties();

    private ConfigManager() {
        String fileName = "config/qa.properties";

        try (InputStream in = getClass().getClassLoader()
                .getResourceAsStream(fileName)) {

            if (in == null) {
                throw new RuntimeException(
                        "Config file not found on classpath: " + fileName
                );
            }

            properties.load(in);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load config file: " + fileName, e
            );
        }
    }

    public static synchronized ConfigManager get() {

        if (instance == null) {
            instance = new ConfigManager();
        }

        return instance;
    }

    public String getApiBaseUrl() {
        return properties.getProperty("api.base.url");
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}