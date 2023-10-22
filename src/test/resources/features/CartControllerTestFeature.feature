Feature: Rest API functionalities

  Scenario: A cart can be created for a user
    Given A user is logged in
    When A post request is sent the cart endpoint
    Then The cart is created