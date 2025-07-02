@checkout @purchase @order
Feature: Checkout Process
  As a logged-in user with items in cart
  I want to complete the checkout process
  So that I can purchase the selected items

  Background:
    Given I am logged in as "standard_user"
    And I have items in my cart
    And I am on the cart page

  @positive @smoke
  Scenario: Complete checkout with valid information
    When I click "Checkout" button
    And I enter first name "John"
    And I enter last name "Doe"
    And I enter postal code "12345"
    And I click "Continue" button
    Then I should see the checkout overview page
    And I should see the payment information
    And I should see the shipping information
    And I should see the item total
    And I should see the tax amount
    And I should see the total amount
    When I click "Finish" button
    Then I should see the order confirmation page
    And I should see "Thank you for your order!" message
    And I should see the order completion message

  @positive
  Scenario Outline: Checkout with different valid postal codes
    When I click "Checkout" button
    And I enter first name "Jane"
    And I enter last name "Smith"
    And I enter postal code "<postal_code>"
    And I click "Continue" button
    Then I should see the checkout overview page

    Examples:
      | postal_code | description     |
      | 12345       | Standard format |
      | A1B2C3      | Alphanumeric    |
      | 12345-6789  | Extended format |
      | 123         | Short format    |

  @positive
  Scenario: Review order details before completion
    Given I am on the checkout overview page
    Then I should see all items from my cart
    And I should see the correct item quantities
    And I should see the correct item prices
    And I should see the subtotal calculation
    And I should see the tax calculation
    And I should see the total calculation

  @positive
  Scenario: Cancel checkout from information page
    Given I am on the checkout information page
    When I click "Cancel" button
    Then I should be redirected to the inventory page
    And my cart items should remain unchanged

  @positive
  Scenario: Cancel checkout from overview page
    Given I am on the checkout overview page
    When I click "Cancel" button
    Then I should be redirected to the inventory page
    And my cart items should remain unchanged

  @positive
  Scenario: Go back to cart from checkout information
    Given I am on the checkout information page
    When I click "Cancel" button
    Then I should be redirected to the cart page

  @positive
  Scenario: Verify cart is cleared after successful order
    Given I have completed a successful order
    When I navigate to the inventory page
    Then the cart should be empty
    And the cart badge should show "0"

  @negative
  Scenario: Cannot proceed with empty first name
    When I click "Checkout" button
    And I enter last name "Doe"
    And I enter postal code "12345"
    And I click "Continue" button
    Then I should see an error message
    And the error message should contain "First Name is required"

  @negative
  Scenario: Cannot proceed with empty last name
    When I click "Checkout" button
    And I enter first name "John"
    And I enter postal code "12345"
    And I click "Continue" button
    Then I should see an error message
    And the error message should contain "Last Name is required"

  @negative
  Scenario: Cannot proceed with empty postal code
    When I click "Checkout" button
    And I enter first name "John"
    And I enter last name "Doe"
    And I click "Continue" button
    Then I should see an error message
    And the error message should contain "Postal Code is required"

  @negative
  Scenario: Cannot proceed with all empty fields
    When I click "Checkout" button
    And I click "Continue" button
    Then I should see an error message
    And the error message should contain "First Name is required"

  @edge @validation
  Scenario: Checkout with special characters in name
    When I click "Checkout" button
    And I enter first name "José"
    And I enter last name "O'Connor"
    And I enter postal code "12345"
    And I click "Continue" button
    Then I should see the checkout overview page

  @edge @validation
  Scenario: Checkout with very long names
    When I click "Checkout" button
    And I enter first name "VeryLongFirstNameThatExceedsNormalLengthLimits"
    And I enter last name "VeryLongLastNameThatExceedsNormalLengthLimits"
    And I enter postal code "12345"
    And I click "Continue" button
    Then I should see the checkout overview page

  @edge @validation
  Scenario: Checkout with numbers in name fields
    When I click "Checkout" button
    And I enter first name "John123"
    And I enter last name "Doe456"
    And I enter postal code "12345"
    And I click "Continue" button
    Then I should see the checkout overview page

  @ui @accessibility
  Scenario: Verify checkout information page elements
    Given I am on the checkout information page
    Then I should see the page title "Checkout: Your Information"
    And I should see the first name field
    And I should see the last name field
    And I should see the postal code field
    And I should see "Continue" button
    And I should see "Cancel" button

  @ui @accessibility
  Scenario: Verify checkout overview page elements
    Given I am on the checkout overview page
    Then I should see the page title "Checkout: Overview"
    And I should see the payment information section
    And I should see the shipping information section
    And I should see the item total
    And I should see the tax amount
    And I should see the total amount
    And I should see "Finish" button
    And I should see "Cancel" button

  @ui @accessibility
  Scenario: Verify order confirmation page elements
    Given I am on the order confirmation page
    Then I should see "Thank you for your order!" message
    And I should see the order completion message
    And I should see "Back Home" button

  @ui @accessibility
  Scenario: Verify form field placeholders
    Given I am on the checkout information page
    Then the first name field should have placeholder "First Name"
    And the last name field should have placeholder "Last Name"
    And the postal code field should have placeholder "Zip/Postal Code"

  @edge @validation
  Scenario: Verify tax calculation accuracy
    Given I am on the checkout overview page
    Then the tax amount should be calculated correctly
    And the total amount should be the sum of item total and tax

  @edge @validation
  Scenario: Verify multiple item checkout
    Given I have multiple items in my cart
    When I complete the checkout process
    Then all items should be included in the order
    And the total should reflect all items

  @performance
  Scenario: Checkout pages load quickly
    When I click "Checkout" button
    Then the checkout information page should load within 3 seconds
    When I complete the information and click "Continue"
    Then the checkout overview page should load within 3 seconds
    When I click "Finish"
    Then the order confirmation page should load within 3 seconds

  @positive
  Scenario: Navigate back home after order completion
    Given I am on the order confirmation page
    When I click "Back Home" button
    Then I should be redirected to the inventory page
    And the cart should be empty 