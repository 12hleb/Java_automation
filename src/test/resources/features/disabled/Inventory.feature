@inventory @products @browsing
Feature: Product Inventory Management
  As a logged-in user
  I want to browse and interact with products
  So that I can view product details and add items to my cart

  Background:
    Given I am logged in as "standard_user"
    And I am on the inventory page

  @positive @smoke
  Scenario: View all products on inventory page
    Then I should see the inventory title "Swag Labs"
    And I should see multiple product items
    And each product should have a name, price, and add to cart button

  @positive
  Scenario: Add a single product to cart
    When I add the first product to cart
    Then the product should be added to cart
    And the cart badge should show "1"
    And the add to cart button should change to "Remove"

  @positive
  Scenario: Add multiple products to cart
    When I add the first product to cart
    And I add the second product to cart
    And I add the third product to cart
    Then the cart badge should show "3"
    And all three products should show "Remove" button

  @positive
  Scenario: Remove product from cart
    Given I have added a product to cart
    When I remove the product from cart
    Then the product should be removed from cart
    And the cart badge should show "0"
    And the button should change back to "Add to cart"

  @positive
  Scenario Outline: Sort products by different criteria
    When I sort products by "<sort_option>"
    Then the products should be sorted by "<sort_option>"

    Examples:
      | sort_option    | description        |
      | Name (A to Z)  | Alphabetical order |
      | Name (Z to A)  | Reverse alphabetical|
      | Price (low to high) | Price ascending |
      | Price (high to low) | Price descending|

  @positive
  Scenario: View product details
    When I click on a product name
    Then I should be redirected to the product detail page
    And I should see the product name
    And I should see the product description
    And I should see the product price
    And I should see an "Add to cart" button

  @positive
  Scenario: Add product from detail page
    Given I am viewing a product detail page
    When I add the product to cart from detail page
    Then the product should be added to cart
    And I should see "Remove" button

  @positive
  Scenario: Navigate back to inventory from product detail
    Given I am viewing a product detail page
    When I click the "Back to products" button
    Then I should be redirected to the inventory page

  @positive
  Scenario: Access shopping cart from inventory
    When I click on the shopping cart icon
    Then I should be redirected to the cart page

  @positive
  Scenario: Access burger menu
    When I click on the burger menu button
    Then the burger menu should be displayed
    And I should see "All Items" option
    And I should see "About" option
    And I should see "Logout" option
    And I should see "Reset App State" option

  @positive
  Scenario: Navigate to About page from burger menu
    When I click on the burger menu button
    And I click on "About" option
    Then I should be redirected to the About page

  @positive
  Scenario: Logout from burger menu
    When I click on the burger menu button
    And I click on "Logout" option
    Then I should be redirected to the login page

  @positive
  Scenario: Reset app state from burger menu
    Given I have added products to cart
    When I click on the burger menu button
    And I click on "Reset App State" option
    Then the cart should be cleared
    And the cart badge should show "0"

  @ui @accessibility
  Scenario: Verify inventory page elements
    Then I should see the burger menu button
    And I should see the shopping cart icon
    And I should see the product sort dropdown
    And I should see the inventory title

  @ui @accessibility
  Scenario: Verify product card elements
    Then each product card should have an image
    And each product card should have a title
    And each product card should have a price
    And each product card should have an "Add to cart" button

  @edge @validation
  Scenario: Verify product count
    Then the total number of products should be 6

  @edge @validation
  Scenario: Verify product prices are displayed correctly
    Then all product prices should be in currency format
    And all product prices should be greater than zero

  @performance
  Scenario: Verify page loads within acceptable time
    When I navigate to the inventory page
    Then the page should load within 5 seconds
    And all product images should be loaded 