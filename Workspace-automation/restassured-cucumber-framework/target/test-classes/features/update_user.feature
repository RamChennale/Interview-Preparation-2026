@Regression @Update
Feature: Update an existing user
  As an API consumer
  I want to update a user's details via PUT /api/users/{id}
  So that the user's record reflects the latest information

  Background:
    Given the user API client is authenticated using "JWT"

  @Smoke
  Scenario: Successfully update user details with valid JWT token
    Given I have updated details name "morpheus" and job "leader" for user id "2"
    When I send a PUT request to update the user
    Then the response status code should be 200
    And the response should contain field "name" with value "morpheus"
    And the response should contain field "job" with value "leader"
    And the response field "updatedAt" should not be null
