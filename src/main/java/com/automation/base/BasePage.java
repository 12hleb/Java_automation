package com.automation.base;

import com.automation.config.ConfigManager;
import com.automation.utils.ScreenshotUtils;
import com.automation.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Base Page class that provides common functionality for all page objects
 * Implements Page Object Model pattern with common element interactions
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected Logger logger;
    protected ConfigManager config;
    protected Actions actions;

    /**
     * Constructor to initialize page object
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.logger = LogManager.getLogger(this.getClass());
        this.config = ConfigManager.getInstance();
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Navigate to page URL
     * @param url URL to navigate to
     */
    public void navigateTo(String url) {
        try {
            driver.get(url);
            logger.info("Navigated to: " + url);
        } catch (Exception e) {
            logger.error("Failed to navigate to " + url + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get current page title
     * @return page title
     */
    public String getPageTitle() {
        try {
            String title = driver.getTitle();
            logger.debug("Page title: " + title);
            return title;
        } catch (Exception e) {
            logger.error("Failed to get page title: " + e.getMessage());
            return "";
        }
    }

    /**
     * Get current page URL
     * @return current URL
     */
    public String getCurrentUrl() {
        try {
            String url = driver.getCurrentUrl();
            logger.debug("Current URL: " + url);
            return url;
        } catch (Exception e) {
            logger.error("Failed to get current URL: " + e.getMessage());
            return "";
        }
    }

    /**
     * Wait for element to be visible and click it
     * @param locator element locator
     */
    public void click(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementClickable(driver, locator);
            element.click();
            logger.debug("Clicked element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("click_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and click it using JavaScript
     * @param locator element locator
     */
    public void clickWithJavaScript(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            logger.debug("Clicked element with JavaScript: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click element with JavaScript " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("javascript_click_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and type text
     * @param locator element locator
     * @param text text to type
     */
    public void type(By locator, String text) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            element.clear();
            element.sendKeys(text);
            logger.debug("Typed text '" + text + "' into element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to type text into element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("type_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and get its text
     * @param locator element locator
     * @return element text
     */
    public String getText(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            String text = element.getText();
            logger.debug("Got text '" + text + "' from element: " + locator);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("get_text_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and get its attribute value
     * @param locator element locator
     * @param attribute attribute name
     * @return attribute value
     */
    public String getAttribute(By locator, String attribute) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            String value = element.getAttribute(attribute);
            logger.debug("Got attribute '" + attribute + "' = '" + value + "' from element: " + locator);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get attribute from element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("get_attribute_failure");
            throw e;
        }
    }

    /**
     * Check if element is displayed
     * @param locator element locator
     * @return true if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            boolean displayed = element.isDisplayed();
            logger.debug("Element displayed: " + locator + " = " + displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Check if element is enabled
     * @param locator element locator
     * @return true if element is enabled
     */
    public boolean isElementEnabled(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            boolean enabled = element.isEnabled();
            logger.debug("Element enabled: " + locator + " = " + enabled);
            return enabled;
        } catch (Exception e) {
            logger.error("Failed to check if element is enabled " + locator + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Wait for element to be visible and select option by visible text
     * @param locator select element locator
     * @param visibleText visible text of option to select
     */
    public void selectByVisibleText(By locator, String visibleText) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            logger.debug("Selected option '" + visibleText + "' from element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to select option from element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("select_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and select option by value
     * @param locator select element locator
     * @param value value of option to select
     */
    public void selectByValue(By locator, String value) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            Select select = new Select(element);
            select.selectByValue(value);
            logger.debug("Selected option with value '" + value + "' from element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to select option from element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("select_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and select option by index
     * @param locator select element locator
     * @param index index of option to select
     */
    public void selectByIndex(By locator, int index) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            Select select = new Select(element);
            select.selectByIndex(index);
            logger.debug("Selected option at index " + index + " from element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to select option from element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("select_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and hover over it
     * @param locator element locator
     */
    public void hoverOver(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            actions.moveToElement(element).perform();
            logger.debug("Hovered over element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to hover over element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("hover_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and perform double click
     * @param locator element locator
     */
    public void doubleClick(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            actions.doubleClick(element).perform();
            logger.debug("Double clicked element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to double click element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("double_click_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and perform right click
     * @param locator element locator
     */
    public void rightClick(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            actions.contextClick(element).perform();
            logger.debug("Right clicked element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to right click element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("right_click_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and drag and drop
     * @param sourceLocator source element locator
     * @param targetLocator target element locator
     */
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        try {
            WebElement source = WaitUtils.waitForElementVisible(driver, sourceLocator);
            WebElement target = WaitUtils.waitForElementVisible(driver, targetLocator);
            actions.dragAndDrop(source, target).perform();
            logger.debug("Dragged element " + sourceLocator + " to " + targetLocator);
        } catch (Exception e) {
            logger.error("Failed to drag and drop element: " + e.getMessage());
            takeScreenshotOnFailure("drag_drop_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and scroll to it
     * @param locator element locator
     */
    public void scrollToElement(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.debug("Scrolled to element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to scroll to element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("scroll_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and get all elements
     * @param locator element locator
     * @return list of elements
     */
    public List<WebElement> findElements(By locator) {
        try {
            List<WebElement> elements = WaitUtils.waitForAllElementsVisible(driver, locator);
            logger.debug("Found " + elements.size() + " elements: " + locator);
            return elements;
        } catch (Exception e) {
            logger.error("Failed to find elements " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("find_elements_failure");
            throw e;
        }
    }

    /**
     * Wait for element to be visible and get single element
     * @param locator element locator
     * @return element
     */
    public WebElement findElement(By locator) {
        try {
            WebElement element = WaitUtils.waitForElementVisible(driver, locator);
            logger.debug("Found element: " + locator);
            return element;
        } catch (Exception e) {
            logger.error("Failed to find element " + locator + ": " + e.getMessage());
            takeScreenshotOnFailure("find_element_failure");
            throw e;
        }
    }

    /**
     * Take screenshot on failure
     * @param description description for the screenshot
     */
    protected void takeScreenshotOnFailure(String description) {
        if (ScreenshotUtils.isScreenshotEnabled()) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, this.getClass().getSimpleName(), description);
            if (screenshotPath != null) {
                logger.info("Screenshot taken on failure: " + screenshotPath);
            }
        }
    }

    /**
     * Wait for page to load completely
     */
    public void waitForPageLoad() {
        try {
            WaitUtils.waitForCondition(driver, 
                webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"), 
                config.getPageLoadTimeout());
            logger.debug("Page loaded completely");
        } catch (Exception e) {
            logger.error("Page did not load completely: " + e.getMessage());
            takeScreenshotOnFailure("page_load_failure");
            throw e;
        }
    }

    /**
     * Refresh the current page
     */
    public void refreshPage() {
        try {
            driver.navigate().refresh();
            waitForPageLoad();
            logger.info("Page refreshed successfully");
        } catch (Exception e) {
            logger.error("Failed to refresh page: " + e.getMessage());
            takeScreenshotOnFailure("refresh_failure");
            throw e;
        }
    }

    /**
     * Navigate back to previous page
     */
    public void navigateBack() {
        try {
            driver.navigate().back();
            waitForPageLoad();
            logger.info("Navigated back successfully");
        } catch (Exception e) {
            logger.error("Failed to navigate back: " + e.getMessage());
            takeScreenshotOnFailure("navigate_back_failure");
            throw e;
        }
    }

    /**
     * Navigate forward to next page
     */
    public void navigateForward() {
        try {
            driver.navigate().forward();
            waitForPageLoad();
            logger.info("Navigated forward successfully");
        } catch (Exception e) {
            logger.error("Failed to navigate forward: " + e.getMessage());
            takeScreenshotOnFailure("navigate_forward_failure");
            throw e;
        }
    }
} 