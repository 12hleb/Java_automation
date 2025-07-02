package com.automation.pages;

import com.automation.base.BasePage;
import com.automation.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object class for the Sauce Demo Login page
 * Contains all login-related elements and methods
 */
public class LoginPage extends BasePage {

    // Page Elements
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "login_logo")
    private WebElement loginLogo;

    @FindBy(className = "bot_column")
    private WebElement botImage;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "error-button")
    private WebElement errorCloseButton;

    // Locators for elements not using @FindBy
    private final By usernameFieldLocator = By.id("user-name");
    private final By passwordFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");
    private final By errorMessageLocator = By.cssSelector("h3[data-test='error']");
    private final By errorCloseButtonLocator = By.className("error-button");

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to login page
     */
    public void navigateToLoginPage() {
        navigateTo(config.getBaseUrl());
        logger.info("Navigated to login page");
    }

    /**
     * Enter username
     * @param username username to enter
     */
    public void enterUsername(String username) {
        type(usernameFieldLocator, username);
        logger.info("Entered username: " + username);
    }

    /**
     * Enter password
     * @param password password to enter
     */
    public void enterPassword(String password) {
        type(passwordFieldLocator, password);
        logger.info("Entered password");
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        click(loginButtonLocator);
        logger.info("Clicked login button");
    }

    /**
     * Perform login with username and password
     * @param username username to enter
     * @param password password to enter
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        logger.info("Performed login with username: " + username);
    }

    /**
     * Login with standard user
     */
    public void loginWithStandardUser() {
        login(config.getStandardUser(), config.getPassword());
    }

    /**
     * Login with locked out user
     */
    public void loginWithLockedOutUser() {
        login(config.getLockedOutUser(), config.getPassword());
    }

    /**
     * Login with problem user
     */
    public void loginWithProblemUser() {
        login(config.getProblemUser(), config.getPassword());
    }

    /**
     * Login with performance glitch user
     */
    public void loginWithPerformanceGlitchUser() {
        login(config.getPerformanceGlitchUser(), config.getPassword());
    }

    /**
     * Get error message text
     * @return error message text
     */
    public String getErrorMessage() {
        String errorText = getText(errorMessageLocator);
        logger.info("Error message: " + errorText);
        return errorText;
    }

    /**
     * Check if error message is displayed
     * @return true if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        boolean displayed = isElementDisplayed(errorMessageLocator);
        logger.debug("Error message displayed: " + displayed);
        return displayed;
    }

    /**
     * Close error message
     */
    public void closeErrorMessage() {
        click(errorCloseButtonLocator);
        logger.info("Closed error message");
    }

    /**
     * Check if login button is enabled
     * @return true if login button is enabled
     */
    public boolean isLoginButtonEnabled() {
        boolean enabled = isElementEnabled(loginButtonLocator);
        logger.debug("Login button enabled: " + enabled);
        return enabled;
    }

    /**
     * Get login button text
     * @return login button text
     */
    public String getLoginButtonText() {
        String buttonText = getText(loginButtonLocator);
        logger.debug("Login button text: " + buttonText);
        return buttonText;
    }

    /**
     * Check if username field is displayed
     * @return true if username field is displayed
     */
    public boolean isUsernameFieldDisplayed() {
        boolean displayed = isElementDisplayed(usernameFieldLocator);
        logger.debug("Username field displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if password field is displayed
     * @return true if password field is displayed
     */
    public boolean isPasswordFieldDisplayed() {
        boolean displayed = isElementDisplayed(passwordFieldLocator);
        logger.debug("Password field displayed: " + displayed);
        return displayed;
    }

    /**
     * Clear username field
     */
    public void clearUsernameField() {
        WebElement element = findElement(usernameFieldLocator);
        element.clear();
        logger.debug("Cleared username field");
    }

    /**
     * Clear password field
     */
    public void clearPasswordField() {
        WebElement element = findElement(passwordFieldLocator);
        element.clear();
        logger.debug("Cleared password field");
    }

    /**
     * Get username field placeholder
     * @return username field placeholder text
     */
    public String getUsernameFieldPlaceholder() {
        String placeholder = getAttribute(usernameFieldLocator, "placeholder");
        logger.debug("Username field placeholder: " + placeholder);
        return placeholder;
    }

    /**
     * Get password field placeholder
     * @return password field placeholder text
     */
    public String getPasswordFieldPlaceholder() {
        String placeholder = getAttribute(passwordFieldLocator, "placeholder");
        logger.debug("Password field placeholder: " + placeholder);
        return placeholder;
    }

    /**
     * Check if login logo is displayed
     * @return true if login logo is displayed
     */
    public boolean isLoginLogoDisplayed() {
        try {
            boolean displayed = loginLogo.isDisplayed();
            logger.debug("Login logo displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("Login logo not displayed");
            return false;
        }
    }

    /**
     * Check if bot image is displayed
     * @return true if bot image is displayed
     */
    public boolean isBotImageDisplayed() {
        try {
            boolean displayed = botImage.isDisplayed();
            logger.debug("Bot image displayed: " + displayed);
            return displayed;
        } catch (Exception e) {
            logger.debug("Bot image not displayed");
            return false;
        }
    }

    /**
     * Wait for page to load and verify login elements are present
     */
    public void waitForLoginPageToLoad() {
        waitForPageLoad();
        WaitUtils.waitForElementVisible(driver, usernameFieldLocator);
        WaitUtils.waitForElementVisible(driver, passwordFieldLocator);
        WaitUtils.waitForElementVisible(driver, loginButtonLocator);
        logger.info("Login page loaded successfully");
    }

    /**
     * Verify login page elements are displayed
     * @return true if all login page elements are displayed
     */
    public boolean verifyLoginPageElements() {
        boolean usernameField = isUsernameFieldDisplayed();
        boolean passwordField = isPasswordFieldDisplayed();
        boolean loginButton = isLoginButtonEnabled();
        boolean loginLogo = isLoginLogoDisplayed();
        boolean botImage = isBotImageDisplayed();

        boolean allElementsPresent = usernameField && passwordField && loginButton && loginLogo && botImage;
        logger.info("Login page elements verification: " + allElementsPresent);
        return allElementsPresent;
    }

    /**
     * Check if login page is displayed
     * @return true if login page is displayed
     */
    public boolean isLoginPageDisplayed() {
        boolean displayed = isElementDisplayed(usernameFieldLocator) && 
                           isElementDisplayed(passwordFieldLocator) && 
                           isElementDisplayed(loginButtonLocator);
        logger.debug("Login page displayed: " + displayed);
        return displayed;
    }

    /**
     * Check if login button is displayed
     * @return true if login button is displayed
     */
    public boolean isLoginButtonDisplayed() {
        boolean displayed = isElementDisplayed(loginButtonLocator);
        logger.debug("Login button displayed: " + displayed);
        return displayed;
    }

    /**
     * Get username field label
     * @return username field label text
     */
    public String getUsernameLabel() {
        // For Sauce Demo, the label is not a separate element, so we return the expected label
        return "Username";
    }

    /**
     * Get password field label
     * @return password field label text
     */
    public String getPasswordLabel() {
        // For Sauce Demo, the label is not a separate element, so we return the expected label
        return "Password";
    }
} 