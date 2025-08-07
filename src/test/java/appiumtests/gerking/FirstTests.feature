Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given the user login "Riot" account
    When the user enters a "ValidCredentials"
    And the user clicks the login button
    And the user clicks the play button
    And the user select "tutorial" as game mode
    When the user move champion 1 to the board
    And the user buy a new champion
    And the validate the gold reduction
    And the user move champion 2 to the board
    Then the user quits the game

#  Scenario: Unsuccessful login with invalid credentials
#    Given the user is on the login page
#    When the user enters an invalid username and password
#    And the user clicks the login button
#    Then an error message should be displayed
