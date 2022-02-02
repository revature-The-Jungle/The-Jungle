Feature:  Search for Users

  Scenario: As a user I want to be able to Search for a user by username
    Given the user is on the homepage
    When user inputs username into the search input field
    When the user clicks the search button
    Then results will be displayed

  Scenario: as a System I don't want to allow incorrect usernames to be searched
    Given the user is on homepage
    When user inputs a username that doesn't exist into the search input field
    When the user clicks the search button
    Then no results will be displayed