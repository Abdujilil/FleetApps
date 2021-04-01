@navigation @regression
Feature: All users should be able to navigate to all options in Fleet module

  Scenario Outline: Users should see same options under Fleet module
    Given the user logs in as "<userType>"
    When the user hovers over "Fleet" module
    Then the user should see following options
      | Vehicles              |
      | Vehicle Odometer      |
      | Vehicle Costs         |
      | Vehicle Contracts     |
      | Vehicles Fuel Logs    |
      | Vehicle Services Logs |
      | Vehicles Model        |

    Examples:
      | userType      |
      | driver        |
      | store manager |
      | sales manager |

  Scenario Outline: Driver accessible options
    Given the user logs in as "driver"
    When the user navigates to "Fleet" "<option>"
    Then the title of the page should contain "<title>"

    Examples:
      | option                | title                 |
      | Vehicles              | Car                   |
      | Vehicle Odometer      | Vehicle Odometer      |
      | Vehicle Costs         | Vehicle Costs         |
      | Vehicles Fuel Logs    | Vehicle Fuel Logs     |
      | Vehicle Services Logs | Vehicle Services Logs |
      | Vehicles Model        | Vehicles Model        |

  @smoke
  Scenario: Driver restricted option
    Given the user logs in as "driver"
    When the user navigates to "Fleet" "Vehicle Contracts"
    Then the user should see an error message

  Scenario Outline: Store manager accessible options
    Given the user logs in as "store manager"
    When the user navigates to "Fleet" "<option>"
    Then the title of the page should contain "<title>"

    Examples:
      | option            | title            |
      | Vehicles          | Car              |
      | Vehicle Costs     | Vehicle Costs    |
      | Vehicle Contracts | Vehicle Contract |
      | Vehicles Model    | Vehicles Model   |

  @smoke
  Scenario Outline: Store manager restricted options
    Given the user logs in as "store manager"
    When the user navigates to "Fleet" "<option>"
    Then the user should see an error message

    Examples:
      | option                |
      | Vehicle Odometer      |
      | Vehicles Fuel Logs    |
      | Vehicle Services Logs |

  Scenario Outline: Sales manager accessible options
    Given the user logs in as "sales manager"
    When the user navigates to "Fleet" "<option>"
    Then the title of the page should contain "<title>"

    Examples:
      | option            | title            |
      | Vehicles          | Car              |
      | Vehicle Costs     | Vehicle Costs    |
      | Vehicle Contracts | Vehicle Contract |
      | Vehicles Model    | Vehicles Model   |

  @smoke
  Scenario Outline: Sales manager restricted options
    Given the user logs in as "sales manager"
    When the user navigates to "Fleet" "<option>"
    Then the user should see an error message

    Examples:
      | option                |
      | Vehicle Odometer      |
      | Vehicles Fuel Logs    |
      | Vehicle Services Logs |