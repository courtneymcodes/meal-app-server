Feature: Rest API functionalities

  Scenario: User is able to add, edit and remove a recipe
    Given A list of recipe favorites are available
    When I add a recipe to my favorites list
    Then The recipe is added
    When I edit a recipe
    Then The recipe content is edited
    When I remove recipe
    Then The recipe is removed
