@login @authentication
Feature: User Authentication
  As a user
  I want to log into the Sauce Demo website
  So that I can access the inventory and make purchases

  Background:
    Given I am on the login page

  @positive @smoke
  Scenario: Successful login with valid credentials
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page

  @positive @abc
  Scenario Outline: Login with different valid user types
    When I enter username "<username>"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page

    Examples:
      | username         | description           |
      | standard_user    | Standard user         |
      | problem_user     | Problem user          |
      | performance_glitch_user | Performance glitch user |

  @negative
  Scenario: Failed login with invalid username
    When I enter username "invalid_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see an error message
    And the error message should contain "Username and password do not match"

  @negative
  Scenario: Failed login with invalid password
    When I enter username "standard_user"
    And I enter password "wrong_password"
    And I click the login button
    Then I should see an error message
    And the error message should contain "Username and password do not match"

  @negative
  Scenario: Failed login with empty credentials
    When I click the login button
    Then I should see an error message
    And the error message should contain "Username is required"

  @negative
  Scenario: Failed login with locked out user
    When I enter username "locked_out_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see an error message
    And the error message should contain "Sorry, this user has been locked out"

  @edge @validation
  Scenario: Login with special characters in username
    When I enter username "user@test.com"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see an error message
    And the error message should contain "Username and password do not match"

  @edge @validation
  Scenario: Login with very long username
    When I enter username "very_long_username_that_exceeds_normal_length_limits_for_testing_purposes"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see an error message
    And the error message should contain "Username and password do not match"

  @ui @accessibility
  Scenario: Verify login form elements are present
    Then I should see the username field
    And I should see the password field
    And I should see the login button
    And the login button should be enabled

  @ui @accessibility
  Scenario: Verify login form placeholder text
    Then the username field should have placeholder "Username"
    And the password field should have placeholder "Password"

  @ui @accessibility
  Scenario: Verify login form labels
    Then I should see the username label "Username"
    And I should see the password label "Password" 