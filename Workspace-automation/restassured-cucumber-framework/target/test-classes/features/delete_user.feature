@Regression @Delete
Feature: Delete a user
  As an API consumer
  I want to delete a user via DELETE /api/users/{id}
  So that the user record is removed from the system

  Background:
    Given the user API client is authenticated using "BASIC"

  @Smoke
  Scenario: Successfully delete a user using Basic authentication
    When I send a DELETE request for user with id "2"
    Then the response status code should be 204
