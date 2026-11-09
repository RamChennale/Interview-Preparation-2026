@Regression @Create
Feature: Create a new user
  As an API consumer
  I want to create a new user via POST /api/users
  So that the user is registered in the system

  Background:
    Given the user API client is authenticated using "JWT"

  @Smoke
  Scenario: Successfully create a user with valid JWT token
    Given I have a new user with name "Ram" and job "Tester"
    When I send a POST request to create the user
    Then the response status code should be 201
    And the response should contain field "name" with value "Ram"
    And the response should contain field "job" with value "Tester"
    And the response field "id" should not be null

  Scenario Outline: Create multiple users with different roles
    Given I have a new user with name "<name>" and job "<job>"
    When I send a POST request to create the user
    Then the response status code should be 201
    And the response should contain field "name" with value "<name>"

    Examples:
      | name    | job       |
      | Sita    | Developer |
      | Lakshman| QA Lead   |
      | Hanuman | DevOps    |
