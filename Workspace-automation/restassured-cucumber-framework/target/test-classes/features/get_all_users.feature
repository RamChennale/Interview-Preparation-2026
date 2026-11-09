@Regression @GetAll
Feature: Retrieve all users
  As an API consumer
  I want to fetch the list of users via GET /api/users
  So that I can view all registered users

  Background:
    #Given the user API client is authenticated using "JWT"

  @Smoke
  Scenario: Successfully retrieve paginated list of users
    When I send a GET request to fetch users on page 1
    Then the response status code should be 200
    And the response should contain a list of users
