package com.automation.hooks;

import com.automation.stepdefinitions.TestContext;
import com.automation.utils.ScreenshotUtils;
import com.automation.utils.WaitUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TestHooks class for Cucumber setup and teardown operations
 * Manages test lifecycle and resource cleanup
 */
public class TestHooks {
    
    private static final Logger logger = LogManager.getLogger(TestHooks.class);
    private TestContext testContext;

    public TestHooks(TestContext testContext) {
        this.testContext = testContext;
    }

    /**
     * Setup before each scenario
     */
    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting scenario: {}", scenario.getName());
        
        // Initialize WebDriver and navigate to application
        testContext.initializeDriver();
        testContext.navigateToApplication();
        
        // Handle any Chrome popups that might appear
        WaitUtils.handleChromePopups(testContext.getDriver());
        
        logger.info("WebDriver initialized and navigated to application");
    }

    /**
     * Cleanup after each scenario
     */
    @After
    public void tearDown(Scenario scenario) {
        logger.info("Finishing scenario: {}", scenario.getName());
        
        // Log scenario status
        if (scenario.isFailed()) {
            logger.error("Scenario '{}' failed", scenario.getName());
            
            // Capture screenshot for failed scenarios
            try {
                String screenshotPath = ScreenshotUtils.takeScreenshot(testContext.getDriver(), scenario.getName());
                logger.info("Screenshot captured for failed scenario: {}", screenshotPath);
                
                // Log current URL and page title for debugging
                String currentUrl = testContext.getDriver().getCurrentUrl();
                String pageTitle = testContext.getDriver().getTitle();
                logger.error("Failed scenario details - URL: {}, Title: {}", currentUrl, pageTitle);
                
            } catch (Exception e) {
                logger.error("Failed to capture screenshot: {}", e.getMessage());
            }
        } else {
            logger.info("Scenario '{}' passed", scenario.getName());
        }
        
        // Clean up WebDriver
        testContext.cleanup();
        
        logger.info("Test cleanup completed");
    }
}