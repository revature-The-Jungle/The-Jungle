#Feature: Users should be able to live chat with other users
#  Scenario: User can join global chat
#    Given the user is on their dashboard/page
#    When the user clicks the chat room button
#    Then the users should be redirected to the global chat room page
#
#  Scenario: User can leave a chatroom
#    Given the user is in the chatroom
#    When the user clicks on the logo
#    Then they are redirected to their dashboard
#
#  Scenario: User can send a message
#    Given the user is in the chatroom
#    When the user clicks on the chat text box
#    When the user enters their chat message
#    When the user clicks the send chat message button
#    Then the message will appear in chat
#
#  Scenario: User join a different group chat
#    Given the user is in the chatroom
#    When the user clicks on the specific group chat in Chats
#    When the user clicks the alert button
#    Then they are redirected to their group chat of their selection
#
#  Scenario: User can send images through chatroom
#    Given the user is in the chatroom
#    When the user clicks on the Choose File button in chatroom
#    When the user selects their chat image they want to send
#    When the user clicks on the send chat message button
#    Then the image appears in the group chat
#
#  Scenario: User images that are too big does not get sent
#    Given the user is in the chatroom
#    When the user clicks on the Choose File button in chatroom
#    When the user selects their chat image that is too big
#    When the user clicks on the send chat message button
#    Then the user clicks the alert button saying File is too big to send
#
#  Scenario: User sees their previous chat history
#    Given the user is in the chatroom
#    When the user clicks on the chat text box
#    When the user enters their chat message
#    When the user clicks the send chat message button
#    When the user clicks on the refresh button
#    Then the user will see their previous messages
