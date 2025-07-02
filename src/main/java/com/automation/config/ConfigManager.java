package com.automation.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration Manager class to handle application configuration
 * Implements Singleton pattern for configuration management
 */
public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
    private static ConfigManager instance;
    private Properties properties;

    private ConfigManager() {
        loadProperties();
    }

    /**
     * Get singleton instance of ConfigManager
     * @return ConfigManager instance
     */
    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    /**
     * Load properties from config.properties file
     */
    private void loadProperties() {
        properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Error loading configuration properties: " + e.getMessage());
            // Set default values if config file is not found
            setDefaultProperties();
        }
    }

    /**
     * Set default properties if config file is not found
     */
    private void setDefaultProperties() {
        properties.setProperty("base.url", "https://www.saucedemo.com/v1/");
        properties.setProperty("browser", "chrome");
        properties.setProperty("headless", "false");
        properties.setProperty("implicit.wait", "10");
        properties.setProperty("explicit.wait", "20");
        properties.setProperty("page.load.timeout", "30");
        properties.setProperty("standard.user", "standard_user");
        properties.setProperty("locked.out.user", "locked_out_user");
        properties.setProperty("problem.user", "problem_user");
        properties.setProperty("performance.glitch.user", "performance_glitch_user");
        properties.setProperty("password", "secret_sauce");
        properties.setProperty("screenshot.on.failure", "true");
        properties.setProperty("screenshot.path", "screenshots/");
        properties.setProperty("extent.report.path", "reports/");
        properties.setProperty("extent.report.title", "Sauce Demo Automation Report");
        properties.setProperty("extent.report.name", "Test Execution Report");
        properties.setProperty("parallel.execution", "true");
        properties.setProperty("thread.count", "3");
        properties.setProperty("log.level", "INFO");
        properties.setProperty("log.file.path", "logs/");
        logger.info("Default configuration properties set");
    }

    /**
     * Get property value as String
     * @param key property key
     * @return property value
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property value as String with default value
     * @param key property key
     * @param defaultValue default value if key not found
     * @return property value or default value
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    /**
     * Get property value as Integer
     * @param key property key
     * @return property value as Integer
     */
    public int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    /**
     * Get property value as Integer with default value
     * @param key property key
     * @param defaultValue default value if key not found
     * @return property value or default value as Integer
     */
    public int getIntProperty(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }

    /**
     * Get property value as Boolean
     * @param key property key
     * @return property value as Boolean
     */
    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    /**
     * Get property value as Boolean with default value
     * @param key property key
     * @param defaultValue default value if key not found
     * @return property value or default value as Boolean
     */
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(properties.getProperty(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // Convenience methods for commonly used properties
    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getBrowser() {
        return getProperty("browser", "chrome");
    }

    public boolean isHeadless() {
        return getBooleanProperty("headless", false);
    }

    public int getImplicitWait() {
        return getIntProperty("implicit.wait", 10);
    }

    public int getExplicitWait() {
        return getIntProperty("explicit.wait", 20);
    }

    public int getPageLoadTimeout() {
        return getIntProperty("page.load.timeout", 30);
    }

    public String getStandardUser() {
        return getProperty("standard.user");
    }

    public String getLockedOutUser() {
        return getProperty("locked.out.user");
    }

    public String getProblemUser() {
        return getProperty("problem.user");
    }

    public String getPerformanceGlitchUser() {
        return getProperty("performance.glitch.user");
    }

    public String getPassword() {
        return getProperty("password");
    }
} 