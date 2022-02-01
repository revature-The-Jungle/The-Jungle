Feature: Users can collaborate within a group/team

  Scenario: Group member can create a group post
    Given the group member is on the group page
    When the group member clicks on the text box
    When the group member enters their post
    When the group member clicks the post button
    Then a success message will appear

  Scenario: Group member can delete a group post
    Given the group member is on the group page
    When the user clicks delete post button
    Then the post will be deleted

  Scenario: Group member can like a group post
    Given the group member is on the group page
    When the user clicks like post button
    Then the like count will increment by one

  Scenario: Group member can like a group post
    Given the group member is on the group page
    When the user clicks unlike comment button
    Then the like count will decrement by one

