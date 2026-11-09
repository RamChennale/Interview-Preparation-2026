@Regression
Feature: Posts API
  As an API consumer
  I want to create, read, update and delete posts
  So that I can manage post resources on the JSONPlaceholder service

  Background:
    Given the Posts API base URL is set

  @create
  Scenario: Create a new post
    Given I have a new post with title "foo", body "bar" and userId 1
    When I send a POST request to "/posts"
    Then the response status code should be 201
    And the response post title should be "foo"
    And the response post body should be "bar"
    And the response post userId should be 1

  @read
  Scenario: Fetch an existing post by id
    Given a post with id 1 already exists
    When I send a GET request to "/posts/1"
    Then the response status code should be 200
    And the response post id should be 1

  @update
  Scenario: Update an existing post
    Given I have updated post data with id 1, title "updated title", body "updated body" and userId 1
    When I send a PUT request to "/posts/1"
    Then the response status code should be 200
    And the response post title should be "updated title"
    And the response post body should be "updated body"

  @delete
  Scenario: Delete an existing post
    Given a post with id 1 already exists
    When I send a DELETE request to "/posts/1"
    Then the response status code should be 200

    @patchRequest
    Scenario: update user name by Patch method
      Given a user input id 1, title "update patch"
      When I send a PATCH request to "/posts/1"
      Then the http response code should be 200
      And the response should be received title as "update patch"