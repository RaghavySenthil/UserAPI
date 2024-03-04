#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: UserAPI
Background: User has valid authorization
  @tag1-getalluser
  Scenario: Get all the Users with expected response body
    Given User creates GET Request for the API endpoint to get all Users
    When  User sends GET request
    Then  Response status code should be 200 OK and response body contains all the users
   @tag2-getinvalidendpoint
  Scenario: Check User able to get all the Users with Valid URL and invalid endpoint
    Given User sets valid URL and invalid endpoint
    When  User sends GET Invalid request
    Then  Response status code should be 404 Not Found and response body contains error message 
  @tag3-post
  Scenario Outline: Validate that user is able to create a new user with valid endpoint and new values for all the fields
    Given User creates POST Request "<Sheetname>" and <rownumber> for the API endpoint with valid credentials
    When User sends HTTPS Request and  request Body with mandatory fields
    Then New user must be created with the Response code: 201

    Examples: 
      | Sheetname | rownumber |
      | ValidData |         0 |
   @tag4-getbyuserid
    Scenario: Check if user is able to get user details by valid user ID
    Given User sets GET request for user API with the valid given base URL and valid endpoint
    When  User sends GET request with valid user ID 
    Then  The response status code should be 200 OK and the response body should contain the user that we searched for
   
  @tag5-getbyusername
  Scenario: Check if user is able to get user details by valid user FirstName
    Given User sets GET request using correct URL endpoint
    When  Get request is made using valid user FirstName
    Then  The response status code should be 200 OK and the response body should contain the user details that we searched for
  
  @tag6-put
   Scenario Outline: Update User Last Name
    Given User creates PUT Request using correct URL endpoint
    When  User sends https request with body containing only mandatory fields-First Name,Updated Last Name,Contact Name,Email Id
    Then  User gets 200:OK status code and response body with updated Last name
    
    Examples: 
      | Sheetname     | rownumber |
      | Put-ValidData |         0 |
    
  @tag7-deletebyuserid
   Scenario: Check if user is able to delete user by valid user ID
    Given User creates DELETE Request for user API with valid endpoint 
    When  User sends DELETE request with valid user ID 
    Then  The response status code should be 200 OK  and the response body should contain the message as User is deleted successfully!
    
   @tag8-deletebyusername
   Scenario: Check if user is able to delete user by valid user Name
    Given User creates DELETE Request for user API with valid endpoint name 
    When  User sends DELETE request with valid user Name
    Then  The response status code should be 200 OK  and the response body should contain the message as User is deleted usersuccessfully!  
 
     @tag9-postInvalid
  Scenario Outline: Validate that user is able to create a new user with valid endpoint and Invalid values for Unique fields
    Given User creates POST Request "<Sheetname>" and <rownumber> for the API endpoint with valid credentials
    When User sends HTTPS Request and  request Body with mandatory fields
    Then New user must be created with the Response code: 400

    Examples: 
      | Sheetname   | rownumber |
      | InValidData |         0 |