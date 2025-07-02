@e2e @complete @journey
Feature: End-to-End User Journey
  As a user
  I want to complete a full shopping experience
  So that I can purchase products from the Sauce Demo website

  Background:
    Given I am on the login page

  @e2e @smoke @critical
  Scenario: Complete purchase journey with single item
    When I login with valid credentials "standard_user"
    And I add the first product to cart
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I enter checkout information "John" "Doe" "12345"
    And I click "Continue" button
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @regression
  Scenario: Complete purchase journey with multiple items
    When I login with valid credentials "standard_user"
    And I add the first product to cart
    And I add the second product to cart
    And I add the third product to cart
    And I click on the shopping cart icon
    And I verify all items are in cart
    And I click "Checkout" button
    And I enter checkout information "Jane" "Smith" "54321"
    And I click "Continue" button
    And I verify order details are correct
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @regression
  Scenario: Browse products and add to cart journey
    When I login with valid credentials "standard_user"
    And I view all products on the inventory page
    And I sort products by "Price (low to high)"
    And I add the cheapest product to cart
    And I sort products by "Name (A to Z)"
    And I add the first alphabetical product to cart
    And I click on the shopping cart icon
    Then I should see exactly 2 items in cart
    And I should see the correct product names
    And I should see the correct product prices

  @e2e @regression
  Scenario: Product detail and purchase journey
    When I login with valid credentials "standard_user"
    And I click on a product name to view details
    And I verify product details are displayed
    And I add the product to cart from detail page
    And I click "Back to products" button
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I enter checkout information "Alice" "Johnson" "98765"
    And I click "Continue" button
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @regression
  Scenario: Cart management and checkout journey
    When I login with valid credentials "standard_user"
    And I add multiple products to cart
    And I click on the shopping cart icon
    And I remove one product from cart
    And I verify the remaining products
    And I click "Continue Shopping" button
    And I add another product to cart
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I enter checkout information "Bob" "Wilson" "11111"
    And I click "Continue" button
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @regression
  Scenario: Navigation and session management journey
    When I login with valid credentials "standard_user"
    And I add products to cart
    And I navigate to the About page
    And I navigate back to inventory
    And I verify cart items are preserved
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I enter checkout information "Charlie" "Brown" "22222"
    And I click "Continue" button
    And I click "Cancel" button
    And I verify I am back on inventory page
    And I verify cart items are still present

  @e2e @regression
  Scenario: App reset and fresh start journey
    When I login with valid credentials "standard_user"
    And I add products to cart
    And I reset the app state from burger menu
    And I verify cart is cleared
    And I add new products to cart
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I enter checkout information "David" "Miller" "33333"
    And I click "Continue" button
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @regression
  Scenario: Logout and re-login journey
    When I login with valid credentials "standard_user"
    And I add products to cart
    And I logout from the application
    And I login again with valid credentials "standard_user"
    And I verify cart is empty after re-login
    And I add new products to cart
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I enter checkout information "Eva" "Davis" "44444"
    And I click "Continue" button
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @negative
  Scenario: Failed login and retry journey
    When I enter invalid credentials "invalid_user" "wrong_password"
    And I click the login button
    And I verify error message is displayed
    And I enter valid credentials "standard_user"
    And I click the login button
    And I verify successful login
    And I add a product to cart
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I enter checkout information "Frank" "Taylor" "55555"
    And I click "Continue" button
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @negative
  Scenario: Checkout validation and correction journey
    When I login with valid credentials "standard_user"
    And I add a product to cart
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I try to proceed with empty fields
    And I verify error message is displayed
    And I enter valid checkout information "Grace" "Anderson" "66666"
    And I click "Continue" button
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @performance
  Scenario: Performance optimized purchase journey
    When I login with valid credentials "standard_user"
    And I verify inventory page loads quickly
    And I add a product to cart
    And I click on the shopping cart icon
    And I verify cart page loads quickly
    And I click "Checkout" button
    And I verify checkout page loads quickly
    And I enter checkout information "Henry" "Clark" "77777"
    And I click "Continue" button
    And I verify overview page loads quickly
    And I click "Finish" button
    And I verify confirmation page loads quickly
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @accessibility
  Scenario: Accessibility compliant purchase journey
    When I login with valid credentials "standard_user"
    And I verify all page elements are accessible
    And I add a product to cart using keyboard navigation
    And I click on the shopping cart icon
    And I verify cart page elements are accessible
    And I click "Checkout" button
    And I verify checkout form elements are accessible
    And I enter checkout information "Iris" "White" "88888"
    And I click "Continue" button
    And I verify overview page elements are accessible
    And I click "Finish" button
    And I verify confirmation page elements are accessible
    Then I should see the order confirmation
    And the cart should be empty

  @e2e @data-driven
  Scenario Outline: Complete purchase with different user types
    When I login with valid credentials "<username>"
    And I add a product to cart
    And I click on the shopping cart icon
    And I click "Checkout" button
    And I enter checkout information "User" "Test" "99999"
    And I click "Continue" button
    And I click "Finish" button
    Then I should see the order confirmation
    And the cart should be empty

    Examples:
      | username         | description           |
      | standard_user    | Standard user         |
      | problem_user     | Problem user          |
      | performance_glitch_user | Performance glitch user | 