@Regression @Create
Feature: create a new user via POST /api/users

  @CRUD
  Scenario: Successfully create a user with valid JWT token
    Given new user with name "Ram" and job "Tester"
    When POST request to create the user
    Then response status code should be 201
