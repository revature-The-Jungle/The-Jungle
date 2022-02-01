Feature: Create Post

Scenario: As a user I want to create a post
  Given the user is on the homepage
  When the user clicks on the create post modal button
  When the user upload their photo
  When the user enters their post body
  When the user clicks on the post button
  Then the user will see the created post


Scenario: As a system I want to reject bad posts due to file size
  Given the user is on the homepage
  When the user clicks on the create post modal button
  When the user upload their photo that is too large
  When the user enters their post body
  When the user clicks on the post button
  Then the user will see an error message in the post feed

Scenario: As a system I want to reject bad posts
  Given the user is on the homepage
  When the user clicks on the create post modal button
  When the user upload their photo
  When the user enters their post body with too much text
  When the user clicks on the post button
  Then the user will see an error message in the post feed
