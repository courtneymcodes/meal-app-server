Feature: Rest API functionalities

  Scenario: User is able to add and remove ingredients from their shopping list
    Given A user is currently logged in
    And A user has a shopping cart
    When I add an ingredient to my cart
    Then The ingredient is added
    When I remove an ingredient from my cart
    Then The ingredient is removed

