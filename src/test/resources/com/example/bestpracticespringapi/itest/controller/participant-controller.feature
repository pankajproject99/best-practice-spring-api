Feature: Participant Controller Test
  Scenario: Get All Participant Successfully
    When I send a "GET" request to "/api/participants"
    Then I get a response code 200

