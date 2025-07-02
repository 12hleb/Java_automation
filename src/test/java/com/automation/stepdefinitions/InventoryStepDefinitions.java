package com.automation.stepdefinitions;

import com.automation.pages.InventoryPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

/**
 * Step definitions for Inventory feature
 * Implements the Gherkin steps for product inventory scenarios
 */
public class InventoryStepDefinitions {
    
    private static final Logger logger = LogManager.getLogger(InventoryStepDefinitions.class);
    private final TestContext testContext;
    private final InventoryPage inventoryPage;

    public InventoryStepDefinitions(TestContext testContext) {
        this.testContext = testContext;
        this.inventoryPage = testContext.getInventoryPage();
    }

    @Given("I am logged in as {string}")
    public void i_am_logged_in_as(String username) {
        logger.info("Logging in as: {}", username);
        testContext.getLoginPage().login(username, "secret_sauce");
        Assert.assertTrue("User should be logged in and on inventory page", 
            inventoryPage.isInventoryPageDisplayed());
    }

    @Given("I am on the inventory page")
    public void i_am_on_the_inventory_page() {
        logger.info("Verifying user is on inventory page");
        Assert.assertTrue("User should be on inventory page", 
            inventoryPage.isInventoryPageDisplayed());
    }

    @When("I add the first product to cart")
    public void i_add_the_first_product_to_cart() {
        logger.info("Adding first product to cart");
        inventoryPage.addProductToCartByIndex(0);
    }

    @When("I add the second product to cart")
    public void i_add_the_second_product_to_cart() {
        logger.info("Adding second product to cart");
        inventoryPage.addProductToCartByIndex(1);
    }

    @When("I add the third product to cart")
    public void i_add_the_third_product_to_cart() {
        logger.info("Adding third product to cart");
        inventoryPage.addProductToCartByIndex(2);
    }

    @When("I click on the shopping cart icon")
    public void i_click_on_the_shopping_cart_icon() {
        logger.info("Clicking shopping cart icon");
        inventoryPage.clickCartIcon();
    }

    @Then("I should see the inventory title {string}")
    public void i_should_see_the_inventory_title(String expectedTitle) {
        logger.info("Verifying inventory title: {}", expectedTitle);
        Assert.assertEquals("Inventory title should match", expectedTitle, 
            inventoryPage.getInventoryTitle());
    }

    @Then("I should see multiple product items")
    public void i_should_see_multiple_product_items() {
        logger.info("Verifying multiple product items are displayed");
        int itemCount = inventoryPage.getInventoryItemCount();
        Assert.assertTrue("Should have multiple product items", itemCount > 0);
    }

    @Then("each product should have a name, price, and add to cart button")
    public void each_product_should_have_name_price_and_button() {
        logger.info("Verifying product elements");
        int itemCount = inventoryPage.getInventoryItemCount();
        Assert.assertTrue("Should have products", itemCount > 0);
        
        // Verify first product has required elements
        String firstProductName = inventoryPage.getAllProductNames().get(0);
        String firstProductPrice = inventoryPage.getAllProductPrices().get(0);
        String firstProductButton = inventoryPage.getProductButtonTextByIndex(0);
        
        Assert.assertNotNull("Product name should not be null", firstProductName);
        Assert.assertNotNull("Product price should not be null", firstProductPrice);
        Assert.assertNotNull("Product button should not be null", firstProductButton);
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expectedCount) {
        logger.info("Verifying cart badge count: {}", expectedCount);
        int actualCount = inventoryPage.getCartBadgeCount();
        int expected = Integer.parseInt(expectedCount);
        Assert.assertEquals("Cart badge count should match", expected, actualCount);
    }

    @Then("the add to cart button should change to {string}")
    public void the_add_to_cart_button_should_change_to(String expectedButtonText) {
        logger.info("Verifying button text changed to: {}", expectedButtonText);
        String actualButtonText = inventoryPage.getProductButtonTextByIndex(0);
        Assert.assertEquals("Button text should match", expectedButtonText, actualButtonText);
    }

    @Then("all three products should show {string} button")
    public void all_three_products_should_show_button(String expectedButtonText) {
        logger.info("Verifying all three products show button: {}", expectedButtonText);
        for (int i = 0; i < 3; i++) {
            String actualButtonText = inventoryPage.getProductButtonTextByIndex(i);
            Assert.assertEquals("Product " + (i + 1) + " button text should match", 
                expectedButtonText, actualButtonText);
        }
    }
}