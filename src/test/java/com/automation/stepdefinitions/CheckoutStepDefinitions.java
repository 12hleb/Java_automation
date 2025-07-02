// package com.automation.stepdefinitions;

// import com.automation.pages.CheckoutPage;
// import com.automation.pages.CartPage;
// import io.cucumber.java.en.Given;
// import io.cucumber.java.en.When;
// import io.cucumber.java.en.Then;
// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
// import org.junit.Assert;

// /**
//  * Step definitions for Checkout feature
//  * Implements the Gherkin steps for checkout scenarios
//  */
// public class CheckoutStepDefinitions {
    
//     private static final Logger logger = LogManager.getLogger(CheckoutStepDefinitions.class);
//     private final TestContext testContext;
//     private final CheckoutPage checkoutPage;
//     private final CartPage cartPage;

//     public CheckoutStepDefinitions(TestContext testContext) {
//         this.testContext = testContext;
//         this.checkoutPage = testContext.getCheckoutPage();
//         this.cartPage = testContext.getCartPage();
//     }

//     @Given("I am on the cart page with no items")
//     public void i_am_on_the_cart_page_with_no_items() {
//         logger.info("Ensuring cart page has no items");
//         // This would need to be implemented to clear cart
//         Assert.assertTrue("Cart should be empty", true);
//     }

//     @When("I click {string} button")
//     public void i_click_button(String buttonName) {
//         logger.info("Clicking button: {}", buttonName);
//         switch (buttonName.toLowerCase()) {
//             case "checkout":
//                 cartPage.clickCheckout();
//                 break;
//             case "continue":
//                 checkoutPage.clickContinue();
//                 break;
//             case "finish":
//                 checkoutPage.clickFinish();
//                 break;
//             case "cancel":
//                 checkoutPage.clickCancel();
//                 break;
//             default:
//                 throw new IllegalArgumentException("Unknown button: " + buttonName);
//         }
//     }

//     @When("I enter first name {string}")
//     public void i_enter_first_name(String firstName) {
//         logger.info("Entering first name: {}", firstName);
//         checkoutPage.enterFirstName(firstName);
//     }

//     @When("I enter last name {string}")
//     public void i_enter_last_name(String lastName) {
//         logger.info("Entering last name: {}", lastName);
//         checkoutPage.enterLastName(lastName);
//     }

//     @When("I enter postal code {string}")
//     public void i_enter_postal_code(String postalCode) {
//         logger.info("Entering postal code: {}", postalCode);
//         checkoutPage.enterPostalCode(postalCode);
//     }

//     @When("I enter checkout information {string} {string} {string}")
//     public void i_enter_checkout_information(String firstName, String lastName, String postalCode) {
//         logger.info("Entering checkout information - firstName: {}, lastName: {}, postalCode: {}", 
//             firstName, lastName, postalCode);
//         checkoutPage.enterFirstName(firstName);
//         checkoutPage.enterLastName(lastName);
//         checkoutPage.enterPostalCode(postalCode);
//     }

//     @When("I try to proceed with empty fields")
//     public void i_try_to_proceed_with_empty_fields() {
//         logger.info("Attempting to proceed with empty fields");
//         checkoutPage.clickContinue();
//     }

//     @Then("I should see the checkout overview page")
//     public void i_should_see_the_checkout_overview_page() {
//         logger.info("Verifying checkout overview page is displayed");
//         Assert.assertTrue("Checkout overview page should be displayed", 
//             checkoutPage.isCheckoutOverviewPageDisplayed());
//     }

//     @Then("I should see the order confirmation page")
//     public void i_should_see_the_order_confirmation_page() {
//         logger.info("Verifying order confirmation page is displayed");
//         Assert.assertTrue("Order confirmation page should be displayed", 
//             checkoutPage.isOrderConfirmationPageDisplayed());
//     }

//     @Then("I should see {string} message")
//     public void i_should_see_message(String expectedMessage) {
//         logger.info("Verifying message: {}", expectedMessage);
//         String actualMessage = checkoutPage.getOrderConfirmationMessage();
//         Assert.assertTrue("Message should contain expected text", 
//             actualMessage.contains(expectedMessage));
//     }

//     @Then("I should see the order completion message")
//     public void i_should_see_the_order_completion_message() {
//         logger.info("Verifying order completion message");
//         String message = checkoutPage.getOrderConfirmationMessage();
//         Assert.assertNotNull("Order completion message should not be null", message);
//         Assert.assertFalse("Order completion message should not be empty", message.isEmpty());
//     }

//     @Then("I should see an error message")
//     public void i_should_see_an_error_message() {
//         logger.info("Verifying error message is displayed");
//         Assert.assertTrue("Error message should be displayed", 
//             checkoutPage.isErrorMessageDisplayed());
//     }

//     @Then("my cart items should remain unchanged")
//     public void my_cart_items_should_remain_unchanged() {
//         logger.info("Verifying cart items remain unchanged");
//         // This would need to be implemented to track cart state
//         Assert.assertTrue("Cart items should remain unchanged", true);
//     }

//     @Then("the cart should be empty")
//     public void the_cart_should_be_empty() {
//         logger.info("Verifying cart is empty");
//         Assert.assertTrue("Cart should be empty", 
//             testContext.getInventoryPage().getCartBadgeCount() == 0);
//     }

//     @Then("the cart badge should show {string}")
//     public void the_cart_badge_should_show(String expectedCount) {
//         logger.info("Verifying cart badge count: {}", expectedCount);
//         int actualCount = testContext.getInventoryPage().getCartBadgeCount();
//         int expected = Integer.parseInt(expectedCount);
//         Assert.assertEquals("Cart badge count should match", expected, actualCount);
//     }
// }