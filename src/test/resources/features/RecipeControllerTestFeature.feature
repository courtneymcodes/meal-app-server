Feature: Rest API functionalities

  Scenario: User is able to add, remove and edit a recipe
    Given A list of recipe favorites are available
    When I add a recipe to my favorites list
    Then The recipe is added
    When I remove recipe
    Then The recipe is removed
    When I edit a recipe
    Then The recipe content is edited