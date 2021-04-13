Feature: Vehicle contract

  Scenario: Create new vehicle contract and verify UI
    Given the user logs in as "store manager"
    When the user creates a vehicle contract
    Then the new vehicle contract info should match expected on confirmation page

   @db
  Scenario: Create new vehicle contract and verify database
    Given the user logs in as "store manager"
    When the user creates a vehicle contract
    And the user retrieves contract info from database
    Then the new vehicle contract info on UI should match database

  @db
  Scenario Outline: Verify total number of contracts match database
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle Contracts"
    Then the total number of contracts on UI match database

    Examples:
      | userType      |
      | store manager |
      | sales manager |

@wip
  Scenario Outline: Change view per page
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle Contracts"
    And the user change view per page to "<itemNumber>"
    Then the number of contracts displayed should be "<itemNumber>"

    Examples:
      | userType      | itemNumber |
      | store manager | 10         |
      | store manager | 25         |
      | store manager | 50         |
      | store manager | 100        |
      | sales manager | 10         |
      | sales manager | 25         |
      | sales manager | 50         |
      | sales manager | 100        |
