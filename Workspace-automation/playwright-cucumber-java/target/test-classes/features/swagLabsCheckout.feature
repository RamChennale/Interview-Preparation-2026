@Regression
Feature: SauceDemo - Login and End to End Checkout
  As a registered SauceDemo user
  I want to log in, add a product to my cart, and complete checkout
  So that I can verify the purchase flow works end to end

  Background: Common login used by every scenario
    Given user launches the SauceDemo application
    When user logs in with username "standard_user" and password "secret_sauce"
    Then user should be navigated to the products page

  @checkout
  Scenario: Add Sauce Labs Backpack to cart and complete checkout successfully
    When user adds "Sauce Labs Backpack" to the cart
    And user clicks on the cart icon
    Then the cart should contain "Sauce Labs Backpack"
    When user clicks on checkout button
    And user enters checkout information "Ram" "Chennale" "123456"
    And user clicks continue button
    And user clicks finish button
    Then user should see the message "Thank you for your order!"
