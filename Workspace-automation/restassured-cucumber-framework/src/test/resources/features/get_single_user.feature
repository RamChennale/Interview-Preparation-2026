@Regression @GetSingle
Feature: Retrieve a single user to fetch a specific user via GET /api/users/{id}


  Background:
    #Given the user API client is authenticated using "API_KEY"

  @GetSingleUser
  Scenario: Successfully retrieve a single user using API Key authentication
    When I send a GET request to fetch user with id "1"
    Then the response status code should be 200
    And the response should contain field "data.id" with value "2"
    And the response field "data.email" should not be null
