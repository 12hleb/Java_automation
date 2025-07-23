package com.automation.utils;

import com.automation.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Utility class for handling various types of waits in Selenium
 * Provides explicit wait methods for better element synchronization
 */
public class WaitUtils {
    private static final Logger logger = LogManager.getLogger(WaitUtils.class);
    private static final ConfigManager config = ConfigManager.getInstance();

    /**
     * Wait for element to be visible
     * @param driver WebDriver instance
     * @param locator element locator
     * @return WebElement if found and visible
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return waitForElementVisible(driver, locator, config.getExplicitWait());
    }

    /**
     * Wait for element to be visible with custom timeout
     * @param driver WebDriver instance
     * @param locator element locator
     * @param timeout timeout in seconds
     * @return WebElement if found and visible
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.debug("Element visible: " + locator);
            return element;
        } catch (TimeoutException e) {
            logger.debug("Element not visible within " + timeout + " seconds: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to be clickable
     * @param driver WebDriver instance
     * @param locator element locator
     * @return WebElement if found and clickable
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return waitForElementClickable(driver, locator, config.getExplicitWait());
    }

    /**
     * Wait for element to be clickable with custom timeout
     * @param driver WebDriver instance
     * @param locator element locator
     * @param timeout timeout in seconds
     * @return WebElement if found and clickable
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            logger.debug("Element clickable: " + locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("Element not clickable within " + timeout + " seconds: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to be present in DOM
     * @param driver WebDriver instance
     * @param locator element locator
     * @return WebElement if found
     */
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return waitForElementPresent(driver, locator, config.getExplicitWait());
    }

    /**
     * Wait for element to be present in DOM with custom timeout
     * @param driver WebDriver instance
     * @param locator element locator
     * @param timeout timeout in seconds
     * @return WebElement if found
     */
    public static WebElement waitForElementPresent(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            logger.debug("Element present: " + locator);
            return element;
        } catch (TimeoutException e) {
            logger.error("Element not present within " + timeout + " seconds: " + locator);
            throw e;
        }
    }

    /**
     * Wait for all elements to be visible
     * @param driver WebDriver instance
     * @param locator element locator
     * @return List of WebElements if found and visible
     */
    public static List<WebElement> waitForAllElementsVisible(WebDriver driver, By locator) {
        return waitForAllElementsVisible(driver, locator, config.getExplicitWait());
    }

    /**
     * Wait for all elements to be visible with custom timeout
     * @param driver WebDriver instance
     * @param locator element locator
     * @param timeout timeout in seconds
     * @return List of WebElements if found and visible
     */
    public static List<WebElement> waitForAllElementsVisible(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            logger.debug("All elements visible: " + locator + " (count: " + elements.size() + ")");
            return elements;
        } catch (TimeoutException e) {
            logger.error("Elements not visible within " + timeout + " seconds: " + locator);
            throw e;
        }
    }

    /**
     * Wait for element to disappear
     * @param driver WebDriver instance
     * @param locator element locator
     * @return true if element disappeared
     */
    public static boolean waitForElementToDisappear(WebDriver driver, By locator) {
        return waitForElementToDisappear(driver, locator, config.getExplicitWait());
    }

    /**
     * Wait for element to disappear with custom timeout
     * @param driver WebDriver instance
     * @param locator element locator
     * @param timeout timeout in seconds
     * @return true if element disappeared
     */
    public static boolean waitForElementToDisappear(WebDriver driver, By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            boolean disappeared = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            logger.debug("Element disappeared: " + locator);
            return disappeared;
        } catch (TimeoutException e) {
            logger.error("Element did not disappear within " + timeout + " seconds: " + locator);
            throw e;
        }
    }

    /**
     * Wait for page title to contain specific text
     * @param driver WebDriver instance
     * @param title text to check in page title
     * @return true if title contains the text
     */
    public static boolean waitForPageTitleContains(WebDriver driver, String title) {
        return waitForPageTitleContains(driver, title, config.getExplicitWait());
    }

    /**
     * Wait for page title to contain specific text with custom timeout
     * @param driver WebDriver instance
     * @param title text to check in page title
     * @param timeout timeout in seconds
     * @return true if title contains the text
     */
    public static boolean waitForPageTitleContains(WebDriver driver, String title, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            boolean titleContains = wait.until(ExpectedConditions.titleContains(title));
            logger.debug("Page title contains: " + title);
            return titleContains;
        } catch (TimeoutException e) {
            logger.error("Page title does not contain '" + title + "' within " + timeout + " seconds");
            throw e;
        }
    }

    /**
     * Wait for URL to contain specific text
     * @param driver WebDriver instance
     * @param url text to check in URL
     * @return true if URL contains the text
     */
    public static boolean waitForUrlContains(WebDriver driver, String url) {
        return waitForUrlContains(driver, url, config.getExplicitWait());
    }

    /**
     * Wait for URL to contain specific text with custom timeout
     * @param driver WebDriver instance
     * @param url text to check in URL
     * @param timeout timeout in seconds
     * @return true if URL contains the text
     */
    public static boolean waitForUrlContains(WebDriver driver, String url, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            boolean urlContains = wait.until(ExpectedConditions.urlContains(url));
            logger.debug("URL contains: " + url);
            return urlContains;
        } catch (TimeoutException e) {
            logger.error("URL does not contain '" + url + "' within " + timeout + " seconds");
            throw e;
        }
    }

    /**
     * Wait for custom condition
     * @param driver WebDriver instance
     * @param condition custom expected condition
     * @param timeout timeout in seconds
     * @param <T> return type of the condition
     * @return result of the condition
     */
    public static <T> T waitForCondition(WebDriver driver, ExpectedCondition<T> condition, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            T result = wait.until(condition);
            logger.debug("Custom condition met");
            return result;
        } catch (TimeoutException e) {
            logger.error("Custom condition not met within " + timeout + " seconds");
            throw e;
        }
    }

    /**
     * Static wait (not recommended, use only when necessary)
     * @param seconds seconds to wait
     */
    public static void staticWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            logger.debug("Static wait completed: " + seconds + " seconds");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Static wait interrupted: " + e.getMessage());
        }
    }

    // Element interaction methods with built-in waits

    /**
     * Wait for element to be clickable and click it
     * @param driver WebDriver instance
     * @param locator element locator
     */
    public static void click(WebDriver driver, By locator) {
        try {
            WebElement element = waitForElementClickable(driver, locator);
            element.click();
            logger.debug("Clicked element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click element " + locator + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible and click it using JavaScript
     * @param driver WebDriver instance
     * @param locator element locator
     */
    public static void clickWithJavaScript(WebDriver driver, By locator) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            logger.debug("Clicked element with JavaScript: " + locator);
        } catch (Exception e) {
            logger.error("Failed to click element with JavaScript " + locator + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible and type text
     * @param driver WebDriver instance
     * @param locator element locator
     * @param text text to type
     */
    public static void type(WebDriver driver, By locator, String text) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            element.clear();
            element.sendKeys(text);
            logger.debug("Typed text '" + text + "' into element: " + locator);
        } catch (Exception e) {
            logger.error("Failed to type text into element " + locator + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible and get its text
     * @param driver WebDriver instance
     * @param locator element locator
     * @return element text
     */
    public static String getText(WebDriver driver, By locator) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            String text = element.getText();
            logger.debug("Got text '" + text + "' from element: " + locator);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text from element " + locator + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible and get its attribute value
     * @param driver WebDriver instance
     * @param locator element locator
     * @param attribute attribute name
     * @return attribute value
     */
    public static String getAttribute(WebDriver driver, By locator, String attribute) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            String value = element.getAttribute(attribute);
            logger.debug("Got attribute '" + attribute + "' = '" + value + "' from element: " + locator);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get attribute from element " + locator + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Wait for element to be visible and check if it's displayed
     * @param driver WebDriver instance
     * @param locator element locator
     * @return true if element is displayed
     */
    public static boolean isElementDisplayed(WebDriver driver, By locator) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            boolean displayed = element.isDisplayed();
            logger.debug("Element displayed: " + locator + " = " + displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Wait for element to be visible and check if it's enabled
     * @param driver WebDriver instance
     * @param locator element locator
     * @return true if element is enabled
     */
    public static boolean isElementEnabled(WebDriver driver, By locator) {
        try {
            WebElement element = waitForElementVisible(driver, locator);
            boolean enabled = element.isEnabled();
            logger.debug("Element enabled: " + locator + " = " + enabled);
            return enabled;
        } catch (Exception e) {
            logger.error("Failed to check if element is enabled " + locator + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Wait for page to load completely
     * @param driver WebDriver instance
     */
    public static void waitForPageLoad(WebDriver driver) {
        try {
            waitForCondition(driver, 
                webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"), 
                config.getPageLoadTimeout());
            logger.debug("Page loaded completely");
        } catch (Exception e) {
            logger.error("Page did not load completely: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Handle Chrome password change popup and other browser dialogs
     * @param driver WebDriver instance
     */
    public static void handleChromePopups(WebDriver driver) {
        try {
            // Handle Chrome password change popup
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                logger.info("Handling alert: " + alertText);
                
                if (alertText.contains("Change your password") || 
                    alertText.contains("password") || 
                    alertText.contains("OK") ||
                    alertText.contains("Continue")) {
                    alert.accept();
                    logger.info("Accepted Chrome password popup");
                } else {
                    alert.dismiss();
                    logger.info("Dismissed unexpected alert: " + alertText);
                }
            } catch (NoAlertPresentException e) {
                // No alert present, which is fine
                logger.debug("No alert present to handle");
            }
            
            // Handle any other browser dialogs that might appear
            try {
                // Switch back to default content in case we were in a frame
                driver.switchTo().defaultContent();
            } catch (Exception e) {
                logger.debug("Error switching to default content: " + e.getMessage());
            }
            
        } catch (Exception e) {
            logger.warn("Error handling Chrome popups: " + e.getMessage());
        }
    }
} 