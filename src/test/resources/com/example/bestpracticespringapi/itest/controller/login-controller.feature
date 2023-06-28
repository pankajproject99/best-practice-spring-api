Feature: User Login
  Scenario: Successful login
    Given I am on the login page
    When I enter username "testuser" and password "password"
    And I click the login button
    Then I should be redirected to the home page
    And I should see "Welcome, testuser"