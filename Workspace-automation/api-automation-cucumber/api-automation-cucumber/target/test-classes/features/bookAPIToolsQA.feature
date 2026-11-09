@Regression
Feature: I want to get a list of books from the API

  @getBooks
  Scenario: Fetch an existing post by id
    #Given a post with id 1 already exists
    Given the request base URI
    When I send GET request to "/v1/Books"
    Then the status code should display 200
    And the response should have title "Git Pocket Guide"


  @read
  Scenario: Fetch an existing post by id
    Given a post with id 1 already exists
    When I send a GET request to "/posts/1"
    Then the response status code should be 200
    And the response post id should be 1