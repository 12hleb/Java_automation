@cart @shopping @management
Feature: Shopping Cart Management
  As a logged-in user
  I want to manage items in my shopping cart
  So that I can review my selections before checkout

  Background:
    Given I am logged in as "standard_user"
    And I am on the inventory page

  @positive @smoke
  Scenario: View empty cart
    When I click on the shopping cart icon
    Then I should be redirected to the cart page
    And I should see the cart title "Your Cart"
    And the cart should be empty
    And I should see "Continue Shopping" button
    And I should not see "Checkout" button

  @positive
  Scenario: Add item to cart and view in cart
    Given I have added a product to cart from inventory
    When I click on the shopping cart icon
    Then I should be redirected to the cart page
    And I should see the added product in cart
    And I should see the product name
    And I should see the product price
    And I should see "Remove" button for the product
    And I should see "Continue Shopping" button
    And I should see "Checkout" button

  @positive
  Scenario: Add multiple items to cart
    Given I have added multiple products to cart
    When I click on the shopping cart icon
    Then I should see all added products in cart
    And the cart should contain the correct number of items
    And each product should have a "Remove" button

  @positive
  Scenario: Remove item from cart
    Given I have items in my cart
    When I remove a product from cart
    Then the product should be removed from cart
    And the cart should show updated item count
    And the removed product should not be visible

  @positive
  Scenario: Remove all items from cart
    Given I have multiple items in my cart
    When I remove all products from cart
    Then the cart should be empty
    And I should not see "Checkout" button
    And I should see "Continue Shopping" button

  @positive
  Scenario: Continue shopping from cart
    Given I am on the cart page
    When I click "Continue Shopping" button
    Then I should be redirected to the inventory page
    And the cart items should remain unchanged

  @positive
  Scenario: Proceed to checkout from cart
    Given I have items in my cart
    When I click "Checkout" button
    Then I should be redirected to the checkout information page

  @positive
  Scenario: Verify cart badge updates correctly
    Given I start with an empty cart
    When I add a product to cart
    Then the cart badge should show "1"
    When I add another product to cart
    Then the cart badge should show "2"
    When I remove a product from cart
    Then the cart badge should show "1"
    When I remove the remaining product from cart
    Then the cart badge should show "0"

  @positive
  Scenario: Cart persists across page navigation
    Given I have added products to cart
    When I navigate to the About page
    And I navigate back to inventory
    Then the cart should still contain the same items
    And the cart badge should show the correct count

  @positive
  Scenario: Cart clears after logout and login
    Given I have added products to cart
    When I logout from the application
    And I login again as "standard_user"
    Then the cart should be empty
    And the cart badge should show "0"

  @positive
  Scenario: Cart clears after app reset
    Given I have added products to cart
    When I reset the app state from burger menu
    Then the cart should be empty
    And the cart badge should show "0"

  @ui @accessibility
  Scenario: Verify cart page elements
    Given I am on the cart page
    Then I should see the cart title "Your Cart"
    And I should see the burger menu button
    And I should see the shopping cart icon
    And I should see "Continue Shopping" button

  @ui @accessibility
  Scenario: Verify cart item elements
    Given I have items in my cart
    Then each cart item should have a product image
    And each cart item should have a product name
    And each cart item should have a product price
    And each cart item should have a "Remove" button

  @edge @validation
  Scenario: Verify cart with maximum items
    Given I add all available products to cart
    When I click on the shopping cart icon
    Then I should see all 6 products in cart
    And the cart badge should show "6"

  @edge @validation
  Scenario: Verify cart item count accuracy
    Given I have added specific products to cart
    When I click on the shopping cart icon
    Then the number of items displayed should match the cart badge count

  @edge @validation
  Scenario: Verify cart total calculation
    Given I have added products with known prices to cart
    When I click on the shopping cart icon
    Then the cart should display the correct total price

  @negative
  Scenario: Cannot checkout with empty cart
    Given I am on the cart page with no items
    Then I should not see "Checkout" button

  @negative
  Scenario: Cannot remove item from empty cart
    Given I am on the cart page with no items
    Then I should not see any "Remove" buttons

  @performance
  Scenario: Cart page loads quickly with items
    Given I have multiple items in my cart
    When I click on the shopping cart icon
    Then the cart page should load within 3 seconds
    And all product images should be displayed 