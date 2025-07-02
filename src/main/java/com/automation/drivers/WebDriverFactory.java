package com.automation.drivers;

import com.automation.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * WebDriver Factory class for creating and managing WebDriver instances
 * Supports multiple browsers with configurable options
 */
public class WebDriverFactory {
    
    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
    private static final ConfigManager config = ConfigManager.getInstance();

    /**
     * Create WebDriver instance based on configuration
     * @return WebDriver instance
     */
    public static WebDriver createDriver() {
        String browser = config.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(config.getProperty("headless", "false"));
        
        logger.info("Creating WebDriver for browser: {} (headless: {})", browser, headless);
        
        switch (browser.toLowerCase()) {
            case "chrome":
                return createChromeDriver(headless);
            case "firefox":
                return createFirefoxDriver(headless);
            case "edge":
                return createEdgeDriver(headless);
            default:
                logger.warn("Unknown browser: {}. Using Chrome as default.", browser);
                return createChromeDriver(headless);
        }
    }

    /**
     * Create Chrome WebDriver
     * @param headless run in headless mode
     * @return Chrome WebDriver
     */
    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        
        if (headless) {
            options.addArguments("--headless");
        }
        
        // Common Chrome options for stability
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-plugins");
        options.addArguments("--disable-images");
        
        // Disable Chrome password manager and save password prompts
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=VizDisplayCompositor");
        
        logger.debug("Chrome options configured: {}", options);
        return new ChromeDriver(options);
    }

    /**
     * Create Firefox WebDriver
     * @param headless run in headless mode
     * @return Firefox WebDriver
     */
    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        
        if (headless) {
            options.addArguments("--headless");
        }
        
        // Common Firefox options for stability
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        
        logger.debug("Firefox options configured: {}", options);
        return new FirefoxDriver(options);
    }

    /**
     * Create Edge WebDriver
     * @param headless run in headless mode
     * @return Edge WebDriver
     */
    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        
        if (headless) {
            options.addArguments("--headless");
        }
        
        // Common Edge options for stability
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        
        logger.debug("Edge options configured: {}", options);
        return new EdgeDriver(options);
    }
} 