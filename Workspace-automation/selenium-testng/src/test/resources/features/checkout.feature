@regression
Feature: SauceDemo End to End Checkout
  As a registered SauceDemo user
  I want to log in, add a product to my cart and complete the checkout process
  So that I can successfully purchase an item from the store

  Background: Common login step used by all scenarios in this feature
    #Scenario: Login with valid user credentials successfully - Background should never have the Scenario
    Given I launch the SauceDemo application
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be logged in successfully and land on the products page

  Scenario: Add Sauce Labs Backpack to cart and complete checkout successfully
    When I add "Sauce Labs Backpack" to the cart
    Then the cart icon should show "1" item
    When I click on the cart icon
    Then "Sauce Labs Backpack" should be present in the cart
    When I click on the Checkout button
    And I enter checkout information with first name "Ram", last name "Chennale" and zip code "123456"
    And I click Continue
    And I click Finish
    Then the order confirmation message "Thank you for your order!" should be displayed


# IMPORTANT NOTES
  #A Background only runs for scenarios in the same feature file, and only if it’s written correctly.
  # Your snippet has Scenario: inside the Background, which is invalid. Remove that line.

  #Rules: for COMMON Background Steps

  #The Background must appear before the first Scenario:.
  #It can contain only Given/When/Then/And steps, never a Scenario: line.
  #Cucumber runs it before every scenario in that file, including when you run a single scenario directly.

  #Cucumber has no global Background across feature files. Each feature file needs its own copy.