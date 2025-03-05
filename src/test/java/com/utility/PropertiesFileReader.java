package com.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesFileReader {

    public Properties getProperty() {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("src/test/resources/properties/Config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}