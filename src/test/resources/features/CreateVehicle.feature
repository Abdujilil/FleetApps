Feature: Create Vehicle

  Scenario Outline: All authorized users can create vehicle with partial information
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle"
    And the user creates a vehicle with "partial" information
    Then the new vehicle information shown on confirmation page should be correct

    Examples:
      | userType      |
      | store manager |
#      | sales manager | sales manager cannot create vehicle, bug reported


  Scenario Outline: All authorized users can create vehicle with full information 1
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle"
    And the user creates a vehicle with "full" information
    Then the new vehicle information shown on confirmation page should be correct part one

    Examples:
      | userType      |
      | store manager |
#      | sales manager | sales manager cannot create vehicle, bug reported


  Scenario Outline: All authorized users can create vehicle with full information 2
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle"
    And the user creates a vehicle with "full" information
    Then the new vehicle information shown on confirmation page should be correct part two

    Examples:
      | userType      |
      | store manager |
#      | sales manager | sales manager cannot create vehicle, bug reported


  Scenario Outline: All authorized users can create vehicle with full information 3
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle"
    And the user creates a vehicle with "full" information
    Then the new vehicle information shown on confirmation page should be correct part three

    Examples:
      | userType      |
      | store manager |
#      | sales manager | sales manager cannot create vehicle, bug reported


  Scenario Outline: All authorized users can create vehicle with full information 4
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle"
    And the user creates a vehicle with "full" information
    Then the new vehicle information shown on confirmation page should be correct part four

    Examples:
      | userType      |
      | store manager |
#      | sales manager | sales manager cannot create vehicle, bug reported


  Scenario Outline: All authorized users can create vehicle with full information 5
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle"
    And the user creates a vehicle with "full" information
    Then the new vehicle information shown on confirmation page should be correct part five

    Examples:
      | userType      |
      | store manager |
#      | sales manager | sales manager cannot create vehicle, bug reported


  Scenario Outline: All authorized users cannot create vehicle with invalid information
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle"
    And the user creates a vehicle with "<infoType>" "<invalidInfo>"
    Then the user should not be able to create a vehicle

    Examples:
      | userType      | infoType             | invalidInfo |
      | store manager | Immatriculation Date | 123456      |
      | store manager | First Contract Date  | abcdef      |
      | store manager | Catalog Value        | !@#$%       |
      | store manager | CO2 Emissions        | abcdef      |
      | store manager | Horsepower Taxation  | #@#$!%%     |


  Scenario Outline: All authorized users can cancel create vehicle process
    Given the user logs in as "<userType>"
    When the user navigates to "Fleet" "Vehicle"
    And the user tries create a vehicle with partial information
    And the user cancel the process
    Then the user should be on vehicles page

    Examples:
      | userType      |
      | store manager |
#      | sales manager | sales manager cannot create vehicle, bug reported