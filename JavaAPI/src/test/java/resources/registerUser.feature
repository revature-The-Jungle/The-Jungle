Feature: Create Account

  Scenario: As a User, I should be able to register a new account, so that I can log into the system.
    Given the user is on the sign up page
    When the user enters First name into the new account form
    When the user enters Last name into the new account form
    When the user enters Date of Birth into the new account form
    When the user enters email into the new account form
    When the user enters a username into the new account form
    When the user enters a password into the new account form
    When the user enters the password again into the new account form
    When the user clicks on the submit button in the new account form
    Then the user will be redirected to their profile page