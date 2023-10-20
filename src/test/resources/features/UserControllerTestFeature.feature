Feature: REST API functionalities

  Scenario: A user registers
    When A new user registers with email and password
    Then The user is added

    Scenario: A user logs in
      Given The user exists
      When The user enters valid credentials
      Then The user is authenticated

