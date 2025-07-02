package com.automation.stepdefinitions;

import com.automation.config.ConfigManager;
import com.automation.drivers.WebDriverFactory;
import com.automation.pages.*;
import org.openqa.selenium.WebDriver;

/**
 * TestContext class to manage shared test data and WebDriver instance
 * across step definitions and test scenarios.
 */
public class TestContext {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private ConfigManager configManager;

    public TestContext() {
        this.configManager = ConfigManager.getInstance();
    }

    /**
     * Initialize WebDriver and Page Objects
     */
    public void initializeDriver() {
        if (driver == null) {
            this.driver = WebDriverFactory.createDriver();
            initializePages();
        }
    }

    /**
     * Initialize all page objects
     */
    private void initializePages() {
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    /**
     * Get WebDriver instance
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Get LoginPage instance
     */
    public LoginPage getLoginPage() {
        return loginPage;
    }

    /**
     * Get InventoryPage instance
     */
    public InventoryPage getInventoryPage() {
        return inventoryPage;
    }

    /**
     * Get CartPage instance
     */
    public CartPage getCartPage() {
        return cartPage;
    }

    /**
     * Get CheckoutPage instance
     */
    public CheckoutPage getCheckoutPage() {
        return checkoutPage;
    }

    /**
     * Get ConfigManager instance
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * Navigate to the application URL
     */
    public void navigateToApplication() {
        String baseUrl = configManager.getProperty("base.url");
        driver.get(baseUrl);
    }

    /**
     * Clean up resources
     */
    public void cleanup() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}