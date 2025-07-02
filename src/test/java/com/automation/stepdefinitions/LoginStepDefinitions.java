package com.automation.stepdefinitions;

import com.automation.pages.LoginPage;
import com.automation.utils.WaitUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

/**
 * Step definitions for Login feature
 * Implements the Gherkin steps for user authentication scenarios
 */
public class LoginStepDefinitions {
    
    private static final Logger logger = LogManager.getLogger(LoginStepDefinitions.class);
    private final TestContext testContext;
    private final LoginPage loginPage;

    public LoginStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
        this.loginPage = testContext.getLoginPage();
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        logger.info("Verifying user is on login page");
        Assert.assertTrue("User should be on login page", loginPage.isLoginPageDisplayed());
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        logger.info("Entering username: {}", username);
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        logger.info("Entering password: {}", password);
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        logger.info("Clicking login button");
        loginPage.clickLoginButton();
        
        // Handle any Chrome popups that might appear after login
        WaitUtils.handleChromePopups(testContext.getDriver());
    }

    @When("I login with valid credentials {string}")
    public void i_login_with_valid_credentials(String username) {
        logger.info("Logging in with valid credentials for user: {}", username);
        loginPage.login(username, "secret_sauce");
    }

    @When("I enter invalid credentials {string} {string}")
    public void i_enter_invalid_credentials(String username, String password) {
        logger.info("Entering invalid credentials - username: {}, password: {}", username, password);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        logger.info("Verifying redirect to inventory page");
        Assert.assertTrue("User should be redirected to inventory page", 
            testContext.getInventoryPage().isInventoryPageDisplayed());
    }





    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        logger.info("Verifying error message is displayed");
        Assert.assertTrue("Error message should be displayed", loginPage.isErrorMessageDisplayed());
    }

    @Then("the error message should contain {string}")
    public void the_error_message_should_contain(String expectedMessage) {
        logger.info("Verifying error message contains: {}", expectedMessage);
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue("Error message should contain expected text", 
            actualMessage.contains(expectedMessage));
    }

    @Then("I should see the username field")
    public void i_should_see_the_username_field() {
        logger.info("Verifying username field is displayed");
        Assert.assertTrue("Username field should be displayed", loginPage.isUsernameFieldDisplayed());
    }

    @Then("I should see the password field")
    public void i_should_see_the_password_field() {
        logger.info("Verifying password field is displayed");
        Assert.assertTrue("Password field should be displayed", loginPage.isPasswordFieldDisplayed());
    }

    @Then("I should see the login button")
    public void i_should_see_the_login_button() {
        logger.info("Verifying login button is displayed");
        Assert.assertTrue("Login button should be displayed", loginPage.isLoginButtonDisplayed());
    }

    @Then("the login button should be enabled")
    public void the_login_button_should_be_enabled() {
        logger.info("Verifying login button is enabled");
        Assert.assertTrue("Login button should be enabled", loginPage.isLoginButtonEnabled());
    }

    @Then("the username field should have placeholder {string}")
    public void the_username_field_should_have_placeholder(String expectedPlaceholder) {
        logger.info("Verifying username field placeholder: {}", expectedPlaceholder);
        Assert.assertEquals("Username field placeholder should match", expectedPlaceholder, 
            loginPage.getUsernameFieldPlaceholder());
    }

    @Then("the password field should have placeholder {string}")
    public void the_password_field_should_have_placeholder(String expectedPlaceholder) {
        logger.info("Verifying password field placeholder: {}", expectedPlaceholder);
        Assert.assertEquals("Password field placeholder should match", expectedPlaceholder, 
            loginPage.getPasswordFieldPlaceholder());
    }

    @Then("I should see the username label {string}")
    public void i_should_see_the_username_label(String expectedLabel) {
        logger.info("Verifying username label: {}", expectedLabel);
        Assert.assertEquals("Username label should match", expectedLabel, 
            loginPage.getUsernameLabel());
    }

    @Then("I should see the password label {string}")
    public void i_should_see_the_password_label(String expectedLabel) {
        logger.info("Verifying password label: {}", expectedLabel);
        Assert.assertEquals("Password label should match", expectedLabel, 
            loginPage.getPasswordLabel());
    }
}