Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given the user login "Riot" account
    When the user enters a "ValidCredentials"
    And the user clicks the login button
    And the user clicks the play button
    And the user select "tutorial" as game mode
