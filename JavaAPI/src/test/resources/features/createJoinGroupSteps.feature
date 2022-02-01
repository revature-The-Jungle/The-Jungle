#Feature: Users can collaborate within a group
#
#  Scenario: User can create a group
#    Given the user is on their dashboard page
#    When the user enters the group name
#    When the user enters the group description
#    When the user clicks the create button
#    Then a created group success message will appear
#
#  Scenario: User can join a group
#    Given the user is on their dashboard page
#    When the user clicks on the group they want to join
#    When the user is redirected to the group page
#    When the user is on the group page
#    When the user clicks on the join group button
#    Then a joined group success message will appear
#
#  Scenario: As the system, I want to reject a blank create group form
#    Given the user is on their dashboard page
#    When the user clicks the create button
#    Then both must enter group information messages will appear
#
#  Scenario: As the system, I want to reject a blank group name
#    Given the user is on their dashboard page
#    When the user enters the group description
#    When the user clicks the create button
#    Then a must enter group name message will appear
#
#  Scenario: As the system, I want to reject a blank group description name
#    Given the user is on their dashboard page
#    When the user enters the group name
#    When the user clicks the create button
#    Then a must enter group description message will appear
#
#  Scenario: As the system, I want to reject a group name that is too short
#    Given the user is on their dashboard page
#    When the user enters the group name that is too short
#    When the user enters the group description
#    When the user clicks the create button
#    Then a group name too short message will appear
#
#  Scenario: As the system, I want to reject a group name that is too long
#    Given the user is on their dashboard page
#    When the user enters a group name that is too long
#    When the user enters the group description
#    When the user clicks the create button
#    Then a group name too long message will appear
#
#  Scenario: As the system, I want to reject a group description that is too long
#    Given the user is on their dashboard page
#    When the user enters the group name
#    When the user enters a group description that is too long
#    When the user clicks the create button
#    Then a group description too long message will appear
#
#  Scenario: As the system, I want to reject a group name that is already taken
#    Given the user is on their dashboard page
#    When the user enters a group name that is already taken
#    When the user enters the group description
#    When the user clicks the create button
#    Then a group name already taken message will appear
