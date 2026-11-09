Feature: OrangeHRM navigation after login

  Background:
    Given I am on the OrangeHRM login page
    When I login with username "Admin" and password "admin123"
    Then I should see the dashboard

  Scenario: Dashboard header is shown after login
    Then the page header should be "Dashboard"

  Scenario Outline: Open a module from the left menu
    When I click the "<menu>" menu
    Then the page header should be "<menu>"

    Examples:
      | menu  |
      | Admin |
      | PIM   |
      | Leave |
