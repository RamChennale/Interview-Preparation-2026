@Regression
Feature: OrangeHRM Admin - User Management

  # This Background runs before EVERY Scenario below.
  Background:
    Given user launches the OrangeHRM application
    When user enters valid username and password
    And user clicks on the login button
    Then user should be navigated to the dashboard page

  @smoke @createUser
  Scenario: Admin creates a new system user
    Given admin navigates to Admin module
    When admin clicks on Add button
    And admin selects user role "ESS"
    And admin enters employee name "A" and selects employee "A8DCo 4Ys 010Z"
    And admin selects status "Enabled"
    And admin enters auto generated username and password
    And admin clicks Save button
    Then new user should be added successfully

  @searchUser
  Scenario: Admin searches for an existing user
    Given admin navigates to Admin module
    When admin searches for username "Admin"
    Then search results should contain "Admin"
