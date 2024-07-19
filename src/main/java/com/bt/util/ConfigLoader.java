package com.bt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class for loading configuration properties from a file.
 * <p>
 * This class is responsible for loading properties from a configuration file
 * named {@code config.properties} located in the classpath. It provides a
 * method to retrieve property values by their keys.
 * </p>
 */
public class ConfigLoader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("Unable to find config.properties");
                throw new IOException("Unable to find config.properties");
            }
            properties.load(input);
            logger.info("Configuration loaded successfully");
        } catch (IOException ex) {
            logger.error("Error loading configuration", ex);
            throw new RuntimeException("Error loading configuration", ex);
        }
    }

    /**
     * Retrieves the value of a property by its key.
     * If the key is not found in the properties file, a warning is logged.
     *
     * @param key the key of the property to retrieve.
     * @return the value of the property, or {@code null} if the property is not found.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property not found: {}", key);
        }
        return value;
    }
}
