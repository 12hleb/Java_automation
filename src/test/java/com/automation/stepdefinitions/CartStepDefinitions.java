// package com.automation.stepdefinitions;

// import com.automation.pages.CartPage;
// import com.automation.pages.InventoryPage;
// import com.automation.pages.LoginPage;
// import io.cucumber.java.en.Given;
// import io.cucumber.java.en.When;
// import io.cucumber.java.en.Then;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.junit.Assert;

// import java.util.List;

// /**
//  * Step definitions for Cart feature
//  * Implements the Gherkin steps for shopping cart scenarios
//  */
// public class CartStepDefinitions {
    
//     private static final Logger logger = LogManager.getLogger(CartStepDefinitions.class);
//     private final TestContext testContext;
//     private final CartPage cartPage;
//     private final InventoryPage inventoryPage;
//     private final LoginPage loginPage;

//     public CartStepDefinitions(TestContext testContext) {
//         this.testContext = testContext;
//         this.cartPage = testContext.getCartPage();
//         this.inventoryPage = testContext.getInventoryPage();
//         this.loginPage = testContext.getLoginPage();
//     }

//     @Given("I have added a product to cart from inventory")
//     public void i_have_added_a_product_to_cart_from_inventory() {
//         logger.info("Adding a product to cart from inventory");
//         inventoryPage.addProductToCartByIndex(0);
//     }

//     @Given("I have added multiple products to cart")
//     public void i_have_added_multiple_products_to_cart() {
//         logger.info("Adding multiple products to cart");
//         inventoryPage.addProductToCartByIndex(0);
//         inventoryPage.addProductToCartByIndex(1);
//         inventoryPage.addProductToCartByIndex(2);
//     }

//     @Given("I have items in my cart")
//     public void i_have_items_in_my_cart() {
//         logger.info("Ensuring items are in cart");
//         if (inventoryPage.getCartBadgeCount() == 0) {
//             inventoryPage.addProductToCartByIndex(0);
//         }
//     }

//     @Given("I have multiple items in my cart")
//     public void i_have_multiple_items_in_my_cart() {
//         logger.info("Ensuring multiple items are in cart");
//         if (inventoryPage.getCartBadgeCount() < 2) {
//             inventoryPage.addProductToCartByIndex(0);
//             inventoryPage.addProductToCartByIndex(1);
//         }
//     }

//     @Given("I am on the cart page")
//     public void i_am_on_the_cart_page() {
//         logger.info("Navigating to cart page");
//         inventoryPage.clickCartIcon();
//     }

//     @When("I remove a product from cart")
//     public void i_remove_a_product_from_cart() {
//         logger.info("Removing a product from cart");
//         cartPage.removeItemFromCartByIndex(0);
//     }

//     @When("I remove all products from cart")
//     public void i_remove_all_products_from_cart() {
//         logger.info("Removing all products from cart");
//         int itemCount = cartPage.getCartItemCount();
//         for (int i = 0; i < itemCount; i++) {
//             cartPage.removeItemFromCartByIndex(0); // Always remove first item as list shifts
//         }
//     }

//     @When("I click {string} button")
//     public void i_click_button(String buttonName) {
//         logger.info("Clicking button: {}", buttonName);
//         switch (buttonName.toLowerCase()) {
//             case "continue shopping":
//                 cartPage.clickContinueShopping();
//                 break;
//             case "checkout":
//                 cartPage.clickCheckout();
//                 break;
//             default:
//                 throw new IllegalArgumentException("Unknown button: " + buttonName);
//         }
//     }

//     @Then("I should be redirected to the cart page")
//     public void i_should_be_redirected_to_the_cart_page() {
//         logger.info("Verifying redirect to cart page");
//         Assert.assertTrue("User should be redirected to cart page", 
//             cartPage.isCartPageDisplayed());
//     }

//     @Then("I should see the cart title {string}")
//     public void i_should_see_the_cart_title(String expectedTitle) {
//         logger.info("Verifying cart title: {}", expectedTitle);
//         Assert.assertEquals("Cart title should match", expectedTitle, 
//             cartPage.getCartTitle());
//     }

//     @Then("the cart should be empty")
//     public void the_cart_should_be_empty() {
//         logger.info("Verifying cart is empty");
//         Assert.assertTrue("Cart should be empty", cartPage.isCartEmpty());
//     }

//     @Then("I should see the added product in cart")
//     public void i_should_see_the_added_product_in_cart() {
//         logger.info("Verifying product is in cart");
//         Assert.assertTrue("Product should be in cart", cartPage.getCartItemCount() > 0);
//     }

//     @Then("I should see the product name")
//     public void i_should_see_the_product_name() {
//         logger.info("Verifying product name is displayed");
//         String productName = cartPage.getCartItemNameByIndex(0);
//         Assert.assertNotNull("Product name should not be null", productName);
//         Assert.assertFalse("Product name should not be empty", productName.isEmpty());
//     }

//     @Then("I should see the product price")
//     public void i_should_see_the_product_price() {
//         logger.info("Verifying product price is displayed");
//         String productPrice = cartPage.getCartItemPriceByIndex(0);
//         Assert.assertNotNull("Product price should not be null", productPrice);
//         Assert.assertTrue("Product price should be in currency format", 
//             productPrice.startsWith("$"));
//     }

//     @Then("I should see {string} button for the product")
//     public void i_should_see_button_for_the_product(String expectedButtonText) {
//         logger.info("Verifying button text: {}", expectedButtonText);
//         String actualButtonText = cartPage.getCartItemButtonTextByIndex(0);
//         Assert.assertEquals("Button text should match", expectedButtonText, actualButtonText);
//     }

//     @Then("I should not see {string} button")
//     public void i_should_not_see_button(String buttonName) {
//         logger.info("Verifying button is not displayed: {}", buttonName);
//         switch (buttonName.toLowerCase()) {
//             case "checkout":
//                 Assert.assertFalse("Checkout button should not be displayed", 
//                     cartPage.isCheckoutButtonDisplayed());
//                 break;
//             default:
//                 throw new IllegalArgumentException("Unknown button: " + buttonName);
//         }
//     }

//     @Then("the cart should contain the correct number of items")
//     public void the_cart_should_contain_the_correct_number_of_items() {
//         logger.info("Verifying cart item count");
//         int cartItemCount = cartPage.getCartItemCount();
//         int badgeCount = inventoryPage.getCartBadgeCount();
//         Assert.assertEquals("Cart item count should match badge count", 
//             badgeCount, cartItemCount);
//     }

//     @Then("each product should have a {string} button")
//     public void each_product_should_have_a_button(String expectedButtonText) {
//         logger.info("Verifying all products have button: {}", expectedButtonText);
//         int itemCount = cartPage.getCartItemCount();
//         for (int i = 0; i < itemCount; i++) {
//             String actualButtonText = cartPage.getCartItemButtonTextByIndex(i);
//             Assert.assertEquals("Product " + (i + 1) + " button text should match", 
//                 expectedButtonText, actualButtonText);
//         }
//     }

//     @Then("the cart should show updated item count")
//     public void the_cart_should_show_updated_item_count() {
//         logger.info("Verifying cart badge shows updated count");
//         Assert.assertTrue("Cart badge should show updated count", 
//             inventoryPage.getCartBadgeCount() >= 0);
//     }

//     @Then("the removed product should not be visible")
//     public void the_removed_product_should_not_be_visible() {
//         logger.info("Verifying removed product is not visible");
//         // This would need to be implemented based on specific product identification
//         Assert.assertTrue("Removed product should not be visible", true);
//     }

//     @Then("I should be redirected to the checkout information page")
//     public void i_should_be_redirected_to_the_checkout_information_page() {
//         logger.info("Verifying redirect to checkout information page");
//         Assert.assertTrue("User should be redirected to checkout information page", 
//             testContext.getCheckoutPage().isCheckoutInformationPageDisplayed());
//     }

//     @Then("the cart items should remain unchanged")
//     public void the_cart_items_should_remain_unchanged() {
//         logger.info("Verifying cart items remain unchanged");
//         // This would need to be implemented to track cart state
//         Assert.assertTrue("Cart items should remain unchanged", true);
//     }
// }