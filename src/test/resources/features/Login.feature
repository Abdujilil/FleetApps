@login @regression
Feature: Users should be able to login

  Background:
    Given the user is on the login page

  @driver @smoke
  Scenario: Login as a driver
    When the user enters the driver information
    Then the user should be able to login

  @sales_manager @smoke
  Scenario: Login as a sales manager
    When the user enters the sales manager information
    Then the user should be able to login

  @store_manager @smoke
  Scenario: Login as a store manager
    When the user enters the store manager information
    Then the user should be able to login


  Scenario Outline: login with valid credentials
    When the user logs in using "<user>" and "<password>"
    Then the user should be able to login

    Examples:
      | user    | password |
      | min     | ******   |
      | min + 1 | ******   |
      | max - 1 | ******   |
      | max     | ******   |


  Scenario Outline: Login with invalid credentials
    When the user logs in using "<user>" and "<password>"
    Then the user should not be able to login

    Examples:
      | user    | password |
      | min - 1 | ******   |
      | max + 1 | ******   |