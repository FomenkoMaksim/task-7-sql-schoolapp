package com.maksimfomenko.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceUtil {
    public static Properties loadPropertiesFromResources(String fileName) throws IOException {

        try (InputStream resourceAsStream = ResourceUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (resourceAsStream == null) {
                throw new IOException("File " + fileName + " not found");
            }

            Properties properties = new Properties();
            properties.load(resourceAsStream);
            return properties;
        }
    }
}

