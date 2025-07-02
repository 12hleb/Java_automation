package com.automation.utils;

import com.automation.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility class for taking screenshots during test execution
 * Provides methods for capturing screenshots on test failures and for debugging
 */
public class ScreenshotUtils {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);
    private static final ConfigManager config = ConfigManager.getInstance();

    /**
     * Take screenshot and save to file
     * @param driver WebDriver instance
     * @param testName name of the test for file naming
     * @return path to the saved screenshot file
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            logger.warn("WebDriver is null, cannot take screenshot");
            return null;
        }

        try {
            // Create screenshots directory if it doesn't exist
            String screenshotPath = config.getProperty("screenshot.path", "screenshots/");
            Path directory = Paths.get(screenshotPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
                logger.info("Created screenshots directory: " + directory);
            }

            // Generate filename with timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = testName + "_" + timestamp + ".png";
            String fullPath = screenshotPath + fileName;

            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            
            // Move screenshot to target location
            Path targetPath = Paths.get(fullPath);
            Files.move(screenshot.toPath(), targetPath);

            logger.info("Screenshot saved: " + fullPath);
            return fullPath;

        } catch (IOException e) {
            logger.error("Failed to take screenshot: " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Unexpected error while taking screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Take screenshot with custom filename
     * @param driver WebDriver instance
     * @param fileName custom filename (without extension)
     * @param description description for the screenshot
     * @return path to the saved screenshot file
     */
    public static String takeScreenshot(WebDriver driver, String fileName, String description) {
        if (driver == null) {
            logger.warn("WebDriver is null, cannot take screenshot");
            return null;
        }

        try {
            // Create screenshots directory if it doesn't exist
            String screenshotPath = config.getProperty("screenshot.path", "screenshots/");
            Path directory = Paths.get(screenshotPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
                logger.info("Created screenshots directory: " + directory);
            }

            // Generate filename with timestamp and description
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fullFileName = fileName + "_" + description + "_" + timestamp + ".png";
            String fullPath = screenshotPath + fullFileName;

            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            
            // Move screenshot to target location
            Path targetPath = Paths.get(fullPath);
            Files.move(screenshot.toPath(), targetPath);

            logger.info("Screenshot saved: " + fullPath);
            return fullPath;

        } catch (IOException e) {
            logger.error("Failed to take screenshot: " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error("Unexpected error while taking screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Take screenshot and return as byte array
     * @param driver WebDriver instance
     * @return screenshot as byte array
     */
    public static byte[] takeScreenshotAsBytes(WebDriver driver) {
        if (driver == null) {
            logger.warn("WebDriver is null, cannot take screenshot");
            return new byte[0];
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            logger.debug("Screenshot captured as bytes");
            return screenshot;
        } catch (Exception e) {
            logger.error("Failed to take screenshot as bytes: " + e.getMessage());
            return new byte[0];
        }
    }

    /**
     * Take screenshot and return as base64 string
     * @param driver WebDriver instance
     * @return screenshot as base64 string
     */
    public static String takeScreenshotAsBase64(WebDriver driver) {
        if (driver == null) {
            logger.warn("WebDriver is null, cannot take screenshot");
            return "";
        }

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            String screenshot = ts.getScreenshotAs(OutputType.BASE64);
            logger.debug("Screenshot captured as base64");
            return screenshot;
        } catch (Exception e) {
            logger.error("Failed to take screenshot as base64: " + e.getMessage());
            return "";
        }
    }

    /**
     * Clean up old screenshots (older than specified days)
     * @param daysToKeep number of days to keep screenshots
     */
    public static void cleanupOldScreenshots(int daysToKeep) {
        try {
            String screenshotPath = config.getProperty("screenshot.path", "screenshots/");
            Path directory = Paths.get(screenshotPath);
            
            if (!Files.exists(directory)) {
                logger.info("Screenshots directory does not exist, nothing to clean");
                return;
            }

            LocalDateTime cutoffDate = LocalDateTime.now().minusDays(daysToKeep);
            AtomicInteger deletedCount = new AtomicInteger(0);

            Files.list(directory)
                .filter(path -> path.toString().endsWith(".png"))
                .filter(path -> {
                    try {
                        LocalDateTime fileTime = LocalDateTime.parse(
                            path.getFileName().toString().split("_")[1] + "_" + 
                            path.getFileName().toString().split("_")[2].split("\\.")[0],
                            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
                        );
                        return fileTime.isBefore(cutoffDate);
                    } catch (Exception e) {
                        logger.warn("Could not parse date from filename: " + path.getFileName());
                        return false;
                    }
                })
                .forEach(path -> {
                    try {
                        Files.delete(path);
                        deletedCount.incrementAndGet();
                        logger.debug("Deleted old screenshot: " + path.getFileName());
                    } catch (IOException e) {
                        logger.error("Failed to delete old screenshot: " + path.getFileName());
                    }
                });

            logger.info("Cleanup completed. Deleted " + deletedCount.get() + " old screenshots");

        } catch (Exception e) {
            logger.error("Error during screenshot cleanup: " + e.getMessage());
        }
    }

    /**
     * Get screenshot directory path
     * @return screenshot directory path
     */
    public static String getScreenshotDirectory() {
        return config.getProperty("screenshot.path", "screenshots/");
    }

    /**
     * Check if screenshot capture is enabled
     * @return true if screenshot capture is enabled
     */
    public static boolean isScreenshotEnabled() {
        return config.getBooleanProperty("screenshot.on.failure", true);
    }
} 