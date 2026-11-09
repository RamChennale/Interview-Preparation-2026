@Regression
Feature: OrangeHRM Login Functionality

  @Login
  Scenario: Successful login with valid credentials
    Given user launches the OrangeHRM application
    When user enters valid username and password
    And user clicks on the login button
    Then user should be navigated to the dashboard page

  @InvalidLogin
  Scenario: Unsuccessful login with invalid credentials
    Given user launches the OrangeHRM application
    When user enters username "Admin" and password "wrongpass"
    And user clicks on the login button
    Then user should see an error message "Invalid credentials"

  @Logout
  Scenario: Successful login with valid credentials
    Given user launches the OrangeHRM application
    When user enters valid username and password
    And user clicks on the login button
    Then user should be navigated to the dashboard page
    When On click of Logout button use should be logged out successfully
